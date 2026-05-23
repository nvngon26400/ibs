package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
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
import com.sbisec.helios.ap.bizcommon.util.OrderStatusUtil;
import com.sbisec.helios.ap.bizcommon.util.dto.CorrectOrderStatusDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectInputA010RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectInputA010ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectInputRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectInputResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMarginRepayOrderCorrectInputService;
import com.sbisec.helios.ap.common.constants.AppConstants;
import com.sbisec.helios.ap.common.enums.DomesticStockTrade;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ExecuteMethod;
import com.sbisec.helios.ap.common.enums.MediateAbleTradeFlag;
import com.sbisec.helios.ap.common.enums.OrderClass;
import com.sbisec.helios.ap.common.enums.SecurityMoneyClass;
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

import jp.co.sbisec.pcenter.dto.yanap.EstimateStockModifyOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.EstimateStockModifyOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateStockModifyOrderOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointOrd;

/**
 * 画面ID：SUB0202_0212-06_1
 * 画面名：信用返済注文訂正入力
 * 2024/05/24 新規作成
 *
 * @author SCSK 笹倉秀行
 */
@Component(value = "cmpIfaMarginRepayOrderCorrectInputService")
public class IfaMarginRepayOrderCorrectInputServiceImpL implements IfaMarginRepayOrderCorrectInputService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMarginRepayOrderCorrectInputServiceImpL.class);
    
    /** 権限チェックエラー */
    private static final String ERRORS_BUTEN_ACCOUNT_NOTEXISTS = "errors.butenAccountNotExist";
    
    /** 取引停止口座エラー */
    private static final String ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** 媒介不可エラー */
    private static final String ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 信用口座未開設エラー */
    private static final String ERRORS_DMS_NOT_OPEN = "errors.dms.domesticMarginAccount.notOpen";

    /** CCSIDが未登録のためご利用できません。 */
    private static final String ERRORS_CMN_CCSID_UNREGISTERED = "errors.cmn.ccsid.unregistered";
    
    /** 注文対象無しエラー */
    private static final String ERRORS_CMN_ORDER_NOTFOUND = "errors.cmn.order.notFound";
    
    /** 注文訂正可否チェックエラー */
    private static final String ERRORS_CMN_ORDER_MODIFY_OUTOFSERVICE = "errors.cmn.orderModify.outOfService";
    
    /** メッセージID:注意情報エラー */
    private static final String ERRORS_NOTICE_ERROR_CHECK = "errors.cmn.noticeErrorCheck";
    
    /** メッセージID:重要なお知らせエラー */
    private static final String ERRORS_INFORMATION_CHECK = "errors.informationCheck";
    
    /** メッセージID:注意情報アラート */
    private static final String ERRORS_NOTICE_WARN_CHECK = "warnings.cmn.noticeWarningCheck";
    
    /** メッセージID:重要なお知らせアラート */
    private static final String ERRORS_INFORMATION_WARN_CHECK = "warnings.cmm.informationCheck";
    
    /** メッセージID:取引注意情報（銘柄） */
    private static final String WARNINGS_DMS_INFORMATIONCHECK = "warnings.dms.informationCheck";
    
    /** メッセージID:注文訂正処理でエラーが発生しました。(エラーコード：{0}、エラーメッセージ{1}) */
    private static final String ERRRORS_CMN_ORDEREXECUTIONMODIFY_EXECUTION_FAILED = "errors.cmn.orderExecutionModify.failed";
    
    /** メッセージID:注文内容に変更がないため、注文訂正を行いません。 */
    private static final String ERRORS_CMN_ORDERMODIFY_NOMODIFY = "errors.cmn.orderModify.noModify";
    
    /** 市場訂正以外の注文内容に変更がないため、注文訂正を行いません。 */
    private static final String ERRORS_CMN_ORDERMODIFY_MARKETONLYMODIFY = "errors.cmn.orderModify.marketOnlyModify";
    
    /** SOR注文として受け付けられません。市場訂正をやり直してください。 */
    private static final String ERRORS_CMN_SORMODIFY_NOMODIFY = "errors.cmn.sorModify.noModify";
    
    /** 区分値:対象取引（メッセージ表示用）: 区分=国内株式取引(2)　*/
    private static final String CODE_VAL_MSG_DISPLAY_TARGET_TRADE = "2";
    
    /** 区分値:対象取引（メッセージ表示用）: 表示パターン:1　*/
    private static final String DISPLAY_VAL_MSG_DISPLAY_TARGET_TRADE = "1";
    
    /** 区分値：証券金銭種別.国内株式 */
    private static final String DOMESTIC_STOCK = "01";
    
    /** 区分値:国籍コード.99 */
    private static final String CODE_VAL_NATIONALITY_CODE_99 = "99";
    
    /** 区分値:通貨コード.999 */
    private static final String CODE_VAL_CURRENCY_CODE_999 = "999";
    
    /** 区分.対象取引（メッセージ表示用） */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 区分.取引種別（国内株式）: 信用返済買(5) */
    private static final String DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_BUY = "5";
    
    /** 区分.取引種別（国内株式）: 信用返済売(6) */
    private static final String DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_SELL = "6";
    
    /** 区分.注文種別: 通常/逆指値(1) */
    private static final String ORDER_CLASS_NORMAL = "1";
    
    /** 区分.注文種別: OCO(2) */
    private static final String ORDER_CLASS_OCO = "2";
    
    /** 区分.信用口座(国内) : "1"（信用口座） */
    private static final String ACCOUNT_STATUS_CLOSED = "1";
    
    /** API:リクエスト（オリジン）:"0"*/
    private static final String API_REQUEST_ORDER_ID_ORIGIN = "0";
    
    /** API:リクエスト（商品区分）:"S"*/
    private static final String API_REQUEST_ORDER_TYPE = "S";
    
    /** API:リクエスト（受付経路区分）:"0"：支店*/
    private static final String API_REQUEST_CALLCENTER_KBN = "0";
    
    /** API:RBE注文種別 逆指値注文*/
    private static final String RBE_ORDER_KIND_SLO = "SLO";
    
    /** API:RBE注文種別 OCO注文*/
    private static final String RBE_ORDER_KIND_OCO = "OCO";
    
    /** API:RBE注文種別 通常注文*/
    private static final String RBE_ORDER_KIND_NORMAL = StringUtils.repeat(StringUtils.SPACE, 3);
    
    /** API:トリガ発動ゾーン　以上*/
    private static final String TRIGGER_ZONE_OVER = "0";
    
    /** API:トリガ発動ゾーン　以下*/
    private static final String TRIGGER_ZONE_UNDER = "1";
    
    /** API:リクエスト（媒介）:"1"媒介注文*/
    private static final String API_INTERMEDIARY = "1";
    
    /** API:リクエスト（IPアドレス）:"ifap"*/
    private static final String API_REQUEST_IP_ADDRESS = "ifap";

    /** API:リクエスト（訂正SOR注文区分）：訂正SOR"1" */
    private static final String API001_SOR_MODIFY_CODE_CORRECT_SOR = "1";

    /** API:リクエスト（訂正SOR注文区分）：通常訂正" " */
    private static final String API001_SOR_MODIFY_CODE_NORMAL = " ";
    
    /** API:指成区分.指値 */
    private static final String CODE_VAL_SASINARI_ID_LIMIT = StringUtils.SPACE;
    
    /** API:指成区分.寄付指値 */
    private static final String CODE_VAL_SASINARI_ID_LIMIT_OPEN = "Z";
    
    /** API:指成区分.引け指値 */
    private static final String CODE_VAL_SASINARI_ID_LIMIT_CLOSE = "I";
    
    /** API:指成区分.成行 */
    private static final String CODE_VAL_SASINARI_ID_MARKET = "N";
    
    /** API:指成区分.寄付成行 */
    private static final String CODE_VAL_SASINARI_ID_MARKET_OPEN = "Y";
    
    /** API:指成区分.引け成行 */
    private static final String CODE_VAL_SASINARI_ID_MARKET_CLOSE = "H";
    
    /** API:指成区分.不成 */
    private static final String CODE_VAL_SASINARI_ID_LIMIT_TO_MARKET = "F";
    
    /** API:指成区分.IOC指 */
    private static final String CODE_VAL_SASINARI_ID_LIMIT_IOC = "P";
    
    /** API:指成区分.IOC成 */
    private static final String CODE_VAL_SASINARI_ID_MARKET_IOC = "O";
    
    /** API:RBE注文ステータス 発火済 */
    private static final String RBE_ORDER_STATUS_IGNITION = "1";
    
    /** 選択市場：当社優先市場／SOR */
    private static final String SELECT_MARKET_SOR = "A";

    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct021 fct021;
    
    @Autowired
    private Fct027 fct027;
    
    @Autowired
    private CodeListService codeListService;
    
    @Autowired
    private ApiWrapper apiWrapper;
    
    @Autowired
    private OrderStatusUtil orderStatusUtil;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaMarginRepayOrderCorrectInputRequestDto
     * Dto レスポンス：IfaMarginRepayOrderCorrectInputResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginRepayOrderCorrectInputResponseDto> initializeA001(
            IfaMarginRepayOrderCorrectInputRequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginRepayOrderCorrectInputServiceImpL.initializeA001");
        }
        
        // 顧客共通情報の取得
        final CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // ①利用者の口座に対する権限チェックを行う。
        InputFct001Dto inpFct001Dto = new InputFct001Dto();
        inpFct001Dto.setButenCode(cc.getButenCode());
        inpFct001Dto.setAccountNumber(cc.getAccountNumber());
        OutputFct001Dto outFct001Dto = fct001.doCheck(inpFct001Dto);
        // 対象顧客参照権限有無＝権限なしの場合：権限なしエラーを返す
        if (Fct001.TARGET_CUSTOMER_REF_AUTH_FLAG_0.equals(outFct001Dto.getTargetCustomerRefAuthFlag())) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_BUTEN_ACCOUNT_NOTEXISTS,
                    IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOTEXISTS,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
        }
        // 取引停止フラグ＝取引停止口座の場合：取引停止口座エラーを返す
        if (Fct001.TRADE_SUSPEND_FLAG_1.equals(outFct001Dto.getTradeSuspendFlag())) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE));
        }
        
        // ②顧客共通情報の「信用口座区分(国内)」をもとに、信用口座開設状況をチェックを行う。
        //    「未開設」：信用口座未開設エラーを返す。
        if (!ACCOUNT_STATUS_CLOSED.equals(cc.getDomesticMarginAccountType())) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_DMS_NOT_OPEN,
                    IfaCommonUtil.getMessage(ERRORS_DMS_NOT_OPEN));
        }
        
        // ③注文情報を取得する。
        CorrectOrderStatusDto response = orderStatusUtil.isCorrectOrderWithApiResponse(dtoReq.getEcOrderNo());
        if (response == null) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_CMN_ORDER_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_CMN_ORDER_NOTFOUND));
        }
        if (!response.isCorrect()) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_CMN_ORDER_MODIFY_OUTOFSERVICE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_ORDER_MODIFY_OUTOFSERVICE));
        }
        final QueryStockUniteOrderPointOrd orderData = response.getOutput().getReqOrderDataExe().get(0);
        
        //　④取引コース媒介可否チェックを行う。
        InputFct003Dto inpFct003Dto = new InputFct003Dto();
        inpFct003Dto.setButenCode(cc.getButenCode());
        inpFct003Dto.setAccountNumber(cc.getAccountNumber());
        inpFct003Dto.setProductCd(DOMESTIC_STOCK);
        if ("K".equals(orderData.getBuySell())) {
            inpFct003Dto.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_BUY);
        } else {
            inpFct003Dto.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_SELL);
        }
        if (isFct003Error(fct003.doCheck(inpFct003Dto))) {
            return IfaCommonUtil
                    .createDataList(List.of(), ErrorLevel.FATAL, ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                            IfaCommonUtil.getMessage(ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                                    new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE,
                                            CODE_VAL_MSG_DISPLAY_TARGET_TRADE,
                                            DISPLAY_VAL_MSG_DISPLAY_TARGET_TRADE) }));
        }
        
        // ⑤SOR取扱区分を取得する。
        InputFct027Dto inputFct027Dto = new InputFct027Dto();
        inputFct027Dto.setBrandCode(orderData.getStockSecCode());
        OutputFct027Dto outputFct027Dto = fct027.getData(inputFct027Dto);
        
        List<IfaMarginRepayOrderCorrectInputResponseDto> resDtoList = new ArrayList<>();
        resDtoList.add(getResponseData(orderData, outputFct027Dto.getSorServiceKbn()));
        return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtils.EMPTY);
    }
    
    /**
     * アクションID：A004
     * アクション名：更新
     * Dto リクエスト：IfaMarginRepayOrderCorrectInputRequestDto
     * Dto レスポンス：IfaMarginRepayOrderCorrectInputResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginRepayOrderCorrectInputResponseDto> updateA004(
            IfaMarginRepayOrderCorrectInputRequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginRepayOrderCorrectInputServiceImpL.updateA004");
        }
        
        // ③注文情報を取得する。
        CorrectOrderStatusDto response = orderStatusUtil.isCorrectOrderWithApiResponse(dtoReq.getEcOrderNo());
        if (response == null) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_CMN_ORDER_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_CMN_ORDER_NOTFOUND));
        }
        if (!response.isCorrect()) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_CMN_ORDER_MODIFY_OUTOFSERVICE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_ORDER_MODIFY_OUTOFSERVICE));
        }
        
        final QueryStockUniteOrderPointOrd orderData = response.getOutput().getReqOrderDataExe().get(0);
        
        // ④SOR取扱区分を取得する。
        InputFct027Dto inputFct027Dto = new InputFct027Dto();
        inputFct027Dto.setBrandCode(orderData.getStockSecCode());
        OutputFct027Dto outputFct027Dto = fct027.getData(inputFct027Dto);
        
        List<IfaMarginRepayOrderCorrectInputResponseDto> resDtoList = new ArrayList<>();
        resDtoList.add(getResponseData(orderData, outputFct027Dto.getSorServiceKbn()));
        return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtils.EMPTY);
    }
    
    /**
     * アクションID：A010
     * アクション名：訂正確認
     * Dto リクエスト：IfaMarginRepayOrderCorrectInputA010RequestDto
     * Dto レスポンス：IfaMarginRepayOrderCorrectInputA010ResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginRepayOrderCorrectInputA010ResponseDto> correctionConfirmA010(
            IfaMarginRepayOrderCorrectInputA010RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginRepayOrderCorrectInputServiceImpL.correctionConfirmA010");
        }
        
        // 顧客共通情報の取得
        final CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // ①利用者の口座に対する権限チェックを行う。
        InputFct001Dto inpFct001Dto = new InputFct001Dto();
        inpFct001Dto.setButenCode(cc.getButenCode());
        inpFct001Dto.setAccountNumber(cc.getAccountNumber());
        OutputFct001Dto outFct001Dto = fct001.doCheck(inpFct001Dto);
        // 対象顧客参照権限有無＝権限なしの場合：権限なしエラーを返す
        if (Fct001.TARGET_CUSTOMER_REF_AUTH_FLAG_0.equals(outFct001Dto.getTargetCustomerRefAuthFlag())) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_BUTEN_ACCOUNT_NOTEXISTS,
                    IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOTEXISTS,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
        }
        // 取引停止フラグ＝取引停止口座の場合：取引停止口座エラーを返す
        if (Fct001.TRADE_SUSPEND_FLAG_1.equals(outFct001Dto.getTradeSuspendFlag())) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE));
        }

        // ユーザ共通情報 .CCSログイン用IDが未設定(Null または空文字）の場合：取引不可エラーを返す
        UserAccount ua = IfaCommonUtil.getUserAccount();
        if (StringUtils.isEmpty(ua.getCcsUserId())) {
            DataList<IfaMarginRepayOrderCorrectInputA010ResponseDto> response = IfaCommonUtil.createDataList(
                    List.of(),
                    ErrorLevel.FATAL,
                    ERRORS_CMN_CCSID_UNREGISTERED,
                    IfaCommonUtil.getMessage(ERRORS_CMN_CCSID_UNREGISTERED)
            );

            return response;
        }
        
        //　②取引コース媒介可否チェックを行う。
        InputFct003Dto inpFct003Dto = new InputFct003Dto();
        inpFct003Dto.setButenCode(cc.getButenCode());
        inpFct003Dto.setAccountNumber(cc.getAccountNumber());
        inpFct003Dto.setProductCd(DOMESTIC_STOCK);
        inpFct003Dto.setTradeCd(dtoReq.getTradeCd());
        if (isFct003Error(fct003.doCheck(inpFct003Dto))) {
            return IfaCommonUtil
                    .createDataList(List.of(), ErrorLevel.FATAL, ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                            IfaCommonUtil.getMessage(ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                                    new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE,
                                            CODE_VAL_MSG_DISPLAY_TARGET_TRADE,
                                            DISPLAY_VAL_MSG_DISPLAY_TARGET_TRADE) }));
        }
        
        // ③口座の取引制限チェックを行う
        InputFct021Dto inputFct021Dto = new InputFct021Dto();
        inputFct021Dto.setButenCode(cc.getButenCode());
        inputFct021Dto.setAccountNumber(cc.getAccountNumber());
        inputFct021Dto.setProductCd(SecurityMoneyClass.DOMESTIC_STOCKS.getId());
        inputFct021Dto.setTradeCd(dtoReq.getTradeCd());
        inputFct021Dto.setCountryCd(CODE_VAL_NATIONALITY_CODE_99);
        inputFct021Dto.setCurrencyCode(CODE_VAL_CURRENCY_CODE_999);
        inputFct021Dto.setTradeRestrictChkMarket(dtoReq.getOrderMarket());
        inputFct021Dto.setPaymentLimit(dtoReq.getPaymentDeadline());
        OutputFct021Dto outputFct021Dto = fct021.doCheck(inputFct021Dto);
        if (Fct021.EXIST.equals(outputFct021Dto.getNoteInfoErrFlag())) {
            // 注意情報エラーありの場合、エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_NOTICE_ERROR_CHECK,
                    IfaCommonUtil.getMessage(ERRORS_NOTICE_ERROR_CHECK,
                            new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE,
                                    CODE_VAL_MSG_DISPLAY_TARGET_TRADE, DISPLAY_VAL_MSG_DISPLAY_TARGET_TRADE) }));
        } else if (Fct021.EXIST.equals(outputFct021Dto.getNoteLimitErrFlag())) {
            // お知らせエラーありの場合、エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_INFORMATION_CHECK,
                    IfaCommonUtil.getMessage(ERRORS_INFORMATION_CHECK));
        }
        
        IfaMarginRepayOrderCorrectInputA010ResponseDto resDto = new IfaMarginRepayOrderCorrectInputA010ResponseDto();
        resDto.setRequest(dtoReq);
        if (Fct021.EXIST.equals(outputFct021Dto.getNoteInfoAlertFlag())) {
            // 注意情報アラートありの場合
            resDto.setNoticeInfoAlert(IfaCommonUtil.getMessage(ERRORS_NOTICE_WARN_CHECK,
                    new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, CODE_VAL_MSG_DISPLAY_TARGET_TRADE,
                            DISPLAY_VAL_MSG_DISPLAY_TARGET_TRADE) }));
        }
        if (Fct021.EXIST.equals(outputFct021Dto.getNoteLimitAlertFlag())) {
            // お知らせアラートありの場合
            resDto.setNoticeAlert(IfaCommonUtil.getMessage(ERRORS_INFORMATION_WARN_CHECK));
        }
        
        // ④銘柄の取引注意情報を取得する。
        InputFct027Dto inputFct027Dto = new InputFct027Dto();
        inputFct027Dto.setBrandCode(dtoReq.getBrandCode());
        OutputFct027Dto outputFct027Dto = fct027.getData(inputFct027Dto);
        // FCT027.規制銘柄区分=1:規制銘柄　の場合、取引注意情報（銘柄）メッセージを格納する。
        if (AppConstants.FLG_ON.equals(outputFct027Dto.getRegKbn())) {
            resDto.setTradeNoticeInfoBrandMsg(IfaCommonUtil.getMessage(WARNINGS_DMS_INFORMATIONCHECK));
        }
        
        // ⑤訂正有無をチェックする。
        // 注文種別=通常/逆指値
        if (ORDER_CLASS_NORMAL.equals(dtoReq.getOrderKind())) {
            // 通常/逆指値.執行方法 = 訂正前価格.通常/逆指値.執行方法
            boolean isSasinariHouhouEqual = StringUtils.equals(dtoReq.getSasinariHouhou(), dtoReq.getCorrectBeforePriceSasinariHouhou());
            // 通常/逆指値.発火条件価格（逆指値）= 訂正前価格.通常/逆指値.発火条件価格（逆指値）※訂正前の執行方法が逆指値の場合 
            boolean isTriggerPriceEqual = StringUtils.equals("3", dtoReq.getCorrectBeforePriceSasinariHouhou())
                    && StringUtils.equals(dtoReq.getTriggerPrice(), dtoReq.getCorrectBeforePriceTriggerPrice());
            // 通常/逆指値.執行方法（逆指値）= 訂正前価格.通常/逆指値.執行方法（逆指値）※訂正前の執行方法が逆指値の場合 
            boolean isGyakusasiHouhouEqual = StringUtils.equals("3", dtoReq.getCorrectBeforePriceSasinariHouhou()) 
                    && StringUtils.equals(dtoReq.getGyakusasiHouhou(), dtoReq.getCorrectBeforePriceGyakusasiHouhou());
            // 通常/逆指値.注文単価 = 訂正前価格.通常/逆指値.注文単価 ※訂正前の執行方法が指値　または　執行方法（逆指値）が指値の場合
            boolean isPriceEqual = (StringUtils.equals("1", dtoReq.getCorrectBeforePriceSasinariHouhou()) || StringUtils.equals("1", dtoReq.getCorrectBeforePriceGyakusasiHouhou()))
                    && StringUtils.equals(dtoReq.getPrice(), dtoReq.getCorrectBeforePricePrice());

            if (isSasinariHouhouEqual 
                    && (!StringUtils.equals("3", dtoReq.getCorrectBeforePriceSasinariHouhou()) || isTriggerPriceEqual)
                    && (!StringUtils.equals("3", dtoReq.getCorrectBeforePriceSasinariHouhou()) || isGyakusasiHouhouEqual)
                    &&  ((!StringUtils.equals("1", dtoReq.getCorrectBeforePriceSasinariHouhou()) 
                    && !StringUtils.equals("1", dtoReq.getCorrectBeforePriceGyakusasiHouhou()) 
                    || isPriceEqual))) {
            	if(StringUtil.isNullOrEmpty(dtoReq.getAfterCorrecMarket()) || dtoReq.getAfterCorrecMarket().equals(dtoReq.getOrderMarket())) {
            		return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_CMN_ORDERMODIFY_NOMODIFY,
                        IfaCommonUtil.getMessage(ERRORS_CMN_ORDERMODIFY_NOMODIFY));
            	} else {
                	return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_CMN_ORDERMODIFY_MARKETONLYMODIFY,
                            IfaCommonUtil.getMessage(ERRORS_CMN_ORDERMODIFY_MARKETONLYMODIFY));
            	}
            }
        // 注文種別=OCO
        } else {
            // OCO1.注文単価 = 訂正前価格.OCO1.注文単価
            boolean isOco1PriceEqual = StringUtils.equals(dtoReq.getOco1Price(), dtoReq.getCorrectBeforePriceOco1Price());
            // OCO2.発火条件価格（逆指値）= 訂正前価格.OCO2.発火条件価格（逆指値）
            boolean isOco2TriggerPriceEqual = StringUtils.equals(dtoReq.getOco2TriggerPrice(), dtoReq.getCorrectBeforePriceOco2TriggerPrice());
            // OCO2.執行方法（逆指値）= 訂正前価格.OCO2.執行方法（逆指値）
            boolean isOco2GyakusasiHouhouEqual = StringUtils.equals(dtoReq.getOco2GyakusasiHouhou(), dtoReq.getCorrectBeforePriceOco2GyakusasiHouhou());
            // OCO2.注文単価 = 訂正前価格.OCO2.注文単価 ※訂正前のOCO2.執行方法（逆指値）が指値の場合
            boolean isOco2PriceEqual = StringUtils.equals("1", dtoReq.getCorrectBeforePriceOco2GyakusasiHouhou()) 
                    && StringUtils.equals(dtoReq.getOco2Price(), dtoReq.getCorrectBeforePriceOco2Price());

            if (isOco1PriceEqual 
                    && isOco2TriggerPriceEqual
                    && isOco2GyakusasiHouhouEqual
                    &&  (!StringUtils.equals("1", dtoReq.getCorrectBeforePriceOco2GyakusasiHouhou()) || isOco2PriceEqual)) {
                return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_CMN_ORDERMODIFY_NOMODIFY,
                        IfaCommonUtil.getMessage(ERRORS_CMN_ORDERMODIFY_NOMODIFY));
            }
        }
        
        // ⑥注文確認を行う。
        EstimateStockModifyOrderInData inData = new EstimateStockModifyOrderInData();
        inData.setButenCd(cc.getButenCode());
        inData.setKozaNo(AccountUtil.formatToApi(cc.getAccountNumber()));
        inData.setAccountId(StringUtils.repeat("0", 11));
        inData.setNumber(StringUtils.repeat("0", 7));
        inData.setOrigin(API_REQUEST_ORDER_ID_ORIGIN);
        inData.setOrderType(API_REQUEST_ORDER_TYPE);
        inData.setModifyType("PRICE" + String.format("%3s", StringUtil.EMPTY_STRING));
        inData.setOrderNo(StringUtils.leftPad(dtoReq.getEcOrderNo(), 6, "0"));
        inData.setQuantity(StringUtils.leftPad(dtoReq.getQuantity(), 8, "0"));
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())) {
            inData.setSasinariKbn(dtoReq.getSasinariJyouken());
        } else {
            inData.setSasinariKbn(dtoReq.getOco1SasinariJyouken());
        }
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())) {
            inData.setPrice(StringUtils.leftPad(dtoReq.getPrice(), 10, "0"));
        } else {
            inData.setPrice(StringUtils.leftPad(dtoReq.getOco1Price(), 10, "0"));
        }
        inData.setCallcenterKbn(API_REQUEST_CALLCENTER_KBN);
        inData.setUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
        inData.setComId(dtoReq.getTesuuryouKbn());
        inData.setCheckId(StringUtils.SPACE);
        if (StringUtils.equals(Boolean.FALSE.toString(), dtoReq.getWorkingStatus())) {
            if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())) {
                if (StringUtils.equals(dtoReq.getSasinariHouhou(), ExecuteMethod.LIMIT.getId())
                        || StringUtils.equals(dtoReq.getSasinariHouhou(), ExecuteMethod.MARKET.getId())) {
                    inData.setRbeOrderKind(RBE_ORDER_KIND_NORMAL);
                } else {
                    inData.setRbeOrderKind(RBE_ORDER_KIND_SLO);
                }
            } else {
                inData.setRbeOrderKind(RBE_ORDER_KIND_OCO);
            }

        } else {
            inData.setRbeOrderKind(RBE_ORDER_KIND_SLO);
        }
        
        if (StringUtils.equals(dtoReq.getWorkingStatus(), Boolean.TRUE.toString())) {
            inData.setTriggerZone(StringUtils.SPACE);
        } else {
            if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())
                    && StringUtils.equals(dtoReq.getSasinariHouhou(), ExecuteMethod.STOP.getId())) {
                if (StringUtils.equals(dtoReq.getTradeCd(), DomesticStockTrade.MARGIN_BACK_BUY.getId())) {
                    inData.setTriggerZone(TRIGGER_ZONE_OVER);
                } else {
                    inData.setTriggerZone(TRIGGER_ZONE_UNDER);
                }
            } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
                if (StringUtils.equals(dtoReq.getTradeCd(), DomesticStockTrade.MARGIN_BACK_BUY.getId())) {
                    inData.setTriggerZone(TRIGGER_ZONE_OVER);
                } else {
                    inData.setTriggerZone(TRIGGER_ZONE_UNDER);
                }
            } else {
                inData.setTriggerZone(StringUtils.SPACE);
            }
        }
        if (StringUtils.equals(dtoReq.getWorkingStatus(), Boolean.TRUE.toString())) {
            inData.setTriggerPrice(StringUtils.repeat("0", 10));
        } else {
            if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())) {
                if (StringUtils.equals(dtoReq.getSasinariHouhou(), ExecuteMethod.STOP.getId())) {
                    inData.setTriggerPrice(StringUtils.leftPad(dtoReq.getTriggerPrice(), 10, "0"));
                } else {
                    inData.setTriggerPrice(StringUtils.repeat("0", 10));
                }
            } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
                inData.setTriggerPrice(StringUtils.leftPad(dtoReq.getOco2TriggerPrice(), 10, "0"));
            } else {
                inData.setTriggerPrice(StringUtils.repeat("0", 10));
            }
        }
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())) {
            inData.setOcoSasinariKbn(StringUtils.SPACE);
        } else {
            inData.setOcoSasinariKbn(dtoReq.getOco2GyakusasiJyouken());
        }
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())) {
            inData.setOcoPrice(StringUtils.repeat(StringUtils.SPACE, 10));
        } else {
            inData.setOcoPrice(dtoReq.getOco2Price());
        }
        inData.setIntermediary(API_INTERMEDIARY);
        inData.setIpAddress(StringUtils.rightPad(API_REQUEST_IP_ADDRESS, 39, " "));
        // 訂正SOR注文区分
        // 市場が「当社優先市場／SOR」に変更された場合
        if (SELECT_MARKET_SOR.equals(dtoReq.getAfterCorrecMarket())) {
        	// "1"：訂正SOR
        	inData.setSorModifyCode(API001_SOR_MODIFY_CODE_CORRECT_SOR);
        // 上記以外
        } else {
        	// "△"：通常訂正
        	inData.setSorModifyCode(API001_SOR_MODIFY_CODE_NORMAL);
        }
        EstimateStockModifyOrderIn input = new EstimateStockModifyOrderIn();
        input.setIndata(inData);
        EstimateStockModifyOrderOutData apiRes = null;
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        apiRes = apiWrapper.estimateStockModifyOrder(input);

        // 市場.訂正後市場=当社優先市場／SOR　かつ　訂正SOR注文結果区分="△"：SOR対象外の場合、エラーとする。
        if (SELECT_MARKET_SOR.equals(dtoReq.getAfterCorrecMarket()) && StringUtils.SPACE.equals(apiRes.getSorModifyStatus())) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                                    ERRORS_CMN_SORMODIFY_NOMODIFY,
                                    IfaCommonUtil.getMessage(ERRORS_CMN_SORMODIFY_NOMODIFY));
        }

        if (apiErrorUtil.isError(apiRes.getShubetu(), apiRes.getCode(), apiRes.getMessage())) {
            return apiErrorUtil.createDataList(List.of(), ERRRORS_CMN_ORDEREXECUTIONMODIFY_EXECUTION_FAILED);
        }
        
        resDto.setBrandName(outputFct027Dto.getBrandName());
        resDto.setShubetu(apiRes.getShubetu());
        resDto.setCode(apiRes.getCode());
        resDto.setErrMessage(apiRes.getMessage());
        resDto.setContractDate(apiRes.getTradeDate());
        resDto.setDeliveryDate(apiRes.getSettlementDate());
        resDto.setOrderDayTime(apiRes.getAcceptDate() + StringUtils.SPACE + apiRes.getAcceptTime());
        resDto.setPositionPower(apiRes.getMarginCapabilityAfter());
        resDto.setYukoKigenChange(apiRes.getModLimitFlg());
        resDto.setYukoKigen(apiRes.getModLimit());
        resDto.setCorrectSorOrderResultClassification(apiRes.getSorModifyStatus());
        resDto.setRequest(dtoReq);
        List<IfaMarginRepayOrderCorrectInputA010ResponseDto> resDtoList = new ArrayList<>();
        resDtoList.add(resDto);
        return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtils.EMPTY);
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
    
    /**
     * レスポンスを生成する.
     *
     * @param orderData APIレスポンスデータ
     * @return レスポンス
     */
    private IfaMarginRepayOrderCorrectInputResponseDto getResponseData(QueryStockUniteOrderPointOrd orderData, String sorServiceKbn) {
        
        IfaMarginRepayOrderCorrectInputResponseDto resDto = new IfaMarginRepayOrderCorrectInputResponseDto();
        resDto.setSorServiceKbn(sorServiceKbn);
        resDto.setBrandCode(orderData.getStockSecCode());
        resDto.setBrandName(orderData.getSecName());
        resDto.setOrderMarket(orderData.getMarketId());
        if ("K".equals(orderData.getBuySell())) {
            resDto.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_BUY);
        } else {
            resDto.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_SELL);
        }
        resDto.setQuantity(orderData.getQuantity());
        resDto.setUnTradeQuantity(orderData.getExecLeftQuantity());
        resDto.setPeriod(orderData.getLimit());
        if (RBE_ORDER_KIND_SLO.equals(orderData.getRbeOrderKind())
                || RBE_ORDER_KIND_NORMAL.equals(orderData.getRbeOrderKind())) {
            resDto.setOrderKind(ORDER_CLASS_NORMAL);
        } else {
            resDto.setOrderKind(ORDER_CLASS_OCO);
        }
        if (ORDER_CLASS_NORMAL.equals(resDto.getOrderKind())) {
            if (RBE_ORDER_KIND_NORMAL.equals(orderData.getRbeOrderKind())) {
                if (StringUtils.equalsAny(orderData.getSasinariId(),
                        new String[] { CODE_VAL_SASINARI_ID_LIMIT, CODE_VAL_SASINARI_ID_LIMIT_OPEN,
                                CODE_VAL_SASINARI_ID_LIMIT_CLOSE, CODE_VAL_SASINARI_ID_LIMIT_TO_MARKET,
                                CODE_VAL_SASINARI_ID_LIMIT_IOC })) {
                    // 指成区分=指値、寄指(Y)、引指(H)、不成(F)、IOC指(I)　の場合
                    resDto.setSasinariHouhou(ExecuteMethod.LIMIT.getId());
                } else if (StringUtils.equalsAny(orderData.getSasinariId(),
                        new String[] { CODE_VAL_SASINARI_ID_MARKET, CODE_VAL_SASINARI_ID_MARKET_OPEN,
                                CODE_VAL_SASINARI_ID_MARKET_CLOSE, CODE_VAL_SASINARI_ID_MARKET_IOC })) {
                    // 指成区分=指成区分=成行、寄成(Y)、引成(H)、IOC成(I)　の場合
                    resDto.setSasinariHouhou(ExecuteMethod.MARKET.getId());
                }
            } else if (RBE_ORDER_KIND_SLO.equals(orderData.getRbeOrderKind())) {
                resDto.setSasinariHouhou(ExecuteMethod.STOP.getId());
            }
            resDto.setSasinariJyouken(orderData.getSasinariId());
            resDto.setTriggerPrice(orderData.getLatestTriggerPrice());
            resDto.setTriggerPriceText(orderData.getLatestTriggerZone());
            if (RBE_ORDER_KIND_SLO.equals(orderData.getRbeOrderKind())) {
                if (StringUtils.equalsAny(orderData.getSasinariId(),
                        new String[] { CODE_VAL_SASINARI_ID_LIMIT, CODE_VAL_SASINARI_ID_LIMIT_OPEN,
                                CODE_VAL_SASINARI_ID_LIMIT_CLOSE, CODE_VAL_SASINARI_ID_LIMIT_TO_MARKET,
                                CODE_VAL_SASINARI_ID_LIMIT_IOC })) {
                    // 指成区分=指値、寄指(Y)、引指(H)、不成(F)、IOC指(I)　の場合
                    resDto.setGyakusasiHouhou(ExecuteMethod.LIMIT.getId());
                } else if (StringUtils.equalsAny(orderData.getSasinariId(),
                        new String[] { CODE_VAL_SASINARI_ID_MARKET, CODE_VAL_SASINARI_ID_MARKET_OPEN,
                                CODE_VAL_SASINARI_ID_MARKET_CLOSE, CODE_VAL_SASINARI_ID_MARKET_IOC })) {
                    // 指成区分=指成区分=成行、寄成(Y)、引成(H)、IOC成(I)　の場合
                    resDto.setGyakusasiHouhou(ExecuteMethod.MARKET.getId());
                }
            }
            resDto.setPrice(orderData.getLimitPrice());
        }
        if (ORDER_CLASS_OCO.equals(resDto.getOrderKind())) {
            resDto.setOco1SasinariHouhou(ExecuteMethod.LIMIT.getId());
            resDto.setOco1SasinariJyouken(orderData.getSasinariId());
            resDto.setOco1Price(orderData.getLimitPrice());
            resDto.setOco2TriggerPrice(orderData.getLatestTriggerPrice());
            resDto.setOco2TriggerPriceText(orderData.getLatestTriggerZone());
            if (StringUtils.equalsAny(orderData.getLatestOcoSasinariId(),
                    new String[] { CODE_VAL_SASINARI_ID_LIMIT, CODE_VAL_SASINARI_ID_LIMIT_TO_MARKET })) {
                // 直近OCO指成区分=指値、不成(F)　の場合
                resDto.setOco2GyakusasiHouhou(ExecuteMethod.LIMIT.getId());
            } else if (StringUtils.equalsAny(orderData.getLatestOcoSasinariId(),
                    new String[] { CODE_VAL_SASINARI_ID_MARKET })) {
                // 直近OCO指成区分=成行　の場合
                resDto.setOco2GyakusasiHouhou(ExecuteMethod.MARKET.getId());
            }
            resDto.setOco2GyakusasiJyouken(orderData.getLatestOcoSasinariId());
            resDto.setOco2Price(orderData.getLatestOcoPrice());
        }
        resDto.setPaymentDeadline(orderData.getPaymentLimit());
        resDto.setMarginTradeTypeText(orderStatusUtil.getMarginTradeType(orderData.getPaymentLimit(),
                orderData.getIppanMgPaymentKbn(), orderData.getIppanMgPaymentLimit()));
        resDto.setRbeOrderStatus(orderData.getRbeOrderStatus());
        resDto.setWorkingStatus(Boolean.FALSE.toString());
        if (RBE_ORDER_STATUS_IGNITION.equals(resDto.getRbeOrderStatus())) {
            resDto.setWorkingStatus(Boolean.TRUE.toString());
        }
        // 新規単価
        String openPriceStr = orderData.getOpenPrice();
        if (openPriceStr != null && !openPriceStr.trim().isEmpty()) {
            BigDecimal openPrice = new BigDecimal(openPriceStr.trim());
            BigDecimal newPrice = openPrice.divide(BigDecimal.valueOf(100L));
            resDto.setNewPrice(newPrice.toPlainString());
        } else {
            resDto.setNewPrice(openPriceStr);
        }

        resDto.setConstructionDate(orderData.getOpenDate());
        resDto.setPositionNo(orderData.getOpenContractNo());
        resDto.setTesuuryouKbn(orderData.getComId());
        resDto.setOrderDate(orderData.getInputDate());
        resDto.setPaymentDeadlineDate(orderData.getIppanMgPaymentLimit());
        resDto.setDateKbn(orderData.getIppanMgPaymentKbn());
        resDto.setTradeStatus(orderData.getExecStatus());
        resDto.setLatestMarketId(orderData.getLatestMarketId());
        resDto.setSorOrderClassification(orderData.getSorKbn());
        return resDto;
    }
}
