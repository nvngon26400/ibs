package com.sbisec.helios.gw.brokerageMenu.customerList.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 顧客一覧_基本 BB申込 リクエストパラメータ
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaCustomerListA006ApiRequest {
    
    /** 部店コード（半角英数字）. */
    @NotEmpty(message = "部店コード")
    @Size(min = 3, max = 3, message = "部店コード")
    private String butenCode;
    
    /** 口座番号（数字）. */
    @NotEmpty(message = "口座番号")
    @Pattern(regexp = "0-9", message = "口座番号")
    @Size(max = 7, message = "口座番号")
    private String accountNumber;
    
    /** 仲介業者コード（数字）. */
    @NotEmpty(message = "仲介業者コード")
    @Pattern(regexp = "0-9", message = "仲介業者コード")
    @Size(max = 4, message = "仲介業者コード")
    private String brokerCode;
    
    /** 営業員コード（半角英数字）. */
    @NotEmpty(message = "営業員コード")
    @Size(min = 4, max = 4, message = "営業員コード")
    private String empCode;
    
}
