package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactSql021ResponseModel {

    /** IFA注文番号 */
    private String ifaOrderNo;

    /** ポイント */
    private String point;

    /** 精算金額（概算） */
    private String netAmount;

}
