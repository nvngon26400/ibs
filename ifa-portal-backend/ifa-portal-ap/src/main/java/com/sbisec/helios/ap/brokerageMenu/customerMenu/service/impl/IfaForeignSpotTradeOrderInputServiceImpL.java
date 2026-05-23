package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.enums.ListedSecuritiesStatus;
import com.sbisec.helios.ap.athena.enums.NisaBuyLimitStopType;
import com.sbisec.helios.ap.athena.enums.NisaType;
import com.sbisec.helios.ap.athena.enums.OrderWarningStatus;
import com.sbisec.helios.ap.athena.enums.SecuritiesType;
import com.sbisec.helios.ap.athena.enums.SettlementMethod;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.ifa.ForeignInformationService;
import com.sbisec.helios.ap.athena.ifa.ForeignStockService;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignScheduleCashBalancesReq;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignScheduleCashBalancesResp;
import com.sbisec.helios.ap.athena.protocol.account.ListScheduleCashBalancesResp;
import com.sbisec.helios.ap.athena.protocol.account.dto.CashBalances;
import com.sbisec.helios.ap.athena.protocol.account.dto.CurrencyCashBalance;
import com.sbisec.helios.ap.athena.protocol.account.dto.ScheduleCashBalance;
import com.sbisec.helios.ap.athena.protocol.common.Market;
import com.sbisec.helios.ap.athena.protocol.common.NisaLimit;
import com.sbisec.helios.ap.athena.protocol.common.PriceData;
import com.sbisec.helios.ap.athena.protocol.common.Securities;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.Order;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.OrderInput;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.SellPossibleQuantity;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockCreatedOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockRuTickSizeResp;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockAttentionSecuritiesResp;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockSecuritiesResp;
import com.sbisec.helios.ap.athena.protocol.information.CreateMarketPriceTicketResp;
import com.sbisec.helios.ap.athena.protocol.information.GetMarketPriceHashResp;
import com.sbisec.helios.ap.athena.protocol.information.ListMarketPricesResp;
import com.sbisec.helios.ap.athena.utils.RequestUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct004;
import com.sbisec.helios.ap.bizcommon.component.Fct006;
import com.sbisec.helios.ap.bizcommon.component.Fct018;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.component.Fct028;
import com.sbisec.helios.ap.bizcommon.component.Fct029;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct004Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct018Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct028Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct029Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct004Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct018Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct028Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct029Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA013RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA013ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA013ResponseDtoBrandInfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA013ResponseDtoComplianceCheck;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA013ResponseDtoConfirmItem;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA013ResponseDtoMarketInfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputBuyingPowerDomestic;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputBuyingPowerDomesticNisaBuyLimit;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputBuyingPowerForeign;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputPriceBasicInfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaForeignSpotTradeOrderInputService;
import com.sbisec.helios.ap.common.enums.CurrencyCode;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ForeignStockTradeAccountOpenStatus;
import com.sbisec.helios.ap.common.enums.ForeignStockTradeClass;
import com.sbisec.helios.ap.common.enums.JrIsaContractType;
import com.sbisec.helios.ap.common.enums.MediateAbleTradeFlag;
import com.sbisec.helios.ap.common.enums.NationalityCode;
import com.sbisec.helios.ap.common.enums.PeriodConditions;
import com.sbisec.helios.ap.common.enums.SecurityMoneyClass;
import com.sbisec.helios.ap.common.enums.SelectAblePriceConditions;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.enums.TradeSuspendFlag;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.service.MedSystemVariableService;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0301-01_1
 * 画面名：外国現物取引注文入力
 * 2024/02/08 新規作成
 *
 * @author SCSK 宇田川達弥
 */
