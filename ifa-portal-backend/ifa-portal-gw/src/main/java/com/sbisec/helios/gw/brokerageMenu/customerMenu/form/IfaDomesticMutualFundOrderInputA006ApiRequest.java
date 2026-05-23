package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 国内投信注文入力A006リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaDomesticMutualFundOrderInputA006ApiRequest {
    
    /** 選択口座. */
    @NotEmpty(message = "選択口座")
    private String selectAccountType;
    
    /** ファンドコード（回数）（半角英数字）. */
    @NotEmpty(message = "ファンドコード（回数）")
    @Size(min = 4, max = 4, message = "ファンドコード（回数）")
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    @NotEmpty(message = "ファンドコード（号）")
    @Size(min = 3, max = 3, message = "ファンドコード（号）")
    private String fundCodeIssues;
    
    /** 乗換優遇枠適用. */
    @NotEmpty(message = "乗換優遇枠適用")
    private String transfersPreferentialQuotaApplication;
    
}
