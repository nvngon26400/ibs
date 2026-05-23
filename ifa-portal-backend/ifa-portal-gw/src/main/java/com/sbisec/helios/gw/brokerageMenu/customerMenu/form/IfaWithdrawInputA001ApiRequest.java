package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 出金入力A001リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawInputA001ApiRequest {

    /** 出金日として指定可能な直近の日付 */
    private String minPayDate;
}
