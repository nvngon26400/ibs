package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

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
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMarginNewOrderCorrectConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCorrectConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCorrectConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCorrectConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCorrectConfirmSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectConfirmA001aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectConfirmA001aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectConfirmA001bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectConfirmA001bDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMarginNewOrderCorrectConfirmService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.RtnCdEnum;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sbisec.pcenter.dto.yanap.StockModifyOrderAutoIn;
import jp.co.sbisec.pcenter.dto.yanap.StockModifyOrderAutoInData;
import jp.co.sbisec.pcenter.dto.yanap.StockModifyOrderAutoOutData;
import jp.co.sbisec.pcenter.dto.yanap.StockModifyOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.StockModifyOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.StockModifyOrderOutData;

/**
 * 画面ID：SUB0202_0212-02_2
 * 画面名：信用新規注文訂正確認
 *
 * @author SCSK
    2024/04/16 新規作成
 */
@Component(value = "cmpIfaMarginNewOrderCorrectConfirmService")
public class IfaMarginNewOrderCorrectConfirmServiceImpL implements IfaMarginNewOrderCorrectConfirmService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMarginNewOrderCorrectConfirmServiceImpL.class);
    
    /** API001:商品区分:固定値 */
    private static final String API001_ORDER_TYPE = "S";
    
    /** API001:訂正区分:固定値 */
    private static final String API001_MODIFY_TYPE = "PRICE   ";
    
    /** API001:受付経路区分:固定値 */
    private static final String API001_CALLCENTER_KBN = "0";
    
    /** API001:チケット出力区分:固定値 */
    private static final String API001_TICKET_ID = " ";
    
    /** API001:余力チェック区分:固定値 */
    private static final String API001_CHECK_ID = " ";
    
    /** API001:媒介:固定値 */
    private static final String API001_INTERMEDIARY = "1";
    
    /** API001:IPアドレス:固定値 */
    private static final String API001_IP_ADDRESS = "ifap";
    
    /** API002:オリジン:固定値 */
    private static final String API002_ORIGIN = "0";
    
    /** API002:商品区分:固定値 */
    private static final String API002_ORDER_TYPE = "S";
    
    /** API002:訂正区分:固定値 */
    private static final String API002_MODIFY_TYPE = "PRICE   ";
    
    /** API002:受付経路区分:固定値 */
    private static final String API002_CALLCENTER_KBN = "0";
    
    /** API002:チケット出力区分:固定値 */
    private static final String API002_TICKET_ID = " ";
    
    /** API002:余力チェック区分:固定値 */
    private static final String API002_CHECK_ID = " ";
    
    /** API002:媒介:固定値 */
    private static final String API002_INTERMEDIARY = "1";
    
    /** API002:IPアドレス:固定値 */
    private static final String API002_IP_ADDRESS = "ifap";
    
    /** API002:自動注文種別 */
    private static final String API002_AUTO_ORDER_CLASS = "IF  ";
    
    /** 媒介可否が"1"（媒介可） */
    private static final String INTERMEDIARY_VALUE_1 = "1";
    
    /** 権限なしエラー */
    private static final String NO_AUTHORIZED = "0";
    
    /** 取引停止口座エラー */
    private static final String TRADE_SUSPEND_FLAG = "1";
    
    /** 証券金銭種別(国内株式) */
    private static final String PRODUCT_CODE_01 = "01";
    
    /** 区分.対象取引（メッセージ表示用）（区分値：2　＠表示パターン：1　） */
    private static final String MSG_DISPLAY_TARGET_TRADE_2 = "2";
    
    /** 入力した部店口座は存在しません */
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    /** 取引停止口座エラー */
    private static final String ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** {0}ができないコースです。 */
    private static final String ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 信用口座が未開設です。 */
    private static final String ERRORS_CMN_DOMESTICMARGINACCOUNT_NOTOPEN = "errors.dms.domesticMarginAccount.notOpen";
    
    /** 当該顧客の{0}に関わる重要な注意情報があるため処理を進めることができません。注意情報欄をご確認ください。 */
    private static final String ERRORS_CMN_NOTICEERRORCHECK = "errors.cmn.noticeErrorCheck";
    
    /** 未確認の「重要なお知らせ」があります。 */
    private static final String ERRORS_INFORMATIONCHECK = "errors.informationCheck";
    
    /** 確認が必要なアラートが新たに発生しました。注文入力画面に戻り再度注文確認を行ってください。 */
    private static final String ERRORS_CMN_INFORMATION_OCCURS = "errors.cmn.information.occurs";
    
    private static final String SPACE = " ";
    
    /** FCT021 国籍コード */
    private static final String COUNTRYCD_99 = "99";
    
    /** FCT021 通貨コード */
    private static final String CURRENCYCODE_999 = "999";
    
    /** FCT027 規制銘柄区分=1:規制銘柄 */
    private static final String REGKBN_1 = "1";
    
    private static final String TRUE = "1";
    
    /** 区分.注文種別:通常/逆指値 */
    private static final String ORDER_CLASS_1 = "1";
    
    /** 区分.注文種別:OCO */
    private static final String ORDER_CLASS_2 = "2";
    
    /** 区分.注文種別:IFD */
    private static final String ORDER_CLASS_3 = "3";
    
    /** 区分.注文種別:IFDOCO */
    private static final String ORDER_CLASS_4 = "4";
    
    /** RBE注文種別 'SLO'：逆指値注文 */
    private static final String RBE_ORDER_KIND_SLO = "SLO";
    
    /** RBE注文種別 オール'△'：通常注文 */
    private static final String RBE_ORDER_KIND_SPACE3 = "   ";
    
    /** RBE注文種別 'OCO'：OCO注文 */
    private static final String RBE_ORDER_KIND_OCO = "OCO";
    
    /** 執行方法:指値 */
    private static final String EXECUTE_METHOD_1 = "1";
    
    /** 執行方法:成行 */
    private static final String EXECUTE_METHOD_2 = "2";
    
    /** 執行方法:逆指値 */
    private static final String EXECUTE_METHOD_3 = "3";
    
    /** 信用新規買 */
    private static final String DOMESTIC_STOCK_TRADE_CLASS_BUY = "3";
    
    /** 信用新規売 */
    private static final String DOMESTIC_STOCK_TRADE_CLASS_SELL = "4";
    
    /** API:トリガ発動ゾーン 以上 */
    private static final String TRIGGER_ZONE_OVER = "0";
    
    /** API:トリガ発動ゾーン 以下 */
    private static final String TRIGGER_ZONE_UNDER = "1";
    
    /** API:トリガ発動ゾーン 指定無し */
    private static final String TRIGGER_ZONE_NONE = " ";
    
    /** sql001.注文状況 */
    private static final String SQL001_ORDER_STATUS = "1";
    
    /** 自動注文種別:IFD子注文 */
    private static final String IFD_SON_ORDER = "DONE";
    
    private static final String ERROR_FRS_PREORDEREXECUTION_FAILED = "errors.frs.preOrderExecution.failed";
    
    /** 注文発注後の注文データが更新できませんでした。注文は完了しています。 */
    private static final String WARNINGS_FRS_POST_ORDER_EXECUTION_COMPLETED = "warnings.frs.postOrderExecution.completed";
    
    /** 注文訂正処理でエラーが発生しました。(エラーコード：{0}、エラーメッセージ{1}) */
    private static final String ERRORS_CMN_ORDER_EXECUTION_MODIFY_FAILED = "errors.cmn.orderExecutionModify.failed";
    
    /** SOR対象外で発注されました。注文は完了しています。 */
    private static final String WARNINGS_FRS_OUT_OF_SOR_ORDER_COMPLETED = "warnings.frs.outOfSOROrder.completed";
    
    private static final String ORDER_STATUS_LIST_ORDER_CLASS_ZERO = "0";
    
    private static final String ORDER_STATUS_LIST_ORDER_CLASS_ONE = "1";
    
    private static final String ORDER_STATUS_LIST_ORDER_CLASS_TWO = "2";
    
    private static final String ORDER_STATUS_LIST_ORDER_CLASS_THREE = "3";
    
    private static final String ORDER_STATUS_LIST_ORDER_CLASS_FOUR = "4";
    
    private static final String ORDER_STATUS_LIST_ORDER_CLASS_FIVE = "5";
    
    private static final String ORDER_STATUS_LIST_ORDER_CLASS_SIX = "6";
    
    private static final String ORDER_STATUS_LIST_ORDER_CLASS_SEVEN = "7";
    
    private static final String SECURITY_TYPE_S = "S";
    
    private static final String TEISEI_KBN_1 = "1";
    
    private static final String WARNING = "W";
    
    private static final char CHAR_ZERO = '0';
    
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    private static final String API_OCO_PRICE_NONE = "          ";

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
    
    /** 訂正SOR注文区分：訂正SOR */
    private static final String CORRECT_SOR_ORDER_CLASSFICATION_SOR = "1";

    /** 選択市場：当社優先市場／SOR */
    private static final String SELECT_MARKET_SOR = "A";
    
    /** API001 リクエスト（訂正SOR注文区分）：訂正SOR"1" */
    private static final String API001_SOR_MODIFY_CODE_CORRECT_SOR = "1";
    
    /** API001 リクエスト（訂正SOR注文区分）：通常訂正" " */
    private static final String API001_SOR_MODIFY_CODE_NORMAL = " ";



    /**
     * FCT001 利用者顧客参照権限チェック
     */
    @Autowired
    private Fct001 fct001;
    
    /**
     * FCT003 取引コース媒介可否チェック
     */
    @Autowired
    private Fct003 fct003;
    
    /**
     * FCT021 取引制限チェック
     */
    @Autowired
    private Fct021 fct021;
    
    /**
     * FCT027 国内株式情報取得
     */
    @Autowired
    private Fct027 fct027;
    
    @Autowired
    private CodeListService codeListService;
    
    /**
     * NRI_APIラッパー
     */
    @Autowired
    private ApiWrapper apiWrapper;
    
    @Autowired
    private IfaMarginNewOrderCorrectConfirmDao dao;

    /**
     * アクションID：A001a
     * アクション名：訂正発注
     * Dto リクエスト：IfaMarginNewOrderCorrectConfirmA001DtoRequest
     * Dto レスポンス：IfaMarginNewOrderCorrectConfirmA001DtoResponse
     * model リクエスト：IfaMarginNewOrderCorrectConfirmSql002RequestModel
     * model レスポンス：IfaMarginNewOrderCorrectConfirmSql002ResponseModel
     *
     * @return A001aレスポンス
     * @exception Exception error
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public DataList<IfaMarginNewOrderCorrectConfirmA001aDtoResponse> correctionOrderA001a(
            IfaMarginNewOrderCorrectConfirmA001aDtoRequest dtoReq)
            throws Exception {
        
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        UserAccount ua = IfaCommonUtil.getUserAccount();
        DataList<IfaMarginNewOrderCorrectConfirmA001aDtoResponse> dtoRes = new DataList<IfaMarginNewOrderCorrectConfirmA001aDtoResponse>();
        List<IfaMarginNewOrderCorrectConfirmA001aDtoResponse> dtoResList = dtoRes.getDataList();
        IfaMarginNewOrderCorrectConfirmA001aDtoResponse a001aRes = new IfaMarginNewOrderCorrectConfirmA001aDtoResponse();
        dtoResList.add(a001aRes);
        
        BeanUtils.copyProperties(a001aRes, dtoReq);
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginNewOrderCorrectConfirmServiceImplL.correctionOrderA001a");
        }
        
        // １．利用者の口座に対する権限チェックを行う。
        // 顧客共通情報取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        String butenCode = customerCommon.getButenCode();
        String accountNumber = customerCommon.getAccountNumber();
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
                        IfaCommonUtil.getMessage(ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                                new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE,
                                        MSG_DISPLAY_TARGET_TRADE_2) }));
                return dtoRes;
            }
        }
        
        // ３．顧客共通情報.信用口座区分（国内）より、国内信用口座開設状況のチェックを行う。
        if (SPACE.equals(customerCommon.getDomesticMarginAccountType())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_CMN_DOMESTICMARGINACCOUNT_NOTOPEN,
                    IfaCommonUtil.getMessage(ERRORS_CMN_DOMESTICMARGINACCOUNT_NOTOPEN));
            return dtoRes;
        }
        
        // ４．口座の取引制限チェックを行う。
        InputFct021Dto inputFct021Dto = new InputFct021Dto();
        inputFct021Dto.setButenCode(butenCode);
        inputFct021Dto.setAccountNumber(accountNumber);
        inputFct021Dto.setProductCd(PRODUCT_CODE_01);
        inputFct021Dto.setTradeCd(dtoReq.getTradeCd());
        inputFct021Dto.setCountryCd(COUNTRYCD_99);
        inputFct021Dto.setCurrencyCode(CURRENCYCODE_999);
        inputFct021Dto.setTradeRestrictChkMarket(dtoReq.getAfterCorrectMarket());
        inputFct021Dto.setPaymentLimit(dtoReq.getPaymentDeadline());
        
        OutputFct021Dto outputFct021Dto = fct021.doCheck(inputFct021Dto);
        // 注意情報エラー有無="1"（あり）：エラーを返す。
        if (outputFct021Dto.getNoteInfoErrFlag().equals(Fct021.EXIST)) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_CMN_NOTICEERRORCHECK,
                    IfaCommonUtil.getMessage(ERRORS_CMN_NOTICEERRORCHECK, new String[] {
                            codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_2) }));
            return dtoRes;
        }
        // お知らせエラー有無="1"（あり）：エラーを返す。
        if (outputFct021Dto.getNoteLimitErrFlag().equals(Fct021.EXIST)) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, ERRORS_INFORMATIONCHECK,
                    IfaCommonUtil.getMessage(ERRORS_INFORMATIONCHECK));
            return dtoRes;
        }
        // 注意情報アラート有無="1"（あり）：
        if (outputFct021Dto.getNoteInfoAlertFlag().equals(Fct021.EXIST)) {
            // 注意情報アラート確認チェックボックス=OFF　または　非表示：エラーを返す。
            if (!TRUE.equals(dtoReq.getNoticeInfoAlertConfirm())) {
                dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        ERRORS_CMN_INFORMATION_OCCURS, IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_OCCURS));
                return dtoRes;
            }
        }
        // お知らせアラート有無="1"（あり）：
        if (outputFct021Dto.getNoteLimitAlertFlag().equals(Fct021.EXIST)) {
            // お知らせアラート確認チェックボックス=OFF　または　非表示：エラーを返す。
            if (!TRUE.equals(dtoReq.getNoticeAlertConfirm())) {
                dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        ERRORS_CMN_INFORMATION_OCCURS, IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_OCCURS));
                return dtoRes;
            }
        }
        
        // ５．銘柄の取引注意情報を取得する。
        InputFct027Dto inputFct027Dto = new InputFct027Dto();
        inputFct027Dto.setBrandCode(dtoReq.getBrandCode());
        OutputFct027Dto outputFct027Dto = fct027.getData(inputFct027Dto);
        // 規制銘柄区分=1:規制銘柄　の場合
        if (outputFct027Dto.getRegKbn().equals(REGKBN_1)) {
            // 取引注意情報（銘柄）確認チェックボックス=OFF　または　非表示：エラーを返す。
            if (!TRUE.equals(dtoReq.getTradeNoticeInfoBrandConfirm())) {
                dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        ERRORS_CMN_INFORMATION_OCCURS, IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_OCCURS));
                return dtoRes;
            }
        }
        
        // ６．発注前に注文訂正内容を国内株式注文テーブルへ記録する。
        // SQL003 登録前のレコードチェック
        IfaMarginNewOrderCorrectConfirmSql003RequestModel sql003Req = new IfaMarginNewOrderCorrectConfirmSql003RequestModel();
        sql003Req.setEcOrderNo(dtoReq.getEcOrderNo());
        sql003Req.setButenCode(butenCode);
        sql003Req.setAccountNumber(accountNumber);
        sql003Req.setInputDate(dtoReq.getInputDate());
        sql003Req.setKokyakuId(customerCommon.getCustomerCode());
        sql003Req.setTokuteiKouzaKbn(customerCommon.getSpecificAccountType());
        sql003Req.setCheckSor(dtoReq.getCheckSor());
        
        DataList<IfaMarginNewOrderCorrectConfirmSql003ResponseModel> sql003ResList = dao
                .selectIfaMarginNewOrderCorrectConfirmSql003(sql003Req);
        IfaMarginNewOrderCorrectConfirmSql003ResponseModel sql003Res = sql003ResList.getDataList().get(0);
        
        String orderStatusListOrderClass = null;
        if (IFD_SON_ORDER.equals(dtoReq.getDoneState())) {
            if (ORDER_CLASS_1.equals(dtoReq.getOrderKind())) {
                if (EXECUTE_METHOD_3.equals(dtoReq.getSasinariHouhou())) {
                    orderStatusListOrderClass = ORDER_STATUS_LIST_ORDER_CLASS_SIX;
                } else {
                    orderStatusListOrderClass = ORDER_STATUS_LIST_ORDER_CLASS_FIVE;
                }
            }
            if (ORDER_CLASS_2.equals(dtoReq.getOrderKind())) {
                orderStatusListOrderClass = ORDER_STATUS_LIST_ORDER_CLASS_SEVEN;
            }
        } else {
            if (ORDER_CLASS_1.equals(dtoReq.getOrderKind())) {
                if (EXECUTE_METHOD_3.equals(dtoReq.getSasinariHouhou())) {
                    orderStatusListOrderClass = ORDER_STATUS_LIST_ORDER_CLASS_ONE;
                } else {
                    orderStatusListOrderClass = ORDER_STATUS_LIST_ORDER_CLASS_ZERO;
                }
            }
            if (ORDER_CLASS_2.equals(dtoReq.getOrderKind())) {
                orderStatusListOrderClass = ORDER_STATUS_LIST_ORDER_CLASS_TWO;
            }
            if (ORDER_CLASS_3.equals(dtoReq.getOrderKind()) || ORDER_CLASS_4.equals(dtoReq.getOrderKind())) {
                if (EXECUTE_METHOD_3.equals(dtoReq.getIfd1SasinariHouhou())) {
                    orderStatusListOrderClass = ORDER_STATUS_LIST_ORDER_CLASS_FOUR;
                } else {
                    orderStatusListOrderClass = ORDER_STATUS_LIST_ORDER_CLASS_THREE;
                }
            }
        }
        if (ORDER_CLASS_1.equals(dtoReq.getOrderKind()) || ORDER_CLASS_2.equals(dtoReq.getOrderKind())) {
            IfaMarginNewOrderCorrectConfirmSql001RequestModel insSql001Req = new IfaMarginNewOrderCorrectConfirmSql001RequestModel();
            BeanUtils.copyProperties(insSql001Req, sql003Res);
            
            insSql001Req.setBrandCode(dtoReq.getBrandCode());
            insSql001Req.setMarket(dtoReq.getAfterCorrectMarket());
            insSql001Req.setOrderStatus(SQL001_ORDER_STATUS);
            insSql001Req.setTradeCd(dtoReq.getTradeCd());
            insSql001Req.setQuantity(dtoReq.getQuantity());
            insSql001Req.setYukoKigen(dtoReq.getPeriod());
            insSql001Req.setPaymentDeadline(dtoReq.getPaymentDeadline());
            insSql001Req.setOrderKind(dtoReq.getOrderKind());
            insSql001Req.setOrderStatusListOrderClass(orderStatusListOrderClass);
            StockModifyOrderInData api001InData = setApi001Req(dtoReq);
            insSql001Req.setKanyuKbn(dtoReq.getKanyuKbn());
            insSql001Req.setReceiveOrder(dtoReq.getOrderMethod());
            insSql001Req.setCheckInsider(dtoReq.getCheckInsiderConfirmCheckBox());
            insSql001Req.setUserId(api001InData.getUserId());
            // 手数料区分 外部コード変換
            insSql001Req
                    .setTesuuryouKbn(codeListService.convertKeyToExtKey("COMM_TYPE", "EC-GW", api001InData.getComId()));
            insSql001Req.setTeiseiKbn(TEISEI_KBN_1);
            insSql001Req.setRbeChumonShubetsu(api001InData.getRbeOrderKind());
            insSql001Req.setSasinariKbn(api001InData.getSasinariKbn());
            insSql001Req.setSashine(api001InData.getPrice());
            String triggerZone = TRIGGER_ZONE_NONE;
            String triggerPrice = "0000000000";
            if (ORDER_CLASS_1.equals(dtoReq.getOrderKind())) {
                // ■注文種別=通常/逆指値の場合
                if (EXECUTE_METHOD_3.equals(dtoReq.getSasinariHouhou())) {
                    // 　■通常/逆指値.執行方法=逆指値　の場合
                    if (DOMESTIC_STOCK_TRADE_CLASS_BUY.equals(dtoReq.getTradeCd())) {
                        // 取引種別=信用新規買
                        triggerZone = TRIGGER_ZONE_OVER;
                    } else if (DOMESTIC_STOCK_TRADE_CLASS_SELL.equals(dtoReq.getTradeCd())) {
                        // 取引種別=信用新規売
                        triggerZone = TRIGGER_ZONE_UNDER;
                    }
                    triggerPrice = dtoReq.getTriggerPrice();
                } 
            } else {
                if (DOMESTIC_STOCK_TRADE_CLASS_BUY.equals(dtoReq.getTradeCd())) {
                    // 取引種別=信用新規買
                    triggerZone = TRIGGER_ZONE_OVER;
                } else if (DOMESTIC_STOCK_TRADE_CLASS_SELL.equals(dtoReq.getTradeCd())) {
                    // 取引種別=信用新規売
                    triggerZone = TRIGGER_ZONE_UNDER;
                }
                triggerPrice = dtoReq.getOco2TriggerPrice();
            }
            insSql001Req.setTriggerZone(triggerZone);
            insSql001Req.setTriggerNedan(triggerPrice);
            insSql001Req.setOcoSasinariKbn(api001InData.getOcoSasinariKbn());
            if (!api001InData.getOcoPrice().isBlank()) {
                insSql001Req.setOcoSashine(api001InData.getOcoPrice());
            }
            insSql001Req.setDoneYuukouKigen(dtoReq.getIfd2Limit());
            insSql001Req.setEc(dtoReq.getEcOrderNo());
            insSql001Req.setRbeOrderStand(dtoReq.getRbeOrderStatus());
            insSql001Req.setSecurityType(SECURITY_TYPE_S);
            insSql001Req.setEcOrderNo(dtoReq.getEcOrderNo());
            
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

            insSql001Req.setBrokerCode(cc.getBrokerCode());
            insSql001Req.setBrokerChargeCode(cc.getBrokerChargeCode());
            insSql001Req.setCreateUser(ua.getUserId());
            insSql001Req.setUpdateUser(ua.getUserId());
            
            try {
                dao.insertIfaMarginNewOrderCorrectConfirmSql001(insSql001Req);
            } catch (Exception e) {
                // DB登録NG：DB登録エラーを返す。
                dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        ERROR_FRS_PREORDEREXECUTION_FAILED,
                        IfaCommonUtil.getMessage(ERROR_FRS_PREORDEREXECUTION_FAILED));
                return dtoRes;
            }
            
            a001aRes.setIfaOrderNo(sql003Res.getIfaOrderNo());
            a001aRes.setIfaOrderSubNo(sql003Res.getIfaOrderSubNo());
            
        }
        if (ORDER_CLASS_3.equals(dtoReq.getOrderKind()) || ORDER_CLASS_4.equals(dtoReq.getOrderKind())) {
            IfaMarginNewOrderCorrectConfirmSql001RequestModel insSql001Req = new IfaMarginNewOrderCorrectConfirmSql001RequestModel();
            BeanUtils.copyProperties(insSql001Req, sql003Res);
            
            insSql001Req.setBrandCode(dtoReq.getBrandCode());
            insSql001Req.setMarket(dtoReq.getAfterCorrectMarket());
            insSql001Req.setOrderStatus(SQL001_ORDER_STATUS);
            insSql001Req.setTradeCd(dtoReq.getTradeCd());
            insSql001Req.setQuantity(dtoReq.getQuantity());
            insSql001Req.setYukoKigen(dtoReq.getPeriod());
            insSql001Req.setPaymentDeadline(dtoReq.getPaymentDeadline());
            insSql001Req.setOrderKind(dtoReq.getOrderKind());
            insSql001Req.setOrderStatusListOrderClass(orderStatusListOrderClass);
            StockModifyOrderAutoInData api002InData = setApi002Req(dtoReq);
            insSql001Req.setKanyuKbn(dtoReq.getKanyuKbn());
            insSql001Req.setReceiveOrder(dtoReq.getOrderMethod());
            insSql001Req.setCheckInsider(dtoReq.getCheckInsiderConfirmCheckBox());
            insSql001Req.setUserId(api002InData.getUserId());
            insSql001Req
                    .setTesuuryouKbn(codeListService.convertKeyToExtKey("COMM_TYPE", "EC-GW", api002InData.getComId()));
            insSql001Req.setTeiseiKbn(TEISEI_KBN_1);
            insSql001Req.setAutoOrderClass(api002InData.getAutoOrderKind());
            insSql001Req.setRbeChumonShubetsu(api002InData.getRbeOrderKind());
            insSql001Req.setSasinariKbn(api002InData.getSasinariKbn());
            insSql001Req.setSashine(api002InData.getPrice());
            String triggerPrice = "0000000000";
            String triggerZone = TRIGGER_ZONE_NONE;
            if (EXECUTE_METHOD_3.equals(dtoReq.getIfd1SasinariHouhou())) {
                if (DOMESTIC_STOCK_TRADE_CLASS_BUY.equals(dtoReq.getTradeCd())) {
                    // 取引種別=信用新規買
                    triggerZone = TRIGGER_ZONE_OVER;
                } else if (DOMESTIC_STOCK_TRADE_CLASS_SELL.equals(dtoReq.getTradeCd())) {
                    // 取引種別=信用新規売
                    triggerZone = TRIGGER_ZONE_UNDER;
                }
                triggerPrice = dtoReq.getIfd1TriggerPrice();
            }
            insSql001Req.setTriggerZone(triggerZone);
            insSql001Req.setTriggerNedan(triggerPrice);
            insSql001Req.setOcoSasinariKbn(api002InData.getOcoSasinariKbn());
            if (!api002InData.getOcoPrice().isBlank()) {
                insSql001Req.setOcoSashine(api002InData.getOcoPrice());
            }
            insSql001Req.setDoneSasinariKbn(api002InData.getDoneSasinariKbn());
            insSql001Req.setDoneSashine(api002InData.getDonePrice());
            insSql001Req.setDoneRbeOrderKind(api002InData.getDoneRbeOrderKind());
            insSql001Req.setDoneTriggerZone(api002InData.getDoneTriggerZone());
            insSql001Req.setDoneTriggerNedan(api002InData.getDoneTriggerPrice());
            insSql001Req.setDoneOcoSasinariKbn(api002InData.getDoneOcoSasinariKbn());
            insSql001Req.setDoneOcoSashine(api002InData.getDoneOcoPrice());
            insSql001Req.setDoneYuukouKigen(dtoReq.getIfd2Limit());
            insSql001Req.setEc(dtoReq.getEcOrderNo());
            insSql001Req.setRbeOrderStand(dtoReq.getRbeOrderStatus());
            insSql001Req.setSecurityType(SECURITY_TYPE_S);
            insSql001Req.setEcOrderNo(dtoReq.getEcOrderNo());

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

            insSql001Req.setBrokerCode(cc.getBrokerCode());
            insSql001Req.setBrokerChargeCode(cc.getBrokerChargeCode());
            insSql001Req.setCreateUser(ua.getUserId());
            insSql001Req.setUpdateUser(ua.getUserId());
            
            try {
                dao.insertIfaMarginNewOrderCorrectConfirmSql001(insSql001Req);
            } catch (Exception e) {
                // DB登録NG：DB登録エラーを返す。
                dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        ERROR_FRS_PREORDEREXECUTION_FAILED,
                        IfaCommonUtil.getMessage(ERROR_FRS_PREORDEREXECUTION_FAILED));
                return dtoRes;
            }
            
            a001aRes.setIfaOrderNo(sql003Res.getIfaOrderNo());
            a001aRes.setIfaOrderSubNo(sql003Res.getIfaOrderSubNo());
            
        }
        dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, RtnCdEnum.SUCCESS.getText(), "");
        
        
        return dtoRes;
        
    }

    /**
     * アクションID：A001b
     * アクション名：訂正発注
     * Dto リクエスト：IfaMarginNewOrderCorrectConfirmA001DtoRequest
     * Dto レスポンス：IfaMarginNewOrderCorrectConfirmA001DtoResponse
     * model リクエスト：IfaMarginNewOrderCorrectConfirmSql002RequestModel
     * model レスポンス：IfaMarginNewOrderCorrectConfirmSql002ResponseModel
     *
     * @return A001bレスポンス
     * @exception Exception error
     */
    @Transactional(rollbackFor = Exception.class)
    public DataList<?> correctionOrderA001b(
            IfaMarginNewOrderCorrectConfirmA001bDtoRequest dtoReq) throws Exception {
        IfaMarginNewOrderCorrectConfirmA001aDtoRequest ifaMarginNewOrderCorrectConfirmA001aDtoRequest = new IfaMarginNewOrderCorrectConfirmA001aDtoRequest();
        BeanUtils.copyProperties(ifaMarginNewOrderCorrectConfirmA001aDtoRequest, dtoReq);
        DataList<IfaMarginNewOrderCorrectConfirmA001aDtoResponse> appResa = correctionOrderA001a(ifaMarginNewOrderCorrectConfirmA001aDtoRequest);
        if (appResa.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            // エラーの場合は返却
            return appResa;
        }
        BeanUtils.copyProperties(dtoReq, appResa.getDataList().get(0));
        UserAccount ua = IfaCommonUtil.getUserAccount();
        DataList<IfaMarginNewOrderCorrectConfirmA001bDtoResponse> dtoRes = new DataList<IfaMarginNewOrderCorrectConfirmA001bDtoResponse>();
        List<IfaMarginNewOrderCorrectConfirmA001bDtoResponse> dtoResList = dtoRes.getDataList();
        IfaMarginNewOrderCorrectConfirmA001bDtoResponse a001bRes = new IfaMarginNewOrderCorrectConfirmA001bDtoResponse();
        dtoResList.add(a001bRes);
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginNewOrderCorrectConfirmServiceImplL.correctionOrderA001b");
        }
        
        IfaMarginNewOrderCorrectConfirmSql002RequestModel updSql002Req = new IfaMarginNewOrderCorrectConfirmSql002RequestModel();
        StockModifyOrderOutData api001Result = new StockModifyOrderOutData();
        StockModifyOrderAutoOutData api002Result = new StockModifyOrderAutoOutData();
        
        Boolean isApiError = false;
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 注文訂正内容の登録
        ErrorLevel el = ErrorLevel.SUCCESS;
        // エラーメッセージ
        String errorMessage = StringUtil.EMPTY_STRING;
        // エラーコード
        String errorMessageId = StringUtil.EMPTY_STRING;
        
        if (ORDER_CLASS_1.equals(dtoReq.getOrderKind()) || ORDER_CLASS_2.equals(dtoReq.getOrderKind())) {
            IfaMarginNewOrderCorrectConfirmA001aDtoRequest a001aReq = new IfaMarginNewOrderCorrectConfirmA001aDtoRequest();
            BeanUtils.copyProperties(a001aReq, dtoReq);
            StockModifyOrderInData api001InData = setApi001Req(a001aReq);
            StockModifyOrderIn api001In = new StockModifyOrderIn();
            api001In.setIndata(api001InData);
            try {
                api001Result = apiWrapper.stockModifyOrder(api001In);
                apiErrorUtil.checkApiResponse(api001Result.getShubetu(), api001Result.getCode(), api001Result.getMessage());

            } catch (Exception e) {
                // システムエラーの場合、DBに受注日を登録してエラーレスポンスを返却する
                // IFA注文番号
                updSql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
                // IFA注文サブ番号
                updSql002Req.setIfaOrderSubNo(dtoReq.getIfaOrderSubNo());
                // 更新者
                updSql002Req.setUpdateUser(ua.getUserId());

                try {
                    dao.updateIfaMarginNewOrderCorrectConfirmSql002b(updSql002Req);
    
                } finally {
                    dtoRes = IfaCommonUtil.createDataList(
                            Collections.emptyList(),
                            ErrorLevel.FATAL,
                            ERRORS_CMN_ORDER_EXECUTION_MODIFY_FAILED,
                            IfaCommonUtil.getMessage(ERRORS_CMN_ORDER_EXECUTION_MODIFY_FAILED)
                    );
                    return dtoRes;
                }
            }
            
                     
            // APIエラー判定
            isApiError = apiErrorUtil.isFatal();
            // IFA注文番号
            updSql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
            // IFA注文サブ番号
            updSql002Req.setIfaOrderSubNo(dtoReq.getIfaOrderSubNo());
            // 種別
            updSql002Req.setShubetu(api001Result.getShubetu());
            // エラーコード
            updSql002Req.setCode(api001Result.getCode());
            // エラーメッセージ
            updSql002Req.setErrMessage(api001Result.getMessage());
            // 更新者
            updSql002Req.setUpdateUser(ua.getUserId());
            // 受注日
            updSql002Req.setOrderDate(api001Result.getAcceptDate());
            if (!isApiError) {
                // 受注時刻
                updSql002Req.setOrderTime(api001Result.getAcceptTime());
                // 与信チェック用時価
                updSql002Req.setEstimatePrice(api001Result.getEstimatePrice());
                // 訂正前後の約定金額(概算)差分
                updSql002Req.setAmount(api001Result.getAmount());
                // 手数料（概算）
                updSql002Req.setCommission(api001Result.getCommission());
                // 消費税（概算）
                updSql002Req.setConsumptionTax(api001Result.getConsumptionTax());
                // 譲渡益税（概算）
                updSql002Req.setCapitalGainTax(api001Result.getCapitalGainTax());
                // 精算金額（概算）
                updSql002Req.setNetAmount(api001Result.getNetAmount());
                // 諸経費
                updSql002Req.setCost(api001Result.getCost());
                // 約定予定日
                updSql002Req.setContractDate(api001Result.getTradeDate());
                // 受渡予定日
                updSql002Req.setDeliveryDate(api001Result.getSettlementDate());
                // 受付有効期限
                updSql002Req.setAcceptLimit(api001Result.getModLimit());
                // 手数料区分（採用）
                updSql002Req.setComIdR(api001Result.getComIdR());
                // 売却可能数量
                updSql002Req.setAcPosition(api001Result.getAcPosition());
                // 注文後の売却可能数量
                updSql002Req.setAcPositionAfter(api001Result.getAcPositionAfter());
                // 買付可能金額
                updSql002Req.setAcBalance(api001Result.getAcBalance());
                // 注文後の買付可能金額
                updSql002Req.setAcBalanceAfter(api001Result.getAcBalanceAfter());
                // 取引不足額
                updSql002Req.setTradeDeficitAmount(api001Result.getTradeDeficitAmount());
                // ISA買付可能枠
                updSql002Req.setIsaBuyLimit(api001Result.getIsaBuyLimit().trim());
                // ジュニアNISA振替金額
                updSql002Req.setJrnisaTransferAmount(api001Result.getJrnisaTransferAmount());
                // 決済可能数量
                updSql002Req.setUnclosedQuantity(api001Result.getUnclosedQuantity());
                // 注文後の決済可能数量
                updSql002Req.setUnclosedQuantityAfter(api001Result.getUnclosedQuantityAfter());
                // 建玉余力
                updSql002Req.setMarginCapability(api001Result.getMarginCapability());
                // 注文後の建玉余力
                updSql002Req.setMarginCapabilityAfter(api001Result.getMarginCapabilityAfter());
                // 維持率
                updSql002Req.setCustomerInfoDetentionRate(api001Result.getActualGrntRate().trim());
                // 注文後維持率
                updSql002Req.setActualGrntRateAfter(api001Result.getActualGrntRateAfter().trim());
            }
            try {
                dao.updateIfaMarginNewOrderCorrectConfirmSql002(updSql002Req);
            } catch (Exception e) {
                // DB登録NG：DB登録エラーを返す。 */
                LOGGER.debug("IfaMarginNewOrderCorrectConfirmServiceImplL.correctionOrderA001b update Exception[{}]",
                        e.getMessage());
                el = ErrorLevel.WARNING;
                errorMessageId = WARNINGS_FRS_POST_ORDER_EXECUTION_COMPLETED;
                apiErrorUtil.addDbError(errorMessageId, null);
            }
            
            if (isApiError) {
                el = ErrorLevel.FATAL;
                errorMessageId = ERRORS_CMN_ORDER_EXECUTION_MODIFY_FAILED;
                errorMessage = IfaCommonUtil.getMessage(ERRORS_CMN_ORDER_EXECUTION_MODIFY_FAILED,
                        new String[] { api001Result.getCode(), api001Result.getMessage() });
                dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), el, errorMessageId, errorMessage);
                return dtoRes;
            } else {
                a001bRes.setContractDate(api001Result.getTradeDate());
                a001bRes.setDeliveryDate(api001Result.getSettlementDate());
                a001bRes.setOrderDayTime(api001Result.getAcceptDate() + SPACE + api001Result.getAcceptTime());
                a001bRes.setAfterCorrectPositionPower(api001Result.getMarginCapabilityAfter());
                a001bRes.setCorrectSorOrderResultClassification(api001Result.getSorModifyStatus());
            }
            
            // 訂正SOR注文区分="1"：訂正SOR　かつ　訂正SOR注文結果区分="△"：SOR対象外の場合、SOR対象外アラートを表示する
            if (CORRECT_SOR_ORDER_CLASSFICATION_SOR.equals(dtoReq.getCorrectSorOrderClassification()) && SPACE.equals(api001Result.getSorModifyStatus())) {
                apiErrorUtil.addDbError(WARNINGS_FRS_OUT_OF_SOR_ORDER_COMPLETED, null);
            }
            
        } else if (ORDER_CLASS_3.equals(dtoReq.getOrderKind()) || ORDER_CLASS_4.equals(dtoReq.getOrderKind())) {
            IfaMarginNewOrderCorrectConfirmA001aDtoRequest a001aReq = new IfaMarginNewOrderCorrectConfirmA001aDtoRequest();
            BeanUtils.copyProperties(a001aReq, dtoReq);
            StockModifyOrderAutoInData api002InData = setApi002Req(a001aReq);
            StockModifyOrderAutoIn api002In = new StockModifyOrderAutoIn();
            api002In.setIndata(api002InData);
            try {
                api002Result = apiWrapper.stockModifyOrderAuto(api002In);
                apiErrorUtil.checkApiResponse(api002Result.getShubetu(), api002Result.getCode(), api002Result.getMessage());

            } catch (Exception e) {
                // システムエラーの場合、DBに受注日を登録してエラーレスポンスを返却する
                // IFA注文番号
                updSql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
                // IFA注文サブ番号
                updSql002Req.setIfaOrderSubNo(dtoReq.getIfaOrderSubNo());
                // 更新者
                updSql002Req.setUpdateUser(ua.getUserId());

                try {
                    dao.updateIfaMarginNewOrderCorrectConfirmSql002b(updSql002Req);
    
                } finally {
                    dtoRes = IfaCommonUtil.createDataList(
                            Collections.emptyList(),
                            ErrorLevel.FATAL,
                            ERRORS_CMN_ORDER_EXECUTION_MODIFY_FAILED,
                            IfaCommonUtil.getMessage(ERRORS_CMN_ORDER_EXECUTION_MODIFY_FAILED)
                    );
                    return dtoRes;
                }
            }
                       
            // APIエラー判定
            isApiError = apiErrorUtil.isFatal();
            
            // IFA注文番号
            updSql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
            // IFA注文サブ番号
            updSql002Req.setIfaOrderSubNo(dtoReq.getIfaOrderSubNo());
            // 種別
            updSql002Req.setShubetu(api002Result.getShubetu());
            // エラーコード
            updSql002Req.setCode(api002Result.getCode());
            // エラーメッセージ
            updSql002Req.setErrMessage(api002Result.getMessage());
            // 更新者
            updSql002Req.setUpdateUser(ua.getUserId());
            // 受注日
            updSql002Req.setOrderDate(api002Result.getAcceptDate());
            if (!isApiError) {
                // 受注時刻
                updSql002Req.setOrderTime(api002Result.getAcceptTime());
                // 与信チェック用時価
                updSql002Req.setEstimatePrice(api002Result.getEstimatePrice());
                // 訂正前後の約定金額(概算)差分
                updSql002Req.setAmount(api002Result.getAmount());
                // 手数料（概算）
                updSql002Req.setCommission(api002Result.getCommission());
                // 消費税（概算）
                updSql002Req.setConsumptionTax(api002Result.getConsumptionTax());
                // 譲渡益税（概算）
                updSql002Req.setCapitalGainTax(api002Result.getCapitalGainTax());
                // 精算金額（概算）
                updSql002Req.setNetAmount(api002Result.getNetAmount());
                // 諸経費
                updSql002Req.setCost(api002Result.getCost());
                // 約定予定日
                updSql002Req.setContractDate(api002Result.getTradeDate());
                // 受渡予定日
                updSql002Req.setDeliveryDate(api002Result.getSettlementDate());
                // 受付有効期限
                updSql002Req.setAcceptLimit(api002Result.getModLimit());
                // 手数料区分（採用）
                updSql002Req.setComIdR(api002Result.getComIdR());
                // 売却可能数量
                updSql002Req.setAcPosition(api002Result.getAcPosition());
                // 注文後の売却可能数量
                updSql002Req.setAcPositionAfter(api002Result.getAcPositionAfter());
                // 買付可能金額
                updSql002Req.setAcBalance(api002Result.getAcBalance());
                // 注文後の買付可能金額
                updSql002Req.setAcBalanceAfter(api002Result.getAcBalanceAfter());
                // 取引不足額
                updSql002Req.setTradeDeficitAmount(api002Result.getTradeDeficitAmount());
                // ISA買付可能枠
                updSql002Req.setIsaBuyLimit(api002Result.getIsaBuyLimit().trim());
                // ジュニアNISA振替金額
                updSql002Req.setJrnisaTransferAmount(api002Result.getJrnisaTransferAmount());
                // 決済可能数量
                updSql002Req.setUnclosedQuantity(api002Result.getUnclosedQuantity());
                // 注文後の決済可能数量
                updSql002Req.setUnclosedQuantityAfter(api002Result.getUnclosedQuantityAfter());
                // 建玉余力
                updSql002Req.setMarginCapability(api002Result.getMarginCapability());
                // 注文後の建玉余力
                updSql002Req.setMarginCapabilityAfter(api002Result.getMarginCapabilityAfter());
                // 維持率
                updSql002Req.setCustomerInfoDetentionRate(api002Result.getActualGrntRate().trim());
                // 注文後維持率
                updSql002Req.setActualGrntRateAfter(api002Result.getActualGrntRateAfter().trim());
            }
            try {
                dao.updateIfaMarginNewOrderCorrectConfirmSql002(updSql002Req);
            } catch (Exception e) {
                // DB登録NG：DB登録エラーを返す。 */
                LOGGER.debug("IfaMarginNewOrderCorrectConfirmServiceImplL.correctionOrderA001b update Exception[{}]",
                        e.getMessage());
                
                errorMessageId = WARNINGS_FRS_POST_ORDER_EXECUTION_COMPLETED;
                apiErrorUtil.addDbError(errorMessageId, null);
            }
            
            if (isApiError) {
                el = ErrorLevel.FATAL;
                errorMessageId = ERRORS_CMN_ORDER_EXECUTION_MODIFY_FAILED;
                errorMessage = IfaCommonUtil.getMessage(ERRORS_CMN_ORDER_EXECUTION_MODIFY_FAILED,
                        new String[] { api002Result.getCode(), api002Result.getMessage() });
                dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), el, errorMessageId, errorMessage);
                return dtoRes;
            } else {
                a001bRes.setContractDate(api002Result.getTradeDate());
                a001bRes.setDeliveryDate(api002Result.getSettlementDate());
                a001bRes.setOrderDayTime(api002Result.getAcceptDate() + SPACE + api002Result.getAcceptTime());
                a001bRes.setAfterCorrectPositionPower(api002Result.getMarginCapabilityAfter());
            }
        }
        
        BeanUtils.copyProperties(a001bRes, dtoReq);
        
        dtoRes = apiErrorUtil.createDataList(dtoResList, "");
        return dtoRes;
    }
    
    /**
     * API001 inDataにセット
     */
    private StockModifyOrderInData setApi001Req(IfaMarginNewOrderCorrectConfirmA001aDtoRequest dtoReq)
            throws Exception {
        
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        UserAccount ua = IfaCommonUtil.getUserAccount();
        
        StockModifyOrderInData inData = new StockModifyOrderInData();
        
        inData.setTransactionId(StringUtil.fillLeft("", CHAR_ZERO, 32));
        inData.setCommandNum(StringUtil.fillLeft("", CHAR_ZERO, 7));
        inData.setCommandDate(StringUtil.fillLeft("", CHAR_ZERO, 8));
        inData.setButenCd(cc.getButenCode());
        inData.setKozaNo(StringUtil.fillLeft(cc.getAccountNumber(), CHAR_ZERO, 7));
        inData.setAccountId(StringUtil.fillLeft("", CHAR_ZERO, 11));
        inData.setNumber(StringUtil.fillLeft("", CHAR_ZERO, 7));
        inData.setOrigin("0");
        inData.setOrderType(API001_ORDER_TYPE);
        inData.setModifyType(API001_MODIFY_TYPE);
        inData.setOrderNo(dtoReq.getEcOrderNo());
        inData.setQuantity(StringUtil.fillLeft(dtoReq.getQuantity(), CHAR_ZERO, 8));
        inData.setCallcenterKbn(API001_CALLCENTER_KBN);
        inData.setUserId(ua.getCcsUserId());
        inData.setTicketId(API001_TICKET_ID);
        inData.setComId(dtoReq.getComId());
        inData.setCheckId(API001_CHECK_ID);
        inData.setIntermediary(API001_INTERMEDIARY);
        inData.setIpAddress(API001_IP_ADDRESS);
        
        // トリガ発動ゾーン、トリガ値段
        inData.setTriggerZone(TRIGGER_ZONE_NONE);                       // 指定なしの場合の値を初期値に設定
        inData.setTriggerPrice(StringUtil.fillLeft("", CHAR_ZERO, 10)); // 指定なしの場合の値を初期値に設定
        if (!dtoReq.getWorkingStatus()) {
            if (ORDER_CLASS_1.equals(dtoReq.getOrderKind())) {
                if (EXECUTE_METHOD_3.equals(dtoReq.getSasinariHouhou())) {
                    if (DOMESTIC_STOCK_TRADE_CLASS_BUY.equals(dtoReq.getTradeCd())) {
                        inData.setTriggerZone(TRIGGER_ZONE_OVER);
                    }
                    if (DOMESTIC_STOCK_TRADE_CLASS_SELL.equals(dtoReq.getTradeCd())) {
                        inData.setTriggerZone(TRIGGER_ZONE_UNDER);
                    }
                    inData.setTriggerPrice(StringUtil.fillLeft(dtoReq.getTriggerPrice(), CHAR_ZERO, 10));
                }
            } else if (ORDER_CLASS_2.equals(dtoReq.getOrderKind())) {
                if (DOMESTIC_STOCK_TRADE_CLASS_BUY.equals(dtoReq.getTradeCd())) {
                    inData.setTriggerZone(TRIGGER_ZONE_OVER);
                }
                if (DOMESTIC_STOCK_TRADE_CLASS_SELL.equals(dtoReq.getTradeCd())) {
                    inData.setTriggerZone(TRIGGER_ZONE_UNDER);
                }
                inData.setTriggerPrice(StringUtil.fillLeft(dtoReq.getOco2TriggerPrice(), CHAR_ZERO, 10));
            }
        }
        
        inData.setPrice(StringUtil.fillLeft("", CHAR_ZERO, 10)); // 指定なしの場合の値を初期値に設定
        // 注文種別=通常/逆指値　の場合
        if (ORDER_CLASS_1.equals(dtoReq.getOrderKind())) {
            inData.setSasinariKbn(dtoReq.getSasinariJyouken());
            inData.setPrice(StringUtil.fillLeft(dtoReq.getPrice(), CHAR_ZERO, 10));
            if (!dtoReq.getWorkingStatus()) {
                if (EXECUTE_METHOD_1.equals(dtoReq.getSasinariHouhou())
                        || EXECUTE_METHOD_2.equals(dtoReq.getSasinariHouhou())) {
                    inData.setRbeOrderKind(RBE_ORDER_KIND_SPACE3);
                }
                if (EXECUTE_METHOD_3.equals(dtoReq.getSasinariHouhou())) {
                    inData.setRbeOrderKind(RBE_ORDER_KIND_SLO);
                }
            }
            inData.setOcoSasinariKbn(SPACE);
            inData.setOcoPrice(API_OCO_PRICE_NONE);
            

        } else if (ORDER_CLASS_2.equals(dtoReq.getOrderKind())) {
            // 注文種別=OCO　の場合
            inData.setSasinariKbn(dtoReq.getOco1SasinariJyouken());
            inData.setPrice(StringUtil.fillLeft(dtoReq.getOco1Price(), CHAR_ZERO, 10));
            if (!dtoReq.getWorkingStatus()) {
                inData.setRbeOrderKind(RBE_ORDER_KIND_OCO);
            }
            inData.setOcoSasinariKbn(dtoReq.getOco2GyakusasiJyouken());
            inData.setOcoPrice(StringUtil.fillLeft(dtoReq.getOco2Price(), CHAR_ZERO, 10));
        }
        
        if (SELECT_MARKET_SOR.equals(dtoReq.getAfterCorrectMarket())) {
            // "1"：訂正SOR
            inData.setSorModifyCode(API001_SOR_MODIFY_CODE_CORRECT_SOR);
        // 上記以外
        } else {
            // "△"：通常訂正
            inData.setSorModifyCode(API001_SOR_MODIFY_CODE_NORMAL);
        }

        if (dtoReq.getWorkingStatus()) {
            inData.setRbeOrderKind(RBE_ORDER_KIND_SLO);
        }
        
        return inData;
    }
    
    /** 
     * API002 inDataにセット
     */
    private StockModifyOrderAutoInData setApi002Req(IfaMarginNewOrderCorrectConfirmA001aDtoRequest dtoReq) {
        
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        UserAccount ua = IfaCommonUtil.getUserAccount();
        StockModifyOrderAutoInData inData = new StockModifyOrderAutoInData();
        
        inData.setTransactionId(StringUtil.fillLeft("", CHAR_ZERO, 32));
        inData.setCommandNum(StringUtil.fillLeft("", CHAR_ZERO, 7));
        inData.setCommandDate(StringUtil.fillLeft("", CHAR_ZERO, 8));
        inData.setButenCd(cc.getButenCode());
        inData.setKozaNo(StringUtil.fillLeft(cc.getAccountNumber(), CHAR_ZERO, 7));
        inData.setAccountId(StringUtil.fillLeft("", CHAR_ZERO, 11));
        inData.setNumber(StringUtil.fillLeft("", CHAR_ZERO, 7));
        inData.setOrigin(API002_ORIGIN);
        inData.setOrderType(API002_ORDER_TYPE);
        inData.setModifyType(API002_MODIFY_TYPE);
        inData.setOrderNo(dtoReq.getEcOrderNo());
        inData.setQuantity(StringUtil.fillLeft(dtoReq.getQuantity(), CHAR_ZERO, 8));
        inData.setSasinariKbn(dtoReq.getIfd1SasinariJyouken());
        inData.setCallcenterKbn(API002_CALLCENTER_KBN);
        inData.setUserId(ua.getCcsUserId());
        inData.setTicketId(API002_TICKET_ID);
        inData.setComId(dtoReq.getComId());
        inData.setCheckId(API002_CHECK_ID);
        inData.setOcoSasinariKbn(SPACE);
        inData.setOcoPrice(API_OCO_PRICE_NONE);
        inData.setAutoOrderKind(API002_AUTO_ORDER_CLASS);
        inData.setIntermediary(API002_INTERMEDIARY);
        inData.setIpAddress(API002_IP_ADDRESS);
        
        // 指値（訂正値段）
        inData.setPrice(StringUtil.fillLeft("", CHAR_ZERO, 10)); // 指定なしの場合の値を初期値に設定
        if (EXECUTE_METHOD_1.equals(dtoReq.getIfd1SasinariHouhou())) {
            inData.setPrice(StringUtil.fillLeft(dtoReq.getIfd1Price(), CHAR_ZERO, 10));
        } else if (EXECUTE_METHOD_3.equals(dtoReq.getIfd1SasinariHouhou())) {
            if (EXECUTE_METHOD_1.equals(dtoReq.getIfd1GyakusasiHouhou())) {
                inData.setPrice(StringUtil.fillLeft(dtoReq.getIfd1Price(), CHAR_ZERO, 10));
            }
        }
        
        // RBE注文種別
        if (dtoReq.getWorkingStatus()) {
            inData.setRbeOrderKind(RBE_ORDER_KIND_SLO);

        } else {
            if (EXECUTE_METHOD_1.equals(dtoReq.getIfd1SasinariHouhou())
                    || EXECUTE_METHOD_2.equals(dtoReq.getIfd1SasinariHouhou())) {
                inData.setRbeOrderKind(RBE_ORDER_KIND_SPACE3);
            }
            if (EXECUTE_METHOD_3.equals(dtoReq.getIfd1SasinariHouhou())) {
                inData.setRbeOrderKind(RBE_ORDER_KIND_SLO);
            }
        }
        
        // トリガ発動ゾーン、トリガ値段
        inData.setTriggerZone(TRIGGER_ZONE_NONE);                       // 指定なしの場合の値を初期値に設定
        inData.setTriggerPrice(StringUtil.fillLeft("", CHAR_ZERO, 10)); // 指定なしの場合の値を初期値に設定
        if (!dtoReq.getWorkingStatus()) {
            if (EXECUTE_METHOD_3.equals(dtoReq.getIfd1SasinariHouhou())) {
                if (DOMESTIC_STOCK_TRADE_CLASS_BUY.equals(dtoReq.getTradeCd())) {
                    inData.setTriggerZone(TRIGGER_ZONE_OVER);
                } else if (DOMESTIC_STOCK_TRADE_CLASS_SELL.equals(dtoReq.getTradeCd())) {
                    inData.setTriggerZone(TRIGGER_ZONE_UNDER);
                }
                inData.setTriggerPrice(StringUtil.fillLeft(dtoReq.getIfd1TriggerPrice(), CHAR_ZERO, 10));
            }
        }
        
        // DONE項目
        inData.setDonePrice(StringUtil.fillLeft("", CHAR_ZERO, 10));        // 指定なしの場合の値を初期値に設定
        inData.setDoneRbeOrderKind(RBE_ORDER_KIND_SPACE3);                  // 指定なしの場合の値を初期値に設定
        inData.setDoneTriggerZone(TRIGGER_ZONE_NONE);                       // 指定なしの場合の値を初期値に設定
        inData.setDoneTriggerPrice(StringUtil.fillLeft("", CHAR_ZERO, 10)); // 指定なしの場合の値を初期値に設定
        inData.setDoneOcoSasinariKbn(SPACE);                                // 指定なしの場合の値を初期値に設定
        inData.setDoneOcoPrice(StringUtil.fillLeft("", CHAR_ZERO, 10));     // 指定なしの場合の値を初期値に設定
        // 注文種別=IFD　の場合
        if (ORDER_CLASS_3.equals(dtoReq.getOrderKind())) {
            inData.setDoneSasinariKbn(dtoReq.getIfd2SasinariJyouken());
            if (EXECUTE_METHOD_1.equals(dtoReq.getIfd2SasinariHouhou())) {
                inData.setDonePrice(StringUtil.fillLeft(dtoReq.getIfd2Price(), CHAR_ZERO, 10));
            } else if (EXECUTE_METHOD_3.equals(dtoReq.getIfd2SasinariHouhou())) {
                if (EXECUTE_METHOD_1.equals(dtoReq.getIfd2GyakusasiHouhou())) {
                    inData.setDonePrice(StringUtil.fillLeft(dtoReq.getIfd2Price(), CHAR_ZERO, 10));
                }
            }
            if (EXECUTE_METHOD_1.equals(dtoReq.getIfd2SasinariHouhou())
                    || EXECUTE_METHOD_2.equals(dtoReq.getIfd2SasinariHouhou())) {
                inData.setDoneRbeOrderKind(RBE_ORDER_KIND_SPACE3);
            }
            if (EXECUTE_METHOD_3.equals(dtoReq.getIfd2SasinariHouhou())) {
                inData.setDoneRbeOrderKind(RBE_ORDER_KIND_SLO);
                if (DOMESTIC_STOCK_TRADE_CLASS_BUY.equals(dtoReq.getTradeCd())) {
                    inData.setDoneTriggerZone(TRIGGER_ZONE_UNDER);
                } else if (DOMESTIC_STOCK_TRADE_CLASS_SELL.equals(dtoReq.getTradeCd())) {
                    inData.setDoneTriggerZone(TRIGGER_ZONE_OVER);
                }
                inData.setDoneTriggerPrice(StringUtil.fillLeft(dtoReq.getIfd2TriggerPrice(), CHAR_ZERO, 10));
            }
        } else if (ORDER_CLASS_4.equals(dtoReq.getOrderKind())) {
            // 注文種別=IFDOCO　の場合
            inData.setDoneSasinariKbn(dtoReq.getOco1SasinariJyouken());
            if (EXECUTE_METHOD_1.equals(dtoReq.getOco1SasinariHouhou())) {
                inData.setDonePrice(StringUtil.fillLeft(dtoReq.getOco1Price(), CHAR_ZERO, 10));
            }
            inData.setDoneRbeOrderKind(RBE_ORDER_KIND_OCO);
            inData.setDoneTriggerPrice(StringUtil.fillLeft(dtoReq.getOco2TriggerPrice(), CHAR_ZERO, 10));
            if (DOMESTIC_STOCK_TRADE_CLASS_BUY.equals(dtoReq.getTradeCd())) {
                inData.setDoneTriggerZone(TRIGGER_ZONE_UNDER);
            } else if (DOMESTIC_STOCK_TRADE_CLASS_SELL.equals(dtoReq.getTradeCd())) {
                inData.setDoneTriggerZone(TRIGGER_ZONE_OVER);
            }
            inData.setDoneOcoSasinariKbn(dtoReq.getOco2GyakusasiJyouken());
            inData.setDoneOcoPrice(StringUtil.fillLeft(dtoReq.getOco2Price(), CHAR_ZERO, 10));
        }
        
        return inData;
    }
    
}
