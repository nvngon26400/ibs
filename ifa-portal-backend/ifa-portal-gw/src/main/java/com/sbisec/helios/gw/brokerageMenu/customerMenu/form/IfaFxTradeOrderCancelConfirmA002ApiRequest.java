package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaFxTradeOrderCancelConfirmA002ApiRequest {
    
    /** 注文番号（数字）. */
    @NotEmpty(message = "注文番号")
    @Pattern(regexp = "0-9", message = "注文番号")
    @Size(min = 18, max = 18, message = "注文番号")
    private String orderNumber;
    
    /** 取引種別（全角半角）. */
    @NotEmpty(message = "取引種別")
    private String tradeCd;
    
    /** 通貨コード（全角半角）. */
    @NotEmpty(message = "通貨コード")
    private String currencyCode;
    
    /** 営業日. */
    @NotEmpty(message = "営業日")
    private String businessDay;
    
    /** サイクル番号. */
    @NotEmpty(message = "サイクル番号")
    private String cycleNumber;
    
    /** 注文イベントID. */
    @NotEmpty(message = "注文イベントID")
    private String orderEventId;
    
    /** 通貨名（全角）. */
    @NotEmpty(message = "通貨名")
    private String currency;
    
    /** 数量（数値(整数)）. */
    @Digits(integer = 15, fraction = 0, message = "数量")
    @NotEmpty(message = "数量")
    @Size(max = 19, message = "数量")
    private String quantity;
    
    /** 取引口座（全角半角）. */
    @NotEmpty(message = "取引口座")
    @Size(max = 10, message = "取引口座")
    private String tradingAccount;
    
}
