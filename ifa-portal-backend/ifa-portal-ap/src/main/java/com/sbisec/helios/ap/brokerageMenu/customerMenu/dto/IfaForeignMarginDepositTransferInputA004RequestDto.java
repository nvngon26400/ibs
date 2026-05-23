package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 米株信用保証金振替入力  A004 リクエストパラメータ
 *
 * @author SCSK
 * 
 */
@Data
public class IfaForeignMarginDepositTransferInputA004RequestDto {
    
    /** 口座選択. */
    private String accountSelect;
    
    /** 指示金額（数値(小数)）. */
    private String destinationAmount;
    
}
