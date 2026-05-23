package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 保有商品一覧 SQL002.基準価額、基準価額単位取得リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListSql002RequestModel {
    
    /** NRIコード（全角半角）. */
    private String nriCd;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
}
