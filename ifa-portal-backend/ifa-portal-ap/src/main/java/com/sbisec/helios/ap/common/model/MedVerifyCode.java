package com.sbisec.helios.ap.common.model;

import com.sbibits.earth.model.ModelBase;

/**
 * 認証コード情報
 *
 * @author SCSK
 *
 */
@SuppressWarnings("serial")
public class MedVerifyCode extends ModelBase {
    
    private String userId;
    
    private String verifyCode;
    
    private String verifyDate;
    
    private String nowDate;
    
    public String getUserId() {
        
        return userId;
    }
    
    public void setUserId(String userId) {
        
        this.userId = userId;
    }
    
    public String getVerifyCode() {
        
        return verifyCode;
    }
    
    public void setVerifyCode(String verifyCode) {
        
        this.verifyCode = verifyCode;
    }
    
    public String getVerifyDate() {
        
        return verifyDate;
    }
    
    public void setVerifyDate(String verifyDate) {
        
        this.verifyDate = verifyDate;
    }
    
    public String getNowDate() {
        
        return nowDate;
    }
    
    public void setNowDate(String nowDate) {
        
        this.nowDate = nowDate;
    }
}
