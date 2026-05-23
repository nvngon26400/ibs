package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import java.util.List;
import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaRepAddA003DtoResponse {
    
    /** 担当者名リスト. */
    private List<IfaRepAddA001DtoRequestEmployeeName> employeeNameList;
    
}
