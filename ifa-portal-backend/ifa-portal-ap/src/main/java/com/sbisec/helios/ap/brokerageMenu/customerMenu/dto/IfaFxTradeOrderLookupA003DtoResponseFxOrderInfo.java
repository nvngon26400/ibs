package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 為替注文情報
 *
 * @author SCSK
 */

@Data
public class IfaFxTradeOrderLookupA003DtoResponseFxOrderInfo {
    
    /** 注文日時. */
    private String orderDate;
    
    /** 為替注文ステータス. */
    private String fxOrderStatus;
    
    /** 約定日時. */
    private String tradeDateTime;
    
    /** 締切日時. */
    private String deadlineDate;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 取引種別. */
    private String tradeCd;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 受渡金額（数値(整数)）. */
    private String deliveryAmount;
    
    /** 約定レート. */
    private String contractRate;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 為替処理ステータス. */
    private String fxTradeStatus;
    
    /** 注文状況（全角半角）. */
    private String orderStatus;
    
    /** 口座区分. */
    private String accountType;
    
    /** 注文番号（数字）. */
    private String orderNumber;
    
    /** サイクル番号. */
    private String cycleNumber;
    
    /** 注文イベントID. */
    private String orderEventId;
    
    /** 営業日 */
    private String businessDay;
}
