package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0202_00-02
 * 画面名：顧客ポータル_顧客情報
 * 2025/2/20 新規作成
 * 
 * @author lianhua.xia
 */

@Data
public class IfaCustomerPortalSql010ResponseModel {

    /** ユーザーネーム（ログインID） */
    private String userName;
    
    /** 手数料区分 */
    private String commissionId;
    
    /** 新規手数料区分 */
    private String newCommissionId;
    
    /** 新規手数料適用日付 */
    private String newCommissionDate;
}
