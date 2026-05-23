package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 外国現物取引注文入力更新リクエスト
 *
 * @author SCSK 宇田川達弥
 */
@Data
public class IfaForeignSpotTradeOrderInputA005RequestDto {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 国コード（全角半角）. */
    private String countryCode;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 取引通貨. */
    private String limitPriceText;
    
    /** 取引種別（全角半角）. */
    private String buySellTypeName;
}
