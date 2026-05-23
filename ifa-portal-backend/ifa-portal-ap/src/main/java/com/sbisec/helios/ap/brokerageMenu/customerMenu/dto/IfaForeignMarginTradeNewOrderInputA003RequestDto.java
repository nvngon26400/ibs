package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;


/**
 * 米株信用取引新規注文入力 A003 リクエストパラメータ
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaForeignMarginTradeNewOrderInputA003RequestDto {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
}
