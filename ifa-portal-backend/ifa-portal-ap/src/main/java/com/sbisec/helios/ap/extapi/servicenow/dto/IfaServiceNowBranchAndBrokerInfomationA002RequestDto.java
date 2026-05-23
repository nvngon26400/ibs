package com.sbisec.helios.ap.extapi.servicenow.dto;

import lombok.Data;

/**
 * SBI証券本支店の仲介業者支店を取得 リクエストDto
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowBranchAndBrokerInfomationA002RequestDto {
    
    /** 本支店コード. */
    private String branchCode;
    
}
