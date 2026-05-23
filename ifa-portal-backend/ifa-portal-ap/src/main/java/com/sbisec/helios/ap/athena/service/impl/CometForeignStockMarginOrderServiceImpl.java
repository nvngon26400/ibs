package com.sbisec.helios.ap.athena.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.OkHttpResponse;
import com.sbisec.helios.ap.athena.enums.BuySell;
import com.sbisec.helios.ap.athena.enums.ClosePositionKind;
import com.sbisec.helios.ap.athena.enums.CountryCode;
import com.sbisec.helios.ap.athena.enums.DepositType;
import com.sbisec.helios.ap.athena.enums.MarginCloseLimitType;
import com.sbisec.helios.ap.athena.enums.MarketCode;
import com.sbisec.helios.ap.athena.enums.OrderLimit;
import com.sbisec.helios.ap.athena.enums.OrderPriceKind;
import com.sbisec.helios.ap.athena.enums.SettlementMethod;
import com.sbisec.helios.ap.athena.enums.SpecificAccount;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.ClosedPositionInput;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginOrderInputForConfirmForeignStockClosedMarginOrder;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginTradeRepayOrderConfirmForCloseForeignStockMarginOrder;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CloseForeignStockMarginOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CloseForeignStockMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockClosedMarginOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockClosedMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedMarginOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateForeignStockMarginOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateForeignStockMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateMarginAccountAutoTransferSettingReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateMarginAccountAutoTransferSettingResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockMarginOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockCreatedMarginOrderInitializationReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockCreatedMarginOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedMarginOrderInitializationReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedMarginOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockMarginOrderDetailReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockMarginOrderDetailResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginAccountAutoTransferSettingReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginAccountAutoTransferSettingResp;
import com.sbisec.helios.ap.athena.service.AbstractBaseService;
import com.sbisec.helios.ap.athena.service.CometForeignStockMarginOrderService;
import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.athena.utils.CometApiUtil;
import com.sbisec.helios.ap.common.service.CodeListService;

/**
 * 外国株式信用 Service implements.
 * 
 * @author mengzhe.li
 * @date 02/10/2022
 */
