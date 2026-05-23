package com.sbisec.helios.gw.brokerageMenu.commFee.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 担当顧客別手数料一覧APIリクエスト証券種別
 *
 * @author 宇田川達弥
 */
@Data
public class IfaRepCustomerCommListApiRequestSecurityClass {
    
    /** id */
    @NotEmpty(message = "証券種別(id)")
    private String id;
    
    /** isSelected */
    @NotEmpty(message = "証券種別(isSelected)")
    private String isSelected;
}
