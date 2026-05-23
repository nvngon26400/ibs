package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaIfaFxOrderInputA002DtoResponse {
    
    /** 参考レート（数値(小数)）. */
    private String referenceRate;
    
    /** 概算用レート. */
    private String computeExchangeRate;
    
    /** デノミ（数値(整数)）. */
    private String denominator;
    
    /** スプレッド. */
    private String adjustPrice;
    
    /** 注文可能数量（数値(整数)）. */
    private String maxOrderableQuantity;
    
    /** 概算買付可能金額（数値(整数)）. */
    private String approximatePurchaseAmount;
    
    /** 外貨指定最大値. */
    private String foreignDesignationMaxValue;
    
    /** 円指定最小値. */
    private String yenDesignationMinValue;
    
    /** 円指定最大値. */
    private String yenDesignationMaxValue;
    
    /** 概算受渡金額（数値(整数)）. */
    private String approximateDeliveryAmount;
    
    /** 概算外貨数量. */
    private String approximateForeignCount;
    
    /** 更新日時. */
    private String updateTime;
}
