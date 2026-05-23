package com.sbisec.helios.ap.broker;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbisec.helios.ap.broker.util.PapyConnectionException;

/**
 * OkHttpResponse
 *
 * @author katsuhiko.kagoshima
 * @date 12/23/2022
 */
public class PapyOkHttpResponse implements OkHttpBaseResponse {
    
    private ObjectMapper objectMapper;
    
    public PapyOkHttpResponse() {
        
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    // リクエスト成功 => true:成功；false:失敗
    private Boolean successful;
    
    // HTTP状態 => 200:成功
    private Integer statusCode;
    
    // 応答の結果
    private String responseData;
    
    public Boolean getSuccessful() {
        
        return successful;
    }
    
    public void setSuccessful(Boolean successful) {
        
        this.successful = successful;
    }
    
    /**
     * @return the statusCode
     */
    public Integer getStatusCode() {
        
        return statusCode;
    }
    
    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(Integer statusCode) {
        
        this.statusCode = statusCode;
    }
    
    public String getResponsData() {
        
        return responseData;
    }
    
    public <T> T getResponseData(Class<T> clazz) throws PapyConnectionException {
        
        try {
            return objectMapper.readValue(this.responseData, clazz);
        } catch (Exception e) {
            throw new PapyConnectionException(e);
        }
    }
    
    public void setResponseData(String responseMsg) {
        
        this.responseData = responseMsg;
    }
}
