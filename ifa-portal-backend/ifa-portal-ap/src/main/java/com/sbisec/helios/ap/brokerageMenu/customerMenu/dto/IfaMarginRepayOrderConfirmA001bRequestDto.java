package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 信用返済注文確認注文発注リクエスト.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaMarginRepayOrderConfirmA001bRequestDto {
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** リクエスト内容. */
    private IfaMarginRepayOrderConfirmA001aRequestDto request;
    
    private String warningMessages;
}
