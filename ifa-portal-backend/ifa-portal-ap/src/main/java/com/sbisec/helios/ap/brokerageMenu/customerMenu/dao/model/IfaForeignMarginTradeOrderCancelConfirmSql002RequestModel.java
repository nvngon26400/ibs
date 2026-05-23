package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaForeignMarginTradeOrderCancelConfirmSql002RequestModel {
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
    
    /** 受付注文番号. */
    private String acceptOrderNo;
    
    /** 受付注文サブ番号. */
    private String acceptOrderSubNo;
    
    /** 注文日. */
    private String orderDate;
    
    /** 注文時間. */
    private String orderTime;
    
    /** 注文期限日. */
    private String tradeLimitDate;
    
    /** APIエラーコード（全角半角）. */
    private String apiErrorCode;
    
    /** APIステータスコード（数字）. */
    private String apiStatusCode;
    
    /** APIメッセージ（全角半角）. */
    private String apiMsg;
    
    /** 更新日時. */
    private String updateTime;
    
    /** 更新者. */
    private String updateUser;
    
}
