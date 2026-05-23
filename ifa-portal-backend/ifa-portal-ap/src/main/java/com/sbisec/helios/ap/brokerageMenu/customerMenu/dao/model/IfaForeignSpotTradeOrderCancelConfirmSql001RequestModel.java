package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 外国現物取引注文取消確認SQL001リクエストモデル
 *
 * @author 宇田川達弥
 */
@Data
public class IfaForeignSpotTradeOrderCancelConfirmSql001RequestModel {
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
    
    /** 受付注文番号. */
    private String acceptOrderNo;
    
    /** 受付注文サブ番号. */
    private String acceptOrderSubNo;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 銘柄コード（全角半角）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 市場コード（全角半角）. */
    private String marketCode;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 注文数量. */
    private String orderQuantity;
    
    /** 価格条件区分（全角半角）. */
    private String priceConditionsType;
    
    /** 指値（数値(小数)）. */
    private String limitPrice;
    
    /** 発火条件価格. */
    private String stopPrice;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 決済区分（全角半角）. */
    private String settlementType;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 勧誘区分（全角半角）. */
    private String invitationType;
    
    /** 受注方法区分（全角半角）. */
    private String orderMethodType;
    
    /** ワーニング申請取引（全角半角）. */
    private String warningApplyType;
    
    /** 重要事項説明区分（全角半角）. */
    private String explanationInfoType;
    
    /** 乗換え勧誘(ETF)区分（全角半角）. */
    private String transferInvitationType;
    
    /** 英文開示銘柄説明区分（全角半角）. */
    private String engPubBrandExpType;
    
    /** 注文日. */
    private String orderDate;
    
    /** 注文時間. */
    private String orderTime;
    
    /** 国内約定日. */
    private String tradeDate;
    
    /** 注文期限日. */
    private String tradeLimitDate;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者営業員コード（半角英数字）. */
    private String intermediaryEmpCd;
    
    /** 作成者. */
    private String createUser;
    
    /** 更新者. */
    private String updateUser;
    
}
