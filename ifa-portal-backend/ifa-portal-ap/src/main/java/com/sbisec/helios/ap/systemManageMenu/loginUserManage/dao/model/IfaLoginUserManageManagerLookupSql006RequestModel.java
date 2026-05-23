package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model;

import lombok.Data;

@Data
public class IfaLoginUserManageManagerLookupSql006RequestModel {

	/** リクエスト.ログインID. */
	private String loginId;
	
	/** リクエスト.SBI証券支店コード */
    private String sbiSecurityCode;

	/** リクエスト.仲介業者コード. */
	private String brokerCode;

	/** リクエスト.仲介業者支店コード. */
	private String subBrokerId;

	/** リクエスト.仲介業者担当者コード. */
	private String employeeId;

}
