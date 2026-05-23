package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 保有商品一覧 SQL009 SQL009.債券ST銘柄コード取得レスポンス
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListSql009ResponseModel {
    
    /** 銘柄コード. */
    private String brandCode;
    
}
