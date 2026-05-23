package com.sbisec.helios.ap.extapi.servicenow.dao.model;

import lombok.Data;

/**
 * 申請情報項目一覧を取得 TB_MED_USERS 情報モデル
 *
 * @author SCSK
 */
@Data
public class IfaA006MedUsersModel {
    
    /** ユーザーID */
    private String userId;
    
    /** ユーザー名 */
    private String userNm;
    
    /** パスワード */
    private String password;
    
    /** 権限コード */
    private String privId;
    
    /** 親ユーザーフラグ */
    private String parentUserFlag;
    
    /** 本支店コード */
    private String branchCode;
    
    /** 本支店名 */
    private String branchName;
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者名 */
    private String brokerName;
    
    /** 仲介業者支店コード */
    private String brokerBranchCode;
    
    /** 仲介業者支店名 */
    private String brokerBranchName;
    
    /** 仲介業者営業員コード */
    private String employeeId;
    
    /** 仲介業者担当者名 */
    private String employeeName;
    
    /** 管理者フラグ */
    private String governorFlag;
    
    /** CCSユーザーID */
    private String ccsUserId;
    
    /** メールアドレス */
    private String mailAddress;
}
