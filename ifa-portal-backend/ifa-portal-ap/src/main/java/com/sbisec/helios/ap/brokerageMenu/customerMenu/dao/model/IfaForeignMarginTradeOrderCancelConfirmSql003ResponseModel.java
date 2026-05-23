package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaForeignMarginTradeOrderCancelConfirmSql003ResponseModel {
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
    
    /** 勧誘区分（全角半角）. */
    private String invitationType;
    
    /** 受注方法区分（全角半角）. */
    private String orderMethodType;
    
    /** 重要事項説明区分（全角半角）. */
    private String explanationInfoType;
    
    /** 英文開示銘柄説明区分（全角半角）. */
    private String engPubBrandExpType;
    
}
