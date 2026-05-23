package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * 投信積立設定済銘柄一覧 追加A003 (投信積立設定入力)のレスポンスパラメタ
 *
 * @author nicksen.li
 * 
 */
@Data
@JsonSerialize
public class IfaMutualFundAccumulateSettingBrandListA003ResponseDto {

    /** 積立設定リスト.協会コード API003 */
    private String fundCode;

    /** 回数(M_F_KAISU) NRIコードの半角スペース左側。4桁(前ゼロあり) SQL001 */
    private String mfkaisu;

    /** 号(M_F_GO) NRIコードの半角スペース右側。2桁(前ゼロあり) SQL001 */
    private String mfgo;
}
