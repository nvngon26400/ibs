package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaCustomerPortalSql003InsertRequestModel {
    
    /** 顧客コード（数字）. */
    private String customerCode;
    
    /** メモ(IFA専用)内容（全角半角）. */
    private String ifaMemoContent;
    
    /** ユーザーID */
    private String userId;
    
}
