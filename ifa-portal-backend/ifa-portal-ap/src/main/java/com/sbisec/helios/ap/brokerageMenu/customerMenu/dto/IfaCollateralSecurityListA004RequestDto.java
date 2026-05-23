package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 代用有価証券一覧 A004 リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaCollateralSecurityListA004RequestDto {
    
    /** 表示基準日（受渡日）. */
    private String displayBaseDate;
    
    /** 代用種別. */
    private String collateralClass;
    
}
