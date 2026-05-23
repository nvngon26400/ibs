package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;


import lombok.Data;

@Data
public class IfaSendReceiveStatusLookupA001LookupDtoResponse {
    /** 書類コード */
    private String paperCd;
    /** 書類名 */
    private String paperName;
}
