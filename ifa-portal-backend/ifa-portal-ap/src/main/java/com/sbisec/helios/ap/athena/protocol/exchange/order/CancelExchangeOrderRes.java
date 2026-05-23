package com.sbisec.helios.ap.athena.protocol.exchange.order;

import lombok.Data;

@Data
public class CancelExchangeOrderRes {
    
    // 注文番号
    private String orderNo;
    
    //為替注文ステータス
    private String exchangeOrderStatus;
    
    //注文種別表示
    private String orderType;
    
    // 通貨コード
    private String currencyCode;

    // 終了日時
    private String closeDatetime;

    // 為替注文金額
    private String orderAmount;

    // 売買区分
    private String buySellCode;

    //営業日(yyyy-MM-dd)
    private String businessDate;

    //注文イベントID
    private String orderEventId;

    //口座区分
    private String specificAccountCode;
    
    //為替取引
    private String exchangeTradeType;
    
    //為替処理ステータス
    private String exchangeProcStatus;

    //サイクル番号
    private Integer cycleNumber;

    //保護区分
    private String depositType;
}
