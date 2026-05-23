package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 画面ID：SUB0202_0208-04_1
 * 画面名：国内株式注文取消確認
 * 2024/01/10 新規作成
 *
 * @author 卞智ホ
 */
@Data
public class IfaDomesticStockOrderCancelConfirmA001ApiRequest {
    
    /** EC受注番号（半角英数字） */
    @NotEmpty(message = "EC受注番号")
    @Size(min = 6, max = 6, message = "EC受注番号")
    private String ecOrderNo;
    
}
