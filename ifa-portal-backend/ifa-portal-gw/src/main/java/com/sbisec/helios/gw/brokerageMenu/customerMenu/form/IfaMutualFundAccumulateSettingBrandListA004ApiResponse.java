package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 投信積立設定済銘柄一覧  A001 レスポンスパラメータ
 *
 * @author nicksen.li
 * 
 */
@Data
public class IfaMutualFundAccumulateSettingBrandListA004ApiResponse {

    /** 積立設定リスト.協会コード API003 */
    private String fundCode;

    /** 回数(M_F_KAISU) NRIコードの半角スペース左側。4桁(前ゼロあり) SQL001 */
    private String mfkaisu;

    /** 号(M_F_GO) NRIコードの半角スペース右側。2桁(前ゼロあり) SQL001 */
    private String mfgo;

    /** 積立設定リスト.非特定預り区分 API003 */
    private String accountType;

    /** 積立設定リスト.決済方法 API003 */
    private String paymentMethod;

}
