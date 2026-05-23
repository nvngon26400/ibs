package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 保有商品一覧 SQL005 SQL005.優先市場取得レスポンス
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListSql005ResponseModel {
    
    /** 取引所/委託会社ｺｰﾄﾞ. */
    private String ipmSeInvestmentsCode;
    
}
