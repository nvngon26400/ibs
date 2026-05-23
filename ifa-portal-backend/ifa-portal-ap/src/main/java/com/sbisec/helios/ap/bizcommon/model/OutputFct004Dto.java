package com.sbisec.helios.ap.bizcommon.model;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 共通関数DTO：FCT004
 * @author base 熊
 */

@Data
public class OutputFct004Dto extends BaseOutputDto {
    
    //計算後の余力金額
    private BigDecimal byingPowerMoneyAfterCalculate;
    
    //当日店頭買付約定金額
    private BigDecimal otcBuyingContractAmountToday;
    
    //外国債券の当日約定金額（買付）
    private BigDecimal contractAmountTodayWithinForeignBond;
    
    //リアルタイム余力(買付)
    private BigDecimal realTimeBuyingPower;
    
}
