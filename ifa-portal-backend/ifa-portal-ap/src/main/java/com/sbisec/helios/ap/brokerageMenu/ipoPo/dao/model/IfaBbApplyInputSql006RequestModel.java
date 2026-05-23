package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyInputSql006RequestModel {
    
    /** 銘柄コード（半角英数字）.※訂正時のみ指定 */
    private String brandCode;
    /** 旧部店. */
    private String oldButen;

    /** 旧口座番号. */
    private String oldAccountNumber;
}
