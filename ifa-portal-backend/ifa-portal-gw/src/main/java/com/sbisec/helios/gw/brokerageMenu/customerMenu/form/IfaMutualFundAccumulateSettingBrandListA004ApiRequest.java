package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * 投信積立設定済銘柄一覧 A004 リクエストパラメータ
 *
 * @author nicksen.li
 * 
 */
@Data
@JsonSerialize
public class IfaMutualFundAccumulateSettingBrandListA004ApiRequest {

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

    @NotEmpty(message = "預り区分")
    private String accountType;

    @NotEmpty(message = "決済方法")
    private String paymentMethod;

}
