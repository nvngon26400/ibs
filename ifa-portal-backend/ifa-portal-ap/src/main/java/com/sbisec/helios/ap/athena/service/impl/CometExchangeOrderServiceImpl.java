package com.sbisec.helios.ap.athena.service.impl;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.OkHttpResponse;
import com.sbisec.helios.ap.athena.protocol.exchange.ifa.order.CreateIfaExchangeOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.ifa.order.CreateIfaExchangeOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ConfirmIfaExchangeCreatedOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ConfirmIfaExchangeCreatedOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.CancelExchangeOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.CancelExchangeOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ConfirmExchangeCreatedOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ConfirmExchangeCreatedOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.CreateExchangeOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.CreateExchangeOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeCancelledOrderInitializationReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeCancelledOrderInitializationRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ListExchangeOrdersReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ListExchangeOrdersRes;
import com.sbisec.helios.ap.athena.service.AbstractBaseService;
import com.sbisec.helios.ap.athena.service.CometExchangeOrderService;
import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.athena.utils.CometApiUtil;
import com.sbisec.helios.ap.common.service.CodeListService;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CometExchangeOrderServiceImpl extends AbstractBaseService implements CometExchangeOrderService {
    
    private static final Logger LOG = LoggerFactory.getLogger(CometExchangeOrderServiceImpl.class);
    
    @Autowired
    private CodeListService codeListService;
    
    private static final String APITYPE = "Athena";
    
    private static final String SELL_BUY_TYPE = "SELL_BUY_TYPE";
    
    private static final String FX_TRADE = "FX_TRADE";
    
    private static final String ORDER_CLASS = "ORDER_CLASS";
    
    private static final String FX_DEPOSIT_TYPE = "FX_DEPOSIT_TYPE";
    
    //売買区分(売却)
    private static final String TRADE_TYPE_SELL = "1";
    
    //APIタイプ
    private static final String ATHENA = "Athena";

    //UNSPECIFIED　※業務的な意味なし
    private static final String UNSPECIFIED = "UNSPECIFIED";
    
    //区分.検索用注文状況（為替取引)
    private static final String FX_TRADE_SEARCH_ORDER_STATUS = "FX_TRADE_SEARCH_ORDER_STATUS";
    
    //区分.検索用取引種別（為替取引)
    private static final String FX_TRADE_SEARCH_TRADE_CLASS = "FX_TRADE_SEARCH_TRADE_CLASS";
    
    //区分.検索結果用取引種別（為替取引)
    private static final String FX_TRADE_SEARCH_RESULT_TRADE_CLASS = "FX_TRADE_SEARCH_RESULT_TRADE_CLASS";
    
    private static final String SELL_METHOD = "SELL_METHOD";
    
    private static final String FOREIGN_DEPOSIT_TYPE = "FOREIGN_DEPOSIT_TYPE";
    
    public CheckExchangeCurrencyServiceStatusRes checkExchangeCurrencyServiceStatus(
            CheckExchangeCurrencyServiceStatusReq req) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometExchangeOrderServiceImpl.checkExchangeCurrencyServiceStatus : {}", hashCode());
        }
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request not empty check
            if (null == req) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
