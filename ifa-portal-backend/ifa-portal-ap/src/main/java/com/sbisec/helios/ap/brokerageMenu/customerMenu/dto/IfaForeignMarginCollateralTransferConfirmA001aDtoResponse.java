package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 米株信用代用振替確認 A001a レスポンスDTO

 * @author SCSK川崎
 */
@Data
public class IfaForeignMarginCollateralTransferConfirmA001aDtoResponse {
    
    /** IFA代用振替指示番号. */
    private String stockTransferNo;
    
}
