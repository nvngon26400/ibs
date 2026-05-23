package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 国内投信注文入力SQL003要求
 *
 * @author　SCSK久保
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaDomesticMutualFundOrderInputSql003RequestModel {

    /** ファンドコード（回数）（半角英数字）. */
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    private String fundCodeIssues;
    
    /** ファンドタイプ（半角数字）. */
    private String fundType;
    
}
