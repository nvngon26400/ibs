package com.sbisec.helios.ap.extapi.servicenow.dao.model;

import lombok.Data;

/**
 * TB_MED_USERS_PRIV モデル
 *
 * @author SCSK
 */
@Data
public class IfaTbMedUsersPriv {
    
    /** ユーザID. */
    private String userId;
    
    /** SEQ番号. */
    private String seqNo;
    
    /** SBI証券支店コード. */
    private String branchId;
    
    /** 仲介業者コード. */
    private String brokerId;
    
    /** 仲介業者支店コード. */
    private String subBrokerId;
    
    /** 仲介業者担当者コード. */
    private String employeeId;
    
    /** 仲介業者担当者名. */
    private String employeeName;
    
    /** 削除フラグ. */
    private String deleteFlg;
    
    /** 作成者. */
    private String createUser;
    
    /** 作成日時. */
    private String createTime;
    
    /** 削除者. */
    private String deleteUser;
    
    /** 削除日時. */
    private String deleteTime;
    
}
