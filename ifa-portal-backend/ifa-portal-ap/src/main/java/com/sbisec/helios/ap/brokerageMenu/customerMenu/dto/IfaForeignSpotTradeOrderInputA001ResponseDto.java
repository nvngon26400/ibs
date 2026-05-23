package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 外国現物取引注文入力初期化レスポンス
 *
 * @author SCSK 宇田川達弥
 */
@Data
public class IfaForeignSpotTradeOrderInputA001ResponseDto {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 国コード（全角半角）. */
    private String countryCode;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 取引種別（全角半角）. */
    private String buySellTypeName;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
}
