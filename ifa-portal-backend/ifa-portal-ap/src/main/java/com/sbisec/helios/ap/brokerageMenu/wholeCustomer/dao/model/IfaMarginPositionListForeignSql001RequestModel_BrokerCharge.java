package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

@Data
public class IfaMarginPositionListForeignSql001RequestModel_BrokerCharge {
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 営業員コード */
    private String empCode;
}
