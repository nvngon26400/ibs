package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 米株信用保証金振替入力  A003 レスポンスパラメータ
 *
 * @author SCSK
 * 
 */
@Data
public class IfaForeignMarginDepositTransferInputA003ApiResponse {
    
    /** 指示可能金額（数値(小数)）. */
    private String destinationAbleAmount;
    
    /** 信用建余力_振替指示前（数値(小数)）. */
    private String marginPositionPowerBefore;
    
    /** 信用建余力_振替指示後（数値(小数)）. */
    private String marginPositionPowerAfter;
    
    /** 委託保証金率_振替指示前（数値(小数)）. */
    private String marginDepositRateBefore;
    
    /** 委託保証金率_振替指示後（数値(小数)）. */
    private String marginDepositRateAfter;
    
}
