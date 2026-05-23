package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model;

import lombok.Data;

@Data
public class IfaLoginIdNewRegisterSql007RequestModel {

	/** リクエスト.仲介業者コード. */
	private String brokerCode;

	/** リクエスト.仲介業者支店コード. */
	private String subBrokerCode;

}
