package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 保有商品一覧 SQL005 SQL005.優先市場取得リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListSql005RequestModel {
    
    /** 株式/投信ﾌﾗｸﾞ. */
    private String ipmProductFlag;
    
    /** 銘柄ｺｰﾄ. */
    private String ipmProductCode;
    
    /** 優先市場ﾌﾗｸﾞ. */
    private String ipmPriExFlag;
    
}
