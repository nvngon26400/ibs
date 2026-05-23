package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import java.util.List;
import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaRepAddA001DtoResponse {
    
    /** ログインID（英数字記号A(+-_./@*#%)）. */
    private String loginId;
    
    /** 権限コード（全角半角）. */
    private String privId;
    
    /** 本店／支店名リスト */
    private List<IfaRepAddA001DtoRequestBranchName> branchNameList;
    
    /** 仲介業者名リスト. */
    private List<IfaRepAddA001DtoRequestBrokerName> brokerNameList;
    
    /** 仲介業者支店名リスト. */
    private List<IfaRepAddA001DtoRequestBrokerBranchName> brokerBranchNameList;
    
    /** 担当者名リスト. */
    private List<IfaRepAddA001DtoRequestEmployeeName> employeeNameList;
    
}
