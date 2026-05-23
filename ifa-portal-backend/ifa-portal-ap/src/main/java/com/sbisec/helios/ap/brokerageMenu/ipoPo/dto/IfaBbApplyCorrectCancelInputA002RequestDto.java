package com.sbisec.helios.ap.brokerageMenu.ipoPo.dto;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyCorrectCancelInputA002RequestDto {
    
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
    
    /** 申込日時. */
    private String bbCreateDate;
    
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
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String receiveMethod;
    
    /** 備考（全角半角）. */
    private String bbRemark;
    
    /** 裁量希望数量. */
    private String discretionQuantity;
    
    /** 選定理由. */
    private String selectReason;
    
    /** 訂正前数量. */
    private String quantityBeforeCorrect;
    
    /** 訂正前成行. */
    private String marketOrderBeforeCorrect;
    
    /** 訂正前価格. */
    private String priceBeforeCorrect;
    
    /** 訂正前ディスカウント率. */
    private String discountRateBeforeCorrect;
    
    /** 訂正前投資家属性順序. */
    private String investorAttributeValueBeforeCorrect;
    
    /** 訂正前投資家属性名. */
    private String investorAttributeNameBeforeCorrect;
    
    /** 訂正前裁量希望数量. */
    private String discretionQuantityBeforeCorrect;
    
    /** 訂正前裁量選定理由. */
    private String selectReasonBeforeCorrect;
    
    /** 訂正前備考. */
    private String bbRemarkBeforeCorrect;
}
