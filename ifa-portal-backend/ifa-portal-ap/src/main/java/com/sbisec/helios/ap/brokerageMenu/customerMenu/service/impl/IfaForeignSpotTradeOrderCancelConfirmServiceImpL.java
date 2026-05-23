package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.DateUtil;
import com.sbisec.helios.ap.athena.enums.BuySell;
import com.sbisec.helios.ap.athena.enums.ListedSecuritiesStatus;
import com.sbisec.helios.ap.athena.ifa.ForeignStockService;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.Order;
import com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockSecuritiesResp;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct018;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct018Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct018Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaForeignSpotTradeOrderCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignSpotTradeOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignSpotTradeOrderCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignSpotTradeOrderCancelConfirmSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmA010aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmA010aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmA010bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmA010bResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmBrandInformation;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmMarketInformation;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.model.ApiStatusModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaForeignSpotTradeOrderCancelConfirmService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ForeignStockTradeAccountOpenStatus;
import com.sbisec.helios.ap.common.enums.ForeignStockTradeClass;
import com.sbisec.helios.ap.common.enums.MediateAbleTradeFlag;
import com.sbisec.helios.ap.common.enums.PeriodConditions;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.enums.TradeSuspendFlag;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0301-03_1
 * 画面名：外国現物取引注文取消確認
 * 2024/03/29 新規作成
 *
 * @author 宇田川達弥
 */
