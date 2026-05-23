package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaFxTradeOrderCancelConfirmA002aDtoRequest {
    
    /** 注文番号（数字）. */
    private String orderNumber;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 営業日 */
    private String businessDay;
    
    /** サイクル番号. */
    private String cycleNumber;
    
    /** 注文イベントID. */
    private String orderEventId;
    
    /** 通貨名（全角）. */
    private String currency;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 取引口座（全角半角）. */
    private String tradingAccount;
    
}
