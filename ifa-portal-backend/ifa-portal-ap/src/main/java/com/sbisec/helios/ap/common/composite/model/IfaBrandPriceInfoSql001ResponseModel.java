package com.sbisec.helios.ap.common.composite.model;

import lombok.Data;

@Data
public class IfaBrandPriceInfoSql001ResponseModel {
    
    /** 超. */
    private String beyondOrderPriceUnit;
    
    /** 以内. */
    private String withinOrderPriceUnit;
    
    /** 呼値. */
    private String callPriceOrderPriceUnit;
    
}
