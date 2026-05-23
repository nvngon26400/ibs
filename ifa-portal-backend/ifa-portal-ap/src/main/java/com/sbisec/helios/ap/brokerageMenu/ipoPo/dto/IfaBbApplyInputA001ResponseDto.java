package com.sbisec.helios.ap.brokerageMenu.ipoPo.dto;

import java.util.List;


import lombok.Data;

/**
 *
 * @author BASE李
 *
 */
@Data
public class IfaBbApplyInputA001ResponseDto {

    /** 銘柄コード（半角英数字）. */
    private String brandCode;

    /** 銘柄（全角半角）. */
    private String brand;

    /** 申込単位. */
    private String applyUnit;

    /** 売買単位（数値(整数)）. */
    private String unit;

    /** 売買単位区分（半角英数字）. */
    private String sellBuyUnitType;

    /** 仮条件（全角半角）. */
    private String assumeCondition;

    /** ブックビルディング申込期間. */
    private String bookBuildingApplyPeriod;

    /** 抽選日. */
    private String lotDate;

    /** ブックビルディング申込期間（開始）（YYYYMMDD）. */
    private String bookBuildingPresentationFrom;

    /** 発行価格区分. */
    private String issuePriceType;

    /** 価格表示（開始）（数値(整数)）. */
    private String startPriceDisplay;

    /** 価格表示（刻み）. */
    private String priceDisplayTick;

    /** 価格表示（終了）（数値(整数)）. */
    private String finishPriceDisplay;

    /** ディスカウント率（開始）（数値(小数)）. */
    private String startDiscountRate;

    /** ディスカウント率（刻み）. */
    private String discountRateTick;

    /** ディスカウント率（終了）（数値(小数)）. */
    private String finishDiscountRate;

    /** 投資家属性プルダウンリスト. */
    private List<String> investorAttributePullDownList;
    
    /** 投資家属性順序リスト */
    private List<String> investorAttributeValueList;

    /** 遷移元画面（全角半角）. */
    private String transitionSourceScreen;

    /** 期間変更メッセージ */
    private String periodUpdateMessage;
    
    /** 価格変更メッセージ */
    private String priceUpdateMessage;
    
    private String marketOrderStrikePrice;
}
