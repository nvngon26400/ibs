package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaMutualFundAccumulateSettingBulkChangeConfirmSql001ListDetail {

    /** 部店コード. */
    private String butenCode;

    /** 口座番号. */
    private String accountNumber;

    /** ファンドコード（回数） */
    private String fundCdKaisu;

    /** ファンドコード（号）. */
    private String fundCdGou;

    /** 預り区分. */
    private String azukariKbn;

    /** 更新区分. */
    private String modifyKbn;

    /** 決済方法. */
    private String paymentMethod;

    /** NISAPI応答枠ぎりぎり買付区分. */
    private String nisaBarelyBuyingKbn;

    /** NISAPI応答枠超過時買付区分. */
    private String nisaExcessBuyingKbn;

    /** 設定金額. */
    private String settingAmount;

    /** コース区分. */
    private String courseKbn;

    /** 積立日付. */
    private String settingReserveDd;

    /** 積立隔月設定. */
    private String settingReserveBimonthly;

    /** 積立毎週設定. */
    private String settingReserveWeekly;

    /** 積立複数日設定. */
    private String settingReserveMultiday;

    /** 1ヵ月あたりの設定金額（概算）. */
    private String oneMonthSumAmount;

    /** ボーナス設定有無. */
    private String settingBonusFlag;

    /** ボーナス設定金額. */
    private String paymentBonus;

    /** ボーナス１設定月. */
    private String reserveMmB1;

    /** ボーナス１設定日. */
    private String reserveDdB1;

    /** ボーナス２設定月. */
    private String reserveMmB2;

    /** ボーナス２設定日. */
    private String reserveDdB2;

    /** 買付予定日. */
    private String planDate;

    /** ボーナス１買付予定日. */
    private String planDateBonus1;

    /** ボーナス２買付予定日. */
    private String planDateBonus2;

    /** 次回買付日. */
    private String nextReserveDate;

    // リクエスト.勧誘区分
    private String kanyuKbn;
    // リクエスト.受付方法
    private String uketukeHouhou;
    // アラート内容確認.コンプラチェックワーニング確認
    private String checkCompWrnAlert;
    // 受付経路区分’2’
    private String receptionRouteKbn;

    /** オペレータＩＤ */
    private String operatorId;

    /** 仲介業者コード */
    private String brokerCode;

    /** 仲介業者営業員コード */
    private String intermediaryEmpCd;

    private String deleteKbn;

    /** 作成者 */
    private String createUser;

    /** 更新者 */
    private String updateUser;

}
