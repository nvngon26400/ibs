package com.sbisec.helios.ap.athena.service.impl;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.OkHttpResponse;
import com.sbisec.helios.ap.athena.enums.CountryCode;
import com.sbisec.helios.ap.athena.enums.ShortableStockTradeStatus;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ListShortableStocksReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ListShortableStocksResp;
import com.sbisec.helios.ap.athena.service.AbstractBaseService;
import com.sbisec.helios.ap.athena.service.CometForeignStockShortableStockService;
import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.athena.utils.CometApiUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 外国株式信用銘柄サービス Service implements.
 * 
 * @author mengzhe.li
 * @date 03/09/2022
 */
@Service
public class CometForeignStockShortableStockServiceImpl extends AbstractBaseService
        implements CometForeignStockShortableStockService {
    
    private static final Logger LOG = LoggerFactory.getLogger(CometForeignStockShortableStockServiceImpl.class);
    
    @Override
    public ListShortableStocksResp listShortableStocks(ListShortableStocksReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockShortableStockServiceImpl.listShortableStocks : {}", hashCode());
        }
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            if (request == null) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            // parameterを設定する
            ListShortableStocksReq.Parameter parameter = request.getParameter();
            
            // 入力チェック「必須」 国コード
            String countryCode = parameter.getCountryCode();
            if (StringUtil.isNullOrEmpty(countryCode) || CountryCode.getById(countryCode) == null) {
                warnMsg = "CountryCode " + countryCode + " is not exists!";
                break;
            }
            // 入力チェック「検索用キーワード」 （最大 384 バイト、大体 128 つ UTF-8 文字に相当する）
            String searchKeyword = parameter.getSearchKeyword();
            if (!StringUtil.isNullOrEmpty(searchKeyword)
                    && searchKeyword.length() > SECURITIES_SEARCHKEYWORD_MAX_LENGTH) {
                warnMsg = "SearchKeyword " + searchKeyword + " is too long!" + searchKeyword.length();
                break;
            }
            
            // 銘柄信用売建可能チェック
            if (!StringUtil.isNullOrEmpty(parameter.getTradeStatus())
                    && null == ShortableStockTradeStatus.getById(parameter.getTradeStatus())) {
                warnMsg = "TradeStatus" + parameter.getTradeStatus() + "is not exists!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        // APIのURLを設定する
        String url = this.getUrl(CometApiUtil.getFs_securities_shortable_stocks());
        // GET請求を送信する
        OkHttpResponse httpResp = this.get(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        ListShortableStocksResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返します。
            resp = httpResp.getResponseData(ListShortableStocksResp.class);
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
