package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaFxOrderInputA001ApiRequest {
    
    /** 通貨コード（全角半角）. */
    @NotEmpty(message = "通貨コード")
    private String currencyCode;
    
    /** 売買区分（全角半角）. */
    @NotEmpty(message = "売買区分")
    @Size(max = 2, message = "売買区分")
    private String tradeKbn;
    
}
