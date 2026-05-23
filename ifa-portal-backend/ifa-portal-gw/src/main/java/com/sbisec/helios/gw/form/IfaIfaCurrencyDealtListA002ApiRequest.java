package com.sbisec.helios.gw.form;

import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 【IFA】取扱通貨一覧A002リクエスト
 *
 * @author SCSK 浦田直輝
 * 
 */
@Data
public class IfaIfaCurrencyDealtListA002ApiRequest {
    
    /** 選択行通貨コード. */
    @NotEmpty(message = "選択行通貨コード")
    private String selectLineCurrencyCode;
    
    /** 売買区分. */
    @NotEmpty(message = "売買区分")
    @Size(max = 2, message = "売買区分")
    private String tradeKbn;
    
}
