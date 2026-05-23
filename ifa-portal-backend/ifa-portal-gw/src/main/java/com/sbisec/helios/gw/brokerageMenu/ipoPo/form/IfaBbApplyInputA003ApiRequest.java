package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyInputA003ApiRequest {
    /** 部店コード */
    private String butenCode;
    /** 口座番号 */
    private String accountNumber;
    /** 銘柄コード */
    private String brandCode;
}
