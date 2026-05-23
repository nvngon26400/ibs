package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaFxOrderInputA008DtoRequest {
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 口座分類（為替取引）. */
    private String accountType;
    
    /** 売却方法. */
    private String saleMethod;
    
    /** 注文金額. */
    private String exchangeOrderAmount;
    
    /** 注文ワーニングしきい値（数値(整数)）. */
    private String warningThreshold;
    
    /** 数量の指定方法. */
    private String quantityDesignationMethod;
    
    /** 数量入力（外貨）（数値(小数)）. */
    private String foreignVolume;
    
    /** 概算外貨数量. */
    private String foreignApproximateDeliveryAmount;
    
    /** 為替グループ（全角半角）. */
    private String exchangeGroup;
}
