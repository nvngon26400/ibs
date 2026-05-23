package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import lombok.Data;

/**
 * 外国株式_店頭注文
 *
 * @author SCSK
 *
 */
@Data
public class IfaOrderListA002ResponseDtoForeignStockCounterOrder {

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
    
    /** 管理番号（半角英数字）. */
    private String manageNumber;
    
    /** 銘柄コード（全角半角）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** ステータス. */
    private String status;
    
    /** 取消理由（全角半角）. */
    private String cancelReason;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 注文日時 */
    private String orderDate;
    
    /** 約定時間. */
    private String tradeTime;
    
    /** 注文数量. */
    private String orderQuantity;
    
    /** 取引価格（数値(小数)）. */
    private String tradePrice;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String orderMethod;
    
    /** 英文開示銘柄. */
    private String engPubBrand;
    
    /** 重要事項の説明. */
    private String importantMatter;
    
    /** ワーニング取引. */
    private String warningTrade;
    
    /** 外国証券情報交付方法. */
    private String foreignSecurityInfoIssueMethod;
    
    /** 乗換え勧誘(ETF). */
    private String switchingSolicitationEtf;
    
    /** ユーザー名. */
    private String userName;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者名（全角半角）. */
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
    
    /** 取引種別（算出）. */
    private String tradeCdCalculation;

}
