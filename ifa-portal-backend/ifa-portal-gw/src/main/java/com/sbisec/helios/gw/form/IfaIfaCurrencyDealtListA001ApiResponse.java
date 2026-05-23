package com.sbisec.helios.gw.form;

import com.ibm.icu.math.BigDecimal;

import lombok.Data;


/**
 * 【IFA】取扱通貨一覧A001レスポンス
 *
 * @author SCSK 浦田直輝
 * 
 */
@Data
public class IfaIfaCurrencyDealtListA001ApiResponse {
    
    /** 通貨リスト. */
    private String currencyList;
    
    /** 通貨コード. */
    private String currencyCode;
    
    /** 通貨名（全角半角）. */
    private String currencyName;
    
    /** 締時間1. */
    private String deadLineTime1;
    
    /** 締時間2. */
    private String deadLineTime2;
    
    /** 小数部有効桁数（数値(整数)）. */
    private Integer decimalLength;
    
    /** 買付参考レート（数値(小数)）. */
    private String purchaseReferenceRate;
    
    /** 売却参考レート（数値(小数)）. */
    private String referenceRateForSale;
    
}
