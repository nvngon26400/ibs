package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;


import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaForeignAmountUnpaidOverdraftAlertListA002RequestDto {

    /** 仲介業者コード（数字）. */
    private String brokerCode;

    /** 仲介業者除外（半角英数字）. */
    private String chkBrokerCodeExclude;

    /** 支店コード（数字）. */
    private String branchCode;

    /** 営業員コード（半角英数字）. */
    private String empCode;

    /** 部店コード（半角英数字）. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;

    /** 顧客名（漢字／カナ）（全角半角）. */
    private String customerNameKanjiKana;

    /** 顧客名（漢字／カナ）_条件. */
    private String customerNameKanjiKanaTerms;

    /** 取引コース（全角半角）. */
    private String course;

}
