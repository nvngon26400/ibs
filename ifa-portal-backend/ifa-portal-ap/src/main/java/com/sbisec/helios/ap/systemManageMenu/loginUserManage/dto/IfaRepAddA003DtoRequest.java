package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaRepAddA003DtoRequest {
    
    /** 権限コード（全角半角）. */
    private String privId;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者支店コード（数字）. */
    private String subBrokerId;
    
}
