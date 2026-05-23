package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import lombok.Data;

@Data
public class IfaLoginIdUpdateRegisterDtoResponse_brokerName {
    
    /** 仲介業者名（全角半角）. */
    private String value;
    
    /** 仲介業者コード（数字）. */
    private String key;
    
}
