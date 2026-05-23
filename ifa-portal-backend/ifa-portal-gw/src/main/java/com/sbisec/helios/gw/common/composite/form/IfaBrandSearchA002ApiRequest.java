package com.sbisec.helios.gw.common.composite.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaBrandSearchA002ApiRequest {
    
    /** キーワード. */
    @NotEmpty(message = "キーワード")
    @Size(max = 60, message = "キーワード")
    private String search;
    
}
