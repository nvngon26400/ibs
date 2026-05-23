package com.sbisec.helios.ap.athena.protocol.exchange.order;

import lombok.Data;

@Data
public class GetExchangeCancelledOrderInitializationRes {
    
    /** 注文番号 */
    private String orderNo;
    
    /** 注文種別表示 */
    private String orderType;
    
    /** 通貨コード */
    private String currencyCode;
    
    /** 注文日時 */
    private String orderInputDatetime;
    
    /** 約定日時 */
    private String executionDatetime;
    
    /** 終了日時 */
    private String closeDatetime;
    
    /** 為替注文金額 */
    private String orderAmount;
    
    /** 受渡金額（円貨） */
    private String netAmount;
    
    /** 為替レート */
    private String exchangeRate;
    
    /** 売買区分 */
    private String buySellCode;
    
    /** 営業日 */
    private String businessDate;
    
    /** 注文イベントID */
    private String orderEventId;
    
    /** 預り区分 */
    private String specificAccountCode;
    
    /** 為替取引 */
    private String exchangeTradeType;
    
    /** サイクル番号 */
    private int cycleNumber;
    
    /** 保護区分 */
    private String depositType;
    
}
