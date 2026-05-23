package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 出金取消確認A001リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawCancelConfirmA001ApiRequest {

    /** EC入出金番号 */
    private String rpNumber;

    /** 出金額 */
    private String payAmount;

    /** 注意情報アラート（全角半角）. */
    private String noticeInfoAlertConfirm;

    /** お知らせアラート（全角半角）. */
    private String noticeAlertConfirm;
}
