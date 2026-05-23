package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 出金取消完了A002レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawCancelCompleteA002ResponseDto {

    /** 出金日として指定可能な直近の日付 */
    private String minPayDate;
}
