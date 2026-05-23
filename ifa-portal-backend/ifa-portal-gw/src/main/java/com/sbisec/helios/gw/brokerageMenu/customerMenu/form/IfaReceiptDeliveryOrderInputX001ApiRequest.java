package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * SUB0202_0212-08_1_現引現渡注文入力
 *
 * @author 池亀緑
 */
@Data
public class IfaReceiptDeliveryOrderInputX001ApiRequest {
    
    /** 銘柄コード */
    private String brandCode;
    
    /** 新規売買区分 */
    private String openTradeKbn;
    
    /** 弁済期限 */
    private String paymentDeadline;
    
    /** 新規市場 */
    private String newOpenMarket;
    
    /** 親株新規約定日 */ 
    private String parentStockTradeDate;

    /** 新規約定日 */ 
    private String newTradeDate;

    /** 新規単価 */ 
    private String newPrice;
 
}
