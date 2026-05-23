package com.sbisec.helios.ap.dto;

import com.ibm.icu.math.BigDecimal;

import lombok.Data;

/**
 * 【IFA】取扱通貨一覧通貨リスト
 *
 * @author SCSK 浦田直輝
 * 
 */
@Data
public class IfaIfaCurrencyDealtListElements {
    
    /** 通貨コード. */
    private String currencyCode;
    
    /** 通貨名（全角半角）. */
    private String currencyName;
    
    /** 小数部有効桁数（数値(整数)）. */
    private Integer decimalLength;
    
    /** 買付参考レート（数値(小数)）. */
    private String purchaseReferenceRate;
    
    /** 売却参考レート（数値(小数)）. */
    private String referenceRateForSale;
    
}
