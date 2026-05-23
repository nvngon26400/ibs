package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import lombok.Data;

@Data
public class IfaJpyAmountUnpaidOverdraftAlertListA004RequestDto {
    /** 部店コード */
    private String butenCode;
    /** 口座番号 */
    private String accountNumber;
}
