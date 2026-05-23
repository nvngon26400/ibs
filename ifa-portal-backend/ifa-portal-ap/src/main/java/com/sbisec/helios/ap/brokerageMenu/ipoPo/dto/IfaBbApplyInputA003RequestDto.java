package com.sbisec.helios.ap.brokerageMenu.ipoPo.dto;

import lombok.Data;


/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyInputA003RequestDto {
    /** 部店コード */
    private String butenCode;
    /** 口座番号 */
    private String accountNumber;
    /** 銘柄コード */
    private String brandCode;

}
