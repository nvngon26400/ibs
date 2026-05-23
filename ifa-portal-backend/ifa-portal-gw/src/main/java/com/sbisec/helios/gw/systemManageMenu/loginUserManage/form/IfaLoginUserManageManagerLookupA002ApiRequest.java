package com.sbisec.helios.gw.systemManageMenu.loginUserManage.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaLoginUserManageManagerLookupA002ApiRequest {

	/** ログインID. */
	@NotEmpty(message = "ログインID")
	@Size(max = 16, message = "ログインID")
	private String loginId;

	/** 支店名仲介業者名（全角半角）. */
	@NotEmpty(message = "支店名仲介業者名")
	@Size(max = 80, message = "支店名仲介業者名")
	private String branchNameBrokerName;

	/** 社員名担当者名（全角半角）. */
	@NotEmpty(message = "社員名担当者名")
	@Size(max = 80, message = "社員名担当者名")
	private String employeeNameChargeName;

}
