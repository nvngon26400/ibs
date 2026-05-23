package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaFxOrderInputA008ApiResponse {
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 通貨名（全角）. */
    private String currency;
    
    /** 売却方法. */
    private String saleMethod;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 為替注文金額. */
    private String exchangeOrderAmount;
    
    /** 約定日時 */
    private String tradeDateTime;
    
    /** 受渡日. */
    private String settlementDate;
    
    /** 為替取引（数値(整数)）. */
    private String fxTrade;
    
    /** 為替レート（数値(小数)）. */
    private String fxRate;
    
    /** 為替レート日時. */
    private String approximateRateExchangeRateDateTime;
    
    /** 受渡金額（円貨）（数値(小数)）. */
    private String yenDeliveryAmount;
    
    /** 口座分類（為替取引）. */
    private String accountType;
    
    /** 注文ワーニングしきい値（数値(整数)）. */
    private String warningThreshold;
    
    /** 注文ワーニングしきい値超過メッセージ（全角半角）. */
    private String orderWarningexceedingThreshold;
    
    /** デノミ（数値(整数)）. */
    private String denominator;
    
    /** 上乗せ区分. */
    private String fxRateAdditionalType;
    
    /** 上乗せ金額(金額). */
    private String additionalPrice;
    
    /** 上乗せ金額(パーセント). */
    // TODO 要確認
    private String additionalPricePercent;
    
    /** 数量の指定方法. */
    private String quantityDesignationMethod;
    
    /** 小数部有効桁数（数値(整数)）. */
    private String decimalLength;
    
}
