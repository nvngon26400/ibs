package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 投信積立設定変更入力 A001 リクエスト
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingChangeInputA001RequestDto {

    /** 協会コード. 投信積立設定済一覧明細エリア.協会コード */
    private String fundCode;

    /** ファンドコード（回数）（半角英数字）. hiddenエリア.ファンドコード（回数） */
    private String mfkaisu;

    /** ファンドコード（号）（半角英数字）. hiddenエリア.ファンドコード（号） */
    private String mfgo;

    /** 預り区分. 投信積立設定済一覧明細エリア.預り区分 */
    private String accountType;

    /** 決済方法. 投信積立設定済一覧明細エリア.決済方法 */
    private String paymentMethod;

}
