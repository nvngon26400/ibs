package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 米株建玉一覧 A001 レスポンスパラメータ（媒介可否情報）
 *
 * @author SCSK
 * 
 */
@Data
public class IfaForeignPositionListA001ApiResponseIntermediaryValue {
    
    /** 媒介可否. */
    private String intermediaryvalue;
    
}
