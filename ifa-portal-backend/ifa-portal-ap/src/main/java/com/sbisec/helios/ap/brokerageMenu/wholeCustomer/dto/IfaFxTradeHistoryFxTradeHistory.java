package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import lombok.Data;

/**
 * 為替取引履歴　為替取引履歴

 * @author SCSK川崎
 */
@Data
public class IfaFxTradeHistoryFxTradeHistory {
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者名（全角半角）. */
    private String brokerName;
    
    /** 営業員コード（半角英数字）. */
    private String empCode;
    
    /** 営業員名（全角半角）. */
    private String brokerChargeName;
    
    /** 部店. */
    private String buten;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 扱者コード（半角英数字）. */
    private String dealerNumber;
    
    /** コース名. */
    private String courseName;
    
    /** 顧客名(漢字). */
    private String customerNameKanji;
    
    /** 顧客名(カナ). */
    private String customerNameKana;
    
    /** 約定日. */
    private String tradeDate;
    
    /** 受渡日. */
    private String settlementDate;
    
    /** 取引種別名（全角半角）. */
    private String tradeTypeName;
    
    /** 通貨. */
    private String currency;
    
    /** 為替レート（数値(小数)）. */
    private String fxRate;
    
    /** 為替スプレッド（数値(小数)）. */
    private String exchangeSpread;
    
    /** 円額（数値(小数)）. */
    private String yenAmount;
    
    /** 外貨額（数値(小数)）. */
    private String foreignAmount;
    
    /** 為替手数料（数値(小数)）. */
    private String exchangeFee;
    
    /** 仲介業支店コード. */
    private String brokerageBranchCode;
    
    /** 仲介業者支店名. */
    private String brokerBranchName;
    
}
