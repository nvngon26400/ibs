package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * 米株信用取引返済注文確認 外部リンク
 * 2026/02/09 新規作成
 *
 * @author 大連 葉
 */
@Data
@JsonSerialize
public class IfaForeignMarginTradeRepayOrderConfirmA012ApiRequest {

    // URLID
    private String urlId;

    // パラメータパターンID
    private String patternId;

    // HTTPメソッド
    private String httpMethod;

    // 銘柄コード
    private String brandCode;
}
