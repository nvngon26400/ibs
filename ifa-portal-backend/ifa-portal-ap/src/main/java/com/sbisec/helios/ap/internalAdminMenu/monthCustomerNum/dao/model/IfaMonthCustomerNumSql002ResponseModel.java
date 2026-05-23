package com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0407_01
 * 画面名：月末口座数
 *
 * @author SBI大連 チョウ
   2025/05/22 新規作成
 */
@Data
public class IfaMonthCustomerNumSql002ResponseModel {
    
    /** 総件数. */
    private int totalRow;
    
    /** 年月 */
    private String dateYm;
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者名 */
    private String brokerName;
    
    /** 月末口座数 */
    private String accountNum;
    
    /** ダウンロード可否区分*/
    private String downloadFlg;
}
