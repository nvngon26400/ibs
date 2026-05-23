package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 検索 リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaCustomerSelectX003RequestDto {
    
    /** 部店. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 顧客名（全角半角）. */
    private String customerName;
    
    /** お気に入り. */
    private String favorite;
    
    /** 取引制限有無. */
    private String tradeRestrictionSelect;
    
    /** 顧客名　（条件リスト）（全角半角）. */
    private String customerNameConditionList;

    /** 顧客コード（数字）. */
    private String customerId;
    
}
