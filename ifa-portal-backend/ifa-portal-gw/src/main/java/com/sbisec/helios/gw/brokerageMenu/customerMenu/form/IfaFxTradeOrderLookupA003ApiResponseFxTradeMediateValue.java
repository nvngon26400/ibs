package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaFxTradeOrderLookupA003ApiResponseFxTradeMediateValue {
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 媒介可否. */
    private String mediateValue;
    
}
