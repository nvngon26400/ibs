package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * ポイント
 *
 * @author SCSK
 */
@Data
public class IfaDomesticMutualFundOrderInputPoint {
    
    /** ポイント種別（半角英数字）. */
    private String pointClass;
    
    /** ポイント数. */
    private Integer pointCount;
    
    /** うち期間固定ポイント. */
    private Integer fixedPeriodPoint;
    
    /** 最短有効期限. */
    private String shortestExpirationDate;
    
    /** 最低利用ポイント数. */
    private Integer minUsePointCount;
    
    /** 利用ポイント単位. */
    private String usePointUnit;
    
    /** ポイント表示エリア表示可否. */
    private String pointShowAreaFlag;
    
    /** ポイント名表示可否. */
    private String pointNameDisplayFlag;
    
    /** ポイント数表示可否. */
    private String pointCountDisplayFlag;
    
    /** うち期間固定ポイント表示可否. */
    private String fixedPeriodPointDisplayFlag;
    
    /** 最短有効期限表示可否. */
    private String shortestExpirationDateDisplayFlag;
    
    /** ポイント利用エリア表示可否. */
    private String pointUseAreaFlag;
    
}
