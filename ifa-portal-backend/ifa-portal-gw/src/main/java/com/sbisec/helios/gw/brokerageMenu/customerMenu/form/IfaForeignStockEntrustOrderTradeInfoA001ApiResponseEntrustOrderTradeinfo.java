package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 委託注文約定情報リスト
 *
 * @author SCSK 矢口
 */
@Data
public class IfaForeignStockEntrustOrderTradeInfoA001ApiResponseEntrustOrderTradeinfo {
    
    /** 約定日時. */
    private String tradeDateTime;
    
    /** 約定数量（数値(整数)）. */
    private String tradeQuantity;
    
    /** 約定単価（数値(小数)）. */
    private String tradePrice;
    
    /** 約定単価通貨コード. */
    private String tradePriceCurrencyCode;
    
}
