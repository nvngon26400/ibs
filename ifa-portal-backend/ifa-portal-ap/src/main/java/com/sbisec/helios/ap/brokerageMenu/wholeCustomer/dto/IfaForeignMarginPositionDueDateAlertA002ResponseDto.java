package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import lombok.Data;

/**
*
*
* @author BASE李
*
*/
@Data
public class IfaForeignMarginPositionDueDateAlertA002ResponseDto {
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
    private String butenCode;
    /** 口座番号 */
    private String accountNumber;
    /** コース名 */
    private String courseName;
    /** 顧客名 */
    private String customerName;
    /** 顧客名_姓名(カナ) */
    private String customerNameKana;
    /** 銘柄コード */
    private String brandCode;
    /** 銘柄名 */
    private String brandName;
    /** 約定基準残高 */
    private String contractStandardDeposit;
    /** 直近返済期限 */
    private String recentRepayDeadline;
}
