package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import lombok.Data;

@Data
public class IfaJpyAmountUnpaidOverdraftAlertListA004ResponseDto {
    
    /** 信用口座区分 */
    private String creditAccountKbn;
    /** 部店コード */
    private String butenCode;
    /** 口座番号 */
    private String accountNumber;
}
