package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 米株信用代用振替確認 代用有価証券振替情報

 * @author SCSK川崎
 */
@Data
public class IfaForeignMarginCollateralTransferConfirmInfo {
    
    /** 代用有価証券振替情報.銘柄コード. */
    @NotEmpty(message = "代用有価証券振替情報.銘柄コード")
    private String collateralDepositListBrandCode;
    
    /** 代用有価証券振替情報.預り区分. */
    @NotEmpty(message = "代用有価証券振替情報.預り区分")
    private String specificAccountCode;
    
    /** 代用有価証券振替情報.保有株数. */
    @NotEmpty(message = "代用有価証券振替情報.保有株数")
    private String transferQuantity;
    
    /** 代用有価証券振替情報.保護区分. */
    @NotEmpty(message = "代用有価証券振替情報.保護区分")
    private String depositType;
    
}
