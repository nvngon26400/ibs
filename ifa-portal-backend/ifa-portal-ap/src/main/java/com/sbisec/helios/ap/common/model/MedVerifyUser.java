package com.sbisec.helios.ap.common.model;

import com.sbibits.earth.model.ModelBase;

/**
 * 認証ユーザ情報
 *
 * @author SCSK
 *
 */
@SuppressWarnings("serial")
public class MedVerifyUser extends ModelBase {
    
    private String userId;
    
    private String mailAddress;
    
    private String verifyStatus;
    
    private String updateBy;
    
    private String updateDate;
    
    public String getUserId() {
        
        return userId;
    }
    
    public void setUserId(String userId) {
        
        this.userId = userId;
    }
    
    public String getMailAddress() {
        
        return mailAddress;
    }
    
    public void setMailAddress(String mailAddress) {
        
        this.mailAddress = mailAddress;
    }
    
    public String getVerifyStatus() {
        
        return verifyStatus;
    }
    
    public void setVerifyStatus(String verifyStatus) {
        
        this.verifyStatus = verifyStatus;
    }
    
    public String getUpdateBy() {
        
        return updateBy;
    }
    
    public void setUpdateBy(String updateBy) {
        
        this.updateBy = updateBy;
    }
    
    public String getUpdateDate() {
        
        return updateDate;
    }
    
    public void setUpdateDate(String updateDate) {
        
        this.updateDate = updateDate;
    }
    
}
