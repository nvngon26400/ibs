package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * 外国現物取引注文確認
 * 2026/02/06 新規作成
 *
 * @author 大連 趙
 */
@Data
@JsonSerialize
public class IfaForeignSpotTradeOrderConfirmA019ApiRequest {

    // URLID
    private String urlId;

    // パラメータパターンID
    private String patternId;

    // HTTPメソッド
    private String httpMethod;

    // 銘柄コード
    private String brandCode;
}
