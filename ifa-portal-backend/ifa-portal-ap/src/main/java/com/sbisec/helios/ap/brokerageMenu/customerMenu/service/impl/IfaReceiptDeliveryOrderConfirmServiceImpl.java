package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.component.Fct027;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct027Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct027Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaReceiptDeliveryOrderConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaReceiptDeliveryOrderConfirmRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaReceiptDeliveryOrderConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderConfirmA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderConfirmA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaReceiptDeliveryOrderConfirmService;
import com.sbisec.helios.ap.common.constants.AppConstants;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.MediateAbleTradeFlag;
import com.sbisec.helios.ap.common.exception.ApiError;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.AccountUtil;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.MarginlReceiptDeliverOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.MarginlReceiptDeliverOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.MarginlReceiptDeliverOrderOutData;

/**
 * 画面ID：SUB0202_0212-08_2
 * 画面名：現引現渡注文確認
 * 2024/04/01 新規作成
 *
 * @author SCSK 笹倉秀行
 */
@Component(value = "cmpIfaReceiptDeliveryOrderConfirmService")
public class IfaReceiptDeliveryOrderConfirmServiceImpl implements IfaReceiptDeliveryOrderConfirmService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaReceiptDeliveryOrderConfirmServiceImpl.class);
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct021 fct021;
    
    @Autowired
    private Fct027 fct027;
    
    @Autowired
    private ApiWrapper apiWrapper;
    
    @Autowired
    private CodeListService codeListService;
    
    @Autowired
    private IfaReceiptDeliveryOrderConfirmDao dao;
    
    /** 権限チェックエラー */
    private static final String ERRORS_BUTEN_ACCOUNT_NOTEXISTS = "errors.butenAccountNotExist";
    
    /** 取引停止口座エラー */
    private static final String ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** 媒介不可エラー */
    private static final String ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 信用口座未開設エラー */
    private static final String ERRORS_DMS_NOT_OPEN = "errors.dms.domesticMarginAccount.notOpen";
    
    /** 注意情報エラー有 */
    private static final String ERRORS_NOTICE_ERROR_CHECK = "errors.cmn.noticeErrorCheck";
    
    /** お知らせエラー有 */
    private static final String ERRORS_INFORMATION_CHECK = "errors.informationCheck";
    
    /** 注意情報アラート有 */
    private static final String WARNINGS_NOTICE_WARNING_CHECK = "warnings.cmn.noticeWarningCheck";
    
    /** お知らせアラート有 */
    private static final String WARNINGS_CMM_INFORMATION_CHECK = "warnings.cmm.informationCheck";
    
    /** 取引注意情報（銘柄）エラー */
    private static final String ERRORS_CMN_INFORMATION_OCCURS = "errors.cmn.information.occurs";
    
    /** メッセージID:DB登録エラー */
    private static final String ERRORS_PRE_ORDER_EXECUTION_FAILED = "errors.frs.preOrderExecution.failed";
    
    /** メッセージID:注文結果DB登録失敗ワーニング */
    private static final String WARNINGS_POST_ORDER_EXECUTION_FAILED = "warnings.frs.postOrderExecution.completed";
    
    /** メッセージID:注文発注API処理結果エラー */
    private static final String ERRORS_ORDER_EXECUTION_FAILED = "errors.cmn.orderExecution.failed";
    
    /** 区分.信用口座(国内) : 1（信用口座） */
    private static final String MARGIN = "1";
    
    /**  区分.対象取引（メッセージ表示用） */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 国籍コード */
    private static final String NATIONALITY_CODE = "99";
    
    /** 通貨コード */
    private static final String CURRENCY_CODE = "999";
    
    /** Fct003設定値 証券金銭種別：国内株式 */
    private static final String DOMESTIC_STOCK = "01";

    /** 区分.取引種別（国内株式） : "7"（現渡） */
    private static final String STOCK_TRADE_CLASS_GENWATASHI = "7";
    
    /** 区分.取引種別（国内株式） : "8"（現引） */
    private static final String STOCK_TRADE_CLASS_GENBIKI = "8";
    
    /** 新規売買区分="0"（買新規） */
    private static final String OPEN_TRADE_KBN_BUY = "0";
    
    /** 新規売買区分="1"（売新規） */
    private static final String OPEN_TRADE_KBN_SELL = "1";
    
    /** 区分値：新規市場.東証　*/
    private static final String CODE_VAL_NEW_MARKET_TSE_INT = "0";
    
    /** 区分値：新規市場.PTS　*/
    private static final String CODE_VAL_NEW_MARKET_PTS_INT = "7";
    
    /** 外部コード：新規市場.TSE　*/
    private static final String CODE_VAL_NEW_MARKET_TSE_EXT = "TKY";
    
    /** 外部コード：新規市場.PTS　*/
    private static final String CODE_VAL_NEW_MARKET_PTS_EXT = "PTS";
    
    /** 弁済期限 日計り(A) */
    private static final String PAYMENT_DDEADLINE_A = "A";

    /**
     * アクションID：A001
     * アクション名：注文発注
     * Dto リクエスト：IfaReceiptDeliveryOrderConfirmA001DtoRequest
     * Dto レスポンス：IfaReceiptDeliveryOrderConfirmA001DtoResponse
     * model リクエスト：IfaReceiptDeliveryOrderConfirmA001RequestModel
     * model レスポンス：IfaReceiptDeliveryOrderConfirmA001ResponseModel
     *
     * @param dtoReq {@code IfaReceiptDeliveryOrderConfirmA001DtoRequest }
     * @return {@code DataList <IfaReceiptDeliveryOrderConfirmA001DtoResponse>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    @Override
    public DataList<IfaReceiptDeliveryOrderConfirmA001DtoResponse> orderPlacementA001a(
            IfaReceiptDeliveryOrderConfirmA001DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaReceiptDeliveryOrderConfirmServiceImpl.orderPlacementA001a");
        }
        // レスポンスデータ
        List<IfaReceiptDeliveryOrderConfirmA001DtoResponse> responseList = new ArrayList<>();
        // 顧客共通情報
        final CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        final String butenCode = cc.getButenCode();
        final String accountNumber = cc.getAccountNumber();
        
        // ①利用者の口座に対する権限チェックを行う。
        InputFct001Dto inpFct001Dto = new InputFct001Dto();
        inpFct001Dto.setButenCode(butenCode);
        inpFct001Dto.setAccountNumber(accountNumber);
        OutputFct001Dto outFct001Dto = fct001.doCheck(inpFct001Dto);
        // 対象顧客参照権限有無＝権限なしの場合：権限なしエラーを返す
        if (Fct001.TARGET_CUSTOMER_REF_AUTH_FLAG_0.equals(outFct001Dto.getTargetCustomerRefAuthFlag())) {
            return IfaCommonUtil.createDataList(responseList, ErrorLevel.FATAL, ERRORS_BUTEN_ACCOUNT_NOTEXISTS,
                    IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOTEXISTS,
                            new String[] { butenCode, accountNumber }));
        }
        // 取引停止フラグ＝取引停止口座の場合：取引停止口座エラーを返す
        if (Fct001.TRADE_SUSPEND_FLAG_1.equals(outFct001Dto.getTradeSuspendFlag())) {
            return IfaCommonUtil.createDataList(responseList, ErrorLevel.FATAL, ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE));
        }
        
        //　②取引コース媒介可否チェックを行う。
        InputFct003Dto inpFct003Dto = new InputFct003Dto();
        inpFct003Dto.setButenCode(butenCode);
        inpFct003Dto.setAccountNumber(accountNumber);
        inpFct003Dto.setProductCd(DOMESTIC_STOCK);
        inpFct003Dto.setTradeCd(dtoReq.getTradeCd());
        //　媒介可否リスト.媒介可否　!＝　1（媒介可）の場合：取引不可エラーを返す
        if (isFct003Error(fct003.doCheck(inpFct003Dto))) {
            return IfaCommonUtil.createDataList(responseList, ErrorLevel.FATAL,
                    ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                            new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, "3") }));
        }
        
        // ③顧客共通情報の「信用口座区分(国内)」をもとに、信用口座開設状況をチェックを行う。
        // 「未開設」：信用口座未開設エラーを返す。
        if (!MARGIN.equals(cc.getDomesticMarginAccountType())) {
            return IfaCommonUtil.createDataList(responseList, ErrorLevel.FATAL, ERRORS_DMS_NOT_OPEN,
                    IfaCommonUtil.getMessage(ERRORS_DMS_NOT_OPEN));
        }
        
        // ④口座の取引制限チェックを行う
        InputFct021Dto fct021Req = new InputFct021Dto();
        fct021Req.setButenCode(butenCode);
        fct021Req.setAccountNumber(accountNumber);
        fct021Req.setProductCd(DOMESTIC_STOCK);
        fct021Req.setTradeCd(dtoReq.getTradeCd());
        fct021Req.setCountryCd(NATIONALITY_CODE);
        fct021Req.setCurrencyCode(CURRENCY_CODE);
        fct021Req.setPaymentLimit(dtoReq.getPaymentDeadline());
        OutputFct021Dto fct021Res = fct021.doCheck(fct021Req);
        // FCT021.注意情報エラー有無=="1"(あり)の場合：注意情報エラーを返す
        if (AppConstants.FLG_ON.equals(fct021Res.getNoteInfoErrFlag())) {
            return IfaCommonUtil.createDataList(responseList, ErrorLevel.FATAL, ERRORS_NOTICE_ERROR_CHECK,
                    IfaCommonUtil.getMessage(ERRORS_NOTICE_ERROR_CHECK,
                            new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, "3") }));
        }
        // FCT021.お知らせエラー有無=="1"(あり)の場合：お知らせエラーを返す
        if (AppConstants.FLG_ON.equals(fct021Res.getNoteLimitErrFlag())) {
            return IfaCommonUtil.createDataList(responseList, ErrorLevel.FATAL, ERRORS_INFORMATION_CHECK,
                    IfaCommonUtil.getMessage(ERRORS_INFORMATION_CHECK));
        }
        // FCT021.注意情報アラート有無=="1"(あり)の場合：注意情報エラーを返す
        if (AppConstants.FLG_ON.equals(fct021Res.getNoteInfoAlertFlag())
                && !AppConstants.FLG_ON.equals(dtoReq.getNoteInfoCheck())) {
            return IfaCommonUtil.createDataList(responseList, ErrorLevel.FATAL, WARNINGS_NOTICE_WARNING_CHECK,
                    IfaCommonUtil.getMessage(WARNINGS_NOTICE_WARNING_CHECK,
                            new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, "3") }));
        }
        // if(FCT021.お知らせアラート有無=="1"(あり)の場合)：お知らせエラーを返す
        if (AppConstants.FLG_ON.equals(fct021Res.getNoteLimitAlertFlag())
                && !AppConstants.FLG_ON.equals(dtoReq.getNoteLimitCheck())) {
            return IfaCommonUtil.createDataList(responseList, ErrorLevel.FATAL, WARNINGS_CMM_INFORMATION_CHECK,
                    IfaCommonUtil.getMessage(WARNINGS_CMM_INFORMATION_CHECK));
        }
        
        // ⑤銘柄の取引注意情報を取得する
        InputFct027Dto fct027Req = new InputFct027Dto();
        fct027Req.setBrandCode(dtoReq.getBrandCode());
        OutputFct027Dto fct027Res = fct027.getData(fct027Req);
        if (AppConstants.FLG_ON.equals(fct027Res.getRegKbn())
                && !AppConstants.FLG_ON.equals(dtoReq.getTradingCautionInformation())) {
            return IfaCommonUtil.createDataList(responseList, ErrorLevel.FATAL, ERRORS_CMN_INFORMATION_OCCURS,
                    IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_OCCURS));
        }
        
        // ⑥発注前に注文内容を国内株式注文テーブルへ記録する。
        IfaReceiptDeliveryOrderConfirmRequestModel sqlRequest = new IfaReceiptDeliveryOrderConfirmRequestModel();
        try {
            final UserAccount userAccount = IfaCommonUtil.getUserAccount();
            sqlRequest.setIfaOrderNo(dao.selectIfaReceiptDeliveryOrderConfirmSql004());
            sqlRequest.setIfaOrderSubNo("1");
            sqlRequest.setButenCode(butenCode);
            sqlRequest.setAccountNumber(accountNumber);
            sqlRequest.setKokyakuId(cc.getCustomerCode());
            sqlRequest.setTokuteiKouzaKbn(cc.getSpecificAccountType());
            sqlRequest.setBrandCode(dtoReq.getBrandCode());
            sqlRequest.setMarket(dtoReq.getBuiltMarket());
            sqlRequest.setOrderStatus("0");
            sqlRequest.setTradeCd(dtoReq.getTradeCd());
            sqlRequest.setQuantity(dtoReq.getQuantity());
            sqlRequest.setDepositType(dtoReq.getAccountType());
            sqlRequest.setPaymentDeadline(dtoReq.getPaymentDeadline());
            sqlRequest.setRepayMethod("2");
            sqlRequest.setOrderKind("1");
            sqlRequest.setOrderStatusListOrderClass("0");
            sqlRequest.setKanyuKbn(dtoReq.getKanyuKbn());
            sqlRequest.setReceiveOrder(dtoReq.getReceiveOrderType());
            sqlRequest.setCheckInsider(dtoReq.getCheckCustomerAttribute());
            sqlRequest.setUserId(userAccount.getCcsUserId());
            sqlRequest.setJoutoekizeiKbn("7".equals(dtoReq.getTradeCd()) ? "2" : " ");
            sqlRequest.setBrokerCode(cc.getBrokerCode());
            sqlRequest.setBrokerChargeCode(cc.getBrokerChargeCode());
            sqlRequest.setCreateUser(userAccount.getUserId());
            sqlRequest.setUpdateUser(userAccount.getUserId());
            
            // リクエスト.弁済期限が日計り(A)の場合
            if (PAYMENT_DDEADLINE_A.equals(dtoReq.getPaymentDeadline())) {
                if ("1".equals(fct027Res.getPremiumShortSaleCcategory())) {
                    sqlRequest.setDailyCreditKbn("2");
                } else {
                    sqlRequest.setDailyCreditKbn("1");
                }
            // リクエスト.弁済期限が日計り(A)以外の場合
            } else {
                sqlRequest.setDailyCreditKbn(" ");
            }
            sqlRequest.setPaymentDeadlineDate(dtoReq.getPaymentDeadlineDate());
            sqlRequest.setDateKbn(dtoReq.getDateKbn());

            dao.insertIfaReceiptDeliveryOrderConfirmSql001(sqlRequest);
            
            IfaReceiptDeliveryOrderConfirmSql002RequestModel sql002Request = new IfaReceiptDeliveryOrderConfirmSql002RequestModel();
            sql002Request.setIfaOrderNo(sqlRequest.getIfaOrderNo());
            sql002Request.setBrandCode(dtoReq.getBrandCode());
            
            // 新規売買区分を取引種別をもとに設定する
            if (STOCK_TRADE_CLASS_GENBIKI.equals(dtoReq.getTradeCd())) {
                sql002Request.setOpenTradeKbn(OPEN_TRADE_KBN_BUY);
            } else if (STOCK_TRADE_CLASS_GENWATASHI.equals(dtoReq.getTradeCd())) {
                sql002Request.setOpenTradeKbn(OPEN_TRADE_KBN_SELL);
            }
            
            // 新規市場を建市場から設定する
            if (CODE_VAL_NEW_MARKET_TSE_INT.equals(dtoReq.getBuiltMarket())) {
                sql002Request.setOpenMarket(CODE_VAL_NEW_MARKET_TSE_EXT);
            } else if (CODE_VAL_NEW_MARKET_PTS_INT.equals(dtoReq.getBuiltMarket())) {
                sql002Request.setOpenMarket(CODE_VAL_NEW_MARKET_PTS_EXT);
            }
            
            sql002Request.setPaymentLimit(dtoReq.getPaymentDeadline());
            sql002Request.setOrgNewTradeDate(dtoReq.getParentStockTradeDate());
            sql002Request.setOpenTradeDate(dtoReq.getConstructionDate());
            sql002Request.setOpenPrice(dtoReq.getOpenPrice());
            sql002Request.setContPosition(dtoReq.getContPosition());
            sql002Request.setUnactualQuantity(dtoReq.getUnactualQuantity());
            sql002Request.setDepositType(dtoReq.getTokuteiContractId());
            sql002Request.setQuantity(dtoReq.getQuantity());
            sql002Request.setCreateUser(userAccount.getUserId());
            sql002Request.setUpdateUser(userAccount.getUserId());
            dao.insertIfaReceiptDeliveryOrderConfirmSql002(sql002Request);
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaReceiptDeliveryOrderConfirmServiceImplL.orderPlacementA001a}", e);
            }
            //DB登録NG：DB登録エラーを返す。
            return IfaCommonUtil.createDataList(responseList, ErrorLevel.FATAL, ERRORS_PRE_ORDER_EXECUTION_FAILED,
                    IfaCommonUtil.getMessage(ERRORS_PRE_ORDER_EXECUTION_FAILED));
        }
        
        // レスポンス
        dtoReq.setIfaOrderNo(sqlRequest.getIfaOrderNo());
        dtoReq.setIfaOrderSubNo(sqlRequest.getIfaOrderSubNo());
        IfaReceiptDeliveryOrderConfirmA001DtoResponse response = new IfaReceiptDeliveryOrderConfirmA001DtoResponse();
        response.setReq(dtoReq);
        responseList.add(response);
        return IfaCommonUtil.createDataList(responseList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtil.EMPTY_STRING);
    }

    /**
     * アクションID：A001
     * アクション名：注文発注
     * Dto リクエスト：IfaReceiptDeliveryOrderConfirmA001DtoRequest
     * Dto レスポンス：IfaReceiptDeliveryOrderConfirmA001DtoResponse
     * model リクエスト：IfaReceiptDeliveryOrderConfirmA001RequestModel
     * model レスポンス：IfaReceiptDeliveryOrderConfirmA001ResponseModel
     *
     * @param dtoReq {@code IfaReceiptDeliveryOrderConfirmA001DtoRequest }
     * @return {@code DataList <IfaReceiptDeliveryOrderConfirmA001DtoResponse>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    @Override
    public DataList<IfaReceiptDeliveryOrderConfirmA001DtoResponse> orderPlacementA001b(
            IfaReceiptDeliveryOrderConfirmA001DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaReceiptDeliveryOrderConfirmServiceImpl.orderPlacementA001b");
        }
        
        List<IfaReceiptDeliveryOrderConfirmA001DtoResponse> responseList = new ArrayList<>();
        IfaReceiptDeliveryOrderConfirmA001DtoResponse response = new IfaReceiptDeliveryOrderConfirmA001DtoResponse();
        responseList.add(response);
        final UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // ⑦注文発注を行う。　        
        MarginlReceiptDeliverOrderOutData api001Output = null;
        try {
            MarginlReceiptDeliverOrderInData data = new MarginlReceiptDeliverOrderInData();
            data.setTransactionId(StringUtils.repeat("0", 32));
            data.setCommandNum(StringUtils.repeat("0", 7));
            data.setCommandDate(StringUtils.repeat("0", 8));
            data.setButenCd(IfaCommonUtil.getCustomerCommon().getButenCode());
            data.setKozaNo(AccountUtil.formatToApi(IfaCommonUtil.getCustomerCommon().getAccountNumber()));
            data.setAccountId(StringUtils.repeat("0", 11));
            data.setNumber(StringUtils.repeat("0", 7));
            data.setOrigin(AppConstants.FLG_OFF);
            data.setBrandCd(dtoReq.getBrandCode());
            data.setSettlementId(
                    codeListService.convertKeyToExtKey("DOMESTIC_STOCK_TRADE_CLASS", "EC-GW2", dtoReq.getTradeCd()));
            data.setQuantity(dtoReq.getQuantity());
            data.setUkewHoho("1");
            data.setMarket(dtoReq.getBuiltMarket());
            data.setPaymentLimit(
                    codeListService.convertKeyToExtKey("PAYMENT_DEADLINE", "EC-GW", dtoReq.getPaymentDeadline()));
            data.setJoZeiKbn("7".equals(dtoReq.getTradeCd()) ? "2" : " ");
            data.setHitokuteiTradeKbn(
                    codeListService.convertKeyToExtKey("DOMESTIC_DEPOSIT_TYPE", "EC-GW", dtoReq.getAccountType()));
            data.setCallcenterKbn(AppConstants.FLG_OFF);
            data.setUserId(userAccount.getCcsUserId());
            data.setVettingKbn(" ");
            data.setCheckPrice(StringUtils.repeat("0", 10));
            data.setOrgNewTradeDate(dtoReq.getParentStockTradeDate());
            data.setOpenTradeDate(dtoReq.getConstructionDate());
            data.setOpenPrice(dtoReq.getOpenPrice());
            data.setCheckId(" ");
            data.setIpAddress(StringUtils.rightPad("ifap", 39, StringUtils.SPACE));
            
            MarginlReceiptDeliverOrderIn api001Input = new MarginlReceiptDeliverOrderIn();
            api001Input.setIndata(data);
            api001Output = apiWrapper.marginlReceiptDeliverOrder(api001Input);
        } catch (Exception e) {
            // システムエラーの場合、DBに受注日を登録してエラーレスポンスを返却する。
            LOGGER.warn("{IfaReceiptDeliveryOrderConfirmServiceImplL.orderPlacementA001b}", e);

            IfaReceiptDeliveryOrderConfirmRequestModel sqlRequest = new IfaReceiptDeliveryOrderConfirmRequestModel();
            sqlRequest.setUpdateUser(userAccount.getUserId());
            sqlRequest.setIfaOrderNo(dtoReq.getIfaOrderNo());
            sqlRequest.setIfaOrderSubNo(dtoReq.getIfaOrderSubNo());

            try {
                dao.updateIfaReceiptDeliveryOrderConfirmSql003b(sqlRequest);

            } finally {
                return IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_ORDER_EXECUTION_FAILED,
                    IfaCommonUtil.getMessage(ERRORS_ORDER_EXECUTION_FAILED)
                );
            }
        }

        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        apiErrorUtil.checkApiResponse(api001Output.getShubetu(), api001Output.getCode(), api001Output.getMessage());
        final Boolean isError = apiErrorUtil.isFatal();
        if (isError) {
            try {
                IfaReceiptDeliveryOrderConfirmRequestModel sqlRequest = new IfaReceiptDeliveryOrderConfirmRequestModel();
                sqlRequest.setShubetu(api001Output.getShubetu());
                sqlRequest.setCode(api001Output.getCode());
                sqlRequest.setErrMessage(api001Output.getMessage());
                sqlRequest.setOrderDate(api001Output.getAcceptDate());
                sqlRequest.setUpdateUser(userAccount.getUserId());
                sqlRequest.setIfaOrderNo(dtoReq.getIfaOrderNo());
                sqlRequest.setIfaOrderSubNo(dtoReq.getIfaOrderSubNo());
                sqlRequest.setError(isError);
                if (dao.updateIfaReceiptDeliveryOrderConfirmSql003(sqlRequest) != 1) {
                    throw new IllegalStateException();
                }
            } catch (Exception dbErrorException) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("{IfaReceiptDeliveryOrderConfirmServiceImplL.orderPlacementA001b}", dbErrorException);
                }
                // DB更新があっても、APIエラーの表示を優先する。
            }
            // APIの処理結果がエラー：エラーを表示する。
            return IfaCommonUtil.createDataList(responseList, ErrorLevel.FATAL, ERRORS_ORDER_EXECUTION_FAILED,
                    IfaCommonUtil.getMessage(ERRORS_ORDER_EXECUTION_FAILED,
                            new String[] { api001Output.getCode(), api001Output.getMessage() }));
        }
        
        // ⑧発注後の注文内容を国内株式注文テーブルへ記録する。
        boolean isDbError = false;
        try {
            IfaReceiptDeliveryOrderConfirmRequestModel sqlRequest = new IfaReceiptDeliveryOrderConfirmRequestModel();
            sqlRequest.setShubetu(api001Output.getShubetu());
            sqlRequest.setCode(api001Output.getCode());
            sqlRequest.setErrMessage(api001Output.getMessage());
            sqlRequest.setUpdateUser(userAccount.getUserId());
            sqlRequest.setSecurityType(api001Output.getOrderType());
            sqlRequest.setEcOrderNo(api001Output.getOrderNum());
            sqlRequest.setOrderDate(api001Output.getAcceptDate());
            sqlRequest.setOrderTime(api001Output.getAcceptTime());
            sqlRequest.setEstimatePrice(api001Output.getEstimatePrice());
            sqlRequest.setAmount(api001Output.getAmount());
            sqlRequest.setCommission(api001Output.getCommission());
            sqlRequest.setConsumptionTax(api001Output.getConsumptionTax());
            sqlRequest.setCapitalGainTax(api001Output.getCapitalGainTax());
            sqlRequest.setNetAmount(api001Output.getNetAmount());
            sqlRequest.setCost(api001Output.getCost());
            sqlRequest.setTradeDate(api001Output.getTradeDate());
            sqlRequest.setSettlementDate(api001Output.getSettlementDate());
            sqlRequest.setAcBalance(api001Output.getAcBalance());
            sqlRequest.setAcBalanceAfter(api001Output.getAcBalanceAfter());
            sqlRequest.setUnclosedQuantity(api001Output.getUnclosedQuantity());
            sqlRequest.setUnclosedQuantityAfter(api001Output.getUnclosedQuantityAfter());
            sqlRequest.setMarginCapability(api001Output.getMarginCapability());
            sqlRequest.setMarginCapabilityAfter(api001Output.getMarginCapabilityAfter());
            sqlRequest.setActualGrntRate(api001Output.getActualGrntRate());
            sqlRequest.setActualGrntRateAfter(api001Output.getActualGrntRateAfter());
            sqlRequest.setIfaOrderNo(dtoReq.getIfaOrderNo());
            sqlRequest.setIfaOrderSubNo(dtoReq.getIfaOrderSubNo());
            sqlRequest.setError(isError);
            if (dao.updateIfaReceiptDeliveryOrderConfirmSql003(sqlRequest) != 1) {
                throw new IllegalStateException();
            }
        } catch (Exception dbErrorException) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaReceiptDeliveryOrderConfirmServiceImplL.orderPlacementA001b}", dbErrorException);
            }
            // DB更新NGの場合は、DB更新エラーのメッセージを表示する。
            isDbError = true;
        }
        
        response.setContractAmount(api001Output.getAmount());
        response.setCharge(api001Output.getCost());
        response.setConsumptionTax(api001Output.getConsumptionTax());
        response.setYieldTax(api001Output.getCapitalGainTax());
        response.setSettlementAmount(api001Output.getNetAmount());
        response.setContractDate(api001Output.getTradeDate());
        response.setDeliveryDate(api001Output.getSettlementDate());
        response.setOrderDate(api001Output.getAcceptDate());
        response.setOrderTime(api001Output.getAcceptTime());
        response.setEcOrderNo(api001Output.getOrderNum());
        response.setReq(dtoReq);
        if (isDbError) {
            return IfaCommonUtil.createDataList(responseList, ErrorLevel.WARNING, WARNINGS_POST_ORDER_EXECUTION_FAILED,
                    IfaCommonUtil.getMessage(WARNINGS_POST_ORDER_EXECUTION_FAILED));
        }

        return apiErrorUtil.createDataList(responseList, null);

    }
    
    /**
     * FCT003の結果から、媒介可否リスト.媒介可否に媒介可が存在するか判定する.
     *
     * @param outFct003Dto FCT003実行結果
     * @return 判定結果
     */
    private boolean isFct003Error(OutputFct003Dto outFct003Dto) {
        
        return outFct003Dto.getMediateProprietyList().stream()
                .map(mediateProprieties -> mediateProprieties.getMediatePropriety())
                .noneMatch(mediatePropriety -> MediateAbleTradeFlag.ARI.getId().equals(mediatePropriety));
    }
}
