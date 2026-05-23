package com.sbisec.helios.ap.brokerageMenu.customerList.dto;

import lombok.Data;

/**
 * 印影照会
 *
 * @author SCSK
 * 
 */
@Data
public class IfaImprintInquiryA001RequestDto {

    /** 部店コード（半角英数字） */
    private String butenCode;

    /** 口座番号（数字） */
    private String accountNumber;

}
