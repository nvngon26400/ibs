package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct008;
import com.sbisec.helios.ap.bizcommon.component.Fct020;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.component.Fct027;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct008Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct020Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct027Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct020Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct027Dto;
import com.sbisec.helios.ap.bizcommon.util.PaymentLimitUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA016RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA016ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputDtoRepayPositionDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMarginRepayOrderInputService;
import com.sbisec.helios.ap.common.constants.AppConstants;
import com.sbisec.helios.ap.common.dto.CheckApiResultDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.MediateAbleTradeFlag;
import com.sbisec.helios.ap.common.enums.RepayMethod;
import com.sbisec.helios.ap.common.enums.SecurityMoneyClass;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.AccountUtil;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.nri.ifa.NriApiService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.EstimateMarginLumpOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateMarginLumpOrderInVec;
import jp.co.sbisec.pcenter.dto.yanap.EstimateMarginLumpOrderOutData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateMarginLumpOrderSortInData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateMarginLumpOrderSortOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1OoutVec;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1OutData;

/**
 * 画面ID：SUB0202_0212-04_1
 * 画面名：信用返済注文入力
 * 2024/04/08 新規作成
 *
 * @author 池亀　緑
 */
@Component(value = "cmpIfaMarginRepayOrderInputService")
public class IfaMarginRepayOrderInputServiceImpL implements IfaMarginRepayOrderInputService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMarginRepayOrderInputServiceImpL.class);
    
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
    
    /** 取引対象無しエラー */
    private static final String ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND = "errors.dms.orderablePosition.notFound";
    
    /** 数量エラー */
    private static final String ERRORS_DMS_ORDERABLEPOSITION_EXCEEDED = "errors.dms.orderablePosition.exceeded";
    
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
    
    /** メッセージID:返済建玉数チェックエラー */
    private static final String ERRORS_DMS_ORDERABLE_POSITION_TOO_MANY = "errors.dms.orderablePosition.tooMany";
    
    /** メッセージID:注文処理でエラーが発生しました。(エラーコード：{0}、エラーメッセージ{1}) */
    private static final String ERRORS_ORDER_EXECUTION_FAILED = "errors.cmn.orderExecution.failed";
    
    /** メッセージID:本売買は、インサイダー情報に基づく売買ではないことの確認が必要です。 */
    private static final String WARNINGS_INSIDER_EXIST = "warning.dms.insider.exist";
    
    /** 区分値:対象取引（メッセージ表示用）: 区分=国内信用取引(3)　*/
    private static final String CODE_VAL_MSG_DISPLAY_TARGET_TRADE = "3";
    
    /** 区分値:対象取引（メッセージ表示用）: 表示パターン:1　*/
    private static final String DISPLAY_VAL_MSG_DISPLAY_TARGET_TRADE = "1";
    
    /** 区分値：証券金銭種別.国内株式 */
    private static final String DOMESTIC_STOCK = "01";
    
    /** 区分値:国籍コード.99 */
    private static final String CODE_VAL_NATIONALITY_CODE_99 = "99";
    
    /** 区分値:通貨コード.999 */
    private static final String CODE_VAL_CURRENCY_CODE_999 = "999";
    
    /** 新規売買区分="0":（買新規） */
    private static final String OPENTRADEKBN_BUY = "0";
    
    /** 区分.対象取引（メッセージ表示用） */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 新規市場:全市場 */
    private static final String NEW_MARKET_ALL = "ALL";
    
    /** 区分.取引種別（国内株式）: 信用返済買(5) */
    private static final String DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_BUY = "5";
    
    /** 区分.取引種別（国内株式）: 信用返済売(6) */
    private static final String DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_SELL = "6";
    
    /** 区分.注文種別: 通常/逆指値(1) */
    private static final String ORDER_CLASS_NORMAL = "1";
    
    /** 区分.注文種別: OCO(2) */
    private static final String ORDER_CLASS_OCO = "2";
    
    /** 区分.執行方法: 指値(1) */
    private static final String EXECUTE_METHOD_PRICE_LIMIT = "1";
    
    /** 区分.執行方法: 逆指値(3) */
    private static final String EXECUTE_METHOD_REVERSE_PRICE_LIMIT = "3";
    
    /** 区分.執行方法: 当日中(1) */
    private static final String PERIOD_CONDITIONS_TODAY = "0";
    
    /** 区分.選択市場: 当社優先市場／SOR(A) */
    private static final String SELECT_MARKET_SOR = "A";
    
    /** 区分.返済方法：一括指定(0) */
    private static final String REPAY_METHOD_MASS_DESIGNATION = "0";
    
    /** 区分.返済方法：個別指定(1) */
    private static final String REPAY_METHOD_INDIVIDUAL = "1";

    /** 区分.返済方法：個別指定（単独）(2) */
    private static final String REPAY_METHOD_INDIVIDUAL_ONLY = "2";
    
    /** リクエストタイプ : "61":評価益率順 */
    private static final String REQUEST_TYPE_EVALUATION_PROFIT_ORDER = "61";
    
    /** 区分.信用口座(国内) : "1"（信用口座） */
    private static final String ACCOUNT_STATUS_CLOSED = "1";
    
    /** API.001内部エラー区分=="1"(内部取引に該当する) */
    private static final String INSIDER_ERROR = "1";
    
    /** 返済建玉明細MAX件数 */
    private static final int REPAY_POSITION_COUNT_MAX = 999;
    
    /** 取得単価の除数 */
    private static final BigDecimal OPEN_PRICE_DIVISOR = new BigDecimal("100");
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct008 fct008;
    
    @Autowired
    private Fct020 fct020;
    
    @Autowired
    private Fct021 fct021;
    
    @Autowired
    private Fct027 fct027;
    
    @Autowired
    private CodeListService codeListService;
    
    @Autowired
    private NriApiService nriApiService;

    @Autowired
    private PaymentLimitUtil paymentLimitUtil;

    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaMarginRepayOrderInputA001RequestDto
     * Dto レスポンス：IfaMarginRepayOrderInputA001ResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginRepayOrderInputA001ResponseDto> initializeA001(
            IfaMarginRepayOrderInputA001RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginRepayOrderInputServiceImpL.initializeA001");
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
        
        //　②取引コース媒介可否チェックを行う。
        InputFct003Dto inpFct003Dto = new InputFct003Dto();
        inpFct003Dto.setButenCode(cc.getButenCode());
        inpFct003Dto.setAccountNumber(cc.getAccountNumber());
        inpFct003Dto.setProductCd(DOMESTIC_STOCK);
        if (OPENTRADEKBN_BUY.equals(dtoReq.getOpenTradeKbn())) {
            inpFct003Dto.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_SELL);
        } else {
            inpFct003Dto.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_BUY);
            
        }
        if (isFct003Error(fct003.doCheck(inpFct003Dto))) {
            return IfaCommonUtil
                    .createDataList(List.of(), ErrorLevel.FATAL, ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                            IfaCommonUtil.getMessage(ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                                    new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE,
                                            CODE_VAL_MSG_DISPLAY_TARGET_TRADE,
                                            DISPLAY_VAL_MSG_DISPLAY_TARGET_TRADE) }));
        }
        
        // ③顧客共通情報の「信用口座区分(国内)」をもとに、信用口座開設状況をチェックを行う。
        //    「未開設」：信用口座未開設エラーを返す。
        if (!ACCOUNT_STATUS_CLOSED.equals(cc.getDomesticMarginAccountType())) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_DMS_NOT_OPEN,
                    IfaCommonUtil.getMessage(ERRORS_DMS_NOT_OPEN));
        }
        
        // ④建玉残高を取得する。
        String requestType = REQUEST_TYPE_EVALUATION_PROFIT_ORDER;
        if (REPAY_METHOD_MASS_DESIGNATION.equals(dtoReq.getRepayMethod())) {
            requestType = dtoReq.getRepaymentOrder();
        }
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();

        // API003.(建玉残高明細)を呼び出す
        final List<QueryMarginContract1OutData> api003ResList = nriApiService.queryMarginContract1List(
                cc.getButenCode(), cc.getAccountNumber(), dtoReq.getBrandCode(), dtoReq.getOpenTradeKbn(),
                NEW_MARKET_ALL, dtoReq.getPaymentDeadline(), requestType);
        
        
        for (QueryMarginContract1OutData outdata : api003ResList) {
            apiErrorUtil.checkApiResponse(outdata.getShubetu(), outdata.getCode(), outdata.getMessage());
        }
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(Collections.emptyList(), null);
        }
        // API003の結果から建玉明細を抽出したリストを作成する
        List<QueryMarginContract1OoutVec> api003PositionList = Optional.ofNullable(api003ResList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(api003Res -> 
                    api003Res != null && api003Res.getQueryMarginContract1Data() != null
                )
                .flatMap(api003Res -> api003Res.getQueryMarginContract1Data().stream())
                .collect(Collectors.toList());

        // 建玉明細のリストをフィルターする
        List<QueryMarginContract1OoutVec> api003PositionListFilter = api003PositionList;
        if (
                StringUtils.equals(dtoReq.getRepayMethod(), REPAY_METHOD_INDIVIDUAL) // 個別指定(1) 
                || StringUtils.equals(dtoReq.getRepayMethod(), REPAY_METHOD_INDIVIDUAL_ONLY) // 個別指定（単独）(2)
        ) {
            api003PositionListFilter = filterRepayPositionDetailListIndivisual(api003PositionList, dtoReq.getRepayPositionDetail());
        }
        
        // 注文可能数量を計算する
        BigDecimal orderableQuantity = null;
        if (
                StringUtils.equals(dtoReq.getRepayMethod(), REPAY_METHOD_INDIVIDUAL) // 個別指定(1) 
                || StringUtils.equals(dtoReq.getRepayMethod(), REPAY_METHOD_MASS_DESIGNATION) // 一括指定(0)
        ) {
            orderableQuantity = getOrderableQuantity(api003PositionList);
        } else {
            orderableQuantity = getOrderableQuantity(api003PositionListFilter); // 個別指定（単独）(2)
        }

        api003PositionList = api003PositionListFilter;
    
        // 建玉残高が不足している場合、エラー
        DataList<IfaMarginRepayOrderInputA001ResponseDto> errorDtoRes = checkOrderQuantity(
                dtoReq.getRepayPositionDetail(),
                api003PositionList,
                dtoReq.getRepayMethod(),
                dtoReq.getTotalQuantity(),
                orderableQuantity
        );

        if (errorDtoRes != null) {
            return errorDtoRes;
        }

        // レスポンスを作成する
        final QueryMarginContract1OutData output = api003ResList.get(0);
        final IfaMarginRepayOrderInputA001ResponseDto resDto = new IfaMarginRepayOrderInputA001ResponseDto();

        // 共通関数FCT027を呼び出す
        InputFct027Dto fct027Req = new InputFct027Dto();
        fct027Req.setBrandCode(dtoReq.getBrandCode());
        OutputFct027Dto fct027Res = fct027.getData(fct027Req);

        // 弁済期限（算出）を算出する。
        String paymentDeadlineCalculation = paymentLimitUtil.getPaymentLimit(output.getPaymentLimit(), dtoReq.getOpenTradeKbn(), output.getIppanMgPaymentKbn(), output.getIppanMgPaymentLimit(), fct027Res.getPremiumShortSaleCcategory());
        resDto.setPaymentDeadlineCalculation(paymentDeadlineCalculation);

        resDto.setBrandCode(dtoReq.getBrandCode());
        if (OPENTRADEKBN_BUY.equals(dtoReq.getOpenTradeKbn())) {
            resDto.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_SELL);
        } else {
            resDto.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_BUY);
        }
        resDto.setPaymentDeadline(dtoReq.getPaymentDeadline());
        resDto.setRepayMethod(dtoReq.getRepayMethod());
        resDto.setRepaymentOrder(dtoReq.getRepaymentOrder());
        resDto.setMaxOrderableQuantity(String.valueOf(orderableQuantity.toPlainString()));
        resDto.setCtNightBatchFinishFlag(output.getNightBatchEndFlg());
        if (REPAY_METHOD_MASS_DESIGNATION.equals(dtoReq.getRepayMethod())) {
            resDto.setRepayPositionDetailList(getDetail(dtoReq.getBrandCode(), output.getNightBatchEndFlg(),
                    StringUtils.EMPTY, resDto.getTradeCd(), dtoReq.getRepayMethod(), dtoReq.getTotalQuantity(),
                    dtoReq.getRepayPositionDetail(), api003PositionList));
            resDto.setTotalQuantity(dtoReq.getTotalQuantity());
        } else {
            resDto.setRepayPositionDetailList(getDetail(dtoReq.getBrandCode(), output.getNightBatchEndFlg(),
                    StringUtils.EMPTY, resDto.getTradeCd(), dtoReq.getRepayMethod(), dtoReq.getTotalQuantity(),
                    dtoReq.getRepayPositionDetail(), api003PositionList));
            // 返済方法=個別指定の場合に、合計数量を設定する
            if (REPAY_METHOD_INDIVIDUAL.equals(dtoReq.getRepayMethod())) {
                resDto.setTotalQuantity(String.valueOf(getTotalQuantity(resDto.getRepayPositionDetailList())));
            }
        }
        if (resDto.getRepayPositionDetailList().isEmpty()) {
            // 個別指定or個別指定(単独)の場合、API003の絞り込みの結果空になる場合があるのでチェック
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND));
        }
        
        List<IfaMarginRepayOrderInputA001ResponseDto> resDtoList = new ArrayList<>();
        resDtoList.add(resDto);
        return apiErrorUtil.createDataList(resDtoList, null);
    }
    
    /**
    * アクションID：A002
    * アクション名：市場選択
    
    * Dto リクエスト：IfaMarginRepayOrderInputA002RequestDto
    * Dto レスポンス：IfaMarginRepayOrderInputA002ResponseDto
    *
    * @param dtoReq リクエストDto
    * @return レスポンスDto
    * @exception Exception 例外が発生した場合
    */
    public DataList<IfaMarginRepayOrderInputA002ResponseDto> marketSelectionA002(
            IfaMarginRepayOrderInputA002RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginRepayOrderInputServiceImpL.marketSelectionA002");
        }
        
        IfaMarginRepayOrderInputA002ResponseDto resDto = new IfaMarginRepayOrderInputA002ResponseDto();
        
        // ②評価損益（リアル）を取得する。
        List<IfaMarginRepayOrderInputDtoRepayPositionDetail> detailList = new ArrayList<>();
        for (IfaMarginRepayOrderInputDtoRepayPositionDetail detail : dtoReq.getRepayPositionDetail()) {
            detail.setProfitAndLossReal(getProfitAndLossReal(dtoReq.getBrandCode(), dtoReq.getCtNightBatchFinishFlag(),
                    detail.getRightType(), dtoReq.getOrderMarket(), dtoReq.getTradeCd(), detail));
            detailList.add(detail);
        }
        resDto.setRepayPositionDetailList(detailList);
        
        // ③営業日リストを取得する。
        InputFct008Dto fct008Req = new InputFct008Dto();
        fct008Req.setBrandCode(dtoReq.getBrandCode());
        fct008Req.setPeriodTargetMarket(dtoReq.getOrderMarket());
        // 共通関数FCT008を呼び出す
        resDto.setBusinessDayList(fct008.getData(fct008Req).getBussiessDaylist().stream()
                .map(new SimpleDateFormat("yyyy/MM/dd")::format).collect(Collectors.toList()));
        
        List<IfaMarginRepayOrderInputA002ResponseDto> resDtoList = new ArrayList<>();
        resDtoList.add(resDto);
        return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtils.EMPTY);
    }
    
    /**
     * アクションID：A004
     * アクション名：更新
     * Dto リクエスト：IfaMarginRepayOrderInputA004RequestDto
     * Dto レスポンス：IfaMarginRepayOrderInputA004ResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginRepayOrderInputA004ResponseDto> updateA004(IfaMarginRepayOrderInputA004RequestDto dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginRepayOrderInputServiceImpL.updateA004");
        }
        
        // 顧客共通情報の取得
        final CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // ②建玉残高を取得する。
        String requestType = REQUEST_TYPE_EVALUATION_PROFIT_ORDER;
        if (REPAY_METHOD_MASS_DESIGNATION.equals(dtoReq.getRepayMethod())) {
            requestType = dtoReq.getRepaymentOrder();
        }
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // API003.(建玉残高明細)を呼び出す
        final List<QueryMarginContract1OutData> api003ResList = nriApiService.queryMarginContract1List(
                cc.getButenCode(), cc.getAccountNumber(), dtoReq.getBrandCode(), dtoReq.getOpenTradeKbn(),
                NEW_MARKET_ALL, dtoReq.getPaymentDeadline(), requestType);

        for (QueryMarginContract1OutData outdata : api003ResList) {
            apiErrorUtil.checkApiResponse(outdata.getShubetu(), outdata.getCode(), outdata.getMessage());
        }
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(Collections.emptyList(), null);
        }
        // API003の結果から建玉明細を抽出したリストを作成する
        List<QueryMarginContract1OoutVec> api003PositionList = Optional.ofNullable(api003ResList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(api003Res ->
                        api003Res != null && api003Res.getQueryMarginContract1Data() != null
                )
                .flatMap(api003Res -> api003Res.getQueryMarginContract1Data().stream())
                .collect(Collectors.toList());

        // 建玉明細のリストをフィルターする
        List<QueryMarginContract1OoutVec> api003PositionListFilter = api003PositionList;
        if (
                StringUtils.equals(dtoReq.getRepayMethod(), REPAY_METHOD_INDIVIDUAL) // 個別指定(1) 
                || StringUtils.equals(dtoReq.getRepayMethod(), REPAY_METHOD_INDIVIDUAL_ONLY) // 個別指定（単独）(2)
        ) {
            api003PositionListFilter = filterRepayPositionDetailListIndivisual(api003PositionList, dtoReq.getRepayPositionDetail());
        }

        // 注文可能数量を計算する
        BigDecimal orderableQuantity = null;
        if (
                StringUtils.equals(dtoReq.getRepayMethod(), REPAY_METHOD_INDIVIDUAL) // 個別指定(1) 
                || StringUtils.equals(dtoReq.getRepayMethod(), REPAY_METHOD_MASS_DESIGNATION) // 一括指定(0)
        ) {
            orderableQuantity = getOrderableQuantity(api003PositionList);
        } else {
            orderableQuantity = getOrderableQuantity(api003PositionListFilter); // 個別指定（単独）(2)
        }

        api003PositionList = api003PositionListFilter;

        // 建玉残高が不足している場合、エラー
        DataList<IfaMarginRepayOrderInputA004ResponseDto> errorDtoRes = checkOrderQuantity(
                dtoReq.getRepayPositionDetail(),
                api003PositionList,
                dtoReq.getRepayMethod(),
                dtoReq.getTotalQuantity(),
                orderableQuantity
        );

        if (errorDtoRes != null) {
            return errorDtoRes;
        }
        
        // レスポンスを作成する
        final QueryMarginContract1OutData output = api003ResList.get(0);
        final IfaMarginRepayOrderInputA004ResponseDto resDto = new IfaMarginRepayOrderInputA004ResponseDto();

        // 共通関数FCT027を呼び出す
        InputFct027Dto fct027Req = new InputFct027Dto();
        fct027Req.setBrandCode(dtoReq.getBrandCode());
        OutputFct027Dto fct027Res = fct027.getData(fct027Req);

        // 弁済期限（算出）を算出する。
        String paymentDeadlineCalculation = paymentLimitUtil.getPaymentLimit(output.getPaymentLimit(), dtoReq.getOpenTradeKbn(), output.getIppanMgPaymentKbn(), output.getIppanMgPaymentLimit(), fct027Res.getPremiumShortSaleCcategory());
        resDto.setPaymentDeadlineCalculation(paymentDeadlineCalculation);

        resDto.setBrandCode(dtoReq.getBrandCode());
        if (OPENTRADEKBN_BUY.equals(dtoReq.getOpenTradeKbn())) {
            resDto.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_SELL);
        } else {
            resDto.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_BUY);
        }
        resDto.setPaymentDeadline(dtoReq.getPaymentDeadline());
        resDto.setRepayMethod(dtoReq.getRepayMethod());
        resDto.setRepaymentOrder(dtoReq.getRepaymentOrder());
        resDto.setMaxOrderableQuantity(String.valueOf(orderableQuantity.toPlainString()));
        resDto.setCtNightBatchFinishFlag(output.getNightBatchEndFlg());
        resDto.setRepayPositionDetailList(getDetail(dtoReq.getBrandCode(), output.getNightBatchEndFlg(),
                dtoReq.getOrderMarket(), resDto.getTradeCd(), dtoReq.getRepayMethod(), dtoReq.getTotalQuantity(),
                dtoReq.getRepayPositionDetail(), api003PositionList));
        if (REPAY_METHOD_MASS_DESIGNATION.equals(dtoReq.getRepayMethod())) {
            resDto.setTotalQuantity(dtoReq.getTotalQuantity());
        } else if (REPAY_METHOD_INDIVIDUAL.equals(dtoReq.getRepayMethod())) {
            // 返済方法=個別指定の場合に、合計数量を設定する
            resDto.setTotalQuantity(String.valueOf(getTotalQuantity(resDto.getRepayPositionDetailList())));
        }
        if (resDto.getRepayPositionDetailList().isEmpty()) {
            // 個別指定or個別指定(単独)の場合、API003の絞り込みの結果空になる場合があるのでチェック
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND));
        }
        // ④営業日リストを取得する。
        InputFct008Dto fct008Req = new InputFct008Dto();
        fct008Req.setBrandCode(dtoReq.getBrandCode());
        fct008Req.setPeriodTargetMarket(dtoReq.getOrderMarket());
        // 共通関数FCT008を呼び出す
        resDto.setBusinessDayList(fct008.getData(fct008Req).getBussiessDaylist().stream()
                .map(new SimpleDateFormat("yyyy/MM/dd")::format).collect(Collectors.toList()));
        
        List<IfaMarginRepayOrderInputA004ResponseDto> resDtoList = new ArrayList<>();
        resDtoList.add(resDto);
        return apiErrorUtil.createDataList(resDtoList, null);
    }
    
    /**
     * アクションID：A016
     * アクション名：注文確認
     * Dto リクエスト：IfaMarginRepayOrderInputA016RequestDto
     * Dto レスポンス：IfaMarginRepayOrderInputA016ResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginRepayOrderInputA016ResponseDto> orderConfirmA016(
            IfaMarginRepayOrderInputA016RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginRepayOrderInputServiceImpL.orderConfirmA016");
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
        
        //　②取引コース媒介可否チェックを行う。
        InputFct003Dto inpFct003Dto = new InputFct003Dto();
        inpFct003Dto.setButenCode(cc.getButenCode());
        inpFct003Dto.setAccountNumber(cc.getAccountNumber());
        inpFct003Dto.setProductCd(DOMESTIC_STOCK);
        if (OPENTRADEKBN_BUY.equals(dtoReq.getOpenTradeKbn())) {
            inpFct003Dto.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_SELL);
        } else {
            inpFct003Dto.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_BUY);
        }
        if (isFct003Error(fct003.doCheck(inpFct003Dto))) {
            return IfaCommonUtil
                    .createDataList(List.of(), ErrorLevel.FATAL, ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                            IfaCommonUtil.getMessage(ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                                    new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE,
                                            CODE_VAL_MSG_DISPLAY_TARGET_TRADE,
                                            DISPLAY_VAL_MSG_DISPLAY_TARGET_TRADE) }));
        }
        
        // ③顧客共通情報の「信用口座区分(国内)」をもとに、信用口座開設状況をチェックを行う。
        // 「未開設」：信用口座未開設エラーを返す。
        if (!ACCOUNT_STATUS_CLOSED.equals(cc.getDomesticMarginAccountType())) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_DMS_NOT_OPEN,
                    IfaCommonUtil.getMessage(ERRORS_DMS_NOT_OPEN));
        }

        // ユーザー共通情報を取得する
        final UserAccount userAccount = IfaCommonUtil.getUserAccount();

        // ユーザ共通情報 .CCSログイン用IDが未設定(Null または空文字）の場合：取引不可エラーを返す
        if (StringUtils.isEmpty(userAccount.getCcsUserId())) {
            DataList<IfaMarginRepayOrderInputA016ResponseDto> response = IfaCommonUtil.createDataList(
                    List.of(),
                    ErrorLevel.FATAL,
                    ERRORS_CMN_CCSID_UNREGISTERED,
                    IfaCommonUtil.getMessage(ERRORS_CMN_CCSID_UNREGISTERED)
            );

            return response;
        }
        
        // ④口座の取引制限チェックを行う
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
        
        IfaMarginRepayOrderInputA016ResponseDto resDto = new IfaMarginRepayOrderInputA016ResponseDto();
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
        
        // ⑤銘柄の取引注意情報を取得する。
        InputFct027Dto inputFct027Dto = new InputFct027Dto();
        inputFct027Dto.setBrandCode(dtoReq.getBrandCode());
        OutputFct027Dto outputFct027Dto = fct027.getData(inputFct027Dto);
        // FCT027.規制銘柄区分=1:規制銘柄　の場合、取引注意情報（銘柄）メッセージを格納する。
        if (AppConstants.FLG_ON.equals(outputFct027Dto.getRegKbn())) {
            resDto.setTradeNoticeInfoBrandMsg(IfaCommonUtil.getMessage(WARNINGS_DMS_INFORMATIONCHECK));
        }
        
        // ⑥建玉残高を取得する。
        String requestType = REQUEST_TYPE_EVALUATION_PROFIT_ORDER;
        if (REPAY_METHOD_MASS_DESIGNATION.equals(dtoReq.getRepayMethod())) {
            requestType = dtoReq.getRepaymentOrder();
        }
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        
        // API003.(建玉残高明細)を呼び出す
        final List<QueryMarginContract1OutData> api003ResList = nriApiService.queryMarginContract1List(
                cc.getButenCode(), cc.getAccountNumber(), dtoReq.getBrandCode(), dtoReq.getOpenTradeKbn(),
                NEW_MARKET_ALL, dtoReq.getPaymentDeadline(), requestType);

        List<String> warningList = new ArrayList<>();
        for (QueryMarginContract1OutData outdata : api003ResList) {
            CheckApiResultDto result = apiErrorUtil.getApiResponseResult(outdata.getShubetu(), outdata.getCode(),
                    outdata.getMessage());
            if (result.getErrorLevel() == ErrorLevel.WARNING) {
                warningList.add(outdata.getMessage() + "（" + outdata.getCode() + "）");
            }
        }
        resDto.setWarningList(warningList);
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(Collections.emptyList(), null);
        }
        // API003の結果から建玉明細を抽出したリストを作成する
        List<QueryMarginContract1OoutVec> api003PositionList = Optional.ofNullable(api003ResList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(api003Res ->
                        api003Res != null && api003Res.getQueryMarginContract1Data() != null
                )
                .flatMap(api003Res -> api003Res.getQueryMarginContract1Data().stream())
                .collect(Collectors.toList());

        // 建玉明細のリストをフィルターする
        List<QueryMarginContract1OoutVec> api003PositionListFilter = api003PositionList;
        if (
                StringUtils.equals(dtoReq.getRepayMethod(), REPAY_METHOD_INDIVIDUAL) // 個別指定(1) 
                || StringUtils.equals(dtoReq.getRepayMethod(), REPAY_METHOD_INDIVIDUAL_ONLY) // 個別指定（単独）(2)
        ) {
            api003PositionListFilter = filterRepayPositionDetailListIndivisual(api003PositionList, dtoReq.getRepayPositionDetail());
        }

        // 注文可能数量を計算する
        BigDecimal orderableQuantity = null;
        if (
                StringUtils.equals(dtoReq.getRepayMethod(), REPAY_METHOD_INDIVIDUAL) // 個別指定(1) 
                || StringUtils.equals(dtoReq.getRepayMethod(), REPAY_METHOD_MASS_DESIGNATION) // 一括指定(0)
        ) {
            orderableQuantity = getOrderableQuantity(api003PositionList);
        } else {
            orderableQuantity = getOrderableQuantity(api003PositionListFilter); // 個別指定（単独）(2)
        }

        api003PositionList = api003PositionListFilter;

        // 建玉残高が不足している場合、エラー
        DataList<IfaMarginRepayOrderInputA016ResponseDto> errorDtoRes = checkOrderQuantity(
                dtoReq.getRepayPositionDetail(),
                api003PositionList,
                dtoReq.getRepayMethod(),
                dtoReq.getQuantity(),
                orderableQuantity
        );

        if (errorDtoRes != null) {
            return errorDtoRes;
        }
        
        // ⑦返済方法に応じて、注文確認を行う。
        // API001
        if (!REPAY_METHOD_MASS_DESIGNATION.equals(dtoReq.getRepayMethod())) {
            if (dtoReq.getRepayPositionDetail().size() > REPAY_POSITION_COUNT_MAX) {
                // リクエスト.建玉明細が1000件以上の場合、返済建玉数チェックエラーを返す
                return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_DMS_ORDERABLE_POSITION_TOO_MANY,
                        IfaCommonUtil.getMessage(ERRORS_DMS_ORDERABLE_POSITION_TOO_MANY));
            }
            
            EstimateMarginLumpOrderInData api001ReqData = new EstimateMarginLumpOrderInData();
            api001ReqData.setButenCd(cc.getButenCode());
            api001ReqData.setKozaNo(AccountUtil.formatToApi(cc.getAccountNumber()));
            api001ReqData.setAccountId(String.format("%11s", StringUtils.SPACE).replaceAll(StringUtils.SPACE, "0"));
            api001ReqData.setNumber(String.format("%7s", StringUtils.SPACE).replaceAll(StringUtils.SPACE, "0"));
            api001ReqData.setOrigin(AppConstants.FLG_OFF);
            api001ReqData.setBrandCd(dtoReq.getBrandCode());
            api001ReqData.setTradeKbn(dtoReq.getTradeCd());
            api001ReqData.setSumQuantity(dtoReq.getQuantity());
            if (ORDER_CLASS_NORMAL.equals(dtoReq.getOrderKind())) {
                api001ReqData.setSasinariKbn(dtoReq.getSasinariJyouken());
            } else {
                api001ReqData.setSasinariKbn(dtoReq.getOco1SasinariJyouken());
            }
            api001ReqData.setPrice(String.format("%10s", StringUtils.SPACE).replaceAll(StringUtils.SPACE, "0"));
            if (ORDER_CLASS_NORMAL.equals(dtoReq.getOrderKind())) {
                if (EXECUTE_METHOD_PRICE_LIMIT.equals(dtoReq.getSasinariHouhou())) {
                    api001ReqData.setPrice(dtoReq.getPrice());
                } else if (EXECUTE_METHOD_REVERSE_PRICE_LIMIT.equals(dtoReq.getSasinariHouhou())) {
                    if (EXECUTE_METHOD_PRICE_LIMIT.equals(dtoReq.getGyakusasiHouhou())) {
                        api001ReqData.setPrice(dtoReq.getPrice());
                    }
                }
            } else if (ORDER_CLASS_OCO.equals(dtoReq.getOrderKind())) {
                api001ReqData.setPrice(dtoReq.getOco1Price());
            }
            api001ReqData.setUkewHoho("7");
            api001ReqData.setMarket(dtoReq.getOrderMarket());
            api001ReqData.setJoZeiKbn("2");
            api001ReqData.setHitokuteiTradeKbn(StringUtils.SPACE);
            api001ReqData.setPaymentLimit(dtoReq.getPaymentDeadline());
            if (PERIOD_CONDITIONS_TODAY.equals(dtoReq.getPeriodTerms())) {
                api001ReqData.setLimit(String.format("%8s", StringUtils.SPACE));
            } else {
                api001ReqData.setLimit(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getLimit()));
            }
            api001ReqData.setSummary(String.format("%30s", StringUtils.SPACE));
            api001ReqData.setPaymentKbn(StringUtils.SPACE);
            api001ReqData.setPaymentMethod(String.format("%10s", StringUtils.SPACE));
            api001ReqData.setBankKbn(StringUtils.SPACE);
            api001ReqData.setBankName(String.format("%20s", StringUtils.SPACE));
            api001ReqData.setCallcenterKbn("0");
            api001ReqData.setUserId(userAccount.getCcsUserId());
            api001ReqData.setVettingKbn(StringUtils.SPACE);
            api001ReqData.setCheckPrice(String.format("%10s", StringUtils.SPACE).replaceAll(StringUtils.SPACE, "0"));
            api001ReqData.setComId(cc.getCustomerAttribute());
            api001ReqData.setCheckId(StringUtils.SPACE);
            api001ReqData.setLumpCount(dtoReq.getRepayPositionCount());
            List<EstimateMarginLumpOrderInVec> vecList = new ArrayList<>();
            for (IfaMarginRepayOrderInputDtoRepayPositionDetail detail : dtoReq.getRepayPositionDetail()) {
                EstimateMarginLumpOrderInVec vec = new EstimateMarginLumpOrderInVec();
                vec.setOrgNewTradeDate(detail.getParentStockTradeDate());
                vec.setOpenTradeDate(detail.getConstructionDate());
                vec.setOpenPrice(new BigDecimal(detail.getNewPrice()).multiply(BigDecimal.valueOf(100L))
                        .stripTrailingZeros().toPlainString());
                vec.setQuantity(detail.getOrderQuantity());
                vec.setBargainMarket(detail.getBuiltMarket());
                vecList.add(vec);
            }
            api001ReqData.setEstimateMarginLumpData(vecList);
            if (ORDER_CLASS_NORMAL.equals(dtoReq.getOrderKind())) {
                if (EXECUTE_METHOD_REVERSE_PRICE_LIMIT.equals(dtoReq.getSasinariHouhou())) {
                    api001ReqData.setRbeOrderKind("SLO");
                } else {
                    api001ReqData.setRbeOrderKind(String.format("%3s", StringUtils.SPACE));
                }
            } else {
                api001ReqData.setRbeOrderKind("OCO");
            }
            api001ReqData.setTriggerZone(StringUtils.SPACE);
            if (ORDER_CLASS_NORMAL.equals(dtoReq.getOrderKind())) {
                if (EXECUTE_METHOD_REVERSE_PRICE_LIMIT.equals(dtoReq.getSasinariHouhou())) {
                    if (DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_BUY.equals(dtoReq.getTradeCd())) {
                        api001ReqData.setTriggerZone("0");
                    } else {
                        api001ReqData.setTriggerZone("1");
                    }
                }
            } else if (ORDER_CLASS_OCO.equals(dtoReq.getOrderKind())) {
                if (DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_BUY.equals(dtoReq.getTradeCd())) {
                    api001ReqData.setTriggerZone("0");
                } else {
                    api001ReqData.setTriggerZone("1");
                }
            }
            api001ReqData.setTriggerPrice(String.format("%10s", StringUtils.SPACE).replaceAll(StringUtils.SPACE, "0"));
            if (ORDER_CLASS_NORMAL.equals(dtoReq.getOrderKind())) {
                if (EXECUTE_METHOD_REVERSE_PRICE_LIMIT.equals(dtoReq.getSasinariHouhou())) {
                    api001ReqData.setTriggerPrice(dtoReq.getTriggerPrice());
                }
            } else {
                api001ReqData.setTriggerPrice(dtoReq.getOco2TriggerPrice());
            }
            api001ReqData.setOcoSasinariKbn(StringUtils.SPACE);
            if (ORDER_CLASS_OCO.equals(dtoReq.getOrderKind())) {
                api001ReqData.setOcoSasinariKbn(dtoReq.getOco2GyakusasiJyouken());
            }
            // API定義より、成行の場合は"0000000000"をセットする
            api001ReqData.setOcoPrice(String.format("%10s", StringUtils.SPACE).replaceAll(StringUtils.SPACE, "0"));
            if (ORDER_CLASS_OCO.equals(dtoReq.getOrderKind())) {
                if (EXECUTE_METHOD_PRICE_LIMIT.equals(dtoReq.getOco2GyakusasiHouhou())) {
                    api001ReqData.setOcoPrice(dtoReq.getOco2Price());
                }
            } else {
                api001ReqData.setOcoPrice(String.format("%10s", StringUtils.SPACE));
            }
            api001ReqData.setIpAddress(StringUtils.rightPad("ifap", 39, StringUtils.SPACE));
            api001ReqData.setSorLastMarket(String.format("%3s", StringUtils.SPACE));
            if (SELECT_MARKET_SOR.equals(dtoReq.getOrderMarket())) {
                api001ReqData.setSorLastMarket("tse");
            }
            
            EstimateMarginLumpOrderOutData api001Res = null;
            api001Res = nriApiService.estimateMarginLumpOrder(api001ReqData);
            
            
            if (apiErrorUtil.isError(api001Res.getShubetu(), api001Res.getCode(), api001Res.getMessage())) {
                return apiErrorUtil.createDataList(List.of(), ERRORS_ORDER_EXECUTION_FAILED);
            }
            
            resDto.setBrandName(outputFct027Dto.getBrandName());
            // if(API.001内部エラー区分=="1"(内部取引に該当する)の場合)
            if (INSIDER_ERROR.equals(api001Res.getInsiderErrKbn())) {
                resDto.setInsiderConfirmMsg(IfaCommonUtil.getMessage(WARNINGS_INSIDER_EXIST));
            }
            
            resDto.setShubetu(api001Res.getShubetu());
            resDto.setCode(api001Res.getCode());
            resDto.setErrMessage(api001Res.getMessage());
            resDto.setQuoteUnitPrice(api001Res.getEstimatePrice());
            resDto.setContractAmount(api001Res.getAmount());
            resDto.setCharge(api001Res.getCost());
            resDto.setConsumptionTax(api001Res.getConsumptionTax());
            resDto.setYieldTax(api001Res.getCapitalGainTax());
            resDto.setSettlementAmount(api001Res.getNetAmount());
            resDto.setContractDate(api001Res.getTradeDate());
            resDto.setDeliveryDate(api001Res.getSettlementDate());
            resDto.setOrderDayTime(api001Res.getAcceptDate() + StringUtils.SPACE + api001Res.getAcceptTime());
            resDto.setRequest(dtoReq);
            List<IfaMarginRepayOrderInputA016ResponseDto> resDtoList = new ArrayList<>();
            resDtoList.add(resDto);
            return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                    StringUtils.EMPTY);
        }
        
        //　API002
        EstimateMarginLumpOrderSortInData api002ReqData = new EstimateMarginLumpOrderSortInData();
        api002ReqData.setButenCd(cc.getButenCode());
        api002ReqData.setKozaNo(AccountUtil.formatToApi(cc.getAccountNumber()));
        api002ReqData.setAccountId(String.format("%11s", StringUtils.SPACE).replaceAll(StringUtils.SPACE, "0"));
        api002ReqData.setNumber(String.format("%7s", StringUtils.SPACE).replaceAll(StringUtils.SPACE, "0"));
        api002ReqData.setOrigin(AppConstants.FLG_OFF);
        api002ReqData.setBrandCd(dtoReq.getBrandCode());
        api002ReqData.setTradeKbn(dtoReq.getTradeCd());
        api002ReqData.setSumQuantity(dtoReq.getQuantity());
        if (ORDER_CLASS_NORMAL.equals(dtoReq.getOrderKind())) {
            api002ReqData.setSasinariKbn(dtoReq.getSasinariJyouken());
        } else {
            api002ReqData.setSasinariKbn(dtoReq.getOco1SasinariJyouken());
        }
        api002ReqData.setPrice(String.format("%10s", StringUtils.SPACE).replaceAll(StringUtils.SPACE, "0"));
        if (ORDER_CLASS_NORMAL.equals(dtoReq.getOrderKind())) {
            if (EXECUTE_METHOD_PRICE_LIMIT.equals(dtoReq.getSasinariHouhou())) {
                api002ReqData.setPrice(dtoReq.getPrice());
            } else if (EXECUTE_METHOD_REVERSE_PRICE_LIMIT.equals(dtoReq.getSasinariHouhou())) {
                if (EXECUTE_METHOD_PRICE_LIMIT.equals(dtoReq.getGyakusasiHouhou())) {
                    api002ReqData.setPrice(dtoReq.getPrice());
                }
            }
        } else if (ORDER_CLASS_OCO.equals(dtoReq.getOrderKind())) {
            api002ReqData.setPrice(dtoReq.getOco1Price());
        }
        api002ReqData.setUkewHoho("7");
        api002ReqData.setMarket(dtoReq.getOrderMarket());
        api002ReqData.setJoZeiKbn("2");
        api002ReqData.setHitokuteiTradeKbn(StringUtils.SPACE);
        api002ReqData.setPaymentLimit(dtoReq.getPaymentDeadline());
        if (PERIOD_CONDITIONS_TODAY.equals(dtoReq.getPeriodTerms())) {
            api002ReqData.setLimit(String.format("%8s", StringUtils.SPACE));
        } else {
            api002ReqData.setLimit(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getLimit()));
        }
        api002ReqData.setSummary(String.format("%30s", StringUtils.SPACE));
        api002ReqData.setPaymentKbn(StringUtils.SPACE);
        api002ReqData.setPaymentMethod(String.format("%10s", StringUtils.SPACE));
        api002ReqData.setBankKbn(StringUtils.SPACE);
        api002ReqData.setBankName(String.format("%20s", StringUtils.SPACE));
        api002ReqData.setCallcenterKbn("0");
        api002ReqData.setUserId(userAccount.getCcsUserId());
        api002ReqData.setVettingKbn(StringUtils.SPACE);
        api002ReqData.setCheckPrice(String.format("%10s", StringUtils.SPACE).replaceAll(StringUtils.SPACE, "0"));
        api002ReqData.setComId(cc.getCustomerAttribute());
        api002ReqData.setCheckId(StringUtils.SPACE);
        api002ReqData.setRequestType(dtoReq.getRepaymentOrder());
        
        if (ORDER_CLASS_NORMAL.equals(dtoReq.getOrderKind())) {
            if (EXECUTE_METHOD_REVERSE_PRICE_LIMIT.equals(dtoReq.getSasinariHouhou())) {
                api002ReqData.setRbeOrderKind("SLO");
            } else {
                api002ReqData.setRbeOrderKind(String.format("%3s", StringUtils.SPACE));
            }
        } else {
            api002ReqData.setRbeOrderKind("OCO");
        }
        api002ReqData.setTriggerZone(StringUtils.SPACE);
        if (ORDER_CLASS_NORMAL.equals(dtoReq.getOrderKind())) {
            if (EXECUTE_METHOD_REVERSE_PRICE_LIMIT.equals(dtoReq.getSasinariHouhou())) {
                if (DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_BUY.equals(dtoReq.getTradeCd())) {
                    api002ReqData.setTriggerZone("0");
                } else {
                    api002ReqData.setTriggerZone("1");
                }
            }
        } else if (ORDER_CLASS_OCO.equals(dtoReq.getOrderKind())) {
            if (DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_BUY.equals(dtoReq.getTradeCd())) {
                api002ReqData.setTriggerZone("0");
            } else {
                api002ReqData.setTriggerZone("1");
            }
        }
        api002ReqData.setTriggerPrice(String.format("%10s", StringUtils.SPACE).replaceAll(StringUtils.SPACE, "0"));
        if (ORDER_CLASS_NORMAL.equals(dtoReq.getOrderKind())) {
            if (EXECUTE_METHOD_REVERSE_PRICE_LIMIT.equals(dtoReq.getSasinariHouhou())) {
                api002ReqData.setTriggerPrice(dtoReq.getTriggerPrice());
            }
        } else {
            api002ReqData.setTriggerPrice(dtoReq.getOco2TriggerPrice());
        }
        api002ReqData.setOcoSasinariKbn(StringUtils.SPACE);
        if (ORDER_CLASS_OCO.equals(dtoReq.getOrderKind())) {
            api002ReqData.setOcoSasinariKbn(dtoReq.getOco2GyakusasiJyouken());
        }
        // API定義より、成行の場合は"0000000000"をセットする
        api002ReqData.setOcoPrice(String.format("%10s", StringUtils.SPACE).replaceAll(StringUtils.SPACE, "0"));
        if (ORDER_CLASS_OCO.equals(dtoReq.getOrderKind())) {
            if (EXECUTE_METHOD_PRICE_LIMIT.equals(dtoReq.getOco2GyakusasiHouhou())) {
                api002ReqData.setOcoPrice(dtoReq.getOco2Price());
            }
        } else {
            api002ReqData.setOcoPrice(String.format("%10s", StringUtils.SPACE));
        }
        api002ReqData.setIpAddress(StringUtils.rightPad("ifap", 39, StringUtils.SPACE));
        api002ReqData.setSorLastMarket(String.format("%3s", StringUtils.SPACE));
        if (SELECT_MARKET_SOR.equals(dtoReq.getOrderMarket())) {
            api002ReqData.setSorLastMarket("tse");
        }
        
        EstimateMarginLumpOrderSortOutData api002Res = null;
       
        api002Res = nriApiService.estimateMarginLumpOrderSort(api002ReqData);
        
        if (apiErrorUtil.isError(api002Res.getShubetu(), api002Res.getCode(), api002Res.getMessage())) {
            return apiErrorUtil.createDataList(List.of(), ERRORS_ORDER_EXECUTION_FAILED);
        }
        
        resDto.setShubetu(api002Res.getShubetu());
        resDto.setCode(api002Res.getCode());
        resDto.setErrMessage(api002Res.getMessage());
        resDto.setBrandName(outputFct027Dto.getBrandName());
        if (INSIDER_ERROR.equals(api002Res.getInsiderErrKbn())) {
            resDto.setInsiderConfirmMsg(IfaCommonUtil.getMessage(WARNINGS_INSIDER_EXIST));
        }
        resDto.setQuoteUnitPrice(api002Res.getEstimatePrice());
        resDto.setContractAmount(api002Res.getAmount());
        resDto.setCharge(api002Res.getCost());
        resDto.setConsumptionTax(api002Res.getConsumptionTax());
        resDto.setYieldTax(api002Res.getCapitalGainTax());
        resDto.setSettlementAmount(api002Res.getNetAmount());
        resDto.setContractDate(api002Res.getTradeDate());
        resDto.setDeliveryDate(api002Res.getSettlementDate());
        resDto.setOrderDayTime(api002Res.getAcceptDate() + StringUtils.SPACE + api002Res.getAcceptTime());
        resDto.setRequest(dtoReq);
        List<IfaMarginRepayOrderInputA016ResponseDto> resDtoList = new ArrayList<>();
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
     * 建玉残高明細APIのレスポンスから注文可能数量を算出する.
     *
     * @param api003PositionList 建玉残高明細APIの建玉明細レスポンスが入ったデータ
     * @return 注文可能数量
     * @throws Exception システムエラー
     */
    private BigDecimal getOrderableQuantity(List<QueryMarginContract1OoutVec> api003PositionList)
            throws Exception {
        
        // 注文可能数量を算出する
        BigDecimal orderableQuantity = api003PositionList.stream()
                .map(val -> {
                    BigDecimal contPosition = new BigDecimal(val.getContPosition());
                    BigDecimal unActualQuantity = new BigDecimal(val.getUnactualQuantity());
                    BigDecimal orderableQuantityIndv = contPosition.subtract(unActualQuantity);
    
                    return orderableQuantityIndv;
                })
                .reduce((x, y) -> x.add(y))
                .orElse(BigDecimal.ZERO);

        return orderableQuantity;
    }

    /**
     * 建玉明細.注文株数から合計注文株数を算出する.
     *
     * @param res 建玉残高明細APIのレスポンスが入ったデータ
     * @return 合計数量
     */
    private long getTotalQuantity(List<IfaMarginRepayOrderInputDtoRepayPositionDetail> detailList) {
        
        long result = 0;
        for (IfaMarginRepayOrderInputDtoRepayPositionDetail detail : detailList) {
            result += Long.valueOf(detail.getOrderQuantity());
        }
        return result;
    }
    
    /**
     * 建玉明細リストを取得する.
     *
     * @param brandCode 銘柄コード
     * @param nightBatchEndFlg CT夜間バッチ終了フラグ
     * @param orderMarket 発注市場
     * @param tradeCd 取引種別
     * @param repayMethod 返済方法
     * @param totalQuantity 合計数量（注文株数）
     * @param reqDetailList リクエストの建玉明細
     * @param vecList 建玉残高明細APIのレスポンスの建玉明細
     * @return レスポンスデータ
     * @throws IllegalAccessException 不正アクセスエラー
     */
    private List<IfaMarginRepayOrderInputDtoRepayPositionDetail> getDetail(String brandCode, String nightBatchEndFlg,
            String orderMarket, String tradeCd, String repayMethod, String totalQuantity,
            List<IfaMarginRepayOrderInputDtoRepayPositionDetail> reqDetailList,
            List<QueryMarginContract1OoutVec> vecList) throws IllegalAccessException {
        
        List<IfaMarginRepayOrderInputDtoRepayPositionDetail> result = new ArrayList<>();
        long totalOrderQuantity = 0;
        for (QueryMarginContract1OoutVec vec : vecList) {
            IfaMarginRepayOrderInputDtoRepayPositionDetail outDetail = new IfaMarginRepayOrderInputDtoRepayPositionDetail();
            
            if (REPAY_METHOD_MASS_DESIGNATION.equals(repayMethod)) {
                outDetail.setBuiltMarket(vec.getBargainMarket());
                outDetail.setConstructionDate(vec.getOpenTradeDate());
                outDetail.setParentStockTradeDate(vec.getOrgNewTradeDate());
                outDetail.setNewPrice(
                        new BigDecimal(vec.getOpenPrice()).divide(BigDecimal.valueOf(100L)).toPlainString());
                outDetail.setPositionNo(vec.getOpenContractNo());
                outDetail.setContPosition(vec.getContPosition());
                outDetail.setCost(vec.getCost());
                outDetail.setUnactualQuantity(vec.getUnactualQuantity());
                
                final long orderQuantity = Long.valueOf(vec.getContPosition())
                        - Long.valueOf(vec.getUnactualQuantity());
                outDetail.setOrderQuantity(String.valueOf(orderQuantity));
                totalOrderQuantity += orderQuantity;
                
                outDetail.setProfitAndLossReal(getProfitAndLossReal(brandCode, nightBatchEndFlg, vec.getStRightId(),
                        orderMarket, tradeCd, outDetail));
                outDetail.setSpecificPositionType(vec.getTokuteiContractId());
                outDetail.setRightType(vec.getStRightId());
                result.add(outDetail);
                
                // 注文株数を充足する建玉に到達したらbreak
                if (Long.valueOf(totalQuantity) <= totalOrderQuantity) {
                    break;
                }
            } else {
                for (IfaMarginRepayOrderInputDtoRepayPositionDetail reqDetail : reqDetailList) {
                    if (reqDetail.getBuiltMarket().equals(vec.getBargainMarket())
                            && DateFormatUtil.dateFormatToYmdNoSign(reqDetail.getConstructionDate())
                                    .equals(vec.getOpenTradeDate())
                            && DateFormatUtil.dateFormatToYmdNoSign(reqDetail.getParentStockTradeDate())
                                    .equals(vec.getOrgNewTradeDate())
                            && new BigDecimal(reqDetail.getNewPrice()).compareTo(
                                    new BigDecimal(vec.getOpenPrice()).divide(BigDecimal.valueOf(100L))) == 0) {
                        outDetail.setBuiltMarket(vec.getBargainMarket());
                        outDetail.setConstructionDate(vec.getOpenTradeDate());
                        outDetail.setParentStockTradeDate(vec.getOrgNewTradeDate());
                        outDetail.setNewPrice(
                                new BigDecimal(vec.getOpenPrice()).divide(BigDecimal.valueOf(100L)).toPlainString());
                        outDetail.setPositionNo(vec.getOpenContractNo());
                        outDetail.setContPosition(vec.getContPosition());
                        outDetail.setCost(vec.getCost());
                        outDetail.setUnactualQuantity(vec.getUnactualQuantity());
                        if (REPAY_METHOD_INDIVIDUAL.equals(repayMethod)) {
                            // 返済方法=個別指定の場合、注文株数をリクエストの建玉明細から引き継ぐ
                            outDetail.setOrderQuantity(reqDetail.getOrderQuantity());
                        }
                        outDetail.setProfitAndLossReal(getProfitAndLossReal(brandCode, nightBatchEndFlg,
                                vec.getStRightId(), orderMarket, tradeCd, outDetail));
                        outDetail.setSpecificPositionType(vec.getTokuteiContractId());
                        outDetail.setRightType(vec.getStRightId());
                        result.add(outDetail);
                    }
                }
            }
        }
        
        // ■返済方法=一括指定　の場合
        // 最終明細は（リクエスト.合計数量-Σ（残高数量-返済注文済未出来数量））をセット　※集計は先頭から最終明細手前まで
        if (REPAY_METHOD_MASS_DESIGNATION.equals(repayMethod)) {
            final IfaMarginRepayOrderInputDtoRepayPositionDetail lastResult = result.get(result.size() - 1);
            lastResult.setOrderQuantity(String.valueOf(
                    (Long.valueOf(lastResult.getContPosition()) - Long.valueOf(lastResult.getUnactualQuantity()))
                            - (Long.valueOf(totalOrderQuantity) - Long.valueOf(totalQuantity))));
        }
        return result;
    }
    
    /**
     * 評価損益（リアル）を取得する.
     *
     * @param brandCode 銘柄コード
     * @param ctNightBatchFinishFlag CT夜間バッチ終了フラグ
     * @param rightType 権利区分
     * @param orderMarket 発注市場
     * @param tradeCd 取引種別
     * @param detail 建玉明細
     * @return 評価損益（リアル）
     */
    private String getProfitAndLossReal(String brandCode, String ctNightBatchFinishFlag, String rightType,
            String orderMarket, String tradeCd, IfaMarginRepayOrderInputDtoRepayPositionDetail detail) {
        
        // ⑤評価損益（リアル）を取得する。
        InputFct020Dto fct020Req = new InputFct020Dto();
        fct020Req.setBrandCode(brandCode);
        fct020Req.setCtNightBatchEndFlag(ctNightBatchFinishFlag);
        fct020Req.setRightType(rightType);
        fct020Req.setMarketCode(orderMarket);
        // FCT020（国内株リアル時価取得）を呼び出す
        OutputFct020Dto fct020Res = fct020.getData(fct020Req);
        
        if (
                fct020Res == null || StringUtils.isBlank(fct020Res.getCurrentValueForEvaluation())
                || detail == null || StringUtils.isBlank(detail.getContPosition())
                || StringUtils.isBlank(detail.getOrderQuantity())
        ) {
            // FCT020.評価用現在値、注文株数、残高数量のいずれかが取得できない場合、値をセットしない。
            return null;
        }
        // 残高数量が0の場合、注文株数>残高数量の場合は、値なしとする
        BigDecimal contPosition = new BigDecimal(detail.getContPosition()); // 残高数量
        BigDecimal orderQuantity = new BigDecimal(detail.getOrderQuantity()); // 注文株数
        if (contPosition.equals(new BigDecimal("0")) || orderQuantity.compareTo(contPosition) > 0) {
            return null;
        }
        try {
            // ■取引種別=信用返済売　の場合
            // (FCT020.評価用現在値×建玉明細.残高数量-建玉明細.取得単価/100×建玉明細.残高数量-建玉明細.諸経費)×(返済建玉明細.注文株数/建玉明細.残高数量)　をセット
            if (DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_SELL.equals(tradeCd)) {
                return new BigDecimal(fct020Res.getCurrentValueForEvaluation())
                        .multiply(contPosition)
                        .subtract(
                                new BigDecimal(detail.getNewPrice()).multiply(contPosition))
                        .subtract(new BigDecimal(detail.getCost()))
                        .multiply(orderQuantity).divide(contPosition, 0, RoundingMode.DOWN).toPlainString();
            }
            // ■取引種別=信用返済買　の場合
            // (建玉明細.取得単価/100×建玉明細.残高数量-FCT020.評価用現在値×建玉明細.残高数量-建玉明細.諸経費)×(返済建玉明細.注文株数/建玉明細.残高数量)　をセット
            return  new BigDecimal(detail.getNewPrice()).multiply(contPosition)
                    .subtract(new BigDecimal(fct020Res.getCurrentValueForEvaluation()).multiply(contPosition))
                    .subtract(new BigDecimal(detail.getCost()))
                    .multiply(orderQuantity).divide(contPosition, 0, RoundingMode.DOWN).toPlainString();
        } catch (NumberFormatException | ArithmeticException | NullPointerException e) {
            // 計算項目が無効の場合は、値をセットしない。
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("IfaMarginRepayOrderInputServiceImpL.getProfitAndLossReal", e);
            }
            return null;
        }
    }

    /**
     * リクエスト.返済建玉明細とAPI003.建玉明細を比較して
     * 返済するために十分な建玉を持っていることを確認する
     *
     * @param repayPositionDetail リクエスト.返済建玉明細
     * @param api003PositionList API003.建玉明細
     * @param repayMethod 返済方法
     * @param totalQuantity リクエスト.合計数量(一括返済の場合のみ必須)
     * @param maxOrderableQuantity API003から算出した注文可能数量(一括返済の場合のみ必須)
     * @return OK: null、NG: エラーレスポンス用DataList
     */
    private <T> DataList<T> checkOrderQuantity(
            List<IfaMarginRepayOrderInputDtoRepayPositionDetail> repayPositionDetail,
            List<QueryMarginContract1OoutVec> api003PositionList,
            String repayMethod,
            String totalQuantity,
            BigDecimal orderableQuantity
    ) {

        // 返済方法が「個別指定」または「個別指定(単独)」の場合
        if (StringUtils.equalsAny(
                repayMethod,
                RepayMethod.INDIVIDUAL.getId(), RepayMethod.INDIVIDUAL_ONLY.getId()
        )) {

            for (IfaMarginRepayOrderInputDtoRepayPositionDetail repayPosition : repayPositionDetail) {

                // 返済対象の建玉を検索する
                // ※ Actionパラメータシートより、下記の4つの条件を全て満たすレコードを検索する。
                // 　 1. API003.建玉明細.建市場 　　　　= リクエスト.返済建玉明細.建市場
                // 　 2. API003.建玉明細.新規約定日 　　= リクエスト.返済建玉明細.新規約定日
                // 　 3. API003.建玉明細.親株新規約定日 = リクエスト.返済建玉明細.親株新規約定日
                // 　 4. API003.建玉明細.取得単価 / 100 = リクエスト.返済建玉明細.新規単価
                QueryMarginContract1OoutVec  api003Position = api003PositionList.stream()
                        .filter(val -> {
                            // 建市場
                            boolean isEqualsBuiltMarket = StringUtils.equals(
                                    val.getBargainMarket(),
                                    repayPosition.getBuiltMarket()
                            );
                            
                            // 新規約定日
                            boolean isEqualsConstructionDate = StringUtils.equals(
                                    val.getOpenTradeDate(),
                                    DateFormatUtil.dateFormatToYmdNoSign(repayPosition.getConstructionDate())
                            );

                            // 親株新規約定日
                            boolean isEqualsParentStockTradeDate = StringUtils.equals(
                                    val.getOrgNewTradeDate(),
                                    DateFormatUtil.dateFormatToYmdNoSign(repayPosition.getParentStockTradeDate())
                            );

                            // 新規単価
                            BigDecimal api003OpenPrice =  new BigDecimal(val.getOpenPrice()).divide(OPEN_PRICE_DIVISOR);
                            BigDecimal repayPositionNewPrice = new BigDecimal(repayPosition.getNewPrice());
                            boolean isEqualsNewPrice = (api003OpenPrice.compareTo(repayPositionNewPrice) == 0);

                            return isEqualsBuiltMarket
                                    && isEqualsConstructionDate
                                    && isEqualsParentStockTradeDate
                                    && isEqualsNewPrice;
                        })
                        .findFirst()
                        .orElse(null);
                 

                // 返済対象の建玉が存在しなければエラーを返す
                if (api003Position == null) {
                    DataList<T> dtoRes = IfaCommonUtil.createDataList(
                            new ArrayList<>(),
                            ErrorLevel.FATAL,
                            ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND,
                            IfaCommonUtil.getMessage(ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND)
                    );

                    return dtoRes;
                }

                // 返済方法が「個別指定」のとき、注文株数 > 注文可能数量の場合エラー
                if (StringUtils.equals(repayMethod, RepayMethod.INDIVIDUAL.getId())) {

                    // 注文株数
                    BigDecimal orderQuantity = new BigDecimal(repayPosition.getOrderQuantity());

                    // 注文可能数量(=API003.残高数量 - API003.返済注文済未出来数量)
                    BigDecimal contPosition = new BigDecimal(api003Position.getContPosition());
                    BigDecimal unActualQuantity = new BigDecimal(api003Position.getUnactualQuantity());
                    BigDecimal orderableQuantityIndv = contPosition.subtract(unActualQuantity);

                    // 注文株数 > 注文可能数量の場合エラーを返す
                    if (0 < orderQuantity.compareTo(orderableQuantityIndv)) {
                        DataList<T> dtoRes = IfaCommonUtil.createDataList(
                                new ArrayList<>(),
                                ErrorLevel.FATAL,
                                ERRORS_DMS_ORDERABLEPOSITION_EXCEEDED,
                                IfaCommonUtil.getMessage(ERRORS_DMS_ORDERABLEPOSITION_EXCEEDED)
                        );

                        return dtoRes;
                    }
                }
            }

        } else if (StringUtils.equals(repayMethod, RepayMethod.BATCH.getId())) { // 返済方法が「一括指定」の場合

            // 合計数量
            BigDecimal orderQuantity = new BigDecimal(totalQuantity);

            // 合計数量　> 注文可能数量 の場合エラーを返す。
            if (0 < orderQuantity.compareTo(orderableQuantity)) {
                DataList<T> dtoRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.FATAL,
                        ERRORS_DMS_ORDERABLEPOSITION_EXCEEDED,
                        IfaCommonUtil.getMessage(ERRORS_DMS_ORDERABLEPOSITION_EXCEEDED)
                );

                return dtoRes;
            }

        }

        return null;
    }

    /**
     * 返済方法=個別指定　または　個別指定（単独）の場合
     * 対象の建玉明細のみを抽出する。
     * ※ Actionパラメータシートより、下記の4つの条件を全て満たすレコードを抽出する。
     *   1. API003.建玉明細.建市場 　　　　= リクエスト.返済建玉明細.建市場
     *   2. API003.建玉明細.新規約定日 　　= リクエスト.返済建玉明細.新規約定日
     *   3. API003.建玉明細.親株新規約定日 = リクエスト.返済建玉明細.親株新規約定日
     *   4. API003.建玉明細.取得単価 / 100 = リクエスト.返済建玉明細.新規単価
     *
     * @param api003PositionList API003.建玉明細
     * @param repayPositionDetail リクエスト.返済建玉明細
     * @return リクエスト.返済建玉明細で指定された建玉の一覧。
     */
    List<QueryMarginContract1OoutVec> filterRepayPositionDetailListIndivisual(
            List<QueryMarginContract1OoutVec> api003PositionList,
            List<IfaMarginRepayOrderInputDtoRepayPositionDetail> repayPositionDetail
    ) {

        List<QueryMarginContract1OoutVec> filteredList = new ArrayList<>();
        for (IfaMarginRepayOrderInputDtoRepayPositionDetail repayPosition : repayPositionDetail) {

            // 返済対象の建玉を検索し、存在する場合リストに追加する。
            List<QueryMarginContract1OoutVec> repayPositionOutVec = api003PositionList.stream()
                    .filter(val -> StringUtils.equals(val.getBargainMarket(), repayPosition.getBuiltMarket())) // 建市場
                    .filter(
                        val -> StringUtils.equals(val.getOpenTradeDate(), DateFormatUtil.dateFormatToYmdNoSign(repayPosition.getConstructionDate()))
                    ) // 新規約定日
                    .filter(
                        val -> StringUtils.equals(val.getOrgNewTradeDate(), DateFormatUtil.dateFormatToYmdNoSign(repayPosition.getParentStockTradeDate()))
                    )  // 親株新規約定日
                    .filter(val -> {
                        BigDecimal api003OpenPrice = new BigDecimal(val.getOpenPrice()).divide(OPEN_PRICE_DIVISOR);
                        BigDecimal repayPositionNewPrice = new BigDecimal(repayPosition.getNewPrice());
                        return api003OpenPrice.compareTo(repayPositionNewPrice) == 0;
                    }) // 新規単価
                    .collect(Collectors.toList());
            
            filteredList.addAll(repayPositionOutVec);
        }

        return filteredList;
    }
}
