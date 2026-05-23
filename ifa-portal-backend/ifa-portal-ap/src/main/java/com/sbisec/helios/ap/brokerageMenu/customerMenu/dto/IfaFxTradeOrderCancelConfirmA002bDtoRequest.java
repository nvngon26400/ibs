package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaFxTradeOrderCancelConfirmA002bDtoRequest {
    
    /** 注文番号（数字）. */
    private String orderNumber;
    
    /** 取引種別. */
    private String tradeCd;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** サイクル番号. */
    private String cycleNumber;
    
    /** 注文イベントID. */
    private String orderEventId;
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
    
    /** 通貨名（全角）. */
    private String currency;
    
    /** 営業日. */
    private String businessDay;
    
}
