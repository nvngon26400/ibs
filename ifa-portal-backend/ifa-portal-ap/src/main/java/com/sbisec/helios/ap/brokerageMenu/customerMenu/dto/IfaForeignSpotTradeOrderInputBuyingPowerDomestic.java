package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 外国現物取引注文入力円貨余力情報
 *
 * @author SCSK 宇田川達弥
 */
@Data
public class IfaForeignSpotTradeOrderInputBuyingPowerDomestic {

    /** 買付余力（総合）. */
    private String yenBuyingPowerGeneralAccount;
    
    /** NISA投資可能枠リスト. */
    private List<IfaForeignSpotTradeOrderInputBuyingPowerDomesticNisaBuyLimit> nisaBuyLimitList;
    
    /** 買付余力（JrNISA）. */
    private String yenBuyingPowerJrNisa;
    
    /** 口座分類（JrNISA）. */
    private String accountClassificationJrNisa;

}
