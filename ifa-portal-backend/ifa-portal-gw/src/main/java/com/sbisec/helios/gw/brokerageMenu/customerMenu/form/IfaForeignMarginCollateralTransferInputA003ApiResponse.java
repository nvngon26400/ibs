package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 米株信用代用振替入力　A003　レスポンス

 * @author SCSK川崎
 */
@Data
public class IfaForeignMarginCollateralTransferInputA003ApiResponse {
    
    /** 現在の余力情報. */
    private IfaForeignMarginCollateralTransferInputPowerInfo currentPower;
    
    /** 受付後余力情報. */
    private IfaForeignMarginCollateralTransferInputPowerInfo afterPower;
    
}
