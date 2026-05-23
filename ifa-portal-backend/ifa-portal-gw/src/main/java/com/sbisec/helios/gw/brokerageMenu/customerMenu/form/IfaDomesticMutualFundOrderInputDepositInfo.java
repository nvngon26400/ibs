package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 預り情報
 *
 * @author SCSK
 */
@Data
public class IfaDomesticMutualFundOrderInputDepositInfo {
    
    /** 売却可能（口数）（数値(整数)）. */
    private String sellAbleLot;
    
    /** 売却可能（金額）（全角半角）. */
    private String sellAbleAmount;
    
    /** 概算評価金額（数値(整数)）. */
    private String approximateValuation;
    
    /** 概算評価金額日付. */
    private String approximateValuationDate;
    
}
