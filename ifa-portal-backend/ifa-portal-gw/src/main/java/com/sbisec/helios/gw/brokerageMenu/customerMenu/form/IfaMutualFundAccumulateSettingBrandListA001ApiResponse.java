package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBonusMonthSumAmountInfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListNextOrderPlan;

import lombok.Data;

/**
 * 投信積立設定済銘柄一覧  A001 レスポンスパラメータ
 *
 * @author nicksen.li
 * 
 */
@Data
public class IfaMutualFundAccumulateSettingBrandListA001ApiResponse {

    /** 1ヶ月あたりの積立金額の概算 コメント. */
    private String oneMonthComment;

    /** 1年あたりの積立金額の概算 コメント. */
    private String oneYearComment;

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

    /** 積立注文ポイント利用の設定. */
    private String pointSetting;
    /** ポイント利用設定 API002. */
    private String pointUseType;
    /** ポイント利用上限 API002. */
    private String pointUseUpperLimit;

    /** 次回発注予定.発注予定日 API001. */
    /** 次回発注予定.発注予定金額 API001. */
    /** 1年あたりの積立金額（概算）.現金決済積立金額 API001. */
    private IfaMutualFundAccumulateSettingBrandListNextOrderPlan nextOrderPlan;

    /** 検索結果総数. */
    private String hitNumber;

    /** 明細リスト. */
    private List<IfaMutualFundAccumulateSettingBrandListDetail> detailList;
}
