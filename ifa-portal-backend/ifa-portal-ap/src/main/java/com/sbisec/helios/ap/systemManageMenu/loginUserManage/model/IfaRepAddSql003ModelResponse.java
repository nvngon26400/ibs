package com.sbisec.helios.ap.systemManageMenu.loginUserManage.model;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaRepAddSql003ModelResponse {
    
    /** 仲介業者営業員コード（半角英数字）. */
    private String brokerChargeCode;
    
    /** 仲介業者担当者名（全角半角）. */
    private String employeeName;
    
}
