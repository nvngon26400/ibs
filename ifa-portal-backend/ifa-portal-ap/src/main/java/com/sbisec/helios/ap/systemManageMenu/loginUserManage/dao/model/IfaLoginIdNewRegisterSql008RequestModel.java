package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model;

import lombok.Data;

@Data
public class IfaLoginIdNewRegisterSql008RequestModel {

	/** リクエスト.ログインID. */
	private String loginId;

	/** リクエスト.ユーザ名. */
	private String userName;

	/** リクエスト.パスワード（暗号化）. */
	private String password;

	/** リクエスト.所属権限コード. */
	private String privId;

	/** リクエスト.本支店コード. */
	private String branchCode;

	/** リクエスト.仲介業者コード. */
	private String brokerCode;

	/** リクエスト.仲介業者支店コード. */
	private String subBrokerCode;

	/** リクエスト.担当者コード. */
	private String employeeId;

	/** リクエスト.担当者名. */
	private String chargeName;

	/** リクエスト.メールアドレス. */
	private String mailAddress;
	
	/** 作成者. */
    private String createUser;
    
    /** 更新者. */
    private String updateUser;
	

}
