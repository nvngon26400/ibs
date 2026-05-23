package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaFxOrderConfirmA001ApiResponse {
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 注文日時. */
    private String orderDate;
    
    /** 注文番号（数字）. */
    private String orderNumber;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 数量の通貨. */
    private String currencyCode;
    
    /** 数量の通貨名. */
    private String currency;
    
    /** 約定日時. */
    private String tradeDateTime;
    
    /** 為替レート（数値(小数)）. */
    private String fxRate;
    
    /** 為替レート日時. */
    private String approximateRateExchangeRateDateTime;
    
    /** 上乗せ区分. */
    private String fxRateAdditionalType;
    
    /** 上乗せ金額. */
    private String additionalPrice;
    
    /** 受渡金額（数値(整数)）. */
    private String deliveryAmount;
    
    /** 為替取引（数値(整数)）. */
    private String fxTrade;
    
    /** 受渡日. */
    private String settlementDate;
    
    /** 口座分類（為替取引）. */
    private String accountType;
    
    /** 注文ワーニングしきい値超過メッセージ（全角半角）. */
    private String orderWarningexceedingThreshold;
    
    /** 注文ワーニングしきい値（数値(整数)）. */
    private String warningThreshold;
    
    /** 小数部有効桁数（数値(整数)）. */
    private String decimalLength;
    
    /** デノミ（数値(整数)）. */
    private String denominator;
    
}
