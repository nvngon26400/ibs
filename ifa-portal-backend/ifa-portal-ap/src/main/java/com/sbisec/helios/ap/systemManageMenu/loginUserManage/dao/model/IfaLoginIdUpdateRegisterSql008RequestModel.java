package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model;

import lombok.Data;

@Data
public class IfaLoginIdUpdateRegisterSql008RequestModel {
    
    /** ログインID（英数字記号A(+-_./@*#%)）. */
    private String loginId;
    
    /** ユーザ名（全角半角）. */
    private String userName;
    
    /** パスワード（英数字記号B(+-_./@*#%!"$&()=~^\?>,|`[]{}:;<')）. */
    private String password;
    
    /** 所属権限コード. */
    private String privId;
    
    /** 本支店コード. */
    private String branchCode;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者支店コード（数字）. */
    private String subBrokerId;
    
    /** 担当者コード. */
    private String employeeId;
    
    /** 担当者名（全角半角）. */
    private String chargeName;
    
    /** 管理者フラグ（全角）. */
    private String governorFlag;
    
    /** CCSID（全角半角）. */
    private String ccsId;
    
    /** CCSPW. */
    private String ccsPw;
    
    /** ユーザID. */
    private String userId;
    
    /** メールアドレス（メールアドレス）. */
    private String mailAddress;
    
}
