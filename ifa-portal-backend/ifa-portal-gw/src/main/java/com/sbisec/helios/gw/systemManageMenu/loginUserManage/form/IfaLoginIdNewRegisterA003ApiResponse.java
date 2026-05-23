package com.sbisec.helios.gw.systemManageMenu.loginUserManage.form;

import java.util.List;

import lombok.Data;

@Data
public class IfaLoginIdNewRegisterA003ApiResponse {

	/** 仲介業者名リスト. */
    private List<IfaLoginIdNewRegisterApiResponse_CommonList> brokerNameList;

	/** 仲介業者支店名リスト. */
    private List<IfaLoginIdNewRegisterApiResponse_CommonList> branchNameList;

	/** 担当者名リスト. */
    private List<IfaLoginIdNewRegisterApiResponse_CommonList> chargeNameList;

}
