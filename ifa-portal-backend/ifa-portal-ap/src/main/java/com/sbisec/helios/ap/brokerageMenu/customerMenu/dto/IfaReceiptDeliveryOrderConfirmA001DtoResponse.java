package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0212-08_2
 * 画面名：現引現渡注文確認
 * 2024/04/01 新規作成
 *
 * @author SCSK 笹倉秀行
 */
@Data
public class IfaReceiptDeliveryOrderConfirmA001DtoResponse {
    
    /** リクエスト内容 */
    private IfaReceiptDeliveryOrderConfirmA001DtoRequest req;
    
    /** 約定金額 */
    private String contractAmount;
    
    /** 手数料 */
    private String charge;
    
    /** 消費税 */
    private String consumptionTax;
    
    /** 譲渡益税 */
    private String yieldTax;
    
    /** 精算金額 */
    private String settlementAmount;
    
    /** 約定予定日 */
    private String contractDate;
    
    /** 受渡予定日 */
    private String deliveryDate;
    
    /** 受注日 */
    private String orderDate;
    
    /** 受注時刻 */
    private String orderTime;
    
    /** EC受注番号 */
    private String ecOrderNo;
}
