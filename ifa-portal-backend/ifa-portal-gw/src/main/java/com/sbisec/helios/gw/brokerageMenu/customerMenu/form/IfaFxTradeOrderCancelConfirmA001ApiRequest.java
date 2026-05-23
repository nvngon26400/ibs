package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaFxTradeOrderCancelConfirmA001ApiRequest {
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 通貨名（全角）. */
    private String currency;
    
    /** 注文番号（数字）. */
    @Pattern(regexp = "0-9", message = "注文番号")
    @Size(min = 18, max = 18, message = "注文番号")
    private String orderNumber;
    
    /** サイクル番号. */
    private String cycleNumber;
    
    /** 注文イベントID. */
    private String orderEventId;
    
    /** 営業日 */
    private String businessDay;
    
}
