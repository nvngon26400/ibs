package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyCorrectConfirmA001ApiRequest {

    /** 部店コード（半角英数字）. */
    @NotEmpty(message = "部店コード")
    @Size(min = 3, max = 3, message = "部店コード")
    private String butenCode;
    
    /** 口座番号（数字）. */
    @NotEmpty(message = "口座番号")
    @Pattern(regexp = "0-9", message = "口座番号")
    @Size(max = 7, message = "口座番号")
    private String accountNumber;
    
    /** 銘柄コード（半角英数字）. */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;
    
    /** ブックビルディング申込期間（開始）（全角半角）. */
    @NotEmpty(message = "ブックビルディング申込期間（開始）")
    @Size(max = 8, message = "ブックビルディング申込期間（開始）")
    private String bookBuildingPresentationFrom;
    
    /** 数量（数値(整数)）. */
    @Digits(integer = 15, fraction = 0, message = "数量")
    @NotEmpty(message = "数量")
    @Size(max = 19, message = "数量")
    private String quantity;
    
    /** 申込日時. */
    @DateTimeFormat(pattern = "yy/MM/dd HH:mm:ss")
    @JsonFormat(pattern = "yy/MM/dd HH:mm:ss")
    @NotEmpty(message = "申込日時")
    @Size(min = 20, max = 20, message = "申込日時")
    private String bbCreateDate;
    
    /** 成行. */
    @NotEmpty(message = "成行")
    private String marketOrder;
    
    /** 価格（数値(小数)）. */
    @Digits(integer = 15, fraction = 2, message = "価格")
    @NotEmpty(message = "価格")
    @Size(max = 22, message = "価格")
    private String price;
    
    /** ディスカウント率（数値(小数)）. */
    @Digits(integer = 3, fraction = 4, message = "ディスカウント率")
    @NotEmpty(message = "ディスカウント率")
    @Size(max = 8, message = "ディスカウント率")
    private String discountRate;
    
    /** 投資家属性順序（全角半角）. */
    @NotEmpty(message = "投資家属性順序")
    private String investorAttributeValue;
    
    /** 投資家属性名. */
    @NotEmpty(message = "投資家属性名")
    private String investorAttributeName;
    
    /** 備考（全角半角）. */
    @NotEmpty(message = "備考")
    @Size(max = 200, message = "備考")
    private String bbRemark;
    
    /** 裁量希望数量. */
    @NotEmpty(message = "裁量希望数量")
    private String discretionQuantity;
    
    /** 選定理由. */
    @NotEmpty(message = "選定理由")
    private String selectReason;
    
    /** 顧客名（漢字）（全角半角）. */
    @NotEmpty(message = "顧客名（漢字）")
    @Size(max = 72, message = "顧客名（漢字）")
    private String customerNameKanji;
    
    /** 顧客名（カナ）（全角半角）. */
    @NotEmpty(message = "顧客名（カナ）")
    @Size(max = 72, message = "顧客名（カナ）")
    private String customerNameKana;
    
    /** 銘柄名（全角半角）. */
    @NotEmpty(message = "銘柄名")
    @Size(max = 40, message = "銘柄名")
    private String brandName;
    
    /** 売買単位区分（全角半角）. */
    @NotEmpty(message = "売買単位区分")
    private String sellBuyUnitType;
    
    /** 発行価格区分. */
    @NotEmpty(message = "発行価格区分")
    private String issuePriceType;
    
    /** 勧誘区分（全角半角）. */
    @NotEmpty(message = "勧誘区分")
    @Size(max = 2, message = "勧誘区分")
    private String kanyuKbn;
    
    /** 受注方法. */
    @NotEmpty(message = "受注方法")
    private String receiveMethod;
    
    /** コンプラランクチェック.メッセージ. */
    @NotEmpty(message = "コンプラランクチェック.メッセージ")
    private String complianceRankCheckMsg;
    
    /** コンプラランクチェック.チェックボックス文言. */
    @NotEmpty(message = "コンプラランクチェック.チェックボックス文言")
    private String complianceRankCheckChkBoxLabel;
    
    /** アラート内容確認.コンプラランクチェック確認. */
    @NotEmpty(message = "アラート内容確認.コンプラランクチェック確認")
    private String invitationCheck;
    
    /** 裁量配分割当回数5回以上ワーニングメッセージ. */
    @NotEmpty(message = "裁量配分割当回数5回以上ワーニングメッセージ")
    private String discretionAllocateTimesOverFiveWarningMsg;
    
    /** 金融資産3000万円未満の裁量申込ワーニングメッセージ. */
    @NotEmpty(message = "金融資産3000万円未満の裁量申込ワーニングメッセージ")
    private String financialAssetLessThanThirtyMillionYenDiscretionApplyWarningMsg;
    
    /** 裁量配分割当回数5回以上ワーニング確認. */
    @NotEmpty(message = "裁量配分割当回数5回以上ワーニング確認")
    private String discretionAllocateTimesOverFive;
    
    /** 金融資産3000万円未満の裁量申込ワーニング確認. */
    @NotEmpty(message = "金融資産3000万円未満の裁量申込ワーニング確認")
    private String financialAssetLessThanThirtyMillionYenDiscretionApply;
    
    /** 注意情報レベル. */
    @NotEmpty(message = "注意情報レベル")
    private String noticeInfoLevel;
    
    /** 訂正前数量. */
    @NotEmpty(message = "訂正前数量")
    private String quantityBeforeCorrect;
    
    /** 訂正前成行. */
    @NotEmpty(message = "訂正前成行")
    private String marketOrderBeforeCorrect;
    
    /** 訂正前価格. */
    @NotEmpty(message = "訂正前価格")
    private String priceBeforeCorrect;
    
    /** 訂正前ディスカント率. */
    @NotEmpty(message = "訂正前ディスカント率")
    private String discountRateBeforeCorrect;
    
    /** 訂正前投資家属性順序. */
    @NotEmpty(message = "訂正前投資家属性順序")
    private String investorAttributeValueBeforeCorrect;
    
    /** 訂正前投資家属性名. */
    @NotEmpty(message = "訂正前投資家属性名")
    private String investorAttributeNameBeforeCorrect;
    
    /** 訂正前裁量希望数量. */
    @NotEmpty(message = "訂正前裁量希望数量")
    private String discretionQuantityBeforeCorrect;
    
    /** 訂正前裁量選定理由. */
    @NotEmpty(message = "訂正前裁量選定理由")
    private String selectReasonBeforeCorrect;
    
    /** 訂正前備考. */
    @NotEmpty(message = "訂正前備考")
    private String bbRemarkBeforeCorrect;

    private String customerCode;
}
