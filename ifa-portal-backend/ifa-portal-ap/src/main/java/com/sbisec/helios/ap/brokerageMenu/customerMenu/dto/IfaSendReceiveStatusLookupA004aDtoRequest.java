package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaSendReceiveStatusLookupA004aDtoRequest {
    /** キーワード */
    private String keyword;
    /** 書類コード */
    private String paperCd;
}
