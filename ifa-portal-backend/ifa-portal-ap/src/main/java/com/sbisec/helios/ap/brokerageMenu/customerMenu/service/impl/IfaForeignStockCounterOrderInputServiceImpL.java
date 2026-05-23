package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct004;
import com.sbisec.helios.ap.bizcommon.component.Fct006;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.component.Fct028;
import com.sbisec.helios.ap.bizcommon.component.Fct034;
import com.sbisec.helios.ap.bizcommon.component.Fct035;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct004Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct028Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct034Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct035Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct004Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct028Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct034Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct035Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaForeignStockCounterOrderInputDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderInputA008RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderInputA008ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaForeignStockCounterOrderInputService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;


/**
 * 画面ID：SUB0202_0302-02_1
 * 画面名：外国株式店頭注文入力
 * 2024/05/07 新規作成
 *
 * @author SCSK 江口
 */
@Component(value = "cmpIfaForeignStockCounterOrderInputService")
public class IfaForeignStockCounterOrderInputServiceImpL implements IfaForeignStockCounterOrderInputService {

    /** エラー.{0}を正しく入力してください。 */
    private static final String ERRORS_ACCURATELY = "errors.accurately";

    /** エラー.入力した部店口座は存在しません。<br>部店: [{0}]、口座: [{1}] */
    private static final String ERRORS_BUTEN_ACCOUNT_NOT_EXIST = "errors.butenAccountNotExist";

    /** エラーこの仲介業者が当該銘柄の取扱対象外です。ご確認ください。<br>仲介業者コード: [{0}]、銘柄コード: [{1}] */
    private static final String ERRORS_BROKER_BRAND_NOT_EXIST = "errors.brokerBrandNotExist";

    /** エラー.取引停止口座のため処理を進めることができません。 */
    private static final String ERRORS_CMN_SELECTED_ACCOUNT_OUT_OF_SERVICE
            = "errors.cmn.selectedAccount.outOfService";

    /** エラー.{0}ができないコースです。 */
    private static final String ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE
            = "errors.cmn.selectedAccountCourse.unavailable";

    /** エラー.当該顧客の{0}に関わる重要な注意情報があるため処理を進めることができません。注意情報欄をご確認ください。 */
    private static final String ERRORS_CMN_NOTICE_ERROR_CHECK = "errors.cmn.noticeErrorCheck";

    /** 外国証券情報交付日には外国証券情報版番以降の日付を入力してください。 */
    private static final String ERRORS_DATE_SPECIFY_AFTER_APPOINTED_DAY = "errors.date.specifyAfterAppointedDay";

    /** エラー.{0}には本日までの日付を入力してください。 */
    private static final String ERRORS_DATE_SPECIFY_TO_TODAY = "errors.date.specifyToToday";

    /** エラー.外国株式口座が未開設です。 */
    private static final String ERRORS_FOREIGN_STOCK_ACCOUNT_CHECK = "errors.foreignStockAccountCheck";

    /** エラー.交付された外国証券情報は最新のものではありません。(後略) */
    private static final String ERRORS_FOREIGN_STOCK_CODE_CHANGE = "errors.foreignStockCodeChange";

    /** エラー.買付余力が不足しているため、注文できません。(後略) */
    private static final String ERRORS_FRS_BUY_LIMIT_OVERFLOW = "errors.frs.buyLimit.overflow";

    /** エラー.残株数が不足しているため、注文できません。(後略) */
    private static final String ERRORS_FRS_COUNT_UNIT_OVERFLOW = "errors.frs.countUnit.overflow";

    /** エラー.ただいまの時間は取引時間外のため注文できません。 */
    private static final String ERRORS_FRS_SERVICE_HOURS_OUT_OF_RANGE = "errors.frs.serviceHours.outOfRange";

    /** エラー.自動振替が設定されているため、店頭取引はできません。「信用新規建(新規買・新規売)の注文時の自動振替設定」の「米ドル預り金」のチェックを外してから注文してください。 */
    private static final String ERRORS_FRS_ORDER_VALUE_IS_MARGIN_BUYING_POWER_SHORTFALL_CASH = "errors.frs.orderValue.isMarginBuyingPowerShortfallCash";

    /** エラー.自動振替が設定されているため、店頭取引はできません。(後略) */
    private static final String ERRORS_FRS_ORDER_VALUE_IS_MARGIN_BUYING_POWER_SHORTFALL_SECURITIES
            = "errors.frs.orderValue.isMarginBuyingPowerShortfallSecurities";

    /** エラー.当該預りは代用預りとなっているため、注文できません。お手数ですが、保護預りへ振替後、再度注文願います。 */
    private static final String ERRORS_FRS_ORDER_VALUE_IS_COLLATERAL_SECURITIES
            = "errors.frs.orderValue.isCollateralSecurities";

    /** エラー.未確認の「重要なお知らせ」があります。 */
    private static final String ERRORS_INFORMATION_CHECK = "errors.informationCheck";

    /** エラー.乗換え勧誘(ETF)が{0}のため、{1}を選択してください。 */
    private static final String ERRORS_INVITATION_CHECK = "errors.invitationCheck";

    /** エラー.{0}は{1}以下であるため、{2}できません。ご確認ください。 */
    private static final String ERRORS_LESS_OR_EQUAL = "errors.lessOrEqual";

    /** エラー.{0}は{1}より大きいため、{2}できません。ご確認ください。 */
    private static final String ERRORS_NUMBER_OVER_RANGE = "errors.numberOverRange";

    /** エラー.{0}を入力してください。 */
    private static final String ERRORS_REQUIRED = "errors.required";

    /** エラー.特定口座が未開設です。 */
    private static final String ERRORS_SPECIFIC_ACCOUNT_CHECK = "errors.specificAccountCheck";

    /** エラー.取引価格が変更しました、この商品は取引に参与できません。 */
    private static final String ERRORS_TRADE_PRICE_CHANGE = "errors.tradePriceChange";

    /** エラー.{0}は{1}より少ない値を入力してください。 */
    private static final String ERRORS_TRADE_RANGE = "errors.tradeRange";

    /** エラー.取引が停止しました、この商品は取引に参与できません。 */
    private static final String ERRORS_TRADE_STOP = "errors.tradeStop";

    /** エラー.取引時間外となったため約定できません。 */
    private static final String ERRORS_TRADE_TIME = "errors.tradeTime";

    /** ワーニング.「重要なお知らせ」が未確認です。重要なお知らせ取引制限欄や注意情報欄を確認してください。 */
    private static final String WARNINGS_CMM_INFORMATION_CHECK = "warnings.cmm.informationCheck";

    /** ワーニング.当該顧客の{0}に関わる注意情報があります。注意情報欄を確認してください。 */
    private static final String WARNINGS_CMN_NOTICE_WARNING_CHECK = "warnings.cmn.noticeWarningCheck";

    /** ワーニング.{0}を超えています。 */
    private static final String WARNINGS_FRS_CONTRACT_PRICE_EXCEEDED = "warnings.frs.contractPrice.exceeded";

    /** ワーニング.投資信託やETFを売却した資金でETFを購入する場合は「乗換え勧誘(ETF)」として確認書の受け入れが(後略) */
    private static final String WARNINGS_FRS_ETF_ORDER_CONFIRM = "warnings.frs.etfOrder.confirm";

    /** 区分.コンプラチェック種類.買付注文 */
    private static final String COMPLA_CHECK_KIND_ORDER_BUY = "1";

    /** 区分.通貨コード.NONE */
    private static final String CURRENCY_CODE_NONE = "999";

    /** 区分.国内外国区分.外国 */
    private static final String DOMESTIC_FOREIGN_TYPE_FOREIGN = "1";

    /** 区分.乗換え勧誘(ETF).勧誘なし */
    private static final String ETF_SOLICITING_TRANSFERS_NO_SOLICITATION = "0";

    /** 区分.乗換え勧誘(ETF).勧誘あり（説明明記、確認書受入） */
    private static final String ETF_SOLICITING_TRANSFERS_SOLICITATION = "1";

    /** 区分.預り区分（外国）.一般 */
    private static final String FOREIGN_DEPOSIT_TYPE_GENERAL = "1";

    /** 区分.預り区分（外国）.特定 */
    private static final String FOREIGN_DEPOSIT_TYPE_SPECIFIC = "2";

    /** 区分.信用口座区分(外国).信用口座 */
    private static final String FOREIGN_MARGIN_ACCOUNT_TYPE_MARGIN_ACCOUNT = "1";

    /** 区分.勧誘区分（外株）.勧誘あり */
    private static final String FOREIGN_STOCK_INVITATION_TYPE_SOLICITATION = "1";

    /** 区分.勧誘区分（外株）.勧誘なし */
    private static final String FOREIGN_STOCK_INVITATION_TYPE_NO_SOLICITATION = "0";

    /** 区分.受注方法区分（外株）.電話他 */
    private static final String FOREIGN_STOCK_ORDER_METHOD_TYPE_CALL_OTHER = "3";

    /** 区分.受注方法区分（外株）.店頭 */
    private static final String FOREIGN_STOCK_ORDER_METHOD_TYPE_COUNTER = "0";

    /** 区分.受注方法区分（外株）.訪問 */
    private static final String FOREIGN_STOCK_ORDER_METHOD_TYPE_VISIT = "1";

    /** 区分.取引種別（外国）.店頭買 */
    private static final String FOREIGN_STOCK_TRADE_CLASS_COUNTER_BUY = "11";

    /** 区分.取引種別（外国）.店頭売 */
    private static final String FOREIGN_STOCK_TRADE_CLASS_COUNTER_SELL = "12";

    /** 区分.勧誘区分.勧誘あり */
    private static final String INVITATION_TYPE_SOLICITATION = "1";

    /** 区分.勧誘区分.勧誘なし */
    private static final String INVITATION_TYPE_NO_SOLICITATION = "2";

