package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 画面ID：SUB0202_0101-01
 * 画面名：資産状況
 * A001 APIレスポンス.保有商品一覧_現金
 * 2023/12/27 新規作成
 *
 * @author SCSK 江口
 *
 */
@Data
public class IfaPortfolioA001HoldingSecurityListCashApiResponse {

    /** 名称 */
    private String name;

    /** 評価額合計（数値(整数)） */
    private String valuationTotal;

    /** 為替レート（数値(小数)） */
    private String fxRate;

    /** 評価額（外貨）（数値(小数)） */
    private String foreignValuation;

    /** 評価損益 */
    private String profitAndLoss;

}
