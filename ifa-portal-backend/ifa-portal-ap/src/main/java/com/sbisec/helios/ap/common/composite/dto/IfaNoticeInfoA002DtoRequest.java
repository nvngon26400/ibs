package com.sbisec.helios.ap.common.composite.dto;

import lombok.Data;

/**
 *
 * @author SCSK
 *
 */
@Data
public class IfaNoticeInfoA002DtoRequest {

    /** 顧客コード. */
    private String customerCode;
    
    /** 部店コード. */
    private String butenCode;
    
    /** 口座番号. */
    private String accountNumber;
    
}
