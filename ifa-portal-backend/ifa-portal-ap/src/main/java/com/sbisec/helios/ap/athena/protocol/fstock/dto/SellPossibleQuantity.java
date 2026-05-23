package com.sbisec.helios.ap.athena.protocol.fstock.dto;

import lombok.Data;

/**
 * 売却可能数.
 *
 * @author SCSK 笹倉 秀行
 *
 */
@Data
public class SellPossibleQuantity {
    
    /** 預り区分 */
    private String specificAccountCode;
    
    /** 株数 */
    private String securitiesQuantity;
    
    /** 保護区分*/
    private String depositType;
}
