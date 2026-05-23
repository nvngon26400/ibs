package com.sbisec.helios.gw.extapi.servicenow.form;

import com.sbisec.helios.gw.extapi.servicenow.validator.BranchCode;

import lombok.Data;

/**
 * SBI証券本支店の仲介業者支店を取得
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowBranchAndBrokerInfomationA002ApiRequest {
    
    /** 本支店コード. */
    @BranchCode
    private String branchCode;
    
}
