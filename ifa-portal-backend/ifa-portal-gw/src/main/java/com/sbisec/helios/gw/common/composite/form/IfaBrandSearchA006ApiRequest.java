package com.sbisec.helios.gw.common.composite.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaBrandSearchA006ApiRequest {
    
    /** 銘柄コード. */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;
    
    /** 信用区分. */
    @NotEmpty(message = "信用区分")
    @Size(max = 4, message = "信用区分")
    private String tradeType;
}
