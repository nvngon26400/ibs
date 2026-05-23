package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * 米株信用保証金振替入力  A003 リクエストパラメータ
 *
 * @author SCSK
 * 
 */
@Data
public class IfaForeignMarginDepositTransferInputA003ApiRequest {
    
    /** 口座選択. */
    @NotEmpty(message = "口座選択")
    private String accountSelect;
    
    /** 指示金額（数値(小数)）. */
    @Digits(integer = 12, fraction = 2, message = "指示金額")
    @Size(max = 18, message = "指示金額")
    private String destinationAmount;
    
}
