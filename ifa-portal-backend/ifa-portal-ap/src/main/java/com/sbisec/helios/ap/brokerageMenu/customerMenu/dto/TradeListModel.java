package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class TradeListModel {
    
    /** 約定時刻. */
    private String tradeTime;
    
    /** 約定数量（数値(整数)）. */
    private String tradeQuantity;
    
    /** 約定単価（数値(小数)）. */
    private String tradePrice;
    
    /** 約定金額（数値(整数)）. */
    private String contractAmount;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;
    
    /** 取消ステータス. */
    private String cancelStatus;
    
    /** 市場（算出）. */
    private String market;
    
}