@Component(value = "cmpIfaForeignSpotTradeOrderInputService")
public class IfaForeignSpotTradeOrderInputServiceImpL implements IfaForeignSpotTradeOrderInputService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaForeignSpotTradeOrderInputServiceImpL.class);
    
    /** メッセージID:権限チェックエラー */
    private static final String MESSAGE_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** メッセージID:取引停止口座エラー */
    private static final String MESSAGE_OUT_OF_SERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** メッセージID:媒介不可エラー */
    private static final String MESSAGE_MEDIATE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** メッセージID:外貨建口座未開設エラー */
    private static final String MESSAGE_FSTOCK_ACCOUNT_NOT_OPENED = "errors.foreignStockAccountCheck";
    
    /** メッセージID:買付停止エラー */
    private static final String MESSAGE_TRADE_STOPPED_BUY_ONLY = "errors.frs.listedSecuritiesStatus.buyStop";
    
    /** メッセージID:売買停止エラー */
    private static final String MESSAGE_TRADE_STOPPED_BUY_SELL = "errors.frs.listedSecuritiesStatus.buySellStop";
    
    /** メッセージID:上場廃止エラー */
    private static final String MESSAGE_DELISTED_STOCK = "errors.frs.listedSecuritiesStatus.delisting";
    
    /** メッセージID:委託注文サービス時間外エラー */
    private static final String MESSAGE_OUT_OF_HOURS = "errors.frs.serviceHours.outOfRange";
    
    /** メッセージID:注文数量/単価範囲外エラー */
    private static final String MESSAGE_ORDER_VALUE_OUT_OF_RANGE = "errors.frs.orderValue.outOfRange";
    
    /** メッセージID:取引単位外エラー */
    private static final String MESSAGE_INCONSISTENT_ORDER = "errors.frs.orderingCondition.inconsistent";
    
    /** メッセージID:期間指定範囲外エラー */
    private static final String MESSAGE_ORDER_TERMS_OUT_OF_RANGE = "errors.frs.orderTerms.outOfRange";
    
    /** メッセージID:乗換勧誘(ETF)勧誘区分エラー */
    private static final String MESSAGE_INVITATION_CHECK = "errors.invitationCheck";
    
    /** メッセージID:乗換勧誘(ETF)アラート */
    private static final String MESSAGE_FOREIGN_ETF_CONFIRM = "warnings.frs.etfOrder.confirm";
    
    /** メッセージID:注意情報エラー */
    private static final String MESSAGE_NOTICE_ERROR_CHECK = "errors.cmn.noticeErrorCheck";
    
    /** メッセージID:重要なお知らせエラー */
    private static final String MESSAGE_INFORMATION_CHECK = "errors.informationCheck";
    
    /** メッセージID:注意情報アラート */
    private static final String MESSAGE_NOTICE_WARN_CHECK = "warnings.cmn.noticeWarningCheck";
    
    /** メッセージID:重要なお知らせアラート */
    private static final String MESSAGE_INFORMATION_WARN_CHECK = "warnings.cmm.informationCheck";
    
    /** 取引注意情報(銘柄)アラート */
    private static final String MESSAGE_STOCK_ATTENTION_CHECK = "warnings.dms.informationCheck";
    
    /** メッセージID:買付余力不足エラー */
    private static final String MESSAGE_BUYING_POWER_SHORT = "errors.frs.buyLimit.overflow";
    
    /** メッセージID:売却株数不足エラー */
    private static final String MESSAGE_STOCK_BALANCE_SHORT = "errors.frs.countUnit.overflow";
    
    /** メッセージID:約定代金ソフトリミット上限超過 */
    private static final String MESSAGE_CONTRACT_PRICE_LIMIT_EXEEDED = "warnings.frs.contractPriceUpperLimit.exceeded";
    
    /** メッセージID:逆指値注文即時発火 */
    private static final String MESSAGE_STOP_PRICE_TRIGGERED_CONFIRM = "warnings.frs.stopPriceCondition.confirm";
    
    /** メッセージID:現地約定日超過（休場日） */
    private static final String MESSAGE_NEXT_BUSINESS_DAY_CONFIRM = "warnings.frs.orderContract.nextBusinessDay";
    
    /** 区分ID:対象取引（メッセージ表示用）　*/
    private static final String CODE_ID_MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 区分値:対象取引（メッセージ表示用）.外国株式　*/
    private static final String CODE_VAL_MSG_DISPLAY_TARGET_TRADE_FSTOCK = "6";
    
    /** 区分値:対象取引（メッセージ表示用）.外国株式現物取引（委託）　*/
    private static final String CODE_VAL_MSG_DISPLAY_TARGET_TRADE_FSTOCK_ENTRUST = "6A";
    
    /** 区分値：証券種別.外国株式現物 */
    private static final String CODE_VAL_SECURITY_CLASS_FSTOCK = "15";
    
    /** 区分ID:勧誘区分（外株）　*/
    private static final String CODE_ID_FOREIGN_STOCK_INVITATION_TYPE = "FOREIGN_STOCK_INVITATION_TYPE";
    
    /** 区分値：ISA契約区分.契約 */
    private static final String CODE_VAL_ISA_CONTRACT_TYPE = "1";
    
    /** 円貨決済停止日URL */
    private static final String FRS_YEN_CLOSED_URL = "FRS_YEN_CLOSED_URL";
    
    /** お取引注意事項URL */
    private static final String FRS_STOCK_QAI_22_URL = "FRS_STOCK_QAI_22_URL";
    
    /** 預り区分（外国）.一般 */
    private static final String FOREIGN_DEPOSIT_TYPE_GENERAL = "GENERAL";
    
    /** 残高取得日数 */
    private static final Integer FRS_BUYING_POWER_LIST_DAYS = Integer.valueOf(6);
    
    /** 残高取得日数（3日） */
    private static final Integer FRS_BUYING_POWER_LIST_DAYS_THREE = Integer.valueOf(3);
    
    /** 預り区分（外国）.JrNISA */
    private static final String FOREIGN_DEPOSIT_TYPE_JR_NISA = "JR_NISA";
    
    /** NISA区分.総合NISA */
    private static final String NISA_TYPE_GENERAL_NISA = "GENERAL_NISA";
    
    /** 外貨買付余力表示対象T+n日 */
    private static final int FOREIGN_CASH_BALANCES_T_DAY = 3;
    
    /** 外貨買付余力表示対象T+n日(ロシア、ベトナム) */
    private static final int FOREIGN_CASH_BALANCES_T_DAY_RU_VN = 4;
    
    /** IPアドレス */
    private static final String IP_ADDRESS = "127.0.0.1";
    
    /** X-App-User-Agent */
    private static final String X_APP_USER_AGENT = "Edge";
    
    /** 判定結果:OK */
    private static final String RESULT_OK = "OK";
    
    /** 取引上限数量(レスポンス).上限なし */
    private static final String TRADE_LIMIT_MAX_RES_NONE = "0";
    
    /** 取引上限数量:999999999 */
    private static final BigDecimal TRADE_LIMIT_MAX_VAL = BigDecimal.valueOf(9999999999L);
    
    /** 取引数量エラー対象項目:注文数量 */
    private static final String ERROR_ITEM_NAME_ORDER_QTY = "注文数量";
    
    /** 取引数量エラー対象項目:注文数量 */
    private static final String ERROR_ITEM_NAME_TRADE_UNIT = "取引単位";
    
    /** 単価エラー対象項目:注文単価（指値） */
    private static final String ERROR_ITEM_NAME_ORDER_PRICE_LIMIT = "注文単価（指値）";
    
    /** 単価エラー対象項目:参照価格（逆指値） */
    private static final String ERROR_ITEM_NAME_STOP_PRICE = "参照価格（逆指値）";
    
    /** 単価エラー対象項目:注文単価（逆指値） */
    private static final String ERROR_ITEM_NAME_EXECUTE_PRICE = "注文単価（逆指値）";
    
    /** 単価上限整数部 */
    private static final BigDecimal PRICE_MAX_INTEGER = BigDecimal.valueOf(999999999L);
    
    /** 単価小数部:9 */
    private static final String PRICE_FRACTION_NINE = "9";
    
    /** 期間指定制限日数T+n日 */
    private static final int PERIOD_DESIGNATION_LIMIT_T_DAY = 9;
    
    /** 区分値:乗換え勧誘(ETF).勧誘あり（説明明記、確認書受入） */
    private static final String CODE_VAL_ETF_SOLICITING_TRANSFERS_SOLICIT = "1";
    
    /** 区分値:乗換え勧誘(ETF).勧誘なし */
    private static final String CODE_VAL_ETF_SOLICITING_TRANSFERS_NONE = "0";
    
    /** 区分値:通貨コード.999 */
    private static final String CODE_VAL_CURRENCY_CODE_999 = "999";
    
    /** 区分値:勧誘区分（外株）.勧誘あり */
    private static final String CODE_VAL_FOREIGN_STOCK_INVITATION_TYPE_INV = "1";
    
    /** 区分値:勧誘区分（外株）.勧誘なし */
    private static final String CODE_VAL_FOREIGN_STOCK_INVITATION_TYPE_NONE = "0";
    
    /** 区分値:勧誘区分.勧誘あり */
    private static final String CODE_VAL_INVITATION_TYPE_INV = "1";
    
    /** 区分値:勧誘区分.勧誘なし */
    private static final String CODE_VAL_INVITATION_TYPE_NONE = "2";
    
    /** 区分値:国内外国区分.外国 */
    private static final String CODE_VAL_DOMESTIC_FOREIGN_TYPE_FGN = "1";
    
    /** 区分値:(FCT006)商品区分.株式 */
    private static final String CODE_VAL_SECURITY_TYPE_STOCK = "1 ";
    
    /** チェックボックス状態:チェックオン */
    private static final String ERROR_ITEM_CHECKBOX_ON = "チェックオン";
    
    /** 勧誘区分チェックエラー接頭辞:"勧誘区分は" */
    private static final String ERROR_INVITATION_VARIABLE_PREFIX = "勧誘区分は";
    
    /** 区分値:受注方法区分（外株）.店頭 */
    private static final String CODE_VAL_FOREIGN_STOCK_ORDER_METHOD_TYPE_OTC = "0";
    
    /** 区分値:受注方法区分（外株）.訪問 */
    private static final String CODE_VAL_FOREIGN_STOCK_ORDER_METHOD_TYPE_VISIT = "1";
    
    /** 区分値:受注方法区分（外株）.電話 */
    private static final String CODE_VAL_FOREIGN_STOCK_ORDER_METHOD_TYPE_TEL = "3";
    
    /** 区分値:受注方法区分.店頭 */
    private static final String CODE_VAL_ORDER_METHOD_TYPE_OTC = "1";
    
    /** 区分値:受注方法区分.訪問 */
    private static final String CODE_VAL_ORDER_METHOD_TYPE_VISIT = "2";
    
    /** 区分値:受注方法区分.電話 */
    private static final String CODE_VAL_ORDER_METHOD_TYPE_TEL = "3";
    
    /** 区分値:コンプラチェック種類.買付注文 */
    private static final String CODE_VAL_COMPLA_CHECK_KIND_SPOT_BUY = "1";

    /** 区分値:預り区分（外国）.JrNISA－一般預り */
    private static final String FOREIGN_DEPOSIT_TYPE_JR_SPECIFIC = "5";
    
    /** 区分値:預り区分（外国）.JrNISA－特定預り */
    private static final String FOREIGN_DEPOSIT_TYPE_JR_GENERAL = "6";
    
    /** 区分値:預り区分（外国）.JrNISA－NISA預り */
    private static final String FOREIGN_DEPOSIT_TYPE_JR_NISA_CODE = "7";
    
    /** 区分値:預り区分（外国）.Jr継続NISA預り */
    private static final String FOREIGN_DEPOSIT_TYPE_CONTINUOUS_MANAGEMENT = "J";

    /** コンプラランクチェック判定結果.ノーマル */
    private static final String COMP_RANK_JUDGEMENT_RESULT_NORMAL = "0";
    
    /** コンプラランクチェック判定結果.アラート */
    private static final String COMP_RANK_JUDGEMENT_RESULT_ALERT = "1";
    
    /** コンプラランクチェック判定結果.エラー */
    private static final String COMP_RANK_JUDGEMENT_RESULT_ERROR = "2";
    
    @Autowired
    Fct001 fct001;
    
    @Autowired
    Fct003 fct003;
    
    @Autowired
    Fct004 fct004;
    
    @Autowired
    Fct006 fct006;
    
    @Autowired
    Fct018 fct018;
    
    @Autowired
    Fct021 fct021;
    
    @Autowired
    Fct028 fct028;
    
    @Autowired
    Fct029 fct029;
    
    @Autowired
    MedSystemVariableService medSystemVariableService;
    
    @Autowired
    CodeListService codeListService;
    
    @Autowired
    ForeignStockService foreignStockService;
    
    @Autowired
    ForeignAccountService foreignAccountService;
    
    @Autowired
    ForeignInformationService foreignInformationService;
    
    @Autowired
    private CometCommonService cometCommonService;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignSpotTradeOrderInputA001RequestDto
     * Dto レスポンス：IfaForeignSpotTradeOrderInputA001ResponseDto
     *
     * @param dtoReq リクエストDto
     * @return Dto レスポンス
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignSpotTradeOrderInputA001ResponseDto> initializeA001(
            IfaForeignSpotTradeOrderInputA001RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignSpotTradeOrderInputServiceImpL.initializeA001");
        }
        
        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        // 口座に対する権限チェック
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(customerCommon.getButenCode());
        inputFct001Dto.setAccountNumber(customerCommon.getAccountNumber());
        
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        
        if (StringUtils.equals(outputFct001Dto.getTargetCustomerRefAuthFlag(),
                TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId())) {
            // 対象顧客参照権限有無が権限なしの場合、権限なしエラーを返す
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(MESSAGE_ACCOUNT_NOT_EXISTS,
                            new String[] { customerCommon.getButenCode(), customerCommon.getAccountNumber() }));
        } else if (StringUtils.equals(outputFct001Dto.getTradeSuspendFlag(), TradeSuspendFlag.SUSPEND.getId())) {
            // 取引停止口座の場合、取引停止エラーを返す
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_OUT_OF_SERVICE,
                    IfaCommonUtil.getMessage(MESSAGE_OUT_OF_SERVICE));
        }
        
        // 媒介可否チェック
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        
        inputFct003Dto.setButenCode(customerCommon.getButenCode());
        inputFct003Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct003Dto.setProductCd(CODE_VAL_SECURITY_CLASS_FSTOCK);
        inputFct003Dto.setTradeCd(dtoReq.getBuySellTypeName());
        
        OutputFct003Dto outputFct003Dto = fct003.doCheck(inputFct003Dto);
        
        // 媒介可否リスト.媒介可否に媒介可が存在するか判定する
        if (outputFct003Dto.getMediateProprietyList().stream()
                .map(mediateProprieties -> mediateProprieties.getMediatePropriety()).noneMatch(
                        mediatePropriety -> StringUtils.equals(MediateAbleTradeFlag.ARI.getId(), mediatePropriety))) {
            // 存在しない場合、媒介不可エラーを返す
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_MEDIATE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(MESSAGE_MEDIATE_UNAVAILABLE, new String[] { codeListService
                            .getValue(CODE_ID_MSG_DISPLAY_TARGET_TRADE, CODE_VAL_MSG_DISPLAY_TARGET_TRADE_FSTOCK_ENTRUST) }));
        }
        
        if (!StringUtils.equals(customerCommon.getForeignStockTradeAccountOpenStatus(),
                ForeignStockTradeAccountOpenStatus.OPEN.getId())) {
            // 外国株式取引口座開設状況が開設済ではない場合、外貨建口座未開設エラーを返す
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_FSTOCK_ACCOUNT_NOT_OPENED,
                    IfaCommonUtil.getMessage(MESSAGE_FSTOCK_ACCOUNT_NOT_OPENED));
        }
        
        // レスポンスデータを作成する
        IfaForeignSpotTradeOrderInputA001ResponseDto responseData = new IfaForeignSpotTradeOrderInputA001ResponseDto();
        BeanUtils.copyProperties(responseData, dtoReq);
        responseData.setCurrencyCode(CurrencyCode.USD.getId());

        return IfaCommonUtil.createDataList(Stream.of(responseData).collect(Collectors.toList()), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtils.EMPTY);
    }
    
    /**
     * アクションID：A003
     * アクション名：株価表示
     * Dto リクエスト：IfaForeignSpotTradeOrderInputA003RequestDto
     * Dto レスポンス：IfaForeignSpotTradeOrderInputA003ResponseDto
     *
     * @param dtoReq リクエストDto
     * @return Dto レスポンス
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignSpotTradeOrderInputA003ResponseDto> stockPriceDisplayA003(
            IfaForeignSpotTradeOrderInputA003RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignSpotTradeOrderInputServiceImpL.stockPriceDisplayA003");
        }
        
        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        // 口座に対する権限チェック
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(customerCommon.getButenCode());
        inputFct001Dto.setAccountNumber(customerCommon.getAccountNumber());
        
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        
        if (StringUtils.equals(outputFct001Dto.getTargetCustomerRefAuthFlag(),
                TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId())) {
            // 対象顧客参照権限有無が権限なしの場合、権限なしエラーを返す
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(MESSAGE_ACCOUNT_NOT_EXISTS,
                            new String[] { customerCommon.getButenCode(), customerCommon.getAccountNumber() }));
        } else if (StringUtils.equals(outputFct001Dto.getTradeSuspendFlag(), TradeSuspendFlag.SUSPEND.getId())) {
            // 取引停止口座の場合、取引停止エラーを返す
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_OUT_OF_SERVICE,
                    IfaCommonUtil.getMessage(MESSAGE_OUT_OF_SERVICE));
        }
        
        // 媒介可否チェック
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setButenCode(customerCommon.getButenCode());
        inputFct003Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct003Dto.setProductCd(CODE_VAL_SECURITY_CLASS_FSTOCK);
        inputFct003Dto.setTradeCd(ForeignStockTradeClass.SPOT_BUY.getId()); // 銘柄指定前なので、現物買付
        
        OutputFct003Dto outputFct003Dto = fct003.doCheck(inputFct003Dto);
        
        // 媒介可否リスト.媒介可否に媒介可が存在するか判定する
        if (outputFct003Dto.getMediateProprietyList().stream()
                .map(mediateProprieties -> mediateProprieties.getMediatePropriety()).noneMatch(
                        mediatePropriety -> StringUtils.equals(MediateAbleTradeFlag.ARI.getId(), mediatePropriety))) {
            // 存在しない場合、媒介不可エラーを返す
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_MEDIATE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(MESSAGE_MEDIATE_UNAVAILABLE, new String[] { codeListService
                            .getValue(CODE_ID_MSG_DISPLAY_TARGET_TRADE, CODE_VAL_MSG_DISPLAY_TARGET_TRADE_FSTOCK_ENTRUST) }));
        }
        
        if (!StringUtils.equals(customerCommon.getForeignStockTradeAccountOpenStatus(),
                ForeignStockTradeAccountOpenStatus.OPEN.getId())) {
            // 外国株式取引口座開設状況が開設済ではない場合、外貨建口座未開設エラーを返す
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_FSTOCK_ACCOUNT_NOT_OPENED,
                    IfaCommonUtil.getMessage(MESSAGE_FSTOCK_ACCOUNT_NOT_OPENED));
        }
        
        // 銘柄情報を取得する
        GetForeignStockSecuritiesResp api001Res = null;
        try {
            api001Res = foreignStockService.getForeignStockSecurities(dtoReq.getCountryCode(), dtoReq.getBrandCode());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.stockPriceDisplayA003}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // 銘柄上場ステータスを判定する
        if (StringUtils.equals(dtoReq.getBuySellTypeName(), ForeignStockTradeClass.SPOT_BUY.getId())) {
            // 取引種別が現物買付の場合、銘柄上場ステータスを判定する
            switch (ListedSecuritiesStatus.getById(api001Res.getListedSecuritiesStatus())) {
                case BUY_STOP:
                    // 買付停止の場合、買付停止エラーを返す
                    return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_TRADE_STOPPED_BUY_ONLY,
                            IfaCommonUtil.getMessage(MESSAGE_TRADE_STOPPED_BUY_ONLY));
                case BUY_SELL_STOP:
                    // 売買停止の場合、売買停止エラーを返す
                    return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_TRADE_STOPPED_BUY_SELL,
                            IfaCommonUtil.getMessage(MESSAGE_TRADE_STOPPED_BUY_SELL));
                case DELISTING:
                    // 上場廃止の場合、上場廃止エラーを返す
                    return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_DELISTED_STOCK,
                            IfaCommonUtil.getMessage(MESSAGE_DELISTED_STOCK));
                default:
                    break;
            }
        }
        
        // レスポンスデータに銘柄情報を設定する
        Securities api001Securities = api001Res.getSecurities();
        Market api001Market = api001Res.getMarket();
        IfaForeignSpotTradeOrderInputA003ResponseDto responseData = new IfaForeignSpotTradeOrderInputA003ResponseDto();
        
        responseData.setBrandName(api001Securities.getSecuritiesName());
        responseData.setBrandCode(api001Securities.getSecuritiesCode());
        responseData.setMarketAbbreviation(api001Market.getMarketShortName());
        responseData.setMarketCode(api001Market.getMarketCode());
        responseData.setCountryCode(api001Market.getCountryCode());
        responseData.setTimeZoneAbbreviation(api001Market.getTimeZoneShortName());
        responseData.setStockListingStatus(api001Res.getListedSecuritiesStatus());
        responseData.setMinimumTradingQuantity(api001Res.getTradeLimitMin());
        responseData.setMaximumTradingQuantity(api001Res.getTradeLimitMax());
        responseData.setTradingUnit(api001Res.getTradeUnit());
        responseData.setStockType(api001Res.getSecuritiesType());
        
        // 注意情報を取得する
        GetForeignStockAttentionSecuritiesResp api003Res = null;
        try {
            api003Res = foreignStockService.getForeignStockAttentionSecurities(dtoReq.getCountryCode(),
                    dtoReq.getBrandCode());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.stockPriceDisplayA003}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        responseData.setTradeLimit(api003Res.getAttentionSecurities().toString());
        
        // マーケット価格取得用チケットを取得する
        CreateMarketPriceTicketResp api004Res = null;
        try {
            api004Res = foreignInformationService.createMarketPriceTicket(customerCommon.getButenCode(),
                    customerCommon.getAccountNumber(), IP_ADDRESS, X_APP_USER_AGENT, dtoReq.getCountryCode());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.stockPriceDisplayA003}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // マーケット価格ハッシュを取得する
        GetMarketPriceHashResp api005Res = null;
        try {
            api005Res = foreignInformationService.getMarketPriceHash(customerCommon.getButenCode(),
                    customerCommon.getAccountNumber(), api004Res.getPriceTicket(), dtoReq.getCountryCode());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.stockPriceDisplayA003}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // 株価を取得する
        ListMarketPricesResp api006Res = null;
        try {
            api006Res = foreignInformationService.listMarketPrices(api005Res.getHashValue(), dtoReq.getCountryCode(),
                    new String[] { api001Securities.getRic() });
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.stockPriceDisplayA003}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        if (CollectionUtils.isNotEmpty(api006Res.getMarketPrices())) {
            PriceData api006PriceData = api006Res.getMarketPrices().get(0).getPrice();
            
            // 株価情報を設定する
            IfaForeignSpotTradeOrderInputPriceBasicInfo priceBasicInfo = new IfaForeignSpotTradeOrderInputPriceBasicInfo();
            priceBasicInfo.setCurrentPrice(api006PriceData.getLast());
            priceBasicInfo.setCurrentPriceDate(api006PriceData.getLastDatetime());
            priceBasicInfo.setTick(api006PriceData.getTickArrow());
            priceBasicInfo.setDiff(api006PriceData.getChange());
            priceBasicInfo.setRatio(api006PriceData.getChangePercent());
            priceBasicInfo.setStart(api006PriceData.getOpen());
            priceBasicInfo.setStartPriceDate(api006PriceData.getOpenDatetime());
            priceBasicInfo.setHigh(api006PriceData.getHigh());
            priceBasicInfo.setHighPriceDate(api006PriceData.getHighDatetime());
            priceBasicInfo.setLow(api006PriceData.getLow());
            priceBasicInfo.setLowPriceDate(api006PriceData.getLowDatetime());
            priceBasicInfo.setVolume(api006PriceData.getVolume());
            priceBasicInfo.setLast(api006PriceData.getPrevClose());
            priceBasicInfo.setLastDate(api006PriceData.getPrevCloseDate());
            responseData.setPriceBasicInfo(priceBasicInfo);
        }
        
        // 注文初期情報を取得する
        GetForeignStockCreatedOrderInitializationResp api002Res = null;
        try {
            api002Res = foreignStockService.getForeignStockCreatedOrderInitialization(customerCommon.getButenCode(),
                    customerCommon.getAccountNumber(), dtoReq.getCountryCode(), dtoReq.getBrandCode(),
                    dtoReq.getBuySellTypeName());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.stockPriceDisplayA003}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        responseData.setCallValue(api002Res.getTickSizes());
        
        responseData.setOrderPriceKindList(api002Res.getOrderPriceKindCodes());
        responseData.setLimitPriceText(api002Res.getTradeCurrencyCode());
        responseData.setValidPeriodList(api002Res.getOrderTerms());
        responseData.setSelectAblePeriodTermsList(api002Res.getOrderLimitCodes());
        responseData.setDepositTypeList(api002Res.getSpecificAccountCodes());
        responseData.setStockPriceKindList(api002Res.getSettlementMethodCodes());
        responseData.setPriceRangeNoLimit(api002Res.getPriceRangeNoLimit().toString());
        responseData.setPriceRangeLimitMin(api002Res.getPriceRangeLimitMin());
        responseData.setPriceRangeLimitMax(api002Res.getPriceRangeLimitMax());
        
        // 国籍コードがロシア(ロシア株)の場合、ロシア呼値を取得する
        GetForeignStockRuTickSizeResp api014Res = null;
        if (StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.RU.getId())) {
            try {
                api014Res = foreignStockService.getForeignStockRuTickSize(dtoReq.getBrandCode());
            } catch (Exception e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.stockPriceDisplayA003}", e);
                }
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
            responseData.setRussianCallValue(api014Res.getTickSize());
        }
        
        if (StringUtils.equals(dtoReq.getBuySellTypeName(), ForeignStockTradeClass.SPOT_SELL.getId())) {
            // 取引種別が現物売却の場合、売却可能数のリストから預り区分が一致するものを取得する
            Optional<SellPossibleQuantity> api002SellPossibleQuantity = Optional.empty();
            if (CollectionUtils.isNotEmpty(api002Res.getSellPossibleQuantities())) {
                api002SellPossibleQuantity = api002Res.getSellPossibleQuantities().stream()
                        .filter(sellPossibleQuantity -> StringUtils
                                .equals(sellPossibleQuantity.getSpecificAccountCode(), dtoReq.getDepositType()))
                        .findFirst();
                api002SellPossibleQuantity.ifPresent(sellPossibleQuantity -> {
                    responseData.setSellableCustodyCategory(sellPossibleQuantity.getSpecificAccountCode());
                    responseData.setSellableProtectionCategory(sellPossibleQuantity.getDepositType());
                });
            }
            
            // 売却可能数を取得する
            if (StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.US.getId())
                    && StringUtils.equals(dtoReq.getBuySellTypeName(), ForeignStockTradeClass.SPOT_SELL.getId())) {
                // 国籍コードが米国(米株)の場合
                InputFct028Dto inputFct028Dto = new InputFct028Dto();
                inputFct028Dto.setButenCode(customerCommon.getButenCode());
                inputFct028Dto.setAccountNumber(customerCommon.getAccountNumber());
                inputFct028Dto.setBrandCode(dtoReq.getBrandCode());
                inputFct028Dto.setDepositType(dtoReq.getDepositType());
                OutputFct028Dto outputFct028Dto = fct028.doCheck(inputFct028Dto);
                responseData.setAcPosition(outputFct028Dto.getAcPositionStockNumber().toPlainString());
            } else {
                // 米国以外の場合、預り区分が一致する売却可能数を取得する
                api002SellPossibleQuantity.ifPresent(sellPossibleQuantity -> {
                    responseData.setAcPosition(sellPossibleQuantity.getSecuritiesQuantity());
                });
            }
        }
        
        // 英文開示銘柄判定を取得する
        InputFct029Dto inputFct029Dto = new InputFct029Dto();
        inputFct029Dto.setNationalityCode(dtoReq.getCountryCode());
        inputFct029Dto.setBrandCode(dtoReq.getBrandCode());
        OutputFct029Dto outputFct029Dto = fct029.doCheck(inputFct029Dto);
        responseData.setEngPubCheck(outputFct029Dto.getIssuesDisclosedInEnglishBrandJudge());
        
        // 外貨の買付余力情報を取得する
        ListForeignScheduleCashBalancesReq api007Req = new ListForeignScheduleCashBalancesReq();
        api007Req.getHeader()
                .setToken(RequestUtil.getToken(customerCommon.getButenCode(), customerCommon.getAccountNumber()));
        api007Req.getParameter().setCurrencyCode(api001Res.getCurrencyCode());
        api007Req.getParameter().setDays(FRS_BUYING_POWER_LIST_DAYS);
        ListForeignScheduleCashBalancesResp api007Res = null;
        try {
            api007Res = foreignAccountService.listForeignScheduleCashBalances(api007Req);
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.stockPriceDisplayA003}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        IfaForeignSpotTradeOrderInputBuyingPowerForeign foreignBuyingPower = new IfaForeignSpotTradeOrderInputBuyingPowerForeign();
        CurrencyCashBalance api007CurrencyCashBalance = api007Res
                .getForeignCashBalances().stream().filter(foreignCashBalance -> StringUtils
                        .equals(foreignCashBalance.getAccountKind(), FOREIGN_DEPOSIT_TYPE_GENERAL))
                .findFirst().get().getCurrencyCashBalances().get(0);
        foreignBuyingPower.setCurrencyCode(api007CurrencyCashBalance.getCurrencyCode());
        
        // 外貨金銭残高スケジュールリストから買付余力を取得する
        foreignBuyingPower.setForeignBuyingPower(api007CurrencyCashBalance.getForeignScheduleCashBalances().stream()
                .filter(foreignScheduleCashBalance -> {
                    if (StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.RU.getId())
                            || StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.VN.getId())) {
                        // ロシア、もしくはベトナムの場合はT+4
                        return foreignScheduleCashBalance.getDaysLater()
                                .intValue() == FOREIGN_CASH_BALANCES_T_DAY_RU_VN;
                    } else {
                        // ロシア、ベトナム以外の場合はT+3
                        return foreignScheduleCashBalance.getDaysLater().intValue() == FOREIGN_CASH_BALANCES_T_DAY;
                    }
                }).findFirst().get().getBuyPossibleAmount());
        
        // 米株店頭、外債買付注文金額を差引後の余力を取得する
        InputFct004Dto inputFct004Dto = new InputFct004Dto();
        inputFct004Dto.setButenCode(customerCommon.getButenCode());
        inputFct004Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct004Dto.setDepositType(FOREIGN_DEPOSIT_TYPE_GENERAL);
        inputFct004Dto.setOtcManageNumber(StringUtils.EMPTY);
        inputFct004Dto.setTradeType(StringUtils.EMPTY);
        OutputFct004Dto outputFct004Dto = fct004.doCheck(inputFct004Dto);
        
        // 国籍コードが米国(米株)の場合、米株店頭、外債買付注文金額を差引後の余力を設定する
        if (StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.US.getId())) {
            foreignBuyingPower
                    .setForeignBuyingPower(outputFct004Dto.getByingPowerMoneyAfterCalculate().toPlainString());
        }
        
        // 円貨の買付余力情報を取得する
        ListScheduleCashBalancesResp api008Res = null;
        try {
            api008Res = foreignAccountService.listScheduleCashBalances(customerCommon.getButenCode(),
                    customerCommon.getAccountNumber(), null, FRS_BUYING_POWER_LIST_DAYS);
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.stockPriceDisplayA003}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        IfaForeignSpotTradeOrderInputBuyingPowerDomestic domesticBuyingPower = new IfaForeignSpotTradeOrderInputBuyingPowerDomestic();
        if (StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.RU.getId())
                || StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.VN.getId())) {
            // ロシア、もしくはベトナムの場合はT+4の買付余力を設定する
            domesticBuyingPower.setYenBuyingPowerGeneralAccount(api008Res.getCashBalances().stream()
                    .filter(cashBalance -> StringUtils.equals(cashBalance.getAccountKind(),
                            FOREIGN_DEPOSIT_TYPE_GENERAL))
                    .findFirst().get().getScheduleCashBalances().stream()
                    .sorted(Comparator.comparing(ScheduleCashBalance::getBusinessDate)).collect(Collectors.toList())
                    .get(FOREIGN_CASH_BALANCES_T_DAY_RU_VN).getBuyPossibleAmount());
        } else {
            // ロシア、ベトナム以外の場合はT+3の買付余力を取得する
            domesticBuyingPower.setYenBuyingPowerGeneralAccount(api008Res.getCashBalances().stream()
                    .filter(cashBalance -> StringUtils.equals(cashBalance.getAccountKind(),
                            FOREIGN_DEPOSIT_TYPE_GENERAL))
                    .findFirst().get().getScheduleCashBalances().stream()
                    .sorted(Comparator.comparing(ScheduleCashBalance::getBusinessDate)).collect(Collectors.toList())
                    .get(FOREIGN_CASH_BALANCES_T_DAY).getBuyPossibleAmount());
        }
        
        // 顧客共通情報.ISA契約区分が契約（NISA口座開設済み）の場合、NISA投資可能枠を設定する
        if (StringUtils.equals(customerCommon.getIsaContractType(), CODE_VAL_ISA_CONTRACT_TYPE)) {
            List<IfaForeignSpotTradeOrderInputBuyingPowerDomesticNisaBuyLimit> resNisaLimitList = new ArrayList<>();
            Map<Integer, List<NisaLimit>> nisaLimitByYear = api008Res.getNisaLimits().stream()
                    .collect(Collectors.groupingByConcurrent(NisaLimit::getNisaBuyYear));
            nisaLimitByYear.keySet().stream().sorted().forEachOrdered(nisaBuyYear -> {
                List<NisaLimit> nisaLimitListOnYear = nisaLimitByYear.get(nisaBuyYear);
                if (nisaLimitListOnYear.stream()
                        .anyMatch(nisaLimit -> StringUtils.equals(nisaLimit.getNisaType(), NISA_TYPE_GENERAL_NISA)
                                && StringUtils.equals(nisaLimit.getNisaBuyLimitStop(),
                                        NisaBuyLimitStopType.INITIALIZATION.getId()))) {
                    // NISA区分が総合NISAで、NISA投資可能枠利用停止種別が初期値（枠利用可）のNISA投資可能枠が存在する場合、成長投資枠の買付可能枠を取得する
                    NisaLimit generalNisaLimit = nisaLimitListOnYear.stream()
                            .filter(nisaLimit -> StringUtils.equals(nisaLimit.getNisaType(), NISA_TYPE_GENERAL_NISA)
                                    && StringUtils.equals(nisaLimit.getNisaBuyLimitStop(),
                                            NisaBuyLimitStopType.INITIALIZATION.getId()))
                            .findFirst().get();
                    IfaForeignSpotTradeOrderInputBuyingPowerDomesticNisaBuyLimit resNisaLimit = new IfaForeignSpotTradeOrderInputBuyingPowerDomesticNisaBuyLimit();
                    resNisaLimit.setAvailableBuyingLimit(generalNisaLimit.getGeneralNisaGrowthInvestmentBuyLimitAmount());
                    resNisaLimit.setAnnualAvailableBuyingLimit(generalNisaLimit.getNisaBuyYear().toString());
                    resNisaLimit.setAccountClassification(generalNisaLimit.getAccountKind());
                    resNisaLimitList.add(resNisaLimit);
                } else if (nisaLimitListOnYear.stream()
                        .anyMatch(nisaLimit -> StringUtils.equals(nisaLimit.getNisaType(), NisaType.NISA.getId())
                                && StringUtils.equals(nisaLimit.getNisaBuyLimitStop(),
                                        NisaBuyLimitStopType.INITIALIZATION.getId()))) {
                    /* 
                     * NISA区分が総合NISAで、NISA投資可能枠利用停止種別が初期値（枠利用可）のNISA投資可能枠が存在しない場合、
                     * NISA区分がNISAで、NISA投資可能枠利用停止種別が初期値（枠利用可）のNISA投資可能枠が存在すれば、買付可能枠を取得する
                     */
                    NisaLimit oldNisaLimit = nisaLimitListOnYear.stream()
                            .filter(nisaLimit -> StringUtils.equals(nisaLimit.getNisaType(), NisaType.NISA.getId())
                                    && StringUtils.equals(nisaLimit.getNisaBuyLimitStop(),
                                            NisaBuyLimitStopType.INITIALIZATION.getId()))
                            .findFirst().get();
                    IfaForeignSpotTradeOrderInputBuyingPowerDomesticNisaBuyLimit resNisaLimit = new IfaForeignSpotTradeOrderInputBuyingPowerDomesticNisaBuyLimit();
                    resNisaLimit.setAvailableBuyingLimit(oldNisaLimit.getNisaBuyLimitAmount());
                    resNisaLimit.setAnnualAvailableBuyingLimit(oldNisaLimit.getNisaBuyYear().toString());
                    resNisaLimit.setAccountClassification(oldNisaLimit.getAccountKind());
                    resNisaLimitList.add(resNisaLimit);
                }
            });
            domesticBuyingPower.setNisaBuyLimitList(resNisaLimitList);
        }
        
        // 顧客共通情報.ジュニアISA契約区分が契約の場合、JrNISAの余力情報を設定する
        if (StringUtils.equals(customerCommon.getJrIsaContractType(), JrIsaContractType.CONTRACT.getId())) {
            // 外貨余力を取得する
            CurrencyCashBalance currencyCashBalanceJrNisa = api007Res
                .getForeignCashBalances().stream()
                .filter(foreignCashBalance -> StringUtils.equals(foreignCashBalance.getAccountKind(), FOREIGN_DEPOSIT_TYPE_JR_NISA))
                .findFirst().map(foreignCashBalance -> foreignCashBalance.getCurrencyCashBalances().get(0)).orElse(null);

            if (currencyCashBalanceJrNisa != null) {
                String api007ForeignBuyingPower = currencyCashBalanceJrNisa.getForeignScheduleCashBalances().stream()
                        .filter(foreignScheduleCashBalance -> {
                            if (StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.RU.getId())
                                    || StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.VN.getId())) {
                                // ロシア、もしくはベトナムの場合はT+4の買付余力を参照する
                                return foreignScheduleCashBalance.getDaysLater()
                                        .intValue() == FOREIGN_CASH_BALANCES_T_DAY_RU_VN;
                            } else {
                                // ロシア、ベトナム以外の場合はT+3の買付余力を参照する
                                return foreignScheduleCashBalance.getDaysLater().intValue() == FOREIGN_CASH_BALANCES_T_DAY;
                            }
                        }).findFirst().get().getBuyPossibleAmount();
                if (StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.US.getId())) {
                    // 国籍コードが米国(米株)の場合、米株店頭、外債買付注文金額を差引後の余力を設定する
                    foreignBuyingPower.setForeignBuyingPowerJrNisa(new BigDecimal(api007ForeignBuyingPower)
                            .subtract(outputFct004Dto.getOtcBuyingContractAmountToday())
                            .subtract(outputFct004Dto.getContractAmountTodayWithinForeignBond()).toPlainString());
                } else {
                    foreignBuyingPower.setForeignBuyingPowerJrNisa(api007ForeignBuyingPower);
                }
                foreignBuyingPower.setCurrencyCodeJrNisa(currencyCashBalanceJrNisa.getCurrencyCode());
            }

            // 円貨余力を取得する
            if (StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.RU.getId())
                    || StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.VN.getId())) {
                // ロシア、もしくはベトナムの場合はT+4の買付余力を設定する
                domesticBuyingPower.setYenBuyingPowerJrNisa(api008Res.getCashBalances().stream()
                        .filter(cashBalance -> StringUtils.equals(cashBalance.getAccountKind(),
                                FOREIGN_DEPOSIT_TYPE_JR_NISA))
                        .findFirst().get().getScheduleCashBalances().stream()
                        .sorted(Comparator.comparing(ScheduleCashBalance::getBusinessDate)).collect(Collectors.toList())
                        .get(FOREIGN_CASH_BALANCES_T_DAY_RU_VN).getBuyPossibleAmount());
            } else {
                // ロシア、ベトナム以外の場合はT+3の買付余力を取得する
                domesticBuyingPower.setYenBuyingPowerJrNisa(api008Res.getCashBalances().stream()
                        .filter(cashBalance -> StringUtils.equals(cashBalance.getAccountKind(),
                                FOREIGN_DEPOSIT_TYPE_JR_NISA))
                        .findFirst().get().getScheduleCashBalances().stream()
                        .sorted(Comparator.comparing(ScheduleCashBalance::getBusinessDate)).collect(Collectors.toList())
                        .get(FOREIGN_CASH_BALANCES_T_DAY).getBuyPossibleAmount());
            }
        }
        
        // リンクURLを取得する
        responseData.setYenClosed(medSystemVariableService.getMedSystemVariable(FRS_YEN_CLOSED_URL));
        responseData.setTradingAttention(medSystemVariableService.getMedSystemVariable(FRS_STOCK_QAI_22_URL));
        
        // レスポンスデータを作成する
        responseData.setBuyingPowerForeignList(Stream.of(foreignBuyingPower).collect(Collectors.toList()));
        responseData.setBuyingPowerDomesticList(Stream.of(domesticBuyingPower).collect(Collectors.toList()));

        return IfaCommonUtil.createDataList(Stream.of(responseData).collect(Collectors.toList()), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtils.EMPTY);
    }
    
    /**
     * アクションID：A005
     * アクション名：更新
     * Dto リクエスト：IfaForeignSpotTradeOrderInputA005RequestDto
     * Dto レスポンス：IfaForeignSpotTradeOrderInputA005ResponseDto
     *
     * @param dtoReq リクエストDto
     * @return 更新レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignSpotTradeOrderInputA005ResponseDto> updateA005(
            IfaForeignSpotTradeOrderInputA005RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignSpotTradeOrderInputServiceImpL.updateA005");
        }
        
        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        // 口座に対する権限チェック
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(customerCommon.getButenCode());
        inputFct001Dto.setAccountNumber(customerCommon.getAccountNumber());
        
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        
        if (StringUtils.equals(outputFct001Dto.getTargetCustomerRefAuthFlag(),
                TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId())) {
            // 対象顧客参照権限有無が権限なしの場合、権限なしエラーを返す
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(MESSAGE_ACCOUNT_NOT_EXISTS,
                            new String[] { customerCommon.getButenCode(), customerCommon.getAccountNumber() }));
        } else if (StringUtils.equals(outputFct001Dto.getTradeSuspendFlag(), TradeSuspendFlag.SUSPEND.getId())) {
            // 取引停止口座の場合、取引停止エラーを返す
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_OUT_OF_SERVICE,
                    IfaCommonUtil.getMessage(MESSAGE_OUT_OF_SERVICE));
        }
        
        // 媒介可否チェック
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setButenCode(customerCommon.getButenCode());
        inputFct003Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct003Dto.setProductCd(CODE_VAL_SECURITY_CLASS_FSTOCK);
        inputFct003Dto.setTradeCd(ForeignStockTradeClass.SPOT_BUY.getId()); // 銘柄指定前なので、現物買付
        
        OutputFct003Dto outputFct003Dto = fct003.doCheck(inputFct003Dto);
        
        // 媒介可否リスト.媒介可否に媒介可が存在するか判定する
        if (outputFct003Dto.getMediateProprietyList().stream()
                .map(mediateProprieties -> mediateProprieties.getMediatePropriety()).noneMatch(
                        mediatePropriety -> StringUtils.equals(MediateAbleTradeFlag.ARI.getId(), mediatePropriety))) {
            // 存在しない場合、媒介不可エラーを返す
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_MEDIATE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(MESSAGE_MEDIATE_UNAVAILABLE, new String[] { codeListService
                            .getValue(CODE_ID_MSG_DISPLAY_TARGET_TRADE, CODE_VAL_MSG_DISPLAY_TARGET_TRADE_FSTOCK_ENTRUST) }));
        }
        
        if (!StringUtils.equals(customerCommon.getForeignStockTradeAccountOpenStatus(),
                ForeignStockTradeAccountOpenStatus.OPEN.getId())) {
            // 外国株式取引口座開設状況が開設済ではない場合、外貨建口座未開設エラーを返す
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_FSTOCK_ACCOUNT_NOT_OPENED,
                    IfaCommonUtil.getMessage(MESSAGE_FSTOCK_ACCOUNT_NOT_OPENED));
        }
        
        // 注文初期情報を取得する
        GetForeignStockCreatedOrderInitializationResp api002Res = null;
        try {
            api002Res = foreignStockService.getForeignStockCreatedOrderInitialization(customerCommon.getButenCode(),
                    customerCommon.getAccountNumber(), dtoReq.getCountryCode(), dtoReq.getBrandCode(),
                    dtoReq.getBuySellTypeName());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.updateA005}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // 注文初期情報の値幅情報を取得する
        IfaForeignSpotTradeOrderInputA005ResponseDto responseData = new IfaForeignSpotTradeOrderInputA005ResponseDto();
        responseData.setPriceRangeNoLimit(api002Res.getPriceRangeNoLimit().toString());
        responseData.setPriceRangeLimitMin(api002Res.getPriceRangeLimitMin());
        responseData.setPriceRangeLimitMax(api002Res.getPriceRangeLimitMax());
        
        if (StringUtils.equals(dtoReq.getBuySellTypeName(), ForeignStockTradeClass.SPOT_SELL.getId())) {
            // 取引種別が現物売却の場合、売却可能数のリストから預り区分が一致するものを取得する
            Optional<SellPossibleQuantity> api002SellPossibleQuantity = Optional.empty();
            if (CollectionUtils.isNotEmpty(api002Res.getSellPossibleQuantities())) {
                api002SellPossibleQuantity = api002Res.getSellPossibleQuantities().stream()
                        .filter(sellPossibleQuantity -> StringUtils
                                .equals(sellPossibleQuantity.getSpecificAccountCode(), dtoReq.getDepositType()))
                        .findFirst();
                api002SellPossibleQuantity.ifPresent(sellPossibleQuantity -> {
                    responseData.setSellableCustodyCategory(sellPossibleQuantity.getSpecificAccountCode());
                    responseData.setSellableProtectionCategory(sellPossibleQuantity.getDepositType());
                });
            }
            
            // 売却可能数を取得する
            if (StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.US.getId())) {
                // 国籍コードが米国(米株)の場合
                final InputFct028Dto inputFct028Dto = new InputFct028Dto();
                inputFct028Dto.setButenCode(customerCommon.getButenCode());
                inputFct028Dto.setAccountNumber(customerCommon.getAccountNumber());
                inputFct028Dto.setBrandCode(dtoReq.getBrandCode());
                inputFct028Dto.setDepositType(dtoReq.getDepositType());
                OutputFct028Dto outputFct028Dto = fct028.doCheck(inputFct028Dto);
                responseData.setAcPosition(outputFct028Dto.getAcPositionStockNumber().toPlainString());
            } else {
                // 米国以外の場合、預り区分が一致する売却可能数を取得する
                api002SellPossibleQuantity.ifPresent(sellPossibleQuantity -> {
                    responseData.setAcPosition(sellPossibleQuantity.getSecuritiesQuantity());
                });
            }
        }
        
        // 外貨の買付余力情報を取得する
        ListForeignScheduleCashBalancesReq api007Req = new ListForeignScheduleCashBalancesReq();
        api007Req.getHeader()
                .setToken(RequestUtil.getToken(customerCommon.getButenCode(), customerCommon.getAccountNumber()));
        api007Req.getParameter().setCurrencyCode(dtoReq.getLimitPriceText());
        api007Req.getParameter().setDays(FRS_BUYING_POWER_LIST_DAYS);
        
        ListForeignScheduleCashBalancesResp api007Res = null;
        try {
            api007Res = foreignAccountService.listForeignScheduleCashBalances(api007Req);
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.updateA005}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        IfaForeignSpotTradeOrderInputBuyingPowerForeign foreignBuyingPower = new IfaForeignSpotTradeOrderInputBuyingPowerForeign();
        CurrencyCashBalance api007CurrencyCashBalance = api007Res
                .getForeignCashBalances().stream().filter(foreignCashBalance -> StringUtils
                        .equals(foreignCashBalance.getAccountKind(), FOREIGN_DEPOSIT_TYPE_GENERAL))
                .findFirst().get().getCurrencyCashBalances().get(0);
        foreignBuyingPower.setCurrencyCode(api007CurrencyCashBalance.getCurrencyCode());
        
        // 外貨金銭残高スケジュールリストから買付余力を取得する
        foreignBuyingPower.setForeignBuyingPower(api007CurrencyCashBalance.getForeignScheduleCashBalances().stream()
                .filter(foreignScheduleCashBalance -> {
                    if (StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.RU.getId())
                            || StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.VN.getId())) {
                        // ロシア、もしくはベトナムの場合はT+4
                        return foreignScheduleCashBalance.getDaysLater()
                                .intValue() == FOREIGN_CASH_BALANCES_T_DAY_RU_VN;
                    } else {
                        // ロシア、ベトナム以外の場合はT+3
                        return foreignScheduleCashBalance.getDaysLater().intValue() == FOREIGN_CASH_BALANCES_T_DAY;
                    }
                }).findFirst().get().getBuyPossibleAmount());
        
        // 米株店頭、外債買付注文金額を差引後の余力を取得する
        InputFct004Dto inputFct004Dto = new InputFct004Dto();
        inputFct004Dto.setButenCode(customerCommon.getButenCode());
        inputFct004Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct004Dto.setDepositType(FOREIGN_DEPOSIT_TYPE_GENERAL);
        inputFct004Dto.setOtcManageNumber(StringUtils.EMPTY);
        inputFct004Dto.setTradeType(StringUtils.EMPTY);
        OutputFct004Dto outputFct004Dto = fct004.doCheck(inputFct004Dto);
        
        // 国籍コードが米国(米株)の場合、米株店頭、外債買付注文金額を差引後の余力を設定する
        if (StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.US.getId())) {
            foreignBuyingPower
                    .setForeignBuyingPower(outputFct004Dto.getByingPowerMoneyAfterCalculate().toPlainString());
        }
        
        // 円貨の買付余力情報を取得する
        ListScheduleCashBalancesResp api008Res = null;
        try {
            api008Res = foreignAccountService.listScheduleCashBalances(customerCommon.getButenCode(),
                    customerCommon.getAccountNumber(), null, FRS_BUYING_POWER_LIST_DAYS);
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.updateA005}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        IfaForeignSpotTradeOrderInputBuyingPowerDomestic domesticBuyingPower = new IfaForeignSpotTradeOrderInputBuyingPowerDomestic();
        CashBalances api008CashBalanceGeneral = api008Res.getCashBalances().stream()
                .filter(cashBalance -> StringUtils.equals(cashBalance.getAccountKind(), FOREIGN_DEPOSIT_TYPE_GENERAL))
                .findFirst().get();
        if (StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.RU.getId())
                || StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.VN.getId())) {
            // ロシア、もしくはベトナムの場合はT+4の買付余力を設定する
            domesticBuyingPower.setYenBuyingPowerGeneralAccount(api008CashBalanceGeneral.getScheduleCashBalances()
                    .stream().sorted(Comparator.comparing(ScheduleCashBalance::getBusinessDate)).collect(Collectors.toList())
                    .get(FOREIGN_CASH_BALANCES_T_DAY_RU_VN).getBuyPossibleAmount());
        } else {
            // ロシア、ベトナム以外の場合はT+3の買付余力を取得する
            domesticBuyingPower.setYenBuyingPowerGeneralAccount(api008CashBalanceGeneral.getScheduleCashBalances()
                    .stream().sorted(Comparator.comparing(ScheduleCashBalance::getBusinessDate)).collect(Collectors.toList())
                    .get(FOREIGN_CASH_BALANCES_T_DAY).getBuyPossibleAmount());
        }
        
        // 顧客共通情報.ISA契約区分が契約（NISA口座開設済み）の場合、NISA投資可能枠を設定する
        if (StringUtils.equals(customerCommon.getIsaContractType(), CODE_VAL_ISA_CONTRACT_TYPE)) {
            List<IfaForeignSpotTradeOrderInputBuyingPowerDomesticNisaBuyLimit> resNisaLimitList = new ArrayList<>();
            Map<Integer, List<NisaLimit>> nisaLimitByYear = api008Res.getNisaLimits().stream()
                    .collect(Collectors.groupingByConcurrent(NisaLimit::getNisaBuyYear));
            nisaLimitByYear.keySet().stream().sorted().forEachOrdered(nisaBuyYear -> {
                List<NisaLimit> nisaLimitListOnYear = nisaLimitByYear.get(nisaBuyYear);
                if (nisaLimitListOnYear.stream()
                        .anyMatch(nisaLimit -> StringUtils.equals(nisaLimit.getNisaType(), NISA_TYPE_GENERAL_NISA)
                                && StringUtils.equals(nisaLimit.getNisaBuyLimitStop(),
                                        NisaBuyLimitStopType.INITIALIZATION.getId()))) {
                    // NISA区分が総合NISAで、NISA投資可能枠利用停止種別が初期値（枠利用可）のNISA投資可能枠が存在する場合、成長投資枠の買付可能枠を取得する
                    NisaLimit generalNisaLimit = nisaLimitListOnYear.stream()
                            .filter(nisaLimit -> StringUtils.equals(nisaLimit.getNisaType(), NISA_TYPE_GENERAL_NISA)
                                    && StringUtils.equals(nisaLimit.getNisaBuyLimitStop(),
                                            NisaBuyLimitStopType.INITIALIZATION.getId()))
                            .findFirst().get();
                    IfaForeignSpotTradeOrderInputBuyingPowerDomesticNisaBuyLimit resNisaLimit = new IfaForeignSpotTradeOrderInputBuyingPowerDomesticNisaBuyLimit();
                    resNisaLimit.setAvailableBuyingLimit(generalNisaLimit.getGeneralNisaGrowthInvestmentBuyLimitAmount());
                    resNisaLimit.setAnnualAvailableBuyingLimit(generalNisaLimit.getNisaBuyYear().toString());
                    resNisaLimit.setAccountClassification(generalNisaLimit.getAccountKind());
                    resNisaLimitList.add(resNisaLimit);
                } else if (nisaLimitListOnYear.stream()
                        .anyMatch(nisaLimit -> StringUtils.equals(nisaLimit.getNisaType(), NisaType.NISA.getId())
                                && StringUtils.equals(nisaLimit.getNisaBuyLimitStop(),
                                        NisaBuyLimitStopType.INITIALIZATION.getId()))) {
                    /* 
                     * NISA区分が総合NISAで、NISA投資可能枠利用停止種別が初期値（枠利用可）のNISA投資可能枠が存在しない場合、
                     * NISA区分がNISAで、NISA投資可能枠利用停止種別が初期値（枠利用可）のNISA投資可能枠が存在すれば、買付可能枠を取得する
                     */
                    NisaLimit oldNisaLimit = nisaLimitListOnYear.stream()
                            .filter(nisaLimit -> StringUtils.equals(nisaLimit.getNisaType(), NisaType.NISA.getId())
                                    && StringUtils.equals(nisaLimit.getNisaBuyLimitStop(),
                                            NisaBuyLimitStopType.INITIALIZATION.getId()))
                            .findFirst().get();
                    IfaForeignSpotTradeOrderInputBuyingPowerDomesticNisaBuyLimit resNisaLimit = new IfaForeignSpotTradeOrderInputBuyingPowerDomesticNisaBuyLimit();
                    resNisaLimit.setAvailableBuyingLimit(oldNisaLimit.getNisaBuyLimitAmount());
                    resNisaLimit.setAnnualAvailableBuyingLimit(oldNisaLimit.getNisaBuyYear().toString());
                    resNisaLimit.setAccountClassification(oldNisaLimit.getAccountKind());
                    resNisaLimitList.add(resNisaLimit);
                }
            });
            domesticBuyingPower.setNisaBuyLimitList(resNisaLimitList);
        }
        
        // 顧客共通情報.ジュニアISA契約区分が契約の場合、JrNISAの余力情報を設定する
        if (StringUtils.equals(customerCommon.getJrIsaContractType(), JrIsaContractType.CONTRACT.getId())) {
            // 外貨余力を取得する
            CurrencyCashBalance currencyCashBalanceJrNisa = api007Res
                .getForeignCashBalances().stream()
                .filter(foreignCashBalance -> StringUtils.equals(foreignCashBalance.getAccountKind(), FOREIGN_DEPOSIT_TYPE_JR_NISA))
                .findFirst().map(foreignCashBalance -> foreignCashBalance.getCurrencyCashBalances().get(0)).orElse(null);

            if (currencyCashBalanceJrNisa != null) {
                String api007ForeignBuyingPower = currencyCashBalanceJrNisa.getForeignScheduleCashBalances().stream()
                        .filter(foreignScheduleCashBalance -> {
                            if (StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.RU.getId())
                                    || StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.VN.getId())) {
                                // ロシア、もしくはベトナムの場合はT+4の買付余力を参照する
                                return foreignScheduleCashBalance.getDaysLater()
                                        .intValue() == FOREIGN_CASH_BALANCES_T_DAY_RU_VN;
                            } else {
                                // ロシア、ベトナム以外の場合はT+3の買付余力を参照する
                                return foreignScheduleCashBalance.getDaysLater().intValue() == FOREIGN_CASH_BALANCES_T_DAY;
                            }
                        }).findFirst().get().getBuyPossibleAmount();
                if (StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.US.getId())) {
                    // 国籍コードが米国(米株)の場合、米株店頭、外債買付注文金額を差引後の余力を設定する
                    foreignBuyingPower.setForeignBuyingPowerJrNisa(new BigDecimal(api007ForeignBuyingPower)
                            .subtract(outputFct004Dto.getOtcBuyingContractAmountToday())
                            .subtract(outputFct004Dto.getContractAmountTodayWithinForeignBond()).toPlainString());
                } else {
                    foreignBuyingPower.setForeignBuyingPowerJrNisa(api007ForeignBuyingPower);
                }
                foreignBuyingPower.setCurrencyCodeJrNisa(currencyCashBalanceJrNisa.getCurrencyCode());
            }

            // 円貨余力を取得する
            if (StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.RU.getId())
                    || StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.VN.getId())) {
                // ロシア、もしくはベトナムの場合はT+4の買付余力を設定する
                domesticBuyingPower.setYenBuyingPowerJrNisa(api008Res.getCashBalances().stream()
                        .filter(cashBalance -> StringUtils.equals(cashBalance.getAccountKind(),
                                FOREIGN_DEPOSIT_TYPE_JR_NISA))
                        .findFirst().get().getScheduleCashBalances().stream()
                        .sorted(Comparator.comparing(ScheduleCashBalance::getBusinessDate)).collect(Collectors.toList())
                        .get(FOREIGN_CASH_BALANCES_T_DAY_RU_VN).getBuyPossibleAmount());
            } else {
                // ロシア、ベトナム以外の場合はT+3の買付余力を取得する
                domesticBuyingPower.setYenBuyingPowerJrNisa(api008Res.getCashBalances().stream()
                        .filter(cashBalance -> StringUtils.equals(cashBalance.getAccountKind(),
                                FOREIGN_DEPOSIT_TYPE_JR_NISA))
                        .findFirst().get().getScheduleCashBalances().stream()
                        .sorted(Comparator.comparing(ScheduleCashBalance::getBusinessDate)).collect(Collectors.toList())
                        .get(FOREIGN_CASH_BALANCES_T_DAY).getBuyPossibleAmount());
            }
        }
        
        // 銘柄情報を取得する
        GetForeignStockSecuritiesResp api001Res = null;
        try {
            api001Res = foreignStockService.getForeignStockSecurities(dtoReq.getCountryCode(), dtoReq.getBrandCode());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.updateA005}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // マーケット価格取得用チケットを取得する
        CreateMarketPriceTicketResp api004Res = null;
        try {
            api004Res = foreignInformationService.createMarketPriceTicket(customerCommon.getButenCode(),
                    customerCommon.getAccountNumber(), IP_ADDRESS, X_APP_USER_AGENT, dtoReq.getCountryCode());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.updateA005}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // マーケット価格ハッシュを取得する
        GetMarketPriceHashResp api005Res = null;
        try {
            api005Res = foreignInformationService.getMarketPriceHash(customerCommon.getButenCode(),
                    customerCommon.getAccountNumber(), api004Res.getPriceTicket(), dtoReq.getCountryCode());
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // 株価を取得する
        Securities api001Securities = api001Res.getSecurities();
        ListMarketPricesResp api006Res = null;
        try {
            api006Res = foreignInformationService.listMarketPrices(api005Res.getHashValue(), dtoReq.getCountryCode(),
                    new String[] { api001Securities.getRic() });
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }

        if (CollectionUtils.isNotEmpty(api006Res.getMarketPrices())) {
            PriceData api006PriceData = api006Res.getMarketPrices().get(0).getPrice();
            
            // 株価情報を設定する
            IfaForeignSpotTradeOrderInputPriceBasicInfo priceBasicInfo = new IfaForeignSpotTradeOrderInputPriceBasicInfo();
            priceBasicInfo.setCurrentPrice(api006PriceData.getLast());
            priceBasicInfo.setCurrentPriceDate(api006PriceData.getLastDatetime());
            priceBasicInfo.setTick(api006PriceData.getTickArrow());
            priceBasicInfo.setDiff(api006PriceData.getChange());
            priceBasicInfo.setRatio(api006PriceData.getChangePercent());
            priceBasicInfo.setStart(api006PriceData.getOpen());
            priceBasicInfo.setStartPriceDate(api006PriceData.getOpenDatetime());
            priceBasicInfo.setHigh(api006PriceData.getHigh());
            priceBasicInfo.setHighPriceDate(api006PriceData.getHighDatetime());
            priceBasicInfo.setLow(api006PriceData.getLow());
            priceBasicInfo.setLowPriceDate(api006PriceData.getLowDatetime());
            priceBasicInfo.setVolume(api006PriceData.getVolume());
            priceBasicInfo.setLast(api006PriceData.getPrevClose());
            priceBasicInfo.setLastDate(api006PriceData.getPrevCloseDate());
            responseData.setPriceBasicInfo(priceBasicInfo);
        }
        
        // レスポンスデータを作成する
        responseData.setBuyingPowerForeignList(Stream.of(foreignBuyingPower).collect(Collectors.toList()));
        responseData.setBuyingPowerDomesticList(Stream.of(domesticBuyingPower).collect(Collectors.toList()));

        return IfaCommonUtil.createDataList(Stream.of(responseData).collect(Collectors.toList()), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtils.EMPTY);
    }

    /**
     * アクションID：A013
     * アクション名：注文確認
     * Dto リクエスト：IfaForeignSpotTradeOrderInputA013RequestDto
     * Dto レスポンス：IfaForeignSpotTradeOrderInputA013ResponseDto
     *
     * @param dtoReq 注文確認リクエストDto
     * @return 注文確認レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignSpotTradeOrderInputA013ResponseDto> orderConfirmA013(
            IfaForeignSpotTradeOrderInputA013RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignSpotTradeOrderInputServiceImpL.orderConfirmA013");
        }
        
        InputFct018Dto inputFct018Dto = new InputFct018Dto();
        inputFct018Dto.setCountryCode(dtoReq.getCountryCode());
        OutputFct018Dto outputFct018Dto = fct018.doCheck(inputFct018Dto);
        
        if (!StringUtils.equals(outputFct018Dto.getProcessResult(), RESULT_OK)) {
            // 処理結果がOKではない場合、委託注文サービス時間外エラーを返す
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_OUT_OF_HOURS,
                    IfaCommonUtil.getMessage(MESSAGE_OUT_OF_HOURS));
        }
        
        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        // 口座に対する権限チェック
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(customerCommon.getButenCode());
        inputFct001Dto.setAccountNumber(customerCommon.getAccountNumber());
        
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        
        if (StringUtils.equals(outputFct001Dto.getTargetCustomerRefAuthFlag(),
                TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId())) {
            // 対象顧客参照権限有無が権限なしの場合、権限なしエラーを返す
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(MESSAGE_ACCOUNT_NOT_EXISTS,
                            new String[] { customerCommon.getButenCode(), customerCommon.getAccountNumber() }));
        } else if (StringUtils.equals(outputFct001Dto.getTradeSuspendFlag(), TradeSuspendFlag.SUSPEND.getId())) {
            // 取引停止口座の場合、取引停止エラーを返す
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_OUT_OF_SERVICE,
                    IfaCommonUtil.getMessage(MESSAGE_OUT_OF_SERVICE));
        }
        
        // 媒介可否チェック
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setButenCode(customerCommon.getButenCode());
        inputFct003Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct003Dto.setProductCd(CODE_VAL_SECURITY_CLASS_FSTOCK);
        inputFct003Dto.setTradeCd(dtoReq.getBuySellTypeName());
        inputFct003Dto.setCountryCd(dtoReq.getCountryCode());
        
        OutputFct003Dto outputFct003Dto = fct003.doCheck(inputFct003Dto);
        
        // 媒介可否リスト.媒介可否に媒介可が存在するか判定する
        if (outputFct003Dto.getMediateProprietyList().stream()
                .map(mediateProprieties -> mediateProprieties.getMediatePropriety()).noneMatch(
                        mediatePropriety -> StringUtils.equals(MediateAbleTradeFlag.ARI.getId(), mediatePropriety))) {
            // 存在しない場合、媒介不可エラーを返す
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_MEDIATE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(MESSAGE_MEDIATE_UNAVAILABLE, new String[] { codeListService
                            .getValue(CODE_ID_MSG_DISPLAY_TARGET_TRADE, CODE_VAL_MSG_DISPLAY_TARGET_TRADE_FSTOCK_ENTRUST) }));
        }
        
        if (!StringUtils.equals(customerCommon.getForeignStockTradeAccountOpenStatus(),
                ForeignStockTradeAccountOpenStatus.OPEN.getId())) {
            // 外国株式取引口座開設状況が開設済ではない場合、外貨建口座未開設エラーを返す
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_FSTOCK_ACCOUNT_NOT_OPENED,
                    IfaCommonUtil.getMessage(MESSAGE_FSTOCK_ACCOUNT_NOT_OPENED));
        }
        
        // 銘柄情報を取得する
        GetForeignStockSecuritiesResp api001Res = null;
        try {
            api001Res = foreignStockService.getForeignStockSecurities(dtoReq.getCountryCode(), dtoReq.getBrandCode());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.orderConfirmA013}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        if (StringUtils.equals(dtoReq.getBuySellTypeName(), ForeignStockTradeClass.SPOT_BUY.getId())) {
            // 取引種別が現物買付の場合、銘柄上場ステータスを判定する
            switch (ListedSecuritiesStatus.getById(api001Res.getListedSecuritiesStatus())) {
                case BUY_STOP:
                    // 買付停止の場合、買付停止エラーを返す
                    return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_TRADE_STOPPED_BUY_ONLY,
                            IfaCommonUtil.getMessage(MESSAGE_TRADE_STOPPED_BUY_ONLY));
                case BUY_SELL_STOP:
                    // 売買停止の場合、売買停止エラーを返す
                    return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_TRADE_STOPPED_BUY_SELL,
                            IfaCommonUtil.getMessage(MESSAGE_TRADE_STOPPED_BUY_SELL));
                case DELISTING:
                    // 上場廃止の場合、上場廃止エラーを返す
                    return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_DELISTED_STOCK,
                            IfaCommonUtil.getMessage(MESSAGE_DELISTED_STOCK));
                default:
                    break;
            }
        }
        
        // 銘柄情報を設定する
        Securities api001Securities = api001Res.getSecurities();
        Market api001Market = api001Res.getMarket();
        IfaForeignSpotTradeOrderInputA013ResponseDtoBrandInfo brandInfo = new IfaForeignSpotTradeOrderInputA013ResponseDtoBrandInfo();
        brandInfo.setBrandName(api001Securities.getSecuritiesName());
        brandInfo.setBrandCode(api001Securities.getSecuritiesCode());
        
        IfaForeignSpotTradeOrderInputA013ResponseDtoMarketInfo marketInfo = new IfaForeignSpotTradeOrderInputA013ResponseDtoMarketInfo();
        marketInfo.setMarketAbbreviation(api001Market.getMarketShortName());
        marketInfo.setCountryCode(api001Market.getCountryCode());
        marketInfo.setMarketCode(api001Market.getMarketCode());
        marketInfo.setTimeZoneAbbreviation(api001Market.getTimeZoneShortName());
        
        IfaForeignSpotTradeOrderInputA013ResponseDto responseData = new IfaForeignSpotTradeOrderInputA013ResponseDto();
        responseData.setBrandInfo(brandInfo);
        responseData.setMarketInfo(marketInfo);
        responseData.setCurrencyCode(api001Res.getCurrencyCode());
        responseData.setBrandClass(api001Res.getSecuritiesType());
        
        // 注文初期情報を取得する
        GetForeignStockCreatedOrderInitializationResp api002Res = null;
        try {
            api002Res = foreignStockService.getForeignStockCreatedOrderInitialization(customerCommon.getButenCode(),
                    customerCommon.getAccountNumber(), dtoReq.getCountryCode(), dtoReq.getBrandCode(),
                    dtoReq.getBuySellTypeName());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.orderConfirmA013}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // 注文数量を判定する
        BigDecimal actualTradeLimitMax = null;
        if (StringUtils.equals(dtoReq.getBuySellTypeName(), ForeignStockTradeClass.SPOT_SELL.getId())) {
            // 取引種別が現物売却の場合、売却可能数を取得する
            SellPossibleQuantity api002SellPossibleQuantity = api002Res
                    .getSellPossibleQuantities().stream().filter(sellPossibleQuantity -> StringUtils
                            .equals(sellPossibleQuantity.getSpecificAccountCode(), dtoReq.getDepositType()))
                    .findFirst().get();
            
            // 売却可能数と取引上限数量(APIレスポンスが"0"の場合9999999999と見做す)の最小値が実際の上限数量
            if (StringUtils.equals(api001Res.getTradeLimitMax(), TRADE_LIMIT_MAX_RES_NONE)) {
                actualTradeLimitMax = new BigDecimal(api002SellPossibleQuantity.getSecuritiesQuantity())
                        .min(TRADE_LIMIT_MAX_VAL);
            } else {
                actualTradeLimitMax = new BigDecimal(api002SellPossibleQuantity.getSecuritiesQuantity())
                        .min(new BigDecimal(api001Res.getTradeLimitMax()));
            }
        } else {
            // それ以外の場合、取引上限数量(APIレスポンスが"0"の場合9999999999と見做す)が実際の上限数量
            if (StringUtils.equals(api001Res.getTradeLimitMax(), TRADE_LIMIT_MAX_RES_NONE)) {
                actualTradeLimitMax = TRADE_LIMIT_MAX_VAL;
            } else {
                actualTradeLimitMax = new BigDecimal(api001Res.getTradeLimitMax());
            }
        }
        BigDecimal orderQuantity = new BigDecimal(dtoReq.getOrderQuantity());
        
        if (orderQuantity.compareTo(new BigDecimal(api001Res.getTradeLimitMin())) < 0
                || actualTradeLimitMax.compareTo(orderQuantity) < 0) {
            /* 
             * 注文数量 ＜ 取引下限数量
             * 取引上限数量(APIレスポンスが"0"の場合9999999999と見做す) ＜ 注文数量
             * 取引種別 ＝ 現物売 かつ 売却可能数 ＜ 注文数量
             * 上記いずれかに該当する場合、エラーを返す
             */
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_ORDER_VALUE_OUT_OF_RANGE,
                    IfaCommonUtil.getMessage(MESSAGE_ORDER_VALUE_OUT_OF_RANGE, new String[] { ERROR_ITEM_NAME_ORDER_QTY,
                            api001Res.getTradeLimitMin(), actualTradeLimitMax.toPlainString() }));
        }
        
        // 数量が取引単位で割り切れることをチェックする
        if (orderQuantity.remainder(new BigDecimal(api001Res.getTradeUnit())).compareTo(BigDecimal.ZERO) != 0) {
            // 割り切れない場合はエラーを返す
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_INCONSISTENT_ORDER,
                    IfaCommonUtil.getMessage(MESSAGE_INCONSISTENT_ORDER, new String[] { ERROR_ITEM_NAME_ORDER_QTY,
                            ERROR_ITEM_NAME_TRADE_UNIT, api001Res.getTradeUnit() }));
        }
        
        // 注意情報を取得する
        GetForeignStockAttentionSecuritiesResp api003Res = null;
        try {
            api003Res = foreignStockService.getForeignStockAttentionSecurities(dtoReq.getCountryCode(),
                    dtoReq.getBrandCode());
            responseData.setTradeLimit(api003Res.getAttentionSecurities().toString());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.orderConfirmA013}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // マーケット価格取得用チケットを取得する
        CreateMarketPriceTicketResp api004Res = null;
        try {
            api004Res = foreignInformationService.createMarketPriceTicket(customerCommon.getButenCode(),
                    customerCommon.getAccountNumber(), IP_ADDRESS, X_APP_USER_AGENT, dtoReq.getCountryCode());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.orderConfirmA013}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // マーケット価格ハッシュを取得する
        GetMarketPriceHashResp api005Res = null;
        try {
            api005Res = foreignInformationService.getMarketPriceHash(customerCommon.getButenCode(),
                    customerCommon.getAccountNumber(), api004Res.getPriceTicket(), dtoReq.getCountryCode());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.orderConfirmA013}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // 株価を取得する
        ListMarketPricesResp api006Res = null;
        try {
            api006Res = foreignInformationService.listMarketPrices(api005Res.getHashValue(), dtoReq.getCountryCode(),
                    new String[] { api001Securities.getRic() });
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.orderConfirmA013}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }

        if (CollectionUtils.isNotEmpty(api006Res.getMarketPrices())) {
            PriceData api006PriceData = api006Res.getMarketPrices().get(0).getPrice();
        
            // 株価情報を設定する
            IfaForeignSpotTradeOrderInputPriceBasicInfo priceBasicInfo = new IfaForeignSpotTradeOrderInputPriceBasicInfo();
            priceBasicInfo.setCurrentPrice(api006PriceData.getLast());
            priceBasicInfo.setCurrentPriceDate(api006PriceData.getLastDatetime());
            priceBasicInfo.setTick(api006PriceData.getTickArrow());
            priceBasicInfo.setDiff(api006PriceData.getChange());
            priceBasicInfo.setRatio(api006PriceData.getChangePercent());
            priceBasicInfo.setStart(api006PriceData.getOpen());
            priceBasicInfo.setHigh(api006PriceData.getHigh());
            priceBasicInfo.setLow(api006PriceData.getLow());
            priceBasicInfo.setVolume(api006PriceData.getVolume());
            priceBasicInfo.setLast(api006PriceData.getPrevClose());
            priceBasicInfo.setLastDate(DateFormatUtil.dateFormatToSlash(api006PriceData.getPrevCloseDate()));
            responseData.setPriceBasicInfo(priceBasicInfo);
        }
        
        // 呼値の小数点以下桁数の最大値を取得する
        int maxTickScale = 0;
        if (StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.US.getId())) {
            // 米国の場合、呼値がないため、2桁とする
            maxTickScale = 2;
        } else {
            // 米国以外の場合、呼値の小数点以下桁数の最大値を取得する
            if (CollectionUtils.isNotEmpty(api002Res.getTickSizes())) {
                maxTickScale = api002Res.getTickSizes().stream()
                        .mapToInt(tickSize -> new BigDecimal(tickSize.getTickSize()).scale()).max().getAsInt();
            }
        }
        
        // 注文単価上限値を取得する
        BigDecimal maxOrderPrice = PRICE_MAX_INTEGER;
        if (maxTickScale > 0) {
            // 小数点以下桁数が1桁以上ある場合、小数部を加算する
            maxOrderPrice = PRICE_MAX_INTEGER.add(
                    new BigDecimal(String.join(StringUtils.EMPTY, Collections.nCopies(maxTickScale, PRICE_FRACTION_NINE)))
                            .movePointLeft(maxTickScale));
        }
        
        // 注文単価の範囲をチェックする
        if (StringUtils.equals(dtoReq.getOrderPriceKindList(), SelectAblePriceConditions.PRICE_LIMIT.getId())) {
            // 価格条件が指値の場合、注文単価（指値）を判定する
            BigDecimal orderPriceLimit = new BigDecimal(dtoReq.getLimitOrderPrice());
            if (orderPriceLimit.compareTo(BigDecimal.ZERO) < 0 || maxOrderPrice.compareTo(orderPriceLimit) < 0) {
                // 注文単価（指値） ＜ 0、注文単価上限値 ＜ 注文単価（指値）のいずれかに該当する場合、エラーを返す
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_ORDER_VALUE_OUT_OF_RANGE,
                        IfaCommonUtil.getMessage(MESSAGE_ORDER_VALUE_OUT_OF_RANGE,
                                new String[] { ERROR_ITEM_NAME_ORDER_PRICE_LIMIT, BigDecimal.ZERO.toPlainString(),
                                        maxOrderPrice.toPlainString() }));
            }
        } else if (StringUtils.equals(dtoReq.getOrderPriceKindList(),
                SelectAblePriceConditions.STOP_ORDER_EXECUTE_PRICE.getId())
                || StringUtils.equals(dtoReq.getOrderPriceKindList(),
                        SelectAblePriceConditions.STOP_MARKET_ORDER_EXECUTE_PRICE.getId())) {
            // 価格条件が逆指値/指値、もしくは逆指値/成行の場合、発火条件価格を判定する
            BigDecimal stopPrice = new BigDecimal(dtoReq.getStopOrderPrice());
            if (stopPrice.compareTo(BigDecimal.ZERO) < 0 || maxOrderPrice.compareTo(stopPrice) < 0) {
                // 発火条件価格 ＜ 0、注文単価上限値 ＜ 発火条件価格のいずれかに該当する場合、エラーを返す
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_ORDER_VALUE_OUT_OF_RANGE,
                        IfaCommonUtil.getMessage(MESSAGE_ORDER_VALUE_OUT_OF_RANGE,
                                new String[] { ERROR_ITEM_NAME_STOP_PRICE, BigDecimal.ZERO.toPlainString(),
                                        maxOrderPrice.toPlainString() }));
            }
            
            if (StringUtils.equals(dtoReq.getOrderPriceKindList(),
                    SelectAblePriceConditions.STOP_ORDER_EXECUTE_PRICE.getId())) {
                // 価格条件が逆指値/指値の場合、注文単価（逆指値）を判定する
                BigDecimal executePrice = new BigDecimal(dtoReq.getStopOrderExecutePrice());
                if (executePrice.compareTo(BigDecimal.ZERO) < 0 || maxOrderPrice.compareTo(executePrice) < 0) {
                    // 注文単価（逆指値） ＜ 0、注文単価上限値 ＜ 注文単価（逆指値）のいずれかに該当する場合、エラーを返す
                    return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_ORDER_VALUE_OUT_OF_RANGE,
                            IfaCommonUtil.getMessage(MESSAGE_ORDER_VALUE_OUT_OF_RANGE,
                                    new String[] { ERROR_ITEM_NAME_EXECUTE_PRICE, BigDecimal.ZERO.toPlainString(),
                                            maxOrderPrice.toPlainString() }));
                }
            }
        }
        
        if (StringUtils.equals(dtoReq.getPeriodRadio(), PeriodConditions.PERIOD_DESIGNATION.getId())) {
            // 期間条件が期間指定の場合、有効期限一覧の先頭9番目以内(T+1～T+9)に該当するか判定する
            if (api002Res.getOrderTerms().stream().sorted().limit(PERIOD_DESIGNATION_LIMIT_T_DAY)
                    .noneMatch(terms -> StringUtils.equals(DateFormatUtil.dateFormatToSlash(terms),
                            DateFormatUtil.dateFormatToSlash(dtoReq.getPeriod())))) {
                // 該当する日付が無い場合、エラーを返す
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_ORDER_TERMS_OUT_OF_RANGE,
                        IfaCommonUtil.getMessage(MESSAGE_ORDER_TERMS_OUT_OF_RANGE));
            }
        }
        
        // 乗換勧誘(ETF)勧誘ありの場合のチェックを行う
        if (StringUtils.equals(dtoReq.getSolicitationEtf(), CODE_VAL_ETF_SOLICITING_TRANSFERS_SOLICIT)
                && !StringUtils.equals(dtoReq.getSolicitTypeList(), CODE_VAL_FOREIGN_STOCK_INVITATION_TYPE_INV)) {
            // 乗換え勧誘(ETF)が勧誘ありの場合に、勧誘区分が勧誘ありでない場合、エラーを返す
            return IfaCommonUtil
                    .createDataList(null, ErrorLevel.FATAL, MESSAGE_INVITATION_CHECK,
                            IfaCommonUtil
                                    .getMessage(MESSAGE_INVITATION_CHECK,
                                            new String[] { ERROR_ITEM_CHECKBOX_ON,
                                                    ERROR_INVITATION_VARIABLE_PREFIX.concat(codeListService.getValue(
                                                            CODE_ID_FOREIGN_STOCK_INVITATION_TYPE,
                                                            CODE_VAL_FOREIGN_STOCK_INVITATION_TYPE_INV)) }));
        }
        
        // 口座の取引制限チェックを行う
        InputFct021Dto inputFct021Dto = new InputFct021Dto();
        inputFct021Dto.setButenCode(customerCommon.getButenCode());
        inputFct021Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct021Dto.setProductCd(SecurityMoneyClass.FOREIGN_STOCK.getId());
        inputFct021Dto.setTradeCd(dtoReq.getBuySellTypeName());
        inputFct021Dto.setCountryCd(dtoReq.getCountryCode());
        inputFct021Dto.setCurrencyCode(CODE_VAL_CURRENCY_CODE_999);
        OutputFct021Dto outputFct021Dto = fct021.doCheck(inputFct021Dto);
        
        if (StringUtils.equals(outputFct021Dto.getNoteInfoErrFlag(), Fct021.EXIST)) {
            // 注意情報エラーありの場合、エラーを返す
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_NOTICE_ERROR_CHECK,
                    IfaCommonUtil.getMessage(MESSAGE_NOTICE_ERROR_CHECK, new String[] { codeListService
                            .getValue(CODE_ID_MSG_DISPLAY_TARGET_TRADE, CODE_VAL_MSG_DISPLAY_TARGET_TRADE_FSTOCK) }));
        } else if (StringUtils.equals(outputFct021Dto.getNoteLimitErrFlag(), Fct021.EXIST)) {
            // お知らせエラーありの場合、エラーを返す
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_INFORMATION_CHECK,
                    IfaCommonUtil.getMessage(MESSAGE_INFORMATION_CHECK));
        }
        
        // アラート情報を格納する
        if (StringUtils.equals(outputFct021Dto.getNoteInfoAlertFlag(), Fct021.EXIST)) {
            // 注意情報アラートありの場合
            responseData.setNoticeInfoAlert(
                    IfaCommonUtil.getMessage(MESSAGE_NOTICE_WARN_CHECK, new String[] { codeListService
                            .getValue(CODE_ID_MSG_DISPLAY_TARGET_TRADE, CODE_VAL_MSG_DISPLAY_TARGET_TRADE_FSTOCK) }));
        }
        if (StringUtils.equals(outputFct021Dto.getNoteLimitAlertFlag(), Fct021.EXIST)) {
            // お知らせアラートありの場合
            responseData.setNoticeAlert(IfaCommonUtil.getMessage(MESSAGE_INFORMATION_WARN_CHECK));
        }
        
        // 取引種別が現物買付、銘柄種別がETFで、乗換勧誘(ETF)が勧誘なしの場合、海外ETFアラート(乗換勧誘に該当しないことの確認)を設定する
        if (StringUtils.equals(dtoReq.getBuySellTypeName(), ForeignStockTradeClass.SPOT_BUY.getId())
                && StringUtils.equals(api001Res.getSecuritiesType(), SecuritiesType.ETF.getId())
                && StringUtils.equals(dtoReq.getSolicitationEtf(), CODE_VAL_ETF_SOLICITING_TRANSFERS_NONE)) {
            responseData.setOverseasEtfAlert(IfaCommonUtil.getMessage(MESSAGE_FOREIGN_ETF_CONFIRM));
        }
        
        // 取引種別が現物買付の場合、コンプラランクチェックを行う
        if (StringUtils.equals(dtoReq.getBuySellTypeName(), ForeignStockTradeClass.SPOT_BUY.getId())) {
            InputFct006Dto inputFct006Dto = new InputFct006Dto();
            inputFct006Dto.setButenCode(customerCommon.getButenCode());
            inputFct006Dto.setAccountNumber(customerCommon.getAccountNumber());
            inputFct006Dto.setBrDomesticFgnInd(CODE_VAL_DOMESTIC_FOREIGN_TYPE_FGN);
            inputFct006Dto.setBrBrandInd(CODE_VAL_SECURITY_TYPE_STOCK);
            inputFct006Dto.setBrandCode1(dtoReq.getBrandCode());
            inputFct006Dto.setComplaCheckKind(CODE_VAL_COMPLA_CHECK_KIND_SPOT_BUY);
            inputFct006Dto.setCountryCode(dtoReq.getCountryCode());
            
            // 勧誘区分を区分値変換して設定する
            switch (dtoReq.getSolicitTypeList()) {
                case CODE_VAL_FOREIGN_STOCK_INVITATION_TYPE_INV:
                    inputFct006Dto.setInvitationType(CODE_VAL_INVITATION_TYPE_INV);
                    break;
                case CODE_VAL_FOREIGN_STOCK_INVITATION_TYPE_NONE:
                    inputFct006Dto.setInvitationType(CODE_VAL_INVITATION_TYPE_NONE);
                    break;
                default:
                    break;
            }
            
            // 受注方法を区分値変換して設定する
            switch (dtoReq.getReceiveOrderType()) {
                case CODE_VAL_FOREIGN_STOCK_ORDER_METHOD_TYPE_OTC:
                    inputFct006Dto.setOrderMethod(CODE_VAL_ORDER_METHOD_TYPE_OTC);
                    break;
                case CODE_VAL_FOREIGN_STOCK_ORDER_METHOD_TYPE_VISIT:
                    inputFct006Dto.setOrderMethod(CODE_VAL_ORDER_METHOD_TYPE_VISIT);
                    break;
                case CODE_VAL_FOREIGN_STOCK_ORDER_METHOD_TYPE_TEL:
                    inputFct006Dto.setOrderMethod(CODE_VAL_ORDER_METHOD_TYPE_TEL);
                    break;
                default:
                    break;
            }
            
            OutputFct006Dto outputFct006Dto = fct006.doCheck(inputFct006Dto);
            responseData.setComplianceCheckResult(outputFct006Dto.getJudgementResult());
            
            // コンプラランクチェック判定結果をもとに処理を行う
            switch (outputFct006Dto.getJudgementResult()) {
                case COMP_RANK_JUDGEMENT_RESULT_NORMAL:
                    // コンプラランクチェック判定結果がノーマルの場合はそのまま後続処理
                    break;
                case COMP_RANK_JUDGEMENT_RESULT_ALERT:
                    // コンプラランクチェック判定結果がアラートの場合はコンプラチェック情報を設定して、後続処理へ
                    IfaForeignSpotTradeOrderInputA013ResponseDtoComplianceCheck complianceCheck = new IfaForeignSpotTradeOrderInputA013ResponseDtoComplianceCheck();
                    complianceCheck.setMessageId(outputFct006Dto.getMessageId());
                    complianceCheck.setComplianceCheckMsg(IfaCommonUtil.getMessage(outputFct006Dto.getMessageId()));
                    complianceCheck.setChkBoxLabel(outputFct006Dto.getChkBoxLabel());
                    responseData.setComplianceCheckList(Stream.of(complianceCheck).collect(Collectors.toList()));
                    break;
                case COMP_RANK_JUDGEMENT_RESULT_ERROR:
                default:
                    // コンプラランクチェック判定結果がエラーの場合、もしくはノーマル、アラート、エラー以外の値の場合、エラーを返す
                    return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, outputFct006Dto.getMessageId(),
                            IfaCommonUtil.getMessage(outputFct006Dto.getMessageId()));
            }
        }
        
        // 取引種別が現物買付で、注意銘柄=trueの場合、取引注意情報（銘柄）メッセージを設定する
        if (StringUtils.equals(dtoReq.getBuySellTypeName(), ForeignStockTradeClass.SPOT_BUY.getId())
                && api003Res.getAttentionSecurities().booleanValue()) {
            responseData.setTradeNoticeInfoBrandMsg(IfaCommonUtil.getMessage(MESSAGE_STOCK_ATTENTION_CHECK));
        }
        
        // 国籍コードが米国(米株)、取引種別が現物売却で、証券残高チェックを行う
        if (StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.US.getId())
                && StringUtils.equals(dtoReq.getBuySellTypeName(), ForeignStockTradeClass.SPOT_SELL.getId())) {
            InputFct028Dto inputFct028Dto = new InputFct028Dto();
            inputFct028Dto.setButenCode(customerCommon.getButenCode());
            inputFct028Dto.setAccountNumber(customerCommon.getAccountNumber());
            inputFct028Dto.setBrandCode(dtoReq.getBrandCode());
            inputFct028Dto.setDepositType(dtoReq.getDepositType());
            OutputFct028Dto outputFct028Dto = fct028.doCheck(inputFct028Dto);
            
            if (new BigDecimal(dtoReq.getOrderQuantity()).compareTo(outputFct028Dto.getAcPositionStockNumber()) > 0) {
                // 注文数量　＞　余力チェック.売却可能数量(株数)の場合、エラーを返す
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_STOCK_BALANCE_SHORT,
                        IfaCommonUtil.getMessage(MESSAGE_STOCK_BALANCE_SHORT,
                                new String[] { dtoReq.getOrderQuantity(),
                                        outputFct028Dto.getRealtimeBalanceStockNumberSell().toPlainString(),
                                        outputFct028Dto.getCurrentDayOtcSellStockNumber().toPlainString() }));
            }
        }
        
        // 国内株式現物注文確認パラメータを作成する
        OrderInput orderInput = new OrderInput();
        orderInput.setCountryCode(dtoReq.getCountryCode());
        orderInput.setMarketCode(dtoReq.getMarketCode());
        orderInput.setSecuritiesCode(dtoReq.getBrandCode());
        orderInput.setBuySellCode(dtoReq.getBuySellTypeName());
        orderInput.setOrderQuantity(dtoReq.getOrderQuantity());
        orderInput.setOrderPriceKindCode(dtoReq.getOrderPriceKindList());
        
        if (StringUtils.equals(dtoReq.getOrderPriceKindList(),
                SelectAblePriceConditions.STOP_ORDER_EXECUTE_PRICE.getId())) {
            orderInput.setOrderPrice(dtoReq.getStopOrderExecutePrice());
            orderInput.setStopPrice(dtoReq.getStopOrderPrice());
        } else if (StringUtils.equals(dtoReq.getOrderPriceKindList(),
                SelectAblePriceConditions.STOP_MARKET_ORDER_EXECUTE_PRICE.getId())) {
            orderInput.setStopPrice(dtoReq.getStopOrderPrice());
        } else {
            orderInput.setOrderPrice(dtoReq.getLimitOrderPrice());
        }
        
        orderInput.setOrderLimitCode(dtoReq.getPeriodRadio());
        orderInput.setOrderTerm(dtoReq.getPeriod());
        orderInput.setSpecificAccountCode(dtoReq.getDepositType());
        orderInput.setSettlementMethodCode(dtoReq.getCurrencyTypeName());
        orderInput.setRemainingPowerCheckDisabled(Boolean.FALSE);
        orderInput.setNisaRemainingPowerCheckDisabled(Boolean.FALSE);
        
        // 国内株式現物注文確認を行う
        ConfirmForeignStockCreatedOrderResp api012Res = null;
        try {
            api012Res = foreignStockService.confirmForeignStockCreatedOrder(customerCommon.getButenCode(),
                    customerCommon.getAccountNumber(), StringUtils.EMPTY, StringUtils.EMPTY, RequestUtil.getChannel(),
                    orderInput);
        } catch (Exception e) {
            // 注文確認で発生したエラーを返す
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.orderConfirmA013}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // 注文確認APIの結果をレスポンスに設定する
        Order api012ResOrder = api012Res.getOrder();
        responseData.setOrderNumber(api012ResOrder.getOrderNo());
        responseData.setOrderSubNumber(api012ResOrder.getOrderSubNo());
        responseData.setLimitPriceText(api012ResOrder.getTradeCurrencyCode());
        responseData.setTradeKbn(api012ResOrder.getBuySellCode());
        responseData.setAutomaticBuyingCategory(api012ResOrder.getAutoStockOrderType());
        responseData.setOrderQuantity(api012ResOrder.getOrderQuantity());
        responseData.setPriceCondition(api012ResOrder.getOrderPriceKindCode());
        responseData.setHiddenOrderPrice(api012ResOrder.getOrderPrice());
        responseData.setTriggerPrice(api012ResOrder.getStopPrice());
        responseData.setTrailStopWidth(api012ResOrder.getTrailingStopAmount());
        responseData.setExecuteBasePrice(api012ResOrder.getNoLimitPrice());
        responseData.setPeriodRadio(api012ResOrder.getOrderLimitCode());
        responseData.setPeriod(DateFormatUtil.dateFormatToSlash(api012ResOrder.getOrderTerm()));
        responseData.setDepositType(api012ResOrder.getSpecificAccountCode());
        responseData.setSettlementType(api012ResOrder.getSettlementMethodCode());
        responseData.setSettlementCurrency(api012ResOrder.getSettlementCurrencyCode());
        responseData.setFxRate(api012ResOrder.getExchangeRate());
        responseData.setAverageTradePrice(api012ResOrder.getExecutionAveragePrice());
        responseData.setUnTradeQuantity(api012ResOrder.getUnexecutedQuantity());
        responseData.setTradeQuantity(api012ResOrder.getExecutionQuantity());
        responseData.setContractAmountForeign(api012ResOrder.getFrnGrossAmount());
        responseData.setContractAmountYen(api012ResOrder.getGrossAmount());
        responseData.setForeignDeliveryAmount(api012ResOrder.getFrnNetAmount());
        responseData.setYenDeliveryAmount(api012ResOrder.getNetAmount());
        responseData.setDeliveryAmount(api012ResOrder.getExecutionNetAmount());
        responseData.setDomesticCommForeign(api012ResOrder.getFrnCommissionAmount());
        responseData.setDomesticCommJpAmount(api012ResOrder.getCommissionAmount());
        responseData.setDomesticConsumptionTaxForeign(api012ResOrder.getFrnCommissionCtax());
        responseData.setDomesticConsumptionTaxYen(api012ResOrder.getCommissionCtax());
        responseData.setForeignFee(api012ResOrder.getFrnLocalCharge());
        responseData.setYenFee(api012ResOrder.getLocalCharge());
        responseData.setLocalSettlementAmountForeign(api012ResOrder.getFrnLocalNetAmount());
        responseData.setLocalSettlementAmountYen(api012ResOrder.getLocalNetAmount());
        responseData.setNisaFrameRestrictionAmount(api012ResOrder.getNisaFixedAmount());
        responseData.setApproximateCapitalGainsTax(api012ResOrder.getSpecificTax());
        responseData.setOrderStatus(api012ResOrder.getOrderStatus());
        responseData.setTradeStatus(api012ResOrder.getExecutionStatus());
        responseData.setOrderStopSituation(api012ResOrder.getWorkingStatus());
        responseData.setDomesticTradeDate(DateFormatUtil.dateFormatToSlash(api012ResOrder.getTradeDate()));
        responseData.setDomesticSettlementDate(DateFormatUtil.dateFormatToSlash(api012ResOrder.getValueDate()));
        responseData.setLocalContractDate(DateFormatUtil.dateFormatToSlash(api012ResOrder.getFrnTradeDate()));
        responseData.setLocalDeliveryDate(DateFormatUtil.dateFormatToSlash(api012ResOrder.getFrnValueDate()));
        responseData.setOrderDate(api012ResOrder.getOrderInputDatetime());
        responseData.setTradeDateTime(api012ResOrder.getExecutionDatetime());
        responseData.setExpirationDateandTime(api012ResOrder.getExpiredDatetime());
        responseData.setQuotePrice(api012Res.getEstimatePrice());
        responseData.setDeliveryAmountExecuted(api012Res.getBuyPossibleAmount());
        responseData.setNisaInvestableQuote(api012Res.getNisaBuyLimitAmount());
        responseData.setAcPositionAfter(api012Res.getSellPossibleQuantity());
        
        if (StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.US.getId())
                && StringUtils.equals(dtoReq.getBuySellTypeName(), ForeignStockTradeClass.SPOT_BUY.getId())
                && StringUtils.equals(dtoReq.getCurrencyTypeName(), SettlementMethod.FOREIGN_SETTLEMENT.getIfaCd())) {
            // 国籍コードが米国(米株)、取引種別が現物買付で、決済方法が外貨決済の場合、金銭残高チェックを行う
        	// FCT004を呼び出し
        	String depositTypeExtKey = codeListService.convertKeyToExtKey("FOREIGN_DEPOSIT_TYPE", "Athena", dtoReq.getDepositType());
            InputFct004Dto inputFct004Dto = new InputFct004Dto();
            inputFct004Dto.setButenCode(customerCommon.getButenCode());
            inputFct004Dto.setAccountNumber(customerCommon.getAccountNumber());
            inputFct004Dto.setDepositType(depositTypeExtKey);
            inputFct004Dto.setOtcManageNumber(StringUtils.EMPTY);
            inputFct004Dto.setTradeType(StringUtils.EMPTY);
            OutputFct004Dto outputFct004Dto = fct004.doCheck(inputFct004Dto);
            // 受渡金額（外貨）と比較する
            if (new BigDecimal(api012Res.getOrder().getFrnNetAmount())
                    .compareTo(outputFct004Dto.getByingPowerMoneyAfterCalculate()) > 0) {
                // 受渡金額（外貨） ＞ 計算後の余力金額の場合、エラーを返す
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MESSAGE_BUYING_POWER_SHORT,
                        IfaCommonUtil.getMessage(MESSAGE_BUYING_POWER_SHORT,
                                new String[] { api012Res.getOrder().getFrnNetAmount(),
                                        outputFct004Dto.getRealTimeBuyingPower().toPlainString(),
                                        outputFct004Dto.getOtcBuyingContractAmountToday().toPlainString(),
                                        outputFct004Dto.getContractAmountTodayWithinForeignBond().toPlainString() }));
            }
            // 注文確認APIで取得した買付余力（注文後）から、金銭残高チェックで取得した当日店頭買付約定金額と外国債券の当日約定金額（買付）を控除した額を設定する
            responseData.setDeliveryAmountExecuted(new BigDecimal(api012Res.getBuyPossibleAmount())
                    .subtract(outputFct004Dto.getOtcBuyingContractAmountToday())
                    .subtract(outputFct004Dto.getContractAmountTodayWithinForeignBond()).toPlainString());
        } else if(StringUtils.equals(dtoReq.getBuySellTypeName(), ForeignStockTradeClass.SPOT_SELL.getId())
                && StringUtils.equals(dtoReq.getCurrencyTypeName(), SettlementMethod.FOREIGN_SETTLEMENT.getIfaCd())) {
            // 取引種別が現物売却で、決済方法が外貨決済の場合、円貨の買付余力を取得する
            ListScheduleCashBalancesResp api008Res = null;
            try {
                api008Res = foreignAccountService.listScheduleCashBalances(customerCommon.getButenCode(),
                        customerCommon.getAccountNumber(), null, FRS_BUYING_POWER_LIST_DAYS_THREE);
            } catch (Exception e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("{IfaForeignSpotTradeOrderInputServiceImpL.initializeA001}", e);
                }
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }

            // リクエスト.預り区分が"JrNISA－特定預り"、"JrNISA－一般預り"、"JrNISA－NISA預り"、"Jr継続NISA預り"のいずれかの場合
            if (StringUtils.equals(dtoReq.getDepositType(), FOREIGN_DEPOSIT_TYPE_JR_SPECIFIC)
                || StringUtils.equals(dtoReq.getDepositType(), FOREIGN_DEPOSIT_TYPE_JR_GENERAL)
                || StringUtils.equals(dtoReq.getDepositType(), FOREIGN_DEPOSIT_TYPE_JR_NISA_CODE)
                || StringUtils.equals(dtoReq.getDepositType(), FOREIGN_DEPOSIT_TYPE_CONTINUOUS_MANAGEMENT)
                ) {
                // API008.精算予定一覧表（日本）.口座分類＝JR_NISA(ジュニアNISA)の、API008.精算予定一覧表（日本）.精算予定一覧表（日本）.買付余力（T＋2営業日）をセット
                String deliveryAmountExecuted = api008Res.getCashBalances().stream()
                        .filter(cashBalance -> StringUtils.equals(cashBalance.getAccountKind(), FOREIGN_DEPOSIT_TYPE_JR_NISA))
                        .findFirst()
                        .flatMap(cashBalance -> cashBalance.getScheduleCashBalances().stream()
                                .filter(ScheduleCashBalance -> StringUtils.equals(
                                        DateFormatUtil.dateFormatToSlash(ScheduleCashBalance.getBusinessDate()),
                                        responseData.getDomesticTradeDate()
                                ))
                                .findFirst()
                                .map(ScheduleCashBalance -> ScheduleCashBalance.getBuyPossibleAmount())
                        )
                        .orElse(null);
                responseData.setDeliveryAmountExecuted(deliveryAmountExecuted);
            } else {
                // API008.精算予定一覧表（日本）.口座分類＝GENERAL(総合)の、API008.精算予定一覧表（日本）.精算予定一覧表（日本）.買付余力（T＋2営業日）をセット
                String deliveryAmountExecuted = api008Res.getCashBalances().stream()
                        .filter(cashBalance -> StringUtils.equals(cashBalance.getAccountKind(), FOREIGN_DEPOSIT_TYPE_GENERAL))
                        .findFirst()
                        .flatMap(cashBalance -> cashBalance.getScheduleCashBalances().stream()
                                .filter(ScheduleCashBalance -> StringUtils.equals(
                                        DateFormatUtil.dateFormatToSlash(ScheduleCashBalance.getBusinessDate()),
                                        responseData.getDomesticTradeDate()
                                ))
                                .findFirst()
                                .map(ScheduleCashBalance -> ScheduleCashBalance.getBuyPossibleAmount())
                        )
                        .orElse(null);
                responseData.setDeliveryAmountExecuted(deliveryAmountExecuted);
            }

        } else {
            // 金銭残高チェックを行う条件に該当しない場合、注文確認APIで取得した買付余力（注文後）を設定する
            responseData.setDeliveryAmountExecuted(api012Res.getBuyPossibleAmount());
        }
        
        // 約定代金ソフトリミット上限超過ありの場合、約定代金ソフトリミット上限超過メッセージを設定する
        if (api012Res.getWarningStatuses().stream().anyMatch(warningStatus -> StringUtils.equals(warningStatus,
                OrderWarningStatus.ORDER_AMOUNT_SOFT_LIMIT.getId()))) {
            responseData.setPriceLimitCheckText(IfaCommonUtil.getMessage(MESSAGE_CONTRACT_PRICE_LIMIT_EXEEDED));
        }
        // 逆指値注文即時発火ありの場合、逆指値注文即時発火メッセージを設定する
        if (api012Res.getWarningStatuses().stream().anyMatch(
                warningStatus -> StringUtils.equals(warningStatus, OrderWarningStatus.STOP_ORDER_TRIGGERED.getId()))) {
            responseData.setMethodCheckText(IfaCommonUtil.getMessage(MESSAGE_STOP_PRICE_TRIGGERED_CONFIRM));
        }
        
        // 現地約定日が注文当日より大きいか比較する
        if (LocalDate.parse(api012ResOrder.getFrnTradeDate(), DateTimeFormatter.ISO_LOCAL_DATE)
                .isAfter(LocalDate.now())) {
            // 現地約定日が注文当日より大きい]場合、現地約定日超過メッセージを設定する
            responseData.setLocalTradeDateLimitMsg(IfaCommonUtil.getMessage(MESSAGE_NEXT_BUSINESS_DAY_CONFIRM));
        }
        
        // リクエストの項目をレスポンスに設定する
        responseData.setBuySellTypeName(dtoReq.getBuySellTypeName());
        responseData.setKanyuKbn(dtoReq.getSolicitTypeList());
        responseData.setReceiveOrderType(dtoReq.getReceiveOrderType());
        responseData.setImportantMatterType(dtoReq.getImportantMatterType());
        responseData.setSolicitationEtf(dtoReq.getSolicitationEtf());
        responseData.setEngPubCheckbox(dtoReq.getEngPubCheckbox());
        responseData.setConfirmItemList(Stream.of(dtoReq.getCheckInsider()).map(reqConfirmItem -> {
            IfaForeignSpotTradeOrderInputA013ResponseDtoConfirmItem resConfirmItem = new IfaForeignSpotTradeOrderInputA013ResponseDtoConfirmItem();
            resConfirmItem.setCheckInsider(reqConfirmItem);
            return resConfirmItem;
        }).collect(Collectors.toList()));
        responseData.setTodayTradeLimitUrl(dtoReq.getTodayTradeLimitUrl());
        responseData.setClosedDay(dtoReq.getClosedDay());
        responseData.setYenClosedDateUrl(medSystemVariableService.getMedSystemVariable(FRS_YEN_CLOSED_URL));
        responseData.setListofHandledStocksUrl(dtoReq.getHandledStockListUrl());
        responseData.setNoticeofTransactionPrecautionsUrl(medSystemVariableService.getMedSystemVariable(FRS_STOCK_QAI_22_URL));
        
        // レスポンスDtoを返す
        return IfaCommonUtil.createDataList(Stream.of(responseData).collect(Collectors.toList()), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtils.EMPTY);
    }
}
