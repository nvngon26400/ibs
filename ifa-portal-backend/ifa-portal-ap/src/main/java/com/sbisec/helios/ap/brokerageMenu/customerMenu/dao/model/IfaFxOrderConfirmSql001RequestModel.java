package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaFxOrderConfirmSql001RequestModel {
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 数量の指定方法. */
    private String quantityDesignationMethod;
    
    /** 売却方法. */
    private String saleMethod;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 通貨名（全角）. */
    private String currency;
    
    /** 為替注文金額. */
    private String quantity;
    
    /** 為替取引（数値(整数)）. */
    private String fxTrade;
    
    /** 口座分類（為替取引）. */
    private String accountType;
    
    /** 預り区分（為替取引）. */
    private String depositType;
    
    /** 為替取引種別区分. */
    private String fxTradeType;
    
    /** 為替取引注文状況. */
    private String fxTradeOrderStatus;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者営業員コード（半角英数字）. */
    private String brokerChargeCode;
    
    /** 作成者. */
    private String createUser;
    
    /** 更新者. */
    private String updateUser;
    
}
