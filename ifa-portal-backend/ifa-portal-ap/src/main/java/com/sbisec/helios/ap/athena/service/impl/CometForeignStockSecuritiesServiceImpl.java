package com.sbisec.helios.ap.athena.service.impl;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.OkHttpResponse;
import com.sbisec.helios.ap.athena.enums.CountryCode;
import com.sbisec.helios.ap.athena.enums.MarginCode;
import com.sbisec.helios.ap.athena.enums.MarketCode;
import com.sbisec.helios.ap.athena.enums.SearchKeywordMatchType;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockSecuritiesReq;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockSecuritiesResp;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.ListForeignStockSecuritiesReq;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.ListForeignStockSecuritiesResp;
import com.sbisec.helios.ap.athena.service.AbstractBaseService;
import com.sbisec.helios.ap.athena.service.CometForeignStockSecuritiesService;
import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.athena.utils.CometApiUtil;
import com.sbisec.helios.ap.common.service.CodeListService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 外国株式銘柄サービス Service implements
 *
 * @author xin.li
 * @date 02/11/2022
 */
@Service
public class CometForeignStockSecuritiesServiceImpl extends AbstractBaseService
        implements CometForeignStockSecuritiesService {
    
    private static final Logger LOG = LoggerFactory.getLogger(CometForeignStockSecuritiesServiceImpl.class);
    
    @Autowired
    private CodeListService codeListService;
    
    private static final String APITYPE = "Athena";
    
    private static final String BRAND_SEARCH_METHOD = "BRAND_SEARCH_METHOD";
    
    @Override
    public GetForeignStockSecuritiesResp getForeignStockSecurities(GetForeignStockSecuritiesReq request)
            throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockSecuritiesServiceImpl.getForeignStockSecurities : {}", hashCode());
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
        
        String url = this.getUrl(CometApiUtil.getFs_securities_countries_country_code_securities_securities_code());
        
        // get要求を送信
        OkHttpResponse httpResp = this.get(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        GetForeignStockSecuritiesResp resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(GetForeignStockSecuritiesResp.class);
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
    
    @Override
    public ListForeignStockSecuritiesResp listForeignStockSecurities(
            ListForeignStockSecuritiesReq listForeignStockSecuritiesReq) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockSecuritiesServiceImpl.listForeignStockSecurities : {}", hashCode());
        }
        
        //内部コード　→　外部コード:Athena転換
        String matchTypeAthena = codeListService.convertKeyToExtKey(BRAND_SEARCH_METHOD, APITYPE,
                listForeignStockSecuritiesReq.getParameter().getMatchType());
        listForeignStockSecuritiesReq.getParameter().setMatchType(matchTypeAthena);
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            
            // 「リクエスト」：空のチェック
            if (null == listForeignStockSecuritiesReq) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // 入力チェック「国コード」
            String countryCode = listForeignStockSecuritiesReq.getParameter().getCountryCode();
            if (!StringUtil.isNullOrEmpty(countryCode) && null == CountryCode.getById(countryCode)) {
                warnMsg = "CountryCode " + countryCode + " is not exists!";
                break;
            }
            
            // 入力チェック「検索用キーワード」
            // （最大 384 バイト、大体 128 つ UTF-8 文字に相当する）
            String searchKeyword = listForeignStockSecuritiesReq.getParameter().getSearchKeyword();
            if (!StringUtil.isNullOrEmpty(searchKeyword)
                    && searchKeyword.length() > SECURITIES_SEARCHKEYWORD_MAX_LENGTH) {
                warnMsg = "SearchKeyword " + searchKeyword + " is too long!" + searchKeyword.length();
                break;
            }
            
            // 入力チェック「マッチ種別」
            String matchType = listForeignStockSecuritiesReq.getParameter().getMatchType();
            if (!StringUtil.isNullOrEmpty(matchType) && null == SearchKeywordMatchType.getById(matchType)) {
                warnMsg = "MatchType " + matchType + " is not exists!";
                break;
            }
            
            // 入力チェック「市場コード」
            String marketCode = listForeignStockSecuritiesReq.getParameter().getMarketCode();
            if (!StringUtil.isNullOrEmpty(marketCode) && null == MarketCode.getById(marketCode)) {
                warnMsg = "MarketCode " + marketCode + " is not exists!";
                break;
            }
            
            // 入力チェック「信用取引コード」
            String marginCode = listForeignStockSecuritiesReq.getParameter().getMarginCode();
            if (!StringUtil.isNullOrEmpty(marginCode) && null == MarginCode.getById(marginCode)) {
                warnMsg = "MarginCode " + marginCode + " is not exists!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        // APIのパースを設定する
        String url = this.getUrl(CometApiUtil.getFs_securities());
        // 検索処理を行って、結果を設定する
        OkHttpResponse httpResp = this.get(url, listForeignStockSecuritiesReq);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // APIの返す結果集を新規作成する
        ListForeignStockSecuritiesResp resp = null;
        try {
            // APIの返す結果を利用して、ListForeignStockSecuritiesRespを設定する
            resp = httpResp.getResponseData(ListForeignStockSecuritiesResp.class);
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
