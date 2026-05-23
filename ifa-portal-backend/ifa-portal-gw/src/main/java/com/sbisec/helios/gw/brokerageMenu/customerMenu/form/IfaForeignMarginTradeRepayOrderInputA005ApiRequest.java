package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaForeignMarginTradeRepayOrderInputA005ApiRequest {
    
    /** 銘柄コード（半角英数字）. */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;
    
    /** 取引種別（全角半角）. */
    @NotEmpty(message = "取引種別")
    @Size(min = 3, max = 3, message = "取引種別")
    private String tradeCd;
    
    /** 期限. */
    @NotEmpty(message = "期限")
    private String limit;
    
    /** 預り区分（全角半角）. */
    @NotEmpty(message = "預り区分")
    @Size(max = 20, message = "預り区分")
    private String depositType;
    
    /** 返済建玉指定方法（全角半角）. */
    @NotEmpty(message = "返済建玉指定方法")
    @Size(max = 8, message = "返済建玉指定方法")
    private String repayPositionDesignateMethod;
    
    /** 建区分. */
    @NotEmpty(message = "建区分")
    private String trade;
    
    /** 弁済期限（全角半角）. */
    @NotEmpty(message = "弁済期限")
    @Size(max = 6, message = "弁済期限")
    private String paymentDeadline;
    
}
