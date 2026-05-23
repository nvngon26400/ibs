package com.sbisec.helios.gw.systemManageMenu.loginUserManage.form;

import lombok.Data;

@Data
public class IfaLoginUserManageManagerLookupA002ApiResponse_LoginIdManageList {
    
    /** ログインID（英数字記号A(+-_./@*#%)）. */
    private String loginId;
    
    /** 管理者フラグ（全角）. */
    private String governorFlag;
    
    /** 権限所属（全角半角）. */
    private String privId;
    
    /** 担当数（数値(整数)）. */
    private String managerCount;
    
    /** 本支店コード. */
    private String branchCode;
    
    /** 支店名（全角半角）. */
    private String headOfficeBranchName;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者名（全角半角）. */
    private String brokerName;
    
    /** 仲介業者支店コード（数字）. */
    private String subBrokerId;
    
    /** 仲介業者担当者コード（数字）. */
    private String employeeId;
    
    /** 仲介業者担当者名（全角半角）. */
    private String employeeName;
    
    /** 社員名担当者名（全角半角）. */
    private String employeeNameChargeName;
    
}
