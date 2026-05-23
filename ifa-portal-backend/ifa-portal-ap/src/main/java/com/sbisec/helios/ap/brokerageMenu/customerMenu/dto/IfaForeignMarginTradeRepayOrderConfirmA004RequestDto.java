package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * A004のリクエスト
 *
 * @author SCSK
 */
@Data
public class IfaForeignMarginTradeRepayOrderConfirmA004RequestDto {
    
    /** 銘柄コード */
    private String brandCode;
    
    /** 取引種別. */
    private String tradeCd;
}
