package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaForeignAmountUnpaidOverdraftAlertListSql001ResponseModel {
    
    private int totalCount;
    /** 年月日 */
    private String dateYmd;
    /** 仲介業者コード */
    private String brokerCode;
    /** 仲介業者名 */
    private String brokerName;
    /** 仲介業支店コード */
    private String brokerageBranchCode;
    /** 仲介業者支店名 */
    private String brokerBranchName;
    /** 営業員コード */
    private String empCode;
    /** 営業員名 */
    private String brokerChargeName;
    /** 部店 */
    private String buten;
    /** 口座番号 */
    private String accountNumber;
    /** コース名 */
    private String courseName;
    /** 顧客名 */
    private String customerName;
    /** 顧客名_姓名(カナ) */
    private String customerNameKana;
    /** 通貨 */
    private String currency;
    /** 評価額（外貨） */
    private String foreignValuation;
}
