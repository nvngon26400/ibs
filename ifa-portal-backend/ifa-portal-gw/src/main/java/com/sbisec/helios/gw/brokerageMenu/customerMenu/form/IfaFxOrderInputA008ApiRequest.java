package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaFxOrderInputA008ApiRequest {
    
    /** 通貨コード（全角半角）. */
    @NotEmpty(message = "通貨コード")
    private String currencyCode;
    
    /** 売買区分（全角半角）. */
    @NotEmpty(message = "売買区分")
    @Size(max = 2, message = "売買区分")
    private String tradeKbn;
    
    /** 口座分類（為替取引）. */
    @NotEmpty(message = "口座分類（為替取引）")
    private String accountTypeChange;
    
    /** 売却方法. */
    @NotEmpty(message = "売却方法")
    private String saleMethod;
    
    /** 注文金額. */
    @NotEmpty(message = "注文金額")
    private String exchangeOrderAmount;
    
    /** 注文ワーニングしきい値（数値(整数)）. */
    @Digits(integer = 17, fraction = 0, message = "注文ワーニングしきい値")
    @NotEmpty(message = "注文ワーニングしきい値")
    @Size(max = 22, message = "注文ワーニングしきい値")
    private String warningThreshold;
    
    /** 数量の指定方法. */
    @NotEmpty(message = "数量の指定方法")
    private String quantityDesignationMethod;
    
    /** 数量入力（外貨）（数値(小数)）. */
    @Digits(integer = 15, fraction = 2, message = "数量入力（外貨）")
    @NotEmpty(message = "数量入力（外貨）")
    @Size(max = 25, message = "数量入力（外貨）")
    private String foreignVolume;
    
    /** 概算外貨数量. */
    @NotEmpty(message = "概算外貨数量")
    private String foreignApproximateDeliveryAmount;
    
    /** 為替グループ（全角半角）. */
    @NotEmpty(message = "為替グループ")
    @Size(max = 2, message = "為替グループ")
    private String exchangeGroup;
    
    /** 口座分類（為替取引） */
    @NotEmpty(message = "口座分類")
    private String accountType;
}
