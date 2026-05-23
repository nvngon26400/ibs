package com.sbisec.helios.ap.systemManageMenu.loginUserManage.model;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaRepAddSql001ModelResponse {
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業支店コード. */
    private String subBrokerId;
    
    /** 仲介業者支店種別. */
    private String brokerBranchKind;
    
    /** 仲介業者支店名. */
    private String brokerBranchName;
    
}
