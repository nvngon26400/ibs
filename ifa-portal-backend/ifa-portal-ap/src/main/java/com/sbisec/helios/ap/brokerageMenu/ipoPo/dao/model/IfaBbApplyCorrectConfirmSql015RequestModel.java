package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;

import java.util.Date;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyCorrectConfirmSql015RequestModel {
    
    /** 部店コード（半角英数字）. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;

    /** 銘柄コード（半角英数字）. */
    private String brandCode;

    /** ブックビルディング申込期間（開始）（全角半角）. */
    private String bookBuildingPresentationFrom;
    
    /** 申込日時. */
    private String bbCreateDate;
    
    /** 裁量希望数量. */
    private String discretionQuantity;
    
    /** 訂正前裁量希望数量. */
    private String discretionQuantityBeforeCorrect;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 選定理由. */
    private String selectReason;
    
    /** 投資家属性順序（全角半角）. */
    private String investorAttributeValue;

    /** 投資家属性名. */
    private String investorAttributeName;
    
    /** システム共通情報.システム日付 */
    private Date sysDate;
    
    /** ユーザ共通情報.ユーザーID */
    private String userId;
    
    /** セクションID. */
    private String sectionId;

    /** 支店名（全角半角）. */
    private String branchName;
    
    /** 成行. */
    private String marketOrder;
    
    /** 備考（全角半角）. */
    private String bbRemark;
    
    /** ディスカウント率（数値(小数)）. */
    private String discountRate;
    
    /** 価格（数値(小数)）. */
    private String price;
}
