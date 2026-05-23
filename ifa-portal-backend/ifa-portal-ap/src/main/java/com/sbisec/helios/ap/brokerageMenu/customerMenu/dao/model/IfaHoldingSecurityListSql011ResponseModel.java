package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 保有商品一覧 SQL011.基準価額取得レスポンス
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListSql011ResponseModel {

    /** 基準価額 */
    private String basePrice;

    /** 協会コード. */
    private String kyoukaiCd;

}
