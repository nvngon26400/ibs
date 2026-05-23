package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 通貨
 *
 * @author SCSK
 */

@Data
public class IfaFxTradeOrderLookupA001DtoResponseCurrency {
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 通貨名（全角）. */
    private String currency;
    
    /** 小数部有効桁数（数値(整数)）. */
    private String decimalLength;
    
}
