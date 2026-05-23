package com.sbisec.helios.ap.athena.protocol.exchange.order.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ExchangeOrderDetail implements Serializable {
    
    private static final long serialVersionUID = -2367606091687424687L;
    
    public ExchangeOrderDetail() {
        
    }
    
    /** 注文番号 */
    private String orderNo;
    
    /** 為替注文ステータス */
    private String exchangeOrderStatus;
    
    /** 注文種別表示 */
    private String orderType;
    
    /** 通貨コード */
    private String currencyCode;
    
    /** 注文日時 */
    private String orderInputDatetime;
    
    /** 約定日時 */
    private String executionDatetime;
    
    /** 締切日時 */
    private String closeDatetime;
    
    /** 注文金額 */
    private String orderAmount;
    
    /** 受渡金額 */
    private String netAmount;
    
    /** レート */
    private String exchangeRate;
    
    /** 売買区分 */
    private String buySellCode;
    
    /**為替注文情報.営業日 "yyyy-MM-dd"形式*/
    private String businessDate;
    
    /** 注文イベントID */
    private String orderEventId;
    
    /** 口座区分 */
    private String specificAccountCode;
    
    /** 為替取引 */
    private String exchangeTradeType;
    
    /** 為替処理ステータス */
    private String exchangeProcStatus;
    
    /** サイクル番号 */
    private String cycleNumber;
    
    /** 預り区分 */
    private String depositType;
    
}