@Service
public class CometForeignStockMarginOrderServiceImpl extends AbstractBaseService
        implements CometForeignStockMarginOrderService {
    
    private static final Logger LOG = LoggerFactory.getLogger(CometForeignStockMarginOrderServiceImpl.class);
    
    @Autowired
    private CodeListService codeListService;
    
    /** APIタイプ：Athena */
    private static final String ATHENA = "Athena";
    
    /** 区分.売買区分 */
    private static final String SELL_BUY_TYPE = "SELL_BUY_TYPE";
    
    /** 区分.選択可能価格条件 */
    private static final String SELECT_ABLE_PRICE_CONDITIONS = "SELECT_ABLE_PRICE_CONDITIONS";
    
    /** 区分.期間条件 */
    private static final String PERIOD_CONDITIONS = "PERIOD_CONDITIONS";
    
    /** 区分.預り区分（外国） */
    private static final String FOREIGN_DEPOSIT_TYPE = "FOREIGN_DEPOSIT_TYPE";
    
    /** 区分.決済区分 */
    private static final String SETTLEMENT_TYPE = "SETTLEMENT_TYPE";
    
    /** 区分.信用期日 */
    private static final String MARGIN_DUE_DATE = "MARGIN_DUE_DATE";
    
    /** 区分.返済方法（外国） */
    private static final String FOREIGN_REPAY_METHOD = "FOREIGN_REPAY_METHOD";
    
    /** 区分.返済順序（外国） */
    private static final String FOREIGN_REPAY_ORDER = "FOREIGN_REPAY_ORDER";
    
    @Override
    public DeleteForeignStockMarginOrderResp deleteForeignStockMarginOrder(DeleteForeignStockMarginOrderReq request)
            throws Exception {
        
        long start = System.currentTimeMillis();
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockMarginOrderServiceImpl.deleteForeignStockMarginOrder : {}", hashCode());
        }
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            if (request == null) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // headerを設定する
            DeleteForeignStockMarginOrderReq.Header header = request.getHeader();
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
            
            // パラメータを設定する
            DeleteForeignStockMarginOrderReq.Parameter parameter = request.getParameter();
            // 必須入力チェック「注文Sub番号」
            if (StringUtil.isNullOrEmpty(parameter.getOrderSubNo())) {
                warnMsg = "OrderSubNo is null or empty!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        // APIのURLを設定する
        String url = this.getUrl(CometApiUtil.getFs_delete_foreign_margin_order());
        // DELETE請求を送信する
        OkHttpResponse httpResp = this.delete(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        DeleteForeignStockMarginOrderResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返します。
            resp = httpResp.getResponseData(DeleteForeignStockMarginOrderResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        /* 外部コード→内部コード変換 */
        // 売買区分
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getBuySellCode())) {
            String convBuySellCode = codeListService.convertExtKeyToKey(SELL_BUY_TYPE, ATHENA,
                    resp.getMarginOrder().getOrder().getBuySellCode());
            resp.getMarginOrder().getOrder().setBuySellCode(convBuySellCode);
        }
        // 価格条件
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getOrderPriceKindCode())) {
            String convOrderPriceKindCode = codeListService.convertExtKeyToKey(SELECT_ABLE_PRICE_CONDITIONS, ATHENA,
                    resp.getMarginOrder().getOrder().getOrderPriceKindCode());
            resp.getMarginOrder().getOrder().setOrderPriceKindCode(convOrderPriceKindCode);
        }
        // 期間条件
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getOrderLimitCode())) {
            String convOrderLimitCode = codeListService.convertExtKeyToKey(PERIOD_CONDITIONS, ATHENA,
                    resp.getMarginOrder().getOrder().getOrderLimitCode());
            resp.getMarginOrder().getOrder().setOrderLimitCode(convOrderLimitCode);
        }
        // 預り区分
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getSpecificAccountCode())) {
            String convSpecificAccountCode = codeListService.convertExtKeyToKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                    resp.getMarginOrder().getOrder().getSpecificAccountCode());
            resp.getMarginOrder().getOrder().setSpecificAccountCode(convSpecificAccountCode);
        }
        // 決済方法
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getSettlementMethodCode())) {
            String convSettlementMethodCode = codeListService.convertExtKeyToKey(SETTLEMENT_TYPE, ATHENA,
                    resp.getMarginOrder().getOrder().getSettlementMethodCode());
            resp.getMarginOrder().getOrder().setSettlementMethodCode(convSettlementMethodCode);
        }
        // 返済建玉指定方法
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getClosePositionKind())) {
            String convClosePositionKind = codeListService.convertExtKeyToKey(FOREIGN_REPAY_METHOD, ATHENA,
                    resp.getMarginOrder().getClosePositionKind());
            resp.getMarginOrder().setClosePositionKind(convClosePositionKind);
        }
        // 評価益降順.返済選択順序
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getCloseSelectionSort())) {
            String convCloseSelectionSort = codeListService.convertExtKeyToKey(FOREIGN_REPAY_ORDER, ATHENA,
                    resp.getMarginOrder().getCloseSelectionSort());
            resp.getMarginOrder().setCloseSelectionSort(convCloseSelectionSort);
        }
        // 信用期日
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getMarginCloseLimitType())) {
            String convMarginCloseLimitType = codeListService.convertExtKeyToKey(MARGIN_DUE_DATE, ATHENA,
                    resp.getMarginOrder().getMarginCloseLimitType());
            resp.getMarginOrder().setMarginCloseLimitType(convMarginCloseLimitType);
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返します
        return resp;
    }
    
    @Override
    public GetForeignStockDeletedMarginOrderInitializationResp getForeignStockDeletedMarginOrderInitialization(
            GetForeignStockDeletedMarginOrderInitializationReq getForeignStockDeletedMarginOrderInitializationReq)
            throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockMarginOrderServiceImpl.getForeignStockDeletedMarginOrderInitialization : {}",
                    hashCode());
        }
        
        // パラメータチェックメッセージ
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // 「リクエスト」：空のチェック
            if (null == getForeignStockDeletedMarginOrderInitializationReq) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // 「Token」：必須入力チェック、正確性チェック
            if (StringUtil.isNullOrEmpty(getForeignStockDeletedMarginOrderInitializationReq.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            if (!checkToken(getForeignStockDeletedMarginOrderInitializationReq.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            
            // 「注文Sub番号」：必須入力チェック
            if (StringUtil
                    .isNullOrEmpty(getForeignStockDeletedMarginOrderInitializationReq.getParameter().getOrderSubNo())) {
                warnMsg = "OrderSubNo is null or empty!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        // APIのURLを設定する。
        String url = this
                .getUrl(CometApiUtil.getFs_order_margin_orders_order_sub_no_get_deleted_order_initialization());
        
        // GET請求を送信する。
        OkHttpResponse httpResp = this.get(url, getForeignStockDeletedMarginOrderInitializationReq);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // RESPONSEをインスタンスする。
        GetForeignStockDeletedMarginOrderInitializationResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返する。
            resp = httpResp.getResponseData(GetForeignStockDeletedMarginOrderInitializationResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        /* 外部コード→内部コード変換 */
        // 売買区分
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getBuySellCode())) {
            String convBuySellCode = codeListService.convertExtKeyToKey(SELL_BUY_TYPE, ATHENA,
                    resp.getMarginOrder().getOrder().getBuySellCode());
            resp.getMarginOrder().getOrder().setBuySellCode(convBuySellCode);
        }
        // 価格条件
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getOrderPriceKindCode())) {
            String convOrderPriceKindCode = codeListService.convertExtKeyToKey(SELECT_ABLE_PRICE_CONDITIONS, ATHENA,
                    resp.getMarginOrder().getOrder().getOrderPriceKindCode());
            resp.getMarginOrder().getOrder().setOrderPriceKindCode(convOrderPriceKindCode);
        }
        // 期間条件
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getOrderLimitCode())) {
            String convOrderLimitCode = codeListService.convertExtKeyToKey(PERIOD_CONDITIONS, ATHENA,
                    resp.getMarginOrder().getOrder().getOrderLimitCode());
            resp.getMarginOrder().getOrder().setOrderLimitCode(convOrderLimitCode);
        }
        // 預り区分
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getSpecificAccountCode())) {
            String convSpecificAccountCode = codeListService.convertExtKeyToKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                    resp.getMarginOrder().getOrder().getSpecificAccountCode());
            resp.getMarginOrder().getOrder().setSpecificAccountCode(convSpecificAccountCode);
        }
        // 決済方法
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getSettlementMethodCode())) {
            String convSettlementMethodCode = codeListService.convertExtKeyToKey(SETTLEMENT_TYPE, ATHENA,
                    resp.getMarginOrder().getOrder().getSettlementMethodCode());
            resp.getMarginOrder().getOrder().setSettlementMethodCode(convSettlementMethodCode);
        }
        // 返済建玉指定方法
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getClosePositionKind())) {
            String convClosePositionKind = codeListService.convertExtKeyToKey(FOREIGN_REPAY_METHOD, ATHENA,
                    resp.getMarginOrder().getClosePositionKind());
            resp.getMarginOrder().setClosePositionKind(convClosePositionKind);
        }
        // 評価益降順.返済選択順序
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getCloseSelectionSort())) {
            String convCloseSelectionSort = codeListService.convertExtKeyToKey(FOREIGN_REPAY_ORDER, ATHENA,
                    resp.getMarginOrder().getCloseSelectionSort());
            resp.getMarginOrder().setCloseSelectionSort(convCloseSelectionSort);
        }
        // 信用期日
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getMarginCloseLimitType())) {
            String convMarginCloseLimitType = codeListService.convertExtKeyToKey(MARGIN_DUE_DATE, ATHENA,
                    resp.getMarginOrder().getMarginCloseLimitType());
            resp.getMarginOrder().setMarginCloseLimitType(convMarginCloseLimitType);
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返する。
        return resp;
    }
    
    @Override
    public CreateForeignStockMarginOrderResp createForeignStockMarginOrder(CreateForeignStockMarginOrderReq request)
            throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockMarginOrderServiceImpl.createForeignStockMarginOrder : {}", hashCode());
        }
        
        // 内部コード→外部コード
        // 価格条件
        if (!StringUtil.isNullOrEmpty(request.getParameter().getOrderPriceKindCode())) {
            String orderPriceKindCode = codeListService.convertKeyToExtKey(SELECT_ABLE_PRICE_CONDITIONS, ATHENA,
                    request.getParameter().getOrderPriceKindCode());
            request.getParameter().setOrderPriceKindCode(orderPriceKindCode);
        }
        
        // 期間条件
        if (!StringUtil.isNullOrEmpty(request.getParameter().getOrderLimitCode())) {
            String orderLimitCode = codeListService.convertKeyToExtKey(PERIOD_CONDITIONS, ATHENA,
                    request.getParameter().getOrderLimitCode());
            request.getParameter().setOrderLimitCode(orderLimitCode);
        }
        
        // 預り区分
        if (!StringUtil.isNullOrEmpty(request.getParameter().getSpecificAccountCode())) {
            String specificAccountCode = codeListService.convertKeyToExtKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                    request.getParameter().getSpecificAccountCode());
            request.getParameter().setSpecificAccountCode(specificAccountCode);
        }
        
        // 決済方法
        if (!StringUtil.isNullOrEmpty(request.getParameter().getSettlementMethodCode())) {
            String settlementMethodCode = codeListService.convertKeyToExtKey(SETTLEMENT_TYPE, ATHENA,
                    request.getParameter().getSettlementMethodCode());
            request.getParameter().setSettlementMethodCode(settlementMethodCode);
        }
        
        // 信用期日
        if (!StringUtil.isNullOrEmpty(request.getParameter().getMarginCloseLimitType())) {
            String marginCloseLimitType = codeListService.convertKeyToExtKey(MARGIN_DUE_DATE, ATHENA,
                    request.getParameter().getMarginCloseLimitType());
            request.getParameter().setMarginCloseLimitType(marginCloseLimitType);
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // requestのNULLチェックを行う
            if (request == null) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // headerを設定する
            CreateForeignStockMarginOrderReq.Header header = request.getHeader();
            
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
            
            // 必須入力チェック「国コード」
            if (StringUtil.isNullOrEmpty(request.getParameter().getCountryCode())
                    || null == CountryCode.getById(request.getParameter().getCountryCode())) {
                warnMsg = "CountryCode is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「市場コード」
            if (StringUtil.isNullOrEmpty(request.getParameter().getMarketCode())
                    || null == MarketCode.getById(request.getParameter().getMarketCode())) {
                warnMsg = "MarketCode is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「銘柄コード」
            if (StringUtil.isNullOrEmpty(request.getParameter().getSecuritiesCode())) {
                warnMsg = "SecuritiesCode is null or empty!";
                break;
            }
            // 桁数チェック「銘柄コード」
            String securitiesCode = request.getParameter().getSecuritiesCode();
            if (SECURITIESCODE_MAX_LENGTH < securitiesCode.length()) {
                warnMsg = "SecuritiesCode " + securitiesCode + " must be " + SECURITIESCODE_MAX_LENGTH + " digits!";
                break;
            }
            
            // 必須入力チェック「売買区分」
            if (StringUtil.isNullOrEmpty(request.getParameter().getBuySellCode())
                    || null == BuySell.getById(request.getParameter().getBuySellCode())) {
                warnMsg = "BuySellCode is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「注文数量」
            String orderQuantity = request.getParameter().getOrderQuantity();
            if (StringUtil.isNullOrEmpty(orderQuantity)) {
                warnMsg = "OrderQuantity is null or empty!";
                break;
            }
            // 桁数チェック「注文数量」
            if (!checkRange(new BigDecimal(orderQuantity), AMOUNT_MIN_VALUE, AMOUNT_MAX_VALUE)) {
                warnMsg = "OrderQuantity " + orderQuantity + " is out of this range!";
                break;
            }
            
            // 必須入力チェック「価格条件」
            if (StringUtil.isNullOrEmpty(request.getParameter().getOrderPriceKindCode())
                    || null == OrderPriceKind.getById(request.getParameter().getOrderPriceKindCode())) {
                warnMsg = "OrderPriceKindCode is not exists or empty!";
                break;
            }
            
            // サイズ範囲:0-999999999.99999999「注文単価」
            String orderPrice = request.getParameter().getOrderPrice();
            if (!StringUtil.isNullOrEmpty(orderPrice)
                    && !checkRange(new BigDecimal(orderPrice), ORDERPRICE_MIN_VALUE, ORDERPRICE_MAX_VALUE)) {
                warnMsg = "OrderPrice " + orderPrice + " is out of this range!";
                break;
            }
            
            // サイズ範囲:0-999999999.99999999「発火条件価格」
            String stopPrice = request.getParameter().getStopPrice();
            if (!StringUtil.isNullOrEmpty(stopPrice)
                    && !checkRange(new BigDecimal(stopPrice), STOPPRICE_MIN_VALUE, STOPPRICE_MAX_VALUE)) {
                warnMsg = "StopPrice " + stopPrice + " is out of this range!";
                break;
            }
            
            // サイズ範囲:0-9999999999999.9999「トレールストップ幅」
            String trailingStopAmount = request.getParameter().getTrailingStopAmount();
            if (!StringUtil.isNullOrEmpty(trailingStopAmount) && !checkRange(new BigDecimal(trailingStopAmount),
                    TRAILINGSTOPAMOUNT_MIN_VALUE, TRAILINGSTOPAMOUNT_MAX_VALUE)) {
                warnMsg = "TrailingStopAmount " + trailingStopAmount + " is out of this range!";
                break;
            }
            
            // 必須入力チェック「期間条件」
            if (StringUtil.isNullOrEmpty(request.getParameter().getOrderLimitCode())
                    || null == OrderLimit.getById(request.getParameter().getOrderLimitCode())) {
                warnMsg = "OrderLimitCode is not exists or empty!";
                break;
            }
            
            // フォーマットチェック「期間 」
            String orderTerm = request.getParameter().getOrderTerm();
            if (!StringUtil.isNullOrEmpty(orderTerm) && !DateUtil.isParsable(orderTerm, FORMAT_YEAR_MONTH_DAY_DASH)) {
                warnMsg = "OrderTerm " + orderTerm + " format is illegal!";
                break;
            }
            
            // 必須入力チェック「預り区分」
            if (StringUtil.isNullOrEmpty(request.getParameter().getSpecificAccountCode())
                    || null == SpecificAccount.getById(request.getParameter().getSpecificAccountCode())) {
                warnMsg = "SpecificAccountCode is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「決済方法」
            if (StringUtil.isNullOrEmpty(request.getParameter().getSettlementMethodCode())
                    || null == SettlementMethod.getById(request.getParameter().getSettlementMethodCode())) {
                warnMsg = "SettlementMethodCode is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「信用期日」
            if (StringUtil.isNullOrEmpty(request.getParameter().getMarginCloseLimitType())
                    || null == MarginCloseLimitType.getById(request.getParameter().getMarginCloseLimitType())) {
                warnMsg = "MarginCloseLimitType is not exists or empty!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.getFs_created_margin_order());
        // POST請求を送信する
        OkHttpResponse httpResp = this.post(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        CreateForeignStockMarginOrderResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返します。
            resp = httpResp.getResponseData(CreateForeignStockMarginOrderResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        String tmp;
        // 信用注文情報.注文情報.売買区分
        tmp = codeListService.convertExtKeyToKey(SELL_BUY_TYPE, ATHENA,
                resp.getMarginOrder().getOrder().getBuySellCode());
        resp.getMarginOrder().getOrder().setBuySellCode(tmp);
        // 信用注文情報.注文情報.価格条件
        tmp = codeListService.convertExtKeyToKey(SELECT_ABLE_PRICE_CONDITIONS, ATHENA,
                resp.getMarginOrder().getOrder().getOrderPriceKindCode());
        resp.getMarginOrder().getOrder().setOrderPriceKindCode(tmp);
        // 信用注文情報.注文情報.期間条件
        tmp = codeListService.convertExtKeyToKey(PERIOD_CONDITIONS, ATHENA,
                resp.getMarginOrder().getOrder().getOrderLimitCode());
        resp.getMarginOrder().getOrder().setOrderLimitCode(tmp);
        // 信用注文情報.注文情報.預り区分
        tmp = codeListService.convertExtKeyToKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                resp.getMarginOrder().getOrder().getSpecificAccountCode());
        resp.getMarginOrder().getOrder().setSpecificAccountCode(tmp);
        // 信用注文情報.注文情報.決済方法
        tmp = codeListService.convertExtKeyToKey(SETTLEMENT_TYPE, ATHENA,
                resp.getMarginOrder().getOrder().getSettlementMethodCode());
        resp.getMarginOrder().getOrder().setSettlementMethodCode(tmp);
        // 信用注文情報.信用期日
        tmp = codeListService.convertExtKeyToKey(MARGIN_DUE_DATE, ATHENA,
                resp.getMarginOrder().getMarginCloseLimitType());
        resp.getMarginOrder().setMarginCloseLimitType(tmp);
        
        // 結果を返します
        return resp;
    }
    
    @Override
    public GetForeignStockCreatedMarginOrderInitializationResp getForeignStockCreatedMarginOrderInitialization(
            GetForeignStockCreatedMarginOrderInitializationReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockMarginOrderServiceImpl.getForeignStockCreatedMarginOrderInitialization : {}",
                    hashCode());
        }
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            if (request == null) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // headerを設定する
            GetForeignStockCreatedMarginOrderInitializationReq.Header header = request.getHeader();
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
            
            // headerを設定する
            GetForeignStockCreatedMarginOrderInitializationReq.Parameter parameter = request.getParameter();
            // 必須入力チェック「国コード」
            if (StringUtil.isNullOrEmpty(parameter.getCountryCode())) {
                warnMsg = "CountryCode is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「株取引区分」
            if (StringUtil.isNullOrEmpty(parameter.getStockTradeType())) {
                warnMsg = "StockTradeType is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「売買区分」
            //if (StringUtil.isNullOrEmpty(parameter.getBuySellCode())) {
            //    warnMsg = "BuySellCode is null or empty!";
            //    break;
            //}
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        // 内部コード→外部コード
        // 預り区分
        if (!StringUtil.isNullOrEmpty(request.getParameter().getSpecificAccountCode())) {
            String specificAccountCodeAthena = codeListService.convertKeyToExtKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                    request.getParameter().getSpecificAccountCode());
            request.getParameter().setSpecificAccountCode(specificAccountCodeAthena);
        }
        
        // APIのURLを設定する
        String url = this.getUrl(CometApiUtil.getFs_order_margin_orders_get_created_order_initialization());
        // GET請求を送信する
        OkHttpResponse httpResp = this.get(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        GetForeignStockCreatedMarginOrderInitializationResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返します。
            resp = httpResp.getResponseData(GetForeignStockCreatedMarginOrderInitializationResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        /* 外部コード→内部コード変換 */
        // 選択可能預り区分リスト
        if (CollectionUtils.isNotEmpty(resp.getSpecificAccountCodes())) {
            for (int i = 0; i < resp.getSpecificAccountCodes().size(); i++) {
                String convSpecificAccountCodes = codeListService.convertExtKeyToKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                        resp.getSpecificAccountCodes().get(i));
                resp.getSpecificAccountCodes().set(i, convSpecificAccountCodes);
            }
        }
        
        // 選択可能決済方法リスト
        if (CollectionUtils.isNotEmpty(resp.getSettlementMethodCodes())) {
            for (int i = 0; i < resp.getSettlementMethodCodes().size(); i++) {
                String convSettlementMethodCodes = codeListService.convertExtKeyToKey(SETTLEMENT_TYPE, ATHENA,
                        resp.getSettlementMethodCodes().get(i));
                resp.getSettlementMethodCodes().set(i, convSettlementMethodCodes);
            }
        }
        
        // 選択可能価格条件リスト
        if (CollectionUtils.isNotEmpty(resp.getOrderPriceKindCodes())) {
            for (int i = 0; i < resp.getOrderPriceKindCodes().size(); i++) {
                String convOrderPriceKindCodes = codeListService.convertExtKeyToKey(SELECT_ABLE_PRICE_CONDITIONS,
                        ATHENA, resp.getOrderPriceKindCodes().get(i));
                resp.getOrderPriceKindCodes().set(i, convOrderPriceKindCodes);
            }
        }
        
        // 選択可能信用期日リスト
        if (CollectionUtils.isNotEmpty(resp.getMarginCloseLimitTypes())) {
            for (int i = 0; i < resp.getMarginCloseLimitTypes().size(); i++) {
                String convMarginCloseLimitTypes = codeListService.convertExtKeyToKey(MARGIN_DUE_DATE, ATHENA,
                        resp.getMarginCloseLimitTypes().get(i));
                resp.getMarginCloseLimitTypes().set(i, convMarginCloseLimitTypes);
            }
        }
        
        // 選択可能返済選択順序リスト
        if (CollectionUtils.isNotEmpty(resp.getCloseSelectionSorts())) {
            for (int i = 0; i < resp.getCloseSelectionSorts().size(); i++) {
                String convCloseSelectionSorts = codeListService.convertExtKeyToKey(FOREIGN_REPAY_ORDER, ATHENA,
                        resp.getCloseSelectionSorts().get(i));
                resp.getCloseSelectionSorts().set(i, convCloseSelectionSorts);
            }
        }
        
        // 選択可能期間条件リスト
        if (CollectionUtils.isNotEmpty(resp.getOrderLimitCodes())) {
            for (int i = 0; i < resp.getOrderLimitCodes().size(); i++) {
                String convOrderLimitCodes = codeListService.convertExtKeyToKey(PERIOD_CONDITIONS, ATHENA,
                        resp.getOrderLimitCodes().get(i));
                resp.getOrderLimitCodes().set(i, convOrderLimitCodes);
            }
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返します
        return resp;
    }
    
    @Override
    public GetForeignStockCreatedMarginOrderInitializationResp getForeignStockCreatedMarginOrderInitializationNoConvertBuySellCode(
            GetForeignStockCreatedMarginOrderInitializationReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        if (LOG.isDebugEnabled()) {
            LOG.debug(
                    "CometForeignStockMarginOrderServiceImpl.getForeignStockCreatedMarginOrderInitializationNoConvertBuySellCode : {}",
                    hashCode());
        }
        
        // 内部コード→外部コード
        // 預り区分
        if (!StringUtil.isNullOrEmpty(request.getParameter().getSpecificAccountCode())) {
            String specificAccountCodeAthena = codeListService.convertKeyToExtKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                    request.getParameter().getSpecificAccountCode());
            request.getParameter().setSpecificAccountCode(specificAccountCodeAthena);
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            if (request == null) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // headerを設定する
            GetForeignStockCreatedMarginOrderInitializationReq.Header header = request.getHeader();
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
            
            // headerを設定する
            GetForeignStockCreatedMarginOrderInitializationReq.Parameter parameter = request.getParameter();
            // 必須入力チェック「国コード」
            if (StringUtil.isNullOrEmpty(parameter.getCountryCode())) {
                warnMsg = "CountryCode is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「株取引区分」
            if (StringUtil.isNullOrEmpty(parameter.getStockTradeType())) {
                warnMsg = "StockTradeType is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「売買区分」
            //if (StringUtil.isNullOrEmpty(parameter.getBuySellCode())) {
            //    warnMsg = "BuySellCode is null or empty!";
            //    break;
            //}
            
            // 入力チェック「預り区分」
            if (StringUtil.isNullOrEmpty(parameter.getSpecificAccountCode())) {
                warnMsg = "SpecificAccountCode is not exists!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        // APIのURLを設定する
        String url = this.getUrl(CometApiUtil.getFs_order_margin_orders_get_created_order_initialization());
        // GET請求を送信する
        OkHttpResponse httpResp = this.get(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        GetForeignStockCreatedMarginOrderInitializationResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返します。
            resp = httpResp.getResponseData(GetForeignStockCreatedMarginOrderInitializationResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        /* 外部コード→内部コード変換 */
        // 選択可能預り区分リスト
        if (CollectionUtils.isNotEmpty(resp.getSpecificAccountCodes())) {
            for (int i = 0; i < resp.getSpecificAccountCodes().size(); i++) {
                String convSpecificAccountCodes = codeListService.convertExtKeyToKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                        resp.getSpecificAccountCodes().get(i));
                resp.getSpecificAccountCodes().set(i, convSpecificAccountCodes);
            }
        }
        
        // 選択可能決済方法リスト
        if (CollectionUtils.isNotEmpty(resp.getSettlementMethodCodes())) {
            for (int i = 0; i < resp.getSettlementMethodCodes().size(); i++) {
                String convSettlementMethodCodes = codeListService.convertExtKeyToKey(SETTLEMENT_TYPE, ATHENA,
                        resp.getSettlementMethodCodes().get(i));
                resp.getSettlementMethodCodes().set(i, convSettlementMethodCodes);
            }
        }
        
        // 選択可能価格条件リスト
        if (CollectionUtils.isNotEmpty(resp.getOrderPriceKindCodes())) {
            for (int i = 0; i < resp.getOrderPriceKindCodes().size(); i++) {
                String convOrderPriceKindCodes = codeListService.convertExtKeyToKey(SELECT_ABLE_PRICE_CONDITIONS,
                        ATHENA, resp.getOrderPriceKindCodes().get(i));
                resp.getOrderPriceKindCodes().set(i, convOrderPriceKindCodes);
            }
        }
        
        // 選択可能信用期日リスト
        if (CollectionUtils.isNotEmpty(resp.getMarginCloseLimitTypes())) {
            for (int i = 0; i < resp.getMarginCloseLimitTypes().size(); i++) {
                String convMarginCloseLimitTypes = codeListService.convertExtKeyToKey(MARGIN_DUE_DATE, ATHENA,
                        resp.getMarginCloseLimitTypes().get(i));
                resp.getMarginCloseLimitTypes().set(i, convMarginCloseLimitTypes);
            }
        }
        
        // 選択可能返済選択順序リスト
        if (CollectionUtils.isNotEmpty(resp.getCloseSelectionSorts())) {
            for (int i = 0; i < resp.getCloseSelectionSorts().size(); i++) {
                String convCloseSelectionSorts = codeListService.convertExtKeyToKey(FOREIGN_REPAY_ORDER, ATHENA,
                        resp.getCloseSelectionSorts().get(i));
                resp.getCloseSelectionSorts().set(i, convCloseSelectionSorts);
            }
        }
        
        // 選択可能期間条件リスト
        if (CollectionUtils.isNotEmpty(resp.getOrderLimitCodes())) {
            for (int i = 0; i < resp.getOrderLimitCodes().size(); i++) {
                String convOrderLimitCodes = codeListService.convertExtKeyToKey(PERIOD_CONDITIONS, ATHENA,
                        resp.getOrderLimitCodes().get(i));
                resp.getOrderLimitCodes().set(i, convOrderLimitCodes);
            }
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返します
        return resp;
    }
    
    @Override
    public ConfirmForeignStockClosedMarginOrderResp confirmForeignStockClosedMarginOrder(
            ConfirmForeignStockClosedMarginOrderReq confirmForeignStockClosedMarginOrderReq) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockMarginOrderServiceImpl.confirmForeignStockClosedMarginOrder : {}", hashCode());
        }
        
        // パラメータチェックメッセージ
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // 「リクエスト」：空のチェック
            if (null == confirmForeignStockClosedMarginOrderReq) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // 「Token」：必須入力チェック、正確性チェック
            if (StringUtil.isNullOrEmpty(confirmForeignStockClosedMarginOrderReq.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            if (!checkToken(confirmForeignStockClosedMarginOrderReq.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            
            // 「チャネル」：必須入力チェック
            if (StringUtil.isNullOrEmpty(confirmForeignStockClosedMarginOrderReq.getHeader().getChannel())) {
                warnMsg = "Channel is null or empty!";
                break;
            }
            
            // 「信用注文情報」：空のチェック
            MarginOrderInputForConfirmForeignStockClosedMarginOrder marginOrder = confirmForeignStockClosedMarginOrderReq.getParameter().getMarginOrder();
            if (null == marginOrder) {
                warnMsg = "MarginOrder is null!";
                break;
            }
            
            // 「国コード」：必須入力チェック、存在チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getCountryCode())) {
                warnMsg = "CountryCode is null or empty!";
                break;
            }
            
            // 「市場コード」：必須入力チェック、存在チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getMarketCode())) {
                warnMsg = "MarketCode is null or empty!";
                break;
            }
            // 「銘柄コード」：必須入力チェック、桁数チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getSecuritiesCode())) {
                warnMsg = "SecuritiesCode is null or empty!";
                break;
            }
            if (SECURITIESCODE_MAX_LENGTH < marginOrder.getSecuritiesCode().length()) {
                warnMsg = "SecuritiesCode " + marginOrder.getSecuritiesCode() + " must be " + SECURITIESCODE_MAX_LENGTH
                        + " digits!";
                break;
            }
            
            // 「売買区分」：必須入力チェック、存在チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getBuySellCode())) {
                warnMsg = "BuySellCode is null or empty!";
                break;
            }
            
            // 「注文数量」：必須入力チェック、桁数チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getOrderQuantity())) {
                warnMsg = "OrderQuantity is null or empty!";
                break;
            }
            if (!checkRange(new BigDecimal(marginOrder.getOrderQuantity()), AMOUNT_MIN_VALUE, AMOUNT_MAX_VALUE)) {
                warnMsg = "OrderQuantity " + marginOrder.getOrderQuantity() + " is out of this range!";
                break;
            }
            
            // 「価格条件」：必須入力チェック、存在チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getOrderPriceKindCode())) {
                warnMsg = "OrderPriceKindCode is null or empty!";
                break;
            }
            
            // 「注文単価」：サイズ範囲チェック(0-999999999.99999999)
            if (!StringUtil.isNullOrEmpty(marginOrder.getOrderPrice())
                    && !checkRange(new BigDecimal(marginOrder.getOrderPrice()), ORDERPRICE_MIN_VALUE,
                            ORDERPRICE_MAX_VALUE)) {
                warnMsg = "OrderPrice " + marginOrder.getOrderPrice() + " is out of this range!";
                break;
            }
            
            // 「発火条件価格」：サイズ範囲チェック(0-999999999.99999999)
            if (!StringUtil.isNullOrEmpty(marginOrder.getStopPrice())
                    && !checkRange(new BigDecimal(marginOrder.getStopPrice()), STOPPRICE_MIN_VALUE,
                            STOPPRICE_MAX_VALUE)) {
                warnMsg = "StopPrice " + marginOrder.getStopPrice() + " is out of this range!";
                break;
            }
            
            // 「トレールストップ幅」：サイズ範囲チェック(0-9999999999999.9999)
            if (!StringUtil.isNullOrEmpty(marginOrder.getTrailingStopAmount())
                    && !checkRange(new BigDecimal(marginOrder.getTrailingStopAmount()), TRAILINGSTOPAMOUNT_MIN_VALUE,
                            TRAILINGSTOPAMOUNT_MAX_VALUE)) {
                warnMsg = "TrailingStopAmount " + marginOrder.getTrailingStopAmount() + " is out of this range!";
                break;
            }
            
            // 「期間条件」：必須入力チェック、存在チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getOrderLimitCode())) {
                warnMsg = "OrderLimitCode is null or empty!";
                break;
            }
            
            // フォーマットチェック「期間 」
            if (!StringUtil.isNullOrEmpty(marginOrder.getOrderTerm())
                    && !DateUtil.isParsable(marginOrder.getOrderTerm(), FORMAT_YEAR_MONTH_DAY_DASH)) {
                warnMsg = "OrderTerm " + marginOrder.getOrderTerm() + " format is illegal!";
                break;
            }
            
            // 「預り区分」：必須入力チェック、存在チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getSpecificAccountCode())) {
                warnMsg = "SpecificAccountCode is null or empty!";
                break;
            }
            
            // 「決済方法」：必須入力チェック、存在チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getSettlementMethodCode())) {
                warnMsg = "SettlementMethodCode is null or empty!";
                break;
            }
            
            // 「返済建玉指定方法」：必須入力チェック、存在チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getClosePositionKind())) {
                warnMsg = "ClosePositionKind is null or empty!";
                break;
            }
            
            // 「返済選択順序」：必須入力チェック、存在チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getCloseSelectionSort())) {
                warnMsg = "CloseSelectionSort is null or empty!";
                break;
            }
            
            List<ClosedPositionInput> positions = null;
            // 「返済相手建玉明細情報」：空のチェック(返済建玉指定方法が「個別指定」の場合)
            if (ClosePositionKind.INDIVIDUAL.getId().equals(marginOrder.getClosePositionKind())) {
                // 返済建玉指定方法が「個別指定」の場合
                positions = confirmForeignStockClosedMarginOrderReq.getParameter().getPositions();
                if (null == positions) {
                    warnMsg = "Positions is null!";
                    break;
                } else {
                    for (ClosedPositionInput position : positions) {
                        // 「売買区分」：必須入力チェック、存在チェック
                        if (StringUtil.isNullOrEmpty(position.getBuySellCode())) {
                            warnMsg = "BuySellCode is null or empty!";
                            break;
                        }
                        
                        // 「信用期日」：必須入力チェック、存在チェック
                        if (StringUtil.isNullOrEmpty(position.getMarginCloseLimitType())) {
                            warnMsg = "MarginCloseLimitType is null or empty!";
                            break;
                        }
                        
                        // 「預り区分」：必須入力チェック、存在チェック
                        if (StringUtil.isNullOrEmpty(position.getSpecificAccountCode())) {
                            warnMsg = "SpecificAccountCode is null or empty!";
                            break;
                        }
                        
                        // 「国内新規約定日」：必須入力チェック、フォーマットチェック
                        if (StringUtil.isNullOrEmpty(position.getTradeDate())) {
                            warnMsg = "TradeDate is null or empty!";
                            break;
                        }
                        if (!DateUtil.isParsable(position.getTradeDate(), FORMAT_YEAR_MONTH_DAY_DASH)) {
                            warnMsg = "TradeDate " + position.getTradeDate() + " format is illegal!";
                            break;
                        }
                        
                        // 「現地新規約定日」：必須入力チェック、フォーマットチェック
                        if (StringUtil.isNullOrEmpty(position.getFrnTradeDate())) {
                            warnMsg = "FrnTradeDate is null or empty!";
                            break;
                        }
                        if (!DateUtil.isParsable(position.getFrnTradeDate(), FORMAT_YEAR_MONTH_DAY_DASH)) {
                            warnMsg = "FrnTradeDate " + position.getFrnTradeDate() + " format is illegal!";
                            break;
                        }
                        
                        // 「建単価」：必須入力チェック
                        if (StringUtil.isNullOrEmpty(position.getPositionPrice())) {
                            warnMsg = "PositionPrice is null or empty!";
                            break;
                        }
                        
                        // 「返済数量」：必須入力チェック
                        if (StringUtil.isNullOrEmpty(position.getCloseOrderQuantity())) {
                            warnMsg = "CloseOrderQuantity is null or empty!";
                            break;
                        }
                    }
                }
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        /* 内部コード→外部コード変換 */
        // 価格条件
        if (!StringUtil.isNullOrEmpty(
                confirmForeignStockClosedMarginOrderReq.getParameter().getMarginOrder().getOrderPriceKindCode())) {
            String orderPriceKindCodeAthena = codeListService.convertKeyToExtKey(SELECT_ABLE_PRICE_CONDITIONS, ATHENA,
                    confirmForeignStockClosedMarginOrderReq.getParameter().getMarginOrder().getOrderPriceKindCode());
            confirmForeignStockClosedMarginOrderReq.getParameter().getMarginOrder()
                    .setOrderPriceKindCode(orderPriceKindCodeAthena);
        }
        // 期間条件
        if (!StringUtil.isNullOrEmpty(
                confirmForeignStockClosedMarginOrderReq.getParameter().getMarginOrder().getOrderLimitCode())) {
            String orderLimitCodeAthena = codeListService.convertKeyToExtKey(PERIOD_CONDITIONS, ATHENA,
                    confirmForeignStockClosedMarginOrderReq.getParameter().getMarginOrder().getOrderLimitCode());
            confirmForeignStockClosedMarginOrderReq.getParameter().getMarginOrder()
                    .setOrderLimitCode(orderLimitCodeAthena);
        }
        // 預り区分
        if (!StringUtil.isNullOrEmpty(
                confirmForeignStockClosedMarginOrderReq.getParameter().getMarginOrder().getSpecificAccountCode())) {
            String specificAccountCodeAthena = codeListService.convertKeyToExtKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                    confirmForeignStockClosedMarginOrderReq.getParameter().getMarginOrder().getSpecificAccountCode());
            confirmForeignStockClosedMarginOrderReq.getParameter().getMarginOrder()
                    .setSpecificAccountCode(specificAccountCodeAthena);
        }
        // 返済建玉指定方法
        if (!StringUtil.isNullOrEmpty(
                confirmForeignStockClosedMarginOrderReq.getParameter().getMarginOrder().getClosePositionKind())) {
            String closePositionKindAthena = codeListService.convertKeyToExtKey(FOREIGN_REPAY_METHOD, ATHENA,
                    confirmForeignStockClosedMarginOrderReq.getParameter().getMarginOrder().getClosePositionKind());
            confirmForeignStockClosedMarginOrderReq.getParameter().getMarginOrder()
                    .setClosePositionKind(closePositionKindAthena);
        }
        
        // 返済選択順序
        if (!StringUtil.isNullOrEmpty(
                confirmForeignStockClosedMarginOrderReq.getParameter().getMarginOrder().getCloseSelectionSort())) {
            String closeSelectionSortAthena = codeListService.convertKeyToExtKey(FOREIGN_REPAY_ORDER, ATHENA,
                    confirmForeignStockClosedMarginOrderReq.getParameter().getMarginOrder().getCloseSelectionSort());
            confirmForeignStockClosedMarginOrderReq.getParameter().getMarginOrder()
                    .setCloseSelectionSort(closeSelectionSortAthena);
        }
        
        for (int i = 0; i < confirmForeignStockClosedMarginOrderReq.getParameter().getPositions().size(); i++) {
            // 建区分
            if (!StringUtil.isNullOrEmpty(
                    confirmForeignStockClosedMarginOrderReq.getParameter().getPositions().get(i).getBuySellCode())) {
                String buySellCodeAthena = codeListService.convertKeyToExtKey(SELL_BUY_TYPE, ATHENA,
                        confirmForeignStockClosedMarginOrderReq.getParameter().getPositions().get(i).getBuySellCode());
                confirmForeignStockClosedMarginOrderReq.getParameter().getPositions().get(i)
                        .setBuySellCode(buySellCodeAthena);
            }
            
            // 返済期限
            if (!StringUtil.isNullOrEmpty(confirmForeignStockClosedMarginOrderReq.getParameter().getPositions().get(i)
                    .getMarginCloseLimitType())) {
                String marginCloseLimitTypeAthena = codeListService.convertKeyToExtKey(MARGIN_DUE_DATE, ATHENA,
                        confirmForeignStockClosedMarginOrderReq.getParameter().getPositions().get(i)
                                .getMarginCloseLimitType());
                confirmForeignStockClosedMarginOrderReq.getParameter().getPositions().get(i)
                        .setMarginCloseLimitType(marginCloseLimitTypeAthena);
            }
            // 預り区分
            if (!StringUtil.isNullOrEmpty(confirmForeignStockClosedMarginOrderReq.getParameter().getPositions().get(i)
                    .getSpecificAccountCode())) {
                String specificAccountCodeAthena = codeListService.convertKeyToExtKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                        confirmForeignStockClosedMarginOrderReq.getParameter().getPositions().get(i)
                                .getSpecificAccountCode());
                confirmForeignStockClosedMarginOrderReq.getParameter().getPositions().get(i)
                        .setSpecificAccountCode(specificAccountCodeAthena);
            }
        }
        
        // APIのURLを設定する。
        String url = this.getUrl(CometApiUtil.getFs_order_margin_orders_confirm_closed_order());
        
        // POST請求を送信する。
        OkHttpResponse httpResp = this.post(url, confirmForeignStockClosedMarginOrderReq);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // RESPONSEをインスタンスする。
        ConfirmForeignStockClosedMarginOrderResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返する。
            resp = httpResp.getResponseData(ConfirmForeignStockClosedMarginOrderResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        /* 外部コード→内部コード変換 */
        // 売買区分
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getBuySellCode())) {
            String convBuySellCode = codeListService.convertExtKeyToKey(SELL_BUY_TYPE, ATHENA,
                    resp.getMarginOrder().getOrder().getBuySellCode());
            resp.getMarginOrder().getOrder().setBuySellCode(convBuySellCode);
        }
        // 価格条件
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getOrderPriceKindCode())) {
            String convOrderPriceKindCode = codeListService.convertExtKeyToKey(SELECT_ABLE_PRICE_CONDITIONS, ATHENA,
                    resp.getMarginOrder().getOrder().getOrderPriceKindCode());
            resp.getMarginOrder().getOrder().setOrderPriceKindCode(convOrderPriceKindCode);
        }
        // 期間条件
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getOrderLimitCode())) {
            String convOrderLimitCode = codeListService.convertExtKeyToKey(PERIOD_CONDITIONS, ATHENA,
                    resp.getMarginOrder().getOrder().getOrderLimitCode());
            resp.getMarginOrder().getOrder().setOrderLimitCode(convOrderLimitCode);
        }
        // 預り区分
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getSpecificAccountCode())) {
            String conSpecificAccountCode = codeListService.convertExtKeyToKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                    resp.getMarginOrder().getOrder().getSpecificAccountCode());
            resp.getMarginOrder().getOrder().setSpecificAccountCode(conSpecificAccountCode);
        }
        // 決済方法
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getSettlementMethodCode())) {
            String convSettlementMethodCode = codeListService.convertExtKeyToKey(SETTLEMENT_TYPE, ATHENA,
                    resp.getMarginOrder().getOrder().getSettlementMethodCode());
            resp.getMarginOrder().getOrder().setSettlementMethodCode(convSettlementMethodCode);
        }
        // 返済建玉指定方法
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getClosePositionKind())) {
            String convClosePositionKind = codeListService.convertExtKeyToKey(FOREIGN_REPAY_METHOD, ATHENA,
                    resp.getMarginOrder().getClosePositionKind());
            resp.getMarginOrder().setClosePositionKind(convClosePositionKind);
        }
        // 評価益降順.返済選択順序
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getCloseSelectionSort())) {
            String convCloseSelectionSort = codeListService.convertExtKeyToKey(FOREIGN_REPAY_ORDER, ATHENA,
                    resp.getMarginOrder().getCloseSelectionSort());
            resp.getMarginOrder().setCloseSelectionSort(convCloseSelectionSort);
        }
        // 信用期日
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getMarginCloseLimitType())) {
            String convMarginCloseLimitType = codeListService.convertExtKeyToKey(MARGIN_DUE_DATE, ATHENA,
                    resp.getMarginOrder().getMarginCloseLimitType());
            resp.getMarginOrder().setMarginCloseLimitType(convMarginCloseLimitType);
        }
        
        // 結果を返する。
        return resp;
    }
    
    @Override
    public CloseForeignStockMarginOrderResp closeForeignStockMarginOrder(
            CloseForeignStockMarginOrderReq closeForeignStockMarginOrderReq) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockMarginOrderServiceImpl.closeForeignStockMarginOrder : {}", hashCode());
        }
        
        // パラメータチェックメッセージ
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // 「リクエスト」：空のチェック
            if (null == closeForeignStockMarginOrderReq) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // 「Token」：必須入力チェック、正確性チェック
            if (StringUtil.isNullOrEmpty(closeForeignStockMarginOrderReq.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            if (!checkToken(closeForeignStockMarginOrderReq.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            
            // 「チャネル」：必須入力チェック
            if (StringUtil.isNullOrEmpty(closeForeignStockMarginOrderReq.getHeader().getChannel())) {
                warnMsg = "Channel is null or empty!";
                break;
            }
            
            // 「信用注文情報」：空のチェック
            MarginTradeRepayOrderConfirmForCloseForeignStockMarginOrder marginOrder = closeForeignStockMarginOrderReq.getParameter().getMarginOrder();
            if (null == marginOrder) {
                warnMsg = "MarginOrder is null!";
                break;
            }
            
            // 「国コード」：必須入力チェック、存在チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getCountryCode())) {
                warnMsg = "CountryCode is null or empty!";
                break;
            }
            
            // 「市場コード」：必須入力チェック、存在チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getMarketCode())) {
                warnMsg = "MarketCode is null or empty!";
                break;
            }
            
            // 「銘柄コード」：必須入力チェック、桁数チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getSecuritiesCode())) {
                warnMsg = "SecuritiesCode is null or empty!";
                break;
            }
            if (SECURITIESCODE_MAX_LENGTH < marginOrder.getSecuritiesCode().length()) {
                warnMsg = "SecuritiesCode " + marginOrder.getSecuritiesCode() + " must be " + SECURITIESCODE_MAX_LENGTH
                        + " digits!";
                break;
            }
            
            // 「売買区分」：必須入力チェック、存在チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getBuySellCode())) {
                warnMsg = "BuySellCode is null or empty!";
                break;
            }
            // 「注文数量」：必須入力チェック、桁数チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getOrderQuantity())) {
                warnMsg = "OrderQuantity is null or empty!";
                break;
            }
            if (!checkRange(new BigDecimal(marginOrder.getOrderQuantity()), AMOUNT_MIN_VALUE, AMOUNT_MAX_VALUE)) {
                warnMsg = "OrderQuantity " + marginOrder.getOrderQuantity() + " is out of this range!";
                break;
            }
            
            // 「価格条件」：必須入力チェック、存在チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getOrderPriceKindCode())) {
                warnMsg = "OrderPriceKindCode is null or empty!";
                break;
            }
            // 「注文単価」：サイズ範囲チェック(0-999999999.99999999)
            if (!StringUtil.isNullOrEmpty(marginOrder.getOrderPrice())
                    && !checkRange(new BigDecimal(marginOrder.getOrderPrice()), ORDERPRICE_MIN_VALUE,
                            ORDERPRICE_MAX_VALUE)) {
                warnMsg = "OrderPrice " + marginOrder.getOrderPrice() + " is out of this range!";
                break;
            }
            
            // 「発火条件価格」：サイズ範囲チェック(0-999999999.99999999)
            if (!StringUtil.isNullOrEmpty(marginOrder.getStopPrice())
                    && !checkRange(new BigDecimal(marginOrder.getStopPrice()), STOPPRICE_MIN_VALUE,
                            STOPPRICE_MAX_VALUE)) {
                warnMsg = "StopPrice " + marginOrder.getStopPrice() + " is out of this range!";
                break;
            }
            
            // 「トレールストップ幅」：サイズ範囲チェック(0-9999999999999.9999)
            if (!StringUtil.isNullOrEmpty(marginOrder.getTrailingStopAmount())
                    && !checkRange(new BigDecimal(marginOrder.getTrailingStopAmount()), TRAILINGSTOPAMOUNT_MIN_VALUE,
                            TRAILINGSTOPAMOUNT_MAX_VALUE)) {
                warnMsg = "TrailingStopAmount " + marginOrder.getTrailingStopAmount() + " is out of this range!";
                break;
            }
            
            // 「期間条件」：必須入力チェック、存在チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getOrderLimitCode())) {
                warnMsg = "OrderLimitCode is null or empty!";
                break;
            }
            
            // フォーマットチェック「期間 」
            if (!StringUtil.isNullOrEmpty(marginOrder.getOrderTerm())
                    && !DateUtil.isParsable(marginOrder.getOrderTerm(), FORMAT_YEAR_MONTH_DAY_DASH)) {
                warnMsg = "OrderTerm " + marginOrder.getOrderTerm() + " format is illegal!";
                break;
            }
            
            // 「預り区分」：必須入力チェック、存在チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getSpecificAccountCode())) {
                warnMsg = "SpecificAccountCode is null or empty!";
                break;
            }
            // 「決済方法」：必須入力チェック、存在チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getSettlementMethodCode())) {
                warnMsg = "SettlementMethodCode is null or empty!";
                break;
            }
            // 「返済建玉指定方法」：必須入力チェック、存在チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getClosePositionKind())) {
                warnMsg = "ClosePositionKind is null or empty!";
                break;
            }
            
            // 「返済選択順序」：必須入力チェック、存在チェック
            if (StringUtil.isNullOrEmpty(marginOrder.getCloseSelectionSort())) {
                warnMsg = "CloseSelectionSort is null or empty!";
                break;
            }
            List<ClosedPositionInput> positions = null;
            // 「返済相手建玉明細情報」：空のチェック(返済建玉指定方法が「個別指定」の場合)
            if (ClosePositionKind.INDIVIDUAL.getId().equals(marginOrder.getClosePositionKind())) {
                // 返済建玉指定方法が「個別指定」の場合
                positions = closeForeignStockMarginOrderReq.getParameter().getPositions();
                if (null == positions) {
                    warnMsg = "Positions is null!";
                    break;
                } else {
                    for (ClosedPositionInput position : positions) {
                        // 「売買区分」：必須入力チェック、存在チェック
                        if (StringUtil.isNullOrEmpty(position.getBuySellCode())) {
                            warnMsg = "BuySellCode is null or empty!";
                            break;
                        }
                        // 「信用期日」：必須入力チェック、存在チェック
                        if (StringUtil.isNullOrEmpty(position.getMarginCloseLimitType())) {
                            warnMsg = "MarginCloseLimitType is null or empty!";
                            break;
                        }
                        
                        // 「預り区分」：必須入力チェック、存在チェック
                        if (StringUtil.isNullOrEmpty(position.getSpecificAccountCode())) {
                            warnMsg = "SpecificAccountCode is null or empty!";
                            break;
                        }
                        
                        // 「国内新規約定日」：必須入力チェック、フォーマットチェック
                        if (StringUtil.isNullOrEmpty(position.getTradeDate())) {
                            warnMsg = "TradeDate is null or empty!";
                            break;
                        }
                        if (!DateUtil.isParsable(position.getTradeDate(), FORMAT_YEAR_MONTH_DAY_DASH)) {
                            warnMsg = "TradeDate " + position.getTradeDate() + " format is illegal!";
                            break;
                        }
                        
                        // 「現地新規約定日」：必須入力チェック、フォーマットチェック
                        if (StringUtil.isNullOrEmpty(position.getFrnTradeDate())) {
                            warnMsg = "FrnTradeDate is null or empty!";
                            break;
                        }
                        if (!DateUtil.isParsable(position.getFrnTradeDate(), FORMAT_YEAR_MONTH_DAY_DASH)) {
                            warnMsg = "FrnTradeDate " + position.getFrnTradeDate() + " format is illegal!";
                            break;
                        }
                        
                        // 「建単価」：必須入力チェック
                        if (StringUtil.isNullOrEmpty(position.getPositionPrice())) {
                            warnMsg = "PositionPrice is null or empty!";
                            break;
                        }
                        // 「建単価」：サイズ範囲チェック(0-999999999.99999999)
                        if (!StringUtil.isNullOrEmpty(position.getPositionPrice())
                                && !checkRange(new BigDecimal(position.getPositionPrice()), POSITION_PRICE_MIN_VALUE,
                                        POSITION_PRICE_MAX_VALUE)) {
                            warnMsg = "PositionPrice " + position.getPositionPrice() + " is out of this range!";
                            break;
                        }
                        
                        // 「 建株数」：必須入力チェック
                        if (StringUtil.isNullOrEmpty(position.getPositionQuantity())) {
                            warnMsg = "positionQuantity is null or empty!";
                            break;
                        }
                        // 「建株数」：サイズ範囲: 0-9999999999
                        if (!checkRange(new BigDecimal(position.getPositionQuantity()), AMOUNT_MIN_VALUE,
                                AMOUNT_MAX_VALUE)) {
                            warnMsg = "PositionQuantity " + position.getPositionQuantity() + " is out of this range!";
                            break;
                        }
                        
                        // 「返済数量」：必須入力チェック
                        if (StringUtil.isNullOrEmpty(position.getCloseOrderQuantity())) {
                            warnMsg = "CloseOrderQuantity is null or empty!";
                            break;
                        }
                        // 「返済数量」：サイズ範囲: 0-9999999999
                        if (!checkRange(new BigDecimal(position.getCloseOrderQuantity()), AMOUNT_MIN_VALUE,
                                AMOUNT_MAX_VALUE)) {
                            warnMsg = "CloseOrderQuantity " + position.getCloseOrderQuantity()
                                    + " is out of this range!";
                            break;
                        }
                    }
                }
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        //　内部コード　→　外部コード　変換
        // 信用注文情報.価格条件
        if (!StringUtil.isNullOrEmpty(
                closeForeignStockMarginOrderReq.getParameter().getMarginOrder().getOrderPriceKindCode())) {
            String orderPriceKindCodeAthena = codeListService.convertKeyToExtKey(SELECT_ABLE_PRICE_CONDITIONS, ATHENA,
                    closeForeignStockMarginOrderReq.getParameter().getMarginOrder().getOrderPriceKindCode());
            closeForeignStockMarginOrderReq.getParameter().getMarginOrder().setOrderPriceKindCode(orderPriceKindCodeAthena);
        }
        // 信用注文情報.期間条件
        if (!StringUtil
                .isNullOrEmpty(closeForeignStockMarginOrderReq.getParameter().getMarginOrder().getOrderLimitCode())) {
            String orderLimitCodeAthena = codeListService.convertKeyToExtKey(PERIOD_CONDITIONS, ATHENA,
                    closeForeignStockMarginOrderReq.getParameter().getMarginOrder().getOrderLimitCode());
            closeForeignStockMarginOrderReq.getParameter().getMarginOrder().setOrderLimitCode(orderLimitCodeAthena);
        }
        // 信用注文情報.預り区分
        if (!StringUtil.isNullOrEmpty(
                closeForeignStockMarginOrderReq.getParameter().getMarginOrder().getSpecificAccountCode())) {
            String specificAccountCodeAthena = codeListService.convertKeyToExtKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                    closeForeignStockMarginOrderReq.getParameter().getMarginOrder().getSpecificAccountCode());
            closeForeignStockMarginOrderReq.getParameter().getMarginOrder()
                    .setSpecificAccountCode(specificAccountCodeAthena);
        }
        // 信用注文情報.決済方法
        if (!StringUtil.isNullOrEmpty(
                closeForeignStockMarginOrderReq.getParameter().getMarginOrder().getSettlementMethodCode())) {
            String settlementMethodCodeAthena = codeListService.convertKeyToExtKey(SETTLEMENT_TYPE, ATHENA,
                    closeForeignStockMarginOrderReq.getParameter().getMarginOrder().getSettlementMethodCode());
            closeForeignStockMarginOrderReq.getParameter().getMarginOrder()
                    .setSettlementMethodCode(settlementMethodCodeAthena);
        }
        // 信用注文情報.返済建玉指定方法
        if (!StringUtil.isNullOrEmpty(
                closeForeignStockMarginOrderReq.getParameter().getMarginOrder().getClosePositionKind())) {
            String closePositionKindAthena = codeListService.convertKeyToExtKey(FOREIGN_REPAY_METHOD, ATHENA,
                    closeForeignStockMarginOrderReq.getParameter().getMarginOrder().getClosePositionKind());
            closeForeignStockMarginOrderReq.getParameter().getMarginOrder()
                    .setClosePositionKind(closePositionKindAthena);
        }
        
        // 信用注文情報.返済選択順序
        if (!StringUtil.isNullOrEmpty(
                closeForeignStockMarginOrderReq.getParameter().getMarginOrder().getCloseSelectionSort())) {
            String CloseSelectionSortAthena = codeListService.convertKeyToExtKey(FOREIGN_REPAY_ORDER, ATHENA,
                    closeForeignStockMarginOrderReq.getParameter().getMarginOrder().getCloseSelectionSort());
            closeForeignStockMarginOrderReq.getParameter().getMarginOrder()
                    .setCloseSelectionSort(CloseSelectionSortAthena);
        }
        for (int i = 0; i < closeForeignStockMarginOrderReq.getParameter().getPositions().size(); i++) {
            // 決済相手建玉明細情報.売買区分
            if (!StringUtil.isNullOrEmpty(
                    closeForeignStockMarginOrderReq.getParameter().getPositions().get(i).getBuySellCode())) {
                String buySellCodeAthena = codeListService.convertKeyToExtKey(SELL_BUY_TYPE, ATHENA,
                        closeForeignStockMarginOrderReq.getParameter().getPositions().get(i).getBuySellCode());
                closeForeignStockMarginOrderReq.getParameter().getPositions().get(i).setBuySellCode(buySellCodeAthena);
            }
            // 決済相手建玉明細情報.信用期日
            if (!StringUtil.isNullOrEmpty(
                    closeForeignStockMarginOrderReq.getParameter().getPositions().get(i).getMarginCloseLimitType())) {
                String marginCloseLimitTypeAthena = codeListService.convertKeyToExtKey(MARGIN_DUE_DATE, ATHENA,
                        closeForeignStockMarginOrderReq.getParameter().getPositions().get(i).getMarginCloseLimitType());
                closeForeignStockMarginOrderReq.getParameter().getPositions().get(i)
                        .setMarginCloseLimitType(marginCloseLimitTypeAthena);
            }
            // 決済相手建玉明細情報.預り区分
            if (!StringUtil.isNullOrEmpty(
                    closeForeignStockMarginOrderReq.getParameter().getPositions().get(i).getSpecificAccountCode())) {
                String specificAccountCodeAthena = codeListService.convertKeyToExtKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                        closeForeignStockMarginOrderReq.getParameter().getPositions().get(i).getSpecificAccountCode());
                closeForeignStockMarginOrderReq.getParameter().getPositions().get(i)
                        .setSpecificAccountCode(specificAccountCodeAthena);
            }
        }
        

        // APIのURLを設定する。
        String url = this.getUrl(CometApiUtil.getFs_order_margin_orders_close_order());
        
        // POST請求を送信する。
        OkHttpResponse httpResp = this.post(url, closeForeignStockMarginOrderReq);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // RESPONSEをインスタンスする。
        CloseForeignStockMarginOrderResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返する。
            resp = httpResp.getResponseData(CloseForeignStockMarginOrderResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        /* 外部コード→内部コード変換 */
        // 売買区分
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getBuySellCode())) {
            String convBuySellCode = codeListService.convertExtKeyToKey(SELL_BUY_TYPE, ATHENA,
                    resp.getMarginOrder().getOrder().getBuySellCode());
            resp.getMarginOrder().getOrder().setBuySellCode(convBuySellCode);
        }
        // 価格条件
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getOrderPriceKindCode())) {
            String convOrderPriceKindCode = codeListService.convertExtKeyToKey(SELECT_ABLE_PRICE_CONDITIONS, ATHENA,
                    resp.getMarginOrder().getOrder().getOrderPriceKindCode());
            resp.getMarginOrder().getOrder().setOrderPriceKindCode(convOrderPriceKindCode);
        }
        // 期間条件
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getOrderLimitCode())) {
            String convOrderLimitCode = codeListService.convertExtKeyToKey(PERIOD_CONDITIONS, ATHENA,
                    resp.getMarginOrder().getOrder().getOrderLimitCode());
            resp.getMarginOrder().getOrder().setOrderLimitCode(convOrderLimitCode);
        }
        // 預り区分
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getSpecificAccountCode())) {
            String conSpecificAccountCode = codeListService.convertExtKeyToKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                    resp.getMarginOrder().getOrder().getSpecificAccountCode());
            resp.getMarginOrder().getOrder().setSpecificAccountCode(conSpecificAccountCode);
        }
        // 決済方法
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getSettlementMethodCode())) {
            String convSettlementMethodCode = codeListService.convertExtKeyToKey(SETTLEMENT_TYPE, ATHENA,
                    resp.getMarginOrder().getOrder().getSettlementMethodCode());
            resp.getMarginOrder().getOrder().setSettlementMethodCode(convSettlementMethodCode);
        }
        // 返済建玉指定方法
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getClosePositionKind())) {
            String convClosePositionKind = codeListService.convertExtKeyToKey(FOREIGN_REPAY_METHOD, ATHENA,
                    resp.getMarginOrder().getClosePositionKind());
            resp.getMarginOrder().setClosePositionKind(convClosePositionKind);
        }
        // 返済選択順序
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getCloseSelectionSort())) {
            String convCloseSelectionSort = codeListService.convertExtKeyToKey(FOREIGN_REPAY_ORDER, ATHENA,
                    resp.getMarginOrder().getCloseSelectionSort());
            resp.getMarginOrder().setCloseSelectionSort(convCloseSelectionSort);
        }
        // 信用期日
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getMarginCloseLimitType())) {
            String convMarginCloseLimitType = codeListService.convertExtKeyToKey(MARGIN_DUE_DATE, ATHENA,
                    resp.getMarginOrder().getMarginCloseLimitType());
            resp.getMarginOrder().setMarginCloseLimitType(convMarginCloseLimitType);
        }
        
        // 結果を返する。
        return resp;
    }
    
    @Override
    public ConfirmForeignStockCreatedMarginOrderResp confirmForeignStockCreatedMarginOrder(
            ConfirmForeignStockCreatedMarginOrderReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockMarginOrderServiceImpl.confirmForeignStockCreatedMarginOrder : {}", hashCode());
        }
        
        // 内部コード→外部コード
        // 価格条件
        if (!StringUtil.isNullOrEmpty(request.getParameter().getOrderPriceKindCode())) {
            String orderPriceKindCodeAthena = codeListService.convertKeyToExtKey(SELECT_ABLE_PRICE_CONDITIONS, ATHENA,
                    request.getParameter().getOrderPriceKindCode());
            request.getParameter().setOrderPriceKindCode(orderPriceKindCodeAthena);
        }
        
        // 期間条件
        if (!StringUtil.isNullOrEmpty(request.getParameter().getOrderLimitCode())) {
            String orderLimitCode = codeListService.convertKeyToExtKey(PERIOD_CONDITIONS, ATHENA,
                    request.getParameter().getOrderLimitCode());
            request.getParameter().setOrderLimitCode(orderLimitCode);
        }
        
        // 預り区分
        if (!StringUtil.isNullOrEmpty(request.getParameter().getSpecificAccountCode())) {
            String specificAccountCode = codeListService.convertKeyToExtKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                    request.getParameter().getSpecificAccountCode());
            request.getParameter().setSpecificAccountCode(specificAccountCode);
        }
        
        // 決済方法
        if (!StringUtil.isNullOrEmpty(request.getParameter().getSettlementMethodCode())) {
            String settlementMethodCode = codeListService.convertKeyToExtKey(SETTLEMENT_TYPE, ATHENA,
                    request.getParameter().getSettlementMethodCode());
            request.getParameter().setSettlementMethodCode(settlementMethodCode);
        }
        
        // 信用期日
        if (!StringUtil.isNullOrEmpty(request.getParameter().getMarginCloseLimitType())) {
            String convMarginCloseLimitType = codeListService.convertKeyToExtKey(MARGIN_DUE_DATE, ATHENA,
                    request.getParameter().getMarginCloseLimitType());
            request.getParameter().setMarginCloseLimitType(convMarginCloseLimitType);
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // requestのNULLチェックを行う
            if (request == null) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // headerを設定する
            ConfirmForeignStockCreatedMarginOrderReq.Header header = request.getHeader();
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
            
            // 必須入力チェック「国コード」
            if (StringUtil.isNullOrEmpty(request.getParameter().getCountryCode())
                    || null == CountryCode.getById(request.getParameter().getCountryCode())) {
                warnMsg = "CountryCode is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「市場コード」
            if (StringUtil.isNullOrEmpty(request.getParameter().getMarketCode())
                    || null == MarketCode.getById(request.getParameter().getMarketCode())) {
                warnMsg = "MarketCode is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「銘柄コード」
            if (StringUtil.isNullOrEmpty(request.getParameter().getSecuritiesCode())) {
                warnMsg = "SecuritiesCode is null or empty!";
                break;
            }
            // 桁数チェック「銘柄コード」
            String securitiesCode = request.getParameter().getSecuritiesCode();
            if (SECURITIESCODE_MAX_LENGTH < securitiesCode.length()) {
                warnMsg = "SecuritiesCode " + securitiesCode + " must be " + SECURITIESCODE_MAX_LENGTH + " digits!";
                break;
            }
            
            // 必須入力チェック「売買区分」
            if (StringUtil.isNullOrEmpty(request.getParameter().getBuySellCode())
                    || null == BuySell.getById(request.getParameter().getBuySellCode())) {
                warnMsg = "BuySellCode is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「注文数量」
            String orderQuantity = request.getParameter().getOrderQuantity();
            if (StringUtil.isNullOrEmpty(orderQuantity)) {
                warnMsg = "OrderQuantity is null or empty!";
                break;
            }
            // 桁数チェック:0-9999999999「注文数量」
            if (!checkRange(new BigDecimal(orderQuantity), AMOUNT_MIN_VALUE, AMOUNT_MAX_VALUE)) {
                warnMsg = "OrderQuantity " + orderQuantity + " is out of this range!";
                break;
            }
            
            // 必須入力チェック「価格条件」
            if (StringUtil.isNullOrEmpty(request.getParameter().getOrderPriceKindCode())
                    || null == OrderPriceKind.getById(request.getParameter().getOrderPriceKindCode())) {
                warnMsg = "OrderPriceKindCode is not exists or empty!";
                break;
            }
            
            // サイズ範囲:0-999999999.99999999「注文単価」
            String orderPrice = request.getParameter().getOrderPrice();
            if (!StringUtil.isNullOrEmpty(orderPrice)
                    && !checkRange(new BigDecimal(orderPrice), ORDERPRICE_MIN_VALUE, ORDERPRICE_MAX_VALUE)) {
                warnMsg = "OrderPrice " + orderPrice + " is out of this range!";
                break;
            }
            
            // サイズ範囲:0-999999999.99999999「発火条件価格」
            String stopPrice = request.getParameter().getStopPrice();
            if (!StringUtil.isNullOrEmpty(stopPrice)
                    && !checkRange(new BigDecimal(stopPrice), STOPPRICE_MIN_VALUE, STOPPRICE_MAX_VALUE)) {
                warnMsg = "StopPrice " + stopPrice + " is out of this range!";
                break;
            }
            
            // サイズ範囲:0-9999999999999.9999「トレールストップ幅」
            String trailingStopAmount = request.getParameter().getTrailingStopAmount();
            if (!StringUtil.isNullOrEmpty(trailingStopAmount) && !checkRange(new BigDecimal(trailingStopAmount),
                    TRAILINGSTOPAMOUNT_MIN_VALUE, TRAILINGSTOPAMOUNT_MAX_VALUE)) {
                warnMsg = "TrailingStopAmount " + trailingStopAmount + " is out of this range!";
                break;
            }
            
            // 必須入力チェック「期間条件」
            if (StringUtil.isNullOrEmpty(request.getParameter().getOrderLimitCode())
                    || null == OrderLimit.getById(request.getParameter().getOrderLimitCode())) {
                warnMsg = "OrderLimitCode is not exists or empty!";
                break;
            }
            
            // フォーマットチェック「期間」
            String orderTerm = request.getParameter().getOrderTerm();
            if (!StringUtil.isNullOrEmpty(orderTerm) && !DateUtil.isParsable(orderTerm, FORMAT_YEAR_MONTH_DAY_DASH)) {
                warnMsg = "OrderTerm " + orderTerm + " format is illegal!";
                break;
            }
            
            // 必須入力チェック「預り区分」
            if (StringUtil.isNullOrEmpty(request.getParameter().getSpecificAccountCode())
                    || null == SpecificAccount.getById(request.getParameter().getSpecificAccountCode())) {
                warnMsg = "SpecificAccountCode is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「決済方法」
            if (StringUtil.isNullOrEmpty(request.getParameter().getSettlementMethodCode())
                    || null == SettlementMethod.getById(request.getParameter().getSettlementMethodCode())) {
                warnMsg = "SettlementMethodCode is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「信用期日」
            if (StringUtil.isNullOrEmpty(request.getParameter().getMarginCloseLimitType())
                    || null == MarginCloseLimitType.getById(request.getParameter().getMarginCloseLimitType())) {
                warnMsg = "MarginCloseLimitType is not exists or empty!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        // HTTPのURLを設定する
        String url = this.getUrl(CometApiUtil.getFs_order_margin_orders_confirm_created_order());
        // POST請求を送信する
        OkHttpResponse httpResp = this.post(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        ConfirmForeignStockCreatedMarginOrderResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返します。
            resp = httpResp.getResponseData(ConfirmForeignStockCreatedMarginOrderResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // marginOrder.order.buySellCode
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getBuySellCode())) {
            String buySellCode = codeListService.convertExtKeyToKey(SELL_BUY_TYPE, ATHENA,
                    resp.getMarginOrder().getOrder().getBuySellCode());
            resp.getMarginOrder().getOrder().setBuySellCode(buySellCode);
        }
        
        // marginOrder.order.orderPriceKindCode
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getOrderPriceKindCode())) {
            String orderPriceKindCode = codeListService.convertExtKeyToKey(SELECT_ABLE_PRICE_CONDITIONS, ATHENA,
                    resp.getMarginOrder().getOrder().getOrderPriceKindCode());
            resp.getMarginOrder().getOrder().setOrderPriceKindCode(orderPriceKindCode);
        }
        
        // marginOrder.order.orderLimitCode
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getOrderLimitCode())) {
            String orderLimitCode = codeListService.convertExtKeyToKey(PERIOD_CONDITIONS, ATHENA,
                    resp.getMarginOrder().getOrder().getOrderLimitCode());
            resp.getMarginOrder().getOrder().setOrderLimitCode(orderLimitCode);
        }
        
        // marginOrder.order.specificAccountCode
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getSpecificAccountCode())) {
            String specificAccountCode = codeListService.convertExtKeyToKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                    resp.getMarginOrder().getOrder().getSpecificAccountCode());
            resp.getMarginOrder().getOrder().setSpecificAccountCode(specificAccountCode);
        }
        
        // marginOrder.order.settlementMethodCode
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getOrder().getSettlementMethodCode())) {
            String settlementMethodCode = codeListService.convertExtKeyToKey(SETTLEMENT_TYPE, ATHENA,
                    resp.getMarginOrder().getOrder().getSettlementMethodCode());
            resp.getMarginOrder().getOrder().setSettlementMethodCode(settlementMethodCode);
        }
        
        // marginOrder.marginCloseLimitType
        if (!StringUtil.isNullOrEmpty(resp.getMarginOrder().getMarginCloseLimitType())) {
            String marginCloseLimitType = codeListService.convertExtKeyToKey(MARGIN_DUE_DATE, ATHENA,
                    resp.getMarginOrder().getMarginCloseLimitType());
            resp.getMarginOrder().setMarginCloseLimitType(marginCloseLimitType);
        }
        
        // 結果を返します
        return resp;
    }
    
    @Override
    public GetMarginAccountAutoTransferSettingResp getMarginAccountAutoTransferSetting(
            GetMarginAccountAutoTransferSettingReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockMarginOrderServiceImpl.getMarginAccountAutoTransferSetting : {}", hashCode());
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // requestのNULLチェックを行う
            if (request == null) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // headerを設定する
            GetMarginAccountAutoTransferSettingReq.Header header = request.getHeader();
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
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }

        // HTTPのURLを設定する
        String url = this.getUrl(CometApiUtil.getAcc_margin_accounts_getSetting());
        // GET請求を送信する
        OkHttpResponse httpResp = this.get(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        GetMarginAccountAutoTransferSettingResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返します。
            resp = httpResp.getResponseData(GetMarginAccountAutoTransferSettingResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        /* 外部→内部コード変換 */
        // 建余力不足 自動振替設定(米ドル)
        //"0" (false)の場合
        if (false == (resp.getMarginBuyingPowerShortfallCash())) {
            resp.setConvMarginBuyingPowerShortfallCash("0");
            //"1" (true)の場合    
        } else if (true == resp.getMarginBuyingPowerShortfallCash()) {
            resp.setConvMarginBuyingPowerShortfallCash("1");
        }
        
        // 建余力不足 自動振替設定(米国株式)
        //"0" (false)の場合
        if (false == (resp.getMarginBuyingPowerShortfallSecurities())) {
            resp.setConvMarginBuyingPowerShortfallSecurities("0");
            //"1" (true)の場合    
        } else if (true == resp.getMarginBuyingPowerShortfallSecurities()) {
            resp.setConvMarginBuyingPowerShortfallSecurities("1");
        }
        
        // 保証金不足 自動振替設定(米ドル)
        //"0" (false)の場合
        if (false == (resp.getMarginShortfallCash())) {
            resp.setConvMarginShortfallCash("0");
            //"1" (true)の場合    
        } else if (true == resp.getMarginShortfallCash()) {
            resp.setConvMarginShortfallCash("1");
        }
        
        // 保証金不足 自動振替設定(米国株式)
        //"0" (false)の場合
        if (false == (resp.getMarginShortfallSecurities())) {
            resp.setConvMarginShortfallSecurities("0");
            //"1" (true)の場合    
        } else if (true == resp.getMarginShortfallSecurities()) {
            resp.setConvMarginShortfallSecurities("1");
        }
        
        // 結果を返します
        return resp;
    }
    
    @Override
    public CreateMarginAccountAutoTransferSettingResp createMarginAccountAutoTransferSetting(
            CreateMarginAccountAutoTransferSettingReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockMarginOrderServiceImpl.createMarginAccountAutoTransferSetting : {}",
                    hashCode());
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // requestのNULLチェックを行う
            if (request == null) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // headerを設定する
            CreateMarginAccountAutoTransferSettingReq.Header header = request.getHeader();
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
            
            // 必須入力チェック「建余力不足 自動振替設定(米ドル)」
            if (Objects.isNull(request.getParameter().getMarginBuyingPowerShortfallCash())) {
                warnMsg = "marginBuyingPowerShortfallCash is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「建余力不足 自動振替設定(米国株式)」
            if (Objects.isNull(request.getParameter().getMarginBuyingPowerShortfallSecurities())) {
                warnMsg = "marginBuyingPowerShortfallSecurities is null or empty!";
                break;
            }
            
            // 必須入力チェック「保証金不足 自動振替設定(米ドル)」
            if (Objects.isNull(request.getParameter().getMarginShortfallCash())) {
                warnMsg = "marginShortfallCash is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「保証金不足 自動振替設定(米国株式)」
            if (Objects.isNull(request.getParameter().getMarginShortfallSecurities())) {
                warnMsg = "marginShortfallSecurities is null or empty!";
                break;
            }
            
            // 必須入力チェック「現物買付時 株式自動振替先設定」
            if (StringUtil.isNullOrEmpty(request.getParameter().getDepositType())
                    || null == DepositType.getById(request.getParameter().getDepositType())) {
                warnMsg = "depositType is null or empty!";
                break;
            }
            
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        // HTTPのURLを設定する
        String url = this.getUrl(CometApiUtil.getAcc_margin_accounts_createSetting());
        // POST請求を送信する
        OkHttpResponse httpResp = this.post(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        CreateMarginAccountAutoTransferSettingResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返します。
            resp = httpResp.getResponseData(CreateMarginAccountAutoTransferSettingResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        /* 外部→内部コード変換 */
        // 建余力不足 自動振替設定(米ドル)
        //"0" (false)の場合
        if (false == (resp.getMarginBuyingPowerShortfallCash())) {
            resp.setConvMarginBuyingPowerShortfallCash("0");
            //"1" (true)の場合    
        } else if (true == resp.getMarginBuyingPowerShortfallCash()) {
            resp.setConvMarginBuyingPowerShortfallCash("1");
        }
        
        // 建余力不足 自動振替設定(米国株式)
        //"0" (false)の場合
        if (false == (resp.getMarginBuyingPowerShortfallSecurities())) {
            resp.setConvMarginBuyingPowerShortfallSecurities("0");
            //"1" (true)の場合    
        } else if (true == resp.getMarginBuyingPowerShortfallSecurities()) {
            resp.setConvMarginBuyingPowerShortfallSecurities("1");
        }
        
        // 保証金不足 自動振替設定(米ドル)
        //"0" (false)の場合
        if (false == (resp.getMarginShortfallCash())) {
            resp.setConvMarginShortfallCash("0");
            //"1" (true)の場合    
        } else if (true == resp.getMarginShortfallCash()) {
            resp.setConvMarginShortfallCash("1");
        }
        
        // 保証金不足 自動振替設定(米国株式)
        //"0" (false)の場合
        if (false == (resp.getMarginShortfallSecurities())) {
            resp.setConvMarginShortfallSecurities("0");
            //"1" (true)の場合    
        } else if (true == resp.getMarginShortfallSecurities()) {
            resp.setConvMarginShortfallSecurities("1");
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返します
        return resp;
    }
    
    @Override
    public GetForeignStockMarginOrderDetailResp getForeignStockMarginOrderDetail(
            GetForeignStockMarginOrderDetailReq request) throws Exception {

        long start = System.currentTimeMillis();
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockMarginOrderServiceImpl.getForeignStockMarginOrderDetail : {}", hashCode());
        }
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            if (request == null) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }

            // headerを設定する
            GetForeignStockMarginOrderDetailReq.Header header = request.getHeader();

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

            // parameterを設定する
            GetForeignStockMarginOrderDetailReq.Parameter parameter = request.getParameter();
            // 必須入力チェック「注文サブ番号」
            if (StringUtil.isNullOrEmpty(parameter.getOrderSubNo())) {
                warnMsg = "OrderSubNo is null or empty!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        // APIのURLを設定する
        String url = this.getUrl(CometApiUtil.getFs_order_orders_margin_orders_order_sub_no_get_detail());
        // GET請求を送信する
        OkHttpResponse httpResp = this.get(url, request);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }

        GetForeignStockMarginOrderDetailResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返します。
            resp = httpResp.getResponseData(GetForeignStockMarginOrderDetailResp.class);
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
}

