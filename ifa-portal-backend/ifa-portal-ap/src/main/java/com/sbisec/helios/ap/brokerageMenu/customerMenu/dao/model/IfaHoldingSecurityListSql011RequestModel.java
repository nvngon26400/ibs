package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 保有商品一覧 SQL011.基準価額取得リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListSql011RequestModel {
    
    /** 協会コード. */
    private String kyoukaiCd;
    
}
