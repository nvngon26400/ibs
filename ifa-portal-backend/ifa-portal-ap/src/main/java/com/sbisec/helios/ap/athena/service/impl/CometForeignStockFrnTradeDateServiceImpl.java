package com.sbisec.helios.ap.athena.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.OkHttpResponse;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetOffsetBusinessDateReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetOffsetBusinessDateResp;
import com.sbisec.helios.ap.athena.service.AbstractBaseService;
import com.sbisec.helios.ap.athena.service.CometForeignStockFrnTradeDateService;
import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.athena.utils.CometApiUtil;

/**
 * マーケット価格情報サービス Service implements.
 * 
 * @author yunhui.zhao
 * @date 02/11/2022
 */
@Service
public class CometForeignStockFrnTradeDateServiceImpl extends AbstractBaseService
        implements CometForeignStockFrnTradeDateService {
    
    private static final Logger LOG = LoggerFactory.getLogger(CometForeignStockFrnTradeDateServiceImpl.class);
    
    public GetOffsetBusinessDateResp getOffsetBusinessDate(GetOffsetBusinessDateReq getOffsetBusinessDateReq)
            throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockFrnTradeDateServiceImpl.getOffsetBusinessDate : {}", hashCode());
        }
        
        // パラメータチェックメッセージ
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // 「リクエスト」：空のチェック
            if (null == getOffsetBusinessDateReq) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // 「国コード」：必須入力チェック、存在チェック
            if (StringUtil.isNullOrEmpty(getOffsetBusinessDateReq.getParameter().getCountryCode())) {
                warnMsg = "CountryCode is null or empty!";
                break;
            }
            //            if (null == CountryCode.getById(getOffsetBusinessDateReq.getParameter().getCountryCode())) {
            //                warnMsg = "CountryCode is not exists!";
            //                break;
            //            }
            
            // 「基準日」：必須入力チェック、フォーマットチェック
            if (StringUtil.isNullOrEmpty(getOffsetBusinessDateReq.getParameter().getBaseDate())) {
                warnMsg = "BaseDate is null or empty!";
                break;
            }
            if (!DateUtil.isParsable(getOffsetBusinessDateReq.getParameter().getBaseDate(),
                    FORMAT_YEAR_MONTH_DAY_DASH)) {
                warnMsg = "BaseDate " + getOffsetBusinessDateReq.getParameter().getBaseDate() + " format is illegal!";
                break;
            }
            
            // 「基準日から何営業日数で計算」：必須入力チェック
            if (getOffsetBusinessDateReq.getParameter().getBusinessDateOffset() == null) {
                warnMsg = "BusinessDateOffset is null or empty!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        // APIのURLを設定する。
        String url = this.getUrl(CometApiUtil.getFs_order_countries_country_code_base_dates_base_date_business_dates());
        
        // GET請求を送信する。
        OkHttpResponse httpResp = this.get(url, getOffsetBusinessDateReq);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // RESPONSEをインスタンスする。
        GetOffsetBusinessDateResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返する。
            resp = httpResp.getResponseData(GetOffsetBusinessDateResp.class);
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
