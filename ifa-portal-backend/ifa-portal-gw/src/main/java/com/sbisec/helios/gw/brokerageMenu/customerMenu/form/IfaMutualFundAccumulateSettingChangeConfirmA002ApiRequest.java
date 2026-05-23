package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeInputComplianceRankCheck;

import lombok.Data;

/**
 * 投信積立設定変更確認 A002 リクエストパラメータ
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingChangeConfirmA002ApiRequest {

    @NotEmpty(message = "協会コード")
    @Size(max = 8, message = "協会コード")
    private String fundCode;
    // 決済方法（変更前）
    private String paymentMethodBefore;
    // 決済方法
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

    /** 旧ジュニアNISA口座開設有無. */
    private String openedJnisa;

    /** 積立買付単位 */
    private  String reserveOrderUnit;

    // NISA枠ぎりぎり注文
    private String nisaBarelyBuyingType;
    private String nisaBarelyBuyingTypeShow;

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
    private IfaMutualFundAccumulateSettingChangeInputComplianceRankCheck complianceRankCheck;

    /** 確認書受け入れアラート. */
    private String confirmDocumentAlert;

    /** ファンドコード（回数）（半角英数字）. */
    private String mfkaisu;

    /** ファンドコード（号）（半角英数字）. */
    private String mfgo;

}
