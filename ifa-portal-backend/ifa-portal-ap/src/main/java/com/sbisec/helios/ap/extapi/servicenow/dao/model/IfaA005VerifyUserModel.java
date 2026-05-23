package com.sbisec.helios.ap.extapi.servicenow.dao.model;

import lombok.Data;

/**
 * A005(ServiceNow)の TB_MED_VERIFY_USERS モデル
 *
 * @author SCSK
 */
@Data
public class IfaA005VerifyUserModel {
    
    /** ユーザID. */
    private String userId;
    
    /** メールアドレス. */
    private String mailAddress;
    
    /** 認証対象. */
    private String verifyStatus;
    
    /** 更新者. */
    private String updateBy;
    
    /** 権限コード. */
    private String privId;
}
