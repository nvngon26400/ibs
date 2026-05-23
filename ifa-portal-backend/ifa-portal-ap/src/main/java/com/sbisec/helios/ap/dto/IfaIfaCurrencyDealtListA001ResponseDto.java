package com.sbisec.helios.ap.dto;

import java.util.List;

import lombok.Data;

/**
 * 【IFA】取扱通貨一覧A001レスポンスDTO
 *
 * @author SCSK 浦田直輝
 * 
 */
@Data
public class IfaIfaCurrencyDealtListA001ResponseDto {
    
    /** 通貨リスト. */
    private List<IfaIfaCurrencyDealtListElements> currencyList;
    
}
