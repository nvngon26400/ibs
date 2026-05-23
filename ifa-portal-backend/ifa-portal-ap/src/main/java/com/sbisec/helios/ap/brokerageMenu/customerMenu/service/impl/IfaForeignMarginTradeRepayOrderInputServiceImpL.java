package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.enums.ClosePositionKind;
import com.sbisec.helios.ap.athena.enums.OrderPriceKind;
import com.sbisec.helios.ap.athena.enums.SettlementMethod;
import com.sbisec.helios.ap.athena.enums.StockTradeType;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.ifa.ForeignInformationService;
import com.sbisec.helios.ap.athena.ifa.ForeignStockService;
import com.sbisec.helios.ap.athena.protocol.account.GetMarginPositionSummaryResp;
import com.sbisec.helios.ap.athena.protocol.account.dto.Position;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.ClosedPositionInput;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginOrder;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginOrderInputForConfirmForeignStockClosedMarginOrder;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockClosedMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockCreatedMarginOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockAttentionSecuritiesResp;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockSecuritiesResp;
import com.sbisec.helios.ap.athena.protocol.information.CreateMarketPriceTicketResp;
import com.sbisec.helios.ap.athena.protocol.information.GetMarketPriceHashResp;
import com.sbisec.helios.ap.athena.protocol.information.ListMarketPricesResp;
import com.sbisec.helios.ap.athena.protocol.information.dto.MarketPrice;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct018;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct018Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct018Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderErrorModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputA012Dto_positionDesignationAreaIndividualPositionInfoList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputA012RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputA012ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputResponseDto_ForeignStockMarginPositionDetailList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputResponseDto_ForeignStockMarginPositionSummary;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputResponseDto_PriceBasicInfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaForeignMarginTradeRepayOrderInputService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ForeignMarginAccountType;
import com.sbisec.helios.ap.common.enums.ForeignRepayMethod;
import com.sbisec.helios.ap.common.enums.ForeignStockTradeAccountOpenStatus;
import com.sbisec.helios.ap.common.enums.MediateAbleTradeFlag;
import com.sbisec.helios.ap.common.enums.NationalityCode;
import com.sbisec.helios.ap.common.enums.PeriodConditions;
import com.sbisec.helios.ap.common.enums.SelectAblePriceConditions;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.enums.TradeSuspendFlag;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.service.MedSystemVariableService;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import lombok.Data;

/**
 * 画面ID：SUB0202_0303-04_1
 * 画面名：米株信用取引返済注文入力
 * @author 松田
 *
 * 2023/11/01 新規作成
 */
