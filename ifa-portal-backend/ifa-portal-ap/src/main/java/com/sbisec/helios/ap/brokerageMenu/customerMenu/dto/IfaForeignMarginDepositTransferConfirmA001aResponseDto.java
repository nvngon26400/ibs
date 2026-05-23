package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 米株信用保証金振替確認A001aレスポンスDTO
 *
 * @author SCSK
 */
@Data
public class IfaForeignMarginDepositTransferConfirmA001aResponseDto {
    
    /** 委託保証金振替区分. */
    private String entrustDepositTransferClassification;
    
    /** 指示金額（数値(小数)）. */
    private String destinationAmount;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** IFA保証金振替指示番号. */
    private String depositTransferNo;
    
}
