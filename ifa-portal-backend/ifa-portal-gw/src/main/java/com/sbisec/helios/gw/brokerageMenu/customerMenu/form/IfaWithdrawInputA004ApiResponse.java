package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 出金入力A004レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawInputA004ApiResponse {

    /** 注意情報アラート（全角半角）. */
    private String noticeInfoAlert;

    /** お知らせアラート（全角半角）. */
    private String noticeAlert;
}
