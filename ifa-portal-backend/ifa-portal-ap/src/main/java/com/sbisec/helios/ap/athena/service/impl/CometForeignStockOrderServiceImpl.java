package com.sbisec.helios.ap.athena.service.impl;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.OkHttpResponse;
import com.sbisec.helios.ap.athena.enums.BuySell;
import com.sbisec.helios.ap.athena.enums.CountryCode;
import com.sbisec.helios.ap.athena.enums.MarketCode;
import com.sbisec.helios.ap.athena.enums.OrderDateType;
import com.sbisec.helios.ap.athena.enums.OrderLimit;
import com.sbisec.helios.ap.athena.enums.OrderPriceKind;
import com.sbisec.helios.ap.athena.enums.SettlementMethod;
import com.sbisec.helios.ap.athena.enums.SpecificAccount;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.Order;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.OrderInput;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateForeignStockOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateForeignStockOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockCreatedOrderInitializationReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockCreatedOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedOrderInitializationReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockOrderDetailReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockOrderDetailResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockRuTickSizeReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockRuTickSizeResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ListForeignStockOrdersReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ListForeignStockOrdersResp;
import com.sbisec.helios.ap.athena.service.AbstractBaseService;
import com.sbisec.helios.ap.athena.service.CometForeignStockOrderService;
import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.athena.utils.CometApiUtil;
import com.sbisec.helios.ap.common.enums.ForeignStockTradeClass;
import com.sbisec.helios.ap.common.enums.SellBuyType;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.DateFormatUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 外国株式取引サービス Service implements
 *
 * @author shuchen.xin
 * @date 01/12/2022
 */
@Service
public class CometForeignStockOrderServiceImpl extends AbstractBaseService implements CometForeignStockOrderService {
    
    private static final Logger LOG = LoggerFactory.getLogger(CometForeignStockOrderServiceImpl.class);
    
    @Autowired
    private CodeListService codeListService;
    
    /** APIタイプ：Athena */
    private static final String ATHENA = "Athena";
    
    /** 区分.選択可能価格条件 */
    private static final String SELECT_ABLE_PRICE_CONDITIONS = "SELECT_ABLE_PRICE_CONDITIONS";
    
    /** 区分.預り区分（外国） */
    private static final String FOREIGN_DEPOSIT_TYPE = "FOREIGN_DEPOSIT_TYPE";
    
    /** 区分.決済区分 */
    private static final String SETTLEMENT_TYPE = "SETTLEMENT_TYPE";
    
    /** 区分.注文状況（外株委託） */
    private static final String FOREIGN_STOCK_ENTRUST_ORDER_STATUS = "FOREIGN_STOCK_ENTRUST_ORDER_STATUS";
    
    /** 区分.約定状況（外株株式） */
    private static final String FOREIGN_STOCK_TRADE_STATUS = "FOREIGN_STOCK_TRADE_STATUS";
    
    /** 区分.手数料適用区分 */
    private static final String COMMISSION_APPLICATION_TYPE = "COMMISSION_APPLICATION_TYPE";
    
    /** 区分ID:決済方法　*/
    private static final String CODE_ID_SETTLEMENT_TYPE = "SETTLEMENT_TYPE";
    
    /** 区分ID:選択可能価格条件　*/
    private static final String CODE_ID_SELECT_ABLE_PRICE_CONDITIONS = "SELECT_ABLE_PRICE_CONDITIONS";
    
    /** 区分ID:預り区分(外国)　*/
    private static final String CODE_ID_FOREIGN_DEPOSIT_TYPE = "FOREIGN_DEPOSIT_TYPE";
    
    /** 区分ID:期間条件　*/
    private static final String CODE_ID_PERIOD_CONDITIONS = "PERIOD_CONDITIONS";
    
    /** 区分ID:売買区分　*/
    private static final String CODE_ID_SELL_BUY_TYPE = "SELL_BUY_TYPE";
    
