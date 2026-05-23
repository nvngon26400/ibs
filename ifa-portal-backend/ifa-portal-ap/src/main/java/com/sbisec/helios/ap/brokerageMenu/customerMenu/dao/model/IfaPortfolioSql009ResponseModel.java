package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0202_0101-01
 * 画面名：資産状況
 * SQL009 外国債券 (外貨建仕組債)の商品明細取得
 * 2023/12/27 新規作成
 *
 * @author SCSK 江口
 *
 */
@Data
public class IfaPortfolioSql009ResponseModel {

    /** 銘柄コード（半角英数字） */
    private String brandCode;

    /** 銘柄名（全角半角） */
    private String brandName;

    /** 約定基準残高（数値(整数)） */
    private String contractStandardDeposit;

    /** 取得単価（数値(整数)） */
    private String openPrice;

    /** 通貨 */
    private String currency;

    /** 為替レート（数値(小数)） */
    private String fxRate;

    /** 評価額（円貨）（数値(整数)） */
    private String valuation;

    /** 証券種別コード（商品分類コード） */
    private String securityClassCode;

    /** 預り区分_ソート */
    private String depositTypeSort;

    /** 通貨_ソート */
    private String currencySort;

}
