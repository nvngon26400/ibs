package com.sbisec.helios.gw.systemManageMenu.loginUserManage.form;

import java.util.List;

import lombok.Data;

@Data
public class IfaLoginIdNewRegisterA006ApiResponse {

	/** 本店／支店名リスト. */
	private List<IfaLoginIdNewRegisterApiResponse_CommonList> headOfficeBranchNameList;

	/** 非表示リスト. */
    private List<IfaLoginIdNewRegisterApiCommon_UnDisplay> unDisplayList;

	/** 表示リスト. */
    private List<String> displayList;

}
