package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model;

import lombok.Data;

@Data
public class IfaLoginIdNewRegisterSql005ResponseModel {

	/** 仲介業者コード（数字）. */
	private String brokerCode;

	/** 仲介業支店コード. */
	private String subBrokerId;

	/** 仲介業者支店種別. */
	private String branchClass;

	/** 仲介業者支店名. */
	private String branchNameBrokerName;

}
