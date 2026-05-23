package com.sbisec.helios.gw.common.enums;

import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.gw.common.constants.LoginMessageKeyConstants;

/**
 * ログイン共通返却データ定義
 *
 * @author SCSK
 *
 */
public enum LoginReturnDataEnums {
    BAD_LOGIN("badLogin", "badLogin", ErrorLevel.FATAL.getId(), LoginMessageKeyConstants.ERRORS_LOGIN_BAD_LOGIN), 
    FIRST_VERIFY("firstverify", "firstverify", ErrorLevel.SUCCESS.getId(), ""), 
    VERIFY("verify", "verify", ErrorLevel.SUCCESS.getId(), ""), 
    NOTVU("notvu", "notvu", ErrorLevel.SUCCESS.getId(), ""), 
    MAIL_ERROR("mailError", "mailError", ErrorLevel.FATAL.getId(), LoginMessageKeyConstants.ERRORS_LOGIN_MAIL_ERROR), 
    UPDATE_FAIL("updateFail", "updateFail", ErrorLevel.SYSTEM_ERROR.getId(), LoginMessageKeyConstants.ERRORS_LOGIN_UPDATE_FAIL), 
    UPDATE_SUCC("updateSucc", "updateSucc", ErrorLevel.SUCCESS.getId(), ""), 
    VERIFY_CODE_ERROR("verifyCodeError", "verifyCodeError", ErrorLevel.FATAL.getId(), LoginMessageKeyConstants.ERRORS_LOGIN_VERIFY_CODE_ERROR), 
    CODE_EXPIRED("codeExpired", "codeExpired", ErrorLevel.FATAL.getId(), LoginMessageKeyConstants.ERRORS_LOGIN_CODE_EXPIRED), 
    NOT_MATCH("notMatch", "notMatch", ErrorLevel.FATAL.getId(), LoginMessageKeyConstants.ERRORS_LOGIN_NOT_MATCH), 
    VERIFY_ERROR("verifyError", "verifyError", ErrorLevel.FATAL.getId(), LoginMessageKeyConstants.ERRORS_LOGIN_VERIFY_ERROR), 
    EXPIRED("expired", "expired", ErrorLevel.FATAL.getId(), LoginMessageKeyConstants.ERRORS_LOGIN_EXPIRED), 
    AUTH_ERROR("authError", "authError", ErrorLevel.FATAL.getId(), LoginMessageKeyConstants.ERRORS_LOGIN_AUTH_ERROR), 
    LOGIN_SUCC("loginSucc", "redirect:", ErrorLevel.SUCCESS.getId(), ""), 
    PASSWORD_UNMATCH_ERROR("passwordUnmatchError", "LOGIN_PASSWORD_ERROR", ErrorLevel.FATAL.getId(), ""), 
    PASSWORD_CHANGE_ERROR("passwordChangeError", "ERROR", ErrorLevel.SYSTEM_ERROR.getId(), LoginMessageKeyConstants.ERRORS_LOGIN_PASSWORD_CHANGE_ERROR), 
    PASSWORD_CHANGE_SUCC("passwordChangeSucc", "SUCCESS", ErrorLevel.SUCCESS.getId(), ""), 
    SYSTEM_ERROR("systemError", "systemError", ErrorLevel.SYSTEM_ERROR.getId(), LoginMessageKeyConstants.ERRORS_LOGIN_SYSTEM_ERROR);
    
    private final String id;
    
    private final String returnCd;
    
    private final int errorLevel;
    
    private final String message;
    
    private LoginReturnDataEnums(String id, String returnCd, int errorLevel, String message) {
        
        this.id = id;
        this.returnCd = returnCd;
        this.errorLevel = errorLevel;
        this.message = message;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getReturnCd() {
        
        return returnCd;
    }
    
    public int getErrorLevel() {
        
        return errorLevel;
    }
    
    public String getMessage() {
        
        return message;
    }
    
    /**
     * キーを元に返却用データを取得する。
     *
     * @param id 検索キー
     * @return ログイン共通返却データ
     */
    public static LoginReturnDataEnums valueOfId(String id) {
        
        if (id == null) {
            return null;
        }
        
        LoginReturnDataEnums[] enums = values();
        for (int i = 0; i < enums.length; i++) {
            if (id.equals(enums[i].getId())) {
                return enums[i];
            }
        }
        
        return null;
    }
}
