package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactAcceptDetailSql004RequestModel {

    /** 顧客コード */
    private String customerId;

    /** 記録日時 */
    private String recordDate;

    /** 作成者 */
    private String createUser;
}
