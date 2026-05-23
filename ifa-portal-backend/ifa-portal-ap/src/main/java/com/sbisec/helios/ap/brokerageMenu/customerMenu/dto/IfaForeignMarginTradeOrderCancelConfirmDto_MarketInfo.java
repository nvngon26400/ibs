package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaForeignMarginTradeOrderCancelConfirmDto_MarketInfo {
    
    /** 市場名. */
    private String marketName;
    
    /** 市場コード. */
    private String marketCode;
    
    /** 国コード（全角半角）. */
    private String countryCode;
    
}
