package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model;

import lombok.Data;

@Data
public class IfaLoginIdNewRegisterSql004ResponseModel {

	/** 本支店コード. */
	private String branchCode;

	/** 本支店種別. */
	private String branchClass;

	/** 本支店名. */
	private String branchName;

	/** 作成日時. */
	private String createTime;

	/** 作成者. */
	private String createUser;

	/** 更新日時. */
	private String updateTime;

	/** 更新者. */
	private String updateUser;

	/** 削除日時. */
	private String deleteTime;

	/** 削除フラグ. */
	private String deleteFlg;

}
