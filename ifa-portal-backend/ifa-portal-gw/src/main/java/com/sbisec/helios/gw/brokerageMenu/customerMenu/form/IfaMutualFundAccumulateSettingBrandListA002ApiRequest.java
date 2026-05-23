package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * 投信積立設定済銘柄一覧 A002 リクエストパラメータ
 *
 * @author nicksen.li
 * 
 */
@Data
@JsonSerialize
public class IfaMutualFundAccumulateSettingBrandListA002ApiRequest {

    @NotEmpty(message = "協会コード")
    @Size(max = 8, message = "協会コード")
    private String fundCode;

    /** ファンドコード（回数）（半角英数字）. */
    @NotEmpty(message = "ファンドコード（回数）")
    @Size(min = 4, max = 4, message = "ファンドコード（回数）")
    private String mfkaisu;
    
    /** ファンドコード（号）（半角英数字）. */
    @NotEmpty(message = "ファンドコード（号）")
    @Size(min = 3, max = 3, message = "ファンドコード（号）")
    private String mfgo;

    private String source;
    private boolean listFlag;
    private String step;

}
