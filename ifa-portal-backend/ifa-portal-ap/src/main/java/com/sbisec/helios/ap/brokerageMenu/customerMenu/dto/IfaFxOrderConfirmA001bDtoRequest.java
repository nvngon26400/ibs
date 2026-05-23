package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaFxOrderConfirmA001bDtoRequest {
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 口座分類（為替取引）. */
    private String accountType;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 上乗せ区分. */
    private String fxRateAdditionalType;
    
    /** 上乗せ金額. */
    private String additionalPrice;
    
    /** 売却方法. */
    private String saleMethod;
    
    /** アラート内容確認.注文金額上限超過. */
    private String warningMessage;
    
    /** 注文ワーニングしきい値超過メッセージ（全角半角）. */
    private String orderWarningexceedingThreshold;
    
    /** 注文ワーニングしきい値（数値(整数)）. */
    private String warningThreshold;
    
    /** 為替取引（数値(整数)）. */
    private String fxTrade;
    
    /** 約定日時. */
    private String tradeDateTime;
    
    /** 数量の指定方法. */
    private String quantityDesignationMethod;
    
    /** 通貨名（全角）. */
    private String currency;
    
    /** 小数部有効桁数（数値(整数)）. */
    private String decimalLength;
    
    /** デノミ（数値(整数)）. */
    private String denominator;
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
}
