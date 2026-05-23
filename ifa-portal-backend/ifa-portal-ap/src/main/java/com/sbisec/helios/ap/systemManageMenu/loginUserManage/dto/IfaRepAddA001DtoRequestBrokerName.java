package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaRepAddA001DtoRequestBrokerName {
    
    /** 仲介業者名（全角半角）. */
    private String brokerName;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
}
