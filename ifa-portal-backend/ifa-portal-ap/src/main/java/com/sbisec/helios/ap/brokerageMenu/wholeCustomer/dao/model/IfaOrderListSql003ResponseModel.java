package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;


import lombok.Data;

/**
 * 募集注文
 *
 * @author SCSK
 *
 */
@Data
public class IfaOrderListSql003ResponseModel {
    
    /** 総件数 */
    private int totalCount;
    
    /** 部店. */
    private String buten;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 契約締結前交付書面コード（半角英数字）. */
    private String customerAttribute;
    
    /** 顧客名_姓名(漢字). */
    private String customerNameKanji;
    
    /** 顧客名_姓名(カナ). */
    private String customerNameKana;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 注文状況（全角半角）. */
    private String orderStatus;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 募集受注日時. */
    private String recruitmentOrderDate;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 発行価格（数値(小数)）. */
    private String issueBbPrice;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String orderMethod;
    
    /** 最重要事項の説明. */
    private String mostImportantMatter;
    
    /** ワーニング申請済. */
    private String warningApplyArranged;
    
    /** 目論見書. */
    private String prospectus;
    
    /** ユーザー名. */
    private String userName;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者名. */
    private String brokerName;
    
    /** 仲介業支店コード. */
    private String brokerageBranchCode;
    
    /** 仲介業者支店名. */
    private String brokerBranchName;
    
    /** 仲介業者営業員コード（半角英数字）. */
    private String brokerChargeCode;
    
    /** 仲介業者担当者名（全角半角）. */
    private String employeeName;
    
    /** 閲覧可能部店コード. */
    private String viewAblrButenCode;

}
