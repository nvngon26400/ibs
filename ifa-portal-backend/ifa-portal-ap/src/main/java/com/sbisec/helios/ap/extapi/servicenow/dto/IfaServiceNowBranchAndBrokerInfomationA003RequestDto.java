package com.sbisec.helios.ap.extapi.servicenow.dto;

import lombok.Data;

/**
 * 仲介業社支店一覧取得 リクエストDto
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowBranchAndBrokerInfomationA003RequestDto {
    
    /** 本支店コード. */
    private String branchCode;
    
    /** 仲介業者コード. */
    private String brokerCode;
    
}
