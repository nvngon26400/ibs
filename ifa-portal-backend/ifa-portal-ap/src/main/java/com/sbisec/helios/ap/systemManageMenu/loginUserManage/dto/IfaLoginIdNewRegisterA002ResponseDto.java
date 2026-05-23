package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaLoginIdNewRegisterA002ResponseDto {

	/** 本店／支店名リスト. */
	private List<IfaLoginIdNewRegisterResponseDto_CommonList> headOfficeBranchNameList;

	/** 仲介業者名リスト. */
    private List<IfaLoginIdNewRegisterResponseDto_CommonList> brokerNameList;

	/** 仲介業者支店名リスト. */
    private List<IfaLoginIdNewRegisterResponseDto_CommonList> branchNameList;

	/** 担当者名リスト. */
    private List<IfaLoginIdNewRegisterResponseDto_CommonList> chargeNameList;

	/** 非表示リスト. */
	private List<IfaLoginIdNewRegisterResponseDto_DisplayCommon> unDisplayList;

	/** 表示リスト. */
    private List<String> displayList;

}