@Component(value = "cmpIfaForeignMarginTradeRepayOrderInputService")
public class IfaForeignMarginTradeRepayOrderInputServiceImpL implements IfaForeignMarginTradeRepayOrderInputService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaForeignMarginTradeRepayOrderInputServiceImpL.class);
    
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
     * FCT018 サービス時間チェック（外国）
     */
    @Autowired
    private Fct018 fct018;
    
    /**
     * FCT021 取引制限チェック
     */
    @Autowired
    private Fct021 fct021;
    
    /**
     * 区分値取得クラス
     */
    @Autowired
    private CodeListService codeListService;
    
    /**
     * システム情報取得クラス
     */
    @Autowired
    private MedSystemVariableService medSystemVariableService;
    
    //    @Autowired
    //    private IfaForeignMarginTradeRepayOrderInputDao dao;
    
    /**
     * Athenaラッパークラス
     */
    @Autowired
    private ForeignStockService foreignStockService;
    
    @Autowired
    private ForeignAccountService foreignAccountService;
    
    @Autowired
    private ForeignInformationService foreignInformationService;
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 入力した部店口座は存在しません。<br>部店: [{0}]、口座: [{1}] */
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    /** 取引停止口座のため処理を進めることができません。 */
    private static final String ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** {0}ができないコースです。 \{0}：区分.対象取引（メッセージ表示用）（区分値：X　＠表示パターン：1　）*/
    private static final String ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 外国株式口座が未開設です。　　※現行踏襲 */
    private static final String ERRORS_FOREIGNSTOCKACCOUNTCHECK = "errors.foreignStockAccountCheck";
    
    /** 米株信用口座が未開設です。 */
    private static final String ERRORS_FRS_FOREIGNSTOCKACCOUNT_NOTOPEN_NO1 = "errors.frs.foreignStockAccount.notOpen#1";
    
    /** ただいまの時間は取引時間外のため注文できません。　　※現行踏襲 */
    private static final String ERRORS_FRS_SERVICEHOURS_OUTOFRANGE = "errors.frs.serviceHours.outOfRange";
    
    /** {0}が下限、上限の範囲にありません。[{1}～{2}]　　　※現行踏襲
        {0}："返済株数" {1}：A001.取引下限数量 {2}：A001.取引上限数量　または　A001.注文可能数量　のうち小さいほう */
    private static final String ERRORS_FRS_ORDERVALUE_OUTOFRANGE = "errors.frs.orderValue.outOfRange";
    
    /** {0}が{1}ではありません。[{2}]　　　※現行踏襲
        {0}："返済株数" {1}："取引単位"  {2}：A001.取引単位 */
    private static final String ERRORS_FRS_ORDERINGCONDITION_INCONSISTENT = "errors.frs.orderingCondition.inconsistent";
    
    /** 指定された建玉がありません。　　　※現行踏襲 */
    private static final String ERRORS_FRS_ORDERABLEPOSITION_NOTFOUND = "errors.frs.orderablePosition.notFound";
    
    /** 注文銘柄に対する取引注意情報があります。取引注意情報の内容を顧客へ説明後に注文を執行してください。 */
    private static final String WARNINGS_DMS_INFORMATIONCHECK = "warnings.dms.informationCheck";
    
    /** 当該顧客の{0}に関わる重要な注意情報があるため処理を進めることができません。注意情報欄をご確認ください。
        {0}：区分.対象取引（メッセージ表示用）（区分値：5　＠表示パターン：1　） */
    private static final String ERRORS_CMN_NOTICEERRORCHECK = "errors.cmn.noticeErrorCheck";
    
    /** 未確認の「重要なお知らせ」があります。 */
    private static final String ERRORS_INFORMATIONCHECK = "errors.informationCheck";
    
    /** 当該顧客の{0}に関わる注意情報があります。注意情報欄を確認してください。　{0}：区分.対象取引（メッセージ表示用）（区分値：5　＠表示パターン：1　） */
    private static final String WARNINGS_CMN_NOTICEWARNINGCHECK = "warnings.cmn.noticeWarningCheck";
    
    /** 未確認の重要なお知らせがあります。注意情報欄を確認してください。 */
    private static final String WARNINGS_CMM_INFORMATIONCHECK = "warnings.cmm.informationCheck";
    
    /** 逆指値注文が予め指定した参照価格に達した状態です。 */
    private static final String WARNINGS_FRS_STOPPRICECONDITION_CONFIRM = "warnings.frs.stopPriceCondition.confirm";
    
    /** 期間指定が注文可能期間を超過しています。 */
    private static final String ERRORS_FRS_ORDER_TERMS_OUT_OF_RANGE = "errors.frs.orderTerms.outOfRange";
    
    /** 海外休場日のため、翌営業日の注文となります */
    private static final String WARNINGS_FRS_ORDER_CONTRACT_NEXT_BUSINESS_DAY = "warnings.frs.orderContract.nextBusinessDay";
    
    // --------------------------------
    // メッセージ引数
    // --------------------------------  
    private static final String MESSAGE_ORDER_COUNT_QUANTITY = "注文数量の合計";
    
    private static final String MESSAGE_ORDER_QUANTITY = "注文数量";
    
    private static final String MESSAGE_CLOSE_ORDER_QUANTITY = "返済株数";
    
    private static final String MESSAGE_TRADE_UNIT = "取引単位";
    
    private static final String MESSAGE_LIMIT_PRICE = "注文単価（指値）";
    
    private static final String MESSAGE_STOP_ORDER_PRICE = "参照価格（逆指値）";
    
    private static final String MESSAGE_STOP_ORDER_EXECUTE_PRICE = "注文単価（逆指値）";
    
    // --------------------------------
    // 設定値
    // --------------------------------    
    /** FCT:証券金銭種別 設定値　（外国株式） */
    private static final String FOREIGNSTOCK = "15";
    
    /** FCT:通貨コード　"999"　設定値*/
    private static final String CURRENCY = "999";
    
    /** FCT018: 判定結果：NG*/
    private static final String RESULT_NG = "NG";
    
    /** 区分定義.ドメインID_対象取引 */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 対象取引 区分値：5 */
    private static final String MSG_DISPLAY_TARGET_TRADE_KUBUN = "5";
    
    /** お知らせエラー条件 */
    private static final String NOTE_INFO_ERR_FLAG = "1";
    
    /**　注意情報エラー条件　*/
    private static final String NOTE_LIMIT_ERR_FLAG = "1";
    
    /** MAPキー：本日の注意銘柄URL */
    private static final String URL_KEY_FRS_USSTOCK_URL = "FRS_USSTOCK_URL";
    
    /** MAPキー：休場日URL */
    private static final String URL_KEY_FRS_USSEC_CLOSED_URL = "FRS_USSEC_CLOSED_URL";
    
    /** MAPキー：円貨決済停止日URL */
    private static final String URL_KEY_FRS_YEN_CLOSED_URL = "FRS_YEN_CLOSED_URL";
    
    /** MAPキー：取扱銘柄一覧URL */
    private static final String URL_KEY_FRS_USEQUITY_LIST_URL = "FRS_USEQUITY_LIST_URL";
    
    /** MAPキー：お取引注意事項URL */
    private static final String URL_KEY_FRS_STOCK_QAI_22_URL = "FRS_STOCK_QAI_22_URL";
    
    /** 日時フォーマット yyyy-MM-dd */
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    
    /** API004リクエスト：IP */
    private static final String API004_REQUEST_IP_ADDRESS = "127.0.0.1";
    
    /** API004リクエスト：X-App-User-Agent */
    private static final String API004_REQUEST_AGENT = "Edge";
    
    /** API008リクエスト：channel */
    private static final String API008_REQUEST_CHANNEL = "PHONE";
    
    /** 注文数量上限 */
    private static final BigDecimal CHECK_PRICE_MAX = new BigDecimal(999999999.99).setScale(2, RoundingMode.DOWN);
    
    /** 取引上限数量(上限値0時の値) */
    private static final BigDecimal TRADE_LIMIT_MAX_UNLIMITED = new BigDecimal(9999999999L);
    
    /** 注文数量最低値 */
    private static final BigDecimal CHECK_PRICE_MIN = BigDecimal.ZERO;
    
    /** 逆指値注文即時発火メッセージ対象 */
    private static final String WARNING_STATUS_STOP_ORDER_TRIGGERED = "STOP_ORDER_TRIGGERED";
    
    /** API008:格納対象外建玉数量 */
    private static final String NOT_TARGET_VALUE_ORDER_COUNT = "0";
    
    @Autowired
    private CometCommonService cometCommonService;
    
    /** 取引種別 */
    private enum ApiTradeCd {
        
        /** 取引種別：買建 */
        BUY("4", "BUY"),
        /** 取引種別：売建 */
        SELL("5", "SELL");
        
        ApiTradeCd(String tradeCd, String apiCd) {
            
            this.tradeCd = tradeCd;
            this.apiCd = apiCd;
        }
        
        private String tradeCd;
        
        private String apiCd;
        
        public String getTradeCd() {
            
            return tradeCd;
        }
        
        public String getApiCd() {
            
            return apiCd;
        }
        
        public static ApiTradeCd getByTradeCd(String tradeCd) {
            
            if (StringUtil.isNullOrEmpty(tradeCd)) {
                return null;
            }
            
            ApiTradeCd[] enums = values();
            
            for (int i = 0; i < enums.length; i++) {
                if (enums[i].getTradeCd().equals(tradeCd)) {
                    return enums[i];
                }
            }
            
            return null;
        }
        
    }
    
    /**
     * API001参照項目Dto
     *
     */
    @Data
    private class Api001Response {
        
        /** 銘柄名 */
        private String brandName;
        
        /** 銘柄コード */
        private String brandCode;
        
        /** 銘柄.ric */
        private String brandRic;
        
        /** 市場略名 */
        private String marketAbbreviatedName;
        
        /** 市場コード */
        private String marketCode;
        
        /** 国コード */
        private String countryCode;
        
        /** 取引通貨 */
        private String limitPrice2;
        
        /** タイムゾーン略名 */
        private String timeZoneAbbreviatedName;
        
        /** 銘柄上場ステータス */
        private String brandListedStatus;
        
        /** 取引下限数量 */
        private String tradeLowerLimitQuantity;
        
        /** 取引上限数量 */
        private String tradeUpperLimitQuantity;
        
        /** 取引単位 */
        private String tradingUnit;
        
        /** 銘柄種別 */
        private String brandSyubetsu;
        
        /** 通貨コード */
        private String currencyCode;
    }
    
    /**
     * API002参照項目Dto
     *
     */
    @Data
    private class Api002Response {
        
        /** 選択可能預り区分リスト */
        private List<String> selectAbleDepositTypeList;
        
        /** 選択可能決済方法リスト */
        private List<String> selectAbleSettlementMethodList;
        
        /** 選択可能価格条件リスト */
        private List<String> selectAblePriceTermsList;
        
        /** 選択可能信用期日リスト */
        private List<String> selectAbleMarginDueDateList;
        
        /** 選択可能返済建玉指定方法リスト */
        private List<String> selectAbleRepayPositionDesignationMethodList;
        
        /** 選択可能返済選択順序リスト */
        private List<String> selectAbleRepaySelectSequenceList;
        
        /** 選択可能期間条件リスト */
        private List<String> selectAblePeriodTermsList;
        
        /** 有効期間一覧 */
        private List<String> validityPeriodList;
    }
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignMarginTradeRepayOrderInputA001DtoRequest
     * Dto レスポンス：IfaForeignMarginTradeRepayOrderInputA001ResponseDto
     * model リクエスト：IfaForeignMarginTradeRepayOrderInputSql001RequestModel
     * model レスポンス：IfaForeignMarginTradeRepayOrderInputSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginTradeRepayOrderInputA001ResponseDto> initializeA001(
            IfaForeignMarginTradeRepayOrderInputA001RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaForeignMarginTradeRepayOrderInputServiceImplL.initializeA001");
        
        IfaForeignMarginTradeRepayOrderErrorModel error = new IfaForeignMarginTradeRepayOrderErrorModel();
        
        // 顧客情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // バリデーションチェック
        if (!checkValidation(false, dtoReq.getTradeCd(), error, cc)) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
        }
        
        IfaForeignMarginTradeRepayOrderInputA001ResponseDto response = new IfaForeignMarginTradeRepayOrderInputA001ResponseDto();
        List<IfaForeignMarginTradeRepayOrderInputA001ResponseDto> resDto = new ArrayList<IfaForeignMarginTradeRepayOrderInputA001ResponseDto>();
        
        try {
            //銘柄情報取得
            Api001Response api001Res = getApi001Response(dtoReq.getBrandCode());
            copyFields(api001Res, response);
            //注意銘柄取得
            response.setTradeLimitTitle(getApi003Response(dtoReq.getBrandCode()));
            // 株価チケットなどを取得
            response.setStockTicket(getApi004Response(cc));
            //ハッシュ結果を取得
            String hashResult = getApi005Info(response.getStockTicket(), cc);
            //株価を取得
            response.setPriceBasicInfo(callAPI006(hashResult, api001Res.getBrandRic()));
            //注文の初期情報を取得
            GetForeignStockCreatedMarginOrderInitializationResp api002Res = getApi002Response(dtoReq.getBrandCode(),
                    dtoReq.getTradeCd(), dtoReq.getDepositType(), cc);
            copyFields(setApi002ResponseColumns(api002Res), response);
            //建玉情報を取得
            GetMarginPositionSummaryResp api007Res = getApi007Response(response.getStockTicket(), dtoReq.getBrandCode(),
                    dtoReq.getTrade(), dtoReq.getPaymentDeadline(), dtoReq.getDepositType(), cc);
            
            // 外国株式信用建玉サマリの作成
            response.setForeignStockMarginPositionSummary(createForeignStockMarginPositionSummary(api007Res));
            // 外国株式信用建玉明細一覧の作成
            response.setForeignStockMarginPositionDetailList(createForeignStockMarginPositionDetailList(api007Res));
            //SQL001から取得
            setURLList(response, dtoReq.getBrandCode());
            // リクエスト項目の設定
            copyFields(dtoReq, response);   
            // 期間の設定
            response.setPeriodList(calcTerm(api002Res.getOrderTerms(), api007Res.getPositions()));
            //レスポンス用DTOに追加
            resDto.add(response);         

        } catch(Exception e) {
            //エラーハンドリング
            LOGGER.info("IfaForeignMarginTradeRepayOrderInputServiceImplL.initializeA001 : Exception occured.");      
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        //正常終了
        return IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        
    }
    
    /**
     * アクションID：A005
     * アクション名：更新
     * Dto リクエスト：IfaForeignMarginTradeRepayOrderInputA005RequestDto
     * Dto レスポンス：IfaForeignMarginTradeRepayOrderInputA005ResponseDto
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginTradeRepayOrderInputA005ResponseDto> updateA005(
            IfaForeignMarginTradeRepayOrderInputA005RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaForeignMarginTradeRepayOrderInputServiceImplL.updateA005");
        
        IfaForeignMarginTradeRepayOrderErrorModel error = new IfaForeignMarginTradeRepayOrderErrorModel();

        // 顧客情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // バリデーションチェック
        if (!checkValidation(false, dtoReq.getTradeCd(), error, cc)) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
        }

        IfaForeignMarginTradeRepayOrderInputA005ResponseDto response = new IfaForeignMarginTradeRepayOrderInputA005ResponseDto();
        List<IfaForeignMarginTradeRepayOrderInputA005ResponseDto> resDto = new ArrayList<IfaForeignMarginTradeRepayOrderInputA005ResponseDto>();           

        try {
            //銘柄情報取得
            Api001Response api001Res = getApi001Response(dtoReq.getBrandCode());
            copyFields(api001Res, response);
            // 株価チケットなどを取得
            String stockTicket = getApi004Response(cc);
            //ハッシュ結果を取得
            String hashResult = getApi005Info(stockTicket, cc);
            //株価を取得
            response.setPriceBasicInfo(callAPI006(hashResult, api001Res.getBrandRic()));
            //注文の初期情報を取得
            GetForeignStockCreatedMarginOrderInitializationResp api002Res = getApi002Response(dtoReq.getBrandCode(),
                    dtoReq.getTradeCd(), dtoReq.getDepositType(), cc);
            copyFields(setApi002ResponseColumns(api002Res), response);
            //建玉情報を取得
            GetMarginPositionSummaryResp api007Res = getApi007Response(stockTicket, dtoReq.getBrandCode(),
                    dtoReq.getTrade(), dtoReq.getPaymentDeadline(), dtoReq.getDepositType(), cc);
            
            // 外国株式信用建玉サマリの作成
            response.setForeignStockMarginPositionSummary(createForeignStockMarginPositionSummary(api007Res));
            // 外国株式信用建玉明細一覧の作成
            response.setForeignStockMarginPositionDetailList(createForeignStockMarginPositionDetailList(api007Res));
             
            // リクエスト項目の設定
            copyFields(dtoReq, response);
            
            // 期間の設定
            response.setPeriodList(calcTerm(api002Res.getOrderTerms(), api007Res.getPositions()));
            
            resDto.add(response);
            
        } catch(Exception e) {
            //エラーハンドリング
            LOGGER.info("IfaForeignMarginTradeRepayOrderInputServiceImplL.updateA005 : Exception occured.");
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        //正常終了
        return IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        
    }
    
    /**
     * アクションID：A012
     * アクション名：注文確認
     * Dto リクエスト：IfaForeignMarginTradeRepayOrderInputA012RequestDto
     * Dto レスポンス：IfaForeignMarginTradeRepayOrderInputA012ResponseDto
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginTradeRepayOrderInputA012ResponseDto> orderConfirmA012(
            IfaForeignMarginTradeRepayOrderInputA012RequestDto dtoReq) throws Exception {
        
        IfaForeignMarginTradeRepayOrderInputA012ResponseDto response = new IfaForeignMarginTradeRepayOrderInputA012ResponseDto();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaForeignMarginTradeRepayOrderInputServiceImplL.orderConfirmA012");
        /* 処理１～３は画面処理前提 */
        
        IfaForeignMarginTradeRepayOrderErrorModel error = new IfaForeignMarginTradeRepayOrderErrorModel();

        // 顧客情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // バリデーションチェック
        if (!checkValidation(true, dtoReq.getTradeCd(), error, cc)) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
        }
        
        List<IfaForeignMarginTradeRepayOrderInputA012ResponseDto> resDto = new ArrayList<IfaForeignMarginTradeRepayOrderInputA012ResponseDto>();

        try {
            
            /* 一括・個別共通のため先にAPIを呼び出す */
            // API001処理
            Api001Response api001Res = getApi001Response(dtoReq.getBrandCode());
            copyFields(api001Res, response);
            // API004処理
            String stockTicket = getApi004Response(cc);
            // API007処理
            GetMarginPositionSummaryResp api007Res = getApi007Response(stockTicket, dtoReq.getBrandCode(),
                    dtoReq.getTrade(), dtoReq.getPaymentDeadline(), dtoReq.getDepositType(), cc);
            
            // 注文数量チェック
            boolean isBatch = StringUtils.equals(dtoReq.getRepayPositionDesignateMethod(),
                    ForeignRepayMethod.BATCH.getId());
            boolean isIndvidual = StringUtils.equals(dtoReq.getRepayPositionDesignateMethod(),
                    ForeignRepayMethod.INDIVIDUAL.getId());
            boolean checkOrderQuantity = true;
            if (isBatch) {
                // 一括処理向けチェック処理
                checkOrderQuantity = checkBatch(dtoReq.getCloseOrderQuantity(), api007Res.getPositionSummary(), api001Res, error);
            } else if (isIndvidual) {
                // 個別処理向けチェック処理
                checkOrderQuantity = checkIndividual(api007Res.getPositions(), dtoReq.getTotal(),
                        dtoReq.getPositionDesignationAreaIndividualPositionInfoList(), api001Res, error);
            }
            if (!checkOrderQuantity) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
            }
            
            // 注意銘柄チェック
            response.setTradeLimit(getApi003Response(dtoReq.getBrandCode()));
            if (StringUtils.equals(response.getTradeLimit(), Boolean.TRUE.toString())) {
                response.setTradeNoticeInfoBrandMsg(Arrays.asList(new String[] {IfaCommonUtil.getMessage(WARNINGS_DMS_INFORMATIONCHECK)}));
            }
            // 注文単価チェック
            if (!checkprice(dtoReq.getLimitPrice2(), MESSAGE_LIMIT_PRICE, error)) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
            }
            // 発火条件価格（逆指値）チェック
            if (!checkprice(dtoReq.getStopOrderPrice(), MESSAGE_STOP_ORDER_PRICE, error)) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
            }
            // 注文単価（逆指値） チェック
            if (!checkprice(dtoReq.getStopOrderExecutePrice2(), MESSAGE_STOP_ORDER_EXECUTE_PRICE, error)) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
            }
            
            // 期間条件チェック
            if (StringUtils.equals(dtoReq.getPeriodRadio(), PeriodConditions.PERIOD_DESIGNATION.getId())) {
                if (checkTerm(dtoReq, api007Res, cc)) {
                    return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_FRS_ORDER_TERMS_OUT_OF_RANGE,
                            IfaCommonUtil.getMessage(ERRORS_FRS_ORDER_TERMS_OUT_OF_RANGE));
                }
            }
            
            // リクエスト項目の設定
            copyFields(dtoReq, response);
            
            // fct021
            OutputFct021Dto fct021Res = callFCT021(dtoReq.getTradeCd(), error, cc);
            if (fct021Res == null) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
            }
            if (StringUtils.equals(fct021Res.getNoteInfoAlertFlag(), Fct021.EXIST)) {
                response.setNoticeInfoAlert(Arrays.asList(new String[] { IfaCommonUtil.getMessage(
                        WARNINGS_CMN_NOTICEWARNINGCHECK,
                        new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_KUBUN),
                                MSG_DISPLAY_TARGET_TRADE_KUBUN }) }));
            }
            if (StringUtils.equals(fct021Res.getNoteLimitAlertFlag(), Fct021.EXIST)) {
                response.setNoticeAlert(
                        Arrays.asList(new String[] { IfaCommonUtil.getMessage(WARNINGS_CMM_INFORMATIONCHECK) }));
            }
            // API008
            ConfirmForeignStockClosedMarginOrderResp api008Res = null;
            api008Res = callApi008(dtoReq, api007Res.getPositions(), cc);
            // API008の内容をレスポンスに設定
            if (!ObjectUtils.isEmpty(api008Res)) {
                setResponseAtApi008(response, api008Res);
            }
            
            // 株価チケットなどを取得
            response.setStockTicket(stockTicket);
            //株価を取得
            response.setPriceBasicInfo(callAPI006(getApi005Info(response.getStockTicket(), cc), api001Res.getBrandRic()));
            
            resDto.add(response);
        } catch(Exception e) {
            //エラーハンドリング
            LOGGER.info("IfaForeignMarginTradeRepayOrderInputServiceImplL.orderConfirmA012 : Exception occured.");
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        //正常終了
        return IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }
    
    /**
     * API向け取引種別の設定
     * 取引種別を変換する（買建："4"→"BUY"､売建："5"→"SELL"）
     * @param tradeCd
     * @return 返還後の取引種別
     */
    private String getApiTradeCd(String tradeCd) {
        
        String apiTradeCd = null;
        
        ApiTradeCd atc = ApiTradeCd.getByTradeCd(tradeCd);
        if (atc == null) {
            return apiTradeCd;
        }
        apiTradeCd = atc.getApiCd();
        
        return apiTradeCd;
    }
    
    /**
     * 一括指定時の入力チェック
     * 
     * @param quantity
     * @param api007ResSummary
     * @param api001Res
     * @return true:OK false:NG
     */
    private boolean checkBatch(String quantity, Position api007ResSummary, Api001Response api001Res, IfaForeignMarginTradeRepayOrderErrorModel error) {
        
        // 注文数量の下限・上限チェック
        if (!isCompareQuantity(api001Res.getTradeLowerLimitQuantity(), api001Res.getTradeUpperLimitQuantity(),
                api007ResSummary.getClosableQuantity(), quantity, MESSAGE_CLOSE_ORDER_QUANTITY, error)) {
            return false;
        }
        
        // 取引単位で割り切れるか？
        if (StringUtil.parseBigDecimal(quantity)
                .remainder(StringUtil.parseBigDecimal(api001Res.getTradingUnit())) != BigDecimal.ZERO) {
            error.setErrorCode(ERRORS_FRS_ORDERINGCONDITION_INCONSISTENT);
            error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_FRS_ORDERINGCONDITION_INCONSISTENT,
                    new String[] { MESSAGE_CLOSE_ORDER_QUANTITY, MESSAGE_TRADE_UNIT, api001Res.getTradingUnit(),
                            api001Res.getTradingUnit() }));
            return false;
        }
        
        return true;
    }
    
    /**
     * 個別指定の入力チェック
     * 
     * @param positions
     * @param quantity
     * @param positionDesignationAreaIndividualPositionInfoList
     * @param api001Res
     * @return true:OK false:NG
     */
    private boolean checkIndividual(List<Position> positions, String quantity,
            List<IfaForeignMarginTradeRepayOrderInputA012Dto_positionDesignationAreaIndividualPositionInfoList> positionDesignationAreaIndividualPositionInfoList,
            Api001Response api001Res, IfaForeignMarginTradeRepayOrderErrorModel error) {
        
        // 注文数量の下限・上限チェック
        if (!isCompareQuantity(api001Res.getTradeLowerLimitQuantity(), api001Res.getTradeUpperLimitQuantity(), null,
                quantity, MESSAGE_ORDER_COUNT_QUANTITY, error)) {
            return false;
        }
        
        // 個別建玉情報一覧ごとにチェック
        for (IfaForeignMarginTradeRepayOrderInputA012Dto_positionDesignationAreaIndividualPositionInfoList req : positionDesignationAreaIndividualPositionInfoList) {
            
            // A010.注文数量（個別）が空文字もしくはNULLの場合、チェック対象外とする。
            if (!ObjectUtils.isEmpty(req.getOrderCount())){
            
                // 建日と建単価で存在チェック
                Optional<Position> pos = positions.stream()
                        .filter(p -> StringUtils.equals(p.getTradeDate(), req.getDomesticTradeDate())
                                && StringUtils.equals(p.getFrnPositionPrice(), req.getSinyoPreviousDayValue()))
                        .findFirst();
                
                // 個別：建玉明細一覧に存在するか
                if (!pos.isPresent()) {
                    error.setErrorCode(ERRORS_FRS_ORDERABLEPOSITION_NOTFOUND);
                    error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_FRS_ORDERABLEPOSITION_NOTFOUND));
                    return false;
                }
                Position detail = pos.get();
                
                // 個別：0以上　かつ　API007.外国株式信用建玉明細一覧.決済注文可能数量以下　の範囲内か
                BigDecimal closableQuantitybd = StringUtil.parseBigDecimal(detail.getClosableQuantity());
                BigDecimal orderCountbd = StringUtil.parseBigDecimal(req.getOrderCount());
                
                if (orderCountbd.compareTo(BigDecimal.ZERO) < 0 || (closableQuantitybd.compareTo(BigDecimal.ZERO) > 0
                        && orderCountbd.compareTo(closableQuantitybd) > 0)) {
                    error.setErrorCode(ERRORS_FRS_ORDERVALUE_OUTOFRANGE);
                    error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_FRS_ORDERVALUE_OUTOFRANGE, new String[] {
                            MESSAGE_ORDER_QUANTITY, BigDecimal.ZERO.toString(), detail.getClosableQuantity() }));
                    return false;
                }
                
                // 取引単位割り切りチェック
                if (StringUtil.parseBigDecimal(detail.getQuantity())
                        .remainder(StringUtil.parseBigDecimal(api001Res.getTradingUnit())) != BigDecimal.ZERO) {
                    error.setErrorCode(ERRORS_FRS_ORDERINGCONDITION_INCONSISTENT);
                    error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_FRS_ORDERINGCONDITION_INCONSISTENT,
                            new String[] { MESSAGE_ORDER_QUANTITY, MESSAGE_TRADE_UNIT, api001Res.getTradingUnit(),
                                    api001Res.getTradingUnit() }));
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * 有効期間の9営業日以内に存在する日付確認
     * @param periodDate
     * @param orderTerms
     * @return
     * @throws Exception 
     */
    private boolean checkTerm(IfaForeignMarginTradeRepayOrderInputA012RequestDto dtoReq,
            GetMarginPositionSummaryResp api007Res, CustomerCommon cc) throws Exception {
        
        GetForeignStockCreatedMarginOrderInitializationResp api002Res = getApi002Response(dtoReq.getBrandCode(),
                dtoReq.getTradeCd(), dtoReq.getDepositType(), cc);
        
        DateFormat format1 = new SimpleDateFormat(DateFormatUtil.SEPARATED_YYYYMMDD);
        DateFormat format2 = new SimpleDateFormat(DateFormatUtil.SEPARATED_YYYYMMDD);
        // API002.有効期間一覧（T+1～T+9のうち取得可能な日付）の最大値
        int loopMaxCount = 9;
        if (CollectionUtils.isEmpty(api002Res.getOrderTerms())) {
            loopMaxCount = 0;
        } else if (api002Res.getOrderTerms().size() < 9) {
            loopMaxCount = api002Res.getOrderTerms().size();
        }
        List<Date> api002DateList = new ArrayList<Date>();
        if (loopMaxCount > 0) {
            for (int i = 0; i < loopMaxCount; i++) {
                try {
                    String convertTerm = api002Res.getOrderTerms().get(i);
                    if (!StringUtil.isNullOrEmpty(convertTerm)) {
                        api002DateList.add(format1.parse(convertTerm));
                    }
                } catch (ParseException e) {
                    LOGGER.debug("DateFormat ParseException: " + e.getMessage(), e);
                }
            }
        }
        // API007.外国株式信用建玉明細一覧.現地決済期日の最大値
        Optional<Date> maxOrderTermDateOpt = api002DateList.stream().max((d1, d2) -> d1.compareTo(d2));
        Optional<Date> maxFrnCloseLimitDateOpt = api007Res.getPositions().stream()
                .map(position -> position.getFrnCloseLimitDate())
                .map(frnCloseLimitDate -> {
                    try {
                        return StringUtil.isNullOrEmpty(frnCloseLimitDate) ? null : format1.parse(frnCloseLimitDate);
                    } catch (ParseException e) {
                        LOGGER.debug("DateFormat ParseException: " + e.getMessage(), e);
                        return null;
                    }
                }).filter(Objects::nonNull).max((d1, d2) -> d1.compareTo(d2));
        Date maxOrderTermDate = maxOrderTermDateOpt.isPresent() ? maxOrderTermDateOpt.get() : null;
        Date maxFrnCloseLimitDate = maxFrnCloseLimitDateOpt.isPresent() ? maxFrnCloseLimitDateOpt.get() : null;
        Date periodDate = format2.parse(DateFormatUtil.dateFormatToHyphen(dtoReq.getPeriod()));
        // API002.有効期間一覧（T+1～T+9のうち取得可能な日付）の最大値とAPI007.外国株式信用建玉明細一覧.現地決済期日の最大値を比較し、小さい方を最大値とする
        if (maxOrderTermDate == null && maxFrnCloseLimitDate == null) {
            LOGGER.debug("API response no date.");
            return true;
        }
        Date maxPeriodDate = null;
        if (maxOrderTermDate == null && maxFrnCloseLimitDate != null) {
            maxPeriodDate = maxFrnCloseLimitDate;
        } else if (maxOrderTermDate != null && maxFrnCloseLimitDate == null) {
            maxPeriodDate = maxOrderTermDate;
        } else {
            maxPeriodDate = maxOrderTermDate.before(maxFrnCloseLimitDate) ? maxOrderTermDate : maxFrnCloseLimitDate;
        }
        
        return periodDate.after(maxPeriodDate);
        
    }
    
    /**
     * レスポンス項目API008設定処理
     * 
     * @param response
     * @param api008Res
     * @throws Exception 
     */
    private void setResponseAtApi008(IfaForeignMarginTradeRepayOrderInputA012ResponseDto response,
            ConfirmForeignStockClosedMarginOrderResp api008Res) throws Exception {
        
        // 注文情報の設定
        setResponseAtApi008MarginOrder(response, api008Res.getMarginOrder());
        
        // 見積価格
        response.setQuotePrice(api008Res.getEstimatePrice());
        // 概算諸経費合計
        response.setApproximateCost(api008Res.getEstimateTotalExpenses());
        // 信用建余力（注文後）
        response.setMarginPositionPowerOrderAfter(api008Res.getMarginBuyingPower());
        // 委託保証金率（注文後預託率）
        response.setMarginDepositRateOrderAfter(api008Res.getDepositRate());
        
        // 逆指値注文即時発火メッセージ
        if (api008Res.getWarningStatuses().contains(WARNING_STATUS_STOP_ORDER_TRIGGERED)) {
            response.setMethodCheckMessage(
                    Arrays.asList(new String[] { IfaCommonUtil.getMessage(WARNINGS_FRS_STOPPRICECONDITION_CONFIRM) }));
        }
        
        // 現地約定日超過メッセージ
        if (api008Res.getMarginOrder() != null && api008Res.getMarginOrder().getOrder() != null) {
            // 注文日時
            Date convDate = DateFormatUtil
                    .dateFormatToDate(api008Res.getMarginOrder().getOrder().getOrderInputDatetime());
            LocalDate orderDate = convDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // 現地約定日
            LocalDate frnTradeDate = LocalDate.parse(api008Res.getMarginOrder().getOrder().getFrnTradeDate(),
                    DateTimeFormatter.ofPattern(DATE_FORMAT));
            
            response.setLocalTradeDateLimitMsg(frnTradeDate.isAfter(orderDate)
                    ? Arrays.asList(
                            new String[] { IfaCommonUtil.getMessage(WARNINGS_FRS_ORDER_CONTRACT_NEXT_BUSINESS_DAY) })
                    : null);
            
        }
        
    }
    
    /**
     * API008注文情報設定
     * @param response
     * @param mo
     */
    private void setResponseAtApi008MarginOrder(IfaForeignMarginTradeRepayOrderInputA012ResponseDto response,
            MarginOrder mo) {
        
        // 注文番号
        response.setOrderNumber(mo.getOrder().getOrderNo());
        // 注文Sub番号
        response.setOrderSubNumber(mo.getOrder().getOrderSubNo());
        // 取引通貨
        response.setCurrencyCode(mo.getOrder().getTradeCurrencyCode());
        // 売買区分
        response.setTradeKbn(mo.getOrder().getBuySellCode());
        // 自動買付区分
        response.setAutoBuyingClassification(mo.getOrder().getAutoStockOrderType());
        // 注文数量
        response.setOrderQuantity(mo.getOrder().getOrderQuantity());
        // 価格条件
        response.setOrderPriceKindList(mo.getOrder().getOrderPriceKindCode());
        // 注文単価
        response.setHiddenOrderPrice(mo.getOrder().getOrderPrice());
        // 発火条件価格
        response.setStopOrderPrice(mo.getOrder().getStopPrice());
        // トレールストップ幅
        response.setTrailStopWidth(mo.getOrder().getTrailingStopAmount());
        // 成行基準価格
        response.setExecuteBasePrice(mo.getOrder().getNoLimitPrice());
        // 期間条件
        response.setPeriodRadio(mo.getOrder().getOrderLimitCode());
        // 期間
        response.setPeriod(mo.getOrder().getOrderTerm());
        // 預り区分
        response.setDepositType(mo.getOrder().getSpecificAccountCode());
        // 決済方法
        response.setKessaiHoho(mo.getOrder().getSettlementMethodCode());
        // 決済通貨
        response.setSettlementCurrencyCode(mo.getOrder().getSettlementCurrencyCode());
        // 為替レート
        response.setFxRate(mo.getOrder().getExchangeRate());
        // 平均約定単価
        response.setAverageTradePrice(mo.getOrder().getExecutionAveragePrice());
        // 未約定数量
        response.setUnTradeQuantity(mo.getOrder().getUnexecutedQuantity());
        // 約定数量（合計）
        response.setTradeQuantityTotal(mo.getOrder().getExecutionQuantity());
        // 約定金額（外貨）
        response.setContractAmountForeign(mo.getOrder().getFrnGrossAmount());
        // 約定金額（円貨）
        response.setContractAmountJpyAmount(mo.getOrder().getGrossAmount());
        // 受渡金額（外貨）
        response.setForeignDeliveryAmount(mo.getOrder().getFrnNetAmount());
        // 受渡金額（円貨）
        response.setYenDeliveryAmount(mo.getOrder().getNetAmount());
        // 受渡金額（約定分）
        response.setContractDeliveryAmount(mo.getOrder().getExecutionNetAmount());
        // 国内手数料（外貨）
        response.setDomesticCommForeign(mo.getOrder().getFrnCommissionAmount());
        // 国内手数料（円貨）
        response.setDomesticCommJpAmount(mo.getOrder().getCommissionAmount());
        // 国内消費税（外貨）
        response.setDomesticConsumptionTaxForeign(mo.getOrder().getFrnCommissionCtax());
        // 国内消費税（円貨）
        response.setDomesticConsumptionTaxJpAmount(mo.getOrder().getCommissionCtax());
        // 現地手数料等（外貨）
        response.setLocalCommForeign(mo.getOrder().getFrnLocalCharge());
        // 現地手数料等（円貨）
        response.setLocalCommJpAmount(mo.getOrder().getLocalCharge());
        // 現地精算金額（外貨）
        response.setLocalSettlementAmountForeign(mo.getOrder().getFrnLocalNetAmount());
        // 現地精算金額（円貨）
        response.setLocalSettlementJpAmount(mo.getOrder().getLocalNetAmount());
        // NISA枠拘束金額
        response.setNisaRestraintAmount(mo.getOrder().getNisaFixedAmount());
        // 概算譲渡益税
        response.setApproximateCapitalGainsTax(mo.getOrder().getSpecificTax());
        // 注文状況
        response.setOrderStatus(mo.getOrder().getOrderStatus());
        // 約定状況
        response.setTradeStatus(mo.getOrder().getExecutionStatus());
        // 発火状況
        response.setStopOrderStatus(mo.getOrder().getWorkingStatus());
        // 国内約定日
        response.setBusinessDaysAfterOrder(mo.getOrder().getTradeDate());
        // 国内受渡日
        response.setDomesticSettlementDate(mo.getOrder().getValueDate());
        // 現地約定日
        response.setLocalTradeDate(mo.getOrder().getFrnTradeDate());
        // 現地受渡日
        response.setForeignSettlementDate(mo.getOrder().getFrnValueDate());
        // 注文日時
        response.setOrderDate(mo.getOrder().getOrderInputDatetime());
        // 約定日時
        response.setTradeDateTime(mo.getOrder().getExecutionDatetime());
        // 失効日時
        response.setExpiredDateTime(mo.getOrder().getExpiredDatetime());
        // 株取引区分
        response.setStockTradeType(mo.getOrder().getStockTradeType());
        // 返済建玉指定方法
        response.setRepayPositionDesignateMethod(mo.getClosePositionKind());
        // 返済選択順序
        response.setRepaySelectOrder(mo.getCloseSelectionSort());
        // 決済損益
        response.setSettlement(mo.getClosedProfitLoss());
        // 保証金不足金額
        response.setDepositDeficientAmount(mo.getMarginDeficitAmount());
        // 振替預り金額
        response.setTransferDepositAmount(mo.getTransferDepositAmount());
        // 振替有価証券評価額
        response.setTransferValuableSecurityValuation(mo.getTransferEvaluationAmount());
        
    }
    
    /**
     * 数量チェック
     * 
     * @param checkPrice
     * @param checkColumName
     * @return
     */
    private boolean checkprice(String checkPrice, String checkColumName, IfaForeignMarginTradeRepayOrderErrorModel error) {
        
        BigDecimal checkPricebd = StringUtil.parseBigDecimal(checkPrice);
        if (checkPricebd == null) {
            return true;
        }
        
        // 0以上　999999999.99以下チェック
        if (!(checkPricebd.compareTo(CHECK_PRICE_MIN) >= 0 && checkPricebd.compareTo(CHECK_PRICE_MAX) <= 0)) {
            error.setErrorCode(ERRORS_FRS_ORDERVALUE_OUTOFRANGE);
            error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_FRS_ORDERVALUE_OUTOFRANGE,
                    new String[] { checkColumName, CHECK_PRICE_MIN.toString(), CHECK_PRICE_MAX.toString() }));
            return false;
        }
        
        return true;
    }
    
    /**
     * 注文数量の上限・下限範囲チェック処理
     * 
     * @param lowerLimit
     * @param higherLimit1
     * @param higherLimit2
     * @param quantity
     * @param messageAtt
     * @return
     */
    private boolean isCompareQuantity(String lowerLimit, String higherLimit1, String higherLimit2, String quantity,
            String messageAtt, IfaForeignMarginTradeRepayOrderErrorModel error) {
        
        /* BigDecimalへ変換 */
        // 下限数量
        BigDecimal lowerLimitbd = StringUtil.parseBigDecimal(lowerLimit);
        // 取引上限数量
        BigDecimal higherLimitbd1 = StringUtil.parseBigDecimal(higherLimit1);
        BigDecimal higherLimitbd2 = !StringUtil.isNullOrEmpty(higherLimit2) ? StringUtil.parseBigDecimal(higherLimit2)
                : null;
        BigDecimal maxLimit = BigDecimal.ZERO;
        // 比較数量
        BigDecimal quantitybd = StringUtil.parseBigDecimal(quantity);
        
        // 上限数量は低い方を参照する
        if (higherLimitbd2 == null || higherLimitbd1.compareTo(higherLimitbd2) <= 0) {
            maxLimit = higherLimitbd1;
        } else {
            maxLimit = higherLimitbd2;
        }
        // 上限値が0の場合は9999999999として扱う
        if (maxLimit.equals(BigDecimal.ZERO)) {
            maxLimit = TRADE_LIMIT_MAX_UNLIMITED;
        }
        
        if (quantitybd.compareTo(lowerLimitbd) < 0
                || quantitybd.compareTo(maxLimit) > 0) {
            error.setErrorCode(ERRORS_FRS_ORDERVALUE_OUTOFRANGE);
            error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_FRS_ORDERVALUE_OUTOFRANGE,
                    new String[] { messageAtt, lowerLimit.toString(), maxLimit.toString() }));
            return false;
        }
        return true;
    }
    
    /**
     * 期間算出処理
     * @param orderTerms API002.有効期間一覧
     * @param positions API007.外国株式信用建玉明細一覧
     * @return 期間
     */
    private List<String> calcTerm(List<String> orderTerms, List<Position> positions) {
        
        LocalDate maxTerm = null;
        boolean isNonApi002 = CollectionUtils.isEmpty(orderTerms);
        boolean isNonApi007 = CollectionUtils.isEmpty(positions);
        
        if (isNonApi002 && isNonApi007) {
            return null;
        }
        List<LocalDate> api002List = new ArrayList<LocalDate>();
        List<LocalDate> api007List = new ArrayList<LocalDate>();
        // 有効期間一覧T+1～T+9の最大値
        LocalDate api002MaxDate = null;
        // 外国株式信用建玉明細一覧.現地決済期日の最大値
        LocalDate api007MaxDate = null;
        // String -> LocalDateに変換して最大値を取得
        if (!isNonApi002) {
            // T+1～T+9
            int loopMaxCount = 9;
            if (loopMaxCount > orderTerms.size()) {
                loopMaxCount = orderTerms.size();
            }
            
            for (int i = 0; i < loopMaxCount; i++) {
                String convertTerm = orderTerms.get(i);
                if (!StringUtil.isNullOrEmpty(convertTerm)) {
                    api002List.add(LocalDate.parse(convertTerm, DateTimeFormatter.ofPattern(DATE_FORMAT)));
                }
            }
            Optional<LocalDate> maxApi002DateOpt = api002List.stream().max(LocalDate::compareTo);
            api002MaxDate = maxApi002DateOpt.isPresent() ? maxApi002DateOpt.get() : null;
        }
        if (!isNonApi007) {
            api007List = positions.stream().filter(pos -> !StringUtil.isNullOrEmpty(pos.getFrnCloseLimitDate()))
                    .map(p -> LocalDate.parse(p.getFrnCloseLimitDate(), DateTimeFormatter.ofPattern(DATE_FORMAT)))
                    .collect(Collectors.toList());
            Optional<LocalDate> maxApi007DateOpt = api007List.stream().max(LocalDate::compareTo);
            api007MaxDate = maxApi007DateOpt.isPresent() ? maxApi007DateOpt.get() : null;
        }
        // 最大値を比較し、小さいほうを期間指定ドロップダウンリストの最大値とする
        if (api002MaxDate != null && api007MaxDate != null) {
            maxTerm = api002MaxDate.isBefore(api007MaxDate) ? api002MaxDate : api007MaxDate;
        } else if (api002MaxDate == null && api007MaxDate != null) {
            maxTerm = api002MaxDate;
        } else {
            maxTerm = api007MaxDate;
        }
        // 期間リストの作成
        List<String> periodList = new ArrayList<String>();
        if (maxTerm != null) {
            for (String term : orderTerms) {
                LocalDate convTerm = LocalDate.parse(term, DateTimeFormatter.ofPattern(DATE_FORMAT));
                if (convTerm.isBefore(maxTerm) || convTerm.equals(maxTerm)) {
                    periodList.add(term);
                } else {
                    break;
                }
            }
        }
        
        return periodList;
    }
    
    /**
     * 各URL項目設定処理
     * @param response
     * @throws Exception
     */
    private void setURLList(IfaForeignMarginTradeRepayOrderInputA001ResponseDto response, String branchCode)
            throws Exception {

        // 本日の注意銘柄URL
        response.setTradeLimitUrl(medSystemVariableService.getMedSystemVariable(URL_KEY_FRS_USSTOCK_URL));
        // 休場日URL
        response.setClosedDayUrl(medSystemVariableService.getMedSystemVariable(URL_KEY_FRS_USSEC_CLOSED_URL));
        // 円貨決済停止日URL
        response.setYenClosedUrl(medSystemVariableService.getMedSystemVariable(URL_KEY_FRS_YEN_CLOSED_URL));
        // 取扱銘柄一覧URL
        response.setUsequityListUrl(medSystemVariableService.getMedSystemVariable(URL_KEY_FRS_USEQUITY_LIST_URL));
        // お取引注意事項URL
        response.setTradingAttentionUrl(medSystemVariableService.getMedSystemVariable(URL_KEY_FRS_STOCK_QAI_22_URL));
        
    }
    
    /**
     * 外国株式信用建玉明細一覧設定処理
     * @param api007Res
     * @return
     */
    private List<IfaForeignMarginTradeRepayOrderInputResponseDto_ForeignStockMarginPositionDetailList> createForeignStockMarginPositionDetailList(
            GetMarginPositionSummaryResp api007Res) {
        
        List<IfaForeignMarginTradeRepayOrderInputResponseDto_ForeignStockMarginPositionDetailList> result = new ArrayList<IfaForeignMarginTradeRepayOrderInputResponseDto_ForeignStockMarginPositionDetailList>();
        if (api007Res.getPositions() == null) {
            // 未取得なら何もしない
            return result;
        }
        for (Position pos : api007Res.getPositions()) {
            IfaForeignMarginTradeRepayOrderInputResponseDto_ForeignStockMarginPositionDetailList dto = new IfaForeignMarginTradeRepayOrderInputResponseDto_ForeignStockMarginPositionDetailList();
            // 国内新規約定日
            dto.setDomesticTradeDate(pos.getTradeDate());
            // 現地新規約定日
            dto.setForeignTradeDate(pos.getFrnTradeDate());
            // 現地決済期日
            dto.setForeignSetDate(pos.getFrnCloseLimitDate());
            // 信用期日
            dto.setMarginDueDate(pos.getMarginCloseLimitType());
            // 建玉必要保証金率
            dto.setMarginRequirementRatio(pos.getPositionMarginRate());
            // 増担保規制建玉フラグ
            dto.setSecurityOpenPositionFlag(pos.getNeedAdditionalCollateral().toString());
            // 新規建単価（外貨）
            dto.setSinyoPreviousDayValue(pos.getFrnPositionPrice());
            // 数量（数値(整数)）
            dto.setQuantity(pos.getQuantity());
            // 決済注文中数量
            dto.setQuantityOrdering(pos.getClosingQuantity());
            // 決済注文可能数量
            dto.setPerOpenInterestMaxOrderableQuantity(pos.getClosableQuantity());
            // 評価損益（外貨）
            dto.setProfitAndLossContainOtherCost(pos.getEvaluationProfitLoss().getFrnEvaluationProfitLoss());
            
            result.add(dto);
        }
        
        return result;
    }
    
    /**
     * 外国株式信用建玉サマリ設定処理
     * 
     * @param api007Res
     * @return
     */
    private IfaForeignMarginTradeRepayOrderInputResponseDto_ForeignStockMarginPositionSummary createForeignStockMarginPositionSummary(
            GetMarginPositionSummaryResp api007Res) {
        
        IfaForeignMarginTradeRepayOrderInputResponseDto_ForeignStockMarginPositionSummary result = new IfaForeignMarginTradeRepayOrderInputResponseDto_ForeignStockMarginPositionSummary();
        
        if (api007Res.getPositionSummary() != null) {
            // 売買区分（全角半角）
            result.setTradeKbn(api007Res.getPositionSummary().getBuySellCode());
            // 信用期日
            result.setMarginDueDate(api007Res.getPositionSummary().getMarginCloseLimitType());
            // 新規建単価（外貨）
            result.setPreviousDayValue(api007Res.getPositionSummary().getFrnPositionPrice());
            // 数量（数値(整数)）
            result.setQuantity(api007Res.getPositionSummary().getQuantity());
            // 決済注文中数量
            result.setStockNumOrdering(api007Res.getPositionSummary().getClosingQuantity());
            // 決済注文可能数量
            result.setStockNumOrderable(api007Res.getPositionSummary().getClosableQuantity());
            // 新規建代金（外貨）（数値(小数)）
            result.setForeignNewPositionAmount(api007Res.getPositionSummary().getFrnPositionAmount());
            // 諸経費合計額（外貨）
            result.setCostForeignLink(api007Res.getPositionSummary().getFrnTotalExpenses());
            // 評価損益（外貨）
            result.setForeignPositionTotalProfitOrLossValuation(
                    api007Res.getPositionSummary().getEvaluationProfitLoss().getFrnEvaluationProfitLoss());
        }
        
        return result;
    }
    
    /**
     * 事前チェック
     * @return true:OK　false:NG
     */
    private boolean checkValidation(boolean chkFct018, String tradeCd, IfaForeignMarginTradeRepayOrderErrorModel error, CustomerCommon cc) {
        
        // FCT018(A012処理時のみ)
        if (chkFct018 && !checkFct018(error)) {
            return false;
        }
        
        // FCT001
        if (!callFCT001(cc.getButenCode(), cc.getAccountNumber(), error)) {
            return false;
        }
        
        // FCT003
        if (!callFCT003(cc.getButenCode(), cc.getAccountNumber(), tradeCd, error)) {
            return false;
        }
        
        //外貨建口座取引開設状況のチェック
        if (StringUtils.equals(cc.getForeignStockTradeAccountOpenStatus(),
                ForeignStockTradeAccountOpenStatus.CLOSED.getId())) {
            error.setErrorCode(ERRORS_FOREIGNSTOCKACCOUNTCHECK);
            error.setErrorMessage(IfaCommonUtil.getMessage(error.getErrorCode()));
            return false;
        }
        
        //外国信用口座開設状況のチェック
        if (StringUtils.equals(cc.getForeignMarginAccountType(), ForeignMarginAccountType.SPOT.getId())) {
            error.setErrorCode(ERRORS_FRS_FOREIGNSTOCKACCOUNT_NOTOPEN_NO1);
            error.setErrorMessage(IfaCommonUtil.getMessage(error.getErrorCode()));
            return false;
        }
        
        return true;
    }
    
    /**
     * FCT001チェック
     * @param butenCode 部店コード
     * @param accountNumber　口座番号
     * @return チェック結果
     */
    private boolean callFCT001(String butenCode, String accountNumber, IfaForeignMarginTradeRepayOrderErrorModel error) {
        
        InputFct001Dto input = new InputFct001Dto();
        input.setAccountNumber(accountNumber);
        input.setButenCode(butenCode);
        
        OutputFct001Dto output = fct001.doCheck(input);
        if (output != null) {
            if (StringUtils.equals(output.getTargetCustomerRefAuthFlag(),
                    TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId())) {
                error.setErrorCode(ERRORS_BUTENACCOUNTNOTEXIST);
                error.setErrorMessage(IfaCommonUtil.getMessage(error.getErrorCode(), new String[] { butenCode, accountNumber }));
                return false;
            }
            if (StringUtils.equals(output.getTradeSuspendFlag(), TradeSuspendFlag.SUSPEND.getId())) {
                error.setErrorCode(ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE);
                error.setErrorMessage(IfaCommonUtil.getMessage(error.getErrorCode()));
                return false;
            }
        }
        return true;
    }
    
    /**
     * FCT003チェック
     * 
     * @param butenCode　部店コード
     * @param accountNumber　口座番号
     * @param tradeCd 取引種別
     * @return チェック結果
     */
    private boolean callFCT003(String butenCode, String accountNumber, String tradeCd, IfaForeignMarginTradeRepayOrderErrorModel error) {
        
        InputFct003Dto input = new InputFct003Dto();
        input.setAccountNumber(accountNumber);
        input.setButenCode(butenCode);
        input.setProductCd(FOREIGNSTOCK);
        input.setCountryCd(NationalityCode.US.getId());
        input.setCurrencyCode(CURRENCY);
        if (!StringUtil.isNullOrEmpty(tradeCd)) {
            input.setTradeCd(tradeCd);
        }
        
        OutputFct003Dto output = fct003.doCheck(input);
        if (output == null
                || StringUtils.equals(MediateAbleTradeFlag.NASHI.getId(), output.getMediateAbleTradeFlag())) {
            error.setErrorCode(ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE);
            error.setErrorMessage(IfaCommonUtil.getMessage(error.getErrorCode(), new String[] {
                    codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_KUBUN) }));
            return false;
        }
        
        return true;
    }
    
    /**
     * FCT018　呼び出す
     * 
     * @param 国コード 
     * @return fct018出力結果
     */
    private boolean checkFct018(IfaForeignMarginTradeRepayOrderErrorModel error) {
        
        InputFct018Dto input = new InputFct018Dto();
        input.setCountryCode(NationalityCode.US.getId());
        OutputFct018Dto outputFct018 = fct018.doCheck(input);
        if (StringUtils.equals(outputFct018.getProcessResult(), RESULT_NG)) {
            error.setErrorCode(ERRORS_FRS_SERVICEHOURS_OUTOFRANGE);
            error.setErrorMessage(IfaCommonUtil.getMessage(error.getErrorCode()));
            return false;
        }
        return true;
    }
    
    /**
     * FCT021
     * 
     * @param tradeCd 取引種別
     * @return fct021出力結果
     */
    private OutputFct021Dto callFCT021(String tradeCd, IfaForeignMarginTradeRepayOrderErrorModel error, CustomerCommon cc) throws Exception {
        
        InputFct021Dto input = new InputFct021Dto();
        input.setAccountNumber(cc.getAccountNumber());
        input.setButenCode(cc.getButenCode());
        input.setTradeCd(tradeCd);
        input.setProductCd(FOREIGNSTOCK);
        input.setCountryCd(NationalityCode.US.getId());
        input.setCurrencyCode(CURRENCY);
        OutputFct021Dto output = fct021.doCheck(input);
        if (output != null) {
            if (StringUtils.equals(output.getNoteInfoErrFlag(), NOTE_INFO_ERR_FLAG)) {
                error.setErrorCode(ERRORS_CMN_NOTICEERRORCHECK);
                error.setErrorMessage(IfaCommonUtil.getMessage(error.getErrorCode(), new String[] {
                        codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_KUBUN) }));
                return null;
            }
            if (StringUtils.equals(output.getNoteLimitErrFlag(), NOTE_LIMIT_ERR_FLAG)) {
                error.setErrorCode(ERRORS_INFORMATIONCHECK);
                error.setErrorMessage(IfaCommonUtil.getMessage(error.getErrorCode()));
                return null;
            }
        }
        
        return output;
    }
    
    /**
     * API001呼び出し処理
     * 
     * @param securitiesCode　銘柄コード
     * @return API出力結果
     * @throws Exception 
     */
    private Api001Response getApi001Response(String securitiesCode) throws Exception {
        
        GetForeignStockSecuritiesResp apiResult = foreignStockService.getForeignStockSecurities(
                NationalityCode.US.getId(),securitiesCode);
        Api001Response result = new Api001Response();
        if (apiResult != null && apiResult.getSecurities() != null) {
            /* 必要な項目のみDtoへ設定する */
            // 銘柄名
            result.setBrandName(apiResult.getSecurities().getSecuritiesName());
            // 銘柄コード 
            result.setBrandCode(apiResult.getSecurities().getSecuritiesCode());
            // 銘柄.ric 
            result.setBrandRic(apiResult.getSecurities().getRic());
            // 市場略名 
            result.setMarketAbbreviatedName(apiResult.getMarket().getMarketShortName());
            // 市場コード 
            result.setMarketCode(apiResult.getMarket().getMarketCode());
            // 国コード 
            result.setCountryCode(apiResult.getMarket().getCountryCode());
            // 取引通貨 
            result.setLimitPrice2(apiResult.getCurrencyCode());
            // タイムゾーン略名 
            result.setTimeZoneAbbreviatedName(apiResult.getMarket().getTimeZoneShortName());
            // 銘柄上場ステータス 
            result.setBrandListedStatus(apiResult.getListedSecuritiesStatus());
            // 取引下限数量 
            result.setTradeLowerLimitQuantity(apiResult.getTradeLimitMin());
            // 取引上限数量 
            result.setTradeUpperLimitQuantity(apiResult.getTradeLimitMax());
            // 取引単位 
            result.setTradingUnit(apiResult.getTradeUnit());
            // 銘柄種別 
            result.setBrandSyubetsu(apiResult.getSecuritiesType());
            // 通貨コード
            result.setCurrencyCode(apiResult.getCurrencyCode());
        }
        
        return result;
    }
    
    /**
     * API002呼び出し処理
     * 
     * @param securitiesCode 銘柄コード
     * @param buySellCode 取引種別
     * @param specificAccountCode 預り区分
     * @return API出力結果
     * @throws Exception 
     */
    private GetForeignStockCreatedMarginOrderInitializationResp getApi002Response(String securitiesCode,
            String buySellCode, String specificAccountCode, CustomerCommon cc) throws Exception {
        
        LOGGER.debug("API GetForeignStockCreatedMarginOrderInitialization Check Start =================== ");
        LOGGER.debug("securitiesCode : {}", securitiesCode);
        LOGGER.debug("buySellCode : {}", buySellCode);
        LOGGER.debug("getApiTradeCd(buySellCode) : {}", getApiTradeCd(buySellCode));
        LOGGER.debug("specificAccountCode : {}", specificAccountCode);
        LOGGER.debug("API GetForeignStockCreatedMarginOrderInitialization Check End ===================== ");
        return foreignStockService.getForeignStockCreatedMarginOrderInitializationNoConvertBuySellCode(
                cc.getButenCode(),
                cc.getAccountNumber(), NationalityCode.US.getId(), securitiesCode, StockTradeType.MARGIN_CLOSE.getId(),
                getApiTradeCd(buySellCode), specificAccountCode);
        
    }
    
    /**
     * API002レスポンス項目設定処理
     * @param apiResult
     * @return
     */
    private Api002Response setApi002ResponseColumns(GetForeignStockCreatedMarginOrderInitializationResp apiResult) {
        
        Api002Response result = new Api002Response();
        
        // 選択可能預り区分リスト 
        result.setSelectAbleDepositTypeList(apiResult.getSpecificAccountCodes());
        // 選択可能決済方法リスト 
        result.setSelectAbleSettlementMethodList(apiResult.getSettlementMethodCodes());
        // 選択可能価格条件リスト 
        result.setSelectAblePriceTermsList(apiResult.getOrderPriceKindCodes());
        // 選択可能信用期日リスト 
        result.setSelectAbleMarginDueDateList(apiResult.getMarginCloseLimitTypes());
        // 選択可能返済建玉指定方法リスト 
        result.setSelectAbleRepayPositionDesignationMethodList(apiResult.getClosePositionKinds());
        // 選択可能返済選択順序リスト 
        result.setSelectAbleRepaySelectSequenceList(apiResult.getCloseSelectionSorts());
        // 選択可能期間条件リスト 
        result.setSelectAblePeriodTermsList(apiResult.getOrderLimitCodes());
        // 有効期間一覧
        result.setValidityPeriodList(apiResult.getOrderTerms());
        
        return result;
        
    }
    
    /**
     * API003呼び出し処理
     * 
     * @param securitiesCode 銘柄コード
     * @return API出力結果
     * @throws Exception 
     */
    private String getApi003Response(String securitiesCode) throws Exception {
        
        GetForeignStockAttentionSecuritiesResp result = foreignStockService
                .getForeignStockAttentionSecurities(NationalityCode.US.getId(), securitiesCode);
        // 注意銘柄のみを返却
        return result.getAttentionSecurities() != null ? result.getAttentionSecurities().toString()
                : StringUtil.EMPTY_STRING;
    }
    
    /**
     * API004呼び出し処理
     * 
     * @param 
     * @return API出力結果
     * @throws Exception 
     */
    private String getApi004Response(CustomerCommon cc) throws Exception {
        
        CreateMarketPriceTicketResp result = foreignInformationService.createMarketPriceTicket(cc.getButenCode(),
                cc.getAccountNumber(), API004_REQUEST_IP_ADDRESS, API004_REQUEST_AGENT, NationalityCode.US.getId());
        
        // 株価チケットのみ返却
        return result != null ? result.getPriceTicket() : StringUtil.EMPTY_STRING;
    }
    
    /**
     * API005呼び出し処理
     * 
     * @param ticket 
     * @return API出力結果
     * @throws Exception 
     */
    private String getApi005Info(String ticket, CustomerCommon cc) throws Exception {
        
        GetMarketPriceHashResp result = foreignInformationService.getMarketPriceHash(cc.getButenCode(),
                cc.getAccountNumber(), ticket, NationalityCode.US.getId());
        return result != null ? result.getHashValue() : StringUtil.EMPTY_STRING;
    }
    
    /**
     * API006から価格基本情報の生成処理
     * 
     * @param hashToken
     * @param rics 
     * @return API出力結果
     * @throws Exception 
     */
    private IfaForeignMarginTradeRepayOrderInputResponseDto_PriceBasicInfo callAPI006(String hashToken, String rics)
            throws Exception {
        
        ListMarketPricesResp result = foreignInformationService.listMarketPrices(hashToken, NationalityCode.US.getId(),
                new String[] { rics });
        
        return getPriceBasicInfoList(result.getMarketPrices());
    }
    
    /**
     * API006結果を参照しResponseDtoに設定する
     * @param marketPrices
     * @return 価格基本情報リスト
     */
    private IfaForeignMarginTradeRepayOrderInputResponseDto_PriceBasicInfo getPriceBasicInfoList(
            List<MarketPrice> marketPrices) {
        
        IfaForeignMarginTradeRepayOrderInputResponseDto_PriceBasicInfo basic = new IfaForeignMarginTradeRepayOrderInputResponseDto_PriceBasicInfo();
        // 0件の場合は何も設定しないで返却
        if (CollectionUtils.isEmpty(marketPrices)) {
            return basic;
        }
        // 現在値
        basic.setCurrentPrice(marketPrices.get(0).getPrice().getLast());
        // 現在値日時
        basic.setCurrentDateTime(marketPrices.get(0).getPrice().getLastDatetime());
        // ティック矢印(アップorダウン)
        basic.setTickArrow(marketPrices.get(0).getPrice().getTickArrow());
        // 前日比
        basic.setDiff(marketPrices.get(0).getPrice().getChange());
        // 前日比(%)
        basic.setDiffPercentage(marketPrices.get(0).getPrice().getChangePercent());
        // 始値
        basic.setStart(marketPrices.get(0).getPrice().getOpen());
        // 高値
        basic.setHigh(marketPrices.get(0).getPrice().getHigh());
        // 安値
        basic.setLow(marketPrices.get(0).getPrice().getLow());
        // 出来高
        basic.setVolume(marketPrices.get(0).getPrice().getVolume());
        // 前日終値
        basic.setLast(marketPrices.get(0).getPrice().getPrevClose());
        // 前日終値日付
        basic.setLastDate(marketPrices.get(0).getPrice().getPrevCloseDate());
        
        return basic;
    }
    
    /**
     * API007呼び出し処理
     * 
     * @param ticket
     * @param securitiesCode
     * @param buySellCode
     * @param marginCloseLimitType
     * @param specificAccountCode
     * @return API出力結果
     * @throws Exception 
     */
    private GetMarginPositionSummaryResp getApi007Response(String ticket, String securitiesCode, String buySellCode,
            String marginCloseLimitType, String specificAccountCode, CustomerCommon cc) throws Exception {
        
        return foreignAccountService.getMarginPositionSummary(cc.getButenCode(), cc.getAccountNumber(),
                NationalityCode.US.getId(), securitiesCode, buySellCode, marginCloseLimitType,
                specificAccountCode,
                null, null,ticket);
    }
    
    /**
     * API008呼び出し処理
     * 
     * @param 
     * @param  
     * @return API出力結果
     * @throws Exception 
     */
    private ConfirmForeignStockClosedMarginOrderResp callApi008(
            IfaForeignMarginTradeRepayOrderInputA012RequestDto dtoReq, List<Position> api007ResList, CustomerCommon cc) throws Exception {
        
        MarginOrderInputForConfirmForeignStockClosedMarginOrder  marginOrder = new MarginOrderInputForConfirmForeignStockClosedMarginOrder();
        //国コード
        marginOrder.setCountryCode(NationalityCode.US.getId());
        //市場コード
        marginOrder.setMarketCode(dtoReq.getMarketCode());
        //銘柄コード
        marginOrder.setSecuritiesCode(dtoReq.getBrandCode());
        //売買区分
        marginOrder.setBuySellCode(getApiTradeCd(dtoReq.getTradeCd()));
        //注文数量
        marginOrder.setOrderQuantity(getOrderQuantityApi008(dtoReq));
        //価格条件
        marginOrder.setOrderPriceKindCode(dtoReq.getOrderPriceKindList());
        //注文単価
        marginOrder.setOrderPrice(getOrderPriceApi008(dtoReq));
        //発火条件価格
        marginOrder.setStopPrice(getStopOrderPrice2Api008(dtoReq));
        //期間条件
        marginOrder.setOrderLimitCode(dtoReq.getPeriodRadio());
        //期間
        marginOrder.setOrderTerm(getPeriodDateApi008(dtoReq));
        //預り区分
        marginOrder.setSpecificAccountCode(dtoReq.getDepositType());
        //決済方法 "FOREIGN_SETTLEMENT"（外貨決済）
        marginOrder.setSettlementMethodCode(SettlementMethod.FOREIGN_SETTLEMENT.getId());
        //返済建玉指定方法
        marginOrder.setClosePositionKind(dtoReq.getRepayPositionDesignateMethod());
        //返済選択順序
        marginOrder.setCloseSelectionSort(dtoReq.getRepaySelectOrder());
        
        List<ClosedPositionInput> inputPositionList = new ArrayList<ClosedPositionInput>();
        if (dtoReq.getPositionDesignationAreaIndividualPositionInfoList() != null) {
            // 外部コードに変換（サービス固有の変換）
            dtoReq.getPositionDesignationAreaIndividualPositionInfoList().stream()
                    .filter(f -> isNotEmptyOrderCount(f.getOrderCount())).forEach(individual -> {
                        
                        Position apiinfo = null;
                        for (Position api007Res : api007ResList) {
                            if (StringUtils.equals(api007Res.getTradeDate(), individual.getDomesticTradeDate())
                                    && StringUtils.equals(api007Res.getFrnPositionPrice(),
                                            individual.getSinyoPreviousDayValue())) {
                                apiinfo = api007Res;
                                break;
                            }
                        }
                        if (apiinfo != null) {
                            ClosedPositionInput input = new ClosedPositionInput();
                            //決済相手建玉明細情報.返済対象建玉の建区分
                            input.setBuySellCode(dtoReq.getTrade());
                            //決済相手建玉明細情報.返済期限
                            input.setMarginCloseLimitType(dtoReq.getMarginDueDate());
                            //決済相手建玉明細情報.預り区分
                            input.setSpecificAccountCode(dtoReq.getDepositType());
                            //決済相手建玉明細情報.国内新規約定日
                            input.setTradeDate(
                                    apiinfo != null ? apiinfo.getTradeDate() : individual.getDomesticTradeDate());
                            //決済相手建玉明細情報.現地新規約定日
                            input.setFrnTradeDate(
                                    apiinfo != null ? apiinfo.getFrnTradeDate() : individual.getForeignTradeDate());
                            //決済相手建玉明細情報.建単価
                            input.setPositionPrice(individual.getSinyoPreviousDayValue());
                            //決済相手建玉明細情報.数量
                            input.setPositionQuantity(
                                    apiinfo != null ? apiinfo.getQuantity() : individual.getQuantity());
                            //決済相手建玉明細情報.注文数量
                            input.setCloseOrderQuantity(individual.getOrderCount());
                            inputPositionList.add(input);
                        }
                    });
        }
        
        return foreignStockService.confirmForeignStockClosedMarginOrder(cc.getButenCode(), cc.getAccountNumber(), null,
                null, API008_REQUEST_CHANNEL, marginOrder, inputPositionList);
    }
    
    /**
     * 対象建玉判定
     * 
     * @param orderCount
     * @return true:対象　false：対象外
     */
    private boolean isNotEmptyOrderCount(String orderCount) {
        
        String checkValue = StringUtil.trim(orderCount);
        
        return !StringUtil.isNullOrEmpty(checkValue) && !StringUtils.equals(NOT_TARGET_VALUE_ORDER_COUNT, checkValue);
        
    }
    
    /**
     * API008リクエスト項目:注文数量設定
     * @param dtoReq　A012リクエストDTO
     * @return
     */
    private String getOrderQuantityApi008(IfaForeignMarginTradeRepayOrderInputA012RequestDto dtoReq) {
        
        String orderQuantity = null;
        //個別指定の場合：注文数量の合計
        if (StringUtils.equals(dtoReq.getRepayPositionDesignateMethod(), ClosePositionKind.INDIVIDUAL.getIfaCd())) {
            orderQuantity = dtoReq.getTotal();
        }
        //一括指定の場合：返済株数
        else if (StringUtils.equals(dtoReq.getRepayPositionDesignateMethod(),
                ClosePositionKind.COLLECTIVE.getIfaCd())) {
            orderQuantity = dtoReq.getCloseOrderQuantity();
        }
        return orderQuantity;
        
    }
    
    /**
     * API008リクエスト項目:注文単価設定
     * @param dtoReq　A012リクエストDTO
     * @return
     */
    private String getOrderPriceApi008(IfaForeignMarginTradeRepayOrderInputA012RequestDto dtoReq) {
        
        String orderPrice = null;
        //価格条件が「指値」の場合：注文単価（指値）
        if (StringUtils.equals(dtoReq.getOrderPriceKindList(), OrderPriceKind.LIMIT.getIfaCd())) {
            orderPrice = dtoReq.getLimitPrice2();
        }
        //価格条件が「逆指値/指値」の場合：注文単価（逆指値）
        else if (StringUtils.equals(dtoReq.getOrderPriceKindList(), OrderPriceKind.STOP_PRICE_LIMIT.getIfaCd())) {
            orderPrice = dtoReq.getStopOrderExecutePrice2();
        }
        return orderPrice;
    }
    
    /**
     * API008リクエスト項目:発火条件価格設定
     * @param dtoReq　A012リクエストDTO
     * @return
     */
    private String getStopOrderPrice2Api008(IfaForeignMarginTradeRepayOrderInputA012RequestDto dtoReq) {
        
        String stopOrderPrice2 = null;
        //価格条件が「逆指値」の場合：発火条件価格（逆指値）
        if (StringUtils.equals(dtoReq.getOrderPriceKindList(),
                SelectAblePriceConditions.STOP_ORDER_EXECUTE_PRICE.getId())
                || StringUtils.equals(dtoReq.getOrderPriceKindList(),
                        SelectAblePriceConditions.STOP_MARKET_ORDER_EXECUTE_PRICE.getId())) {
            stopOrderPrice2 = dtoReq.getStopOrderPrice();
        }
        return stopOrderPrice2;
        
    }
    
    /**
     * API008リクエスト項目:期間
     * @param dtoReq　A012リクエストDTO
     * @return
     * @throws Exception 
     */
    private String getPeriodDateApi008(IfaForeignMarginTradeRepayOrderInputA012RequestDto dtoReq) throws Exception {
        
        String periodDate = null;
        //期間条件が「期間指定」の場合のみ
        if (StringUtils.equals(dtoReq.getPeriodRadio(), PeriodConditions.PERIOD_DESIGNATION.getId())) {
            periodDate = DateFormatUtil.dateFormatToHyphen(dtoReq.getPeriod());
        }
        return periodDate;
        
    }
        
    /**
     * 別クラスの同名フィールドをコピーする
     * (BeanUtils.copyPropertiesが機能しないため)
     * @param source コピー元のインスタンス
     * @param target コピー先のインスタンス
     */
    private static void copyFields(Object source, Object target) {
        
        final String prefixSet = "set";
        final String prefixGet = "get";
        
        Set<String> methodSet = new HashSet<String>();
        for (Method method : target.getClass().getMethods())
            if (method.getName().startsWith(prefixSet))
                methodSet.add(method.getName().substring(prefixSet.length()));
            
        try {
            for (Method method : source.getClass().getMethods()) {
                if (!method.getName().startsWith(prefixGet))
                    continue;
                String curName = method.getName().substring(prefixGet.length());
                if (methodSet.contains(curName)) {
                    Method newMethod = target.getClass().getMethod(prefixSet + curName, method.getReturnType());
                    newMethod.invoke(target, method.invoke(source));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
