package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct027Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMarginRepayOrderConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginRepayOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginRepayOrderConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginRepayOrderConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderConfirmA001aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderConfirmA001aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderConfirmA001bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderConfirmA001bResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderConfirmRepayPositionDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMarginRepayOrderConfirmService;
import com.sbisec.helios.ap.common.enums.DomesticMarginAccountType;
import com.sbisec.helios.ap.common.enums.DomesticStockTradeClass;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ExecuteMethod;
import com.sbisec.helios.ap.common.enums.MediateAbleTradeFlag;
import com.sbisec.helios.ap.common.enums.OrderClass;
import com.sbisec.helios.ap.common.enums.PeriodConditions;
import com.sbisec.helios.ap.common.enums.RepayMethod;
import com.sbisec.helios.ap.common.enums.SecurityMoneyClass;
import com.sbisec.helios.ap.common.enums.SelectMarket;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.enums.TradeSuspendFlag;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;
import com.sbisec.helios.ap.common.exception.SystemErrorException;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.AccountUtil;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.nri.ifa.NriApiService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import jp.co.sbisec.pcenter.dto.yanap.MarginLumpPlaceOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.MarginLumpPlaceOrderInVec;
import jp.co.sbisec.pcenter.dto.yanap.MarginLumpPlaceOrderOutData;
import jp.co.sbisec.pcenter.dto.yanap.MarginLumpPlaceOrderSortInData;
import jp.co.sbisec.pcenter.dto.yanap.MarginLumpPlaceOrderSortOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1OoutVec;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1OutData;


/**
 * 画面ID：SUB0202_0212-04_2
 * 画面名：信用返済注文確認
 * 2024/04/04 新規作成
 *
 * @author 宇田川達弥
 *
 */
