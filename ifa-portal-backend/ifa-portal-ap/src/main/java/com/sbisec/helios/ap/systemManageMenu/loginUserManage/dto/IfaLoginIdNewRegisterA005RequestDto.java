package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import lombok.Data;

@Data
public class IfaLoginIdNewRegisterA005RequestDto {

	/** 仲介業者コード（数字）. */
	private String brokerCode;

	/** 仲介業者支店コード（数字）. */
	private String subBrokerId;

}
