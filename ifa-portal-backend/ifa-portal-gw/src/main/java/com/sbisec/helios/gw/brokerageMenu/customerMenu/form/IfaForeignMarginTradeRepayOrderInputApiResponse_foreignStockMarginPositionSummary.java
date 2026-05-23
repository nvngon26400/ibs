package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaForeignMarginTradeRepayOrderInputApiResponse_foreignStockMarginPositionSummary {
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 信用期日. */
    private String marginDueDate;
    
    /** 新規建単価（外貨）. */
    private String previousDayValue;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 決済注文中数量. */
    private String quantityOrdering;
    
    /** 決済注文可能数量. */
    private String perOpenInterestMaxOrderableQuantity;
    
    /** 新規建代金（外貨）（数値(小数)）. */
    private String foreignNewPositionAmount;
    
    /** 諸経費合計額（外貨）. */
    private String costForeignLink;
    
    /** 評価損益（外貨）. */
    private String profitAndLossContainOtherCost;
    
}
