package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 出金入力A003レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawInputA003ApiResponse {

    /** 出金可能金額 */
    private String acBalance;

    /** 受付後の出金可能金額 */
    private String acBalanceAfter;

    /** 注意情報アラート（全角半角）. */
    private String noticeInfoAlert;

    /** お知らせアラート（全角半角）. */
    private String noticeAlert;

}
