package com.sbisec.helios.ap.brokerageMenu.ipoPo.dto;

import java.util.List;

import lombok.Data;


/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyInputA003ResponseDto {
    /** 銘柄コード */
    private String brandCode;
    /** 銘柄 */
    private String brand;
    /** 申込単位 */
    private String applyUnit;
    /** 売買単位 */
    private String unit;
    /** 売買単位区分 */
    private String sellBuyUnitType;
    /** 仮条件 */
    private String assumeCondition;
    /** ブックビルディング申込期間 */
    private String bookBuildingApplyPeriod;
    /** 抽選日 */
    private String lotDate;
    /** ブックビルディング申込期間（開始） */
    private String bookBuildingPresentationFrom;
    /** 発行価格区分 */
    private String issuePriceType;
    /** 価格表示（開始） */
    private String startPriceDisplay;
    /** 価格表示（刻み） */
    private String priceDisplayTick;
    /** 価格表示（終了） */
    private String finishPriceDisplay;
    /** ディスカウント率（開始） */
    private String startDiscountRate;
    /** ディスカウント率（刻み） */
    private String discountRateTick;
    /** ディスカウント率（終了） */
    private String finishDiscountRate;
    /** 投資家属性プルダウンリスト */
    private List<String> investorAttributePullDownList;
    /** 投資家属性順序 */
    private List<String> investorAttributeValueList;
    /** 目論見書閲覧 */
    private String readTime;
    /** 期間変更メッセージ */
    private String periodUpdateMessage;
    /** 価格変更メッセージ */
    private String priceUpdateMessage;
    
    private String marketOrderStrikePrice;
}