    @Override
    public ListForeignStockOrdersResp listForeignStockOrders(ListForeignStockOrdersReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockOrderServiceImpl.listForeignStockOrders : {}", hashCode());
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request non empty check.
            if (null == request) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // Token non empty check
            String token = request.getHeader().getToken();
            if (StringUtil.isNullOrEmpty(token)) {
                warnMsg = "Token is null or empty!";
                break;
            }
            if (!checkToken(token)) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            
            // CountryCode exist check
            String countryCode = request.getParameter().getCountryCode();
            if (StringUtil.isNullOrEmpty(countryCode)) {
                warnMsg = "CountryCode is null or empty!";
                break;
            }
            if (null == CountryCode.getById(countryCode)) {
                warnMsg = "CountryCode " + countryCode + " is not exists!";
                break;
            }
            
            // OrderDateFrom non empty check
            String orderDateFrom = request.getParameter().getOrderDateFrom();
            if (StringUtil.isNullOrEmpty(orderDateFrom)) {
                warnMsg = "OrderDateFrom is null or empty!";
                break;
            }
            if (!DateUtil.isParsable(orderDateFrom, FORMAT_YEAR_MONTH_DAY_DASH)) {
                warnMsg = "OrderDateFrom " + orderDateFrom + " format is illegal!";
                break;
            }
            // OrderDateTo non empty check
            String orderDateTo = request.getParameter().getOrderDateTo();
            if (StringUtil.isNullOrEmpty(orderDateTo)) {
                warnMsg = "OrderDateTo is null or empty!";
                break;
            }
            if (!DateUtil.isParsable(orderDateTo, FORMAT_YEAR_MONTH_DAY_DASH)) {
                warnMsg = "OrderDateTo " + orderDateTo + " format is illegal!";
                break;
            }
            // OrderDateType exist check
            String orderDateType = request.getParameter().getOrderDateType();
            if (StringUtil.isNullOrEmpty(orderDateType)) {
                warnMsg = "OrderDateType is null or empty!";
                break;
            }
            if (null == OrderDateType.getById(orderDateType)) {
                warnMsg = "OrderDateType " + orderDateType + " is not exists!";
                break;
            }
            
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.getFs_order_orders());
        
