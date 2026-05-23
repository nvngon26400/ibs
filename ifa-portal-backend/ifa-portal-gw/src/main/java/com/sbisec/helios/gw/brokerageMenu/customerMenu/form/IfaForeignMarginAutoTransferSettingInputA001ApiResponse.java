package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaForeignMarginAutoTransferSettingInputA001ApiResponse {
    
    /** 建余力不足.米国株式. */
    private String marginBuyingPowerShortfallSecurities;
    
    /** 建余力不足.米ドル. */
    private String marginBuyingPowerShortfallCash;
    
    /** 保証金不足.米国株式. */
    private String marginShortfallSecurities;
    
    /** 保証金不足.米ドル. */
    private String marginShortfallCash;
    
    /** 株式自動振替先設定.振替先. */
    private String depositType;
    
}
