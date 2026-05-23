package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 出金実行A001レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawExecuteConfirmA001ResponseDto {

    /** 受付前出金可能金額 */
    private String acBalance;

    /** 受付後出金可能金額 */
    private String acBalanceAfter;

}
