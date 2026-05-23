package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaForeignMarginTradeRepayOrderInputA005RequestDto {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 期限. */
    private String limit;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 返済建玉指定方法（全角半角）. */
    private String repayPositionDesignateMethod;
    
    /** 建区分. */
    private String trade;
    
    /** 弁済期限（全角半角）. */
    private String paymentDeadline;
    
}
