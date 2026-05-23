package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class IfaForeignMarginAutoTransferSettingConfirmA001ApiRequest {
    
    /** 建余力不足.米国株式. */
    @NotEmpty(message = "建余力不足.米国株式")
    private String marginBuyingPowerShortfallSecurities;
    
    /** 建余力不足.米ドル. */
    @NotEmpty(message = "建余力不足.米ドル")
    private String marginBuyingPowerShortfallCash;
    
    /** 保証金不足.米国株式. */
    @NotEmpty(message = "保証金不足.米国株式")
    private String marginShortfallSecurities;
    
    /** 保証金不足.米ドル. */
    @NotEmpty(message = "保証金不足.米ドル")
    private String marginShortfallCash;
    
    /** 株式自動振替先設定.振替先. */
    @NotEmpty(message = "株式自動振替先設定.振替先")
    private String depositType;
    
}
