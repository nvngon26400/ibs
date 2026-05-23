package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 米株信用代用振替確認 A001a リクエストDTO

 * @author SCSK川崎
 */
@Data
public class IfaForeignMarginCollateralTransferConfirmA001aDtoRequest {
    
    /** 代用有価証券振替区分. */
    @NotEmpty(message = "代用有価証券振替区分")
    private String transferClassification;
    
    /** 代用有価証券振替情報リスト. */
    @NotEmpty(message = "代用有価証券振替情報リスト")
    private List<IfaForeignMarginCollateralTransferConfirmInfo> collateralSecurityTransferInfoList;
    
}
