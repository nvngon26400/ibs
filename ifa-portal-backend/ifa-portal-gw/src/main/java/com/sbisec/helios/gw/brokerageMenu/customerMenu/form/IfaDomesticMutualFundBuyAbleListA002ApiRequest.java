package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * 国内投信購入可能一覧  A002 リクエストパラメータ
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaDomesticMutualFundBuyAbleListA002ApiRequest {
    
    /** 売買区分（投信）. */
    @NotEmpty(message = "売買区分（投信）")
    private String mutualFundSellBuyType;
    
    /** ファンドコード（回数）（半角英数字）. */
    @NotEmpty(message = "ファンドコード（回数）")
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    @NotEmpty(message = "ファンドコード（号）")
    private String fundCodeIssues;
    
    /** 預り区分（全角半角）. */
    @NotEmpty(message = "預り区分")
    @Size(max = 20, message = "預り区分")
    private String depositType;
    
}
