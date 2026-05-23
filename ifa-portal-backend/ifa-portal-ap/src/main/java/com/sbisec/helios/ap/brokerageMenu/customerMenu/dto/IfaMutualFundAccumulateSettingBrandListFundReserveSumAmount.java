package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 投信積立設定済銘柄一覧 「1カ月あたりの積立金額（概算）」「1年あたりの積立金額（概算）」 レスポンスパラメタ
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount {

    /** 積立金額 */
    private String settingAmount;

    /** 現金決済積立金額 */
    private String cashSettingAmount;

    /** クレカ決済積立金額 */
    private String creditCardSettingAmount;

    /** 特定／一般積立金額 */
    private String normalSettingAmount;

    /** NISA（成長投資枠）積立金額 */
    private String nisaGrowthSettingAmount;

    /** NISA（つみたて投資枠）積立金額 */
    private String nisaReserveSettingAmount;

}
