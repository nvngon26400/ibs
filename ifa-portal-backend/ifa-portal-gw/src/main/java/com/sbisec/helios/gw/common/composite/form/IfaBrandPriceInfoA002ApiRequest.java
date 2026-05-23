package com.sbisec.helios.gw.common.composite.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaBrandPriceInfoA002ApiRequest {
    
    /** 銘柄コード. */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;
    
    /** 市場. */
    @NotEmpty(message = "市場")
    @Size(max = 4, message = "市場")
    private String market;
    
}
