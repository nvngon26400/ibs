package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * アラート内容確認
 *
 * @author SCSK 金志
 */
@Data
public class AlertContentsConfirm {
    
    /** 取引注意情報（銘柄）確認. */
    private String tradingCautionInformation;
    
    /** 注意情報アラート確認. */
    private String noteInfoCheckbox;
    
    /** お知らせアラート確認. */
    private String noteLimitCheck;
    
    /** 増し担保規制確認. */
    private String additionalCollateralRegulationsConfirm;
    
    /** 逆指値注文即時発火. */
    private String methodCheck;
    
    /** 翌営業日向け注文. */
    private String nextDayCheck;
    
}
