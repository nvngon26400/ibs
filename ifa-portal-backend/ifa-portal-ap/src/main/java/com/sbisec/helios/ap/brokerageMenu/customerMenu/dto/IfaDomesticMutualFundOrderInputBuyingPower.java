package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 買付余力
 *
 * @author SCSK
 */
@Data
public class IfaDomesticMutualFundOrderInputBuyingPower {
    
    /** 総合口座. */
    private String wholeAccount;
    
    /** ジュニアNISA口座. */
    private String jrNisaAccount;
    
}
