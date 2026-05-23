package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 保有商品一覧 SQL008 SQL008.債券銘柄用補助コード取得レスポンス
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListSql008ResponseModel {
    
    /** 補助コード. */
    private String tradeCodePare;
    
}
