package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaFxOrderInputA002ApiRequest {
    
    /** 通貨コード（全角半角）. */
    @NotEmpty(message = "通貨コード")
    private String currencyCode;
    
    /** 売買区分（全角半角）. */
    @NotEmpty(message = "売買区分")
    @Size(max = 2, message = "売買区分")
    private String tradeKbn;
    
    /** 数量入力（外貨）（数値(小数)）. */
    @Digits(integer = 15, fraction = 2, message = "数量入力（外貨）")
    @Size(max = 25, message = "数量入力（外貨）")
    private String foreignVolume;
    
    /** 数量入力（円）（数値(整数)）. */
    @Digits(integer = 17, fraction = 0, message = "数量入力（円）")
    @Size(max = 22, message = "数量入力（円）")
    private String domesticVolume;
    
    /** 小数部有効桁数（数値(整数)）. */
    private String decimalLength;
    
    /** 為替グループ（全角半角）. */
    private String exchangeGroup;
    
    /** 取引下限（数値(小数)）. */
    @Digits(integer = 15, fraction = 2, message = "取引下限")
    @NotEmpty(message = "取引下限")
    @Size(max = 25, message = "取引下限")
    private String closableQuantity;
    
    /** 取引上限（数値(小数)）. */
    @Digits(integer = 15, fraction = 2, message = "取引上限")
    @NotEmpty(message = "取引上限")
    @Size(max = 25, message = "取引上限")
    private String tradeLimitMax;
    
    /** 取引単位. */
    @NotEmpty(message = "取引単位")
    private String tradeLimitMin;
    
    /** 口座分類（為替取引） */
    @NotEmpty(message = "口座分類")
    private String accountType;
}
