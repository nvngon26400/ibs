package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaForeignMarginTradeRepayOrderInputA001RequestDto {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 弁済期限（全角半角）. */
    private String paymentDeadline;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 建区分. */
    private String trade;
    
}
