package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaRealEntrustDepositRateA002ApiResponse {

    /** 当日基準_委託保証金率 . */
    private String todayBaseMarginDepositRate8;
    
    /** 当日基準_(A)実質保証金. */
    private String todayBaseForeignCurrencyactualDeposit;
    
    /** 当日基準_委託保証金現金. */
    private String todayBaseMarginDepositCache;
    
    /** 当日基準_代用有価証券評価額合計. */
    private String todayBaseAmericaAlternativeSecuritiesTotal;
    
    /** 当日基準_建玉評価損合計. */
    private String todayBaseTotalOpenInterestValuationLoss;
    
    /** 当日基準_決済損益合計. */
    private String todayBaseTotalSettlementProfitLoss;
    
    /** 当日基準_支払諸経費等合計. */
    private String todayBaseTotalExpensesPaid;
    
    /** 当日基準_参考委託保証金率. */
    private String todayBaseForeignReferenceMarginDeposit;
    
    /** 当日基準_参考保証金. */
    private String todayBaseReferenceSecurityDeposit;
    
    /** 当日基準_(C)預り金. */
    private String todayBaseForeignCurrencydeposit;
    
    /** 当日基準_(D)保護預り有価証券評価額合計. */
    private String todayBaseTotalAppraisalValueOfSecuritiesInCustody;
    
    /** 当日基準_(B)建代金合計. */
    private String todayBaseConstructionPriceTotal;
    
    /** 値洗い基準_委託保証金率 . */
    private String markToBaseMarginDepositRate8;
    
    /** 値洗い基準_(A)実質保証金. */
    private String markToBaseForeignCurrencyactualDeposit;
    
    /** 値洗い基準_委託保証金現金. */
    private String markToBaseMarginDepositCache;
    
    /** 値洗い基準_代用有価証券評価額合計. */
    private String markToBaseAmericaAlternativeSecuritiesTotal;
    
    /** 値洗い基準_建玉評価損合計. */
    private String markToBaseTotalOpenInterestValuationLoss;
    
    /** 値洗い基準_決済損益合計. */
    private String markToBaseTotalSettlementProfitLoss;
    
    /** 値洗い基準_支払諸経費等合計. */
    private String markToBaseTotalExpensesPaid;
    
    /** 値洗い基準_参考委託保証金率. */
    private String markToBaseForeignReferenceMarginDeposit;
    
    /** 値洗い基準_参考保証金. */
    private String markToBaseReferenceSecurityDeposit;
    
    /** 値洗い基準_(C)預り金. */
    private String markToBaseForeignCurrencydeposit;
    
    /** 値洗い基準_(D)保護預り有価証券評価額合計. */
    private String markToBaseTotalAppraisalValueOfSecuritiesInCustody;
    
    /** 値洗い基準_(B)建代金合計. */
    private String markToBaseConstructionPriceTotal;

}
