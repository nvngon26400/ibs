package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.ifa.ExchangeService;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.protocol.account.client_entry.cashing.MultiGetPossibleWithdrawalsReq;
import com.sbisec.helios.ap.athena.protocol.account.client_entry.cashing.MultiGetPossibleWithdrawalsRes;
import com.sbisec.helios.ap.athena.protocol.account.dto.PossibleWithdrawalInput;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetExchangeTradeCurrencyReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetExchangeTradeCurrencyRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.CreateExchangeOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.CreateExchangeOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeBusinessDateReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeBusinessDateRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ListExchangeOrdersReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ListExchangeOrdersRes;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
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
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaFxOrderConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxOrderConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderConfirmA001aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderConfirmA001aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderConfirmA001bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderConfirmA001bDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderConfirmErrorModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaFxOrderConfirmService;
import com.sbisec.helios.ap.common.enums.CurrencyCode;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ForeignSecurityTradeAccountOpenStatus;
import com.sbisec.helios.ap.common.enums.ForeignTradeClass;
import com.sbisec.helios.ap.common.enums.NationalityCode;
import com.sbisec.helios.ap.common.enums.SecurityMoneyClass;
import com.sbisec.helios.ap.common.enums.SellBuyType;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 画面ID：SUB0202_0502-02_2
 * 画面名：為替注文確認
 * @author <author-name>
 *
 * 2023/09/25 新規作成
 */
