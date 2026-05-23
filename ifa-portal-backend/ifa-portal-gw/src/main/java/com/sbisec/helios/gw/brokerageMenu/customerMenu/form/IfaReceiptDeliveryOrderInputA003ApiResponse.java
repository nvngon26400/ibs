package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * SUB0202_0212-08_1_現引現渡注文入力
 *
 * @author 池亀緑
 */
@Data
public class IfaReceiptDeliveryOrderInputA003ApiResponse {
    
    /** 新規建玉指定番号 */
    private String newOpenInterestNumber;
    
    /** 親株新規建日 */
    private String parentStockConstructionDate;
    
    /** 新規建日 */
    private String constructionDate;
    
    /** 取得単価 */
    private String openPrice;
    
    /** 弁済期限年月日数. */
    private String paymentDeadlineDate;

    /** 年月日区分. */
    private String dateKbn;

    /** 新規単価 */
    private String newPrice;
    
    /** 注文可能数量 */
    private String maxOrderableQuantity;

    /** 弁済期限（算出） */
    private String paymentDeadlineCalculation;
    
    /** 現引可能額.受渡日（T+2） */
    private String deliveryDateT2;
    
    /** 現引可能額.受渡日（T+2）_現引可能額 */
    private String cashOnDeliveryT2;
    
    /** 現引可能額.受渡日（T+3） */
    private String deliveryDateT3;
    
    /** 現引可能額.受渡日（T+3）_現引可能額 */
    private String cashOnDeliveryT3;
    
}
