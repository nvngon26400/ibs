package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaFxTradeOrderCancelConfirmA001DtoRequest {
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 通貨名（全角）. */
    private String currency;
    
    /** 注文番号（数字）. */
    private String orderNumber;
    
    /** サイクル番号. */
    private String cycleNumber;
    
    /** 注文イベントID. */
    private String orderEventId;
    
    /** 営業日. */
    private String businessDay;
    
}
