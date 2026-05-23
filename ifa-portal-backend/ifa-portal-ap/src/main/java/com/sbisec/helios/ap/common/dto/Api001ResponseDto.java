package com.sbisec.helios.ap.common.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Api001ResponseDto {

    /**
     * 保有株数
     */
    private BigDecimal securitiesQuantity;
    
    /**
     * 売却注文中
     */
    private BigDecimal sellFixedOrderQuantity;
}
