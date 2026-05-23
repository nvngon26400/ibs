package com.sbisec.helios.ap.athena.service.impl;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.OkHttpResponse;
import com.sbisec.helios.ap.athena.protocol.fstock.lending.GetForeignStockLendingSubscribedStatusReq;
import com.sbisec.helios.ap.athena.protocol.fstock.lending.GetForeignStockLendingSubscribedStatusResp;
import com.sbisec.helios.ap.athena.service.AbstractBaseService;
import com.sbisec.helios.ap.athena.service.CometForeignStockLendingService;
import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.athena.utils.CometApiUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 外国株式貸株サービス Service implements
 *
 * @author SCSK川崎
 * @date 03/22/2024
 */
@Service
public class CometForeignStockLendingServiceImpl 
        extends AbstractBaseService implements CometForeignStockLendingService {
    
    private static final Logger LOG = LoggerFactory.getLogger(CometForeignStockLendingServiceImpl.class);
    
    @Override
    public GetForeignStockLendingSubscribedStatusResp getForeignStockLendingSubscribedStatus(
            GetForeignStockLendingSubscribedStatusReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockLendingServiceImpl.getForeignStockLendingSubscribedStatus : {}", hashCode());
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request non empty check.
            if (null == request) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // headerを設定する
            GetForeignStockLendingSubscribedStatusReq.Header header = request.getHeader();
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
        
        String url = this.getUrl(CometApiUtil.getFs_lending_stock_lendings_getStatus());
        
        // get要求を送信
        OkHttpResponse httpResp = this.get(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        GetForeignStockLendingSubscribedStatusResp resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(GetForeignStockLendingSubscribedStatusResp.class);
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
