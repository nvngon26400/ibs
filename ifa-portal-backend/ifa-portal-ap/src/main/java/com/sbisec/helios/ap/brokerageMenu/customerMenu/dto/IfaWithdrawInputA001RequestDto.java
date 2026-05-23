package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 出金入力A001リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawInputA001RequestDto {

    /** 出金日として指定可能な直近の日付 */
    private String minPayDate;
}
