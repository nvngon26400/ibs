package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 国内投信注文入力A005リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaDomesticMutualFundOrderInputA005ApiRequest {
    
    /** 売買区分（投信）. */
    @NotEmpty(message = "売買区分（投信）")
    private String mutualFundSellBuyType;
    
    /** ファンドコード（回数）（半角英数字）. */
    @NotEmpty(message = "ファンドコード（回数）")
    @Size(min = 4, max = 4, message = "ファンドコード（回数）")
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    @NotEmpty(message = "ファンドコード（号）")
    @Size(min = 3, max = 3, message = "ファンドコード（号）")
    private String fundCodeIssues;
    
    /** 預り区分（全角半角）. */
    @NotEmpty(message = "預り区分")
    @Size(max = 20, message = "預り区分")
    private String depositType;
    
}
