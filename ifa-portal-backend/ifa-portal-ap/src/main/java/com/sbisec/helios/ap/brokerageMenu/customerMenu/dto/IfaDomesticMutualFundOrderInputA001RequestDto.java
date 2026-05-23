package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 国内投信注文入力A001リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaDomesticMutualFundOrderInputA001RequestDto {
    
    /** 売買区分（投信）. */
    private String mutualFundSellBuyType;
    
    /** ファンドコード（回数）（半角英数字）. */
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    private String fundCodeIssues;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 目論見書チェック区分. */
    private String dispatchId;
    
}
