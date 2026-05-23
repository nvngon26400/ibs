package com.sbisec.helios.gw.systemManageMenu.loginUserManage.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class IfaLoginIdNewRegisterA003ApiRequest {

	/** 所属権限コード. */
	@NotEmpty(message = "所属権限コード")
	private String privId;

	/** 本支店コード. */
	@NotEmpty(message = "本支店コード")
	private String branchCode;

}
