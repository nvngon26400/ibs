package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;
/**
 * 国内投信購入・積立可能一覧積立（銘柄名の取得）のレスポンスパラメタ
 *
 * @author WJL
 * 
 */

@Data
public class IfaDomesticMutualFundBuyAbleListA007ResponseDto {

    /** 銘柄名. */
    private String kyoukaiName;
    
    /** ファンドコード（回数）（半角英数字）. */
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    private String fundCodeIssues;
}
