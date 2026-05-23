package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyCorrectConfirmSql004ResponseModel {
    /** 可能回数. */
    private String ableTimes;

    /** 本年の年間裁量配分割当回数（数値(整数)）. */
    private String discretionAlloCount;

    /** 本年の年間裁量配分割当上限数. */
    private String discretionAlloUpperLimit;

    /** 移管前裁量配分割当回数. */
    private String beforeTransferDiscretionAlloCount;

    /** 旧部店. */
    private String oldButen;

    /** 旧口座番号. */
    private String oldAccountNumber;
}
