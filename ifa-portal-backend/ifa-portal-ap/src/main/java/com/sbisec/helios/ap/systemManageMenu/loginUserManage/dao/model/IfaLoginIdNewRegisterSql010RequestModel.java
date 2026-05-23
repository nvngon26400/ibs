package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model;

import lombok.Data;

@Data
public class IfaLoginIdNewRegisterSql010RequestModel {

	/** リクエスト.ログインID. */
	private String loginId;

	/** リクエスト.表示リスト.メニューコード. */
	private String menuId;
	
	/** 作成者. */
    private String createUser;
    
    /** 更新者. */
    private String updateUser;

}
