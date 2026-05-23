package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0202_0101-01
 * 画面名：資産状況
 * SQL011 外貨預金明細取得
 * 2023/12/27 新規作成
 *
 * @author SCSK 江口
 *
 */
@Data
public class IfaPortfolioSql011ResponseModel {

    /** 通貨 */
    private String currency;

    /** 為替レート（数値(小数)） */
    private String fxRate;

    /** 評価額（外貨）（数値(小数)） */
    private String foreignValuation;

    /** 評価額（円貨）（数値(整数)） */
    private String valuation;

    /** 証券種別コード（商品分類コード） */
    private String securityClassCode;

    /** 通貨ソート */
    private String currencySort;

}
