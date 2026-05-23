package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyInputA001ApiRequest {
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** ブックビルディング申込期間（開始）. */
    private String bookBuildingPresentationFrom;
}
