package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import lombok.Data;

@Data
public class IfaLoginIdUpdateRegisterA004DtoRequest {
    
    /** 所属権限コード. */
    private String privId;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者支店コード（数字）. */
    private String subBrokerId;
    
}
