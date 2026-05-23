package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyConfirmSql015RequestModel {
    /** 銘柄コード（半角英数字）. */
    private String brandCode;

    /** ブックビルディング申込期間（開始）（全角半角）. */
    private String bookBuildingPresentationFrom;

    /** 部店コード（半角英数字）. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;

    /** 扱者番号. */
    private String dealerNumber;

    /** 顧客名_姓(漢字). */
    private String customerLastNameKanji;

    /** 顧客名_名(漢字). */
    private String customerFirstNameKanji;

    /** 顧客名_姓(カナ). */
    private String customerLastNameKana;

    /** 顧客名_名(カナ). */
    private String customerFirstNameKana;

    /** 数量（数値(整数)）.  */
    private String quantity;

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

    /** 作成日. */
    private String createDate;

    /** ユーザ共通情報.ユーザーID. */
    private String userId;

    /** セクションID. */
    private String sectionId;

    /** 支店名. */
    private String branchName;

    /** ユーザ共通情報.ユーザー名. */
    private String userName;

    /** 成行（ストライクプライス）. */
    private String marketOrder;

    /** 裁量配分希望数量. */
    private String discretionQuantity;

    /** 選定理由. */
    private String selectReason;

}
