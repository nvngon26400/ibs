package com.sbisec.helios.gw.systemManageMenu.loginUserManage.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaLoginIdNewRegisterA004ApiRequest {

    /** 所属権限コード（数字）. */
    @NotEmpty(message = "所属権限コード")
    private String privId;
    
	/** 仲介業者コード（数字）. */
	@NotEmpty(message = "仲介業者コード")
	@Pattern(regexp="0-9", message = "仲介業者コード")
	@Size(max = 4, message = "仲介業者コード")
	private String brokerCode;

}
