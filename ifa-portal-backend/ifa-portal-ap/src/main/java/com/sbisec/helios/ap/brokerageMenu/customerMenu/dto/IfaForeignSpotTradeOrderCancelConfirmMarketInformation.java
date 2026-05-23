package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 外国現物取引注文取消確認 市場情報
 *
 * @author 宇田川達弥
 */
@Data
public class IfaForeignSpotTradeOrderCancelConfirmMarketInformation {
    
    /** 市場略名. */
    private String marketName;
    
    /** 市場コード（全角半角）. */
    private String marketCode;
    
    /** 国コード（全角半角）. */
    private String countryCode;
    
}
