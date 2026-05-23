package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaRepAddA008DtoRequest {
    
    /** 権限コード（全角半角）. */
    private String privId;
    
    /** SBI証券支店コード（数字）. */
    private String sbiSecurityCode;
    
}
