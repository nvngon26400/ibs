package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import lombok.Data;

/**
 * 国内投資信託（定期積立）
 *
 * @author SBI大連 夏
 * @date   2025/03/26
 */
@Data
public class IfaOrderListA002ApiResponseDomesticMutualFundRegularRecruitment {
    
    /** 部店コード */
    private String buten;
    
    /** 口座番号 */
    private String accountNumber;
    
    /** コース区分 */
    private String customerAttribute;
    
    /** 顧客名(漢字) */
    private String customerNameKanji;
    
    /** 顧客名(カナ) */
    private String customerNameKana;
    
    /** 銘柄コード */
    private String brandCode;
    
    /** 協会コード */
    private String fundCode;
    
    /** ファンド名 */
    private String fundName;
    
    /** 変更解除区分 */
    private String modifyCancelClassification;
    
    /** 取引種別 */
    private String tradeCd;
    
    /** 決済方法 */
    private String paymentMethod;
    
    /** 預り区分 */
    private String depositType;
    
    /** 受注日時 */
    private String orderDayTimeCalculation;
    
    /** 積立コース */
    private String accumulateCourse;
    
    /** 設定金額 */
    private String settingAmount;
    
    /** ボーナス月の設定 */
    private String bonusMonthSetting;
    
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
    
    /** NISA枠ぎりぎり注文 */
    private String nisaBarelyBuyingKbn;
    
    /** 課税枠シフト注文 */
    private String nisaExcessBuyingKbn;
    
    /** 1カ月あたりの積立金額 */
    private String oneMonthSumAmount;
    
    /** 次回発注予定日 */
    private String nextReserveDate;
    
    /** 発注者 */
    private String client;
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者名. */
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
