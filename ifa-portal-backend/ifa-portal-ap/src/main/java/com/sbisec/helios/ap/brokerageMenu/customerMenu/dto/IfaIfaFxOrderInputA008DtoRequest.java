package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaIfaFxOrderInputA008DtoRequest {
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 売却方法. */
    private String saleMethod;
    
    /** 為替注文金額. */
    private String orderAmount;
    
    /** 注文ワーニングしきい値（数値(整数)）. */
    private String warningThreshold;
    
    /** 数量の指定方法 */
    private String quantityDesignationMethod;
    
    /** 数量入力（外貨） */
    private String foreignVolume;
    
    /** 概算外貨数量 */
    private String approximateForeignCount;
    
    /** 為替グループ（全角半角）. */
    private String exchangeGroup;
}
