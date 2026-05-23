package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaFxTradeOrderLookupA001ApiResponseCurrency {
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 通貨名（全角）. */
    private String currency;
    
    /** 小数部有効桁数（数値(整数)）. */
    private String decimalLength;
    
}
