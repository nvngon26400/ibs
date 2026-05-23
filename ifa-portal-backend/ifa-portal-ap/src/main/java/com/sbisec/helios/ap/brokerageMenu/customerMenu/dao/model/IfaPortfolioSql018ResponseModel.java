package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 画面ID：SUB0202_0101-01
 * 画面名：資産状況
 * SQL018 トータルリターン取得処理
 * 2023/12/27 新規作成
 *
 * @author SCSK 江口
 *
 */
@Data
public class IfaPortfolioSql018ResponseModel {

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

    /** 金額(買付・再投資・募集・入庫)合計 */
    private String amountBuyReinvestSubscriptDeliverInTotal;

    /** 金額(売却・償還・出庫)合計 */
    private String amountSellRedemptionDeliverOutTotal;

    /** 利益収益金合計 */
    private String couponRevenueTotal;

    /** トータルリターン */
    private String totalReturnYen;

    /** トータルリターン（率） */
    private BigDecimal totalReturnRate;

}
