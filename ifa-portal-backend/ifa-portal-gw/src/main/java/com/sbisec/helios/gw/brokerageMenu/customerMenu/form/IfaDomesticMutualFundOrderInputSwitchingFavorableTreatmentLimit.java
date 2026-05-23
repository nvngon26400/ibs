package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 乗換優遇限度額
 *
 * @author SCSK
 */
@Data
public class IfaDomesticMutualFundOrderInputSwitchingFavorableTreatmentLimit {
    
    /** 総合口座（今月残）. */
    private String wholeAccountThisMonth;
    
    /** 総合口座（来月残）. */
    private String wholeAccountNextMonth;
    
    /** ジュニアNISA口座（今月残）. */
    private String jrNisaAccountThisMonth;
    
    /** ジュニアNISA口座（来月残）. */
    private String jrNisaAccountNextMonth;
    
}
