package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 出金実行A001レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawExecuteConfirmA001ApiResponse {

    /** 受付前出金可能金額 */
    private String acBalance;

    /** 受付後出金可能金額 */
    private String acBalanceAfter;

    /** 出金日として指定可能な直近の日付 */
    private String minPayDate;

}
