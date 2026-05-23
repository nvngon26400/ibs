package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import java.util.Date;

import lombok.Data;

/**
 * 発注後の注文更新リクエストモデル
 *
 * @author SCSK 金志
 * 
 */
@Data
public class IfaForeignMarginTradeNewOrderConfirmSql002RequestModel {
    
    /** 注文番号（数字）. */
    private String orderNumber;
    
    /** 注文Sub番号（数字）. */
    private String orderSubNumber;
    
    /** 注文日時. */
    private Date orderDateTime;
    
    /** 期間. */
    private String period;
    
    /** HTTPのレスポンスコード. */
    private int responseCode;
    
    /** ユーザ共通情報.ユーザーID. */
    private String userId;
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
    
    /** API.エラーコード. */
    private String errorCode;
    
    /** API.エラーメッセージ. */
    private String errMessage;
    
}
