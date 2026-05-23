package com.sbisec.helios.ap.bizcommon.component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.model.AthenaErrorMessageModel;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignScheduleCashBalancesReq;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignScheduleCashBalancesResp;
import com.sbisec.helios.ap.athena.protocol.account.dto.CurrencyCashBalance;
import com.sbisec.helios.ap.athena.protocol.account.dto.ForeignCashBalance;
import com.sbisec.helios.ap.athena.protocol.account.dto.ForeignScheduleCashBalance;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
import com.sbisec.helios.ap.athena.utils.RequestUtil;
import com.sbisec.helios.ap.bizcommon.dao.Fct004Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct004Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct004Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct004Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct004Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct004Sql003ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.InputFct004Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct004Dto;
import com.sbisec.helios.ap.common.service.CometCommonService;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT004
 * IFAP米株 余力チェック（金額）
 *
 * @author SCSK
 */

@Component
public class Fct004 {
    
    @Autowired
    private Fct004Dao dao;
    
    @Autowired
    private ForeignAccountService foreignAccountService;
    
    @Autowired
    private CometCommonService cometCommonService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct004.class);
    
    /** 保証金振替(預り金→保証金) */
    private static final String CASH_DEPOSIT = "CASH_DEPOSIT";
    
    /** 通貨コード設定値"USD" */
    private static final String USD = "USD";
    
    /** GENERAL(一般) */
    private static final String GENERAL = "GENERAL";
    
    /** SPECIFIC(特定) */
    private static final String SPECIFIC = "SPECIFIC";
    
    /** SPECIFIC_MANAGED(特定管理) */
    private static final String SPECIFIC_MANAGED = "SPECIFIC_MANAGED";
    
    /** NISA */
    private static final String NISA = "NISA";
    
    /** JR_GENERAL(Jr一般) */
    private static final String JR_GENERAL = "JR_GENERAL";
    
    /** JR_SPECIFIC(Jr特定) */
    private static final String JR_SPECIFIC = "JR_SPECIFIC";
    
    /** JR_NISA(JrNISA) */
    private static final String JR_NISA = "JR_NISA";
    
    /** 取得日数設定値"4" */
    private static final int FOUR = 4;
    
    /** 買付 */
    private static final String PURCHASE = "3";
    
    /** T+3 */
    private static final int DAYS = 3;
    
    /**
     * IFAP米株 余力チェック（金額）
     *
     * @param input リクエストDto
     * @return レスポンスDto
     * @throws Exception IFAP米株 余力チェック（金額）時に例外が発生した場合
     */
    public OutputFct004Dto doCheck(InputFct004Dto input) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct004.doCheck");
        }
        
        OutputFct004Dto resDto = new OutputFct004Dto();
        
        // 必須項目判断処理
        // 部店コード, 口座番号, 預り区分のいずれかがない場合空のレスポンスを返却する
        if (
                StringUtil.isNullOrEmpty(input.getButenCode())
                || StringUtil.isNullOrEmpty(input.getAccountNumber())
                || StringUtil.isNullOrEmpty(input.getDepositType())
        ) {
            return resDto;
        }
        
        // ①当日外国債券買（金額）を取得する。
        // SQL001を発行し、遡る時間(SQL001.名称)を取得する
        Fct004Sql001ResponseModel sql001Res = new Fct004Sql001ResponseModel();
        try {
            sql001Res = dao.getTime();
        } catch (Exception e) {
            LOGGER.error("Fct004.doCheck Fct004DaoImpl.getTime Exception[{}]", e.getMessage());
        }
        
        // SQL002を発行し、外国債券の集計金額(SQL002.TOTAL_AMOUNT)を取得する
        Fct004Sql002RequestModel sql002Req = new Fct004Sql002RequestModel();
        sql002Req.setButenCode(input.getButenCode());
        sql002Req.setAccountNumber(input.getAccountNumber());
        sql002Req.setName(Integer.valueOf(sql001Res.getName()));
        Fct004Sql002ResponseModel sql002Res = new Fct004Sql002ResponseModel();
        try {
            sql002Res = dao.getForeignBondBuyAmount(sql002Req);
        } catch (Exception e) {
            LOGGER.error("Fct004.doCheck Fct004DaoImpl.getForeignBondBuyAmount Exception[{}]", e.getMessage());
        }
        
        // SQL002で0件だった場合、空のレスポンスを返却する
        if (sql002Res == null) {
            return resDto;
        }
        
        // 買付余力初期化
        BigDecimal purchaseSpare = BigDecimal.ZERO;
        // ②Comet-APIからリアルタイム余力を取得する
        // リクエスト.取引区分=="CASH_DEPOSIT"{保証金振替(預り金→保証金)}の場合
        if (StringUtils.equals(input.getTradeType(), CASH_DEPOSIT)) {
            // 買付余力="０"
            purchaseSpare = BigDecimal.ZERO;
        } else {
            ListForeignScheduleCashBalancesReq api001Input = new ListForeignScheduleCashBalancesReq();
            // Header.tokenとしてリクエスト.部店コード + "-" + リクエスト.口座番号をセット
            api001Input.getHeader().setToken(RequestUtil.getToken(input.getButenCode(), input.getAccountNumber()));
            
            // 通貨コード(パラメータ.currencyCode)として"USD"をセット
            api001Input.getParameter().setCurrencyCode(USD);
            // 口座分類(パラメータ.accountKind)として以下で決定した"GENERAL"(総合)または"JR_NISA"(ジュニアNIS)をセット
            // "GENERAL"：リクエスト.預り区分がGENERAL(一般)・SPECIFIC(特定)・SPECIFIC_MANAGED(特定管理)・NISA のいずれかの場合
            if (StringUtils.equals(input.getDepositType(), GENERAL)
                    || StringUtils.equals(input.getDepositType(), SPECIFIC)
                    || StringUtils.equals(input.getDepositType(), SPECIFIC_MANAGED)
                    || StringUtils.equals(input.getDepositType(), NISA)) {
                api001Input.getParameter().setAccountKind(GENERAL);
            }
            // "JR_NISA"：リクエスト.預かり区分がJR_GENERAL(Jr一般)・JR_SPECIFIC(Jr特定)・JR_NISA(JrNISA) のいずれかの場合
            if (StringUtils.equals(input.getDepositType(), JR_GENERAL)
                    || StringUtils.equals(input.getDepositType(), JR_SPECIFIC)
                    || StringUtils.equals(input.getDepositType(), JR_NISA)) {
                api001Input.getParameter().setAccountKind(JR_NISA);
            }
            // 取得日数(パラメータ.days)として'4'をセット
            api001Input.getParameter().setDays(FOUR);
            // Api001返却想定データ初期化
            ListForeignScheduleCashBalancesResp api001Output = new ListForeignScheduleCashBalancesResp();
            // API001(外貨金銭残高スケジュール取得API)を呼び出し、T+3の買付余力を取得する
            try {
                api001Output = foreignAccountService.listForeignScheduleCashBalances(api001Input);
                
                // API001が正常終了しなかった場合
            } catch (Exception e) {
                if (e instanceof AthenaBusinessException) {
                    String errorCode = ((AthenaBusinessException) e).getErrorCode();
                    AthenaErrorMessageModel apiStatusModel = cometCommonService.getAthenaErrorCodeAndMessage(errorCode);
                    ((AthenaBusinessException) e).setMessage(apiStatusModel.getErrorMessage());
                }

                throw e;
                // レスポンス.エラーコードにAPI001から出力されるエラーコードを設定
                // resDto.setReturnCode(((AthenaBusinessException) e).getErrorCode());
                // レスポンスを返却する
                // return resDto;
            }
            
            if (api001Output != null) {
                if (api001Output.getForeignCashBalances() != null) {
                    for (ForeignCashBalance fc : api001Output.getForeignCashBalances()) {
                        if (fc.getCurrencyCashBalances() != null) {
                            for (CurrencyCashBalance cc : fc.getCurrencyCashBalances()) {
                                if (cc.getForeignScheduleCashBalances() != null) {
                                    for (ForeignScheduleCashBalance fs : cc.getForeignScheduleCashBalances()) {
                                        if (!StringUtil.isNullOrEmpty(fs.getBuyPossibleAmount())
                                                && DAYS == fs.getDaysLater()) {
                                            // T+3の買付余力取得
                                            purchaseSpare = new BigDecimal(fs.getBuyPossibleAmount());
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        // ③当日店頭注文（買付）情報を取得する。
        // SQL003を発行し、店頭取引注文情報を取得する
        Fct004Sql003RequestModel sql003Req = new Fct004Sql003RequestModel();
        sql003Req.setButenCode(input.getButenCode());
        sql003Req.setAccountNumber(input.getAccountNumber());
        List<Fct004Sql003ResponseModel> sql003Res = new ArrayList<>();
        try {
            sql003Res = dao.getShopTransactionOrder(sql003Req);
        } catch (Exception e) {
            LOGGER.error("Fct004.doCheck Fct004DaoImpl.getShopTransactionOrder Exception[{}]", e.getMessage());
        }
        // 店頭注文情報が存在しない&&SQL002.TOTAL_AMOUNT==0の場合
        if (sql003Res.size() == 0 && sql002Res.getTotalAmount().compareTo(BigDecimal.ZERO) == 0) {
            // レスポンス.計算後の余力金額に②で取得した買付余力を設定する。
            resDto.setByingPowerMoneyAfterCalculate(purchaseSpare);
            // レスポンス.当日店頭買付約定金額に"0"を設定する。
            resDto.setOtcBuyingContractAmountToday(BigDecimal.ZERO);
            // レスポンス.外国債券の当日約定金額（買付)に"0"を設定する。
            resDto.setContractAmountTodayWithinForeignBond(BigDecimal.ZERO);
            // レスポンス.リアルタイム余力（買付）に②で取得した買付余力を設定する。
            resDto.setRealTimeBuyingPower(purchaseSpare);
            // レスポンスを返却する。
            return resDto;
        }
        
        // 店頭買（金額)初期化
        BigDecimal shopBuy = BigDecimal.ZERO;
        // 店頭注文情報が存在する場合
        if (sql003Res.size() > 0) {
            // リクエスト.店頭管理番号が存在する場合(店頭ワーニング受付、店頭ステータス訂正）の場合
            if (!StringUtil.isNullOrEmpty(input.getOtcManageNumber())) {
                // 店頭買（金額)＝以下の条件を満たすSQ003の出力レコードの約定金額の合計値
                // ・SQL003.管理番号!=リクエスト.店頭管理番号
                // ・SQL003.売買区分=='3'(買付)
                for (int i = 0; i < sql003Res.size(); i++) {
                    if (!StringUtils.equals(sql003Res.get(i).getOrderNo(), input.getOtcManageNumber())
                            && StringUtils.equals(sql003Res.get(i).getTradeType(), PURCHASE)) {
                        shopBuy = shopBuy.add(sql003Res.get(i).getAppointPrice());
                    }
                }
            } else {
                // 店頭買（金額)＝以下の条件を満たすSQ003の出力レコードの約定金額の合計値
                // ・SQL003.売買区分=='3'(買付)
                for (int i = 0; i < sql003Res.size(); i++) {
                    if (StringUtils.equals(sql003Res.get(i).getTradeType(), PURCHASE)) {
                        shopBuy = shopBuy.add(sql003Res.get(i).getAppointPrice());
                    }
                }
            }
        }
        
        // 米株余力（金額）初期化
        BigDecimal usaSpare = BigDecimal.ZERO;
        // ④米株余力（金額）を計算する。
        // 米株余力（金額）＝ ②買付余力　-　③店頭買（金額）-　①外国債券の集計金額
        BigDecimal tmp01 = purchaseSpare;
        // ②買付余力　-　③店頭買（金額）
        usaSpare = tmp01.subtract(shopBuy);
        // ②買付余力　-　①外国債券の集計金額
        usaSpare = usaSpare.subtract(sql002Res.getTotalAmount());
        
        // ⑤下記の戻り値を設定して返す。
        // レスポンス.計算後の余力金額に④米株余力（金額）を設定する
        resDto.setByingPowerMoneyAfterCalculate(usaSpare);
        // レスポンス.当日店頭買付約定金額に③店頭買（金額）を設定する
        resDto.setOtcBuyingContractAmountToday(shopBuy);
        // レスポンス.外国債券の当日約定金額（買付)に①外国債券の集計金額を設定する。
        BigDecimal totalAmount = sql002Res.getTotalAmount();
        resDto.setContractAmountTodayWithinForeignBond(totalAmount);
        // レスポンス.リアルタイム余力（買付）に②買付余力を設定する。
        resDto.setRealTimeBuyingPower(purchaseSpare);
        // レスポンスを返却する。
        return resDto;
    }
    
}