@Component(value = "cmpIfaForeignSpotTradeOrderCancelConfirmService")
public class IfaForeignSpotTradeOrderCancelConfirmServiceImpL implements IfaForeignSpotTradeOrderCancelConfirmService {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaForeignSpotTradeOrderCancelConfirmServiceImpL.class);
    
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
    
    /** メッセージID:利用者権限エラー */
    private static final String MESSAGE_USER_ACCOUNT_INSUFFICIENT_PRIVILEGE = "errors.frs.orderExecution.insufficientPrivilege";
    
    /** メッセージID:委託注文サービス時間外エラー */
    private static final String MESSAGE_OUT_OF_HOURS = "errors.frs.serviceHours.outOfRange";
    
    /** メッセージID:DB登録エラー */
    private static final String MESSAGE_PRE_ORDER_EXECUTION_FAILED = "errors.frs.preOrderExecution.failed";
    
    /** メッセージID:注文結果DB登録失敗ワーニング */
    private static final String MESSAGE_POST_ORDER_EXECUTION_WARNING = "warnings.frs.postOrderExecution.completed";
    
    /** メッセージID:注文発注API処理結果エラー */
    private static final String MESSAGE_ORDER_EXECUTION_CANCEL_FAILED = "errors.cmn.orderExecutionCancel.failed";
    
    /** 区分ID:対象取引（メッセージ表示用）　*/
    private static final String CODE_ID_MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 区分値:対象取引（メッセージ表示用）.外国株式現物取引（委託）　*/
    private static final String CODE_VAL_MSG_DISPLAY_TARGET_TRADE_FSTOCK_ENTRUST = "6A";
    
    /** 区分値：証券種別.外国株式現物 */
    private static final String CODE_VAL_SECURITY_CLASS_FSTOCK = "15";
    
    /** 判定結果:OK */
    private static final String RESULT_OK = "OK";
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct018 fct018;
    
    @Autowired
    private CodeListService codeListService;
    
    @Autowired
    private ForeignStockService foreignStockService;
    
    @Autowired
    private IfaForeignSpotTradeOrderCancelConfirmDao dao;
    
    @Autowired
    private CometCommonService cometCommonService;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignSpotTradeOrderCancelConfirmA001RequestDto
     * Dto レスポンス：IfaForeignSpotTradeOrderCancelConfirmA001ResponseDto
     * model リクエスト：IfaForeignMarginTradeOrderCancelConfirmSql003RequestModel
     * model レスポンス：IfaForeignMarginTradeOrderCancelConfirmSql003ResponseModel
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignSpotTradeOrderCancelConfirmA001ResponseDto> initializeA001(
            IfaForeignSpotTradeOrderCancelConfirmA001RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignSpotTradeOrderCancelConfirmServiceImpL.initializeA001");
        }
        
        // 顧客共通情報の取得
        final CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        // 口座に対する権限チェック
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
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
        
        if (!StringUtils.equals(customerCommon.getForeignStockTradeAccountOpenStatus(),
                ForeignStockTradeAccountOpenStatus.OPEN.getId())) {
            // 外国株式取引口座開設状況が開設済ではない場合、外貨建口座未開設エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_FSTOCK_ACCOUNT_NOT_OPENED,
                    IfaCommonUtil.getMessage(MESSAGE_FSTOCK_ACCOUNT_NOT_OPENED));
        }
        
        // 注文初期情報を取得する
        GetForeignStockDeletedOrderInitializationResp api001Res = null;
        try {
            api001Res = foreignStockService.getForeignStockDeletedOrderInitialization(customerCommon.getButenCode(),
                    customerCommon.getAccountNumber(), dtoReq.getOrderSubNumber());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderCancelConfirmServiceImpL.initializeA001}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        final Order api001ResOrder = api001Res.getOrder();
        
        // 取引種別を判定する
        String tradeCd = StringUtils.EMPTY;
        if (StringUtils.equals(BuySell.BUY.getIfaCd(), api001ResOrder.getBuySellCode())) {
            // 買付：現物買
            tradeCd = ForeignStockTradeClass.SPOT_BUY.getId();
        } else if (StringUtils.equals(BuySell.SELL.getIfaCd(), api001ResOrder.getBuySellCode())) {
            // 売却：現物売
            tradeCd = ForeignStockTradeClass.SPOT_SELL.getId();
        }
        
        // 媒介可否チェック
        final InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setButenCode(customerCommon.getButenCode());
        inputFct003Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct003Dto.setProductCd(CODE_VAL_SECURITY_CLASS_FSTOCK);
        inputFct003Dto.setTradeCd(tradeCd);
        
        final OutputFct003Dto outputFct003Dto = fct003.doCheck(inputFct003Dto);
        
        // 媒介可否リスト.媒介可否に媒介可が存在するか判定する
        if (outputFct003Dto.getMediateProprietyList().stream()
                .map(mediateProprieties -> mediateProprieties.getMediatePropriety()).noneMatch(
                        mediatePropriety -> StringUtils.equals(MediateAbleTradeFlag.ARI.getId(), mediatePropriety))) {
            // 存在しない場合、媒介不可エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_MEDIATE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(MESSAGE_MEDIATE_UNAVAILABLE, new String[] { codeListService
                            .getValue(CODE_ID_MSG_DISPLAY_TARGET_TRADE, CODE_VAL_MSG_DISPLAY_TARGET_TRADE_FSTOCK_ENTRUST) }));
        }
        
        // 銘柄情報を取得する
        GetForeignStockSecuritiesResp api004Res = null;
        try {
            api004Res = foreignStockService.getForeignStockSecurities(dtoReq.getCountryCd(),
                    api001ResOrder.getSecurities().getSecuritiesCode());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderCancelConfirmServiceImpL.initializeA001}", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // 銘柄上場ステータスを判定する
        if (StringUtils.equals(tradeCd, ForeignStockTradeClass.SPOT_BUY.getId())) {
            // 取引種別が現物買付の場合、銘柄上場ステータスを判定する
            switch (ListedSecuritiesStatus.getById(api004Res.getListedSecuritiesStatus())) {
                case BUY_STOP:
                    // 買付停止の場合、買付停止エラーを返す
                    return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_TRADE_STOPPED_BUY_ONLY,
                            IfaCommonUtil.getMessage(MESSAGE_TRADE_STOPPED_BUY_ONLY));
                case BUY_SELL_STOP:
                    // 売買停止の場合、売買停止エラーを返す
                    return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_TRADE_STOPPED_BUY_SELL,
                            IfaCommonUtil.getMessage(MESSAGE_TRADE_STOPPED_BUY_SELL));
                case DELISTING:
                    // 上場廃止の場合、上場廃止エラーを返す
                    return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_DELISTED_STOCK,
                            IfaCommonUtil.getMessage(MESSAGE_DELISTED_STOCK));
                default:
                    break;
            }
        }
        
        // レスポンスを設定する
        final IfaForeignSpotTradeOrderCancelConfirmA001ResponseDto responseData = new IfaForeignSpotTradeOrderCancelConfirmA001ResponseDto();
        responseData.setCountryCd(dtoReq.getCountryCd());
        responseData.setOrderNumber(api001ResOrder.getOrderNo());
        responseData.setOrderSubNumber(api001ResOrder.getOrderSubNo());
        responseData.setLimitPriceText(api001ResOrder.getTradeCurrencyCode());
        responseData.setTradeCd(tradeCd);
        responseData.setForeignQuantity(api001ResOrder.getOrderQuantity());
        responseData.setPriceConditionsType(api001ResOrder.getOrderPriceKindCode());
        responseData.setHiddenOrderPrice(api001ResOrder.getOrderPrice());
        responseData.setStopOrderPrice2(api001ResOrder.getStopPrice());
        responseData.setPeriodRadio(api001ResOrder.getOrderLimitCode());
        responseData.setPeriod(api001ResOrder.getOrderTerm());
        responseData.setDepositType(api001ResOrder.getSpecificAccountCode());
        responseData.setKessaiHoho(api001ResOrder.getSettlementMethodCode());
        responseData.setOrderDate(api001ResOrder.getOrderInputDatetime());
        responseData.setDomesticTradeDate(api001ResOrder.getTradeDate());
        responseData.setForeignTradeDate(api001ResOrder.getFrnTradeDate());
        responseData.setTradeKbn(api001ResOrder.getBuySellCode());
        responseData.setBrandListedStatus(api004Res.getListedSecuritiesStatus());
        
        // 銘柄情報を設定する
        final IfaForeignSpotTradeOrderCancelConfirmBrandInformation brandInfo = new IfaForeignSpotTradeOrderCancelConfirmBrandInformation();
        brandInfo.setBrandCode(api001ResOrder.getSecurities().getSecuritiesCode());
        brandInfo.setBrandName(api001ResOrder.getSecurities().getSecuritiesName());
        responseData.setBrandInformationList(brandInfo);
        
        // 市場情報を設定する
        final IfaForeignSpotTradeOrderCancelConfirmMarketInformation marketInfo = new IfaForeignSpotTradeOrderCancelConfirmMarketInformation();
        marketInfo.setMarketName(api001ResOrder.getMarket().getMarketShortName());
        marketInfo.setMarketCode(api001ResOrder.getMarket().getMarketCode());
        marketInfo.setCountryCode(api001ResOrder.getMarket().getCountryCode());
        responseData.setMarketInformationList(marketInfo);
        
        // 結果を返却する
        return IfaCommonUtil.createDataList(List.of(responseData), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtils.EMPTY);
    }
    
    /**
     * アクションID：A010
     * アクション名：注文取消
     * Dto リクエスト：IfaForeignSpotTradeOrderCancelConfirmA010aRequestDto
     * Dto レスポンス：IfaForeignSpotTradeOrderCancelConfirmA010aResponseDto
     * model リクエスト：IfaForeignMarginTradeOrderCancelConfirmSql001RequestModel
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignSpotTradeOrderCancelConfirmA010aResponseDto> orderCancellationA010a(
            IfaForeignSpotTradeOrderCancelConfirmA010aRequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignSpotTradeOrderCancelConfirmServiceImpL.orderCancellationA010a");
        }
        
        // 委託注文サービス時間をチェックする
        final InputFct018Dto inputFct018Dto = new InputFct018Dto();
        inputFct018Dto.setCountryCode(dtoReq.getCountryCd());
        final OutputFct018Dto outputFct018Dto = fct018.doCheck(inputFct018Dto);
        
        if (!StringUtils.equals(outputFct018Dto.getProcessResult(), RESULT_OK)) {
            // 処理結果がOKではない場合、委託注文サービス時間外エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_OUT_OF_HOURS,
                    IfaCommonUtil.getMessage(MESSAGE_OUT_OF_HOURS));
        }
        
        // ユーザ共通情報の取得
        final UserAccount userAccount = IfaCommonUtil.getUserAccount();
        // ユーザ共通情報.権限コードを参照して利用者の権限チェックを行う。
        if (PrivId.HEAD_OFFICE.getId().equals(userAccount.getPrivId())
                || PrivId.BRANCH.getId().equals(userAccount.getPrivId())) {
            // SBI証券本店（権限1）、もしくはSBI証券支店（権限2）の場合、権限エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL,
                    MESSAGE_USER_ACCOUNT_INSUFFICIENT_PRIVILEGE,
                    IfaCommonUtil.getMessage(MESSAGE_USER_ACCOUNT_INSUFFICIENT_PRIVILEGE));
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
        inputFct003Dto.setProductCd(CODE_VAL_SECURITY_CLASS_FSTOCK);
        inputFct003Dto.setTradeCd(dtoReq.getTradeCd());
        
        final OutputFct003Dto outputFct003Dto = fct003.doCheck(inputFct003Dto);
        
        // 媒介可否リスト.媒介可否に媒介可が存在するか判定する
        if (outputFct003Dto.getMediateProprietyList().stream()
                .map(mediateProprieties -> mediateProprieties.getMediatePropriety()).noneMatch(
                        mediatePropriety -> StringUtils.equals(MediateAbleTradeFlag.ARI.getId(), mediatePropriety))) {
            // 存在しない場合、媒介不可エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_MEDIATE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(MESSAGE_MEDIATE_UNAVAILABLE, new String[] { codeListService
                            .getValue(CODE_ID_MSG_DISPLAY_TARGET_TRADE, CODE_VAL_MSG_DISPLAY_TARGET_TRADE_FSTOCK_ENTRUST) }));
        }
        
        if (!StringUtils.equals(customerCommon.getForeignStockTradeAccountOpenStatus(),
                ForeignStockTradeAccountOpenStatus.OPEN.getId())) {
            // 外国株式取引口座開設状況が開設済ではない場合、外貨建口座未開設エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_FSTOCK_ACCOUNT_NOT_OPENED,
                    IfaCommonUtil.getMessage(MESSAGE_FSTOCK_ACCOUNT_NOT_OPENED));
        }
        
        // 受付注文番号がAPI001.注文番号と一致する外国委託注文を取得する
        final Optional<String> selSql003Res = dao
                .selectIfaForeignSpotTradeOrderCancelConfirmSql003(dtoReq.getOrderNumber());
        final IfaForeignSpotTradeOrderCancelConfirmSql001RequestModel insSql001Req = new IfaForeignSpotTradeOrderCancelConfirmSql001RequestModel();
        
        if (selSql003Res.isPresent()) {
            // 受付注文番号がAPI001.注文番号と一致する外国委託注文が1件以上存在する場合、外国委託注文データを取得する
            final Optional<IfaForeignSpotTradeOrderCancelConfirmSql004ResponseModel> selSql004Res = dao
                    .selectIfaForeignSpotTradeOrderCancelConfirmSql004(dtoReq.getOrderNumber(),
                            selSql003Res.get().toString());
            final IfaForeignSpotTradeOrderCancelConfirmSql004ResponseModel selSql004ResModel = selSql004Res.get();
            
            // 取消発注前の外国委託注文データを作成する
            insSql001Req.setIfaOrderNo(selSql004ResModel.getIfaOrderNo());
            insSql001Req.setIfaOrderSubNo(String.valueOf(Integer.parseInt(selSql004ResModel.getIfaOrderSubNo()) + 1));
            insSql001Req.setAcceptOrderNo(dtoReq.getOrderNumber());
            insSql001Req.setAcceptOrderSubNo(dtoReq.getOrderSubNumber());
            insSql001Req.setButenCode(customerCommon.getButenCode());
            insSql001Req.setAccountNumber(customerCommon.getAccountNumber());
            insSql001Req.setBrandCode(dtoReq.getBrandInformationList().getBrandCode());
            insSql001Req.setBrandName(dtoReq.getBrandInformationList().getBrandName());
            insSql001Req.setMarketCode(dtoReq.getMarketInformationList().getMarketCode());
            insSql001Req.setTradeKbn(dtoReq.getTradeKbn());
            insSql001Req.setOrderQuantity(dtoReq.getForeignQuantity());
            insSql001Req.setPriceConditionsType(dtoReq.getPriceConditionsType());
            insSql001Req.setLimitPrice(dtoReq.getHiddenOrderPrice());
            insSql001Req.setStopPrice(dtoReq.getStopOrderPrice2());
            insSql001Req.setCurrencyCode(dtoReq.getLimitPriceText());
            insSql001Req.setSettlementType(dtoReq.getKessaiHoho());
            insSql001Req.setDepositType(dtoReq.getDepositType());
            insSql001Req.setInvitationType(selSql004ResModel.getInvitationType());
            insSql001Req.setOrderMethodType(selSql004ResModel.getOrderMethodType());
            insSql001Req.setWarningApplyType(selSql004ResModel.getWarningApplyType());
            insSql001Req.setExplanationInfoType(selSql004ResModel.getExplanationInfoType());
            insSql001Req.setTransferInvitationType(selSql004ResModel.getTransferInvitationType());
            insSql001Req.setEngPubBrandExpType(selSql004ResModel.getEngPubBrandExpType());
            if (StringUtils.isNotEmpty(dtoReq.getOrderDate())) {
                //YYYYMMDD形式に変換して格納する
                insSql001Req.setOrderDate(DateFormatUtil.offSetFormatToYmd(dtoReq.getOrderDate()));
                //YYYY/MM/DD HH:MM:SS形式に変換して格納する
                insSql001Req.setOrderTime(
                        OffsetDateTime.parse(dtoReq.getOrderDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                                .format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD_HHMMSS)));
            } else {
                // 注文日時がnullか空の場合は、初期値を設定する
                insSql001Req.setOrderDate(DateFormatUtil.AS_DEFUAL_ORDER_INPUT_DATE);
                insSql001Req.setOrderTime(DateFormatUtil.AS_DEFUAL_ORDER_INPUT_DATETIME);
            }
            // 国内約定日をYYYYMMDD形式で設定する
            insSql001Req.setTradeDate(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getDomesticTradeDate()));
            if (StringUtils.equals(PeriodConditions.TODAY.getId(), dtoReq.getPeriodRadio())) {
                // 期間条件が当日中の場合、現地約定日をYYYYMMDD形式で設定する
                insSql001Req.setTradeLimitDate(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getForeignTradeDate()));
            } else {
                // 期間条件が当日中以外の場合、期間をYYYYMMDD形式で設定する
                insSql001Req.setTradeLimitDate(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getPeriod()));
            }
            insSql001Req.setBrokerCode(customerCommon.getBrokerCode());
            insSql001Req.setIntermediaryEmpCd(customerCommon.getBrokerChargeCode());
            insSql001Req.setCreateUser(userAccount.getUserId());
            insSql001Req.setUpdateUser(userAccount.getUserId());
            
        } else {
            // 受付注文番号がAPI001.注文番号と一致する外国委託注文が0件の場合、IFA注文番号を取得する
            insSql001Req.setIfaOrderNo(dao.selectIfaForeignSpotTradeOrderCancelConfirmSql005());
            
            // 取消発注前の外国委託注文データを作成する
            insSql001Req.setIfaOrderSubNo(BigDecimal.ONE.toPlainString());
            insSql001Req.setAcceptOrderNo(dtoReq.getOrderNumber());
            insSql001Req.setAcceptOrderSubNo(dtoReq.getOrderSubNumber());
            insSql001Req.setButenCode(customerCommon.getButenCode());
            insSql001Req.setAccountNumber(customerCommon.getAccountNumber());
            insSql001Req.setBrandCode(dtoReq.getBrandInformationList().getBrandCode());
            insSql001Req.setBrandName(dtoReq.getBrandInformationList().getBrandName());
            insSql001Req.setMarketCode(dtoReq.getMarketInformationList().getMarketCode());
            insSql001Req.setTradeKbn(dtoReq.getTradeKbn());
            insSql001Req.setOrderQuantity(dtoReq.getForeignQuantity());
            insSql001Req.setPriceConditionsType(dtoReq.getPriceConditionsType());
            insSql001Req.setLimitPrice(dtoReq.getHiddenOrderPrice());
            insSql001Req.setStopPrice(dtoReq.getStopOrderPrice2());
            insSql001Req.setCurrencyCode(dtoReq.getLimitPriceText());
            insSql001Req.setSettlementType(dtoReq.getKessaiHoho());
            insSql001Req.setDepositType(dtoReq.getDepositType());
            if (StringUtils.isNotEmpty(dtoReq.getOrderDate())) {
                //YYYYMMDD形式に変換して格納する
                insSql001Req.setOrderDate(DateFormatUtil.offSetFormatToYmd(dtoReq.getOrderDate()));
                //YYYY/MM/DD HH:MM:SS形式に変換して格納する
                insSql001Req.setOrderTime(
                        OffsetDateTime.parse(dtoReq.getOrderDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                                .format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD_HHMMSS)));
            } else {
                // 注文日時がnullか空の場合は、初期値を設定する
                insSql001Req.setOrderDate(DateFormatUtil.AS_DEFUAL_ORDER_INPUT_DATE);
                insSql001Req.setOrderTime(DateFormatUtil.AS_DEFUAL_ORDER_INPUT_DATETIME);
            }
            // 国内約定日をYYYYMMDD形式で設定する
            insSql001Req.setTradeDate(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getDomesticTradeDate()));
            if (StringUtils.equals(PeriodConditions.TODAY.getId(), dtoReq.getPeriodRadio())) {
                // 期間条件が当日中の場合、現地約定日をYYYYMMDD形式で設定する
                insSql001Req.setTradeLimitDate(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getForeignTradeDate()));
            } else {
                // 期間条件が当日中以外の場合、期間をYYYYMMDD形式で設定する
                insSql001Req.setTradeLimitDate(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getPeriod()));
            }
            insSql001Req.setBrokerCode(customerCommon.getBrokerCode());
            insSql001Req.setIntermediaryEmpCd(customerCommon.getBrokerChargeCode());
            insSql001Req.setCreateUser(userAccount.getUserId());
            insSql001Req.setUpdateUser(userAccount.getUserId());
        }
        
        try {
            // 取消発注前の外国委託注文データを登録する
            dao.insertIfaForeignSpotTradeOrderCancelConfirmSql001(insSql001Req);
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderCancelConfirmServiceImpL.orderCancellationA010a}", e);
            }
            //DB登録NG：DB登録エラーを返す。
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_PRE_ORDER_EXECUTION_FAILED,
                    IfaCommonUtil.getMessage(MESSAGE_PRE_ORDER_EXECUTION_FAILED));
        }
        
        // 取消発注前の外国委託注文データとして登録されたIFA注文番号、IFA注文サブ番号をレスポンスに設定する
        final IfaForeignSpotTradeOrderCancelConfirmA010aResponseDto responseData = new IfaForeignSpotTradeOrderCancelConfirmA010aResponseDto();
        responseData.setIfaOrderNo(insSql001Req.getIfaOrderNo());
        responseData.setIfaOrderSubNo(insSql001Req.getIfaOrderSubNo());
        
        // 結果を返却する
        return IfaCommonUtil.createDataList(List.of(responseData), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtils.EMPTY);
    }
    
    /**
     * アクションID：A010
     * アクション名：注文取消
     * Dto リクエスト：IfaForeignSpotTradeOrderCancelConfirmA010bRequestDto
     * Dto レスポンス：IfaForeignSpotTradeOrderCancelConfirmA010bResponseDto
     * model リクエスト：IfaForeignMarginTradeOrderCancelConfirmSql002RequestModel
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignSpotTradeOrderCancelConfirmA010bResponseDto> orderCancellationA010b(
            IfaForeignSpotTradeOrderCancelConfirmA010bRequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignSpotTradeOrderCancelConfirmServiceImpL.orderCancellationA010b");
        }
        
        // 顧客共通情報の取得
        final CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        // 外国株式現物注文取消登録APIを呼び出す
        final ApiStatusModel apiStatusModel = new ApiStatusModel();
        DeleteForeignStockOrderResp api002Res = null;
        // APIエラー用のdataList
        DataList<IfaForeignSpotTradeOrderCancelConfirmA010bResponseDto> dataListApiErr = null;
        try {
            api002Res = foreignStockService.deleteForeignStockOrder(customerCommon.getButenCode(),
                    customerCommon.getAccountNumber(), StringUtils.EMPTY, StringUtils.EMPTY,
                    dtoReq.getRequest().getOrderSubNumber());
        } catch (AthenaBusinessException e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaForeignSpotTradeOrderCancelConfirmServiceImpL.orderCancellationA010b}", e);
            }
            
            apiStatusModel.setApiErrorCode(((AthenaBusinessException) e).getErrorCode());
            apiStatusModel.setApiMessage(((AthenaBusinessException) e).getMessage());
            apiStatusModel.setApiStatusCode(((AthenaBusinessException) e).getStatusCode());
            
            dataListApiErr = cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, "", null), e);
        }
        
        // ユーザ共通情報の取得
        final UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // 取消注文結果を作成する
        Order api002ResOrder = null;
        final IfaForeignSpotTradeOrderCancelConfirmSql002RequestModel updSql002Req = new IfaForeignSpotTradeOrderCancelConfirmSql002RequestModel();
        if (StringUtils.isEmpty(apiStatusModel.getApiErrorCode())) {
            api002ResOrder = api002Res.getOrder();
            updSql002Req.setAcceptOrderNo(api002ResOrder.getOrderNo());
            updSql002Req.setAcceptOrderSubNo(api002ResOrder.getOrderSubNo());
            //YYYYMMDD形式に変換して格納
            updSql002Req.setOrderDate(DateFormatUtil.offSetFormatToYmd(api002ResOrder.getOrderInputDatetime()));
            //YYYY/MM/DD HH:MM:SS形式に変換して格納
            updSql002Req.setOrderTime(
                    OffsetDateTime.parse(api002ResOrder.getOrderInputDatetime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                            .format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD_HHMMSS)));
            if (PeriodConditions.TODAY.getId().equals(api002ResOrder.getOrderLimitCode())) {
                // 期間条件が当日中の場合、現地約定日をYYYYMMDD形式で設定する
                updSql002Req.setTradeLimitDate(DateFormatUtil.dateFormatToYmdNoSign(api002ResOrder.getFrnTradeDate()));
            } else {
                // 期間条件が当日中以外の場合、期間をYYYYMMDD形式で設定する
                updSql002Req.setTradeLimitDate(DateFormatUtil.dateFormatToYmdNoSign(api002ResOrder.getOrderTerm()));
            }
            updSql002Req.setApiStatusCode(Integer.toString(HttpStatus.OK.value()));
            updSql002Req.setUpdateUser(userAccount.getUserId());
            updSql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
            updSql002Req.setIfaOrderSubNo(dtoReq.getIfaOrderSubNo());
        } else {
            updSql002Req.setApiErrorCode(apiStatusModel.getApiErrorCode());
            updSql002Req.setApiStatusCode(apiStatusModel.getApiStatusCode().toString());
            updSql002Req.setApiMsg(apiStatusModel.getApiMessage());
            updSql002Req.setUpdateUser(userAccount.getUserId());
            updSql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
            updSql002Req.setIfaOrderSubNo(dtoReq.getIfaOrderSubNo());
        }
        
        // 取消注文結果をDBに反映する
        boolean isUpdateSucess = false;
        try {
            if (dao.updateIfaForeignSpotTradeOrderCancelConfirmSql002(updSql002Req) == 1) {
                isUpdateSucess = true;
            }
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        
        // 注文取消結果がエラーの場合、DB更新の結果にかかわらず、エラーを返す。
        if (null != dataListApiErr) {
            return dataListApiErr;
        }
        
        // レスポンスを設定する
        final IfaForeignSpotTradeOrderCancelConfirmA010bResponseDto responseData = new IfaForeignSpotTradeOrderCancelConfirmA010bResponseDto();
        responseData.setOrderNumber(api002ResOrder.getOrderNo());
        responseData.setOrderSubNumber(api002ResOrder.getOrderSubNo());
        responseData.setLimitPriceText(api002ResOrder.getTradeCurrencyCode());
        responseData.setTradeKbn(api002ResOrder.getBuySellCode());
        responseData.setForeignQuantity(api002ResOrder.getOrderQuantity());
        responseData.setPriceConditionsType(api002ResOrder.getOrderPriceKindCode());
        responseData.setHiddenOrderPrice(api002ResOrder.getOrderPrice());
        responseData.setStopOrderPrice2(api002ResOrder.getStopPrice());
        responseData.setPeriodRadio(api002ResOrder.getOrderLimitCode());
        responseData.setPeriod(api002ResOrder.getOrderTerm());
        responseData.setDepositType(api002ResOrder.getSpecificAccountCode());
        responseData.setKessaiHoho(api002ResOrder.getSettlementMethodCode());
        responseData.setOrderDate(api002ResOrder.getOrderInputDatetime());
        responseData.setRequest(dtoReq.getRequest());

        // 銘柄情報を設定する
        final IfaForeignSpotTradeOrderCancelConfirmBrandInformation brandInfo = new IfaForeignSpotTradeOrderCancelConfirmBrandInformation();
        brandInfo.setBrandCode(api002ResOrder.getSecurities().getSecuritiesCode());
        brandInfo.setBrandName(api002ResOrder.getSecurities().getSecuritiesName());
        responseData.setBrandInformationList(brandInfo);
        
        // 市場情報を設定する
        final IfaForeignSpotTradeOrderCancelConfirmMarketInformation marketInfo = new IfaForeignSpotTradeOrderCancelConfirmMarketInformation();
        marketInfo.setMarketName(api002ResOrder.getMarket().getMarketShortName());
        marketInfo.setMarketCode(api002ResOrder.getMarket().getMarketCode());
        marketInfo.setCountryCode(api002ResOrder.getMarket().getCountryCode());
        responseData.setMarketInformationList(marketInfo);
        
        if (isUpdateSucess) {
            // 取消発注結果が正常で、DB更新に成功した場合
            return IfaCommonUtil.createDataList(List.of(responseData), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                    StringUtils.EMPTY);
        }
        
        // 取消発注結果が正常だが、DB更新に失敗した場合、ワーニング情報を設定したレスポンスを返す
        return IfaCommonUtil.createDataList(List.of(responseData), ErrorLevel.WARNING,
                MESSAGE_POST_ORDER_EXECUTION_WARNING, IfaCommonUtil.getMessage(MESSAGE_POST_ORDER_EXECUTION_WARNING));
    }
}
