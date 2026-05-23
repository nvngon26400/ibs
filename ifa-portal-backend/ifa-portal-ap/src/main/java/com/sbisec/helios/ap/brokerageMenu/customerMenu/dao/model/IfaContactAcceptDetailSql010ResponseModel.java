package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactAcceptDetailSql010ResponseModel {

    /** IFA注文番号 */
    private String ifaOrderNo;

    /** 注文状況 */
    private String orderStatus;

    /** 2番目レコードの注文状況 */
    private String preOrderStatus;
}
