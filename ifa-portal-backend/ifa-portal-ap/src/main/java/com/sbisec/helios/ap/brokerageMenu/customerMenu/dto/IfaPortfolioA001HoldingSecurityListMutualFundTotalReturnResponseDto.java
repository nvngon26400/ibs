package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0101-01
 * 画面名：資産状況
 * A001 レスポンスDTO.保有商品一覧_投資信託トータルリターン
 * 2023/12/27 新規作成
 *
 * @author SCSK 江口
 *
 */
@Data
public class IfaPortfolioA001HoldingSecurityListMutualFundTotalReturnResponseDto {

    /** 保有状況 */
    private String holdingStatus;

    /** NRIコード（全角半角） */
    private String nriCd;

    /** ファンド名（全角半角） */
    private String fundName;

    /** 非特定預り区分 */
    private String nonSpecificDepositCategory;

    /** 預り移送_時価評価金額（当日） */
    private String depositTransferMarketValueToday;

    /** 金額(売却・償還・出庫)合計 */
    private String amountSellRedemptionDeliverOutTotal;

    /** 利金収益金合計 */
    private String couponRevenueTotal;

    /** 金額(買付・再投資・募集・入庫)合計 */
    private String amountBuyReinvestSubscriptDeliverInTotal;

    /** トータルリターン */
    private String totalReturnYen;

    /** トータルリターン（率） */
    private String totalReturnRate;

}
