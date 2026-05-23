package com.sbisec.helios.ap.common.composite.dto;

import lombok.Data;

@Data
public class IfaBrandSearchA001DtoRequest {
    
    /** 銘柄コード. */
    private String brandCode;
    
    /** 信用区分. */
    private String tradeType;
    
}
