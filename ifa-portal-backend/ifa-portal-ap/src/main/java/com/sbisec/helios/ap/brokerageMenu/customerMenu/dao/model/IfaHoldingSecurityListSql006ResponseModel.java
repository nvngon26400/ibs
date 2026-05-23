package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 保有商品一覧 SQL006
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListSql006ResponseModel {

    /** ISA買付可能判定区分（当年）. */
    private String isaBuyAbleThisYear;

    /** ISA買付可能判定区分（翌年）. */
    private String isaBuyAbleNextYear;

    /** 顧客コード */
    private String customerId;

}
