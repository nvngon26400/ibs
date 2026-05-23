package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 買付余力（国内） A001リクエストDto

 * @author 松田
 *
 */
@Data
public class IfaBuyingPowerDomesticA001RequestDto {

    /** 部店コード（半角英数字）. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;

}
