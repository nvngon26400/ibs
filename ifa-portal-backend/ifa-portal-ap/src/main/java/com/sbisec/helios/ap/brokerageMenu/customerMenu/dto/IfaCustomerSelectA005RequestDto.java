package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 顧客選択 リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaCustomerSelectA005RequestDto {
    
    /** 部店. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
}
