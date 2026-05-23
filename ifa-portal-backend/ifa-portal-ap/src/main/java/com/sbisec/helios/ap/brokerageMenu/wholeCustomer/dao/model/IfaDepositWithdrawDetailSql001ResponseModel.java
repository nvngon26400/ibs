package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 入出金明細SQL001レスポンスモデル
 *
 * @author SCSK
 */
@Data
public class IfaDepositWithdrawDetailSql001ResponseModel {
    
    /** 総件数. */
    private String total;
    
    /** 部店. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 取引コース（全角半角）. */
    private String course;
    
    /** 顧客名(漢字). */
    private String customerNameKanji;
    
    /** 顧客名(カナ). */
    private String customerNameKana;
    
    /** 入出金日. */
    private String settleDate;
    
    /** 摘要（全角半角）. */
    private String dispAbstract;
    
    /** 受渡金額（数値(整数)）. */
    private BigDecimal depositAmount;
    
    /** 対象テーブル. */
    private String targetTbl;
    
    /** 計上種別. */
    private String tradeType;
    
    /** 取引コード3. */
    private String tradeCode3;
    
    /** 取引補助コード. */
    private String tradeCodePare;
    
    /** 勘定コード. */
    private String accountingCode;
    
    /** 取引コード. */
    private String tradeCode;
    
    /** 商品コード（全角半角）. */
    private String productCode;
    
    /** 対客報告年月日. */
    private String reportDate;
    
    /** 仕訳番号. */
    private String postingNo;
    
    /** 正式漢字銘柄名. */
    private String formalKanjiSecName;
    
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
    
    /** 取引 */
    private String code;
}
