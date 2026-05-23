package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaFxTradeOrderLookupA003ApiRequest {
    
    /** 絞込期間from. */
    private String refinementPeriodFrom;
    
    /** 絞込期間to. */
    private String refinementPeriodTo;
    
    /** 注文状況. */
    private String orderStatus;
    
    /** 通貨コード. */
    private String currencyCode;
    
    /** 売買区分. */
    private String tradeKbn;
    
}
