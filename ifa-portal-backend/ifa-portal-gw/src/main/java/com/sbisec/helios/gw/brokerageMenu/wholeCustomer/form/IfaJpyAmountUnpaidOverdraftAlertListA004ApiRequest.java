package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import lombok.Data;

@Data
public class IfaJpyAmountUnpaidOverdraftAlertListA004ApiRequest {
    /** 部店コード */
    private String butenCode;
    /** 口座番号 */
    private String accountNumber;
}
