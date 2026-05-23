package com.sbisec.helios.gw.common.composite.form;

import lombok.Data;

@Data
public class IfaNoticeInfoA002ApiRequest {

    /** 顧客コード. */
    private String customerCode;
    
    /** 部店コード. */
    private String butenCode;
    
    /** 口座番号. */
    private String accountNumber;
    
}
