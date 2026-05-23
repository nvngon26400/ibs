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
public class IfaCustomerPortalSql007ResponseModel {

    /** 顧客名_姓名(カナ) */
    private String nameKana;

    /** 郵便番号(前)(後)（半角英数字）. */
    private String zipCode;
    
    /** 住所(漢字)（全角半角）. */
    private String addressKanji1;
    
    /** 住所(カナ)（全角半角）. */
    private String addressKana1;
    
    /** 性別 */
    private String sexKbn;
}
