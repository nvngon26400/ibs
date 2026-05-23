package com.sbisec.helios.ap.broker;

import java.util.Map;

import okhttp3.MediaType;

/**
 * <p>
 * Description:OkHttpRequest
 * </p>
 *
 * @author katsuhiko.kagoshima
 * @date 12/23/2022
 */
public interface OkHttpBaseRequest {
    
    public interface CONTENT_TYPE {
    };
    
    public String getUrl();
    
    public void setUrl(String url);
    
    public MediaType getContentType();
    
    public void setContentType(MediaType contentType);
    
    public Map<String, Object> getHeaders();
    
    public void setHeaders(Object obj) throws Exception;
    
    public Map<String, Object> getParameters();
    
    public void setParameters(Object obj) throws Exception;
    
}
