package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;
import lombok.Data;

/**
 * 米株信用代用振替入力　A004　レスポンスDTO

 * @author SCSK川崎
 */
@Data
public class IfaForeignMarginCollateralTransferInputA004DtoResponse {
    
    /** 代用有価証券振替情報. */
    private List<IfaForeignMarginCollateralTransferInputCollateralSecurity> collateralSecurityTransferInfo;
    
    /** 現在の余力情報. */
    private IfaForeignMarginCollateralTransferPowerInfo currentPower;

    /** 受付後余力情報. */
    private IfaForeignMarginCollateralTransferPowerInfo afterPower;
    
    /** 代用有価証券振替区分. */
    private String transferClassification;
    
}
