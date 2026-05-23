package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import java.util.List;
import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaRepAddA002DtoResponse {
    
    /** 仲介業者支店名リスト. */
    private List<IfaRepAddA001DtoRequestBrokerBranchName> brokerBranchNameList;
    
    /** 担当者名リスト. */
    private List<IfaRepAddA001DtoRequestEmployeeName> employeeNameList;
    
}
