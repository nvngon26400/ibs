package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaForeignMarginTradeRepayOrderErrorModel {
    
    /** エラーメッセージ */
    private String errorMessage;
    
    /** エラーコード */
    private String errorCode;
}
