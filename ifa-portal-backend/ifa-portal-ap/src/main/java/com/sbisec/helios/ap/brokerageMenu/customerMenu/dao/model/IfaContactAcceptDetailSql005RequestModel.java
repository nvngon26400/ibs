package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactAcceptDetailSql005RequestModel {

    /** 部店コード */
    private String butenCode;

    /** 口座番号 */
    private String accountNumber;

    /** 記録日時 */
    private String recordDate;

    /** 作成者 */
    private String createUser;
}
