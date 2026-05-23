package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import lombok.Data;

@Data
public class IfaLoginUserManageManagerLookupA002RequestDto {

	/** ログインID. */
	private String loginId;

	/** 支店名仲介業者名（全角半角）. */
	private String branchNameBrokerName;

	/** 社員名担当者名（全角半角）. */
	private String employeeNameChargeName;

}
