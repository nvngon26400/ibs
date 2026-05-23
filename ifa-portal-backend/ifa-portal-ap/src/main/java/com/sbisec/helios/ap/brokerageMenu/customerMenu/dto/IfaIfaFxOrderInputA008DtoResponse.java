package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaIfaFxOrderInputA008DtoResponse {
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 通貨名（全角）. */
    private String currency;
    
    /** 売却方法. */
    private String saleMethod;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 為替注文金額. */
    private String orderAmount;
    
    /** 約定日時. */
    private String tradeDateTime;
    
    /** 受渡日. */
    private String settlementDate;
    
    /** 為替レート（数値(小数)）. */
    private String fxRate;
    
    /** 為替レート日時. */
    private String exchangeRateDateTime;
    
    /** 受渡金額（円貨）（数値(小数)）. */
    private String yenDeliveryAmount;
    
    /** 注文ワーニングしきい値（数値(整数)）. */
    private String warningThreshold;
    
    /** 注文ワーニングしきい値超過メッセージ（全角半角）. */
    private String orderWarningexceedingThreshold;
    
    /** 適用スプレッド（数値(小数)）. */
    private String selectedCurrencyInfo;
    
    /** 上乗せ区分. */
    private String fxRateAdditionalType;
    
    /** 上乗せ金額(金額). */
    private String adjustAmount;
    
    /** 上乗せ金額(パーセント). */
    private String adjustPercent;
    
    /** 数量の指定方法 */
    private String quantityDesignationMethod;
    
    /** 小数部有効桁数（数値(整数)）. */
    private String decimalLength;
}
