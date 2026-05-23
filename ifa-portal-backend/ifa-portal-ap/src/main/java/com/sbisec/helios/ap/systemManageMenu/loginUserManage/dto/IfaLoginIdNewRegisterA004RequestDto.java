package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import lombok.Data;

@Data
public class IfaLoginIdNewRegisterA004RequestDto {

    /** 所属権限コード. */
    private String privId;
    
	/** 仲介業者コード（数字）. */
	private String brokerCode;
}
