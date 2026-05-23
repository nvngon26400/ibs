package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaLoginIdUpdateRegisterA006DtoRequest {
    
    /** ログインID. */
    private String loginId;
    
    /** 権限コード（全角半角）. */
    private String privId;
    
    /** 本支店コード. */
    private String branchCode;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者支店コード（数字）. */
    private String subBrokerId;
    
    /** 仲介業者担当者コード（数字）. */
    private String employeeId;
    
    /** 社員名担当者名（全角半角）. */
    private String employeeNameChargeName;
    
    /** メニューリスト */
    private List<IfaLoginIdUpdateRegisterDtoRequest_menuList> menuList;
    
}
