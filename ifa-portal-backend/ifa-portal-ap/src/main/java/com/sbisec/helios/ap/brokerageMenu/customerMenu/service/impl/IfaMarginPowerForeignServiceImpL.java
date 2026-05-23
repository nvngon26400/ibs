package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.athena.enums.DeficitBalanceStatus;
import com.sbisec.helios.ap.athena.enums.MarkToMarketStatus;
import com.sbisec.helios.ap.athena.enums.RemainingPowerAlertStatus;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.protocol.account.GetMarginPowerDetailResp;
import com.sbisec.helios.ap.athena.protocol.account.ListDepositRateBasisResp;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignMarginScheduleCashBalancesResp;
import com.sbisec.helios.ap.athena.protocol.account.dto.DeficitBalanceDetail;
import com.sbisec.helios.ap.athena.protocol.account.dto.ForeignMarginScheduleCashBalance;
import com.sbisec.helios.ap.athena.protocol.account.dto.InitialMarginShortfallDetail;
import com.sbisec.helios.ap.athena.protocol.account.dto.MarginCurrencyCashBalance;
import com.sbisec.helios.ap.athena.protocol.account.dto.MarginTransition;
import com.sbisec.helios.ap.athena.protocol.account.dto.PowerMarginCallDetail;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerSummaryResp;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginPowerForeignA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginPowerForeignA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginPowerForeignA001DtoResponseAddDeposit;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginPowerForeignA001DtoResponseDepositDeficient;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginPowerForeignA001DtoResponseEntrustDepositTransition;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginPowerForeignA001DtoResponseMarginTrade;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginPowerForeignA001DtoResponseNewPositionDeficient;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMarginPowerForeignService;
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
 * 画面ID：SUB0202_010304-01
 * 画面名：信用余力(米株)
 * アクションID：A001
 * アクション名：初期化

 * @author SCSK 矢口
    2023/12/1 新規作成
 */
