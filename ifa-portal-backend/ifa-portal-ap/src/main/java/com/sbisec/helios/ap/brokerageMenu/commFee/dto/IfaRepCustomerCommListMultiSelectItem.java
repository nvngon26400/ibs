package com.sbisec.helios.ap.brokerageMenu.commFee.dto;

import lombok.Data;

/**
 * 担当顧客別手数料一覧複数選択項目
 *
 * @author 宇田川達弥
 */
@Data
public class IfaRepCustomerCommListMultiSelectItem {
    
    /** id */
    private String id;
    
    /** isSelected */
    private Boolean isSelected;
}
