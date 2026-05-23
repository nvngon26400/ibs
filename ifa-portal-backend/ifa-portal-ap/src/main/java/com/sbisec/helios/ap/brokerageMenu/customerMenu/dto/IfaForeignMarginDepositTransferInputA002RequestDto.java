package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 米株信用保証金振替入力  A002 リクエストパラメータ
 *
 * @author SCSK
 * 
 */
@Data
public class IfaForeignMarginDepositTransferInputA002RequestDto {
    
    /** 口座選択. */
    private String accountSelect;
    
}
