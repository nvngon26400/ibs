package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0202_0101-01
 * 画面名：資産状況
 * SQL019 投資信託、外国株式、外貨建MMFの商品明細取得
 * 2023/12/27 新規作成
 *
 * @author SCSK 江口
 *
 */
@Data
public class IfaPortfolioSql019ResponseModel {

    /** 年月日 */
    private String dateYmd;

    /** 仲介業者コード（数字） */
    private String brokerCode;

    /** 仲介業者名（全角半角） */
    private String brokerName;

    /** 扱者コード（半角英数字） */
    private String dealerNumber;

    /** 仲介業者支店コード（数字） */
    private String subBrokerId;

    /** 営業員コード（半角英数字） */
    private String empCode;

    /** 営業員名（全角半角） */
    private String brokerChargeName;

    /** コースコード */
    private String courceCode;

    /** コース名 */
    private String courceName;

    /** 部店 */
    private String branch;

    /** 口座番号（数字） */
    private String accountNumber;

    /** 顧客名（全角半角） */
    private String customerName;

    /** 証券種別コード（商品分類コード） */
    private String securityClassCode;

    /** 証券種別名（商品分類名） */
    private String securityClass;

    /** 銘柄コード（半角英数字） */
    private String brandCode;

    /** 銘柄名（全角半角） */
    private String brandName;

    /** 商品種別コード */
    private String commodityClassCode;

    /** 取引種別名（全角半角） */
    private String tradeTypeName;

    /** 預り区分（全角半角） */
    private String depositType;

    /** 預り区分名 */
    private String depositTypeName;

    /** 取得単価（数値(整数)） */
    private String openPrice;

    /** 時価（数値(小数)） */
    private String price;

    /** 評価損益 */
    private String profitAndLoss;

    /** 約定基準残高（数値(整数)） */
    private String contractStandardDeposit;

    /** 評価額（円貨）（数値(整数)） */
    private String valuation;

    /** 通貨 */
    private String currency;

    /** 評価額（外貨）（数値(小数)） */
    private String foreignValuation;

    /** 為替レート（数値(小数)） */
    private String fxRate;

    /** 評価額合計（円貨） */
    private String valuationTotal;

    /** 預り区分_ソート */
    private String depositTypeSort;

    /** 通貨_ソート */
    private String currencySort;

}
