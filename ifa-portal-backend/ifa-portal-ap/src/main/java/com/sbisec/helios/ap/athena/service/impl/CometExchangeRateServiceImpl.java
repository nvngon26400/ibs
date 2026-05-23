package com.sbisec.helios.ap.athena.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.OkHttpResponse;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeRatesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeRatesRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListIfaExchangeTradeRatesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListIfaExchangeTradeRatesRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.dto.IfaExchangeTradeRate;
import com.sbisec.helios.ap.athena.service.AbstractBaseService;
import com.sbisec.helios.ap.athena.service.CometExchangeRateService;
import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.athena.utils.CometApiUtil;
import com.sbisec.helios.ap.common.service.CodeListService;

@Service
public class CometExchangeRateServiceImpl extends AbstractBaseService implements CometExchangeRateService {
    
    private static final Logger LOG = LoggerFactory.getLogger(CometExchangeRateServiceImpl.class);
    
    @Autowired
    private CodeListService codeListService;
    
    private static final String APITYPE = "Athena";
    
    private static final String SELL_BUY_TYPE = "SELL_BUY_TYPE";
    
    private static final String FX_RATE_ADDITIONAL_TYPE = "FX_RATE_ADDITIONAL_TYPE";
    
    public ListIfaExchangeTradeRatesRes listIfaExchangeTradeRates(ListIfaExchangeTradeRatesReq req) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometExchangeRateServiceImpl.listIfaExchangeTradeRates : {}", hashCode());
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
            
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.list_ifa_exchange_trade_rates());
        
        // get要求を送信
        OkHttpResponse httpResp = this.get(url, req);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // 設定応答メッセージ
        ListIfaExchangeTradeRatesRes resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(ListIfaExchangeTradeRatesRes.class);
            
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        IfaExchangeTradeRate ifaExchangeTradeRate = resp.getIfaExchangeTradeRates().get(0);
        //外部コード:Athena転換 (区分.為替レート上乗せ区分)
        String adjustType = codeListService.convertExtKeyToKey(FX_RATE_ADDITIONAL_TYPE, APITYPE,
                ifaExchangeTradeRate.getAdjustType());
        resp.getIfaExchangeTradeRates().get(0).setAdjustType(adjustType);
        
        // 結果を返す
        return resp;
        
    }
    
    public ListExchangeTradeRatesRes listExchangeTradeRates(ListExchangeTradeRatesReq req) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometExchangeRateServiceImpl.listExchangeTradeRates : {}", hashCode());
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request not empty check
            if (null == req) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        /* 内部コード→外部コード */
        // 売買区分
        if (!StringUtil.isNullOrEmpty(req.getParameter().getBuySellCode())) {
            String buySellCodeAthena = codeListService.convertKeyToExtKey(SELL_BUY_TYPE, APITYPE,
                    req.getParameter().getBuySellCode());
            req.getParameter().setBuySellCode(buySellCodeAthena);
        }
        
        String url = this.getUrl(CometApiUtil.list_exchange_trade_rates());
        
        // get要求を送信
        OkHttpResponse httpResp = this.get(url, req);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // 設定応答メッセージ
        ListExchangeTradeRatesRes resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(ListExchangeTradeRatesRes.class);
            
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        /* 外部コード→内部コード変換 */
        if (CollectionUtils.isNotEmpty(resp.getExchangeTradeRates())) {
            for (int i = 0; i < resp.getExchangeTradeRates().size(); i++) {
                // レート情報.上乗せ区分
                if (!StringUtil.isNullOrEmpty(resp.getExchangeTradeRates().get(i).getAdjustType())) {
                    String convAdjustType = codeListService.convertExtKeyToKey(FX_RATE_ADDITIONAL_TYPE, APITYPE,
                            resp.getExchangeTradeRates().get(i).getAdjustType());
                    resp.getExchangeTradeRates().get(i).setAdjustType(convAdjustType);
                }
            }
        }
        // 結果を返す
        return resp;
    }
}
