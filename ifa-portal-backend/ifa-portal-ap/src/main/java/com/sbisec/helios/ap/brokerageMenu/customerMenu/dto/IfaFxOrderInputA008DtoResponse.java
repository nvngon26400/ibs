package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaFxOrderInputA008DtoResponse {
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 通貨名（全角）. */
    private String currency;
    
    /** 売却方法 */
    private String saleMethod;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 為替注文金額 */
    private String exchangeOrderAmount;
    
    /** 約定日時 */
    private String tradeDateTime;
    
    /** 受渡日 */
    private String settlementDate;
    
    /** 為替取引 */
    private String fxTrade;
    
    /** 為替レート */
    private String fxRate;
    
    /** 為替レート日時 */
    private String approximateRateExchangeRateDateTime;
    
    /** 受渡金額（円貨） */
    private String yenDeliveryAmount;
    
    /** 口座分類（為替取引） */
    private String accountType;
    
    /** 上乗せ区分 */
    private String fxRateAdditionalType;
    
    /** 上乗せ金額(金額) */
    private String additionalPrice;
    
    /** 上乗せ金額(パーセント) */
    private String additionalPricePercent;
    
    /** 数量の指定方法 */
    private String quantityDesignationMethod;
    
    /** 注文ワーニングしきい値*/
    private String warningThreshold;
    
    /** 注文ワーニングしきい値超過メッセージ. */
    private String orderWarningexceedingThreshold;
    
    /** デノミ（数値(整数)）. */
    private String denominator;
    
}
