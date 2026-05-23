package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model;

import lombok.Data;

@Data
public class IfaLoginIdUpdateRegisterSql011ResponseModel {
    
    /**ユーザーID*/
    private String userId;
    
    /**ユーザー名*/
    private String userNm;
    
    /**パスワード*/
    private String password;
    
    /**最後パスワード更新日時*/
    private String lastpwuptime;
    
    /**権限コード*/
    private String privId;
    
    /**SBI証券支店コード*/
    private String branchId;
    
    /**仲介業者コード*/
    private String brokerId;
    
    /**仲介業者支店コード*/
    private String subBrokerId;
    
    /**仲介業者担当者コード*/
    private String employeeId;
    
    /**仲介業者担当者名*/
    private String employeeName;
    
    /**管理者フラグ*/
    private String governorFlag;
    
    /**ログイン状況*/
    private String loginStatus;
    
    /**パートナーサイトログイン用ID*/
    private String partnerUserId;
    
    /**パートナーサイトログイン用パスワード*/
    private String partnerUserPw;
    
    /**CCSログイン用ID*/
    private String ccsUserId;
    
    /**CCSログイン用パスワード*/
    private String ccsUserPw;
    
    /**作成者*/
    private String createUser;
    
    /**作成日時*/
    private String createTime;
    
    /**更新者*/
    private String uptimestampUser;
    
    /**更新日時*/
    private String uptimestampTime;
    
    /**メールアドレス*/
    private String mailAddress;
}
