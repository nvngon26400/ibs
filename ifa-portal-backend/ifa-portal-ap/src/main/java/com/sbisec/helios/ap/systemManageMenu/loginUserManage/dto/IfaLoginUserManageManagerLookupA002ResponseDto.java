package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaLoginUserManageManagerLookupA002ResponseDto {

	/** ログインID管理リスト. */
	private List<IfaLoginUserManageManagerLookupA002ResponseDto_LoginIdManageList> loginIdManageList;

}
