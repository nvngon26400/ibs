package com.sbisec.helios.ap.systemManageMenu.loginUserManage.model;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaRepAddSql004ModelRequest {
    
    /** ユーザーID（全角半角）. */
    private String userId;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者担当者コード（数字）. */
    private String employeeId;
    
}
