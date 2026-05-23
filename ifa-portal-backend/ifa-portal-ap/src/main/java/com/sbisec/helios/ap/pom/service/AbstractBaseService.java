package com.sbisec.helios.ap.pom.service;

import java.util.Objects;

import com.sbisec.helios.ap.pom.OkHttpClientWrapper;
import com.sbisec.helios.ap.pom.OkHttpRequest;
import com.sbisec.helios.ap.pom.OkHttpResponse;
import com.sbisec.helios.ap.pom.utils.PomConfig;
import com.sbisec.helios.ap.pom.utils.PomException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * AbstractBaseService.
 * 
 * 
 */
public abstract class AbstractBaseService {
    
    private static final Logger LOG = LoggerFactory.getLogger(AbstractBaseService.class);
    
    /**
     * Get url address
     * 
     * @param api API 
     * @return URL
     */
    protected final String getUrl(String api) {
        
        return PomConfig.getHostProtocol() + "://" + PomConfig.getHostIp() + ":" + PomConfig.getHostPort() + api;
    }
    
    /**
     * 
     * @param url API
     * @param req 検索条件
     * @return OkHttpResponse
     * @throws Exception 例外発生時
     */
    protected final OkHttpResponse get(String url, Object req) throws Exception {
        
        // Create Pom's request protocol class
        OkHttpRequest request = new OkHttpRequest();
        // Set URL
        request.setUrl(url);
        // Set request parameters
        request.setParameters(req);
        var sw = new StopWatch();
        sw.start();
        if (LOG.isDebugEnabled()) {
            LOG.debug("request url : {}", url);
        }
        // Execute get request
        OkHttpResponse res = OkHttpClientWrapper.getInstance().get(request);
        sw.stop();
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {},{}", url, sw.prettyPrint());
        }
        // Check Response
        checkResponseException(res);
        
        // Return response.
        return res;
        
    }
    
    /**
     * 
     * @param url API
     * @param req 検索条件
     * @return OkHttpResponse
     * @throws Exception 例外発生時
     */
    protected final OkHttpResponse post(String url, Object req) throws Exception {
        
        // Create Pom's request protocol class
        OkHttpRequest request = new OkHttpRequest();
        // Set URL
        request.setUrl(url);
        // Set request parameters
        request.setParameters(req);
        var sw = new StopWatch();
        sw.start();
        if (LOG.isDebugEnabled()) {
            LOG.debug("request ->{}", url);
        }
        // Execute get request
        OkHttpResponse res = OkHttpClientWrapper.getInstance().post(request);
        sw.stop();
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {},{}", url, sw.prettyPrint());
        }
        
        // Check Response
        checkResponseException(res);
        
        // Return response.
        return res;
    }
    
    /**
     * 
     * @param url API
     * @param req 検索条件
     * @return OkHttpResponse
     * @throws Exception 例外発生時
     */
    protected final OkHttpResponse put(String url, Object req) throws Exception {
        
        // Create Pom's request protocol class
        OkHttpRequest request = new OkHttpRequest();
        // Set URL
        request.setUrl(url);
        // Set request parameters
        request.setParameters(req);
        var sw = new StopWatch();
        sw.start();
        if (LOG.isDebugEnabled()) {
            LOG.debug("request ->{}", url);
        }
        // Execute get request
        OkHttpResponse res = OkHttpClientWrapper.getInstance().put(request);
        sw.stop();
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {},{}", url, sw.prettyPrint());
        }
        
        // Check Response
        checkResponseException(res);
        
        // Return response.
        return res;
    }
    
    /**
     * 
     * @param url API
     * @param req 検索条件
     * @return OkHttpResponse
     * @throws Exception 例外発生時
     */
    protected final OkHttpResponse delete(String url, Object req) throws Exception {
        
        // Create Pom's request protocol class
        OkHttpRequest request = new OkHttpRequest();
        // Set URL
        request.setUrl(url);
        // Set request parameters
        request.setParameters(req);
        var sw = new StopWatch();
        sw.start();
        if (LOG.isDebugEnabled()) {
            LOG.debug("request ->{}", url);
        }
        // Execute get request
        OkHttpResponse res = OkHttpClientWrapper.getInstance().delete(request);
        sw.stop();
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {},{}", url, sw.prettyPrint());
        }
        // Check Response
        checkResponseException(res);
        
        // Return response.
        return res;
    }
    
    @SuppressWarnings("unchecked")
    private void checkResponseException(OkHttpResponse res) throws Exception {
        
        if (Objects.isNull(res)) {
            LOG.warn("Pom api Response is null!");
            throw new PomException("Response is null!");
        }
        if (!res.getSuccessful()) {
            LOG.error("Pom Exception request failed:{}", res.getResponsData());
            throw new PomException(res.getResponsData());
        }
    }
    
}
