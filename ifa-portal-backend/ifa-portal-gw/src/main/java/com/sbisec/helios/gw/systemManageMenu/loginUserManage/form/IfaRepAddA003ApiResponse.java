package com.sbisec.helios.gw.systemManageMenu.loginUserManage.form;

import java.util.List;
import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaRepAddA003ApiResponse {
    
    /** 担当者名リスト. */
    private List<IfaRepAddA001ApiResponseEmployeeName> employeeNameList;
    
}
