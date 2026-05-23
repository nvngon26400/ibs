package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaLoginIdNewRegisterA004ResponseDto {

	/** 仲介業者支店名リスト. */
    private List<IfaLoginIdNewRegisterResponseDto_CommonList> branchNameList;

	/** 担当者名リスト. */
    private List<IfaLoginIdNewRegisterResponseDto_CommonList> chargeNameList;

}
