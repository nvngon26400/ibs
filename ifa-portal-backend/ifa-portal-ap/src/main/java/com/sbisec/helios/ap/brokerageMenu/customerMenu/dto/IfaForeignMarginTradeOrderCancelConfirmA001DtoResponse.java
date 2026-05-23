package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaForeignMarginTradeOrderCancelConfirmA001DtoResponse {
    
    /** 注文番号（数字）. */
    private String orderNumber;
    
    /** 注文Sub番号（数字）. */
    private String orderSubNumber;
    
    /** 銘柄情報. */
    private IfaForeignMarginTradeOrderCancelConfirmDto_BrandInfo BrandInfo;
    
    /** 取引通貨. */
    private String currencyCode;
    
    /** 市場情報. */
    private IfaForeignMarginTradeOrderCancelConfirmDto_MarketInfo MarketInfo;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 注文数量. */
    private String foreignQuantity;
    
    /** 価格条件. */
    private String orderPriceKindList;
    
    /** 注文単価（数値(小数)）. */
    private String hiddenOrderPrice;
    
    /** 発火条件価格. */
    private String stopOrderPrice2;
    
    /** 期間条件. */
    private String periodRadio;
    
    /** 期間. */
    private String period;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 決済方法（全角半角）. */
    private String currencyTypeRadio;
    
    /** 注文日時. */
    private String orderDate;
    
    /** 国内約定日. */
    private String domesticTradeDate;
    
    /** 現地約定日. */
    private String foreignTradeDate;
    
    /** 返済建玉指定方法（全角半角）. */
    private String repaymentMethodRadio;
    
    /** 返済選択順序（全角半角）. */
    private String sortOrderRadio;
    
    /** 信用期日. */
    private String marginDueDate;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
}
