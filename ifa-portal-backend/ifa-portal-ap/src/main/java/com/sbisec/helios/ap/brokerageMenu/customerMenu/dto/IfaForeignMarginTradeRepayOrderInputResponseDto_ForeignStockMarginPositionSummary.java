package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaForeignMarginTradeRepayOrderInputResponseDto_ForeignStockMarginPositionSummary {
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 信用期日. */
    private String marginDueDate;
    
    /** 新規建単価（外貨）. */
    private String previousDayValue;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 決済注文中数量. */
    private String stockNumOrdering;
    
    /** 決済注文可能数量. */
    private String stockNumOrderable;
    
    /** 新規建代金（外貨）（数値(小数)）. */
    private String foreignNewPositionAmount;
    
    /** 諸経費合計額（外貨）. */
    private String costForeignLink;
    
    /** 評価損益（外貨）. */
    private String foreignPositionTotalProfitOrLossValuation;
    
}
