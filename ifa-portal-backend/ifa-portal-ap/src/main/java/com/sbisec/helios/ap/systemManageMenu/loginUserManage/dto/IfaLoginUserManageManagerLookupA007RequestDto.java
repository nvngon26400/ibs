package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import lombok.Data;

@Data
public class IfaLoginUserManageManagerLookupA007RequestDto {

	/** ログインID. */
	private String loginId;
	
	/** SBI証券支店コード */
	private String sbiSecurityCode;
	
	/** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者支店コード */
    private String subBrokerId;
    
    /**　仲介業者担当者コード */
    private String employeeId;

	/** 担当数（数値(整数)）. */
	private String managerCount;

}
