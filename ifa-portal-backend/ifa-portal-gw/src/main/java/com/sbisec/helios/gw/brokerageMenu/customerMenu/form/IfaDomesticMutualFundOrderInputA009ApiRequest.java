package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 国内投信注文入力A009リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaDomesticMutualFundOrderInputA009ApiRequest {
    
    /** 選択口座. */
    @NotEmpty(message = "選択口座")
    private String selectAccountType;
    
    /** 選択預り区分. */
    @NotEmpty(message = "選択預り区分")
    private String selectDepositType;
    
    /** ファンドコード（回数）（半角英数字）. */
    @NotEmpty(message = "ファンドコード（回数）")
    @Size(min = 4, max = 4, message = "ファンドコード（回数）")
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    @NotEmpty(message = "ファンドコード（号）")
    @Size(min = 3, max = 3, message = "ファンドコード（号）")
    private String fundCodeIssues;
    
    /** 売買区分（投信）. */
    @NotEmpty(message = "売買区分（投信）")
    private String mutualFundSellBuyType;
    
    /** ファンドタイプ（半角英数字）. */
    @NotEmpty(message = "ファンドタイプ")
    @Size(min = 1, max = 1, message = "ファンドタイプ")
    private String fundType;
    
}
