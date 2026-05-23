package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 信用余力(米株) A001 レスポンスパラメータ
 * 委託保証金率リスト

 * @author SCSK 矢口
 *
 */
@Data
public class IfaMarginPowerForeignA001DtoResponseEntrustDepositTransition {
    
    /** 営業日(yyyy-MM-dd). */
    private String domesticSettlementDate;
    
    /** 営業日後. */
    private String settlementDateAfterBusinessDay;
    
    /** 委託保証金現金合計（数値(小数)）. */
    private String marginDepositTotalCache;
    
    /** 代用有価証券評価額合計（数値(整数)）. */
    private String alternativeSecuritiesTotal;
    
    /** 評価損・決済損益・諸経費合計（数値(小数)）. */
    private String marginDepositRateSettlementProfitLossCost;
    
    /** (A)実質保証金（数値(小数)）. */
    private String foreignCurrencyactualDeposit;
    
    /** (B)建玉金合計. */
    private String constructionPriceTotal;
    
    /** 委託保証金率 A/B×100. */
    private String marginDepositRate8;
    
    /** (C)預り金（数値(小数)）. */
    private String foreignCurrencydeposit;
    
    /** (D)保護預り 有価証券評価額合計. */
    private String totalAppraisalValueOfSecuritiesInCustody;
    
    /** 参考委託保証金率 (A+C+D/B×100). */
    private String foreignReferenceMarginDeposit;
    
}
