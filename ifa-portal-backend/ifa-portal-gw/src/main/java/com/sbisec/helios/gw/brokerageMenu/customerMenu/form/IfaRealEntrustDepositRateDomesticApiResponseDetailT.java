package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 画面ID：SUB0202_010302-03
 * 画面名：リアル委託保証金率（国内）
 
 * SCSK
 * 2024/07/29 新規作成
 */

@Data
public class IfaRealEntrustDepositRateDomesticApiResponseDetailT {
        
    /** 委託保証金率. */
    private String entrustDepositRate;
    
    /** 委託保証金率区分. */
    private String entrustDepositRateType;
    
    /** 委託保証金現金. */
    private String marginDepositCache;
    
    /** 代用有価証券評価額合計. */
    private String alternativeSecuritiesTotal;
    
    /** 建玉評価損合計. */
    private String totalOpenInterestValuationLoss;
    
    /** 決済損益合計. */
    private String totalSettlementProfitLoss;
    
    /** 支払諸経費等合計. */
    private String totalExpensesPaid;
    
    /** 実質保証金. */
    private String currencyActualDeposit;
    
    /** 建代金合計. */
    private String constructionPriceTotal;
    
    /** 参考委託保証金率. */
    private String referenceMarginDeposit;

    /** 参考委託保証金率区分. */
    private String referenceMarginDepositType;

    /** SBIハイブリッド預金残高. */
    private String hybridDepositBalance;

}
