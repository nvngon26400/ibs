package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 米株信用代用振替入力　代用有価証券振替情報

 * @author SCSK川崎
 */
@Data
public class IfaForeignMarginCollateralTransferInputCollateralSecurity {
    
    /** 銘柄名（全角半角）. */
    private String collateralDepositListBrandName;
    
    /** 銘柄コード（半角英数字）. */
    private String collateralDepositListBrandCode;
    
    /** 代用掛目（数値(整数)）. */
    private String collateralAssessment;
    
    /** 預り区分（全角半角）. */
    private String specificAccountCode;
    
    /** 保有株数（数値(整数)）. */
    private String transferQuantity;
    
    /** 受渡未到来株数. */
    private String notReceivedQuantity;
    
    /** 評価単価. */
    private String valuatePrice;
    
    /** 代用評価額. */
    private String collateralValuation;
    
    /** 保護区分（全角半角）. */
    private String depositType;
}
