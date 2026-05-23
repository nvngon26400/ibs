package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 保有商品一覧 SQL010 SQL010.ST（セキュリティ・トークン）の商品明細取得リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListSql010RequestModel {
    
    /** 部店コード. */
    private String butenCode;
    
    /** 口座番号. */
    private String accountNumber;
    
    /** 取得口座区分. */
    private String accountGetKbn;
    
}
