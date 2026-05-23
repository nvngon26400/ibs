package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 手数料率リスト(n)
 *
 * @author SCSK
 */
@Data
public class IfaDomesticMutualFundOrderInputCommRate {
    
    /** 手数料率条件（全角半角）. */
    private BigDecimal commRateConditions;
    
    /** 手数料率（数値(小数)）. */
    private BigDecimal commRate;
    
}
