package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 信用余力(米株) A001 レスポンスパラメータ
 * 信用取引リスト
 * 
 * @author SCSK 矢口
 *
 */
@Data
public class IfaMarginPowerForeignA001ApiResponseMarginTrade {
    
    /** 営業日(yyyy-MM-dd). */
    private String domesticSettlementDate;
    
    /** 営業日後. */
    private String settlementDateAfterBusinessDay;
    
    /** 保証金現金（数値(整数)）. */
    private String margin;
    
    /** 支払額（数値(整数)）. */
    private String payment;
    
    /** 未約定信用決済損（数値(整数)）. */
    private String uncontractedCreditSettlementLoss;
    
    /** 受取額（数値(整数)）. */
    private String amountReceived;
    
    /** 振替予定額 (現物口座→信用口座). */
    private String plannedTransferAmount;
    
    /** 残高(保証金). */
    private String balanceDeposit;
    
    /** 必要保証金額（数値(小数)）. */
    private String requiredSettlementAmountForeignCurrency;
    
}
