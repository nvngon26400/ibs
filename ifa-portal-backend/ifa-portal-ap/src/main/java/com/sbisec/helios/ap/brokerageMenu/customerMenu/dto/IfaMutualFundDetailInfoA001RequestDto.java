package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 投信詳細情報リクエストDTO
 *
 * @author SCSK
 *
 */
@Data
public class IfaMutualFundDetailInfoA001RequestDto {
    
    /** ファンドコード（回数）（半角英数字）. */
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    private String fundCodeIssues;
    
}
