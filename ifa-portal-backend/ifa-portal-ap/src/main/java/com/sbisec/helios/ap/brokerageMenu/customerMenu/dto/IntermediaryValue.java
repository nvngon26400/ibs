package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 媒介可否リスト
 *
 * @author SCSK
 */
@Data
public class IntermediaryValue {
    
    /** 取引種別 */
    private String tradeClass;
    
    /** 媒介可否. */
    private String intermediaryValue;
    
}
