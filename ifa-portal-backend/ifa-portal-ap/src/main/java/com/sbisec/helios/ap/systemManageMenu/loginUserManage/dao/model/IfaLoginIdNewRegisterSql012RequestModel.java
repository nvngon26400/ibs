package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model;

import lombok.Data;

@Data
public class IfaLoginIdNewRegisterSql012RequestModel {

	/** リクエスト.ログインID. */
	private String loginId;

	/** リクエスト.メールアドレス. */
	private String mailAddress;

	/** リクエスト.所属権限コード. */
	private String privId;
	
	/** 更新者. */
    private String updateUser;

}
