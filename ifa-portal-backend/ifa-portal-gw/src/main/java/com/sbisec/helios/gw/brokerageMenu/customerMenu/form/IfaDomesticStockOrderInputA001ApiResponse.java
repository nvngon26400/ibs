package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 国内株式注文入力A001レスポンストDTO
 *
 * @author SCSK
 * 
 */
@Data
public class IfaDomesticStockOrderInputA001ApiResponse {
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 買付余力. */
    private IfaDomesticStockOrderInputApiResponseBuyingPower buyingPower;
    
}
