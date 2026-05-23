package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

/**
 * 米株建玉一覧 A001 レスポンスパラメータ
 *
 * @author SCSK
 * 
 */
@Data
public class IfaForeignPositionListA001ApiResponse {
    
    /** 建玉一覧リスト. */
    private List<IfaForeignPositionListA001ApiResponsePositionList> positionListList;
    
    /** 媒介可否リスト. */
    private List<IfaForeignPositionListA001ApiResponseIntermediaryValue> intermediaryvalueList;
    
    /** 取引停止フラグ. */
    private String tradeSuspendFlag;
    
}
