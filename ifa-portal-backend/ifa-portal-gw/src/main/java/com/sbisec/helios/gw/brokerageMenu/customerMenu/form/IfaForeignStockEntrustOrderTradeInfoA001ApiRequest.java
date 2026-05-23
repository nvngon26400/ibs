package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * 外国株式委託注文約定情報A001リクエスト
 *
 * @author SCSK 矢口
 */
@Data
public class IfaForeignStockEntrustOrderTradeInfoA001ApiRequest {
    
    /** 注文番号（数字）. */
    @NotEmpty(message = "注文番号")
    @Pattern(regexp = "0-9", message = "注文番号")
    @Size(min = 18, max = 18, message = "注文番号")
    private String orderNumber;
    
    /** 注文Sub番号（数字）. */
    @Digits(integer = 18, fraction = 0, message = "注文Sub番号")
    @NotEmpty(message = "注文Sub番号")
    @Pattern(regexp = "0-9", message = "注文Sub番号")
    @Size(max = 18, message = "注文Sub番号")
    private String orderSubNumber;
    
    /** 取引種別（全角半角）. */
    @NotEmpty(message = "取引種別")
    @Size(min = 3, max = 3, message = "取引種別")
    private String tradeCd;
    
}
