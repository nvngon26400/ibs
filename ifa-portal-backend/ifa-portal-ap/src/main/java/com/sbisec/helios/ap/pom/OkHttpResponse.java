package com.sbisec.helios.ap.pom;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.sbisec.helios.ap.pom.utils.PomException;

/**
 * OkHttpResponse
 *
 * @author shuchen.xin
 * @version 1.0
 * @date 4/12/2022
 */
public class OkHttpResponse {
    
    private ObjectMapper objectMapper;
    
    public OkHttpResponse() {
        
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
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
    
    @NotNull
    public <T> T getResponseData(Class<T> clazz) throws PomException {
        
        try {
            return objectMapper.readValue(this.responseData, clazz);
        } catch (Exception e) {
            throw new PomException(e);
        }
    }
    
    public void setResponseData(String responseMsg) {
        
        this.responseData = responseMsg;
    }
}