    /** 区分.建余力不足 自動振替設定(米国株式).true */
    private static final String MARGIN_BUYING_POWER_SHORTFALL_SECURITIES_TRUE = "1";

    /** 区分.対象取引（メッセージ表示用） */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";

    /** 区分.対象取引（メッセージ表示用）.外国株式取引 */
    private static final String MSG_DISPLAY_TARGET_TRADE_FOREIGN_STOCK_TRADE = "6";

    /** 区分.対象取引（メッセージ表示用）.米株店頭取引 */
    private static final String MSG_DISPLAY_TARGET_TRADE_FOREIGN_STOCK_COUNTER_TRADE = "6B";

    /** 区分.国籍コード.US */
    private static final String NATIONALITY_CODE_US = "US";

    /** 区分.受注方法区分.電話他 */
    private static final String ORDER_METHOD_TYPE_CALL_OTHER = "3";

    /** 区分.受注方法区分.店頭 */
    private static final String ORDER_METHOD_TYPE_COUNTER = "1";

    /** 区分.受注方法区分.訪問 */
    private static final String ORDER_METHOD_TYPE_VISIT = "2";

    /** 区分.余力確認.余力（株数／金額）確認済 */
    private static final String POWER_CONFIRM_CHECKED = "1";
    
    /** 区分.証券金銭種別.外国株式 */
    private static final String SECURITY_MONEY_CLASS_FOREIGN_STOCK = "15";

    /** 区分.商品区分.株式 */
    private static final String SECURITY_TYPE_STOCK = "1 ";

    /** 区分.特定口座区分.特定口座(代行納付) */
    private static final String SPECIFIC_ACCOUNT_WITHHOLDING = "1";

    /** 区分.特定口座区分.特定口座(確定申告) */
    private static final String SPECIFIC_ACCOUNT_TAX_RETURN = "2";

    /** 取引区分.買付 */
    private static final String TRADE_CLASSIFICATION_BUY = "3";

    /** 取引区分.売却 */
    private static final String TRADE_CLASSIFICATION_SELL = "1";

    // 表示パターン.1
    private static final String DISPLAY_PATTERN_1 = "1";

    /** 顧客共通情報.外国株式取引口座開設状況.開設済 */
    private static final String CC_FOREIGN_STOCK_TRADE_ACCOUNT_OPEN_STATUS_OPEN = "1";

    /** FCT001.対象顧客参照権限有無.権限なし */
    private static final String FCT001_TARGET_CUSTOMER_REF_AUTH_FLAG_UNAUTHORIZED = "0";

    /** FCT001.取引停止フラグ.取引停止口座 */
    private static final String FCT001_TRADE_SUSPEND_FLAG_SUSPEND = "1";

    /** FCT003.媒介可否リスト.媒介可否.媒介可 */
    private static final String FCT003_MEDIATE_PROPRIETY_LIST_MEDIATE_PRPRIETY_AVAILABLE = "1";

    /** FCT021.注意情報アラート有無.あり */
    private static final String NOTE_INFO_ALERT_FLAG_ALERT = "1";

    /** FCT021.注意情報エラー有無.あり */
    private static final String NOTE_INFO_ERR_FLAG_ERROR = "1";

    /** FCT021.お知らせアラート有無.あり */
    private static final String NOTE_LIMIT_ALERT_FLAG_ALERT = "1";

    /** FCT021.お知らせエラー有無.あり */
    private static final String NOTE_LIMIT_ERR_FLAG_ERROR = "1";

    /** FCT034.代用預りフラグ.代用預り */
    private static final String COLLATERAL_SECURITIES_TRUE = "TRUE";

    /** SQL001.販売状態.取引停止 */
    private static final String SQL001_TRADE_STATUS_TRADE_STOP = "1";

    /** SQL003.ETFフラグ */
    private static final String SQL003_ETF_FLAG_TRUE = "1";

    /** ロガー */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(IfaForeignStockCounterOrderInputServiceImpL.class);

    @Autowired
    private CodeListService codeListService;

    @Autowired
    private IfaDateUtil ifaDateUtil;

    @Autowired
    private IfaForeignStockCounterOrderInputDao dao;

    @Autowired
    private Fct001 fct001;

    @Autowired
    private Fct003 fct003;

    @Autowired
    private Fct004 fct004;

    @Autowired
    private Fct006 fct006;

    @Autowired
    private Fct021 fct021;

    @Autowired
    private Fct028 fct028;

    @Autowired
    private Fct034 fct034;

    @Autowired
    private Fct035 fct035;

    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignStockCounterOrderInputA001RequestDto
     * Dto レスポンス：IfaForeignStockCounterOrderInputA001ResponseDto
     * model リクエスト：IfaForeignStockCounterOrderInputSql001RequestModel
     * model レスポンス：IfaForeignStockCounterOrderInputSql001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 初期化に必要な情報
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaForeignStockCounterOrderInputA001ResponseDto> initializeA001(
            IfaForeignStockCounterOrderInputA001RequestDto dtoReq
    ) throws Exception {
        IfaForeignStockCounterOrderInputA001ResponseDto innerData = new IfaForeignStockCounterOrderInputA001ResponseDto();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignStockCounterOrderInputServiceImplL.initializeA001");
        }
        
        /* =================================================================== */
        /* ① 利用者口座に対する権限チェックを行う                                */
        /* =================================================================== */

        DataList<IfaForeignStockCounterOrderInputA001ResponseDto> dtoResFct001 = checkFct001();
        if (dtoResFct001 != null) {
            return dtoResFct001;
        }

        /* =================================================================== */
        /* ② 取引コース媒介可否チェックを行う                                    */
        /* =================================================================== */

        DataList<IfaForeignStockCounterOrderInputA001ResponseDto> dtoResFct003 = checkFct003(dtoReq.getTradeClassification());
        if (dtoResFct003 != null) {
            return dtoResFct003;
        }

        /* =================================================================== */
        /* ③ 外株口座が開設されていることのチェックを行う                         */
        /* =================================================================== */

        DataList<IfaForeignStockCounterOrderInputA001ResponseDto> dtoResForeignStockAccount = checkForeignStockAccount();
        if (dtoResForeignStockAccount != null) {
            return dtoResForeignStockAccount;
        }

        /* =================================================================== */
        /* ① 項目チェック情報の取得を行う                                        */
        /* =================================================================== */

        // SQL001の呼び出し
        IfaForeignStockCounterOrderInputSql001RequestModel selSql001Req
                = new IfaForeignStockCounterOrderInputSql001RequestModel();
        selSql001Req.setBrandCode(dtoReq.getBrandCode());
        selSql001Req.setBrokerCode(IfaCommonUtil.getCustomerCommon().getBrokerCode());
        DataList<IfaForeignStockCounterOrderInputSql001ResponseModel> selSql001Res
                = dao.selectIfaForeignStockCounterOrderInputSql001(selSql001Req);

        /* =================================================================== */
        /* ⑤ 仲介業者コードと銘柄コードの有効性チェックを行う                     */
        /* =================================================================== */

        DataList<IfaForeignStockCounterOrderInputA001ResponseDto> dtoResBrokerCodeAndBrandCode
                = checkBrokerCodeAndBrandCode(selSql001Res, dtoReq.getBrandCode());
        if (dtoResBrokerCodeAndBrandCode != null) {
            return dtoResBrokerCodeAndBrandCode;
        }

        /* =================================================================== */
        /* ⑤ 取引状況チェックを行う                                             */
        /* =================================================================== */

        DataList<IfaForeignStockCounterOrderInputA001ResponseDto> dtoResTradeStatus
                = checkTradeStatus(selSql001Res.get(0).getTradeStatus());
        if (dtoResTradeStatus != null) {
            return dtoResTradeStatus;
        }

        /* =================================================================== */
        /* ⑦ 残販売枠チェックを行う                                             */
        /* =================================================================== */
        
        if (
                (// 売却の場合
                        dtoReq.getTradeClassification().equals(TRADE_CLASSIFICATION_SELL)
                        && selSql001Res.get(0).getSellTradeCount().compareTo(BigDecimal.ZERO) <= 0
                )
                || (// 買付の場合
                        dtoReq.getTradeClassification().equals(TRADE_CLASSIFICATION_BUY)
                        && selSql001Res.get(0).getBuyTradeCount().compareTo(BigDecimal.ZERO) <= 0
                )
        ) {
            DataList<IfaForeignStockCounterOrderInputA001ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_LESS_OR_EQUAL,
                    IfaCommonUtil.getMessage(
                            ERRORS_LESS_OR_EQUAL,
                            new String[] {
                                    "残販売枠数",
                                    "0",
                                    "外国株式店頭注文入力画面に遷移"
                            }
                    )
            );

