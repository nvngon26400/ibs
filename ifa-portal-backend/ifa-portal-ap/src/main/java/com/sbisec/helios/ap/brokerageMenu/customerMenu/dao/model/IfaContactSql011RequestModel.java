package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactSql011RequestModel {

    /** 部店コード */
    private String butenCode;

    /** 口座番号 */
    private String accountNumber;
}
