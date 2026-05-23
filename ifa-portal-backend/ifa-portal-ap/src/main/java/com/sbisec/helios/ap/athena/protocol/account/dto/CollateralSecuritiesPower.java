package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 代用有価証券余力情報 Dto.

 * @author SCSK 川崎
   @date 03/26/2024
 */
@Data
public class CollateralSecuritiesPower implements Serializable {
    
    private static final long serialVersionUID = -8639196653621158208L;
    
    public CollateralSecuritiesPower() {
        
    }
    
    /** 代用有価証券評価額合計 */
    private String totalCollateralValue;
    
    /** 受入委託保証金 */
    private String grossMargin;
    
    /** 信用建余力 */
    private String marginBuyingPower;
    
    /** 出庫余力 */
    private String collateralWithdrawable;
    
    /** 預託率 */
    private String depositRate;
    
}
