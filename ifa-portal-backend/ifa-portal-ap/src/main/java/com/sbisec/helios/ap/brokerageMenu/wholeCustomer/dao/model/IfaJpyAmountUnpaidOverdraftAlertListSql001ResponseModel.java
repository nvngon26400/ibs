package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;
import lombok.Data;

/**
*
*
* @author BASE李
*
*/
@Data
public class IfaJpyAmountUnpaidOverdraftAlertListSql001ResponseModel {
    
    /** 総件数 */
    private int totalCount; 
    /** 受渡日(1) */
    private String stlDate0;
    /** 仲介業者コード */
    private String brokerCode;
    /** 仲介業者営業員コード */
    private String brokerChargeCode;
    /** 部店コード */
    private String butenCode;
    /** 口座番号 */
    private String accountNumber;
    /** 預り金赤残(1) */
    private String chargeAmount0;
    /** 仲介業者支店コード */
    private String subBrokerId;
    /** 契約締結前交付書面コード名 */
    private String customerAttributeName;
    /** 顧客名_姓名(漢字) */
    private String customerNameKanji;
    /** 顧客名_姓名(カナ) */
    private String customerNameKana;
    /** 仲介業者支店名 */
    private String brokerBranchName;
    /** 仲介業者担当者名 */
    private String employeeName;
    /** 仲介業者名 */
    private String brokerName;
}
