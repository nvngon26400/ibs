package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaFxTradeOrderCancelConfirmSql002RequestModel {
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
    
    /** 預り区分（為替取引）. */
    private String depositTypeFxTrade;
    
    /** 注文種別表示. */
    private String orderTypeDisplay;
    
    /** APIエラーコード（全角半角）. */
    private String apiErrorCode;
    
    /** APIステータスコード（数字）. */
    private Integer apiStatusCode;
    
    /** APIメッセージ（全角半角）. */
    private String apiMsg;
    
    /** 更新日時. */
    private String updateTime;
    
    /** 更新者. */
    private String updateUser;
    
    /** APIエラーFLAG. */
    private Boolean orderCancelErrorFlag;
    
}
