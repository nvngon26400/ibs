package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 国内投信購入積立可能一覧SQL002要求
 *
 * @author WJL
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaDomesticMutualFundBuyAbleListSql002RequestModel {

    /** ファンドコード（回数）（半角英数字）. */
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    private String fundCodeIssues;
}
