package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 外国現物取引注文確認注文発注レスポンスアラート内容確認
 *
 * @author 福岡利基
 */
@Data
public class IfaForeignSpotTradeOrderConfirmA010ResponseDtoAlertCheck {
    
    /** 取引注意情報（銘柄）確認. */
    private String tradingCautionInformation;
    
    /** コンプラランクチェック確認. */
    private String invitationCheck;
    
    /** 注意情報アラート確認. */
    private String noteInfoCheckbox;
    
    /** お知らせアラート確認. */
    private String noteLimitCheck;
    
    /** 約定代金の上限超過. */
    private String priceLimitCheck;
    
    /** 逆指値注文即時発火. */
    private String methodCheck;
    
    /** 翌営業日向け注文. */
    private String nextDayCheck;
    
    /** 海外ETFアラート確認. */
    private String overseasEtfAlertConfirm;
    
}
