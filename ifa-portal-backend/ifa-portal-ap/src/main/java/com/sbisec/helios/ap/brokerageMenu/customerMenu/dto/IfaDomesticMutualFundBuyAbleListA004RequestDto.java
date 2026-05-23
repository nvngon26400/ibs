package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 国内投信購入・積立可能一覧 積立 レスポンスパラメタ
 *
 * @author WJL
 * 
 */
@Data
public class IfaDomesticMutualFundBuyAbleListA004RequestDto {
     
    /** ファンドコード（回数）（半角英数字）. */
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    private String fundCodeIssues;
    
}
