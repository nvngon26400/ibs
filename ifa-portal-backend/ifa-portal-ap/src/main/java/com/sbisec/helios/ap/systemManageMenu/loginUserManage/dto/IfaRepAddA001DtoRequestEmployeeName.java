package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaRepAddA001DtoRequestEmployeeName {
    
    /** 仲介業者担当者名（全角半角）. */
    private String employeeName;
    
    /** 仲介業者担当者コード（数字）. */
    private String employeeId;
    
}
