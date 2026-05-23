package com.sbisec.helios.ap.bizcommon.dao.model;

import lombok.Data;

@Data
public class Fct003Sql001ResponseModel {
    
    //部店コード
    private String butenCode;
    
    //口座番号
    private String accountNumber;
    
    //仲介業者コード
    private String brokerCode;
    
    //契約締結前交付書面コード
    private String customerAttribute;
}
