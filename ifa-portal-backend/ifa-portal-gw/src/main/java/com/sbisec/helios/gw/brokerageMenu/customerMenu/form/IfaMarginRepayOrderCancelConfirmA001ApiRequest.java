package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 信用返済注文取消確認初期化APIリクエスト.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaMarginRepayOrderCancelConfirmA001ApiRequest {
    
    /** EC受注番号（半角英数字）. */
    @NotEmpty(message = "EC受注番号")
    private String ecOrderNo;
    
}
