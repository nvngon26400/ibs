package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaCustomerPortalSql003UpdateRequestModel {
    
    /** 顧客コード（数字）. */
    private String customerCode;
    
    /** メモ(IFA専用)内容（全角半角）. */
    private String ifaMemoContent;
    
    /** メモ(IFA専用)更新日時 */
    private String ifaMemoUpdateDateTime;
    
    /** ユーザーID */
    private String userId;
    
}
