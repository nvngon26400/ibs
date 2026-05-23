package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * 外国現物取引注文入力 外部リンク
 * 2026/02/09 新規作成
 *
 * @author 大連 葉
 */
@Data
@JsonSerialize
public class IfaForeignSpotTradeOrderInputA021ApiRequest {

    // URLID
    private String urlId;

    // パラメータパターンID
    private String patternId;

    // HTTPメソッド
    private String httpMethod;

    // 銘柄コード
    private String brandCode;
}