            return dtoRes;
        }

        /* =================================================================== */
        /* ⑧ 取引時間チェックを行う                                             */
        /* =================================================================== */

        DataList<IfaForeignStockCounterOrderInputA001ResponseDto> dtoResTradeTime = checkTradeTime(
                selSql001Res.get(0).getTradeStartTime(),
                selSql001Res.get(0).getTradeEndTime()
        );

        if (dtoResTradeTime != null) {
            return dtoResTradeTime;
        }

        /* =================================================================== */
        /* ⑨ 営業日チェックを行う                                               */
        /* =================================================================== */

        DataList<IfaForeignStockCounterOrderInputA001ResponseDto> dtoResBusinessDay = checkBusinessDay();
        if (dtoResBusinessDay != null) {
            return dtoResBusinessDay;
        }

        /* =================================================================== */
        /* ⑩ 買付の場合、買付余力（外貨）を取得する                              */
        /* =================================================================== */

        if (dtoReq.getTradeClassification().equals(TRADE_CLASSIFICATION_BUY)) {
            // 特定買付余力（外貨）
            String specificBuyingPowerForeign = getForeignBuyingPowerByDepositType(FOREIGN_DEPOSIT_TYPE_SPECIFIC).toPlainString();
            innerData.setSpecificBuyingPowerForeign(specificBuyingPowerForeign);

            // 一般買付余力（外貨）
            String generalBuyingPowerForeign = getForeignBuyingPowerByDepositType(FOREIGN_DEPOSIT_TYPE_GENERAL).toPlainString();
            innerData.setGeneralBuyingPowerForeign(generalBuyingPowerForeign);
        }

        /* =================================================================== */
        /* ⑪ 売却の場合、注文可能数量を取得する                                  */
        /* =================================================================== */

        if (dtoReq.getTradeClassification().equals(TRADE_CLASSIFICATION_SELL)) {
            // 特定注文可能数量
            Pair<BigDecimal, DataList<IfaForeignStockCounterOrderInputA001ResponseDto>> fct028Specific = getAcPositionStockNumberByDepositType(
                    dtoReq.getBrandCode(),
                    FOREIGN_DEPOSIT_TYPE_SPECIFIC
            );
            // エラーが設定されていれば、エラーを返却する
            if (fct028Specific.getRight() != null) {
                return fct028Specific.getRight();
            }
            String specificOrderAbleQuantity = fct028Specific.getLeft().toPlainString();
            innerData.setSpecificOrderAbleQuantity(specificOrderAbleQuantity);

            // 一般注文可能数量
            Pair<BigDecimal, DataList<IfaForeignStockCounterOrderInputA001ResponseDto>> fct028General = getAcPositionStockNumberByDepositType(
                    dtoReq.getBrandCode(),
                    FOREIGN_DEPOSIT_TYPE_GENERAL
            );
            // エラーが設定されていれば、エラーを返却する
            if (fct028General.getRight() != null) {
                return fct028General.getRight();
            }
            String generalOrderAbleQuantity = fct028General.getLeft().toPlainString();
            innerData.setGeneralOrderAbleQuantity(generalOrderAbleQuantity);
        }

        /* =================================================================== */
        /* ⑫～⑭ 各種URLを取得する                                               */
        /* =================================================================== */

        // 注文入力の使い方URL
        DataList<IfaForeignStockCounterOrderInputSql005ResponseModel> selSql005Res
                = dao.selectIfaForeignStockCounterOrderInputSql005();
        if (
                selSql005Res != null
                && selSql005Res.getDataList() != null
                && selSql005Res.size() > 0
        ) {
            innerData.setOrderInputUsageUrl(selSql005Res.get(0).getOrderInputUsageUrl());
        }

        // チャートURL
        DataList<IfaForeignStockCounterOrderInputSql006ResponseModel> selSql006Res
                = dao.selectIfaForeignStockCounterOrderInputSql006();
        if (
                selSql006Res != null
                && selSql006Res.getDataList() != null
                && selSql006Res.size() > 0
        ) {
            innerData.setChartUrl(selSql006Res.get(0).getChartUrl());
        }

        // 株価参照URL
        DataList<IfaForeignStockCounterOrderInputSql007ResponseModel> selSql007Res
                = dao.selectIfaForeignStockCounterOrderInputSql007();
        if (
                selSql007Res != null
                && selSql007Res.getDataList() != null
                && selSql007Res.size() > 0
        ) {
            innerData.setStockPriceReferenceUrl(selSql007Res.get(0).getStockPriceReferenceUrl());
        }

        /* =================================================================== */
        /* ⑮～⑯ 各種チェック用上限値を取得する                                   */
        /* =================================================================== */

        // チェック用上限約定金額
        DataList<IfaForeignStockCounterOrderInputSql009ResponseModel> selSql009Res
                = dao.selectIfaForeignStockCounterOrderInputSql009();
        if (
                selSql009Res != null
                && selSql009Res.getDataList() != null
                && selSql009Res.size() > 0
        ) {
            innerData.setForCheckUpperLimitContractAmount(
                    selSql009Res.get(0).getForCheckUpperLimitContractAmount()
            );
        }

        // チェック用上限注文数量
        DataList<IfaForeignStockCounterOrderInputSql010ResponseModel> selSql010Res
                = dao.selectIfaForeignStockCounterOrderInputSql010();
        if (
                selSql010Res != null
                && selSql010Res.getDataList() != null
                && selSql010Res.size() > 0
        ) {
            innerData.setForCheckUpperLimitOrderAmount(
                    selSql010Res.get(0).getForCheckUpperLimitOrderAmount()
            );
        }

        /* =================================================================== */
        /* ⑰ 店頭注文新規登録初期情報を取得する                                  */
        /* =================================================================== */

        // SQL003を実行する
        IfaForeignStockCounterOrderInputSql003RequestModel selSql003Req
                = new IfaForeignStockCounterOrderInputSql003RequestModel();
        selSql003Req.setBrandCode(dtoReq.getBrandCode());
        selSql003Req.setBrokerCode(IfaCommonUtil.getCustomerCommon().getBrokerCode());
        DataList<IfaForeignStockCounterOrderInputSql003ResponseModel> selSql003Res
                = dao.selectIfaForeignStockCounterOrderInputSql003(selSql003Req);

        // SQL003の実行結果をinnerDataに格納する
        if (
                selSql003Res != null
                && selSql003Res.getDataList() != null
                && selSql003Res.size() > 0
        ) {
            BeanUtils.copyProperties(innerData, selSql003Res.get(0));
        }
        
        /* =================================================================== */
        /* レスポンスを返却する                                                 */
        /* =================================================================== */

        // リクエストの折り返し項目をコピー
        BeanUtils.copyProperties(innerData, dtoReq);

        DataList<IfaForeignStockCounterOrderInputA001ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                Arrays.asList(innerData),
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                StringUtil.EMPTY_STRING
        );
        return dtoRes;
    }
    

    /**
     * アクションID：A002
     * アクション名：更新
     * Dto リクエスト：IfaForeignStockCounterOrderInputA002RequestDto
     * Dto レスポンス：IfaForeignStockCounterOrderInputA002ResponseDto
     * model リクエスト：IfaForeignStockCounterOrderInputSql004RequestModel
     * model レスポンス：IfaForeignStockCounterOrderInputSql004ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 更新に必要な情報
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaForeignStockCounterOrderInputA002ResponseDto> updateA002(
            IfaForeignStockCounterOrderInputA002RequestDto dtoReq
    ) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignStockCounterOrderInputServiceImplL.updateA002");
        }

        /* =================================================================== */
        /* ① 上限数量を取得する                                                 */
        /* =================================================================== */

        // SQL004を呼び出す
        IfaForeignStockCounterOrderInputSql004RequestModel selSql004Req = new IfaForeignStockCounterOrderInputSql004RequestModel();
        selSql004Req.setBrandCode(dtoReq.getBrandCode());
        selSql004Req.setBrokerCode(IfaCommonUtil.getCustomerCommon().getBrokerCode());
        DataList<IfaForeignStockCounterOrderInputSql004ResponseModel> selSql004Res = dao.selectIfaForeignStockCounterOrderInputSql004(selSql004Req);
        IfaForeignStockCounterOrderInputA002ResponseDto innerData = new IfaForeignStockCounterOrderInputA002ResponseDto();
        BeanUtils.copyProperties(innerData, selSql004Res);

        // 上限数量が空ではないことをチェック
        if (
                selSql004Res == null
                || selSql004Res.getDataList() == null
                || selSql004Res.size() == 0
        ) {
            DataList<IfaForeignStockCounterOrderInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_LESS_OR_EQUAL,
                    IfaCommonUtil.getMessage(
                            ERRORS_LESS_OR_EQUAL,
                            new String[] { "残販売枠数", "0", "注文" }
                    )
            );

            return dtoRes;
        }
        
        // 上限数量が0以下でないことをチェック
        boolean isZeroMaxLimitQuantity = false;

        // 売却の場合
        if (dtoReq.getTradeKbn().equals(TRADE_CLASSIFICATION_SELL)) {
            long maxSellLimitQuantity = selSql004Res.get(0).getMaxSellLimitQuantity();
            isZeroMaxLimitQuantity = (maxSellLimitQuantity <= 0);
            innerData.setMaxSellLimitQuantity(Long.toString(maxSellLimitQuantity));
        }

        // 買付の場合
        if (dtoReq.getTradeKbn().equals(TRADE_CLASSIFICATION_BUY)) {
            long maxBuyLimitQuantity = selSql004Res.get(0).getMaxBuyLimitQuantity();
            isZeroMaxLimitQuantity = (maxBuyLimitQuantity <= 0);
            innerData.setMaxBuyLimitQuantity(Long.toString(maxBuyLimitQuantity));
        }

        if (isZeroMaxLimitQuantity) {
            DataList<IfaForeignStockCounterOrderInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_LESS_OR_EQUAL,
                    IfaCommonUtil.getMessage(
                            ERRORS_LESS_OR_EQUAL,
                            new String[] { "残販売枠数", "0", "注文" }
                    )
            );

            return dtoRes;
        }

        /* =================================================================== */
        /* ② 買付の場合、買付余力（外貨）を取得する                              */
        /* =================================================================== */

        if (dtoReq.getTradeKbn().equals(TRADE_CLASSIFICATION_BUY)) {
            // 特定買付余力（外貨）
            String specificBuyingPowerForeign = getForeignBuyingPowerByDepositType(FOREIGN_DEPOSIT_TYPE_SPECIFIC).toPlainString();
            innerData.setSpecificBuyingPowerForeign(specificBuyingPowerForeign);

            // 一般買付余力（外貨）
            String generalBuyingPowerForeign = getForeignBuyingPowerByDepositType(FOREIGN_DEPOSIT_TYPE_GENERAL).toPlainString();
            innerData.setGeneralBuyingPowerForeign(generalBuyingPowerForeign);
        }

        /* =================================================================== */
        /* ② 売却の場合、注文可能数量を取得する                                  */
        /* =================================================================== */

        if (dtoReq.getTradeKbn().equals(TRADE_CLASSIFICATION_SELL)) {
            // 特定注文可能数量
            Pair<BigDecimal, DataList<IfaForeignStockCounterOrderInputA002ResponseDto>> fct028Specific = getAcPositionStockNumberByDepositType(
                    dtoReq.getBrandCode(),
                    FOREIGN_DEPOSIT_TYPE_SPECIFIC
            );
            // エラーが設定されていれば、エラーを返却する
            if (fct028Specific.getRight() != null) {
                return fct028Specific.getRight();
            }
            String specificOrderAbleQuantity = fct028Specific.getLeft().toPlainString();
            innerData.setSpecificOrderAbleQuantity(specificOrderAbleQuantity);

            // 一般注文可能数量
            Pair<BigDecimal, DataList<IfaForeignStockCounterOrderInputA002ResponseDto>> fct028General = getAcPositionStockNumberByDepositType(
                    dtoReq.getBrandCode(),
                    FOREIGN_DEPOSIT_TYPE_GENERAL
            );
            // エラーが設定されていれば、エラーを返却する
            if (fct028General.getRight() != null) {
                return fct028General.getRight();
            }
            String generalOrderAbleQuantity = fct028General.getLeft().toPlainString();
            innerData.setGeneralOrderAbleQuantity(generalOrderAbleQuantity);
        }

        /* =================================================================== */
        /* レスポンスを返却する                                                 */
        /* =================================================================== */

        DataList<IfaForeignStockCounterOrderInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                Arrays.asList(innerData),
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                StringUtil.EMPTY_STRING
        );
        return dtoRes;

    }
    

    /**
     * アクションID：A008
     * アクション名：注文確認
     * Dto リクエスト：IfaForeignStockCounterOrderInputA008RequestDto
     * Dto レスポンス：IfaForeignStockCounterOrderInputA008ResponseDto
     * model リクエスト：IfaForeignStockCounterOrderInputSql001RequestModel
     * model レスポンス：IfaForeignStockCounterOrderInputSql001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 注文確認に必要な情報
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaForeignStockCounterOrderInputA008ResponseDto> orderConfirmA008(
            IfaForeignStockCounterOrderInputA008RequestDto dtoReq
    ) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignStockCounterOrderInputServiceImplL.orderConfirmA008");
        }
        
        /* =================================================================== */
        /* ① 余力確認チェックボックスの必須チェックを行う                         */
        /* =================================================================== */

        if (!dtoReq.getPowerConfirm().equals(POWER_CONFIRM_CHECKED)) {
            DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_REQUIRED,
                    IfaCommonUtil.getMessage(
                            ERRORS_REQUIRED,
                            new String[] { "余力確認チェックボックス" }
                    )
            );

            return dtoRes;
        }

        /* =================================================================== */
        /* ② 利用者の口座に対する権限チェックを行う                               */
        /* =================================================================== */

        DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoResFct001 = checkFct001();
        if (dtoResFct001 != null) {
            return dtoResFct001;
        }

        /* =================================================================== */
        /* ③ 取引コース媒介可否チェックを行う                                    */
        /* =================================================================== */

        DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoResFct003 = checkFct003(dtoReq.getTradeKbn());
        if (dtoResFct003 != null) {
            return dtoResFct003;
        }

        /* =================================================================== */
        /* ④ 口座の取引制限チェックを行う                                        */
        /* =================================================================== */

        IfaForeignStockCounterOrderInputA008ResponseDto innerData = new IfaForeignStockCounterOrderInputA008ResponseDto();
        DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoResFct021 = checkFct021(innerData, dtoReq.getTradeKbn());
        if (dtoResFct021 != null) {
            return dtoResFct021;
        }

        /* =================================================================== */
        /* ⑤ 約定金額の上限チェックを行う                                        */
        /* =================================================================== */

        BigDecimal contractAmount = new BigDecimal(dtoReq.getContractAmount());
        BigDecimal upperLimitContractAmount = new BigDecimal(dtoReq.getForCheckUpperLimitContractAmount());

        // 画面.チェック用上限約定金額 < 約定金額ならエラー
        if (0 < contractAmount.compareTo(upperLimitContractAmount)) {
            String upperLimitContractAmountStr = formatBigDecimal2MillionDollarsString(upperLimitContractAmount);
            DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_NUMBER_OVER_RANGE,
                    IfaCommonUtil.getMessage(
                            ERRORS_NUMBER_OVER_RANGE,
                            new String[] { "約定金額", upperLimitContractAmountStr, "取引" }
                    )
            );

            return dtoRes;
        }

        /* =================================================================== */
        /* ⑥ 約定金額のワーニングチェックを行う                                  */
        /* =================================================================== */

        // SQL008の呼び出し
        DataList<IfaForeignStockCounterOrderInputSql008ResponseModel> selSql008Res
                = dao.selectIfaForeignStockCounterOrderInputSql008();

        // SQL008.名称(ワーニング約定金額) < 約定金額の場合、innerDataにワーニングを格納
        BigDecimal warningThresholdContractAmount = new BigDecimal(selSql008Res.get(0).getWarningThresholdContractAmount());

        if (0 < contractAmount.compareTo(warningThresholdContractAmount)) {
            String warningThresholdContractAmountStr = formatBigDecimal2MillionDollarsString(warningThresholdContractAmount);
            innerData.setTradeAmountAlert(IfaCommonUtil.getMessage(
                    WARNINGS_FRS_CONTRACT_PRICE_EXCEEDED,
                    new String[] { "約定金額が" + warningThresholdContractAmountStr }
            ));
        }

        /* =================================================================== */
        /* ⑦ 約定金額と数量・取引価格の整合性チェックを行う                       */
        /* =================================================================== */

        BigDecimal quantity = new BigDecimal(dtoReq.getQuantity());
        BigDecimal tradePrice = new BigDecimal(dtoReq.getTradePrice());
        BigDecimal calcedContractAmount = quantity.multiply(tradePrice);

        //  画面.約定金額 != 画面.数量×画面.取引価格 の場合、エラー
        if (contractAmount.compareTo(calcedContractAmount) != 0) {
            DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_ACCURATELY,
                    IfaCommonUtil.getMessage(
                        ERRORS_ACCURATELY,
                            new String[] { "約定金額" }
                    )
            );

            return dtoRes;
        }

        /* =================================================================== */
        /* ⑧ 注文数量の上限チェックを行う                                        */
        /* =================================================================== */

        BigDecimal upperLimitOrderAmount = new BigDecimal(dtoReq.getForCheckUpperLimitOrderAmount());

        // 画面.チェック用上限注文数量 < 画面.数量の場合、エラー
        if (0 < quantity.compareTo(upperLimitOrderAmount)) {
            DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_NUMBER_OVER_RANGE,
                    IfaCommonUtil.getMessage(
                        ERRORS_NUMBER_OVER_RANGE,
                            new String[] { "数量", dtoReq.getForCheckUpperLimitOrderAmount(), "取引" }
                    )
            );

            return dtoRes;
        }

        /* =================================================================== */
        /* ⑨ 買付の場合かつ銘柄がETFに該当する場合かつ勧誘なしならアラートを格納   */
        /* =================================================================== */

        if (
                dtoReq.getTradeKbn().equals(TRADE_CLASSIFICATION_BUY)
                && dtoReq.getEtfFlag().equals(SQL003_ETF_FLAG_TRUE)
                && dtoReq.getSwitchingSolicitationEtf().equals(ETF_SOLICITING_TRANSFERS_NO_SOLICITATION)
        ) {
            String overseasEtfAlert = IfaCommonUtil.getMessage(WARNINGS_FRS_ETF_ORDER_CONFIRM);
            innerData.setOverseasEtfAlert(overseasEtfAlert);
        }

        /* =================================================================== */
        /* ⑩～⑫ 外国証券情報版番のチェック                                       */
        /* =================================================================== */

        DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoResForeignSecurityInfo = checkForeignSecurityInfo(dtoReq);
        if (dtoResForeignSecurityInfo != null) {
            return dtoResForeignSecurityInfo;
        }

        /* =================================================================== */
        /* ⑬ 画面に乗換え勧誘(ETF)が項目表示されている場合、関連項目チェックを行う  */
        /* =================================================================== */

        // 画面に乗換え勧誘(ETF)が表示されている場合
        if (
                dtoReq.getTradeKbn().equals(TRADE_CLASSIFICATION_BUY)
                && dtoReq.getEtfFlag().equals(SQL003_ETF_FLAG_TRUE)
        ) {
            // 画面.乗換え勧誘(ETF)が「勧誘あり(説明記録、確認書受入)」　かつ 画面.勧誘区分が「勧誘なし」の場合、エラー
            if (
                    dtoReq.getSwitchingSolicitationEtf().equals(ETF_SOLICITING_TRANSFERS_SOLICITATION)
                    && dtoReq.getKanyuKbn().equals(FOREIGN_STOCK_INVITATION_TYPE_NO_SOLICITATION)
            ) {
                DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.FATAL,
                        ERRORS_INVITATION_CHECK,
                        IfaCommonUtil.getMessage(
                            ERRORS_INVITATION_CHECK,
                                new String[] { "勧誘あり", "勧誘区分は勧誘あり" }
                        )
                );

                return dtoRes;
            }
        }

        /* =================================================================== */
        /* ⑭ 画面に説明日が項目表示されている場合、英字開示説明日の日付チェック    */
        /* =================================================================== */

        // 画面に説明日が表示されている場合
        String engPubYmdStr = dtoReq.getEngPubYmd();
        if (!StringUtil.isNullOrEmpty(engPubYmdStr)) {
            LocalDate currentDate = ifaDateUtil.getCurrentLocalDate(ZoneId.of("UTC+09:00"));
            LocalDate engPubYmd
                    = LocalDate.parse(engPubYmdStr, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            if (
                    dtoReq.getTradeKbn().equals(TRADE_CLASSIFICATION_BUY)
                    && currentDate.compareTo(engPubYmd) < 0
            ) {
                DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.FATAL,
                        ERRORS_DATE_SPECIFY_TO_TODAY,
                        IfaCommonUtil.getMessage(
                                ERRORS_DATE_SPECIFY_TO_TODAY,
                                new String[] { "英文開示銘柄説明日" }
                        )
                );

                return dtoRes;
            }
        }

        /* =================================================================== */
        /* ⑮ 外株口座が開設されていることのチェックを行う                         */
        /* =================================================================== */

        DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoResForeignStockAccount = checkForeignStockAccount();
        if (dtoResForeignStockAccount != null) {
            return dtoResForeignStockAccount;
        }

        /* =================================================================== */
        /* ⑯ 画面預り区分ラジオで「特定」が選択されている場合、口座種別チェック    */
        /* =================================================================== */
        String specificAccountType = IfaCommonUtil.getCustomerCommon().getSpecificAccountType();
        if (
                dtoReq.getDepositType().equals(FOREIGN_DEPOSIT_TYPE_SPECIFIC)
                && !specificAccountType.equals(SPECIFIC_ACCOUNT_WITHHOLDING)
                && !specificAccountType.equals(SPECIFIC_ACCOUNT_TAX_RETURN)
        ) {
            DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_SPECIFIC_ACCOUNT_CHECK,
                    IfaCommonUtil.getMessage(ERRORS_SPECIFIC_ACCOUNT_CHECK)
            );

            return dtoRes;
        }

        /* =================================================================== */
        /* ⑰ 買付の場合、取引チェック（コンプラランクチェック）                   */
        /* =================================================================== */
        
        if (dtoReq.getTradeKbn().equals(TRADE_CLASSIFICATION_BUY)) {
            DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoResFct006
                    = checkFct006(innerData, dtoReq.getBrandCode(), dtoReq.getKanyuKbn(), dtoReq.getOrderMethod());
            if (dtoResFct006 != null) {
                return dtoResFct006;
            }
        }

        /* =================================================================== */
        /* ⑱ 買付の場合、金銭残高チェックを行う                                  */
        /* =================================================================== */

        if (dtoReq.getTradeKbn().equals(TRADE_CLASSIFICATION_BUY)) {
            DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoResCashBalance
                    = checkCashBalance(dtoReq.getDepositType(), dtoReq.getContractAmount());
            if (dtoResCashBalance != null) {
                return dtoResCashBalance;
            }
        }

        /* =================================================================== */
        /* ⑲ 売却の場合、数量チェックを行う                                      */
        /* =================================================================== */
        if (dtoReq.getTradeKbn().equals(TRADE_CLASSIFICATION_SELL)) {
            DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoResQuantity
                    = checkQuantity(dtoReq.getBrandCode(), dtoReq.getDepositType(), dtoReq.getQuantity());
            if (dtoResQuantity != null) {
                return dtoResQuantity;
            }
        }

        /* =================================================================== */
        /* ⑳ 営業日チェックを行う                                               */
        /* =================================================================== */

        DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoResBusinessDay = checkBusinessDay();
        if (dtoResBusinessDay != null) {
            return dtoResBusinessDay;
        }

        /* =================================================================== */
        /* ㉑ 項目チェック情報の取得を行う                                       */
        /* =================================================================== */

        // SQL001の呼び出し
        IfaForeignStockCounterOrderInputSql001RequestModel selSql001Req
                = new IfaForeignStockCounterOrderInputSql001RequestModel();
        selSql001Req.setBrandCode(dtoReq.getBrandCode());
        selSql001Req.setBrokerCode(IfaCommonUtil.getCustomerCommon().getBrokerCode());
        DataList<IfaForeignStockCounterOrderInputSql001ResponseModel> selSql001Res
                = dao.selectIfaForeignStockCounterOrderInputSql001(selSql001Req);

        /* =================================================================== */
        /* ㉒ 仲介業者コードと銘柄コードの有効性チェックを行う                    */
        /* =================================================================== */

        DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoResBrokerCodeAndBrandCode
                = checkBrokerCodeAndBrandCode(selSql001Res, dtoReq.getBrandCode());
        if (dtoResBrokerCodeAndBrandCode != null) {
            return dtoResBrokerCodeAndBrandCode;
        }

        /* =================================================================== */
        /* ㉓ 取引状況チェックを行う                                            */
        /* =================================================================== */

        DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoResTradeStatus
                = checkTradeStatus(selSql001Res.get(0).getTradeStatus());
        if (dtoResTradeStatus != null) {
            return dtoResTradeStatus;
        }

        /* =================================================================== */
        /* ㉔ 数量チェック(残販売枠)を行う                                                */
        /* =================================================================== */
        DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoResTradeCount = checkTradeCount(
                dtoReq.getTradeKbn(),
                dtoReq.getQuantity(),
                selSql001Res.get(0).getSellTradeCount(),
                selSql001Res.get(0).getBuyTradeCount()
        );
        if (dtoResTradeCount != null) {
            return dtoResTradeCount;
        }

        /* =================================================================== */
        /* ㉕ 価格変更チェックを行う                                             */
        /* =================================================================== */

        DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoResTradePrice = checkTradePriceUnchanged(
                dtoReq.getTradeKbn(),
                dtoReq.getTradePrice(),
                selSql001Res.get(0).getSellTradePrice(),
                selSql001Res.get(0).getBuyTradePrice()
        );
        if (dtoResTradePrice != null) {
            return dtoResTradePrice;
        }


        /* =================================================================== */
        /* ㉖ 取引時間チェックを行う                                            */
        /* =================================================================== */
        
        DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoResTradeTime = checkTradeTime(
                selSql001Res.get(0).getTradeStartTime(),
                selSql001Res.get(0).getTradeEndTime()
        );
        if (dtoResTradeTime != null) {
            return dtoResTradeTime;
        }

        /* =================================================================== */
        /* レスポンスを返却する                                                 */
        /* =================================================================== */

        // リクエストの折り返し項目をコピー
        BeanUtils.copyProperties(innerData, dtoReq);

        DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                Arrays.asList(innerData),
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                StringUtil.EMPTY_STRING
        );

        return dtoRes;
    }
    

    /**
     * 営業日チェック
     *
     * @return 営業日：null、非営業日：エラーレスポンス用DataList
     * @exception Exception システムエラー
     */
    private <T> DataList<T> checkBusinessDay() throws Exception {
        // SQL002の呼び出し
        DataList<IfaForeignStockCounterOrderInputSql002ResponseModel> selSql002Res
                = dao.selectIfaForeignStockCounterOrderInputSql002();

        // 営業日のチェック
        // レコードが存在する場合、非営業日と判断
        if (
                selSql002Res != null
                && selSql002Res.getDataList() != null
                && selSql002Res.size() > 0
                && selSql002Res.get(0).getCount() > 0
        ) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_FRS_SERVICE_HOURS_OUT_OF_RANGE,
                    IfaCommonUtil.getMessage(ERRORS_FRS_SERVICE_HOURS_OUT_OF_RANGE)
            );

            return dtoRes;
        }

        return null;
    }


    /**
     * 仲介業者コードと銘柄コードの有効性チェック
     *
     * @param selSql001Res SQL001のレスポンス
     * @return OK：null、NG：エラーレスポンス用DataList
     * @exception Exception システムエラー
     */
    private <T> DataList<T> checkBrokerCodeAndBrandCode(
            DataList<IfaForeignStockCounterOrderInputSql001ResponseModel> selSql001Res,
            String brandCode
    ) throws Exception {

        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String brokerCode = cc.getBrokerCode();

        if (
                selSql001Res == null
                || selSql001Res.getDataList() == null
                || selSql001Res.size() == 0
        ) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_BROKER_BRAND_NOT_EXIST,
                    IfaCommonUtil.getMessage(
                            ERRORS_BROKER_BRAND_NOT_EXIST,
                            new String[] { brokerCode, brandCode }
                    )
            );

            return dtoRes;
        }

        return null;
    }


    /**
     * 金銭残高チェック
     *
     * @param depositType 預り区分
     * @param contractAmount 約定金額
     * @return OK：null、NG：エラーレスポンス用DataList
     */
    private DataList<IfaForeignStockCounterOrderInputA008ResponseDto> checkCashBalance(
            String depositType,
            String contractAmount
    )  throws Exception {
        
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        String foreignMarginAccountType = cc.getForeignMarginAccountType();

        // 信用口座の場合のチェック
        if (foreignMarginAccountType.equals(FOREIGN_MARGIN_ACCOUNT_TYPE_MARGIN_ACCOUNT)) {
            // FCT035の呼び出し
            InputFct035Dto inputFct035Dto = new InputFct035Dto();
            inputFct035Dto.setButenCode(butenCode);
            inputFct035Dto.setAccountNumber(accountNumber);
            OutputFct035Dto outputFct035Dto = fct035.doCheck(inputFct035Dto);

            // FCT035.建余力不足_自動振替設定（米ドル）フラグ = TRUEの場合エラー
            if (
                        outputFct035Dto == null
                        || outputFct035Dto.getCapacityShortageAutoTransferSettingUSDFlag() == null
                        || outputFct035Dto.getCapacityShortageAutoTransferSettingUSDFlag().equals(MARGIN_BUYING_POWER_SHORTFALL_SECURITIES_TRUE)
            ) {
                DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.FATAL,
                        ERRORS_FRS_ORDER_VALUE_IS_MARGIN_BUYING_POWER_SHORTFALL_CASH,
                        IfaCommonUtil.getMessage(ERRORS_FRS_ORDER_VALUE_IS_MARGIN_BUYING_POWER_SHORTFALL_CASH)
                );

                return dtoRes;
            }
        }

        // FCT004を呼び出し
        String depositTypeExtKey = codeListService.convertKeyToExtKey("FOREIGN_DEPOSIT_TYPE", "Athena", depositType);
        InputFct004Dto inputFct004Dto = new InputFct004Dto();
        inputFct004Dto.setButenCode(butenCode);
        inputFct004Dto.setAccountNumber(accountNumber);
        inputFct004Dto.setDepositType(depositTypeExtKey);
        OutputFct004Dto outputFct004Dto = fct004.doCheck(inputFct004Dto);

        // 顧客口座内残高上限値（FCT004.計算後の余力金額） < 画面.約定金額 の場合エラー
        BigDecimal calcedContractAmount = outputFct004Dto.getByingPowerMoneyAfterCalculate();
        BigDecimal contractAmountBigDecimal = new BigDecimal(contractAmount);

        if (calcedContractAmount.compareTo(contractAmountBigDecimal) < 0) {

            String realTimeBuyingPower = outputFct004Dto.getRealTimeBuyingPower().toPlainString();
            String otcBuytingContractAmountToday = outputFct004Dto.getOtcBuyingContractAmountToday().toPlainString();
            String contractAmountTodayWithinForeignBond = outputFct004Dto.getContractAmountTodayWithinForeignBond().toPlainString();

            DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_FRS_BUY_LIMIT_OVERFLOW,
                    IfaCommonUtil.getMessage(
                            ERRORS_FRS_BUY_LIMIT_OVERFLOW,
                            new String[] {
                                    contractAmount,
                                    realTimeBuyingPower,
                                    otcBuytingContractAmountToday,
                                    contractAmountTodayWithinForeignBond
                            }
                    )
            );

            return dtoRes;
        }

        return null;
    }   

    /**
     * FCT001 利用者の口座に対する権限チェック
     *
     * @return OK：null、NG：エラーレスポンス用DataList
     */
    private <T> DataList<T> checkFct001() {
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();

        // FCT001の呼び出し
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(butenCode);
        inputFct001Dto.setAccountNumber(accountNumber);
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);

        // 対象顧客参照権限有無 = 権限なし の場合、権限なしエラーを返す
        if (
                outputFct001Dto == null
                || outputFct001Dto.getTargetCustomerRefAuthFlag() == null
                || outputFct001Dto.getTargetCustomerRefAuthFlag().equals(FCT001_TARGET_CUSTOMER_REF_AUTH_FLAG_UNAUTHORIZED)
        ) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_BUTEN_ACCOUNT_NOT_EXIST,
                    IfaCommonUtil.getMessage(
                                ERRORS_BUTEN_ACCOUNT_NOT_EXIST,
                                new String[] { butenCode, accountNumber }
                    )
            );

            return dtoRes;
        }

        // 取引停止フラグ = 取引停止口座 の場合、取引停止エラーを返す
        if (
                outputFct001Dto.getTradeSuspendFlag() == null
                || outputFct001Dto.getTradeSuspendFlag().equals(FCT001_TRADE_SUSPEND_FLAG_SUSPEND)
        ) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_CMN_SELECTED_ACCOUNT_OUT_OF_SERVICE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTED_ACCOUNT_OUT_OF_SERVICE)
            );

            return dtoRes;
        }

        return null;
    }


    /**
     * FCT003 取引コース媒介可否チェック
     *
     * @param tradeClassification 売却："1"、買付："3"
     * @return OK：null、NG：エラーレスポンス用DataList
     * @exception Exception システムエラー
     */
    private <T> DataList<T> checkFct003(String tradeClassification) throws Exception {
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        String tradeCd = convTradeKbn2ForeignStockTradeClass(tradeClassification);

        // FCT003の呼び出し
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setButenCode(butenCode);
        inputFct003Dto.setAccountNumber(accountNumber);
        inputFct003Dto.setProductCd(SECURITY_MONEY_CLASS_FOREIGN_STOCK);
        inputFct003Dto.setTradeCd(tradeCd);
        inputFct003Dto.setCountryCd(NATIONALITY_CODE_US);
        inputFct003Dto.setCurrencyCode(CURRENCY_CODE_NONE);
        OutputFct003Dto outputFct003Dto = fct003.doCheck(inputFct003Dto);

        // 取引停止フラグ = 取引停止口座 の場合、取引停止口座エラーを返す
        if (
                outputFct003Dto == null
                || CollectionUtils.isEmpty(outputFct003Dto.getMediateProprietyList())
                || outputFct003Dto.getMediateProprietyList().get(0).getMediatePropriety() == null
                || !outputFct003Dto.getMediateProprietyList().get(0).getMediatePropriety()
                        .equals(FCT003_MEDIATE_PROPRIETY_LIST_MEDIATE_PRPRIETY_AVAILABLE)
        ) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(
                            ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                            new String[] {codeListService.getValue(
                                    MSG_DISPLAY_TARGET_TRADE,
                                    MSG_DISPLAY_TARGET_TRADE_FOREIGN_STOCK_COUNTER_TRADE,
                                    DISPLAY_PATTERN_1
                            )}
                    )
            );

            return dtoRes;
        }

        return null;
    }


    /**
     * FCT006 コンプラランクチェック
     *
     * @param innerData ワーニングがあれば格納するため
     * @param kanyuKbn 勧誘区分
     * @param orderMethod 受注方法
     * @return OK：null、NG：エラーレスポンス用DataList
     * @exception Exception システムエラー
     */
    private DataList<IfaForeignStockCounterOrderInputA008ResponseDto> checkFct006(
            IfaForeignStockCounterOrderInputA008ResponseDto innerData,
            String brandCode,
            String kanyuKbn,
            String orderMethod
    ) throws Exception {

        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        String innerKanyuKbn = convForeignStockInvitationType2InvitationType(kanyuKbn);
        String innerOrderMethod = convForeignStockOrderMethodType2OrderMethodType(orderMethod);

        // FCT006の呼び出し
        InputFct006Dto inputFct006Dto = new InputFct006Dto();
        inputFct006Dto.setButenCode(butenCode);
        inputFct006Dto.setAccountNumber(accountNumber);
        inputFct006Dto.setBrDomesticFgnInd(DOMESTIC_FOREIGN_TYPE_FOREIGN);
        inputFct006Dto.setBrBrandInd(SECURITY_TYPE_STOCK);
        inputFct006Dto.setBrandCode1(brandCode);
        inputFct006Dto.setCountryCode("US");
        inputFct006Dto.setInvitationType(innerKanyuKbn);
        inputFct006Dto.setOrderMethod(innerOrderMethod);
        inputFct006Dto.setComplaCheckKind(COMPLA_CHECK_KIND_ORDER_BUY);
        OutputFct006Dto outputFct006Dto = fct006.doCheck(inputFct006Dto);

        switch (outputFct006Dto.getJudgementResult()) {
            case "0":
                break;

            case "1":
                innerData.setMsg(IfaCommonUtil.getMessage(outputFct006Dto.getMessageId()));
                innerData.setChkBoxLabel(outputFct006Dto.getChkBoxLabel());
                break;

            case "2": // fall-through
            default:
                DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.FATAL,
                        outputFct006Dto.getMessageId(),
                        IfaCommonUtil.getMessage(outputFct006Dto.getMessageId())
                );

                return dtoRes;
        }

        innerData.setJudgmentResult(outputFct006Dto.getJudgementResult());

        return null;
    }


    /**
     * FCT021 取引制限チェック<br>
     * 取引制限のチェックを行い、ワーニングがあればinnerDataに格納する。<br>
     * エラーがあれば、エラーレスポンス用のDataListを返却する。
     *
     * @param innerData ワーニングがあれば、格納するため
     * @param tradeClassification 売却："1"、買付："3"
     * @return OK：null、NG：エラーレスポンス用DataList
     * @exception Exception システムエラー
     */
    private DataList<IfaForeignStockCounterOrderInputA008ResponseDto> checkFct021(
            IfaForeignStockCounterOrderInputA008ResponseDto innerData,
            String tradeClassification
    ) throws Exception {
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        String tradeCd = convTradeKbn2ForeignStockTradeClass(tradeClassification);

        // FCT021を呼び出し
        InputFct021Dto inputFct021Dto = new InputFct021Dto();
        inputFct021Dto.setButenCode(butenCode);
        inputFct021Dto.setAccountNumber(accountNumber);
        inputFct021Dto.setProductCd(SECURITY_MONEY_CLASS_FOREIGN_STOCK);
        inputFct021Dto.setTradeCd(tradeCd);
        inputFct021Dto.setCountryCd(NATIONALITY_CODE_US);
        inputFct021Dto.setCurrencyCode(CURRENCY_CODE_NONE);
        OutputFct021Dto outputFct021Dto = fct021.doCheck(inputFct021Dto);

        // 注意情報エラー有無をチェック
        if (
                outputFct021Dto == null
                || outputFct021Dto.getNoteInfoErrFlag().equals(NOTE_INFO_ERR_FLAG_ERROR)
        ) {
            DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_CMN_NOTICE_ERROR_CHECK,
                    IfaCommonUtil.getMessage(
                            ERRORS_CMN_NOTICE_ERROR_CHECK,
                            new String[] {codeListService.getValue(
                                    MSG_DISPLAY_TARGET_TRADE,
                                    MSG_DISPLAY_TARGET_TRADE_FOREIGN_STOCK_TRADE,
                                    DISPLAY_PATTERN_1
                            )}
                    )
            );

            return dtoRes;

        }

        // お知らせエラー有無をチェック
        if (outputFct021Dto.getNoteLimitErrFlag().equals(NOTE_LIMIT_ERR_FLAG_ERROR)) {
            DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_INFORMATION_CHECK,
                    IfaCommonUtil.getMessage(ERRORS_INFORMATION_CHECK)
            );

            return dtoRes;
        }

        // 注意情報アラートがあればinnerDataに格納
        if (outputFct021Dto.getNoteInfoAlertFlag().equals(NOTE_INFO_ALERT_FLAG_ALERT)) {
            innerData.setNoticeInfoAlert(IfaCommonUtil.getMessage(
                    WARNINGS_CMN_NOTICE_WARNING_CHECK,
                    new String[] {codeListService.getValue(
                        MSG_DISPLAY_TARGET_TRADE,
                        MSG_DISPLAY_TARGET_TRADE_FOREIGN_STOCK_TRADE,
                        DISPLAY_PATTERN_1
                    )}
            ));
        }

        // お知らせアラートがあればinnerDataに格納
        if (outputFct021Dto.getNoteLimitAlertFlag().equals(NOTE_LIMIT_ALERT_FLAG_ALERT)) {
            innerData.setNoticeAlert(IfaCommonUtil.getMessage(WARNINGS_CMM_INFORMATION_CHECK));
        }

        return null;
    }


    /**
     * 外国証券情報のチェック
     *
     * @param dtoReq A008のリクエストDTO
     * @return OK：null、NG：レスポンス用DataList
     * @exception Exception システムエラー
     */
    private DataList<IfaForeignStockCounterOrderInputA008ResponseDto> checkForeignSecurityInfo(
            IfaForeignStockCounterOrderInputA008RequestDto dtoReq
    ) throws Exception {
        
        // 外国証券情報.版版 / 交付日の画面側表示なしならばreturn
        String tradeClassification = dtoReq.getTradeKbn();
        String foreignStockYmd = dtoReq.getForeignStockYmd();
        if (
                !StringUtils.equals(tradeClassification, TRADE_CLASSIFICATION_BUY)
                || StringUtil.isNullOrEmpty(foreignStockYmd)
        ) {
            return null;
        }

        // 外国証券情報.版版の一致性チェック
        String forCheckForeignStockYmd = dtoReq.getForCheckForeignStockYmd();
        if (!foreignStockYmd.equals(forCheckForeignStockYmd)) {
            DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.FATAL,
                        ERRORS_FOREIGN_STOCK_CODE_CHANGE,
                        IfaCommonUtil.getMessage(ERRORS_FOREIGN_STOCK_CODE_CHANGE)
            );

            return dtoRes;
        }

        // 現在日付 < 外国証券情報.交付日の場合エラー
        LocalDate currentDate = ifaDateUtil.getCurrentLocalDate(ZoneId.of("UTC+09:00"));
        String documentDeliveryDate = dtoReq.getDocumentDeliveryDate();
        LocalDate documentDeliveryDateLocalDate
                = LocalDate.parse(documentDeliveryDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        if (0 < documentDeliveryDateLocalDate.compareTo(currentDate)) {
            DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.FATAL,
                        ERRORS_DATE_SPECIFY_TO_TODAY,
                        IfaCommonUtil.getMessage(
                                ERRORS_DATE_SPECIFY_TO_TODAY,
                                new String[] { "外国証券情報交付日" }
                        )
            );

            return dtoRes;
        }

        // 版版と交付日の関連チェック
        LocalDate foreignStockYmdLocalDate
                = LocalDate.parse(foreignStockYmd, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        if (documentDeliveryDateLocalDate.compareTo(foreignStockYmdLocalDate) < 0) {
            DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_DATE_SPECIFY_AFTER_APPOINTED_DAY,
                    IfaCommonUtil.getMessage(ERRORS_DATE_SPECIFY_AFTER_APPOINTED_DAY)
            );

            return dtoRes;
        }

        return null;
    }


    /**
     * 外国株式口座開設済みチェック
     *
     * @return 開設済み：null、開設済みではない：エラーレスポンス用DataList
     */
    private <T> DataList<T> checkForeignStockAccount() {
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String foreignStockTradeAccountOpenStatus = cc.getForeignStockTradeAccountOpenStatus();

        if (
                foreignStockTradeAccountOpenStatus == null
                || !foreignStockTradeAccountOpenStatus.equals(CC_FOREIGN_STOCK_TRADE_ACCOUNT_OPEN_STATUS_OPEN)
        ) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_FOREIGN_STOCK_ACCOUNT_CHECK,
                    IfaCommonUtil.getMessage(ERRORS_FOREIGN_STOCK_ACCOUNT_CHECK)
            );

            return dtoRes;
        }

        return null;
    }


    /**
     * 数量チェック
     *
     * @param brandcode 銘柄コード
     * @param depositType 預り区分
     * @param quantity 数量
     * @return OK：null、NG：レスポンス用DataList
     */
    private DataList<IfaForeignStockCounterOrderInputA008ResponseDto> checkQuantity(
            String brandCode,
            String depositType,
            String quantity
    ) throws Exception {
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        String foreignMarginAccountType = cc.getForeignMarginAccountType();

        // 信用口座の場合下記チェックを行う
        if (foreignMarginAccountType.equals(FOREIGN_MARGIN_ACCOUNT_TYPE_MARGIN_ACCOUNT)) {
            // FCT035を呼び出し
            InputFct035Dto inputFct035Dto = new InputFct035Dto();
            inputFct035Dto.setButenCode(butenCode);
            inputFct035Dto.setAccountNumber(accountNumber);
            OutputFct035Dto outputFct035Dto = fct035.doCheck(inputFct035Dto);

            // 自動振替設定チェック
            if (
                    outputFct035Dto == null
                    || outputFct035Dto.getCapacityShortageAutoTransferSettingUSStocksFlag() == null
                    || outputFct035Dto.getCapacityShortageAutoTransferSettingUSStocksFlag().equals(MARGIN_BUYING_POWER_SHORTFALL_SECURITIES_TRUE)
            ) {
                DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.FATAL,
                        ERRORS_FRS_ORDER_VALUE_IS_MARGIN_BUYING_POWER_SHORTFALL_SECURITIES,
                        IfaCommonUtil.getMessage(ERRORS_FRS_ORDER_VALUE_IS_MARGIN_BUYING_POWER_SHORTFALL_SECURITIES)
                );

                return dtoRes;
            }

            // FCT034を呼び出し
            InputFct034Dto inputFct034Dto = new InputFct034Dto();
            inputFct034Dto.setButenCode(butenCode);
            inputFct034Dto.setAccountNumber(accountNumber);
            inputFct034Dto.setBrandCode(brandCode);
            inputFct034Dto.setDepositType(depositType);
            OutputFct034Dto outputFct034Dto = fct034.doCheck(inputFct034Dto);

            // 代用預りチェック
            if (
                    outputFct034Dto == null
                    || outputFct034Dto.getAlternativeDepositFlag() == null
                    || outputFct034Dto.getAlternativeDepositFlag().equals(COLLATERAL_SECURITIES_TRUE)
            ) {
                DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.FATAL,
                        ERRORS_FRS_ORDER_VALUE_IS_COLLATERAL_SECURITIES,
                        IfaCommonUtil.getMessage(ERRORS_FRS_ORDER_VALUE_IS_COLLATERAL_SECURITIES)
                );

                return dtoRes;
            }
        }

        // FCT028を呼び出し
        InputFct028Dto inputFct028Dto = new InputFct028Dto();
        inputFct028Dto.setButenCode(butenCode);
        inputFct028Dto.setAccountNumber(accountNumber);
        inputFct028Dto.setBrandCode(brandCode);
        inputFct028Dto.setDepositType(depositType);
        OutputFct028Dto outputFct028Dto = fct028.doCheck(inputFct028Dto);

        // APIエラーチェック
        if (outputFct028Dto.getApiStatusModel() != null) {
            DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    outputFct028Dto.getApiStatusModel().getApiErrorCode(),
                    outputFct028Dto.getApiStatusModel().getErrorMessage()
            );

            return dtoRes;
        }

        // 顧客売却可能数量(株数)（FCT028.売却可能数量(株数)） ＜ 画面.数量 の場合、エラー
        BigDecimal acPositionstockNumber = outputFct028Dto.getAcPositionStockNumber();
        BigDecimal quantityBigDecimal = new BigDecimal(quantity);
        
        if (acPositionstockNumber.compareTo(quantityBigDecimal) < 0) {
            
            String realtimeBalanceStockNumberSell = outputFct028Dto.getRealtimeBalanceStockNumberSell().toPlainString();
            String currentDayOtcSellStockNumber = outputFct028Dto.getCurrentDayOtcSellStockNumber().toPlainString();

            DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_FRS_COUNT_UNIT_OVERFLOW,
                    IfaCommonUtil.getMessage(
                            ERRORS_FRS_COUNT_UNIT_OVERFLOW,
                            new String[] {
                                    quantity,
                                    realtimeBalanceStockNumberSell,
                                    currentDayOtcSellStockNumber
                            }
                    )
            );

            return dtoRes;
        }

        return null;
    }


    /**
     * 数量チェック(残販売枠)
     *
     * @param tradeClassification 売却："1"、買付："3"
     * @param tradePrice 画面.取引価格
     * @param sellTradePrice SQL001.売取引価格
     * @param buyTradePrice SQL001.買取引価格
     * @return OK：null、NG：レスポンス用DataList
     * @exception Exception システムエラー
     */
    private DataList<IfaForeignStockCounterOrderInputA008ResponseDto> checkTradeCount(
            String tradeClassification,
            String quantity,
            BigDecimal sellTradeCount,
            BigDecimal buyTradeCount
    ) throws Exception {
        
        // 残販売枠（株数）
        BigDecimal tradeCount = BigDecimal.ZERO;

        // 売却の場合の残販売枠（株数）
        if (tradeClassification.equals(TRADE_CLASSIFICATION_SELL)) {
            tradeCount = sellTradeCount;
        }

        // 買付の場合の残販売枠（株数）
        if (tradeClassification.equals(TRADE_CLASSIFICATION_BUY)) {
            tradeCount = buyTradeCount;
        }

        // 残販売枠（株数）< 画面.数量 の場合エラー
        BigDecimal quantityBigDecimal = new BigDecimal(quantity);
        
        if (tradeCount.compareTo(quantityBigDecimal) < 0) {
            DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_TRADE_RANGE,
                    IfaCommonUtil.getMessage(
                            ERRORS_TRADE_RANGE,
                            new String[] { "数量", "上限数量" }
                    )
            );

            return dtoRes;
        }

        return null;
    }


    /**
     * 価格変更チェック
     *
     * @param tradeClassification 売却："1"、買付："3"
     * @param tradePrice 画面.取引価格
     * @param sellTradePrice SQL001.売取引価格
     * @param buyTradePrice SQL001.買取引価格
     * @return OK：null、NG：レスポンス用DataList
     * @exception Exception システムエラー
     */
    private DataList<IfaForeignStockCounterOrderInputA008ResponseDto> checkTradePriceUnchanged(
            String tradeClassification,
            String tradePrice,
            String sellTradePrice,
            String buyTradePrice
    ) throws Exception {

        // 計算された取引価格
        String calcedTradePrice = "";

        // 売却の場合の計算された取引価格
        if (tradeClassification.equals(TRADE_CLASSIFICATION_SELL)) {
            BigDecimal calcedTradePriceBigDecimal = new BigDecimal(sellTradePrice);
            BigDecimal ceiledTradePrice = calcedTradePriceBigDecimal.setScale(3, RoundingMode.CEILING);
            BigDecimal strippedTradePrice = ceiledTradePrice.stripTrailingZeros();
            calcedTradePrice = strippedTradePrice.toPlainString();
        }

        // 買付の場合の計算された取引価格
        if (tradeClassification.equals(TRADE_CLASSIFICATION_BUY)) {
            BigDecimal calcedTradePriceBigDecimal = new BigDecimal(buyTradePrice);
            BigDecimal flooredTradePrice = calcedTradePriceBigDecimal.setScale(3, RoundingMode.FLOOR);
            BigDecimal strippedTradePrice = flooredTradePrice.stripTrailingZeros();
            calcedTradePrice = strippedTradePrice.toPlainString();
        }

        // 計算された取引価格が画面から受け取った取引価格と異なる場合エラー
        if (!calcedTradePrice.equals(tradePrice)) {
            DataList<IfaForeignStockCounterOrderInputA008ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_TRADE_PRICE_CHANGE,
                    IfaCommonUtil.getMessage(ERRORS_TRADE_PRICE_CHANGE)
            );

            return dtoRes;
        }

        return null;
    }


    /**
     * 取引状況チェック
     *
     * @param tradeStatus 取引状況
     * @return OK：null、NG：エラーレスポンス用DataList
     * @exception Exception システムエラー
     */
    private <T> DataList<T> checkTradeStatus(String tradeStatus) throws Exception {

        if (
                tradeStatus == null
                || tradeStatus.equals(SQL001_TRADE_STATUS_TRADE_STOP)
        ) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_TRADE_STOP,
                    IfaCommonUtil.getMessage(ERRORS_TRADE_STOP)
            );

            return dtoRes;
        }

        return null;
    }


    /**
     * 取引時間チェック
     *
     * @param tradeStartTimeStr 取引開始時間(HH:MM形式)
     * @param tradeEndTimeStr 取引終了時間(HH:MM形式)
     * @return 取引時間内：null、取引時間外：エラーレスポンス用DataList
     * @exception Exception システムエラー
     */
    private <T> DataList<T> checkTradeTime(String tradeStartTimeStr, String tradeEndTimeStr) throws Exception {

        LocalTime currentTime = ifaDateUtil.getCurrentLocalTime(ZoneId.of("UTC+09:00"));
        LocalTime tradeStartTime = LocalTime.parse(tradeStartTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime tradeEndTime = LocalTime.parse(tradeEndTimeStr, DateTimeFormatter.ofPattern("HH:mm"));

        // 時刻を比較して、取引開始時間 <= 現在時刻 <= 取引終了時刻の関係でなければエラーを返す
        if (
                currentTime.isBefore(tradeStartTime)
                || currentTime.isAfter(tradeEndTime)
        ) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.FATAL,
                        ERRORS_TRADE_TIME,
                        IfaCommonUtil.getMessage(ERRORS_TRADE_TIME)
            );

            return dtoRes;
        }

        return null;
    }


    /**
     * FCT028 選択した銘柄、預り区分の注文可能数量を取得
     *
     * @param brandCode 銘柄コード
     * @param depositType 預り区分
     * @return 売却可能数量（株数）, エラーチェック(OK: null, NG: エラーレスポンスDTO)
     * @exception Exception システムエラー
     */
    private <T> Pair<BigDecimal, DataList<T>> getAcPositionStockNumberByDepositType(String brandCode, String depositType) throws Exception {
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();

        // FCT028を呼び出し
        InputFct028Dto inputFct028Dto = new InputFct028Dto();
        inputFct028Dto.setButenCode(butenCode);
        inputFct028Dto.setAccountNumber(accountNumber);
        inputFct028Dto.setBrandCode(brandCode);
        inputFct028Dto.setDepositType(depositType);
        OutputFct028Dto outputFct028Dto = fct028.doCheck(inputFct028Dto);

        // APIエラーチェック
        if (outputFct028Dto.getApiStatusModel() != null) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    outputFct028Dto.getApiStatusModel().getApiErrorCode(),
                    outputFct028Dto.getApiStatusModel().getErrorMessage()
            );

            return Pair.of(null, dtoRes);
        }

        return Pair.of(outputFct028Dto.getAcPositionStockNumber(), null);
    }


    /**
     * FCT004 選択した預り区分の買付余力(外貨)を取得
     *
     * @param depositType 預り区分
     * @return 計算後の余力金額
     * @exception Exception システムエラー
     */
    private BigDecimal getForeignBuyingPowerByDepositType(String depositType) throws Exception {
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        String depositTypeExtKey = codeListService.convertKeyToExtKey("FOREIGN_DEPOSIT_TYPE", "Athena", depositType);

        // FCT004を呼び出し
        InputFct004Dto inputFct004Dto = new InputFct004Dto();
        inputFct004Dto.setButenCode(butenCode);
        inputFct004Dto.setAccountNumber(accountNumber);
        inputFct004Dto.setDepositType(depositTypeExtKey);
        OutputFct004Dto outputFct004Dto = fct004.doCheck(inputFct004Dto);

        return outputFct004Dto.getByingPowerMoneyAfterCalculate();
    }


    /**
     * 区分.勧誘区分（外株)を区分.勧誘区分に変換する
     * 変換できない場合NULLを返す
     *
     * @param kanyuKbn 区分.勧誘区分（外株）
     * @return 区分.勧誘区分
     */
    private String convForeignStockInvitationType2InvitationType(String kanyuKbn) {

        if (kanyuKbn == null) {
            return null;
        }

        switch (kanyuKbn) {
            case FOREIGN_STOCK_INVITATION_TYPE_SOLICITATION:
                return INVITATION_TYPE_SOLICITATION;

            case FOREIGN_STOCK_INVITATION_TYPE_NO_SOLICITATION:
                return INVITATION_TYPE_NO_SOLICITATION;
        
            default:
                return null;
        }
    }

    /**
     * 区分.受注方法区分（外株)を区分.受注方法区分に変換する
     * 変換できない場合NULLを返す
     *
     * @param orderMethod 区分.受注方法区分（外株)
     * @return 区分.受注方法区分
     */
    private String convForeignStockOrderMethodType2OrderMethodType(String orderMethod) {

        if (orderMethod == null) {
            return null;
        }

        switch (orderMethod) {
            case FOREIGN_STOCK_ORDER_METHOD_TYPE_COUNTER:
                return ORDER_METHOD_TYPE_COUNTER;

            case FOREIGN_STOCK_ORDER_METHOD_TYPE_VISIT:
                return ORDER_METHOD_TYPE_VISIT;

            case FOREIGN_STOCK_ORDER_METHOD_TYPE_CALL_OTHER:
                return ORDER_METHOD_TYPE_CALL_OTHER;
        
            default:
                return null;
        }
    }


    /**
     * 画面から受け取った取引種別や売買区分を、区分.取引種別（外国株式）に変換<br>
     * 売却("1") => 店頭売("12")<br>
     * 買付("3") => 店頭買("11")<br>
     *
     * @param tradeKbn 取引種別 または 売買区分
     * @return 区分.取引種別（外国株式）(tradeKbnが売却("1")と買付("3")のいずれでもない場合null)
     * @exception Exception システムエラー
     */
    private String convTradeKbn2ForeignStockTradeClass(String tradeKbn) throws Exception {
        if (StringUtils.equals(tradeKbn, TRADE_CLASSIFICATION_SELL)) {
            return FOREIGN_STOCK_TRADE_CLASS_COUNTER_SELL;
        }

        if (StringUtils.equals(tradeKbn, TRADE_CLASSIFICATION_BUY)) {
            return FOREIGN_STOCK_TRADE_CLASS_COUNTER_BUY;
        }

        return null;
    }


    /**
     * BigDecimal型の数値を
     * xx万ドルの形に変換する。
     *
     * @param value 金額
     * @return [value/1万]万ドル
     */
    String formatBigDecimal2MillionDollarsString(BigDecimal value) {
        BigDecimal scaledDownValue = value.divide(new BigDecimal("10000"), 0, RoundingMode.HALF_UP);
        String scaledDownValueStr = scaledDownValue.toPlainString() + "万ドル";

        return scaledDownValueStr;
    }

}
