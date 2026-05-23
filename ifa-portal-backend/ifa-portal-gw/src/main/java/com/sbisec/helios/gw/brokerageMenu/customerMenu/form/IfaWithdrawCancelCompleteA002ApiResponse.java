package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 出金完了A002レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawCancelCompleteA002ApiResponse {

    /** 出金日として指定可能な直近の日付 */
    private String minPayDate;
}
