package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 米株信用保証金振替入力  A004 レスポンスパラメータ
 *
 * @author SCSK
 * 
 */
@Data
public class IfaForeignMarginDepositTransferInputA004ApiResponse {
    
    /** 振替指定日. */
    private String transferDate;
    
    /** 受付後振替指示可能額（数値(小数)）. */
    private String afterTransferDestinationAbleAmount;
    
    /** 現在の振替指示可能額（数値(小数)）. */
    private String currentTransferDestinationAbleAmount;
    
    /** 信用建余力_振替指示前（数値(小数)）. */
    private String marginPositionPowerBefore;
    
    /** 信用建余力_振替指示後（数値(小数)）. */
    private String marginPositionPowerAfter;
    
    /** 委託保証金率_振替指示前（数値(小数)）. */
    private String marginDepositRateBefore;
    
    /** 委託保証金率_振替指示後（数値(小数)）. */
    private String marginDepositRateAfter;
    
    /** 口座選択. */
    private String accountSelect;
    
    /** 指示金額（数値(小数)）. */
    private String destinationAmount;
    
}
