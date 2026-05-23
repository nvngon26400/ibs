package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaFxOrderInputA001DtoResponse {
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 通貨名（全角）. */
    private String currency;
    
    /** 小数部有効桁数（数値(整数)）. */
    private String decimalLength;
    
    /** 為替グループ（全角半角）. */
    private String exchangeGroup;
    
    /** 注文ワーニングしきい値（数値(整数)）. */
    private String warningThreshold;
    
    /** 取引下限（数値(小数)）. */
    private String closableQuantity;
    
    /** 取引上限（数値(小数)）. */
    private String tradeLimitMax;
    
    /** 取引単位. */
    private String tradeLimitMin;
    
    /** 外貨スピンボタン増減幅. */
    private String foreignSpinButtonRange;
    
    /** 参考レート（数値(小数)）. */
    private String referenceRate;
    
    /** 概算用レート. */
    private String approximationRate;
    
    /** デノミ（数値(整数)）. */
    private String denominator;
    
    /** 注文可能数量（数値(整数)）. */
    private String maxOrderableQuantity;
    
    /** 概算買付可能金額（数値(整数)）. */
    private String approximatePurchaseAmount;
    
    /** 外貨指定最大値. */
    private String foreignCurrencyMax;
    
    /** 円指定最小値. */
    private String yenCurrencyMin;
    
    /** 円指定最大値. */
    private String yenCurrencyMax;
    
    /** ジュニアNISA口座状態. */
    private String jrNisaAccountStatus;
    
}
