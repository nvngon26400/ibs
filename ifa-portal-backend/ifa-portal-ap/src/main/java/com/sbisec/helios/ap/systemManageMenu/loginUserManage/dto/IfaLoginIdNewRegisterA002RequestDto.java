package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaLoginIdNewRegisterA002RequestDto {

	/** 所属権限コード. */
	private String privId;
	
	/** 非表示リスト. */
	private List<IfaLoginIdNewRegisterResponseDto_DisplayCommon> unDisplayList;

	/** 表示リスト. */
    private List<String> displayList;

}
