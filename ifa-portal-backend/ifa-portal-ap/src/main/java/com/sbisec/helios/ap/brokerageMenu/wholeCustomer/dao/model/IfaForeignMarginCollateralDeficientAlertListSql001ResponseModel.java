package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;


import lombok.Data;

/**
*
*
* @author BASE李
*
*/
@Data
public class IfaForeignMarginCollateralDeficientAlertListSql001ResponseModel {
    
    /** 総件数 */
    private int totalCount;
    
    /** 基準日 */
    private String standardDate;

    /** 仲介業者コード */
    private String brokerCode;

    /** 仲介業者支店名 */
    private String brokerBranchName;

    /** 仲介業者支店コード */
    private String subBrokerId;

    /** 仲介業者営業員コード */
    private String brokerChargeCode;

    /** 仲介業者担当者名 */
    private String employeeName;

    /** 部店 */
    private String buten;

    /** 口座番号 */
    private String accountNumber;

    /** 契約締結前交付書面コード名 */
    private String customerAttributeName;

    /** 顧客名_姓名(漢字) */
    private String customerNameKanji;

    /** 顧客名_姓名(カナ) */
    private String customerNameKana;

    /** 通貨 */
    private String currency;

    /** 受入保証金 */
    private String acceptDeposit;

    /** 維持率 */
    private String maintenanceRate;

    /** 追証請求額 */
    private String marginDemandAmount;
    /** 仲介業者名 */
    private String brokerName;
}
