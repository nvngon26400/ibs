package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 買付余力
 *
 * @author SCSK
 * 
 */
@Data
public class IfaDomesticStockOrderInputApiResponseBuyingPower {
    
    /** 受渡日(T+2). */
    private String deliveryDateT2;
    
    /** 受渡日(T+3). */
    private String deliveryDateT3;
    
    /** 総合口座（T+2）. */
    private String wholeAccountT2;
    
    /** 総合口座（T+3）. */
    private String wholeAccountT3;
    
    /** JrNISA口座（T+2）. */
    private String jrNisaAccountStatusT2;
    
    /** JrNISA口座（T+3）. */
    private String jrNisaAccountStatusT3;
    
    /** NISA買付可能枠（数値(整数)）. */
    private String nisaBuy;
    
}
