package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 出金実行A001リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawExecuteConfirmA001RequestDto {

    /** 出金日 */
    private String payDate;

    /** 出金額 */
    private String payAmount;

    /** 注意情報アラート（全角半角）. */
    private String noticeInfoAlertConfirm;

    /** お知らせアラート（全角半角）. */
    private String noticeAlertConfirm;
}
