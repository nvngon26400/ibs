package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 代用有価証券一覧 A001 リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaCollateralSecurityListA001RequestDto {
    
    /** 代用種別. */
    private String collateralClass;
    
}
