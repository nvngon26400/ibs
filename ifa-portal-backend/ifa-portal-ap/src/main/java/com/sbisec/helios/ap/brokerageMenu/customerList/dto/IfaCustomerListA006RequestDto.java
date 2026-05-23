package com.sbisec.helios.ap.brokerageMenu.customerList.dto;

import lombok.Data;

/**
 * 顧客一覧_基本 BB申込 リクエストパラメータ
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaCustomerListA006RequestDto {
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 営業員コード（半角英数字）. */
    private String empCode;
    
}
