package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 出金入力A004レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawInputA004ResponseDto {

    /** 注意情報アラート（全角半角）. */
    private String noticeInfoAlert;

    /** お知らせアラート（全角半角）. */
    private String noticeAlert;
}
