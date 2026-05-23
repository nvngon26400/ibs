package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 信用新規注文取消確認初期化リクエスト.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaMarginNewOrderCancelConfirmA001RequestDto {
    
    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;
}
