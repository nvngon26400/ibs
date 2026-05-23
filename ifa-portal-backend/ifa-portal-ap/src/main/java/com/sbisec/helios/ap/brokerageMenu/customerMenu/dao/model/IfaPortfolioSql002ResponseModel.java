package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0202_0101-01
 * 画面名：資産状況
 * SQL002 国内株式の商品明細取得
 * 2023/12/27 新規作成
 *
 * @author SCSK 江口
 *
 */
@Data
public class IfaPortfolioSql002ResponseModel {

    /** 銘柄コード（半角英数字） */
    private String brandCode;

    /** 銘柄名（全角半角） */
    private String brandName;

    /** 預り区分（全角半角） */
    private String depositType;

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

    /** 預り区分_ソート */
    private String depositTypeSort;

}
