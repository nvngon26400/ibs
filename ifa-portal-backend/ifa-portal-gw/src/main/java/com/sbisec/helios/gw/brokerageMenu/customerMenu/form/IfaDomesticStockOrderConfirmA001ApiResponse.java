package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaDomesticStockOrderConfirmA001ApiResponse {
    
    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;
    
    /** ジュニアNISA振替金額（数値(整数)）. */
    private String jrnisaTransferAmount;
    
    /** 見積単価（数値(小数)）. */
    private String quoteUnitPrice;
    
    /** 約定金額（数値(整数)）. */
    private String contractAmount;
    
    /** 手数料/諸費用（数値(整数)）. */
    private String charge;
    
    /** 消費税（数値(整数)）. */
    private String consumptionTax;
    
    /** 讓渡益税（数値(整数)）. */
    private String yieldTax;
    
    /** 精算金額（数値(整数)）. */
    private String settlementAmount;
    
    /** 投資可能枠. */
    private String investableAmount;
    
    /** 約定予定日. */
    private String contractDate;
    
    /** 受渡予定日. */
    private String deliveryDate;
    
    /** 受注日時. */
    private String orderDayTime;
    
    /** 注文入力市場（全角半角）. */
    private String orderedMarket;
    
    /** リクエスト内容. */
    private IfaDomesticStockOrderConfirmA001ApiRequest requestContents;
    
}
