package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaRepAddA002DtoRequest {
    
    /** 権限コード（全角半角）. */
    private String privId;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
}
