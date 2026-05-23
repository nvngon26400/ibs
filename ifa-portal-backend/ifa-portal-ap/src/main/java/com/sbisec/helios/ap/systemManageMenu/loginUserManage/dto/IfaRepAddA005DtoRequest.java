package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaRepAddA005DtoRequest {
    
    /** ユーザーID（全角半角）. */
    private String userId;
    
    /** SBI証券支店コード. */
    private String sbiSecurityCode;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者支店コード（数字）. */
    private String subBrokerId;
    
    /** 仲介業者担当者コード（数字）. */
    private String employeeId;
    
    /** 担当者名（全角半角）. */
    private String chargeName;
    
}
