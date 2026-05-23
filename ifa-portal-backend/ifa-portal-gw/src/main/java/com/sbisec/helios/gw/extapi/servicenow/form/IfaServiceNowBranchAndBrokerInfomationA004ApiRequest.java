package com.sbisec.helios.gw.extapi.servicenow.form;

import com.sbisec.helios.gw.extapi.servicenow.validator.BrokerBranchCode;
import com.sbisec.helios.gw.extapi.servicenow.validator.BrokerCode;

import lombok.Data;

/**
 * 営業員一覧を取得
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowBranchAndBrokerInfomationA004ApiRequest {
    
    /** 仲介業者コード. */
    @BrokerCode
    private String brokerCode;
    
    /** 仲介業者支店コード. */
    @BrokerBranchCode
    private String brokerBranchCode;
    
}
