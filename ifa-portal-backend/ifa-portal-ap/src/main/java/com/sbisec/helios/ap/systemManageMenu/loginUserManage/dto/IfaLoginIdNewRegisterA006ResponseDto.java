package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaLoginIdNewRegisterA006ResponseDto {

	/** 本店／支店名リスト. */
	private List<IfaLoginIdNewRegisterResponseDto_CommonList> headOfficeBranchNameList;

	/** 非表示リスト. */
	private List<IfaLoginIdNewRegisterResponseDto_DisplayCommon> unDisplayList;

	/** 表示リスト. */
    private List<String> displayList;

}
