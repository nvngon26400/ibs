package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.athena.enums.BuySell;
import com.sbisec.helios.ap.athena.enums.ListedSecuritiesStatus;
import com.sbisec.helios.ap.athena.ifa.ForeignInformationService;
import com.sbisec.helios.ap.athena.ifa.ForeignStockService;
import com.sbisec.helios.ap.athena.protocol.common.PriceData;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.SellPossibleQuantity;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateForeignStockOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockCreatedOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockAttentionSecuritiesResp;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockSecuritiesResp;
import com.sbisec.helios.ap.athena.protocol.information.CreateMarketPriceTicketResp;
import com.sbisec.helios.ap.athena.protocol.information.GetMarketPriceHashResp;
import com.sbisec.helios.ap.athena.protocol.information.ListMarketPricesResp;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
import com.sbisec.helios.ap.athena.utils.RequestUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct004;
import com.sbisec.helios.ap.bizcommon.component.Fct006;
import com.sbisec.helios.ap.bizcommon.component.Fct018;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.component.Fct028;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct004Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct018Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct028Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto.MediatePropriety;
import com.sbisec.helios.ap.bizcommon.model.OutputFct004Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct018Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct028Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaForeignSpotTradeOrderConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignSpotTradeOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA004ResponseDtoPrise;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA010ResponseDtoAlertCheck;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA010ResponseDtoCheck;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA010ResponseDtoComplianceCheck;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA010ResponseDtoOrder;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA010aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA010aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA010bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA010bResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.model.ApiStatusModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaForeignSpotTradeOrderConfirmService;
import com.sbisec.helios.ap.common.constants.AppConstants;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ForeignStockTradeAccountOpenStatus;
import com.sbisec.helios.ap.common.enums.ForeignStockTradeClass;
import com.sbisec.helios.ap.common.enums.MediateAbleTradeFlag;
import com.sbisec.helios.ap.common.enums.NationalityCode;
import com.sbisec.helios.ap.common.enums.PeriodConditions;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.enums.SecurityMoneyClass;
import com.sbisec.helios.ap.common.enums.SelectAblePriceConditions;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.enums.TradeSuspendFlag;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0301-01_2
 * 画面名：外国現物取引注文確認
 *
 * @author 福岡　利基
 */
