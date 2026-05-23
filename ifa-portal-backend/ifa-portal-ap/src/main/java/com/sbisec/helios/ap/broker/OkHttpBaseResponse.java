package com.sbisec.helios.ap.broker;

/**
 * OkHttpResponse
 *
 * @author katsuhiko.kagoshima
 * @date 12/23/2022
 */
public interface OkHttpBaseResponse {
    
    public Boolean getSuccessful();
    
    public void setSuccessful(Boolean successful);
    
    public Integer getStatusCode();
    
    public void setStatusCode(Integer statusCode);
    
    public String getResponsData();
    
    public <T> T getResponseData(Class<T> clazz) throws Exception;
    
    public void setResponseData(String responseMsg);
}
