package com.sbisec.helios.ap.brokerageMenu.ipoPo.dto;

import lombok.Data;

/**
 * 画面ID：SUB0204_01-04_1
 * 画面名：募集期間マスタ登録
 * 2024/03/26 新規作成
 *
 * @author SCSK 濱田
 */
@Data
public class IfaSubscriptPeriodMasterRegisterInputA001ResponseDto {

    /** 銘柄コード（半角英数字）. */
    private String brandCode;

    /** 銘柄名（全角半角）. */
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

    /** ブックビルディング申込期間（開始） */
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

    /** 銘柄コード１付き */
    private String brandCodeWith1;

    /** たばこ開示（全角半角） */
    private String smokingCigarette;

    /** 電子交付のみ（全角半角） */
    private String onlyElectronicDelivery;

    /** 配分上限株数（数値(整数)） */
    private String maxAllocation;

    /** 入金予定日 */
    private String depositScheduleDate;

    /** 募集期間FROM */
    private String bbPeriodFrom;

    /** 募集期間TO */
    private String bbPeriodTo;

    /** 発行価格（数値(小数)） */
    private String issueBbPrice;

    /** 上場日 */
    private String listedDate;

    /** 作成日 */
    private String createDate;

    /** 作成者 */
    private String createUser;

    /** 更新日 */
    private String updateDate;

    /** 更新者 */
    private String updateUser;

    /** 機能ID（全角半角） */
    private String functionId;

    /** IFAのブックビルディング申込期間（開始） */
    private String ifaBookBuildingPresentationFrom;

    /** IFAのブックビルディング申込期間（終了） */
    private String ifaBookBuildingPresentationTo;

    /** 募集入力件数（数値(整数)）. */
    private String subscriptInputCount;

    /** 募集期間情報登録件数（数値(整数)）. */
    private String subscriptPeriodInfoRegisterCount;

    /** 手数料 */
    private String comm;
    
    /** 価格変更フラグ */
    private String changePriceFlag;
    
    /** 価格変更メッセージ */
    private String changePriceMsg;
    
    /** 期間変更フラグ */
    private String changeBbPeriodFlag;
    
    /** 期間変更メッセージ */
    private String changeBbPeriodMsg;

}