@Component(value = "cmpIfaForeignSpotTradeOrderConfirmService")
public class IfaForeignSpotTradeOrderConfirmServiceImpL implements IfaForeignSpotTradeOrderConfirmService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaForeignSpotTradeOrderConfirmServiceImpL.class);
    
    /** メッセージID:権限なしエラー*/
    private static final String ERRORS_BUTEN_ACCOUNT_NOT_EXIST = "errors.butenAccountNotExist";
    
    /** メッセージID:媒介不可エラー */
    private static final String ERRORS_MEDIATE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** メッセージID:外国株式口座未開設エラー */
    private static final String ERRORS_FSTOCK_ACCOUNT_NOT_OPENED = "errors.foreignStockAccountCheck";
    
    /** メッセージID:委託注文サービス時間外エラー */
    private static final String ERRORS_OUT_OF_HOURS = "errors.frs.serviceHours.outOfRange";
    
    /** メッセージID:利用者権限エラー */
    private static final String ERRORS_USER_ACCOUNT_INSUFFICIENT_PRIVILEGE = "errors.frs.orderExecution.insufficientPrivilege";
    
    /** メッセージID:取引停止口座エラー */
    private static final String ERRORS_OUT_OF_SERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** メッセージID:買付停止エラー */
    private static final String ERRORS_TRADE_STOPPED_BUY_ONLY = "errors.frs.listedSecuritiesStatus.buyStop";
    
    /** メッセージID:売買停止エラー */
    private static final String ERRORS_TRADE_STOPPED_BUY_SELL = "errors.frs.listedSecuritiesStatus.buySellStop";
    
    /** メッセージID:上場廃止エラー */
    private static final String ERRORS_DELISTED_STOCK = "errors.frs.listedSecuritiesStatus.delisting";
    
    /** メッセージID:注文数量/単価範囲外エラー */
    private static final String ERRORS_ORDER_VALUE_OUT_OF_RANGE = "errors.frs.orderValue.outOfRange";
    
    /** メッセージID:取引単位外エラー */
    private static final String ERRORS_INCONSISTENT_ORDER = "errors.frs.orderingCondition.inconsistent";
    
    /** メッセージID:期間指定範囲外エラー */
    private static final String ERRORS_ORDER_TERMS_OUT_OF_RANGE = "errors.frs.orderTerms.outOfRange";
    
    /** メッセージID:乗換勧誘(ETF)勧誘区分エラー */
    private static final String ERRORS_INVITATION_CHECK = "errors.invitationCheck";
    
    /** メッセージID:注意情報エラー */
    private static final String ERRORS_NOTICE_ERROR_CHECK = "errors.cmn.noticeErrorCheck";
    
    /** メッセージID:重要なお知らせエラー */
    private static final String ERRORS_INFORMATION_CHECK = "errors.informationCheck";
    
    /** メッセージID:アラートエラー */
    private static final String ERRORS_INFOMATION_OCCURS = "errors.cmn.information.occurs";
    
    /** メッセージID:買付余力不足エラー */
    private static final String ERRORS_BUYING_POWER_SHORT = "errors.frs.buyLimit.overflow";
    
    /** メッセージID:売却株数不足エラー */
    private static final String ERRORS_STOCK_BALANCE_SHORT = "errors.frs.countUnit.overflow";
    
    /** メッセージID:DB登録エラー */
    private static final String ERRORS_PRE_ORDER_EXECUTION_FAILED = "errors.frs.preOrderExecution.failed";
    
    /** メッセージID:注文結果DB登録失敗ワーニング */
    private static final String WARNINGS_POST_ORDER_EXECUTION_FAILED = "warnings.frs.postOrderExecution.completed";
    
    /** メッセージID:注文発注API処理結果エラー */
    private static final String ERRORS_ORDER_EXECUTION_FAILED = "errors.cmn.orderExecution.failed";
    
    /** 区分ID:対象取引（メッセージ表示用）　*/
    private static final String CODE_ID_MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 区分値:対象取引（メッセージ表示用）.外国株式　*/
    private static final String CODE_VAL_MSG_DISPLAY_TARGET_TRADE_FSTOCK = "6";
    
    /** 区分値:対象取引（メッセージ表示用）.外国株式現物取引（委託）　*/
    private static final String CODE_VAL_MSG_DISPLAY_TARGET_TRADE_FSTOCK_ENTRUST = "6A";
    
    /** IPアドレス */
    private static final String IP_ADDRESS = "127.0.0.1";
    
    /** APIユーザーエージェント（Edge固定) */
    private static final String API_USER_AGENT = "Edge";
    
    /**判定結果：NG*/
    private static final String RESULT_NG = "NG";
    
    /** エラーメッセージ表示用「注文数量」 */
    private static final String ORDER_QUANTITY = "注文数量";
    
    /** エラーメッセージ表示用「注文単価（指値）」 */
    private static final String ORDER_PRICE = "注文単価（指値）";
    
    /** エラーメッセージ表示用「参照価格（逆指値）」 */
    private static final String STOP_ORDER_PRICE = "参照価格（逆指値）";
    
    /** エラーメッセージ表示用「注文単価（逆指値）」 */
    private static final String ORDER_PRICE_REVERCE = "注文単価（逆指値）";
    
    /** メッセージID:乗換勧誘(ETF)勧誘区分エラー用メッセージ１ */
    private static final String INVITATION_ERROR_MESSAGE1 = "チェックオン";
    
    /** メッセージID:乗換勧誘(ETF)勧誘区分エラー用メッセージ２ */
    private static final String INVITATION_ERROR_MESSAGE2 = "勧誘区分は勧誘あり";
    
    /** ゼロ(文字列) */
    private static final String ZERO = "0";
    
    /** 価格チェック最大値(整数部+小数点) */
    private static final String PRICE_MAX = "999999999.";
    
    /** 価格チェック最大値(小数部) */
    private static final String PRICE_MAX_FLOAT = "9";
    
    /** チェック最大値 */
    private static final String CHECK_MAX = "9999999999";
    
    /** 日付チェック最大値 */
    private static final int DATE_CHECK_LIMIT = 9;
    
    /** コンプラチェックボックス文言:ワーニング申請・承認済 */
    private static final String COMPLA_CHECK_BOX_WARNING = "1";
    
    /** コンプラチェックボックス文言:勧誘なし */
    private static final String COMPLA_CHECK_BOX_NOT = "2";
    
    /** 決済区分：外貨決済 */
    private static final String SETTLEMENT_TYPE_FOREIGN = "2";
    
    /** チャネル設定値 */
    private static final String CHANNEL = "PHONE";
    
    /** IFA注文サブ番号デフォルト値 */
    private static final String IFA_ORDER_SUB_NUMBER = "1";
    
    /** APIタイプ：Athena */
    private static final String ATHENA = "Athena";
    
    /** ドメインID:価格条件 */
    private static final String PRICE_CONDITIONS = "SELECT_ABLE_PRICE_CONDITIONS";
    
    /** ドメインID:期間条件 */
    private static final String PERIOD_CONDITIONS = "PERIOD_CONDITIONS";
    
    /** ドメインID:預り区分 */
    private static final String FOREIGN_DEPOSIT_TYPE = "FOREIGN_DEPOSIT_TYPE";
    
    /** ドメインID:決済区分 */
    private static final String SETTLEMENT_TYPE = "SETTLEMENT_TYPE";
    
    /** 注文日時デフォルト値 */
    private static final String ORDER_DEFAULT = "1900/01/01 00:00:00";
    
    /** 通貨コード */
    private static final String CURRENCY_CODE = "999";
    
    /** 勧誘区分 */
    private static final String INVITATION = "2";
    
    /** 国内外国区分 */
    private static final String BR_DOMESTIC_FGN_IND = "1";
    
    /** 商品区分 */
    private static final String BR_BRAND_IND = "1 ";
    
    /** 受注方法:訪問 */
    private static final String ORDER_TYPE_VISIT = "1";
    
    /** 受注方法区分:店頭 */
    private static final String ORDER_METHOD_TYPE_SHOP = "1";
    
    /** 受注方法区分:訪問 */
    private static final String ORDER_METHOD_TYPE_VISIT = "2";
    
    /** 証券金銭種別：外国株式 */
    private static final String SECURITY_MONEY_CLASS_FOREIGN = "15";
    
    /** コンプラランクチェック判定結果.ノーマル */
    private static final String COMPLA_RANK_JUDGEMENT_RESULT_NORMAL = "0";
    
    /** コンプラランクチェック判定結果.アラート */
    private static final String COMPLA_RANK_JUDGEMENT_RESULT_ALERT = "1";
    
    /** APIエラーメッセージ切り出し始点 */
    private static final int API_MESSAGE_START = 0;
    
    /** APIエラーメッセージ切り出し終点 */
    private static final int API_MESSAGE_END = 100;
    
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
    CodeListService codeListService;
    
    @Autowired
    private IfaForeignSpotTradeOrderConfirmDao dao;
    
    @Autowired
    private ForeignStockService foreignStockService;
    
    @Autowired
    private ForeignInformationService foreignInformationService;
    
    @Autowired
    private CometCommonService cometCommonService;
    
    /**
     * アクションID：A004
     * アクション名：更新
     * Dto リクエスト：IfaForeignSpotTradeOrderConfirmA004DtoRequest
     * Dto レスポンス：IfaForeignSpotTradeOrderConfirmA004DtoResponse
     *
     * @param dtoReq リクエスト
     * @return Dto レスポンス
     * @exception Exception エラーが発生した場合
     */
    public DataList<IfaForeignSpotTradeOrderConfirmA004ResponseDto> updateA004(
            IfaForeignSpotTradeOrderConfirmA004RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignSpotTradeOrderConfirmServiceImplL.updateA004");
        }
        
        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        // 利用者の口座に対する権限チェックを行う。　（FCT001）
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(customerCommon.getButenCode());
        inputFct001Dto.setAccountNumber(customerCommon.getAccountNumber());
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        List<IfaForeignSpotTradeOrderConfirmA004ResponseDto> resSubList = new ArrayList<>();
        // 権限あり：次の処理へ。
        // 権限なし：権限なしエラーを返す。
        if (TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId()
                .equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_BUTEN_ACCOUNT_NOT_EXIST,
                    IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOT_EXIST,
                            new String[] { customerCommon.getButenCode(), customerCommon.getAccountNumber() }));
        }
        
        // 口座の契約締結前交付書面コードのチェック　および　共同募集顧客(共募顧客)のチェックを行う　（FCT003）
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setButenCode(customerCommon.getButenCode());
        inputFct003Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct003Dto.setProductCd(SECURITY_MONEY_CLASS_FOREIGN);
        inputFct003Dto.setTradeCd(dtoReq.getTradeCd());
        inputFct003Dto.setCountryCd(dtoReq.getCountryCode());
        OutputFct003Dto outputFct003Dto = fct003.doCheck(inputFct003Dto);
        // 「FCT003.レスポンス.媒介可否リスト.媒介可否＝""1""（媒介可）」の場合：次の処理へ。
        // 上記以外：媒介不可エラーを返す。
        if (checkFct003Result(outputFct003Dto)) {
            return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_MEDIATE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(ERRORS_MEDIATE_UNAVAILABLE, new String[] { codeListService
                            .getValue(CODE_ID_MSG_DISPLAY_TARGET_TRADE, CODE_VAL_MSG_DISPLAY_TARGET_TRADE_FSTOCK_ENTRUST) }));
        }
        
        // 顧客共通情報.外国株式取引口座開設状況より、外貨建口座取引開設状況のチェックを行う。
        // 「開設済」：次の処理へ。
        // 「未開設」：外国株式口座未開設エラーを返す。
        if (ForeignStockTradeAccountOpenStatus.CLOSED.getId()
                .equals(customerCommon.getForeignStockTradeAccountOpenStatus())) {
            return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_FSTOCK_ACCOUNT_NOT_OPENED,
                    IfaCommonUtil.getMessage(ERRORS_FSTOCK_ACCOUNT_NOT_OPENED));
        }
        
        DataList<IfaForeignSpotTradeOrderConfirmA004ResponseDto> dtoRes = new DataList<IfaForeignSpotTradeOrderConfirmA004ResponseDto>();
        
        // 株価を取得する。　
        // 銘柄情報を取得する。　（API001）
        GetForeignStockSecuritiesResp api001Res = null;
        try {
            api001Res = foreignStockService.getForeignStockSecurities(dtoReq.getCountryCode(), dtoReq.getBrandCode());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderConfirmServiceImplL.updateA004}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // 株価チケットなどを取得する。　（API008）
        CreateMarketPriceTicketResp api008Res = null;
        try {
            api008Res = foreignInformationService.createMarketPriceTicket(customerCommon.getButenCode(),
                    customerCommon.getAccountNumber(), IP_ADDRESS, API_USER_AGENT, dtoReq.getCountryCode());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderConfirmServiceImplL.updateA004}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // ハッシュ結果を取得する。　（API009）
        GetMarketPriceHashResp api009Res = null;
        try {
            api009Res = foreignInformationService.getMarketPriceHash(customerCommon.getButenCode(),
                    customerCommon.getAccountNumber(), api008Res.getPriceTicket(), dtoReq.getCountryCode());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderConfirmServiceImplL.updateA004}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // 株価を取得する。　（API004）
        ListMarketPricesResp api004Res = null;
        try {
            api004Res = foreignInformationService.listMarketPrices(api009Res.getHashValue(), dtoReq.getCountryCode(),
                    new String[] { api001Res.getSecurities().getRic() });
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderConfirmServiceImplL.updateA004}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        //レスポンスに値をセットする
        IfaForeignSpotTradeOrderConfirmA004ResponseDto responseData = new IfaForeignSpotTradeOrderConfirmA004ResponseDto();

        if (CollectionUtils.isNotEmpty(api004Res.getMarketPrices())) {
            IfaForeignSpotTradeOrderConfirmA004ResponseDtoPrise responsePrice = new IfaForeignSpotTradeOrderConfirmA004ResponseDtoPrise();
            PriceData price = api004Res.getMarketPrices().get(0).getPrice();
            responsePrice.setCurrentPrice(price.getLast());
            responsePrice.setCurrentPriceDate(price.getLastDatetime());
            responsePrice.setTick(price.getTickArrow());
            responsePrice.setDiff(price.getChange());
            responsePrice.setRatio(price.getChangePercent());
            responsePrice.setStart(price.getOpen());
            responsePrice.setHigh(price.getHigh());
            responsePrice.setLow(price.getLow());
            responsePrice.setVolume(price.getVolume());
            responsePrice.setLast(price.getPrevClose());
            responsePrice.setLastDate(price.getPrevCloseDate());

            responseData.setPriceBasicInfo(responsePrice);
        }
        resSubList.add(responseData);
        
        return IfaCommonUtil.createDataList(resSubList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtils.EMPTY);
    }
    
    /**
     * アクションID：A010a
     * アクション名：注文発注
     * Dto リクエスト：IfaForeignSpotTradeOrderConfirmA010aRequestDto
     * Dto レスポンス：IfaForeignSpotTradeOrderConfirmA010aResponseDto
     * model リクエスト：IfaForeignSpotTradeOrderConfirmSql001RequestModel
     *
     * @param dtoReq リクエスト
     * @return A010レスポンス
     * @exception Exception 例外発生時throw
     */
    public DataList<IfaForeignSpotTradeOrderConfirmA010aResponseDto> orderPlacementA010a(
            IfaForeignSpotTradeOrderConfirmA010aRequestDto dtoReq) throws Exception {
        
        DataList<IfaForeignSpotTradeOrderConfirmA010aResponseDto> response = new DataList<IfaForeignSpotTradeOrderConfirmA010aResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignSpotTradeOrderConfirmServiceImplL.orderPlacementA010a");
        }
        
        // ユーザ共通情報の取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // 委託注文サービス時間のチェックを行う。　（FCT018）
        InputFct018Dto inputFct018Dto = new InputFct018Dto();
        inputFct018Dto.setCountryCode(dtoReq.getCountryCode());
        OutputFct018Dto outputFct018Dto = fct018.doCheck(inputFct018Dto);
        List<IfaForeignSpotTradeOrderConfirmA010aResponseDto> resSubList = new ArrayList<>();
        // OKの場合：②の処理へ
        // NGの場合：エラーを返す
        if (RESULT_NG.equals(outputFct018Dto.getProcessResult())) {
            return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_OUT_OF_HOURS,
                    IfaCommonUtil.getMessage(ERRORS_OUT_OF_HOURS));
        }
        
        // ユーザ共通情報.権限コードを参照して利用者の権限チェックを行う。
        // SBI証券本店（権限1）、SBI証券支店（権限2）以外：処理③へ
        // SBI証券本店（権限1）、SBI証券支店（権限2）// 　：権限エラーを返す
        if (PrivId.HEAD_OFFICE.getId().equals(userAccount.getPrivId())
                || PrivId.BRANCH.getId().equals(userAccount.getPrivId())) {
            return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL,
                    ERRORS_USER_ACCOUNT_INSUFFICIENT_PRIVILEGE,
                    IfaCommonUtil.getMessage(ERRORS_USER_ACCOUNT_INSUFFICIENT_PRIVILEGE));
        }
        
        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        // 利用者の口座に対する権限チェックを行う。　（FCT001）
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(customerCommon.getButenCode());
        inputFct001Dto.setAccountNumber(customerCommon.getAccountNumber());
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        // 対象顧客参照権限有無＝"0"（権限なし）：権限なしエラーを返す。
        if (TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId()
                .equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_BUTEN_ACCOUNT_NOT_EXIST,
                    IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOT_EXIST,
                            new String[] { customerCommon.getButenCode(), customerCommon.getAccountNumber() }));
        }
        // 取引停止フラグ＝"1"（取引停止口座）：取引停止口座エラーを返す。
        if (TradeSuspendFlag.SUSPEND.getId().equals(outputFct001Dto.getTradeSuspendFlag())) {
            return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_OUT_OF_SERVICE,
                    IfaCommonUtil.getMessage(ERRORS_OUT_OF_SERVICE));
        }
        // 上記以外：次の処理へ
        
        // 口座の契約締結前交付書面コードのチェック　および　共同募集顧客(共募顧客)のチェックを行う　（FCT003）
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setButenCode(customerCommon.getButenCode());
        inputFct003Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct003Dto.setProductCd(SecurityMoneyClass.FOREIGN_STOCK.getId());
        inputFct003Dto.setTradeCd(dtoReq.getTradeCd());
        inputFct003Dto.setCountryCd(dtoReq.getCountryCode());
        OutputFct003Dto outputFct003Dto = fct003.doCheck(inputFct003Dto);
        // 「FCT003.レスポンス.媒介可否リスト.媒介可否＝""1""（媒介可）」の場合：次の処理へ。"
        // 上記以外：媒介不可エラーを返す。
        if (checkFct003Result(outputFct003Dto)) {
            return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_MEDIATE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(ERRORS_MEDIATE_UNAVAILABLE, new String[] { codeListService
                            .getValue(CODE_ID_MSG_DISPLAY_TARGET_TRADE, CODE_VAL_MSG_DISPLAY_TARGET_TRADE_FSTOCK_ENTRUST) }));
        }
        
        // 顧客共通情報.外国株式取引口座開設状況より、外貨建口座取引開設状況のチェックを行う。
        // 「開設済」：次の処理へ。
        // 「未開設」：外国株式口座未開設エラーを返す。
        if (ForeignStockTradeAccountOpenStatus.CLOSED.getId()
                .equals(customerCommon.getForeignStockTradeAccountOpenStatus())) {
            return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_FSTOCK_ACCOUNT_NOT_OPENED,
                    IfaCommonUtil.getMessage(ERRORS_FSTOCK_ACCOUNT_NOT_OPENED));
        }
        
        // 銘柄情報を取得する。　（API001）
        GetForeignStockSecuritiesResp api001Res = null;
        try {
            api001Res = foreignStockService.getForeignStockSecurities(dtoReq.getCountryCode(), dtoReq.getBrandCode());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderConfirmServiceImplL.orderPlacementA010}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        // 現物買付の場合、「銘柄上場ステータス」チェックを行う
        if (ForeignStockTradeClass.SPOT_BUY.getId().equals(dtoReq.getTradeCd())) {
            // 「買付停止」 ：エラーを返す
            if (ListedSecuritiesStatus.BUY_STOP.getId().equals(api001Res.getListedSecuritiesStatus())) {
                return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_TRADE_STOPPED_BUY_ONLY,
                        IfaCommonUtil.getMessage(ERRORS_TRADE_STOPPED_BUY_ONLY));
            }
            // 「売買停止」：エラーを返す
            if (ListedSecuritiesStatus.BUY_SELL_STOP.getId().equals(api001Res.getListedSecuritiesStatus())) {
                return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_TRADE_STOPPED_BUY_SELL,
                        IfaCommonUtil.getMessage(ERRORS_TRADE_STOPPED_BUY_SELL));
            }
            // 「上場廃止」：エラーを返す
            if (ListedSecuritiesStatus.DELISTING.getId().equals(api001Res.getListedSecuritiesStatus())) {
                return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_DELISTED_STOCK,
                        IfaCommonUtil.getMessage(ERRORS_DELISTED_STOCK));
            }
            // 上記以外：次の処理へ
        }
        
        // 注意銘柄を確認する。　（API003）
        GetForeignStockAttentionSecuritiesResp api003Res = null;
        try {
            api003Res = foreignStockService.getForeignStockAttentionSecurities(dtoReq.getCountryCode(),
                    dtoReq.getBrandCode());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderConfirmServiceImplL.orderPlacementA010}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // 数量をチェックする。
        // 銘柄情報を取得する。　（API001） (取得済み）
        // 売却可能数を取得する。　（API002）
        GetForeignStockCreatedOrderInitializationResp api002Res = null;
        try {
            api002Res = foreignStockService.getForeignStockCreatedOrderInitialization(customerCommon.getButenCode(),
                    customerCommon.getAccountNumber(), dtoReq.getCountryCode(), dtoReq.getBrandCode(),
                    dtoReq.getTradeCd());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderConfirmServiceImplL.orderPlacementA010}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // 数量が、取引下限数量以上　かつ　取引上限数量（ただし現物売却の場合は、売却可能数（対象の預り区分の株数）の小さい方の数量）以下
        // の範囲内にあることをチェックする。（ただし取引上限数量が0の場合は上限なしとして扱う）　（API001・API002）
        // エラーなし：次の処理へ
        // エラーあり：エラーを返す。
        String tradeLimitMax = checkApi001and002Max(dtoReq, api001Res, api002Res);
        if (checkApi001and002Result(dtoReq.getOrderQuantity(), api001Res.getTradeLimitMin(), tradeLimitMax)) {
            return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_ORDER_VALUE_OUT_OF_RANGE,
                    IfaCommonUtil.getMessage(ERRORS_ORDER_VALUE_OUT_OF_RANGE,
                            new String[] { ORDER_QUANTITY, api001Res.getTradeLimitMin(), tradeLimitMax }));
        }
        
        // 数量が、取引単位で割り切れることをチェックする。　（API001）
        // エラーなし：次の処理へ
        // エラーあり：エラーを返す。
        if (new BigDecimal(dtoReq.getOrderQuantity()).remainder(new BigDecimal(api001Res.getTradeUnit()))
                .compareTo(BigDecimal.ZERO) != 0) {
            return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_INCONSISTENT_ORDER,
                    IfaCommonUtil.getMessage(ERRORS_INCONSISTENT_ORDER,
                            new String[] { ORDER_QUANTITY, api001Res.getTradeLimitMin(), api001Res.getTradeUnit() }));
        }
        
        // 注文単価（指値）が、0以上999999999.**以下の範囲にあることをチェックする。
        // エラーなし：次の処理へ
        // エラーあり：エラーを返す。
        if (dtoReq.getOrderPriceKindText().equals(SelectAblePriceConditions.PRICE_LIMIT.getId())) {
            String hiddenOrderPriceMax = PRICE_MAX
                    + StringUtils.repeat(PRICE_MAX_FLOAT, new BigDecimal(dtoReq.getHiddenOrderPrice()).scale());
            if (checkPriceRange(dtoReq.getHiddenOrderPrice(), hiddenOrderPriceMax)) {
                return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_ORDER_VALUE_OUT_OF_RANGE,
                        IfaCommonUtil.getMessage(ERRORS_ORDER_VALUE_OUT_OF_RANGE,
                                new String[] { ORDER_PRICE, ZERO, hiddenOrderPriceMax }));
            }
        } else if (SelectAblePriceConditions.STOP_ORDER_EXECUTE_PRICE.getId().equals(dtoReq.getOrderPriceKindText())
                || SelectAblePriceConditions.STOP_MARKET_ORDER_EXECUTE_PRICE.getId()
                        .equals(dtoReq.getOrderPriceKindText())) {
            // 発火条件価格（逆指値）が、0以上999999999.**以下の範囲にあることをチェックする。
            // エラーなし：次の処理へ
            // エラーあり：エラーを返す。
            String stopOrderPriceMax = PRICE_MAX
                    + StringUtils.repeat(PRICE_MAX_FLOAT, new BigDecimal(dtoReq.getStopOrderPrice()).scale());
            if (checkPriceRange(dtoReq.getStopOrderPrice(), stopOrderPriceMax)) {
                return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_ORDER_VALUE_OUT_OF_RANGE,
                        IfaCommonUtil.getMessage(ERRORS_ORDER_VALUE_OUT_OF_RANGE,
                                new String[] { STOP_ORDER_PRICE, ZERO, stopOrderPriceMax }));
            }
            
            if (SelectAblePriceConditions.STOP_ORDER_EXECUTE_PRICE.getId().equals(dtoReq.getOrderPriceKindText())) {
                // 価格条件が逆指値/指値の場合に、注文単価（逆指値）が、0以上999999999.**以下の範囲にあることをチェックする。
                // エラーなし：次の処理へ
                // エラーあり：エラーを返す。
                String hiddenOrderPriceMax = PRICE_MAX
                        + StringUtils.repeat(PRICE_MAX_FLOAT, new BigDecimal(dtoReq.getHiddenOrderPrice()).scale());
                if (checkPriceRange(dtoReq.getHiddenOrderPrice(), hiddenOrderPriceMax)) {
                    return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_ORDER_VALUE_OUT_OF_RANGE,
                            IfaCommonUtil.getMessage(ERRORS_ORDER_VALUE_OUT_OF_RANGE,
                                    new String[] { ORDER_PRICE_REVERCE, ZERO, hiddenOrderPriceMax }));
                }
            }
        }
        
        // 期間条件が「期間指定」の場合、日付が9営業日後以内であることをチェックする。　（API002）
        if (checkApi002Result(dtoReq, api002Res)) {
            return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_ORDER_TERMS_OUT_OF_RANGE,
                    IfaCommonUtil.getMessage(ERRORS_ORDER_TERMS_OUT_OF_RANGE));
        }
        
        // 乗換え勧誘(ETF)がチェックオンの場合、勧誘区分が「勧誘あり」に選択されていることをチェックする。
        // エラーなし：次の処理へ
        // エラーあ り：エラーを返す。
        if (AppConstants.FLG_ON.equals(dtoReq.getSolicitationEtfText())
                && !AppConstants.FLG_ON.equals(dtoReq.getSolicitTypeName())) {
            return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_INVITATION_CHECK,
                    IfaCommonUtil.getMessage(ERRORS_INVITATION_CHECK,
                            new String[] { INVITATION_ERROR_MESSAGE1, INVITATION_ERROR_MESSAGE2 }));
        }
        
        // 口座の取引制限チェックを行う。　（FCT021）
        // 注意情報エラー有無="1"（あり）：エラーを返す。
        // お知らせエラー有無="1"（あり）：エラーを返す。
        // 注意情報アラート有無="1"（あり）：
        // 注意情報アラート確認チェックボックス=ON：次の処理へ
        // 注意情報アラート確認チェックボックス=OFF　または　非表示：エラーを返す。
        // お知らせアラート有無="1"（あり）：アラート情報を格納する。
        // お知らせアラート確認チェックボックス=ON：次の処理へ
        // お知らせアラート確認チェックボックス=OFF　または　非表示：エラーを返す。
        // 上記チェックを実施したら、次の処理へ。
        InputFct021Dto inputFct021Dto = new InputFct021Dto();
        inputFct021Dto.setButenCode(customerCommon.getButenCode());
        inputFct021Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct021Dto.setProductCd(SecurityMoneyClass.FOREIGN_STOCK.getId());
        inputFct021Dto.setTradeCd(dtoReq.getTradeCd());
        inputFct021Dto.setCountryCd(dtoReq.getCountryCode());
        inputFct021Dto.setCurrencyCode(CURRENCY_CODE);
        OutputFct021Dto outputFct021Dto = fct021.doCheck(inputFct021Dto);
        if (Fct021.EXIST.equals(outputFct021Dto.getNoteInfoErrFlag())) {
            return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_NOTICE_ERROR_CHECK, IfaCommonUtil
                    .getMessage(ERRORS_NOTICE_ERROR_CHECK, new String[] { codeListService
                            .getValue(CODE_ID_MSG_DISPLAY_TARGET_TRADE, CODE_VAL_MSG_DISPLAY_TARGET_TRADE_FSTOCK) }));
        }
        if (Fct021.EXIST.equals(outputFct021Dto.getNoteLimitErrFlag())) {
            return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_INFORMATION_CHECK,
                    IfaCommonUtil.getMessage(ERRORS_INFORMATION_CHECK));
        }
        
        if (checkFct021Result(dtoReq, outputFct021Dto)) {
            return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_INFOMATION_OCCURS,
                    IfaCommonUtil.getMessage(ERRORS_INFOMATION_OCCURS));
        }
        
        // 現物買付の場合、コンプラランクチェックを行う。　（FCT006）
        if (ForeignStockTradeClass.SPOT_BUY.getId().equals(dtoReq.getTradeCd())) {
            InputFct006Dto inputFct006Dto = new InputFct006Dto();
            inputFct006Dto.setButenCode(customerCommon.getButenCode());
            inputFct006Dto.setAccountNumber(customerCommon.getAccountNumber());
            inputFct006Dto.setBrDomesticFgnInd(BR_DOMESTIC_FGN_IND);
            inputFct006Dto.setBrBrandInd(BR_BRAND_IND);
            inputFct006Dto.setBrandCode1(dtoReq.getBrandCode());
            inputFct006Dto.setInvitationType(dtoReq.getSolicitTypeName());
            inputFct006Dto.setCountryCode(dtoReq.getCountryCode());
            if (COMPLA_RANK_JUDGEMENT_RESULT_NORMAL.equals(dtoReq.getSolicitTypeName())) {
                inputFct006Dto.setInvitationType(INVITATION);
            }
            inputFct006Dto.setOrderMethod(dtoReq.getReceiveOrderTypeName());
            if (COMPLA_RANK_JUDGEMENT_RESULT_NORMAL.equals(dtoReq.getReceiveOrderTypeName())) {
                inputFct006Dto.setOrderMethod(ORDER_METHOD_TYPE_SHOP);
            } else if (ORDER_TYPE_VISIT.equals(dtoReq.getReceiveOrderTypeName())) {
                inputFct006Dto.setOrderMethod(ORDER_METHOD_TYPE_VISIT);
            }
            inputFct006Dto.setComplaCheckKind(COMPLA_CHECK_BOX_WARNING);
            OutputFct006Dto outputFct006Dto = fct006.doCheck(inputFct006Dto);
            // FCT006.判定結果=0：ノーマル：次の処理へ
            // FCT006.判定結果=1：アラート：
            if (COMPLA_RANK_JUDGEMENT_RESULT_ALERT.equals(outputFct006Dto.getJudgementResult())) {
                // FTC006.コンプラランクチェック.チェックボックス文言と同じチェックボックスがON：次の処理へ
                // FTC006.コンプラランクチェック.チェックボックス文言と同じチェックボックスがOFF　または　非表示：エラーを返す。
                if (checkFct006Result(dtoReq, outputFct006Dto)) {
                    return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_INFOMATION_OCCURS,
                            IfaCommonUtil.getMessage(ERRORS_INFOMATION_OCCURS));
                }
            } else if (!COMPLA_RANK_JUDGEMENT_RESULT_NORMAL.equals(outputFct006Dto.getJudgementResult())
                    && !COMPLA_RANK_JUDGEMENT_RESULT_ALERT.equals(outputFct006Dto.getJudgementResult())) {
                // FCT006.判定結果=2：エラー：エラーを返す。
                // FCT006.判定結果=上記以外：エラーを返す。
                return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, outputFct006Dto.getMessageId(),
                        IfaCommonUtil.getMessage(outputFct006Dto.getMessageId()));
            }
        }
        
        // 注意銘柄を取得する。　（API003）
        // 売買区分=買付　かつ　注意銘柄=true　の場合
        // 取引注意情報（銘柄）確認チェックボックス=ON：次の処理へ
        // 取引注意情報（銘柄）確認チェックボックス=OFF　または　非表示：エラーを返す。
        if (checkApi003Result(dtoReq, api003Res)) {
            return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_INFOMATION_OCCURS,
                    IfaCommonUtil.getMessage(ERRORS_INFOMATION_OCCURS));
        }
        
        // 米国かつ買いで外貨決済の場合、金銭残高チェックを行う。　（FCT004）
        // リクエスト.概算受渡金額（外貨）　≦　【共通】余力チェック.計算後の余力金額　：チェックOKとして次の処理へ
        // リクエスト.概算受渡金額（外貨）　＞　【共通】余力チェック.計算後の余力金額　：チェックNGとしてエラーを返す。
        if (StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.US.getId())
                && ForeignStockTradeClass.SPOT_BUY.getId().equals(dtoReq.getTradeCd())
                && SETTLEMENT_TYPE_FOREIGN.equals(dtoReq.getCurrencyTypeName())) {
            InputFct004Dto inputFct004Dto = new InputFct004Dto();
            inputFct004Dto.setButenCode(customerCommon.getButenCode());
            inputFct004Dto.setAccountNumber(customerCommon.getAccountNumber());
            inputFct004Dto.setDepositType(dtoReq.getDepositTypeName());
            inputFct004Dto.setTradeType(StringUtils.EMPTY);
            inputFct004Dto.setOtcManageNumber(StringUtils.EMPTY);
            
            OutputFct004Dto outputFct004Dto = fct004.doCheck(inputFct004Dto);
            if (outputFct004Dto.getByingPowerMoneyAfterCalculate()
                    .compareTo(new BigDecimal(dtoReq.getApproximateDeliveryForeignAmount())) < 0) {
                return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_BUYING_POWER_SHORT,
                        IfaCommonUtil.getMessage(ERRORS_BUYING_POWER_SHORT,
                                new String[] { dtoReq.getApproximateDeliveryForeignAmount(),
                                        outputFct004Dto.getRealTimeBuyingPower().toPlainString(),
                                        outputFct004Dto.getOtcBuyingContractAmountToday().toPlainString(),
                                        outputFct004Dto.getContractAmountTodayWithinForeignBond().toPlainString() }));
            }
        }
        
        // 米国かつ売りの場合、証券残高チェックを行う。　（FCT028）
        // 注文数量　≦　【共通】余力チェック.売却可能数量(株数)　：チェックOKとして次の処理へ
        // 注文数量　＞　【共通】余力チェック.売却可能数量(株数)　：チェックNGとしてエラーを返す。
        if (StringUtils.equals(dtoReq.getCountryCode(), NationalityCode.US.getId())
                && ForeignStockTradeClass.SPOT_SELL.getId().equals(dtoReq.getTradeCd())) {
            InputFct028Dto inputFct028Dto = new InputFct028Dto();
            inputFct028Dto.setButenCode(customerCommon.getButenCode());
            inputFct028Dto.setAccountNumber(customerCommon.getAccountNumber());
            inputFct028Dto.setBrandCode(dtoReq.getBrandCode());
            inputFct028Dto.setDepositType(dtoReq.getDepositTypeName());
            inputFct028Dto.setOtcManagementNo(StringUtils.EMPTY);
            
            OutputFct028Dto outputFct028Dto = fct028.doCheck(inputFct028Dto);
            if (outputFct028Dto.getAcPositionStockNumber().compareTo(new BigDecimal(dtoReq.getOrderQuantity())) < 0) {
                return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL, ERRORS_STOCK_BALANCE_SHORT,
                        IfaCommonUtil.getMessage(ERRORS_STOCK_BALANCE_SHORT,
                                new String[] { dtoReq.getOrderQuantity(),
                                        outputFct028Dto.getRealtimeBalanceStockNumberSell().toPlainString(),
                                        outputFct028Dto.getCurrentDayOtcSellStockNumber().toPlainString() }));
            }
        }
        
        //IFA注文番号をシーケンスオブジェクトから取得する。
        String ifaOrderNo = dao.selectIfaForeignSpotTradeOrderConfirmSql003();
        // 発注前に注文内容をDBに登録する。
        // 注文データをDBに登録する。　（SQL001）
        IfaForeignSpotTradeOrderConfirmSql001RequestModel insSql001Req = new IfaForeignSpotTradeOrderConfirmSql001RequestModel();
        insSql001Req.setIfaOrderNo(ifaOrderNo);
        insSql001Req.setIfaOrderSubNo(IFA_ORDER_SUB_NUMBER);
        insSql001Req.setAcceptOrderNo(dtoReq.getOrderNumber());
        insSql001Req.setAcceptOrderSubNo(dtoReq.getOrderSubNumber());
        insSql001Req.setButenCode(customerCommon.getButenCode());
        insSql001Req.setAccountNumber(customerCommon.getAccountNumber());
        insSql001Req.setBrandCode(dtoReq.getBrandCode());
        insSql001Req.setBrandName(dtoReq.getBrandName());
        insSql001Req.setExchangeCode(dtoReq.getMarketCode());
        insSql001Req.setMethodType(ZERO);
        insSql001Req.setTradeType(dtoReq.getTradeKbn());
        insSql001Req.setOrderQuantity(dtoReq.getOrderQuantity());
        insSql001Req.setTradeMethodType(dtoReq.getOrderPriceKindText());
        insSql001Req.setLimitPrice(dtoReq.getHiddenOrderPrice());
        insSql001Req.setStopPrice(dtoReq.getStopOrderPrice());
        insSql001Req.setCurrencyCode(dtoReq.getLimitPriceText());
        insSql001Req.setSettlementType(dtoReq.getCurrencyTypeName());
        insSql001Req.setDepositType(dtoReq.getDepositTypeName());
        insSql001Req.setInvitationType(dtoReq.getSolicitTypeName());
        insSql001Req.setOrderMethodType(dtoReq.getReceiveOrderTypeName());
        insSql001Req.setWarningApplyType(complianceCheckResult(dtoReq));
        insSql001Req.setExplantionInfoType(dtoReq.getImportantMatterTypeText());
        insSql001Req.setTransferInvitationType(dtoReq.getSolicitationEtfText());
        insSql001Req.setEngPubBrandExpType(dtoReq.getEngPubText());
        insSql001Req.setConfirmationType(dtoReq.getCheckInsider());
        insSql001Req.setOrderType(ZERO);
        //YYYYMMDD形式に変換して格納
        insSql001Req.setOrderDate(DateFormatUtil.AS_DEFUAL_ORDER_INPUT_DATE);
        //YYYY/MM/DD HH:MM:SS形式に変換して格納
        insSql001Req.setOrderTime(ORDER_DEFAULT);
        if (StringUtils.isNotEmpty(dtoReq.getOrderDate())) {
            insSql001Req.setOrderDate(DateFormatUtil.offSetFormatToYmd(dtoReq.getOrderDate()));
            insSql001Req.setOrderTime(DateFormatUtil.dateFormatToSeparatedYmdhms(dtoReq.getOrderDate()));
        }
        insSql001Req.setTradeDate(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getDomesticTradeDate()));
        insSql001Req.setTradeLimitDate(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getPeriod()));
        //期間条件が当日注文の場合は、A013.現地約定日
        if (PeriodConditions.TODAY.getId().equals(dtoReq.getPeriodDate())) {
            insSql001Req.setTradeLimitDate(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getLocalContractDate()));
        }
        insSql001Req.setBrokerCode(customerCommon.getBrokerCode());
        insSql001Req.setIntermediaryEmpCd(customerCommon.getBrokerChargeCode());
        insSql001Req.setCreateUser(userAccount.getUserId());
        insSql001Req.setUpdateUser(userAccount.getUserId());
        try {
            dao.insertIfaForeignSpotTradeOrderConfirmSql001(insSql001Req);
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderConfirmServiceImplL.orderPlacementA010}", e);
            }
            //DB登録NG：DB登録エラーを返す。
            return IfaCommonUtil.createDataList(resSubList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_PRE_ORDER_EXECUTION_FAILED));
        }
        // DB登録OK：次の処理へ
        
        // A010bで使う値を格納
        IfaForeignSpotTradeOrderConfirmA010aResponseDto responseData = new IfaForeignSpotTradeOrderConfirmA010aResponseDto();
        responseData.setIfaOrderNo(ifaOrderNo);
        responseData.setSecuritiesType(api001Res.getSecuritiesType());
        responseData.setAttentionSecurities(api003Res.getAttentionSecurities().toString());
        resSubList.add(responseData);
        return IfaCommonUtil.createDataList(resSubList, ErrorLevel.SUCCESS, "0", "");
    }
    
    /**
     * アクションID：A010b
     * アクション名：注文発注
     * Dto リクエスト：IfaForeignSpotTradeOrderConfirmA010bRequestDto
     * Dto レスポンス：IfaForeignSpotTradeOrderConfirmA010bResponseDto
     * model リクエスト：IfaForeignSpotTradeOrderConfirmSql001RequestModel
     *
     * @param dtoReq リクエスト
     * @return A010レスポンス
     * @exception Exception 例外発生時throw
     */
    public DataList<IfaForeignSpotTradeOrderConfirmA010bResponseDto> orderPlacementA010b(
            IfaForeignSpotTradeOrderConfirmA010bRequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignSpotTradeOrderConfirmServiceImplL.orderPlacementA010b");
        }
        
        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        // 注文発注を行う。　（API007）
        // 外国株式現物注文登録APIにて注文発注を行い、結果を取得する。
        String orderPrice = null;
        //価格条件が「指値」もしくは「逆指値」の場合設定
        if (SelectAblePriceConditions.PRICE_LIMIT.getId().equals(dtoReq.getOrderPriceKindText())
                || SelectAblePriceConditions.STOP_ORDER_EXECUTE_PRICE.getId().equals(dtoReq.getOrderPriceKindText())) {
            orderPrice = dtoReq.getHiddenOrderPrice();
        }
        String stopPrice = null;
        //価格条件が「逆指値」もしくは「逆指値/成行」の場合設定
        if (SelectAblePriceConditions.STOP_ORDER_EXECUTE_PRICE.getId().equals(dtoReq.getOrderPriceKindText())
                || SelectAblePriceConditions.STOP_MARKET_ORDER_EXECUTE_PRICE.getId()
                        .equals(dtoReq.getOrderPriceKindText())) {
            stopPrice = dtoReq.getStopOrderPrice();
        }
        String noLimitPrice = null;
        //価格条件が「成行」もしくは「逆指値/成行」の場合設定
        if (SelectAblePriceConditions.MARKET_ORDER.getId().equals(dtoReq.getOrderPriceKindText())
                || SelectAblePriceConditions.STOP_MARKET_ORDER_EXECUTE_PRICE.getId()
                        .equals(dtoReq.getOrderPriceKindText())) {
            noLimitPrice = dtoReq.getExecuteBasePrice();
        }
        String orderTerm = null;
        //期間条件が期間指定の場合
        if (PeriodConditions.PERIOD_DESIGNATION.getId().equals(dtoReq.getPeriodDate())) {
            orderTerm = DateFormatUtil.dateFormatToHyphen(dtoReq.getPeriod());
        }
        
        // 取引種別を判定する
        String tradeCd = null;
        if (ForeignStockTradeClass.SPOT_BUY.getId().equals(dtoReq.getTradeCd())) {
            tradeCd = BuySell.BUY.getId();
        } else if (ForeignStockTradeClass.SPOT_SELL.getId().equals(dtoReq.getTradeCd())) {
            tradeCd = BuySell.SELL.getId();
        }
        
        CreateForeignStockOrderResp api007Res = null;
        ApiStatusModel apiStatusModel = new ApiStatusModel();
        DataList<IfaForeignSpotTradeOrderConfirmA010bResponseDto> dataListApiErr = null;
        try {
            api007Res = foreignStockService.createForeignStockOrder(
                    RequestUtil.getToken(customerCommon.getButenCode(), customerCommon.getAccountNumber()), null, null,
                    CHANNEL, dtoReq.getCountryCode(), dtoReq.getMarketCode(), dtoReq.getBrandCode(),
                    tradeCd, dtoReq.getOrderQuantity(),
                    codeListService.convertKeyToExtKey(PRICE_CONDITIONS, ATHENA, dtoReq.getOrderPriceKindText()),
                    orderPrice, stopPrice, noLimitPrice,
                    codeListService.convertKeyToExtKey(PERIOD_CONDITIONS, ATHENA, dtoReq.getPeriodDate()), orderTerm,
                    codeListService.convertKeyToExtKey(FOREIGN_DEPOSIT_TYPE, ATHENA, dtoReq.getDepositTypeName()),
                    codeListService.convertKeyToExtKey(SETTLEMENT_TYPE, ATHENA, dtoReq.getCurrencyTypeName()));
        } catch (AthenaBusinessException e) {
            apiStatusModel.setApiErrorCode(((AthenaBusinessException) e).getErrorCode());
            apiStatusModel.setApiMessage(((AthenaBusinessException) e).getMessage());
            apiStatusModel.setApiStatusCode(((AthenaBusinessException) e).getStatusCode());
            dataListApiErr = cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, "", null), e);
        }
        // 注文発注結果を格納し次の処理へ
        
        // ユーザ共通情報の取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // 発注後に注文結果をDBに反映する。
        // 注文データをDBに登録する。　（SQL002）
        // DB登録OK：次の処理へ
        // DB更新NG：注文結果DB登録失敗ワーニング情報を格納し、正常を返す。
        boolean isUpdateSucess = false;
        try {
            if (StringUtils.isEmpty(apiStatusModel.getApiErrorCode())) {
                IfaForeignSpotTradeOrderConfirmSql001RequestModel updSql002Req = new IfaForeignSpotTradeOrderConfirmSql001RequestModel();
                updSql002Req.setAcceptOrderNo(api007Res.getOrder().getOrderNo());
                updSql002Req.setAcceptOrderSubNo(api007Res.getOrder().getOrderSubNo());
                updSql002Req.setOrderDate(DateFormatUtil.offSetFormatToYmd(api007Res.getOrder().getOrderInputDatetime()));
                updSql002Req.setOrderTime(DateFormatUtil.dateFormatToSeparatedYmdhms(api007Res.getOrder().getOrderInputDatetime()));
                updSql002Req
                        .setTradeLimitDate(DateFormatUtil.dateFormatToYmdNoSign(api007Res.getOrder().getOrderTerm()));
                //期間条件が当日注文の場合は、現地約定日を格納
                if (PeriodConditions.TODAY.getId().equals(api007Res.getOrder().getOrderLimitCode())) {
                    updSql002Req.setTradeLimitDate(DateFormatUtil.dateFormatToYmdNoSign(api007Res.getOrder().getFrnTradeDate()));
                }
                updSql002Req.setApiStatusCode(Integer.toString(HttpStatus.OK.value()));
                updSql002Req.setUpdateUser(userAccount.getUserId());
                updSql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
                updSql002Req.setIfaOrderSubNo(IFA_ORDER_SUB_NUMBER);
                if (dao.updateIfaForeignSpotTradeOrderConfirmSql002Success(updSql002Req) == 1) {
                    isUpdateSucess = true;
                }
            } else {
                IfaForeignSpotTradeOrderConfirmSql001RequestModel updSql002Req = new IfaForeignSpotTradeOrderConfirmSql001RequestModel();
                updSql002Req.setApiErrorCode(apiStatusModel.getApiErrorCode());
                updSql002Req.setApiStatusCode(apiStatusModel.getApiStatusCode().toString());
                updSql002Req.setApiMsg(apiStatusModel.getApiMessage());
                //　エラーメッセージが100桁を超えたら、上100桁のみDBへ更新する。
                if (apiStatusModel.getApiMessage().length() > API_MESSAGE_END) {
                    updSql002Req
                            .setApiMsg(apiStatusModel.getApiMessage().substring(API_MESSAGE_START, API_MESSAGE_END));
                }
                updSql002Req.setUpdateUser(userAccount.getUserId());
                updSql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
                updSql002Req.setIfaOrderSubNo(IFA_ORDER_SUB_NUMBER);
                if (dao.updateIfaForeignSpotTradeOrderConfirmSql002Error(updSql002Req) == 1) {
                    isUpdateSucess = true;
                }
            }
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        
        List<IfaForeignSpotTradeOrderConfirmA010bResponseDto> resSubList = new ArrayList<>();
        // 注文発注結果がエラー：DB更新の結果にかかわらず、エラーを返す。
        if (null != dataListApiErr) {
            return dataListApiErr;
        }
        
        //レスポンスに値をセットする
        IfaForeignSpotTradeOrderConfirmA010ResponseDtoOrder setOrder = new IfaForeignSpotTradeOrderConfirmA010ResponseDtoOrder();
        setOrder.setOrderNumber(api007Res.getOrder().getOrderNo());
        setOrder.setOrderSubNumber(api007Res.getOrder().getOrderSubNo());
        setOrder.setLimitPriceText(api007Res.getOrder().getTradeCurrencyCode());
        setOrder.setTradeKbn(api007Res.getOrder().getBuySellCode());
        setOrder.setAutomaticBuyingCategory(api007Res.getOrder().getAutoStockOrderType());
        setOrder.setOrderQuantity(api007Res.getOrder().getOrderQuantity());
        setOrder.setPriceCondition(api007Res.getOrder().getOrderPriceKindCode());
        setOrder.setHiddenOrderPrice(api007Res.getOrder().getOrderPrice());
        setOrder.setTriggerPrice(api007Res.getOrder().getStopPrice());
        setOrder.setTrailStopWidth(api007Res.getOrder().getTrailingStopAmount());
        setOrder.setExecuteBasePrice(api007Res.getOrder().getNoLimitPrice());
        setOrder.setPeriodRadio(api007Res.getOrder().getOrderLimitCode());
        setOrder.setPeriod(api007Res.getOrder().getOrderTerm());
        setOrder.setDepositType(api007Res.getOrder().getSpecificAccountCode());
        setOrder.setSettlementType(api007Res.getOrder().getSettlementMethodCode());
        setOrder.setSettlementCurrency(api007Res.getOrder().getSettlementCurrencyCode());
        setOrder.setFxRate(api007Res.getOrder().getExchangeRate());
        setOrder.setAverageTradePrice(api007Res.getOrder().getExecutionAveragePrice());
        setOrder.setUnTradeQuantity(api007Res.getOrder().getUnexecutedQuantity());
        setOrder.setTradeQuantity(api007Res.getOrder().getExecutionQuantity());
        setOrder.setContractAmountForeign(api007Res.getOrder().getFrnGrossAmount());
        setOrder.setContractAmountYen(api007Res.getOrder().getGrossAmount());
        setOrder.setForeignDeliveryAmount(api007Res.getOrder().getFrnNetAmount());
        setOrder.setYenDeliveryAmount(api007Res.getOrder().getNetAmount());
        setOrder.setDeliveryAmount(api007Res.getOrder().getExecutionNetAmount());
        setOrder.setDomesticforeignFee(api007Res.getOrder().getFrnCommissionAmount());
        setOrder.setDomesticyenFee(api007Res.getOrder().getCommissionAmount());
        setOrder.setDomesticConsumptionTaxForeign(api007Res.getOrder().getFrnCommissionCtax());
        setOrder.setDomesticConsumptionTaxYen(api007Res.getOrder().getCommissionCtax());
        setOrder.setForeignFee(api007Res.getOrder().getFrnLocalCharge());
        setOrder.setYenFee(api007Res.getOrder().getLocalCharge());
        setOrder.setLocalSettlementAmountForeign(api007Res.getOrder().getFrnLocalNetAmount());
        setOrder.setLocalSettlementAmountYen(api007Res.getOrder().getLocalNetAmount());
        setOrder.setNisaFrameRestrictionAmount(api007Res.getOrder().getNisaFixedAmount());
        setOrder.setApproximateCapitalGainsTax(api007Res.getOrder().getSpecificTax());
        setOrder.setOrderStatus(api007Res.getOrder().getOrderStatus());
        setOrder.setTradeStatus(api007Res.getOrder().getExecutionStatus());
        setOrder.setOrderStopSituation(api007Res.getOrder().getWorkingStatus());
        setOrder.setDomesticTradeDate(api007Res.getOrder().getTradeDate());
        setOrder.setDomesticSettlementDate(api007Res.getOrder().getValueDate());
        setOrder.setLocalContractDate(api007Res.getOrder().getFrnTradeDate());
        setOrder.setLocalDeliveryDate(api007Res.getOrder().getFrnValueDate());
        setOrder.setOrderDate(api007Res.getOrder().getOrderInputDatetime());
        setOrder.setTradeDateTime(api007Res.getOrder().getExecutionDatetime());
        setOrder.setExpirationDateandTime(api007Res.getOrder().getExpiredDatetime());
        IfaForeignSpotTradeOrderConfirmA010bResponseDto responseData = new IfaForeignSpotTradeOrderConfirmA010bResponseDto();
        responseData.setOrderList(new ArrayList<IfaForeignSpotTradeOrderConfirmA010ResponseDtoOrder>());
        responseData.getOrderList().add(setOrder);
        responseData.setBrandClass(dtoReq.getSecuritiesType());
        responseData.setTradeLimit(dtoReq.getAttentionSecurities());
        responseData.setTradeCd(dtoReq.getTradeCd());
        responseData.setKanyuKbn(dtoReq.getSolicitTypeName());
        responseData.setReceiveOrderType(dtoReq.getReceiveOrderTypeName());
        responseData.setImportantMatterType(dtoReq.getImportantMatterTypeText());
        responseData.setSolicitationEtf(dtoReq.getSolicitationEtfText());
        responseData.setEngPubCheckbox(dtoReq.getEngPubText());
        IfaForeignSpotTradeOrderConfirmA010ResponseDtoCheck setCheck = new IfaForeignSpotTradeOrderConfirmA010ResponseDtoCheck();
        setCheck.setCheckInsider(dtoReq.getCheckInsider());
        responseData.setCheckList(new ArrayList<IfaForeignSpotTradeOrderConfirmA010ResponseDtoCheck>());
        responseData.getCheckList().add(setCheck);
        IfaForeignSpotTradeOrderConfirmA010ResponseDtoAlertCheck setAlertCheck = new IfaForeignSpotTradeOrderConfirmA010ResponseDtoAlertCheck();
        setAlertCheck.setTradingCautionInformation(dtoReq.getTradingCautionInformation());
        setAlertCheck.setInvitationCheck(dtoReq.getInvitationCheck());
        setAlertCheck.setNoteInfoCheckbox(dtoReq.getNoteInfoCheckbox());
        setAlertCheck.setNoteLimitCheck(dtoReq.getNoteLimitCheck());
        setAlertCheck.setPriceLimitCheck(dtoReq.getPriceLimitCheck());
        setAlertCheck.setMethodCheck(dtoReq.getMethodCheck());
        setAlertCheck.setNextDayCheck(dtoReq.getNextDayCheck());
        setAlertCheck.setOverseasEtfAlertConfirm(dtoReq.getOverseasEtfCheck());
        responseData.setAlertCheckList(new ArrayList<IfaForeignSpotTradeOrderConfirmA010ResponseDtoAlertCheck>());
        responseData.getAlertCheckList().add(setAlertCheck);
        responseData.setNoticeInfoAlert(dtoReq.getNoticeInfoAlert());
        responseData.setNoticeAlert(dtoReq.getNoticeAlert());
        IfaForeignSpotTradeOrderConfirmA010ResponseDtoComplianceCheck setComplianceCheck = new IfaForeignSpotTradeOrderConfirmA010ResponseDtoComplianceCheck();
        setComplianceCheck.setComplianceCheckMsg(dtoReq.getComplianceCheckMessage());
        setComplianceCheck.setChkBoxLabel(dtoReq.getComplianceCheckWording());
        responseData
                .setComplianceCheckList(new ArrayList<IfaForeignSpotTradeOrderConfirmA010ResponseDtoComplianceCheck>());
        responseData.getComplianceCheckList().add(setComplianceCheck);
        responseData.setTradeNoticeInfoBrandMsg(dtoReq.getTradeNoticeInfoBrandMsg());
        responseData.setContractAmountOverMessage(dtoReq.getContractAmountOverMessage());
        responseData.setStopOrderInstantMessage(dtoReq.getStopOrderInstantMessage());
        responseData.setNextBusinessDayMessage(dtoReq.getNextBusinessDayMessage());
        responseData.setOverseasEtfAlertMessage(dtoReq.getOverseasEtfAlertMessage());
        responseData.setBrandCode(dtoReq.getBrandCode());
        responseData.setBrandName(dtoReq.getBrandName());
        responseData.setCountryCode(dtoReq.getCountryCode());
        responseData.setMarketAbbreviation(dtoReq.getMarketAbbreviation());
        responseData.setTodayTradeLimitUrl(dtoReq.getTodayTradeLimitUrl());
        resSubList.add(responseData);
        if (isUpdateSucess) {
            return IfaCommonUtil.createDataList(resSubList, ErrorLevel.SUCCESS, "0", "");
        }
        
        return IfaCommonUtil.createDataList(resSubList, ErrorLevel.SUCCESS, WARNINGS_POST_ORDER_EXECUTION_FAILED,
                IfaCommonUtil.getMessage(WARNINGS_POST_ORDER_EXECUTION_FAILED));
    }
    
    /**
     * FCT003チェック
     *
     * @param outputFct003Dto FCT003実行結果
     * @return 判定結果
     */
    private boolean checkFct003Result(OutputFct003Dto outputFct003Dto) {
        
        for (MediatePropriety result : outputFct003Dto.getMediateProprietyList()) {
            if (MediateAbleTradeFlag.ARI.getId().equals(result.getMediatePropriety())) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * API001とAPI002の複合チェックに使用する最大値の決定
     *
     * @param dtoReq リクエスト
     * @param api001Res API001実行結果
     * @param api002Res API002実行結果
     * @return チェックに使用する最大値
     */
    private String checkApi001and002Max(IfaForeignSpotTradeOrderConfirmA010aRequestDto dtoReq,
            GetForeignStockSecuritiesResp api001Res, GetForeignStockCreatedOrderInitializationResp api002Res) {
        
        //チェックに使う最大値に「取引上限数量（ただし現物売却の場合は、売却可能数（対象の預り区分の株数）の小さい方の数量）」を格納する
        String tradeLimitMax = api001Res.getTradeLimitMax();
        if (ForeignStockTradeClass.SPOT_SELL.getId().equals(dtoReq.getTradeCd())) {
            for (SellPossibleQuantity sellQuantity : api002Res.getSellPossibleQuantities()) {
                //預り区分が一致するかつ株数が現在保持している数量より少ない場合更新
                if (sellQuantity.getSpecificAccountCode().equals(dtoReq.getDepositTypeName())
                        && new BigDecimal(tradeLimitMax)
                                .compareTo(new BigDecimal(sellQuantity.getSecuritiesQuantity())) > 0) {
                    tradeLimitMax = sellQuantity.getSecuritiesQuantity();
                }
            }
        }
        
        return tradeLimitMax;
    }
    
    /**
     * API001とAPI002の複合チェック
     *
     * @param check チェック対象値
     * @param checkMin チェック範囲最小値
     * @param checkMax チェック範囲最大値
     * @return 判定結果
     * 
     */
    private boolean checkApi001and002Result(String check, String checkMin, String checkMax) {
        
        //取引上限数量が0の場合は上限なしとして扱う
        if (ZERO.equals(checkMax)) {
            checkMax = CHECK_MAX;
        }
        //数量が、取引下限数量以上　かつ　取引上限数量以下の範囲にあることをチェックする
        if (Long.parseLong(check) < Long.parseLong(checkMin) || Long.parseLong(check) > Long.parseLong(checkMax)) {
            return true;
        }
        return false;
    }
    
    /**
     * 価格範囲チェック
     *
     * @param checkPrice チェック対象値
     * @param priceMax 最大値
     * @return 判定結果
     */
    private boolean checkPriceRange(String checkPrice, String priceMax) {
        
        if (new BigDecimal(checkPrice).compareTo(BigDecimal.ZERO) < 0) {
            return true;
        }
        if (new BigDecimal(checkPrice).compareTo(new BigDecimal(priceMax)) > 0) {
            return true;
        }
        return false;
    }
    
    /**
     * FCT021チェック
     *
     * @param dtoReq リクエスト
     * @param outputFct021Dto FCT021実行結果
     * @return 判定結果
     */
    private boolean checkFct021Result(IfaForeignSpotTradeOrderConfirmA010aRequestDto dtoReq,
            OutputFct021Dto outputFct021Dto) {
        
        if (Fct021.EXIST.equals(outputFct021Dto.getNoteInfoAlertFlag())
                && !Fct021.EXIST.equals(dtoReq.getNoteInfoCheckbox())) {
            return true;
        }
        if (Fct021.EXIST.equals(outputFct021Dto.getNoteLimitAlertFlag())
                && !Fct021.EXIST.equals(dtoReq.getNoteLimitCheck())) {
            return true;
        }
        return false;
    }
    
    /**
     * FCT006チェック
     *
     * @param dtoReq リクエスト
     * @param outputFct006Dto FCT006実行結果
     * @return 判定結果
     */
    private boolean checkFct006Result(IfaForeignSpotTradeOrderConfirmA010aRequestDto dtoReq,
            OutputFct006Dto outputFct006Dto) {
        
        //リクエスト.コンプラチェック.メッセージIDとFCT006.コンプラチェック.メッセージIDが同一かつチェックボックスがONであること
        if (dtoReq.getComplianceCheckMessageId().equals(outputFct006Dto.getMessageId())
                && AppConstants.FLG_ON.equals(dtoReq.getInvitationCheck())) {
            return false;
        }
        return true;
    }
    
    /**
     * API002チェック
     *
     * @param dtoReq リクエスト
     * @param api002Res API002実行結果
     * @return 判定結果
     */
    private boolean checkApi002Result(IfaForeignSpotTradeOrderConfirmA010aRequestDto dtoReq,
            GetForeignStockCreatedOrderInitializationResp api002Res) {
        
        // // 期間条件が「当日」の場合スキップ
        if (PeriodConditions.TODAY.getId().equals(dtoReq.getPeriodDate())) {
            return false;
        }
        // リクエスト.期間  が API002.有効期間一覧 "yyyy-MM-dd"形式 の配列の先頭から9つ目までにない場合、エラー
        // エラーなし：次の処理へ
        // エラーあり：エラーを返す。
        for (int key = 0; key < DATE_CHECK_LIMIT; key++) {
            if (dtoReq.getPeriod().equals(DateFormatUtil.dateFormatToSlash(api002Res.getOrderTerms().get(key)))) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * API003チェック
     *
     * @param dtoReq リクエスト
     * @param api003Res API003実行結果
     * @return 判定結果
     */
    private boolean checkApi003Result(IfaForeignSpotTradeOrderConfirmA010aRequestDto dtoReq,
            GetForeignStockAttentionSecuritiesResp api003Res) {
        
        if (ForeignStockTradeClass.SPOT_BUY.getId().equals(dtoReq.getTradeCd()) && api003Res.getAttentionSecurities()) {
            if (!AppConstants.FLG_ON.equals(dtoReq.getTradingCautionInformation())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * コンプラチェック_判定結果を判定する
     *
     * @param dtoReq リクエスト
     * @return コンプラチェック_判定結果
     */
    private String complianceCheckResult(IfaForeignSpotTradeOrderConfirmA010aRequestDto dtoReq) {
        
        //■遷移元画面.A013.取引種別＝現物売却の場合
        //　null
        //■コンプラランクチェック.チェックボックス文言=△・◇ワーニング申請は「申請・承認済」であることを確認済　の場合
        //　アラート内容確認.コンプラランクチェック確認　チェックボックスOFF:0、OFF:1
        //■コンプラランクチェック.チェックボックス文言=勧誘なし　の場合
        //　2
        //■上記以外
        //　0
        if (ForeignStockTradeClass.SPOT_SELL.getId().equals(dtoReq.getTradeCd())) {
            return null;
        }
        if (COMPLA_CHECK_BOX_WARNING.equals(dtoReq.getComplianceCheckWording())) {
            return dtoReq.getComplianceCheckWording();
        }
        if (COMPLA_CHECK_BOX_NOT.equals(dtoReq.getComplianceCheckWording())) {
            return dtoReq.getComplianceCheckWording();
        }
        return ZERO;
    }
}
