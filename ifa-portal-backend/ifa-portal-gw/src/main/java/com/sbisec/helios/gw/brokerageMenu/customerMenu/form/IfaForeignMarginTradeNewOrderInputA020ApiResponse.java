package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 米株信用取引新規注文入力 A020 レスポンスパラメータ
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaForeignMarginTradeNewOrderInputA020ApiResponse {
    
    /** 約定金額(外貨). */
    private String approximatePositionAmount;
    
}
