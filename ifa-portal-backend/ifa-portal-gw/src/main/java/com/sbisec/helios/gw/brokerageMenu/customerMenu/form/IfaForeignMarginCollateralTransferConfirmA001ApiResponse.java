package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 米株信用代用振替確認 A001 レスポンス

 * @author SCSK川崎
 */
@Data
public class IfaForeignMarginCollateralTransferConfirmA001ApiResponse {
    
    /** 振替指示後余力情報. */
    private IfaForeignMarginCollateralTransferInputPowerInfo afterPower;
    
}
