package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 投信積立設定一括変更入力 A001 リスポンス
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingBulkChangeInputA001ResponseDto {

    /** 1カ月あたりの積立金額（概算）.積立金額 API001. */
    /** 1カ月あたりの積立金額（概算）.現金決済積立金額 API001. */
    /** 1カ月あたりの積立金額（概算）.クレカ決済積立金額 API001. */
    /** 1カ月あたりの積立金額（概算）.特定／一般積立金額 API001. */
    /** 1カ月あたりの積立金額（概算）.NISA（成長投資枠）積立金額 API001. */
    /** 1カ月あたりの積立金額（概算）.NISA（つみたて投資枠）積立金額 API001. */
    private IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount oneMonthSumAmount;

    /** 「1カ月あたりの積立金額（概算）.NISA（つみたて投資枠）上限金額」 1ヵ月クレカの積立設定上限金額 API001. */
    private String oneMonthLimitNisaReserveAmount;

    /** 1年あたりの積立金額（概算）.積立金額 API001. */
    /** 1年あたりの積立金額（概算）.現金決済積立金額 API001. */
    /** 1年あたりの積立金額（概算）.クレカ決済積立金額 API001. */
    /** 1年あたりの積立金額（概算）.特定／一般積立金額 API001. */
    /** 1年あたりの積立金額（概算）.NISA（成長投資枠）積立金額 API001. */
    /** 1年あたりの積立金額（概算）.NISA（つみたて投資枠）積立金額 API001. */
    private IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount oneYearSumAmount;

    /** 「1年あたりの積立金額（概算）.NISA（つみたて投資枠）上限金額」 1年あたりのNISA（つみたて投資枠）設定金額上限 API001. */
    private String oneYearLimitNisaReserveAmount;


    /** ボーナス月設定の合計金額 API001. */
    private String bonusSumAmount;

    /** 「ボーナス月設定の年間合計額」情報 */
    private IfaMutualFundAccumulateSettingBonusMonthSumAmountInfo bonusMonthSettingInfo;

    // NISA（つみたて投資枠）ボーナス月設定の合計金額
    private String bonusSumAmountNisaReserve;

    /** 積立注文ポイント利用の設定. */
    private String pointSetting;
    /** ポイント利用設定 API002. */
    private String pointUseType;
    /** ポイント利用上限 API002. */
    private String pointUseUpperLimit;

    /** API003 ジュニアNISA口座開設有無. */
    private String openedJnisa;

    // API003 積立設定リスト
    List<IfaMutualFundAccumulateSettingBulkChangeData> bulkChangeList;

}
