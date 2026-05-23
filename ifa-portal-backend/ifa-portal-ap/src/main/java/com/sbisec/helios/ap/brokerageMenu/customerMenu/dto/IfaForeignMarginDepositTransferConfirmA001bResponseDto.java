package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 米株信用保証金振替確認A001bレスポンスDTO
 *
 * @author SCSK
 */
@Data
public class IfaForeignMarginDepositTransferConfirmA001bResponseDto {
    
    /** 信用建余力_振替指示後（数値(小数)）. */
    private String marginPositionPowerAfter;
    
    /** 委託保証金率_振替指示後（数値(小数)）. */
    private String marginDepositRateAfter;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
}
