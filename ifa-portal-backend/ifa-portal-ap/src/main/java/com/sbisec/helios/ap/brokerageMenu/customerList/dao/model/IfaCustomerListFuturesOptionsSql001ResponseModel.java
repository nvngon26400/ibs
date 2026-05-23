package com.sbisec.helios.ap.brokerageMenu.customerList.dao.model;

import lombok.Data;

/**
 * 顧客一覧_先OP
 *
 * @author SCSK
 *
 */
@Data
public class IfaCustomerListFuturesOptionsSql001ResponseModel {
    
    /** 総件数. */
    private int totalRow;
    
    /** Cランク. */
    private String tcCompRank;
    
    /** 顧客名（カナ）（全角半角）. */
    private String nameKana;
    
    /** 顧客名（漢字）（全角半角）. */
    private String nameKanji;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 支店コード（数字）. */
    private String brokerBranchCode;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者営業員コード（半角英数字）. */
    private String intermediaryEmpCd;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 担当者名（全角半角）. */
    private String brokerChargeName;
    
    /** 仲介業者支店名（支店名用）. */
    private String branchName;
    
    /** 仲介業者支店名（仲介業者名用）. */
    private String branchNameHead;
    
    /** 必要委託証拠金（先OP）. */
    private String fuOpRequireMargin;
    
    /** 受入証拠金（先OP）. */
    private String fuOpAcceptMargin;
    
    /** 前日評価損益（先OP）. */
    private String profLossFuOp;
    
    /** コース名. */
    private String customerAttributeName;
    
}
