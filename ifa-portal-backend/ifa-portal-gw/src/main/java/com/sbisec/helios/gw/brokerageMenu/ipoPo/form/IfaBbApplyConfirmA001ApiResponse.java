package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;


import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyConfirmA001ApiResponse {
    /** 部店コード. */
    private String butenCode;
    /** 口座番号. */
    private String accountNumber;
    /** 銘柄コード. */
    private String brandCode;
    /** ブックビルディング申込期間（開始）. */
    private String bookBuildingPresentationFrom;
    /** 数量. */
    private String quantity;
    /** 成行. */
    private String marketOrder;
    /** 価格. */
    private String price;
    /** ディスカウント率. */
    private String discountRate;
    /** 投資家属性順序. */
    private String investorAttributeValue;
    /** 投資家属性名. */
    private String investorAttributeName;
    /** 備考. */
    private String bbRemark;
    /** 裁量希望数量. */
    private String discretionQuantity;
    /** 選定理由. */
    private String selectReason;
    /** 顧客名（漢字）. */
    private String customerNameKanji;
    /** 顧客名（カナ）. */
    private String customerNameKana;
    /** 銘柄名. */
    private String brandName;
    /** 売買単位区分. */
    private String sellBuyUnitType;
    /** 発行価格区分. */
    private String issuePriceType;
    /** 勧誘区分. */
    private String kanyuKbn;
    /** 法人区分. */
    private String corporationType;
    /** 受注方法. */
    private String receiveMethod;
    /** コンプラランクチェック.チェックボックス文言. */
    private String chkBoxLabel;
    /** 裁量配分割当回数5回以上ワーニングメッセージ. */
    private String discretionAllocateTimesOverFiveWarningMsg;
    /** 金融資産3000万円未満の裁量申込ワーニングメッセージ. */
    private String financialAssetLessThanThirtyMillionYenDiscretionApplyWarningMsg;
    /** 注意情報レベル. */
    private String noticeInfoLevel;
    private String customerCode;
}
