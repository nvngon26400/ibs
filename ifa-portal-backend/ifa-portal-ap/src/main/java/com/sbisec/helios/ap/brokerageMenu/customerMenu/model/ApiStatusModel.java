package com.sbisec.helios.ap.brokerageMenu.customerMenu.model;

import com.sbibits.earth.model.ModelBase;

/**
 * Comet Api bussiness Exception Model
 *
 */
public class ApiStatusModel extends ModelBase {
    
    private static final long serialVersionUID = 1L;
    // APIステータスコード
    private Integer apiStatusCode;
    // APIエラーコード
    private String apiErrorCode;
    // APIメッセージ
    private String apiMessage;
    
    /**
     * @return the apiStatusCode
     */
    public Integer getApiStatusCode() {
        
        return apiStatusCode;
    }
    
    /**
     * @param apiStatusCode the apiStatusCode to set
     */
    public void setApiStatusCode(Integer apiStatusCode) {
        
        this.apiStatusCode = apiStatusCode;
    }
    
    /**
     * @return the apiErrorCode
     */
    public String getApiErrorCode() {
        
        return apiErrorCode;
    }
    
    /**
     * @param apiErrorCode the apiErrorCode to set
     */
    public void setApiErrorCode(String apiErrorCode) {
        
        this.apiErrorCode = apiErrorCode;
    }
    
    /**
     * @return the apiMessage
     */
    public String getApiMessage() {
        
        return apiMessage;
    }
    
    /**
     * @param apiMessage the apiMessage to set
     */
    public void setApiMessage(String apiMessage) {
        
        this.apiMessage = apiMessage;
    }
}
