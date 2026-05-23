package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaFxTradeOrderCancelConfirmSql003bResponseModel {
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
    
    /** 為替取引サービス種別. */
    private String fxTradeServiceClass;
}
