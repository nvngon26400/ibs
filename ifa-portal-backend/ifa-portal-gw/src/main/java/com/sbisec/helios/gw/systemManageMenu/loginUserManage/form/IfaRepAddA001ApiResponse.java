package com.sbisec.helios.gw.systemManageMenu.loginUserManage.form;

import java.util.List;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaRepAddA001ApiResponse {
    
    /** ログインID（英数字記号A(+-_./@*#%)）. */
    private String loginId;
    
    /** 権限コード（全角半角）. */
    private String privId;
    
    /** 本店／支店名リスト */
    private List<IfaRepAddA001ApiResponseBranchName> branchNameList;
    
    /** 仲介業者名リスト. */
    private List<IfaRepAddA001ApiResponseBrokerName> brokerNameList;
    
    /** 仲介業者支店名リスト. */
    private List<IfaRepAddA001ApiResponseBrokerBranchName> brokerBranchNameList;
    
    /** 担当者名リスト. */
    private List<IfaRepAddA001ApiResponseEmployeeName> employeeNameList;
    
}
