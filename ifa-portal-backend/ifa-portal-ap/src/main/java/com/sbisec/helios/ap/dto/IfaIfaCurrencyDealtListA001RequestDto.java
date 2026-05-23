package com.sbisec.helios.ap.dto;

import lombok.Data;


/**
 * 【IFA】取扱通貨一覧A001リクエスト
 *
 * @author SCSK 浦田直輝
 * 
 */
@Data
public class IfaIfaCurrencyDealtListA001RequestDto {
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 表示基準日（受渡日）. */
    private String displayBaseDate;
    
    /** 代用種別. */
    private String collateralClass;
    
}
