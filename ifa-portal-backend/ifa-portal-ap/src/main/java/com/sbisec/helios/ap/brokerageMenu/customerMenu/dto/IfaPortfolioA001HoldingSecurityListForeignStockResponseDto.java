package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0101-01
 * 画面名：資産状況
 * A001 レスポンスDTO.保有商品一覧_外国株式
 * 2023/12/27 新規作成
 *
 * @author SCSK 江口
 *
 */
@Data
public class IfaPortfolioA001HoldingSecurityListForeignStockResponseDto {

    /** 銘柄コード（半角英数字） */
    private String brandCode;

    /** 銘柄名（全角半角） */
    private String brandName;

    /** 預り区分（全角半角） */
    private String depositType;

    /** 約定基準残高（数値(整数)） */
    private String contractStandardDeposit;

    /** 通貨 */
    private String currency;

    /** 為替レート（数値(小数)） */
    private String fxRate;

    /** 取得単価（数値(整数)） */
    private String openPrice;

    /** 時価（現地通貨） */
    private String marketValueForeign;

    /** 評価額（円貨）（数値(整数)） */
    private String valuation;

    /** 評価損益 */
    private String profitAndLoss;

}
