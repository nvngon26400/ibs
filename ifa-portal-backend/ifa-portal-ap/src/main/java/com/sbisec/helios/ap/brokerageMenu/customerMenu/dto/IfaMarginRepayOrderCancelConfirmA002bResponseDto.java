package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 信用返済注文取消確認注文発注レスポンス.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaMarginRepayOrderCancelConfirmA002bResponseDto {
    
    /** 受注日時. */
    private String orderDayTime;
    
    /** リクエスト内容. */
    private IfaMarginRepayOrderCancelConfirmA002aRequestDto request;
    
}
