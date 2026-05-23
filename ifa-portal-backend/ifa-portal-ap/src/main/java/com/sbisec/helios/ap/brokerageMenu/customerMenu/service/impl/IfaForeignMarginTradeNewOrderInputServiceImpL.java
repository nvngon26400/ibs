package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.enums.ListedSecuritiesStatus;
import com.sbisec.helios.ap.athena.enums.MarginOrderWarningStatus;
import com.sbisec.helios.ap.athena.enums.StockTradeType;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.ifa.ForeignInformationService;
import com.sbisec.helios.ap.athena.ifa.ForeignStockService;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginOrderInput;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockCreatedMarginOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerHeadlineResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerReferenceResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ListShortableStocksResp;
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
import com.sbisec.helios.ap.bizcommon.component.Fct029;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct018Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct029Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto.MediatePropriety;
import com.sbisec.helios.ap.bizcommon.model.OutputFct018Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct029Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA012RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA012ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA019RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA019ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA020RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA020ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputBrand;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputMarket;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputPriceBasicInfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaForeignMarginTradeNewOrderInputService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.service.MedSystemVariableService;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * 画面ID：SUB0202_0303-01_1
 * 画面名：米株信用取引新規注文入力
 *
 * @author SCSK池田
 *
    2023/10/24 新規作成
 */

