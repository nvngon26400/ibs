package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 「ボーナス月設定の年間合計額」情報 レスポンスパラメタ
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingBonusMonthSumAmountInfo {

    /** ボーナス月設定の合計金額 */
    private String bonusSumAmount;

    /** 特定／一般 ボーナス月設定の合計金額 */
    private String bonusSumAmountNormal;

    /** NISA（成長投資枠） ボーナス月設定の合計金額 */
    private String bonusSumAmountNisaGrowth;

    /** NISA（つみたて投資枠） ボーナス月設定の合計金額 */
    private String bonusSumAmountNisaReserve;

}
