package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 外国現物取引注文確認更新リクエスト
 *
 * @author 福岡利基
 */
@Data
public class IfaForeignSpotTradeOrderConfirmA004RequestDto {
    
    /** 国コード（全角半角）. */
    private String countryCode;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
}