@Component(value = "cmpIfaForeignMarginTradeNewOrderInputService")
public class IfaForeignMarginTradeNewOrderInputServiceImpL implements IfaForeignMarginTradeNewOrderInputService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaForeignMarginTradeNewOrderInputServiceImpL.class);
    
    // athena api error 元ネタ現行ソース：AmericaStockReturnCode.java
    public static final String ATHENA_API_ERROR_CODE = "athena_api_error";
    
    // 外国株式取引口座開設状況：未開設 @区分定義書.xlsx
    private static final String FOREIGN_STOCK_TRADE_ACCOUNT_OPEN_STAUTS_NOT_OPENED = "0";
    
    // 外貨建口座未開設エラー
    private static final String ERRORS_FOREIGN_STOCK_ACCOUNT_CHECK = "errors.foreignStockAccountCheck";
    
    // 信用口座区分(外国)：現物口座 @区分定義書 
    private static final String FOREIGN_MARGIN_ACCOUNT_TYPE_PHYSICAL = " ";
    
    // 外国信用口座未開設エラー
    private static final String ERRORS_NOT_OPEN = "errors.frs.foreignStockAccount.notOpen#1";
    
    // FCT001_出力パラメータ：対象顧客参照権限有無（なし）
    private static final String AUTH_ERROR_VALUE = "0";
    
    // FCT001_出力パラメータ：取引停止フラグ（取引停止口座）
    private static final String TRADE_SUSPEND_FLAG_VALUE = "1";
    
    // FCT001_エラーメッセージ：権限なしエラー
    private static final String FCT001_ERRORS_BUTEN_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    // FCT001_エラーメッセージ：取引停止口座エラー
    private static final String FCT001_ERRORS_OUT_OF_SERVICE = "errors.cmn.selectedAccount.outOfService";
    
    // FCT003_FCT021_入力パラメータ：証券金銭種別(外国株式) @区分定義書.xlsx
    private static final String FOREIGN_STOCKS = "15";
    
    // 入力パラメータ：国(籍)コード
    private static final String COUNTRY_CODE = "US";
    
    // FCT003_FCT021_入力パラメータ：通貨コード
    private static final String CURRENCY_CODE = "999";
    
    // FCT003_エラーメッセージ：媒介不可エラー
    private static final String FCT003_ERRORS_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    // FCT018_入力パラメータ：国コード
    private static final String COUNTRY_CODE_US = "US";
    
    // FCT018_出力パラメータ：判定結果
    private static final String RESULT_NG = "NG";
    
    // FCT018_エラーメッセージ：取引時間外
    private static final String FCT018_ERRORS_OUT_OF_RANGE = "errors.frs.serviceHours.outOfRange";
    
    // FCT021_エラーメッセージ：注意情報エラー
    private static final String FCT021_ERRORS_NOTICE_ERROR_CHECK = "errors.cmn.noticeErrorCheck";
    
    // FCT021_エラーメッセージ：お知らせエラー
    private static final String FCT021_ERRORS_INFORMATION_CHECK = "errors.informationCheck";
    
    // FCT021_アラートメッセージ：注意情報アラート
    private static final String FCT021_WARNINGS_NOTICE_WARNING_CHECK = "warnings.cmn.noticeWarningCheck";
    
    // FCT021_アラートメッセージ：お知らせアラート
    private static final String FCT021_WARNINGS_INFORMATION_CHECK = "warnings.cmm.informationCheck";
    
    // API001_エラーメッセージ：上場廃止エラー
    private static final String API001_ERRORS_DELISTING = "errors.frs.listedSecuritiesStatus.delisting";
    
    // API001_エラーメッセージ：信用新規買建規制 規制ありエラー
    private static final String API001_ERRORS_OPEN_BUY_RESTRICT = "errors.frs.listedSecuritiesStatus.openBuyRestrict";
    
    // API001_エラーメッセージ：信用新規売建規制 規制ありエラー
    private static final String API001_ERRORS_OPEN_SELL_RESTRICT = "errors.frs.listedSecuritiesStatus.openSellRestrict";
    
    // API001_エラーメッセージ：数量が取引可能範囲外
    private static final String API001_ERRORS_OUT_OF_RANGE = "errors.frs.orderValue.outOfRange";

    // API007_逆指値注文即時発火あり
    private static final String WARNINGS_FRS_STOP_PRICE_CONDITION_CONFIRM = "warnings.frs.stopPriceCondition.confirm";

    // API007_増し担保規制銘柄取引あり
    private static final String WARNINGS_FRS_ADDITIONAL_COLLATERAL_TRADE_CONFIRM = "warnings.frs.additionalCollateralTrade.confirm";
    
    // API001_エラーメッセージ：数量が取引単位で割り切れない
    private static final String API001_ERRORS_INCONSITENT = "errors.frs.orderingCondition.inconsistent";
    
    // エラーメッセージ：注文単価が取引可能範囲外
    private static final String ERRORS_ORDER_VALUE_OUT_OF_RANGE = "errors.frs.orderValue.outOfRange";
    
    // API009_エラーメッセージ
    private static final String API009_NOT_FOUND = "errors.frs.orderableShortPosition.notFound";
    
    // 現地約定日超過メッセージ 
    private static final String WARNINGS_NEXT_BUSINESS_DAY = "warnings.frs.orderContract.nextBusinessDay";
    
    // warnings.dms.informationCheck
    private static final String WARNINGS_DMS_INFORMATIONCHECK = "warnings.dms.informationCheck";
    
    // errors.frs.orderTerms.outOfRange
    private static final String ERRORS_TERMS_OUTOFRANGE = "errors.frs.orderTerms.outOfRange";
    
    // 注文単価チェック：最大値
    private static final BigDecimal MAX_PRICE = new BigDecimal(999999999.99);
    
    // 注文単価チェックエラー文字列：注文単価（指値）
    private static final String ORDER_PRICE_ASKING_PRICE = "注文単価(指値)";
    
    // 注文単価チェックエラー文字列：注文単価（指値）
    private static final String ORDER_PRICE_REFERENCE_PRICE = "参照価格(逆指値)";
    
    // 注文単価チェックエラー文字列：注文単価（指値）
    private static final String ORDER_PRICE_STOP_LOSS = "注文単価(逆指値)";
    
    /** 対象取引（メッセージ表示用） */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 本日の注意銘柄URL */
    private static final String FRS_USSTOCK_URL = "FRS_USSTOCK_URL";
    
    /** 休場日URL */
    private static final String FRS_USSEC_CLOSED_URL = "FRS_USSEC_CLOSED_URL";
    
    /** 円貨決済停止日URL */
    private static final String FRS_YEN_CLOSED_URL = "FRS_YEN_CLOSED_URL";
    
    /** 取扱銘柄一覧URL */
    private static final String FRS_USEQUITY_LIST_URL = "FRS_USEQUITY_LIST_URL";
    
    /** お取引注意事項URL */
    private static final String FRS_STOCK_QAI_22_URL = "FRS_STOCK_QAI_22_URL";
    
    /** 注文数量 */
    private static final String ORDER_COUNT = "注文数量";
    
    /** 取引単位 */
    private static final String TRADE_UNIT = "取引単位";
    
    /** あり */
    private static final String EXIST = "1";
    
    /** 期間指定 */
    private static final String CARRY_OVER_ORDER = "1";
    
    /** 9営業日 */
    private static final int MAX_ORDER_TERM_LIST = 9;
    
    /** 取引上限数量の上限なし */
    private static final BigDecimal LIMIT_MAX = new BigDecimal("9999999999");
    
    /** 信用新規買 */
    private static final String BUY = "BUY";
    
    /** 信用新規売 */
    private static final String SELL = "SELL";
    
    // 価格条件
    private enum PriceConditons {
        
        // 指値
        LIMIT("1"),
        // 成行
        MARKET("2"),
        // 逆指値/指値
        STOP_PRICE_LIMIT("3"),
        // 逆指値/成行
        STOP_PRICE_MARKET("4");
        
        private final String value;
        
        PriceConditons(final String value) {
            
            this.value = value;
        }
        
        private String getValue() {
            
            return value;
        }
    }
    
    // 取引種別（外国株式） ※信用新規買／信用新規売の判断に使用 @区分定義書.xlsx
    private enum ForeignStockTradeClass {
        
        // 信用新規買
        BUY_CREDIT_NEW("2"),
        // 信用新規売
        SELL_CREDIT_NEW("3");
        
        private final String value;
        
        ForeignStockTradeClass(final String value) {
            
            this.value = value;
        }
        
        private String getValue() {
            
            return value;
        }
    }
    
    // 注文ワーニングステータス「外国株式現物注文 用」 @移植元：WarningStatuses.java
    private enum WarningStatuses {
        
        // 約定代金
        ORDER_AMOUNT_SOFT_LIMIT("ORDER_AMOUNT_SOFT_LIMIT", "約定代金ソフトリミット上限超過"),
        // 逆指値
        STOP_ORDER_TRIGGERED("STOP_ORDER_TRIGGERED", "逆指値注文即時発火"),
        // 増し担保規制銘柄取引
        ADDITIONAL_COLLATERAL_TRADE("ADDITIONAL_COLLATERAL_TRADE", "増し担保規制銘柄取引");
        
        private final String id;
        
        private final String name;
        
        private WarningStatuses(String id, String name) {
            
            this.id = id;
            this.name = name;
        }
        
        public String getId() {
            
            return id;
        }
        
        public String getName() {
            
            return name;
        }
        
        public static WarningStatuses valueOfId(String id) {
            
            if (id == null) {
                return null;
            }
            
            WarningStatuses[] enums = values();
            
            for (int i = 0; i < enums.length; i++) {
                if (enums[i].getId().equals(id)) {
                    return enums[i];
                }
            }
            
            return null;
        }
    }
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct018 fct018;
    
    @Autowired
    private Fct021 fct021;
    
    @Autowired
    private Fct029 fct029;
    
    @Autowired
    private ForeignStockService foreignStockService;
    
    @Autowired
    private ForeignAccountService foreignAccountService;
    
    @Autowired
    private ForeignInformationService foreignInformationService;
    
    @Autowired
    private MedSystemVariableService medSystemVariableService;
    
    @Autowired
    private CodeListService codelistservice;
    
    @Autowired
    private CometCommonService cometCommonService;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignMarginTradeNewOrderInputA001RequestDto
     * Dto レスポンス：IfaForeignMarginTradeNewOrderInputA001ResponseDto
     *
     * @param reqDto リクエストパラメータ
     * @return 実行結果
     * @exception Exception 業務例外
     */
    public DataList<IfaForeignMarginTradeNewOrderInputA001ResponseDto> initializeA001(
            IfaForeignMarginTradeNewOrderInputA001RequestDto reqDto) throws Exception {
        
        DataList<IfaForeignMarginTradeNewOrderInputA001ResponseDto> resDto = new DataList<IfaForeignMarginTradeNewOrderInputA001ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginTradeNewOrderInputServiceImplL.initializeA001");
        }
        
        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        String butenCode = customerCommon.getButenCode();
        String accountNumber = customerCommon.getAccountNumber();
        // FCT001実行
        resDto = executeFct001(butenCode, accountNumber);
        // 正常終了以外の場合、エラー内容を返却して処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        // FCT003実行
        resDto = executeFct003(butenCode, accountNumber, null);
        // 正常終了以外の場合、エラー内容を返却して処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        // 外貨建口座取引開設状況のチェック
        resDto = checkForeignStockTradeAccountOpenStatus(customerCommon.getForeignStockTradeAccountOpenStatus());
        // 正常終了ではない場合、処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        // 外国信用口座開設状況のチェック
        resDto = checkForeignMarginAccountype(customerCommon.getForeignMarginAccountType());
        // 正常終了ではない場合、処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        return resDto;
    }
    
    /**
     * アクションID：A003
     * アクション名：株価表示
     * Dto リクエスト：IfaForeignMarginTradeNewOrderInputA003RequestDto
     * Dto レスポンス：IfaForeignMarginTradeNewOrderInputA003ResponseDto
     *
     * @param reqDto リクエストパラメータ
     * @return 実行結果
     * @exception Exception 業務例外
     */
    public DataList<IfaForeignMarginTradeNewOrderInputA003ResponseDto> stockPriceDisplayA003(
            IfaForeignMarginTradeNewOrderInputA003RequestDto reqDto) throws Exception {
        
        DataList<IfaForeignMarginTradeNewOrderInputA003ResponseDto> resDto = new DataList<IfaForeignMarginTradeNewOrderInputA003ResponseDto>();
        List<IfaForeignMarginTradeNewOrderInputA003ResponseDto> resDtoList = new ArrayList<IfaForeignMarginTradeNewOrderInputA003ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginTradeNewOrderInputServiceImplL.stockPriceDisplayA003");
        }
        
        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        String butenCode = customerCommon.getButenCode();
        String accountNumber = customerCommon.getAccountNumber();
        
        // FCT001実行
        resDto = executeFct001(butenCode, accountNumber);
        // 正常終了以外の場合、エラー内容を返却して処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        // FCT003実行
        resDto = executeFct003(butenCode, accountNumber, reqDto.getTradeCd());
        // 正常終了以外の場合、エラー内容を返却して処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        // 外貨建口座取引開設状況のチェック
        resDto = checkForeignStockTradeAccountOpenStatus(customerCommon.getForeignStockTradeAccountOpenStatus());
        // 正常終了ではない場合、処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        // 外国信用口座開設状況のチェック
        resDto = checkForeignMarginAccountype(customerCommon.getForeignMarginAccountType());
        // 正常終了ではない場合、処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        // API001呼出：数量をチェックする。
        // 銘柄情報を取得する。
        // API001のリクエストパラメータを設定する。
        // API001を実行する。
        GetForeignStockSecuritiesResp resApi001 = new GetForeignStockSecuritiesResp();
        try {
            resApi001 = foreignStockService.getForeignStockSecurities(COUNTRY_CODE, reqDto.getBrandCode());
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // 実行結果のハンドリング
        if (StringUtils.equals(reqDto.getTradeCd(), ForeignStockTradeClass.BUY_CREDIT_NEW.getValue())) {
            // 画面の取引種別が信用新規買の場合、
            // 銘柄上場ステータスが「上場廃止」：エラーを返す
            if (StringUtils.equals(resApi001.getListedSecuritiesStatus(), ListedSecuritiesStatus.DELISTING.getId())) {
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_DELISTING,
                        IfaCommonUtil.getMessage(API001_ERRORS_DELISTING));
                return resDto;
            }
            // 信用新規買建規制が「規制あり」：エラーを返す
            if (resApi001.getOpenBuyRestrict()) {
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_OPEN_BUY_RESTRICT,
                        IfaCommonUtil.getMessage(API001_ERRORS_OPEN_BUY_RESTRICT));
                return resDto;
            }
        } else if (StringUtils.equals(reqDto.getTradeCd(), ForeignStockTradeClass.SELL_CREDIT_NEW.getValue())) {
            // 画面の取引種別が信用新規売の場合
            // 銘柄上場ステータスが「上場廃止」：エラーを返す
            if (StringUtils.equals(resApi001.getListedSecuritiesStatus(), ListedSecuritiesStatus.DELISTING.getId())) {
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_DELISTING,
                        IfaCommonUtil.getMessage(API001_ERRORS_DELISTING));
                return resDto;
            }
            // 信用新規売建規制が「規制あり」：エラーを返す
            if (resApi001.getOpenSellRestrict()) {
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_OPEN_SELL_RESTRICT,
                        IfaCommonUtil.getMessage(API001_ERRORS_OPEN_SELL_RESTRICT));
                return resDto;
            }
        }
        
        // API003呼出：注意銘柄を取得する。
        // API003のリクエストパラメータを設定する。
        // API003を実行する。
        GetForeignStockAttentionSecuritiesResp resApi003 = new GetForeignStockAttentionSecuritiesResp();
        try {
            resApi003 = foreignStockService.getForeignStockAttentionSecurities(COUNTRY_CODE, reqDto.getBrandCode());
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // API004呼出
        CreateMarketPriceTicketResp resApi004 = new CreateMarketPriceTicketResp();
        try {
            resApi004 = foreignInformationService.createMarketPriceTicket(butenCode, accountNumber, "127.0.0.1", "Edge",
                    COUNTRY_CODE);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // API005呼出
        GetMarketPriceHashResp resApi005 = new GetMarketPriceHashResp();
        try {
            resApi005 = foreignInformationService.getMarketPriceHash(butenCode, accountNumber,
                    resApi004.getPriceTicket(), COUNTRY_CODE);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // API006呼出：株価を取得する。
        // API006のリクエストパラメータを設定する。
        // API006を実行する。
        ListMarketPricesResp resApi006 = new ListMarketPricesResp();
        try {
            resApi006 = foreignInformationService.listMarketPrices(resApi005.getHashValue(), COUNTRY_CODE,
                    new String[] { resApi001.getSecurities().getRic() });
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // API002呼出：期間条件が「期間指定」の場合、日付が9営業日後以内であることをチェックする。
        // API002のリクエストパラメータを設定する。
        // API002を実行する。
        String buySellCode = null;
        if (StringUtils.equals(reqDto.getTradeCd(), "2")) {
            buySellCode = BUY;
        } else if (StringUtils.equals(reqDto.getTradeCd(), "3")) {
            buySellCode = SELL;
        }
        GetForeignStockCreatedMarginOrderInitializationResp resApi002 = new GetForeignStockCreatedMarginOrderInitializationResp();
        try {
            resApi002 = foreignStockService.getForeignStockCreatedMarginOrderInitialization(butenCode, accountNumber,
                    COUNTRY_CODE, reqDto.getBrandCode(), StockTradeType.MARGIN_OPEN.getId(), buySellCode, null);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // API010呼出：期間条件が「期間指定」の場合、日付が9営業日後以内であることをチェックする。
        // API010のリクエストパラメータを設定する。
        // API010を実行する。
        GetMarginPowerHeadlineResp resApi010 = new GetMarginPowerHeadlineResp();
        try {
            resApi010 = foreignAccountService.getMarginPowerHeadline(butenCode, accountNumber, COUNTRY_CODE);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        IfaForeignMarginTradeNewOrderInputA003ResponseDto res = new IfaForeignMarginTradeNewOrderInputA003ResponseDto();
        // FCT029実行
        String judgeResult = executeFct029(reqDto.getBrandCode());
        // 英文開示銘柄判定
        res.setEngPubText(judgeResult);
        
        // API009呼出：売建可能数量を取得する。
        // API009のリクエストパラメータを設定する。
        // API009を実行する。
        ListShortableStocksResp resApi009 = new ListShortableStocksResp();
        try {
            resApi009 = foreignStockService.listShortableStocks(COUNTRY_CODE, reqDto.getBrandCode(), null, null);
        } catch (Exception e) {
            // 画面リクエスト.取引種別が「信用新規売(3)」の場合、API009レスポンスのチェックを行う。
            if (StringUtils.equals(reqDto.getTradeCd(), ForeignStockTradeClass.SELL_CREDIT_NEW.getValue())) {
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
        }
        // 画面リクエスト.取引種別が「信用新規売(3)」の場合、API009レスポンスのチェックを行う。
        if (StringUtils.equals(reqDto.getTradeCd(), ForeignStockTradeClass.SELL_CREDIT_NEW.getValue())) {
            // API009レスポンスの応答結果が0件の場合：エラーを返す。
            if (CollectionUtils.isEmpty(resApi009.getShortableStocks())) {
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API009_NOT_FOUND,
                        IfaCommonUtil.getMessage(API009_NOT_FOUND));
                return resDto;
            }
        }
        
        // 銘柄名
        res.setBrandName(resApi001.getSecurities().getSecuritiesName());
        // 銘柄コード
        res.setBrandCode(resApi001.getSecurities().getSecuritiesCode());
        // 市場略名
        res.setListedMarket(resApi001.getMarket().getMarketShortName());
        // 市場コード
        res.setMarketCode(resApi001.getMarket().getMarketCode());
        // タイムゾーン略名
        res.setTimeZoneAbbreviatedName(resApi001.getMarket().getTimeZoneShortName());
        // 銘柄上場ステータス
        res.setBrandListedStatus(resApi001.getListedSecuritiesStatus());
        // 取引下限数量
        res.setTradeLowerLimitQuantity(resApi001.getTradeLimitMin());
        // 取引上限数量
        res.setTradeUpperLimitQuantity(resApi001.getTradeLimitMax());
        // 取引単位
        res.setTradeUnit(resApi001.getTradeUnit());
        // 銘柄種別
        res.setBrandClass(resApi001.getSecuritiesType());
        // 信用新規買建規制
        res.setMarginNewLongPositionRegulation(resApi001.getOpenBuyRestrict());
        // 信用新規売建規制
        res.setMarginNewShortPositionRegulation(resApi001.getOpenSellRestrict());
        // 注意銘柄
        res.setTradeLimit(resApi003.getAttentionSecurities());
        
        List<IfaForeignMarginTradeNewOrderInputPriceBasicInfo> priceBasicInfoList = new ArrayList<IfaForeignMarginTradeNewOrderInputPriceBasicInfo>();
        for (MarketPrice marketPrice : resApi006.getMarketPrices()) {
            IfaForeignMarginTradeNewOrderInputPriceBasicInfo priceBasicInfo = new IfaForeignMarginTradeNewOrderInputPriceBasicInfo();
            // 価格基本情報.現在値
            priceBasicInfo.setCurrentPrice(marketPrice.getPrice().getLast());
            // 価格基本情報.現在値日時
            priceBasicInfo.setCurrentDateTime(marketPrice.getPrice().getLastDatetime());
            // ティック矢印(アップorダウン)
            priceBasicInfo.setTick(marketPrice.getPrice().getTickArrow());
            // 価格基本情報.前日比
            priceBasicInfo.setDiff(marketPrice.getPrice().getChange());
            // 価格基本情報.前日比(%)
            priceBasicInfo.setRatio(marketPrice.getPrice().getChangePercent());
            // 価格基本情報.始値
            priceBasicInfo.setStart(marketPrice.getPrice().getOpen());
            // 価格基本情報.始値日時
            priceBasicInfo.setStartDate(marketPrice.getPrice().getOpenDatetime());
            // 価格基本情報.高値
            priceBasicInfo.setHigh(marketPrice.getPrice().getHigh());
            // 価格基本情報.高値日時
            priceBasicInfo.setHighDate(marketPrice.getPrice().getHighDatetime());
            // 価格基本情報.安値
            priceBasicInfo.setLow(marketPrice.getPrice().getLow());
            // 価格基本情報.安値日時
            priceBasicInfo.setLowDate(marketPrice.getPrice().getLowDatetime());
            // 価格基本情報.出来高
            priceBasicInfo.setVolume(marketPrice.getPrice().getVolume());
            // 価格基本情報.前日終値
            priceBasicInfo.setLast(marketPrice.getPrice().getPrevClose());
            // 価格基本情報.前日終値日付
            priceBasicInfo.setLastDate(marketPrice.getPrice().getPrevCloseDate());
            priceBasicInfoList.add(priceBasicInfo);
        }
        res.setPriceBasicInfo(priceBasicInfoList);
        
        // 選択可能預り区分リスト
        res.setDepositType(resApi002.getSpecificAccountCodes());
        // 選択可能決済方法リスト
        res.setCurrencyTypeName(resApi002.getSettlementMethodCodes());
        // 選択可能価格条件リスト
        res.setOrderPriceKindList(resApi002.getOrderPriceKindCodes());
        // 選択可能信用期日リスト
        res.setMarginDateList(resApi002.getMarginCloseLimitTypes());
        // 選択可能期間条件リスト
        res.setPeriodRadio(resApi002.getOrderLimitCodes());
        // 有効期間一覧
        res.setPeriodDate(resApi002.getOrderTerms());
        // 取引通貨
        res.setTradeCurrency(resApi002.getTradeCurrencyCode());
        // 在庫株数
        if (CollectionUtils.isEmpty(resApi009.getShortableStocks())) {
            res.setQuantityAvailableForSale(null);
        } else {
            res.setQuantityAvailableForSale(resApi009.getShortableStocks().get(0).getPositionQuantity());
        }
        // 本日の注意銘柄URL
        res.setTradeLimitUrl(medSystemVariableService.getMedSystemVariable(FRS_USSTOCK_URL));
        // 休場日URL
        res.setClosedDay(medSystemVariableService.getMedSystemVariable(FRS_USSEC_CLOSED_URL));
        // 円貨決済停止日URL
        res.setYenClosed(medSystemVariableService.getMedSystemVariable(FRS_YEN_CLOSED_URL));
        // 取扱銘柄一覧URL
        res.setUsequityList(medSystemVariableService.getMedSystemVariable(FRS_USEQUITY_LIST_URL));
        // お取引注意事項URL
        res.setTradingAttention(medSystemVariableService.getMedSystemVariable(FRS_STOCK_QAI_22_URL));
        // 信用建余力
        res.setForeignMarginPositionPower(resApi010.getMarginBuyingPower());
        // 預託率
        res.setCollateralTransferMarginDepositRateAfter(resApi010.getDepositRate());
        
        resDtoList.add(res);
        resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        
        return resDto;
    }
    
    /**
     * アクションID：A005
     * アクション名：更新
     * Dto リクエスト：IfaForeignMarginTradeNewOrderInputA005RequestDto
     * Dto レスポンス：IfaForeignMarginTradeNewOrderInputA005ResponseDto
     *
     * @param reqDto リクエストパラメータ
     * @return 実行結果
     * @exception Exception 業務例外
     */
    public DataList<IfaForeignMarginTradeNewOrderInputA005ResponseDto> updateA005(
            IfaForeignMarginTradeNewOrderInputA005RequestDto reqDto) throws Exception {
        
        DataList<IfaForeignMarginTradeNewOrderInputA005ResponseDto> resDto = new DataList<IfaForeignMarginTradeNewOrderInputA005ResponseDto>();
        List<IfaForeignMarginTradeNewOrderInputA005ResponseDto> resDtoList = new ArrayList<IfaForeignMarginTradeNewOrderInputA005ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginTradeNewOrderInputServiceImplL.updateA005");
        }
        
        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        String butenCode = customerCommon.getButenCode();
        String accountNumber = customerCommon.getAccountNumber();
        // FCT001実行
        resDto = executeFct001(butenCode, accountNumber);
        // 正常終了以外の場合、エラー内容を返却して処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        // FCT003実行
        resDto = executeFct003(butenCode, accountNumber, reqDto.getTradeCd());
        // 正常終了以外の場合、エラー内容を返却して処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        // 外貨建口座取引開設状況のチェック
        resDto = checkForeignStockTradeAccountOpenStatus(customerCommon.getForeignStockTradeAccountOpenStatus());
        // 正常終了ではない場合、処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        // 外国信用口座開設状況のチェック
        resDto = checkForeignMarginAccountype(customerCommon.getForeignMarginAccountType());
        // 正常終了ではない場合、処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        // API010を実行する。
        GetMarginPowerHeadlineResp resApi010 = new GetMarginPowerHeadlineResp();
        try {
            resApi010 = foreignAccountService.getMarginPowerHeadline(butenCode, accountNumber, COUNTRY_CODE);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // API001呼出：数量をチェックする。
        // 銘柄情報を取得する。
        // API001のリクエストパラメータを設定する。
        // API001を実行する。
        GetForeignStockSecuritiesResp resApi001 = new GetForeignStockSecuritiesResp();
        try {
            resApi001 = foreignStockService.getForeignStockSecurities(COUNTRY_CODE, reqDto.getBrandCode());
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // 実行結果のハンドリング
        if (StringUtils.equals(reqDto.getTradeCd(), ForeignStockTradeClass.BUY_CREDIT_NEW.getValue())) {
            // 画面の取引種別が信用新規買の場合、
            // 銘柄上場ステータスが「上場廃止」：エラーを返す
            if (StringUtils.equals(resApi001.getListedSecuritiesStatus(), ListedSecuritiesStatus.DELISTING.getId())) {
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_DELISTING,
                        IfaCommonUtil.getMessage(API001_ERRORS_DELISTING));
                return resDto;
            }
            // 信用新規買建規制が「規制あり」：エラーを返す
            if (resApi001.getOpenBuyRestrict()) {
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_OPEN_BUY_RESTRICT,
                        IfaCommonUtil.getMessage(API001_ERRORS_OPEN_BUY_RESTRICT));
                return resDto;
            }
        } else if (StringUtils.equals(reqDto.getTradeCd(), ForeignStockTradeClass.SELL_CREDIT_NEW.getValue())) {
            // 画面の取引種別が信用新規売の場合
            // 銘柄上場ステータスが「上場廃止」：エラーを返す
            if (StringUtils.equals(resApi001.getListedSecuritiesStatus(), ListedSecuritiesStatus.DELISTING.getId())) {
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_DELISTING,
                        IfaCommonUtil.getMessage(API001_ERRORS_DELISTING));
                return resDto;
            }
            // 信用新規売建規制が「規制あり」：エラーを返す
            if (resApi001.getOpenSellRestrict()) {
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_OPEN_SELL_RESTRICT,
                        IfaCommonUtil.getMessage(API001_ERRORS_OPEN_SELL_RESTRICT));
                return resDto;
            }
        }
        
        // API004呼出
        CreateMarketPriceTicketResp resApi004 = new CreateMarketPriceTicketResp();
        try {
            resApi004 = foreignInformationService.createMarketPriceTicket(butenCode, accountNumber, "127.0.0.1", "Edge",
                    COUNTRY_CODE);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // API005呼出
        GetMarketPriceHashResp resApi005 = new GetMarketPriceHashResp();
        try {
            resApi005 = foreignInformationService.getMarketPriceHash(butenCode, accountNumber,
                    resApi004.getPriceTicket(), COUNTRY_CODE);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // API006呼出：株価を取得する。
        // API006のリクエストパラメータを設定する。
        // API006を実行する。
        ListMarketPricesResp resApi006 = new ListMarketPricesResp();
        try {
            resApi006 = foreignInformationService.listMarketPrices(resApi005.getHashValue(), COUNTRY_CODE,
                    new String[] { resApi001.getSecurities().getRic() });
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // API009呼出：売建可能数量を取得する。
        // API009のリクエストパラメータを設定する。
        // API009を実行する。
        ListShortableStocksResp resApi009 = new ListShortableStocksResp();
        try {
            resApi009 = foreignStockService.listShortableStocks(COUNTRY_CODE, reqDto.getBrandCode(), null, null);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        // 画面リクエスト.取引種別が「信用新規売(3)」の場合、API009レスポンスのチェックを行う。
        if (StringUtils.equals(reqDto.getTradeCd(), ForeignStockTradeClass.SELL_CREDIT_NEW.getValue())) {
            // API009レスポンスの応答結果が0件の場合：エラーを返す。
            if (CollectionUtils.isEmpty(resApi009.getShortableStocks())) {
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API009_NOT_FOUND,
                        IfaCommonUtil.getMessage(API009_NOT_FOUND));
                return resDto;
            }
        }
        
        IfaForeignMarginTradeNewOrderInputA005ResponseDto res = new IfaForeignMarginTradeNewOrderInputA005ResponseDto();
        List<IfaForeignMarginTradeNewOrderInputPriceBasicInfo> priceBasicInfoList = new ArrayList<>();
        for (MarketPrice marketPrice : resApi006.getMarketPrices()) {
            IfaForeignMarginTradeNewOrderInputPriceBasicInfo priceBasicInfo = new IfaForeignMarginTradeNewOrderInputPriceBasicInfo();
            // 価格基本情報.現在値T
            priceBasicInfo.setCurrentPrice(marketPrice.getPrice().getLast());
            // 価格基本情報.現在値日時
            priceBasicInfo.setCurrentDateTime(marketPrice.getPrice().getLastDatetime());
            // ティック矢印(アップorダウン)
            priceBasicInfo.setTick(marketPrice.getPrice().getTickArrow());
            // 価格基本情報.前日比
            priceBasicInfo.setDiff(marketPrice.getPrice().getChange());
            // 価格基本情報.前日比(%)
            priceBasicInfo.setRatio(marketPrice.getPrice().getChangePercent());
            // 価格基本情報.始値
            priceBasicInfo.setStart(marketPrice.getPrice().getOpen());
            // 価格基本情報.始値日時
            priceBasicInfo.setStartDate(marketPrice.getPrice().getOpenDatetime());
            // 価格基本情報.高値
            priceBasicInfo.setHigh(marketPrice.getPrice().getHigh());
            // 価格基本情報.高値日時
            priceBasicInfo.setHighDate(marketPrice.getPrice().getHighDatetime());
            // 価格基本情報.安値
            priceBasicInfo.setLow(marketPrice.getPrice().getLow());
            // 価格基本情報.安値日時
            priceBasicInfo.setLowDate(marketPrice.getPrice().getLowDatetime());
            // 価格基本情報.出来高
            priceBasicInfo.setVolume(marketPrice.getPrice().getVolume());
            // 価格基本情報.前日終値
            priceBasicInfo.setLast(marketPrice.getPrice().getPrevClose());
            // 価格基本情報.前日終値日付
            priceBasicInfo.setLastDate(marketPrice.getPrice().getPrevCloseDate());
            priceBasicInfoList.add(priceBasicInfo);
        }
        res.setPriceBasicInfo(priceBasicInfoList);
        
        // 在庫株数
        if (CollectionUtils.isEmpty(resApi009.getShortableStocks())) {
            res.setQuantityAvailableForSale(null);
        } else {
            res.setQuantityAvailableForSale(resApi009.getShortableStocks().get(0).getPositionQuantity());
        }
        // 信用建余力
        res.setForeignMarginPositionPower(resApi010.getMarginBuyingPower());
        // 預託率
        res.setCollateralTransferMarginDepositRateAfter(resApi010.getDepositRate());
        
        resDtoList.add(res);
        resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        
        return resDto;
    }
    
    /**
     * アクションID：A012
     * アクション名：注文確認
     * Dto リクエスト：IfaForeignMarginTradeNewOrderInputA012RequestDto
     * Dto レスポンス：IfaForeignMarginTradeNewOrderInputA012ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param reqDto リクエストパラメータ
     * @return 実行結果
     * @exception Exception 業務例外
     */
    public DataList<IfaForeignMarginTradeNewOrderInputA012ResponseDto> orderConfirmA012(
            IfaForeignMarginTradeNewOrderInputA012RequestDto reqDto) throws Exception {
        
        DataList<IfaForeignMarginTradeNewOrderInputA012ResponseDto> resDto = new DataList<IfaForeignMarginTradeNewOrderInputA012ResponseDto>();
        List<IfaForeignMarginTradeNewOrderInputA012ResponseDto> resDtoList = new ArrayList<IfaForeignMarginTradeNewOrderInputA012ResponseDto>();
        IfaForeignMarginTradeNewOrderInputA012ResponseDto res = new IfaForeignMarginTradeNewOrderInputA012ResponseDto();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginTradeNewOrderInputServiceImplL.orderConfirmA012");
        }
        
        // FCT018：委託注文サービス時間のチェックを行う
        resDto = executeFct018();
        // 正常終了以外の場合、エラー内容を返却して処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        String butenCode = customerCommon.getButenCode();
        String accountNumber = customerCommon.getAccountNumber();
        // FCT001実行
        resDto = executeFct001(butenCode, accountNumber);
        // 正常終了以外の場合、エラー内容を返却して処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        // FCT003実行
        resDto = executeFct003(butenCode, accountNumber, reqDto.getTradeCd());
        // 正常終了以外の場合、エラー内容を返却して処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        // 外貨建口座取引開設状況のチェック
        resDto = checkForeignStockTradeAccountOpenStatus(customerCommon.getForeignStockTradeAccountOpenStatus());
        // 正常終了ではない場合、処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        // 外国信用口座開設状況のチェック
        resDto = checkForeignMarginAccountype(customerCommon.getForeignMarginAccountType());
        // 正常終了ではない場合、処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        // API001呼出：数量をチェックする。
        // 銘柄情報を取得する。
        // API001のリクエストパラメータを設定する。
        // API001を実行する。
        GetForeignStockSecuritiesResp resApi001 = new GetForeignStockSecuritiesResp();
        try {
            resApi001 = foreignStockService.getForeignStockSecurities(COUNTRY_CODE, reqDto.getBrandCode());
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // 実行結果のハンドリング
        if (StringUtils.equals(reqDto.getTradeCd(), ForeignStockTradeClass.BUY_CREDIT_NEW.getValue())) {
            // 画面の取引種別が信用新規買の場合、
            // 銘柄上場ステータスが「上場廃止」：エラーを返す
            if (StringUtils.equals(resApi001.getListedSecuritiesStatus(), ListedSecuritiesStatus.DELISTING.getId())) {
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_DELISTING,
                        IfaCommonUtil.getMessage(API001_ERRORS_DELISTING));
                return resDto;
            }
            // 信用新規買建規制が「規制あり」：エラーを返す
            if (resApi001.getOpenBuyRestrict()) {
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_OPEN_BUY_RESTRICT,
                        IfaCommonUtil.getMessage(API001_ERRORS_OPEN_BUY_RESTRICT));
                return resDto;
            }
        } else if (StringUtils.equals(reqDto.getTradeCd(), ForeignStockTradeClass.SELL_CREDIT_NEW.getValue())) {
            // 画面の取引種別が信用新規売の場合
            // 銘柄上場ステータスが「上場廃止」：エラーを返す
            if (StringUtils.equals(resApi001.getListedSecuritiesStatus(), ListedSecuritiesStatus.DELISTING.getId())) {
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_DELISTING,
                        IfaCommonUtil.getMessage(API001_ERRORS_DELISTING));
                return resDto;
            }
            // 信用新規売建規制が「規制あり」：エラーを返す
            if (resApi001.getOpenSellRestrict()) {
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_OPEN_SELL_RESTRICT,
                        IfaCommonUtil.getMessage(API001_ERRORS_OPEN_SELL_RESTRICT));
                return resDto;
            }
        }
        
        BigDecimal limitMax = new BigDecimal(resApi001.getTradeLimitMax());
        if (limitMax.compareTo(BigDecimal.ZERO) == 0) {
            limitMax = LIMIT_MAX;
        }
        // リクエストパラメータ.注文数量 ＞ API001.取引上限数量の場合
        if (new BigDecimal(reqDto.getForeignQuantity()).compareTo(limitMax) > 0) {
            // レスポンスにエラーを設定する
            resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_OUT_OF_RANGE,
                    IfaCommonUtil.getMessage(API001_ERRORS_OUT_OF_RANGE,
                            new String[] { ORDER_COUNT, resApi001.getTradeLimitMin(), limitMax.toString() }));
            return resDto;
        }
        // リクエストパラメータ.注文数量 ＜ API001.取引下限数量の場合
        if (new BigDecimal(reqDto.getForeignQuantity()).compareTo(new BigDecimal(resApi001.getTradeLimitMin())) < 0) {
            // レスポンスにエラーを設定する
            resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_OUT_OF_RANGE,
                    IfaCommonUtil.getMessage(API001_ERRORS_OUT_OF_RANGE,
                            new String[] { ORDER_COUNT, resApi001.getTradeLimitMin(), limitMax.toString() }));
            return resDto;
        }
        
        // 数量が、取引単位で割り切れることをチェックする。
        // リクエストパラメータ.注文数量 % API001.取引単位 != 0の場合
        if (new BigDecimal(reqDto.getForeignQuantity())
                .remainder(new BigDecimal(resApi001.getTradeUnit())) != BigDecimal.ZERO) {
            // レスポンスにエラーを設定する
            resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_INCONSITENT,
                    IfaCommonUtil.getMessage(API001_ERRORS_INCONSITENT,
                            new String[] { ORDER_COUNT, TRADE_UNIT, resApi001.getTradeUnit() }));
            return resDto;
        }
        
        // API003呼出：注意銘柄を取得する。
        // API003のリクエストパラメータを設定する。
        // API003を実行する。
        GetForeignStockAttentionSecuritiesResp resApi003 = new GetForeignStockAttentionSecuritiesResp();
        try {
            resApi003 = foreignStockService.getForeignStockAttentionSecurities(COUNTRY_CODE, reqDto.getBrandCode());
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        // 注意銘柄=true　の場合に取引注意情報（銘柄）メッセージをセット
        if (resApi003.getAttentionSecurities()) {
            // 取引注意情報（銘柄）メッセージ
            res.setTradeNoticeInformationBrand(IfaCommonUtil.getMessage(WARNINGS_DMS_INFORMATIONCHECK));
        } else {
            res.setTradeNoticeInformationBrand("");
        }
        
        // API004呼出
        CreateMarketPriceTicketResp resApi004 = new CreateMarketPriceTicketResp();
        try {
            resApi004 = foreignInformationService.createMarketPriceTicket(butenCode, accountNumber, "127.0.0.1", "Edge",
                    COUNTRY_CODE);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // API005呼出
        GetMarketPriceHashResp resApi005 = new GetMarketPriceHashResp();
        try {
            resApi005 = foreignInformationService.getMarketPriceHash(butenCode, accountNumber,
                    resApi004.getPriceTicket(), COUNTRY_CODE);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // API006呼出：株価を取得する。
        // API006のリクエストパラメータを設定する。
        // API006を実行する。
        ListMarketPricesResp resApi006 = new ListMarketPricesResp();
        try {
            resApi006 = foreignInformationService.listMarketPrices(resApi005.getHashValue(), COUNTRY_CODE,
                    new String[] { resApi001.getSecurities().getRic() });
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        if (StringUtils.equals(reqDto.getOrderPriceKindList(), PriceConditons.LIMIT.getValue())) {
            // "注文単価（指値）が、0以上　999999999.99以下の範囲にあることをチェックする。
            resDto = checkOrderPrice(reqDto.getHiddenOrderPrice(), ORDER_PRICE_ASKING_PRICE);
            // 正常終了ではない場合、処理を終了する。
            if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
                return resDto;
            }
        } else if (!StringUtils.equals(reqDto.getOrderPriceKindList(), PriceConditons.MARKET.getValue())) {
            if (StringUtils.equals(reqDto.getOrderPriceKindList(), PriceConditons.STOP_PRICE_LIMIT.getValue())) {
                // 注文単価（逆指値）が、0以上　999999999.99以下の範囲にあることをチェックする。
                resDto = checkOrderPrice(reqDto.getHiddenOrderPriceReversePriceLimit(), ORDER_PRICE_STOP_LOSS);
                // 正常終了ではない場合、処理を終了する。
                if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
                    return resDto;
                }
            }
            // 発火条件価格（逆指値）が、0以上　999999999.99以下の範囲にあることをチェックする。
            resDto = checkOrderPrice(reqDto.getOrderInputAreaPriceTermsreversePriceLimitStopOrderPrice(),
                    ORDER_PRICE_REFERENCE_PRICE);
            // 正常終了ではない場合、処理を終了する。
            if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
                return resDto;
            }
        }
        
        // API002呼出：期間条件が「期間指定」の場合、日付が9営業日後以内であることをチェックする。
        // API002のリクエストパラメータを設定する。
        // API002を実行する。
        String buySellCode = null;
        if (StringUtils.equals(reqDto.getTradeCd(), "2")) {
            buySellCode = BUY;
        } else if (StringUtils.equals(reqDto.getTradeCd(), "3")) {
            buySellCode = SELL;
        }
        GetForeignStockCreatedMarginOrderInitializationResp resApi002 = new GetForeignStockCreatedMarginOrderInitializationResp();
        try {
            resApi002 = foreignStockService.getForeignStockCreatedMarginOrderInitialization(butenCode, accountNumber,
                    COUNTRY_CODE, reqDto.getBrandCode(), StockTradeType.MARGIN_OPEN.getId(), buySellCode, null);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // リクエストパラメータ.期間条件=="1" （"CARRY_OVER_ORDER"（期間指定））の場合
        if (StringUtils.equals(reqDto.getPeriodRadio(), CARRY_OVER_ORDER)) {
            if (null == resApi002.getOrderTerms() || resApi002.getOrderTerms().size() == 0) {
                // レスポンスに注文可能期間エラーを設定する
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_TERMS_OUTOFRANGE,
                        IfaCommonUtil.getMessage(ERRORS_TERMS_OUTOFRANGE));
                return resDto;
            }
            int orderTermSize = resApi002.getOrderTerms().size() > MAX_ORDER_TERM_LIST ? MAX_ORDER_TERM_LIST
                    : resApi002.getOrderTerms().size();
            List<String> orderTermList = resApi002.getOrderTerms().subList(0, orderTermSize);
            // リクエストパラメータ.期間 ＞ API002.有効期間一覧の場合（9営業日後以降の場合）
            if (!orderTermList.contains(DateFormatUtil.dateFormatToHyphen(reqDto.getPeriod()))) {
                // レスポンスに注文可能期間エラーを設定する
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_TERMS_OUTOFRANGE,
                        IfaCommonUtil.getMessage(ERRORS_TERMS_OUTOFRANGE));
                return resDto;
            }
        }
        
        // FCT021：口座の取引制限チェックを行う。   
        InputFct021Dto reqFct021 = new InputFct021Dto();
        reqFct021.setButenCode(butenCode);
        reqFct021.setAccountNumber(accountNumber);
        reqFct021.setProductCd(FOREIGN_STOCKS);
        reqFct021.setTradeCd(reqDto.getTradeCd());
        reqFct021.setCountryCd(COUNTRY_CODE);
        reqFct021.setCurrencyCode(CURRENCY_CODE);
        OutputFct021Dto resFct021 = fct021.doCheck(reqFct021);
        resDto = executeFct021(resFct021);
        // 正常終了、アラート終了以外の場合、エラー内容を返却して処理を終了する。
        if (resDto.getErrorLevel() == ErrorLevel.FATAL.getId()) {
            return resDto;
        }
        
        ConfirmForeignStockCreatedMarginOrderResp resApi007 = new ConfirmForeignStockCreatedMarginOrderResp();
        MarginOrderInput moi = new MarginOrderInput();
        moi.setCountryCode(COUNTRY_CODE);
        moi.setMarketCode(reqDto.getMarketCode());
        moi.setSecuritiesCode(reqDto.getBrandCode());
        moi.setBuySellCode(buySellCode);
        moi.setOrderQuantity(reqDto.getForeignQuantity());
        moi.setOrderPriceKindCode(reqDto.getOrderPriceKindList());
        // 価格条件の場合
        if (StringUtils.equals(reqDto.getOrderPriceKindList(), PriceConditons.LIMIT.getValue())) {
            // 価格条件が「指値」の場合
            moi.setOrderPrice(reqDto.getHiddenOrderPrice());
            
            // 価格条件(逆指値)の場合
        } else if (!StringUtils.equals(reqDto.getOrderPriceKindList(), PriceConditons.MARKET.getValue())) {
            // 価格条件が「逆指値/指値」の場合
            if (StringUtils.equals(reqDto.getOrderPriceKindList(), PriceConditons.STOP_PRICE_LIMIT.getValue())) {
                moi.setOrderPrice(reqDto.getHiddenOrderPriceReversePriceLimit());
            }
            // 価格条件が「逆指値」の場合のみ
            moi.setStopPrice(reqDto.getOrderInputAreaPriceTermsreversePriceLimitStopOrderPrice());
        }
        
        moi.setOrderLimitCode(reqDto.getPeriodRadio());
        if (StringUtils.equals(reqDto.getPeriodRadio(), CARRY_OVER_ORDER)) {
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date period = sdFormat.parse(reqDto.getPeriod());
            SimpleDateFormat sdFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            moi.setOrderTerm(sdFormat2.format(period));
        }
        moi.setSpecificAccountCode(reqDto.getDepositType());
        moi.setSettlementMethodCode(reqDto.getKessaiHoho());
        moi.setMarginCloseLimitType(reqDto.getMarginDueDate());
        // API007呼出：注文確認を行う。
        try {
            resApi007 = foreignStockService.confirmForeignStockCreatedMarginOrder(butenCode, accountNumber, null, null,
                    "PHONE", moi);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // 注文ワーニングステータス
        res.setMethodCheckMessage("");
        for (String warnStatusId : resApi007.getWarningStatuses()) {
            
            // 逆指値注文即時発火あり：逆指値注文即時発火メッセージを格納して次の処理へ
            if (warnStatusId == WarningStatuses.STOP_ORDER_TRIGGERED.getId()) {
                res.setMethodCheckMessage(IfaCommonUtil.getMessage(WarningStatuses.valueOfId(warnStatusId).getName()));
                continue;
            }
            // 増し担保規制銘柄取引あり：増し担保規制銘柄取引メッセージを格納して次の処理へ
            if (warnStatusId == WarningStatuses.ADDITIONAL_COLLATERAL_TRADE.getId()) {
                res.setMethodCheckMessage(IfaCommonUtil.getMessage(WarningStatuses.valueOfId(warnStatusId).getName()));
                continue;
            }
        }
        /*
         API007「現地約定日」を、注文当日の日付と比較する。
         現地約定日が注文当日より大きい：現地約定日超過メッセージを格納して次の処理へ
         注文当日はシステム日付で実装（基本設計書に注文当日の項目がない）
         */
        if (checkOrderDate(resApi007.getMarginOrder().getOrder().getFrnTradeDate())) {
            res.setLocalTradeDateLimitMsg(IfaCommonUtil.getMessage(WARNINGS_NEXT_BUSINESS_DAY));
        } else {
            res.setLocalTradeDateLimitMsg("");
        }
        
        // 注文番号
        res.setOrderNumber(resApi007.getMarginOrder().getOrder().getOrderNo());
        // 注文Sub番号
        res.setOrderSubNumber(resApi007.getMarginOrder().getOrder().getOrderSubNo());
        // 銘柄情報
        res.setBrandInformation(resApi007.getMarginOrder().getOrder().getSecurities());
        // 取引通貨
        res.setTradeCurrency(resApi007.getMarginOrder().getOrder().getTradeCurrencyCode());
        // 市場情報
        res.setMarketInformation(resApi007.getMarginOrder().getOrder().getMarket());
        // 売買区分
        res.setTradeKbn(resApi007.getMarginOrder().getOrder().getBuySellCode());
        // 自動買付区分
        res.setAutobuyKbn(resApi007.getMarginOrder().getOrder().getAutoStockOrderType());
        // 注文数量
        res.setForeignQuantity(resApi007.getMarginOrder().getOrder().getOrderQuantity());
        // 価格条件
        res.setOrderPriceKindList(resApi007.getMarginOrder().getOrder().getOrderPriceKindCode());
        // 注文単価
        res.setHiddenOrderPrice(resApi007.getMarginOrder().getOrder().getOrderPrice());
        // 発火条件価格
        res.setOrderInputAreaPriceTermsreversePriceLimitStopOrderPrice(
                resApi007.getMarginOrder().getOrder().getStopPrice());
        // トレールストップ幅
        res.setTrailStopWidth(resApi007.getMarginOrder().getOrder().getTrailingStopAmount());
        // 成行基準価格
        res.setExecuteBasePrice(resApi007.getMarginOrder().getOrder().getNoLimitPrice());
        // 期間条件
        res.setPeriodRadio(resApi007.getMarginOrder().getOrder().getOrderLimitCode());
        // 期間
        res.setPeriod(resApi007.getMarginOrder().getOrder().getOrderTerm());
        // 預り区分
        res.setDepositType(resApi007.getMarginOrder().getOrder().getSpecificAccountCode());
        // 決済区分
        res.setSettlementType(resApi007.getMarginOrder().getOrder().getSettlementMethodCode());
        // 決済通貨
        res.setSettlementCurrencyCode(resApi007.getMarginOrder().getOrder().getSettlementCurrencyCode());
        // 為替レート
        res.setFxRate(resApi007.getMarginOrder().getOrder().getExchangeRate());
        // 平均約定単価
        res.setAverageTradePrice(resApi007.getMarginOrder().getOrder().getExecutionAveragePrice());
        // 未約定数量
        res.setUnTradeQuantity(resApi007.getMarginOrder().getOrder().getUnexecutedQuantity());
        // 約定数量
        res.setTradeQuantity(resApi007.getMarginOrder().getOrder().getExecutionQuantity());
        // 約定金額（外貨）
        res.setApproximatePositionAmount(resApi007.getMarginOrder().getOrder().getFrnGrossAmount());
        // 約定金額（円貨）
        res.setTradeAmountYen(resApi007.getMarginOrder().getOrder().getGrossAmount());
        // 受渡金額（外貨）
        res.setDeliveryAmountForeign(resApi007.getMarginOrder().getOrder().getFrnNetAmount());
        // 受渡金額（円貨）
        res.setDeliveryAmountYen(resApi007.getMarginOrder().getOrder().getNetAmount());
        // 受渡金額（約定分）
        res.setContractDeliveryAmount(resApi007.getMarginOrder().getOrder().getExecutionNetAmount());
        // 国内手数料（外貨）
        res.setDomesticCommForeign(resApi007.getMarginOrder().getOrder().getFrnCommissionAmount());
        // 国内手数料（円貨）
        res.setDomesticCommJpAmount(resApi007.getMarginOrder().getOrder().getCommissionAmount());
        // 国内消費税（外貨）
        res.setDomesticConsumptionTaxForeign(resApi007.getMarginOrder().getOrder().getFrnCommissionCtax());
        // 国内消費税（円貨）
        res.setDomesticConsumptionTaxJpAmount(resApi007.getMarginOrder().getOrder().getCommissionCtax());
        // 現地手数料等（外貨）
        res.setLocalCommForeign(resApi007.getMarginOrder().getOrder().getFrnLocalCharge());
        // 現地手数料等（円貨）
        res.setLocalCommJpAmount(resApi007.getMarginOrder().getOrder().getLocalCharge());
        // 現地精算金額（外貨）
        res.setLocalSettlementAmountForeign(resApi007.getMarginOrder().getOrder().getFrnLocalNetAmount());
        // 現地精算金額（円貨）
        res.setLocalSettlementJpAmount(resApi007.getMarginOrder().getOrder().getLocalNetAmount());
        // 概算譲渡益税
        res.setApproximateCapitalGainsTax(resApi007.getMarginOrder().getOrder().getSpecificTax());
        // 注文状況
        res.setOrderStatus(resApi007.getMarginOrder().getOrder().getOrderStatus());
        // 約定状況
        res.setTradeStatus(resApi007.getMarginOrder().getOrder().getExecutionStatus());
        // 発火状況
        res.setStopOrderStatus(resApi007.getMarginOrder().getOrder().getWorkingStatus());
        // 国内約定日
        res.setDomesticTradeDate(resApi007.getMarginOrder().getOrder().getTradeDate());
        // 国内受渡日
        res.setDomesticSettlementDate(resApi007.getMarginOrder().getOrder().getValueDate());
        // 現地約定日
        res.setLocalTradeDate(resApi007.getMarginOrder().getOrder().getFrnTradeDate());
        // 現地受渡日
        res.setForeignSettlementDate(resApi007.getMarginOrder().getOrder().getFrnValueDate());
        // 注文日時
        res.setOrderDate(resApi007.getMarginOrder().getOrder().getOrderInputDatetime());
        // 約定日時
        res.setTradeDateTime(resApi007.getMarginOrder().getOrder().getExecutionDatetime());
        // 失効日時
        res.setRevocationDataTime(resApi007.getMarginOrder().getOrder().getExpiredDatetime());
        // 株取引区分
        res.setStockTradeType(resApi007.getMarginOrder().getOrder().getStockTradeType());
        // 信用期日
        res.setMarginDueDate(resApi007.getMarginOrder().getMarginCloseLimitType());
        // 決済損益
        res.setSettlement(resApi007.getMarginOrder().getClosedProfitLoss());
        // 保証金不足金額
        res.setDepositDeficientAmount(resApi007.getMarginOrder().getMarginDeficitAmount());
        // 振替預り金額
        res.setTransferDepositAmount(resApi007.getMarginOrder().getTransferDepositAmount());
        // 振替有価証券評価額
        res.setTransferValuableSecurityValuation(resApi007.getMarginOrder().getTransferEvaluationAmount());
        // 見積価格
        res.setQuotePrice(resApi007.getEstimatePrice());
        // 適用金利率
        res.setApplicableInterestRate(resApi007.getAppliedInterestRate());
        // 適用貸株料率
        res.setApplicableStockLendingFeeRate(resApi007.getAppliedLendingFeeRate());
        // 信用建余力（注文後）
        res.setForeignMarginPositionPower(resApi007.getAfterPower().getMarginBuyingPower());
        // 委託保証金率（注文後預託率）
        res.setMarginDepositRateOrderAfter(resApi007.getAfterPower().getDepositRate());
        res.setMethodCheckMessage("");
        res.setAdditionalCollateralRegulationBrandTradeMsg("");
        for (String warning : resApi007.getWarningStatuses()) {
            // 逆指値注文即時発火メッセージ
            if (StringUtils.equals(warning, MarginOrderWarningStatus.STOP_ORDER_TRIGGERED.getId())) {
                res.setMethodCheckMessage(IfaCommonUtil.getMessage(WARNINGS_FRS_STOP_PRICE_CONDITION_CONFIRM));
            }
            // 増し担保規制銘柄取引メッセージ
            if (StringUtils.equals(warning, MarginOrderWarningStatus.ADDITIONAL_COLLATERAL_TRADE.getId())) {
                res.setAdditionalCollateralRegulationBrandTradeMsg(IfaCommonUtil.getMessage(WARNINGS_FRS_ADDITIONAL_COLLATERAL_TRADE_CONFIRM));
            }
        }
        // 注意銘柄
        res.setTradeLimit(resApi003.getAttentionSecurities());
        
        IfaForeignMarginTradeNewOrderInputBrand brand = new IfaForeignMarginTradeNewOrderInputBrand();
        // 銘柄情報.銘柄名
        brand.setBrandName(resApi001.getSecurities().getSecuritiesName());
        // 銘柄情報.銘柄コード
        brand.setBrandCode(resApi001.getSecurities().getSecuritiesCode());
        res.setBrand(brand);
        
        IfaForeignMarginTradeNewOrderInputMarket marketList = new IfaForeignMarginTradeNewOrderInputMarket();
        // 市場情報.市場コード
        marketList.setMarketCode(resApi001.getMarket().getMarketCode());
        // 市場情報.市場略名
        marketList.setListedMarket(resApi001.getMarket().getMarketShortName());
        // 市場情報.国コード
        marketList.setCountryCode(resApi001.getMarket().getCountryCode());
        // 通貨コード
        res.setCurrencyCode(resApi001.getCurrencyCode());
        // 市場.タイムゾーン略名
        marketList.setTimeZoneAbbreviatedName(resApi001.getMarket().getTimeZoneShortName());
        res.setMarketList(marketList);
        // 銘柄種別
        res.setBrandClass(resApi001.getSecuritiesType());
        
        List<IfaForeignMarginTradeNewOrderInputPriceBasicInfo> priceBasicInfoList = new ArrayList<>();
        for (MarketPrice marketPrice : resApi006.getMarketPrices()) {
            IfaForeignMarginTradeNewOrderInputPriceBasicInfo priceBasicInfo = new IfaForeignMarginTradeNewOrderInputPriceBasicInfo();
            // 価格基本情報.現在値
            priceBasicInfo.setCurrentPrice(marketPrice.getPrice().getLast());
            // 価格基本情報.現在値日時
            priceBasicInfo.setCurrentDateTime(marketPrice.getPrice().getLastDatetime());
            // ティック矢印(アップorダウン)
            priceBasicInfo.setTick(marketPrice.getPrice().getTickArrow());
            // 価格基本情報.前日比
            priceBasicInfo.setDiff(marketPrice.getPrice().getChange());
            // 価格基本情報.前日比(%)
            priceBasicInfo.setRatio(marketPrice.getPrice().getChangePercent());
            // 価格基本情報.始値
            priceBasicInfo.setStart(marketPrice.getPrice().getOpen());
            // 価格基本情報.高値
            priceBasicInfo.setHigh(marketPrice.getPrice().getHigh());
            // 価格基本情報.安値
            priceBasicInfo.setLow(marketPrice.getPrice().getLow());
            // 価格基本情報.出来高
            priceBasicInfo.setVolume(marketPrice.getPrice().getVolume());
            // 価格基本情報.前日終値
            priceBasicInfo.setLast(marketPrice.getPrice().getPrevClose());
            // 価格基本情報.前日終値日付
            priceBasicInfo.setLastDate(marketPrice.getPrice().getPrevCloseDate());
            priceBasicInfoList.add(priceBasicInfo);
        }
        res.setPriceBasicInfo(priceBasicInfoList);
        
        // 勧誘区分
        res.setKanyuKbn(reqDto.getKanyuKbn());
        // 受注方法
        res.setReceiveOrderType(reqDto.getReceiveOrderType());
        // 重要事項の説明
        res.setImportantMatterType(reqDto.getImportantMatterType());
        // 英文開示銘柄
        res.setEngPubText(reqDto.getEngPubText());
        // 確認項目.インサイダー確認
        res.setCheckInsider(reqDto.getCheckInsider());
        // 注意情報アラート
        if (StringUtils.equals(resFct021.getNoteInfoAlertFlag(), EXIST)) {
            res.setNoticeInfoAlert(IfaCommonUtil.getMessage(
                    FCT021_WARNINGS_NOTICE_WARNING_CHECK,
                    new String[] { codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "5", "1") }
            ));
        } else {
            res.setNoticeInfoAlert("");
        }
        // お知らせアラート
        if (StringUtils.equals(resFct021.getNoteLimitAlertFlag(), EXIST)) {
            res.setNoticeAlert(IfaCommonUtil.getMessage(FCT021_WARNINGS_INFORMATION_CHECK));
        } else {
            res.setNoticeAlert("");
        }
        // 本日の注意銘柄URL
        res.setTradeLimitUrl(reqDto.getTradeLimit());
        // 休場日URL
        res.setClosedDay(reqDto.getClosedDay());
        // 円貨決済停止日URL
        res.setYenClosed(reqDto.getYenClosed());
        // 取扱銘柄一覧URL
        res.setUsequityList(reqDto.getUsequityList());
        // お取引注意事項URL
        res.setTradingAttention(reqDto.getTradingAttention());
        // 取引種別
        res.setTradeCd(reqDto.getTradeCd());
        
        resDtoList.add(res);
        if (resDto.getErrorLevel() != ErrorLevel.WARNING.getId()) {
            resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        } else {
            resDto.setDataList(resDtoList);
        }
        
        return resDto;
    }
    
    /**
     * アクションID：A019
     * アクション名：表示する（参考信用建余力）
     * Dto リクエスト：IfaForeignMarginTradeNewOrderInputA019RequestDto
     * Dto レスポンス：IfaForeignMarginTradeNewOrderInputA019ResponseDto
     *
     * @param reqDto リクエストパラメータ
     * @return 実行結果
     * @exception Exception 業務例外
     */
    public DataList<IfaForeignMarginTradeNewOrderInputA019ResponseDto> displayReferenceMarginBalanceA019(
            IfaForeignMarginTradeNewOrderInputA019RequestDto reqDto) throws Exception {
        
        DataList<IfaForeignMarginTradeNewOrderInputA019ResponseDto> resDto = new DataList<IfaForeignMarginTradeNewOrderInputA019ResponseDto>();
        List<IfaForeignMarginTradeNewOrderInputA019ResponseDto> resDtoList = new ArrayList<IfaForeignMarginTradeNewOrderInputA019ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginTradeNewOrderInputServiceImplL.displayReferenceMarginBalanceA019");
        }
        
        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        GetMarginPowerReferenceResp resApi011 = new GetMarginPowerReferenceResp();
        // API011呼出：注文確認を行う。
        try {
            resApi011 = foreignAccountService.getMarginPowerReference(customerCommon.getButenCode(),
                    customerCommon.getAccountNumber(), COUNTRY_CODE_US);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        IfaForeignMarginTradeNewOrderInputA019ResponseDto res = new IfaForeignMarginTradeNewOrderInputA019ResponseDto();
        // 参考信用建余力
        res.setReferenceMarginPower(resApi011.getReferenceMarginBuyingPower());
        
        resDtoList.add(res);
        resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        
        return resDto;
    }
    
    /**
     * アクションID：A020
     * アクション名：概算建代金
     * Dto リクエスト：IfaForeignMarginTradeNewOrderInputA020RequestDto
     * Dto レスポンス：IfaForeignMarginTradeNewOrderInputA020ResponseDto
     *
     * @param reqDto リクエストパラメータ
     * @return 実行結果
     * @exception Exception 業務例外
     */
    public DataList<IfaForeignMarginTradeNewOrderInputA020ResponseDto> estimatedConstructionFeeA020(
            IfaForeignMarginTradeNewOrderInputA020RequestDto reqDto) throws Exception {
        
        DataList<IfaForeignMarginTradeNewOrderInputA020ResponseDto> resDto = new DataList<IfaForeignMarginTradeNewOrderInputA020ResponseDto>();
        List<IfaForeignMarginTradeNewOrderInputA020ResponseDto> resDtoList = new ArrayList<IfaForeignMarginTradeNewOrderInputA020ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginTradeNewOrderInputServiceImplL.estimatedConstructionFeeA020");
        }
        
        // FCT018：委託注文サービス時間のチェックを行う
        resDto = executeFct018();
        // 正常終了以外の場合、エラー内容を返却して処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        String butenCode = customerCommon.getButenCode();
        String accountNumber = customerCommon.getAccountNumber();
        // FCT001実行
        resDto = executeFct001(butenCode, accountNumber);
        // 正常終了以外の場合、エラー内容を返却して処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        // FCT003実行
        resDto = executeFct003(butenCode, accountNumber, reqDto.getTradeCd());
        // 正常終了以外の場合、エラー内容を返却して処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        // 外貨建口座取引開設状況のチェック
        resDto = checkForeignStockTradeAccountOpenStatus(customerCommon.getForeignStockTradeAccountOpenStatus());
        // 正常終了ではない場合、処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        // 外国信用口座開設状況のチェック
        resDto = checkForeignMarginAccountype(customerCommon.getForeignMarginAccountType());
        // 正常終了ではない場合、処理を終了する。
        if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return resDto;
        }
        
        // API001呼出：数量をチェックする。
        // 銘柄情報を取得する。
        // API001のリクエストパラメータを設定する。
        // API001を実行する。
        GetForeignStockSecuritiesResp resApi001 = new GetForeignStockSecuritiesResp();
        try {
            resApi001 = foreignStockService.getForeignStockSecurities(COUNTRY_CODE, reqDto.getBrandCode());
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // 実行結果のハンドリング
        if (StringUtils.equals(reqDto.getTradeCd(), ForeignStockTradeClass.BUY_CREDIT_NEW.getValue())) {
            // 画面の取引種別が信用新規買の場合、
            // 銘柄上場ステータスが「上場廃止」：エラーを返す
            if (StringUtils.equals(resApi001.getListedSecuritiesStatus(), ListedSecuritiesStatus.DELISTING.getId())) {
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_DELISTING,
                        IfaCommonUtil.getMessage(API001_ERRORS_DELISTING));
                return resDto;
            }
            // 信用新規買建規制が「規制あり」：エラーを返す
            if (resApi001.getOpenBuyRestrict()) {
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_OPEN_BUY_RESTRICT,
                        IfaCommonUtil.getMessage(API001_ERRORS_OPEN_BUY_RESTRICT));
                return resDto;
            }
        } else if (StringUtils.equals(reqDto.getTradeCd(), ForeignStockTradeClass.SELL_CREDIT_NEW.getValue())) {
            // 画面の取引種別が信用新規売の場合
            // 銘柄上場ステータスが「上場廃止」：エラーを返す
            if (StringUtils.equals(resApi001.getListedSecuritiesStatus(), ListedSecuritiesStatus.DELISTING.getId())) {
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_DELISTING,
                        IfaCommonUtil.getMessage(API001_ERRORS_DELISTING));
                return resDto;
            }
            // 信用新規売建規制が「規制あり」：エラーを返す
            if (resApi001.getOpenSellRestrict()) {
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_OPEN_SELL_RESTRICT,
                        IfaCommonUtil.getMessage(API001_ERRORS_OPEN_SELL_RESTRICT));
                return resDto;
            }
        }
        
        BigDecimal limitMax = new BigDecimal(resApi001.getTradeLimitMax());
        if (limitMax.compareTo(BigDecimal.ZERO) == 0) {
            limitMax = LIMIT_MAX;
        }
        // リクエストパラメータ.注文数量 ＞ API001.取引上限数量の場合
        if (new BigDecimal(reqDto.getForeignQuantity()).compareTo(limitMax) > 0) {
            // レスポンスにエラーを設定する
            resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_OUT_OF_RANGE,
                    IfaCommonUtil.getMessage(API001_ERRORS_OUT_OF_RANGE,
                            new String[] { ORDER_COUNT, resApi001.getTradeLimitMin(), limitMax.toString() }));
            return resDto;
        }
        // リクエストパラメータ.注文数量 ＜ API001.取引下限数量の場合
        if (new BigDecimal(reqDto.getForeignQuantity()).compareTo(new BigDecimal(resApi001.getTradeLimitMin())) < 0) {
            // レスポンスにエラーを設定する
            resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_OUT_OF_RANGE,
                    IfaCommonUtil.getMessage(API001_ERRORS_OUT_OF_RANGE,
                            new String[] { ORDER_COUNT, resApi001.getTradeLimitMin(), limitMax.toString() }));
            return resDto;
        }
        
        // 数量が、取引単位で割り切れることをチェックする。
        // リクエストパラメータ.注文数量 % API001.取引単位 != 0の場合
        if (new BigDecimal(reqDto.getForeignQuantity())
                .remainder(new BigDecimal(resApi001.getTradeUnit())) != BigDecimal.ZERO) {
            // レスポンスにエラーを設定する
            resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, API001_ERRORS_INCONSITENT,
                    IfaCommonUtil.getMessage(API001_ERRORS_INCONSITENT,
                            new String[] { ORDER_COUNT, TRADE_UNIT, resApi001.getTradeUnit() }));
            return resDto;
        }
        
        if (StringUtils.equals(reqDto.getOrderPriceKindList(), PriceConditons.LIMIT.getValue())) {
            // "注文単価（指値）が、0以上　999999999.99以下の範囲にあることをチェックする。
            resDto = checkOrderPrice(reqDto.getHiddenOrderPrice(), ORDER_PRICE_ASKING_PRICE);
            // 正常終了ではない場合、処理を終了する。
            if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
                return resDto;
            }
        } else if (!StringUtils.equals(reqDto.getOrderPriceKindList(), PriceConditons.MARKET.getValue())) {
            if (StringUtils.equals(reqDto.getOrderPriceKindList(), PriceConditons.STOP_PRICE_LIMIT.getValue())) {
                // 注文単価（逆指値）が、0以上　999999999.99以下の範囲にあることをチェックする。
                resDto = checkOrderPrice(reqDto.getHiddenOrderPriceReversePriceLimit(), ORDER_PRICE_STOP_LOSS);
                // 正常終了ではない場合、処理を終了する。
                if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
                    return resDto;
                }
            }
            // 発火条件価格（逆指値）が、0以上　999999999.99以下の範囲にあることをチェックする。
            resDto = checkOrderPrice(reqDto.getOrderInputAreaPriceTermsreversePriceLimitStopOrderPrice(),
                    ORDER_PRICE_REFERENCE_PRICE);
            // 正常終了ではない場合、処理を終了する。
            if (resDto.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
                return resDto;
            }
        }
        
        // API002呼出：期間条件が「期間指定」の場合、日付が9営業日後以内であることをチェックする。
        // API002のリクエストパラメータを設定する。
        // API002を実行する。
        String buySellCode = null;
        if (StringUtils.equals(reqDto.getTradeCd(), "2")) {
            buySellCode = BUY;
        } else if (StringUtils.equals(reqDto.getTradeCd(), "3")) {
            buySellCode = SELL;
        }
        GetForeignStockCreatedMarginOrderInitializationResp resApi002 = new GetForeignStockCreatedMarginOrderInitializationResp();
        try {
            resApi002 = foreignStockService.getForeignStockCreatedMarginOrderInitialization(butenCode, accountNumber,
                    COUNTRY_CODE, reqDto.getBrandCode(), StockTradeType.MARGIN_OPEN.getId(), buySellCode, null);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        // リクエストパラメータ.期間条件=="1" （"CARRY_OVER_ORDER"（期間指定））の場合
        if (StringUtils.equals(reqDto.getPeriodRadio(), CARRY_OVER_ORDER)) {
            if (null == resApi002.getOrderTerms() || resApi002.getOrderTerms().size() == 0) {
                // レスポンスに注文可能期間エラーを設定する
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_TERMS_OUTOFRANGE,
                        IfaCommonUtil.getMessage(ERRORS_TERMS_OUTOFRANGE));
                return resDto;
            }
            int orderTermSize = resApi002.getOrderTerms().size() > MAX_ORDER_TERM_LIST ? MAX_ORDER_TERM_LIST
                    : resApi002.getOrderTerms().size();
            List<String> orderTermList = resApi002.getOrderTerms().subList(0, orderTermSize);
            // リクエストパラメータ.期間 ＞ API002.有効期間一覧の場合（9営業日後以降の場合）
            if (!orderTermList.contains(DateFormatUtil.dateFormatToHyphen(reqDto.getPeriod()))) {
                // レスポンスに注文可能期間エラーを設定する
                resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_TERMS_OUTOFRANGE,
                        IfaCommonUtil.getMessage(ERRORS_TERMS_OUTOFRANGE));
                return resDto;
            }
        }
        
        // 口座の取引制限チェックを行う。
        // FCT021：口座の取引制限チェックを行う。   
        InputFct021Dto reqFct021 = new InputFct021Dto();
        reqFct021.setButenCode(butenCode);
        reqFct021.setAccountNumber(accountNumber);
        reqFct021.setProductCd(FOREIGN_STOCKS);
        reqFct021.setTradeCd(reqDto.getTradeCd());
        reqFct021.setCountryCd(COUNTRY_CODE);
        reqFct021.setCurrencyCode(CURRENCY_CODE);
        OutputFct021Dto resFct021 = fct021.doCheck(reqFct021);
        resDto = executeFct021(resFct021);
        // 正常終了、アラート終了以外の場合、エラー内容を返却して処理を終了する。
        if (resDto.getErrorLevel() == ErrorLevel.FATAL.getId()) {
            return resDto;
        }
        
        ConfirmForeignStockCreatedMarginOrderResp resApi007 = new ConfirmForeignStockCreatedMarginOrderResp();
        MarginOrderInput moi = new MarginOrderInput();
        moi.setCountryCode(COUNTRY_CODE);
        moi.setMarketCode(reqDto.getMarketCode());
        moi.setSecuritiesCode(reqDto.getBrandCode());
        moi.setBuySellCode(buySellCode);
        moi.setOrderQuantity(reqDto.getForeignQuantity());
        moi.setOrderPriceKindCode(reqDto.getOrderPriceKindList());
        // 価格条件の場合
        if (StringUtils.equals(reqDto.getOrderPriceKindList(), PriceConditons.LIMIT.getValue())) {
            // 価格条件が「指値」の場合
            moi.setOrderPrice(reqDto.getHiddenOrderPrice());
            
            // 価格条件(逆指値)の場合
        } else if (!StringUtils.equals(reqDto.getOrderPriceKindList(), PriceConditons.MARKET.getValue())) {
            // 価格条件が「逆指値/指値」の場合
            if (StringUtils.equals(reqDto.getOrderPriceKindList(), PriceConditons.STOP_PRICE_LIMIT.getValue())) {
                moi.setOrderPrice(reqDto.getHiddenOrderPriceReversePriceLimit());
            }
            // 価格条件が「逆指値」の場合のみ
            moi.setStopPrice(reqDto.getOrderInputAreaPriceTermsreversePriceLimitStopOrderPrice());
        }
        
        moi.setOrderLimitCode(reqDto.getPeriodRadio());
        if (StringUtils.equals(reqDto.getPeriodRadio(), CARRY_OVER_ORDER)) {
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date period = sdFormat.parse(reqDto.getPeriod());
            SimpleDateFormat sdFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            moi.setOrderTerm(sdFormat2.format(period));
        }
        moi.setSpecificAccountCode(reqDto.getDepositType());
        moi.setSettlementMethodCode(reqDto.getKessaiHoho());
        moi.setMarginCloseLimitType(reqDto.getMarginDueDate());
        // API007呼出：注文確認を行う。
        try {
            resApi007 = foreignStockService.confirmForeignStockCreatedMarginOrder(butenCode, accountNumber, null, null,
                    "PHONE", moi);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        IfaForeignMarginTradeNewOrderInputA020ResponseDto res = new IfaForeignMarginTradeNewOrderInputA020ResponseDto();
        // 約定金額（外貨）
        res.setApproximatePositionAmount(resApi007.getMarginOrder().getOrder().getFrnGrossAmount());
        
        resDtoList.add(res);
        if (resDto.getErrorLevel() != ErrorLevel.WARNING.getId()) {
            resDto = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        } else {
            resDto.setDataList(resDtoList);
        }
        
        return resDto;
    }
    
    /**
    * 共通：FCT001実行処理
    *
    * @param butenCode 顧客共通情報の部店コード
    * @param accountNumber 顧客共通情報の口座番号
    * @return データリスト. エラー有の場合そのエラー内容
    */
    private <E> DataList<E> executeFct001(String butenCode, String accountNumber) {
        
        List<E> list = new ArrayList<E>();
        
        // FCT001：利用者の口座に対する権限チェックを行う
        InputFct001Dto reqFct001 = new InputFct001Dto();
        reqFct001.setButenCode(butenCode);
        reqFct001.setAccountNumber(accountNumber);
        OutputFct001Dto resFct001 = fct001.doCheck(reqFct001);
        
        if (StringUtils.equals(resFct001.getTargetCustomerRefAuthFlag(), AUTH_ERROR_VALUE)) {
            // 対象顧客参照権限：なしの場合
            return IfaCommonUtil.createDataList(list, ErrorLevel.FATAL, FCT001_ERRORS_BUTEN_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(FCT001_ERRORS_BUTEN_ACCOUNT_NOT_EXISTS,
                            new String[] { butenCode, accountNumber }));
            
        } else if (StringUtils.equals(resFct001.getTradeSuspendFlag(), TRADE_SUSPEND_FLAG_VALUE)) {
            // 取引停止口座フラグ：取引停止の場合
            return IfaCommonUtil.createDataList(list, ErrorLevel.FATAL, FCT001_ERRORS_OUT_OF_SERVICE,
                    IfaCommonUtil.getMessage(FCT001_ERRORS_OUT_OF_SERVICE));
        }
        return new DataList<E>();
    }
    
    /**
    * 共通：FCT003実行処理
    *
    * @param butenCode 顧客共通情報の部店コード
    * @param accountNumber 顧客共通情報の口座番号
    * @param tradeCd A001の場合null. A003、A005、A012、A20の場合、画面リクエスト.取引種別
    * 
    * @return データリスト. エラー有の場合そのエラー内容
    */
    private <E> DataList<E> executeFct003(String butenCode, String accountNumber, String tradeCd) {
        
        List<E> list = new ArrayList<E>();
        String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "5", "1");
        DataList<E> error = IfaCommonUtil.createDataList(list, ErrorLevel.FATAL, FCT003_ERRORS_UNAVAILABLE,
                IfaCommonUtil.getMessage(FCT003_ERRORS_UNAVAILABLE, new String[] { codeName }));
        
        // FCT003の呼び出し
        InputFct003Dto reqFct003 = new InputFct003Dto();
        reqFct003.setButenCode(butenCode);
        reqFct003.setAccountNumber(accountNumber);
        reqFct003.setProductCd(FOREIGN_STOCKS);
        if (!StringUtil.isNullOrEmpty(tradeCd)) {
            reqFct003.setTradeCd(tradeCd);
            reqFct003.setCountryCd(COUNTRY_CODE);
            reqFct003.setCurrencyCode(CURRENCY_CODE);
        }
        OutputFct003Dto resFct003 = fct003.doCheck(reqFct003);
        
        // FCT003.レスポンス.媒介可否リスト.取引種別が「"信用新規買"」かつ　レスポンス.媒介可否リスト.媒介可否＝"1"（媒介可）
        // または
        // FCT003.レスポンス.媒介可否リスト.取引種別が「"信用新規売"」かつ　レスポンス.媒介可否リスト.媒介可否＝"1"（媒介可）が存在しない場合
        int num = 0;
        for (MediatePropriety loop : resFct003.getMediateProprietyList()) {
            if ((StringUtils.equals(loop.getTradeClass(), ForeignStockTradeClass.BUY_CREDIT_NEW.getValue())
                    && StringUtils.equals(loop.getMediatePropriety(), EXIST))
                    || (StringUtils.equals(loop.getTradeClass(), ForeignStockTradeClass.SELL_CREDIT_NEW.getValue())
                            && StringUtils.equals(loop.getMediatePropriety(), EXIST))) {
                // 存在した場合プラス
                num++;
            }
        }
        if (num == 0) {
            // レスポンスに媒介不可エラーを設定する
            return error;
        }
        
        DataList<E> success = new DataList<E>();
        return success;
    }
    
    /**
     * 共通：FCT018実行処理
     *
     * @return データリスト. エラー有の場合そのエラー内容
     */
    private <E> DataList<E> executeFct018() {
        
        List<E> list = new ArrayList<E>();
        
        // FCT018：委託注文サービス時間のチェックを行う
        InputFct018Dto reqFct018 = new InputFct018Dto();
        reqFct018.setCountryCode(COUNTRY_CODE_US);
        OutputFct018Dto resFct018 = fct018.doCheck(reqFct018);
        // チェック結果がNGの場合
        if (StringUtils.equals(resFct018.getProcessResult(), RESULT_NG)) {
            return IfaCommonUtil.createDataList(list, ErrorLevel.FATAL, FCT018_ERRORS_OUT_OF_RANGE,
                    IfaCommonUtil.getMessage(FCT018_ERRORS_OUT_OF_RANGE));
        }
        return new DataList<E>();
    }
    
    /**
     * 共通：FCT021エラー処理
    　* @param resFct021 FCT021結果
     *
     * @return データリスト. エラー／ワーニング有の場合そのエラー内容
     */
    private <T> DataList<T> executeFct021(OutputFct021Dto resFct021) throws Exception {
        
        // ワーニングありの場合も処理継続するため、処理結果としては成功を示す
        DataList<T> success = new DataList<>();
        List<T> list = new ArrayList<T>();
        
        if (StringUtils.equals(resFct021.getNoteInfoErrFlag(), "1")) {
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "5", "1");
            return IfaCommonUtil.createDataList(list, ErrorLevel.FATAL, FCT021_ERRORS_NOTICE_ERROR_CHECK,
                    IfaCommonUtil.getMessage(FCT021_ERRORS_NOTICE_ERROR_CHECK, new String[] { codeName }));
            
        } else if (StringUtils.equals(resFct021.getNoteLimitErrFlag(), "1")) {
            return IfaCommonUtil.createDataList(list, ErrorLevel.FATAL, FCT021_ERRORS_INFORMATION_CHECK,
                    IfaCommonUtil.getMessage(FCT021_ERRORS_INFORMATION_CHECK));
        }
        if (StringUtils.equals(resFct021.getNoteInfoAlertFlag(), "1")) {
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "5", "1");
            success = IfaCommonUtil.createDataList(list, ErrorLevel.WARNING, FCT021_WARNINGS_NOTICE_WARNING_CHECK,
                    IfaCommonUtil.getMessage(FCT021_WARNINGS_NOTICE_WARNING_CHECK, new String[] { codeName }));
        }
        if (StringUtils.equals(resFct021.getNoteLimitAlertFlag(), "1")) {
            success = IfaCommonUtil.createDataList(list, ErrorLevel.WARNING, FCT021_WARNINGS_INFORMATION_CHECK,
                    IfaCommonUtil.getMessage(FCT021_WARNINGS_INFORMATION_CHECK));
        }
        
        return success;
    }
    
    /**
    * 共通：FCT029 英文開示銘柄取得
    *
    * @param brandCode 画面リクエスト.銘柄コード
    * @return 英文開示銘柄判定 "1"（英文開示銘柄に該当する）、"0"（英文開示銘柄に該当しない）
    */
    private String executeFct029(String brandCode) {
        
        // FCT018：委託注文サービス時間のチェックを行う
        InputFct029Dto reqFct029 = new InputFct029Dto();
        reqFct029.setNationalityCode(COUNTRY_CODE_US);
        reqFct029.setBrandCode(brandCode);
        OutputFct029Dto resFct029 = fct029.doCheck(reqFct029);
        return resFct029.getIssuesDisclosedInEnglishBrandJudge();
    }
    
    /**
    * 共通：外貨建口座取引開設状況のチェック
    *
    * @param status 顧客共通情報.外国株式取引口座開設状況
    * @return データリスト. エラー有の場合そのエラー内容
    */
    private <T> DataList<T> checkForeignStockTradeAccountOpenStatus(String status) {
        
        // 顧客共通情報.外国株式取引口座開設状況より、外貨建口座取引開設状況のチェックを行う。
        if (StringUtils.equals(status, FOREIGN_STOCK_TRADE_ACCOUNT_OPEN_STAUTS_NOT_OPENED)) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_FOREIGN_STOCK_ACCOUNT_CHECK,
                    IfaCommonUtil.getMessage(ERRORS_FOREIGN_STOCK_ACCOUNT_CHECK));
        }
        
        return new DataList<>();
    }
    
    /**
    * 共通：外国信用口座開設状況のチェック
    *
    * @param type 顧客共通情報.信用口座区分（外国）
    * @return データリスト. エラー有の場合そのエラー内容
    */
    private <T> DataList<T> checkForeignMarginAccountype(String type) {
        
        // 外国信用口座開設状況のチェックを行う。(現物口座である場合はエラー)
        if (StringUtils.equals(type, FOREIGN_MARGIN_ACCOUNT_TYPE_PHYSICAL)) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_NOT_OPEN,
                    IfaCommonUtil.getMessage(ERRORS_NOT_OPEN));
        }
        
        return new DataList<>();
    }
    
    /**
     * 共通：現地約定日と注文当日の比較
     * 現地約定日が注文当日より大きいかチェックする。
     *
     * @param contractDate API007.信用注文情報.注文.現地約定日
     * @return true：現地約定日が注文当日より大きい（呼び出し元でエラーメッセージを設定する. false ：ワーニングなし
     * @throws ParseException 日付変換時のエラー
     */
    private boolean checkOrderDate(String contractDate) throws Exception {
        
        boolean result = false;
        
        Date sysDate = new Date();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date checkDate = sdf.parse(contractDate);
        
        // チェック対象の日付（現地約定日）が注文当日の日付より大きい場合
        if (checkDate.compareTo(sysDate) == 1) {
            result = true;
        }
        
        return result;
    }
    
    /**
    * 共通：注文単価のチェック
    *
    * @param price チェック対象の注文単価（注文単価（指値）、発火条件価格（逆指値）、注文単価（逆指値））
    * @param target 処理対象のエラー発生時に出力するエラー文字列
    * @return データリスト. エラー有の場合そのエラー内容
    */
    private <T> DataList<T> checkOrderPrice(String price, String target) {
        
        // 小数点型で比較をする
        BigDecimal checkPrice = new BigDecimal(price);
        
        // 注文単価 < 0 もしくは 999999999.99 < 注文単価の場合エラー
        if (checkPrice.compareTo(BigDecimal.ZERO) == -1 || checkPrice.compareTo(MAX_PRICE) == 1) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_ORDER_VALUE_OUT_OF_RANGE, IfaCommonUtil
                    .getMessage(ERRORS_ORDER_VALUE_OUT_OF_RANGE, new String[] { target, "0", "999999999.99" }));
        }
        
        return new DataList<>();
    }
}
