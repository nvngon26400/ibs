package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.Collections;
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
import com.sbisec.helios.ap.bizcommon.util.PaymentLimitUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderInputA003DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderInputA003DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderInputA006DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderInputA006DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderInputX001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderInputX001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaReceiptDeliveryOrderInputService;
import com.sbisec.helios.ap.common.dto.CheckApiResultDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.MediateAbleTradeFlag;
import com.sbisec.helios.ap.common.model.CustomerCommon;
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

import jp.co.sbisec.pcenter.dto.yanap.EstimateMgRcptDliverOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.EstimateMgRcptDliverOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateMgRcptDliverOrderOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1In;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1InData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1OoutVec;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1OutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstimateCapabilityIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstimateCapabilityInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstimateCapabilityOutData;

/**
 * SUB0202_0212-08_1_現引現渡注文入力
 *
 * @author 池亀緑
 */
@Component(value = "cmpIfaReceiptDeliveryOrderInputService")
public class IfaReceiptDeliveryOrderInputServiceImpl implements IfaReceiptDeliveryOrderInputService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaReceiptDeliveryOrderInputServiceImpl.class);
    
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
    private PaymentLimitUtil paymentLimitUtil;

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
    
    /** 取引注意情報（銘柄）メッセージ */
    private static final String WARNINGS_DMS_INFORMATION_CHECK = "warnings.dms.informationCheck";
    
    /** 取引対象無しエラー */
    private static final String ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND = "errors.dms.orderablePosition.notFound";
    
    /** 数量エラー */
    private static final String ERRORS_DMS_ORDERABLEPOSITION_EXCEEDED = "errors.dms.orderablePosition.exceeded";
    
    /** メッセージID:注文発注API処理結果エラー */
    private static final String ERRORS_ORDER_EXECUTION_FAILED = "errors.cmn.orderExecution.failed";
    
    /** 内部者確認メッセージ */
    private static final String WARNINGS_DMS_INSIDER_EXIST = "warning.dms.insider.exist";
    
    /** CCSID未登録 エラー */
    private static final String ERRORS_CMN_CCSID_UNREGISTERED = "errors.cmn.ccsid.unregistered";
    
    /** 内部者エラー区分　＝　１（内部者取引に該当する） */
    private static final String INSIDER_ERR_KBN = "1";
    
    /** 区分.信用口座(国内) : "1"（信用口座） */
    private static final String MARGIN = "1";
    
    /** 区分.取引種別（国内株式） : "7"（現渡） */
    private static final String GENWATASHI = "7";
    
    /** 区分.取引種別（国内株式） : "8"（現引） */
    private static final String GENBIKI = "8";
    
    /** 区分.対象取引（メッセージ表示用） */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 区分.弁済期限 */
    private static final String PAYMENT_DEADLINE = "PAYMENT_DEADLINE";
    
    /** 区分.取引種別（国内株式） */
    private static final String DOMESTIC_STOCK_TRADE_CLASS = "DOMESTIC_STOCK_TRADE_CLASS";

    /** 区分.預り区分（国内） */
    private static final String DOMESTIC_DEPOSIT_TYPE = "DOMESTIC_DEPOSIT_TYPE";
    
    /** 区分.並替順序 : "61":評価益率順 */
    private static final String EVALUATION_PROFIT_SORT = "61";
    
    /** 区分値：3 */
    private static final String THREE = "3";
    
    /** EC-GW */
    private static final String EC_GW = "EC-GW";
    
    /** EC-GW2 */
    private static final String EC_GW2 = "EC-GW2";
    
    /** 国籍コード */
    private static final String NATIONALITY_CODE = "99";
    
    /** 通貨コード */
    private static final String CURRENCY_CODE = "999";
    
    /** Fct003設定値 証券金銭種別：国内株式 */
    private static final String DOMESTIC_STOCK = "01";
    
    /** 余力項目セット区分="Ａ"：Ｔ～Ｔ＋５まで全てセット */
    private static final String REQUEST_KBN1 = "A";
    
    /** 追証項目セット区分="N"：セット不要（初期値をセット） */
    private static final String REQUEST_KBN2 = "N";
    
    /** 新規売買区分="0" */
    private static final String BUY = "0";
    
    /** 新規売買区分="1" */
    private static final String SELL = "1";
    
    /** "1"(規制銘柄) */
    private static final String REGULATION_BRAND = "1";
    
    /** "2"(申告分離) */
    private static final String REPORT_SEPARATE = "2";
    
    /** "1" */
    private static final String ONE = "1";
    
    /** "50" */
    private static final String FIFTY = "50";
    
    /** アカウント毎の連番(ALL"0") */
    private static final String NUMBER_ALL0 = "0000000";
    
    /** 与信チェック用時価("0000000000") */
    private static final String CHECK_PRICE_ALL0 = "0000000000";
    
    /** アカウントID(ALL"0") */
    private static final String ACCOUNT_ID_ALL0 = "00000000000";
    
    /** オリジン("0") */
    private static final String OROGIN_0 = "0";
    
    /** 受渡方法("1") */
    private static final String UKEWHOHO_1 = "1";
    
    /** 受付経路区分("0") */
    private static final String CALLCENTER_KBN_0 = "0";
    
    /**
     * アクションID：X001
     * アクション名：初期化
     * Dto リクエスト：IfaReceiptDeliveryOrderInputX001DtoRequest
     * Dto レスポンス：IfaReceiptDeliveryOrderInputX001DtoResponse
     * model リクエスト：IfaReceiptDeliveryOrderInputX001RequestModel
     * model レスポンス：IfaReceiptDeliveryOrderInputX001ResponseModel
     *
     * @param dtoReq {@code IfaReceiptDeliveryOrderInputX001DtoRequest }
     * @return {@code DataList <IfaReceiptDeliveryOrderInputX001DtoResponse>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaReceiptDeliveryOrderInputX001DtoResponse> initializeX001(
            IfaReceiptDeliveryOrderInputX001DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaReceiptDeliveryOrderInputServiceImpl.initializeX001");
        }
        // レスポンスデータ
        List<IfaReceiptDeliveryOrderInputX001DtoResponse> resMainList = new ArrayList<>();
        // 顧客共通情報
        final CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // ①利用者の口座に対する権限チェックを行う。
        InputFct001Dto inpFct001Dto = new InputFct001Dto();
        inpFct001Dto.setButenCode(cc.getButenCode());
        inpFct001Dto.setAccountNumber(cc.getAccountNumber());
        OutputFct001Dto outFct001Dto = fct001.doCheck(inpFct001Dto);
        
        //　Fct001
        // 対象顧客参照権限有無＝権限なしの場合：権限なしエラーを返す
        if (Fct001.TARGET_CUSTOMER_REF_AUTH_FLAG_0.equals(outFct001Dto.getTargetCustomerRefAuthFlag())) {
            // 業務エラーメッセージの取得
            return IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ERRORS_BUTEN_ACCOUNT_NOTEXISTS,
                    IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOTEXISTS,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
        }
        // 取引停止フラグ＝取引停止口座の場合：取引停止口座エラーを返す
        if (Fct001.TRADE_SUSPEND_FLAG_1.equals(outFct001Dto.getTradeSuspendFlag())) {
            return IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE));
        }
        
        //　②取引コース媒介可否チェックを行う。
        InputFct003Dto inpFct003Dto = new InputFct003Dto();
        inpFct003Dto.setButenCode(cc.getButenCode());
        inpFct003Dto.setAccountNumber(cc.getAccountNumber());
        inpFct003Dto.setProductCd(DOMESTIC_STOCK);
        // リクエスト.新規売買区分　＝　’0’（買新規）の場合
        if (BUY.equals(dtoReq.getOpenTradeKbn())) {
            inpFct003Dto.setTradeCd(GENBIKI);
        } else {
            inpFct003Dto.setTradeCd(GENWATASHI);
        }
        
        //　Fct003
        //　媒介可否リスト.媒介可否　＝　1（媒介可)以外：取引不可エラーを返す。
        if (isFct003Error(fct003.doCheck(inpFct003Dto))) {
            // 区分.対象取引（メッセージ表示用）（区分値：3（国内信用取引） ＠表示パターン：1
            return IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL,
                    ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                            new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, THREE) }));
        }
        
        // ③顧客共通情報の「信用口座区分(国内)」をもとに、信用口座開設状況をチェックを行う。
        //    「未開設」：信用口座未開設エラーを返す。
        if (!MARGIN.equals(cc.getDomesticMarginAccountType())) {
            return IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ERRORS_DMS_NOT_OPEN,
                    IfaCommonUtil.getMessage(ERRORS_DMS_NOT_OPEN));
        }
        
        // ④建玉残高明細APIを呼び出して、未決済建玉明細を取得する。
        // "リクエストパラメーター(
        QueryMarginContract1InData api001ReqInData = new QueryMarginContract1InData();
        // 部店コード,
        api001ReqInData.setButenCd(cc.getButenCode());
        // 口座番号,
        api001ReqInData.setKozaNo(AccountUtil.formatToApi(cc.getAccountNumber()));
        // 銘柄コード,
        api001ReqInData.setBrandCd(dtoReq.getBrandCode());
        // 新規売買区分,
        api001ReqInData.setOpenTradeKbn(dtoReq.getOpenTradeKbn());
        // 新規市場,
        api001ReqInData.setOpenMarket(dtoReq.getNewOpenMarket());
        // 弁済期限,
        api001ReqInData.setPaymentLimit(dtoReq.getPaymentDeadline());
        // リクエストタイプ,"61":評価益率順
        api001ReqInData.setRequestType(EVALUATION_PROFIT_SORT);
        // 検索番号指定ＦＲＯＭ,one（API初回呼出時）
        api001ReqInData.setRefFrom(ONE);
        // 検索番号指定ＴＯ,fifty（API初回呼出時）
        api001ReqInData.setRefTo(FIFTY);
        // )をセット"
        QueryMarginContract1In api001Req = new QueryMarginContract1In();
        api001Req.setIndata(api001ReqInData);
        
        // API001.(建玉残高明細)を呼び出す
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        List<QueryMarginContract1OutData> api001ResList = apiWrapper.queryMarginContract1List2(api001Req);
        for (QueryMarginContract1OutData outdata : api001ResList) {
            apiErrorUtil.checkApiResponse(outdata.getShubetu(), outdata.getCode(), outdata.getMessage());
        }
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(Collections.emptyList(), ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND);
        }
        //３）上記以外の場合、取引対象無しエラーを返す。
        if (api001ResList == null || api001ResList.size() == 0) {
            return IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND));
        }
        QueryMarginContract1OoutVec api001ResData = getQueryMarginContract1OoutVec(dtoReq.getParentStockTradeDate(),
                dtoReq.getNewTradeDate(), dtoReq.getNewPrice(), api001ResList);
        //３）上記以外の場合、取引対象無しエラーを返す。
        if (api001ResData == null) {
            return IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND));
        }
        
        //レスポンスパラメータをセット
        IfaReceiptDeliveryOrderInputX001DtoResponse resMain = new IfaReceiptDeliveryOrderInputX001DtoResponse();
        resMain.setBrandCode(dtoReq.getBrandCode());
        //新規売買区分　＝　’0’（買建）の場合、’8’（現引）
        if (BUY.equals(dtoReq.getOpenTradeKbn())) {
            resMain.setOpenTradeKbn(GENBIKI);
        }
        //新規売買区分　＝　’1’（売建）の場合、’7’（現渡）
        if (SELL.equals(dtoReq.getOpenTradeKbn())) {
            resMain.setOpenTradeKbn(GENWATASHI);
        }
        resMain.setPaymentDeadline(dtoReq.getPaymentDeadline());
        resMain.setNewOpenMarket(dtoReq.getNewOpenMarket());
        resMain.setParentStockTradeDate(dtoReq.getParentStockTradeDate());
        resMain.setNewTradeDate(dtoReq.getNewTradeDate());
        resMain.setSelectLineNewPrice(dtoReq.getNewPrice());
        resMain.setNewOpenInterestNumber(api001ResData.getOpenContractNo());
        resMain.setParentStockConstructionDate(api001ResData.getOrgNewTradeDate());
        resMain.setConstructionDate(api001ResData.getOpenTradeDate());
        resMain.setOpenPrice(api001ResData.getOpenPrice());

        String paymentLimit = "";
        for (QueryMarginContract1OutData outdata : api001ResList) {
            resMain.setPaymentDeadlineDate(outdata.getIppanMgPaymentLimit());
            resMain.setDateKbn(outdata.getIppanMgPaymentKbn());
            paymentLimit = outdata.getPaymentLimit();
        }

        // ⑤建玉残高明細APIで取得した値をもとに、新規単価を算出する。
        // 新規単価　＝　API001.繰り返し部.取得単価　／　100
        resMain.setNewPrice(String.valueOf(Double.parseDouble(api001ResData.getOpenPrice()) / 100.0));

        // 注文可能数量　＝　API001.繰り返し部.残高数量 ー　API001.繰り返し部.返済注文済未出来数量
        resMain.setMaxOrderableQuantity(String.valueOf(Long.parseLong(api001ResData.getContPosition())
                - Long.parseLong(api001ResData.getUnactualQuantity())));
        
        // ⑦銘柄の取引注意情報を取得する
        InputFct027Dto fct027Req = new InputFct027Dto();
        // 銘柄コード(リクエスト.銘柄コード)をセットする
        fct027Req.setBrandCode(dtoReq.getBrandCode());
        
        // 共通関数FCT027を呼び出す
        OutputFct027Dto fct027Res = fct027.getData(fct027Req);

        // 弁済期限（算出）を算出する。
        String paymentDeadlineCalculation = paymentLimitUtil.getPaymentLimit(paymentLimit, dtoReq.getOpenTradeKbn(), resMain.getDateKbn(), resMain.getPaymentDeadlineDate(), fct027Res.getPremiumShortSaleCcategory());
        resMain.setPaymentDeadlineCalculation(paymentDeadlineCalculation);

        //⑨現引可能額を取得する。
        // 信用建玉余力リクエスト:入力値
        QueryMgEstimateCapabilityInData api003ReqInData = new QueryMgEstimateCapabilityInData(cc.getButenCode(),
                AccountUtil.formatToApi(cc.getAccountNumber()), REQUEST_KBN1, REQUEST_KBN2);
        QueryMgEstimateCapabilityIn api003ReqIn = new QueryMgEstimateCapabilityIn();
        api003ReqIn.setIndata(api003ReqInData);
        // 信用建玉余力リクエスト:NRI_QueryMgEstimateCapability
        QueryMgEstimateCapabilityOutData api003Req = apiWrapper.queryMgEstimateCapability(api003ReqIn);
        if (apiErrorUtil.isError(api003Req.getShubetu(), api003Req.getCode(), api003Req.getMessage())) {
            return apiErrorUtil.createDataList(resMainList, null);
        }
        // 信用建玉余力リクエスト:出力値
        resMain.setDeliveryDateT2(api003Req.getSettlementDateT().get(2).getSettlementDate());
        resMain.setCashOnDeliveryT2(api003Req.getSettlementDateT().get(0).getActualReceiptPower());
        resMain.setDeliveryDateT3(api003Req.getSettlementDateT().get(3).getSettlementDate());
        resMain.setCashOnDeliveryT3(api003Req.getSettlementDateT().get(1).getActualReceiptPower());
        
        resMainList.add(resMain);
        return apiErrorUtil.createDataList(resMainList, null);
    }
    
    /**
     * アクションID：A003
     * アクション名：初期化
     * Dto リクエスト：IfaReceiptDeliveryOrderInputA003DtoRequest
     * Dto レスポンス：IfaReceiptDeliveryOrderInputA003DtoResponse
     * model リクエスト：IfaReceiptDeliveryOrderInputA003RequestModel
     * model レスポンス：IfaReceiptDeliveryOrderInputA003ResponseModel
     *
     * @param dtoReq {@code IfaReceiptDeliveryOrderInputA003DtoRequest }
     * @return {@code DataList <IfaReceiptDeliveryOrderInputA003DtoResponse>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    @Override
    public DataList<IfaReceiptDeliveryOrderInputA003DtoResponse> updateA003(
            IfaReceiptDeliveryOrderInputA003DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaReceiptDeliveryOrderInputServiceImpl.updateA003");
        }
        // レスポンスデータ
        List<IfaReceiptDeliveryOrderInputA003DtoResponse> resMainList = new ArrayList<>();
        // 顧客共通情報
        final CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // ②建玉残高を取得する。
        // "リクエストパラメーター(
        QueryMarginContract1InData api001ReqInData = new QueryMarginContract1InData();
        // 部店コード,
        api001ReqInData.setButenCd(cc.getButenCode());
        // 口座番号,
        api001ReqInData.setKozaNo(AccountUtil.formatToApi(cc.getAccountNumber()));
        // 銘柄コード,
        api001ReqInData.setBrandCd(dtoReq.getBrandCode());
        // 新規売買区分,
        api001ReqInData.setOpenTradeKbn(dtoReq.getOpenTradeKbn());
        // 新規市場,
        api001ReqInData.setOpenMarket(dtoReq.getNewOpenMarket());
        // 弁済期限,
        api001ReqInData.setPaymentLimit(dtoReq.getPaymentDeadline());
        // リクエストタイプ,"61":評価益率順
        api001ReqInData.setRequestType(EVALUATION_PROFIT_SORT);
        // 検索番号指定ＦＲＯＭ,one（API初回呼出時）
        api001ReqInData.setRefFrom(ONE);
        // 検索番号指定ＴＯ,fifty（API初回呼出時）
        api001ReqInData.setRefTo(FIFTY);
        // )をセット"
        QueryMarginContract1In api001Req = new QueryMarginContract1In();
        api001Req.setIndata(api001ReqInData);
        
        // API001.(建玉残高明細)を呼び出す
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        List<QueryMarginContract1OutData> api001ResList = apiWrapper.queryMarginContract1List2(api001Req);
        for (QueryMarginContract1OutData outdata : api001ResList) {
            apiErrorUtil.checkApiResponse(outdata.getShubetu(), outdata.getCode(), outdata.getMessage());
        }
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(Collections.emptyList(), ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND);
        }
        //３）上記以外の場合、取引対象無しエラーを返す。
        if (api001ResList == null || api001ResList.size() == 0) {
            return IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND));
        }
        QueryMarginContract1OoutVec api001ResData = getQueryMarginContract1OoutVec(dtoReq.getParentStockTradeDate(),
                dtoReq.getNewTradeDate(), dtoReq.getNewPrice(), api001ResList);
        //３）上記以外の場合、取引対象無しエラーを返す。
        if (api001ResData == null) {
            return IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND));
        }
        
        // ③建玉残高明細APIで取得した値をもとに、注文可能数量を算出する。
        // 画面.数量　 >　 注文可能数量(API001.繰り返し部.残高数量 ー　API001.繰り返し部.返済注文済未出来数量) の場合：エラーを返す。※リクエストに数量がある場合
        if (StringUtils.isNotEmpty(dtoReq.getQuantity())
                && Long.parseLong(dtoReq.getQuantity()) > Long.parseLong(api001ResData.getContPosition())
                        - Long.parseLong(api001ResData.getUnactualQuantity())) {
            return IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ERRORS_DMS_ORDERABLEPOSITION_EXCEEDED,
                    IfaCommonUtil.getMessage(ERRORS_DMS_ORDERABLEPOSITION_EXCEEDED));
        }
        //レスポンスパラメータをセット
        IfaReceiptDeliveryOrderInputA003DtoResponse resMain = new IfaReceiptDeliveryOrderInputA003DtoResponse();
        // 新規単価　＝　API001.繰り返し部.取得単価　／　100
        resMain.setNewPrice(String.valueOf(Double.parseDouble(api001ResData.getOpenPrice()) / 100.0));
        // 注文可能数量　＝　API001.繰り返し部.残高数量 ー　API001.繰り返し部.返済注文済未出来数量
        resMain.setMaxOrderableQuantity(String.valueOf(Long.parseLong(api001ResData.getContPosition())
                - Long.parseLong(api001ResData.getUnactualQuantity())));
        resMain.setNewOpenInterestNumber(api001ResData.getOpenContractNo());
        resMain.setParentStockConstructionDate(api001ResData.getOrgNewTradeDate());
        resMain.setConstructionDate(api001ResData.getOpenTradeDate());
        resMain.setOpenPrice(api001ResData.getOpenPrice());

        String paymentLimit = "";
        for (QueryMarginContract1OutData outdata : api001ResList) {
            resMain.setPaymentDeadlineDate(outdata.getIppanMgPaymentLimit());
            resMain.setDateKbn(outdata.getIppanMgPaymentKbn());
            paymentLimit = outdata.getPaymentLimit();
        }

        // ⑤銘柄の取引注意情報を取得する
        InputFct027Dto fct027Req = new InputFct027Dto();
        // 銘柄コード(リクエスト.銘柄コード)をセットする
        fct027Req.setBrandCode(dtoReq.getBrandCode());
        
        // 共通関数FCT027を呼び出す
        OutputFct027Dto fct027Res = fct027.getData(fct027Req);

        // 弁済期限（算出）を算出する。
        String paymentDeadlineCalculation = paymentLimitUtil.getPaymentLimit(paymentLimit, dtoReq.getOpenTradeKbn(), resMain.getDateKbn(), resMain.getPaymentDeadlineDate(), fct027Res.getPremiumShortSaleCcategory());
        resMain.setPaymentDeadlineCalculation(paymentDeadlineCalculation);

        //⑦現引可能額を取得する。
        // 信用建玉余力リクエスト:入力値
        QueryMgEstimateCapabilityInData api003ReqInData = new QueryMgEstimateCapabilityInData(cc.getButenCode(),
                AccountUtil.formatToApi(cc.getAccountNumber()), REQUEST_KBN1, REQUEST_KBN2);
        QueryMgEstimateCapabilityIn api003ReqIn = new QueryMgEstimateCapabilityIn();
        api003ReqIn.setIndata(api003ReqInData);
        // 信用建玉余力リクエスト:NRI_QueryMgEstimateCapability
        QueryMgEstimateCapabilityOutData api003Res = apiWrapper.queryMgEstimateCapability(api003ReqIn);
        if (apiErrorUtil.isError(api003Res.getShubetu(), api003Res.getCode(), api003Res.getMessage())) {
            return apiErrorUtil.createDataList(resMainList, null);
        }
        // 信用建玉余力リクエスト:出力値
        resMain.setDeliveryDateT2(api003Res.getSettlementDateT().get(2).getSettlementDate());
        resMain.setCashOnDeliveryT2(api003Res.getSettlementDateT().get(0).getActualReceiptPower());
        resMain.setDeliveryDateT3(api003Res.getSettlementDateT().get(3).getSettlementDate());
        resMain.setCashOnDeliveryT3(api003Res.getSettlementDateT().get(1).getActualReceiptPower());
        
        resMainList.add(resMain);
        return apiErrorUtil.createDataList(resMainList, null);
    }
    
    /**
     * アクションID：A006
     * アクション名：初期化
     * Dto リクエスト：IfaReceiptDeliveryOrderInputA006DtoRequest
     * Dto レスポンス：IfaReceiptDeliveryOrderInputA006DtoResponse
     * model リクエスト：IfaReceiptDeliveryOrderInputA006RequestModel
     * model レスポンス：IfaReceiptDeliveryOrderInputA006ResponseModel
     *
     * @param dtoReq {@code IfaReceiptDeliveryOrderInputA006DtoRequest }
     * @return {@code DataList <IfaReceiptDeliveryOrderInputA006DtoResponse>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    @Override
    public DataList<IfaReceiptDeliveryOrderInputA006DtoResponse> orderConfirmA006(
            IfaReceiptDeliveryOrderInputA006DtoRequest dtoReq) throws Exception {
        
        // レスポンスデータ
        List<IfaReceiptDeliveryOrderInputA006DtoResponse> resMainList = new ArrayList<>();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaReceiptDeliveryOrderInputServiceImpl.orderConfirmA006");
        }
        
        // 顧客共通情報
        final CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // ①利用者の口座に対する権限チェックを行う。
        InputFct001Dto inpFct001Dto = new InputFct001Dto();
        inpFct001Dto.setButenCode(cc.getButenCode());
        inpFct001Dto.setAccountNumber(cc.getAccountNumber());
        
        OutputFct001Dto outFct001Dto = fct001.doCheck(inpFct001Dto);
        
        //　Fct001
        // 対象顧客参照権限有無＝権限なしの場合：権限なしエラーを返す
        if (Fct001.TARGET_CUSTOMER_REF_AUTH_FLAG_0.equals(outFct001Dto.getTargetCustomerRefAuthFlag())) {
            // 業務エラーメッセージの取得
            return IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ERRORS_BUTEN_ACCOUNT_NOTEXISTS,
                    IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOTEXISTS,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
        }
        // 取引停止フラグ＝取引停止口座の場合：取引停止口座エラーを返す
        if (Fct001.TRADE_SUSPEND_FLAG_1.equals(outFct001Dto.getTradeSuspendFlag())) {
            return IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE));
        }
        
        //　②取引コース媒介可否チェックを行う。
        InputFct003Dto inpFct003Dto = new InputFct003Dto();
        inpFct003Dto.setButenCode(cc.getButenCode());
        inpFct003Dto.setAccountNumber(cc.getAccountNumber());
        inpFct003Dto.setProductCd(DOMESTIC_STOCK);
        inpFct003Dto.setTradeCd(dtoReq.getTradeCd());
        
        //　Fct003
        //　媒介可否リスト.媒介可否　＝　1（媒介可)以外：取引不可エラーを返す。
        if (isFct003Error(fct003.doCheck(inpFct003Dto))) {
            // 区分.対象取引（メッセージ表示用）（区分値：3（国内信用取引） ＠表示パターン：1
            return IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL,
                    ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                            new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, THREE) }));
        }
        // ③顧客共通情報の「信用口座区分(国内)」をもとに、信用口座開設状況をチェックを行う。
        // 「未開設」：信用口座未開設エラーを返す。
        if (!MARGIN.equals(cc.getDomesticMarginAccountType())) {
            return IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ERRORS_DMS_NOT_OPEN,
                    IfaCommonUtil.getMessage(ERRORS_DMS_NOT_OPEN));
        }
        
        // ユーザ共通情報.CCSログイン用IDのチェックを行う。
        if (StringUtil.isNullOrEmpty(IfaCommonUtil.getUserAccount().getCcsUserId())) {
            // 未設定(Null または空文字）の場合：取引不可エラーを返す。
            return IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL,
            ERRORS_CMN_CCSID_UNREGISTERED,
                    IfaCommonUtil.getMessage(ERRORS_CMN_CCSID_UNREGISTERED));
        }

        // ④口座の取引制限チェックを行う
        InputFct021Dto fct021Req = new InputFct021Dto();
        // 部店コード(顧客共通情報.部店コード)をセットする
        fct021Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        fct021Req.setAccountNumber(cc.getAccountNumber());
        // 証券金銭種別(国内株式（"01"）)をセットする
        fct021Req.setProductCd(DOMESTIC_STOCK);
        // 取引種別(リクエスト.取引種別)をセットする
        fct021Req.setTradeCd(dtoReq.getTradeCd());
        // 国籍コード(99)をセットする
        fct021Req.setCountryCd(NATIONALITY_CODE);
        // 通貨コード(999)をセットする
        fct021Req.setCurrencyCode(CURRENCY_CODE);
        // 弁済期限をセットする
        fct021Req.setPaymentLimit(dtoReq.getMarginTradeTypeText());
        
        // 共通関数FCT021を呼び出す
        OutputFct021Dto fct021Res = fct021.doCheck(fct021Req);
        
        //レスポンスパラメータをセット
        IfaReceiptDeliveryOrderInputA006DtoResponse response = new IfaReceiptDeliveryOrderInputA006DtoResponse();
        
        // FCT021.注意情報エラー有無=="1"(あり)の場合：注意情報エラーを返す
        if (Fct021.EXIST.equals(fct021Res.getNoteInfoErrFlag())) {
            // 区分.対象取引（メッセージ表示用）（区分値：3（国内信用取引） ＠表示パターン：1
            return IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ERRORS_NOTICE_ERROR_CHECK,
                    IfaCommonUtil.getMessage(ERRORS_NOTICE_ERROR_CHECK,
                            new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, THREE) }));
        }
        // FCT021.お知らせエラー有無=="1"(あり)の場合：お知らせエラーを返す
        if (Fct021.EXIST.equals(fct021Res.getNoteLimitErrFlag())) {
            return IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ERRORS_INFORMATION_CHECK,
                    IfaCommonUtil.getMessage(ERRORS_INFORMATION_CHECK));
        }
        // FCT021.注意情報アラート有無=="1"(あり)の場合：注意情報アラートを格納する
        if (Fct021.EXIST.equals(fct021Res.getNoteInfoAlertFlag())) {
            // 区分.対象取引（メッセージ表示用）（区分値：3（国内信用取引） ＠表示パターン：1
            // 注意情報アラート
            response.setNoticeInfoAlert(IfaCommonUtil.getMessage(WARNINGS_NOTICE_WARNING_CHECK,
                    new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, THREE) }));
        }
        // FCT021.お知らせアラート有無=="1"(あり)の場合：お知らせアラートを格納する
        if (Fct021.EXIST.equals(fct021Res.getNoteLimitAlertFlag())) {
            // お知らせアラート
            response.setNoticeAlert(IfaCommonUtil.getMessage(WARNINGS_CMM_INFORMATION_CHECK));
        }
        
        // ⑤銘柄の取引注意情報を取得する
        InputFct027Dto fct027Req = new InputFct027Dto();
        // 銘柄コード(リクエスト.銘柄コード)をセットする
        fct027Req.setBrandCode(dtoReq.getBrandCode());
        
        // 共通関数FCT027を呼び出す
        OutputFct027Dto fct027Res = fct027.getData(fct027Req);
        
        // 銘柄名
        response.setBrandName(fct027Res.getBrandName());
        // FCT027.規制銘柄区分=="1"(規制銘柄)の場合:取引注意情報（銘柄）メッセージを格納する
        if (REGULATION_BRAND.equals(fct027Res.getRegKbn())) {
            // 取引注意情報(銘柄)メッセージ(warnings.dms.informationCheck)を格納する
            response.setTradeNoticeInfoBrandMsg(IfaCommonUtil.getMessage(WARNINGS_DMS_INFORMATION_CHECK));
        }
        // ⑥建玉残高を取得する。
        // "リクエストパラメーター(
        QueryMarginContract1InData api001ReqInData = new QueryMarginContract1InData();
        // 部店コード,
        api001ReqInData.setButenCd(cc.getButenCode());
        // 口座番号,
        api001ReqInData.setKozaNo(AccountUtil.formatToApi(cc.getAccountNumber()));
        // 銘柄コード,
        api001ReqInData.setBrandCd(dtoReq.getBrandCode());
        // 新規売買区分,
        // リクエスト.取引種別　＝　’0’（買新規）の場合
        if (GENBIKI.equals(dtoReq.getTradeCd())) {
            api001ReqInData.setOpenTradeKbn(BUY);
        }
        // リクエスト.取引種別　＝　’1’（売新規）の場合
        if (GENWATASHI.equals(dtoReq.getTradeCd())) {
            api001ReqInData.setOpenTradeKbn(SELL);
        }
        // 新規市場,
        api001ReqInData.setOpenMarket(dtoReq.getBuiltMarket());
        // 弁済期限,
        api001ReqInData.setPaymentLimit(dtoReq.getMarginTradeTypeText());
        // リクエストタイプ,"61":評価益率順
        api001ReqInData.setRequestType(EVALUATION_PROFIT_SORT);
        // 検索番号指定ＦＲＯＭ,one（API初回呼出時）
        api001ReqInData.setRefFrom(ONE);
        // 検索番号指定ＴＯ,fifty（API初回呼出時）
        api001ReqInData.setRefTo(FIFTY);
        // )をセット"
        QueryMarginContract1In api001Req = new QueryMarginContract1In();
        api001Req.setIndata(api001ReqInData);
        
        // API001.(建玉残高明細)を呼び出す
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        List<QueryMarginContract1OutData> api001ResList = apiWrapper.queryMarginContract1List2(api001Req);
        List<String> warningList = new ArrayList<>();
        for (QueryMarginContract1OutData outdata : api001ResList) {
            CheckApiResultDto result = apiErrorUtil.getApiResponseResult(outdata.getShubetu(), outdata.getCode(),
                    outdata.getMessage());
            if (result.getErrorLevel() == ErrorLevel.WARNING) {
                warningList.add(outdata.getMessage() + "（" + outdata.getCode() + "）");
            }
        }
        response.setWarningList(warningList);
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(Collections.emptyList(), ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND);
        }
        //３）上記以外の場合、取引対象無しエラーを返す。
        if (api001ResList == null || api001ResList.size() == 0) {
            return IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND));
        }
        QueryMarginContract1OoutVec api001ResData = getQueryMarginContract1OoutVec(dtoReq.getParentStockTradeDate(),
                dtoReq.getConstructionDate(), dtoReq.getNewPrice(), api001ResList);
        //３）上記以外の場合、取引対象無しエラーを返す。
        if (api001ResData == null) {
            return IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DMS_ORDERABLEPOSITION_NOTFOUND));
        }
        
        // ⑦建玉残高明細APIで取得した値をもとに、注文可能数量を算出する。
        // 注文可能数量　＝　API001.繰り返し部.残高数量 ー　API001.繰り返し部.返済注文済未出来数量
        final Long quantity = Long.parseLong(api001ResData.getContPosition())
                - Long.parseLong(api001ResData.getUnactualQuantity());
        
        // 画面.数量　 >　 注文可能数量 の場合：エラーを返す。
        if (Long.parseLong(dtoReq.getQuantity()) > quantity) {
            return IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ERRORS_DMS_ORDERABLEPOSITION_EXCEEDED,
                    IfaCommonUtil.getMessage(ERRORS_DMS_ORDERABLEPOSITION_EXCEEDED));
        }
        // 残高数量
        response.setContPosition(api001ResData.getContPosition());
        // 返済注文済未出来数量
        response.setUnactualQuantity(api001ResData.getUnactualQuantity());
        // 特定建玉区分
        response.setTokuteiContractId(api001ResData.getTokuteiContractId());
        
        // ⑧注文確認を行う。
        // "リクエストパラメーター(
        EstimateMgRcptDliverOrderInData api002ReqInData = new EstimateMgRcptDliverOrderInData();
        // 部店コード,
        api002ReqInData.setButenCd(cc.getButenCode());
        // 口座番号,
        api002ReqInData.setKozaNo(AccountUtil.formatToApi(cc.getAccountNumber()));
        // アカウントID(ALL"0")
        api002ReqInData.setAccountId(ACCOUNT_ID_ALL0);
        // アカウント毎の連番(ALL"0")
        api002ReqInData.setNumber(NUMBER_ALL0);
        // オリジン("0")
        api002ReqInData.setOrigin(OROGIN_0);
        // 銘柄コード
        api002ReqInData.setBrandCd(dtoReq.getBrandCode());
        // 決済方法
        api002ReqInData.setSettlementId(
                codeListService.convertKeyToExtKey(DOMESTIC_STOCK_TRADE_CLASS, EC_GW2, dtoReq.getTradeCd()));
        // 注文株数
        api002ReqInData.setQuantity(dtoReq.getQuantity());
        // 受渡方法("1")
        api002ReqInData.setUkewHoho(UKEWHOHO_1);
        // 建市場
        api002ReqInData.setMarket(dtoReq.getBuiltMarket());
        // 弁済期限
        api002ReqInData.setPaymentLimit(
                codeListService.convertKeyToExtKey(PAYMENT_DEADLINE, EC_GW, dtoReq.getMarginTradeTypeText()));
        // 'リクエスト.取引種別をもとに設定 取引種別　＝　”現渡”の場合:'2'（申告分離）
        if (GENWATASHI.equals(dtoReq.getTradeCd())) {
            api002ReqInData.setJoZeiKbn(REPORT_SEPARATE);
        } else {
            // 上記以外の場合:'△'
            api002ReqInData.setJoZeiKbn(StringUtils.SPACE);
        }
        // 非特定預り売買区分
        api002ReqInData.setHitokuteiTradeKbn(
                codeListService.convertKeyToExtKey(DOMESTIC_DEPOSIT_TYPE, EC_GW, dtoReq.getAccountType()));
        // 受付経路区分("0")
        api002ReqInData.setCallcenterKbn(CALLCENTER_KBN_0);
        // ユーザーＩＤ
        // ユーザー共通情報
        api002ReqInData.setUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
        // ベティング区分("△")
        api002ReqInData.setVettingKbn(StringUtils.SPACE);
        // 与信チェック用時価("0000000000")
        api002ReqInData.setCheckPrice(CHECK_PRICE_ALL0);
        //親株新規約定日
        api002ReqInData.setOrgNewTradeDate(dtoReq.getParentStockTradeDate());
        //新規約定日
        api002ReqInData.setOpenTradeDate(dtoReq.getConstructionDate());
        //新規単価
        api002ReqInData.setOpenPrice(dtoReq.getOpenPrice());
        // 余力チェック区分("△")
        api002ReqInData.setCheckId(StringUtils.SPACE);
        
        EstimateMgRcptDliverOrderIn api002ReqIn = new EstimateMgRcptDliverOrderIn();
        api002ReqIn.setIndata(api002ReqInData);
        // 信用建玉余力リクエスト:出力値
        EstimateMgRcptDliverOrderOutData api002Res = new EstimateMgRcptDliverOrderOutData();
        
        // 信用現引現渡注文確認:NRI_EstimateMgRcptDliverOrder
        api002Res = apiWrapper.estimateMgRcptDliverOrder(api002ReqIn);
        
        if (apiErrorUtil.isError(api002Res.getShubetu(), api002Res.getCode(), api002Res.getMessage())) {
            // API002の実行結果がエラーの場合
            return IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ERRORS_ORDER_EXECUTION_FAILED,
                    IfaCommonUtil.getMessage(ERRORS_ORDER_EXECUTION_FAILED,
                            new String[] { api002Res.getCode(), api002Res.getMessage() }));
        }
        
        // API002.内部者エラー区分　＝　１（内部者取引に該当する）：内部者確認メッセージを格納する。
        if (INSIDER_ERR_KBN.equals(api002Res.getInsiderErrKbn())) {
            // 内部者確認メッセージ
            response.setInsiderConfirmMsg(IfaCommonUtil.getMessage(WARNINGS_DMS_INSIDER_EXIST));
        }
        
        //レスポンスパラメータをセット
        // リクエスト内容
        response.setReq(dtoReq);
        // 約定金額
        response.setContractAmount(api002Res.getAmount());
        // 手数料
        response.setCharge(api002Res.getCost());
        // 消費税
        response.setConsumptionTax(api002Res.getConsumptionTax());
        // 譲渡益税
        response.setYieldTax(api002Res.getCapitalGainTax());
        // 精算金額
        response.setSettlementAmount(api002Res.getNetAmount());
        // 約定予定日
        response.setContractDate(api002Res.getTradeDate());
        // 受渡予定日
        response.setDeliveryDate(api002Res.getSettlementDate());
        // 受注日
        response.setOrderDate(api002Res.getAcceptDate());
        // 受注時刻
        response.setOrderTime(api002Res.getAcceptTime());
        // 種別
        response.setShubetu(api002Res.getShubetu());
        // エラーコード
        response.setCode(api002Res.getCode());
        // エラーメッセージ
        response.setErrMessage(api002Res.getMessage());
        
        resMainList.add(response);
        return IfaCommonUtil.createDataList(resMainList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtils.EMPTY);
    }
    
    /**
     * 建玉残高明細APIのレスポンスから対象データを取得する.
     * <p/>
     *　以下の全てに合致するデータが対象。
     *　・API001.繰り返し部.親株新規約定日　＝　リクエスト.親株新規約定日
     *　・API001.繰り返し部.新規約定日　＝　リクエスト.新規約定日
     *　・API001.繰り返し部.取得単価　＝　リクエスト.新規単価
     *
     * @param parentStockTradeDate リクエスト.親株新規約定日
     * @param newTradeDate リクエスト.新規約定日
     * @param newPrice リクエスト.新規単価
     * @param apiRes 建玉残高明細APIのレスポンスが入ったデータ
     * @return 対象データ
     * @throws IllegalAccessException 不正アクセスエラー
     * @throws IllegalArgumentException 不正な引数に対するエラー
     */
    private QueryMarginContract1OoutVec getQueryMarginContract1OoutVec(String parentStockTradeDate, String newTradeDate,
            String newPrice, List<QueryMarginContract1OutData> apiRes)
            throws IllegalArgumentException, IllegalAccessException {
        
        for (QueryMarginContract1OutData outData : apiRes) {
            for (QueryMarginContract1OoutVec vec : outData.getQueryMarginContract1Data()) {
                if (parentStockTradeDate.equals(vec.getOrgNewTradeDate()) && newTradeDate.equals(vec.getOpenTradeDate())
                        && Double.valueOf(newPrice) == Double.valueOf(vec.getOpenPrice()) / 100) {
                    return vec;
                }
            }
        }
        
        return null;
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
