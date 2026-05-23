package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 米株信用取引新規注文入力 銘柄情報
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaForeignMarginTradeNewOrderInputBrand {
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
}
