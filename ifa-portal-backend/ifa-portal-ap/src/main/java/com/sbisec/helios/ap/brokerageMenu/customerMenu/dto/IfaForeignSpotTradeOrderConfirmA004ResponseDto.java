package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 外国現物取引注文確認更新レスポンス
 *
 * @author 福岡利基
 */
@Data
public class IfaForeignSpotTradeOrderConfirmA004ResponseDto {
    
    /** 価格基本情報. */
    private IfaForeignSpotTradeOrderConfirmA004ResponseDtoPrise priceBasicInfo;
    
}
