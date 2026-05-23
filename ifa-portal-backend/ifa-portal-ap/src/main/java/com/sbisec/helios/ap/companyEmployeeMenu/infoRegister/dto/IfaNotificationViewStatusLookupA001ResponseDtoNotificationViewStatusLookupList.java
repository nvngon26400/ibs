package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto;

import lombok.Data;

@Data
public class IfaNotificationViewStatusLookupA001ResponseDtoNotificationViewStatusLookupList {

	/** 仲介業者支店名. */
	private String branchName;

	/** 仲介業者支店コード（数字）. */
	private String subBrokerId;

	/** 仲介業者担当者名（全角半角）. */
	private String employeeName;

	/** 仲介業者担当者コード（数字）. */
	private String employeeId;

	/** 既読フラグ（数字）. */
	private String t5nReadFlg;

	/** 閲覧日. */
	private String readDate;

	/** ログインID（英数字記号A(+-_./@*#%)）. */
	private String loginId;

}
