package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.component.Fct027;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct027Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct027Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMarginRepayOrderCorrectConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginRepayOrderCorrectConfirmSqlModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectConfirmA001aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectConfirmA001aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectConfirmA001bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectConfirmA001bResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMarginRepayOrderCorrectConfirmService;
import com.sbisec.helios.ap.common.enums.DomesticMarginAccountType;
import com.sbisec.helios.ap.common.enums.DomesticStockTradeClass;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ExecuteMethod;
import com.sbisec.helios.ap.common.enums.MediateAbleTradeFlag;
import com.sbisec.helios.ap.common.enums.OrderClass;
import com.sbisec.helios.ap.common.enums.SecurityMoneyClass;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.enums.TradeSuspendFlag;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;
import com.sbisec.helios.ap.common.exception.SystemErrorException;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.AccountUtil;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sbisec.pcenter.dto.yanap.StockModifyOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.StockModifyOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.StockModifyOrderOutData;

/**
 * 画面ID：SUB0202_0212-06_2
 * 画面名：信用返済注文訂正確認
 * 2024/05/24 新規作成
 *
 * @author SCSK 笹倉秀行
 */
@Component(value = "cmpIfaMarginRepayOrderCorrectConfirmService")
public class IfaMarginRepayOrderCorrectConfirmServiceImpL implements IfaMarginRepayOrderCorrectConfirmService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMarginRepayOrderCorrectConfirmServiceImpL.class);
    
    /** メッセージID:権限チェックエラー */
    private static final String MESSAGE_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** メッセージID:取引停止口座エラー */
    private static final String MESSAGE_OUT_OF_SERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** メッセージID:媒介不可エラー */
    private static final String MESSAGE_MEDIATE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** メッセージID:信用口座未開設エラー */
    private static final String MESSAGE_DMARGIN_ACCOUNT_NOT_OPENED = "errors.dms.domesticMarginAccount.notOpen";
    
    /** メッセージID:注意情報エラー */
    private static final String MESSAGE_NOTICE_ERROR_CHECK = "errors.cmn.noticeErrorCheck";
    
    /** メッセージID:重要なお知らせエラー */
    private static final String MESSAGE_INFORMATION_CHECK = "errors.informationCheck";
    
    /** メッセージID:取引注意情報確認 */
    private static final String MESSAGE_INFORMATION_OCCURS = "errors.cmn.information.occurs";
    
    /** メッセージID:注文発注前の注文データが登録できないため、注文しませんでした。 */
    private static final String ERRORS_FRS_PREORDEREXECUTION_FAILED = "errors.frs.preOrderExecution.failed";
    
    /** メッセージID:注文結果DB登録失敗ワーニング */
    private static final String MESSAGE_POST_ORDER_EXECUTION_WARNING = "warnings.frs.postOrderExecution.completed";
    
    /** メッセージID:注文訂正処理でエラーが発生しました。(エラーコード：{0}、エラーメッセージ{1}) */
    private static final String ERRRORS_CMN_ORDEREXECUTIONMODIFY_EXECUTION_FAILED = "errors.cmn.orderExecutionModify.failed";
    
    /** SOR対象外ワーニング */
    private static final String WARNINGS_FRS_OUT_OF_SOR_ORDER_COMPLETED = "warnings.frs.outOfSOROrder.completed";
    
    /** 区分ID:対象取引（メッセージ表示用）　*/
    private static final String CODE_ID_MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 区分値:対象取引（メッセージ表示用）.国内信用取引　*/
    private static final String CODE_VAL_MSG_DISPLAY_TARGET_TRADE_DMARGIN = "3";
    
    /** 区分値：証券種別.国内株式 */
    private static final String CODE_VAL_SECURITY_CLASS_DSTOCK = "01";
    
    /** 区分値:通貨コード.999 */
    private static final String CODE_VAL_CURRENCY_CODE_999 = "999";
    
    /** 区分値:国籍コード.99 */
    private static final String CODE_VAL_NATIONALITY_CODE_99 = "99";
    
    /** 区分値：注意情報の確認.確認済 */
    private static final String CODE_VAL_NOTICE_INFO_CONFIRM_CONFIRMED = "1";
    
    /** 区分値：重要なお知らせの確認.確認済 */
    private static final String CODE_VAL_IMPORTANT_NOTIFICATION_CONFIRM_CONFIRMED = "1";
    
    /** 区分値：取引注意情報の説明.説明済 */
    private static final String CODE_VAL_TRADE_NOTICE_INFO_EXPLAIN_EXPLAINED = "1";
    
    /** 区分値：注文種別（一覧）.通常注文 */
    private static final String CODE_VAL_LIST_ORDER_CLASS_NORMAL = "0";
    
    /** 区分値：注文種別（一覧）.逆指値注文 */
    private static final String CODE_VAL_LIST_ORDER_CLASS_STOP_PRICE = "1";
    
    /** 区分値：注文種別（一覧）.OCO注文 */
    private static final String CODE_VAL_LIST_ORDER_CLASS_OCO = "2";
    
    /** 区分値：直近トリガ発動ゾーン.以上 */
    private static final String CODE_VAL_LATEST_TRIGGER_PRICE_GREATER_EQUAL = "0";
    
    /** 区分値：直近トリガ発動ゾーン.以下 */
    private static final String CODE_VAL_LATEST_TRIGGER_PRICE_LESS_EQUAL = "1";
    
    /** 規制銘柄 */
    private static final String REGULATION_BRAND = "1";
    
    /** RBE注文種別.通常注文 */
    private static final String RBE_ORDER_KIND_NORMAL = "   ";
    
    /** RBE注文種別.逆指値注文 */
    private static final String RBE_ORDER_KIND_STOP_PRICE = "SLO";
    
    /** RBE注文種別.OCO注文 */
    private static final String RBE_ORDER_KIND_OCO = "OCO";
    
    /** トリガ値段.通常注文 */
    private static final String PRICE_UNSPECIFIED = "0000000000";
    
    /** 受付経路区分.支店 */
    private static final String CALLCENTER_KBN_BRANCH = "0";
    
    /** API:商品区分 設定値：株 */
    private static final String ORDER_TYPE_STOCK = "S";
    
    /** API:訂正区分 設定値 */
    private static final String MODIFY_TYPE_VALUE = "PRICE   ";
    
    /** API:媒介 設定値：媒介注文 */
    private static final String INTERMEDIARY_VALUE = "1";
    
    /** IPアドレス.ifap */
    private static final String IP_ADDRESS_IFAP = "ifap";

    /** 区分ID:手数料区分 */
    private static final String CODE_ID_COMM_TYPE = "COMM_TYPE";
    
    /** APIタイプ:EC-GW */
    private static final String API_TYPE_ECGW = "EC-GW";
    
    /** パディング用Char'0'. */
    private static final char PADDING_CHAR_ZERO = '0';
    
    /** パディング長:トランザクションID(32桁) */
    private static final int PADDING_LENGTH_TRANSACTION_ID = 32;
    
    /** パディング長:通番(7桁) */
    private static final int PADDING_LENGTH_COMMAND_NUM = 7;
    
    /** パディング長:API002.アカウントID(11桁) */
    private static final int PADDING_LENGTH_API_ACCOUNT_ID = 11;
    
    /** パディング長:アカウント毎の連番(7桁) */
    private static final int PADDING_LENGTH_NUMBER_PER_ACCOUNT = 7;

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
    
    /** 利用者顧客参照権限チェック */
    @Autowired
    private Fct001 fct001;
    
    /** 取引コース媒介可否チェック */
    @Autowired
    private Fct003 fct003;
    
    /** 取引制限チェック */
    @Autowired
    private Fct021 fct021;
    
    /** 国内株式情報取得 */
    @Autowired
    private Fct027 fct027;
    
    /** コードリストService */
    @Autowired
    private CodeListService codeListService;
    
    /** 信用返済注文確認Dao */
    @Autowired
    private IfaMarginRepayOrderCorrectConfirmDao dao;
    
    @Autowired
    private ApiWrapper apiWrapper;
    
    /**
     * アクションID：A001a
     * アクション名：訂正発注
     * Dto リクエスト：IfaMarginRepayOrderCorrectConfirmA001aRequestDto
     * Dto レスポンス：IfaMarginRepayOrderCorrectConfirmA001aResponseDto
     * model リクエスト：IfaMarginRepayOrderCorrectConfirmSqlModel
     * model レスポンス：IfaMarginRepayOrderCorrectConfirmSqlModel
     *
     * @param dtoReq 訂正発注リクエスト
     * @return 訂正発注レスポンス
     * @exception Exception 例外が発生した場合
     */
    @Transactional
    @Override
    public DataList<IfaMarginRepayOrderCorrectConfirmA001aResponseDto> correctionOrderA001a(
            IfaMarginRepayOrderCorrectConfirmA001aRequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginRepayOrderCorrectConfirmServiceImplL.correctionOrderA001a");
        }
        
        final CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        // ①利用者の口座に対する権限チェックを行う。
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(customerCommon.getButenCode());
        inputFct001Dto.setAccountNumber(customerCommon.getAccountNumber());
        final OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        if (StringUtils.equals(outputFct001Dto.getTargetCustomerRefAuthFlag(),
                TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId())) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(MESSAGE_ACCOUNT_NOT_EXISTS,
                            new String[] { customerCommon.getButenCode(), customerCommon.getAccountNumber() }));
        } else if (StringUtils.equals(outputFct001Dto.getTradeSuspendFlag(), TradeSuspendFlag.SUSPEND.getId())) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_OUT_OF_SERVICE,
                    IfaCommonUtil.getMessage(MESSAGE_OUT_OF_SERVICE));
        }
        
        // ②取引コース媒介可否チェックを行う。
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setButenCode(customerCommon.getButenCode());
        inputFct003Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct003Dto.setProductCd(CODE_VAL_SECURITY_CLASS_DSTOCK);
        inputFct003Dto.setTradeCd(dtoReq.getTradeCd());
        if (fct003.doCheck(inputFct003Dto).getMediateProprietyList().stream()
                .map(mediateProprieties -> mediateProprieties.getMediatePropriety()).noneMatch(
                        mediatePropriety -> StringUtils.equals(MediateAbleTradeFlag.ARI.getId(), mediatePropriety))) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_MEDIATE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(MESSAGE_MEDIATE_UNAVAILABLE, new String[] { codeListService
                            .getValue(CODE_ID_MSG_DISPLAY_TARGET_TRADE, CODE_VAL_MSG_DISPLAY_TARGET_TRADE_DMARGIN) }));
        }
        
        // ③顧客共通情報.信用口座区分（国内）より、国内信用口座開設状況のチェックを行う。
        if (!StringUtils.equals(customerCommon.getDomesticMarginAccountType(),
                DomesticMarginAccountType.MARGIN_ACCOUNT.getId())) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_DMARGIN_ACCOUNT_NOT_OPENED,
                    IfaCommonUtil.getMessage(MESSAGE_DMARGIN_ACCOUNT_NOT_OPENED));
        }
        
        // ④口座の取引制限チェックを行う。
        InputFct021Dto inputFct021Dto = new InputFct021Dto();
        inputFct021Dto.setButenCode(customerCommon.getButenCode());
        inputFct021Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct021Dto.setProductCd(SecurityMoneyClass.DOMESTIC_STOCKS.getId());
        inputFct021Dto.setTradeCd(dtoReq.getTradeCd());
        inputFct021Dto.setCountryCd(CODE_VAL_NATIONALITY_CODE_99);
        inputFct021Dto.setCurrencyCode(CODE_VAL_CURRENCY_CODE_999);
        inputFct021Dto.setTradeRestrictChkMarket(dtoReq.getAfterCorrecMarket());
        inputFct021Dto.setPaymentLimit(dtoReq.getPaymentDeadline());
        final OutputFct021Dto outputFct021Dto = fct021.doCheck(inputFct021Dto);
        if (StringUtils.equals(outputFct021Dto.getNoteInfoErrFlag(), Fct021.EXIST)) {
            // 注意情報エラーありの場合
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_NOTICE_ERROR_CHECK,
                    IfaCommonUtil.getMessage(MESSAGE_NOTICE_ERROR_CHECK, new String[] { codeListService
                            .getValue(CODE_ID_MSG_DISPLAY_TARGET_TRADE, CODE_VAL_MSG_DISPLAY_TARGET_TRADE_DMARGIN) }));
        } else if (StringUtils.equals(outputFct021Dto.getNoteLimitErrFlag(), Fct021.EXIST)) {
            // お知らせエラーありの場合
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_INFORMATION_CHECK,
                    IfaCommonUtil.getMessage(MESSAGE_INFORMATION_CHECK));
        }
        if (StringUtils.equals(outputFct021Dto.getNoteInfoAlertFlag(), Fct021.EXIST)) {
            // 注意情報アラートありの場合、注意情報アラート確認チェックボックスの状態を判定する
            if (!StringUtils.equals(dtoReq.getNoticeInfoAlertConfirm(), CODE_VAL_NOTICE_INFO_CONFIRM_CONFIRMED)) {
                // 注意情報アラート確認チェックボックスが確認済ではない場合、エラーを返す
                return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_INFORMATION_OCCURS,
                        IfaCommonUtil.getMessage(MESSAGE_INFORMATION_OCCURS));
            }
        }
        if (StringUtils.equals(outputFct021Dto.getNoteLimitAlertFlag(), Fct021.EXIST)) {
            // お知らせアラートありの場合、お知らせアラート確認チェックボックスの状態を判定する
            if (!StringUtils.equals(dtoReq.getNoticeAlertConfirm(),
                    CODE_VAL_IMPORTANT_NOTIFICATION_CONFIRM_CONFIRMED)) {
                // お知らせアラート確認チェックボックスが確認済ではない場合、エラーを返す
                return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_INFORMATION_OCCURS,
                        IfaCommonUtil.getMessage(MESSAGE_INFORMATION_OCCURS));
            }
        }
        
        // ⑤銘柄の取引注意情報を取得する。
        InputFct027Dto inputFct027Dto = new InputFct027Dto();
        inputFct027Dto.setBrandCode(dtoReq.getBrandCode());
        final OutputFct027Dto outputFct027Dto = fct027.getData(inputFct027Dto);
        if (StringUtils.equals(outputFct027Dto.getRegKbn(), REGULATION_BRAND)) {
            // 規制銘柄の場合、取引注意情報（銘柄）確認の有無を判定する
            if (!StringUtils.equals(dtoReq.getTradeNoticeInfoBrandConfirm(),
                    CODE_VAL_TRADE_NOTICE_INFO_EXPLAIN_EXPLAINED)) {
                // 確認済ではない(チェックボックスがOFF、または非表示)場合、エラーを返す
                return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_INFORMATION_OCCURS,
                        IfaCommonUtil.getMessage(MESSAGE_INFORMATION_OCCURS));
            }
        }
        
        // ⑥発注前に注文訂正内容を国内株式注文テーブルへ記録する。
        IfaMarginRepayOrderCorrectConfirmSqlModel sql00102Res = null;
        String ifaOrderNo = null;
        try {
            sql00102Res = dao.selectIfaMarginRepayOrderCorrectConfirmSql00102(dtoReq.getEcOrderNo(),
                    customerCommon.getButenCode(), dtoReq.getOrderDate());
            if (sql00102Res == null) {
                // IFA注文番号を取得する
                ifaOrderNo = dao.selectIfaMarginRepayOrderCorrectConfirmSql00103();
            }
        } catch (Exception e) {
            // IFA注文番号が取得できない場合は登録出来ないため、DB登録エラーを返す。
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_FRS_PREORDEREXECUTION_FAILED,
                    IfaCommonUtil.getMessage(ERRORS_FRS_PREORDEREXECUTION_FAILED));
        }
        
        IfaMarginRepayOrderCorrectConfirmSqlModel sqlReq = new IfaMarginRepayOrderCorrectConfirmSqlModel();
        final UserAccount userAccount = IfaCommonUtil.getUserAccount();
        if (sql00102Res == null) {
            sqlReq.setIfaOrderNo(ifaOrderNo);
            sqlReq.setIfaOrderSubNo("1");
            sqlReq.setButenCode(customerCommon.getButenCode());
            sqlReq.setAccountNumber(customerCommon.getAccountNumber());
            sqlReq.setCustomerId(customerCommon.getCustomerCode());
            sqlReq.setSpecificKbn(customerCommon.getSpecificAccountType());
            sqlReq.setRepaymentMethodType(null);
            sqlReq.setRequestType(null);
            sqlReq.setCheckSor(dtoReq.getCheckSor());
            sqlReq.setJoZeiKbn(null);
        } else {
            sqlReq.setIfaOrderNo(sql00102Res.getIfaOrderNo());
            sqlReq.setIfaOrderSubNo(String.valueOf(Long.valueOf(sql00102Res.getIfaOrderSubNo()) + 1));
            sqlReq.setButenCode(sql00102Res.getButenCode());
            sqlReq.setAccountNumber(sql00102Res.getAccountNumber());
            sqlReq.setCustomerId(sql00102Res.getCustomerId());
            sqlReq.setSpecificKbn(sql00102Res.getSpecificKbn());
            sqlReq.setRepaymentMethodType(sql00102Res.getRepaymentMethodType());
            sqlReq.setRequestType(sql00102Res.getRequestType());
            sqlReq.setCheckSor(sql00102Res.getCheckSor());
            sqlReq.setJoZeiKbn(sql00102Res.getJoZeiKbn());
        }
        sqlReq.setBrandCd(dtoReq.getBrandCode());
        sqlReq.setMarket(dtoReq.getAfterCorrecMarket());
        sqlReq.setOrderStatus("1");
        sqlReq.setTradeKbn(dtoReq.getTradeCd());
        sqlReq.setQuantity(dtoReq.getQuantity());
        sqlReq.setLimit(dtoReq.getPeriod());
        sqlReq.setPaymentLimit(dtoReq.getPaymentDeadline());
        sqlReq.setOrderSyubetsu(dtoReq.getOrderKind());
        sqlReq.setKanyuKbn(dtoReq.getKanyuKbn());
        sqlReq.setJutyuKbn(dtoReq.getOrderMethod());
        sqlReq.setCheckInsider(dtoReq.getCheckInsider());
        sqlReq.setUserId(userAccount.getCcsUserId());
        sqlReq.setTesuuryouKbn(
                codeListService.convertKeyToExtKey(CODE_ID_COMM_TYPE, API_TYPE_ECGW, dtoReq.getTesuuryouKbn()));
        sqlReq.setAlterFlg("1");
        sqlReq.setAlterOrderNum(dtoReq.getEcOrderNo());
        sqlReq.setRbeOrderStatus(dtoReq.getRbeOrderStatus());
        sqlReq.setOrderType(ORDER_TYPE_STOCK);
        sqlReq.setOrderNum(dtoReq.getEcOrderNo());

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
        sqlReq.setDailyCreditKbn(dailyCreditKbn); // 一日信用区分
        sqlReq.setPaymentDeadlineDate(dtoReq.getPaymentDeadlineDate()); // 弁済期限年月日数
        sqlReq.setDateKbn(dtoReq.getDateKbn()); // 年月日区分

        sqlReq.setBrokerCode(customerCommon.getBrokerCode());
        sqlReq.setIntermediaryEmpCd(customerCommon.getBrokerChargeCode());
        sqlReq.setCreateUser(userAccount.getUserId());
        sqlReq.setUpdateUser(userAccount.getUserId());
        
        String limitPrice = null;
        String triggerZone = StringUtils.SPACE;
        String triggerPrice = null;
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())) {
            // 注文種別が通常/逆指値の場合
            sqlReq.setSasinariKbn(dtoReq.getSasinariJyouken());
            sqlReq.setOcoSasinariKbn(StringUtils.SPACE);
            if (StringUtils.equals(dtoReq.getSasinariHouhou(), ExecuteMethod.STOP.getId())) {
                // 執行方法が逆指値の場合
                sqlReq.setOrderSyubetsuList(CODE_VAL_LIST_ORDER_CLASS_STOP_PRICE);
                if (!Boolean.valueOf(dtoReq.getWorkingStatus())) {
                    sqlReq.setRbeOrderKind(RBE_ORDER_KIND_STOP_PRICE);
                }
                
                limitPrice = dtoReq.getPrice();
                triggerPrice = dtoReq.getTriggerPrice();
                
                // 取引種別からトリガー発動ゾーンを設定する
                if (StringUtils.equals(dtoReq.getTradeCd(), DomesticStockTradeClass.MARGIN_REPAY_BUY.getId())) {
                    triggerZone = CODE_VAL_LATEST_TRIGGER_PRICE_GREATER_EQUAL;
                } else if (StringUtils.equals(dtoReq.getTradeCd(), DomesticStockTradeClass.MARGIN_REPAY_SELL.getId())) {
                    triggerZone = CODE_VAL_LATEST_TRIGGER_PRICE_LESS_EQUAL;
                }
            
            } else {
                // 執行方法が逆指値以外の場合
                sqlReq.setOrderSyubetsuList(CODE_VAL_LIST_ORDER_CLASS_NORMAL);
                if (!Boolean.valueOf(dtoReq.getWorkingStatus())) {
                    sqlReq.setRbeOrderKind(RBE_ORDER_KIND_NORMAL);
                }
                
                if (StringUtils.equals(dtoReq.getSasinariHouhou(), ExecuteMethod.LIMIT.getId())) {
                    // 執行方法が指値の場合
                    limitPrice = dtoReq.getPrice();
                }
            }
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
            // 注文種別がOCOの場合
            sqlReq.setOrderSyubetsuList(CODE_VAL_LIST_ORDER_CLASS_OCO);
            if (!Boolean.valueOf(dtoReq.getWorkingStatus())) {
                sqlReq.setRbeOrderKind(RBE_ORDER_KIND_OCO);
            }
            sqlReq.setSasinariKbn(dtoReq.getOco1SasinariJyouken());
            sqlReq.setOcoSasinariKbn(dtoReq.getOco2GyakusasiJyouken());
            sqlReq.setOcoSashine(StringUtils.defaultIfEmpty(dtoReq.getOco2Price(), PRICE_UNSPECIFIED));
            limitPrice = dtoReq.getOco1Price();
            triggerPrice = dtoReq.getOco2TriggerPrice();
            
            // 取引種別からトリガー発動ゾーンを設定する
            if (StringUtils.equals(dtoReq.getTradeCd(), DomesticStockTradeClass.MARGIN_REPAY_BUY.getId())) {
                triggerZone = CODE_VAL_LATEST_TRIGGER_PRICE_GREATER_EQUAL;
            } else if (StringUtils.equals(dtoReq.getTradeCd(), DomesticStockTradeClass.MARGIN_REPAY_SELL.getId())) {
                triggerZone = CODE_VAL_LATEST_TRIGGER_PRICE_LESS_EQUAL;
            }
        }
        if (Boolean.valueOf(dtoReq.getWorkingStatus())) {
            sqlReq.setRbeOrderKind(RBE_ORDER_KIND_STOP_PRICE);
        }
        sqlReq.setPrice(StringUtils.defaultIfEmpty(limitPrice, PRICE_UNSPECIFIED));
        sqlReq.setTriggerZone(triggerZone);
        sqlReq.setTriggerPrice(StringUtils.defaultIfEmpty(triggerPrice, PRICE_UNSPECIFIED));
        

        try {
            // 訂正発注前の国内株式注文データを登録する
            if (dao.insertIfaMarginRepayOrderCorrectConfirmSql00101(sqlReq) != 1) {
                // 登録結果が1件にならない場合は実行時例外を通知する
                throw new IfaRuntimeException(ERRORS_FRS_PREORDEREXECUTION_FAILED);
            }
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("IfaMarginRepayOrderCorrectConfirmServiceImplL.correctionOrderA001a", e);
            }
            //DB登録NG：DB登録エラーを返す。ロールバックのためSystemErrorExceptionにdataListをラップする
            throw new SystemErrorException(
                    IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_FRS_PREORDEREXECUTION_FAILED,
                            IfaCommonUtil.getMessage(ERRORS_FRS_PREORDEREXECUTION_FAILED)));
        }
        
        final IfaMarginRepayOrderCorrectConfirmA001aResponseDto responseData = new IfaMarginRepayOrderCorrectConfirmA001aResponseDto();
        responseData.setIfaOrderNo(sqlReq.getIfaOrderNo());
        responseData.setIfaOrderSubNo(sqlReq.getIfaOrderSubNo());
        return IfaCommonUtil.createDataList(List.of(responseData), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtils.EMPTY);
    }
    
    /**
     * アクションID：A001b
     * アクション名：訂正発注
     * Dto リクエスト：IfaMarginRepayOrderCorrectConfirmA001bRequestDto
     * Dto レスポンス：IfaMarginRepayOrderCorrectConfirmA001bResponseDto
     * model リクエスト：IfaMarginRepayOrderCorrectConfirmSql002RequestModel
     * model レスポンス：IfaMarginRepayOrderCorrectConfirmSql002ResponseModel
     *
     * @param dtoReq 訂正発注リクエスト
     * @return 訂正発注レスポンス
     * @exception Exception 例外が発生した場合
     */
    @Override
    public DataList<IfaMarginRepayOrderCorrectConfirmA001bResponseDto> correctionOrderA001b(
            IfaMarginRepayOrderCorrectConfirmA001bRequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginRepayOrderCorrectConfirmServiceImplL.correctionOrderA001b");
        }
        
        final CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        final UserAccount userAccount = IfaCommonUtil.getUserAccount();
        final IfaMarginRepayOrderCorrectConfirmA001aRequestDto req = dtoReq.getRequest();
        
        StockModifyOrderInData inData = new StockModifyOrderInData();
        inData.setTransactionId(StringUtils.repeat(PADDING_CHAR_ZERO, PADDING_LENGTH_TRANSACTION_ID));
        inData.setCommandNum(StringUtils.repeat(PADDING_CHAR_ZERO, PADDING_LENGTH_COMMAND_NUM));
        inData.setCommandDate(StringUtils.repeat(PADDING_CHAR_ZERO, DateFormatUtil.YYYYMMDD.length()));
        inData.setButenCd(customerCommon.getButenCode());
        inData.setKozaNo(AccountUtil.formatToApi(customerCommon.getAccountNumber()));
        inData.setAccountId(StringUtils.repeat(PADDING_CHAR_ZERO, PADDING_LENGTH_API_ACCOUNT_ID));
        inData.setNumber(StringUtils.repeat(PADDING_CHAR_ZERO, PADDING_LENGTH_NUMBER_PER_ACCOUNT));
        inData.setOrigin("0");
        inData.setOrderType(ORDER_TYPE_STOCK);
        inData.setModifyType(MODIFY_TYPE_VALUE);
        inData.setOrderNo(StringUtils.leftPad(req.getEcOrderNo(), 6, "0"));
        inData.setQuantity(StringUtils.leftPad(req.getQuantity(), 8, "0"));
        inData.setCallcenterKbn(CALLCENTER_KBN_BRANCH);
        inData.setUserId(userAccount.getCcsUserId());
        inData.setTicketId(StringUtils.SPACE);
        inData.setComId(req.getTesuuryouKbn());
        inData.setCheckId(StringUtils.SPACE);
        inData.setIntermediary(INTERMEDIARY_VALUE);
        inData.setIpAddress(StringUtils.rightPad(IP_ADDRESS_IFAP, 39, " "));
        
        String limitPrice = null;
        String triggerZone = StringUtils.SPACE;
        String triggerPrice = null;
        if (StringUtils.equals(req.getOrderKind(), OrderClass.NORMAL.getId())) {
            // 注文種別が通常/逆指値の場合
            inData.setSasinariKbn(req.getSasinariJyouken());
            inData.setOcoSasinariKbn(StringUtils.SPACE);
            inData.setOcoPrice(StringUtils.repeat(StringUtils.SPACE, PRICE_UNSPECIFIED.length()));
            if (StringUtils.equals(req.getSasinariHouhou(), ExecuteMethod.STOP.getId())) {
                // 執行方法が逆指値の場合
                if (!Boolean.valueOf(req.getWorkingStatus())) {
                    inData.setRbeOrderKind(RBE_ORDER_KIND_STOP_PRICE);
                }
                limitPrice = req.getPrice();

                if (!Boolean.valueOf(req.getWorkingStatus())) { 
                    triggerPrice = req.getTriggerPrice();
                    
                    // 取引種別からトリガー発動ゾーンを設定する
                    if (StringUtils.equals(req.getTradeCd(), DomesticStockTradeClass.MARGIN_REPAY_BUY.getId())) {
                        triggerZone = CODE_VAL_LATEST_TRIGGER_PRICE_GREATER_EQUAL;
                    } else if (StringUtils.equals(req.getTradeCd(), DomesticStockTradeClass.MARGIN_REPAY_SELL.getId())) {
                        triggerZone = CODE_VAL_LATEST_TRIGGER_PRICE_LESS_EQUAL;
                    }
                }
            } else {
                // 執行方法が逆指値以外の場合
                if (!Boolean.valueOf(req.getWorkingStatus())) {
                    inData.setRbeOrderKind(RBE_ORDER_KIND_NORMAL);
                }
                
                if (StringUtils.equals(req.getSasinariHouhou(), ExecuteMethod.LIMIT.getId())) {
                    // 執行方法が指値の場合
                    limitPrice = req.getPrice();
                }
            }
        } else if (StringUtils.equals(req.getOrderKind(), OrderClass.OCO.getId())) {
            // 注文種別がOCOの場合
            if (!Boolean.valueOf(req.getWorkingStatus())) {
                inData.setRbeOrderKind(RBE_ORDER_KIND_OCO);
            }
            inData.setSasinariKbn(req.getOco1SasinariJyouken());
            inData.setOcoSasinariKbn(req.getOco2GyakusasiJyouken());
            inData.setOcoPrice(StringUtils.defaultIfEmpty(StringUtils.leftPad(req.getOco2Price(), 10, "0"), PRICE_UNSPECIFIED));
            limitPrice = req.getOco1Price();

            if (!Boolean.valueOf(req.getWorkingStatus())) { 
                triggerPrice = req.getOco2TriggerPrice();
                
                // 取引種別からトリガー発動ゾーンを設定する
                if (StringUtils.equals(req.getTradeCd(), DomesticStockTradeClass.MARGIN_REPAY_BUY.getId())) {
                    triggerZone = CODE_VAL_LATEST_TRIGGER_PRICE_GREATER_EQUAL;
                } else if (StringUtils.equals(req.getTradeCd(), DomesticStockTradeClass.MARGIN_REPAY_SELL.getId())) {
                    triggerZone = CODE_VAL_LATEST_TRIGGER_PRICE_LESS_EQUAL;
                }
            }
        }
        if (Boolean.valueOf(req.getWorkingStatus())) {
            inData.setRbeOrderKind(RBE_ORDER_KIND_STOP_PRICE);
        }
        inData.setPrice(StringUtils.defaultIfEmpty(StringUtils.leftPad(limitPrice, 10, "0"), PRICE_UNSPECIFIED));
        inData.setTriggerZone(triggerZone);
        inData.setTriggerPrice(StringUtils.defaultIfEmpty(StringUtils.leftPad(triggerPrice, 10, "0"), PRICE_UNSPECIFIED));
        if (SELECT_MARKET_SOR.equals(req.getAfterCorrecMarket())) {
        	// "1"：訂正SOR
        	inData.setSorModifyCode(API001_SOR_MODIFY_CODE_CORRECT_SOR);
        } else {
        	// "△"：通常訂正
        	inData.setSorModifyCode(API001_SOR_MODIFY_CODE_NORMAL);
        }
        
        StockModifyOrderIn input = new StockModifyOrderIn();
        input.setIndata(inData);
        StockModifyOrderOutData apiRes = null;

        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        try {
            apiRes = apiWrapper.stockModifyOrder(input);
            apiErrorUtil.checkApiResponse(apiRes.getShubetu(), apiRes.getCode(), apiRes.getMessage());
            
        } catch (Exception e) {
            // APIエラーでシステムエラーの場合、受注日を登録してエラーレスポンスを返却する
            IfaMarginRepayOrderCorrectConfirmSqlModel sqlReq = new IfaMarginRepayOrderCorrectConfirmSqlModel();
            sqlReq.setIfaOrderNo(dtoReq.getIfaOrderNo());
            sqlReq.setIfaOrderSubNo(dtoReq.getIfaOrderSubNo());
            sqlReq.setUpdateUser(userAccount.getUserId());
           
            try {
                dao.updateIfaMarginRepayOrderCorrectConfirmSql002b(sqlReq);

            } finally {
                DataList<IfaMarginRepayOrderCorrectConfirmA001bResponseDto> dtoRes = IfaCommonUtil.createDataList(
                        List.of(),
                        ErrorLevel.FATAL,
                        ERRRORS_CMN_ORDEREXECUTIONMODIFY_EXECUTION_FAILED,
                        IfaCommonUtil.getMessage(ERRRORS_CMN_ORDEREXECUTIONMODIFY_EXECUTION_FAILED)
                );

                return dtoRes;
            }
        }
        
        IfaMarginRepayOrderCorrectConfirmSqlModel sqlReq = new IfaMarginRepayOrderCorrectConfirmSqlModel();
        sqlReq.setIfaOrderNo(dtoReq.getIfaOrderNo());
        sqlReq.setIfaOrderSubNo(dtoReq.getIfaOrderSubNo());
        sqlReq.setShubetu(apiRes.getShubetu());
        sqlReq.setCode(apiRes.getCode());
        sqlReq.setMessage(apiRes.getMessage());
        sqlReq.setUpdateUser(userAccount.getUserId());
        sqlReq.setAcceptDate(apiRes.getAcceptDate());
        sqlReq.setError(apiErrorUtil.isFatal());
        if (!apiErrorUtil.isFatal()) {
            sqlReq.setAcceptTime(apiRes.getAcceptTime());
            sqlReq.setEstimatePrice(apiRes.getEstimatePrice());
            sqlReq.setAmount(apiRes.getAmount());
            sqlReq.setCommission(apiRes.getCommission());
            sqlReq.setConsumptionTax(apiRes.getConsumptionTax());
            sqlReq.setCapitalGainTax(apiRes.getCapitalGainTax());
            sqlReq.setNetAmount(apiRes.getNetAmount());
            sqlReq.setCost(apiRes.getCost());
            sqlReq.setTradeDate(apiRes.getTradeDate());
            sqlReq.setSettlementDate(apiRes.getSettlementDate());
            sqlReq.setAcceptLimit(apiRes.getModLimit());
            sqlReq.setComIdR(apiRes.getComIdR());
            sqlReq.setAcPosition(apiRes.getAcPosition());
            sqlReq.setAcPositionAfter(apiRes.getAcPositionAfter());
            sqlReq.setAcBalance(apiRes.getAcBalance());
            sqlReq.setAcBalanceAfter(apiRes.getAcBalanceAfter());
            sqlReq.setTradeDeficitAmount(apiRes.getTradeDeficitAmount());
            sqlReq.setIsaBuyLimit(apiRes.getIsaBuyLimit());
            sqlReq.setJrnisaTransferAmount(apiRes.getJrnisaTransferAmount());
            sqlReq.setUnclosedQuantity(apiRes.getUnclosedQuantity());
            sqlReq.setUnclosedQuantityAfter(apiRes.getUnclosedQuantityAfter());
            sqlReq.setMarginCapability(apiRes.getMarginCapability());
            sqlReq.setMarginCapabilityAfter(apiRes.getMarginCapabilityAfter());
            sqlReq.setActualGrntRate(apiRes.getActualGrntRate());
            sqlReq.setActualGrntRateAfter(apiRes.getActualGrntRateAfter());
        }
        
        boolean isUpdateSuccess = false;
        try {
            // 訂正発注後の国内株式注文の更新
            if (dao.updateIfaMarginRepayOrderCorrectConfirmSql002(sqlReq) == 1) {
                // 更新件数が1件の場合に、DB更新が正常終了と判断する
                isUpdateSuccess = true;
            }
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("IfaMarginRepayOrderCorrectConfirmServiceImplL.correctionOrderA001b", e);
            }
        }

        if (apiErrorUtil.isFatal()) {
            // APIの結果がエラーの場合のレスポンス
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL,
                    ERRRORS_CMN_ORDEREXECUTIONMODIFY_EXECUTION_FAILED,
                    IfaCommonUtil.getMessage(ERRRORS_CMN_ORDEREXECUTIONMODIFY_EXECUTION_FAILED,
                            new String[] { apiRes.getCode(), apiRes.getMessage() }));
        }
        
        IfaMarginRepayOrderCorrectConfirmA001bResponseDto responseData = new IfaMarginRepayOrderCorrectConfirmA001bResponseDto();
        responseData.setRequest(req);
        responseData.setContractDate(apiRes.getTradeDate());
        responseData.setDeliveryDate(apiRes.getSettlementDate());
        responseData.setOrderDayTime(
                String.join(StringUtils.SPACE, new String[] { apiRes.getAcceptDate(), apiRes.getAcceptTime() }));
        responseData.setPositionPower(apiRes.getMarginCapabilityAfter());
        responseData.setCorrectSorOrderResultClassification(apiRes.getSorModifyStatus());
        
        if (!isUpdateSuccess) {
            
            apiErrorUtil.addDbError(MESSAGE_POST_ORDER_EXECUTION_WARNING, null);
        }

        if (CORRECT_SOR_ORDER_CLASSFICATION_SOR.equals(req.getCorrectSorOrderClassification()) && StringUtils.SPACE.equals(apiRes.getSorModifyStatus())) {
        	apiErrorUtil.addDbError(WARNINGS_FRS_OUT_OF_SOR_ORDER_COMPLETED, null);
        }

        return apiErrorUtil.createDataList(List.of(responseData), "");
    }
}
