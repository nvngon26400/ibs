package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactSql022ResponseModel {

    /** トランザクションID */
    private String tranId;

    /** 拘束金額（NISA） */
    private String nisaKingaku;

    /** 精算金額 */
    private String seisanKingaku;
}
