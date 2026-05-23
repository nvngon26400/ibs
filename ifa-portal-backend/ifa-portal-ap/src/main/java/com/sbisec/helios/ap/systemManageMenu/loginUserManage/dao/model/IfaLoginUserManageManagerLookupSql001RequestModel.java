package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model;

import lombok.Data;

@Data
public class IfaLoginUserManageManagerLookupSql001RequestModel {

	/** ユーザID. */
	private String userId;

	/** 支店名仲介業者名（全角半角）. */
	private String branchNameBrokerName;

	/** 社員名担当者名（全角半角）. */
	private String employeeNameChargeName;

}
