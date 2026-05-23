package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaFxOrderConfirmSql002RequestModel {
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
    
    /** 注文番号（数字）. */
    private String orderNumber;
    
    /** 約定日時. */
    private String tradeDateTime;
    
    /** 受渡日 . */
    private String settlementDate;
    
    /** 為替レート（数値(小数)）. */
    private String fxRate;
    
    /** 為替レート日時. */
    private String rateDatetime;
    
    /** 受渡金額（円貨）（数値(小数)）. */
    private String yenDeliveryAmount;
    
    /** 注文日時. */
    private String orderDate;
    
    /** 注文種別表示. */
    private String orderClass;
    
    /** APIエラーコード（全角半角）. */
    private String apiErrorCode;
    
    /** APIステータスコード（数字）. */
    private String aPIStatusCode;
    
    /** APIメッセージ（全角半角）. */
    private String apiMsg;
    
    /** 更新日時. */
    private String updateTime;
    
    /** 更新者. */
    private String updateUser;
    
}
