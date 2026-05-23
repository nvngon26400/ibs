package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactAcceptDetailSql012ResponseModel {

    /** トランザクションID */
    private String tranId;

    /** 取消区分 */
    private String torikeshiKbn;
}
