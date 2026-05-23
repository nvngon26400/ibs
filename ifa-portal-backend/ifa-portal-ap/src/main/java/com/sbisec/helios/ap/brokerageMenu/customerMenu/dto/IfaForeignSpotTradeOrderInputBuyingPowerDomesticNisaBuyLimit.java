package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 外国現物取引注文入力円貨余力情報NISA投資可能枠
 *
 * @author SCSK 宇田川達弥
 */
@Data
public class IfaForeignSpotTradeOrderInputBuyingPowerDomesticNisaBuyLimit {
    
    /** 買付可能枠（総合）. */
    private String availableBuyingLimit;
    
    /** 買付可能枠年（総合）. */
    private String annualAvailableBuyingLimit;
    
    /** 口座分類（総合）. */
    private String accountClassification;

}
