package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyCorrectConfirmSql012RequestModel {
    /** 部店コード. */
    private String butenCode;
    /** 口座番号. */
    private String accountNumber;
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    /** ブックビルディング申込期間（開始）. */
    private String bookBuildingPresentationFrom;
}
