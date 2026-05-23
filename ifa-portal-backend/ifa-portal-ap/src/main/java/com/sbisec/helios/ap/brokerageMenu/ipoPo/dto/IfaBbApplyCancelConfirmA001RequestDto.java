package com.sbisec.helios.ap.brokerageMenu.ipoPo.dto;

import lombok.Data;

/**
 * 画面ID：SUB0204_02-03_2
 * 画面名：BB申込取消確認
 *
 * @author BASE 李
 2024/05/14 新規作成
 */
@Data
public class IfaBbApplyCancelConfirmA001RequestDto {
    /** 部店コード（半角英数字）. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;

    /** 銘柄コード（半角英数字）. */
    private String brandCode;

    /** ブックビルディング申込期間（開始）（全角半角）. */
    private String bookBuildingPresentationFrom;

    /** 数量（数値(整数)）. */
    private String quantity;

    /** 成行. */
    private String marketOrder;

    /** 価格（数値(小数)）. */
    private String price;

    /** ディスカウント率（数値(小数)）. */
    private String discountRate;

    /** 投資家属性順序（半角英数字）. */
    private String investorAttributeValue;

    /** 投資家属性名. */
    private String investorAttributeName;

    /** 備考（全角半角）. */
    private String bbRemark;

    /** 裁量希望数量. */
    private String discretionQuantity;

    /** 選定理由. */
    private String selectReason;

    /** 顧客名（漢字）（全角半角）. */
    private String customerNameKanji;

    /** 顧客名（カナ）（全角半角）. */
    private String customerNameKana;

    /** 銘柄名（全角半角）. */
    private String brandName;

    /** 売買単位区分（半角英数字）. */
    private String sellBuyUnitType;

    /** 発行価格区分. */
    private String issuePriceType;

    /** 注意情報レベル. */
    private String noticeInfoLevel;
    
    private String customerCode;
}
