package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * 米株信用取引新規注文入力 A005 リクエストパラメータ
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaForeignMarginTradeNewOrderInputA005ApiRequest {
    
    /** 銘柄コード（半角英数字）. */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;
    
    /** 取引種別（全角半角）. */
    @NotEmpty(message = "取引種別")
    private String tradeCd;
    
}
