package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 投信積立設定済銘柄一覧 明細 レスポンスパラメタ
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingBrandListDetail {

    private String id;

    /** 積立設定リスト.協会コード API003 */
    private String fundCode;

    /** 銘柄コード 「回数＋' '(半角スペース)＋号」 SQL001 */
    /** 回数(M_F_KAISU) NRIコードの半角スペース左側。4桁(前ゼロあり) SQL001 */
    /** 号(M_F_GO) NRIコードの半角スペース右側。2桁(前ゼロあり) SQL001 */
    private String brandCode;

    /** 回数(M_F_KAISU) NRIコードの半角スペース左側。4桁(前ゼロあり) SQL001 */
    private String mfkaisu;

    /** 号(M_F_GO) NRIコードの半角スペース右側。2桁(前ゼロあり) SQL001 */
    private String mfgo;

    /** 積立設定リスト.ファンド名 API003 */
    private String fundName;

    /** 積立設定リスト.非特定預り区分 API003 */
    private String accountType;

    /** 積立設定リスト.非特定預り区分名 */
    private String accountTypeName;

    /** 積立設定リスト.決済方法 API003 */
    private String paymentMethod;

    /** 積立設定リスト.決済方法名 */
    private String paymentMethodName;

    /** 明細リスト.積立コース API003 */
    private String courseType;

    /** 積立設定リスト.設定金額 API003 */
    private String settingAmount;

    /** 明細リスト.ボーナス設定 API003 */
    /** 積立設定リスト.ボーナス設定金額 */
    private String settingBonusAmount;
    /** 積立設定リスト.{ボーナス１設定月, ボーナス１設定日, ボーナス２設定月, ボーナス２設定日} */
    private String settingBonusMonthDay;

    /** 「明細リスト.NISA枠ぎりぎり注文」 積立設定リスト.nisa枠ぎりぎり買付区分 API003 */
    private String nisaBarelyBuyingType;

    /** 「明細リスト.課税枠シフト注文」 積立設定リスト.nisa枠超過時買付区分 API003 */
    private String taxShiftType;

    /** 「明細リスト.1ヵ月あたりの積立概算」 積立設定リスト.1ヶ月あたりの設定金額（概算） API003 */
    private String oneMonthSumAmount;

    /** 「明細リスト.次回発注予定日」 積立設定リスト.次回買付日 API003 */
    private String nextReserveDate;

    /** 明細リスト.積立可否 FCT001 FCT003 FCT017 */
    private String accumulateAvailability;

    /** 明細リスト.積立変更可否 FCT001 FCT003 FCT017 API003 */
    private String accumulateChangeAvailability;

    /** 明細リスト.積立解除可否 API003 */
    private String accumulateCancelAvailability;

}
