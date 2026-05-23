package com.sbisec.helios.ap.athena.service.impl;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.OkHttpResponse;
import com.sbisec.helios.ap.athena.enums.CountryCode;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockAttentionSecuritiesReq;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockAttentionSecuritiesResp;
import com.sbisec.helios.ap.athena.service.AbstractBaseService;
import com.sbisec.helios.ap.athena.service.CometForeignStockAttentionSecuritiesService;
import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.athena.utils.CometApiUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 外国株式銘柄サービス Service implements
 * 
 * @author shuchen.xin
 * @date 02/16/2022
 */
@Service
public class CometForeignStockAttentionSecuritiesServiceImpl extends AbstractBaseService
        implements CometForeignStockAttentionSecuritiesService {
    
    private static final Logger LOG = LoggerFactory.getLogger(CometForeignStockAttentionSecuritiesServiceImpl.class);
    
    @Override
    public GetForeignStockAttentionSecuritiesResp getForeignStockAttentionSecurities(
            GetForeignStockAttentionSecuritiesReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockAttentionSecuritiesServiceImpl.getForeignStockAttentionSecurities : {}",
                    hashCode());
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
        
        String url = this.getUrl(CometApiUtil.getFs_securities_countries_securities_attention_securities());
        
        // get要求を送信
        OkHttpResponse httpResp = this.get(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        GetForeignStockAttentionSecuritiesResp resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(GetForeignStockAttentionSecuritiesResp.class);
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
