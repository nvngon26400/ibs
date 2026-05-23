package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.math.BigDecimal;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct020;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct037;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct020Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct037Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct020Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.bizcommon.model.OutputFct037Dto;
import com.sbisec.helios.ap.bizcommon.util.PaymentLimitUtil;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaOrderListDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA002ResponseDtoDomesticMutualFund;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA002ResponseDtoDomesticMutualFundRegularRecruitment;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA002ResponseDtoDomesticStock;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA002ResponseDtoForeignStockCounterOrder;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA002ResponseDtoForeignStockEntrustOrder;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA002ResponseDtoSubscriptOrder;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA005aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA005aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA005aResponseDtoOrderList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums.AccumulateCourse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaOrderListService;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaOrderListCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB020302_0101-01
 * 画面名：注文一覧
 *
 * @author BASE李
 2024/03/30 新規作成
 */
@Component(value = "cmpIfaOrderListService")
public class IfaOrderListServiceImpL implements IfaOrderListService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaOrderListServiceImpL.class);
    
    @Autowired
    private IfaOrderListDao dao;
    
    @Autowired
    private CodeListService codeListService;
    
    @Autowired
    private ComplianceService complianceService;
    
    @Autowired
    private Fct030 fct030;
    
    @Autowired
    private Fct020 fct020;
    
    @Autowired
    private Fct037 fct037;
    
    @Autowired
    private Fct038 fct038;

    @Autowired
    private PaymentLimitUtil paymentLimitUtil;

    private static final String DATE_FORMAT_NEN_GA_PI = "yyyy年MM月dd日";
    
    private static final String DATE14 = "yyyy/MM/dd HH:mm:ss";
    
    private static final String DATE14_WITHOUT_SEPARATOR = "yyyyMMddHHmmss";
    
    private static final String DATE14_WITH_SEPARATOR = "yyyy-MM-dd HH:mm:ss";
    
    private static final String DATE8 = "yyyy/MM/dd";
    private static final String DATE8_WITHOUT_SEPARATOR = "yyyyMMdd";
    
    private static final String COURSE_ERROR = "取引コース";
    
    private static final String PERIOD = "期間指定";
    
    private static final String MONTH_12 = "12ヶ月";
    
    private static final String ORDER_SELECT = "商品";
    
    /** 権限SBI証券本店 */
    private static final String PRIVID_1 = "1";
    
    /** 国内株式 */
    private static final String ORDER_0 = "0";
    
    /** 国内投資信託 */
    private static final String ORDER_1 = "1";
    
    /** 募集注文 */
    private static final String ORDER_2 = "2";
    
    /** 外国株式（委託注文） */
    private static final String ORDER_3 = "3";
    
    /** 外国株式（店頭注文） */
    private static final String ORDER_4 = "4";
    
    /** 国内投資信託（定期積立） */
    private static final String ORDER_5 = "5";
    
    /** コース区分 */
    private static final String COURSE_ONE = "1";
    private static final String COURSE_TWO = "2";
    private static final String COURSE_THREE = "3";
    private static final String COURSE_FOUR = "4";
    private static final String COURSE_FIVE = "5";
    
    /** 設定日の日付が31の場合、MM/月末と表示する */
    private static final String MONTH_END_CODE = "31";
    private static final String MONTH_END_VALUE = "月末";
    
    /** 取引種別 */
    private static final String SAVINGS = "積立";
    
    /** 決済方法 = 現金 */
    private static final String CASH_CODE = "1";
    
    /** 決済方法区分 */
    private static final String FUND_RESERVE_PAYMENT_METHOD = "FUND_RESERVE_PAYMENT_METHOD";
    
    private static final String FUND_RESERVE_MODIFY_KBN = "FUND_RESERVE_MODIFY_KBN";
    
    private static final String FUND_RESERVE_NISA_BARELY_BUYING_KBN = "FUND_RESERVE_NISA_BARELY_BUYING_KBN";
    
    private static final String FUND_RESERVE_TAX_SHIFT_KBN = "FUND_RESERVE_TAX_SHIFT_KBN";
    
    /** 預り区分（投信積立） */
    private static final String RESERVE_TRADE_DEPOSIT_TYPE = "RESERVE_TRADE_DEPOSIT_TYPE";
    
    private static final String MAX_COUNT_5000 = "5,000";
    
    private static final String PRICE_TANI_EN = "円";
    
    private static final String PRICE_TANI_KOU = "口";
    
    private static final String NASHI = "";
    
    /** 取引種別＝'2':購入（積立）の場合 */
    private static final String JTO_TRADE_TYPE_2 = "2";
    
    private static final String ORDER_POINT_UNCERTAIN = "未確定";
    
    /** 仕法区分＝'0':現物の場合 */
    private static final String METHOD_TYPE_0 = "0";
    
    /** 仕法区分＝'0':現物の場合 */
    private static final String METHOD_TYPE_10 = "10";
    
    /** 仕法区分＝'0':現物の場合 */
    private static final String METHOD_TYPE_11 = "11";
    
    /** 売買区分＝'3':買付の場合 */
    private static final String TRADEKBN_3 = "3";
    
    /** 売買区分＝'1':売付の場合 */
    private static final String TRADEKBN_1 = "1";
    
    /** 取引種別0現物買 */
    private static final String TRADE_TYPE_0 = "0";
    
    /** 取引種別1現物売 */
    private static final String TRADE_TYPE_1 = "1";
    
    /** 取引種別2新規買 */
    private static final String TRADE_TYPE_2 = "2";
    
    /** 取引種別3新規売 */
    private static final String TRADE_TYPE_3 = "3";
    
    /** 取引種別4返済買 */
    private static final String TRADE_TYPE_4 = "4";
    
    /** 取引種別5返済売 */
    private static final String TRADE_TYPE_5 = "5";
    
    /** 取引種別7現引 */
    private static final String TRADE_TYPE_7 = "7";
    
    /** 取引種別8現渡 */
    private static final String TRADE_TYPE_8 = "8";
    /** 取引種別11店頭買 */
    private static final String TRADE_TYPE_11 = "11";
    
    /** 取引種別12店頭売 */
    private static final String TRADE_TYPE_12 = "12";
    
    private static final String DOMESTIC_FORMAT = "#,##0.##";
    
    /** 価格条件区分 4 */
    private static final String PRICE_CONDITIONS_TYPE_4 = "4";
    
    /** 価格条件区分 3 */
    private static final String PRICE_CONDITIONS_TYPE_3 = "3";
    
    private static final String ZERO = "0";
    
    private static final String STOP_MARKETORDER_PREFIX = "逆指値/成行：現在値が";
    private static final String STOP_MARKETORDER_SUFFX_UP = "以上になった時点で成行で発注";
    private static final String STOP_MARKETORDER_SUFFX_DOWN = "以下になった時点で成行で発注";
    private static final String STOP_SASHINE_PREFIX = "逆指値/指値：現在値が";
    private static final String STOP_SASHINE_MID_UP = "以上になった時点で";
    private static final String STOP_SASHINE_MID_DOWN = "以下になった時点で";
    private static final String STOP_SASHINE_SUFFX = "で発注";
    
    private static final String FCT038_SCREEN_ID = "SUB020302_0101-01";
    
    private static final String TRADEKBN_K = "K";
    private static final String TRADEKBN_U = "U";
    private static final String TRADEKBN_V = "V";
    private static final String TRADEKBN_A = "A";
    private static final String TRADEKBN_B = "B";
    private static final String[] SASINARIKBNLIST = { " ", "Z", "I", "F", "P" };
    
    /** 価格条件区分1指値 */
    private static final String PRICECONDITIONSTYPE_1 = "1";
    /** 価格条件区分2成行 */
    private static final String PRICECONDITIONSTYPE_2 = "2";
    /** 価格条件区分3逆指値(指値) */
    private static final String PRICECONDITIONSTYPE_3 = "3";
    /** 価格条件区分4逆指値(成行) */
    private static final String PRICECONDITIONSTYPE_4 = "4";
    
    private static final String MARKET_ORDER = "成行";

    
    
    /** 乗換優遇枠適用 */
    private static final String TRANSFERS_PREFERENTIAL_QUOTA_APPLICATION = "TRANSFERS_PREFERENTIAL_QUOTA_APPLICATION";
    /** 目論見書の交付方法 */
    private static final String PROSPECTUS_ISSUE_METHOD = "PROSPECTUS_ISSUE_METHOD";
    /** 乗換勧誘 */
    private static final String SOLICITING_TRANSFERS = "SOLICITING_TRANSFERS";
    /** 利益相反説明 */
    private static final String CONFLICT_OF_INTEREST_EXPLAIN = "CONFLICT_OF_INTEREST_EXPLAIN";
    /** 費用について説明済 */
    private static final String COST_EXPLAINED = "COST_EXPLAINED";
    /** 複数取引業者での手数料等明示済 */
    private static final String MULTIPLE_TRADE_CLEARLY_COMM_STATED = "MULTIPLE_TRADE_CLEARLY_COMM_STATED";
    /** 同一通貨/同一国の乗換 */
    private static final String SAME_CURRENCY_SAME_COUNTRY_TRANSFERS = "SAME_CURRENCY_SAME_COUNTRY_TRANSFERS";
    /** 他社間投信乗換勧誘 */
    private static final String INTERCOMPANY_MUTUAL_FUND_TRANSFER_SOLICITATION = "INTERCOMPANY_MUTUAL_FUND_TRANSFER_SOLICITATION";
    /** 短期売却確認 */
    private static final String SHORT_TERM_SALE_CONFIRM = "SHORT_TERM_SALE_CONFIRM";
    /** 償還前売却確認 */
    private static final String PRE_REDEMPTION_SELL_CONFIRM = "PRE_REDEMPTION_SELL_CONFIRM";
    /** 契約締結前交付書面コード */
    private static final String PRE_CONTRACT_DOC_CODE = "PRE_CONTRACT_DOC_CODE";
    /** 注文状況（一覧） */
    private static final String LIST_ORDER_STATUS = "LIST_ORDER_STATUS";
    /** 取引種別（国内株式） */
    private static final String DOMESTIC_STOCK_TRADE_CLASS = "DOMESTIC_STOCK_TRADE_CLASS";
    /** 預り区分（国内） */
    private static final String DOMESTIC_DEPOSIT_TYPE = "DOMESTIC_DEPOSIT_TYPE";
    /** 選択市場 */
    private static final String SELECT_MARKET = "SELECT_MARKET";
    /** 注文種別（一覧） */
    private static final String LIST_ORDER_CLASS = "LIST_ORDER_CLASS";
    /** 手数料区分 */
    private static final String COMM_TYPE = "COMM_TYPE";
    /** 指成区分 */
    private static final String LIMIT_MARKET_TYPE = "LIMIT_MARKET_TYPE";
    /** 勧誘区分 */
    private static final String INVITATION_TYPE = "INVITATION_TYPE";
    /** 受注方法区分 */
    private static final String ORDER_METHOD_TYPE = "ORDER_METHOD_TYPE";
    /** 対面募集注文預り区分 */
    private static final String FACE_TO_FACE_SUBSCRIPT_ORDER_DEPOSIT_TYPE = "FACE_TO_FACE_SUBSCRIPT_ORDER_DEPOSIT_TYPE";
    /** 預り区分（投信） */
    private static final String MUTUAL_FUND_DEPOSIT_TYPE = "MUTUAL_FUND_DEPOSIT_TYPE";
    /** ポイント種別 */
    private static final String POINT_TYPE = "POINT_TYPE";
    /** 分配金受取方法指定 */
    private static final String DISTRIBUTION_RECEIVE_METHOD = "DISTRIBUTION_RECEIVE_METHOD";
    /** 注文状況（募集） */
    private static final String SUBSCRIPT_ORDER_STATUS = "SUBSCRIPT_ORDER_STATUS";
    /** 重要事項の説明 */
    private static final String IMPORTANT_MATTERS_EXPLAIN = "IMPORTANT_MATTERS_EXPLAIN";
    /** ワーニング申請取引 */
    private static final String WARNING_APPLICATION_TRAD = "WARNING_APPLICATION_TRAD";
    /** 外国市場区分 */
    private static final String FOREIGN_MARKET_TYPE = "FOREIGN_MARKET_TYPE";
    /** 取引種別（外国株式） */
    private static final String FOREIGN_STOCK_TRADE_CLASS = "FOREIGN_STOCK_TRADE_CLASS";
    /** 取引種別（国内投信） */
    private static final String DOMESTIC_MUTUAL_FUND_TRADE_CLASS = "DOMESTIC_MUTUAL_FUND_TRADE_CLASS";
    /** 選択可能価格条件 */
    private static final String SELECT_ABLE_PRICE_CONDITIONS = "SELECT_ABLE_PRICE_CONDITIONS";
    /** 決済区分 */
    private static final String SETTLEMENT_TYPE = "SETTLEMENT_TYPE";
    /** 預り区分（外国） */
    private static final String FOREIGN_DEPOSIT_TYPE = "FOREIGN_DEPOSIT_TYPE";
    /** 勧誘区分（外株） */
    private static final String FOREIGN_STOCK_INVITATION_TYPE = "FOREIGN_STOCK_INVITATION_TYPE";
    /** 受注方法区分（外株） */
    private static final String FOREIGN_STOCK_ORDER_METHOD_TYPE = "FOREIGN_STOCK_ORDER_METHOD_TYPE";
    /** 英文開示銘柄 */
    private static final String ISSUES_DISCLOSED_IN_ENGLISH_BRAND = "ISSUES_DISCLOSED_IN_ENGLISH_BRAND";
    /** 乗換え勧誘(ETF) */
    private static final String ETF_SOLICITING_TRANSFERS = "ETF_SOLICITING_TRANSFERS";
    /** 注文ステータス（外株店頭） */
    private static final String FOREIGN_STOCK_COUNTER_ORDER_STATUS = "FOREIGN_STOCK_COUNTER_ORDER_STATUS";
    /** 外国証券情報の交付方法 */
    private static final String FOREIGN_SECURITY_INFO_ISSUE_METHOD = "FOREIGN_SECURITY_INFO_ISSUE_METHOD";
    
    //APIタイプ
    private static final String EC_GW = "EC-GW";

    /** 表示パターン */
    private static final String DISPLAY_PATTERN_1 = "1";
    private static final String DISPLAY_PATTERN_2 = "2";
    private static final String DISPLAY_PATTERN_3 = "3";
    private static final String DISPLAY_PATTERN_4 = "4";
    private static final String DISPLAY_PATTERN_5 = "5";
    private static final String DISPLAY_PATTERN_7 = "7";
    
    private static final String PUNCTUATION = ".";
    
    private static final String INTEGER_FORMAT = "#,###";
    
    
    private enum MessageId {
        ERRORS_SELECTED("errors.selected"),
        ERRORS_DATERANGE("errors.dateRange"),
        ERRORS_CMN_IFAAGENTCODES_NOTEXIST("errors.cmn.ifaAgentCodes.notExist"),
        ERRORS_DATALIST_NOTFOUND("errors.dataList.notfound"),
        WARNINGS_DATALIST_OVERMAXROWNUM("warnings.dataList.overMaxRownum"),
        WARNINGS_DATALIST_CSV_OVERMAXROWNUM("warnings.dataList.csv.overMaxRownum"),
        ;
        
        private String key;
        
        private MessageId(String key) {
            this.key = key;
        }
    }

    /**
     * Fct020　cache　key
     *
     * @author SCSK
     *
     */
    private class Fct020InputDtoBrandCodeAndMarket {
        private String brandCode;
        private String market;
        
        @Override
        public int hashCode() {
            
            final int prime = 31;
            int result = 1;
            result = prime * result + getEnclosingInstance().hashCode();
            result = prime * result + Objects.hash(brandCode, market);
            return result;
        }
        
        @Override
        public boolean equals(Object obj) {
            
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Fct020InputDtoBrandCodeAndMarket other = (Fct020InputDtoBrandCodeAndMarket) obj;
            if (!getEnclosingInstance().equals(other.getEnclosingInstance())) {
                return false;
            }
                
            return Objects.equals(brandCode, other.brandCode) && Objects.equals(market, other.market);
        }
        
        private IfaOrderListServiceImpL getEnclosingInstance() {
            
            return IfaOrderListServiceImpL.this;
        }
        
    }
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaOrderListA001RequestDto
     * Dto レスポンス：IfaOrderListA001ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaOrderListA001ResponseDto> initializeA001(IfaOrderListA001RequestDto dtoReq)
            throws Exception {
        DataList<IfaOrderListA001ResponseDto> dtoRes = new DataList<IfaOrderListA001ResponseDto>();
        IfaOrderListA001ResponseDto ifaOrderListA001ResponseDto = new IfaOrderListA001ResponseDto();
        dtoRes.getDataList().add(ifaOrderListA001ResponseDto);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaOrderListServiceImplL.initializeA001");
        }
        
        // ① 注文一覧コメントを取得する。
        DataList<IfaOrderListSql007ResponseModel> sql007Res = dao.selectIfaOrderListSql007();
        if (sql007Res.size() != 0) {
            ifaOrderListA001ResponseDto.setOrderListComment(sql007Res.get(0).getOrderListComment());
        }

        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaOrderListA002RequestDto
     * Dto レスポンス：IfaOrderListA002ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaOrderListA002ResponseDto> displayA002(IfaOrderListA002RequestDto dtoReq)
            throws Exception {
        DataList<IfaOrderListA002ResponseDto> dtoRes = new DataList<IfaOrderListA002ResponseDto>();
        IfaOrderListA002ResponseDto a002Res = new IfaOrderListA002ResponseDto();
        dtoRes.getDataList().add(a002Res);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaOrderListServiceImplL.displayA002");
        }
        // ① リクエスト.取引コースのチェックを行う。
        if (ObjectUtils.isEmpty(dtoReq.getCourse())) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_SELECTED.key, IfaCommonUtil.getMessage(MessageId.ERRORS_SELECTED.key, new String[] {COURSE_ERROR}));
        }
        // ② リクエスト.期間指定（From）、リクエスト.期間指定（To）のチェックを行う。
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT_NEN_GA_PI);
        LocalDate periodYmFrom = LocalDate.parse(dtoReq.getPeriodYmFrom(), format);
        LocalDate periodYmTo = LocalDate.parse(dtoReq.getPeriodYmTo(), format);
        LocalDate periodNow = LocalDate.now();
        dtoReq.setPeriodYmFrom(periodYmFrom.format(DateTimeFormatter.BASIC_ISO_DATE));
        dtoReq.setPeriodYmTo(periodYmTo.format(DateTimeFormatter.BASIC_ISO_DATE));

        if (periodYmTo.minusMonths(12).compareTo(periodYmFrom) > 0 || periodNow.minusMonths(12).compareTo(periodYmFrom) > 0
        || periodNow.minusMonths(12).compareTo(periodYmTo) > 0) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_DATERANGE.key, IfaCommonUtil.getMessage(MessageId.ERRORS_DATERANGE.key, new String[] {PERIOD, MONTH_12}));
        }
        // ③ リクエスト.商品のチェックを行う。
        if (ObjectUtils.isEmpty(dtoReq.getOrderSelected())) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_DATERANGE.key, IfaCommonUtil.getMessage(MessageId.ERRORS_SELECTED.key, new String[] {ORDER_SELECT}));
        }
        
        // ④ ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：　参照可能な仲介業者コードと営業員コードを取得する。
        String privId = IfaCommonUtil.getUserAccount().getMedUsers().getPrivId();
        List<BrokerCharge> brokerChargeList = null;
        if (!PRIVID_1.equals(privId)) {
            OutputFct030Dto outputFct030Dto = fct030.getData(null);
            brokerChargeList = outputFct030Dto.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key, IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key));
            }
        } else {
            brokerChargeList = new ArrayList<>();
        }
        // ⑤ 取得件数合計 = 0を設定する。
        int sumCount = 0;
        // ⑥ リクエスト.商品 に '0':国内株式（現物・信用・現引・現渡） が設定されている場合、以下の処理を行う。
        List<String> orderList = Arrays.asList(dtoReq.getOrderSelected().split(","));
        DataList<IfaOrderListSql001ResponseModel> sql001Res = null;
        if (orderList.contains(ORDER_0)) {
            IfaOrderListSql001RequestModel sql001ReqModel = new IfaOrderListSql001RequestModel();
            BeanUtils.copyProperties(sql001ReqModel, dtoReq);
            sql001ReqModel.setPrivId(privId);
            sql001ReqModel.setBrokerChargeList(brokerChargeList);
            sql001Res = dao.selectIfaOrderListSql001(sql001ReqModel);
            if (sql001Res.size() != 0) {
                sumCount += sql001Res.get(0).getTotalCount();
            }
        }
        // ⑦ リクエスト.商品 に '1':国内投資信託 が設定されている場合、以下の処理を行う。
        DataList<IfaOrderListSql002ResponseModel> sql002Res = null;
        if (orderList.contains(ORDER_1)) {
            IfaOrderListSql002RequestModel sql002ReqModel = new IfaOrderListSql002RequestModel();
            BeanUtils.copyProperties(sql002ReqModel, dtoReq);
            sql002ReqModel.setPrivId(privId);
            sql002ReqModel.setBrokerChargeList(brokerChargeList);
            sql002Res = dao.selectIfaOrderListSql002(sql002ReqModel);
            if (sql002Res.size() != 0) {
                sumCount += sql002Res.get(0).getTotalCount();
            }
        }
        // ⑧ リクエスト.商品 に '2':募集注文 が設定されている場合、以下の処理を行う。
        DataList<IfaOrderListSql003ResponseModel> sql003Res = null;
        if (orderList.contains(ORDER_2)) {
            IfaOrderListSql003RequestModel sql003ReqModel = new IfaOrderListSql003RequestModel();
            BeanUtils.copyProperties(sql003ReqModel, dtoReq);
            sql003ReqModel.setPrivId(privId);
            sql003ReqModel.setBrokerChargeList(brokerChargeList);
            sql003Res = dao.selectIfaOrderListSql003(sql003ReqModel);
            if (sql003Res.size() != 0) {
                sumCount += sql003Res.get(0).getTotalCount();
            }
        }
        // ⑨ リクエスト.商品 に '3':外国株式（委託注文） が設定されている場合、以下の処理を行う。
        DataList<IfaOrderListSql004ResponseModel> sql004Res = null;
        if (orderList.contains(ORDER_3)) {
            IfaOrderListSql004RequestModel sql004ReqModel = new IfaOrderListSql004RequestModel();
            BeanUtils.copyProperties(sql004ReqModel, dtoReq);
            sql004ReqModel.setPrivId(privId);
            sql004ReqModel.setBrokerChargeList(brokerChargeList);
            sql004Res = dao.selectIfaOrderListSql004(sql004ReqModel);
            if (sql004Res.size() != 0) {
                sumCount += sql004Res.get(0).getTotalCount();
            }
        }
        // ⑩ リクエスト.商品 に '4':外国株式（店頭注文） が設定されている場合、以下の処理を行う。
        DataList<IfaOrderListSql005ResponseModel> sql005Res = null;
        if (orderList.contains(ORDER_4)) {
            IfaOrderListSql005RequestModel sql005ReqModel = new IfaOrderListSql005RequestModel();
            BeanUtils.copyProperties(sql005ReqModel, dtoReq);
            sql005ReqModel.setPrivId(privId);
            sql005ReqModel.setBrokerChargeList(brokerChargeList);
            sql005Res = dao.selectIfaOrderListSql005(sql005ReqModel);
            if (sql005Res.size() != 0) {
                sumCount += sql005Res.get(0).getTotalCount();
            }
        }
        // ⑫ リクエスト.商品 に '5':国内投資信託（定期積立） が設定されている場合、以下の処理を行う。
        DataList<IfaOrderListSql008ResponseModel> sql008Res = null;
        if (orderList.contains(ORDER_5)) {
            IfaOrderListSql008RequestModel sql008ReqModel = new IfaOrderListSql008RequestModel();
            BeanUtils.copyProperties(sql008ReqModel, dtoReq);
            sql008ReqModel.setPrivId(privId);
            sql008ReqModel.setBrokerChargeList(brokerChargeList);
            sql008Res = dao.selectIfaOrderListSql008(sql008ReqModel);
            if (sql008Res.size() != 0) {
                sumCount += sql008Res.get(0).getTotalCount();
            }
        }
        // ⑫ 取得件数合計チェック
        // 0件
        if (sumCount == 0) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.INFO,
                    MessageId.ERRORS_DATALIST_NOTFOUND.key, IfaCommonUtil.getMessage(MessageId.ERRORS_DATALIST_NOTFOUND.key));
        }
        // 5000件より多い
        if (sumCount > 5000) {
            dtoRes.setErrorLevel(ErrorLevel.WARNING.getId());
            dtoRes.setMessage(IfaCommonUtil.getMessage(MessageId.WARNINGS_DATALIST_OVERMAXROWNUM.key, new String[] {MAX_COUNT_5000, String.valueOf(sumCount)}));
            dtoRes.setReturnCode(MessageId.WARNINGS_DATALIST_OVERMAXROWNUM.key);
        }
        // ⑬　上限値5000件
        int limit = 5000;
        // ⑭   国内株式リストのデータの場合、以下の処理を行う。
        if (sql001Res != null) {
            Map<Fct020InputDtoBrandCodeAndMarket, String> fct020Cache = new HashMap<>();
            
            List<IfaOrderListA002ResponseDtoDomesticStock> domesticStockList = new ArrayList<>();
            for (IfaOrderListSql001ResponseModel sql001ResModel : sql001Res.getDataList()) {
                if (limit-- <= 0) {
                    break;
                }
                // 国内株式
                IfaOrderListA002ResponseDtoDomesticStock ifaOrderListA002ResponseDtoDomesticStock = new IfaOrderListA002ResponseDtoDomesticStock();
                BeanUtils.copyProperties(ifaOrderListA002ResponseDtoDomesticStock, sql001ResModel);
                ifaOrderListA002ResponseDtoDomesticStock.setSashine(sql001ResModel.getPrice());
                ifaOrderListA002ResponseDtoDomesticStock.setOrderDayTimeCalculation(sql001ResModel.getOrderDate() + sql001ResModel.getOrderDayTimeCalculation());
                // 外部コード　→　内部コード　変換
                String comIdR = codeListService.convertExtKeyToKey(COMM_TYPE, EC_GW, sql001ResModel.getComIdR());
                ifaOrderListA002ResponseDtoDomesticStock.setComIdR(comIdR);
                // fct020 cache key を作成
                Fct020InputDtoBrandCodeAndMarket fct020InputDtoBrandCodeAndMarket = new Fct020InputDtoBrandCodeAndMarket();
                fct020InputDtoBrandCodeAndMarket.brandCode = sql001ResModel.getBrandCode();
                fct020InputDtoBrandCodeAndMarket.market = sql001ResModel.getMarket();
                // fct020 cache　に国内株式リスト.銘柄コードと国内株式リスト.市場の組み合わせがなし
                if (!fct020Cache.containsKey(fct020InputDtoBrandCodeAndMarket)) {
                    InputFct020Dto inputFct020Dto = new InputFct020Dto();
                    inputFct020Dto.setBrandCode(sql001ResModel.getBrandCode());
                    inputFct020Dto.setMarketCode(sql001ResModel.getMarket());
                    // 評価用現在値を取得する
                    OutputFct020Dto outputFct020Dto = fct020.getData(inputFct020Dto);
                    ifaOrderListA002ResponseDtoDomesticStock.setCurrentPrice(outputFct020Dto.getCurrentValueForEvaluation());
                    // cache に格納
                    fct020Cache.put(fct020InputDtoBrandCodeAndMarket, outputFct020Dto.getCurrentValueForEvaluation());
                } else {
                    // fct020 cache がある
                    ifaOrderListA002ResponseDtoDomesticStock.setCurrentPrice(fct020Cache.get(fct020InputDtoBrandCodeAndMarket));
                }
                // 国内株式リストのデータに紐づく注文条件を取得する。
                OutputFct037Dto outputFct037Dto = fct037.getData(createInputFct037Dto(sql001ResModel));
                ifaOrderListA002ResponseDtoDomesticStock.setOrderTerms(outputFct037Dto.getOrderConditions());
                // 弁済期限（算出）
                ifaOrderListA002ResponseDtoDomesticStock.setPaymentDeadlineCalculation(paymentLimitUtil.getPaymentLimitOrderList(sql001ResModel.getTradeCd(),
                                                        sql001ResModel.getOrderStatus(),
                                                        sql001ResModel.getPaymentDeadline(),
                                                        sql001ResModel.getDateKbn(),
                                                        sql001ResModel.getDailyCreditKbn(),
                                                        sql001ResModel.getPaymentDeadlineDate()));
                
                // 国内株式リストに国内株式を格納
                domesticStockList.add(ifaOrderListA002ResponseDtoDomesticStock);
            }
            // a002Resに国内株式リストを格納
            a002Res.setIfaOrderListA002ResponseDtoDomesticStockList(domesticStockList);
        }
        // 国内投資信託リストのデータの場合、以下の処理を行う。
        if (sql002Res != null) {
            List<IfaOrderListA002ResponseDtoDomesticMutualFund> domesticMutualFundList = new ArrayList<>();
            for (IfaOrderListSql002ResponseModel sql002ResModel : sql002Res.getDataList()) {
                if (limit-- <= 0) {
                    break;
                }
                IfaOrderListA002ResponseDtoDomesticMutualFund ifaOrderListA002ResponseDtoDomesticMutualFund = new IfaOrderListA002ResponseDtoDomesticMutualFund();
                BeanUtils.copyProperties(ifaOrderListA002ResponseDtoDomesticMutualFund, sql002ResModel);
                ifaOrderListA002ResponseDtoDomesticMutualFund.setOrderDayTimeCalculation(sql002ResModel.getOrderDate() + sql002ResModel.getOrderTime());
                ifaOrderListA002ResponseDtoDomesticMutualFund.setFundCodeCalculation(sql002ResModel.getFundCodeTimes() + "." + sql002ResModel.getFundCodeIssues());
                // 外部コード　→　内部コード　変換
                String pointType = codeListService.convertExtKeyToKey(POINT_TYPE, EC_GW, sql002ResModel.getPointType());
                ifaOrderListA002ResponseDtoDomesticMutualFund.setPointType(pointType);
                // 口数/金額（算出値）を算出し
                String unit = sql002ResModel.getUnit();
                String amount = sql002ResModel.getAmount();
                if (!ObjectUtils.isEmpty(unit)) {
                    ifaOrderListA002ResponseDtoDomesticMutualFund.setUnitAmountCalculation(unit + PRICE_TANI_KOU);
                } else if (!ObjectUtils.isEmpty(amount)) {
                    ifaOrderListA002ResponseDtoDomesticMutualFund.setUnitAmountCalculation(amount + PRICE_TANI_EN);
                } else {
                    ifaOrderListA002ResponseDtoDomesticMutualFund.setUnitAmountCalculation(NASHI);
                }
                
                // 利用ポイント編集
                ifaOrderListA002ResponseDtoDomesticMutualFund.setOrderPoint(getOrderPoint(sql002ResModel));
                
                domesticMutualFundList.add(ifaOrderListA002ResponseDtoDomesticMutualFund);
            }
            a002Res.setIfaOrderListA002ResponseDtoDomesticMutualFundList(domesticMutualFundList);
        }
        if (sql003Res != null) {
            List<IfaOrderListA002ResponseDtoSubscriptOrder> subscriptOrderList = new ArrayList<>();
            for (IfaOrderListSql003ResponseModel sql003ResModel : sql003Res.getDataList()) {
                if (limit-- <= 0) {
                    break;
                }
                IfaOrderListA002ResponseDtoSubscriptOrder ifaOrderListA002ResponseDtoSubscriptOrder = new IfaOrderListA002ResponseDtoSubscriptOrder();
                BeanUtils.copyProperties(ifaOrderListA002ResponseDtoSubscriptOrder, sql003ResModel);
                subscriptOrderList.add(ifaOrderListA002ResponseDtoSubscriptOrder);
            }
            a002Res.setIfaOrderListA002ResponseDtoSubscriptOrderList(subscriptOrderList);
        }
        // 外国株式（委託注文）リストのデータの場合
        if (sql004Res != null) {
            List<IfaOrderListA002ResponseDtoForeignStockEntrustOrder> foreignStockEntrustOrderList = new ArrayList<>();
            for (IfaOrderListSql004ResponseModel sql004ResModel : sql004Res.getDataList()) {
                if (limit-- <= 0) {
                    break;
                }
                IfaOrderListA002ResponseDtoForeignStockEntrustOrder ifaOrderListA002ResponseDtoForeignStockEntrustOrder = new IfaOrderListA002ResponseDtoForeignStockEntrustOrder();
                BeanUtils.copyProperties(ifaOrderListA002ResponseDtoForeignStockEntrustOrder, sql004ResModel);
                // 取引種別を算出
                ifaOrderListA002ResponseDtoForeignStockEntrustOrder.setTradeCdCalculation(getTradeCdCalculation(sql004ResModel));
                // 条件詳細（算出）を算出、以下を設定する
                ifaOrderListA002ResponseDtoForeignStockEntrustOrder.setConditionDetailsCalculation(getConditionDetailsCalculation(sql004ResModel));
                foreignStockEntrustOrderList.add(ifaOrderListA002ResponseDtoForeignStockEntrustOrder);
            }
            a002Res.setIfaOrderListA002ResponseDtoForeignStockEntrustOrderList(foreignStockEntrustOrderList);
        }
        
        if (sql005Res != null) {
            List<IfaOrderListA002ResponseDtoForeignStockCounterOrder> foreignStockCounterOrderList = new ArrayList<>();
            for (IfaOrderListSql005ResponseModel sql005ResModel : sql005Res.getDataList()) {
                if (limit-- <= 0) {
                    break;
                }
                IfaOrderListA002ResponseDtoForeignStockCounterOrder ifaOrderListA002ResponseDtoForeignStockCounterOrder = new IfaOrderListA002ResponseDtoForeignStockCounterOrder();
                BeanUtils.copyProperties(ifaOrderListA002ResponseDtoForeignStockCounterOrder, sql005ResModel);
                ifaOrderListA002ResponseDtoForeignStockCounterOrder.setOrderDate(sql005ResModel.getOrderTime());
                ifaOrderListA002ResponseDtoForeignStockCounterOrder.setTradeCdCalculation(getTradeCdCalculation(sql005ResModel));
                foreignStockCounterOrderList.add(ifaOrderListA002ResponseDtoForeignStockCounterOrder);
            }
            a002Res.setIfaOrderListA002ResponseDtoForeignStockCounterOrderList(foreignStockCounterOrderList);
        }
        // 国内投資信託（定期積立）リストのデータの場合、以下の処理を行う。
        if(sql008Res != null) {
            List<IfaOrderListA002ResponseDtoDomesticMutualFundRegularRecruitment> domesticMutualFundRegularRecruitmentList = new ArrayList<>();
            for (IfaOrderListSql008ResponseModel sql008ResModel : sql008Res.getDataList()) {
                if (limit-- <= 0) {
                    break;
                }
                IfaOrderListA002ResponseDtoDomesticMutualFundRegularRecruitment ifaOrderListA002ResponseDtoDomesticMutualFundRegularRecruitment = new IfaOrderListA002ResponseDtoDomesticMutualFundRegularRecruitment();
                BeanUtils.copyProperties(ifaOrderListA002ResponseDtoDomesticMutualFundRegularRecruitment, sql008ResModel);
                // 取引コース
                ifaOrderListA002ResponseDtoDomesticMutualFundRegularRecruitment.setCustomerAttribute(sql008ResModel.getCustomerAttribute());
                // 銘柄コード = ファンドコード（回数） + "." + ファンドコード（号）
                    ifaOrderListA002ResponseDtoDomesticMutualFundRegularRecruitment.setBrandCode(sql008ResModel.getFundCdKaisu() + PUNCTUATION + sql008ResModel.getFundCdGou());
                // ファンド名
                ifaOrderListA002ResponseDtoDomesticMutualFundRegularRecruitment.setFundName(sql008ResModel.getMFName());
                // 取引種別
                ifaOrderListA002ResponseDtoDomesticMutualFundRegularRecruitment.setTradeCd(SAVINGS);
                // 受注日時
                ifaOrderListA002ResponseDtoDomesticMutualFundRegularRecruitment.setOrderDayTimeCalculation(sql008ResModel.getOrderDateTime());
                // 積立コース
                ifaOrderListA002ResponseDtoDomesticMutualFundRegularRecruitment.setAccumulateCourse(getAccumulateCourseValue(sql008ResModel));
                // ボーナス月の設定
                if(StringUtils.equals(CASH_CODE, sql008ResModel.getPaymentMethod())) {
                    String bonusDate = null;
                    bonusDate = getBonusMonth(sql008ResModel.getReserveMmB1(), sql008ResModel.getReserveDdB1(), sql008ResModel.getReserveMmB2(), sql008ResModel.getReserveDdB2());
                    if(StringUtils.isNotEmpty(sql008ResModel.getPaymentBonus()) && StringUtils.isNotEmpty(bonusDate)) {
                        ifaOrderListA002ResponseDtoDomesticMutualFundRegularRecruitment.setBonusMonthSetting(getAmount(sql008ResModel.getPaymentBonus()) + bonusDate);
                    }
                }
                domesticMutualFundRegularRecruitmentList.add(ifaOrderListA002ResponseDtoDomesticMutualFundRegularRecruitment);
            }
            a002Res.setIfaOrderListA002ResponseDtoDomesticMutualFundRegularRecruitmentList(domesticMutualFundRegularRecruitmentList);
        }
        return dtoRes;
    }
    
    
    
    /**
     * アクションID：A005
     * アクション名：CSV出力
     * Dto リクエスト：IfaOrderListA005RequestDto
     * Dto レスポンス：IfaOrderListA005ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaOrderListA005aResponseDto> csvOutputA005(IfaOrderListA005aRequestDto dtoReq, String sessionId)
            throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaOrderListServiceImplL.csvOutputA005");
        }
        if (dtoReq.getEmpCodeAutoDispFlag() == null) {
            dtoReq.setEmpCodeAutoDispFlag("");
        }
        //① A002の①~⑪と同様の処理を行う。
        if (ObjectUtils.isEmpty(dtoReq.getCourse())) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_SELECTED.key, IfaCommonUtil.getMessage(MessageId.ERRORS_SELECTED.key, new String[] {COURSE_ERROR}));
        }

        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT_NEN_GA_PI);
        LocalDate periodYmFrom = LocalDate.parse(dtoReq.getPeriodYmFrom(), format);
        LocalDate periodYmTo = LocalDate.parse(dtoReq.getPeriodYmTo(), format);
        LocalDate periodNow = LocalDate.now();
        dtoReq.setPeriodYmFrom(periodYmFrom.format(DateTimeFormatter.BASIC_ISO_DATE));
        dtoReq.setPeriodYmTo(periodYmTo.format(DateTimeFormatter.BASIC_ISO_DATE));
        
        if (periodYmTo.minusMonths(12).compareTo(periodYmFrom) > 0 || periodNow.minusMonths(12).compareTo(periodYmFrom) > 0
        || periodNow.minusMonths(12).compareTo(periodYmTo) > 0) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_DATERANGE.key, IfaCommonUtil.getMessage(MessageId.ERRORS_DATERANGE.key, new String[] {PERIOD, MONTH_12}));
        }

        if (ObjectUtils.isEmpty(dtoReq.getOrderSelected())) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_DATERANGE.key, IfaCommonUtil.getMessage(MessageId.ERRORS_SELECTED.key, new String[] {ORDER_SELECT}));
        }
        

        String privId = IfaCommonUtil.getUserAccount().getMedUsers().getPrivId();
        List<BrokerCharge> brokerChargeList = null;
        if (!PRIVID_1.equals(privId)) {
            OutputFct030Dto outputFct030Dto = fct030.getData(null);
            brokerChargeList = outputFct030Dto.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key, IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key));
            }
        } else {
            brokerChargeList = new ArrayList<>();
        }

        int sumCount = 0;

        List<String> orderList = Arrays.asList(dtoReq.getOrderSelected().split(","));
        DataList<IfaOrderListSql001ResponseModel> sql001Res = null;
        if (orderList.contains(ORDER_0)) {
            IfaOrderListSql001RequestModel sql001ReqModel = new IfaOrderListSql001RequestModel();
            BeanUtils.copyProperties(sql001ReqModel, dtoReq);
            sql001ReqModel.setPrivId(privId);
            sql001ReqModel.setBrokerChargeList(brokerChargeList);
            sql001Res = dao.selectIfaOrderListSql001(sql001ReqModel);
            if (sql001Res.size() != 0) {
                sumCount += sql001Res.get(0).getTotalCount();
            }
        }

        DataList<IfaOrderListSql002ResponseModel> sql002Res = null;
        if (orderList.contains(ORDER_1)) {
            IfaOrderListSql002RequestModel sql002ReqModel = new IfaOrderListSql002RequestModel();
            BeanUtils.copyProperties(sql002ReqModel, dtoReq);
            sql002ReqModel.setPrivId(privId);
            sql002ReqModel.setBrokerChargeList(brokerChargeList);
            sql002Res = dao.selectIfaOrderListSql002(sql002ReqModel);
            if (sql002Res.size() != 0) {
                sumCount += sql002Res.get(0).getTotalCount();
            }
        }

        DataList<IfaOrderListSql003ResponseModel> sql003Res = null;
        if (orderList.contains(ORDER_2)) {
            IfaOrderListSql003RequestModel sql003ReqModel = new IfaOrderListSql003RequestModel();
            BeanUtils.copyProperties(sql003ReqModel, dtoReq);
            sql003ReqModel.setPrivId(privId);
            sql003ReqModel.setBrokerChargeList(brokerChargeList);
            sql003Res = dao.selectIfaOrderListSql003(sql003ReqModel);
            if (sql003Res.size() != 0) {
                sumCount += sql003Res.get(0).getTotalCount();
            }
        }

        DataList<IfaOrderListSql004ResponseModel> sql004Res = null;
        if (orderList.contains(ORDER_3)) {
            IfaOrderListSql004RequestModel sql004ReqModel = new IfaOrderListSql004RequestModel();
            BeanUtils.copyProperties(sql004ReqModel, dtoReq);
            sql004ReqModel.setPrivId(privId);
            sql004ReqModel.setBrokerChargeList(brokerChargeList);
            sql004Res = dao.selectIfaOrderListSql004(sql004ReqModel);
            if (sql004Res.size() != 0) {
                sumCount += sql004Res.get(0).getTotalCount();
            }
        }

        DataList<IfaOrderListSql005ResponseModel> sql005Res = null;
        if (orderList.contains(ORDER_4)) {
            IfaOrderListSql005RequestModel sql005ReqModel = new IfaOrderListSql005RequestModel();
            BeanUtils.copyProperties(sql005ReqModel, dtoReq);
            sql005ReqModel.setPrivId(privId);
            sql005ReqModel.setBrokerChargeList(brokerChargeList);
            sql005Res = dao.selectIfaOrderListSql005(sql005ReqModel);
            if (sql005Res.size() != 0) {
                sumCount += sql005Res.get(0).getTotalCount();
            }
        }

        DataList<IfaOrderListSql008ResponseModel> sql008Res = null;
        if (orderList.contains(ORDER_5)) {
            IfaOrderListSql008RequestModel sql008ReqModel = new IfaOrderListSql008RequestModel();
            BeanUtils.copyProperties(sql008ReqModel, dtoReq);
            sql008ReqModel.setPrivId(privId);
            sql008ReqModel.setBrokerChargeList(brokerChargeList);
            sql008Res = dao.selectIfaOrderListSql008(sql008ReqModel);
            if (sql008Res.size() != 0) {
                sumCount += sql008Res.get(0).getTotalCount();
            }
        }

        // ② CSVダウンロードMAX件数を取得する。
        InputFct038Dto inputFct038Dto = new InputFct038Dto();
        inputFct038Dto.setScreenId(FCT038_SCREEN_ID);
        inputFct038Dto.setUserAuthority(privId);
        int csvDownloadNum = fct038.getData(inputFct038Dto).getCsvDownloadNum();        
        if (sumCount == 0) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_DATALIST_NOTFOUND.key, IfaCommonUtil.getMessage(MessageId.ERRORS_DATALIST_NOTFOUND.key));
        }
        DataList<IfaOrderListA005aResponseDto> dtoRes = new DataList<IfaOrderListA005aResponseDto>();
        // ③ 取得件数合計チェック
        if (sumCount > csvDownloadNum) {
            dtoRes.setErrorLevel(ErrorLevel.WARNING.getId());
            dtoRes.setMessage(IfaCommonUtil.getMessage(
                    MessageId.WARNINGS_DATALIST_CSV_OVERMAXROWNUM.key,
                    new String[] {String.valueOf(csvDownloadNum), String.valueOf(sumCount), String.valueOf(csvDownloadNum)}
            ));
            dtoRes.setReturnCode(MessageId.WARNINGS_DATALIST_CSV_OVERMAXROWNUM.key);
        }
        
        
        DataList<IfaOrderListA005aResponseDtoOrderList> csvDownList = new DataList<>();
        List<IfaOrderListA005aResponseDtoOrderList> ifaOrderListA005aResponseDtoOrderListList = csvDownList.getDataList();
        // ⑬　上限値
        int limit = csvDownloadNum;
        // ⑭   国内株式リストのデータの場合、以下の処理を行う。
        if (sql001Res != null) {
            Map<Fct020InputDtoBrandCodeAndMarket, String> fct020Cache = new HashMap<>();
            for (IfaOrderListSql001ResponseModel sql001ResModel : sql001Res.getDataList()) {
                if (limit-- <= 0) {
                    break;
                }
                // fct020 cache key を作成
                Fct020InputDtoBrandCodeAndMarket fct020InputDtoBrandCodeAndMarket = new Fct020InputDtoBrandCodeAndMarket();
                fct020InputDtoBrandCodeAndMarket.brandCode = dtoReq.getBrandCode();
                fct020InputDtoBrandCodeAndMarket.market = sql001ResModel.getMarket();
                // fct020 cache　に国内株式リスト.銘柄コードと国内株式リスト.市場の組み合わせがなし
                String currentPrice;
                if (!fct020Cache.containsKey(fct020InputDtoBrandCodeAndMarket)) {
                    InputFct020Dto inputFct020Dto = new InputFct020Dto();
                    inputFct020Dto.setBrandCode(sql001ResModel.getBrandCode());
                    inputFct020Dto.setMarketCode(sql001ResModel.getMarket());
                    // 評価用現在値を取得する
                    OutputFct020Dto outputFct020Dto = fct020.getData(inputFct020Dto);
                    currentPrice = outputFct020Dto.getCurrentValueForEvaluation();
                    // cache に格納
                    fct020Cache.put(fct020InputDtoBrandCodeAndMarket, outputFct020Dto.getCurrentValueForEvaluation());
                } else {
                    // fct020 cache がある
                    currentPrice = fct020Cache.get(fct020InputDtoBrandCodeAndMarket);
                }
                // 国内株式リストのデータに紐づく注文条件を取得する。
                OutputFct037Dto outputFct037Dto = fct037.getData(createInputFct037Dto(sql001ResModel));
                // 国内株式リストに国内株式を格納
                addCsvDownList(ifaOrderListA005aResponseDtoOrderListList, sql001ResModel, currentPrice, outputFct037Dto.getOrderConditions());
            }
        }
        // 国内投資信託リストのデータの場合、以下の処理を行う。
        if (sql002Res != null) {
            for (IfaOrderListSql002ResponseModel sql002ResModel : sql002Res.getDataList()) {
                if (limit-- <= 0) {
                    break;
                }
                String orderDate = sql002ResModel.getOrderDate() + sql002ResModel.getOrderTime();
                String unitAmountCalculation;
                // 口数/金額（算出値）を算出し
                String unit = sql002ResModel.getUnit();
                String amount = sql002ResModel.getAmount();
                if (!ObjectUtils.isEmpty(unit)) {
                    unitAmountCalculation = unit + PRICE_TANI_KOU;
                } else if (!ObjectUtils.isEmpty(amount)) {
                    unitAmountCalculation = amount + PRICE_TANI_EN;
                } else {
                    unitAmountCalculation = NASHI;
                }
                addCsvDownList(ifaOrderListA005aResponseDtoOrderListList, sql002ResModel, orderDate, unitAmountCalculation);
            }
            
        }
        
        // 国内投資信託（定期積立）リストのデータの場合、以下の処理を行う。
        if (sql008Res != null) {
            for (IfaOrderListSql008ResponseModel sql008ResModel : sql008Res.getDataList()) {
                if (limit-- <= 0) {
                    break;
                }
                addCsvDownList(ifaOrderListA005aResponseDtoOrderListList, sql008ResModel);
            }
        }
        
        if (sql003Res != null) {
            for (IfaOrderListSql003ResponseModel sql003ResModel : sql003Res.getDataList()) {
                if (limit-- <= 0) {
                    break;
                }
                addCsvDownList(ifaOrderListA005aResponseDtoOrderListList, sql003ResModel);
            }
            
        }
        // 外国株式（委託注文）リストのデータの場合
        if (sql004Res != null) {
            for (IfaOrderListSql004ResponseModel sql004ResModel : sql004Res.getDataList()) {
                if (limit-- <= 0) {
                    break;
                }
                addCsvDownList(ifaOrderListA005aResponseDtoOrderListList, sql004ResModel, getTradeCdCalculation(sql004ResModel), getConditionDetailsCalculation(sql004ResModel));
            }
        }
        
        if (sql005Res != null) {
            for (IfaOrderListSql005ResponseModel sql005ResModel : sql005Res.getDataList()) {
                if (limit-- <= 0) {
                    break;
                }
                addCsvDownList(ifaOrderListA005aResponseDtoOrderListList, sql005ResModel, getTradeCdCalculation(sql005ResModel), sql005ResModel.getOrderTime());
            }
        }
        
        CsvOutPutUtil csvOutPutUtil = new IfaOrderListCsvOut(complianceService);
        String csvFileName = csvOutPutUtil.doCreateCsvFile(csvDownList, sessionId,
                IfaCommonUtil.getUserAccount().getUserId(), dtoReq.getEmpCodeAutoDispFlag());
        dtoRes.setTitle(csvFileName);
        dtoRes.setTotalSize(sumCount);
        IfaOrderListA005aResponseDto ifaOrderListA005aResponseDto = new IfaOrderListA005aResponseDto();
        ifaOrderListA005aResponseDto.setPattern(dtoReq.getEmpCodeAutoDispFlag());
        dtoRes.getDataList().add(ifaOrderListA005aResponseDto);
        return dtoRes;
    }
    
    /**
     * 国内株式
     *
     * @param csvDownList ダウンロード用リスト
     * @param sql001ResModel sql001レスポンス
     * @throws Exception システムエラー
     */
    private void addCsvDownList(List<IfaOrderListA005aResponseDtoOrderList> csvDownList, IfaOrderListSql001ResponseModel sql001ResModel,
            String currentPrice, String orderCondition) throws Exception {
        IfaOrderListA005aResponseDtoOrderList ifaOrderListA005aResponseDtoOrderList = new IfaOrderListA005aResponseDtoOrderList();
        ifaOrderListA005aResponseDtoOrderList.setButenCode(sql001ResModel.getButenCode());
        ifaOrderListA005aResponseDtoOrderList.setAccountNumber(accountNumberFormat(sql001ResModel.getAccountNumber()));
        // 契約締結前交付書面コード
        ifaOrderListA005aResponseDtoOrderList.setCourse(codeListService.getValue(PRE_CONTRACT_DOC_CODE, sql001ResModel.getCustomerAttribute(), DISPLAY_PATTERN_1));
        ifaOrderListA005aResponseDtoOrderList.setCustomerNameKanji(sql001ResModel.getCustomerNameKanji());
        ifaOrderListA005aResponseDtoOrderList.setCustomerNameKana(sql001ResModel.getCustomerNameKana());
        ifaOrderListA005aResponseDtoOrderList.setOrderNumber(sql001ResModel.getEcOrderNo());
        ifaOrderListA005aResponseDtoOrderList.setBrandCode(sql001ResModel.getBrandCode());
        ifaOrderListA005aResponseDtoOrderList.setBrandName(sql001ResModel.getBrandName());
        // 注文状況（一覧）
        ifaOrderListA005aResponseDtoOrderList.setCorrectCancleKbn(codeListService.getValue(LIST_ORDER_STATUS, sql001ResModel.getOrderStatus(), DISPLAY_PATTERN_1));
        
        // 弁済期限（算出）
        String paymentDeadlineCalculation = paymentLimitUtil.getPaymentLimitOrderList(sql001ResModel.getTradeCd(),
                        sql001ResModel.getOrderStatus(),
                        sql001ResModel.getPaymentDeadline(),
                        sql001ResModel.getDateKbn(),
                        sql001ResModel.getDailyCreditKbn(),
                        sql001ResModel.getPaymentDeadlineDate());

        // 取引種別(取引種別+弁済期限（算出）)
        ifaOrderListA005aResponseDtoOrderList.setTradeCd(codeListService.getValue(DOMESTIC_STOCK_TRADE_CLASS, sql001ResModel.getTradeCd(), DISPLAY_PATTERN_1)
                + paymentDeadlineCalculation);

        // 預り区分（国内）
        ifaOrderListA005aResponseDtoOrderList.setDepositType(codeListService.getValue(DOMESTIC_DEPOSIT_TYPE, sql001ResModel.getDepositType(), DISPLAY_PATTERN_2));
        if (TRADE_TYPE_7.equals(sql001ResModel.getTradeCd()) || TRADE_TYPE_8.equals(sql001ResModel.getTradeCd())) {
            ifaOrderListA005aResponseDtoOrderList.setMarket(NASHI);
            ifaOrderListA005aResponseDtoOrderList.setConditionDetails(NASHI);
            ifaOrderListA005aResponseDtoOrderList.setSecuriytClass(NASHI);
            ifaOrderListA005aResponseDtoOrderList.setPeriod(NASHI);
            ifaOrderListA005aResponseDtoOrderList.setPrice(NASHI);
        } else {
            // 選択市場
            ifaOrderListA005aResponseDtoOrderList.setMarket(codeListService.getValue(SELECT_MARKET, sql001ResModel.getMarket(), DISPLAY_PATTERN_2));
            ifaOrderListA005aResponseDtoOrderList.setConditionDetails(orderCondition);
            // 注文種別（一覧）
            ifaOrderListA005aResponseDtoOrderList.setSecuriytClass(codeListService.getValue(LIST_ORDER_CLASS, sql001ResModel.getOrderClassList(), DISPLAY_PATTERN_1));
            // 手数料区分
            // 外部コード　→　内部コード　変換
            String comIdR = codeListService.getValue(COMM_TYPE, codeListService.convertExtKeyToKey(COMM_TYPE, EC_GW, sql001ResModel.getComIdR()), DISPLAY_PATTERN_1);
            if (comIdR == null) {
                comIdR = "-";
            }
            String depositType = ifaOrderListA005aResponseDtoOrderList.getDepositType();
            if (depositType == null) {
                depositType = "-";
            }
            ifaOrderListA005aResponseDtoOrderList.setDepositType(depositType + "/" + comIdR);
            if (sql001ResModel.getYukoKigen() != null && !StringUtils.equals(sql001ResModel.getYukoKigen().trim(), "")) {
                ifaOrderListA005aResponseDtoOrderList.setPeriod(
                        DateTimeFormatter.ofPattern(DATE8).format(LocalDate.parse(
                                sql001ResModel.getYukoKigen(),
                                DateTimeFormatter.ofPattern(DATE8_WITHOUT_SEPARATOR)
                        ))
                );
            }
            
            // 指成区分
            ifaOrderListA005aResponseDtoOrderList.setPrice(codeListService.getValue(LIMIT_MARKET_TYPE, sql001ResModel.getSasinariKbn(), DISPLAY_PATTERN_1));
        }
        if ((sql001ResModel.getOrderDate() + sql001ResModel.getOrderDayTimeCalculation()) != null) {
            String orderDate = DateTimeFormatter.ofPattern(DATE14).format(
                    LocalDateTime.parse(
                            sql001ResModel.getOrderDate() + sql001ResModel.getOrderDayTimeCalculation(),
                            DateTimeFormatter.ofPattern(DATE14_WITHOUT_SEPARATOR)
                    )
            );
            
            ifaOrderListA005aResponseDtoOrderList.setOrderDate(orderDate);
        }
        
        ifaOrderListA005aResponseDtoOrderList.setQuantity(sql001ResModel.getQuantity());
        if ((!TRADE_TYPE_7.equals(sql001ResModel.getTradeCd()) && !TRADE_TYPE_8.equals(sql001ResModel.getTradeCd()))
                && Arrays.asList(SASINARIKBNLIST).contains(sql001ResModel.getSasinariKbn())) {
            ifaOrderListA005aResponseDtoOrderList.setPrice(ifaOrderListA005aResponseDtoOrderList.getPrice() + " " + sql001ResModel.getPrice());
        }
        ifaOrderListA005aResponseDtoOrderList.setCurrentPrice(currentPrice);
        // 勧誘区分
        ifaOrderListA005aResponseDtoOrderList.setKanyuKbn(codeListService.getValue(INVITATION_TYPE, sql001ResModel.getKanyuKbn(), DISPLAY_PATTERN_1));
        // 受注方法区分
        ifaOrderListA005aResponseDtoOrderList.setOrderMethod(codeListService.getValue(ORDER_METHOD_TYPE, sql001ResModel.getOrderMethod(), DISPLAY_PATTERN_1));
        ifaOrderListA005aResponseDtoOrderList.setOrder(sql001ResModel.getUserName());
        ifaOrderListA005aResponseDtoOrderList.setBrokerCode(sql001ResModel.getBrokerCode());
        ifaOrderListA005aResponseDtoOrderList.setBrokerName(sql001ResModel.getBrokerName());
        ifaOrderListA005aResponseDtoOrderList.setBrokerageBranchCode(sql001ResModel.getBrokerageBranchCode());
        ifaOrderListA005aResponseDtoOrderList.setBrokerBranchName(sql001ResModel.getBrokerBranchName());
        ifaOrderListA005aResponseDtoOrderList.setBrokerChargeCode(sql001ResModel.getBrokerChargeCode());
        ifaOrderListA005aResponseDtoOrderList.setEmployeeName(sql001ResModel.getEmployeeName());
        ifaOrderListA005aResponseDtoOrderList.setViewAblrButenCode(sql001ResModel.getViewAblrButenCode());                           
        csvDownList.add(ifaOrderListA005aResponseDtoOrderList);
    }
    
    /**
     * 国内投資信託
     *
     * @param csvDownList ダウンロード用リスト
     * @param sql002ResModel sql002レスポンス
     * @param orderDate 注文日時
     * @param unitAmountCalculation  口数/金額
     * @throws Exception システムエラー
     */
    private void addCsvDownList(List<IfaOrderListA005aResponseDtoOrderList> csvDownList,
            IfaOrderListSql002ResponseModel sql002ResModel, String orderDate, String unitAmountCalculation) throws Exception {
        IfaOrderListA005aResponseDtoOrderList ifaOrderListA005aResponseDtoOrderList = new IfaOrderListA005aResponseDtoOrderList();
        ifaOrderListA005aResponseDtoOrderList.setButenCode(sql002ResModel.getButenCode());
        ifaOrderListA005aResponseDtoOrderList.setAccountNumber(accountNumberFormat(sql002ResModel.getAccountNumber()));
        // 契約締結前交付書面コード
        ifaOrderListA005aResponseDtoOrderList.setCourse(codeListService.getValue(PRE_CONTRACT_DOC_CODE, sql002ResModel.getCustomerAttribute(), DISPLAY_PATTERN_1));
        ifaOrderListA005aResponseDtoOrderList.setCustomerNameKanji(sql002ResModel.getCustomerNameKanji());
        ifaOrderListA005aResponseDtoOrderList.setCustomerNameKana(sql002ResModel.getCustomerNameKana());
        ifaOrderListA005aResponseDtoOrderList.setOrderNumber(sql002ResModel.getEcOrderNo());
        ifaOrderListA005aResponseDtoOrderList.setBrandCode(sql002ResModel.getFundCodeTimes() + "." + sql002ResModel.getFundCodeIssues());
        ifaOrderListA005aResponseDtoOrderList.setBrandName(sql002ResModel.getFundName());
        // 注文状況（一覧）
        ifaOrderListA005aResponseDtoOrderList.setCorrectCancleKbn(codeListService.getValue(LIST_ORDER_STATUS, sql002ResModel.getOrderStatus(), DISPLAY_PATTERN_1));
        // 取引種別（国内投信）
        ifaOrderListA005aResponseDtoOrderList.setTradeCd(codeListService.getValue(DOMESTIC_MUTUAL_FUND_TRADE_CLASS, StringUtils.trim(sql002ResModel.getTradeCd()), DISPLAY_PATTERN_1));
        // 預り区分（投信）
        if (TRADEKBN_K.equals(sql002ResModel.getTradeKbn())) {
            ifaOrderListA005aResponseDtoOrderList.setRedemptionIncentives(codeListService.getValue(TRANSFERS_PREFERENTIAL_QUOTA_APPLICATION, sql002ResModel.getNorikaeYuguKbn(), DISPLAY_PATTERN_1));
            ifaOrderListA005aResponseDtoOrderList.setWarningApplyTrade(codeListService.getValue(WARNING_APPLICATION_TRAD, sql002ResModel.getCheckCompWrnAlert(), DISPLAY_PATTERN_1));
            ifaOrderListA005aResponseDtoOrderList.setProspectus(codeListService.getValue(PROSPECTUS_ISSUE_METHOD, sql002ResModel.getMokuromiKoufuKbn(), DISPLAY_PATTERN_1));
            ifaOrderListA005aResponseDtoOrderList.setSwitchingSolicitation(codeListService.getValue(SOLICITING_TRANSFERS, sql002ResModel.getNorikaeKanyuKbn(), DISPLAY_PATTERN_1));
            ifaOrderListA005aResponseDtoOrderList.setConflictOfInterestExplain(codeListService.getValue(CONFLICT_OF_INTEREST_EXPLAIN, sql002ResModel.getConflictOfInterestExplain(), DISPLAY_PATTERN_3));
            ifaOrderListA005aResponseDtoOrderList.setSalesCommRate(codeListService.getValue(COST_EXPLAINED, sql002ResModel.getConfirmItemHiyou(), DISPLAY_PATTERN_3));
            ifaOrderListA005aResponseDtoOrderList.setCheckFukusu(codeListService.getValue(MULTIPLE_TRADE_CLEARLY_COMM_STATED, sql002ResModel.getConfirmItemFukusu(), DISPLAY_PATTERN_3));
            // 預り区分=(5:買付時：Jr特定/Jr一般　または △:特定/一般) であり、かつ (特定口座区分=(1:特定口座(代行納付) または 2:特定口座(確定申告)) でない)場合
            if ((sql002ResModel.getDepositType().equals("5") || sql002ResModel.getDepositType().equals(" ")) && !(sql002ResModel.getSpecificAccountType().equals("1") || sql002ResModel.getSpecificAccountType().equals("2"))) {
                ifaOrderListA005aResponseDtoOrderList.setDepositType(codeListService.getValue(MUTUAL_FUND_DEPOSIT_TYPE, sql002ResModel.getDepositType(), DISPLAY_PATTERN_7));
            } else {
                ifaOrderListA005aResponseDtoOrderList.setDepositType(codeListService.getValue(MUTUAL_FUND_DEPOSIT_TYPE, sql002ResModel.getDepositType(), DISPLAY_PATTERN_2));
            }
        } else {
            ifaOrderListA005aResponseDtoOrderList.setDepositType(codeListService.getValue(MUTUAL_FUND_DEPOSIT_TYPE, sql002ResModel.getDepositType(), DISPLAY_PATTERN_1));
        }
        if (orderDate != null) {
            ifaOrderListA005aResponseDtoOrderList.setOrderDate(DateTimeFormatter.ofPattern(DATE14).format(LocalDateTime.parse(orderDate, DateTimeFormatter.ofPattern(DATE14_WITHOUT_SEPARATOR))));
        }
        
        ifaOrderListA005aResponseDtoOrderList.setPrice(unitAmountCalculation);
        // ポイント種別
        // 外部コード　→　内部コード　変換
        String pointType = codeListService.convertExtKeyToKey(POINT_TYPE, EC_GW, sql002ResModel.getPointType());
        ifaOrderListA005aResponseDtoOrderList.setPointType(codeListService.getValue(POINT_TYPE, pointType, DISPLAY_PATTERN_1));
        // 利用ポイント編集
        ifaOrderListA005aResponseDtoOrderList.setOrderPoint(getOrderPoint(sql002ResModel));
        
        // 分配金受取方法指定
        ifaOrderListA005aResponseDtoOrderList
        .setDistributionReceiveMethodDesignation(codeListService.getValue(DISTRIBUTION_RECEIVE_METHOD, sql002ResModel.getDistributionReceiveMethodDesignation(), DISPLAY_PATTERN_4));
        ifaOrderListA005aResponseDtoOrderList.setKanyuKbn(codeListService.getValue(INVITATION_TYPE, sql002ResModel.getKanyuKbn(), DISPLAY_PATTERN_1));
        ifaOrderListA005aResponseDtoOrderList.setOrderMethod(codeListService.getValue(ORDER_METHOD_TYPE, sql002ResModel.getOrderMethod(), DISPLAY_PATTERN_1));

        ifaOrderListA005aResponseDtoOrderList.setDouitsuTukaKuniKbn(codeListService.getValue(SAME_CURRENCY_SAME_COUNTRY_TRANSFERS, sql002ResModel.getDouitsuTukaKuniKbn(), DISPLAY_PATTERN_1));
        ifaOrderListA005aResponseDtoOrderList.setTashaNorikaeKbn(codeListService.getValue(INTERCOMPANY_MUTUAL_FUND_TRANSFER_SOLICITATION, sql002ResModel.getTashaNorikaeKbn(), DISPLAY_PATTERN_1));
        if (TRADEKBN_U.equals(sql002ResModel.getTradeKbn())
                || TRADEKBN_V.equals(sql002ResModel.getTradeKbn())
                || TRADEKBN_A.equals(sql002ResModel.getTradeKbn())
                || TRADEKBN_B.equals(sql002ResModel.getTradeKbn())) {
            ifaOrderListA005aResponseDtoOrderList.setTankiSellKbn(codeListService.getValue(SHORT_TERM_SALE_CONFIRM, sql002ResModel.getTankiSellKbn(), DISPLAY_PATTERN_1));
            ifaOrderListA005aResponseDtoOrderList.setShokanMaeKbn(codeListService.getValue(PRE_REDEMPTION_SELL_CONFIRM, sql002ResModel.getShokanMaeKbn(), DISPLAY_PATTERN_1));
        }
        ifaOrderListA005aResponseDtoOrderList.setOrder(sql002ResModel.getUserName());
        ifaOrderListA005aResponseDtoOrderList.setBrokerCode(sql002ResModel.getBrokerCode());
        ifaOrderListA005aResponseDtoOrderList.setBrokerName(sql002ResModel.getBrokerName());
        ifaOrderListA005aResponseDtoOrderList.setBrokerageBranchCode(sql002ResModel.getBrokerageBranchCode());
        ifaOrderListA005aResponseDtoOrderList.setBrokerBranchName(sql002ResModel.getBrokerBranchName());
        ifaOrderListA005aResponseDtoOrderList.setBrokerChargeCode(sql002ResModel.getBrokerChargeCode());
        ifaOrderListA005aResponseDtoOrderList.setEmployeeName(sql002ResModel.getEmployeeName());
        ifaOrderListA005aResponseDtoOrderList.setViewAblrButenCode(sql002ResModel.getViewAblrButenCode());
        csvDownList.add(ifaOrderListA005aResponseDtoOrderList);
    }
    
    /**
     * 募集注文
     *
     * @param csvDownList ダウンロード用リスト
     * @param sql001ResModel sql003レスポンス
     * @throws Exception システムエラー
     */
    private void addCsvDownList(List<IfaOrderListA005aResponseDtoOrderList> csvDownList, IfaOrderListSql003ResponseModel sql003ResModel) throws Exception {
        IfaOrderListA005aResponseDtoOrderList ifaOrderListA005aResponseDtoOrderList = new IfaOrderListA005aResponseDtoOrderList();
        ifaOrderListA005aResponseDtoOrderList.setButenCode(sql003ResModel.getButen());
        ifaOrderListA005aResponseDtoOrderList.setAccountNumber(accountNumberFormat(sql003ResModel.getAccountNumber()));
        ifaOrderListA005aResponseDtoOrderList.setCourse(codeListService.getValue(PRE_CONTRACT_DOC_CODE, sql003ResModel.getCustomerAttribute(), DISPLAY_PATTERN_1));
        ifaOrderListA005aResponseDtoOrderList.setCustomerNameKanji(sql003ResModel.getCustomerNameKanji());
        ifaOrderListA005aResponseDtoOrderList.setCustomerNameKana(sql003ResModel.getCustomerNameKana());
        ifaOrderListA005aResponseDtoOrderList.setBrandCode(sql003ResModel.getBrandCode());
        ifaOrderListA005aResponseDtoOrderList.setBrandName(sql003ResModel.getBrandName());
        // 注文状況（募集）
        ifaOrderListA005aResponseDtoOrderList.setOrderStatus(codeListService.getValue(SUBSCRIPT_ORDER_STATUS, sql003ResModel.getOrderStatus(), DISPLAY_PATTERN_1));
        // 対面募集注文預り区分
        ifaOrderListA005aResponseDtoOrderList.setDepositType(codeListService.getValue(FACE_TO_FACE_SUBSCRIPT_ORDER_DEPOSIT_TYPE, sql003ResModel.getDepositType(), DISPLAY_PATTERN_1));
        ifaOrderListA005aResponseDtoOrderList.setOrderDate(sql003ResModel.getRecruitmentOrderDate());
        ifaOrderListA005aResponseDtoOrderList.setQuantity(sql003ResModel.getQuantity());
        ifaOrderListA005aResponseDtoOrderList.setPrice(sql003ResModel.getIssueBbPrice());
        ifaOrderListA005aResponseDtoOrderList.setKanyuKbn(codeListService.getValue(INVITATION_TYPE, sql003ResModel.getKanyuKbn(), DISPLAY_PATTERN_1));
        ifaOrderListA005aResponseDtoOrderList.setOrderMethod(codeListService.getValue(ORDER_METHOD_TYPE, sql003ResModel.getOrderMethod(), DISPLAY_PATTERN_1));
        // 重要事項の説明
        ifaOrderListA005aResponseDtoOrderList.setImportantMatter(codeListService.getValue(IMPORTANT_MATTERS_EXPLAIN, sql003ResModel.getMostImportantMatter(), DISPLAY_PATTERN_1));
        // ワーニング申請取引
        ifaOrderListA005aResponseDtoOrderList.setWarningApplyTrade(codeListService.getValue(WARNING_APPLICATION_TRAD, sql003ResModel.getWarningApplyArranged(), DISPLAY_PATTERN_1));
        // 目論見書の交付方法
        ifaOrderListA005aResponseDtoOrderList.setProspectus(codeListService.getValue(PROSPECTUS_ISSUE_METHOD, sql003ResModel.getProspectus(), DISPLAY_PATTERN_2));
        
        ifaOrderListA005aResponseDtoOrderList.setOrder(sql003ResModel.getUserName());
        ifaOrderListA005aResponseDtoOrderList.setBrokerCode(sql003ResModel.getBrokerCode());
        ifaOrderListA005aResponseDtoOrderList.setBrokerName(sql003ResModel.getBrokerName());
        ifaOrderListA005aResponseDtoOrderList.setBrokerageBranchCode(sql003ResModel.getBrokerageBranchCode());
        ifaOrderListA005aResponseDtoOrderList.setBrokerBranchName(sql003ResModel.getBrokerBranchName());
        ifaOrderListA005aResponseDtoOrderList.setBrokerChargeCode(sql003ResModel.getBrokerChargeCode());
        ifaOrderListA005aResponseDtoOrderList.setEmployeeName(sql003ResModel.getEmployeeName());
        ifaOrderListA005aResponseDtoOrderList.setViewAblrButenCode(sql003ResModel.getViewAblrButenCode());
        csvDownList.add(ifaOrderListA005aResponseDtoOrderList);
    }
    
    /**
     * 外国株式（委託注文）
     *
     * @param csvDownList ダウンロード用リスト
     * @param sql001ResModel sql004レスポンス
     * @throws Exception システムエラー
     */
    private void addCsvDownList(List<IfaOrderListA005aResponseDtoOrderList> csvDownList,
            IfaOrderListSql004ResponseModel sql004ResModel, String tradeCd, String conditionDetails) throws Exception {
        IfaOrderListA005aResponseDtoOrderList ifaOrderListA005aResponseDtoOrderList = new IfaOrderListA005aResponseDtoOrderList();
        ifaOrderListA005aResponseDtoOrderList.setButenCode(sql004ResModel.getButenCode());
        ifaOrderListA005aResponseDtoOrderList.setAccountNumber(accountNumberFormat(sql004ResModel.getAccountNumber()));
        ifaOrderListA005aResponseDtoOrderList.setCourse(codeListService.getValue(PRE_CONTRACT_DOC_CODE, sql004ResModel.getCustomerAttribute(), DISPLAY_PATTERN_1));
        ifaOrderListA005aResponseDtoOrderList.setCustomerNameKanji(sql004ResModel.getCustomerNameKanji());
        ifaOrderListA005aResponseDtoOrderList.setCustomerNameKana(sql004ResModel.getCustomerNameKana());
        ifaOrderListA005aResponseDtoOrderList.setOrderNumber(sql004ResModel.getAcceptOrderNumber());
        ifaOrderListA005aResponseDtoOrderList.setBrandCode(sql004ResModel.getTickerCode());
        ifaOrderListA005aResponseDtoOrderList.setBrandName(sql004ResModel.getBrandName());
        // 外国市場区分
        ifaOrderListA005aResponseDtoOrderList.setMarket(codeListService.getValue(FOREIGN_MARKET_TYPE, sql004ResModel.getMarketCode(), DISPLAY_PATTERN_1));
        // 注文状況（一覧）
        ifaOrderListA005aResponseDtoOrderList.setCorrectCancleKbn(codeListService.getValue(LIST_ORDER_STATUS, sql004ResModel.getSecuriytClassType(), DISPLAY_PATTERN_1));
        // 取引種別（外国株式）
        ifaOrderListA005aResponseDtoOrderList.setTradeCd(codeListService.getValue(FOREIGN_STOCK_TRADE_CLASS, tradeCd, DISPLAY_PATTERN_1));
        // 選択可能価格条件
        ifaOrderListA005aResponseDtoOrderList.setSecuriytClass(codeListService.getValue(SELECT_ABLE_PRICE_CONDITIONS, sql004ResModel.getPriceConditionsType(), DISPLAY_PATTERN_5));
        ifaOrderListA005aResponseDtoOrderList.setConditionDetails(conditionDetails);
        // 決済区分
        ifaOrderListA005aResponseDtoOrderList.setSettlementMethod(codeListService.getValue(SETTLEMENT_TYPE, sql004ResModel.getSettlementType(), DISPLAY_PATTERN_1));
        // 預り区分（外国）
        ifaOrderListA005aResponseDtoOrderList.setDepositType(codeListService.getValue(FOREIGN_DEPOSIT_TYPE, sql004ResModel.getDepositType(), DISPLAY_PATTERN_1));
        ifaOrderListA005aResponseDtoOrderList.setOrderDate(sql004ResModel.getOrderTime());
        if (sql004ResModel.getOrderDeadlineDate() != null) {
            ifaOrderListA005aResponseDtoOrderList.setPeriod(
                    DateTimeFormatter.ofPattern(DATE8).format(LocalDate.parse(
                            sql004ResModel.getOrderDeadlineDate(),
                            DateTimeFormatter.ofPattern(DATE8_WITHOUT_SEPARATOR)
                    ))
            );
        }
        ifaOrderListA005aResponseDtoOrderList.setQuantity(sql004ResModel.getOrderQuantity());
        String priceType = sql004ResModel.getPriceConditionsType();
        if (PRICECONDITIONSTYPE_1.equals(priceType) || PRICECONDITIONSTYPE_3.equals(priceType)) {
            ifaOrderListA005aResponseDtoOrderList.setPrice(sql004ResModel.getSashine() + sql004ResModel.getCurrencyCode());
        } else if (PRICECONDITIONSTYPE_2.equals(priceType) || PRICECONDITIONSTYPE_4.equals(priceType)) {
            ifaOrderListA005aResponseDtoOrderList.setPrice(MARKET_ORDER);
        }
        // 勧誘区分（外株）
        ifaOrderListA005aResponseDtoOrderList.setKanyuKbn(codeListService.getValue(FOREIGN_STOCK_INVITATION_TYPE, sql004ResModel.getKanyuKbn(), DISPLAY_PATTERN_1));
        // 受注方法区分（外株）
        ifaOrderListA005aResponseDtoOrderList.setOrderMethod(codeListService.getValue(FOREIGN_STOCK_ORDER_METHOD_TYPE, sql004ResModel.getJutyuKbn(), DISPLAY_PATTERN_1));
        // 英文開示銘柄
        ifaOrderListA005aResponseDtoOrderList.setEngPubType(codeListService.getValue(ISSUES_DISCLOSED_IN_ENGLISH_BRAND, sql004ResModel.getEngPubType(), DISPLAY_PATTERN_3));
        // 重要事項の説明
        ifaOrderListA005aResponseDtoOrderList.setImportantMatter(codeListService.getValue(IMPORTANT_MATTERS_EXPLAIN, sql004ResModel.getImportantMatterTypeClass(), DISPLAY_PATTERN_1));
        // ワーニング申請取引
        ifaOrderListA005aResponseDtoOrderList.setWarningApplyTrade(codeListService.getValue(WARNING_APPLICATION_TRAD, sql004ResModel.getWarningApplyTrade(), DISPLAY_PATTERN_1));
        // 乗換え勧誘(ETF)
        ifaOrderListA005aResponseDtoOrderList.setSwitchingSolicitation(codeListService.getValue(ETF_SOLICITING_TRANSFERS, sql004ResModel.getSolicitationEtfType(), DISPLAY_PATTERN_1));
        ifaOrderListA005aResponseDtoOrderList.setOrder(sql004ResModel.getUserName());
        ifaOrderListA005aResponseDtoOrderList.setBrokerCode(sql004ResModel.getBrokerCode());
        ifaOrderListA005aResponseDtoOrderList.setBrokerName(sql004ResModel.getBrokerName());
        ifaOrderListA005aResponseDtoOrderList.setBrokerageBranchCode(sql004ResModel.getBrokerageBranchCode());
        ifaOrderListA005aResponseDtoOrderList.setBrokerBranchName(sql004ResModel.getBrokerBranchName());
        ifaOrderListA005aResponseDtoOrderList.setBrokerChargeCode(sql004ResModel.getBrokerChargeCode());
        ifaOrderListA005aResponseDtoOrderList.setEmployeeName(sql004ResModel.getEmployeeName());
        ifaOrderListA005aResponseDtoOrderList.setViewAblrButenCode(sql004ResModel.getViewAblrButenCode());
        csvDownList.add(ifaOrderListA005aResponseDtoOrderList);
    }
    
    /**
     * 外国株式（店頭注文）
     *
     * @param csvDownList ダウンロード用リスト
     * @param sql001ResModel sql005レスポンス
     * @param orderStatus 取引種別
     * @param orderDate 受付日時
     * @throws Exception システムエラー
     */
    private void addCsvDownList(List<IfaOrderListA005aResponseDtoOrderList> csvDownList,
             IfaOrderListSql005ResponseModel sql005ResModel, String orderStatus, String orderDate) throws Exception {
        IfaOrderListA005aResponseDtoOrderList ifaOrderListA005aResponseDtoOrderList = new IfaOrderListA005aResponseDtoOrderList();
        ifaOrderListA005aResponseDtoOrderList.setButenCode(sql005ResModel.getButen());
        ifaOrderListA005aResponseDtoOrderList.setAccountNumber(accountNumberFormat(sql005ResModel.getAccountNumber()));
        ifaOrderListA005aResponseDtoOrderList.setCourse(codeListService.getValue(PRE_CONTRACT_DOC_CODE, sql005ResModel.getCustomerAttribute(), DISPLAY_PATTERN_1));
        ifaOrderListA005aResponseDtoOrderList.setCustomerNameKanji(sql005ResModel.getCustomerNameKanji());
        ifaOrderListA005aResponseDtoOrderList.setCustomerNameKana(sql005ResModel.getCustomerNameKana());
        ifaOrderListA005aResponseDtoOrderList.setOrderNumber(sql005ResModel.getManageNumber());
        ifaOrderListA005aResponseDtoOrderList.setBrandCode(sql005ResModel.getBrandCode());
        ifaOrderListA005aResponseDtoOrderList.setBrandName(sql005ResModel.getBrandName());
        // 注文ステータス（外株店頭）
        ifaOrderListA005aResponseDtoOrderList.setOrderStatus(codeListService.getValue(FOREIGN_STOCK_COUNTER_ORDER_STATUS, sql005ResModel.getStatus(), DISPLAY_PATTERN_1));
        ifaOrderListA005aResponseDtoOrderList.setCancelReason(sql005ResModel.getCancelReason());
        // 取引種別（外国株式）
        ifaOrderListA005aResponseDtoOrderList.setTradeCd(codeListService.getValue(FOREIGN_STOCK_TRADE_CLASS, orderStatus, DISPLAY_PATTERN_1));
        ifaOrderListA005aResponseDtoOrderList.setDepositType(codeListService.getValue(FOREIGN_DEPOSIT_TYPE, sql005ResModel.getDepositType(), DISPLAY_PATTERN_1));
        if (orderDate != null) {
            ifaOrderListA005aResponseDtoOrderList.setOrderDate(orderDate.replaceAll("-", "/"));
        }
        
        ifaOrderListA005aResponseDtoOrderList.setTradeTime(sql005ResModel.getTradeTime());
        ifaOrderListA005aResponseDtoOrderList.setQuantity(sql005ResModel.getOrderQuantity());
        ifaOrderListA005aResponseDtoOrderList.setPrice(sql005ResModel.getTradePrice());
        // 勧誘区分
        ifaOrderListA005aResponseDtoOrderList.setKanyuKbn(codeListService.getValue(FOREIGN_STOCK_INVITATION_TYPE, sql005ResModel.getKanyuKbn(), DISPLAY_PATTERN_1));
        // 受注方法区分（外株）
        ifaOrderListA005aResponseDtoOrderList.setOrderMethod(codeListService.getValue(FOREIGN_STOCK_ORDER_METHOD_TYPE, sql005ResModel.getOrderMethod(), DISPLAY_PATTERN_1));
        
        ifaOrderListA005aResponseDtoOrderList.setEngPubType(codeListService.getValue(ISSUES_DISCLOSED_IN_ENGLISH_BRAND, sql005ResModel.getEngPubBrand(), DISPLAY_PATTERN_3));
        ifaOrderListA005aResponseDtoOrderList.setImportantMatter(codeListService.getValue(IMPORTANT_MATTERS_EXPLAIN, sql005ResModel.getImportantMatter(), DISPLAY_PATTERN_1));
        ifaOrderListA005aResponseDtoOrderList.setWarningApplyTrade(codeListService.getValue(WARNING_APPLICATION_TRAD, sql005ResModel.getWarningTrade(), DISPLAY_PATTERN_1));
        // 外国証券情報の交付方法
        ifaOrderListA005aResponseDtoOrderList.setProspectus(codeListService.getValue(FOREIGN_SECURITY_INFO_ISSUE_METHOD, sql005ResModel.getForeignSecurityInfoIssueMethod(), DISPLAY_PATTERN_1));
        ifaOrderListA005aResponseDtoOrderList.setSwitchingSolicitation(codeListService.getValue(ETF_SOLICITING_TRANSFERS, sql005ResModel.getSwitchingSolicitationEtf(), DISPLAY_PATTERN_1));

        ifaOrderListA005aResponseDtoOrderList.setOrder(sql005ResModel.getUserName());
        ifaOrderListA005aResponseDtoOrderList.setBrokerCode(sql005ResModel.getBrokerCode());
        ifaOrderListA005aResponseDtoOrderList.setBrokerName(sql005ResModel.getBrokerName());
        ifaOrderListA005aResponseDtoOrderList.setBrokerageBranchCode(sql005ResModel.getBrokerageBranchCode());
        ifaOrderListA005aResponseDtoOrderList.setBrokerBranchName(sql005ResModel.getBrokerBranchName());
        ifaOrderListA005aResponseDtoOrderList.setBrokerChargeCode(sql005ResModel.getBrokerChargeCode());
        ifaOrderListA005aResponseDtoOrderList.setEmployeeName(sql005ResModel.getEmployeeName());
        ifaOrderListA005aResponseDtoOrderList.setViewAblrButenCode(sql005ResModel.getViewAblrButenCode());
        csvDownList.add(ifaOrderListA005aResponseDtoOrderList);
    }
    
    /**
     * 国内投資信託（定期積立）
     *
     * @param csvDownList ダウンロード用リスト
     * @param sql008ResModel sql008レスポンス
     * @throws Exception システムエラー
     */
    private void addCsvDownList(List<IfaOrderListA005aResponseDtoOrderList> csvDownList,
            IfaOrderListSql008ResponseModel sql008ResModel) throws Exception {
        IfaOrderListA005aResponseDtoOrderList ifaOrderListA005aResponseDtoOrderList = new IfaOrderListA005aResponseDtoOrderList();
        ifaOrderListA005aResponseDtoOrderList.setButenCode(sql008ResModel.getButen());
        ifaOrderListA005aResponseDtoOrderList.setAccountNumber(accountNumberFormat(sql008ResModel.getAccountNumber()));
        ifaOrderListA005aResponseDtoOrderList.setCourse(codeListService.getValue(PRE_CONTRACT_DOC_CODE, sql008ResModel.getCustomerAttribute(), DISPLAY_PATTERN_1));
        ifaOrderListA005aResponseDtoOrderList.setCustomerNameKanji(sql008ResModel.getCustomerNameKanji());
        ifaOrderListA005aResponseDtoOrderList.setCustomerNameKana(sql008ResModel.getCustomerNameKana());
        ifaOrderListA005aResponseDtoOrderList.setBrandCode(sql008ResModel.getFundCdKaisu() + PUNCTUATION + sql008ResModel.getFundCdGou());
        ifaOrderListA005aResponseDtoOrderList.setBrandName(sql008ResModel.getMFName());
        // 変更/解除区分
        ifaOrderListA005aResponseDtoOrderList.setCorrectCancleKbn(codeListService.getValue(FUND_RESERVE_MODIFY_KBN, sql008ResModel.getModifyCancelClassification(), DISPLAY_PATTERN_1));
        // 取引種別（国内投信）
        ifaOrderListA005aResponseDtoOrderList.setTradeCd(SAVINGS);
        // 預り区分
        ifaOrderListA005aResponseDtoOrderList.setDepositType(codeListService.getValue(RESERVE_TRADE_DEPOSIT_TYPE, sql008ResModel.getDepositType(), DISPLAY_PATTERN_3));
        // 決済方法
        ifaOrderListA005aResponseDtoOrderList.setSettlementMethod(codeListService.getValue(FUND_RESERVE_PAYMENT_METHOD, sql008ResModel.getPaymentMethod(), DISPLAY_PATTERN_1));
        // 受注日時
        if (StringUtils.isNotEmpty(sql008ResModel.getOrderDateTime())) {
            ifaOrderListA005aResponseDtoOrderList.setOrderDate(
                DateTimeFormatter.ofPattern(DATE14).format(LocalDateTime.parse(
                    sql008ResModel.getOrderDateTime(),
                        DateTimeFormatter.ofPattern(DATE14_WITH_SEPARATOR)
                ))
        );
        }
        // 積立コース
        ifaOrderListA005aResponseDtoOrderList.setAccumulateCourse(getAccumulateCourseValue(sql008ResModel));
        // 設定金額
        ifaOrderListA005aResponseDtoOrderList.setSettingAmount(getAmount(sql008ResModel.getSettingAmount()));
        // ボーナス月の設定
        if(StringUtils.equals(CASH_CODE, sql008ResModel.getPaymentMethod())) {
            String bonusDate = null;
            bonusDate = getBonusMonth(sql008ResModel.getReserveMmB1(), sql008ResModel.getReserveDdB1(), sql008ResModel.getReserveMmB2(), sql008ResModel.getReserveDdB2());
            if(StringUtils.isNoneEmpty(sql008ResModel.getPaymentBonus()) || StringUtils.isNoneEmpty(bonusDate)) {
                ifaOrderListA005aResponseDtoOrderList.setBonusMonthSetting(
                    Stream.of(getAmount(sql008ResModel.getPaymentBonus()), bonusDate)
                    .filter(s -> s != null)
                    .collect(Collectors.joining()));
            }
        }
        // NISA枠ぎりぎり注文
        ifaOrderListA005aResponseDtoOrderList.setNisaBarelyBuyingKbn(codeListService.getValue(FUND_RESERVE_NISA_BARELY_BUYING_KBN, sql008ResModel.getNisaBarelyBuyingKbn(), DISPLAY_PATTERN_1));
        // 課税シフト注文
        ifaOrderListA005aResponseDtoOrderList.setNisaExcessBuyingKbn(codeListService.getValue(FUND_RESERVE_TAX_SHIFT_KBN, sql008ResModel.getNisaExcessBuyingKbn(), DISPLAY_PATTERN_1));
        // 1カ月あたりの積立金額
        ifaOrderListA005aResponseDtoOrderList.setOneMonthSumAmount(getAmount(sql008ResModel.getOneMonthSumAmount()));
        // 次回発注予定日
        if (sql008ResModel.getNextReserveDate() != null && !StringUtils.equals(sql008ResModel.getNextReserveDate().trim(), "")) {
            ifaOrderListA005aResponseDtoOrderList.setNextReserveDate(
                    DateTimeFormatter.ofPattern(DATE8).format(LocalDate.parse(
                        sql008ResModel.getNextReserveDate(),
                            DateTimeFormatter.ofPattern(DATE8_WITHOUT_SEPARATOR)
                    ))
            );
        }
        ifaOrderListA005aResponseDtoOrderList.setOrder(sql008ResModel.getClient());
        ifaOrderListA005aResponseDtoOrderList.setBrokerCode(sql008ResModel.getBrokerCode());
        ifaOrderListA005aResponseDtoOrderList.setBrokerName(sql008ResModel.getBrokerName());
        ifaOrderListA005aResponseDtoOrderList.setBrokerageBranchCode(sql008ResModel.getBrokerBranchCode());
        ifaOrderListA005aResponseDtoOrderList.setBrokerBranchName(sql008ResModel.getBrokerBranchName());
        ifaOrderListA005aResponseDtoOrderList.setBrokerChargeCode(sql008ResModel.getBrokerChargeCode());
        ifaOrderListA005aResponseDtoOrderList.setEmployeeName(sql008ResModel.getEmployeeName());
        ifaOrderListA005aResponseDtoOrderList.setViewAblrButenCode(sql008ResModel.getVisibleButenCode());
        csvDownList.add(ifaOrderListA005aResponseDtoOrderList);
    }
    
    private InputFct037Dto createInputFct037Dto(IfaOrderListSql001ResponseModel sql001ResModel) {
        InputFct037Dto inputFct037Dto = new InputFct037Dto();
        inputFct037Dto.setOrderCorrectStatus(ZERO);
        inputFct037Dto.setAutoOrderClass(sql001ResModel.getAutoOrderKind());
        inputFct037Dto.setRbeChumonShubetsu(sql001ResModel.getRbeOrderKind());
        inputFct037Dto.setRbeOrderStatus(sql001ResModel.getRbeOrderStatus());
        inputFct037Dto.setSashinariKbn(sql001ResModel.getSasinariKbn());
        inputFct037Dto.setSashine(sql001ResModel.getPrice());
        inputFct037Dto.setLatestTriggerZone(sql001ResModel.getTriggerZone());
        inputFct037Dto.setLatestTriggerNedan(sql001ResModel.getTriggerPrice());
        inputFct037Dto.setLatestOcoLimitMarketType(sql001ResModel.getOcoSasinariKbn());
        
        inputFct037Dto.setLatestOcoLimitlimitPrice(sql001ResModel.getOcoSashine());
        inputFct037Dto.setDoneRbeOrderClass(sql001ResModel.getDoneRbeOrderKind());
        inputFct037Dto.setDoneLimitMarketType(sql001ResModel.getDoneSasinariKbn());
        inputFct037Dto.setDoneLimitPrice(sql001ResModel.getDoneSashine());
        inputFct037Dto.setDoneTriggerZone(sql001ResModel.getDoneTriggerZone());
        inputFct037Dto.setDoneTriggerNedan(sql001ResModel.getDoneTriggerPrice());
        inputFct037Dto.setDoneOcoLimitMarketType(sql001ResModel.getDoneOcoSasinariKbn());
        inputFct037Dto.setDoneOcoLimitPrice(sql001ResModel.getDoneOcoSashine());
        inputFct037Dto.setDoneExpirationDate(sql001ResModel.getDoneLimit());
        return inputFct037Dto;
    }
    
    private String accountNumberFormat(String accountNumber) {
        return StringUtils.leftPad(accountNumber, 7, '0');
    }

    /**
    * 注文時ポイントを算出
    *
    * @param sql002ResModel SQL002レスポンス
    * @return 注文時ポイント
    */
    public String getOrderPoint(IfaOrderListSql002ResponseModel sql002ResModel) {

        // 取引種別
        String tradeCd = StringUtils.trim(sql002ResModel.getTradeCd());
        // 注文時ポイント
        String orderPoint = sql002ResModel.getOrderPoint();

        if (JTO_TRADE_TYPE_2.equals(tradeCd)) {
            // 取引種別 = "2"：購入（積立）の場合、"未確定"を表示する。
            return ORDER_POINT_UNCERTAIN;
        } else {
            // 上記以外、注文時ポイントを表示する。(注文時ポイントに値がない場合は0を表示する)
            return StringUtil.isNullOrEmpty(orderPoint) ? ZERO : orderPoint;
        }
    }

    
    /**
    * 取引種別（算出）を算出
    *
    * @param sql004ResponseModel SQL004レスポンス
    * @return 取引種別
    */
    public String getTradeCdCalculation(IfaOrderListSql004ResponseModel sql004ResponseModel) {
        String methodType = sql004ResponseModel.getMethodType();
        String tradeKbn = sql004ResponseModel.getTradeKbn();
        // 仕法区分＝'0':現物の場合
        if (METHOD_TYPE_0.equals(methodType)) {
            // 売買区分＝'3':買付の場合
            if (TRADEKBN_3.equals(tradeKbn)) {
                return TRADE_TYPE_0;
            } else if (TRADEKBN_1.equals(tradeKbn)) {
                return TRADE_TYPE_1;
            }
        } else if (METHOD_TYPE_10.equals(methodType)) {
            // 仕法区分＝'10':信用新規の場合
            // 売買区分＝'3':買付の場合
            if (TRADEKBN_3.equals(tradeKbn)) {
                return TRADE_TYPE_2;
            } else if (TRADEKBN_1.equals(tradeKbn)) {
                return TRADE_TYPE_3;
            }
        } else if (METHOD_TYPE_11.equals(methodType)) {
            // 仕法区分＝'11':信用返済の場合
            // 売買区分＝'3':買付の場合
            if (TRADEKBN_3.equals(tradeKbn)) {
                return TRADE_TYPE_4;
            } else if (TRADEKBN_1.equals(tradeKbn)) {
                return TRADE_TYPE_5;
            }
        }
        return NASHI;
    }
   
    /**
    * 取引種別（算出）を算出
    *
    * @param sql005ResponseModel SQL005レスポンス
    * @return 取引種別
    */
    public String getTradeCdCalculation(IfaOrderListSql005ResponseModel sql005ResponseModel) {
        String tradeKbn = sql005ResponseModel.getTradeKbn();
        // 売買区分＝'3':買付の場合
        if (TRADEKBN_3.equals(tradeKbn)) {
            return TRADE_TYPE_11;
        } else if (TRADEKBN_1.equals(tradeKbn)) {
            return TRADE_TYPE_12;
        }
        return NASHI;
    }
   
    /**
    * 条件詳細（算出）を算出
    *
    * @param sql004ResponseModel SQL004レスポンス
    * @return 条件詳細
    */
    public String getConditionDetailsCalculation(IfaOrderListSql004ResponseModel sql004ResponseModel) {
        DecimalFormat domesticFormat = new DecimalFormat(DOMESTIC_FORMAT);
        // 発火条件価格 #,##0.#
        String stopOrderPrice = null;
        if (!ObjectUtils.isEmpty(sql004ResponseModel.getStopOrderPrice())) {
            BigDecimal stopOrderPriceBigDecimal = new BigDecimal(sql004ResponseModel.getStopOrderPrice());
            stopOrderPrice = domesticFormat.format(stopOrderPriceBigDecimal);
        }
        // 売買区分
        String tradeKbn = sql004ResponseModel.getTradeKbn();
        String currencyCode = sql004ResponseModel.getCurrencyCode();
        // 価格条件区分
        String priceConditionsType = sql004ResponseModel.getPriceConditionsType();
        if (PRICE_CONDITIONS_TYPE_4.equals(priceConditionsType)) {
            if (TRADEKBN_3.equals(tradeKbn)) {
                return STOP_MARKETORDER_PREFIX + stopOrderPrice + currencyCode + STOP_MARKETORDER_SUFFX_UP;
            } else if (TRADEKBN_1.equals(tradeKbn)) {
                return STOP_MARKETORDER_PREFIX + stopOrderPrice + currencyCode + STOP_MARKETORDER_SUFFX_DOWN;
            }
        } else if (PRICE_CONDITIONS_TYPE_3.equals(priceConditionsType)) {
            // 指値 #,##0.#
            String sashine = null;
            if (!ObjectUtils.isEmpty(sql004ResponseModel.getSashine())) {
                BigDecimal sashineBigDecimal = new BigDecimal(sql004ResponseModel.getSashine());
                sashine = domesticFormat.format(sashineBigDecimal);
            }
            
            if (TRADEKBN_3.equals(tradeKbn)) {
                return STOP_SASHINE_PREFIX + stopOrderPrice + currencyCode + STOP_SASHINE_MID_UP + sashine + currencyCode + STOP_SASHINE_SUFFX;
            } else if (TRADEKBN_1.equals(tradeKbn)) {
                return STOP_SASHINE_PREFIX + stopOrderPrice + currencyCode + STOP_SASHINE_MID_DOWN + sashine + currencyCode + STOP_SASHINE_SUFFX;
            }
        }
        return NASHI;  
    }
    
    /**
     * 積立コースを算出
     *
     * @param sql008ResponseModel SQL008レスポンス
     * @return 積立コース
     */
    public String getAccumulateCourseValue(IfaOrderListSql008ResponseModel sql008ResponseModel) {
        
        switch(sql008ResponseModel.getAccumulateCourse()) {
            case COURSE_ONE :
                return AccumulateCourse.getEveryDayCourse();
            case COURSE_TWO :
                return AccumulateCourse.getEveryWeekCourse(sql008ResponseModel.getSettingReserveWeekly());
            case COURSE_THREE :
                return AccumulateCourse.getEveryMonthCourse(sql008ResponseModel.getSettingReserveDd());
            case COURSE_FOUR :
                return AccumulateCourse.getMultipleDaysCourse(sql008ResponseModel.getSettingReserveMultiday());
            case COURSE_FIVE :
                return AccumulateCourse.getBimonthlyCourse(sql008ResponseModel.getSettingReserveBimonthly(), sql008ResponseModel.getSettingReserveDd());
            default :
                return null;
        }
    }

    /**
     * 数字を3桁ごとにカンマを付けて整形します
     */
    public String getAmount(String amount) {
        
        if(StringUtils.isNoneEmpty(amount)) {
            long number = Long.parseLong(amount);
        DecimalFormat format = new DecimalFormat(INTEGER_FORMAT);
        return format.format(number) + PRICE_TANI_EN;
        }
        return null;
    }
    
    public String getBonusMonth(String month1, String day1, String month2, String day2) {
        
        String date1 = null;
        String date2 = null;
        
        if(StringUtils.equals(day1, MONTH_END_CODE)) {
            day1 = MONTH_END_VALUE;
        }
        if(StringUtils.equals(day2, MONTH_END_CODE)) {
            day2 = MONTH_END_VALUE;
        }
        if(StringUtils.isNotEmpty(month1) && StringUtils.isNotEmpty(day1)) {
            date1 = month1 + "/" + day1;
            if(StringUtils.isNotEmpty(month2) && StringUtils.isNotEmpty(day2)) {
                date2 = month2 + "/" + day2;
                return "(" + date1 + "," + date2 + ")";
            }else {
                return "(" + date1 + ")";
            }
        }
        return null;
    }
}
