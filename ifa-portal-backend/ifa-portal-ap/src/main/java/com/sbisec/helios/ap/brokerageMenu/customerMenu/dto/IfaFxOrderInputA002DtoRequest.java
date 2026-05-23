package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaFxOrderInputA002DtoRequest {
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 数量入力（外貨）（数値(小数)）. */
    private String foreignVolume;
    
    /** 数量入力（円）（数値(整数)）. */
    private String domesticVolume;
    
    /** 小数部有効桁数（数値(整数)）. */
    private String decimalLength;
    
    /** 為替グループ（全角半角）. */
    private String exchangeGroup;
    
    /** 取引下限（数値(小数)）. */
    private String closableQuantity;
    
    /** 取引上限（数値(小数)）. */
    private String tradeLimitMax;
    
    /** 取引単位. */
    private String tradeLimitMin;
    
    /** 口座分類（為替取引） */
    private String accountType;
}
