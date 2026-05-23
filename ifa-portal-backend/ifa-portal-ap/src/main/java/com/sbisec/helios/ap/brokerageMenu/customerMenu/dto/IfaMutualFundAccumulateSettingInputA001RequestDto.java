package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 投信積立設定入力 A001 リクエスト
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingInputA001RequestDto {

    /** 協会コード. */
    private String fundCode;

    /** ファンドコード（回数）（半角英数字）. */
    private String mfkaisu;

    /** ファンドコード（号）（半角英数字）. */
    private String mfgo;

    /** 遷移元画面. */
    private String source;

    /** 戻るボタンの元 */
    private boolean listFlag;

    private String step;

}