/*            
            // 必須入力チェック「サービスグループ」
            if (StringUtil.isNullOrEmpty(req.getParameter().getServiceGroup())) {
                warnMsg = "ServiceGroup is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「サービスタイプ」
            if (StringUtil.isNullOrEmpty(req.getParameter().getServiceType())) {
                warnMsg = "ServiceType is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「通貨コード」
            if (StringUtil.isNullOrEmpty(req.getParameter().getCurrencyCode())) {
                warnMsg = "CurrencyCode is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「売買区分」
            if (StringUtil.isNullOrEmpty(req.getParameter().getBuySellCode())) {
                warnMsg = "BuySellCode is not exists or empty!";
                break;
            }
 */           
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        //内部コード　→　外部コード:Athena転換
        String buySellCodeAthena = codeListService.convertKeyToExtKey(SELL_BUY_TYPE, APITYPE,
                req.getParameter().getBuySellCode());
        req.getParameter().setBuySellCode(buySellCodeAthena);
        
        String url = this.getUrl(CometApiUtil.check_exchange_currency_service_status());
        
        // post要求を送信
        OkHttpResponse httpResp = this.post(url, req);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // 設定応答メッセージ
        CheckExchangeCurrencyServiceStatusRes resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(CheckExchangeCurrencyServiceStatusRes.class);
            
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返す
        return resp;
    }
    
    public CheckExchangeCurrencyServiceStatusRes checkExchangeCurrencyServiceStatusOrderCancel(
            CheckExchangeCurrencyServiceStatusReq req) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometExchangeOrderServiceImpl.checkExchangeCurrencyServiceStatusOrderCancel : {}", hashCode());
        }
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request not empty check
            if (null == req) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // 必須入力チェック「サービスグループ」
            if (StringUtil.isNullOrEmpty(req.getParameter().getServiceGroup())) {
                warnMsg = "ServiceGroup is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「サービスタイプ」
            if (StringUtil.isNullOrEmpty(req.getParameter().getServiceType())) {
                warnMsg = "ServiceType is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「通貨コード」
            if (StringUtil.isNullOrEmpty(req.getParameter().getCurrencyCode())) {
                warnMsg = "CurrencyCode is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「売買区分」
            if (StringUtil.isNullOrEmpty(req.getParameter().getBuySellCode())) {
                warnMsg = "BuySellCode is not exists or empty!";
                break;
            }
            
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        //内部コード　→　外部コード:Athena転換
        String buySellCodeAthena = codeListService.convertKeyToExtKey(FX_TRADE_SEARCH_TRADE_CLASS, APITYPE,
                req.getParameter().getBuySellCode());
        req.getParameter().setBuySellCode(buySellCodeAthena);
        
        String url = this.getUrl(CometApiUtil.check_exchange_currency_service_status());
        
        // post要求を送信
        OkHttpResponse httpResp = this.post(url, req);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // 設定応答メッセージ
        CheckExchangeCurrencyServiceStatusRes resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(CheckExchangeCurrencyServiceStatusRes.class);
            
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返す
        return resp;
    }
    
    public ConfirmIfaExchangeCreatedOrderRes confirmIfaExchangeCreatedOrder(ConfirmIfaExchangeCreatedOrderReq req)
            throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometExchangeOrderServiceImpl.confirmIfaExchangeCreatedOrder : {}", hashCode());
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request not empty check
            if (null == req) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            // Token not empty check
            String token = req.getHeader().getToken();
            if (StringUtil.isNullOrEmpty(token)) {
                warnMsg = "Token is null or empty!";
                break;
            }
            // 必須入力チェック「オペレーターID」
            if (StringUtil.isNullOrEmpty(req.getHeader().getOperator_id())) {
                warnMsg = "Operator_id is not exists or empty!";
                break;
            }
            // 必須入力チェック「通貨コード」
            if (StringUtil.isNullOrEmpty(req.getParameter().getCurrencyCode())) {
                warnMsg = "CurrencyCode is not exists or empty!";
                break;
            }
            // 必須入力チェック「売買区分」
            if (StringUtil.isNullOrEmpty(req.getParameter().getBuySellCode())) {
                warnMsg = "BuySellCode is not exists or empty!";
                break;
            }
            // 必須入力チェック「口座分類」
            if (StringUtil.isNullOrEmpty(req.getParameter().getAccountKind())) {
                warnMsg = "AccountKind is not exists or empty!";
                break;
            }
            // 必須入力チェック「為替注文金額」
            if (StringUtil.isNullOrEmpty(req.getParameter().getOrderAmount())) {
                warnMsg = "OrderAmount is not exists or empty!";
                break;
            }
            // 売却の場合、必須「売却方法区分」
            if (TRADE_TYPE_SELL.equals(req.getParameter().getBuySellCode())
                    && StringUtil.isNullOrEmpty(req.getParameter().getSellMethod())) {
                warnMsg = "sellMethod is not exists or empty!";
                break;
            }
            
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        //外部コード:Athena転換
        String tradeKbnAthena = codeListService.convertKeyToExtKey(SELL_BUY_TYPE, APITYPE,
                req.getParameter().getBuySellCode());
        req.getParameter().setBuySellCode(tradeKbnAthena);
        
        String url = this.getUrl(CometApiUtil.confirm_ifa_exchange_created_order());
        
        // get要求を送信
        OkHttpResponse httpResp = this.post(url, req);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // 設定応答メッセージ
        ConfirmIfaExchangeCreatedOrderRes resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(ConfirmIfaExchangeCreatedOrderRes.class);
            
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返す
        return resp;
    }
    
    @Override
    public CreateIfaExchangeOrderRes createIfaExchangeOrder(CreateIfaExchangeOrderReq req) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometExchangeOrderServiceImpl.createIfaExchangeOrder : {}", hashCode());
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request not empty check
            if (null == req) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // 必須入力チェック「通貨コード」
            if (StringUtil.isNullOrEmpty(req.getParameter().getCurrencyCode())) {
                warnMsg = "CurrencyCode is not exists or empty!";
                break;
            }
            // 必須入力チェック「売買区分」
            if (StringUtil.isNullOrEmpty(req.getParameter().getBuySellCode())) {
                warnMsg = "BuySellCode is not exists or empty!";
                break;
            }
            // 必須入力チェック「口座分類」
            if (StringUtil.isNullOrEmpty(req.getParameter().getAccountKind())) {
                warnMsg = "AccountKind is not exists or empty!";
                break;
            }
            // 必須入力チェック「為替注文金額」
            if (StringUtil.isNullOrEmpty(req.getParameter().getOrderAmount())) {
                warnMsg = "OrderAmount is not exists or empty!";
                break;
            }
            
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        //外部コード:Athena転換
        String tradeKbnAthena = codeListService.convertKeyToExtKey(SELL_BUY_TYPE, APITYPE,
                req.getParameter().getBuySellCode());
        String accountKind = codeListService.convertKeyToExtKey(FOREIGN_DEPOSIT_TYPE, APITYPE,
                req.getParameter().getAccountKind());
        String sellMethod = codeListService.convertKeyToExtKey(SELL_METHOD, APITYPE,
                req.getParameter().getSellMethod());
        req.getParameter().setBuySellCode(tradeKbnAthena);
        req.getParameter().setAccountKind(accountKind);
        req.getParameter().setSellMethod(sellMethod);
        
        String url = this.getUrl(CometApiUtil.create_ifa_exchange_order());
        
        // post要求を送信
        OkHttpResponse httpResp = this.post(url, req);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // 設定応答メッセージ
        CreateIfaExchangeOrderRes resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(CreateIfaExchangeOrderRes.class);
            
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        //外部コード:Athena転換
        String tradeKbn = codeListService.convertExtKeyToKey(SELL_BUY_TYPE, APITYPE, resp.getBuySellCode());
        resp.setBuySellCode(tradeKbn);
        
        //外部コード:Athena転換 為替取引
        String exchangeTradeType = codeListService.convertExtKeyToKey(FX_TRADE, APITYPE, resp.getExchangeTradeType());
        resp.setExchangeTradeType(exchangeTradeType);
        
        //外部コード:Athena転換 注文種別表示
        String orderType = codeListService.convertExtKeyToKey(FX_TRADE_SEARCH_RESULT_TRADE_CLASS, APITYPE,
                resp.getOrderType());
        resp.setOrderType(orderType);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返す
        return resp;
    }
    
    public ConfirmExchangeCreatedOrderRes confirmExchangeCreatedOrder(ConfirmExchangeCreatedOrderReq req)
            throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometExchangeOrderServiceImpl.confirmExchangeCreatedOrder : {}", hashCode());
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request not empty check
            if (null == req) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // token not empty check
            if (StringUtil.isNullOrEmpty(req.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            // token format check
            if (!checkToken(req.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            
            // 必須入力チェック「オペレーターID」
            if (StringUtil.isNullOrEmpty(req.getHeader().getOperator_id())) {
                warnMsg = "OperatorId is not exists or empty!";
                break;
            }
            // 必須入力チェック「為替注文入力情報.通貨コード」
            if (StringUtil.isNullOrEmpty(req.getParameter().getCurrencyCode())) {
                warnMsg = "CurrencyCode is not exists or empty!";
                break;
            }
            // 必須入力チェック「為替注文入力情報.売買区分」
            if (StringUtil.isNullOrEmpty(req.getParameter().getBuySellCode())) {
                warnMsg = "BuySellCode is not exists or empty!";
                break;
            }
            // 必須入力チェック「為替注文入力情報.口座分類」
            if (StringUtil.isNullOrEmpty(req.getParameter().getAccountKind())) {
                warnMsg = "AccountKind is not exists or empty!";
                break;
            }
            // 必須入力チェック「為替注文入力情報.為替注文金額」
            if (StringUtil.isNullOrEmpty(req.getParameter().getOrderAmount())) {
                warnMsg = "OrderAmount is not exists or empty!";
                break;
            }
            
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        //外部コード:Athena転換
        String buySellCodeAthena = codeListService.convertKeyToExtKey(SELL_BUY_TYPE, APITYPE,
                req.getParameter().getBuySellCode());
        req.getParameter().setBuySellCode(buySellCodeAthena);
        
        String url = this.getUrl(CometApiUtil.confirm_exchange_created_order());
        
        // post要求を送信
        OkHttpResponse httpResp = this.post(url, req);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // 設定応答メッセージ
        ConfirmExchangeCreatedOrderRes resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(ConfirmExchangeCreatedOrderRes.class);
            
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        /* 外部コード:Athena転換 */
        // 為替取引
        String convExchangeTradeType = codeListService.convertExtKeyToKey(FX_TRADE, APITYPE,
                resp.getExchangeTradeType());
        resp.setExchangeTradeType(convExchangeTradeType);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返す
        return resp;
    }
    
    public CreateExchangeOrderRes createExchangeOrder(CreateExchangeOrderReq req) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometExchangeOrderServiceImpl.createExchangeOrder : {}", hashCode());
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request not empty check
            if (null == req) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // Token not empty check
            String token = req.getHeader().getToken();
            if (StringUtil.isNullOrEmpty(token)) {
                warnMsg = "Token is null or empty!";
                break;
            }
            
            // OperatorId not empty check
            if (StringUtil.isNullOrEmpty(req.getHeader().getOperator_id())) {
                warnMsg = "OperatorId is null or empty!";
                break;
            }
            
            // 必須入力チェック「為替注文入力情報.通貨コード」
            if (StringUtil.isNullOrEmpty(req.getParameter().getCurrencyCode())) {
                warnMsg = "CurrencyCode is not exists or empty!";
                break;
            }
            // 必須入力チェック「為替注文入力情報.売買区分」
            if (StringUtil.isNullOrEmpty(req.getParameter().getBuySellCode())) {
                warnMsg = "BuySellCode is not exists or empty!";
                break;
            }
            // 必須入力チェック「為替注文入力情報.口座分類」
            if (StringUtil.isNullOrEmpty(req.getParameter().getAccountKind())) {
                warnMsg = "AccountKind is not exists or empty!";
                break;
            }
            // 必須入力チェック「為替注文入力情報.為替注文金額」
            if (StringUtil.isNullOrEmpty(req.getParameter().getOrderAmount())) {
                warnMsg = "OrderAmount is not exists or empty!";
                break;
            }
            
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        //外部コード:Athena転換
        String buySellCodeAthena = codeListService.convertKeyToExtKey(SELL_BUY_TYPE, APITYPE,
                req.getParameter().getBuySellCode());
        req.getParameter().setBuySellCode(buySellCodeAthena);
        
        String url = this.getUrl(CometApiUtil.create_exchange_order());
        
        // post要求を送信
        OkHttpResponse httpResp = this.post(url, req);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // 設定応答メッセージ
        CreateExchangeOrderRes resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(CreateExchangeOrderRes.class);
            
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        //外部コード:Athena転換
        // 売買区分
        if (!StringUtil.isNullOrEmpty(resp.getBuySellCode())) {
            String convBuySellCode = codeListService.convertExtKeyToKey(SELL_BUY_TYPE, APITYPE, resp.getBuySellCode());
            resp.setBuySellCode(convBuySellCode);
        }
        // 為替取引
        if (!StringUtil.isNullOrEmpty(resp.getExchangeTradeType())) {
            String convExchangeTradeType = codeListService.convertExtKeyToKey(FX_TRADE, APITYPE, resp.getExchangeTradeType());
            resp.setExchangeTradeType(convExchangeTradeType);
        }
        // 注文種別表示
        if (!StringUtil.isNullOrEmpty(resp.getOrderType())) {
            String convOrderType = codeListService.convertExtKeyToKey(FX_TRADE_SEARCH_RESULT_TRADE_CLASS, APITYPE,
                    resp.getOrderType());
            resp.setOrderType(convOrderType);
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返す
        return resp;
    }
    
    @Override
    public ListExchangeOrdersRes listExchangeOrders(ListExchangeOrdersReq listExchangeOrdersReq) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometExchangeOrderServiceImpl.ListExchangeOrders : {}", hashCode());
        }
        
        //　パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // listExchangeOrdersReq not empty check
            if (listExchangeOrdersReq == null) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            // token not empty check
            if (StringUtil.isNullOrEmpty(listExchangeOrdersReq.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            // token format check
            if (!checkToken(listExchangeOrdersReq.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
        } while (false);
        
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.getExchange_order_orders());
        
        //　内部コード　→　外部コード　変換
        //注文状況
        if (!StringUtil.isNullOrEmpty(listExchangeOrdersReq.getParameter().getExchangeOrderStatusInput())) {
            String exchangeOrderStatusInputAthena = codeListService.convertKeyToExtKey(FX_TRADE_SEARCH_ORDER_STATUS,
                    ATHENA, listExchangeOrdersReq.getParameter().getExchangeOrderStatusInput());
            listExchangeOrdersReq.getParameter().setExchangeOrderStatusInput(exchangeOrderStatusInputAthena);
        }

        //売買区分
        if (!StringUtil.isNullOrEmpty(listExchangeOrdersReq.getParameter().getBuySellCode())) {
            String buySellAthena = codeListService.convertKeyToExtKey(FX_TRADE_SEARCH_TRADE_CLASS, ATHENA,
                    listExchangeOrdersReq.getParameter().getBuySellCode());
            listExchangeOrdersReq.getParameter().setBuySellCode(buySellAthena);
        }
        
        // get要求を送信
        OkHttpResponse httpResp = this.get(url, listExchangeOrdersReq);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // 設定応答メッセージ
        ListExchangeOrdersRes resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(ListExchangeOrdersRes.class);
            
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        if (CollectionUtils.isNotEmpty(resp.getOrderDetails())) {
            for (int i = 0; i < resp.getOrderDetails().size(); i++) {
                // UNSPECIFIEDの場合はnullと読み替え
                if (UNSPECIFIED.equals(resp.getOrderDetails().get(i).getExchangeProcStatus())) {
                    resp.getOrderDetails().get(i).setExchangeProcStatus(null);
                }

                // 外部コード　→　内部コード　変換
                if (!StringUtil.isNullOrEmpty(resp.getOrderDetails().get(i).getOrderType())) {
                    String orderTypeIfa = codeListService.convertExtKeyToKey(FX_TRADE_SEARCH_RESULT_TRADE_CLASS, ATHENA,
                            resp.getOrderDetails().get(i).getOrderType());
                    resp.getOrderDetails().get(i).setOrderType(orderTypeIfa);
                }
                if (!StringUtil.isNullOrEmpty(resp.getOrderDetails().get(i).getBuySellCode())) {
                    String buySellCodeIfa = codeListService.convertExtKeyToKey(FX_TRADE_SEARCH_TRADE_CLASS, ATHENA,
                            resp.getOrderDetails().get(i).getBuySellCode());
                    resp.getOrderDetails().get(i).setBuySellCode(buySellCodeIfa);
                }
            }
        }
        // 結果を返す
        return resp;
    }
    
    @Override
    public GetExchangeCancelledOrderInitializationRes getExchangeCancelledOrderInitialization(
            GetExchangeCancelledOrderInitializationReq req) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometExchangeOrderServiceImpl.getExchangeCancelledOrderInitialization : {}", hashCode());
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request not empty check
            if (null == req) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            // Token not empty check
            String token = req.getHeader().getToken();
            if (StringUtil.isNullOrEmpty(token)) {
                warnMsg = "Token is null or empty!";
                break;
            }
            // 必須入力チェック「注文番号」
            if (StringUtil.isNullOrEmpty(req.getParameter().getOrderNo())) {
                warnMsg = "OrderNo is not exists or empty!";
                break;
            }
            // 必須入力チェック「営業日」
            if (StringUtil.isNullOrEmpty(req.getParameter().getBusinessDate())) {
                warnMsg = "BusinessDate is not exists or empty!";
                break;
            }
            // 必須入力チェック「通貨ペア」
            if (StringUtil.isNullOrEmpty(req.getParameter().getCurrencyPair())) {
                warnMsg = "CurrencyPair is not exists or empty!";
                break;
            }
            // 必須入力チェック「サイクル番号」
            if (StringUtil.isNullOrEmpty(String.valueOf(req.getParameter().getCycleNumber()))) {
                warnMsg = "CycleNumber is not exists or empty!";
                break;
            }
            // 必須入力チェック「注文イベントID」
            if (StringUtil.isNullOrEmpty(req.getParameter().getOrderEventId())) {
                warnMsg = "OrderEventId is not exists or empty!";
                break;
            }
            
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.get_exchange_cancelled_order_initialization());
        
        // get要求を送信
        OkHttpResponse httpResp = this.get(url, req);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // 設定応答メッセージ
        GetExchangeCancelledOrderInitializationRes resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(GetExchangeCancelledOrderInitializationRes.class);
            
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        //外部コード → 内部コード (売買区分)
        String buySellCode = codeListService.convertExtKeyToKey(FX_TRADE_SEARCH_TRADE_CLASS, APITYPE,
                resp.getBuySellCode());
        resp.setBuySellCode(buySellCode);
        
        // 結果を返す
        return resp;
    }
    
    public CancelExchangeOrderRes cancelExchangeOrder(CancelExchangeOrderReq req) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometExchangeOrderServiceImpl.cancelExchangeOrder : {}", hashCode());
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request not empty check
            if (null == req) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            // Token not empty check
            String token = req.getHeader().getToken();
            if (StringUtil.isNullOrEmpty(token)) {
                warnMsg = "Token is null or empty!";
                break;
            }
            // 必須入力チェック「注文番号」
            if (StringUtil.isNullOrEmpty(req.getParameter().getOrderNo())) {
                warnMsg = "OrderNo is not exists or empty!";
                break;
            }
            // 必須入力チェック「売買区分」
            if (StringUtil.isNullOrEmpty(req.getParameter().getBuySellCode())) {
                warnMsg = "BuySellCode is not exists or empty!";
                break;
            }
            // 必須入力チェック「通貨コード」
            if (StringUtil.isNullOrEmpty(req.getParameter().getCurrencyCode())) {
                warnMsg = "CurrencyCode is not exists or empty!";
                break;
            }
            // 必須入力チェック「営業日」
            if (StringUtil.isNullOrEmpty(req.getParameter().getBusinessDate())) {
                warnMsg = "BusinessDate is not exists or empty!";
                break;
            }
            // 必須入力チェック「サイクル番号」
            if (StringUtil.isNullOrEmpty(String.valueOf(req.getParameter().getCycleNumber()))) {
                warnMsg = "CycleNumber is not exists or empty!";
                break;
            }
            // 必須入力チェック「注文イベントID」
            if (StringUtil.isNullOrEmpty(String.valueOf(req.getParameter().getOrderEventId()))) {
                warnMsg = "OrderEventId is not exists or empty!";
                break;
            }
            
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.cancel_exchange_order());
        
        //　内部コード　→　外部コード　変換
        // 売買区分
        String buySellAthena = codeListService.convertKeyToExtKey(FX_TRADE_SEARCH_TRADE_CLASS, ATHENA,
                req.getParameter().getBuySellCode());
        req.getParameter().setBuySellCode(buySellAthena);
        
        // post要求を送信
        OkHttpResponse httpResp = this.post(url, req);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // 設定応答メッセージ
        CancelExchangeOrderRes resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(CancelExchangeOrderRes.class);
            
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        //外部コード → 内部コード (注文種別表示)
        String orderType = codeListService.convertExtKeyToKey(FX_TRADE_SEARCH_RESULT_TRADE_CLASS, APITYPE,
                resp.getOrderType());
        resp.setOrderType(orderType);
        
        //外部コード → 内部コード (売買区分)
        String buySellCode = codeListService.convertExtKeyToKey(FX_TRADE_SEARCH_TRADE_CLASS, APITYPE,
                resp.getBuySellCode());
        resp.setBuySellCode(buySellCode);
        
        //外部コード → 内部コード (保護区分)
        String depositType = codeListService.convertExtKeyToKey(FX_DEPOSIT_TYPE, APITYPE, resp.getDepositType());
        resp.setDepositType(depositType);
        
        // 結果を返す
        return resp;
    }
}
