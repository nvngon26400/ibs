package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaForeignMarginAutoTransferSettingConfirmA001bRequestDto {
    
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
    
    /** IFA自動振替設定指示番号 */
    private String autoTransferSettingNo;
    
}
