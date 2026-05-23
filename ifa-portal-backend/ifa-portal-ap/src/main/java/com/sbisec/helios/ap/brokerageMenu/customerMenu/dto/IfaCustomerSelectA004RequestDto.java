package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * お気に入り登録・解除 リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaCustomerSelectA004RequestDto {
    
    /** 部店. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** お気に入り登録状況（数字）. */
    private String favoRegStatus;
    
    /** 顧客コード（数字）. */
    private String customerCode;
    
}