@Component(value = "cmpIfaMarginPowerForeignService")
public class IfaMarginPowerForeignServiceImpL implements IfaMarginPowerForeignService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMarginPowerForeignServiceImpL.class);
    
    /** 権限チェック値 「権限なし」 */
    public static final String TARGET_CUSTOMER_REF_AUTH_FLAG_0 = "0";
    
    /** 権限チェックエラー */
    public static final String ERRORS_BUTEN_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** 外国信用口座開設状況チェック値 「未開設」 */
    public static final String FOREIGN_MARGIN_ACCOUNT_TYPE_SPACE = " ";
    
    /** 外国信用口座未開設エラー */
    public static final String ERRORS_FRS_FOREIGN_STOCK_ACCOUNT_NOT_OPEN = "errors.frs.foreignStockAccount.notOpen#1";
    
    /** 国コード 「US」 */
    public static final String COUNTRY_CODE_US = "US";
    
    /** 外貨信用保証金残高スケジュール取得 通貨コード 「USD」 */
    public static final String CURRENCY_CODE_USD = "USD";
    
    /** 外貨信用保証金残高スケジュール取得 取得日数 */
    public static final Integer DAYS_6 = 6;
    
    /** 追証フラグ設定値　「0（追証未発生）」 */
    public static final String MARGIN_CALL_FLAG_0 = "0";
    
    /** 追証フラグ設定値　「1（追証発生）」 */
    public static final String MARGIN_CALL_FLAG_1 = "1";
    
    /** 預り金不足フラグ設定値　「0（預り金不足なし）」 */
    public static final String DEPOSIT_DEFICIENT_FLAG_0 = "0";
    
    /** 預り金不足フラグ設定値　「1（預り金不足）」 */
    public static final String DEPOSIT_DEFICIENT_FLAG_1 = "1";
    
    /** 新規建不足フラグ設定値　「0（新規建て不足なし）」 */
    public static final String NEW_POSITION_DEFICIENT_FLAG_0 = "0";
    
    /** 新規建不足フラグ設定値　「1（新規建て不足）」 */
    public static final String NEW_POSITION_DEFICIENT_FLAG_1 = "1";
    
    /** 信用取引リスト・委託保証金率リスト.営業日後　設定値　「（当日）」 */
    public static final String SETTLEMENT_DATE_AFTER_BUSINESS_DAY_0 = "（当日）";
    
    /** 信用取引リスト・委託保証金率リスト.営業日後　設定値　「（1営業日後）」 */
    public static final String SETTLEMENT_DATE_AFTER_BUSINESS_DAY_1 = "（1営業日後）";
    
    /** 信用取引リスト・委託保証金率リスト.営業日後　設定値　「（2営業日後）」 */
    public static final String SETTLEMENT_DATE_AFTER_BUSINESS_DAY_2 = "（2営業日後）";
    
    /** 信用取引リスト・委託保証金率リスト.営業日後　設定値　「（3営業日後）」 */
    public static final String SETTLEMENT_DATE_AFTER_BUSINESS_DAY_3 = "（3営業日後）";
    
    /** 信用取引リスト・委託保証金率リスト.営業日後　設定値　「（4営業日後）」 */
    public static final String SETTLEMENT_DATE_AFTER_BUSINESS_DAY_4 = "（4営業日後）";
    
    /** 信用取引リスト・委託保証金率リスト.営業日後　設定値　「（5営業日後）」 */
    public static final String SETTLEMENT_DATE_AFTER_BUSINESS_DAY_5 = "（5営業日後）";
    
    /** 追加保証金リスト.値洗区分　設定値　「概算」 */
    public static final String MARK_TO_MARKET_TYPE_EST = "概算";
    
    /** 追加保証金リスト.値洗区分　設定値　「確定」 */
    public static final String MARK_TO_MARKET_TYPE_FIX = "確定";
    
    /** 預り金不足リスト.状況　設定値　「未解消」 */
    public static final String DEFICIT_BALANCE_STATUS_UNRESOLVED = "未解消";
    
    /** 預り金不足リスト.状況　設定値　「赤残付替中」 */
    public static final String DEFICIT_BALANCE_STATUS_RESOLVING = "赤残付替中";
    
    /** 預り金不足リスト.状況　設定値　「入金による解除（赤残付け替え不要）」 */
    public static final String DEFICIT_BALANCE_STATUS_SOLVED_AFTER_DEPOSIT = "入金による解除（赤残付け替え不要）";
    
    /** 預り金不足リスト.状況　設定値　「赤残付替後の解除」 */
    public static final String DEFICIT_BALANCE_STATUS_SOLVED = "赤残付替後の解除";
    
    /** 預り金不足リスト.状況　設定値　「赤残付替後に解除できず」 */
    public static final String DEFICIT_BALANCE_STATUS_UNRESOLVED_AFTER_REPLACEMENT = "赤残付替後に解除できず";
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private ForeignAccountService foreignAccountService;
    
    @Autowired
    private CometCommonService cometCommonService;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaMarginPowerForeignA001DtoRequest
     * Dto レスポンス：IfaMarginPowerForeignA001DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel

     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @throws Exception 初期化処理で例外が発生した場合
     * */
    public DataList<IfaMarginPowerForeignA001DtoResponse> initializeA001(IfaMarginPowerForeignA001DtoRequest dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginPowerForeignServiceImplL.initializeA001");
        }
        
        DataList<IfaMarginPowerForeignA001DtoResponse> dtoRes = new DataList<IfaMarginPowerForeignA001DtoResponse>();
        List<IfaMarginPowerForeignA001DtoResponse> resDtoList = new ArrayList<IfaMarginPowerForeignA001DtoResponse>();
        
        
        //==============================
        // ①利用者の口座に対する権限チェックを行う
        //==============================
        
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        
        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        inputFct001Dto.setButenCode(customerCommon.getButenCode());
        inputFct001Dto.setAccountNumber(customerCommon.getAccountNumber());
        
        // FCT001の実行
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        
        /*
         * 権限あり：次の処理へ。
         * 権限なし：権限なしエラーを返す。
         */
        if (outputFct001Dto == null || StringUtils.equals(outputFct001Dto.getTargetCustomerRefAuthFlag(),
                IfaMarginPowerForeignServiceImpL.TARGET_CUSTOMER_REF_AUTH_FLAG_0)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaMarginPowerForeignServiceImpL.ERRORS_BUTEN_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(IfaMarginPowerForeignServiceImpL.ERRORS_BUTEN_ACCOUNT_NOT_EXISTS,
                            new String[] { customerCommon.getButenCode(), customerCommon.getAccountNumber() }));
            return dtoRes;
        }
        
        
        // ===========================================================
        // ②顧客共通情報の「信用口座区分(外国)」より、外国信用口座開設状況をチェックを行う。
        //============================================================
        
        // 顧客共通情報の取得
        String foreignMarginAccountType = customerCommon.getForeignMarginAccountType();
        
        /*
         * 「開設済」：次の処理へ。
         * 「未開設」：外国信用口座未開設エラーを返す。
         */
        if (StringUtils.equals(foreignMarginAccountType, IfaMarginPowerForeignServiceImpL.FOREIGN_MARGIN_ACCOUNT_TYPE_SPACE)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaMarginPowerForeignServiceImpL.ERRORS_FRS_FOREIGN_STOCK_ACCOUNT_NOT_OPEN,
                    IfaCommonUtil.getMessage(IfaMarginPowerForeignServiceImpL.ERRORS_FRS_FOREIGN_STOCK_ACCOUNT_NOT_OPEN));
            return dtoRes;
        }
        
        
        //======================================
        // ③追加保証金、預り金不足、新規建て不足情報の取得
        //======================================
        
        // 顧客共通情報の取得
        String butenCode = customerCommon.getButenCode();
        // 口座番号は7桁前0埋め
        String accountNumber = (String.format("%7s", customerCommon.getAccountNumber()).replace(" ", "0"));
        
        //API001呼び出し
        GetMarginPowerSummaryResp api001Response = new GetMarginPowerSummaryResp();
        try {
            api001Response = foreignAccountService.getMarginPowerSummary(butenCode, accountNumber, COUNTRY_CODE_US);
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaMarginPowerForeignServiceImplL.initializeA001}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        IfaMarginPowerForeignA001DtoResponse resDto = new IfaMarginPowerForeignA001DtoResponse();
        
        
        //-----------------------
        // 追証ステータスの判定
        //-----------------------
        
        // 余力追証明細の取得
        List<PowerMarginCallDetail> marginCallDetailList = api001Response.getMarginCallDetails();
        
        /*
         * 以下２つの条件を満たす場合に「追証フラグ」に’１’（追証発生）を設定。
         * 1.API001.余力追証明細が1件以上あること
         * 2.任意の（1件以上の）余力追証明細.追証状況が true(追証)であること
         */
        int i = 0;
        if (marginCallDetailList != null && marginCallDetailList.size() > 0) {
            for (PowerMarginCallDetail item : marginCallDetailList) {
                if (item != null && item.getMarginCalled() != null && item.getMarginCalled()) {
                    i++;
                }
            }
        }
        if (i >= 1) {
            resDto.setMarginCallFlag(MARGIN_CALL_FLAG_1);
        } else {
            resDto.setMarginCallFlag(MARGIN_CALL_FLAG_0);
        }
        
        
        //-----------------------
        // 預り金不足の判定
        //-----------------------
        
        // 預り金不足明細の取得
        List<DeficitBalanceDetail> deficitBalanceDetailList = api001Response.getDeficitBalanceDetails();
        
        /*
         * 以下２つの条件を満たす場合、「預り金不足フラグ」に'１’（預り金不足）を設定。
         * 1.API001.預り金不足明細が1件以上あること
         * 2.任意の（1件以上の）預り金不足明細.赤残ステータスが下記のいずれかであること
         * ・未解消
         * ・赤残付替中
         * ・赤残付替後に解除できず
         */
        int j = 0;
        if (deficitBalanceDetailList != null && deficitBalanceDetailList.size() > 0) {
            for (DeficitBalanceDetail item : deficitBalanceDetailList) {
                if (item != null && item.getDeficitBalanceStatus() != null
                        && (DeficitBalanceStatus.UNRESOLVED.getId().equals(item.getDeficitBalanceStatus())
                        || DeficitBalanceStatus.RESOLVING.getId().equals(item.getDeficitBalanceStatus())
                        || DeficitBalanceStatus.UNRESOLVED_AFTER_REPLACEMENT.getId().equals(item.getDeficitBalanceStatus()))) {
                    j++;
                }
            }
        }
        if (j >= 1) {
            resDto.setDepositDeficientFlag(DEPOSIT_DEFICIENT_FLAG_1);
        } else {
            resDto.setDepositDeficientFlag(DEPOSIT_DEFICIENT_FLAG_0);
        }
        
        
        //-----------------------
        // 新規建て不足の判定
        //-----------------------
        
        // 追証アラートステータスリストの取得
        List<String> remainingPowerAlertsStatus = api001Response.getRemainingPowerAlertStatus();
        
        /*
         * 追証アラートステータスリストが以下の場合に「新規建不足フラグ」に'１’（新規建て不足）を設定。
         * ・新規建時の保証金不足
         * ・.API001.新規建不足明細が1件以上あること
         */
        if (
            (
                remainingPowerAlertsStatus != null
                && remainingPowerAlertsStatus.contains(RemainingPowerAlertStatus.MARGIN_SHORTFALL.getId()) 
            ) &&
            (
                api001Response.getInitialMarginShortfallDetails() != null
                && api001Response.getInitialMarginShortfallDetails().size() > 0
            )
        ) {
            resDto.setNewPositionDeficientFlag(NEW_POSITION_DEFICIENT_FLAG_1);
        } else {
            resDto.setNewPositionDeficientFlag(NEW_POSITION_DEFICIENT_FLAG_0);
        }
        
        
        // --- DtoResponseにパラメータをセットする -----------------
        
        // 追加保証金リスト
        List<IfaMarginPowerForeignA001DtoResponseAddDeposit> addDepositList = new ArrayList<IfaMarginPowerForeignA001DtoResponseAddDeposit>();
        
        // 追証フラグ＝１（追証発生）の時に値を格納
        if ("1".equals(resDto.getMarginCallFlag())) {
            setAddDepositList(api001Response, addDepositList);
        }
        // 追加保証金リストをセット
        resDto.setAddDeposit(addDepositList);
        
        
        // 預り金不足リスト
        List<IfaMarginPowerForeignA001DtoResponseDepositDeficient> depositDeficientList = new ArrayList<IfaMarginPowerForeignA001DtoResponseDepositDeficient>();
        
        // 預り金不足フラグ=1（預り金不足）の時に値を格納
        if ("1".equals(resDto.getDepositDeficientFlag())) {
            setDepositDeficientList(api001Response, depositDeficientList);
        }
        // 預り金不足リストをセット
        resDto.setDepositDeficient(depositDeficientList);
        
        
        // 新規建不足リスト
        List<IfaMarginPowerForeignA001DtoResponseNewPositionDeficient> newPositionDeficientList = new ArrayList<IfaMarginPowerForeignA001DtoResponseNewPositionDeficient>();
        
        // 新規建不足フラグ=１（新規建て不足）の時に値を格納
        if ("1".equals(resDto.getNewPositionDeficientFlag())) {
            setNewPositionDeficientList(api001Response, newPositionDeficientList);
        }
        // 新規建不足リストをセット
        resDto.setNewPositionDeficient(newPositionDeficientList);
        
        
        //=====================
        // ③信用取引情報の取得
        //=====================
        
        // API002呼び出し
        ListForeignMarginScheduleCashBalancesResp api002Response = new ListForeignMarginScheduleCashBalancesResp();
        try {
            api002Response = foreignAccountService.listForeignMarginScheduleCashBalances(butenCode, accountNumber, CURRENCY_CODE_USD, DAYS_6);
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaMarginPowerForeignServiceImplL.initializeA001}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // --- DtoResponseにパラメータをセットする -----------------
        
        // 信用取引リストへ値を格納
        List<IfaMarginPowerForeignA001DtoResponseMarginTrade> marginTradeList = new ArrayList<IfaMarginPowerForeignA001DtoResponseMarginTrade>();
        setMarginTradeList(api002Response, marginTradeList);
        
        // 信用取引リストをセット
        resDto.setMarginTrade(marginTradeList);
        
        
        //====================================
        // ④余力情報、委託保証金率の推移情報の取得
        //====================================
        
        // API003呼び出し
        GetMarginPowerDetailResp api003Response = new GetMarginPowerDetailResp();
        try {
            api003Response = foreignAccountService.getMarginPowerDetail(butenCode, accountNumber, COUNTRY_CODE_US);
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaMarginPowerForeignServiceImplL.initializeA001}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // --- DtoResponseにパラメータをセットする -----------------
        
        // 委託保証金率リストへ値を格納
        List<IfaMarginPowerForeignA001DtoResponseEntrustDepositTransition> entrustDepositTransitionList = new ArrayList<IfaMarginPowerForeignA001DtoResponseEntrustDepositTransition>();
        setEntrustDepositTransitionList(api003Response, entrustDepositTransitionList);
        
        // 委託保証金率リストをセット
        resDto.setEntrustDepositTransition(entrustDepositTransitionList);
        
        // 余力情報_信用建余力をセット
        // 金額が0もしくはマイナスの場合は「0.00」
        if (api003Response.getMarginBuyingPower().equals("0") || api003Response.getMarginBuyingPower().contains("-")) {
            resDto.setForeignNewBuildingCapacity("0.00");
        } else {
            resDto.setForeignNewBuildingCapacity(api003Response.getMarginBuyingPower());
        }
        
        // 余力情報_委託保証金率をセット
        // 保証金率が0の場合は「0.00」
        if (api003Response.getDepositRateWithSameDayStandard().equals("0")) {
            resDto.setMarginDepositRate8("0.00");
        } else {
            resDto.setMarginDepositRate8(api003Response.getDepositRateWithSameDayStandard());
        }
        
        // 余力情報_米ドル保証金をセット
        resDto.setUsdSecurityDeposit(api003Response.getMarginWithdrawable());
        
        // 余力情報_代用有価証券をセット
        resDto.setSubstituteSecurities(api003Response.getCollateralWithdrawable());
        
        
        //--------------------------------
        // 受渡文言設定
        //--------------------------------
        
        // 信用取引リスト.営業日後
        if (resDto.getMarginTrade() != null) {
            if (resDto.getMarginTrade().size() > 0) {
                resDto.getMarginTrade().get(0).setSettlementDateAfterBusinessDay(SETTLEMENT_DATE_AFTER_BUSINESS_DAY_0);
            }
            if (resDto.getMarginTrade().size() > 1) {
                resDto.getMarginTrade().get(1).setSettlementDateAfterBusinessDay(SETTLEMENT_DATE_AFTER_BUSINESS_DAY_1);
            }
            if (resDto.getMarginTrade().size() > 2) {
                resDto.getMarginTrade().get(2).setSettlementDateAfterBusinessDay(SETTLEMENT_DATE_AFTER_BUSINESS_DAY_2);
            }
            if (resDto.getMarginTrade().size() > 3) {
                resDto.getMarginTrade().get(3).setSettlementDateAfterBusinessDay(SETTLEMENT_DATE_AFTER_BUSINESS_DAY_3);
            }
            if (resDto.getMarginTrade().size() > 4) {
                resDto.getMarginTrade().get(4).setSettlementDateAfterBusinessDay(SETTLEMENT_DATE_AFTER_BUSINESS_DAY_4);
            }
            if (resDto.getMarginTrade().size() > 5) {
                resDto.getMarginTrade().get(5).setSettlementDateAfterBusinessDay(SETTLEMENT_DATE_AFTER_BUSINESS_DAY_5);
            }
        }
        
        // 委託保証金率リスト.営業日後
        if (resDto.getEntrustDepositTransition() != null) {
            if (resDto.getEntrustDepositTransition().size() > 0) {
                resDto.getEntrustDepositTransition().get(0)
                .setSettlementDateAfterBusinessDay(SETTLEMENT_DATE_AFTER_BUSINESS_DAY_0);
            }
            if (resDto.getEntrustDepositTransition().size() > 1) {
                resDto.getEntrustDepositTransition().get(1)
                .setSettlementDateAfterBusinessDay(SETTLEMENT_DATE_AFTER_BUSINESS_DAY_1);
            }
            if (resDto.getEntrustDepositTransition().size() > 2) {
                resDto.getEntrustDepositTransition().get(2)
                .setSettlementDateAfterBusinessDay(SETTLEMENT_DATE_AFTER_BUSINESS_DAY_2);
            }
            if (resDto.getEntrustDepositTransition().size() > 3) {
                resDto.getEntrustDepositTransition().get(3)
                .setSettlementDateAfterBusinessDay(SETTLEMENT_DATE_AFTER_BUSINESS_DAY_3);
            }
            if (resDto.getEntrustDepositTransition().size() > 4) {
                resDto.getEntrustDepositTransition().get(4)
                .setSettlementDateAfterBusinessDay(SETTLEMENT_DATE_AFTER_BUSINESS_DAY_4);
            }
            if (resDto.getEntrustDepositTransition().size() > 5) {
                resDto.getEntrustDepositTransition().get(5)
                .setSettlementDateAfterBusinessDay(SETTLEMENT_DATE_AFTER_BUSINESS_DAY_5);
            }
        }
        
        
        //====================================
        // ⑤余力情報_リアル委託保証金率の取得
        //====================================
        
        // API004呼び出し
        ListDepositRateBasisResp api004Response = new ListDepositRateBasisResp();
        try {
            api004Response = foreignAccountService.listDepositRateBasis(butenCode, accountNumber, COUNTRY_CODE_US);
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaMarginPowerForeignServiceImplL.initializeA001}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // --- DtoResponseにパラメータをセットする -----------------
        
        // 余力情報_リアル委託保証金率をセット
        // 保証金率が0の場合は「0.00」
        if (api004Response.getDepositRateBasis().get(0).getMarginRate().equals("0")) {
            resDto.setRealTimeMarginDepositRate("0.00");
        } else {
            resDto.setRealTimeMarginDepositRate(api004Response.getDepositRateBasis().get(0).getMarginRate());
        }
        
        
        // DataListの作成
        List<IfaMarginPowerForeignA001DtoResponse> dtoResList = new ArrayList<IfaMarginPowerForeignA001DtoResponse>();
        dtoResList.add(resDto);
        
        return IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
    }
    
    
    /**
     * 追加保証金リストにAPIのレスポンスを格納する

     * @param apiResp 外国株式信用建余力サマリ取得APIのレスポンス
     * @param addDepositList 追加保証金リスト
     */
    private void setAddDepositList(GetMarginPowerSummaryResp apiResp,
            List<IfaMarginPowerForeignA001DtoResponseAddDeposit> addDepositList) {
        
        // 追加保証金リスト（Actionパラメータ）
        IfaMarginPowerForeignA001DtoResponseAddDeposit addDepositDto = null;
        
        // 追証明細存在しない場合
        if (apiResp == null || apiResp.getMarginCallDetails() == null || apiResp.getMarginCallDetails().size() <= 0) {
            return;
        }
        // 余力追証明細（APIのフィールド）
        List<PowerMarginCallDetail> marginCallDetailList = apiResp.getMarginCallDetails();
        for (PowerMarginCallDetail item : marginCallDetailList) {
            if (item == null) {
                continue;
            }
            addDepositDto = new IfaMarginPowerForeignA001DtoResponseAddDeposit();
            
            // 値洗区分（全角半角）
            /* 
             * 値洗い区分の値に応じて以下の変換を行う
             * ■ "EST"の場合 "概算"
             * ■ "FIX"の場合 "確定" 
             */
            if (MarkToMarketStatus.EST.getId().equals(item.getMarkToMarketStatus())) {
                addDepositDto.setMarkToMarketType(MARK_TO_MARKET_TYPE_EST);
            } else if (MarkToMarketStatus.FIX.getId().equals(item.getMarkToMarketStatus())) {
                addDepositDto.setMarkToMarketType(MARK_TO_MARKET_TYPE_FIX);
            }
            
            // 解消期限
            addDepositDto.setCancellationDeadline(item.getDepositDueDate());
            
            // 当初追証額（数値(整数)）
            addDepositDto.setInitialMarginAmount(item.getAdditionalMargin());
            
            // 追証発生日
            addDepositDto.setMarginCallDate(item.getFrnAssessmentDate());
            
            // 入金充当額
            addDepositDto.setAddDepositPaymentInterest(item.getCashDepositAmount());
            
            // 代用充当額（数値(小数)）
            addDepositDto.setSubstituteInterest(item.getCollateralDepositAmount());
            
            // 決済建玉充当額（数値(小数)）
            addDepositDto.setSettlementOpenInterestForeignCurrency(item.getClosedPositionEquivalentToDeposit());
            
            // 必要額（数値(小数)）
            addDepositDto.setOpenInterestCostForeignCurrency(item.getRequiredDepositAmount());
            
            addDepositList.add(addDepositDto);
            
        }
    }
    
    /**
     * 預り金不足リストにAPIのレスポンスを格納する

     * @param apiResp 外国株式信用建余力サマリ取得APIのレスポンス
     * @param depositDeficientList 預り金不足リスト
     */
    private void setDepositDeficientList(GetMarginPowerSummaryResp apiResp,
            List<IfaMarginPowerForeignA001DtoResponseDepositDeficient> depositDeficientList) {
        
        // 預り金不足リスト（Actionパラメータ）
        IfaMarginPowerForeignA001DtoResponseDepositDeficient depositDeficientDto = null;
        
        // 預り金不足明細存在しない場合
        if (apiResp == null || apiResp.getDeficitBalanceDetails() == null
                || apiResp.getDeficitBalanceDetails().size() <= 0) {
            return;
        }
        // 預り金不足情報（APIのフィールド）
        List<DeficitBalanceDetail> deficitBalanceDetailList = apiResp.getDeficitBalanceDetails();
        for (DeficitBalanceDetail item : deficitBalanceDetailList) {
            if (item == null) {
                continue;
            }
            depositDeficientDto = new IfaMarginPowerForeignA001DtoResponseDepositDeficient();
            
            // 解消期限
            depositDeficientDto.setCancellationDeadline(item.getDepositDueDate());
            
            // 状況（全角半角）
            /*
             * 赤残ステータスの値に応じて以下の変換を行う
             * ■"UNRESOLVED”の場合 "未解消"
             * ■"RESOLVINGの場合 "赤残付替中"
             * ■"SOLVED_AFTER_DEPOSITの場合 "入金による解除（赤残付け替え不要）"
             * ■"SOLVED"の場合 "赤残付替後の解除"
             * ■"UNRESOLVED_AFTER_REPLACEMENT”の場合 "赤残付替後に解除できず"
             */
            if (DeficitBalanceStatus.UNRESOLVED.getId().equals(item.getDeficitBalanceStatus())) {
                depositDeficientDto.setStatus(DEFICIT_BALANCE_STATUS_UNRESOLVED);
            } else if (DeficitBalanceStatus.RESOLVING.getId().equals(item.getDeficitBalanceStatus())) {
                depositDeficientDto.setStatus(DEFICIT_BALANCE_STATUS_RESOLVING);
            } else if (DeficitBalanceStatus.SOLVED_AFTER_DEPOSIT.getId().equals(item.getDeficitBalanceStatus())) {
                depositDeficientDto.setStatus(DEFICIT_BALANCE_STATUS_SOLVED_AFTER_DEPOSIT);
            } else if (DeficitBalanceStatus.SOLVED.getId().equals(item.getDeficitBalanceStatus())) {
                depositDeficientDto.setStatus(DEFICIT_BALANCE_STATUS_SOLVED);
            } else if (DeficitBalanceStatus.UNRESOLVED_AFTER_REPLACEMENT.getId().equals(item.getDeficitBalanceStatus())) {
                depositDeficientDto.setStatus(DEFICIT_BALANCE_STATUS_UNRESOLVED_AFTER_REPLACEMENT);
            }
            
            // 預り金不足額（数値(整数)）
            depositDeficientDto.setDepositShortage(item.getDeficitBalanceAmount());
            
            // 入金額（数値(整数)）
            depositDeficientDto.setDepositAmount(item.getDepositAmount());
            
            
            depositDeficientList.add(depositDeficientDto);
            
        }
    }
    
    /**
     * 新規建不足リストにAPIのレスポンスを格納する

     * @param apiResp 外国株式信用建余力サマリ取得APIのレスポンス
     * @param newPositionDeficientList 新規建不足リスト
     */
    private void setNewPositionDeficientList(GetMarginPowerSummaryResp apiResp,
            List<IfaMarginPowerForeignA001DtoResponseNewPositionDeficient> newPositionDeficientList) {
        
        // 新規建不足リスト（Actionパラメータ）
        IfaMarginPowerForeignA001DtoResponseNewPositionDeficient newPositionDeficientDto = null;
        
        // 新規建不足明細存在しない場合
        if (apiResp == null || apiResp.getInitialMarginShortfallDetails() == null
                || apiResp.getInitialMarginShortfallDetails().size() <= 0) {
            return;
        }
        // 新規建不足明細（APIのフィールド）
        List<InitialMarginShortfallDetail> initialMarginShortfallDetailList = apiResp
                .getInitialMarginShortfallDetails();
        for (InitialMarginShortfallDetail item : initialMarginShortfallDetailList) {
            if (item == null) {
                continue;
            }
            newPositionDeficientDto = new IfaMarginPowerForeignA001DtoResponseNewPositionDeficient();
            
            // 解消期限
            newPositionDeficientDto.setCancellationDeadline(item.getDepositDueDate());
            
            // 新規建不足金額（数値(小数)）
            newPositionDeficientDto.setConstructionShortage(item.getTotalInitialMarginShortfall());
            
            // 発生日
            newPositionDeficientDto.setAccuralDate(item.getAssessmentDate());
            
            // 入金充当額（数値(小数)）
            newPositionDeficientDto.setPaymentInterest(item.getCashDepositAmount());
            
            // 代用充当額（数値(小数)）
            newPositionDeficientDto.setSubstituteInterest(item.getCollateralDepositAmount());
            
            // 決済建玉充当額（数値(小数)）
            newPositionDeficientDto
                    .setSettlementOpenInterestForeignCurrency(item.getClosedPositionEquivalentToDeposit());
            
            // 必要額（数値(小数)）
            newPositionDeficientDto.setOpenInterestCostForeignCurrency(item.getRequiredDepositAmount());
            
            
            newPositionDeficientList.add(newPositionDeficientDto);
            
        }
        
    }
    
    /**
     * 信用取引リストにAPIのレスポンスを格納する

     * @param apiResp 外貨信用保証金残高スケジュール取得APIのレスポンス
     * @param marginTradeList 信用取引リスト
     */
    private void setMarginTradeList(ListForeignMarginScheduleCashBalancesResp apiResp,
            List<IfaMarginPowerForeignA001DtoResponseMarginTrade> marginTradeList) {
        
        // 信用取引リスト（Actionパラメータ）
        IfaMarginPowerForeignA001DtoResponseMarginTrade marginTradeDto = null;
        
        // 外貨信用保証金残高(通貨別)存在しない場合
        if (apiResp == null || apiResp.getMarginCurrencyCashBalances() == null
                || apiResp.getMarginCurrencyCashBalances().size() <= 0) {
            return;
        }
        // 外貨信用保証金残高(通貨別)（APIのフィールド）
        List<MarginCurrencyCashBalance> marginCurrencyCashBalanceList = apiResp.getMarginCurrencyCashBalances();
        for (MarginCurrencyCashBalance item : marginCurrencyCashBalanceList) {
            if (item == null) {
                continue;
            }
            
            // 外貨信用保証金残高スケジュールリスト存在しない場合
            if (item == null || item.getForeignMarginScheduleCashBalances() == null
                    || item.getForeignMarginScheduleCashBalances().size() <= 0) {
                return;
            }
            // 外貨信用保証金残高スケジュールリスト（APIから渡されたデータ）
            List<ForeignMarginScheduleCashBalance> foreignMarginScheduleCashBalanceList = item
                    .getForeignMarginScheduleCashBalances();
            
            for (ForeignMarginScheduleCashBalance item2 : foreignMarginScheduleCashBalanceList) {
                if (item2 == null) {
                    continue;
                }
                marginTradeDto = new IfaMarginPowerForeignA001DtoResponseMarginTrade();
                
                // 営業日(yyyy-MM-dd)
                marginTradeDto.setDomesticSettlementDate(item2.getBusinessDate());
                
                // 保証金現金（数値(整数)）
                // 金額が0の場合は「0.00」
                if (item2.getMarginCash().equals("0")) {
                    marginTradeDto.setMargin("0.00");
                } else {
                    marginTradeDto.setMargin(item2.getMarginCash());
                }
                
                // 支払額（数値(整数)）
                // 金額が0の場合は「0.00」
                if (item2.getPaymentAmount().equals("0")) {
                    marginTradeDto.setPayment("0.00");
                } else {
                    marginTradeDto.setPayment(item2.getPaymentAmount());
                }
                
                // 未約定信用決済損（数値(整数)）
                // 金額が0の場合は「0.00」
                if (item2.getUnexecPayBgLoss().equals("0")) {
                    marginTradeDto.setUncontractedCreditSettlementLoss("0.00");
                } else {
                    marginTradeDto.setUncontractedCreditSettlementLoss(item2.getUnexecPayBgLoss());
                }
                
                // 受取額（数値(整数)）
                // 金額が0の場合は「0.00」
                if (item2.getReceiveAmountValue().equals("0")) {
                    marginTradeDto.setAmountReceived("0.00");
                } else {
                    marginTradeDto.setAmountReceived(item2.getReceiveAmountValue());
                }
                
                // 振替予定額 (現物口座→信用口座)
                // 金額が0の場合は「0.00」
                if (item2.getTransferEstimatedAmount().equals("0")) {
                    marginTradeDto.setPlannedTransferAmount("0.00");
                } else {
                    marginTradeDto.setPlannedTransferAmount(item2.getTransferEstimatedAmount());
                }
                
                // 残高(保証金)
                // 金額が0の場合は「0.00」
                if (item2.getMarginBalance().equals("0")) {
                    marginTradeDto.setBalanceDeposit("0.00");
                } else {
                    marginTradeDto.setBalanceDeposit(item2.getMarginBalance());
                }
                
                // 必要保証金額（数値(小数)）
                // 金額が0の場合は「0.00」
                if (item2.getRequiredMargin().equals("0")) {
                    marginTradeDto.setRequiredSettlementAmountForeignCurrency("0.00");
                } else {
                    marginTradeDto.setRequiredSettlementAmountForeignCurrency(item2.getRequiredMargin());
                }
                
                marginTradeList.add(marginTradeDto);
            }
        }
    }
    
    /**
     * 委託保証金率リストにAPIのレスポンスを格納する

     * @param apiResp 外国株式信用建余力詳細取得APIのレスポンス
     * @param entrustDepositTransitionList 委託保証金率リスト
     */
    private void setEntrustDepositTransitionList(GetMarginPowerDetailResp apiResp,
            List<IfaMarginPowerForeignA001DtoResponseEntrustDepositTransition> entrustDepositTransitionList) {
        
        // 委託保証金率リスト（Actionパラメータ）
        IfaMarginPowerForeignA001DtoResponseEntrustDepositTransition entrustDepositTransitionDto = null;
        
        // 委託保証金率の推移情報存在しない場合
        if (apiResp == null || apiResp.getMarginTransitions() == null || apiResp.getMarginTransitions().size() <= 0) {
            return;
        }
        // 委託保証金率の推移情報（APIのフィールド）
        List<MarginTransition> marginTransitionList = apiResp.getMarginTransitions();
        for (MarginTransition item : marginTransitionList) {
            if (item == null) {
                continue;
            }
            entrustDepositTransitionDto = new IfaMarginPowerForeignA001DtoResponseEntrustDepositTransition();
            
            // 営業日(yyyy-MM-dd)
            entrustDepositTransitionDto.setDomesticSettlementDate(item.getBaseDate());
            
            // 委託保証金現金合計（数値(小数)）
            entrustDepositTransitionDto.setMarginDepositTotalCache(item.getMarginBalance());
            
            // 代用有価証券評価額合計（数値(整数)）
            entrustDepositTransitionDto.setAlternativeSecuritiesTotal(item.getTotalCollateralValue());
            
            // 委託保証金率リスト.評価損・決済損益・諸経費合計（数値(小数)）
            BigDecimal totalUnrealizedProfitLoss = new BigDecimal(item.getTotalEvaluationProfitLoss());
            BigDecimal totalClosedProfitLoss = new BigDecimal(item.getTotalClosedProfitLoss());
            BigDecimal totalExpenses = new BigDecimal(item.getTotalExpenses());
            entrustDepositTransitionDto.setMarginDepositRateSettlementProfitLossCost((totalUnrealizedProfitLoss.add(totalClosedProfitLoss.add(totalExpenses))).toString());
            
            // (A)実質保証金（数値(小数)）
            entrustDepositTransitionDto.setForeignCurrencyactualDeposit(item.getActualMargin());
            
            // (B)建玉金合計
            entrustDepositTransitionDto.setConstructionPriceTotal(item.getTotalUnclosedGrossAmount());
            
            // 委託保証金率 A/B×100
            entrustDepositTransitionDto.setMarginDepositRate8(item.getDepositRate());
            
            // (C)預り金（数値(小数)）
            entrustDepositTransitionDto.setForeignCurrencydeposit(item.getTransferPossibleAmount());
            
            // (D)保護預り 有価証券評価額合計
            entrustDepositTransitionDto
                    .setTotalAppraisalValueOfSecuritiesInCustody(item.getTotalProtectionEvaluationAmount());
            
            // 参考委託保証金率 (A+C+D/B×100)
            entrustDepositTransitionDto.setForeignReferenceMarginDeposit(item.getReferenceDepositRate());
            
            
            entrustDepositTransitionList.add(entrustDepositTransitionDto);
        }
    }
}
