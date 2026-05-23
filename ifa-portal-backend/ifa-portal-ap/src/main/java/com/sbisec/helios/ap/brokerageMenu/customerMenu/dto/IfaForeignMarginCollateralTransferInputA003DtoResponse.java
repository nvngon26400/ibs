package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 米株信用代用振替入力　A003　レスポンスDTO

 * @author SCSK川崎
 */
@Data
public class IfaForeignMarginCollateralTransferInputA003DtoResponse {
    
    /** 現在の余力情報. */
    private IfaForeignMarginCollateralTransferPowerInfo currentPower;
    
    /** 受付後余力情報. */
    private IfaForeignMarginCollateralTransferPowerInfo afterPower;
    
}
