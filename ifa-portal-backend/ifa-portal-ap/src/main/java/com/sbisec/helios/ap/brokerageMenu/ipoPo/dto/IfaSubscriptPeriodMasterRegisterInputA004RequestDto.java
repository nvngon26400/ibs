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
public class IfaSubscriptPeriodMasterRegisterInputA004RequestDto {

    /** 銘柄コード（半角英数字）(hidden) */
    private String brandCode;
    
    /** ブックビルディング申込期間（開始）（全角半角）(hidden) */
    private String bookBuildingPresentationFrom;

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

    /** IFAのブックビルディング申込期間（開始） */
    private String ifaBookBuildingPresentationFrom;

    /** IFAのブックビルディング申込期間（終了） */
    private String ifaBookBuildingPresentationTo;

    /** 更新日(hidden) */
    private String updateDate;

    /** 発行価格区分(hidden) */
    private String issuePriceType;

    /** 発行価格（数値(小数)）(hidden) */
    private String issueBbPriceHiddenItem;

    /** 電子交付のみ（全角半角）(hidden) */
    private String onlyElectronicDeliveryHiddenItem;

    /** 手数料 */
    private String comm;
    
    /** 受渡期日(hidden) */
    private String deliveryDueDate;
    
    /** 価格変更フラグ */
    private String changePriceFlag;
    
    /** 価格変更メッセージ */
    private String changePriceMsg;
    
    /** 期間変更フラグ */
    private String changeBbPeriodFlag;
    
    /** 期間変更メッセージ */
    private String changeBbPeriodMsg;

}
