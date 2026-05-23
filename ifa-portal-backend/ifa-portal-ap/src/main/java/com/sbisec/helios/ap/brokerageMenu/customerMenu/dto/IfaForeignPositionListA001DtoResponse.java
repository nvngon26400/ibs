package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 米株建玉一覧 A001 レスポンスパラメータ
 *
 * @author SCSK
 * 
 */
@Data
public class IfaForeignPositionListA001DtoResponse {
    
    /** 建玉一覧リスト. */
    private List<IfaForeignPositionListA001DtoResponsePositionList> positionListList;
    
    /** 媒介可否リスト. */
    private List<IfaForeignPositionListA001DtoResponseIntermediaryValue> intermediaryValueList;
    
    /** 取引停止フラグ. */
    private String tradeSuspendFlag;
    
}
