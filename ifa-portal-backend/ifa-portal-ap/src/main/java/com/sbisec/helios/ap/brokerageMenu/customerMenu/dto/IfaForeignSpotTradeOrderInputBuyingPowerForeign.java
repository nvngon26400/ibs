package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 外国現物取引注文入力外貨余力情報
 *
 * @author SCSK 宇田川達弥
 */
@Data
public class IfaForeignSpotTradeOrderInputBuyingPowerForeign {

    /** 買付余力（総合）. */
    private String foreignBuyingPower;
    
    /** 通貨コード（総合）. */
    private String currencyCode;
    
    /** 買付余力（JrNISA）. */
    private String foreignBuyingPowerJrNisa;
    
    /** 通貨コード（JrNISA）. */
    private String currencyCodeJrNisa;
    
}
