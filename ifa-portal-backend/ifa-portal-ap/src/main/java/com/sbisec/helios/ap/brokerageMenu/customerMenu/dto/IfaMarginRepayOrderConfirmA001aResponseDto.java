package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 信用返済注文確認発注前の注文内容登録レスポンス.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaMarginRepayOrderConfirmA001aResponseDto {
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    private String warningMessages;
}
