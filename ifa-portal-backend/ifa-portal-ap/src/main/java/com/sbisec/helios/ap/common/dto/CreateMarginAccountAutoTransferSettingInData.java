package com.sbisec.helios.ap.common.dto;

import lombok.Data;

@Data
public class CreateMarginAccountAutoTransferSettingInData {
    
    //Header.token
    private String headerToken;
    
    //Header.request_id
    private String headerRequestId;
    
    //信用口座自動振替設定情報.建余力不足 自動振替設定(米ドル)
    private String settingMarginBuyingPowerShortfallCash;
    
    //信用口座自動振替設定情報.建余力不足 自動振替設定(米国株式)
    private String settingMarginBuyingPowerShortfallSecurities;
    
    //信用口座自動振替設定情報.保証金不足 自動振替設定(米ドル)
    private String settingMarginShortfallCash;
    
    //信用口座自動振替設定情報.保証金不足 自動振替設定(米国株式)
    private String settingMarginShortfallSecurities;
    
    //信用口座自動振替設定情報.現物買付時 株式自動振替先設定
    private String settingDepositType;
    
}
