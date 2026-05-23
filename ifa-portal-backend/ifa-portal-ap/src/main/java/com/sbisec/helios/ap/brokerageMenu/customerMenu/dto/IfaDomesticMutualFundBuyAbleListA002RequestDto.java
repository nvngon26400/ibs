package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 国内投信購入可能一覧 乗換優先限度額 レスポンスパラメタ
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaDomesticMutualFundBuyAbleListA002RequestDto {
    
    /** 売買区分（投信）. */
    private String mutualFundSellBuyType;
    
    /** ファンドコード（回数）（半角英数字）. */
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    private String fundCodeIssues;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
}
