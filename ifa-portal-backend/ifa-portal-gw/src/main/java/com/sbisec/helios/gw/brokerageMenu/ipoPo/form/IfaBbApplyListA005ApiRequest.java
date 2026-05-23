package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyListA005ApiRequest {
    /** 銘柄コード */
    private String brandCode;
    /** ブックビルディング申込期間（開始） */
    private String bookBuildingPresentationFrom;
    /** 部店コード */
    private String butenCode;
    /** 口座番号 */
    private String accountNumber;
}
