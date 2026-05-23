package com.sbisec.helios.ap.broker.service.impl;

import java.util.Map;

import com.sbisec.helios.ap.broker.OkHttpBaseResponse;
import com.sbisec.helios.ap.broker.PapyOkHttpClientWrapper;
import com.sbisec.helios.ap.broker.PapyOkHttpRequest;
import com.sbisec.helios.ap.broker.PapyOkHttpResponse;
import com.sbisec.helios.ap.broker.protocol.PapyBaseRequest;
import com.sbisec.helios.ap.broker.util.PapyConfig;
import com.sbisec.helios.ap.broker.util.PapyConnectionException;
import com.sbisec.helios.ap.broker.util.PapyValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PapyConnectionBaseService .
 * 
 * @author katsuhiko.kagoshima
 * @date 12/23/2022
 */
public abstract class PapyConnectionBaseService {
    
    private static final Logger LOG = LoggerFactory.getLogger(PapyConnectionBaseService.class);
    
    /** Papy api response error message key */
    private static final String PAPY_ERROR_MSG_KEY_MESSAGE = "errorMessage";
    
    private static final String PAPY_ERROR_MSG_KEY_ERRORCODE = "errorCode";
    
    /**
     * Get url address
     * 
     * @param api 必須『PAPY API - interface - Api』
     * @return
     */
    protected final String getUrl(String api) {
        
        return PapyConfig.getHostProtocol() + "://" + PapyConfig.getHostIp() + ":" + PapyConfig.getHostPort() + api;
    }
    
    /**
     * 
     * @param url API
     * @param req 検索条件</BR>
     * req.getHeader() 『Header』</BR>
     * req.getParameter() 『パラメータ』
     * @return OkHttpResponse
     * @throws Exception
     */
    protected final PapyOkHttpResponse post(String url, PapyBaseRequest req) throws Exception {
        
        // Create Papy's request protocol class
        PapyOkHttpRequest request = new PapyOkHttpRequest();
        // Set URL
        request.setUrl(url);
        // Set request header
        request.setHeaders(req.getHeader());
        // Set request parameters
        request.setParameters(req.getParameter());
        // Execute get request
        PapyOkHttpResponse res = null;
        
        //API接続でエラーが発生した場合は、PapyConnectionExceptionとして処理する。
        try {
            res = PapyOkHttpClientWrapper.getInstance().post(request);
        } catch (Exception e) {
            LOG.debug("Api connection failed: ", e);
            throw new PapyConnectionException();
        }
        
        // Check Response
        checkResponseExcrption(res);
        
        // Return response.
        return res;
    }
    
    @SuppressWarnings("unchecked")
    private void checkResponseExcrption(OkHttpBaseResponse res) throws Exception {
        
        if (null == res) {
            LOG.warn("Papy api Response is null!");
            throw new PapyConnectionException("Response is null!");
        } else if (!res.getSuccessful()) {
            LOG.warn("Api connection failed, HTTP Status:[{}]", res.getStatusCode());
            throw new PapyConnectionException();
        } else {
            Map<String, Object> responseData = res.getResponseData(Map.class);
            
            // when PAPY_ERROR_MSG_KEY_ERRORCODE is exists 
            //       And PAPY_ERROR_MSG_KEY_ERRORCODE value is not null
            // then throw PapyValidationException pve
            if ((responseData.containsKey(PAPY_ERROR_MSG_KEY_ERRORCODE)
                    && !"".equals((String) responseData.get(PAPY_ERROR_MSG_KEY_ERRORCODE)))
                    || (responseData.containsKey(PAPY_ERROR_MSG_KEY_MESSAGE)
                            && !"".equals((String) responseData.get(PAPY_ERROR_MSG_KEY_MESSAGE)))) {
                
                PapyValidationException pve = new PapyValidationException();
                
                // set errorCode
                pve.setErrorCode((String) responseData.get(PAPY_ERROR_MSG_KEY_ERRORCODE));
                
                // set errorMessage 
                pve.setErrorMessage((String) responseData.get(PAPY_ERROR_MSG_KEY_MESSAGE));
                
                LOG.warn("Error Code:{}, Error Message: {}", pve.getErrorCode(), pve.getErrorMessage());
                throw pve;
            }
        }
        LOG.debug("Get papy response data : success");
    }
    
    /**
     * 口座番号を左0埋めで7桁の変換する。
     * @param accountNo 変換の口座番号
     * @return 変換後の口座番号
     */
    protected String paddingAccountNo(String accountNo) {
        
        return String.format("%07d", Integer.valueOf(accountNo));
    }
}
