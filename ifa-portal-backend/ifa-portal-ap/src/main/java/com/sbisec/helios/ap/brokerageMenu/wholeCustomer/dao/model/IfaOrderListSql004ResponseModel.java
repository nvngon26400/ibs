package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;


import lombok.Data;

/**
 * 外国株式（委託注文）
 *
 * @author SCSK
 *
 */
@Data
public class IfaOrderListSql004ResponseModel {
    
    /** 総件数 */
    private int totalCount;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 契約締結前交付書面コード（半角英数字）. */
    private String customerAttribute;
    
    /** 顧客名_姓名(漢字). */
    private String customerNameKanji;
    
    /** 顧客名_姓名(カナ). */
    private String customerNameKana;
    
    /** 受付注文番号. */
    private String acceptOrderNumber;
    
    /** 受付注文サブ番号. */
    private String acceptOrderSubNumber;
    
    /** ティッカーコード（全角半角）. */
    private String tickerCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 市場コード（全角半角）. */
    private String marketCode;
    
    /** 注文種別区分（全角半角）. */
    private String securiytClassType;
    
    /** 仕法区分（全角半角）. */
    private String methodType;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 価格条件区分（全角半角）. */
    private String priceConditionsType;
    
    /** 発火条件価格. */
    private String stopOrderPrice;
    
    /** 決済区分（全角半角）. */
    private String settlementType;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 注文時間. */
    private String orderTime;
    
    /** 注文期限日. */
    private String orderDeadlineDate;
    
    /** 注文数量. */
    private String orderQuantity;
    
    /** 指値（数値(小数)）. */
    private String sashine;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法区分（全角半角）. */
    private String jutyuKbn;
    
    /** 英文開示銘柄説明区分（全角半角）. */
    private String engPubType;
    
    /** 重要事項説明区分（全角半角）. */
    private String importantMatterTypeClass;
    
    /** ワーニング申請取引（全角半角）. */
    private String warningApplyTrade;
    
    /** 乗換え勧誘(ETF)区分（全角半角）. */
    private String solicitationEtfType;
    
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
