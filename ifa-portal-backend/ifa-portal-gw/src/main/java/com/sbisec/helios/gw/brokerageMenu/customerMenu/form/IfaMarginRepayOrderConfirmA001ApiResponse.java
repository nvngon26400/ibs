package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 信用返済注文確認注文発注APIレスポンス.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaMarginRepayOrderConfirmA001ApiResponse {
    
    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;
    
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
    
    /** 約定予定日. */
    private String contractDate;
    
    /** 受渡予定日. */
    private String deliveryDate;
    
    /** 受注日時. */
    private String orderDayTime;
    
    /** リクエスト内容. */
    private IfaMarginRepayOrderConfirmA001ApiRequest request;
}
