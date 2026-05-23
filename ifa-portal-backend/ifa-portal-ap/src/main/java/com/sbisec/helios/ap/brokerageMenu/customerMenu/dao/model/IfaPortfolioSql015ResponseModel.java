package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0202_0101-01
 * 画面名：資産状況
 * SQL015 米株信用建玉明細取得
 * 2023/12/27 新規作成
 *
 * @author SCSK 江口
 *
 */
@Data
public class IfaPortfolioSql015ResponseModel {

    /** 銘柄コード（半角英数字） */
    private String brandCode;

    /** 銘柄名（全角半角） */
    private String brandName;

    /** 取引種別名（全角半角） */
    private String tradeTypeName;

    /** 指定扱区分（全角半角） */
    private String designationDealtClassification;

    /** 返済期限 */
    private String lastTradeDate;

    /** 為替レート（数値(小数)） */
    private String fxRate;

    /** 約定基準残高（数値(整数)） */
    private String contractStandardDeposit;

    /** 取得単価（数値(整数)） */
    private String openPrice;

    /** 時価（数値(小数)） */
    private String price;

    /** 評価額（円貨）（数値(整数)） */
    private String valuation;

    /** 評価損益 */
    private String profitAndLoss;

    /** 通貨 */
    private String currency;

}
