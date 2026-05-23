package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaForeignMarginTradeOrderCancelConfirmApi_MarketInfo {
    
    /** 市場名. */
    private String marketName;
    
    /** 市場コード. */
    private String marketCode;
    
    /** 国コード（全角半角）. */
    private String countryCode;
    
}
