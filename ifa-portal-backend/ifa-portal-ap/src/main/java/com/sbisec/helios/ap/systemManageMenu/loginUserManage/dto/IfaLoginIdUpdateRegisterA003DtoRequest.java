package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import lombok.Data;

@Data
public class IfaLoginIdUpdateRegisterA003DtoRequest {
    
    /** 所属権限コード. */
    private String privId;
    
    /** 本支店コード. */
    private String branchCode;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者支店コード（数字）. */
    private String subBrokerId;
    
}
