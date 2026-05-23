package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 米株信用保証金振替確認A001aリクエストDTO
 *
 * @author SCSK
 */
@Data
public class IfaForeignMarginDepositTransferConfirmA001aRequestDto {
    
    /** 委託保証金振替区分. */
    private String entrustDepositTransferClassification;
    
    /** 指示金額（数値(小数)）. */
    private String destinationAmount;
    
}
