package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaFxTradeOrderCancelConfirmA002ApiResponse {
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 口座区分. */
    private String accountType;
    
    /** 注文種別表示. */
    private String orderTypeDisplay;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 通貨名（全角）. */
    private String currency;
    
}
