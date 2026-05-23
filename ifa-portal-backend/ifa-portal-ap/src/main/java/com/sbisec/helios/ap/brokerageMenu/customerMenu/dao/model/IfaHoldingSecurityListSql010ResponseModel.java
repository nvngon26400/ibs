package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 保有商品一覧 SQL010 SQL010.ST（セキュリティ・トークン）の商品明細取得レスポンス
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListSql010ResponseModel {

    /** 年月日 */
    private String dateYmd;

    /** 部店コード. */
    private String butenCode;

    /** 口座番号） */
    private String accountNumber;

    /** 銘柄コード */
    private String brandCode;

    /** 銘柄名 */
    private String brandName;

    /** 預り区分 */
    private String depositType;

    /** 預り区分名 */
    private String depositName;

    /** 取得単価 */
    private String acquirePrice;

    /** 時価 */
    private String appraiseMarketPrice;

    /** 評価損益 */
    private String appraiseProfitLossPrice;

    /** 約定基準残高 */
    private String execBaseBalance;

    /** 評価額（円貨）. */
    private String currenctPrice;

    /** 評価額合計（円貨）. */
    private String totalCurrenctPrice;

}
