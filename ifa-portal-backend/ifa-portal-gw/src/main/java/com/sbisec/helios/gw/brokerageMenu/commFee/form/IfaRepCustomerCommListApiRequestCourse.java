package com.sbisec.helios.gw.brokerageMenu.commFee.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 担当顧客別手数料一覧APIリクエスト取引コース
 *
 * @author 宇田川達弥
 */
@Data
public class IfaRepCustomerCommListApiRequestCourse {
    
    /** id */
    @NotEmpty(message = "取引コース(id)")
    private String id;
    
    /** isSelected */
    @NotEmpty(message = "取引コース(isSelected)")
    private Boolean isSelected;
}
