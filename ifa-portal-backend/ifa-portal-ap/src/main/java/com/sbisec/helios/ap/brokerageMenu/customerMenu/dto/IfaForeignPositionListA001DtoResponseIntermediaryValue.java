package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 米株建玉一覧 A001 レスポンスパラメータ（媒介可否情報）
 *
 * @author SCSK
 * 
 */
@Data
public class IfaForeignPositionListA001DtoResponseIntermediaryValue {
    
    /** 取引種別 */
    private String tradeClass;
    
    /** 媒介可否. */
    private String intermediaryValue;
    
}
