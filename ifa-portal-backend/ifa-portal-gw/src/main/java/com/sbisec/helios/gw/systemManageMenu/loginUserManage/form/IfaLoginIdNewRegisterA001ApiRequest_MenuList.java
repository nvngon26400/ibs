package com.sbisec.helios.gw.systemManageMenu.loginUserManage.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class IfaLoginIdNewRegisterA001ApiRequest_MenuList {

	/** メニューコード. */
	@NotEmpty(message = "メニューリスト.メニューコード")
	private String menuId;

	/** メニュータイトル. */
	@NotEmpty(message = "メニューリスト.メニュータイトル")
	private String menuTitle;

}
