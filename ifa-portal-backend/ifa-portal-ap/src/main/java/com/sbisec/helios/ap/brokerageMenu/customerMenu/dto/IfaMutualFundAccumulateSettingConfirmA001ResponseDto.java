package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 投信積立設定確認 A001 リクエスト
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingConfirmA001ResponseDto {

    /** 口座番号（数字）. */
    // 手動変換 部店コード + "-" + 口座番号
    private String accountNumber;
    
    /** 個人・法人アイコン. */
    // 1（法人）
    private String corporationKbn;
    
    /** 顧客名. */
    // 手動変換 顧客名（漢字）+ "（"＋顧客名（カナ）＋"）"
    private String customerName;

    /** 銘柄名. */
    private String brandName;

    /** 協会コード. */
    private String fundCode;

    /** 決済方法. */
    private String paymentMethod;

    /** 預り区分. */
    private String accountType;
    private String accountTypeName;

    /** 積立買付単位 */
    private  String reserveOrderUnit;

    /** NISA枠ぎりぎり注文. */
    /**
     * ■NISAぎりぎり注文チェック＝チェックありの場合 ”2” 
     * ■NISAぎりぎり注文チェック＝チェックなしの場合 ”1” 
     * ■上記以外 "0”
     */
    private String nisaBarelyBuyingType;
    private String nisaBarelyBuyingTypeShow;

    /** 課税枠シフト注文. */
    /**
     * ■課税枠シフト注文チェック＝チェックありの場合 ”2” 
     * ■課税枠シフト注文文チェック＝チェックなしの場合 ”1” 
     * ■上記以外 "0”
     */
    private String taxShiftType;
    private String taxShiftTypeShow;

    /** 設定金額. */
    private String settingAmount;

    /** 設定金額概算手数料.*/
    private String estimateFundOrder;

    /** 1ヵ月あたりの設定金額（概算）. */
    private String oneMonthSumAmountStr;

    /** コース区分. */
    /**
     * ■リクエスト.コース＝毎日の場合 "1" 
     * ■リクエスト.コース＝毎週の場合 "2" 
     * ■リクエスト.コース＝毎月の場合 "3"
     * ■リクエスト.コース＝複数日の場合 "4" 
     * ■リクエスト.コース＝奇数月、偶数月の場合 "5"
     */
    private String courseType;

    /** 積立日付. */
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

    /** 買付予定日. */
    private String planDate;

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

    /** ボーナス買付予定日. */
    private String bonusPlanDate;

    /** ボーナス概算手数料 */
    private String estimateFundOrderBonus;

    /** 次回買付日. */
    private String nextReserveDate;

    /** 銘柄コード. */
    private String brandCode;

    /** 旧ジュニアNISA口座開設有無. */
    private String openedJnisa;

    /** 勧誘区分. */
    private String kanyuKbn;

    /** 受付方法. */
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

    /** 注意情報アラート. */
    private String noticeInfoAlert;

    /** お知らせアラート. */
    private String noticeAlert;

    /** コンプラランクチェック.メッセージ. */
    private String message;

    /** コンプラランクチェック.チェックボックス文言. */
    private String invitationCheck;

    /** コンプラランクチェック.開始基準確認メッセージ. */
    private String startCriteriaConfirmMsg;

    /** 確認書受け入れアラート. */
    private String confirmDocumentAlert;

//    /** ファンドコード（回数）（半角英数字）. */
//    private String mfkaisu;
//
//    /** ファンドコード（号）（半角英数字）. */
//    private String mfgo;

    /** リクエスト内容. */
    private IfaMutualFundAccumulateSettingConfirmA001RequestDto requestContents;
}
