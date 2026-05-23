package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 米株信用取引新規注文入力 市場情報
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaForeignMarginTradeNewOrderInputMarket {
    
    /** 市場コード（全角半角）. */
    private String marketCode;
    
    /** 市場略名. */
    private String listedMarket;
    
    /** 国コード（全角半角）. */
    private String countryCode;
    
    /** タイムゾーン略名. */
    private String timeZoneAbbreviatedName;
    
}
