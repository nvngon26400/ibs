package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 外国現物取引注文確認更新APIレスポンス
 *
 * @author 福岡利基
 */
@Data
public class IfaForeignSpotTradeOrderConfirmA004ApiResponse {
    
    /** 価格基本情報. */
    private IfaForeignSpotTradeOrderConfirmA004ApiResponsePrise priceBasicInfo;
    
}
