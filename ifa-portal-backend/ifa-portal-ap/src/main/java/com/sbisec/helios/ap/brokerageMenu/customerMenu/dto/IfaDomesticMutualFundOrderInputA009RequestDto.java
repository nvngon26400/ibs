package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 国内投信注文入力A009リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaDomesticMutualFundOrderInputA009RequestDto {
    
    /** 選択口座. */
    private String selectAccountType;
    
    /** 選択預り区分. */
    private String selectDepositType;
    
    /** ファンドコード（回数）（半角英数字）. */
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    private String fundCodeIssues;
    
    /** 売買区分（投信）. */
    private String mutualFundSellBuyType;
    
    /** ファンドタイプ（半角英数字）. */
    private String fundType;
    
}
