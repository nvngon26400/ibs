package com.sbisec.helios.gw.extapi.servicenow.form;

import com.sbisec.helios.gw.extapi.servicenow.validator.BranchKind;

import lombok.Data;

/**
 * 本店・支店情報取得
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowBranchAndBrokerInfomationA001ApiRequest {
    
    /** 所属権限. */
    @BranchKind
    private String branchKind;
    
}
