package com.sbisec.helios.ap.athena.service.impl;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.OkHttpResponse;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetExchangeTradeCurrencyReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetExchangeTradeCurrencyRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeCurrenciesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeCurrenciesRes;
import com.sbisec.helios.ap.athena.service.AbstractBaseService;
import com.sbisec.helios.ap.athena.service.CometExchangeCurrencyService;
import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.athena.utils.CometApiUtil;
import com.sbisec.helios.ap.common.service.CodeListService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CometExchangeCurrencyServiceImpl extends AbstractBaseService implements CometExchangeCurrencyService{
    
    private static final Logger LOG = LoggerFactory.getLogger(CometExchangeCurrencyServiceImpl.class);
    
    @Autowired
    private CodeListService codeListService;
    
    private static final String APITYPE = "Athena";
    
    private static final String FX_GROUP = "FX_GROUP";
    
    
    public GetExchangeTradeCurrencyRes getExchangeTradeCurrency(GetExchangeTradeCurrencyReq req) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometExchangeCurrencyServiceImpl.getExchangeTradeCurrency : {}", hashCode());
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
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.get_exchange_trade_currency());
        
        // get要求を送信
        OkHttpResponse httpResp = this.get(url, req);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // 設定応答メッセージ
        GetExchangeTradeCurrencyRes resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(GetExchangeTradeCurrencyRes.class);
            
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        
        //外部コード:Athena転換 (区分.為替グループ)
        String exchangeGroup = codeListService.convertExtKeyToKey(FX_GROUP, APITYPE, resp.getExchangeGroup());
        resp.setExchangeGroup(exchangeGroup);
        
        // 結果を返す
        return resp;
        
    }

    
    @Override
    public ListExchangeTradeCurrenciesRes ListExchangeTradeCurrencies(
            ListExchangeTradeCurrenciesReq listExchangeTradeCurrenciesReq) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometExchangeCurrencyServiceImpl.ListExchangeTradeCurrencies : {}", hashCode());
        }
        
        String url = this.getUrl(CometApiUtil.getExchange_master_exchange_trade_currencies());
        
        //　内部コード　→　外部コード　変換 不要
        
        // get要求を送信
        OkHttpResponse httpResp = this.get(url, listExchangeTradeCurrenciesReq);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // 設定応答メッセージ
        ListExchangeTradeCurrenciesRes resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(ListExchangeTradeCurrenciesRes.class);
            
            // 外部コード　→　内部コード　変換　不要
            
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
}
