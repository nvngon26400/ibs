package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class IfaHoldingSecurityListA019ApiRequest {
    @NotEmpty(message = "協会コード")
    /** 協会コード. */
    private String fundCode;
}
