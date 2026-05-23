package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 為替取引媒介可否
 *
 * @author SCSK
 */

@Data
public class IfaFxTradeOrderLookupA001DtoResponseFxTradeMediatePropriety {
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 媒介可否. */
    private String mediatePropriety;
    
}
