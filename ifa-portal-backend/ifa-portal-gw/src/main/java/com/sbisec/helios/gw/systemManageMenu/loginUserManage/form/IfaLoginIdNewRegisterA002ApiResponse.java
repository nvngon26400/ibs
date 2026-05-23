package com.sbisec.helios.gw.systemManageMenu.loginUserManage.form;

import java.util.List;

import lombok.Data;

@Data
public class IfaLoginIdNewRegisterA002ApiResponse {

	/** 本店／支店名リスト. */
	private List<IfaLoginIdNewRegisterApiResponse_CommonList> headOfficeBranchNameList;

	/** 仲介業者名リスト. */
    private List<IfaLoginIdNewRegisterApiResponse_CommonList> brokerNameList;

	/** 仲介業者支店名リスト. */
    private List<IfaLoginIdNewRegisterApiResponse_CommonList> branchNameList;

	/** 担当者名リスト. */
    private List<IfaLoginIdNewRegisterApiResponse_CommonList> chargeNameList;

	/** 非表示リスト. */
    private List<IfaLoginIdNewRegisterApiCommon_UnDisplay> unDisplayList;

	/** 表示リスト. */
    private List<String> displayList;

}
