package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaMarginPowerDomesticA001DtoRequest {

    /** 部店コード（半角英数字）. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;

}
