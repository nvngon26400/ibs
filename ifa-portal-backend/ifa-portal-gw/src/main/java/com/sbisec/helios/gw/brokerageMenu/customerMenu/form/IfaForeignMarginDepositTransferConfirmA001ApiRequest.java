package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 米株信用保証金振替確認A001リクエスト
 *
 * @author SCSK 
 */
@Data
public class IfaForeignMarginDepositTransferConfirmA001ApiRequest {
    
    /** 委託保証金振替区分. */
    @NotEmpty(message = "委託保証金振替区分")
    private String entrustDepositTransferClassification;
    
    /** 指示金額（数値(小数)）. */
    @Digits(integer = 12, fraction = 2, message = "指示金額")
    @NotEmpty(message = "指示金額")
    @Size(max = 18, message = "指示金額")
    private String destinationAmount;
    
}
