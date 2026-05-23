package com.sbisec.helios.gw.systemManageMenu.loginUserManage.form;

import java.util.List;

import lombok.Data;

@Data
public class IfaLoginIdUpdateRegisterA004ApiResponse {
    
    /** 仲介業者支店名リスト. */
    private List<IfaLoginIdUpdateRegisterApiResponse_branchName> branchNameList;
    
    /** 担当者名リスト. */
    private List<IfaLoginIdUpdateRegisterApiResponse_chargeName> chargeNameList;
    
}
