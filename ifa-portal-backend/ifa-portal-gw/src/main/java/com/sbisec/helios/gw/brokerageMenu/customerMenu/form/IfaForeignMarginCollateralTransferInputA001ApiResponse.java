package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;
import lombok.Data;

/**
 * 米株信用代用振替入力　A001　レスポンス

 * @author SCSK川崎
 */
@Data
public class IfaForeignMarginCollateralTransferInputA001ApiResponse {
    
    /** 出庫余力. */
    private String ableWithdrawal;
    
    /** 振替指示入力(保護→代用). */
    private List<IfaForeignMarginCollateralTransferInputCollateralSecurity> protection;
    
    /** 振替指示入力(代用→保護). */
    private List<IfaForeignMarginCollateralTransferInputCollateralSecurity> substitution;
    
    /** 貸株加入. */
    private Boolean subscribed;

}
