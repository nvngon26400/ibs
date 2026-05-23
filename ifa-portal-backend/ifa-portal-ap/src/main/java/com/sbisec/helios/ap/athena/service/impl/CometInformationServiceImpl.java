package com.sbisec.helios.ap.athena.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.OkHttpResponse;
import com.sbisec.helios.ap.athena.enums.CountryCode;
import com.sbisec.helios.ap.athena.protocol.information.CreateMarketPriceTicketReq;
import com.sbisec.helios.ap.athena.protocol.information.CreateMarketPriceTicketResp;
import com.sbisec.helios.ap.athena.protocol.information.GetMarketPriceHashReq;
import com.sbisec.helios.ap.athena.protocol.information.GetMarketPriceHashResp;
import com.sbisec.helios.ap.athena.protocol.information.ListMarketPricesReq;
import com.sbisec.helios.ap.athena.protocol.information.ListMarketPricesResp;
import com.sbisec.helios.ap.athena.service.AbstractBaseService;
import com.sbisec.helios.ap.athena.service.CometInformationService;
import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.athena.utils.CometApiUtil;
import com.sbisec.helios.ap.common.service.CodeListService;

/**
 * マーケット価格情報サービス Service implements.
 * 
 * @author yunhui.zhao
 * @date 02/11/2022
 */
@Service
public class CometInformationServiceImpl extends AbstractBaseService implements CometInformationService {
    
    private static final Logger LOG = LoggerFactory.getLogger(CometInformationServiceImpl.class);
    
    @Autowired
    private CodeListService codelistservice;
    
    /** APIタイプ：Athena */
    private static final String ATHENA = "Athena";
    
    /** 区分.現在値ティック */
    private static final String CURRENT_TICK = "CURRENT_TICK";
    
    @Override
    public ListMarketPricesResp listMarketPrices(ListMarketPricesReq listMarketPricesReq) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometInformationServiceImpl.listMarketPrices : {}", hashCode());
        }
        
        // パラメータチェックメッセージ
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // 「リクエスト」：空のチェック
            if (null == listMarketPricesReq) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // 「Hash Token」：必須入力チェック
            if (StringUtil.isNullOrEmpty(listMarketPricesReq.getHeader().getHash_token())) {
                warnMsg = "Hash_token is null or empty!";
                break;
            }
            
            // 「国コード」：存在チェック(国コードがNULLではない場合)
            if (!StringUtil.isNullOrEmpty(listMarketPricesReq.getParameter().getCountryCode())
                    && null == CountryCode.getById(listMarketPricesReq.getParameter().getCountryCode())) {
                warnMsg = "CountryCode is not exists!";
                break;
            }
            
            // 「RICリスト」：必須入力チェック
            String[] ric = listMarketPricesReq.getParameter().getRics();
            if (null == ric || ric.length == 0) {
                warnMsg = "ric is null or empty!";
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        // APIのURLを設定する。
        String url = this.getUrl(CometApiUtil.getInfo_market_price_prices());
        
        // GET請求を送信する。
        OkHttpResponse httpResp = this.get(url, listMarketPricesReq);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // RESPONSEをインスタンスする。
        ListMarketPricesResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返する。
            resp = httpResp.getResponseData(ListMarketPricesResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // ティック矢印(アップorダウン) 外部コード→内部コード
        if (resp != null) {
            for (int i = 0; i < resp.getMarketPrices().size(); i++) {
                String tmp = codelistservice.convertExtKeyToKey(CURRENT_TICK, ATHENA,
                        resp.getMarketPrices().get(i).getPrice().getTickArrow());
                resp.getMarketPrices().get(i).getPrice().setTickArrow(tmp);
            }
        }
        
        // 結果を返する。
        return resp;
    }
    
    @Override
    public CreateMarketPriceTicketResp createMarketPriceTicket(CreateMarketPriceTicketReq createMarketPriceTicketReq)
            throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometInformationServiceImpl.createMarketPriceTicket : {}", hashCode());
        }
        
        // パラメータチェックメッセージ
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // 「リクエスト」：空のチェック
            if (null == createMarketPriceTicketReq) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // 「Token」：必須入力チェック、正確性チェック
            if (StringUtil.isNullOrEmpty(createMarketPriceTicketReq.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            if (!checkToken(createMarketPriceTicketReq.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            
            // 「ip」：必須入力チェック
            if (StringUtil.isNullOrEmpty(createMarketPriceTicketReq.getHeader().getIp())) {
                warnMsg = "Ip is null or empty!";
                break;
            }
            
            // 「App User Agent」：必須入力チェック
            if (StringUtil.isNullOrEmpty(createMarketPriceTicketReq.getHeader().getAppUserAgent())) {
                warnMsg = "AppUserAgent is null or empty!";
                break;
            }
            
            // 「国コード」：必須入力チェック、存在チェック
            if (StringUtil.isNullOrEmpty(createMarketPriceTicketReq.getParameter().getCountryCode())) {
                warnMsg = "CountryCode is null or empty!";
                break;
            }
            if (null == CountryCode.getById(createMarketPriceTicketReq.getParameter().getCountryCode())) {
                warnMsg = "CountryCode is not exists!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        // APIのURLを設定する。
        String url = this.getUrl(CometApiUtil.getInfo_market_price_tickets());
        
        // POST請求を送信する。
        OkHttpResponse httpResp = this.post(url, createMarketPriceTicketReq);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // RESPONSEをインスタンスする。
        CreateMarketPriceTicketResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返する。
            resp = httpResp.getResponseData(CreateMarketPriceTicketResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返する。
        return resp;
    }
    
    public GetMarketPriceHashResp getMarketPriceHash(GetMarketPriceHashReq getMarketPriceHashReq) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometInformationServiceImpl.getMarketPriceHash : {}", hashCode());
        }
        
        // パラメータチェックメッセージ
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // 「リクエスト」：空のチェック
            if (null == getMarketPriceHashReq) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // 「Token」：必須入力チェック、正確性チェック
            if (StringUtil.isNullOrEmpty(getMarketPriceHashReq.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            if (!checkToken(getMarketPriceHashReq.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            
            // 「国コード」：必須入力チェック、存在チェック
            if (StringUtil.isNullOrEmpty(getMarketPriceHashReq.getParameter().getCountryCode())) {
                warnMsg = "CountryCode is null or empty!";
                break;
            }
            if (null == CountryCode.getById(getMarketPriceHashReq.getParameter().getCountryCode())) {
                warnMsg = "CountryCode is not exists!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        // APIのURLを設定する。
        String url = this.getUrl(CometApiUtil.getInfo_market_price_countries_country_code_price_hashes());
        
        // GET請求を送信する。
        OkHttpResponse httpResp = this.get(url, getMarketPriceHashReq);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // RESPONSEをインスタンスする。
        GetMarketPriceHashResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返する。
            resp = httpResp.getResponseData(GetMarketPriceHashResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返する。
        return resp;
    }
    
}
