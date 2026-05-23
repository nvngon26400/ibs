package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.ifa.ForeignInformationService;
import com.sbisec.helios.ap.athena.ifa.ForeignStockService;
import com.sbisec.helios.ap.athena.protocol.account.GetMarginPositionSummaryResp;
import com.sbisec.helios.ap.athena.protocol.account.dto.Position;
import com.sbisec.helios.ap.athena.protocol.common.PriceData;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.ClosedPositionInput;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginOrder;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginTradeRepayOrderConfirmForCloseForeignStockMarginOrder;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.Order;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CloseForeignStockMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockCreatedMarginOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockAttentionSecuritiesResp;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockSecuritiesResp;
import com.sbisec.helios.ap.athena.protocol.information.CreateMarketPriceTicketResp;
import com.sbisec.helios.ap.athena.protocol.information.GetMarketPriceHashResp;
import com.sbisec.helios.ap.athena.protocol.information.ListMarketPricesResp;
import com.sbisec.helios.ap.athena.protocol.information.dto.MarketPrice;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
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
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaForeignMarginTradeRepayOrderConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeRepayOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeRepayOrderConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeRepayOrderConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeRepayOrderConfirmSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA004ResponseDto_priceBasicInfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA010RequestDto_positionDesignationAreaIndividualPositionInfoList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA010aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA010aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA010bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA010bResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderErrorModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaForeignMarginTradeRepayOrderConfirmService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ForeignMarginAccountType;
import com.sbisec.helios.ap.common.enums.ForeignRepayMethod;
import com.sbisec.helios.ap.common.enums.ForeignStockTradeAccountOpenStatus;
import com.sbisec.helios.ap.common.enums.ForeignStockTradeClass;
import com.sbisec.helios.ap.common.enums.ImportantNotificationConfirm;
import com.sbisec.helios.ap.common.enums.MediateAbleTradeFlag;
import com.sbisec.helios.ap.common.enums.NationalityCode;
import com.sbisec.helios.ap.common.enums.NoticeInfoConfirm;
import com.sbisec.helios.ap.common.enums.SelectAblePriceConditions;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.enums.TradeSuspendFlag;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import lombok.Data;

/**
 * 画面ID：SUB0202_0303-04_2
 * 画面名：米株信用取引返済注文確認
 * 2023/09/07 新規作成
 *
 * @author SCSK
 */
