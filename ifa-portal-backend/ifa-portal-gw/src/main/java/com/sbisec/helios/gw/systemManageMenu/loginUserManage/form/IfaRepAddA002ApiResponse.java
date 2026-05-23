package com.sbisec.helios.gw.systemManageMenu.loginUserManage.form;

import java.util.List;
import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaRepAddA002ApiResponse {
    
    /** 仲介業者支店名リスト. */
    private List<IfaRepAddA001ApiResponseBrokerBranchName> brokerBranchNameList;
    
    /** 担当者名リスト. */
    private List<IfaRepAddA001ApiResponseEmployeeName> employeeNameList;
    
}
