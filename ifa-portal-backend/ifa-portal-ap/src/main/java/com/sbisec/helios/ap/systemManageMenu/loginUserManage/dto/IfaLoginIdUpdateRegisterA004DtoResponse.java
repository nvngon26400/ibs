package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaLoginIdUpdateRegisterA004DtoResponse {
    
    /** 仲介業者支店名リスト. */
    private List<IfaLoginIdUpdateRegisterDtoResponse_branchName> branchNameList;
    
    /** 担当者名リスト. */
    private List<IfaLoginIdUpdateRegisterDtoResponse_chargeName> chargeNameList;
    
}
