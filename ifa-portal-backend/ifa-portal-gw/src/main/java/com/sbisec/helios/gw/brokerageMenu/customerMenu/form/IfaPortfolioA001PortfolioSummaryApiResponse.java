package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 画面ID：SUB0202_0101-01
 * 画面名：資産状況
 * A001 APIレスポンス.保有商品一覧_資産状況サマリリスト
 * 2023/12/27 新規作成
 *
 * @author SCSK 江口
 *
 */
@Data
public class IfaPortfolioA001PortfolioSummaryApiResponse {

    /** 証券種別 */
    private String securityClass;

    /** 評価額（数値(小数)） */
    private String valuation;

    /** 評価損益 */
    private String profitAndLoss;

    /** 資産比率 */
    private String byProductAssetsRatio;
}
