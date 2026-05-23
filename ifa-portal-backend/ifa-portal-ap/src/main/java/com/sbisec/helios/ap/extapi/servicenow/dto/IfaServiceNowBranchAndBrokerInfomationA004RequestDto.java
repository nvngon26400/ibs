package com.sbisec.helios.ap.extapi.servicenow.dto;

import lombok.Data;

/**
 * 営業員一覧を取得 リクエストDto
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowBranchAndBrokerInfomationA004RequestDto {
    
    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 仲介業者支店コード. */
    private String brokerBranchCode;
    
}
