package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaFxOrderInputA002ApiResponse {
    
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
    
    /** 概算受渡金額（数値(整数)）. */
    private String approximateDeliveryAmount;
    
    /** 概算外貨数量. */
    private String foreignApproximateDeliveryAmount;
    
    /** 更新日時. */
    private String updateTime;
    
}
