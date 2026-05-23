package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0104-01
 * 画面名：注文状況一覧
 *
 * @author 齋藤
 *
 *          2023/10/16 新規作成
 */

@Data
public class IfaOrderStatusListA004DtoRequest {

    /** 管理番号（半角英数字）. */
    private String manageNumber;

    /** 売買区分（全角半角）. */
    private String tradeKbn;

}
