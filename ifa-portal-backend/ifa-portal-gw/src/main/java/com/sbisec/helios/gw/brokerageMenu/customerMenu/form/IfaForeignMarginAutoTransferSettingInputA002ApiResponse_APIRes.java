package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaForeignMarginAutoTransferSettingInputA002ApiResponse_APIRes {
    
    /** 建余力不足 自動振替設定(米ドル). */
    private String marginBuyingPowerShortfallCASH;
    
    /** 建余力不足 自動振替設定(米国株式). */
    private String marginBuyingPowerShortfallSecurities;
    
    /** 保証金不足 自動振替設定(米ドル). */
    private String marginShortfallCASH;
    
    /** 保証金不足 自動振替設定(米国株式). */
    private String marginShortfallSecurities;
    
    /** 現物買付時 株式自動振替先設定. */
    private String DepositType;
    
}
