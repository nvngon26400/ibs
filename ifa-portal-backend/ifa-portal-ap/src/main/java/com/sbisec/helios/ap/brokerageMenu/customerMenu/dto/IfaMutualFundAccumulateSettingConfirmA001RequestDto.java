package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;


import lombok.Data;

/**
 * 投信積立設定確認 A001 リクエスト
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingConfirmA001RequestDto {

    /** 協会コード. */
    private String fundCode;

    /** 決済方法. */
    private String paymentMethod;

    /** 預り区分. */
    private String accountType;

    /** コース区分. */
    private String settingAmount;

    /** 積立日付. */
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

    /** 旧ジュニアNISA口座開設有無. */
    private String openedJnisa;

    /** 積立買付単位 */
    private  String reserveOrderUnit;

    /** NISA枠ぎりぎり注文. */
    private String nisaBarelyBuyingType;
    private String nisaBarelyBuyingTypeShow;

    /** 課税枠シフト注文. */
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

    /** 銘柄コード. */
    private String brandCode;

    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;

    /** 受注方法. */
    private String receiveMethod;

    /** 確認項目.ご注意事項. */
    private String checkMadoAki;


    /** アラート内容確認.コンプラランクチェック確認. */
    private String complianceRankCheckConfirm;

    /** アラート内容確認.コンプラランクチェック開始基準確認. */
    private String complianceRankCheckStartBaseConfirm;

    /** アラート内容確認.注意情報アラート確認. */
    private String noticeInfoAlertConfirm;

    /** アラート内容確認.お知らせアラート確認. */
    private String noticeAlertConfirm;

    /** アラート内容確認.確認書受け入れアラート確認. */
    private String confirmDocumentAlertConfirm;

    /** 注意情報アラート（全角半角）. */
    private String noticeInfoAlert;

    /** お知らせアラート（全角半角）. */
    private String noticeAlert;

    /** コンプラランクチェック. */
    private IfaMutualFundAccumulateSettingInputComplianceRankCheck complianceRankCheck;

    /** 確認書受け入れアラート. */
    private String confirmDocumentAlert;

    /** ファンドコード（回数）（半角英数字）. */
    private String mfkaisu;

    /** ファンドコード（号）（半角英数字）. */
    private String mfgo;

}
