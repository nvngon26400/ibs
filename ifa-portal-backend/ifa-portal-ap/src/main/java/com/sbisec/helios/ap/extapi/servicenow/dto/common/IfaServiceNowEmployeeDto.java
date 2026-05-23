package com.sbisec.helios.ap.extapi.servicenow.dto.common;

import lombok.Data;

/**
 * 営業員情報 Dto
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowEmployeeDto {

    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 仲介業者支店コード. */
    private String brokerBranchCode;
    
    /** 仲介業者営業員コード. */
    private String employeeId;
    
    /** 仲介業者担当者名. */
    private String employeeName;
    
}