@Component(value = "cmpIfaFxOrderConfirmService")
public class IfaFxOrderConfirmServiceImpL implements IfaFxOrderConfirmService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaFxOrderConfirmServiceImpL.class);
    
    // --------------------------------
    // メッセージ
    // --------------------------------    
    /** 入力した部店口座は存在しません。<br>部店: [{0}]、口座: [{1}] */
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    /** 取引停止口座のため処理を進めることができません。 */
    private static final String ERRORS_CMN_SELECTED_ACCOUNT_OUT_OF_SERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** 選択した通貨は現在お取引できません。 */
    private static final String ERRORS_EXT_ORDER_EXECUTION_UNAVAILABLE = "errors.ext.orderExecution.unavailable";
    
    /** 外貨建商品取引口座が未開設です。 */
    private static final String ERRORS_CMN_FOREIGN_SECURITIES_ACCOUNT_NOT_OPEN = "errors.cmn.foreignSecuritiesAccount.notOpen";
    
    /** {0}ができないコースです。 */
    private static final String ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 売却可能数量が不足しているため、注文できません。 \\u30FB売却数量={0}[{4}] \\u30FBリアルタイム余力={1}[{4}] \\u30FB当日店頭買付約定金額={2}[{4}] \\u30FB外国債券の当日約定金額（買付）={3}[{4}] */
    private static final String ERRORS_EXT_COUNT_UNIT_OVERFLOW = "errors.ext.countUnit.overflow";
    
    /** 確認が必要なアラートが新たに発生しました。注文入力画面に戻り再度注文確認を行ってください。 */
    private static final String ERRORS_CMN_INFORMATION_OCCURS = "errors.cmn.information.occurs";
    
    /** 注文発注前の注文データが登録できないため、注文しませんでした。 */
    private static final String ERRORS_FRS_PRE_ORDER_EXECUTION_FAILED = "errors.frs.preOrderExecution.failed";
    
    /** 注文発注後の注文データが更新できませんでした。注文は完了しています。 */
    private static final String WARNINGS_FRS_POST_ORDER_EXECUTION_COMPLETED = "warnings.frs.postOrderExecution.completed";
    
    /** 定時為替取引では、同一約定タイミング内の「買付→売却」、「売却→買付」は制限されています。 前の注文を取り消すことで新しい注文を行えるようになります。 */
    private static final String ERRORS_EXT_REVERSE_TRADE_EXIST = "errors.ext.reverseTrade.exist";
    
    /** 注文処理でエラーが発生しました。(エラーコード：{0}、エラーメッセージ{1}) */
    private static final String ERRORS_CMN_ORDER_EXECUTION_FAILED = "errors.cmn.orderExecution.failed";
    
    // --------------------------------
    // 変数定義
    // --------------------------------
    /** FCT001結果：対象顧客参照権限無し */
    private static final String FCT001_NO_AUTH = "0";
    
    /** FCT001結果：取引停止口座 */
    private static final String FCT001_NO_TRADE = "1";
    
    /** FCT003エラーメッセージ：対象取引（メッセージ表示用） 区分値 */
    private static final String FCT003_CODE_VALUE = "1";
    
    /** APIリクエスト固定値:為替 */
    private static final String API_REQUEST_EXCHANGE = "EXCHANGE";
    
    /** API001サービスタイプ：為替取引注文 */
    private static final String API_SERVICE_TYPE = "ORDER";
    
    /** API003売却方法区分:固定値 */
    private static final String API_SELL_METHOD_SELL_PART = "SELL_PART";
    
    /** API003:request_id(設定無し) */
    private static final String API003_REQUEST_ID = "";
    
    /** API006注文状況:固定値 */
    private static final String API_EXCHANGE_ORDER_STATUS = "2";
    
    /** 為替取引 */
    private static final String FX_TRADE_REGULAR = "2";
    
    /** 対象取引（メッセージ表示用）:ドメインID */
    private static final String CODE_MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 取引区分:保証金振替（預り金→保証金） */
    private static final String TRADE_TYPE_CASH_DEPOSIT = "CASH_DEPOSIT";
    
    /** 通貨コード：JPY */
    private static final String CURRENCY_CODE_JPY = "JPY";
    
    /** IFAサブ注文番号 */
    private static final String IFA_ORDER_SUB_NO = "1";
    
    /** 預り区分（為替取引） */
    private static final String DEPOSIT_TYPE_FX_TRADE = "3";
    
    /** 為替取引種別区分 */
    private static final String FX_TRADE_TYPE = "2";
    
    /** 為替取引注文状況:新規 */
    private static final String FX_TRADE_ORDER_STATUS_NEW = "0";
    
    /** FCT003_媒介可否:不可 */
    private static final String FCT003_MEDIATE_PROPRIETY_NG = "0";
    
    private static final String SELL_BUY_TYPE_ONE = "1";
    
    /**
     * APIエラー時のDTOクラス
     * @author 松田
     *
     */
    @Data
    private class ApiStatusDto {
        
        // APIステータスコード
        private String apiStatusCode;
        
        // APIエラーコード
        private String apiErrorCode;
        
        // APIメッセージ
        private String apiMessage;
    }
    
    @Data
    private class BuySellDto {
        
        /** 売買区分＝買付 */
        private Boolean isBuyType;
        
        /** 売買区分＝売却 */
        private Boolean isSellType;
        
        /** 売買区分 */
        private String sellBuyType;
    }
    
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
    private IfaFxOrderConfirmDao dao;
    
    @Autowired
    private CometCommonService cometCommonService;
    
    /**
     * アクションID：A001a
     * アクション名：注文発注
     * Dto リクエスト：IfaFxOrderConfirmA001aDtoRequest
     * Dto レスポンス：IfaFxOrderConfirmA001DtoResponse
     * model リクエスト：IfaFxOrderConfirmSql001RequestModel
     * model レスポンス：IfaFxOrderConfirmSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaFxOrderConfirmA001aDtoResponse> orderPlacementA001a(IfaFxOrderConfirmA001aDtoRequest dtoReq)
            throws Exception {
        
        IfaFxOrderConfirmA001aDtoResponse response = new IfaFxOrderConfirmA001aDtoResponse();
        List<IfaFxOrderConfirmA001aDtoResponse> responseList = new ArrayList<IfaFxOrderConfirmA001aDtoResponse>();
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaFxOrderConfirmServiceImplL.orderA001");
        
        IfaFxOrderConfirmErrorModel error = new IfaFxOrderConfirmErrorModel();
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        BuySellDto buySellDto = new BuySellDto();
        
        // 変数初期化
        init(dtoReq.getTradeKbn(), buySellDto);
        
        // 事前チェック
        if (!validationCheck(dtoReq, cc, error, buySellDto)) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
        }
        // 注文内容を為替取引注文テーブルへ記録
        String ifaOrderNo = null;
        try {
            ifaOrderNo = insertExchangeOrderInfo(dtoReq, cc);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_FRS_PRE_ORDER_EXECUTION_FAILED,
                    IfaCommonUtil.getMessage(ERRORS_FRS_PRE_ORDER_EXECUTION_FAILED));
        }
        
        // レスポンス項目の設定(後続処理があるためIFA注文番号のみ返す)
        response.setIfaOrderNo(ifaOrderNo);
        responseList.add(response);
        
        return IfaCommonUtil.createDataList(responseList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(),
                null);
    }
    
    /**
     * アクションID：A001b
     * アクション名：注文発注
     * Dto リクエスト：IfaFxOrderConfirmA001bDtoRequest
     * Dto レスポンス：IfaFxOrderConfirmA001bDtoResponse
     * model リクエスト：IfaFxOrderConfirmSql002RequestModel
     * model レスポンス：IfaFxOrderConfirmSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaFxOrderConfirmA001bDtoResponse> orderPlacementA001b(IfaFxOrderConfirmA001bDtoRequest dtoReq)
            throws Exception {
        
        DataList<IfaFxOrderConfirmA001bDtoResponse> dtoRes = new DataList<IfaFxOrderConfirmA001bDtoResponse>();
        IfaFxOrderConfirmA001bDtoResponse response = new IfaFxOrderConfirmA001bDtoResponse();
        List<IfaFxOrderConfirmA001bDtoResponse> responseList = new ArrayList<IfaFxOrderConfirmA001bDtoResponse>();
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaFxOrderConfirmServiceImplL.orderA001b");
        
        IfaFxOrderConfirmErrorModel error = new IfaFxOrderConfirmErrorModel();
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        AccountType accountType = AccountType.getById(dtoReq.getAccountType());
        BuySellDto buySellDto = new BuySellDto();
        init(dtoReq.getTradeKbn(), buySellDto);
        // API003呼び出し
        CreateExchangeOrderRes api003Response = new CreateExchangeOrderRes();
        ApiStatusDto apiStatus = null;
        DataList<IfaFxOrderConfirmA001bDtoResponse> dtoResApiErr = null;
        try {
            api003Response = callAPI003(dtoReq.getCurrencyCode(), dtoReq.getQuantity(), accountType, cc, buySellDto);
        } catch (Exception e) {
        	LOGGER.error(e.getMessage());
            if (e instanceof AthenaBusinessException) {
                apiStatus = new ApiStatusDto();
                apiStatus.setApiErrorCode(((AthenaBusinessException) e).getErrorCode());
                apiStatus.setApiMessage(((AthenaBusinessException) e).getMessage());
                apiStatus.setApiStatusCode(((AthenaBusinessException) e).getStatusCode().toString());
                dtoResApiErr = cometCommonService.checkBussinessException(
                        IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, "", null), e);
            }
        }
        ErrorLevel resultEl = ErrorLevel.SUCCESS;
        try {
            // SQL002処理
            updateApiResult(api003Response, dtoReq.getIfaOrderNo(), apiStatus);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            error.setErrorCode(WARNINGS_FRS_POST_ORDER_EXECUTION_COMPLETED);
            error.setErrorMessage(IfaCommonUtil.getMessage(WARNINGS_FRS_POST_ORDER_EXECUTION_COMPLETED));
            resultEl = ErrorLevel.WARNING;
        }
        
        if (dtoResApiErr != null) {
            return dtoResApiErr;
        }
        // レスポンス設定
        setResponse(response, dtoReq, api003Response, buySellDto);
        responseList.add(response);
        
        if (resultEl == ErrorLevel.SUCCESS) {
            dtoRes = IfaCommonUtil.createDataList(responseList, resultEl, resultEl.toString(), null);
        } else {
            dtoRes = IfaCommonUtil.createDataList(responseList, resultEl, error.getErrorCode(),
                    error.getErrorMessage());
        }
        
        return dtoRes;
    }
    
    /**
     * 初期化処理
     */
    private void init(String reqSellBuyType, BuySellDto buySellDto) {
        
        /* 各処理で利用する変数の初期化 */
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
     * 事前チェック
     * @param currencyCode 通貨コード
     * @param tradeKbn 売買区分
     * @return trues:OK　false:NG
     * @throws Exception
     */
    private boolean validationCheck(IfaFxOrderConfirmA001aDtoRequest dtoReq, CustomerCommon cc,
            IfaFxOrderConfirmErrorModel error, BuySellDto buySellDto) throws Exception {
        
        // FCT001：利用者顧客参照権限チェック
        if (!checkFCT001(cc, error)) {
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
        if (!checkFCT003(dtoReq.getCurrencyCode(), dtoReq.getTradeKbn(), cc, error)) {
            return false;
        }
        
        // API001処理(通貨別サービスステータスチェックAPI)チェック
        try {
            if (!checkAPI001(dtoReq.getCurrencyCode(), buySellDto)) {
                error.setErrorCode(ERRORS_EXT_ORDER_EXECUTION_UNAVAILABLE);
                error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_EXT_ORDER_EXECUTION_UNAVAILABLE));
                return false;
            }
        } catch (Exception e) {
            DataList<?> dataList = cometCommonService.checkBussinessException(IfaCommonUtil
                    .createDataList(new ArrayList<>(), ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            error.setErrorCode(dataList.getReturnCode());
            error.setErrorMessage(dataList.getMessage());
            return false;
        }
        
        // 定時為替取引における注文中の同一通貨反対売買有無チェック
        try {
            if (!isExistsSameCurrencySellBuy(dtoReq.getCurrencyCode(), dtoReq.getFxTrade(), dtoReq.getTradeDateTime(),
                    cc, buySellDto)) {
                error.setErrorCode(ERRORS_EXT_REVERSE_TRADE_EXIST);
                error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_EXT_REVERSE_TRADE_EXIST));
                return false;
            }
        } catch (Exception e) {
            DataList<?> dataList = cometCommonService.checkBussinessException(IfaCommonUtil
                    .createDataList(new ArrayList<>(), ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            error.setErrorCode(dataList.getReturnCode());
            error.setErrorMessage(dataList.getMessage());
            return false;
        }
        
        // 注文金額<=ワーニングしきい値　チェック
        BigDecimal quantityBd = StringUtil.parseBigDecimal(dtoReq.getQuantity());
        GetExchangeTradeCurrencyRes api002Response = null;
        try {
            api002Response = callAPI002(dtoReq.getCurrencyCode(), quantityBd);
        } catch (Exception e) {
            DataList<?> dataList = cometCommonService.checkBussinessException(IfaCommonUtil
                    .createDataList(new ArrayList<>(), ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            error.setErrorCode(dataList.getReturnCode());
            error.setErrorMessage(dataList.getMessage());
            return false;
        }
        BigDecimal warningThreshold = StringUtil.parseBigDecimal(api002Response.getWarningThreshold());
        if (!api002Response.getWarningThreshold().equals("999999999")) {            
            if (!(quantityBd.compareTo(warningThreshold) <= 0) && StringUtil.isNullOrEmpty(dtoReq.getWarningMessage())) {
                error.setErrorCode(ERRORS_CMN_INFORMATION_OCCURS);
                error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_OCCURS));
                return false;
            }
        }
        
        // 注文金額<=注文可能数量のチェック
        if (!checkMaxOrderableQuantity(quantityBd, dtoReq.getAccountType(), dtoReq.getCurrencyCode(), api002Response,
                cc, error, buySellDto)) {
            return false;
        }
        
        return true;
    }
    
    /**
     * 定時為替取引における注文中の同一通貨反対売買有無チェック
     * @param currencyCode 通貨コード
     * @param fxTrade 為替取引
     * @param tradeDateTime　約定日時
     * @return true:OK　false:NG
     * @throws Exception 
     */
    private boolean isExistsSameCurrencySellBuy(String currencyCode, String fxTrade, String tradeDateTime,
            CustomerCommon cc, BuySellDto buySellDto)
            throws Exception {
        
        if (!StringUtils.equals(fxTrade, FX_TRADE_REGULAR)) {
            return true;
        }
        
        ListExchangeOrdersRes api006Response = null;
        try {
            api006Response = callAPI006(currencyCode, cc, buySellDto);
        } catch (Exception e) {
            throw e;
        }
        if (api006Response == null || CollectionUtils.isEmpty(api006Response.getOrderDetails())) {
            return true;
        }
        // 「リクエスト.約定日時」＝「API006.為替注文情報.通貨コード.約定日時」のレコードが1件以上存在する場合、エラー
        if (api006Response.getOrderDetails().stream().filter(s -> StringUtils.equals(tradeDateTime,
                DateFormatUtil.dateFormatToSeparatedYmdhm(s.getExecutionDatetime()))).findFirst().isPresent()) {
            return false;
        }
        return true;
    }
    
    /**
     * レスポンス項目設定
     * @param response レスポンス
     * @param dtoReq リクエスト
     * @param api003Response API003処理結果
     */
    private void setResponse(IfaFxOrderConfirmA001bDtoResponse response, IfaFxOrderConfirmA001bDtoRequest dtoReq,
            CreateExchangeOrderRes api003Response, BuySellDto buySellDto) {
        
        // リクエスト項目の設定
        setResponseFromRequest(response, dtoReq, buySellDto);
        // API項目の設定
        setResponseFromApi(response, api003Response);
    }
    
    /**
     * API項目からのレスポンス設定
     * @param response レスポンス
     * @param api003Response API003処理結果
     */
    private void setResponseFromApi(IfaFxOrderConfirmA001bDtoResponse response, CreateExchangeOrderRes api003Response) {
        
        // 注文日時
        response.setOrderDate(api003Response.getOrderInputDatetime());
        // 注文番号
        response.setOrderNumber(api003Response.getOrderNo());
        // 数量
        response.setQuantity(api003Response.getOrderAmount());
        // 数量の通貨
        response.setCurrencyCode(api003Response.getCurrencyCode());
        // 数量の通貨名
        response.setCurrency(api003Response.getCurrencyName());
        // 約定日時
        response.setTradeDateTime(api003Response.getExecutionDatetime());
        // 為替レート
        response.setFxRate(api003Response.getExchangeRate());
        // 為替レート日時
        response.setApproximateRateExchangeRateDateTime(api003Response.getRateDatetime());
        // 受渡金額
        response.setDeliveryAmount(api003Response.getNetAmount());
        // 為替取引
        response.setFxTrade(api003Response.getExchangeTradeType());
        // 受渡日
        response.setSettlementDate(api003Response.getValueDate());
        
    }
    
    /**
     * リクエスト項目からのレスポンス設定処理
     * @param dtoReq
     * @param ifaOrderNo
     * @return
     */
    private void setResponseFromRequest(IfaFxOrderConfirmA001bDtoResponse response,
            IfaFxOrderConfirmA001bDtoRequest dtoReq, BuySellDto buySellDto) {
        
        /* リクエスト項目のコピー */
        // 売買区分
        response.setTradeKbn(dtoReq.getTradeKbn());
        if (buySellDto.getIsBuyType()) {
            // 上乗せ区分
            response.setFxRateAdditionalType(dtoReq.getFxRateAdditionalType());
            // 上乗せ金額
            response.setAdditionalPrice(dtoReq.getAdditionalPrice());
        }
        // 口座分類
        response.setAccountType(dtoReq.getAccountType());
        // 注文ワーニングしきい値超過メッセージ
        response.setOrderWarningexceedingThreshold(dtoReq.getOrderWarningexceedingThreshold());
        // 注文ワーニングしきい値
        response.setWarningThreshold(dtoReq.getWarningThreshold());
        // 小数部有効桁数 
        response.setDecimalLength(dtoReq.getDecimalLength());
        // デノミ 
        response.setDenominator(dtoReq.getDenominator());
        // IFA注文番号
        response.setIfaOrderNo(dtoReq.getIfaOrderNo());
    }
    
    /**
     * 為替情報登録処理
     * @param dtoReq
     * @return IFA注文番号
     * @throws Exception
     */
    private String insertExchangeOrderInfo(IfaFxOrderConfirmA001aDtoRequest dtoReq, CustomerCommon cc)
            throws Exception {
        
        IfaFxOrderConfirmSql001RequestModel insSql001Req = new IfaFxOrderConfirmSql001RequestModel();
        UserAccount ua = IfaCommonUtil.getUserAccount();
        String ifaOrderNo = dao.selectIfaFxOrderConfirmSql003();
        if (StringUtil.isNullOrEmpty(ifaOrderNo)) {
            throw new Exception();
        }
        insSql001Req.setIfaOrderNo(ifaOrderNo);
        insSql001Req.setIfaOrderSubNo(IFA_ORDER_SUB_NO);
        insSql001Req.setButenCode(cc.getButenCode());
        insSql001Req.setAccountNumber(cc.getAccountNumber());
        insSql001Req.setTradeCd(dtoReq.getTradeKbn());
        insSql001Req.setQuantityDesignationMethod(dtoReq.getQuantityDesignationMethod());
        insSql001Req.setSaleMethod(dtoReq.getSaleMethod());
        insSql001Req.setCurrencyCode(dtoReq.getCurrencyCode());
        insSql001Req.setCurrency(dtoReq.getCurrency());
        insSql001Req.setQuantity(dtoReq.getQuantity());
        insSql001Req.setFxTrade(dtoReq.getFxTrade());
        insSql001Req.setAccountType(dtoReq.getAccountType());
        insSql001Req.setDepositType(DEPOSIT_TYPE_FX_TRADE);
        insSql001Req.setFxTradeType(FX_TRADE_TYPE);
        insSql001Req.setFxTradeOrderStatus(FX_TRADE_ORDER_STATUS_NEW);
        insSql001Req.setBrokerCode(cc.getBrokerCode());
        insSql001Req.setBrokerChargeCode(cc.getBrokerChargeCode());
        insSql001Req.setCreateUser(ua.getUserId());
        insSql001Req.setUpdateUser(ua.getUserId());
        
        int insSql001Res = dao.insertIfaFxOrderConfirmSql001(insSql001Req);
        if (insSql001Res != 1) {
            throw new Exception();
        }
        
        return ifaOrderNo;
    }
    
    /**
     * API処理結果更新処理
     * @param api003Response
     * @param ifaOrderNo
     * @param apiStatus
     * @throws Exception
     */
    private void updateApiResult(CreateExchangeOrderRes api003Response, String ifaOrderNo, ApiStatusDto apiStatus)
            throws Exception {
        
        IfaFxOrderConfirmSql002RequestModel insSql002Req = new IfaFxOrderConfirmSql002RequestModel();
        UserAccount ua = IfaCommonUtil.getUserAccount();
        // 正常終了時
        if (api003Response != null && apiStatus == null) {
            insSql002Req.setOrderNumber(api003Response.getOrderNo());
            insSql002Req.setTradeDateTime(
                    DateFormatUtil.dateFormatToSeparatedYmdhms(api003Response.getExecutionDatetime()));
            insSql002Req.setSettlementDate(DateFormatUtil.dateFormatToYmdNoSign(api003Response.getValueDate()));
            insSql002Req.setFxRate(api003Response.getExchangeRate());
            insSql002Req.setRateDatetime(DateFormatUtil.dateFormatToSeparatedYmdhms(api003Response.getRateDatetime()));
            insSql002Req.setYenDeliveryAmount(api003Response.getNetAmount());
            insSql002Req
                    .setOrderDate(DateFormatUtil.dateFormatToSeparatedYmdhms(api003Response.getOrderInputDatetime()));
            insSql002Req.setOrderClass(api003Response.getOrderType());
        } else if (apiStatus != null) {
            // 異常終了時（エラーの持ち方が変わるため）
            insSql002Req.setAPIStatusCode(apiStatus.getApiStatusCode());
            insSql002Req.setApiMsg(apiStatus.getApiMessage());
            insSql002Req.setApiErrorCode(apiStatus.getApiErrorCode());
        }
        insSql002Req.setIfaOrderNo(ifaOrderNo);
        insSql002Req.setIfaOrderSubNo(IFA_ORDER_SUB_NO);
        insSql002Req.setUpdateUser(ua.getUserId());
        int insSql002Res = dao.updateIfaFxOrderConfirmSql002(insSql002Req);
        if (insSql002Res != 1) {
            throw new Exception();
        }
        
    }
    
    /**
     * 売却可能数量チェック処理
     * @param quantity 注文金額
     * @param accountType　口座分類
     * @param currencyCode　通貨コード
     * @param api002Response　API002処理結果
     * @return  true:OK false:NG
     * @throws Exception
     */
    private boolean checkMaxOrderableQuantity(BigDecimal quantity, String accountType, String currencyCode,
            GetExchangeTradeCurrencyRes api002Response, CustomerCommon cc, IfaFxOrderConfirmErrorModel error,
            BuySellDto buySellDto)
            throws Exception {
        
        AccountType at = AccountType.getById(accountType);
        GetExchangeBusinessDateRes api004Response = new GetExchangeBusinessDateRes();
        MultiGetPossibleWithdrawalsRes api005Response = new MultiGetPossibleWithdrawalsRes();
        OutputFct004Dto fct004Result = new OutputFct004Dto();
        if (!buySellDto.getIsSellType() || !StringUtils.equals(currencyCode, CurrencyCode.USD.getId())) {
            return true;
        }
        // API004 営業日情報取得API（リテール向け）
        try {
            api004Response = callAPI004(api002Response.getExchangeGroup());
        } catch (Exception e) {
            DataList<?> dataList = cometCommonService.checkBussinessException(IfaCommonUtil
                    .createDataList(new ArrayList<>(), ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            error.setErrorCode(dataList.getReturnCode());
            error.setErrorMessage(dataList.getMessage());
            return false;
        }
        // API005 出金可能金額一括取得API
        try {
            api005Response = callAPI005(currencyCode, api004Response.getDepositWithdrawalDate(), at, cc, buySellDto);
        } catch (Exception e) {
            DataList<?> dataList = cometCommonService.checkBussinessException(IfaCommonUtil
                    .createDataList(new ArrayList<>(), ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            error.setErrorCode(dataList.getReturnCode());
            error.setErrorMessage(dataList.getMessage());
            return false;
        }
        
        // 注文可能数量
        BigDecimal maxOrderableQuantity = BigDecimal.ZERO;
        BigDecimal withdrawalPossibleAmountBd = StringUtil
                .parseBigDecimal(api005Response.getPossibleWithdrawals().get(0).getWithdrawalPossibleAmount());
        
        // FCT004 計算後の余力金額
        fct004Result = callFCT004(at, cc);
        maxOrderableQuantity = withdrawalPossibleAmountBd.add(fct004Result.getByingPowerMoneyAfterCalculate());
        
        if (quantity.compareTo(maxOrderableQuantity) > 0) {
            error.setErrorCode(ERRORS_EXT_COUNT_UNIT_OVERFLOW);
            error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_EXT_COUNT_UNIT_OVERFLOW,
                    new String[] { quantity.toString(),
                            api005Response.getPossibleWithdrawals().get(0).getWithdrawalPossibleAmount().toString(),
                            fct004Result.getOtcBuyingContractAmountToday().toString(),
                            fct004Result.getContractAmountTodayWithinForeignBond().toString(), currencyCode }));
            return false;
        }
        
        return true;
    }
    
    /**
     * FCT001チェック
     * @param butenCode　部店コード
     * @param accountNumber 口座番号
     * @return API結果
     */
    private boolean checkFCT001(CustomerCommon cc, IfaFxOrderConfirmErrorModel error) {
        
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
    private boolean checkFCT003(String currencyCode, String tradeKbn, CustomerCommon cc,
            IfaFxOrderConfirmErrorModel error) {
        
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
                    new String[] { codeListService.getValue(CODE_MSG_DISPLAY_TARGET_TRADE, FCT003_CODE_VALUE) }));
            error.setErrorCode(ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE);
            return false;
        }
        
        return true;
        
    }
    
    /**
     * 計算後の余力金額取得処理
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @param accountTypeChange 口座分類（為替取引）
     * @return 計算後の余力金額
     */
    private OutputFct004Dto callFCT004(AccountType accountType, CustomerCommon cc)  throws Exception {
        
        InputFct004Dto input = new InputFct004Dto();
        input.setButenCode(cc.getButenCode());
        input.setAccountNumber(cc.getAccountNumber());
        input.setTradeType(TRADE_TYPE_CASH_DEPOSIT);
        input.setOtcManageNumber(" "); // 空白
        if (accountType != null) {
            input.setDepositType(accountType.getName());
        }
        
        return fct004.doCheck(input);
    }
    
    /**
     * API001 通貨別サービスステータスチェックAPI
     * @param currencyCode 通貨コード
     * @param sellBuyType 売買区分
     * @return API処理結果
     * @throws Exception
     */
    private boolean checkAPI001(String currencyCode, BuySellDto buySellDto) throws Exception {
        
        CheckExchangeCurrencyServiceStatusReq req = new CheckExchangeCurrencyServiceStatusReq();
        req.getParameter().setServiceGroup(API_REQUEST_EXCHANGE);
        req.getParameter().setServiceType(API_SERVICE_TYPE);
        req.getParameter().setCurrencyCode(currencyCode);
        req.getParameter().setBuySellCode(buySellDto.getSellBuyType());
        
        CheckExchangeCurrencyServiceStatusRes api001Response = null;
        try {
            api001Response = exchangeService.checkExchangeCurrencyServiceStatus(req);
        } catch (Exception e) {
            throw e;
        }
        
        return api001Response != null && api001Response.isAvailable();
    }
    
    /**
     * API002 為替取引通貨マスタ取得API
     * @param currencyCode 通貨コード
     * @return API処理結果
     * @throws Exception
     */
    private GetExchangeTradeCurrencyRes callAPI002(String currencyCode, BigDecimal quantity) throws Exception {
        
        GetExchangeTradeCurrencyReq req = new GetExchangeTradeCurrencyReq();
        req.getParameter().setCurrencyCode(currencyCode);
        
        return exchangeService.getExchangeTradeCurrency(req);
    }
    
    /**
     * API003 為替取引レート情報一覧取得API（リテール向け）
     * @param currencyCode 通貨コード
     * @param orderAmount　注文金額
     * @param at 口座分類
     * @return API処理結果
     * @throws Exception
     */
    private CreateExchangeOrderRes callAPI003(String currencyCode, String orderAmount, AccountType at,
            CustomerCommon cc, BuySellDto buySellDto)
            throws Exception {
        
        CreateExchangeOrderReq req = new CreateExchangeOrderReq();
        UserAccount ua = IfaCommonUtil.getUserAccount();
        req.getHeader().setToken(getToken(cc));
        req.getHeader().setOperator_id(ua.getCcsUserId());
        req.getHeader().setRequest_id(API003_REQUEST_ID);
        
        req.getParameter().setCurrencyCode(currencyCode);
        req.getParameter().setBuySellCode(buySellDto.getSellBuyType());
        req.getParameter().setAccountKind(at.getName());
        req.getParameter().setOrderAmount(orderAmount);
        if (SELL_BUY_TYPE_ONE.equals(buySellDto.getSellBuyType())) {
            req.getParameter().setSellMethod(API_SELL_METHOD_SELL_PART);
        }
        
        return exchangeService.createExchangeOrder(req);
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
            AccountType accountType, CustomerCommon cc, BuySellDto buySellDto) throws Exception {
        
        MultiGetPossibleWithdrawalsReq req = new MultiGetPossibleWithdrawalsReq();
        UserAccount ua = IfaCommonUtil.getUserAccount();
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
        input.setAccountKind(accountType.getName());
        inputList.add(input);
        
        req.getParameter().setPossibleWithdrawals(inputList);
        
        return foreignAccountService.multiGetPossibleWithdrawals(req);
    }
    
    /**
     * API006 為替注文一覧取得API
     * @param currencyCode 通貨コード
     * @return API処理結果
     * @throws Exception
     */
    private ListExchangeOrdersRes callAPI006(String currencyCode, CustomerCommon cc, BuySellDto buySellDto)
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
    
}
