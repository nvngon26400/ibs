package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * 検索 リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaCustomerSelectX003ApiRequest {
    
    /** 部店. */
    @NotEmpty(message = "部店")
    private String butenCode;
    
    /** 口座番号（数字）. */
    @NotEmpty(message = "口座番号")
    @Pattern(regexp = "0-9", message = "口座番号")
    @Size(max = 7, message = "口座番号")
    private String accountNumber;
    
    /** 顧客名（全角半角）. */
    @NotEmpty(message = "顧客名")
    private String customerName;
    
    /** お気に入り. */
    @NotEmpty(message = "お気に入り")
    private String favorite;
    
    /** 取引制限有無. */
    @NotEmpty(message = "取引制限有無")
    private String tradeRestrictionSelect;
    
    /** 顧客名　（条件リスト）（全角半角）. */
    @NotEmpty(message = "顧客名　（条件リスト）")
    private String customerNameConditionList;
    
    /** 顧客コード（数字）. */
    @NotEmpty(message = "顧客コード")
    @Pattern(regexp = "0-9", message = "顧客コード")
    @Size(max = 9, message = "顧客コード")
    private String customerId;
    
}
