package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * 画面ID：SUB0204_01-04_1
 * 画面名：募集期間マスタ登録
 * 2024/03/26 新規作成
 *
 * @author SCSK 濱田
 */
@Data
public class IfaSubscriptPeriodMasterRegisterInputA004ApiRequest {

    /** 銘柄コード（半角英数字） */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;

    /** ブックビルディング申込期間（開始）（全角半角） */
    @NotEmpty(message = "ブックビルディング申込期間（開始）")
    @Size(max = 8, message = "ブックビルディング申込期間（開始）")
    private String bookBuildingPresentationFrom;

    /** 銘柄コード１付き */
    @NotEmpty(message = "銘柄コード１付き")
    private String brandCodeWith1;

    /** たばこ開示（全角半角） */
    @NotEmpty(message = "たばこ開示")
    @Size(max = 10, message = "たばこ開示")
    private String smokingCigarette;

    /** 電子交付のみ（全角半角） */
    @NotEmpty(message = "電子交付のみ")
    @Size(max = 10, message = "電子交付のみ")
    private String onlyElectronicDelivery;

    /** 配分上限株数（数値(整数)） */
    @Digits(integer = 10, fraction = 0, message = "配分上限株数")
    @NotEmpty(message = "配分上限株数")
    @Size(max = 13, message = "配分上限株数")
    private String maxAllocation;

    /** 入金予定日 */
    @NotEmpty(message = "入金予定日")
    private String depositScheduleDate;

    /** 募集期間FROM */
    @NotEmpty(message = "募集期間FROM")
    private String bbPeriodFrom;

    /** 募集期間TO */
    @NotEmpty(message = "募集期間TO")
    private String bbPeriodTo;

    /** 発行価格（数値(小数)） */
    @Digits(integer = 12, fraction = 2, message = "発行価格")
    @NotEmpty(message = "発行価格")
    @Size(max = 18, message = "発行価格")
    private String issueBbPrice;

    /** 上場日 */
    @NotEmpty(message = "上場日")
    private String listedDate;

    /** IFAのブックビルディング申込期間（開始） */
    @NotEmpty(message = "IFAのブックビルディング申込期間（開始）")
    private String ifaBookBuildingPresentationFrom;

    /** IFAのブックビルディング申込期間（終了） */
    @NotEmpty(message = "IFAのブックビルディング申込期間（終了）")
    private String ifaBookBuildingPresentationTo;

    /** 手数料 */
    @NotEmpty(message = "手数料")
    private String comm;

    /** 更新日 */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "更新日")
    @Size(min = 10, max = 10, message = "更新日")
    private String updateDate;

    /** 発行価格（hidden項目）（数値(小数)） */
    @Digits(integer = 12, fraction = 2, message = "発行価格")
    @NotEmpty(message = "発行価格")
    @Size(max = 18, message = "発行価格")
    private String issueBbPriceHiddenItem;

    /** 発行価格区分（hidden項目) */
    private String issuePriceType;

    /** 電子交付のみ(hidden項目)（全角半角） */
    private String onlyElectronicDeliveryHiddenItem;

    /** 受渡期日(hidden項目) */
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
