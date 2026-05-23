package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 米株信用代用振替確認 A001b レスポンスDTO

 * @author SCSK川崎
 */
@Data
public class IfaForeignMarginCollateralTransferConfirmA001bDtoResponse {
    
    /** 振替指示後余力情報. */
    private IfaForeignMarginCollateralTransferPowerInfo afterPower;
        
}
