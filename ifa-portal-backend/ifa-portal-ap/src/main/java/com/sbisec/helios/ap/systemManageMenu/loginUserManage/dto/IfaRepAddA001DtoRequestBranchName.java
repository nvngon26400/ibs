package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaRepAddA001DtoRequestBranchName {
    
    /** SBI証券支店コード. */
    private String sbiSecurityCode;
    
    /** 本支店名 */
    private String mainBranchName;
    
}
