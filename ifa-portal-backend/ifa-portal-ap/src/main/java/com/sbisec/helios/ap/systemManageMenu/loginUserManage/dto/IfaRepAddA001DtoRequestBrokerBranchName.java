package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaRepAddA001DtoRequestBrokerBranchName {
    
    /** 仲介業者支店名. */
    private String brokerBranchName;
    
    /** 仲介業者支店コード（数字）. */
    private String subBrokerId;
    
}
