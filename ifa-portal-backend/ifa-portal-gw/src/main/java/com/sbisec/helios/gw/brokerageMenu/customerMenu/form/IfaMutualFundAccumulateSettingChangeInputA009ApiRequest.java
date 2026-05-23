package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 投信積立設定変更入力 A009 リクエストパラメータ
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingChangeInputA009ApiRequest {

    @NotEmpty(message = "協会コード")
    @Size(max = 20, message = "協会コード")
    private String fundCode;

    @NotEmpty(message = "ファンドコード（回数）")
    private String mfkaisu;

    @NotEmpty(message = "ファンドコード（号）")
    private String mfgo;

    @NotEmpty(message = "決済方法")
    private String paymentMethod;

    @NotEmpty(message = "預り区分")
    private String accountType;

    @NotEmpty(message = "設定金額")
    private String settingAmount;

    // ■リクエスト.コース＝毎日の場合
    // "1"
    // ■リクエスト.コース＝毎週の場合
    // "2"
    // ■リクエスト.コース＝毎月の場合
    // "3"
    // ■リクエスト.コース＝複数日の場合
    // "4"
    // ■リクエスト.コース＝奇数月、偶数月の場合
    // "5"
    @NotEmpty(message = "コース区分")
    private String courseType;

    /** 積立日付. */
    // ■コース区分＝毎月、奇数月、偶数月の場合
    // 設定日
    private String settingReserveDay;

    /** 積立隔月設定. */
    // ■リクエスト.コース＝奇数月の場合
    // "1"
    // ■リクエスト.コース＝偶数月の場合
    // "2"
    private String settingReserveBimonthly;

    /** 積立毎週設定. */
    // ■リクエスト.コース＝毎週の場合
    // ■リクエスト.設定日＝月の場合
    // "1"
    // ■リクエスト.設定日＝火の場合
    // "2"
    // ■リクエスト.設定日＝水の場合
    // "3"
    // ■リクエスト.設定日＝木の場合
    // "4"
    // ■リクエスト.設定日＝金の場合
    // "5"
    private String settingReserveWeek;

    /** 積立複数日設定. */
    // ■リクエスト.コース＝複数日の場合
    // リクエスト.設定日
    private String settingReserveMultiDay;

    // 旧ジュニアNISA口座開設有無
    /** 旧ジュニアNISA口座開設有無. */
    private String openedJnisa;

    /** 積立買付単位 */
    private  String reserveOrderUnit;

    // NISA枠ぎりぎり注文
    private String nisaBarelyBuyingType;
    private String nisaBarelyBuyingTypeShow;

    // 課税枠シフト注文チェック
    // 課税枠シフト注文
    private String taxShiftType;
    private String taxShiftTypeShow;

    // ボーナス設定有無
    // ボーナス設定有無
    private String settingBonusFlag;

    // ボーナス設定金額
    private String settingBonusAmount;
    // ボーナス１設定月
    private String settingBonus1Month;
    // ボーナス１設定日
    private String settingBonus1Day;
    // ボーナス２設定月
    private String settingBonus2Month;
    // ボーナス２設定日
    private String settingBonus2Day;

    /** 勧誘区分（全角半角）. */
    @NotEmpty(message = "勧誘区分")
    @Size(max = 2, message = "勧誘区分")
    private String kanyuKbn;

    /** 受注方法. */
    @NotEmpty(message = "受注方法")
    private String receiveMethod;

    @NotEmpty(message = "注意事項 説明済確認")
    @Size(min = 1, max = 1, message = "注意事項 説明済確認")
    private String checkMadoAki;

}
