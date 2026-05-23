package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 投信積立設定入力 A010 リクエスト
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingInputA010RequestDto {

    /** 協会コード. */
    private String fundCode;

    /** ファンドコード（回数）（半角英数字）. */
    private String mfkaisu;

    /** ファンドコード（号）（半角英数字）. */
    private String mfgo;

    /** 決済方法. */
    private String paymentMethod;

    /** 預り区分. */
    private String accountType;

    /** 設定金額. */
    private String settingAmount;

    /** コース. */
    /**
     * ■リクエスト.コース＝毎日の場合 "1" 
     * ■リクエスト.コース＝毎週の場合 "2" 
     * ■リクエスト.コース＝毎月の場合 "3"
     * ■リクエスト.コース＝複数日の場合 "4" 
     * ■リクエスト.コース＝奇数月、偶数月の場合 "5"
     */
    private String courseType;

    /** 設定日. */
    /**
     * ■コース区分＝毎月、奇数月、偶数月の場合 設定日
     */
    private String settingReserveDay;

    /** 積立隔月設定. */
    /**
     * ■リクエスト.コース＝奇数月の場合 "1" 
     * ■リクエスト.コース＝偶数月の場合 "2"
     */
    private String settingReserveBimonthly;

    /** 積立毎週設定. */
    /**
     * ■リクエスト.コース＝毎週の場合 
     * ■リクエスト.設定日＝月の場合 "1" 
     * ■リクエスト.設定日＝火の場合 "2" 
     * ■リクエスト.設定日＝水の場合 "3"
     * ■リクエスト.設定日＝木の場合 "4" 
     * ■リクエスト.設定日＝金の場合 "5"
     */
    private String settingReserveWeek;

    /** 積立複数日設定. */
    /**
     * ■リクエスト.コース＝複数日の場合 リクエスト.設定日
     */
    private String settingReserveMultiDay;

    /** 旧ジュニアNISA口座開設有無. */
    private String openedJnisa;

    /** 積立買付単位 */
    private  String reserveOrderUnit;

    /** NISA枠ぎりぎり注文チェック. */
    /**
     * ■NISAぎりぎり注文チェック＝チェックありの場合 ”2” 
     * ■NISAぎりぎり注文チェック＝チェックなしの場合 ”1” 
     * ■上記以外 "0”
     */
    private String nisaBarelyBuyingType;
    private String nisaBarelyBuyingTypeShow;

    /** 課税枠シフト注文チェック. */
    /**
     * ■課税枠シフト注文チェック＝チェックありの場合 ”2” 
     * ■課税枠シフト注文文チェック＝チェックなしの場合 ”1” 
     * ■上記以外 "0”
     */
    private String taxShiftType;
    private String taxShiftTypeShow;

    /** ボーナス設定有無. */
    private String settingBonusFlag;

    /** ボーナス設定金額. */
    private String settingBonusAmount;

    /** ボーナス１設定月. */
    private String settingBonus1Month;

    /** ボーナス１設定日. */
    private String settingBonus1Day;

    /** ボーナス２設定月. */
    private String settingBonus2Month;

    /** ボーナス２設定日. */
    private String settingBonus2Day;

    /** 勧誘区分. */
    private String kanyuKbn;

    /** 受注方法受付方法. */
    private String receiveMethod;

    /** 銘柄コード. */
    private String brandCode;

}
