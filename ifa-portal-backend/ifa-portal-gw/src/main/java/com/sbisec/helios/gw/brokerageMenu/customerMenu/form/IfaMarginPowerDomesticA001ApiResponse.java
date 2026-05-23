package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaMarginPowerDomesticA001ApiResponse {
    
    /** 受渡日別入金予定合計額 ※複合語登録対象*/
    private String deliveryDateDepositScheduleTotalAmount;
    
    /** 追証ステータス */
    private String marginStatus;
    
    /** 追加保証金未解消に伴う建玉強制返済予定日時 */
    private String plannedDateForcedRedemptionOpenInterest;
    
    /** 追証入金予定日 */
    private String marginDepositScheduleDate;
    
    /** 追加保証金を全て解消するための必要額 */
    private String amountrequiredtocancelallAdditionalsecuritydeposits;
    
    /** 追証基準維持率 */
    private String marginstandardmaintenanceRate;
    
    /** 追証金額 */
    private String marginAmount;
    
    /** 追証発生日 */
    private String marginCallDate;
    
    /** 追証解消期限 */
    private String marginCancellationDeadline;
    
    /** 建玉強制返済執行期限 */
    private String deadlineforforcedrepaymentOfopeninterest;
    
    /** 信用建余力 */
    private String creditCapacity;
    
    /** 現引可能額 */
    private String cashOnDelivery;
    
    /** 現物買付余力 */
    private String buyingRemainingPower;
    
    /** 出金・振替可能額 */
    private String withdrawalCapacity;
    
    /** 建玉総限度額 */
    private String openInterestLimit;
    
    /** 前日委託保証金率(T+0) */
    private String consignmentDeposit;
    
    /** 新規建保証金率（信用建余力の計算に適用） */
    private String newOpenInterest;
    
    /** 現金保証金率（新規建に必要な保証金現金の計算に適用） */
    private String chache;
    
    /** 現物買付保証金率（現物の買付余力の計算に適用） */
    private String inKindPurchase;
    
    /** 出金・振替保証金率（出金・振替可能額の計算に適用） */
    private String withdrawTransfer;
    
    /** 前日基準.未決済建玉金額 */
    private String previousDayBaseOpenInterestAmount;
    
    /** 自動スイープ対象フラグ ※複合語登録対象*/
    private String autoSweepTargetFlag;
    
    /** 追証情報リスト */
    private List<IfaMarginPowerDomesticA001ApiResponseMarginCallinfo> marginCallinfoList;
    
    /** 受渡日（T+0）～受渡日（T+5）リスト */
    private List<IfaMarginPowerDomesticA001ApiResponseDeliveryDateToFiveDaysLater> deliveryDateToFiveDaysLaterList;
    
}
