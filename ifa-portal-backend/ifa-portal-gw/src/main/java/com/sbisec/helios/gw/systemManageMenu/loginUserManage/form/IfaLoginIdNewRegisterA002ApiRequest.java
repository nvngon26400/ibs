package com.sbisec.helios.gw.systemManageMenu.loginUserManage.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class IfaLoginIdNewRegisterA002ApiRequest {

	/** 所属権限コード. */
	@NotEmpty(message = "所属権限コード")
	private String privId;

	/** 非表示リスト. */
	@NotEmpty(message = "非表示リスト")
	private List<IfaLoginIdNewRegisterApiCommon_UnDisplay> unDisplayList;

	/** 表示リスト. */
	@NotEmpty(message = "表示リスト")
    private List<String> displayList;

}
