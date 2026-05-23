package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 画面ID：SUB0202_0208-03
 * 画面名：国内株式注文訂正入力
 * 2023/01/09 新規作成
 *
 * @author 齋藤
 */
@Data
public class IfaDomesticStockOrderCorrectInputA001ApiRequest {
    
    /** EC受注番号 */
    @NotEmpty(message = "EC受注番号")
    @Size(min = 6, max = 6, message = "EC受注番号")
    private String ecOrderNo;
    
}
