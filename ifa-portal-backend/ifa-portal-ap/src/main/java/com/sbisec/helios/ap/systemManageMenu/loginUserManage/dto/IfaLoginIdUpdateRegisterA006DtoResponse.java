package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaLoginIdUpdateRegisterA006DtoResponse {
    
    /** 本店支店名リスト. */
    private List<IfaLoginIdUpdateRegisterDtoResponse_headOfficeBranchName> headOfficeBranchNameList;
    
    /** 仲介業者名リスト. */
    private List<IfaLoginIdUpdateRegisterDtoResponse_brokerName> brokerNameList;
    
    /** 仲介業者支店名リスト. */
    private List<IfaLoginIdUpdateRegisterDtoResponse_branchName> branchNameList;
    
    /** 担当者名リスト. */
    private List<IfaLoginIdUpdateRegisterDtoResponse_chargeName> chargeNameList;
    
    /** 非表示リスト. */
    private List<IfaLoginIdUpdateRegisterDtoResponse_unDisplay> unDisplayList;
    
    /** 表示リスト. */
    private List<String> displayList;
    
}
