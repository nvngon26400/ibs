package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 米株信用保証金振替入力  A002 レスポンスパラメータ
 *
 * @author SCSK
 * 
 */
@Data
public class IfaForeignMarginDepositTransferInputA002ResponseDto {
    
    /** 指示可能金額（数値(小数)）. */
    private String destinationAbleAmount;
    
}
