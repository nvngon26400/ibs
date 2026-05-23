package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaForeignMarginTradeRepayOrderInputResponseDto_ForeignStockMarginPositionDetailList {
    
    /** 国内新規約定日. */
    private String domesticTradeDate;
    
    /** 現地新規約定日. */
    private String foreignTradeDate;
    
    /** 現地決済期日. */
    private String foreignSetDate;
    
    /** 信用期日. */
    private String marginDueDate;
    
    /** 建玉必要保証金率. */
    private String marginRequirementRatio;
    
    /** 増担保規制建玉フラグ. */
    private String securityOpenPositionFlag;
    
    /** 新規建単価（外貨）. */
    private String sinyoPreviousDayValue;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 決済注文中数量. */
    private String quantityOrdering;
    
    /** 決済注文可能数量. */
    private String perOpenInterestMaxOrderableQuantity;
    
    /** 評価損益（外貨）. */
    private String profitAndLossContainOtherCost;
    
}
