package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 国内投信購入・積立可能一覧  A007 リクエストパラメータ
 *
 * @author WJL
 * 
 */
@Data
public class IfaDomesticMutualFundBuyAbleListA007ApiRequest {

    /** ファンドコード（回数）（半角英数字）. */
    @NotEmpty(message = "ファンドコード（回数）")
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    @NotEmpty(message = "ファンドコード（号）")
    private String fundCodeIssues;
    
}
