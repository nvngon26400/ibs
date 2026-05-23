package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

/**
 * 国内投資信託（定期積立）
 *
 * @author SBI大連 夏
 * @date   2025/03/26
 */
@Data
public class IfaOrderListSql008ResponseModel {

    /** 総件数 */
    private int totalCount;
    
    /** 部店コード */
    private String buten;
    
    /** 口座番号 */
    private String accountNumber;
    
    /** 契約締結前交付書面コード */
    private String customerAttribute;
    
    /** 顧客名(漢字) */
    private String customerNameKanji;
    
    /** 顧客名(カナ) */
    private String customerNameKana;
    
    /** ファンドコード（回数） */
    private String fundCdKaisu;
    
    /** ファンドコード（号） */
    private String fundCdGou;
    
    /** 協会コード */
    private String mFCode;
    
    /** ファンドファンド正式名 */
    private String mFName;
    
    /** 更新区分 AS 変更解除区分 */
    private String modifyCancelClassification;
    
    /** 決済方法 */
    private String paymentMethod;
    
    /** 預り区分 */
    private String depositType;
    
    /** 作成日時 AS 受注日時 */
    private String orderDateTime;
    
    /** NISA枠ぎりぎり注文 */
    private String nisaBarelyBuyingKbn;
    
    /** 課税枠シフト注文 */
    private String nisaExcessBuyingKbn;
    
    /** 積立コース */
    private String accumulateCourse;
    
    /** 設定金額 */
    private String settingAmount;
    
    /** 積立日付 */
    private String settingReserveDd;
    
    /** 積立隔月設定 */
    private String settingReserveBimonthly;
    
    /** 積立毎週設定 */
    private String settingReserveWeekly;
    
    /** 積立複数日設定 */
    private String settingReserveMultiday;
    
    /** ボーナス設定有無 */
    private String settingBonusFlag;
    
    /** ボーナス設定金額 */
    private String paymentBonus;
    
    /** ボーナス１設定月 */
    private String reserveMmB1;
    
    /** ボーナス１設定日 */
    private String reserveDdB1;
    
    /** ボーナス2設定月 */
    private String reserveMmB2;
    
    /** ボーナス2設定日 */
    private String reserveDdB2;
    
    /** 1カ月あたりの積立金額 */
    private String oneMonthSumAmount;
    
    /** 次回買付日 */
    private String nextReserveDate;
    
    /** 作成者 AS 発注者 */
    private String client;
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者支店名 AS 仲介業者名. */
    private String brokerName;
    
    /** 仲介業支店コード */
    private String brokerBranchCode;
    
    /** 仲介業者支店名 */
    private String brokerBranchName;
    
    /** 仲介業者営業員コード */
    private String brokerChargeCode;
    
    /** 仲介業者担当者名 */
    private String employeeName;
    
    /** 閲覧可能部店コード */
    private String visibleButenCode;
}
