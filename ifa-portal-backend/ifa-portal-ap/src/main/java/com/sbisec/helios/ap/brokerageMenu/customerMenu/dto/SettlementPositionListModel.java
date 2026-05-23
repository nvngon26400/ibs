package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SettlementPositionListModel {
    
    /** 新規約定日. */
    private String openTradeDate;
    
    /** 決済期日（全角半角）. */
    private String lastTradeDate;
    
    /** 建市場（全角半角）. */
    private String bargainMarket;
    
    /** 親株新規約定日. */
    private String orgNewTradeDate;
    
    /** 建単価. */
    private String builtPrice;
    
    /** 注文数量. */
    private String orderQuantity;
    
    /** 約定数量（数値(整数)）. */
    private String actualQuantity;
    
    /** 諸経費合計（数値(整数)）. */
    private String costTotal;
    
    /** 平均約定単価（算出）. */
    private String averageTradePrice;
    
    /** 決済損益（算出）. */
    private String settlementLossProfit;
    
}
