package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaForeignMarginAutoTransferSettingInputActionResponseDto {
    
    /** 建余力不足 自動振替設定(米ドル). */
    private String marginBuyingPowerShortfallCash;
    
    /** 建余力不足 自動振替設定(米国株式). */
    private String marginBuyingPowerShortfallSecurities;
    
    /** 保証金不足 自動振替設定(米ドル). */
    private String marginShortfallCash;
    
    /** 保証金不足 自動振替設定(米国株式). */
    private String marginShortfallSecurities;
    
    /** 現物買付時 株式自動振替先設定. */
    private String depositType;
}
