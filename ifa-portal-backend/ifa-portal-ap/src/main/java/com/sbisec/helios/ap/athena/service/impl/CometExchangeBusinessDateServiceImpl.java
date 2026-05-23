package com.sbisec.helios.ap.athena.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.OkHttpResponse;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetIfaExchangeBusinessDateReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetIfaExchangeBusinessDateRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeBusinessDateReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeBusinessDateRes;
import com.sbisec.helios.ap.athena.service.AbstractBaseService;
import com.sbisec.helios.ap.athena.service.CometExchangeBusinessDateService;
import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.athena.utils.CometApiUtil;
import com.sbisec.helios.ap.common.service.CodeListService;

@Service
public class CometExchangeBusinessDateServiceImpl extends AbstractBaseService
        implements CometExchangeBusinessDateService {
    
    private static final Logger LOG = LoggerFactory.getLogger(CometExchangeBusinessDateServiceImpl.class);
    
    @Autowired
    private CodeListService codeListService;
    
    private static final String APITYPE = "Athena";
    
    private static final String FX_GROUP = "FX_GROUP";
    
    public GetIfaExchangeBusinessDateRes getIfaExchangeBusinessDate(GetIfaExchangeBusinessDateReq req)
            throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometExchangeBusinessDateServiceImpl.getIfaExchangeBusinessDate : {}", hashCode());
        }
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request not empty check
            if (null == req) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            // 必須入力チェック「為替グループ」
            if (StringUtil.isNullOrEmpty(req.getParameter().getExchangeGroup())) {
                warnMsg = "ExchangeGroup is not exists or empty!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        //外部コード:Athena転換
        String exchangeGroupAthena = codeListService.convertKeyToExtKey(FX_GROUP, APITYPE,
                req.getParameter().getExchangeGroup());
        req.getParameter().setExchangeGroup(exchangeGroupAthena);
        
        String url = this.getUrl(CometApiUtil.get_ifa_exchange_business_date());
        
        // get要求を送信
        OkHttpResponse httpResp = this.get(url, req);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // 設定応答メッセージ
        GetIfaExchangeBusinessDateRes resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(GetIfaExchangeBusinessDateRes.class);
            
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
    
    /**
     * 営業日情報取得API（リテール向け）
     * @param req
     * @return
     * @throws Exception
     */
    public GetExchangeBusinessDateRes getExchangeBusinessDate(GetExchangeBusinessDateReq req) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometExchangeBusinessDateServiceImpl.getExchangeBusinessDate : {}", hashCode());
        }
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request not empty check
            if (null == req) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            // 必須入力チェック「為替グループ」
            if (StringUtil.isNullOrEmpty(req.getParameter().getExchangeGroup())) {
                warnMsg = "ExchangeGroup is not exists or empty!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        //外部コード:Athena転換
        String exchangeGroupAthena = codeListService.convertKeyToExtKey(FX_GROUP, APITYPE,
                req.getParameter().getExchangeGroup());
        req.getParameter().setExchangeGroup(exchangeGroupAthena);
        
        String url = this.getUrl(CometApiUtil.get_exchange_business_date());
        
        // get要求を送信
        OkHttpResponse httpResp = this.get(url, req);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // 設定応答メッセージ
        GetExchangeBusinessDateRes resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(GetExchangeBusinessDateRes.class);
            
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
