package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.protocol.account.CashDeposit;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignScheduleCashBalancesResp;
import com.sbisec.helios.ap.athena.protocol.account.dto.CurrencyCashBalance;
import com.sbisec.helios.ap.athena.protocol.account.dto.ForeignCashBalance;
import com.sbisec.helios.ap.athena.protocol.account.dto.ForeignScheduleCashBalance;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct004;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct004Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct004Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerForeignA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerForeignA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerForeignA001DtoResponseBycountry;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerForeignA001DtoResponseBycountryBuyingPowerJuniorAccount;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerForeignA001DtoResponseBycountryBuyingPowerWholeAccount;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerForeignA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaBuyingPowerForeignService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_010303-01
 * 画面名：買付余力(外国)

 * @author 渡辺　祐斗
    2023/11/27 新規作成

 */
@Component(value = "cmpIfaBuyingPowerForeignService")
public class IfaBuyingPowerForeignServiceImpL implements IfaBuyingPowerForeignService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBuyingPowerForeignServiceImpL.class);
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct004 fct004;
    
    @Autowired
    private ForeignAccountService foreignAccountService;

    @Autowired
    private CometCommonService cometCommonService;
    
    /** 権限チェックエラー */
    public static final String ERRORS_BUTEN_ACCOUNT_NOTEXISTS = "errors.butenAccountNotExist";
    
    /** 外貨建口座未開設エラー */
    public static final String ERRORS_CMN_FOREIGN_SECURITIES_ACCOUNT_NOTOPEN = "errors.cmn.foreignSecuritiesAccount.notOpen";
    
    /** 権限チェックエラー値 「権限なし」 */
    public static final String TARGET_CUSTOMER_REF_AUTH_FLAG = "0";
    
    /** 外貨建口座未開設エラー値 「外貨建商品取引口座開設状況：未開設」 */
    public static final String FOREIGN_COMMODITY_TRADE_ACCOUNT_OPEN_STATUS = "0";
    
    /** 外貨建口座未開設エラー値 「外国株式取引口座開設状況：未開設」 */
    public static final String FOREIGN_STOCK_TRADE_ACCOUNT_OPEN_STATUS = "0";
    
    /** 外貨金銭残高スケジュール取得APIのパラメータ 取得日数 */
    private static final Integer FOREIGNSCHEDULECASHBALANCES_API_PARAM_DAYS = 6;
    
    /** 外貨金銭残高スケジュール取得APIのパラメータ 口座分類 */
    public static final String GENERAL_ACCOUNT = "GENERAL";
    
    /** 外貨金銭残高スケジュール取得APIのパラメータ 口座分類 */
    public static final String JR_NISA_ACCOUNT = "JR_NISA";
    
    /** 外貨金銭残高スケジュール取得APIのパラメータ 通貨コード */
    public static final String USD = "USD";
    
    /** 対象口座文字列 現物取引（総合口座） */
    private static final String DEPOSIT_TYPE_GENERAL = "GENERAL";
    
    /** 何営業日後（日数） "当日" */
    private static final String BUSINESS_DAYS_TODAY = "当日";
    
    /** 何営業日後（日数） "1営業日後" */
    private static final String BUSINESS_DAYS_ONE_DAYS = "1営業日後";
    
    /** 何営業日後（日数） "2営業日後" */
    private static final String BUSINESS_DAYS_TWO_DAYS = "2営業日後";
    
    /** 何営業日後（日数） "3営業日後" */
    private static final String BUSINESS_DAYS_THREE_DAYS = "3営業日後";
    
    /** 何営業日後（日数） "4営業日後" */
    private static final String BUSINESS_DAYS_FOUR_DAYS = "4営業日後";
    
    /** 何営業日後（日数） "5営業日後" */
    private static final String BUSINESS_DAYS_FIVE_DAYS = "5営業日後";
    
    /** SWITCH文 ケース:0 */
    private static final int CASE_ZERO = 0;
    
    /** SWITCH文 ケース:1 */
    private static final int CASE_ONE = 1;
    
    /** SWITCH文 ケース:2 */
    private static final int CASE_TWO = 2;
    
    /** SWITCH文 ケース:3 */
    private static final int CASE_THREE = 3;
    
    /** SWITCH文 ケース:4 */
    private static final int CASE_FOUR = 4;
    
    /** SWITCH文 ケース:5 */
    private static final int CASE_FIVE = 5;
    
    /** ブランク設定 */
    private static final String BLANK_SPACE = " ";
    
    /** 桁数フォーマットによる"7桁" */
    private static final String SEVEN = "%7s";
    
    /** 桁数フォーマットによる"前0埋め" */
    private static final String ZERO = "0";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaBuyingPowerForeignA001DtoRequest
     * Dto レスポンス：IfaBuyingPowerForeignA001DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel

     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @throws Exception API取得時に例外が発生した場合
     */
    public DataList<IfaBuyingPowerForeignA001DtoResponse> initializeA001(IfaBuyingPowerForeignA001DtoRequest dtoReq)
            throws Exception {
        
        DataList<IfaBuyingPowerForeignA001DtoResponse> dtoRes = new DataList<IfaBuyingPowerForeignA001DtoResponse>();
        List<IfaBuyingPowerForeignA001DtoResponse> resMainList = new ArrayList<>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaBuyingPowerForeignServiceImplL.initializeA001");
            
        }
        
        // 顧客共通情報
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // Fct001
        InputFct001Dto inptFct001Dto = new InputFct001Dto();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        inptFct001Dto.setButenCode(butenCode);
        inptFct001Dto.setAccountNumber(accountNumber);
        OutputFct001Dto outFct001Dto = fct001.doCheck(inptFct001Dto);
        
        // Fct001　利用者の口座に対する権限チェックを行う
        // ①対象顧客参照権限有無＝権限なしの場合：権限なしエラーを返す
        if (outFct001Dto == null || StringUtils.equals(outFct001Dto.getTargetCustomerRefAuthFlag(),
                IfaBuyingPowerForeignServiceImpL.TARGET_CUSTOMER_REF_AUTH_FLAG)) {
            // 業務エラーメッセージの取得
            dtoRes = IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL,
                    IfaBuyingPowerForeignServiceImpL.ERRORS_BUTEN_ACCOUNT_NOTEXISTS,
                    IfaCommonUtil.getMessage(IfaBuyingPowerForeignServiceImpL.ERRORS_BUTEN_ACCOUNT_NOTEXISTS,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            return dtoRes;
        }
        
        // ②顧客共通情報の「外貨建取引口座開設状況」「外国株式取引口座開設状況」より、外貨建口座取引開設状況をチェックを行う。
        if (StringUtils.equals(cc.getForeignStockTradeAccountOpenStatus(),
                IfaBuyingPowerForeignServiceImpL.FOREIGN_COMMODITY_TRADE_ACCOUNT_OPEN_STATUS)
                && StringUtils.equals(cc.getForeignSecurityTradeAccountOpenStatus(),
                        IfaBuyingPowerForeignServiceImpL.FOREIGN_STOCK_TRADE_ACCOUNT_OPEN_STATUS)) {
            // 業務エラーメッセージの取得
            dtoRes = IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL,
                    IfaBuyingPowerForeignServiceImpL.ERRORS_CMN_FOREIGN_SECURITIES_ACCOUNT_NOTOPEN,
                    IfaCommonUtil.getMessage(IfaBuyingPowerForeignServiceImpL.ERRORS_CMN_FOREIGN_SECURITIES_ACCOUNT_NOTOPEN));
            return dtoRes;
        }
        // ③預り金一括取得APIを呼び出し、API002.預り金リスト.預り金が0でないAPI002.預り金リスト.通貨コードを取得する（最大17個)
        List<String> currencyCodes = new ArrayList<>();
        try {
            currencyCodes = getApi002CurrencyCodes();
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaBuyingPowerForeignServiceImplL.initializeA001}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        // ④外貨金銭残高スケジュール取得
        List<String> checkCurrencyCodes = List.of("HKD","IDR","KRW","MYR","RUB","SGD","THB","USD","VND");
        List<ListForeignScheduleCashBalancesResp> api002ResList = new ArrayList<>();
        // 口座番号は7桁前0埋め処理
        String accountNo = (String.format(SEVEN, cc.getAccountNumber()).replace(BLANK_SPACE, ZERO));
        // ③で取得した通貨コードに下記のいずれかの通貨コードが含まれる場合、下記のAPI入力でAPIを呼び出す
        if (currencyCodes.stream().anyMatch(checkCurrencyCodes::contains)) {
            try {
                api002ResList.add(foreignAccountService.listForeignScheduleCashBalances(
                        cc.getButenCode(), accountNo, null, null, FOREIGNSCHEDULECASHBALANCES_API_PARAM_DAYS));
            } catch (Exception e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("{IfaBuyingPowerForeignServiceImplL.initializeA001}", e);
                }
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
        }
        
        // ③で取得した通貨コードに下記に該当しない通貨コードが含まれる場合、該当する通貨コードの数だけ繰返し下記のAPI入力でAPI呼び出す
        List<String> notMatchCurrencyCodes = currencyCodes.stream()
        .filter(currencyCode -> !checkCurrencyCodes.contains(currencyCode)).collect(Collectors.toList());;
        
        for (String currencyCode : notMatchCurrencyCodes) {
            try {
                api002ResList.add(foreignAccountService.listForeignScheduleCashBalances(
                        cc.getButenCode(), accountNo, currencyCode, null, FOREIGNSCHEDULECASHBALANCES_API_PARAM_DAYS));
            } catch (Exception e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("{IfaBuyingPowerForeignServiceImplL.initializeA001}", e);
                }
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
        }
        
        // ④買付余力（総合口座）の編集（API001の外貨金銭残高スケジュール.口座分類＝GENERAL（総合口座）の場合）
        // 国別情報を取得するためにIfaBuyingPowerForeignA001DtoResponseBycountryオブジェクト生成        
        List<IfaBuyingPowerForeignA001DtoResponseBycountry> byCountryList = new ArrayList<>();
        for (ListForeignScheduleCashBalancesResp listForeignScheduleCashBalancesResp : api002ResList) {
            byCountryList.addAll(getByCountry(listForeignScheduleCashBalancesResp, cc));
        }
        
        //　IfaBuyingPowerForeignA001DtoResponseオブジェクト生成
        IfaBuyingPowerForeignA001DtoResponse resMain = new IfaBuyingPowerForeignA001DtoResponse();
        
        
        resMain.setByCountryList(byCountryList);
        resMainList.add(resMain);
        dtoRes = IfaCommonUtil.createDataList(resMainList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), "");
        
        return dtoRes;
        
    }
    
    /**
     * アクションID：A002
     * アクション名：再検索
     * Dto リクエスト：IfaBuyingPowerForeignA002DtoRequest
     * Dto レスポンス：IfaBuyingPowerForeignA002DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel

     *  @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @throws Exception API取得時に例外が発生した場合
     */
    public DataList<IfaBuyingPowerForeignA001DtoResponse> reSearchA002(IfaBuyingPowerForeignA002DtoRequest dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaBuyingPowerForeignServiceImplL.reSearchA002");
        }
        
        // 顧客共通情報
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        List<IfaBuyingPowerForeignA001DtoResponseBycountry> byCountryList = new ArrayList<>();
        List<IfaBuyingPowerForeignA001DtoResponse> resMainList = new ArrayList<>();
        IfaBuyingPowerForeignA001DtoResponse resMain = new IfaBuyingPowerForeignA001DtoResponse();
        DataList<IfaBuyingPowerForeignA001DtoResponse> dtoRes = new DataList<IfaBuyingPowerForeignA001DtoResponse>();
        
        // ③預り金一括取得APIを呼び出し、API002.預り金リスト.預り金が0でないAPI002.預り金リスト.通貨コードを取得する（最大17個)
        List<String> currencyCodes = new ArrayList<>();
        try {
            currencyCodes = getApi002CurrencyCodes();
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaBuyingPowerForeignServiceImplL.initializeA001}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        // ④外貨金銭残高スケジュール取得
        List<String> checkCurrencyCodes = List.of("HKD","IDR","KRW","MYR","RUB","SGD","THB","USD","VND");
        List<ListForeignScheduleCashBalancesResp> api002ResList = new ArrayList<>();
        // 口座番号は7桁前0埋め処理
        String accountNo = (String.format(SEVEN, cc.getAccountNumber()).replace(BLANK_SPACE, ZERO));
        // ③で取得した通貨コードに下記のいずれかの通貨コードが含まれる場合、下記のAPI入力でAPIを呼び出す
        if (currencyCodes.stream().anyMatch(checkCurrencyCodes::contains)) {
            try {
                api002ResList.add(foreignAccountService.listForeignScheduleCashBalances(
                        cc.getButenCode(), accountNo, null, null, FOREIGNSCHEDULECASHBALANCES_API_PARAM_DAYS));
            } catch (Exception e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("{IfaBuyingPowerForeignServiceImplL.initializeA001}", e);
                }
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
        }
        
        // ③で取得した通貨コードに下記に該当しない通貨コードが含まれる場合、該当する通貨コードの数だけ繰返し下記のAPI入力でAPI呼び出す
        List<String> notMatchCurrencyCodes = currencyCodes.stream()
        .filter(currencyCode -> !checkCurrencyCodes.contains(currencyCode)).collect(Collectors.toList());
        
        for (String currencyCode : notMatchCurrencyCodes) {
            try {
                api002ResList.add(foreignAccountService.listForeignScheduleCashBalances(
                        cc.getButenCode(), accountNo, currencyCode, null, FOREIGNSCHEDULECASHBALANCES_API_PARAM_DAYS));
            } catch (Exception e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("{IfaBuyingPowerForeignServiceImplL.initializeA001}", e);
                }
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
        }
        
        // ④買付余力（総合口座）の編集（API001の外貨金銭残高スケジュール.口座分類＝GENERAL（総合口座）の場合）
        // 国別情報を取得するためにIfaBuyingPowerForeignA001DtoResponseBycountryオブジェクト生成        
        for (ListForeignScheduleCashBalancesResp listForeignScheduleCashBalancesResp : api002ResList) {
            byCountryList.addAll(getByCountry(listForeignScheduleCashBalancesResp, cc));
        }
        
        // 買付余力（総合・ジュニア口座）の編集
        resMain.setByCountryList(byCountryList);
        resMainList.add(resMain);
        dtoRes = IfaCommonUtil.createDataList(resMainList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), "");
        
        return dtoRes;
    }
    
    // 営業日毎に以下の買付余力（総合口座・ジュニア口座）リストの編集処理を繰り返すメソッド
    // A001.A002において同様の処理を行うためメソッドを作成し呼び出しを行う  
    private List<IfaBuyingPowerForeignA001DtoResponseBycountry> getByCountry(
            ListForeignScheduleCashBalancesResp listForeignScheduleCashBalancesResp, CustomerCommon cc)  throws Exception {
        
        // 繰返し処理の初回のみ実行のためのフラグ設定
        boolean firstFlag = true;
        List<IfaBuyingPowerForeignA001DtoResponseBycountry> byCountryList = new ArrayList<>();
        // 外貨現金残高情報のリストを取得し、リストから一つずつ"ForeignCashBalance"クラスのオブジェクトを取り出しfcbに代入し、処理を行う。
        for (ForeignCashBalance fcb : listForeignScheduleCashBalancesResp.getForeignCashBalances()) {
            // 
            boolean flag = false;
            // 総合口座の場合
            if (GENERAL_ACCOUNT.equals(fcb.getAccountKind())) {
                // IfaBuyingPowerForeignA001DtoResponseBycountryBuyingpowerwholeaccountクラスのオブジェクトに格納するため
                // 外貨金銭残高スケジュール(全通貨)繰り返し処理を行うためにリストをforで繰り返し
                for (CurrencyCashBalance ccb : fcb.getCurrencyCashBalances()) {
                    // buyingPowerwholeaccountListとして用意
                    List<IfaBuyingPowerForeignA001DtoResponseBycountryBuyingPowerWholeAccount> buyingPowerwholeAccountList = new ArrayList<>();
                    // <IfaBuyingPowerForeignA001DtoResponseBycountry>オブジェクトを生成
                    IfaBuyingPowerForeignA001DtoResponseBycountry byCountry = new IfaBuyingPowerForeignA001DtoResponseBycountry();
                    OutputFct004Dto outputFct004Dto = new OutputFct004Dto();
                    for (ForeignScheduleCashBalance fsb : ccb.getForeignScheduleCashBalances()) {
                        // 総合口座の各変数に値を入れて編集のための<IfaBuyingPowerForeignA001DtoResponse_Bycountry_Buyingpowerwholeaccount>オブジェクトを生成
                        IfaBuyingPowerForeignA001DtoResponseBycountryBuyingPowerWholeAccount buyingPowerWholeAccount = new IfaBuyingPowerForeignA001DtoResponseBycountryBuyingPowerWholeAccount();
                        // 国内受渡日
                        buyingPowerWholeAccount.setDomesticSettlementDate(fsb.getBusinessDate());
                        // 営業日後
                        if (fsb.getDaysLater() == null) {
                            buyingPowerWholeAccount.setSettlementDateAfterBusinessDay(null);
                        } else {
                            buyingPowerWholeAccount
                                    .setSettlementDateAfterBusinessDay(getBusinessDayString(fsb.getDaysLater()));
                        }
                        // 預り金（数値(整数)）
                        buyingPowerWholeAccount.setYenDeposit(fsb.getKeepCash());
                        // 通貨
                        buyingPowerWholeAccount.setCurrencyCode(ccb.getCurrencyCode());
                        // 入金額（数値(整数)）
                        buyingPowerWholeAccount.setDepositAmount(fsb.getCashReceipt());
                        // 支払額（数値(整数)）
                        buyingPowerWholeAccount.setPayment(fsb.getPaymentAmount());
                        // 未約定買注文額（数値(整数)）
                        buyingPowerWholeAccount.setUncontractedPurchaseOrderAmount(fsb.getFixedOrderAmount());
                        // 出金額（数値(整数)）
                        buyingPowerWholeAccount.setWithdrawAmount(fsb.getAmountPayValue());
                        // 受取額（数値(整数)）
                        buyingPowerWholeAccount.setAmountReceived(fsb.getReceiveAmountValue());
                        // 受取額（日計り分）（数値(整数)）
                        buyingPowerWholeAccount.setDayTrading(fsb.getFixedDayTradeAmount());
                        // 振替予定額（信用口座→現物口座）
                        buyingPowerWholeAccount.setTransferPlanAmount(fsb.getTransferEstimatedMarginAmount());
                        // 残高（預り金）（数値(整数)）
                        buyingPowerWholeAccount.setDepositTotalBalance(fsb.getRemainingBuyPossibleAmount());
                        // 必要精算額
                        buyingPowerWholeAccount.setNeedSettleAmount(fsb.getNeedSettlementValue());
                        // 買付余力1
                        buyingPowerWholeAccount.setBuyingPower(fsb.getBuyPossibleAmount());
                        
                        // FAPI001.外貨金銭残高スケジュール.外貨金銭残高スケジュール(全通貨).通貨コード＝"USD"の場合のみ以下を行う
                        if (USD.equals(ccb.getCurrencyCode())) {
                            // IFAP米株 余力チェック（金額）の取得 (繰返し処理の初回のみ実行)
                            if (firstFlag) {
                                firstFlag = false;
                                //・FCT004より取得した項目をもとに以下を設定
                                InputFct004Dto inputFct004Dto = new InputFct004Dto();
                                inputFct004Dto.setButenCode(cc.getButenCode());
                                inputFct004Dto.setAccountNumber(cc.getAccountNumber());
                                inputFct004Dto.setDepositType(DEPOSIT_TYPE_GENERAL);
                                inputFct004Dto.setOtcManageNumber(BLANK_SPACE);
                                inputFct004Dto.setTradeType(BLANK_SPACE);
                                outputFct004Dto = fct004.doCheck(inputFct004Dto);
                            }
                            
                            // ・買付余力（総合口座）リスト.当日米株店頭買付約定金額 
                            // FCT004.当日店頭買付約定金額をマイナス値にし設定
                            BigDecimal bigDecimlOtcBuyingContractAmountToday = outputFct004Dto
                                    .getOtcBuyingContractAmountToday().negate();
                            buyingPowerWholeAccount.setTodayAmericaCounterBuyTradeAmount(
                                    bigDecimlOtcBuyingContractAmountToday.toString());
                            
                            // ・買付余力（総合口座）リスト.当日外債買付代金
                            BigDecimal bigDecimlContractAmountTodayWithinForeignBond = outputFct004Dto
                                    .getContractAmountTodayWithinForeignBond().negate();
                            buyingPowerWholeAccount.setTodayForeignBondBuyAmount(
                                    bigDecimlContractAmountTodayWithinForeignBond.toString());
                            
                            // ・買付余力（総合口座）リスト.買付余力2
                            // fsb.getBuyPossibleAmount()がnullの場合BuyingPower2にnullをセット
                            if (fsb.getBuyPossibleAmount() == null) {
                                buyingPowerWholeAccount.setBuyingPower2(null);
                            } else {
                                // 買付余力（総合口座）リスト.買付余力１　-　FCT004.当日店頭買付約定金額　-　FCT004.外国債券の当日約定金額（買付） ※API001で取得した各営業日毎の買付余力に対して計算する。
                                BigDecimal buyingPower2 = new BigDecimal(fsb.getBuyPossibleAmount())
                                        .subtract(outputFct004Dto.getOtcBuyingContractAmountToday())
                                        .subtract(outputFct004Dto.getContractAmountTodayWithinForeignBond());
                                buyingPowerWholeAccount.setBuyingPower2(buyingPower2.toString());
                            }
                        }
                        // buyingPowerwholeAccountListにbuyingPowerWholeAccountオブジェクトを追加
                        buyingPowerwholeAccountList.add(buyingPowerWholeAccount);
                    }
                    
                    // 通貨別にList<IfaBuyingPowerForeignA001DtoResponseBycountry> byCountryListに値をセットする処理
                    if (byCountryList != null && byCountryList.size() > 0) {
                        int index = 0;
                        for (IfaBuyingPowerForeignA001DtoResponseBycountry bc : byCountryList) {
                            if (bc.getBuyingPowerJuniorAccountList() != null
                                    && bc.getBuyingPowerJuniorAccountList().size() > 0
                                    && bc.getBuyingPowerJuniorAccountList().get(0).getCurrencyCode() != null
                                    && bc.getBuyingPowerJuniorAccountList().get(0).getCurrencyCode()
                                            .equals(ccb.getCurrencyCode())) {
                                bc.setBuyingPowerWholeAccountList(buyingPowerwholeAccountList);
                                byCountryList.set(index, bc);
                                flag = true;
                                break;
                            }
                            index++;
                        }
                    }
                    
                    // 通貨が別な場合値をセットする処理
                    if (flag == false) {
                        byCountry.setBuyingPowerWholeAccountList(buyingPowerwholeAccountList);
                        byCountryList.add(byCountry);
                    }
                }
                // JrNISA口座の場合
            } else if (JR_NISA_ACCOUNT.equals(fcb.getAccountKind())) {
                // 外貨金銭残高スケジュール(全通貨)繰り返し処理を行うためにリストをforで繰り返し
                for (CurrencyCashBalance ccb : fcb.getCurrencyCashBalances()) {
                    // IfaBuyingPowerForeignA001DtoResponseBycountryBuyingpowerjunioraccountクラスのオブジェクトに格納するための入れ物をbuyingPowerwholeaccountListとして用意
                    List<IfaBuyingPowerForeignA001DtoResponseBycountryBuyingPowerJuniorAccount> buyingPowerJuniorAccountList = new ArrayList<>();
                    // <IfaBuyingPowerForeignA001DtoResponseBycountry>オブジェクトを生成
                    IfaBuyingPowerForeignA001DtoResponseBycountry byCountry = new IfaBuyingPowerForeignA001DtoResponseBycountry();
                    for (ForeignScheduleCashBalance fsb : ccb.getForeignScheduleCashBalances()) {
                        // 総合口座の各変数に値を入れて編集のための<IfaBuyingPowerForeignA001DtoResponseBycountryBuyingpowerjunioraccount>オブジェクトを生成
                        IfaBuyingPowerForeignA001DtoResponseBycountryBuyingPowerJuniorAccount buyingPowerJuniorAccount = 
                                new IfaBuyingPowerForeignA001DtoResponseBycountryBuyingPowerJuniorAccount();
                        // 国内受渡日
                        buyingPowerJuniorAccount.setDomesticSettlementDate(fsb.getBusinessDate());
                        // 営業日後
                        if (fsb.getDaysLater() == null) {
                            buyingPowerJuniorAccount.setSettlementDateAfterBusinessDay(null);
                        } else {
                            buyingPowerJuniorAccount
                                    .setSettlementDateAfterBusinessDay(getBusinessDayString(fsb.getDaysLater()));
                        }
                        // 預り金（数値(整数)）
                        buyingPowerJuniorAccount.setYenDeposit(fsb.getKeepCash());
                        // 通貨
                        buyingPowerJuniorAccount.setCurrencyCode(ccb.getCurrencyCode());
                        // 入金額（数値(整数)）
                        buyingPowerJuniorAccount.setDepositAmount(fsb.getCashReceipt());
                        // 支払額（数値(整数)）
                        buyingPowerJuniorAccount.setPayment(fsb.getPaymentAmount());
                        // 未約定買注文額（数値(整数)）
                        buyingPowerJuniorAccount.setUncontractedPurchaseOrderAmount(fsb.getFixedOrderAmount());
                        // 出金額（数値(整数)）
                        buyingPowerJuniorAccount.setWithdrawAmount(fsb.getAmountPayValue());
                        // 受取額（数値(整数)）
                        buyingPowerJuniorAccount.setAmountReceived(fsb.getReceiveAmountValue());
                        // 受取額（日計り分）（数値(整数)）
                        buyingPowerJuniorAccount.setDayTrading(fsb.getFixedDayTradeAmount());
                        // 振替予定額(総合口座→ジュニアNISA口座)
                        buyingPowerJuniorAccount.setTransferPlanAmount(fsb.getTransferEstimatedAmount());
                        // 残高（預り金）（数値(整数)）
                        buyingPowerJuniorAccount.setDepositTotalBalance(fsb.getRemainingBuyPossibleAmount());
                        // 振替可能額(総合口座→ジュニアNISA口座)
                        buyingPowerJuniorAccount.setTransferAbleAmount(fsb.getTransferPossibleAmount());
                        // 必要精算額
                        buyingPowerJuniorAccount.setNeedSettleAmount(fsb.getNeedSettlementValue());
                        // 買付余力
                        buyingPowerJuniorAccount.setBuyingPower(fsb.getBuyPossibleAmount());
                        
                        buyingPowerJuniorAccountList.add(buyingPowerJuniorAccount);
                    }
                    
                    // 通貨別にList<IfaBuyingPowerForeignA001DtoResponseBycountryBuyingPowerJuniorAccount> buyingPowerJuniorAccountListに値をセットする処理
                    if (byCountryList != null && byCountryList.size() > 0) {
                        int index = 0;
                        for (IfaBuyingPowerForeignA001DtoResponseBycountry bc : byCountryList) {
                            if (bc.getBuyingPowerWholeAccountList() != null
                                    && bc.getBuyingPowerWholeAccountList().size() > 0
                                    && bc.getBuyingPowerWholeAccountList().get(0).getCurrencyCode() != null
                                    && bc.getBuyingPowerWholeAccountList().get(0).getCurrencyCode()
                                            .equals(ccb.getCurrencyCode())) {
                                bc.setBuyingPowerJuniorAccountList(buyingPowerJuniorAccountList);
                                byCountryList.set(index, bc);
                                flag = true;
                                break;
                            }
                            index++;
                        }
                    }
                    
                    // 通貨が別な場合値をセットする処理
                    if (flag == false) {
                        byCountry.setBuyingPowerJuniorAccountList(buyingPowerJuniorAccountList);
                        byCountryList.add(byCountry);
                    }
                }
            }
        }
        return byCountryList;
        
    }
    
    /**
     * 預り金一括取得APIを呼び出し
     * @return 預り金リスト.通貨コード
     * @throws Exception
     */
    private List<String> getApi002CurrencyCodes() throws Exception {
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String acountNumber = cc.getAccountNumber();
        List<String> currencyCodes = List.of("USD","HKD","EUR","AUD","NZD","CAD","ZAR","MXN","TRY","SGD","KRW","RUB","VND","IDR","THB","MYR","CNY");
        List<CashDeposit> cashDeposits = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            cashDeposits.addAll(foreignAccountService.multiGetCashDeposits(butenCode, acountNumber, "GENERAL", i, currencyCodes).getCashDeposits());
        }
        // 顧客共通情報.ジュニアISA契約区分＝1かつ顧客共通情報.払出制限解除フラグ＝1（払出制限中）の場合
        if ("1".equals(cc.getJrIsaContractType()) && "1".equals(cc.getWithdrawalRestrictionCancelFlag())) {
            for (int i = 1; i <= 6; i++) {
                cashDeposits.addAll(foreignAccountService.multiGetCashDeposits(butenCode, acountNumber, "JR_NISA", i, currencyCodes).getCashDeposits());
            }
        }
        // API002.預り金リスト.預り金が0でない
        return cashDeposits.stream()
                .filter(c -> !StringUtil.parseBigDecimal(c.getDepositAmount()).equals(StringUtil.parseBigDecimal("0")))
                .map(c -> c.getCurrencyCode())
                .distinct()
                .collect(Collectors.toList());
    }

    
    // 何営業日後（日数）の算出メソッド
    private String getBusinessDayString(int daysLater) {
        
        switch (daysLater) {
            case CASE_ZERO:
                return BUSINESS_DAYS_TODAY;
            /* breakなし */
            case CASE_ONE:
                return BUSINESS_DAYS_ONE_DAYS;
            /* breakなし */
            case CASE_TWO:
                return BUSINESS_DAYS_TWO_DAYS;
            /* breakなし */
            case CASE_THREE:
                return BUSINESS_DAYS_THREE_DAYS;
            /* breakなし */
            case CASE_FOUR:
                return BUSINESS_DAYS_FOUR_DAYS;
            /* breakなし */
            case CASE_FIVE:
                return BUSINESS_DAYS_FIVE_DAYS;
            /* breakなし */
            default:
                return null;
            /* breakなし */
            
        }
    }
}
