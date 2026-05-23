package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBonusMonthSumAmountInfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListNextOrderPlan;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingInputA001Response_AccountType;

import lombok.Data;

/**
 * 投信積立設定入力 A001 リスポンス
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingInputA001ApiResponse {
    
    /** 協会コード. */
    private String fundCode;

    /** ファンドコード（回数）（半角英数字）. */
    private String mfkaisu;

    /** ファンドコード（号）（半角英数字）. */
    private String mfgo;

    /** 預り区分. */
    private String accountType;

    /** 預り区分リスト. */
    private List<IfaMutualFundAccumulateSettingInputA001Response_AccountType> accountTypeOptions;

    /** 銘柄名. */
    private String fundName;

    /** 基準価額. */
    private String standardPriceStr;

    /** 基準価額単位. */
    private String standardPriceUnitStr;

    /** 基準価額増減区分. */
    private String tick;

    /** 基準価額対象日付. */
    private String priceDate;

    // 前日比
    /**
     * ■基準価額データがない場合 "-"を表示 ■上記以外 前日比（+は赤、-は青0は黒）
     */
    private String previousChangeStr;
    private String previousChangeSign;

    // 前日比率
    /**
     * ■基準価額増減区分＝NO_CHANGEの場合 "（-%）"を表示 ■上記以外 前日比率+"%"（+は赤、-は青、0は黒）
     */
    private String previousRatio;

    // 純資産
    /**
     * ■純資産=値なし の場合 "-"を表示 
     * ■上記以外 純資産+"百万円"
     */
    private String netAssetStr;

    /** 積立買付単位 */
    private  String reserveOrderUnit;
    /** 最低申込金額 */
    private  String minApplyAmount;
    /** 申込単位 */
    private  String applyUnit;

    /** NISA枠ぎりぎり注文 コメント. */
    private String nisaBarelyBuyingTypeComment;

    /** 課税枠シフト注文 コメント. */
    private String taxShiftTypeComment;

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

    /** 次回発注予定.発注予定日 API001. */
    /** 次回発注予定.発注予定金額 API001. */
    /** 1年あたりの積立金額（概算）.現金決済積立金額 API001. */
    private IfaMutualFundAccumulateSettingBrandListNextOrderPlan nextOrderPlan;

    /** 戻る button */
    private String goBackButton;
    
    /** 旧ジュニアNISA口座開設有無. */
    private String openedJnisa;

    /** アクションID */
    private String goToPageActionId;

    /** 戻るボタンの元 */
    private boolean listFlag;

    private String source;
    private String step;

}
