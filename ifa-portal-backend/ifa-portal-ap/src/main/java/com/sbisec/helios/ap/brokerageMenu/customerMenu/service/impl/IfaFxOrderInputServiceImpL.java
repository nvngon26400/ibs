package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.enums.AdjustType;
import com.sbisec.helios.ap.athena.ifa.ExchangeService;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.protocol.account.accounts.GetJrNisaAccountStatusReq;
import com.sbisec.helios.ap.athena.protocol.account.accounts.GetJrNisaAccountStatusRes;
import com.sbisec.helios.ap.athena.protocol.account.client_entry.cashing.MultiGetPossibleWithdrawalsReq;
import com.sbisec.helios.ap.athena.protocol.account.client_entry.cashing.MultiGetPossibleWithdrawalsRes;
import com.sbisec.helios.ap.athena.protocol.account.dto.PossibleWithdrawalInput;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetExchangeTradeCurrencyReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetExchangeTradeCurrencyRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeRatesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeRatesRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ConfirmExchangeCreatedOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ConfirmExchangeCreatedOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeBusinessDateReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeBusinessDateRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ListExchangeOrdersReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ListExchangeOrdersRes;
import com.sbisec.helios.ap.athena.utils.RequestUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct004;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct004Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct004Dto;
//import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaFxOrderInputDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderInputA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderInputA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderInputA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderInputA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderInputA008DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderInputA008DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderInputErrorModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaFxOrderInputService;
import com.sbisec.helios.ap.common.enums.CurrencyCode;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ForeignSecurityTradeAccountOpenStatus;
import com.sbisec.helios.ap.common.enums.ForeignTradeClass;
import com.sbisec.helios.ap.common.enums.FxQuantityDesignationMethod;
import com.sbisec.helios.ap.common.enums.NationalityCode;
import com.sbisec.helios.ap.common.enums.SecurityMoneyClass;
import com.sbisec.helios.ap.common.enums.SellBuyType;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 画面ID：SUB0202_0502-02_1
 * 画面名：為替注文入力
 * @author 松田
 *
 * 2023/09/16 新規作成
 */
