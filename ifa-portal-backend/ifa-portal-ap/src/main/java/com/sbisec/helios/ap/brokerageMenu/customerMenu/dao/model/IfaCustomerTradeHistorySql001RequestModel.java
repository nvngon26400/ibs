package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0202_0109-01
 * 画面名：取引履歴（顧客別）
 * 2025/07/24 新規作成
 *
 * @author SCSK
 */
@Data
public class IfaCustomerTradeHistorySql001RequestModel {

    /** 顧客ID */
    private String customerCode;

    /** 銘柄コード */
    private String brandCode;

    /** 商品検索コード(大分類) */
    private String securitySearchCodeBigClass;

    /** 商品検索コード(中分類) */
    private String securitySearchCodeMidClass;

    /** 商品検索コード(小分類) */
    private String securitySearchCodeSmallClass;

    /** 取引検索コード(大分類) */
    private String tradeSearchCodeBigClass;

    /** 取引検索コード(中分類) */
    private String tradeSearchCodeMidClass;

    /** 取引検索コード(小分類) */
    private String tradeSearchCodeSmallClass;

    /** 並び順指定【項目】 */
    private String sortOrderItem;

    /** 並び順指定【属性】 */
    private String sortOrderProfile;

    /** 期間指定（From） */
    private String periodFrom;

    /** 期間指定（To） */
    private String periodTo;

    /** 実行パターン */
    private String exPattern;

    /** 最大取得件数 */
    private int maxRowNum;
}
