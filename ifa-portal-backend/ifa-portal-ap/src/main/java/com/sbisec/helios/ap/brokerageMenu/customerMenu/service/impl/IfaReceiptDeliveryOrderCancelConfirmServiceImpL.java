package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.util.OrderStatusUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaReceiptDeliveryOrderCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaReceiptDeliveryOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaReceiptDeliveryOrderCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaReceiptDeliveryOrderCancelConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaReceiptDeliveryOrderCancelConfirmSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderCancelConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaReceiptDeliveryOrderCancelConfirmService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.RtnCdEnum;
import com.sbisec.helios.ap.common.exception.ApiError;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.AccountUtil;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.nri.ifa.NriApiService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sbisec.pcenter.dto.yanap.MgRcptDeliverCancelOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.MgRcptDeliverCancelOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.MgRcptDeliverCancelOrderOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointOrd;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointOutData;

/**
 * 画面ID：SUB0202_0212-09_1
 * 画面名：現引現渡注文取消確認
 *
 * @author SCSK
 2024/05/21 新規作成
 */
@Component(value = "cmpIfaReceiptDeliveryOrderCancelConfirmService")
public class IfaReceiptDeliveryOrderCancelConfirmServiceImpL implements IfaReceiptDeliveryOrderCancelConfirmService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaReceiptDeliveryOrderCancelConfirmServiceImpL.class);
    
    /** 入力した部店口座は存在しません */
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    /** 取引停止口座エラー */
    private static final String ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** 権限なしエラー */
    private static final String NO_AUTHORIZED = "0";
    
    /** 取引停止口座エラー */
    private static final String TRADE_SUSPEND_FLAG = "1";
    
    private static final char CHAR_ZERO = '0';
    
    /** API001リクエスト区分 "2":受注番号指定 */
    private static final String EXEC_ORDER = "2";
    
    /** API001検索番号指定 */
    private static final String REF_FROM_AND_TO = "     ";
    
    /** API001取引区分 */
    private static final String TORIHIKI_KBN = "2";
    
    /** API001約定取得区分 */
    private static final String TRADE_GET_KBN = "1";
    
    private static final String SPACE = " ";
    
    /** 指定した注文が見つかりません。 */
    private static final String ERRORS_CMN_ORDER_NOTFOUND = "errors.cmn.order.notFound";
    
    /** 指定した注文は取消できません。 */
    private static final String ERRORS_CMN_ORDERCANCEL_OUTOFSERVICE = "errors.cmn.orderCancel.outOfService";
    
    /** 証券金銭種別(国内株式) */
    private static final String PRODUCT_CODE_01 = "01";
    
    /** 媒介可否が"1"（媒介可） */
    private static final String INTERMEDIARY_VALUE_1 = "1";
    
    /** {0}ができないコースです。 */
    private static final String ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 区分.対象取引（メッセージ表示用）（区分値：2 ＠表示パターン：1 ） */
    private static final String MSG_DISPLAY_TARGET_TRADE_2 = "2";
    
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 注文部.決済方法="5":現引 */
    private static final String SETTLEMENT_ID_5 = "5";
    
    /** 注文部.決済方法="1":現渡 */
    private static final String SETTLEMENT_ID_1 = "1";
    
    /** 区分.取引種別（国内株式） 現引 */
    private static final String DOMESTIC_STOCK_TRADE_CLASS_8 = "8";
    
    /** 区分.取引種別（国内株式） 現渡 */
    private static final String DOMESTIC_STOCK_TRADE_CLASS_7 = "7";
    
    /** 信用口座が未開設です。 */
    private static final String ERRORS_CMN_DOMESTICMARGINACCOUNT_NOTOPEN = "errors.dms.domesticMarginAccount.notOpen";
    
    /** 注文発注前の注文データが登録できないため、注文しませんでした。 */
    private static final String ERROR_FRS_PREORDEREXECUTION_FAILED = "errors.frs.preOrderExecution.failed";
    
    private static final String ZERO = "0";
    
    /** API002 リクエスト（アカウントID）：ALL"0" */
    private static final String API_REQUEST_ORDER_ID_ACCOUNT_ID = String
            .format("%3s-%7s", StringUtil.EMPTY_STRING, StringUtil.EMPTY_STRING).replace(" ", "0");
    
    private static final String ORDER_TYPE = "S";
    
    private static final String IP_ADDRESS = "ifap";
    
    private static final String WARNING = "W";
    
    /** 注文取消後の注文データが更新できませんでした。注文取消は完了しています。 */
    private static final String WARNINGS_CMN_POSTORDEREXECUTIONCANCEL_COMPLETED = "warnings.cmn.postOrderExecutionCancel.completed";
    
    private static final String ERRORS_CMN_ORDEREXECUTIONCANCEL_FAILED = "errors.cmn.orderExecutionCancel.failed";
    
    /** CCSIDが未登録のためご利用できません。 */
    private static final String ERRORS_CMN_CCSID_UNREGISTERED = "errors.cmn.ccsid.unregistered";
    
    /** 弁済期限 日計り(D) */
    private static final String PAYMENT_DDEADLINE_D = "D";

    /** 弁済期限 日計りH(E) */
    private static final String PAYMENT_DDEADLINE_E = "E";

    /** 一日信用区分 日計り */
    private static final String DAILY_CREDIT_KBN_1 = "1";

    /** 一日信用区分 日計りH */
    private static final String DAILY_CREDIT_KBN_2 = "2";

    /** 一日信用区分 日計り,日計りH以外 */
    private static final String DAILY_CREDIT_KBN_OTHER = " ";

    /** FCT001 利用者顧客参照権限チェック */
    @Autowired
    private Fct001 fct001;
    
    /** FCT003 取引コース媒介可否チェック */
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private OrderStatusUtil orderStatusUtil;
    
    @Autowired
    private CodeListService codeListService;
    
    @Autowired
    private NriApiService nriApiService;
    
    @Autowired
    private IfaReceiptDeliveryOrderCancelConfirmDao dao;

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaReceiptDeliveryOrderCancelConfirmA001RequestDto
     * Dto レスポンス：IfaReceiptDeliveryOrderCancelConfirmA001ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaReceiptDeliveryOrderCancelConfirmA001ResponseDto> initializeA001(
            IfaReceiptDeliveryOrderCancelConfirmA001RequestDto dtoReq) throws Exception {
        
        DataList<IfaReceiptDeliveryOrderCancelConfirmA001ResponseDto> dtoRes = new DataList<IfaReceiptDeliveryOrderCancelConfirmA001ResponseDto>();
        List<IfaReceiptDeliveryOrderCancelConfirmA001ResponseDto> dtoResList = dtoRes.getDataList();
        IfaReceiptDeliveryOrderCancelConfirmA001ResponseDto a001Res = new IfaReceiptDeliveryOrderCancelConfirmA001ResponseDto();
        dtoResList.add(a001Res);
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaReceiptDeliveryOrderCancelConfirmServiceImplL.initializeA001");
        }
        
        // 顧客共通情報取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        String butenCode = customerCommon.getButenCode();
        String accountNumber = customerCommon.getAccountNumber();
        
        // １．利用者の口座に対する権限チェックを行う。
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setAccountNumber(accountNumber);
        inputFct001Dto.setButenCode(butenCode);
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        
        // 対象顧客参照権限有無＝"0"（権限なし） エラー
        if (NO_AUTHORIZED.equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_BUTENACCOUNTNOTEXIST,
                    IfaCommonUtil.getMessage(ERRORS_BUTENACCOUNTNOTEXIST, new String[] { butenCode, accountNumber }));
            return dtoRes;
        }
        // 取引停止フラグ＝"1"（取引停止口座） エラー
        if (TRADE_SUSPEND_FLAG.equals(outputFct001Dto.getTradeSuspendFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE));
            return dtoRes;
        }
        
        // ２．注文情報を取得する。
        QueryStockUniteOrderPointInData api001InData = new QueryStockUniteOrderPointInData();
        api001InData.setButenCd(butenCode);
        String kozaNo = StringUtil.fillLeft(accountNumber, CHAR_ZERO, 7);
        api001InData.setKozaNo(kozaNo);
        api001InData.setExecOrder(EXEC_ORDER);
        api001InData.setRefFrom(REF_FROM_AND_TO);
        api001InData.setRefTo(REF_FROM_AND_TO);
        api001InData.setOrderNo(dtoReq.getEcOrderNo());
        api001InData.setTorihikiKbn(TORIHIKI_KBN);
        api001InData.setTradeGetKbn(TRADE_GET_KBN);
        api001InData.setBrandCd(StringUtil.EMPTY_STRING);
        api001InData.setAccountGetKbn(SPACE);
        QueryStockUniteOrderPointOutData api001Res = new QueryStockUniteOrderPointOutData();
        try {
            api001Res = nriApiService.queryStockUniteOrderPoint(api001InData);
        } catch (ApiError ae) {
            // APIでエラーが発生した場合、エラーを返す。
            LOGGER.warn("IfaReceiptDeliveryOrderCancelConfirmServiceImpL.initializeA001", ae);
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, ERRORS_CMN_ORDER_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_CMN_ORDER_NOTFOUND));
            return dtoRes;
        }
        
        // 指定注文存在チェックエラーを返す
        final Optional<QueryStockUniteOrderPointOrd> optionalReqOrder = api001Res.getReqOrderDataExe().stream()
                .filter(reqOrderData -> StringUtils.equals(reqOrderData.getOrderNo(), dtoReq.getEcOrderNo()))
                .findFirst();
        
        if (!optionalReqOrder.isPresent()) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, ERRORS_CMN_ORDER_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_CMN_ORDER_NOTFOUND));
            return dtoRes;
        }
        // 取消ボタン表示判定（算出）
        if (!orderStatusUtil.canCancelOrder(api001Res.getReqOrderDataExe().get(0))) {
            // 指定した注文は訂正できません。
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_CMN_ORDERCANCEL_OUTOFSERVICE, IfaCommonUtil.getMessage(ERRORS_CMN_ORDERCANCEL_OUTOFSERVICE));
            return dtoRes;
        }
        // 信用取引区分（算出）を算出
        String marginTradeTypeText = orderStatusUtil.getMarginTradeType(
                api001Res.getReqOrderDataExe().get(0).getPaymentLimit(),
                api001Res.getReqOrderDataExe().get(0).getIppanMgPaymentKbn(),
                api001Res.getReqOrderDataExe().get(0).getIppanMgPaymentLimit());
        
        a001Res.setMarginTradeTypeText(marginTradeTypeText);
        a001Res.setBrandCode(api001Res.getReqOrderDataExe().get(0).getStockSecCode());
        a001Res.setBrandName(api001Res.getReqOrderDataExe().get(0).getSecName());
        if (SETTLEMENT_ID_5.equals(api001Res.getReqOrderDataExe().get(0).getSettlementId())) {
            a001Res.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_8);
        } else if (SETTLEMENT_ID_1.equals(api001Res.getReqOrderDataExe().get(0).getSettlementId())) {
            a001Res.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_7);
        }
        a001Res.setOrderQuantity(api001Res.getReqOrderDataExe().get(0).getQuantity());
        a001Res.setUnTradeQuantity(api001Res.getReqOrderDataExe().get(0).getQuantity());
        a001Res.setDepositType(api001Res.getReqOrderDataExe().get(0).getHitokuteiTradeKbn());
        a001Res.setOrderDayTime(api001Res.getReqOrderDataExe().get(0).getInputDate());
        a001Res.setNewPositionNumber(api001Res.getReqOrderDataExe().get(0).getOpenContractNo());
        a001Res.setNewTradeDate(api001Res.getReqOrderDataExe().get(0).getOpenDate());
        a001Res.setNewPrice(new BigDecimal(api001Res.getReqOrderDataExe().get(0).getOpenPrice().trim())
                .divide(BigDecimal.valueOf(100L)).toPlainString());
        a001Res.setPaymentDeadline(api001Res.getReqOrderDataExe().get(0).getPaymentLimit());
        a001Res.setMarket(api001Res.getReqOrderDataExe().get(0).getMarketId());
        a001Res.setPaymentDeadlineDate(api001Res.getReqOrderDataExe().get(0).getIppanMgPaymentLimit());
        a001Res.setDateKbn(api001Res.getReqOrderDataExe().get(0).getIppanMgPaymentKbn());
        a001Res.setComIdR(api001Res.getReqOrderDataExe().get(0).getComIdR());
        
        // ３．取引コース媒介可否チェックを行う。
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setButenCode(butenCode);
        inputFct003Dto.setAccountNumber(accountNumber);
        inputFct003Dto.setProductCd(PRODUCT_CODE_01);
        inputFct003Dto.setTradeCd(a001Res.getTradeCd());
        OutputFct003Dto outputFct003Dto;
        outputFct003Dto = fct003.doCheck(inputFct003Dto);
        for (OutputFct003Dto.MediatePropriety mediatePropriety : outputFct003Dto.getMediateProprietyList()) {
            if (!StringUtils.equals(INTERMEDIARY_VALUE_1, mediatePropriety.getMediatePropriety())) {
                dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                        IfaCommonUtil.getMessage(ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE, new String[] {
                                codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_2) }));
                return dtoRes;
            }
        }
        
        // ４．顧客共通情報.信用口座区分（国内）より、国内信用口座開設状況のチェックを行う。
        if (SPACE.equals(customerCommon.getDomesticMarginAccountType())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_CMN_DOMESTICMARGINACCOUNT_NOTOPEN,
                    IfaCommonUtil.getMessage(ERRORS_CMN_DOMESTICMARGINACCOUNT_NOTOPEN));
            return dtoRes;
        }
        
        dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, RtnCdEnum.SUCCESS.getText(),
                StringUtil.EMPTY_STRING);
        return dtoRes;
    }
    
    /**
     * アクションID：A002a
     * アクション名：注文発注
     * Dto リクエスト：IfaReceiptDeliveryOrderCancelConfirmA002RequestDto
     * Dto レスポンス：IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public DataList<IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto> orderPlacementA002a(
            IfaReceiptDeliveryOrderCancelConfirmA002RequestDto dtoReq,
            IfaReceiptDeliveryOrderCancelConfirmSql002RequestModel sql002Req)
            throws Exception {
        DataList<IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto> dtoRes = new DataList<IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto>();
        List<IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto> dtoResList = dtoRes.getDataList();
        IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto a002Res = new IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto();
        dtoResList.add(a002Res);
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaReceiptDeliveryOrderCancelConfirmServiceImplL.orderPlacementA002");
        }
        
        // 顧客共通情報取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        String butenCode = customerCommon.getButenCode();
        String accountNumber = customerCommon.getAccountNumber();


        
        // １．利用者の口座に対する権限チェックを行う。
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setAccountNumber(accountNumber);
        inputFct001Dto.setButenCode(butenCode);
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        
        // 対象顧客参照権限有無＝"0"（権限なし） エラー
        if (NO_AUTHORIZED.equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_BUTENACCOUNTNOTEXIST,
                    IfaCommonUtil.getMessage(ERRORS_BUTENACCOUNTNOTEXIST, new String[] { butenCode, accountNumber }));
            return dtoRes;
        }
        // 取引停止フラグ＝"1"（取引停止口座） エラー
        if (TRADE_SUSPEND_FLAG.equals(outputFct001Dto.getTradeSuspendFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE));
            return dtoRes;
        }
        
        // ２．取引コース媒介可否チェックを行う。
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setButenCode(butenCode);
        inputFct003Dto.setAccountNumber(accountNumber);
        inputFct003Dto.setProductCd(PRODUCT_CODE_01);
        inputFct003Dto.setTradeCd(dtoReq.getTradeCd());
        OutputFct003Dto outputFct003Dto;
        outputFct003Dto = fct003.doCheck(inputFct003Dto);
        for (OutputFct003Dto.MediatePropriety mediatePropriety : outputFct003Dto.getMediateProprietyList()) {
            if (!StringUtils.equals(INTERMEDIARY_VALUE_1, mediatePropriety.getMediatePropriety())) {
                dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                        IfaCommonUtil.getMessage(ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE, new String[] {
                                codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_2) }));
                return dtoRes;
            }
        }

        // ユーザ共通情報.CCSログイン用IDのチェックを行う。
        if (StringUtil.isNullOrEmpty(IfaCommonUtil.getUserAccount().getCcsUserId())) {
            // 未設定(Null または空文字）の場合：取引不可エラーを返す。
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
            ERRORS_CMN_CCSID_UNREGISTERED,
                    IfaCommonUtil.getMessage(ERRORS_CMN_CCSID_UNREGISTERED));
        }
        
        // 顧客共通情報取得
        String kokyakuId = customerCommon.getCustomerCode();
        String tokuteiKouzaKbn = customerCommon.getSpecificAccountType();
        
        // ユーザ共通情報取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String ccsUserId = userAccount.getMedUsers().getCcsUserId();
        
        // ３．発注前に注文取消内容を国内株式注文テーブルへ記録する。
        IfaReceiptDeliveryOrderCancelConfirmSql003RequestModel sql003Req = new IfaReceiptDeliveryOrderCancelConfirmSql003RequestModel();
        sql003Req.setEcOrderNo(dtoReq.getEcOrderNo());
        sql003Req.setButenCode(butenCode);
        sql003Req.setOrderDayTime(dtoReq.getOrderDayTime());
        sql003Req.setAccountNumber(accountNumber);
        sql003Req.setKokyakuId(kokyakuId);
        sql003Req.setTokuteiKouzaKbn(tokuteiKouzaKbn);
        sql003Req.setUserId(ccsUserId);
        DataList<IfaReceiptDeliveryOrderCancelConfirmSql003ResponseModel> sql003ResList = dao.selectIfaReceiptDeliveryOrderCancelConfirmSql003(sql003Req);
        IfaReceiptDeliveryOrderCancelConfirmSql003ResponseModel sql003Res = sql003ResList.getDataList().get(0);
        
        IfaReceiptDeliveryOrderCancelConfirmSql001RequestModel insSql001Req = new IfaReceiptDeliveryOrderCancelConfirmSql001RequestModel();
        BeanUtils.copyProperties(insSql001Req, sql003Res);
        String userId = userAccount.getMedUsers().getUserId();
        insSql001Req.setBrandCode(dtoReq.getBrandCode());
        insSql001Req.setMarket(dtoReq.getMarket());
        insSql001Req.setTradeCd(dtoReq.getTradeCd());
        insSql001Req.setUnTradeQuantity(dtoReq.getUnTradeQuantity());
        insSql001Req.setDepositType(dtoReq.getDepositType());
        insSql001Req.setPaymentDeadline(dtoReq.getPaymentDeadline());
        insSql001Req.setTorikeshiUserId(ccsUserId);
        insSql001Req.setEcOrderNo(dtoReq.getEcOrderNo());
        insSql001Req.setBrokerCode(customerCommon.getBrokerCode());
        insSql001Req.setBrokerChargeCode(customerCommon.getBrokerChargeCode());

        String dailyCreditKbn = "";
        // リクエスト.弁済期限が日計り(D)の場合は、'1'
        if (PAYMENT_DDEADLINE_D.equals(dtoReq.getPaymentDeadline())){
            dailyCreditKbn = DAILY_CREDIT_KBN_1;
        // リクエスト.弁済期限が日計りH(E)の場合は、'2'
        } else if (PAYMENT_DDEADLINE_E.equals(dtoReq.getPaymentDeadline())) {
            dailyCreditKbn = DAILY_CREDIT_KBN_2;
        // リクエスト.弁済期限が上記以外の場合は、'△'
        } else {
            dailyCreditKbn = DAILY_CREDIT_KBN_OTHER;
        }
        insSql001Req.setDailyCreditKbn(dailyCreditKbn); // 一日信用区分
        insSql001Req.setPaymentDeadlineDate(dtoReq.getPaymentDeadlineDate()); // 弁済期限年月日数
        insSql001Req.setDateKbn(dtoReq.getDateKbn()); // 年月日区分

        insSql001Req.setComIdR(dtoReq.getComIdR());
        insSql001Req.setCreateUser(userId);
        insSql001Req.setUpdateUser(userId);
        try {
            dao.insertIfaReceiptDeliveryOrderCancelConfirmSql001(insSql001Req);
        } catch (Exception e) {
            // DB登録NG：DB登録エラーを返す。
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERROR_FRS_PREORDEREXECUTION_FAILED, IfaCommonUtil.getMessage(ERROR_FRS_PREORDEREXECUTION_FAILED));
            return dtoRes;
        }
        
        sql002Req.setIfaOrderNo(sql003Res.getIfaOrderNo());
        sql002Req.setIfaOrderSubNo(sql003Res.getIfaOrderSubNo());
        
        dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, RtnCdEnum.SUCCESS.getText(), "");

        return dtoRes;
    }

    /**
     * アクションID：A002b
     * アクション名：注文発注
     * Dto リクエスト：IfaReceiptDeliveryOrderCancelConfirmA002RequestDto
     * Dto レスポンス：IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Transactional(rollbackFor = Exception.class)
    public DataList<IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto> orderPlacementA002b(
            IfaReceiptDeliveryOrderCancelConfirmA002RequestDto dtoReq)
            throws Exception {
        
        IfaReceiptDeliveryOrderCancelConfirmSql002RequestModel sql002Req = new IfaReceiptDeliveryOrderCancelConfirmSql002RequestModel();
        DataList<IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto> a002aRes = orderPlacementA002a(dtoReq, sql002Req);
        if (a002aRes.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            // エラーの場合は返却
            return a002aRes;
        }
        
        DataList<IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto> dtoRes = new DataList<IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto>();
        List<IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto> dtoResList = dtoRes.getDataList();
        IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto a002bRes = new IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto();
        dtoResList.add(a002bRes);
        
        final CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        final UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // ４．注文取消発注を行う。
        MgRcptDeliverCancelOrderInData api002InData = new MgRcptDeliverCancelOrderInData();
        api002InData.setTransactionId(
                String.format("%32s", StringUtil.EMPTY_STRING).replaceAll(SPACE, ZERO));
        api002InData
                .setCommandNum(String.format("%7s", StringUtil.EMPTY_STRING).replaceAll(SPACE, ZERO));
        api002InData.setCommandDate(
                String.format("%8s", StringUtil.EMPTY_STRING).replaceAll(SPACE, ZERO));
        api002InData.setButenCd(customerCommon.getButenCode());
        api002InData.setKozaNo(AccountUtil.formatToApi(customerCommon.getAccountNumber()));
        api002InData.setAccountId(API_REQUEST_ORDER_ID_ACCOUNT_ID);
        api002InData.setNumber(String.format("%7s", StringUtil.EMPTY_STRING).replaceAll(SPACE, ZERO));
        api002InData.setOrigin(ZERO);
        api002InData.setOrderType(ORDER_TYPE);
        api002InData.setOrderNum(dtoReq.getEcOrderNo());
        api002InData.setCxlCallcenterKbn(ZERO);
        api002InData.setCxlUserId(userAccount.getCcsUserId());
        api002InData.setIpAddress(IP_ADDRESS);
        
        MgRcptDeliverCancelOrderIn api002In = new MgRcptDeliverCancelOrderIn();
        api002In.setIndata(api002InData);
        MgRcptDeliverCancelOrderOutData api002Res = new MgRcptDeliverCancelOrderOutData();
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();

        try {
            api002Res = nriApiService.mgRcptDeliverCancelOrder(api002InData);
            apiErrorUtil.checkApiResponse(api002Res.getShubetu(), api002Res.getCode(), api002Res.getMessage());

        } catch (Exception e) {
            // システムエラーの場合、受注日をDBに登録してエラーレスポンスを返却する。
            sql002Req.setUpdateUser(userAccount.getUserId());
            try {
                dao.updateIfaReceiptDeliveryOrderCancelConfirmSql002b(sql002Req);

            } finally {
                dtoRes = IfaCommonUtil.createDataList(
                        Collections.emptyList(),
                        ErrorLevel.FATAL,
                        ERRORS_CMN_ORDEREXECUTIONCANCEL_FAILED,
                        IfaCommonUtil.getMessage(ERRORS_CMN_ORDEREXECUTIONCANCEL_FAILED)
                );
                return dtoRes;
            }
        }

        // APIエラー判定
        Boolean isApiError = apiErrorUtil.isFatal();
        // 注文取消内容の登録
        ErrorLevel el = ErrorLevel.SUCCESS;
        // エラーメッセージ
        String errorMessage = StringUtil.EMPTY_STRING;
        // エラーコード
        String errorMessageId = StringUtil.EMPTY_STRING;
        
        // ５．発注後に注文取消内容を国内株式注文テーブルへ記録する。
        if (!isApiError) {
            sql002Req.setOrderTime(api002Res.getAcceptTime());
        }
        sql002Req.setOrderDate(api002Res.getAcceptDate());
        sql002Req.setShubetu(api002Res.getShubetu());
        sql002Req.setCode(api002Res.getCode());
        sql002Req.setErrMessage(api002Res.getMessage());
        sql002Req.setUpdateUser(userAccount.getUserId());
        try {
            dao.updateIfaReceiptDeliveryOrderCancelConfirmSql002(sql002Req);
        } catch (Exception e) {
            // DB登録NG：DB登録エラーを格納し次の処理へ。
            LOGGER.debug("IfaReceiptDeliveryOrderCancelConfirmServiceImpL.orderPlacementA002b update Exception[{}]",
                    e.getMessage());
            errorMessageId = WARNINGS_CMN_POSTORDEREXECUTIONCANCEL_COMPLETED;
            apiErrorUtil.addDbError(errorMessageId, null);
        }
        
        // ６．APIエラー：エラーを表示する。
        if (isApiError) {
            el = ErrorLevel.FATAL;
            errorMessageId = ERRORS_CMN_ORDEREXECUTIONCANCEL_FAILED;
            errorMessage = IfaCommonUtil.getMessage(ERRORS_CMN_ORDEREXECUTIONCANCEL_FAILED,
                    new String[] { api002Res.getCode(), api002Res.getMessage() });
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), el, errorMessageId, errorMessage);
            return dtoRes;
        }
        
        BeanUtils.copyProperties(a002bRes, dtoReq);
        a002bRes.setOrderDayTime(api002Res.getAcceptDate() + SPACE + api002Res.getAcceptTime());
        
        dtoRes = apiErrorUtil.createDataList(dtoResList, "");
        return dtoRes;
    }
    
}
