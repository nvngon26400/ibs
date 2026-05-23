package com.sbisec.helios.gw.extapi.servicenow.form.common;

import com.sbisec.helios.gw.extapi.servicenow.validator.BranchCode;
import com.sbisec.helios.gw.extapi.servicenow.validator.BranchName;
import com.sbisec.helios.gw.extapi.servicenow.validator.BrokerBranchCode;
import com.sbisec.helios.gw.extapi.servicenow.validator.BrokerBranchName;
import com.sbisec.helios.gw.extapi.servicenow.validator.BrokerCode;
import com.sbisec.helios.gw.extapi.servicenow.validator.BrokerName;
import com.sbisec.helios.gw.extapi.servicenow.validator.EmployeeId;
import com.sbisec.helios.gw.extapi.servicenow.validator.EmployeeName;
import com.sbisec.helios.gw.extapi.servicenow.validator.ParentUserFlag;

import lombok.Data;

/**
 * 仲介業者
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowBroker {
    
    /** 親ユーザーフラグ. */
    @ParentUserFlag
    private String parentUserFlag;
    
    /** 本支店コード. */
    @BranchCode
    private String branchCode;

    /** 本支店名. */
    @BranchName
    private String branchName;

    /** 仲介業者コード. */
    @BrokerCode
    private String brokerCode;

    /** 仲介業者名. */
    @BrokerName
    private String brokerName;

    /** 仲介業者支店コード. */
    @BrokerBranchCode
    private String brokerBranchCode;

    /** 仲介業者支店名. */
    @BrokerBranchName
    private String brokerBranchName;

    /** 仲介業者営業員コード. */
    @EmployeeId
    private String employeeId;

    /** 仲介業者担当者名. */
    @EmployeeName
    private String employeeName;

}
