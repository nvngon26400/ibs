package com.sbisec.helios.ap.extapi.servicenow.dto;

import lombok.Data;

/**
 * 本店・支店情報取得 リクエストDto
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowBranchAndBrokerInfomationA001RequestDto {
    
    /** 所属権限. */
    private String branchKind;
    
}
