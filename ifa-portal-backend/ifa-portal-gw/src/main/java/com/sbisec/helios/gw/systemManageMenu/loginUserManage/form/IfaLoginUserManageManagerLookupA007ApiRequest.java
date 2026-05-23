package com.sbisec.helios.gw.systemManageMenu.loginUserManage.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaLoginUserManageManagerLookupA007ApiRequest {

	/** ログインID. */
	@NotEmpty(message = "ログインID")
	@Size(max = 16, message = "ログインID")
	private String loginId;
	
	/** SBI証券支店コード */
	@NotEmpty(message = "SBI証券支店コード")
	@Size(max = 3, message = "SBI証券支店コード")
	@Pattern(regexp = "0-9", message = "SBI証券支店コード")
	private String sbiSecurityCode;
	
	/** 仲介業者コード */
	@NotEmpty(message = "仲介業者コード")
	@Size(max = 4, message = "仲介業者コード")
    @Pattern(regexp = "0-9", message = "仲介業者コード")
	private String brokerCode;
	
	/** 仲介業者支店コード */
	@NotEmpty(message = "仲介業者支店コード")
	@Size(max = 3, message = "仲介業者支店コード")
    @Pattern(regexp = "0-9", message = "仲介業者支店コード")
	private String subBrokerId;
	
	/** 仲介業者担当者コード */
	@NotEmpty(message = "仲介業者担当者コード")
	@Size(max = 4, message = "仲介業者担当者コード")
    @Pattern(regexp = "0-9", message = "仲介業者担当者コード")
	private String employeeId;

	/** 担当数（数値(整数)）. */
	@Digits(integer = 3, fraction = 0, message = "担当数")
	@NotEmpty(message = "担当数")
	@Size(max = 3, message = "担当数")
	private String managerCount;

}
