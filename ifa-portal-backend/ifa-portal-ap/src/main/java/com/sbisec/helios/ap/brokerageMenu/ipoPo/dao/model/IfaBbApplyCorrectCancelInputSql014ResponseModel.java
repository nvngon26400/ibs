package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;


import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyCorrectCancelInputSql014ResponseModel {

    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 申込日時. */
    private String bbCreateDate;
    
    /** 成行（ストライクプライス）. */
    private String marketOrder;
    
    /** 価格/ディスカウント率（数値(小数)）. */
    private String priceDiscountRate;
    
    /** 投資家属性順序（半角英数字）. */
    private String investorAttributeValue;
    
    /** 投資家属性名. */
    private String investorAttributeName;
    
    /** 備考（全角半角）. */
    private String bbRemark;
    
    /** 裁量配分希望数量. */
    private String discretionQuantity;
    
    /** 選定理由. */
    private String selectReason;

}
