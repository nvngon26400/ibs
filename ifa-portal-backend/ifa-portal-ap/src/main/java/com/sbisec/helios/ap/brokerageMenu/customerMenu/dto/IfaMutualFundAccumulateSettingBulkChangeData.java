package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 積立設定変更前(後)データ
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingBulkChangeData {

    /** ファンドコード（回数）（半角英数字）. */
    private String mfkaisu;

    /** ファンドコード（号）（半角英数字）. */
    private String mfgo;

    // 銘柄コード
    private String brandCode;

    /** 投資信託協会コード */
    private String fundCode;

    /** 投資信託協会名（投資信託名） */
    private String fundName;

    /** 決済方法 */
    private String paymentMethod;

    /** 預り区分 */
    private String accountType;

    /** 設定金額 */
    private String settingAmount;

    /** 1ヵ月あたりの設定金額（概算） */
    private String oneMonthSumAmount;

    /** コース区分 */
    private String courseType;

    /** 積立日付 */
    private String settingReserveDay;

    /** 積立隔月設定 */
    private String settingReserveBimonthly;

    /** 積立毎週設定 */
    private String settingReserveWeek;

    /** 積立複数日設定 */
    private String settingReserveMultiDay;

    /** NISA枠ぎりぎり注文 */
    private String nisaBarelyBuyingType;

    /** 課税枠シフト注文 */
    private String taxShiftType;

    /** ボーナス設定有無 */
    private String settingBonusFlag;

    /** ボーナス設定金額 */
    private String settingBonusAmount;

    /** ボーナス１設定月 */
    private String settingBonus1Month;

    /** ボーナス１設定日 */
    private String settingBonus1Day;

    /** ボーナス２設定月 */
    private String settingBonus2Month;

    /** ボーナス２設定日 */
    private String settingBonus2Day;

    /** 次の買付予定日 */
    private String planDate;

    /** ボーナス月次の買付予定日１ */
    private String bonusPlanDate1;

    /** ボーナス月次の買付予定日２ */
    private String bonusPlanDate2;

    /** ボーナス買付予定日. */
    private String bonusPlanDate;

    /** 設定金額概算手数料 */
    private String estimateFundOrder;

    /** ボーナス概算手数料 */
    private String estimateFundOrderBonus;

    /** 設定金額の次回買付日 */
    private String nextReserveDate;

}
