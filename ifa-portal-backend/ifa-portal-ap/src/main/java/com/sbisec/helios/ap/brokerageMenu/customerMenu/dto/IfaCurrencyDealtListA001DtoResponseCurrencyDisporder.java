package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaCurrencyDealtListA001DtoResponseCurrencyDisporder {
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 通貨名（全角半角）. */
    private String currencyName;
    
    //実装バグ(画面項目定義確認漏れ)
    /** 為替取引. */
    private String fxTrade;
    
    /** 締時間1. */
    private String deadlines1;
    
    /** 締時間2. */
    private String deadlines2;
    
    /** 小数部有効桁数（数値(整数)）. */
    private Integer decimalLength;
    
    /** 買付参考レート（数値(小数)）. */
    private String purchaseReferenceRate;
    
    /** 売却参考レート（数値(小数)）. */
    private String referenceRateForSale;
    
    /** コード１. */
    private String disporder;
    
}
