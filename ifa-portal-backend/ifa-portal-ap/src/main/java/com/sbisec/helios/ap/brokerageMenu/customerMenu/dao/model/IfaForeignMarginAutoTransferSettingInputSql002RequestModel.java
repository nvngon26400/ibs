package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaForeignMarginAutoTransferSettingInputSql002RequestModel {
    
    /** 顧客共通情報.部店コード. */
    private String butenCode;
    
    /** 顧客共通情報.口座番号. */
    private String accountNumber;
    
    /** 遡る時間（分）. */
    private Integer timeInterval;
    
}