        // get要求を送信
        OkHttpResponse httpResp = this.get(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        ListForeignStockOrdersResp resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(ListForeignStockOrdersResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        /* 外部コード→内部コード変換 */
        if (CollectionUtils.isNotEmpty(resp.getOrderDecodes())) {
            for (int i = 0; i < resp.getOrderDecodes().size(); i++) {
                // 価格条件
                if (!StringUtil.isNullOrEmpty(resp.getOrderDecodes().get(i).getOrderPriceKindCode())) {
                    String convOrderPriceKindCode = codeListService.convertExtKeyToKey(SELECT_ABLE_PRICE_CONDITIONS,
                            ATHENA, resp.getOrderDecodes().get(i).getOrderPriceKindCode());
                    resp.getOrderDecodes().get(i).setOrderPriceKindCode(convOrderPriceKindCode);
                }
                
                // 預り区分
                if (!StringUtil.isNullOrEmpty(resp.getOrderDecodes().get(i).getSpecificAccountCode())) {
                    String convSpecificAccountCode = codeListService.convertExtKeyToKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                            resp.getOrderDecodes().get(i).getSpecificAccountCode());
                    resp.getOrderDecodes().get(i).setSpecificAccountCode(convSpecificAccountCode);
                }
                
                // 決済方法
                if (!StringUtil.isNullOrEmpty(resp.getOrderDecodes().get(i).getSettlementMethodCode())) {
                    String convSettlementMethodCode = codeListService.convertExtKeyToKey(SETTLEMENT_TYPE, ATHENA,
                            resp.getOrderDecodes().get(i).getSettlementMethodCode());
                    resp.getOrderDecodes().get(i).setSettlementMethodCode(convSettlementMethodCode);
                }
                // 注文状況
                if (!StringUtil.isNullOrEmpty(resp.getOrderDecodes().get(i).getOrderStatus())) {
                    String convOrderStatus = codeListService.convertExtKeyToKey(FOREIGN_STOCK_ENTRUST_ORDER_STATUS,
                            ATHENA, resp.getOrderDecodes().get(i).getOrderStatus());
                    resp.getOrderDecodes().get(i).setOrderStatus(convOrderStatus);
                }
                // 約定状況
                if (!StringUtil.isNullOrEmpty(resp.getOrderDecodes().get(i).getExecutionStatus())) {
                    String convExecutionStatus = codeListService.convertExtKeyToKey(FOREIGN_STOCK_TRADE_STATUS, ATHENA,
                            resp.getOrderDecodes().get(i).getExecutionStatus());
                    resp.getOrderDecodes().get(i).setExecutionStatus(convExecutionStatus);
                }
                // 手数料適用区分
                if (!StringUtil.isNullOrEmpty(resp.getOrderDecodes().get(i).getCommissionType())) {
                    String convCommissionType = codeListService.convertExtKeyToKey(COMMISSION_APPLICATION_TYPE, ATHENA,
                            resp.getOrderDecodes().get(i).getCommissionType());
                    resp.getOrderDecodes().get(i).setCommissionType(convCommissionType);
                }
                
            }
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返します
        return resp;
    }
    
    /**
     * 外国株式取引サービス - 外国株式現物注文初期情報取得API.
     *
     * @param request Httpリクエスト
     * @return 外国株式現物注文初期情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.GetForeignStockCreatedOrderInitializationReq
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.GetForeignStockCreatedOrderInitializationResp
     */
    @Override
    public GetForeignStockCreatedOrderInitializationResp getForeignStockCreatedOrderInitialization(
            GetForeignStockCreatedOrderInitializationReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockOrderServiceImpl.getForeignStockCreatedOrderInitialization : {}", hashCode());
        }
        
        // 売買区分を設定する
        if (StringUtils.equals(request.getParameter().getBuySellCode(), ForeignStockTradeClass.SPOT_BUY.getId())) {
            request.getParameter().setBuySellCode(
                    codeListService.convertKeyToExtKey(CODE_ID_SELL_BUY_TYPE, ATHENA, SellBuyType.BUY.getId()));
        } else if (StringUtils.equals(request.getParameter().getBuySellCode(),
                ForeignStockTradeClass.SPOT_SELL.getId())) {
            request.getParameter().setBuySellCode(
                    codeListService.convertKeyToExtKey(CODE_ID_SELL_BUY_TYPE, ATHENA, SellBuyType.SELL.getId()));
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request non empty check.
            if (null == request) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // CountryCode check
            String countryCode = request.getParameter().getCountryCode();
            if (StringUtil.isNullOrEmpty(countryCode)) {
                warnMsg = "CountryCode is null or empty!";
                break;
            }
            if (null == CountryCode.getById(countryCode)) {
                warnMsg = "CountryCode is not exists!";
                break;
            }

            // BuySellCode check
            String buySellCode = request.getParameter().getBuySellCode();
            if (StringUtil.isNullOrEmpty(buySellCode)) {
                warnMsg = "BuySellCode is null or empty!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.getFs_order_orders_get_created_order_initialization());
        
        // get要求を送信
        OkHttpResponse httpResp = this.get(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        GetForeignStockCreatedOrderInitializationResp resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(GetForeignStockCreatedOrderInitializationResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        // 選択可能預り区分リストの区分値を変換する
        resp.setSpecificAccountCodes(resp
                .getSpecificAccountCodes().stream().map(specificAccountCode -> codeListService
                        .convertExtKeyToKey(CODE_ID_FOREIGN_DEPOSIT_TYPE, ATHENA, specificAccountCode))
                .collect(Collectors.toList()));
        
        // 選択可能価格条件リストの区分値を変換する
        resp.setOrderPriceKindCodes(resp
                .getOrderPriceKindCodes().stream().map(orderPriceKind -> codeListService
                        .convertExtKeyToKey(CODE_ID_SELECT_ABLE_PRICE_CONDITIONS, ATHENA, orderPriceKind))
                .collect(Collectors.toList()));
        
        // 売却可能数の預り区分の区分値を変換する
        resp.getSellPossibleQuantities()
                .forEach(sellPossibleQuantity -> sellPossibleQuantity
                        .setSpecificAccountCode(codeListService.convertExtKeyToKey(CODE_ID_FOREIGN_DEPOSIT_TYPE, ATHENA,
                                sellPossibleQuantity.getSpecificAccountCode())));
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返します
        return resp;
    }
    
    /**
     * 外国株式取引サービス - 外国株式ロシア株呼値情報取得API.
     *
     * @param request Httpリクエスト
     * @return 外国株式ロシア株呼値情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.GetForeignStockRuTickSizeReq
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.GetForeignStockRuTickSizeResp
     */
    @Override
    public GetForeignStockRuTickSizeResp getForeignStockRuTickSize(GetForeignStockRuTickSizeReq request)
            throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockOrderServiceImpl.getForeignStockRuTickSize : {}", hashCode());
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request non empty check.
            if (null == request) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // SecuritiesCode check
            String securitiesCode = request.getParameter().getSecuritiesCode();
            if (StringUtil.isNullOrEmpty(securitiesCode)) {
                warnMsg = "SecuritiesCode is null or empty!";
                break;
            }
            
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.getFs_order_countries_ru_ticksizes_securities());
        
        // get要求を送信
        OkHttpResponse httpResp = this.get(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        GetForeignStockRuTickSizeResp resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(GetForeignStockRuTickSizeResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返します
        return resp;
    }
    
    /**
     * 外国株式取引サービス - 外国株式現物注文確認API.
     *
     * @param request Httpリクエスト
     * @return 外国株式現物注文確認情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedOrderReq
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedOrderResp
     */
    @Override
    public ConfirmForeignStockCreatedOrderResp confirmForeignStockCreatedOrder(
            ConfirmForeignStockCreatedOrderReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockOrderServiceImpl.confirmForeignStockCreatedOrder : {}", hashCode());
        }
        
        // 注文入力情報の区分値及び日付フォーマットを変換する
        ConfirmForeignStockCreatedOrderReq.Parameter orderInput = request.getParameter();
        
        // 売買区分
        if (StringUtils.equals(orderInput.getBuySellCode(), ForeignStockTradeClass.SPOT_BUY.getId())) {
            orderInput.setBuySellCode(
                    codeListService.convertKeyToExtKey(CODE_ID_SELL_BUY_TYPE, ATHENA, SellBuyType.BUY.getId()));
        } else if (StringUtils.equals(orderInput.getBuySellCode(), ForeignStockTradeClass.SPOT_SELL.getId())) {
            orderInput.setBuySellCode(
                    codeListService.convertKeyToExtKey(CODE_ID_SELL_BUY_TYPE, ATHENA, SellBuyType.SELL.getId()));
        }
        
        // 価格条件
        orderInput.setOrderPriceKindCode(codeListService.convertKeyToExtKey(CODE_ID_SELECT_ABLE_PRICE_CONDITIONS,
                ATHENA, orderInput.getOrderPriceKindCode()));
        
        // 期間条件
        orderInput.setOrderLimitCode(
                codeListService.convertKeyToExtKey(CODE_ID_PERIOD_CONDITIONS, ATHENA, orderInput.getOrderLimitCode()));
        
        // 期間
        orderInput.setOrderTerm(DateFormatUtil.dateFormatToHyphen(orderInput.getOrderTerm()));
        
        // 預り区分
        orderInput.setSpecificAccountCode(codeListService.convertKeyToExtKey(CODE_ID_FOREIGN_DEPOSIT_TYPE, ATHENA,
                orderInput.getSpecificAccountCode()));
        
        // 決済方法
        orderInput.setSettlementMethodCode(codeListService.convertKeyToExtKey(CODE_ID_SETTLEMENT_TYPE, ATHENA,
                orderInput.getSettlementMethodCode()));
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request non empty check.
            if (null == request) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // headerを設定する
            ConfirmForeignStockCreatedOrderReq.Header header = request.getHeader();
            // 必須入力チェック「token」
            if (StringUtil.isNullOrEmpty(header.getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            // tokenの正確性チェックを行う。
            if (!checkToken(header.getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            
            // 必須入力チェック「channel」
            if (StringUtil.isNullOrEmpty(header.getChannel())) {
                warnMsg = "Channel is null or empty!";
                break;
            }
            
            // parameterを設定する
            ConfirmForeignStockCreatedOrderReq.Parameter parameter = request.getParameter();
            // 必須入力チェック「CountryCode」
            String countryCode = parameter.getCountryCode();
            if (StringUtil.isNullOrEmpty(countryCode)) {
                warnMsg = "CountryCode is null or empty!";
                break;
            }
            if (null == CountryCode.getById(countryCode)) {
                warnMsg = "CountryCode is not exists!";
                break;
            }
            // 必須入力チェック「marketCode」
            String marketCode = parameter.getMarketCode();
            if (StringUtil.isNullOrEmpty(marketCode)) {
                warnMsg = "MarketCode is null or empty!";
                break;
            }
            if (null == MarketCode.getById(marketCode)) {
                warnMsg = "MarketCode is not exists!";
                break;
            }
            // 必須入力チェック「securitiesCode」
            String securitiesCode = parameter.getSecuritiesCode();
            if (StringUtil.isNullOrEmpty(securitiesCode)) {
                warnMsg = "SecuritiesCode is null or empty!";
                break;
            }
            // 桁数チェック「securitiesCodeコード」
            if (SECURITIESCODE_MAX_LENGTH < securitiesCode.length()) {
                warnMsg = "SecuritiesCode " + securitiesCode + " must be " + SECURITIESCODE_MAX_LENGTH + " digits!";
                break;
            }
            
            // 必須入力チェック「buySellCode」
            String buySellCode = parameter.getBuySellCode();
            if (StringUtil.isNullOrEmpty(buySellCode)) {
                warnMsg = "BuySellCode is null or empty!";
                break;
            }
            if (null == BuySell.getById(buySellCode)) {
                warnMsg = "BuySellCode is not exists!";
                break;
            }
            // 必須入力チェック「orderQuantity」
            String orderQuantity = parameter.getOrderQuantity();
            if (StringUtil.isNullOrEmpty(orderQuantity)) {
                warnMsg = "OrderQuantity is null or empty!";
                break;
            }
            // 桁数チェック:0-9999999999「orderQuantity」
            if (!checkRange(new BigDecimal(orderQuantity), AMOUNT_MIN_VALUE, AMOUNT_MAX_VALUE)) {
                warnMsg = "OrderQuantity " + orderQuantity + " is out of this range!";
                break;
            }
            
            // 必須入力チェック「orderPriceKindCode」
            String orderPriceKindCode = parameter.getOrderPriceKindCode();
            if (StringUtil.isNullOrEmpty(orderPriceKindCode)) {
                warnMsg = "OrderPriceKindCode is null or empty!";
                break;
            }
            if (null == OrderPriceKind.getById(orderPriceKindCode)) {
                warnMsg = "OrderPriceKindCode is not exists!";
                break;
            }
            
            // サイズ範囲:0-999999999.99999999「orderPrice」
            String orderPrice = parameter.getOrderPrice();
            if (!StringUtil.isNullOrEmpty(orderPrice)
                    && !checkRange(new BigDecimal(orderPrice), ORDERPRICE_MIN_VALUE, ORDERPRICE_MAX_VALUE)) {
                warnMsg = "OrderPrice " + orderPrice + " is out of this range!";
                break;
            }
            
            // サイズ範囲:0-999999999.99999999「stopPrice」
            String stopPrice = parameter.getStopPrice();
            if (!StringUtil.isNullOrEmpty(stopPrice)
                    && !checkRange(new BigDecimal(stopPrice), STOPPRICE_MIN_VALUE, STOPPRICE_MAX_VALUE)) {
                warnMsg = "StopPrice " + stopPrice + " is out of this range!";
                break;
            }
            
            // サイズ範囲:0-9999999999999.9999「trailingStopAmount」
            String trailingStopAmount = parameter.getTrailingStopAmount();
            if (!StringUtil.isNullOrEmpty(trailingStopAmount) && !checkRange(new BigDecimal(trailingStopAmount),
                    TRAILINGSTOPAMOUNT_MIN_VALUE, TRAILINGSTOPAMOUNT_MAX_VALUE)) {
                warnMsg = "TrailingStopAmount " + trailingStopAmount + " is out of this range!";
                break;
            }
            
            // 必須入力チェック「orderLimitCode」
            String orderLimitCode = parameter.getOrderLimitCode();
            if (StringUtil.isNullOrEmpty(orderLimitCode)) {
                warnMsg = "OrderLimitCode is null or empty!";
                break;
            }
            if (null == OrderLimit.getById(orderLimitCode)) {
                warnMsg = "OrderLimitCode is not exists!";
                break;
            }
            
            // フォーマットチェック「orderTerm」
            String orderTerm = parameter.getOrderTerm();
            if (!StringUtil.isNullOrEmpty(orderTerm) && !DateUtil.isParsable(orderTerm, FORMAT_YEAR_MONTH_DAY_DASH)) {
                warnMsg = "OrderTerm " + orderTerm + " format is illegal!";
            }
            
            // 必須入力チェック「specificAccountCode」
            String specificAccountCode = parameter.getSpecificAccountCode();
            if (StringUtil.isNullOrEmpty(specificAccountCode)) {
                warnMsg = "SpecificAccountCode is null or empty!";
                break;
            }
            if (null == SpecificAccount.getById(specificAccountCode)) {
                warnMsg = "SpecificAccountCode is not exists!";
                break;
            }
            // 必須入力チェック「settlementMethodCode」
            String settlementMethodCode = parameter.getSettlementMethodCode();
            if (StringUtil.isNullOrEmpty(settlementMethodCode)) {
                warnMsg = "SettlementMethodCode is null or empty!";
                break;
            }
            if (null == SettlementMethod.getById(settlementMethodCode)) {
                warnMsg = "SettlementMethodCode is not exists!";
                break;
            }
            
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.getFs_confirm_created_order());
        
        // post要求を送信
        OkHttpResponse httpResp = this.post(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        ConfirmForeignStockCreatedOrderResp resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(ConfirmForeignStockCreatedOrderResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 注文情報の区分値を変換する
        Order respOrder = resp.getOrder();
        
        // 売買区分
        respOrder.setBuySellCode(
                codeListService.convertExtKeyToKey(CODE_ID_SELL_BUY_TYPE, ATHENA, respOrder.getBuySellCode()));
        
        // 価格条件
        respOrder.setOrderPriceKindCode(codeListService.convertExtKeyToKey(CODE_ID_SELECT_ABLE_PRICE_CONDITIONS, ATHENA,
                respOrder.getOrderPriceKindCode()));
        
        // 期間条件
        respOrder.setOrderLimitCode(
                codeListService.convertExtKeyToKey(CODE_ID_PERIOD_CONDITIONS, ATHENA, respOrder.getOrderLimitCode()));
        
        // 預り区分
        respOrder.setSpecificAccountCode(codeListService.convertExtKeyToKey(CODE_ID_FOREIGN_DEPOSIT_TYPE, ATHENA,
                respOrder.getSpecificAccountCode()));
        
        // 決済区分
        respOrder.setSettlementMethodCode(codeListService.convertExtKeyToKey(CODE_ID_SETTLEMENT_TYPE, ATHENA,
                respOrder.getSettlementMethodCode()));
        
        // 結果を返します
        return resp;
    }
    
    /**
     * 外国株式取引サービス - 外国株式現物注文登録API.
     * 
     * @param request Httpリクエスト
     * @return 外国株式現物注文登録情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.CreateForeignStockOrderResp
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.CreateForeignStockOrderReq
     */
    @Override
    public CreateForeignStockOrderResp createForeignStockOrder(CreateForeignStockOrderReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockOrderServiceImpl.createForeignStockOrder : {}", hashCode());
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request non empty check.
            if (null == request) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.getFs_created_order());
        
        // post要求を送信
        OkHttpResponse httpResp = this.post(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        CreateForeignStockOrderResp resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(CreateForeignStockOrderResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        // 注文情報の区分値を変換する
        Order respOrder = resp.getOrder();
        
        // 売買区分
        respOrder.setBuySellCode(
                codeListService.convertExtKeyToKey(CODE_ID_SELL_BUY_TYPE, ATHENA, respOrder.getBuySellCode()));
        
        // 価格条件
        respOrder.setOrderPriceKindCode(codeListService.convertExtKeyToKey(CODE_ID_SELECT_ABLE_PRICE_CONDITIONS, ATHENA,
                respOrder.getOrderPriceKindCode()));
        
        // 期間条件
        respOrder.setOrderLimitCode(
                codeListService.convertExtKeyToKey(CODE_ID_PERIOD_CONDITIONS, ATHENA, respOrder.getOrderLimitCode()));
        
        // 預り区分
        respOrder.setSpecificAccountCode(codeListService.convertExtKeyToKey(CODE_ID_FOREIGN_DEPOSIT_TYPE, ATHENA,
                respOrder.getSpecificAccountCode()));
        
        // 決済方法
        respOrder.setSettlementMethodCode(codeListService.convertExtKeyToKey(CODE_ID_SETTLEMENT_TYPE, ATHENA,
                respOrder.getSettlementMethodCode()));
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返します
        return resp;
    }

    @Override
    public GetForeignStockOrderDetailResp getForeignStockOrderDetail(
            GetForeignStockOrderDetailReq getForeignStockOrderDetailReq) throws Exception {
        long start = System.currentTimeMillis();

        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockOrderServiceImpl.getForeignStockOrderDetail : {}", hashCode());
        }
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {

            // 「リクエスト」：空のチェック
            if (null == getForeignStockOrderDetailReq) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }

            // 必須入力チェック「Token」
            String token = getForeignStockOrderDetailReq.getHeader().getToken();
            if (StringUtil.isNullOrEmpty(token)) {
                warnMsg = "Token is null or empty!";
                break;
            }

            // 「Token」：正確性チェック
            if (!checkToken(token)) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }

            // 必須入力チェック「注文番号」
            String orderNo = getForeignStockOrderDetailReq.getParameter().getOrderNo();
            if (StringUtil.isNullOrEmpty(orderNo)) {
                warnMsg = "orderNo is null or empty!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }

        // APIのパースを設定する
        String url = this.getUrl(CometApiUtil.getFs_order_orders_orderNo_getDetail());
        // 検索処理を行って、結果を設定する
        OkHttpResponse httpResp = this.get(url, getForeignStockOrderDetailReq);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // APIの返す結果集を新規作成する
        GetForeignStockOrderDetailResp resp = null;
        try {
            // APIの返す結果を利用して、GetForeignStockOrderDetailRespを設定する
            resp = httpResp.getResponseData(GetForeignStockOrderDetailResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }

        // 結果を返します
        return resp;
    }
    
    /**
     * 外国株式取引サービス - 外国株式現物注文取消初期情報取得API.
     *
     * @param request Httpリクエスト
     * @return 外国株式現物注文取消初期情報
     * @throws Exception 異常
     * 
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedOrderInitializationReq
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedOrderInitializationResp
     */
    @Override
    public GetForeignStockDeletedOrderInitializationResp getForeignStockDeletedOrderInitialization(
            GetForeignStockDeletedOrderInitializationReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockOrderServiceImpl.getForeignStockDeletedOrderInitialization : {}", hashCode());
        }
        
        // パラメータチェックメッセージ
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // 「リクエスト」：空のチェック
            if (null == request) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // 「Token」：必須入力チェック、正確性チェック
            if (StringUtil.isNullOrEmpty(request.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            if (!checkToken(request.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            
            // 「注文Sub番号」：必須入力チェック
            if (StringUtil.isNullOrEmpty(request.getParameter().getOrderSubNo())) {
                warnMsg = "OrderSubNo is null or empty!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        // APIのURLを設定する。
        String url = this.getUrl(CometApiUtil.getFs_get_foreign_deleted_initialization());
        
        // GET請求を送信する。
        OkHttpResponse httpResp = this.get(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // 設定応答メッセージ
        GetForeignStockDeletedOrderInitializationResp resp = null;
        try {
            // 文字列をエンティティBeanに変換する
            resp = httpResp.getResponseData(GetForeignStockDeletedOrderInitializationResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        // 注文情報の区分値を変換する。
        Order respOrder = resp.getOrder();
        
        // 売買区分
        respOrder.setBuySellCode(
                codeListService.convertExtKeyToKey(CODE_ID_SELL_BUY_TYPE, ATHENA, respOrder.getBuySellCode()));
        
        // 価格条件
        respOrder.setOrderPriceKindCode(codeListService.convertExtKeyToKey(CODE_ID_SELECT_ABLE_PRICE_CONDITIONS, ATHENA,
                respOrder.getOrderPriceKindCode()));
        
        // 期間条件
        respOrder.setOrderLimitCode(
                codeListService.convertExtKeyToKey(CODE_ID_PERIOD_CONDITIONS, ATHENA, respOrder.getOrderLimitCode()));
        
        // 預り区分
        respOrder.setSpecificAccountCode(codeListService.convertExtKeyToKey(CODE_ID_FOREIGN_DEPOSIT_TYPE, ATHENA,
                respOrder.getSpecificAccountCode()));
        
        // 決済方法
        respOrder.setSettlementMethodCode(codeListService.convertExtKeyToKey(CODE_ID_SETTLEMENT_TYPE, ATHENA,
                respOrder.getSettlementMethodCode()));
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返却する。
        return resp;
    }
    
    /**
     * 外国株式取引サービス - 外国株式現物注文取消登録API.
     *
     * @param request Httpリクエスト
     * @return 外国株式現物注文取消登録レスポンス
     * @throws Exception 異常
     * 
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockOrderReq
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockOrderResp
     */
    public DeleteForeignStockOrderResp deleteForeignStockOrder(DeleteForeignStockOrderReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockOrderServiceImpl.deleteForeignStockOrder : {}", hashCode());
        }
        
        // パラメータチェックメッセージ
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // 「リクエスト」：空のチェック
            if (null == request) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // 「Token」：必須入力チェック、正確性チェック
            if (StringUtil.isNullOrEmpty(request.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            if (!checkToken(request.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            
            // 「注文Sub番号」：必須入力チェック
            if (StringUtil.isNullOrEmpty(request.getParameter().getOrderSubNo())) {
                warnMsg = "OrderSubNo is null or empty!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        // APIのURLを設定する。
        String url = this.getUrl(CometApiUtil.getFs_delete_foreign_stock_order());
        
        // DELETEリクエストを送信する。
        OkHttpResponse httpResp = this.delete(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // 設定応答メッセージ
        DeleteForeignStockOrderResp resp = null;
        try {
            // 文字列をエンティティBeanに変換する
            resp = httpResp.getResponseData(DeleteForeignStockOrderResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        // 注文情報の区分値を変換する。
        Order respOrder = resp.getOrder();
        
        // 売買区分
        respOrder.setBuySellCode(
                codeListService.convertExtKeyToKey(CODE_ID_SELL_BUY_TYPE, ATHENA, respOrder.getBuySellCode()));
        
        // 価格条件
        respOrder.setOrderPriceKindCode(codeListService.convertExtKeyToKey(CODE_ID_SELECT_ABLE_PRICE_CONDITIONS, ATHENA,
                respOrder.getOrderPriceKindCode()));
        
        // 期間条件
        respOrder.setOrderLimitCode(
                codeListService.convertExtKeyToKey(CODE_ID_PERIOD_CONDITIONS, ATHENA, respOrder.getOrderLimitCode()));
        
        // 預り区分
        respOrder.setSpecificAccountCode(codeListService.convertExtKeyToKey(CODE_ID_FOREIGN_DEPOSIT_TYPE, ATHENA,
                respOrder.getSpecificAccountCode()));
        
        // 決済方法
        respOrder.setSettlementMethodCode(codeListService.convertExtKeyToKey(CODE_ID_SETTLEMENT_TYPE, ATHENA,
                respOrder.getSettlementMethodCode()));
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返却する。
        return resp;
    }
}
