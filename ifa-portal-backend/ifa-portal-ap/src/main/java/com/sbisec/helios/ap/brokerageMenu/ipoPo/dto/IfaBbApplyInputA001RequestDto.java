package com.sbisec.helios.ap.brokerageMenu.ipoPo.dto;



import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data

public class IfaBbApplyInputA001RequestDto {
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** ブックビルディング申込期間（開始）. */
    private String bookBuildingPresentationFrom;
}
