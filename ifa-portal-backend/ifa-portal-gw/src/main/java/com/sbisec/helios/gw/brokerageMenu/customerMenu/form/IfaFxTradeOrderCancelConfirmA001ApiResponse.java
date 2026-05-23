package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaFxTradeOrderCancelConfirmA001ApiResponse {
    
    /** 選択行_取引種別の売買. */
    private String selectedRowOrderTypeTrade;
    
    /** 選択行_数量・受渡金額の数量の通貨コード. */
    private String selectedRowForeignCurrencyCurrencyCode;
    
    /** 選択行_通貨の通貨名. */
    private String selectedRowCurrency;
    
    /** 選択行_注文番号. */
    private String selectedRowOrderNumber;
    
    /** 選択行_サイクル番号. */
    private String selectedRowCycleNumber;
    
    /** 選択行_注文イベントID. */
    private String selectedRowOrderEventId;
    
    /** 選択行_営業日. */
    private String selectedRowBusinessDay;
    
    /** 注文番号（数字）. */
    private String orderNumber;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 口座区分. */
    private String accountType;
    
    /** サイクル番号. */
    private String cycleNumber;
    
}