@Component(value = "cmpIfaMarginRepayOrderConfirmService")
public class IfaMarginRepayOrderConfirmServiceImpL implements IfaMarginRepayOrderConfirmService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMarginRepayOrderConfirmServiceImpL.class);
    
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
    
    /** メッセージID:指定建玉残高明細存在チェックエラー */
    private static final String MESSAGE_POSITION_NOT_FOUND = "errors.dms.orderablePosition.notFound";
    
    /** メッセージID:指定建玉残高明細存在チェックエラー */
    private static final String MESSAGE_POSITION_EXCEEDED = "errors.dms.orderablePosition.exceeded";
    
    /** メッセージID:返済建玉数チェックエラー */
    private static final String MESSAGE_POSITION_TOO_MANY = "errors.dms.orderablePosition.tooMany";
    
    /** メッセージID:DB登録エラー */
    private static final String MESSAGE_PRE_ORDER_EXECUTION_FAILED = "errors.frs.preOrderExecution.failed";
    
    /** メッセージID:注文発注エラー */
    private static final String MESSAGE_ORDER_EXECUTION_FAILED = "errors.cmn.orderExecution.failed";
    
    /** メッセージID:注文結果DB登録失敗ワーニング */
    private static final String MESSAGE_POST_ORDER_EXECUTION_WARNING = "warnings.frs.postOrderExecution.completed";
    
    /** 区分ID:対象取引（メッセージ表示用）　*/
    private static final String CODE_ID_MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 区分ID:契約締結前交付書面コード */
    private static final String CODE_ID_PRE_CONTRACT_DOC_CODE = "PRE_CONTRACT_DOC_CODE";
    
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
    
    /** 区分値：新規建売買区分.売建 */
    private static final String CODE_VAL_NEW_CREDIT_SELL_BUY_TYPE_SELL = "1";
    
    /** 区分値：新規建売買区分.買建 */
    private static final String CODE_VAL_NEW_CREDIT_SELL_BUY_TYPE_BUY = "0";
    
    /** 区分値：返済順序.評価益順 */
    private static final String CODE_VAL_REPAY_ORDER_EVAL_PROFIT_DESC = "61";
    
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
    
    /** 区分値：新規市場.東証　*/
    private static final String CODE_VAL_NEW_MARKET_TSE_INT = "0";
    
    /** 区分値：新規市場.PTS　*/
    private static final String CODE_VAL_NEW_MARKET_PTS_INT = "7";
    
    /** 外部コード：新規市場.TSE　*/
    private static final String CODE_VAL_NEW_MARKET_TSE_EXT = "TKY";
    
    /** 外部コード：新規市場.PTS　*/
    private static final String CODE_VAL_NEW_MARKET_PTS_EXT = "PTS";
    
    /** 規制銘柄 */
    private static final String REGULATION_BRAND = "1";
    
    /** 新規市場.ALL */
    private static final String OPEN_MARKET_ALL = "ALL";
    
    /** RBE注文種別.通常注文 */
    private static final String RBE_ORDER_KIND_NORMAL = "   ";
    
    /** RBE注文種別.逆指値注文 */
    private static final String RBE_ORDER_KIND_STOP_PRICE = "SLO";
    
    /** RBE注文種別.OCO注文 */
    private static final String RBE_ORDER_KIND_OCO = "OCO";
    
    /** トリガ値段.通常注文 */
    private static final String PRICE_UNSPECIFIED = "0000000000";
    
    /** 譲渡益税区分.申告分離 */
    private static final String CAPITAL_GAIN_TAX_KBN_SEPARATE = "2";
    
    /** 受渡方法.信用決済 */
    private static final String UKEW_HOHO_MARGIN = "7";
    
    /** 受付経路区分.支店 */
    private static final String CALLCENTER_KBN_BRANCH = "0";
    
    /** IPアドレス.ifap */
    private static final String IP_ADDRESS_IFAP = "ifap";
    
    /** SOR受注時板乗せ市場区分.SOR-東証板乗せ注文 */
    private static final String SOR_LAST_MARKET_TSE = "tse";
    
    /** SOR受注時板乗せ市場区分.SOR対象外 */
    private static final String SOR_LAST_MARKET_NOT_SOR = "   ";
    
    /** APIタイプ:EC-GW */
    private static final String API_TYPE_EC_GW = "EC-GW";
    
    /** 返済建玉明細発注MAX件数 */
    private static final int REPAY_POSITION_COUNT_MAX = 999;
    
    /** APIリクエストのトランザクションIDのフィールド長 */
    private static final int TRANSACTION_ID_LENGTH = 32;
    
    /** APIリクエストの通番のフィールド長 */
    private static final int COMMAND_NUM_LENGTH = 7;
    
    /** APIリクエストのアカウントIDのフィールド長 */
    private static final int ACCOUNT_ID_LENGTH = 11;
    
    /** APIリクエストのアカウント毎の通番のフィールド長 */
    private static final int ORDER_ID_NUMBER_LENGTH = 7;
    
    /** APIリクエストの摘要のフィールド長 */
    private static final int SUMMARY_LENGTH = 30;
    
    /** APIリクエストの決済方法のフィールド長 */
    private static final int PAYMENT_METHOD_LENGTH = 10;
    
    /** APIリクエストの振込先銀行名のフィールド長 */
    private static final int BANK_NAME_LENGTH = 20;
    
    /** APIリクエストの注文株数のフィールド長 */
    private static final int QUANTITY_LENGTH = 8;
    
    /** APIリクエストの返済建玉カウントフィールド長 */
    private static final int LUMP_COUNT_LENGTH = 4;
    
    /** APIリクエストの新規単価のフィールド長 */
    private static final int NEW_PRICE_LENGTH = 12;
    
    /** APIリクエストのOCO指値のフィールド長 */
    private static final int OCO_PRICE_LENGTH = 10;

    /** APIリクエストのIPアドレスのフィールド長 */
    private static final int IP_ADDRESS_LENGTH = 39;

    /** 取得単価の除数 */
    private static final BigDecimal OPEN_PRICE_DIVISOR = new BigDecimal("100");
    
    /** 弁済期限 日計り(A) */
    private static final String PAYMENT_DDEADLINE_A = "A";

    /** 利用者顧客参照権限チェック */
    @Autowired
    Fct001 fct001;
    
    /** 取引コース媒介可否チェック */
    @Autowired
    Fct003 fct003;
    
    /** 取引制限チェック */
    @Autowired
    Fct021 fct021;
    
    /** 国内株式情報取得 */
    @Autowired
    Fct027 fct027;
    
    /** コードリストService */
    @Autowired
    CodeListService codeListService;
    
    /** NRI-API呼出サービス */
    @Autowired
    private NriApiService nriApiService;
    
    /** 信用返済注文確認Dao */
    @Autowired
    private IfaMarginRepayOrderConfirmDao dao;
    

    /**
     * アクションID：A001a
     * アクション名：注文発注
     * Dto リクエスト：IfaMarginRepayOrderConfirmA001aRequestDto
     * Dto レスポンス：IfaMarginRepayOrderConfirmA001aResponseDto
     * model リクエスト：IfaMarginRepayOrderConfirmSql001RequestModel
     * model レスポンス：IfaMarginRepayOrderConfirmSql001ResponseModel
     *
     * @param dtoReq 注文発注リクエスト
     * @return 注文発注レスポンス
     * @exception Exception 例外が発生した場合
     */
    @Transactional
    @Override
    public DataList<IfaMarginRepayOrderConfirmA001aResponseDto> orderPlacementA001a(
            IfaMarginRepayOrderConfirmA001aRequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginRepayOrderConfirmServiceImplL.orderPlacementA001a");
        }
        
        // 顧客共通情報の取得
        final CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        // 口座に対する権限チェック
        final InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(customerCommon.getButenCode());
        inputFct001Dto.setAccountNumber(customerCommon.getAccountNumber());
        
        final OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        
        if (StringUtils.equals(outputFct001Dto.getTargetCustomerRefAuthFlag(),
                TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId())) {
            // 対象顧客参照権限有無が権限なしの場合、権限なしエラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(MESSAGE_ACCOUNT_NOT_EXISTS,
                            new String[] { customerCommon.getButenCode(), customerCommon.getAccountNumber() }));
        } else if (StringUtils.equals(outputFct001Dto.getTradeSuspendFlag(), TradeSuspendFlag.SUSPEND.getId())) {
            // 取引停止口座の場合、取引停止エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_OUT_OF_SERVICE,
                    IfaCommonUtil.getMessage(MESSAGE_OUT_OF_SERVICE));
        }
        
        // 媒介可否チェック
        final InputFct003Dto inputFct003Dto = new InputFct003Dto();
        
        inputFct003Dto.setButenCode(customerCommon.getButenCode());
        inputFct003Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct003Dto.setProductCd(CODE_VAL_SECURITY_CLASS_DSTOCK);
        inputFct003Dto.setTradeCd(dtoReq.getTradeCd());
        
        final OutputFct003Dto outputFct003Dto = fct003.doCheck(inputFct003Dto);
        
        // 媒介可否リスト.媒介可否に媒介可が存在するか判定する
        if (outputFct003Dto.getMediateProprietyList().stream()
                .map(mediateProprieties -> mediateProprieties.getMediatePropriety()).noneMatch(
                        mediatePropriety -> StringUtils.equals(MediateAbleTradeFlag.ARI.getId(), mediatePropriety))) {
            // 存在しない場合、媒介不可エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_MEDIATE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(MESSAGE_MEDIATE_UNAVAILABLE, new String[] { codeListService
                            .getValue(CODE_ID_MSG_DISPLAY_TARGET_TRADE, CODE_VAL_MSG_DISPLAY_TARGET_TRADE_DMARGIN) }));
        }
        
        if (!StringUtils.equals(customerCommon.getDomesticMarginAccountType(),
                DomesticMarginAccountType.MARGIN_ACCOUNT.getId())) {
            // 国内信用口座開設状況が開設済ではない場合、信用口座未開設エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_DMARGIN_ACCOUNT_NOT_OPENED,
                    IfaCommonUtil.getMessage(MESSAGE_DMARGIN_ACCOUNT_NOT_OPENED));
        }
        
        // 口座の取引制限チェック
        final InputFct021Dto inputFct021Dto = new InputFct021Dto();
        
        inputFct021Dto.setButenCode(customerCommon.getButenCode());
        inputFct021Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct021Dto.setProductCd(SecurityMoneyClass.DOMESTIC_STOCKS.getId());
        inputFct021Dto.setTradeCd(dtoReq.getTradeCd());
        inputFct021Dto.setCountryCd(CODE_VAL_NATIONALITY_CODE_99);
        inputFct021Dto.setCurrencyCode(CODE_VAL_CURRENCY_CODE_999);
        inputFct021Dto.setTradeRestrictChkMarket(dtoReq.getOrderMarket());
        inputFct021Dto.setPaymentLimit(dtoReq.getPaymentDeadline());
        final OutputFct021Dto outputFct021Dto = fct021.doCheck(inputFct021Dto);
        
        // 口座の取引制限チェック結果で、該当する場合エラーを返す条件
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
        
        // 注意情報、お知らせのアラートがあるかをそれぞれ確認し、アラートがある場合には対応する項目のチェックボックスの状態を確認する
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
        
        // 国内株式情報を取得する
        final InputFct027Dto inputFct027Dto = new InputFct027Dto();
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
        
        // リクエストタイプ(返済順序)を返済方法から設定する
        String requestType = StringUtils.EMPTY;
        if (StringUtils.equalsAny(dtoReq.getRepayMethod(),
                new String[] { RepayMethod.INDIVIDUAL.getId(), RepayMethod.INDIVIDUAL_ONLY.getId() })) {
            requestType = CODE_VAL_REPAY_ORDER_EVAL_PROFIT_DESC;
        } else if (StringUtils.equals(dtoReq.getRepayMethod(), RepayMethod.BATCH.getId())) {
            requestType = dtoReq.getRepaymentOrder();
        }
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 建玉残高明細を取得する
        List<QueryMarginContract1OutData> api003ResList = nriApiService.queryMarginContract1List(customerCommon.getButenCode(),
                customerCommon.getAccountNumber(), dtoReq.getBrandCode(), dtoReq.getOpenTradeKbn(), OPEN_MARKET_ALL,
                dtoReq.getPaymentDeadline(), requestType);
        
        for (QueryMarginContract1OutData outdata : api003ResList) {
            apiErrorUtil.checkApiResponse(outdata.getShubetu(), outdata.getCode(), outdata.getMessage());
        }
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(Collections.emptyList(), MESSAGE_ORDER_EXECUTION_FAILED);
        }
        List<QueryMarginContract1OoutVec> api003PositionList = List.of();
        if (CollectionUtils.isNotEmpty(api003ResList)) {
            api003PositionList = api003ResList.stream()
                    .flatMap(api003Res -> api003Res.getQueryMarginContract1Data().stream())
                    .collect(Collectors.toList());
        }
        
        if (StringUtils.equalsAny(dtoReq.getRepayMethod(),
                new String[] { RepayMethod.INDIVIDUAL.getId(), RepayMethod.INDIVIDUAL_ONLY.getId() })) {
            // 返済方法が「個別指定」、または「個別指定（単独）」の場合
            for (IfaMarginRepayOrderConfirmRepayPositionDetail repayPosition : dtoReq.getRepayPositionDetail()) {
                boolean isNoneMatch = true;
                for (QueryMarginContract1OoutVec api003Position : api003PositionList) {
                    /*
                     * API003レスポンス.建玉明細.建市場　　　　　　 =　リクエスト.返済建玉明細.建市場
                     * API003レスポンス.建玉明細.新規約定日　　　 =　リクエスト.返済建玉明細.新規約定日
                     * API003レスポンス.建玉明細.親株新規約定日　=　リクエスト.返済建玉明細.親株新規約定日
                     * API003レスポンス.建玉明細.取得単価/100　　=　リクエスト.返済建玉明細.新規単価
                     * に該当するか判定する.
                     * 新規約定日、親株新規約定日は、API003レスポンスがYYYYMMDD形式であることを考慮して判定する
                     */
                    if (StringUtils.equals(api003Position.getBargainMarket(), repayPosition.getBuiltMarket())
                            && StringUtils.equals(api003Position.getOpenTradeDate(),
                                    DateFormatUtil.dateFormatToYmdNoSign(repayPosition.getConstructionDate()))
                            && StringUtils.equals(api003Position.getOrgNewTradeDate(),
                                    DateFormatUtil.dateFormatToYmdNoSign(repayPosition.getParentStockTradeDate()))
                            && new BigDecimal(api003Position.getOpenPrice()).divide(OPEN_PRICE_DIVISOR)
                                    .compareTo(new BigDecimal(repayPosition.getNewPrice())) == 0) {
                        isNoneMatch = false;
                        break;
                    }
                }
                
                if (isNoneMatch) {
                    // 返済建玉明細に該当する建玉明細が存在しない場合、指定建玉残高明細存在チェックエラー を返す
                    return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_POSITION_NOT_FOUND,
                            IfaCommonUtil.getMessage(MESSAGE_POSITION_NOT_FOUND));
                }
            }
        } else if (StringUtils.equals(dtoReq.getRepayMethod(), RepayMethod.BATCH.getId())) {
            // 返済方法が「一括指定」の場合
            if (CollectionUtils.isNotEmpty(api003ResList)) {
                if (new BigDecimal(dtoReq.getQuantity()).compareTo(api003PositionList.stream()
                        .map(marginContract1Data -> new BigDecimal(marginContract1Data.getContPosition())
                                .subtract(new BigDecimal(marginContract1Data.getUnactualQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)) > 0) {
                    // リクエスト.数量が建玉明細の(残高数量-返済注文済未出来数量)の総和を上回る場合、注文数量チェックエラーを返す
                    return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_POSITION_EXCEEDED,
                            IfaCommonUtil.getMessage(MESSAGE_POSITION_EXCEEDED));
                }
            }
        }
        
        String ifaOrderNo = null;
        try {
            // IFA注文番号を取得する
            ifaOrderNo = dao.selectIfaMarginRepayOrderConfirmSql004();
        } catch (Exception e) {
            // IFA注文番号が取得できない場合は登録出来ないため、DB登録エラーを返す。
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_PRE_ORDER_EXECUTION_FAILED,
                    IfaCommonUtil.getMessage(MESSAGE_PRE_ORDER_EXECUTION_FAILED));
        }
        
        // 注文発注前の国内株式注文データを作成する
        final IfaMarginRepayOrderConfirmSql001RequestModel insSql001Req = new IfaMarginRepayOrderConfirmSql001RequestModel();
        insSql001Req.setIfaOrderNo(ifaOrderNo);
        insSql001Req.setButenCode(customerCommon.getButenCode());
        insSql001Req.setAccountNumber(customerCommon.getAccountNumber());
        insSql001Req.setCustomerId(customerCommon.getCustomerCode());
        insSql001Req.setSpecificKbn(customerCommon.getSpecificAccountType());
        insSql001Req.setBrandCode(dtoReq.getBrandCode());
        insSql001Req.setMarket(dtoReq.getOrderMarket());
        insSql001Req.setTradeKbn(dtoReq.getTradeCd());
        insSql001Req.setQuantity(dtoReq.getQuantity());
        insSql001Req.setPaymentLimit(dtoReq.getPaymentDeadline());
        insSql001Req.setRepaymentMethodType(dtoReq.getRepayMethod());
        insSql001Req.setRequestType(dtoReq.getRepaymentOrder());
        insSql001Req.setOrderSyubetsu(dtoReq.getOrderKind());
        insSql001Req.setKanyuKbn(dtoReq.getKanyuKbn());
        insSql001Req.setReceiveOrder(dtoReq.getOrderMethod());
        insSql001Req.setCheckInsider(dtoReq.getCheckInsider());
        insSql001Req.setCheckSor(dtoReq.getCheckSor());
        insSql001Req.setTesuuryouKbn(codeListService.convertKeyToExtKey(CODE_ID_PRE_CONTRACT_DOC_CODE, API_TYPE_EC_GW,
                customerCommon.getCustomerAttribute()));
        insSql001Req.setCapitalGainTaxKbn(CAPITAL_GAIN_TAX_KBN_SEPARATE);

        // リクエスト.弁済期限が日計り(A)の場合
        if (PAYMENT_DDEADLINE_A.equals(dtoReq.getPaymentDeadline())) {
            if ("1".equals(outputFct027Dto.getPremiumShortSaleCcategory())) {
                insSql001Req.setDailyCreditKbn("2");
            } else {
                insSql001Req.setDailyCreditKbn("1");
            }
        // リクエスト.弁済期限が日計り(A)以外の場合
        } else {
            insSql001Req.setDailyCreditKbn(" ");
        }
        if (api003ResList != null && api003ResList.size() != 0) {
            insSql001Req.setPaymentDeadlineDate(api003ResList.get(0).getIppanMgPaymentLimit());
            insSql001Req.setDateKbn(api003ResList.get(0).getIppanMgPaymentKbn());
        }

        insSql001Req.setBrokerCode(customerCommon.getBrokerCode());
        insSql001Req.setIntermediaryEmpCd(customerCommon.getBrokerChargeCode());
        
        if (StringUtils.equals(dtoReq.getPeriodTerms(), PeriodConditions.TODAY.getId())) {
            // 期間条件が当日中の場合、期間を桁数分スペース埋めにする
            insSql001Req.setLimit(StringUtils.repeat(StringUtils.SPACE, DateFormatUtil.YYYYMMDD.length()));
        } else {
            // それ以外の場合、期間.日付をYYYYMMDD形式で設定する
            insSql001Req.setLimit(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getLimit()));
        }
        
        String limitPrice = null;
        String triggerZone = StringUtils.SPACE;
        String triggerPrice = null;
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())) {
            // 注文種別が通常/逆指値の場合
            insSql001Req.setSashinariKbn(dtoReq.getSasinariJyouken());
            insSql001Req.setOcoSashinariKbn(StringUtils.SPACE);
            if (StringUtils.equals(dtoReq.getSasinariHouhou(), ExecuteMethod.STOP.getId())) {
                // 執行方法が逆指値の場合
                insSql001Req.setOrderSyubetsuList(CODE_VAL_LIST_ORDER_CLASS_STOP_PRICE);
                insSql001Req.setRbeOrderKind(RBE_ORDER_KIND_STOP_PRICE);
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
                insSql001Req.setOrderSyubetsuList(CODE_VAL_LIST_ORDER_CLASS_NORMAL);
                insSql001Req.setRbeOrderKind(RBE_ORDER_KIND_NORMAL);
                
                if (StringUtils.equals(dtoReq.getSasinariHouhou(), ExecuteMethod.LIMIT.getId())) {
                    // 執行方法が指値の場合
                    limitPrice = dtoReq.getPrice();
                }
            }
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
            // 注文種別がOCOの場合
            insSql001Req.setOrderSyubetsuList(CODE_VAL_LIST_ORDER_CLASS_OCO);
            insSql001Req.setRbeOrderKind(RBE_ORDER_KIND_OCO);
            insSql001Req.setSashinariKbn(dtoReq.getOco1SasinariJyouken());
            insSql001Req.setOcoSashinariKbn(dtoReq.getOco2GyakusasiJyouken());
            insSql001Req.setOcoSashine(StringUtils.defaultString(dtoReq.getOco2Price(), PRICE_UNSPECIFIED));
            limitPrice = dtoReq.getOco1Price();
            triggerPrice = dtoReq.getOco2TriggerPrice();
            
            // 取引種別からトリガー発動ゾーンを設定する
            if (StringUtils.equals(dtoReq.getTradeCd(), DomesticStockTradeClass.MARGIN_REPAY_BUY.getId())) {
                triggerZone = CODE_VAL_LATEST_TRIGGER_PRICE_GREATER_EQUAL;
            } else if (StringUtils.equals(dtoReq.getTradeCd(), DomesticStockTradeClass.MARGIN_REPAY_SELL.getId())) {
                triggerZone = CODE_VAL_LATEST_TRIGGER_PRICE_LESS_EQUAL;
            }
        }
        insSql001Req.setLimitPrice(StringUtils.defaultString(limitPrice, PRICE_UNSPECIFIED));
        insSql001Req.setTriggerZone(triggerZone);
        insSql001Req.setTriggerPrice(StringUtils.defaultString(triggerPrice, PRICE_UNSPECIFIED));
        
        // ユーザ共通情報の取得
        final UserAccount userAccount = IfaCommonUtil.getUserAccount();
        insSql001Req.setUserId(userAccount.getCcsUserId());
        insSql001Req.setCreateUser(userAccount.getUserId());
        insSql001Req.setUpdateUser(userAccount.getUserId());
        
        try {
            // 注文発注前の国内株式注文データを登録する
            final int insSql001Res = dao.insertIfaMarginRepayOrderConfirmSql001(insSql001Req);
            
            if (insSql001Res != 1) {
                // 登録結果が1件にならない場合は実行時例外を通知する
                throw new IfaRuntimeException(MESSAGE_PRE_ORDER_EXECUTION_FAILED);
            }
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("IfaMarginRepayOrderConfirmServiceImplL.orderPlacementA001a", e);
            }
            //DB登録NG：DB登録エラーを返す。ロールバックのためSystemErrorExceptionにdataListをラップする
            throw new SystemErrorException(IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL,
                    MESSAGE_PRE_ORDER_EXECUTION_FAILED, IfaCommonUtil.getMessage(MESSAGE_PRE_ORDER_EXECUTION_FAILED)));
        }
        
        if (StringUtils.equalsAny(dtoReq.getRepayMethod(),
                new String[] { RepayMethod.INDIVIDUAL.getId(), RepayMethod.INDIVIDUAL_ONLY.getId() })) {
            // 返済方法が「個別指定」、または「個別指定（単独）」の場合、注文発注前の返済指定建玉情報を作成する
            try {
                for (IfaMarginRepayOrderConfirmRepayPositionDetail repayPosition : dtoReq.getRepayPositionDetail()) {
                    final IfaMarginRepayOrderConfirmSql002RequestModel insSql002Req = new IfaMarginRepayOrderConfirmSql002RequestModel();
                    insSql002Req.setIfaOrderNo(ifaOrderNo);
                    insSql002Req.setBrandCode(dtoReq.getBrandCode());
                    insSql002Req.setPaymentLimit(dtoReq.getPaymentDeadline());
                    insSql002Req.setOrgNewTradeDate(
                            DateFormatUtil.dateFormatToYmdNoSign(repayPosition.getParentStockTradeDate()));
                    insSql002Req.setOpenTradeDate(
                            DateFormatUtil.dateFormatToYmdNoSign(repayPosition.getConstructionDate()));
                    insSql002Req.setOpenPrice(new BigDecimal(repayPosition.getNewPrice()).multiply(OPEN_PRICE_DIVISOR)
                            .stripTrailingZeros().toPlainString());
                    insSql002Req.setContPosition(repayPosition.getContPosition());
                    insSql002Req.setUnactualQuantity(repayPosition.getUnactualQuantity());
                    insSql002Req.setDepositType(repayPosition.getSpecificPositionType());
                    insSql002Req.setQuantity(repayPosition.getOrderQuantity());
                    insSql002Req.setCreateUser(userAccount.getUserId());
                    insSql002Req.setUpdateUser(userAccount.getUserId());
                    
                    // 新規売買区分を取引種別から設定する
                    if (StringUtils.equals(dtoReq.getTradeCd(), DomesticStockTradeClass.MARGIN_REPAY_BUY.getId())) {
                        insSql002Req.setOpenTradeKbn(CODE_VAL_NEW_CREDIT_SELL_BUY_TYPE_SELL);
                    } else if (StringUtils.equals(dtoReq.getTradeCd(),
                            DomesticStockTradeClass.MARGIN_REPAY_SELL.getId())) {
                        insSql002Req.setOpenTradeKbn(CODE_VAL_NEW_CREDIT_SELL_BUY_TYPE_BUY);
                    }
                    
                    // 新規市場を建市場から設定する
                    if (StringUtils.equals(repayPosition.getBuiltMarket(), CODE_VAL_NEW_MARKET_TSE_INT)) {
                        insSql002Req.setOpenMarket(CODE_VAL_NEW_MARKET_TSE_EXT);
                    } else if (StringUtils.equals(repayPosition.getBuiltMarket(), CODE_VAL_NEW_MARKET_PTS_INT)) {
                        insSql002Req.setOpenMarket(CODE_VAL_NEW_MARKET_PTS_EXT);
                    }
                    
                    // 注文発注前の信用返済指定建玉データを登録する
                    final int insSql002Res = dao.insertIfaMarginRepayOrderConfirmSql002(insSql002Req);
                    
                    if (insSql002Res != 1) {
                        // 作成データ1件に対する登録結果が1件にならない場合は実行時例外を通知する
                        throw new IfaRuntimeException(MESSAGE_PRE_ORDER_EXECUTION_FAILED);
                    }
                }
            } catch (Exception e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("IfaMarginRepayOrderConfirmServiceImplL.orderPlacementA001a", e);
                }
                //DB登録NG：DB登録エラーを返す。国内株式注文データを含めてロールバックするため、dataListをラップしたSystemErrorExceptionをthrowする
                throw new SystemErrorException(
                        IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_PRE_ORDER_EXECUTION_FAILED,
                                IfaCommonUtil.getMessage(MESSAGE_PRE_ORDER_EXECUTION_FAILED)));
            }
        }
        
        // 国内株式注文データ登録時に新規採番したIFA注文番号を返却する
        final IfaMarginRepayOrderConfirmA001aResponseDto responseData = new IfaMarginRepayOrderConfirmA001aResponseDto();
        responseData.setIfaOrderNo(ifaOrderNo);
        
        responseData.setWarningMessages(apiErrorUtil.getWarningMessages());
        return IfaCommonUtil.createDataList(List.of(responseData), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtils.EMPTY);
    }
    
    /**
     * アクションID：A001b
     * アクション名：注文発注
     * Dto リクエスト：IfaMarginRepayOrderConfirmA001bRequestDto
     * Dto レスポンス：IfaMarginRepayOrderConfirmA001bResponseDto
     * model リクエスト：IfaMarginRepayOrderConfirmSql002RequestModel
     * model レスポンス：IfaMarginRepayOrderConfirmSql002ResponseModel
     *
     * @param dtoReq 注文発注リクエスト
     * @return 注文発注レスポンス
     * @exception Exception 例外が発生した場合
     */
    @Override
    public DataList<IfaMarginRepayOrderConfirmA001bResponseDto> orderPlacementA001b(
            IfaMarginRepayOrderConfirmA001bRequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginRepayOrderConfirmServiceImplL.orderPlacementA001b");
        }
        
        // 顧客共通情報の取得
        final CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        // ユーザ共通情報の取得
        final UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // 注文発注の画面からのリクエスト内容
        final IfaMarginRepayOrderConfirmA001aRequestDto overallReq = dtoReq.getRequest();
        // 返済方法
        final String repayMethod = overallReq.getRepayMethod();
        
        // 返済方法別に発注API呼び出しと注文発注後の国内株式注文の更新データを作成する
        IfaMarginRepayOrderConfirmSql003RequestModel updSql003Req = null;
        MarginLumpPlaceOrderOutData api001Res = null;
        MarginLumpPlaceOrderSortOutData api002Res = null;
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        if (StringUtils.equalsAny(repayMethod,
                new String[] { RepayMethod.INDIVIDUAL.getId(), RepayMethod.INDIVIDUAL_ONLY.getId() })) {
            // 返済方法が「個別指定」、または「個別指定（単独）」の場合
            if (overallReq.getRepayPositionDetail().size() > REPAY_POSITION_COUNT_MAX) {
                // リクエスト.建玉明細が1000件以上の場合、返済建玉数チェックエラーを返す
                return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_POSITION_TOO_MANY,
                        IfaCommonUtil.getMessage(MESSAGE_POSITION_TOO_MANY));
            }
            
            // 信用一括返済注文のリクエスト情報を作成する
            MarginLumpPlaceOrderInData api001Req = new MarginLumpPlaceOrderInData();
            api001Req.setTransactionId(StringUtils.repeat(BigDecimal.ZERO.toPlainString(), TRANSACTION_ID_LENGTH));
            api001Req.setCommandNum(StringUtils.repeat(BigDecimal.ZERO.toPlainString(), COMMAND_NUM_LENGTH));
            api001Req.setCommandDate(
                    StringUtils.repeat(BigDecimal.ZERO.toPlainString(), DateFormatUtil.YYYYMMDD.length()));
            api001Req.setButenCd(customerCommon.getButenCode());
            api001Req.setKozaNo(AccountUtil.formatToApi(customerCommon.getAccountNumber()));
            api001Req.setAccountId(StringUtils.repeat(BigDecimal.ZERO.toPlainString(), ACCOUNT_ID_LENGTH));
            api001Req.setNumber(StringUtils.repeat(BigDecimal.ZERO.toPlainString(), ORDER_ID_NUMBER_LENGTH));
            api001Req.setOrigin(BigDecimal.ZERO.toPlainString());
            api001Req.setBrandCd(overallReq.getBrandCode());
            api001Req.setTradeKbn(overallReq.getTradeCd());
            api001Req.setSumQuantity(
                    StringUtils.leftPad(overallReq.getQuantity(), QUANTITY_LENGTH, BigDecimal.ZERO.toPlainString()));
            api001Req.setUkewHoho(UKEW_HOHO_MARGIN);
            api001Req.setMarket(overallReq.getOrderMarket());
            api001Req.setJoZeiKbn(CAPITAL_GAIN_TAX_KBN_SEPARATE);
            api001Req.setHitokuteiTradeKbn(StringUtils.SPACE);
            api001Req.setPaymentLimit(overallReq.getPaymentDeadline());
            api001Req.setSummary(StringUtils.repeat(StringUtils.SPACE, SUMMARY_LENGTH));
            api001Req.setPaymentKbn(StringUtils.SPACE);
            api001Req.setPaymentMethod(StringUtils.repeat(StringUtils.SPACE, PAYMENT_METHOD_LENGTH));
            api001Req.setBankKbn(StringUtils.SPACE);
            api001Req.setBankName(StringUtils.repeat(StringUtils.SPACE, BANK_NAME_LENGTH));
            api001Req.setCallcenterKbn(CALLCENTER_KBN_BRANCH);
            api001Req.setUserId(userAccount.getCcsUserId());
            api001Req.setVettingKbn(StringUtils.SPACE);
            api001Req.setCheckPrice(PRICE_UNSPECIFIED);
            api001Req.setComId(customerCommon.getCustomerAttribute());
            api001Req.setCheckId(StringUtils.SPACE);
            api001Req.setLumpCount(StringUtils.leftPad(overallReq.getRepayPositionCount(), LUMP_COUNT_LENGTH,
                    BigDecimal.ZERO.toPlainString()));
            api001Req.setIpAddress(StringUtils.rightPad(IP_ADDRESS_IFAP, IP_ADDRESS_LENGTH, StringUtils.SPACE));
            
            String limitPrice = PRICE_UNSPECIFIED;
            String triggerZone = StringUtils.SPACE;
            String triggerPrice = PRICE_UNSPECIFIED;
            if (StringUtils.equals(overallReq.getOrderKind(), OrderClass.NORMAL.getId())) {
                // 注文種別が通常/逆指値の場合
                api001Req.setSasinariKbn(overallReq.getSasinariJyouken());
                api001Req.setOcoSasinariKbn(StringUtils.SPACE);
                api001Req.setOcoPrice(StringUtils.repeat(StringUtils.SPACE, OCO_PRICE_LENGTH));
                if (StringUtils.equals(overallReq.getSasinariHouhou(), ExecuteMethod.STOP.getId())) {
                    // 執行方法が逆指値の場合
                    api001Req.setRbeOrderKind(RBE_ORDER_KIND_STOP_PRICE);
                    limitPrice = overallReq.getPrice();
                    triggerPrice = overallReq.getTriggerPrice();
                    
                    // 取引種別からトリガー発動ゾーンを設定する
                    if (StringUtils.equals(overallReq.getTradeCd(), DomesticStockTradeClass.MARGIN_REPAY_BUY.getId())) {
                        triggerZone = CODE_VAL_LATEST_TRIGGER_PRICE_GREATER_EQUAL;
                    } else if (StringUtils.equals(overallReq.getTradeCd(),
                            DomesticStockTradeClass.MARGIN_REPAY_SELL.getId())) {
                        triggerZone = CODE_VAL_LATEST_TRIGGER_PRICE_LESS_EQUAL;
                    }
                } else {
                    // 執行方法が逆指値以外の場合
                    api001Req.setRbeOrderKind(RBE_ORDER_KIND_NORMAL);
                    
                    if (StringUtils.equals(overallReq.getSasinariHouhou(), ExecuteMethod.LIMIT.getId())) {
                        // 執行方法が指値の場合
                        limitPrice = overallReq.getPrice();
                    }
                }
            } else if (StringUtils.equals(overallReq.getOrderKind(), OrderClass.OCO.getId())) {
                // 注文種別がOCOの場合
                api001Req.setRbeOrderKind(RBE_ORDER_KIND_OCO);
                api001Req.setSasinariKbn(overallReq.getOco1SasinariJyouken());
                api001Req.setOcoSasinariKbn(overallReq.getOco2GyakusasiJyouken());
                api001Req.setOcoPrice(StringUtils.defaultString(StringUtils.leftPad(overallReq.getOco2Price(),
                        PRICE_UNSPECIFIED.length(), BigDecimal.ZERO.toPlainString()), PRICE_UNSPECIFIED));
                limitPrice = overallReq.getOco1Price();
                triggerPrice = overallReq.getOco2TriggerPrice();
                
                // 取引種別からトリガー発動ゾーンを設定する
                if (StringUtils.equals(overallReq.getTradeCd(), DomesticStockTradeClass.MARGIN_REPAY_BUY.getId())) {
                    triggerZone = CODE_VAL_LATEST_TRIGGER_PRICE_GREATER_EQUAL;
                } else if (StringUtils.equals(overallReq.getTradeCd(),
                        DomesticStockTradeClass.MARGIN_REPAY_SELL.getId())) {
                    triggerZone = CODE_VAL_LATEST_TRIGGER_PRICE_LESS_EQUAL;
                }
            }
            api001Req.setPrice(StringUtils.defaultString(
                    StringUtils.leftPad(limitPrice, PRICE_UNSPECIFIED.length(), BigDecimal.ZERO.toPlainString()),
                    PRICE_UNSPECIFIED));
            api001Req.setTriggerZone(triggerZone);
            api001Req.setTriggerPrice(StringUtils.defaultString(
                    StringUtils.leftPad(triggerPrice, PRICE_UNSPECIFIED.length(), BigDecimal.ZERO.toPlainString()),
                    PRICE_UNSPECIFIED));
            
            if (StringUtils.equals(overallReq.getPeriodTerms(), PeriodConditions.TODAY.getId())) {
                // 期間条件が当日中の場合、期間を桁数分スペース埋めにする
                api001Req.setLimit(StringUtils.repeat(StringUtils.SPACE, DateFormatUtil.YYYYMMDD.length()));
            } else {
                // それ以外の場合、期間.日付をYYYYMMDD形式で設定する
                api001Req.setLimit(DateFormatUtil.dateFormatToYmdNoSign(overallReq.getLimit()));
            }
            
            if (StringUtils.equals(overallReq.getOrderMarket(), SelectMarket.SOR.getId())) {
                // 発注市場が当社優先市場／SORの場合
                api001Req.setSorLastMarket(SOR_LAST_MARKET_TSE);
            } else {
                // それ以外の場合
                api001Req.setSorLastMarket(SOR_LAST_MARKET_NOT_SOR);
            }
            
            api001Req.setMarginLumpPlaceData(overallReq.getRepayPositionDetail().stream().map(repayPosition -> {
                // 返済建玉情報を作成する
                final MarginLumpPlaceOrderInVec api001Position = new MarginLumpPlaceOrderInVec();
                
                api001Position.setOrgNewTradeDate(repayPosition.getParentStockTradeDate());
                api001Position.setOpenTradeDate(repayPosition.getConstructionDate());
                api001Position.setOpenPrice(
                        StringUtils.leftPad(new BigDecimal(repayPosition.getNewPrice()).multiply(OPEN_PRICE_DIVISOR)
                                .stripTrailingZeros().toPlainString(), NEW_PRICE_LENGTH, StringUtils.SPACE));
                api001Position.setQuantity(StringUtils.leftPad(repayPosition.getOrderQuantity(), QUANTITY_LENGTH,
                        BigDecimal.ZERO.toPlainString()));
                api001Position.setBargainMarket(repayPosition.getBuiltMarket());
                
                return api001Position;
            }).collect(Collectors.toList()));
            
            // 信用一括返済注文APIを呼び出す
            try {
                api001Res = nriApiService.marginLumpPlaceOrder(api001Req);
                apiErrorUtil.checkApiResponse(api001Res.getShubetu(), api001Res.getCode(), api001Res.getMessage());

            } catch (Exception e) {
                // システムエラーの場合、DBに登録してレスポンスを返却する
                updSql003Req = new IfaMarginRepayOrderConfirmSql003RequestModel();
                updSql003Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
                updSql003Req.setUpdateUser(userAccount.getUserId());

                try {
                    dao.updateIfaMarginRepayOrderConfirmSql003b(updSql003Req);

                } finally {
                    DataList<IfaMarginRepayOrderConfirmA001bResponseDto> dtoRes = IfaCommonUtil.createDataList(
                        List.of(),
                        ErrorLevel.FATAL,
                        MESSAGE_ORDER_EXECUTION_FAILED,
                        IfaCommonUtil.getMessage(MESSAGE_ORDER_EXECUTION_FAILED)
                    );
                    return dtoRes;
                }
            }
            
            // 注文発注後の国内株式注文の更新データを作成する
            updSql003Req = new IfaMarginRepayOrderConfirmSql003RequestModel();
            updSql003Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
            updSql003Req.setShubetsu(api001Res.getShubetu());
            updSql003Req.setErrorCode(api001Res.getCode());
            updSql003Req.setErrorMessage(api001Res.getMessage());
            updSql003Req.setUpdateUser(userAccount.getUserId());
            updSql003Req.setError(apiErrorUtil.isFatal());
            
            if (!apiErrorUtil.isFatal()) {
                // APIの応答が正常またはワーニングの場合の値を設定する
                updSql003Req.setOrderType(api001Res.getOrderType());
                updSql003Req.setOrderNum(api001Res.getOrderNum());
                updSql003Req.setAcceptDate(api001Res.getAcceptDate());
                updSql003Req.setAcceptTime(api001Res.getAcceptTime());
                updSql003Req.setEstimatePrice(api001Res.getEstimatePrice());
                updSql003Req.setAmount(api001Res.getAmount());
                updSql003Req.setCommission(api001Res.getCommission());
                updSql003Req.setConsumptionTax(api001Res.getConsumptionTax());
                updSql003Req.setCapitalGainTax(api001Res.getCapitalGainTax());
                updSql003Req.setNetAmount(api001Res.getNetAmount());
                updSql003Req.setCost(api001Res.getCost());
                updSql003Req.setTradeDate(api001Res.getTradeDate());
                updSql003Req.setSettlementDate(api001Res.getSettlementDate());
                updSql003Req.setAcceptLimit(api001Res.getAcceptLimit());
                updSql003Req.setComIdR(api001Res.getComIdR());
                updSql003Req.setOrderedMarket(api001Res.getOrderedMarket());
                updSql003Req.setSorLinkKbn(api001Res.getSorLinkKbn());
                updSql003Req.setUnclosedQuantity(api001Res.getUnclosedQuantity());
                updSql003Req.setUnclosedQuantityAfter(api001Res.getUnclosedQuantityAfter());
                updSql003Req.setMarginCapability(api001Res.getMarginCapability());
                updSql003Req.setMarginCapabilityAfter(api001Res.getMarginCapabilityAfter());
                updSql003Req.setActualGrntRate(api001Res.getActualGrntRate());
                updSql003Req.setActualGrntRateAfter(api001Res.getActualGrntRateAfter());
            }
        } else if (StringUtils.equals(repayMethod, RepayMethod.BATCH.getId())) {
            // 返済方法が「一括指定」の場合、返済順序指定信用一括返済注文のリクエスト情報を作成する
            MarginLumpPlaceOrderSortInData api002Req = new MarginLumpPlaceOrderSortInData();
            api002Req.setTransactionId(StringUtils.repeat(BigDecimal.ZERO.toPlainString(), TRANSACTION_ID_LENGTH));
            api002Req.setCommandNum(StringUtils.repeat(BigDecimal.ZERO.toPlainString(), COMMAND_NUM_LENGTH));
            api002Req.setCommandDate(
                    StringUtils.repeat(BigDecimal.ZERO.toPlainString(), DateFormatUtil.YYYYMMDD.length()));
            api002Req.setButenCd(customerCommon.getButenCode());
            api002Req.setKozaNo(AccountUtil.formatToApi(customerCommon.getAccountNumber()));
            api002Req.setAccountId(StringUtils.repeat(BigDecimal.ZERO.toPlainString(), ACCOUNT_ID_LENGTH));
            api002Req.setNumber(StringUtils.repeat(BigDecimal.ZERO.toPlainString(), ORDER_ID_NUMBER_LENGTH));
            api002Req.setOrigin(BigDecimal.ZERO.toPlainString());
            api002Req.setBrandCd(overallReq.getBrandCode());
            api002Req.setTradeKbn(overallReq.getTradeCd());
            api002Req.setSumQuantity(
                    StringUtils.leftPad(overallReq.getQuantity(), QUANTITY_LENGTH, BigDecimal.ZERO.toPlainString()));
            api002Req.setUkewHoho(UKEW_HOHO_MARGIN);
            api002Req.setMarket(overallReq.getOrderMarket());
            api002Req.setJoZeiKbn(CAPITAL_GAIN_TAX_KBN_SEPARATE);
            api002Req.setHitokuteiTradeKbn(StringUtils.SPACE);
            api002Req.setPaymentLimit(overallReq.getPaymentDeadline());
            api002Req.setSummary(StringUtils.repeat(StringUtils.SPACE, SUMMARY_LENGTH));
            api002Req.setPaymentKbn(StringUtils.SPACE);
            api002Req.setPaymentMethod(StringUtils.repeat(StringUtils.SPACE, PAYMENT_METHOD_LENGTH));
            api002Req.setBankKbn(StringUtils.SPACE);
            api002Req.setBankName(StringUtils.repeat(StringUtils.SPACE, BANK_NAME_LENGTH));
            api002Req.setCallcenterKbn(CALLCENTER_KBN_BRANCH);
            api002Req.setUserId(userAccount.getCcsUserId());
            api002Req.setVettingKbn(StringUtils.SPACE);
            api002Req.setCheckPrice(PRICE_UNSPECIFIED);
            api002Req.setComId(customerCommon.getCustomerAttribute());
            api002Req.setCheckId(StringUtils.SPACE);
            api002Req.setRequestType(overallReq.getRepaymentOrder());
            api002Req.setIpAddress(StringUtils.rightPad(IP_ADDRESS_IFAP, IP_ADDRESS_LENGTH, StringUtils.SPACE));
            
            String limitPrice = PRICE_UNSPECIFIED;
            String triggerZone = StringUtils.SPACE;
            String triggerPrice = PRICE_UNSPECIFIED;
            if (StringUtils.equals(overallReq.getOrderKind(), OrderClass.NORMAL.getId())) {
                // 注文種別が通常/逆指値の場合
                api002Req.setSasinariKbn(overallReq.getSasinariJyouken());
                api002Req.setOcoSasinariKbn(StringUtils.SPACE);
                api002Req.setOcoPrice(StringUtils.repeat(StringUtils.SPACE, OCO_PRICE_LENGTH));
                if (StringUtils.equals(overallReq.getSasinariHouhou(), ExecuteMethod.STOP.getId())) {
                    // 執行方法が逆指値の場合
                    api002Req.setRbeOrderKind(RBE_ORDER_KIND_STOP_PRICE);
                    limitPrice = overallReq.getPrice();
                    triggerPrice = overallReq.getTriggerPrice();
                    
                    // 取引種別からトリガー発動ゾーンを設定する
                    if (StringUtils.equals(overallReq.getTradeCd(), DomesticStockTradeClass.MARGIN_REPAY_BUY.getId())) {
                        triggerZone = CODE_VAL_LATEST_TRIGGER_PRICE_GREATER_EQUAL;
                    } else if (StringUtils.equals(overallReq.getTradeCd(),
                            DomesticStockTradeClass.MARGIN_REPAY_SELL.getId())) {
                        triggerZone = CODE_VAL_LATEST_TRIGGER_PRICE_LESS_EQUAL;
                    }
                } else {
                    // 執行方法が逆指値以外の場合
                    api002Req.setRbeOrderKind(RBE_ORDER_KIND_NORMAL);
                    
                    if (StringUtils.equals(overallReq.getSasinariHouhou(), ExecuteMethod.LIMIT.getId())) {
                        // 執行方法が指値の場合
                        limitPrice = overallReq.getPrice();
                    }
                }
            } else if (StringUtils.equals(overallReq.getOrderKind(), OrderClass.OCO.getId())) {
                // 注文種別がOCOの場合
                api002Req.setRbeOrderKind(RBE_ORDER_KIND_OCO);
                api002Req.setSasinariKbn(overallReq.getOco1SasinariJyouken());
                api002Req.setOcoSasinariKbn(overallReq.getOco2GyakusasiJyouken());
                api002Req.setOcoPrice(StringUtils.defaultString(StringUtils.leftPad(overallReq.getOco2Price(),
                        PRICE_UNSPECIFIED.length(), BigDecimal.ZERO.toPlainString()), PRICE_UNSPECIFIED));
                limitPrice = overallReq.getOco1Price();
                triggerPrice = overallReq.getOco2TriggerPrice();
                
                // 取引種別からトリガー発動ゾーンを設定する
                if (StringUtils.equals(overallReq.getTradeCd(), DomesticStockTradeClass.MARGIN_REPAY_BUY.getId())) {
                    triggerZone = CODE_VAL_LATEST_TRIGGER_PRICE_GREATER_EQUAL;
                } else if (StringUtils.equals(overallReq.getTradeCd(),
                        DomesticStockTradeClass.MARGIN_REPAY_SELL.getId())) {
                    triggerZone = CODE_VAL_LATEST_TRIGGER_PRICE_LESS_EQUAL;
                }
            }
            api002Req.setPrice(StringUtils.defaultString(
                    StringUtils.leftPad(limitPrice, PRICE_UNSPECIFIED.length(), BigDecimal.ZERO.toPlainString()),
                    PRICE_UNSPECIFIED));
            api002Req.setTriggerZone(triggerZone);
            api002Req.setTriggerPrice(StringUtils.defaultString(
                    StringUtils.leftPad(triggerPrice, PRICE_UNSPECIFIED.length(), BigDecimal.ZERO.toPlainString()),
                    PRICE_UNSPECIFIED));
            
            if (StringUtils.equals(overallReq.getPeriodTerms(), PeriodConditions.TODAY.getId())) {
                // 期間条件が当日中の場合、期間を桁数分スペース埋めにする
                api002Req.setLimit(StringUtils.repeat(StringUtils.SPACE, DateFormatUtil.YYYYMMDD.length()));
            } else {
                // それ以外の場合、期間.日付をYYYYMMDD形式で設定する
                api002Req.setLimit(DateFormatUtil.dateFormatToYmdNoSign(overallReq.getLimit()));
            }
            
            if (StringUtils.equals(overallReq.getOrderMarket(), SelectMarket.SOR.getId())) {
                // 発注市場が当社優先市場／SORの場合
                api002Req.setSorLastMarket(SOR_LAST_MARKET_TSE);
            } else {
                // それ以外の場合
                api002Req.setSorLastMarket(SOR_LAST_MARKET_NOT_SOR);
            }
            
            // 返済順序指定信用一括返済注文APIを呼び出す
            try {
                api002Res = nriApiService.marginLumpPlaceOrderSort(api002Req);
                apiErrorUtil.checkApiResponse(api002Res.getShubetu(), api002Res.getCode(), api002Res.getMessage());

            } catch (Exception e) {
                // システムエラーの場合、DBに登録してレスポンスを返却する
                updSql003Req = new IfaMarginRepayOrderConfirmSql003RequestModel();
                updSql003Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
                updSql003Req.setUpdateUser(userAccount.getUserId());

                try {
                    dao.updateIfaMarginRepayOrderConfirmSql003b(updSql003Req);

                } finally {
                    DataList<IfaMarginRepayOrderConfirmA001bResponseDto> dtoRes = IfaCommonUtil.createDataList(
                        List.of(),
                        ErrorLevel.FATAL,
                        MESSAGE_ORDER_EXECUTION_FAILED,
                        IfaCommonUtil.getMessage(MESSAGE_ORDER_EXECUTION_FAILED)
                    );
                    return dtoRes;
                }
            }

            // 注文発注後の国内株式注文の更新データを作成する
            updSql003Req = new IfaMarginRepayOrderConfirmSql003RequestModel();
            updSql003Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
            updSql003Req.setShubetsu(api002Res.getShubetu());
            updSql003Req.setErrorCode(api002Res.getCode());
            updSql003Req.setErrorMessage(api002Res.getMessage());
            updSql003Req.setUpdateUser(userAccount.getUserId());
            updSql003Req.setError(apiErrorUtil.isFatal());
            
            if (!apiErrorUtil.isFatal()) {
                // APIの応答が正常またはワーニングの場合の値を設定する
                updSql003Req.setOrderType(api002Res.getOrderType());
                updSql003Req.setOrderNum(api002Res.getOrderNum());
                updSql003Req.setAcceptDate(api002Res.getAcceptDate());
                updSql003Req.setAcceptTime(api002Res.getAcceptTime());
                updSql003Req.setEstimatePrice(api002Res.getEstimatePrice());
                updSql003Req.setAmount(api002Res.getAmount());
                updSql003Req.setCommission(api002Res.getCommission());
                updSql003Req.setConsumptionTax(api002Res.getConsumptionTax());
                updSql003Req.setCapitalGainTax(api002Res.getCapitalGainTax());
                updSql003Req.setNetAmount(api002Res.getNetAmount());
                updSql003Req.setCost(api002Res.getCost());
                updSql003Req.setTradeDate(api002Res.getTradeDate());
                updSql003Req.setSettlementDate(api002Res.getSettlementDate());
                updSql003Req.setAcceptLimit(api002Res.getAcceptLimit());
                updSql003Req.setComIdR(api002Res.getComIdR());
                updSql003Req.setOrderedMarket(api002Res.getOrderedMarket());
                updSql003Req.setSorLinkKbn(api002Res.getSorLinkKbn());
                updSql003Req.setUnclosedQuantity(api002Res.getUnclosedQuantity());
                updSql003Req.setUnclosedQuantityAfter(api002Res.getUnclosedQuantityAfter());
                updSql003Req.setMarginCapability(api002Res.getMarginCapability());
                updSql003Req.setMarginCapabilityAfter(api002Res.getMarginCapabilityAfter());
                updSql003Req.setActualGrntRate(api002Res.getActualGrntRate());
                updSql003Req.setActualGrntRateAfter(api002Res.getActualGrntRateAfter());
            }
        }
        
        boolean isUpdateSuccess = false;
        try {
            // 注文発注後の国内株式注文の更新
            final int updSql003Res = dao.updateIfaMarginRepayOrderConfirmSql003(updSql003Req);
            
            // 更新件数が1件の場合に、DB更新が正常終了と判断する
            if (updSql003Res == 1) {
                isUpdateSuccess = true;
            }
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("IfaMarginRepayOrderConfirmServiceImplL.orderPlacementA001b", e);
            }
        }
        
        // エラー判定を行った後、レスポンスデータを作成する
        IfaMarginRepayOrderConfirmA001bResponseDto responseData = null;
        if (StringUtils.equalsAny(repayMethod,
                new String[] { RepayMethod.INDIVIDUAL.getId(), RepayMethod.INDIVIDUAL_ONLY.getId() })) {
            // 返済方法が「個別指定」、または「個別指定（単独）」の場合
            if (apiErrorUtil.isFatal()) {
                // エラー判定結果が「致命的」の場合、APIのエラー情報を返却する
                return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_ORDER_EXECUTION_FAILED,
                        IfaCommonUtil.getMessage(MESSAGE_ORDER_EXECUTION_FAILED,
                                new String[] { api001Res.getCode(), api001Res.getMessage() }));
            }
            
            // 信用一括返済注文APIのレスポンスを設定する
            responseData = new IfaMarginRepayOrderConfirmA001bResponseDto();
            responseData.setEcOrderNo(api001Res.getOrderNum());
            responseData.setQuoteUnitPrice(api001Res.getEstimatePrice());
            responseData.setContractAmount(api001Res.getAmount());
            responseData.setCharge(api001Res.getCost());
            responseData.setConsumptionTax(api001Res.getConsumptionTax());
            responseData.setYieldTax(api001Res.getCapitalGainTax());
            responseData.setSettlementAmount(api001Res.getNetAmount());
            responseData.setContractDate(api001Res.getTradeDate());
            responseData.setDeliveryDate(api001Res.getSettlementDate());
            responseData.setOrderDayTime(String.join(StringUtils.SPACE,
                    new String[] { api001Res.getAcceptDate(), api001Res.getAcceptTime() }));
            
            // 注文発注の画面からのリクエスト内容を設定する
            responseData.setRequest(overallReq);
        } else if (StringUtils.equals(repayMethod, RepayMethod.BATCH.getId())) {
            // 返済方法が「一括指定」の場合
            if (apiErrorUtil.isFatal()) {
                // エラー判定結果が「致命的」の場合、APIのエラー情報を返却する
                return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_ORDER_EXECUTION_FAILED,
                        IfaCommonUtil.getMessage(MESSAGE_ORDER_EXECUTION_FAILED,
                                new String[] { api002Res.getCode(), api002Res.getMessage() }));
            }
            
            // 返済順序指定信用一括返済注文APIのレスポンスを設定する
            responseData = new IfaMarginRepayOrderConfirmA001bResponseDto();
            responseData.setEcOrderNo(api002Res.getOrderNum());
            responseData.setQuoteUnitPrice(api002Res.getEstimatePrice());
            responseData.setContractAmount(api002Res.getAmount());
            responseData.setCharge(api002Res.getCost());
            responseData.setConsumptionTax(api002Res.getConsumptionTax());
            responseData.setYieldTax(api002Res.getCapitalGainTax());
            responseData.setSettlementAmount(api002Res.getNetAmount());
            responseData.setContractDate(api002Res.getTradeDate());
            responseData.setDeliveryDate(api002Res.getSettlementDate());
            responseData.setOrderDayTime(String.join(StringUtils.SPACE,
                    new String[] { api002Res.getAcceptDate(), api002Res.getAcceptTime() }));
            
            // 注文発注の画面からのリクエスト内容を設定する
            responseData.setRequest(overallReq);
        }
        
        if (!isUpdateSuccess) {
            // DB更新でエラーが発生した場合、ワーニングメッセージを設定して返却する
            apiErrorUtil.addDbError(MESSAGE_POST_ORDER_EXECUTION_WARNING, null);
        }
        DataList<IfaMarginRepayOrderConfirmA001bResponseDto> dtoRes = apiErrorUtil.createDataList(List.of(responseData), "");
        if (apiErrorUtil.isWarn() && !ObjectUtils.isEmpty(dtoReq.getWarningMessages())){
            dtoRes.setMessage(dtoReq.getWarningMessages() + "<sep>" + dtoRes.getMessage());
        } else if (apiErrorUtil.isSuccess() && !ObjectUtils.isEmpty(dtoReq.getWarningMessages())) {
            dtoRes.setMessage(dtoReq.getWarningMessages());
            dtoRes.setErrorLevel(ErrorLevel.WARNING.getId());
        }
        // レスポンスを返却する
        return dtoRes;
    }
}
