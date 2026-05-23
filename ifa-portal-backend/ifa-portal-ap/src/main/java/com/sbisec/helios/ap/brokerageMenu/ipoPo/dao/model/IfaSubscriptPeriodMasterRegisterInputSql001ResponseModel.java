package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0204_01-04_1
 * 画面名：募集期間マスタ登録
 * 2024/03/26 新規作成
 *
 * @author SCSK 濱田
 */
@Data
public class IfaSubscriptPeriodMasterRegisterInputSql001ResponseModel {

    /** 銘柄コード（半角英数字） */
    private String brandCode;

    /** 銘柄名（全角半角） */
    private String brandName;

    /** 銘柄（全角半角） */
    private String brand;

    /** 売買単位（数値(整数)） */
    private String unit;

    /** 申込単位 */
    private String applyUnit;

    /** 抽選日時 */
    private String bbLotDate;

    /** 発行価格区分 */
    private String issuePriceType;

    /** 価格表示（開始）（数値(整数)） */
    private String startPriceDisplay;

    /** 価格表示（終了）（数値(整数)） */
    private String finishPriceDisplay;

    /** 価格表示（刻み） */
    private String priceDisplayTick;

    /** ディスカウント率（開始）（数値(小数)） */
    private String startDiscountRate;

    /** ディスカウント率（終了）（数値(小数)） */
    private String finishDiscountRate;

    /** ディスカウント率（刻み） */
    private String discountRateTick;

    /** 成行（ストライクプライス） */
    private String marketOrderStrikePrice;

    /** ブックビルディング申込期間（開始）（全角半角） */
    private String bookBuildingPresentationFrom;

    /** ブックビルディング申込期間（終了） */
    private String bookBuildingPresentationTo;

    /** ブックビルディング申込期間（開始）保持用 */
    private String bookBuildingPresentationFromForKeep;

    /** ブックビルディング申込期間 */
    private String bookBuildingApplyPeriod;

    /** 受渡期日 */
    private String deliveryDueDate;

    /** 緊急入力停止 */
    private String emergencyInputSuspend;

    /** 売買単位区分（半角英数字） */
    private String sellBuyUnitType;

    /** IPO／PO区分 */
    private String ipoPoClassification;

    /** 仮条件（全角半角） */
    private String assumeCondition;

}
