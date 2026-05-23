package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;
import lombok.Data;

/**
 * 米株信用代用振替入力　A004　レスポンス

 * @author SCSK川崎
 */
@Data
public class IfaForeignMarginCollateralTransferInputA004ApiResponse {
    
    /** 代用有価証券振替情報. */
    private List<IfaForeignMarginCollateralTransferInputCollateralSecurity> collateralSecurityTransferInfo;
    
    /** 現在の余力情報. */
    private IfaForeignMarginCollateralTransferInputPowerInfo currentPower;
    
    /** 受付後余力情報. */
    private IfaForeignMarginCollateralTransferInputPowerInfo afterPower;
    
    /** 代用有価証券振替区分. */
    private String transferClassification;

}