@Component(value = "cmpIfaForeignMarginTradeRepayOrderConfirmService")
public class IfaForeignMarginTradeRepayOrderConfirmServiceImpL
        implements IfaForeignMarginTradeRepayOrderConfirmService {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaForeignMarginTradeRepayOrderConfirmServiceImpL.class);
    
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
    
    /** 当該顧客の{0}に関わる重要な注意情報があるため処理を進めることができません。注意情報欄をご確認ください。
        {0}：区分.対象取引（メッセージ表示用）（区分値：5　＠表示パターン：1　） */
    private static final String ERRORS_CMN_NOTICEERRORCHECK = "errors.cmn.noticeErrorCheck";
    
    /** 未確認の「重要なお知らせ」があります。 */
    private static final String ERRORS_INFORMATIONCHECK = "errors.informationCheck";
    
    /** 期間指定が注文可能期間を超過しています。 */
    private static final String ERRORS_FRS_ORDER_TERMS_OUT_OF_RANGE = "errors.frs.orderTerms.outOfRange";
    
    /** {0}が下限、上限の範囲にありません。[{1}～{2}] */
    private static final String ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE = "errors.frs.orderValue.outOfRange";
    
    /** {0}が{1}ではありません。[{2}] */
    private static final String ERROR_FRS_ORDERING_CONDITION_INCONSISTENT = "errors.frs.orderingCondition.inconsistent";
    
    /** 指定された建玉が最新の建玉明細一覧に存在するチェックエラー ：　指定された建玉がありません */
    private static final String ERROR_FRS_ORDER_ABLE_POSITION_NOT_FOUND = "errors.frs.orderablePosition.notFound";
    
    /** 確認が必要なアラートが新たに発生しました。注文入力画面に戻り再度注文確認を行ってください。　*/
    private static final String ERROR_CMN_INFORMATION_OCCURS = "errors.cmn.information.occurs";
    
    /** DB登録エラー ：　注文発注前の注文データが登録できないため、注文しませんでした。　*/
    private static final String ERROR_FRS_PRE_ORDER_EXECUTION_FAILED = "errors.frs.preOrderExecution.failed";
    
    /** DB更新エラー ：　注文発注後の注文データが更新できませんでした。注文は完了しています。　*/
    private static final String WARNINGS_FRS_POST_ORDER_EXECUTION_COMPLETED = "warnings.frs.postOrderExecution.completed";
    
    /** 注文処理でエラーが発生しました。(エラーコード：{0}、エラーメッセージ{1})　*/
    private static final String ERROR_CMN_ORDER_EXECUTION_FAILED = "errors.cmn.orderExecution.failed";
    
    /** 米株信用取引の利用者権限がありません。 */
    private static final String ERRORS_FRS_ORDER_EXECUTION_INSUFFICIENT_PRIVILEGE__MARGIN_TRADING = "errors.frs.orderExecution.insufficientPrivilege.MarginTrading";
    
    /** 対象外エラー */
    private static final String ERROR_UNTARGETED = "対象外エラー";
    
    /** エラーメッセージ引数：0 */
    private static final String ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE_ATTR_0 = "0";
    
    /** エラーメッセージ */
    private static final String ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE_0_1 = "返済株数";
    
    /** エラーメッセージ */
    private static final String ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE_0_2 = "注文数量の合計";
    
    /** エラーメッセージ */
    private static final String ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE_0_3 = "注文数量";
    
    /** エラーメッセージ */
    private static final String ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE_0_4 = "注文単価（指値）";
    
    /** エラーメッセージ */
    private static final String ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE_0_5 = "参照価格（逆指値）";
    
    /** エラーメッセージ */
    private static final String ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE_0_6 = "注文単価（逆指値）";
    
    /** エラーメッセージ */
    private static final String ERROR_FRS_ORDERING_CONDITION_INCONSISTENT_0_0 = "返済株数";
    
    /** エラーメッセージ */
    private static final String ERROR_FRS_ORDERING_CONDITION_INCONSISTENT_1_0 = "取引単位";
    
    /** エラーメッセージ */
    private static final String ERROR_FRS_ORDERING_CONDITION_INCONSISTENT_0_1 = "注文数量";
    
    /** エラーメッセージ */
    private static final String ERROR_FRS_ORDERING_CONDITION_INCONSISTENT_1_1 = "取引単位";
    
    // --------------------------------
    // 設定値
    // --------------------------------      
    /** HTTP正常終了 */
    private static final String HTTP_SUCCESS = "200"; 
    
    /** FCT:証券金銭種別 設定値　（外国株式） */
    private static final String FCT_FOREIGNSTOCK = "15";
    
    /** FCT:通貨コード　"999"　設定値*/
    private static final String FCT_CURRENCY_CODE = "999";
    
    /** FCT018: 判定結果：NG*/
    private static final String RESULT_NG = "NG";
    
    /** API007:ipアドレス */
    private static final String API004_REQUEST_IP_ADDRESS = "127.0.0.1";
    
    /** API007:X-App-User-Agent */
    private static final String API004_REQUEST_AGENT = "Edge";
    
    /** 区分定義.ドメインID_対象取引 */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 対象取引 区分値：5 */
    private static final String MSG_DISPLAY_TARGET_TRADE_KUBUN = "5";
    
    /** 売買区分　： BUY */
    private static final String BUY_SELL_CODE_BUY = "BUY";
    
    /** 売買区分　： SELL */
    private static final String BUY_SELL_CODE_SELL = "SELL";
    
    /** 注文単価　：　 999999999.99 */
    private static final Double ORDER_PRICE_HIGH = 999999999.99;
    
    /** 取引上限数量(上限値0時の値) */
    private static final Long TRADE_LIMIT_MAX_UNLIMITED = 9999999999L;
    
    /** 注文単価　：　 0 */
    private static final Double ORDER_PRICE_LOW = 0.00;
    
    /** 期間条件　： 1 -> 期間指定 
     * 区分名: 期間条件
     * 0 -> 当日中
     * 1 -> 期間指定
     */
    private static final String PERIOD_CONDITION_1 = "1";
    
    private static final String PERIOD_CONDITION_0 = "0";
    
    /** 株取引区分　： MARGIN_CLOSE -> 信用返済 */
    private static final String STOCK_TRADE_TYPE_MARGIN_CLOSE = "MARGIN_CLOSE";
    
    /** 取引注意情報（銘柄）確認　： 1 -> チェックON */
    private static final String TRADING_CAUTION_INFORMATION_ON = "1";
    
    /** チャンネル　： PHONE -> 電話 */
    private static final String CHANNEL_PHONE = "PHONE";
    
    /** エラーレベル　： -1 -> FATAL ("致命的") */
    private static final Integer ERROR_LEVEL_FATAL = -1;
    
    /** IFA注文サブ番号　： 1 -> 新規注文 */
    private static final String IFA_ORDER_SUB_NUMBER_1 = "1";
    
    /** 仕法区分　： 11 -> 信用返済
     * 0 -> 現物 
     * 10 -> 信用新規 
     * 11 -> 信用返済
     */
    private static final String METHOD_TYPE_11 = "11";
    
    /** 注文日時：初期値 */
    private static final String ORDER_DATE_DEFAULT = "19000101";
    private static final String ORDER_DATE_TIME_DEFAULT = "1900-01-01 00:00:00";
    
    
    /** 建単価　： 0  */
    private static final String POSITION_PRICE_0 = "0";
    
    /** SBI証券本店（権限1）、SBI証券支店（権限2） */
    private static final List<String> PRIV_SBI_AUTH_LIST = Arrays.asList(new String[] { "1", "2" });
    
    /** 注文種別区分 ：　0 -> 新規（発注）
     * 0 -> 新規（発注） 
     * 1 -> 訂正 
     * 2 -> 取消
     */
    private static final String SECURITY_CLASS_TYPE_0 = "0";
    
    /** 対象外建玉数量 */
    private static final String NOT_TARGET_VALUE_ORDER_COUNT = "0";
    
    @Autowired
    private IfaForeignMarginTradeRepayOrderConfirmDao dao;
    
    /**
     * 区分値取得クラス
     */
    @Autowired
    private CodeListService codeListService;
    
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
    
    @Autowired
    private ForeignInformationService foreignInformationService;
    
    @Autowired
    private ForeignStockService foreignStockService;
    
    @Autowired
    private ForeignAccountService foreignAccountService;
    
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
     * APIエラー時のDTOクラス
     * @author 松田
     *
     */
    @Data
    private class ApiStatusDto {
        
        // APIステータスコード
        private String apiStatusCode;
        
        // APIエラーコード
        private String apiErrorCode;
        
        // APIメッセージ
        private String apiMessage;
    }
    
    /**
     * アクションID：A004
     * アクション名：更新
     * Dto リクエスト：IfaForeignMarginTradeRepayOrderConfirmA004RequestDto
     * Dto レスポンス：IfaForeignMarginTradeRepayOrderConfirmA004ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq A004のリクエスト
     * @return DataList A004のレスポンス
     * @exception Exception エクセプション
     */
    public DataList<IfaForeignMarginTradeRepayOrderConfirmA004ResponseDto> updateA004(
            IfaForeignMarginTradeRepayOrderConfirmA004RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginTradeRepayOrderConfirmServiceImpL.updateA004");
        }
        List<IfaForeignMarginTradeRepayOrderConfirmA004ResponseDto> resDtoList = new ArrayList<IfaForeignMarginTradeRepayOrderConfirmA004ResponseDto>();
        
        IfaForeignMarginTradeRepayOrderErrorModel error = new IfaForeignMarginTradeRepayOrderErrorModel();

        // 共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // ユーザアカウント情報の取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        
        // バリデーションチェック
        if (!checkValidation(false, dtoReq.getTradeCd(), error, cc, ua)) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
        }
        
        // 銘柄情報を取得
        GetForeignStockSecuritiesResp respApi001 = null;
        try {
            respApi001 = doApi001(dtoReq.getBrandCode());            
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        responseNullCheck(respApi001);
        responseNullCheck(respApi001.getSecurities());
        // 株価チケット
        CreateMarketPriceTicketResp respApi007 = null;
        try {
            respApi007 = doApi007(cc);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        responseNullCheck(respApi007);
        // ハッシュ結果
        GetMarketPriceHashResp respApi008 = null;
        try {
            respApi008 = doApi008(respApi007.getPriceTicket(), cc);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        responseNullCheck(respApi008);
        
        ListMarketPricesResp respApi003 = null;
        try {
            respApi003 = doApi003(respApi008.getHashValue(), respApi001.getSecurities().getRic());
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        responseNullCheck(respApi003);
        IfaForeignMarginTradeRepayOrderConfirmA004ResponseDto response = new IfaForeignMarginTradeRepayOrderConfirmA004ResponseDto();
        // ⑥画面を更新する。
        if (!CollectionUtils.isEmpty(respApi003.getMarketPrices())) {
            MarketPrice marketPrice = respApi003.getMarketPrices().get(0);
            IfaForeignMarginTradeRepayOrderConfirmA004ResponseDto_priceBasicInfo dto = new IfaForeignMarginTradeRepayOrderConfirmA004ResponseDto_priceBasicInfo();
            PriceData price = marketPrice.getPrice();
            dto.setCurrentPrice(price.getLast());
            dto.setCurrentDateTime(price.getLastDatetime());
            dto.setDiff(price.getChange());
            dto.setDiffPercentage(price.getChangePercent());
            dto.setStart(price.getOpen());
            dto.setHigh(price.getHigh());
            dto.setLow(price.getLow());
            dto.setVolume(price.getVolume());
            dto.setLast(price.getPrevClose());
            dto.setLastDate(price.getPrevCloseDate());
            dto.setTickArrow(price.getTickArrow());
            response.setPriceBasicInfo(dto);
            resDtoList.add(response);
        }
        
        return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(),
                null);
    }
    
    /**
     * アクションID：A010a
     * アクション名：注文発注
     * Dto リクエスト：IfaForeignMarginTradeRepayOrderConfirmA010aRequestDto
     * Dto レスポンス：IfaForeignMarginTradeRepayOrderConfirmA010aResponseDto
     * model リクエスト：IfaForeignMarginTradeRepayOrderConfirmSql001RequestModel
     * model リクエスト：IfaForeignMarginTradeRepayOrderConfirmSql002RequestModel
     *
     * @param dtoReq A010aリクエスト
     * @return DataList A010aレスポンス
     * @exception Exception エクセプション
     */
    public DataList<IfaForeignMarginTradeRepayOrderConfirmA010aResponseDto> orderPlacementA010a(
            IfaForeignMarginTradeRepayOrderConfirmA010aRequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginTradeRepayOrderConfirmServiceImplL.orderPlacementA010a");
        }
        DataList<IfaForeignMarginTradeRepayOrderConfirmA010aResponseDto> dtoRes = new DataList<IfaForeignMarginTradeRepayOrderConfirmA010aResponseDto>();
        List<IfaForeignMarginTradeRepayOrderConfirmA010aResponseDto> resDtoList = new ArrayList<IfaForeignMarginTradeRepayOrderConfirmA010aResponseDto>();
        IfaForeignMarginTradeRepayOrderConfirmA010aResponseDto response = new IfaForeignMarginTradeRepayOrderConfirmA010aResponseDto();

        IfaForeignMarginTradeRepayOrderErrorModel error = new IfaForeignMarginTradeRepayOrderErrorModel();

        // 共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // ユーザアカウント情報の取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
                
        // 返済建玉指定方法:一括指定
        boolean isBatch = false;
        // 返済建玉指定方法:個別指定
        boolean isIndividual = false;

        if (!StringUtil.isNullOrEmpty(dtoReq.getRepayPositionDesignateMethod())) {
            isBatch = StringUtils.equals(dtoReq.getRepayPositionDesignateMethod(), ForeignRepayMethod.BATCH.getId());
            isIndividual = StringUtils.equals(dtoReq.getRepayPositionDesignateMethod(), ForeignRepayMethod.INDIVIDUAL.getId());
        }

        try {
            
            // バリデーションチェック
            if (!checkValidation(true, dtoReq.getTradeCd(), error, cc, ua)) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
            }
            // API001処理：銘柄情報を取得する。 */
            GetForeignStockSecuritiesResp respApi001 = null;
            try {
                respApi001 = doApi001(dtoReq.getBrandCode());
            } catch (Exception e) {
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
            responseNullCheck(respApi001);
            // レスポンスをセットする
            fillFromApi001Response(response, respApi001);
            // 外国株式信用建玉明細一覧の決済注文可能数量を取得する。 */
            GetMarginPositionSummaryResp respApi006 = null;
            try {
                respApi006 = doApi006(dtoReq, cc);
            } catch (Exception e) {
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
            responseNullCheck(respApi006);
            // 銘柄種別をセット
            response.setBrandClass(respApi001.getSecuritiesType());
            
            boolean checkOrderQuantity = true;
            if (isBatch) {
                // 一括処理向けチェック処理
                checkOrderQuantity = checkBatchQuantity(dtoReq.getOrderCount(), respApi001, respApi006, error);
            } else if (isIndividual) {
                // 個別処理向けチェック処理
                checkOrderQuantity = checkIndividualQuantity(dtoReq.getOrderCount(), respApi006.getPositions(),
                        dtoReq.getPositionDesignationAreaIndividualPositionInfoList(), respApi001, respApi006, error);
            }
            
            if (!checkOrderQuantity) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
            }
            
            // ⑨"注文単価（指値）が、0以上　999999999.99以下の範囲にあることをチェックする。 */
            if (!StringUtil.isNullOrEmpty(dtoReq.getHiddenOrderPrice())) {
            // 　※0　≦　注文単価　≦　999999999.99" */
                Double hiddenOrderPrice = Double.valueOf(dtoReq.getHiddenOrderPrice());
                if (!(hiddenOrderPrice >= ORDER_PRICE_LOW && hiddenOrderPrice <= ORDER_PRICE_HIGH)) {
                    // エラーあり：エラーを返す。 */
                    String messageAttr = StringUtil.EMPTY_STRING;
                    if (StringUtils.equals(dtoReq.getOrderPriceKindList(),
                            SelectAblePriceConditions.PRICE_LIMIT.getId())) {
                        messageAttr = ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE_0_4;
                    } else if (StringUtils.equals(dtoReq.getOrderPriceKindList(),
                            SelectAblePriceConditions.STOP_ORDER_EXECUTE_PRICE.getId())) {
                        messageAttr = ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE_0_6;
                    }
                    
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE,
                            IfaCommonUtil.getMessage(ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE, new String[] { messageAttr,
                                    String.valueOf(ORDER_PRICE_LOW), String.valueOf(ORDER_PRICE_HIGH) }));
                    return dtoRes;
                }
            }
            // エラーなし：次の処理へ */
            if (!StringUtil.isNullOrEmpty(dtoReq.getStopOrderPrice2())) {
                // ⑩"発火条件価格（逆指値）が、0以上　999999999.99以下の範囲にあることをチェックする。 */
                // 　※0　≦　注文単価　≦　999999999.99" */
                Double stopOrderPrice = Double.valueOf(dtoReq.getStopOrderPrice2());
                if (!(stopOrderPrice >= ORDER_PRICE_LOW && stopOrderPrice <= ORDER_PRICE_HIGH)) {
                    // エラーあり：エラーを返す。 */
                    dtoRes = IfaCommonUtil
                            .createDataList(resDtoList, ErrorLevel.FATAL, ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE,
                                    IfaCommonUtil.getMessage(ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE,
                                            new String[] { ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE_0_5,
                                                    String.valueOf(ORDER_PRICE_LOW),
                                                    String.valueOf(ORDER_PRICE_HIGH) }));
                    return dtoRes;
                }
            }
            
            // エラーなし：次の処理へ */
            // ⑫期間条件が「期間指定」の場合、日付が指定可能期間内であることをチェックする。 */
            if (PERIOD_CONDITION_1.equals(dtoReq.getPeriodRadio())) {
                try {                    
                    if (checkTerm(dtoReq, respApi006, cc)) {
                        // エラーあり：エラーを返す。 */
                        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                                ERRORS_FRS_ORDER_TERMS_OUT_OF_RANGE,
                                IfaCommonUtil.getMessage(ERRORS_FRS_ORDER_TERMS_OUT_OF_RANGE));
                        return dtoRes;
                    }
                } catch (Exception e) {
                    return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                            ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
                }
                // エラーなし：次の処理へ */
            }
            // ⑬取引制限チェックを行う */
            // fct021
            if (!checkFct021(dtoReq.getTradeCd(), dtoReq.getNoteInfoCheckbox(), dtoReq.getNoteLimitCheck(), error, cc)) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
            }
            
            // 上記チェックを実施したら、次の処理へ。 */
            // ⑭注意銘柄を取得する。 */
            GetForeignStockAttentionSecuritiesResp respApi002 = null;
            try {
                respApi002 = doApi002(dtoReq);
            } catch (Exception e) {
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
            responseNullCheck(respApi002);
            // レスポンスをセットする
            fillFromApi002Response(response, respApi002);
            if (respApi002.getAttentionSecurities()) {
                // 注意銘柄=true　の場合 */
                String tradingCautionInformation = dtoReq.getTradingCautionInformation();
                if (!StringUtils.equals(tradingCautionInformation, TRADING_CAUTION_INFORMATION_ON)) {
                    // 取引注意情報（銘柄）確認チェックボックス=OFF　または　非表示：エラーを返す。 */
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERROR_CMN_INFORMATION_OCCURS,
                            IfaCommonUtil.getMessage(ERROR_CMN_INFORMATION_OCCURS));
                    return dtoRes;
                }
                // 取引注意情報（銘柄）確認チェックボックス=ON：次の処理へ */
            }
            IfaForeignMarginTradeRepayOrderConfirmSql004ResponseModel respSql004 = doSql004();
            responseNullCheck(respSql004);
            // レスポンスをセットする
            fillFromSql004Response(response, respSql004);
            String ifaOrderNo = respSql004.getIfaOrderNo();
            // ⑮発注前に注文内容をDBに登録する。 */
            try {
                // 注文データをDBに登録する。 */
                int insSql001Res = doSql001(dtoReq, ifaOrderNo, cc, ua);
                if (0 == insSql001Res) {
                    throw new BusinessErrorNoticeException();
                }
            } catch (Exception e) {
                // DB登録NG：DB登録エラーを返す。 */
                LOGGER.error(
                        "IfaForeignMarginTradeRepayOrderConfirmServiceImpL.insertOrderInfo dao.insertIfaForeignMarginTradeRepayOrderConfirmSql001 Exception[{}]",
                        e.getMessage());
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        Integer.toString(ErrorLevel.FATAL.getId()),
                        IfaCommonUtil.getMessage(ERROR_FRS_PRE_ORDER_EXECUTION_FAILED));
                return dtoRes;
            }
            // DB登録OK：次の処理へ: */
            // ⑯発注前に個別建玉情報をDBに登録する。 */
            try {
                // 個別建玉情報をDBに登録する。 */
                int insSql002Res = 0;
                int responseCount = 0;
                if (isBatch) {
                    insSql002Res = doSql002Batch(dtoReq, ifaOrderNo, cc, ua);
                    responseCount = 1;
                } else if (isIndividual) {
                    // 注文数量が入力されている建玉情報のみを登録する
                    List<IfaForeignMarginTradeRepayOrderConfirmA010RequestDto_positionDesignationAreaIndividualPositionInfoList> insertList = dtoReq
                            .getPositionDesignationAreaIndividualPositionInfoList().stream()
                            .filter(p -> isNotEmptyOrderCount(p.getOrderCount())).collect(Collectors.toList());
                    insSql002Res = doSql002Individual(dtoReq, insertList, ifaOrderNo, cc, ua);
                    responseCount = insertList.size();
                }
                
                if (responseCount != insSql002Res) {
                    throw new BusinessErrorNoticeException();
                }
            } catch (Exception e) {
                // DB登録NG：DB登録エラーを返す。*/
                LOGGER.error(
                        "IfaForeignMarginTradeRepayOrderConfirmServiceImpL.insertPositionInfo dao.insertIfaForeignMarginTradeRepayOrderConfirmSql002 Exception[{}]",
                        e.getMessage());
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        Integer.toString(ErrorLevel.FATAL.getId()),
                        IfaCommonUtil.getMessage(ERROR_FRS_PRE_ORDER_EXECUTION_FAILED));
                return dtoRes;
            }
        } catch (BusinessErrorNoticeException e) {
            dtoRes.setErrorLevel(ERROR_LEVEL_FATAL);
            return dtoRes;
        }
        
        resDtoList.add(response);
        
        return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS,
                Integer.toString(ErrorLevel.SUCCESS.getId()), null);
    }
    
    /**
     * 個別指定時の注文数量チェック
     * 
     * @param orderCount 
     * @param positions
     * @param positionDesignationAreaIndividualPositionInfoList
     * @param respApi001
     * @return true:OK false:NG
     */
    private boolean checkIndividualQuantity(String orderCount, List<Position> positions,
            List<IfaForeignMarginTradeRepayOrderConfirmA010RequestDto_positionDesignationAreaIndividualPositionInfoList> positionDesignationAreaIndividualPositionInfoList,
            GetForeignStockSecuritiesResp respApi001, GetMarginPositionSummaryResp respApi006, IfaForeignMarginTradeRepayOrderErrorModel error) {
        
        Long orderQuantity = Long.valueOf(orderCount);
        Long tradeLimitMin = Long.valueOf(respApi001.getTradeLimitMin());
        Long tradeLimitMax = Long.valueOf(respApi001.getTradeLimitMax());
        // 0の場合は9999999999として扱う
        if (Long.valueOf(0).equals(tradeLimitMax)) {
            tradeLimitMax = TRADE_LIMIT_MAX_UNLIMITED;
        }
        if (orderQuantity < tradeLimitMin || orderQuantity > tradeLimitMax) {
            // エラーあり：エラーを返す */
            error.setErrorCode(ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE);
            error.setErrorMessage(IfaCommonUtil.getMessage(ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE,
                    new String[] { ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE_0_2, respApi001.getTradeLimitMin(),
                            tradeLimitMax.toString() }));
            return false;
            
        }
        // エラーなし：次の処理へ */
        
        if (null == respApi006.getPositionSummary().getSecurities()) {
            // エラーあり：エラーを返す */
            error.setErrorCode(ERROR_FRS_ORDER_ABLE_POSITION_NOT_FOUND);
            error.setErrorMessage(IfaCommonUtil.getMessage(ERROR_FRS_ORDER_ABLE_POSITION_NOT_FOUND));
            return false;
        }
        // エラーなし：次の処理へ */
        for (IfaForeignMarginTradeRepayOrderConfirmA010RequestDto_positionDesignationAreaIndividualPositionInfoList req : positionDesignationAreaIndividualPositionInfoList) {
            
            // A010.注文数量（個別）が空文字もしくはNULLの場合、チェック対象外とする。
            if (!ObjectUtils.isEmpty(req.getOrderCount())){
            
                // A010.注文数量（個別）が指定された建玉がAPI006で取得した最新の建玉明細一覧に存在するかチェックする。 */
                // 建日と建単価で存在チェック
                Optional<Position> pos = positions.stream()
                        .filter(p -> StringUtils.equals(p.getTradeDate(), req.getDomesticTradeDate())
                                && StringUtils.equals(p.getFrnPositionPrice(), req.getSinyoPreviousDayValue()))
                        .findFirst();
                if (!pos.isPresent()) {
                    error.setErrorCode(ERROR_FRS_ORDER_ABLE_POSITION_NOT_FOUND);
                    error.setErrorMessage(IfaCommonUtil.getMessage(ERROR_FRS_ORDER_ABLE_POSITION_NOT_FOUND));
                    return false;
                }
                Position detail = pos.get();
                
                // A010.注文数量（個別）が、0以上　かつ　API006.外国株式信用建玉明細一覧.決済注文可能数量以下　の範囲内にあることをチェックする。 */
                Long orderQuantityIndividual = Long.valueOf(req.getOrderCount());
                Long closableQuantity = Long.valueOf(detail.getClosableQuantity());
                
                if (orderQuantityIndividual < 0 || (closableQuantity != 0 && orderQuantityIndividual > closableQuantity)) {
                    // エラーあり：エラーを返す */
                    error.setErrorCode(ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE);
                    error.setErrorMessage(IfaCommonUtil.getMessage(ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE,
                            new String[] { ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE_0_3, ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE_ATTR_0, detail.getClosableQuantity() }));
                    return false;
                }
                
                // エラーなし：次の処理へ */
                // 注文数量が、API001.取引単位で割り切れることをチェックする。 */
                Long tradeUnit = Long.valueOf(respApi001.getTradeUnit());
                if (orderQuantityIndividual % tradeUnit != 0) {
                    // エラーあり：エラーを返す */
                    error.setErrorCode(ERROR_FRS_ORDERING_CONDITION_INCONSISTENT);
                    error.setErrorMessage(IfaCommonUtil.getMessage(ERROR_FRS_ORDERING_CONDITION_INCONSISTENT,
                            new String[] { ERROR_FRS_ORDERING_CONDITION_INCONSISTENT_0_1,
                                    ERROR_FRS_ORDERING_CONDITION_INCONSISTENT_1_1, respApi001.getTradeUnit() }));
                    
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * 一括指定時の注文数量チェック
     * 
     * @param quantity
     * @param api007ResSummary
     * @param api001Res
     * @return true:OK false:NG
     */
    private boolean checkBatchQuantity(String orderCount, GetForeignStockSecuritiesResp respApi001,
            GetMarginPositionSummaryResp respApi006, IfaForeignMarginTradeRepayOrderErrorModel error) {
        // ⑦一括指定の場合、返済株数をチェックする。 */
        
        // A010.注文数量が、API001.取引下限数量以上　かつ　API001.取引上限数量（ただし0の場合は上限なしとして扱う）とAPI006.外国株式信用建玉サマリ.決済注文可能数量の小さい方の数量以下　の範囲内にあることをチェックする。 */
        Long quantity = Long.valueOf(orderCount);
        Long tradeLimitMin = Long.valueOf(respApi001.getTradeLimitMin());
        Long tradeLimitMax = Long.valueOf(respApi001.getTradeLimitMax());
        Long closableQuantity = Long.valueOf(respApi006.getPositionSummary().getClosableQuantity());
        Long limitQuantity = tradeLimitMax;
        // 数値が低い方を優先
        if (tradeLimitMax > closableQuantity) {
            limitQuantity = closableQuantity;
        }
        // 0の場合は9999999999として扱う
        if (Long.valueOf(0).equals(limitQuantity)) {
            limitQuantity = TRADE_LIMIT_MAX_UNLIMITED;
        }
        
        // 返済株数チェック
        if (quantity < tradeLimitMin || quantity > limitQuantity) {
            // エラーあり：エラーを返す */
            error.setErrorCode(ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE);
            error.setErrorMessage(IfaCommonUtil.getMessage(ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE,
                    new String[] { ERROR_FRS_ORDER_VALUE_OUT_OF_RANGE_0_1, respApi001.getTradeLimitMin(),
                            limitQuantity.toString() }));
            return false;
        }
        // エラーなし：次の処理へ */
        // 返済株数が、銘柄マスタ取得API.取引単位で割り切れることをチェックする。 */
        Long tradeUnit = Long.valueOf(respApi001.getTradeUnit());
        if (quantity % tradeUnit != 0) {
            error.setErrorCode(ERROR_FRS_ORDERING_CONDITION_INCONSISTENT);
            error.setErrorMessage(IfaCommonUtil.getMessage(ERROR_FRS_ORDERING_CONDITION_INCONSISTENT,
                    new String[] { ERROR_FRS_ORDERING_CONDITION_INCONSISTENT_0_0,
                            ERROR_FRS_ORDERING_CONDITION_INCONSISTENT_1_0, respApi001.getTradeUnit() }));
            return false;
        }
        return true;
    }
    
    /**
     * 有効期間の9営業日以内に存在する日付確認
     * @param dtoReq
     * @param api006Res
     * @return
     * @throws Exception 
     */
    private boolean checkTerm(IfaForeignMarginTradeRepayOrderConfirmA010aRequestDto dtoReq,
            GetMarginPositionSummaryResp api006Res, CustomerCommon cc) throws Exception {
        
        GetForeignStockCreatedMarginOrderInitializationResp respApi005 = null;
        try {
            respApi005 = doApi005(dtoReq, cc);            
        } catch (Exception e) {
            throw e;
        }
        
        DateFormat format = new SimpleDateFormat(DateFormatUtil.SEPARATED_YYYYMMDD);
        // API005.有効期間一覧（T+1～T+9のうち取得可能な日付）の最大値
        int loopMaxCount = 9;
        if (CollectionUtils.isEmpty(respApi005.getOrderTerms())) {
            loopMaxCount = 0;
        } else if (respApi005.getOrderTerms().size() < 9) {
            loopMaxCount = respApi005.getOrderTerms().size();
        }
        List<Date> api005DateList = new ArrayList<Date>();
        if (loopMaxCount > 0) {
            for (int i = 0; i < loopMaxCount; i++) {
                try {
                    String convertTerm = respApi005.getOrderTerms().get(i);
                    if (!StringUtil.isNullOrEmpty(convertTerm)) {
                        api005DateList.add(format.parse(convertTerm));
                    }
                } catch (ParseException e) {
                    LOGGER.debug("DateFormat ParseException: " + e.getMessage(), e);
                }
            }
        }
        // API006.外国株式信用建玉明細一覧.現地決済期日の最大値
        Optional<Date> maxOrderTermDateOpt = api005DateList.stream().max((d1, d2) -> d1.compareTo(d2));
        Optional<Date> maxFrnCloseLimitDateOpt = api006Res.getPositions().stream()
                .map(position -> position.getFrnCloseLimitDate()).map(frnCloseLimitDate -> {
                    try {
                        return StringUtil.isNullOrEmpty(frnCloseLimitDate) ? null : format.parse(frnCloseLimitDate);
                    } catch (ParseException e) {
                        LOGGER.debug("DateFormat ParseException: " + e.getMessage(), e);
                        return null;
                    }
                }).filter(Objects::nonNull).max((d1, d2) -> d1.compareTo(d2));
        Date maxOrderTermDate = maxOrderTermDateOpt.isPresent() ? maxOrderTermDateOpt.get() : null;
        Date maxFrnCloseLimitDate = maxFrnCloseLimitDateOpt.isPresent() ? maxFrnCloseLimitDateOpt.get() : null;
        Date periodDate = format.parse(dtoReq.getPeriod());
        // API005.有効期間一覧（T+1～T+9のうち取得可能な日付）の最大値とAPI006.外国株式信用建玉明細一覧.現地決済期日の最大値を比較し、小さい方を最大値とする
        if (maxOrderTermDate == null && maxFrnCloseLimitDate == null) {
            LOGGER.debug("API response no date.");
            return false;
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
     * アクションID：A010b
     * アクション名：注文発注
     * Dto リクエスト：IfaForeignMarginTradeRepayOrderConfirmA010bRequestDto
     * Dto レスポンス：IfaForeignMarginTradeRepayOrderConfirmA010bResponseDto
     * model レスポンス：IfaForeignMarginTradeRepayOrderConfirmSql003ResponseModel
     *
     * @param dtoReq A010bリクエスト
     * @return DataList A010bレスポンス
     * @exception Exception エクセプション
     */
    public DataList<IfaForeignMarginTradeRepayOrderConfirmA010bResponseDto> orderPlacementA010b(
            IfaForeignMarginTradeRepayOrderConfirmA010bRequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginTradeRepayOrderConfirmServiceImplL.orderPlacementA010b");
        }
        DataList<IfaForeignMarginTradeRepayOrderConfirmA010bResponseDto> dtoRes = new DataList<IfaForeignMarginTradeRepayOrderConfirmA010bResponseDto>();
        
        List<IfaForeignMarginTradeRepayOrderConfirmA010bResponseDto> resDtoList = new ArrayList<IfaForeignMarginTradeRepayOrderConfirmA010bResponseDto>();
        IfaForeignMarginTradeRepayOrderConfirmA010bResponseDto response = new IfaForeignMarginTradeRepayOrderConfirmA010bResponseDto();
        
        IfaForeignMarginTradeRepayOrderErrorModel error = new IfaForeignMarginTradeRepayOrderErrorModel();

        // 共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // ユーザアカウント情報の取得
        UserAccount ua = IfaCommonUtil.getUserAccount();

        // 返済建玉指定方法:一括指定
        boolean isBatch = false;
        // 返済建玉指定方法:個別指定
        boolean isIndividual = false;

        if (!StringUtil.isNullOrEmpty(dtoReq.getRepayPositionDesignateMethod())) {
            isBatch = StringUtils.equals(dtoReq.getRepayPositionDesignateMethod(), ForeignRepayMethod.BATCH.getId());
            isIndividual = StringUtils.equals(dtoReq.getRepayPositionDesignateMethod(), ForeignRepayMethod.INDIVIDUAL.getId());
        }


        // ⑰注文発注を行う。 */
        CloseForeignStockMarginOrderResp respApi004 = null;
        ApiStatusDto apiStatus = null;
        DataList<IfaForeignMarginTradeRepayOrderConfirmA010bResponseDto> dtoResApiErr = null;
        try {
            // 外国株式信用返済注文登録APIにて注文発注を行い、結果を取得する。 */
            respApi004 = doApi004(dtoReq, cc, isBatch, isIndividual);
            // レスポンスをセットする
            fillFromApi004Response(response, respApi004);
            responseNullCheck(respApi004);
        } catch (Exception e) {
            if (e instanceof AthenaBusinessException) {
                apiStatus = new ApiStatusDto();
                apiStatus.setApiErrorCode(((AthenaBusinessException) e).getErrorCode());
                apiStatus.setApiMessage(((AthenaBusinessException) e).getMessage());
                apiStatus.setApiStatusCode(((AthenaBusinessException) e).getStatusCode().toString());
                dtoResApiErr = cometCommonService.checkBussinessException(
                        IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, "", null), e);
            }
        }
        ErrorLevel resultEl = ErrorLevel.SUCCESS;
        // ⑱発注後に注文結果をDBに反映する。 */
        try {
            // 注文データをDBに登録する。 */
            doSql003(dtoReq, respApi004, apiStatus, ua);
        } catch (Exception e) {
            // DB更新NG：注文結果DB登録失敗ワーニング情報を格納して次の処理へ */
            error.setErrorCode(WARNINGS_FRS_POST_ORDER_EXECUTION_COMPLETED);
            error.setErrorMessage(IfaCommonUtil.getMessage(WARNINGS_FRS_POST_ORDER_EXECUTION_COMPLETED));
            resultEl = ErrorLevel.WARNING;
        }
        if (dtoResApiErr != null) {
            return dtoResApiErr;
        }
        
        // レスポンスをセットする
        fillFromRequest(response, dtoReq);
        resDtoList.add(response);
        
        if (resultEl == ErrorLevel.SUCCESS) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, resultEl, resultEl.toString(), null);
        } else {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, resultEl, error.getErrorCode(), error.getErrorMessage());
        }
        
        return dtoRes;
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
     * 事前チェック
     * @return true:OK　false:NG
     */
    private boolean checkValidation(boolean chkA010, String tradeCd, IfaForeignMarginTradeRepayOrderErrorModel error, CustomerCommon cc, UserAccount ua) {
        
        // A010のみに実行する処理
        if (chkA010) {
            // FCT018
            if (!checkFct018(error)) {
                return false;
            }
            // 利用者の権限チェック
            if (PRIV_SBI_AUTH_LIST.contains(ua.getPrivId())) {
                error.setErrorCode(ERRORS_FRS_ORDER_EXECUTION_INSUFFICIENT_PRIVILEGE__MARGIN_TRADING);
                error.setErrorMessage(IfaCommonUtil.getMessage(error.getErrorCode()));
                return false;
            }
            
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
        input.setProductCd(FCT_FOREIGNSTOCK);
        input.setCountryCd(NationalityCode.US.getId());
        input.setCurrencyCode(FCT_CURRENCY_CODE);
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
     * FCT021チェック処理
     * 
     * @param tradeCd 取引種別
     * @return fct021出力結果
     */
    private boolean checkFct021(String tradeCd, String noticeInfoAlertConfirm, String notificationAlertConfirm, IfaForeignMarginTradeRepayOrderErrorModel error, CustomerCommon cc)
            throws Exception {
        
        InputFct021Dto input = new InputFct021Dto();
        input.setAccountNumber(cc.getAccountNumber());
        input.setButenCode(cc.getButenCode());
        input.setTradeCd(tradeCd);
        input.setProductCd(FCT_FOREIGNSTOCK);
        input.setCountryCd(NationalityCode.US.getId());
        input.setCurrencyCode(FCT_CURRENCY_CODE);
        OutputFct021Dto output = fct021.doCheck(input);
        if (output != null) {
            // 注意情報エラー有
            if (StringUtils.equals(output.getNoteInfoErrFlag(), Fct021.EXIST)) {
                error.setErrorCode(ERRORS_CMN_NOTICEERRORCHECK);
                error.setErrorMessage(IfaCommonUtil.getMessage(error.getErrorCode(), new String[] {
                        codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_KUBUN) }));
                return false;
            }
            // お知らせエラー有
            if (StringUtils.equals(output.getNoteLimitErrFlag(), Fct021.EXIST)) {
                error.setErrorCode(ERRORS_INFORMATIONCHECK);
                error.setErrorMessage(IfaCommonUtil.getMessage(error.getErrorCode()));
                return false;
            }
            // 注意情報アラート有 & 注意情報アラート確認チェックボックス=OFF
            if (StringUtils.equals(output.getNoteInfoAlertFlag(), Fct021.EXIST)
                    && !StringUtils.equals(noticeInfoAlertConfirm, NoticeInfoConfirm.ON.getId())) {
                error.setErrorCode(ERROR_CMN_INFORMATION_OCCURS);
                error.setErrorMessage(IfaCommonUtil.getMessage(error.getErrorCode()));
                return false;
            }
            // お知らせアラート有 & お知らせアラート確認チェックボックス=OFF
            if (StringUtils.equals(output.getNoteLimitAlertFlag(), Fct021.EXIST)
                    && !StringUtils.equals(notificationAlertConfirm, ImportantNotificationConfirm.ON.getId())) {
                error.setErrorCode(ERROR_CMN_INFORMATION_OCCURS);
                error.setErrorMessage(IfaCommonUtil.getMessage(error.getErrorCode()));
                return false;
            }
            
        }
        
        return true;
    }
    
    /**
     * API001を呼び出し処理
     *
     * @param dtoReq A010aのリクエスト
     * @return GetForeignStockSecuritiesResp API001のレスポンス
     */
    private GetForeignStockSecuritiesResp doApi001(String brandCode) throws Exception {
        
        return foreignStockService.getForeignStockSecurities(NationalityCode.US.getId(), brandCode);
    }
    
    /**
     * API002呼び出し処理
     * @param dtoReq
     * @return
     * @throws Exception
     */
    private GetForeignStockAttentionSecuritiesResp doApi002(
            IfaForeignMarginTradeRepayOrderConfirmA010aRequestDto dtoReq) throws Exception {
        
        String countryCode = NationalityCode.US.getId();
        String securitiesCode = dtoReq.getBrandCode();
        return foreignStockService.getForeignStockAttentionSecurities(countryCode, securitiesCode);
    }
    
    /**
     * API003呼び出し処理
     * 
     * @param dtoReq
     * @return
     * @throws Exception
     */
    private ListMarketPricesResp doApi003(String hashToken, String rics) throws Exception {
        
        return foreignInformationService.listMarketPrices(hashToken, NationalityCode.US.getId(), new String[] { rics });
    }
    
    /**
     * API004呼び出し処理
     * 
     * @param dtoReq
     * @return
     * @throws Exception
     */
    private CloseForeignStockMarginOrderResp doApi004(IfaForeignMarginTradeRepayOrderConfirmA010bRequestDto dtoReq, CustomerCommon cc, boolean isBatch, boolean isIndividual)
            throws Exception {
        
        String orderQuantity = "0";
        List<IfaForeignMarginTradeRepayOrderConfirmA010RequestDto_positionDesignationAreaIndividualPositionInfoList> orderList = new ArrayList<IfaForeignMarginTradeRepayOrderConfirmA010RequestDto_positionDesignationAreaIndividualPositionInfoList>();
        if (dtoReq.getPositionDesignationAreaIndividualPositionInfoList() != null) {
            orderList = dtoReq.getPositionDesignationAreaIndividualPositionInfoList().stream()
                    .filter(p -> isNotEmptyOrderCount(p.getOrderCount())).collect(Collectors.toList());
        }
        
        List<ClosedPositionInput> postionList = new ArrayList<>();
        
        if (isBatch) {
            orderQuantity = dtoReq.getOrderCount();
        } else if (isIndividual) {
            orderQuantity = getSumValue(orderList.stream().map(o -> o.getOrderCount()).collect(Collectors.toList()))
                    .toString();
        }
        
        MarginTradeRepayOrderConfirmForCloseForeignStockMarginOrder marginOrder = new MarginTradeRepayOrderConfirmForCloseForeignStockMarginOrder();
        marginOrder.setCountryCode(NationalityCode.US.getId());
        marginOrder.setMarketCode(dtoReq.getMarketCode());
        marginOrder.setSecuritiesCode(dtoReq.getBrandCode());
        marginOrder.setBuySellCode(getApiTradeCd(dtoReq.getTradeCd()));
        marginOrder.setOrderQuantity(orderQuantity);
        marginOrder.setOrderPriceKindCode(dtoReq.getOrderPriceKindList());
        if (StringUtils.equals(dtoReq.getOrderPriceKindList(),
                SelectAblePriceConditions.PRICE_LIMIT.getId())
                || StringUtils.equals(dtoReq.getOrderPriceKindList(),
                        SelectAblePriceConditions.STOP_ORDER_EXECUTE_PRICE.getId())) {
            // 価格条件が「指値」「逆指値」の場合
            marginOrder.setOrderPrice(dtoReq.getHiddenOrderPrice());
        }
        if (StringUtils.equals(dtoReq.getOrderPriceKindList(),
                SelectAblePriceConditions.STOP_ORDER_EXECUTE_PRICE.getId())
                || StringUtils.equals(dtoReq.getOrderPriceKindList(),
                        SelectAblePriceConditions.STOP_MARKET_ORDER_EXECUTE_PRICE.getId())) {
            // 価格条件が「逆指値」の場合
            marginOrder.setStopPrice(dtoReq.getStopOrderPrice2());
        }
        
        marginOrder.setTrailingStopAmount(null);
        marginOrder.setOrderLimitCode(dtoReq.getPeriodRadio());
        if (PERIOD_CONDITION_1.equals(dtoReq.getPeriodRadio())) {
            marginOrder.setOrderTerm(dtoReq.getPeriod());
        }
        marginOrder.setSpecificAccountCode(dtoReq.getDepositType());
        marginOrder.setSettlementMethodCode(dtoReq.getKessaiHoho());
        marginOrder.setClosePositionKind(dtoReq.getRepayPositionDesignateMethod());
        marginOrder.setCloseSelectionSort(dtoReq.getRepaySelectOrder());
        
        orderList.stream().forEach(o -> {
            ClosedPositionInput position = new ClosedPositionInput();
            postionList.add(position);
            position.setBuySellCode(dtoReq.getTrade());
            position.setMarginCloseLimitType(o.getMarginDueDate());
            position.setSpecificAccountCode(dtoReq.getDepositType());
            position.setTradeDate(o.getDomesticTradeDate());
            position.setFrnTradeDate(o.getForeignTradeDate());
            position.setPositionPrice(o.getSinyoPreviousDayValue());
            position.setPositionQuantity(o.getQuantity());
            position.setCloseOrderQuantity(o.getOrderCount());
        });
        
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        String tradePassword = null;
        String requestId = null;
        String channel = CHANNEL_PHONE;
        return foreignStockService.closeForeignStockMarginOrder(butenCode, accountNumber, tradePassword, requestId,
                channel, marginOrder, postionList);
    }
    
    /**
     * API005呼び出し処理
     * @param dtoReq
     * @return
     * @throws Exception
     */
    private GetForeignStockCreatedMarginOrderInitializationResp doApi005(
            IfaForeignMarginTradeRepayOrderConfirmA010aRequestDto dtoReq, CustomerCommon cc) throws Exception {
        
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        String brandCode = dtoReq.getBrandCode();
        String buySellCode = getApiTradeCd(dtoReq.getTradeCd());
        String specificAccountCode = dtoReq.getDepositType();
        return foreignStockService.getForeignStockCreatedMarginOrderInitializationNoConvertBuySellCode(butenCode,
                accountNumber,
                NationalityCode.US.getId(), brandCode, STOCK_TRADE_TYPE_MARGIN_CLOSE, buySellCode, specificAccountCode);
    }
    
    /**
     * API006呼び出し処理
     * 
     * @param dtoReq
     * @return
     * @throws Exception
     */
    private GetMarginPositionSummaryResp doApi006(IfaForeignMarginTradeRepayOrderConfirmA010aRequestDto dtoReq, CustomerCommon cc)
            throws Exception {
        
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        String brandCode = dtoReq.getBrandCode();
        String buySellCode = calculateBuySellCode(dtoReq.getTradeCd());
        String marginCloseLimitType = dtoReq.getLastTradeDate();
        String specificAccountCode = dtoReq.getDepositType();
        String tradeDate = null;
        String frnTradeDate = null;
        String ticket = dtoReq.getStockTicket();
        return foreignAccountService.getMarginPositionSummaryForRepayOrderConfirm(butenCode, accountNumber,
                NationalityCode.US.getId(),
                brandCode, buySellCode, marginCloseLimitType, specificAccountCode, tradeDate, frnTradeDate,ticket);
    }
    
    /**
     * API007呼び出し処理
     * 
     * @param 
     * @return API出力結果
     * @throws Exception 
     */
    private CreateMarketPriceTicketResp doApi007(CustomerCommon cc) throws Exception {
        
        return foreignInformationService.createMarketPriceTicket(cc.getButenCode(), cc.getAccountNumber(),
                API004_REQUEST_IP_ADDRESS, API004_REQUEST_AGENT, NationalityCode.US.getId());
        
    }
    
    /**
     * API008呼び出し処理
     * 
     * @param 
     * @return API出力結果
     * @throws Exception 
     */
    private GetMarketPriceHashResp doApi008(String ticket, CustomerCommon cc) throws Exception {
        
        return foreignInformationService.getMarketPriceHash(cc.getButenCode(), cc.getAccountNumber(), ticket,
                NationalityCode.US.getId());
        
    }
    
    /**
     * SQL001発行処理
     * 
     * @param dtoReq
     * @param contextMap
     * @return
     * @throws Exception
     */
    private int doSql001(IfaForeignMarginTradeRepayOrderConfirmA010aRequestDto dtoReq, String ifaOrderNo, CustomerCommon cc, UserAccount ua)
            throws Exception {
        
        IfaForeignMarginTradeRepayOrderConfirmSql001RequestModel insSql001Req = new IfaForeignMarginTradeRepayOrderConfirmSql001RequestModel();
        
        insSql001Req.setIfaOrderNo(ifaOrderNo);
        insSql001Req.setIfaOrderSubNo(IFA_ORDER_SUB_NUMBER_1);
        insSql001Req.setAcceptOrderNo(dtoReq.getOrderNumber());
        insSql001Req.setAcceptOrderSubNo(dtoReq.getOrderSubNumber());
        insSql001Req.setButenCode(cc.getButenCode());
        insSql001Req.setAccountNumber(cc.getAccountNumber());
        insSql001Req.setTickerCode(dtoReq.getBrandCode());
        insSql001Req.setBrandName(dtoReq.getBrandName());
        insSql001Req.setMarketCode(dtoReq.getMarketCode());
        insSql001Req.setMethodType(METHOD_TYPE_11);
        insSql001Req.setTradeKbn(dtoReq.getTradeKbn());
        insSql001Req.setOrderQuantity(dtoReq.getOrderCount());
        insSql001Req.setPriceConditionsType(dtoReq.getOrderPriceKindList());
        insSql001Req.setSashine(dtoReq.getHiddenOrderPrice());
        insSql001Req.setStopOrderPrice(dtoReq.getStopOrderPrice2());
        insSql001Req.setCurrencyCode(dtoReq.getCurrencyCode());
        insSql001Req.setSettlementType(dtoReq.getKessaiHoho());
        insSql001Req.setDepositType(dtoReq.getDepositType());
        insSql001Req.setKanyuKbn(dtoReq.getKanyuKbn());
        insSql001Req.setJutyuKbn(dtoReq.getReceiveOrderTypeName());
        insSql001Req.setBeforeTradeConfirmType(dtoReq.getCheckInsider());
        insSql001Req.setSecurityClassType(SECURITY_CLASS_TYPE_0);
        insSql001Req.setOrderDate(getOrderDate(dtoReq.getOrderDate()));
        insSql001Req.setOrderTime(getConvertOrderDate(dtoReq.getOrderDate()));
        insSql001Req.setDomesticTradeDate(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getBusinessDaysAfterOrder()));
        insSql001Req.setOrderDeadline(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getPeriod()));
        insSql001Req.setBrokerCode(cc.getBrokerCode());
        insSql001Req.setBrokerChargeCode(cc.getBrokerChargeCode());
        insSql001Req.setApiErrorCode(null);
        insSql001Req.setApiStatusCode(null);
        insSql001Req.setApiMsg(null);
        insSql001Req.setCreateUser(ua.getUserId());
        insSql001Req.setUpdateUser(ua.getUserId());
        return dao.insertIfaForeignMarginTradeRepayOrderConfirmSql001(insSql001Req);
    }
    
    /**
     * SQL002発行処理（一括）
     * @param dtoReq
     * @param ifaOrderNumber
     * @return
     * @throws Exception
     */
    private int doSql002Batch(IfaForeignMarginTradeRepayOrderConfirmA010aRequestDto dtoReq, String ifaOrderNumber, CustomerCommon cc, UserAccount ua)
            throws Exception {
        
        IfaForeignMarginTradeRepayOrderConfirmSql002RequestModel insSql002Req = new IfaForeignMarginTradeRepayOrderConfirmSql002RequestModel();
        insSql002Req.setIfaOrderNo(ifaOrderNumber);
        insSql002Req.setTickerCode(dtoReq.getBrandCode());
        insSql002Req.setMethodType(METHOD_TYPE_11);
        insSql002Req.setRepayPeriodType(dtoReq.getLastTradeDate());
        insSql002Req.setTradeKbn(dtoReq.getTrade());
        insSql002Req.setDepositType(dtoReq.getDepositType());
        insSql002Req.setOpenTradeDate(ORDER_DATE_DEFAULT);
        insSql002Req.setPositionPrice(POSITION_PRICE_0);
        insSql002Req.setPositionQuantity(null);
        insSql002Req.setRepayQuantity(dtoReq.getOrderCount());
        insSql002Req.setButenCode(cc.getButenCode());
        insSql002Req.setAccountNumber(cc.getAccountNumber());
        String repaymentMethodType = dtoReq.getRepayPositionDesignateMethod();
        insSql002Req.setRepaymentMethodType(repaymentMethodType);
        String requestType = dtoReq.getRepaySelectOrder();
        insSql002Req.setRequestType(requestType);
        insSql002Req.setCreateUser(ua.getUserId());
        insSql002Req.setUpdateUser(ua.getUserId());
        return dao.insertIfaForeignMarginTradeRepayOrderConfirmSql002(insSql002Req);
    }
    
    /**
     * SQL002発行処理（個別）
     * @param dtoReq
     * @param insertList 
     * @param ifaOrderNumber
     * @return
     * @throws Exception
     */
    private int doSql002Individual(IfaForeignMarginTradeRepayOrderConfirmA010aRequestDto dtoReq,
            List<IfaForeignMarginTradeRepayOrderConfirmA010RequestDto_positionDesignationAreaIndividualPositionInfoList> insertList,
            String ifaOrderNumber, CustomerCommon cc, UserAccount ua)
            throws Exception {
        
        int insCount = 0;
        
        for (IfaForeignMarginTradeRepayOrderConfirmA010RequestDto_positionDesignationAreaIndividualPositionInfoList ind : insertList) {
            IfaForeignMarginTradeRepayOrderConfirmSql002RequestModel insSql002Req = new IfaForeignMarginTradeRepayOrderConfirmSql002RequestModel();
            insSql002Req.setIfaOrderNo(ifaOrderNumber);
            insSql002Req.setTickerCode(dtoReq.getBrandCode());
            insSql002Req.setMethodType(METHOD_TYPE_11);
            insSql002Req.setRepayPeriodType(dtoReq.getLastTradeDate());
            insSql002Req.setTradeKbn(dtoReq.getTrade());
            insSql002Req.setDepositType(dtoReq.getDepositType());
            insSql002Req.setOpenTradeDate(DateFormatUtil.dateFormatToYmdNoSign(ind.getDomesticTradeDate()));
            insSql002Req.setPositionPrice(ind.getSinyoPreviousDayValue());
            insSql002Req.setPositionQuantity(ind.getQuantity());
            insSql002Req.setRepayQuantity(ind.getOrderCount());
            insSql002Req.setButenCode(cc.getButenCode());
            insSql002Req.setAccountNumber(cc.getAccountNumber());
            String repaymentMethodType = dtoReq.getRepayPositionDesignateMethod();
            insSql002Req.setRepaymentMethodType(repaymentMethodType);
            String requestType = dtoReq.getRepaySelectOrder();
            insSql002Req.setRequestType(requestType);
            insSql002Req.setCreateUser(ua.getUserId());
            insSql002Req.setUpdateUser(ua.getUserId());
            insCount = insCount + dao.insertIfaForeignMarginTradeRepayOrderConfirmSql002(insSql002Req);
        }
        
        return insCount;
    }
    
    /**
     * SQL003発行処理（API正常終了）
     * 
     * @param dtoReq
     * @param contextMap
     * @return
     * @throws Exception
     */
    private int doSql003(IfaForeignMarginTradeRepayOrderConfirmA010bRequestDto dtoReq,
            CloseForeignStockMarginOrderResp respApi004, ApiStatusDto apiStatus, UserAccount ua) throws Exception {
        
        IfaForeignMarginTradeRepayOrderConfirmSql003RequestModel updSql003Req = new IfaForeignMarginTradeRepayOrderConfirmSql003RequestModel();
        updSql003Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
        updSql003Req.setIfaOrderSubNo(IFA_ORDER_SUB_NUMBER_1);
        
        if (apiStatus != null) {
            updSql003Req.setApiErrorCode(apiStatus.getApiErrorCode());
            updSql003Req.setApiMsg(apiStatus.getApiMessage());
            updSql003Req.setApiStatusCode(apiStatus.getApiStatusCode());
        } else {
            Order order = respApi004.getMarginOrder().getOrder();
            updSql003Req.setAcceptOrderNo(order.getOrderNo());
            updSql003Req.setAcceptOrderSubNo(order.getOrderSubNo());
            updSql003Req.setOrderDate(getOrderDate(order.getOrderInputDatetime()));
            updSql003Req.setOrderTime(getConvertOrderDate(order.getOrderInputDatetime()));
            // 期間条件が当日注文の場合は、遷移元画面.A012.現地約定日(画面.期間)
            String orderDeadLine = StringUtils.equals(dtoReq.getPeriodRadio(), PERIOD_CONDITION_0) ? dtoReq.getPeriod()
                    : order.getOrderTerm();
            updSql003Req.setOrderDeadline(DateFormatUtil.dateFormatToYmdNoSign(orderDeadLine));
            updSql003Req.setApiStatusCode(HTTP_SUCCESS);
        }
        updSql003Req.setUpdateUser(ua.getUserId());
        return dao.updateIfaForeignMarginTradeRepayOrderConfirmSql003(updSql003Req);
    }
    
    /**
     * 注文日取得処理（YYYYMMDD形式）
     * 
     * @param orderDate
     * @return
     */
    private String getOrderDate(String orderDate) {
        String orderDateConv = StringUtil.EMPTY_STRING;
        
        if(!StringUtil.isNullOrEmpty(orderDate)) {
            try {
                orderDateConv = DateFormatUtil.dateFormatToYmd(orderDate);
            } catch(Exception e) {
                LOGGER.debug("orderDate DateFormatUtil.dateFormatToSeparatedYmdhms exception.");
                orderDateConv = ORDER_DATE_DEFAULT;
            }
        } else {
            orderDateConv = ORDER_DATE_DEFAULT;
        }
        return orderDateConv;
    }
    
    /**
     * 注文日時形式変換処理(yyyy-MM-dd HH:mm:ss形式)
     * @param orderDate
     * @return
     */
    private String getConvertOrderDate(String orderDate) {
        String orderDateConv = StringUtil.EMPTY_STRING;
        
        if(!StringUtil.isNullOrEmpty(orderDate)) {
            try {
                orderDateConv = DateFormatUtil.dateFormatToSeparatedYmdhms(orderDate);
            } catch(Exception e) {
                LOGGER.debug("orderDate DateFormatUtil.dateFormatToSeparatedYmdhms exception.");
                orderDateConv = ORDER_DATE_TIME_DEFAULT;
            }
        } else {
            orderDateConv = ORDER_DATE_TIME_DEFAULT;
        }
        return orderDateConv;
    }
    
    /**
     * SQL004の発行処理
     * 
     * @return
     * @throws Exception
     */
    private IfaForeignMarginTradeRepayOrderConfirmSql004ResponseModel doSql004() throws Exception {
        
        return dao.selectIfaForeignMarginTradeRepayOrderConfirmSql004();
    }
    
    /**
     * API001処理結果設定
     * @param dto
     * @param respApi001
     */
    private void fillFromApi001Response(IfaForeignMarginTradeRepayOrderConfirmA010aResponseDto dto,
            GetForeignStockSecuritiesResp respApi001) {
        
        dto.setBrandClass(respApi001.getSecuritiesType());
    }
    
    /**
     * API002処理結果設定
     * @param dto
     * @param respApi002
     */
    private void fillFromApi002Response(IfaForeignMarginTradeRepayOrderConfirmA010aResponseDto dto,
            GetForeignStockAttentionSecuritiesResp respApi002) {
        
        dto.setTradeLimit(String.valueOf(respApi002.getAttentionSecurities()));
    }
    
    /**
     * API004処理結果設定
     * @param dto
     * @param respApi004
     */
    private void fillFromApi004Response(IfaForeignMarginTradeRepayOrderConfirmA010bResponseDto dto,
            CloseForeignStockMarginOrderResp respApi004) {
        
        MarginOrder marginOrder = respApi004.getMarginOrder();
        dto.setMarginDepositRateOrderAfter(respApi004.getDepositRate());
        dto.setQuotePrice(respApi004.getEstimatePrice());
        dto.setApproximateCost(respApi004.getEstimateTotalExpenses());
        dto.setMarginPositionPowerOrderAfter(respApi004.getMarginBuyingPower());
        dto.setMarginDepositRateOrderAfter(respApi004.getDepositRate());
        
        if (null != marginOrder) {
            dto.setRepayPositionDesignateMethod(marginOrder.getClosePositionKind());
            dto.setRepaySelectOrder(marginOrder.getCloseSelectionSort());
            dto.setMarginDueDate(marginOrder.getMarginCloseLimitType());
            dto.setSettlement(marginOrder.getClosedProfitLoss());
            dto.setDepositDeficientAmount(marginOrder.getMarginDeficitAmount());
            dto.setTransferDepositAmount(marginOrder.getTransferDepositAmount());
            dto.setTransferValuableSecurityValuation(marginOrder.getTransferEvaluationAmount());
            Order order = marginOrder.getOrder();
            if (null != order) {
                dto.setOrderNumber(order.getOrderNo());
                dto.setOrderSubNumber(order.getOrderSubNo());
                dto.setCurrencyCode(order.getTradeCurrencyCode());
                dto.setTradeKbn(order.getBuySellCode());
                dto.setAutoBuyingClassification(order.getAutoStockOrderType());
                dto.setOrderQuantity(order.getOrderQuantity());
                dto.setOrderPriceKindList(order.getOrderPriceKindCode());
                dto.setHiddenOrderPrice(order.getOrderPrice());
                dto.setStopOrderPrice(order.getStopPrice());
                dto.setTrailStopWidth(order.getTrailingStopAmount());
                dto.setExecuteBasePrice(order.getNoLimitPrice());
                dto.setPeriodRadio(order.getOrderLimitCode());
                dto.setPeriod(order.getOrderTerm());
                dto.setDepositType(order.getSpecificAccountCode());
                dto.setKessaiHoho(order.getSettlementMethodCode());
                dto.setSettlementCurrencyCode(order.getSettlementCurrencyCode());
                dto.setFxRate(order.getExchangeRate());
                dto.setAverageTradePrice(order.getExecutionAveragePrice());
                dto.setUnTradeQuantity(order.getUnexecutedQuantity());
                dto.setTradeQuantity(order.getExecutionQuantity());
                dto.setContractAmountForeign(order.getFrnGrossAmount());
                dto.setContractAmountJpyAmount(order.getGrossAmount());
                dto.setForeignDeliveryAmount(order.getFrnNetAmount());
                dto.setYenDeliveryAmount(order.getNetAmount());
                dto.setContractDeliveryAmount(order.getExecutionNetAmount());
                dto.setDomesticCommForeign(order.getFrnCommissionAmount());
                dto.setDomesticCommJpAmount(order.getCommissionAmount());
                dto.setDomesticConsumptionTaxForeign(order.getFrnCommissionCtax());
                dto.setDomesticConsumptionTaxJpAmount(order.getCommissionCtax());
                dto.setLocalCommForeign(order.getFrnLocalCharge());
                dto.setLocalCommJpAmount(order.getLocalCharge());
                dto.setLocalSettlementAmountForeign(order.getFrnLocalNetAmount());
                dto.setLocalSettlementJpAmount(order.getLocalNetAmount());
                dto.setNisaRestraintAmount(order.getNisaFixedAmount());
                dto.setApproximateCapitalGainsTax(order.getSpecificTax());
                dto.setOrderStatus(order.getOrderStatus());
                dto.setTradeStatus(order.getExecutionStatus());
                dto.setStopOrderStatus(order.getWorkingStatus());
                dto.setBusinessDaysAfterOrder(order.getTradeDate());
                dto.setDomesticSettlementDate(order.getValueDate());
                dto.setLocalTradeDate(order.getFrnTradeDate());
                dto.setForeignSettlementDate(order.getFrnValueDate());
                dto.setOrderDate(order.getOrderInputDatetime());
                dto.setTradeDateTime(order.getExecutionDatetime());
                dto.setExpiredDateTime(order.getExpiredDatetime());
                dto.setStockTradeType(order.getStockTradeType());
            }
        }
    }
    
    /**
     * SQL004処理結果設定
     * @param dtoRes
     * @param respSql004
     */
    private void fillFromSql004Response(IfaForeignMarginTradeRepayOrderConfirmA010aResponseDto dto,
            IfaForeignMarginTradeRepayOrderConfirmSql004ResponseModel respSql004) {
        
        dto.setIfaOrderNo(respSql004.getIfaOrderNo());
    }
    
    /**
     * レスポンス項目設定
     * @param dto
     * @param dtoReq
     */
    private void fillFromRequest(IfaForeignMarginTradeRepayOrderConfirmA010bResponseDto dto,
            IfaForeignMarginTradeRepayOrderConfirmA010bRequestDto dtoReq) {
        
        dto.setCheckInsider(dtoReq.getCheckInsider());
        dto.setTradingCautionInformation(dtoReq.getTradingCautionInformation());
        dto.setNoteInfoCheckbox(dtoReq.getNoteInfoCheckbox());
        dto.setNoteLimitCheck(dtoReq.getNoteLimitCheck());
        dto.setMethodCheck(dtoReq.getMethodCheck());
        dto.setNextDayCheck(dtoReq.getNextDayCheck());
        dto.setTradeCd(dtoReq.getTradeCd());
        dto.setLastTradeDate(dtoReq.getLastTradeDate());
        dto.setKanyuKbn(dtoReq.getKanyuKbn());
        dto.setReceiveOrderTypeName(dtoReq.getReceiveOrderTypeName());
        dto.setNoticeInfoAlert(dtoReq.getNoticeInfoAlert());
        dto.setNoticeAlert(dtoReq.getNoticeAlert());
        dto.setStopOrderInstantMessage(dtoReq.getStopOrderInstantMessage());
        dto.setNextBusinessDayMessage(dtoReq.getNextBusinessDayMessage());
        dto.setTradeNoticeInfoBrandMsg(dtoReq.getTradeNoticeInfoBrandMsg());
        dto.setMarketAbbreviatedName(dtoReq.getMarketAbbreviatedName());
        dto.setBrandCode(dtoReq.getBrandCode());
        dto.setBrandName(dtoReq.getBrandName());
        dto.setTradeLimitUrl(dtoReq.getTradeLimitUrl());
        dto.setBrandClass(dtoReq.getBrandClass());
        dto.setTradeLimit(dtoReq.getTradeLimit());
    }
    
    /**
     * リストの値を合計して返却する
     * 
     * @param filterList
     * @return 合計値
     */
    private BigDecimal getSumValue(List<String> filterList) {
        
        BigDecimal resultSumValue = BigDecimal.ZERO;
        
        if (!CollectionUtils.isEmpty(filterList)) {
            List<BigDecimal> convertList = filterList.stream().map(m -> StringUtil.parseBigDecimal(m))
                    .collect(Collectors.toList());
            resultSumValue = convertList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        
        return resultSumValue;
        
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
     * 取引種別チェック処理
     * 
     * @param dtoReq
     * @return
     */
    private String calculateBuySellCode(String tradeCd) {
        
        ForeignStockTradeClass buySellCode = ForeignStockTradeClass.valueOfId(tradeCd);
        if (buySellCode == null) {
            throw new IfaRuntimeException(ERROR_UNTARGETED);
        }
        
        switch (buySellCode) {
        case MARGIN_REPAY_SELL: {
            return BUY_SELL_CODE_BUY;
        }
        case MARGIN_REPAY_BUY: {
            return BUY_SELL_CODE_SELL;
        }
        default:
            throw new IfaRuntimeException(ERROR_UNTARGETED);
        }
    }
    
    /**
     * NULLチェック
     * @param object
     */
    private void responseNullCheck(Object object) {
        
        if (null == object) {
            throw new IfaRuntimeException(ERROR_UNTARGETED);
        }
    }
    
    /**
     * 業務エラーリマインド
     *
     * @author SCSK
     *
     */
    private static class BusinessErrorNoticeException extends Exception {
        
        private static final long serialVersionUID = -1417638461032022075L;
        
        public BusinessErrorNoticeException() {
            
        }
    }
}
