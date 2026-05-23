package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaCustomerPortalSql003SelectExclusiveResquestModel {
    
    /** 顧客コード（数字）. */
    private String customerCode;
    
    /** メモ(IFA専用)更新日時 */
    private String ifaMemoUpdateDateTime;
    
}
