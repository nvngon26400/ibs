package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto;

import lombok.Data;

/**
 * SUB0506_01-02_自己点検記録簿詳細 DTO要求
 *
 * @author SCSK
 *
 */
@Data
public class IfaSelfInspectBlotterDetailA001RequestDto {
    
    /** 登録年月. */
    private String registerDate;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者名（全角半角）. */
    private String brokerName;
    
}
