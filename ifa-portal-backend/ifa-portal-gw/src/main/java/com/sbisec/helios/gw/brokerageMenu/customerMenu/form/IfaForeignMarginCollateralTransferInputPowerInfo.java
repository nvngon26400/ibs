package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 米株信用代用振替入力　余力情報

 * @author SCSK川崎
 */
@Data
public class IfaForeignMarginCollateralTransferInputPowerInfo {
    
    /** 信用建余力. */
    private String marginBuyingPower;
    
    /** 預託率. */
    private String depositRate;
    
    /** 代用有価証券評価額合計（数値(整数)）. */
    private String totalCollateralValue;
    
}
