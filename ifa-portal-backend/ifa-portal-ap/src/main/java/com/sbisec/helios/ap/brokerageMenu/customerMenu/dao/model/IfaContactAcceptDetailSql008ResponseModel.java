package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactAcceptDetailSql008ResponseModel {

    /** IFA注文番号 */
    private String ifaOrderNo;

    /** 注文種別区分 */
    private String orderType;
}
