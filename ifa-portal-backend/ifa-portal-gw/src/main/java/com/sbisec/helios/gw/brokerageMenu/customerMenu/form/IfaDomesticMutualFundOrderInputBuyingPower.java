package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

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