@Component(value = "cmpIfaFxOrderInputService")
public class IfaFxOrderInputServiceImpL implements IfaFxOrderInputService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaFxOrderInputServiceImpL.class);
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 入力した部店口座は存在しません。 */
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    /** 取引停止口座のため処理を進めることができません。 */
    private static final String ERRORS_CMN_SELECTED_ACCOUNT_OUT_OF_SERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** 選択した通貨は現在お取引できません。 */
    private static final String ERRORS_EXT_ORDER_EXECUTION_UNAVAILABLE = "errors.ext.orderExecution.unavailable";
    
    /** 外貨建商品取引口座が未開設です。 */
    private static final String ERRORS_CMN_FOREIGN_SECURITIES_ACCOUNT_NOT_OPEN = "errors.cmn.foreignSecuritiesAccount.notOpen";
    
    /** {0}ができないコースです。 */
    private static final String ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";

    /** CCSIDが未登録のためご利用できません。 */
    private static final String ERRORS_CMN_CCSID_UNREGISTERED = "errors.cmn.ccsid.unregistered";
    
    /** 売却可能数量が不足しているため、注文できません。 \\u30FB売却数量={0}[{4}] \\u30FBリアルタイム余力={1}[{4}] \\u30FB当日店頭買付約定金額={2}[{4}] \\u30FB外国債券の当日約定金額（買付）={3}[{4}] */
    private static final String ERRORS_EXT_COUNT_UNIT_OVERFLOW = "errors.ext.countUnit.overflow";
    
    /** 定時為替取引では、同一約定タイミング内の「買付→売却」、「売却→買付」は制限されています。 前の注文を取り消すことで新しい注文を行えるようになります。 */
    private static final String ERRORS_EXT_REVERSE_TRADE_EXIST = "errors.ext.reverseTrade.exist";
    
    /** 閾値超過メッセージ(メッセージID無し) */
    private static final String ERRORS_OVER_WARNING_THRESHOLD = "注文金額が%s（しきい値）を超えています。";
    
    // --------------------------------
    // 変数定義
    // --------------------------------
    private static final String EXT_COUNT_UNIT_OVERFLOW_SUBSTITUTE = "0";
    
    /** FCT001結果：対象顧客参照権限無し */
    private static final String FCT001_NO_AUTH = "0";
    
    /** FCT001結果：取引停止口座 */
    private static final String FCT001_NO_TRADE = "1";
    
    /** 為替取引 */
    private static final String FX_TRADE = "1";
    
    /** 対象取引（メッセージ表示用）:ドメインID */
    private static final String CODE_MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** APIリクエスト固定値:為替 */
    private static final String API_REQUEST_EXCHANGE = "EXCHANGE";
    
    /** API001サービスタイプ：為替取引注文 */
    private static final String API_SERVICE_TYPE = "ORDER";
    
    /** API008注文状況:固定値 */
    private static final String API_EXCHANGE_ORDER_STATUS = "2";
    
    /** 取引区分:保証金振替（預り金→保証金） */
    private static final String TRADE_TYPE_CASH_DEPOSIT = "CASH_DEPOSIT";
    
    /** API002為替取引:定時為替 */
    private static final String API_FX_TRADE = "REGULAR";
    
    /** 乗せ金額(パーセント)算出値 */
    private static final BigDecimal ADJUST_CALCULATION = new BigDecimal(100);
    
    /** 通貨コード：JPY */
    private static final String CURRENCY_CODE_JPY = "JPY";
    
    /** API007売却方法区分:固定値 */
    private static final String API_SELL_METHOD_SELL_PART = "SELL_PART";
    
    /** 外貨スピンボタン増減幅:設定値100 */
    private static final String FOREIGN_SPIN_BUTTON_RANGE_100_VALUE = "100";
    
    /** 外貨スピンボタン増減幅:設定値10000 */
    private static final String FOREIGN_SPIN_BUTTON_RANGE_10000_VALUE = "10000";
    
    /** 外貨スピンボタン増減幅:設定値10000対象 */
    private static final List<CurrencyCode> FOREIGN_SPIN_BUTTON_RANGE_10000 = Arrays
            .asList(new CurrencyCode[] { CurrencyCode.VND, CurrencyCode.KRW, CurrencyCode.IDR });
    
    /** 日付フォーマット YYYY/MM/DD HH:MM */
    private static final String FORMAT_YYYYMMDDHHMM = "yyyy/MM/dd HH:mm";
    
    /** FCT003_媒介可否:不可 */
    private static final String FCT003_MEDIATE_PROPRIETY_NG = "0";
    
    /** 口座分類（為替取引） */
    private enum AccountType {
        
        GENERAL("1", "GENERAL"), JR_NISA("2", "JR_NISA");
        
        AccountType(String id, String name) {
            
            this.id = id;
            this.name = name;
        }
        
        private String id;
        
        private String name;
        
        public String getId() {
            
            return id;
        }
        
        public String getName() {
            
            return name;
        }
        
        public static AccountType getById(String id) {
            
            if (StringUtils.isEmpty(id)) {
                return null;
            }
            
            AccountType[] enums = values();
            
            for (int i = 0; i < enums.length; i++) {
                if (enums[i].getId().equals(id)) {
                    return enums[i];
                }
            }
            
            return null;
        }
        
    }
    
    /**
     * API呼び出しクラス(Athena)
     */
    @Autowired
    private ExchangeService exchangeService;
    
    @Autowired
    private ForeignAccountService foreignAccountService;
    
    /**
     * FCT001
     */
    @Autowired
    private Fct001 fct001;
    
    /**
     * FCT003
     */
    @Autowired
    private Fct003 fct003;
    
    /**
     * FCT004
     */
    @Autowired
    private Fct004 fct004;
    
    /**
     * 区分値取得クラス
     */
    @Autowired
    CodeListService codeListService;
    
    @Autowired
    private CometCommonService cometCommonService;
    
    //    @Autowired
    //    private IfaFxOrderInputDao dao;
    /**
     * 各Action共通レスポンスクラス
     *
     */
    @Data
    private class CommonIfaFxOrderInputDtoResponse {
        
        /** 通貨コード（全角半角）. */
        private String currencyCode;
        
        /** 売買区分（全角半角）. */
        private String tradeKbn;
        
        /** 通貨名（全角）. */
        private String currency;
        
        /** 小数部有効桁数（数値(整数)）. */
        private String decimalLength;
        
        /** 為替グループ（全角半角）. */
        private String exchangeGroup;
        
        /** 注文ワーニングしきい値（数値(整数)）. */
        private String warningThreshold;
        
        /** 取引下限（数値(小数)）. */
        private String closableQuantity;
        
        /** 取引上限（数値(小数)）. */
        private String tradeLimitMax;
        
        /** 取引単位. */
        private String tradeLimitMin;
        
        /** 外貨スピンボタン増減幅. */
        private String foreignSpinButtonRange;
        
        /** 参考レート（数値(小数)）. */
        private String referenceRate;
        
        /** 概算用レート. */
        private String approximationRate;
        
        /** デノミ（数値(整数)）. */
        private String denominator;
        
        /** 注文可能数量（数値(整数)）. */
        private String maxOrderableQuantity;
        
        /** 概算買付可能金額（数値(整数)）. */
        private String approximatePurchaseAmount;
        
        /** 外貨指定最大値. */
        private String foreignCurrencyMax;
        
        /** 円指定最小値. */
        private String yenCurrencyMin;
        
        /** 円指定最大値. */
        private String yenCurrencyMax;
        
        /** ジュニアNISA口座状態. */
        private String jrNisaAccountStatus;
    }
    
    @Data
    private class BuySellDto {
        
        /** 売買区分＝買付 */
        private Boolean isBuyType;
        
        /** 売買区分＝売却 */
        private Boolean isSellType;
        
        /** 売買区分 */
        private String sellBuyType;
        
        /** 口座分類 */
        private AccountType at;
    }
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaFxOrderInputA001DtoRequest
     * Dto レスポンス：IfaFxOrderInputA001DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaFxOrderInputA001DtoResponse> initializeA001(IfaFxOrderInputA001DtoRequest dtoReq)
            throws Exception {
        
        List<IfaFxOrderInputA001DtoResponse> responseList = new ArrayList<IfaFxOrderInputA001DtoResponse>();
        IfaFxOrderInputA001DtoResponse response = new IfaFxOrderInputA001DtoResponse();
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaFxOrderInputServiceImplL.initializeA001");
        
        IfaFxOrderInputErrorModel error = new IfaFxOrderInputErrorModel();
        BuySellDto buySellDto = new BuySellDto();
        
        // 変数初期化
        init(dtoReq.getTradeKbn(), null, buySellDto);
        // 共通処理実施
        CommonIfaFxOrderInputDtoResponse commonResponse = commonAction(dtoReq, error, buySellDto);
        if (commonResponse == null) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
        }
        
        // レスポンス項目設定
        copyFields(commonResponse, response);
        responseList.add(response);
        
        return IfaCommonUtil.createDataList(responseList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(),
                null);
    }
    
    /**
     * アクションID：A002
     * アクション名：更新
     * Dto リクエスト：IfaFxOrderInputA002DtoRequest
     * Dto レスポンス：IfaFxOrderInputA002DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaFxOrderInputA002DtoResponse> updateA002(IfaFxOrderInputA002DtoRequest dtoReq) throws Exception {
        
        IfaFxOrderInputA002DtoResponse response = new IfaFxOrderInputA002DtoResponse();
        List<IfaFxOrderInputA002DtoResponse> responseList = new ArrayList<IfaFxOrderInputA002DtoResponse>();
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaFxOrderInputServiceImplL.updateA002");
        
        IfaFxOrderInputErrorModel error = new IfaFxOrderInputErrorModel();
        BuySellDto buySellDto = new BuySellDto();
        
        // 変数初期化
        init(dtoReq.getTradeKbn(), dtoReq.getAccountType(), buySellDto);
        // 共通処理実施
        CommonIfaFxOrderInputDtoResponse commonResponse = commonAction(dtoReq, error, buySellDto);
        if (!StringUtil.isNullOrEmpty(error.getErrorCode()) && !StringUtil.isNullOrEmpty(error.getErrorMessage())) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
        }
        // レスポンス項目設定
        copyFields(commonResponse, response);
        /* 概算金額算出 */
        // 概算受渡金額
        if (!StringUtil.isNullOrEmpty(dtoReq.getForeignVolume())) {
            response.setApproximateDeliveryAmount(calculationVolume(dtoReq.getForeignVolume(),
                    commonResponse.getDenominator(), commonResponse.getApproximationRate(), true));
        }
        
        // 概算外貨数量
        if (!StringUtil.isNullOrEmpty(dtoReq.getDomesticVolume())) {
            response.setForeignApproximateDeliveryAmount(calculationVolume(dtoReq.getDomesticVolume(),
                    commonResponse.getDenominator(), commonResponse.getApproximationRate(), false));
        }
        // 更新日時
        response.setUpdateTime(DateUtil.format(FORMAT_YYYYMMDDHHMM));
        
        responseList.add(response);
        
        return IfaCommonUtil.createDataList(responseList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(),
                null);
        
    }
    
    /**
     * アクションID：A008
     * アクション名：注文確認
     * Dto リクエスト：IfaFxOrderInputA008DtoRequest
     * Dto レスポンス：IfaFxOrderInputA008DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaFxOrderInputA008DtoResponse> orderConfirmationA008(IfaFxOrderInputA008DtoRequest dtoReq)
            throws Exception {
        
        DataList<IfaFxOrderInputA008DtoResponse> dtoRes = new DataList<IfaFxOrderInputA008DtoResponse>();
        List<IfaFxOrderInputA008DtoResponse> responseList = new ArrayList<IfaFxOrderInputA008DtoResponse>();
        IfaFxOrderInputA008DtoResponse response = new IfaFxOrderInputA008DtoResponse();
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaFxOrderInputServiceImplL.orderConfirmationA008");
        
        IfaFxOrderInputErrorModel error = new IfaFxOrderInputErrorModel();
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        UserAccount ua = IfaCommonUtil.getUserAccount();
        BuySellDto buySellDto = new BuySellDto();
        
        // 変数初期化
        init(dtoReq.getTradeKbn(), dtoReq.getAccountType(), buySellDto);
        
        // 事前チェック
        if (!validationCheckCommon(dtoReq.getCurrencyCode(), dtoReq.getTradeKbn(), cc, error, buySellDto, true)) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
        }
        
        // 為替注文金額の設定
        BigDecimal fxOrderAmount = BigDecimal.ZERO;
        
        if (StringUtils.equals(FxQuantityDesignationMethod.FOREIGN.getId(), dtoReq.getQuantityDesignationMethod())) {
            fxOrderAmount = StringUtil.parseBigDecimal(dtoReq.getForeignVolume());
        } else if (StringUtils.equals(FxQuantityDesignationMethod.YEN.getId(), dtoReq.getQuantityDesignationMethod())) {
            fxOrderAmount = StringUtil.parseBigDecimal(dtoReq.getForeignApproximateDeliveryAmount());
        }
        // ワーニング閾値チェック(#,##0形式想定)
        BigDecimal warningThresholdBd = StringUtil.parseBigDecimal(dtoReq.getWarningThreshold().replaceAll(",", ""));
        if (!dtoReq.getWarningThreshold().replaceAll(",", "").equals("999999999")) {
            if (fxOrderAmount.compareTo(warningThresholdBd) > 0) {
                response.setOrderWarningexceedingThreshold(String.format(ERRORS_OVER_WARNING_THRESHOLD,
                        dtoReq.getWarningThreshold() + dtoReq.getCurrencyCode()));
            }
        }
        
        if (buySellDto.getIsSellType() && StringUtils.equals(dtoReq.getCurrencyCode(), CurrencyCode.USD.getId())) {
            // 売却なら売却可能数量不足チェック
            if (!isCanQuantityAvailableForSale(dtoReq.getCurrencyCode(), dtoReq.getExchangeGroup(), fxOrderAmount, cc,
                    error, buySellDto)) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, error.getErrorCode(),
                        error.getErrorMessage());
            }
        }
        ConfirmExchangeCreatedOrderRes api007Result = new ConfirmExchangeCreatedOrderRes();
        try {
            // API007
            api007Result = callAPI007(dtoReq.getCurrencyCode(), fxOrderAmount.toString(), cc, ua, buySellDto);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil
                    .createDataList(new ArrayList<>(), ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        // API002
        GetExchangeTradeCurrencyRes api002Response = null;
        try {
            api002Response = callAPI002(dtoReq.getCurrencyCode());            
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        if (isExistsSameExecutionDatetime(dtoReq.getCurrencyCode(),
                api002Response.getExchangeTradeType(), cc, buySellDto, error)) {
            error.setErrorCode(ERRORS_EXT_REVERSE_TRADE_EXIST);
            error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_EXT_REVERSE_TRADE_EXIST));
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
        } else {
            if (!StringUtil.isNullOrEmpty(error.getErrorCode()) && !StringUtil.isNullOrEmpty(error.getErrorMessage())) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
            }
        }
        
        // API003処理（レート情報を取得）
        ListExchangeTradeRatesRes api003Response = new ListExchangeTradeRatesRes();
        try {
            api003Response = callAPI003(dtoReq.getCurrencyCode(), cc, buySellDto, ua);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        BigDecimal adjustPercentResult = BigDecimal.ZERO;
        if (api003Response != null && !CollectionUtils.isEmpty(api003Response.getExchangeTradeRates())
                && StringUtils.equals(api003Response.getExchangeTradeRates().get(0).getAdjustType(),
                        AdjustType.ADJUST_PERCENT.getId())) {
            // 上乗せ金額算出
            BigDecimal adjustPercent = StringUtil
                    .parseBigDecimal(api003Response.getExchangeTradeRates().get(0).getAdjustPercent());
            adjustPercentResult = adjustPercent.subtract(ADJUST_CALCULATION);
        }
        
        // responseの設定
        setA008Response(response, dtoReq, api003Response, api007Result, adjustPercentResult);
        responseList.add(response);
        dtoRes = IfaCommonUtil.createDataList(responseList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(),
                null);
        return dtoRes;
    }
    
    /**
     * API008取得件数チェック
     * @param currencyCode 通貨コード
     * @param exchangeGroup 為替グループ
     * @return　true:>0　false:0
     * @throws Exception
     */
    private boolean isExistsSameExecutionDatetime(String currencyCode, String exchangeGroup, CustomerCommon cc,
            BuySellDto buySellDto, IfaFxOrderInputErrorModel error)
            throws Exception {
        
        if (!StringUtils.equals(exchangeGroup, API_FX_TRADE)) {
            return false;
        }
        ListExchangeOrdersRes api008Response = null;
        try {            
            api008Response = callAPI008(currencyCode, cc, buySellDto);
        } catch (Exception e) {
            DataList<?> dataList = cometCommonService.checkBussinessException(IfaCommonUtil
                    .createDataList(new ArrayList<>(), ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            error.setErrorCode(dataList.getReturnCode());
            error.setErrorMessage(dataList.getMessage());
            return false;
        }
        
        if (api008Response == null || CollectionUtils.isEmpty(api008Response.getOrderDetails())) {
            return false;
        }

        return true;
    }
    
    /**
     * 売却なら売却可能数量不足チェック
     * @param currencyCode 通貨コード
     * @param exchangeGroup 為替グループ
     * @param fxOrderAmount 為替注文金額
     * @return true:OK false:NG
     * @throws Exception
     */
    private boolean isCanQuantityAvailableForSale(String currencyCode, String exchangeGroup, BigDecimal fxOrderAmount,
            CustomerCommon cc, IfaFxOrderInputErrorModel error, BuySellDto buySellDto)
            throws Exception {
        
        GetExchangeBusinessDateRes api004Response = new GetExchangeBusinessDateRes();
        MultiGetPossibleWithdrawalsRes api005Response = new MultiGetPossibleWithdrawalsRes();
        OutputFct004Dto fct004Result = new OutputFct004Dto();
        // API004処理(営業日情報を取得)
        try {            
            api004Response = callAPI004(exchangeGroup);
        } catch (Exception e) {
            DataList<?> dataList = cometCommonService.checkBussinessException(IfaCommonUtil
                    .createDataList(new ArrayList<>(), ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            error.setErrorCode(dataList.getReturnCode());
            error.setErrorMessage(dataList.getMessage());
            return false;
        }
        
        // API005処理(出金可能額を取得)
        try {            
            api005Response = callAPI005(currencyCode, api004Response.getDepositWithdrawalDate(), cc, buySellDto);
        } catch (Exception e) {
            DataList<?> dataList = cometCommonService.checkBussinessException(IfaCommonUtil
                    .createDataList(new ArrayList<>(), ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            error.setErrorCode(dataList.getReturnCode());
            error.setErrorMessage(dataList.getMessage());
            return false;
        }
        
        // 計算後の余力金額を取得する。FCT004
        fct004Result = callFCT004(cc, buySellDto);
        
        // 注文可能数量を計算
        BigDecimal maxOrderableQuantity = new BigDecimal(
                api005Response.getPossibleWithdrawals().get(0).getWithdrawalPossibleAmount())
                        .add(fct004Result.getByingPowerMoneyAfterCalculate());
        
        // 注文金額<=注文可能数量であることをチェック
        if (fxOrderAmount.compareTo(maxOrderableQuantity) > 0) {
            // FCT004が呼ばれなかった場合は0を代入する
            String realTimeBuyingPower = fct004Result.getRealTimeBuyingPower() != null
                    ? fct004Result.getRealTimeBuyingPower().toString()
                    : EXT_COUNT_UNIT_OVERFLOW_SUBSTITUTE;
            String otcBuyingContractAmountToday = fct004Result.getOtcBuyingContractAmountToday() != null
                    ? fct004Result.getOtcBuyingContractAmountToday().toString()
                    : EXT_COUNT_UNIT_OVERFLOW_SUBSTITUTE;
            String contractAmountTodayWithinForeignBond = fct004Result.getContractAmountTodayWithinForeignBond() != null
                    ? fct004Result.getContractAmountTodayWithinForeignBond().toString()
                    : EXT_COUNT_UNIT_OVERFLOW_SUBSTITUTE;
            
            error.setErrorCode(ERRORS_EXT_COUNT_UNIT_OVERFLOW);
            error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_EXT_COUNT_UNIT_OVERFLOW,
                    new String[] { fxOrderAmount.toString(),
                            api005Response.getPossibleWithdrawals().get(0).getWithdrawalPossibleAmount().toString(),
                            otcBuyingContractAmountToday,
                            contractAmountTodayWithinForeignBond, currencyCode }));
            return false;
        }
        
        return true;
    }
    
    /**
     *
     * @param dtoReq dtoReq
     * @param error エラーメッセージ
     * @param buySellDto
     * @return
     * @throws Exception
     */
    private CommonIfaFxOrderInputDtoResponse commonAction(Object dtoReq, IfaFxOrderInputErrorModel error, BuySellDto buySellDto)
            throws Exception {
        boolean isUpdate = false;
        // 通貨コード
        String currencyCode = null;
        // 取引区分
        String tradeKbn = null;
        // 為替グループ
        String exchangeGroup = null;
        String decimalLength = null;
        // 取引上限
        String tradeLimitMax = null;
        // 取引単位
        String tradeLimitMin = null;
        // 取引下限
        String closableQuantity = null;
        if (dtoReq instanceof IfaFxOrderInputA001DtoRequest) {
            IfaFxOrderInputA001DtoRequest a001DtoReq = (IfaFxOrderInputA001DtoRequest) dtoReq;
            currencyCode = a001DtoReq.getCurrencyCode();
            tradeKbn = a001DtoReq.getTradeKbn();
        } else {
            isUpdate = true;
            IfaFxOrderInputA002DtoRequest a002DtoReq = (IfaFxOrderInputA002DtoRequest) dtoReq;
            currencyCode = a002DtoReq.getCurrencyCode();
            tradeKbn = a002DtoReq.getTradeKbn();
            exchangeGroup = a002DtoReq.getExchangeGroup();
            decimalLength = a002DtoReq.getDecimalLength();
            tradeLimitMax = a002DtoReq.getTradeLimitMax();
            tradeLimitMin = a002DtoReq.getTradeLimitMin();
            closableQuantity = a002DtoReq.getClosableQuantity();
        }
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        UserAccount ua = IfaCommonUtil.getUserAccount();
        // 事前チェック
        if (!validationCheckCommon(currencyCode, tradeKbn, cc, error, buySellDto, false)) {
            return null;
        }
        
        String inputExchangeGroup = exchangeGroup;
        GetExchangeTradeCurrencyRes api002Response = null;
        /* 先にAPIの情報を全て取得する */
        // API002処理（通貨情報取得）
        if (StringUtil.isNullOrEmpty(inputExchangeGroup)) {
            try {
                api002Response = callAPI002(currencyCode);
            } catch (Exception e) {
                DataList<?> dataList = cometCommonService.checkBussinessException(IfaCommonUtil
                        .createDataList(new ArrayList<>(), ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
                error.setErrorCode(dataList.getReturnCode());
                error.setErrorMessage(dataList.getMessage());
                return null;
            }
            inputExchangeGroup = api002Response.getExchangeGroup();
        }
        
        // API003処理（レート情報を取得）
        ListExchangeTradeRatesRes api003Response = null;
        try {    
            api003Response = callAPI003(currencyCode, cc, buySellDto, ua);
        } catch (Exception e) {
            DataList<?> dataList = cometCommonService.checkBussinessException(IfaCommonUtil
                    .createDataList(new ArrayList<>(), ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            error.setErrorCode(dataList.getReturnCode());
            error.setErrorMessage(dataList.getMessage());
            return null;
        }
        
        // API004処理(営業日情報を取得)
        GetExchangeBusinessDateRes api004Response = null;
        try {            
            api004Response = callAPI004(inputExchangeGroup);
        } catch (Exception e) {
            DataList<?> dataList = cometCommonService.checkBussinessException(IfaCommonUtil
                    .createDataList(new ArrayList<>(), ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            error.setErrorCode(dataList.getReturnCode());
            error.setErrorMessage(dataList.getMessage());
            return null;
        }
        
        // API005処理(出金可能額を取得)
        MultiGetPossibleWithdrawalsRes api005Response = null;
        try {
            api005Response = callAPI005(currencyCode, api004Response.getDepositWithdrawalDate(), cc, buySellDto);
        } catch (Exception e) {
            DataList<?> dataList = cometCommonService.checkBussinessException(IfaCommonUtil
                    .createDataList(new ArrayList<>(), ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            error.setErrorCode(dataList.getReturnCode());
            error.setErrorMessage(dataList.getMessage());
            return null;
        }
        
        // API006(外貨建口座JrNISA口座開設ステータスを取得)
        GetJrNisaAccountStatusRes api006Response = null;
        CommonIfaFxOrderInputDtoResponse commonResponse = new CommonIfaFxOrderInputDtoResponse();
        if (!isUpdate) {
            try {            
                api006Response = callAPI006(cc);
            } catch (Exception e) {
                DataList<?> dataList = cometCommonService.checkBussinessException(IfaCommonUtil
                        .createDataList(new ArrayList<>(), ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
                error.setErrorCode(dataList.getReturnCode());
                error.setErrorMessage(dataList.getMessage());
                return null;
            }
        } else {
            // 取引下限
            commonResponse.setClosableQuantity(closableQuantity);
            // 取引上限
            commonResponse.setTradeLimitMax(tradeLimitMax);
            // 取引単位
            commonResponse.setTradeLimitMin(tradeLimitMin);
        }
        
        // レスポンス項目設定
        setResponseCommon(commonResponse, currencyCode, tradeKbn, decimalLength, api002Response, api003Response,
                api005Response, api006Response, cc, buySellDto);
        
        return commonResponse;
    }
    
    /**
     * 共通事前チェック
     * @param currencyCode 通貨コード
     * @param tradeKbn 売買区分
     * @return trues:OK　false:NG
     * @throws Exception
     */
    private boolean validationCheckCommon(String currencyCode, String tradeKbn, CustomerCommon cc,
            IfaFxOrderInputErrorModel error, BuySellDto buySellDto, Boolean isCheckCCS) throws Exception {
        
        // FCT001：利用者顧客参照権限チェック
        if (!callFCT001(cc, error)) {
            return false;
        }
        
        // 外貨建口座取引開設状況をチェック
        if (StringUtils.equals(cc.getForeignSecurityTradeAccountOpenStatus(),
                ForeignSecurityTradeAccountOpenStatus.CLOSED.getId())
                && StringUtils.equals(cc.getForeignStockTradeAccountOpenStatus(),
                        ForeignSecurityTradeAccountOpenStatus.CLOSED.getId())) {
            error.setErrorCode(ERRORS_CMN_FOREIGN_SECURITIES_ACCOUNT_NOT_OPEN);
            error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_CMN_FOREIGN_SECURITIES_ACCOUNT_NOT_OPEN));
            
            return false;
        }
        
        // FCT003
        if (!callFCT003(currencyCode, tradeKbn, cc, error)) {
            return false;
        }

        // ユーザ共通情報 .CCSログイン用IDが未設定(Null または空文字）の場合：取引不可エラーを返す
        // A008アクションのみチェックを行う
        if (isCheckCCS) {
            UserAccount ua = IfaCommonUtil.getUserAccount();
            if (StringUtils.isEmpty(ua.getCcsUserId())) {
                error.setErrorCode(ERRORS_CMN_CCSID_UNREGISTERED);
                error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_CMN_CCSID_UNREGISTERED));

                return false;
            }
        }
        
        // API001処理(通貨別サービスステータスチェックAPI)
        CheckExchangeCurrencyServiceStatusRes api001Response = null;
        try {
            api001Response = callAPI001(currencyCode, buySellDto);
        } catch (Exception e) {
            DataList<?> dataList = cometCommonService.checkBussinessException(IfaCommonUtil
                    .createDataList(new ArrayList<>(), ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            error.setErrorCode(dataList.getReturnCode());
            error.setErrorMessage(dataList.getMessage());
            return false;
        }
        if (!api001Response.isAvailable()) {
            error.setErrorCode(ERRORS_EXT_ORDER_EXECUTION_UNAVAILABLE);
            error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_EXT_ORDER_EXECUTION_UNAVAILABLE));
            return false;
        }
        return true;
    }
    
    /**
     * 共通レスポンス項目設定処理
     * @param response
     * @param currencyCode
     * @param tradeKbn
     * @param decimalLength
     * @param api002Response
     * @param api003Response
     * @param api005Response
     * @param api006Response
     */
    private void setResponseCommon(CommonIfaFxOrderInputDtoResponse response, String currencyCode, String tradeKbn,
            String decimalLength, GetExchangeTradeCurrencyRes api002Response, ListExchangeTradeRatesRes api003Response,
            MultiGetPossibleWithdrawalsRes api005Response, GetJrNisaAccountStatusRes api006Response,
            CustomerCommon cc, BuySellDto buySellDto)  throws Exception {
        
        // --------------------
        // 共通項目を先に設定
        // --------------------
        // 通貨コード
        response.setCurrencyCode(currencyCode);
        // 売買区分
        response.setTradeKbn(tradeKbn);
        
        // 小数点有効桁数
        int editDecimalLength = 0;
        
        if (api002Response != null) {
            // 通貨名
            response.setCurrency(api002Response.getCurrencyName());
            // 小数部有効桁数
            response.setDecimalLength(String.valueOf(api002Response.getDecimalLength()));
            editDecimalLength = Integer.valueOf(api002Response.getDecimalLength());
            // 為替グループ
            response.setExchangeGroup(api002Response.getExchangeGroup());
            // 注文ワーニングしきい値
            response.setWarningThreshold(api002Response.getWarningThreshold());
        } else {
            editDecimalLength = Integer.valueOf(decimalLength);
        }
        
        // 参考レート （API003が複数件の場合は先頭のみ参照）
        BigDecimal referenceRateBd = StringUtil
                .parseBigDecimal(api003Response.getExchangeTradeRates().get(0).getReferenceExchangeRate());
        
        response.setReferenceRate(referenceRateBd.setScale(editDecimalLength, RoundingMode.DOWN).toString());
        // 概算用レート （API003が複数件の場合は先頭のみ参照）
        response.setApproximationRate(api003Response.getExchangeTradeRates().get(0).getComputeExchangeRate());
        // デノミ （API003が複数件の場合は先頭のみ参照）
        response.setDenominator(api003Response.getExchangeTradeRates().get(0).getBasePrice());
        if (api006Response != null) {
            // ジュニアNISA口座状態            
            response.setJrNisaAccountStatus(api006Response.getJrNisaAccountStatusCode());
        }
        // 外貨スピンボタン増減幅
        CurrencyCode currency = CurrencyCode.getById(currencyCode);
        if (currency == null) {
            response.setForeignSpinButtonRange(null);
        } else if (FOREIGN_SPIN_BUTTON_RANGE_10000.contains(currency)) {
            response.setForeignSpinButtonRange(FOREIGN_SPIN_BUTTON_RANGE_10000_VALUE);
        } else {
            response.setForeignSpinButtonRange(FOREIGN_SPIN_BUTTON_RANGE_100_VALUE);
        }
        
        // ------------------------------
        // 買付・売却で設定値が変わる項目の設定
        // ------------------------------
        if (buySellDto.getIsBuyType()) {
            setResponseInitializeBuy(response, api002Response, api003Response, api005Response);
        } else if (buySellDto.getIsSellType()) {
            setResponseInitializeSell(response, currencyCode, api002Response, api003Response, api005Response, cc,
                    buySellDto);
        }
        // ------------------------------
        // 注文可能数量算出後に設定可能な項目
        // ------------------------------
        BigDecimal computeExchangeRate = StringUtil
                .parseBigDecimal(api003Response.getExchangeTradeRates().get(0).getComputeExchangeRate());
        BigDecimal basePrice = StringUtil.parseBigDecimal(api003Response.getExchangeTradeRates().get(0).getBasePrice());
        BigDecimal maxOrderableQuantity = StringUtil.parseBigDecimal(response.getMaxOrderableQuantity());
        // 外貨指定最大値 (注文可能数量または取引上限のうち小さいほう)
        // ただし、取引上限＝0の場合、注文可能数量と9999999999999.9999の小さい方とする
        if (response.getTradeLimitMax().equals("0")) {
            if (maxOrderableQuantity.compareTo(StringUtil.parseBigDecimal("9999999999999.9999")) < 0) {
                response.setForeignCurrencyMax(response.getMaxOrderableQuantity());
            } else {
                response.setForeignCurrencyMax("9999999999999.9999");
            }
        } else {            
            if (maxOrderableQuantity.compareTo(StringUtil.parseBigDecimal(response.getTradeLimitMax())) < 0) {
                response.setForeignCurrencyMax(response.getMaxOrderableQuantity());
            } else {
                response.setForeignCurrencyMax(response.getTradeLimitMax());
            }
        }
        // 円指定最小値
        // 取引下限×API003.概算用レート÷API003.デノミ（計算結果を小数点以下切り上げ）
        BigDecimal yenMin = StringUtil.parseBigDecimal(response.getClosableQuantity()).multiply(computeExchangeRate)
                .divide(basePrice, 0, RoundingMode.UP);
        response.setYenCurrencyMin(yenMin.toString());
        // 円指定最大値
        // [注文可能数量または取引上限のうち小さいほう]×API003.概算用レート÷API003.デノミ（計算結果を小数点以下切り上げ）
        // ただし、取引上限＝0の場合、取引上限を9999999999999.9999として算出する
        // ※ 注文可能数量または取引上限のうち小さいほうは外貨指定最大値と一致するので、その値を利用する
        BigDecimal yenLimitMax = StringUtil.parseBigDecimal(response.getForeignCurrencyMax())
                .multiply(computeExchangeRate)
                .divide(basePrice, 0, RoundingMode.UP);
        response.setYenCurrencyMax(yenLimitMax.toPlainString());
        
        if (buySellDto.getIsBuyType()) {
            // 概算買付可能金額(買付の場合のみ算出)
            // 注文可能数量÷API003.レート情報.デノミ×API003.レート情報.概算用レート（計算結果を小数点以下切り捨て）
            BigDecimal approximatePurchaseAmount = maxOrderableQuantity
                            .multiply(computeExchangeRate)
                            .divide(basePrice, 0, RoundingMode.FLOOR);
            response.setApproximatePurchaseAmount(approximatePurchaseAmount.toString());
        }
        
    }
    
    /**
     * 共通レスポンス項目設定（買付）
     * @param response レスポンス
     * @param dtoReq　リクエスト
     * @param api002Response　API002処理結果
     * @param api003Response　API003処理結果
     * @param api005Response　API005処理結果
     */
    private void setResponseInitializeBuy(CommonIfaFxOrderInputDtoResponse response,
            GetExchangeTradeCurrencyRes api002Response, ListExchangeTradeRatesRes api003Response,
            MultiGetPossibleWithdrawalsRes api005Response) {
        
        if (api002Response != null) {
            // 取引下限
            response.setClosableQuantity(api002Response.getBuyLimitMin());
            // 取引上限
            response.setTradeLimitMax(api002Response.getBuyLimitMax());
            // 取引単位
            response.setTradeLimitMin(api002Response.getBuyUnit());
        }
        // 注文可能数量 （API005が複数件の場合は先頭のみ参照）
        response.setMaxOrderableQuantity(calculationMaxOrderableQuantityBuy(
                api005Response.getPossibleWithdrawals().get(0).getWithdrawalPossibleAmount(),
                api003Response.getExchangeTradeRates().get(0).getBasePrice(),
                api003Response.getExchangeTradeRates().get(0).getComputeExchangeRate()).toString());
        
    }
    
    /**
     * 共通レスポンス項目設定（売却）
     * @param response レスポンス
     * @param dtoReq　リクエスト
     * @param api002Response　API002処理結果
     * @param api003Response　API003処理結果
     * @param api005Response　API005処理結果
     */
    private void setResponseInitializeSell(CommonIfaFxOrderInputDtoResponse response, String currencyCode,
            GetExchangeTradeCurrencyRes api002Response, ListExchangeTradeRatesRes api003Response,
            MultiGetPossibleWithdrawalsRes api005Response, CustomerCommon cc, BuySellDto buySellDto) throws Exception {
        
        // 計算後の余力金額
        BigDecimal byingPowerMoneyAfterCalculate = null;
        
        if (api002Response != null) {
            // 取引下限
            response.setClosableQuantity(api002Response.getSellLimitMin());
            // 取引上限
            response.setTradeLimitMax(api002Response.getSellLimitMax());
            // 取引単位
            response.setTradeLimitMin(api002Response.getSellUnit());
        }
        
        if (StringUtils.equals(currencyCode, CurrencyCode.USD.getId())) {
            byingPowerMoneyAfterCalculate = callFCT004(cc, buySellDto).getByingPowerMoneyAfterCalculate();
        }
        
        // 注文可能数量
        response.setMaxOrderableQuantity(calculationMaxOrderableQuantitySell(currencyCode,
                api005Response.getPossibleWithdrawals().get(0).getWithdrawalPossibleAmount(),
                byingPowerMoneyAfterCalculate).toString());
    }
    
    /**
     * 注文可能数量算出（買付）
     * @param withdrawalPossibleAmount 出勤可能額
     * @param basePrice デノミ
     * @param computeExchangeRate　概算用
     * @return 注文可能数量
     */
    private BigDecimal calculationMaxOrderableQuantityBuy(String withdrawalPossibleAmount, String basePrice,
            String computeExchangeRate) {
        
        BigDecimal withdrawalPossibleAmountBd = StringUtil.parseBigDecimal(withdrawalPossibleAmount);
        BigDecimal basePriceBd = StringUtil.parseBigDecimal(basePrice);
        BigDecimal computeExchangeRateBd = StringUtil.parseBigDecimal(computeExchangeRate);
        
        BigDecimal canOrderCount = BigDecimal.ZERO;
        // （API005.出金可能額×API003.レート情報.デノミ÷API003.レート情報.概算用レート（計算結果を小数点以下切り捨て））
        canOrderCount = withdrawalPossibleAmountBd.multiply(basePriceBd).divide(computeExchangeRateBd, 0,
                RoundingMode.DOWN);
        
        return canOrderCount;
    }
    
    /**
     * 注文可能数量算出（売却）
     * @param currencyCode 通貨コード
     * @param withdrawalPossibleAmount　出勤可能額
     * @return　注文可能数量
     */
    private BigDecimal calculationMaxOrderableQuantitySell(String currencyCode, String withdrawalPossibleAmount,
            BigDecimal byingPowerMoneyAfterCalculate) {
        
        BigDecimal canOrderCount = BigDecimal.ZERO;
        BigDecimal withdrawalPossibleAmountBd = StringUtil.parseBigDecimal(withdrawalPossibleAmount);
        
        if (StringUtils.equals(currencyCode, CurrencyCode.USD.getId())) {
            
            canOrderCount = withdrawalPossibleAmountBd.add(byingPowerMoneyAfterCalculate);
        } else {
            canOrderCount = withdrawalPossibleAmountBd;
        }
        
        return canOrderCount;
    }
    
    /**
     * 概算金額算出
     * @param inputVolume 入力値
     * @param denominator デノミ
     * @param approximationRate 概算用レート
     * @param calculationFlag　true:概算受渡金額 false:概算外貨数量
     * @return 算出値
     */
    private String calculationVolume(String inputVolume, String denominator, String approximationRate,
            boolean calculationFlag) {
        
        BigDecimal inputVolumeBd = StringUtil.parseBigDecimal(inputVolume);
        BigDecimal denominatorBd = StringUtil.parseBigDecimal(denominator);
        BigDecimal approximationRateBd = StringUtil.parseBigDecimal(approximationRate);
        BigDecimal result = BigDecimal.ZERO;
        if (calculationFlag) {
            // 数量入力（外貨）×画面.概算用レート÷画面.デノミ（計算結果を小数点以下切り上げ）
            result = inputVolumeBd.multiply(approximationRateBd).divide(denominatorBd, 0, RoundingMode.UP);
        } else {
            // 画面.数量入力（円）×画面.デノミ÷画面.概算用レート（計算結果を小数点以下切り捨て）
            result = inputVolumeBd.multiply(denominatorBd).divide(approximationRateBd, 0, RoundingMode.DOWN);
        }
        return result.toString();
    }
    
    /**
     * 初期化処理
     */
    private void init(String reqSellBuyType, String aType, BuySellDto buySellDto) {
        
        /* 各処理で利用する変数の初期化 */
        if (StringUtils.isEmpty(aType)) {
            buySellDto.setAt(AccountType.GENERAL);
        } else {
            buySellDto.setAt(AccountType.getById(aType));
        }
        
        SellBuyType sbType = SellBuyType.valueOfId(reqSellBuyType);
        if (sbType != null) {
            buySellDto.setIsBuyType(sbType == SellBuyType.BUY);
            buySellDto.setIsSellType(sbType == SellBuyType.SELL);
            
        } else {
            buySellDto.setIsBuyType(false);
            buySellDto.setIsSellType(false);
        }
        buySellDto.setSellBuyType(reqSellBuyType);
    }
    
    /**
     * API001 通貨別サービスステータスチェックAPI
     * @param currencyCode 通貨コード
     * @param sellBuyType 売買区分
     * @return API処理結果
     * @throws Exception
     */
    private CheckExchangeCurrencyServiceStatusRes callAPI001(String currencyCode, BuySellDto buySellDto)
            throws Exception {
        
        CheckExchangeCurrencyServiceStatusReq req = new CheckExchangeCurrencyServiceStatusReq();
        req.getParameter().setServiceGroup(API_REQUEST_EXCHANGE);
        req.getParameter().setServiceType(API_SERVICE_TYPE);
        req.getParameter().setCurrencyCode(currencyCode);
        req.getParameter().setBuySellCode(buySellDto.getSellBuyType());
        
        return exchangeService.checkExchangeCurrencyServiceStatus(req);
    }
    
    /**
     * API002 為替取引通貨マスタ取得API
     * @param currencyCode 通貨コード
     * @return API処理結果
     * @throws Exception
     */
    private GetExchangeTradeCurrencyRes callAPI002(String currencyCode) throws Exception {
        
        GetExchangeTradeCurrencyReq req = new GetExchangeTradeCurrencyReq();
        req.getParameter().setCurrencyCode(currencyCode);
        return exchangeService.getExchangeTradeCurrency(req);
    }
    
    /**
     * API003 為替取引レート情報一覧取得API（リテール向け）
     * @param currencyCode 通貨コード
     * @return API処理結果
     * @throws Exception
     */
    private ListExchangeTradeRatesRes callAPI003(String currencyCode, CustomerCommon cc, BuySellDto buySellDto,
            UserAccount ua)
            throws Exception {
        
        ListExchangeTradeRatesReq req = new ListExchangeTradeRatesReq();
        req.getParameter().setCurrencyCode(currencyCode);
        req.getParameter().setBuySellCode(buySellDto.getSellBuyType());
        req.getHeader().setToken(getToken(cc));
        req.getHeader().setOperator_id(ua.getCcsUserId());
        return exchangeService.listExchangeTradeRates(req);
    }
    
    /**
     * API004 営業日情報取得API（リテール向け）
     * @param exchangeGroup 為替グループ
     * @return API処理結果
     * @throws Exception
     */
    private GetExchangeBusinessDateRes callAPI004(String exchangeGroup) throws Exception {
        
        GetExchangeBusinessDateReq req = new GetExchangeBusinessDateReq();
        req.getParameter().setExchangeGroup(exchangeGroup);
        return exchangeService.getExchangeBusinessDate(req);
    }
    
    /**
     * API005 出金可能金額一括取得API
     * @param currencyCode 通貨コード
     * @param depositWithdrawalDate 出金予定日
     * @return API処理結果
     * @throws Exception
     */
    private MultiGetPossibleWithdrawalsRes callAPI005(String currencyCode, String depositWithdrawalDate,
            CustomerCommon cc, BuySellDto buySellDto)
            throws Exception {
        
        MultiGetPossibleWithdrawalsReq req = new MultiGetPossibleWithdrawalsReq();
        req.getHeader().setToken(RequestUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
        PossibleWithdrawalInput input = new PossibleWithdrawalInput();
        List<PossibleWithdrawalInput> inputList = new ArrayList<PossibleWithdrawalInput>();
        
        if (buySellDto.getIsBuyType()) {
            input.setCurrencyCode(CURRENCY_CODE_JPY);
        } else if (buySellDto.getIsSellType()) {
            input.setCurrencyCode(currencyCode);
        }
        input.setWithdrawalDate(depositWithdrawalDate);
        input.setCashingJournalType(API_REQUEST_EXCHANGE);
        
        if (buySellDto.getAt() != null) {
            input.setAccountKind(buySellDto.getAt().getName());
        }
        inputList.add(input);
        
        req.getParameter().setPossibleWithdrawals(inputList);
        
        return foreignAccountService.multiGetPossibleWithdrawals(req);
    }
    
    /**
     * API006 外貨建口座JrNISA口座開設ステータス取得
     * @return API処理結果
     * @throws Exception
     */
    private GetJrNisaAccountStatusRes callAPI006(CustomerCommon cc) throws Exception {
        
        GetJrNisaAccountStatusReq req = new GetJrNisaAccountStatusReq();
        req.getHeader().setToken(getToken(cc));
        req.getParameter().setBaseDate(DateUtil.format(DateUtil.SEPARATED_HYPHEN_YYYYMMDD));
        return foreignAccountService.getJrNisaAccountStatus(req);
    }
    
    /**
     * API007 為替注文確認API（リテール向け）
     * @param currencyCode 通貨コード
     * @param accountType 口座分類
     * @param orderAmount 注文金額
     * @return API処理結果
     * @throws Exception
     */
    private ConfirmExchangeCreatedOrderRes callAPI007(String currencyCode, String orderAmount, CustomerCommon cc,
            UserAccount ua, BuySellDto buySellDto) throws Exception {
        
        ConfirmExchangeCreatedOrderReq req = new ConfirmExchangeCreatedOrderReq();
        req.getHeader().setToken(getToken(cc));
        req.getHeader().setOperator_id(ua.getCcsUserId());
        
        req.getParameter().setCurrencyCode(currencyCode);
        req.getParameter().setBuySellCode(buySellDto.getSellBuyType());
        req.getParameter().setAccountKind(buySellDto.getAt().getName());
        req.getParameter().setOrderAmount(orderAmount);
        if (buySellDto.getIsSellType()) {
            req.getParameter().setSellMethod(API_SELL_METHOD_SELL_PART);
        }
        return exchangeService.confirmExchangeCreatedOrder(req);
        
    }
    
    /**
     * API008 為替注文一覧取得API
     * @param currencyCode 通貨コード
     * @return API処理結果
     * @throws Exception
     */
    private ListExchangeOrdersRes callAPI008(String currencyCode, CustomerCommon cc, BuySellDto buySellDto)
            throws Exception {
        
        ListExchangeOrdersReq req = new ListExchangeOrdersReq();
        req.getHeader().setToken(getToken(cc));
        req.getParameter().setExchangeOrderStatusInput(API_EXCHANGE_ORDER_STATUS);
        req.getParameter().setCurrencyCode(currencyCode);
        if (buySellDto.getIsBuyType()) {
            req.getParameter().setBuySellCode("2");
        } else {
            req.getParameter().setBuySellCode("1");
        }
        
        return exchangeService.listExchangeOrders(req);
        
    }
    
    /**
     * APIリクエスト項目：トークン設定
     * @return
     */
    private String getToken(CustomerCommon cc) {
        
        // 顧客共通情報.部店コード(3桁)+顧客共通情報.口座番号(7桁)
        return RequestUtil.getToken(cc.getButenCode(), cc.getAccountNumber());
    }
    
    /**
     * FCT001チェック
     * @param butenCode　部店コード
     * @param accountNumber 口座番号
     * @return API結果
     */
    private boolean callFCT001(CustomerCommon cc, IfaFxOrderInputErrorModel error) {
        
        InputFct001Dto input = new InputFct001Dto();
        input.setAccountNumber(cc.getAccountNumber());
        input.setButenCode(cc.getButenCode());
        
        OutputFct001Dto output = fct001.doCheck(input);
        if (output != null) {
            if (StringUtils.equals(output.getTargetCustomerRefAuthFlag(), FCT001_NO_AUTH)) {
                error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_BUTENACCOUNTNOTEXIST,
                        new String[] { cc.getButenCode(), cc.getAccountNumber() }));
                error.setErrorCode(ERRORS_BUTENACCOUNTNOTEXIST);
                return false;
            }
            if (StringUtils.equals(output.getTradeSuspendFlag(), FCT001_NO_TRADE)) {
                error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_CMN_SELECTED_ACCOUNT_OUT_OF_SERVICE));
                error.setErrorCode(ERRORS_CMN_SELECTED_ACCOUNT_OUT_OF_SERVICE);
                return false;
            }
            
        }
        return true;
    }
    
    /**
     * FCT003チェック
     * @param currencyCode 通貨コード
     * @param tradeKbn 売買区分
     * @return
     */
    private boolean callFCT003(String currencyCode, String tradeKbn, CustomerCommon cc,
            IfaFxOrderInputErrorModel error) {
        
        InputFct003Dto input = new InputFct003Dto();
        input.setButenCode(cc.getButenCode());
        input.setAccountNumber(cc.getAccountNumber());
        input.setProductCd(SecurityMoneyClass.FOREIGN.getId()); // 外貨
        if (StringUtils.equals(tradeKbn, SellBuyType.BUY.getId())) {
            input.setTradeCd(ForeignTradeClass.BUY.getId());
        } else if (StringUtils.equals(tradeKbn, SellBuyType.SELL.getId())) {
            input.setTradeCd(ForeignTradeClass.SELL.getId());
        }
        input.setCountryCd(NationalityCode.OT.getId()); // 国籍コード「99」
        input.setCurrencyCode(currencyCode);
        
        OutputFct003Dto result = fct003.doCheck(input);
        
        boolean isError = false;
        if (result == null || CollectionUtils.isEmpty(result.getMediateProprietyList())) {
            isError = true;
        } else if (result.getMediateProprietyList().stream()
                .filter(m -> StringUtils.equals(m.getMediatePropriety(), FCT003_MEDIATE_PROPRIETY_NG)).findAny()
                .isPresent()) {
                isError = true;
        }
        
        if (isError) {
            error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                    new String[] { codeListService.getValue(CODE_MSG_DISPLAY_TARGET_TRADE, FX_TRADE) }));
            error.setErrorCode(ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE);
            return false;
        }
        
        return true;
        
    }
    
    /**
     * A008のレスポンス項目に値を設定
     * @param response A008のレスポンス
     * @param accountNumber 口座番号
     * @param accountTypeChange 口座分類（為替取引）
     * @return 計算後の余力金額
     */
    private void setA008Response(IfaFxOrderInputA008DtoResponse response, IfaFxOrderInputA008DtoRequest dtoReq,
            ListExchangeTradeRatesRes api003Res, ConfirmExchangeCreatedOrderRes api007Res,
            BigDecimal adjustPercentResult) {
        
        //通貨コード
        response.setCurrencyCode(api007Res.getCurrencyCode().getId());
        //通貨名
        response.setCurrency(api007Res.getCurrencyName());
        //売却方法
        response.setSaleMethod(dtoReq.getSaleMethod());
        //売買区分
        response.setTradeKbn(dtoReq.getTradeKbn());
        //為替注文金額
        response.setExchangeOrderAmount(api007Res.getOrderAmount());
        //約定日時　定時為替の場合のみ値が返却される
        if (api007Res.getExchangeTradeType().equals("2")) {
            response.setTradeDateTime(api007Res.getExecutionDatetime());
        }
        //受渡日
        response.setSettlementDate(api007Res.getValueDate());
        //為替取引
        response.setFxTrade(api007Res.getExchangeTradeType());
        //為替レート
        response.setFxRate(api007Res.getExchangeRate());
        //為替レート日時
        response.setApproximateRateExchangeRateDateTime(api007Res.getRateDatetime());
        //受渡金額（円貨）
        response.setYenDeliveryAmount(api007Res.getNetAmount());
        //口座分類（為替取引）
        response.setAccountType(dtoReq.getAccountType());
        //注文ワーニングしきい値（数値(整数)）
        response.setWarningThreshold(dtoReq.getWarningThreshold());
        // 注文ワーニングしきい値超過メッセージ
        // A008内で設定済み
        if (api003Res.getExchangeTradeRates() != null) {
            //デノミ
            response.setDenominator(api003Res.getExchangeTradeRates().get(0).getBasePrice());
            // 上乗せ区分
            response.setFxRateAdditionalType(api003Res.getExchangeTradeRates().get(0).getAdjustType());
            // 上乗せ金額(金額)
            response.setAdditionalPrice(api003Res.getExchangeTradeRates().get(0).getAdjustAmount());
        }
        // 上乗せ金額(パーセント)
        response.setAdditionalPricePercent(adjustPercentResult.toString());
        // 数量の指定方法
        response.setQuantityDesignationMethod(dtoReq.getQuantityDesignationMethod());
    }
    
    /**
     * 計算後の余力金額取得処理
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @param accountTypeChange 口座分類（為替取引）
     * @return 計算後の余力金額
     */
    private OutputFct004Dto callFCT004(CustomerCommon cc, BuySellDto buySellDto) throws Exception {
        
        InputFct004Dto input = new InputFct004Dto();
        input.setButenCode(cc.getButenCode());
        input.setAccountNumber(cc.getAccountNumber());
        input.setTradeType(TRADE_TYPE_CASH_DEPOSIT);
        input.setOtcManageNumber(" "); // 空白
        if (buySellDto.getAt() != null) {
            input.setDepositType(buySellDto.getAt().getName());
        }
        
        return fct004.doCheck(input);
    }
    
    /**
     * 別クラスの同名フィールドをコピーする
     * (BeanUtils.copyPropertiesが機能しないため)
     * @param source コピー元のインスタンス
     * @param target コピー先のインスタンス
     */
    private static void copyFields(Object source, Object target) {
        
        final String prefixSet = "set";
        final String prefixGet = "get";
        
        Set<String> methodSet = new HashSet<String>();
        for (Method method : target.getClass().getMethods())
            if (method.getName().startsWith(prefixSet))
                methodSet.add(method.getName().substring(prefixSet.length()));
            
        try {
            for (Method method : source.getClass().getMethods()) {
                if (!method.getName().startsWith(prefixGet))
                    continue;
                String curName = method.getName().substring(prefixGet.length());
                if (methodSet.contains(curName)) {
                    Method newMethod = target.getClass().getMethod(prefixSet + curName, method.getReturnType());
                    newMethod.invoke(target, method.invoke(source));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
