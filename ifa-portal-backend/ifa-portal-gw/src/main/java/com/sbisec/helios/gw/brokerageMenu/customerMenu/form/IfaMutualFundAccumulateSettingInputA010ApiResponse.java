package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingInputComplianceRankCheck;

import lombok.Data;

/**
 * 投信積立設定入力 A010 リスポンス
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingInputA010ApiResponse {

    /** 口座番号（数字）. */
    // 手動変換 部店コード + "-" + 口座番号
    private String accountNumber;
    
    /** 個人・法人アイコン. */
    // 1（法人）
    private String corporationKbn;
    
    /** 顧客名. */
    // 手動変換 顧客名（漢字）+ "（"＋顧客名（カナ）＋"）"
    private String customerName;

    /** ファンドコード（回数）（半角英数字）. */
    private String mfkaisu;

    /** ファンドコード（号）（半角英数字）. */
    private String mfgo;

    /** 銘柄名. */
    private String brandName;

    /** 銘柄コード. */
    private String brandCode;

    /** 協会コード. */
    private String fundCode;

    /** 決済方法. */
    private String paymentMethod;

    /** 預り区分. */
    private String accountType;
    private String accountTypeName;

    // 旧ジュニアNISA口座開設有無
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

    /** 設定金額. */
    private String settingAmount;

    /** 設定金額概算手数料.*/
    private String estimateFundOrder;

    /** 1ヵ月あたりの設定金額（概算）. */
    private String oneMonthSumAmountStr;

    /** 買付予定日. */
    private String planDate;

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

    /** コンプラランクチェック.メッセージ */
    private String message;
    /** コンプラランクチェック..チェックボックス文言 */
    private String chkBoxLabel;

    /** 注意情報アラート（全角半角）. */
    private String noticeInfoAlert;
    /** お知らせアラート. */
    private String noticeAlert;
    /** 確認書受け入れアラート. */
    private String confirmDocumentAlert;
    /** 勧誘区分. */
    private String kanyuKbn;

    /** 受注方法受付方法. */
    private String receiveMethod;

    /** コンプラランクチェック. */
    private IfaMutualFundAccumulateSettingInputComplianceRankCheck complianceRankCheck;

}
