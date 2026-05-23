package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 米株信用取引新規注文入力 A019 レスポンスパラメータ
 * 
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaForeignMarginTradeNewOrderInputA019ResponseDto {
    
    /** 参考信用建余力（数値(小数)）. */
    private String referenceMarginPower;
    
}
