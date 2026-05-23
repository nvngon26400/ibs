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
public class IfaCustomerPortalSql009ResponseModel {

    /** 閲覧可能部店コード */
    private String visibleButenCode;
    
    /** ログインＩＤ・取引ＰＷの配布状況 */
    private String idPwFlg;
    
    /** 社員営業員（ＣＲ）コード */
    private String empCrCd;
    
    /** 社員営業員（ＣＳ）コード */
    private String empCsCd;
    
    /** CR担当者名 */
    private String empCrName;
    
    /** CS担当者名 */
    private String empCsName;
}
