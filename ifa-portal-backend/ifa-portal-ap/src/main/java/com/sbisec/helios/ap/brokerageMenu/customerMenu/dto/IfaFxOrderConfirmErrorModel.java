package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaFxOrderConfirmErrorModel {
    
    /** エラーメッセージ */
    private String errorMessage;
    
    /** エラーコード */
    private String errorCode;
}
