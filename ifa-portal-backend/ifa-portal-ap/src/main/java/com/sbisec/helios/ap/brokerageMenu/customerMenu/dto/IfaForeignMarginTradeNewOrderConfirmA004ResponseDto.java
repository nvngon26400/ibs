package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 米株信用取引新規注文確認A004リスポンスDTO
 *
 * @author SCSK 金志
 * 
 */
@Data
public class IfaForeignMarginTradeNewOrderConfirmA004ResponseDto {
    
    /** 価格基本情報. */
    private PriceBaseInfo priceBaseInfo;
    
}
