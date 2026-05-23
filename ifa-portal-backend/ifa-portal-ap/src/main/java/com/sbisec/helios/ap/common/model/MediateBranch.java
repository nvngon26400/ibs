package com.sbisec.helios.ap.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 仲介業者本店・支店
 *
 * @author yoshitaka.nishida
 *
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class MediateBranch extends ModelBase {
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業支店コード */
    private String brokerBranchCode;
    
    /** 仲介業者支店種別 */
    private String brokerBranchKind;
    
    /** 仲介業者支店名 */
    @JsonProperty("brokerName")
    private String branchName;
}
