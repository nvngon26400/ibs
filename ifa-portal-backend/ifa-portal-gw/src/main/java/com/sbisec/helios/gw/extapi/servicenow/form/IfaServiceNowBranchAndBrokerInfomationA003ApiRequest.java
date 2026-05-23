package com.sbisec.helios.gw.extapi.servicenow.form;

import com.sbisec.helios.gw.extapi.servicenow.validator.BranchCode;
import com.sbisec.helios.gw.extapi.servicenow.validator.BrokerCode;

import lombok.Data;

/**
 * 仲介業社支店一覧取得
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowBranchAndBrokerInfomationA003ApiRequest {
    
    /** 本支店コード. */
    @BranchCode
    private String branchCode;
    
    /** 仲介業者コード. */
    @BrokerCode
    private String brokerCode;
    
}
