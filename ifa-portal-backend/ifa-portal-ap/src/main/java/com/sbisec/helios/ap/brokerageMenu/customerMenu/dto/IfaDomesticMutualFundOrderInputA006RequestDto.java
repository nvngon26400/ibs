package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 国内投信注文入力A006リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaDomesticMutualFundOrderInputA006RequestDto {
    
    /** 選択口座. */
    private String selectAccountType;
    
    /** ファンドコード（回数）（半角英数字）. */
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    private String fundCodeIssues;
    
    /** 乗換優遇枠適用. */
    private String transfersPreferentialQuotaApplication;
    
}
