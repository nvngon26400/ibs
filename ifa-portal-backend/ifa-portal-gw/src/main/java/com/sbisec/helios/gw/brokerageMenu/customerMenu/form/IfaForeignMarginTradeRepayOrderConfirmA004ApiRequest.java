package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * A004のリクエスト
 *
 * @author SCSK
 */
@Data
public class IfaForeignMarginTradeRepayOrderConfirmA004ApiRequest {

    /** 銘柄コード */
    private String brandCode;
    
    /** 取引種別. */
    private String tradeCd;
}
