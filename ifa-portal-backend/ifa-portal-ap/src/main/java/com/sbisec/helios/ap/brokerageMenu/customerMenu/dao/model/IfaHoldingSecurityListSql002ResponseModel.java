package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 保有商品一覧 SQL002.基準価額、基準価額単位取得レスポンス
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListSql002ResponseModel {
    
    /** 基準価額単位（数値(整数)）. */
    private String basePriceUnit;
    
    /** 協会コード（全角半角）. */
    private String kyoukaiCd;
    
    /** 回数（数値(整数)）. */
    private String times;
    
    /** 号2（全角半角）. */
    private String issue2;
    
}
