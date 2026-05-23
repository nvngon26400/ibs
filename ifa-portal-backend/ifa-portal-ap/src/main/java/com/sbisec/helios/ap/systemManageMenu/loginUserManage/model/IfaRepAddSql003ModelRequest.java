package com.sbisec.helios.ap.systemManageMenu.loginUserManage.model;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaRepAddSql003ModelRequest {
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業支店コード. */
    private String subBrokerId;
    
}
