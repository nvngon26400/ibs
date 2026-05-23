package com.sbisec.helios.ap.bizcommon.dao.model;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

@Data
public class Fct003Sql002RequestModel extends ModelBase {
    
    private String customerAttribute;
    
    private String productCd;
    
    private String tradeCd;
    
    private String countryCd;
    
    private String currencyCode;
}
