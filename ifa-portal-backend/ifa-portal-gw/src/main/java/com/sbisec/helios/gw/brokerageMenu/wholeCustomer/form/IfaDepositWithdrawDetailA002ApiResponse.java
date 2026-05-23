package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import lombok.Data;

/**
 * 入出金明細A002レスポンスDTO
 *
 * @author SCSK
 */
@Data
public class IfaDepositWithdrawDetailA002ApiResponse {
    
    /** 取得件数. */
    private String acquireCount;
    
    /** データ件数. */
    private String dataCount;
    
    /** 入出金明細. */
    private String depositWithdrawDetail;
    
    /** 部店. */
    private String buten;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 取引コース（全角半角）. */
    private String course;
    
    /** 顧客名(漢字). */
    private String customerNameKanji;
    
    /** 顧客名(カナ). */
    private String customerNameKana;
    
    /** 入出金日. */
    private String depositsAndWithdrawalsDate;
    
    /** 取引（全角半角）. */
    private String openTradeKbn;
    
    /** 摘要（全角半角）. */
    private String dispAbstract;
    
    /** 受渡金額（数値(整数)）. */
    private String deliveryAmount;
    
    /** 営業員コード（半角英数字）. */
    private String empCode;
    
    /** 営業員名（全角半角）. */
    private String brokerChargeName;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者名（全角半角）. */
    private String brokerName;
    
    /** 支店コード（数字）. */
    private String branchCode;
    
    /** 支店名（全角半角）. */
    private String branchName;
    
    /** 出金額合計 合計額. */
    private String withdrawTotalAmount;
    
    /** 出金額合計 件数. */
    private String withdrawTotalCount;
    
    /** 入金額合計 合計額. */
    private String depositTotalAmount;
    
    /** 入金額合計 件数. */
    private String depositTotalCount;
    
    /** 振替出金額合計 合計額. */
    private String transferWithdrawTotalAmount;
    
    /** 振替出金額合計 件数. */
    private String transferWithdrawTotalCount;
    
    /** 振替入金額合計 合計額. */
    private String transferDepositTotalAmount;
    
    /** 振替入金額合計 件数. */
    private String transferDepositTotalCount;
    
}
