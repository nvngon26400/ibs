package com.sbisec.helios.gw.systemManageMenu.loginUserManage.form;

import java.util.List;

import lombok.Data;

@Data
public class IfaLoginIdUpdateRegisterA002ApiResponse {
    
    /** 本店支店名リスト. */
    private List<IfaLoginIdUpdateRegisterApiResponse_headOfficeBranchName> headOfficeBranchNameList;
    
    /** 仲介業者名リスト. */
    private List<IfaLoginIdUpdateRegisterApiResponse_brokerName> brokerNameList;
    
    /** 仲介業者支店名リスト. */
    private List<IfaLoginIdUpdateRegisterApiResponse_branchName> branchNameList;
    
    /** 担当者名リスト. */
    private List<IfaLoginIdUpdateRegisterApiResponse_chargeName> chargeNameList;
    
    /** 非表示リスト. */
    private List<IfaLoginIdUpdateRegisterApiResponse_unDisplay> unDisplayList;
    
    /** 表示リスト. */
    private List<String> displayList;
    
}
