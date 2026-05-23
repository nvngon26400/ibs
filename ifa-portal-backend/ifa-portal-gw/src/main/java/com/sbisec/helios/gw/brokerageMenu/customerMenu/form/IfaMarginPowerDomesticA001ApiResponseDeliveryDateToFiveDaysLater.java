package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaMarginPowerDomesticA001ApiResponseDeliveryDateToFiveDaysLater {

    /** 受渡日（T+0）～受渡日（T+5）.受渡日 */
    private String settlementDateT0toT5;

    /** 受渡日（T+0）～受渡日（T+5）.委託保証金率 */
    private String marginDepositRate9;

    /** 受渡日（T+0）～受渡日（T+5）.参考委託保証金率 */
    private String domesticReferenceMarginDeposit;

    /** 受渡日（T+0）～受渡日（T+5）.建玉必要額 */
    private String openInterestCostYen;

    /** 受渡日（T+0）～受渡日（T+5）.追証予定額 */
    private String additionalMarginPlannedAmount;

    /** 受渡日（T+0）～受渡日（T+5）.預り金不足予定額 */
    private String shortageDepositDue;

    /** 余力詳細_受渡日（T+0）～受渡日（T+5）.委託保証金率 */
    private String reservePowerDetailDeliveryDateToFiveDaysLaterMarginDepositRate9;

    /** 受渡日（T+0）～受渡日（T+5）.31％超分 */
    private String over31per;

    /** 受渡日（T+0）～受渡日（T+5）.31%超分(当社のみ) */
    private String over31perOurCompany;

    /** 受渡日（T+0）～受渡日（T+5）.31%超分(当社+SSNB) */
    private String over31perOurCompanySsnb;

    /** 受渡日（T+0）～受渡日（T+5）.信用建余力 */
    private String creditCapacity;

    /** 受渡日（T+0）～受渡日（T+5）.30％超分 */
    private String over30per;

    /** 受渡日（T+0）～受渡日（T+5）.30%超分(当社のみ) */
    private String over30perOurCompany;

    /** 受渡日（T+0）～受渡日（T+5）.30%超分(当社+SSNB) */
    private String over30perOurCompanySsnb;

    /** 受渡日（T+0）～受渡日（T+5）.30_2%超分(当社のみ) */
    private String over302perOurCompany;

    /** 受渡日（T+0）～受渡日（T+5）.30_2%超分(当社+SSNB) */
    private String over302perOurCompanySsnb;

    /** 受渡日（T+0）～受渡日（T+5）.現物買付余力 */
    private String buyingRemainingPower;

    /** 受渡日（T+0）～受渡日（T+5）.出金振替可能額 */
    private String withdrawalCapacity;

    /** 受渡日（T+0）～受渡日（T+5）.委託保証金受渡日 */
    private String consignmentSecurityDepositDeliveryDate;

    /** 受渡日（T+0）～受渡日（T+5）.保証金現金（余力WK）（上段） */
    private String remainingCapacityWkTop;

    /** 受渡日（T+0）～受渡日（T+5）.保証金現金（余力WK）（下段） */
    private String remainingCapacityWkUnder;

    /** 受渡日（T+0）～受渡日（T+5）.SSNB精算前保証金現金(余力WK)（上段） */
    private String marginDepositBeforeSsnbCalculateTop;

    /** 受渡日（T+0）～受渡日（T+5）.SSNB精算前保証金現金(余力WK)（下段） */
    private String marginDepositBeforeSsnbCalculateUnder;

    /** 受渡日（T+0）～受渡日（T+5）.SSNB精算額 */
    private String ssnbCalculateAmount;

    /** 受渡日（T+0）～受渡日（T+5）.SSNB精算後保証金 */
    private String marginDepositAfterSsnbCalculate;

    /** 受渡日（T+0）～受渡日（T+5）.代用入庫額 */
    private String alternativeReceipt;

    /** 受渡日（T+0）～受渡日（T+5）.代用出庫額 */
    private String substituteDeliveryAmount;

    /** 受渡日（T+0）～受渡日（T+5）.保証金代用 */
    private String alternativeDeposit;

    /** 受渡日（T+0）～受渡日（T+5）.保証金合計 */
    private String totalDeposit;

    /** 受渡日（T+0）～受渡日（T+5）.評価損 */
    private String valuationLoss;

    /** 受渡日（T+0）～受渡日（T+5）.評価損（評価損益） */
    private String domesticPositionValuation;

    /** 受渡日（T+0）～受渡日（T+5）.決済損益 */
    private String settlement;

    /** 受渡日（T+0）～受渡日（T+5）.支払諸経費 */
    private String paymentExpence;

    /** 受渡日（T+0）～受渡日（T+5）.実質保証金 */
    private String yenActualDeposit;

    /** 受渡日（T+0）～受渡日（T+5）.SBIハイブリッド預金残高 */
    private String hybridDepositBalance;

    /** 受渡日（T+0）～受渡日（T+5）.実保+SBIハイブリッド預金 */
    private String realInsuranceAndHybridDepositBalance;

    /** 受渡日（T+0）～受渡日（T+5）.当社保証金+SBIハイブリッド預金(現金部分のみ) */
    private String depositAndHybridDepositBalance;

    /** 受渡日（T+0）～受渡日（T+5）.注文中建玉金額 */
    private String orderOpenInterest;

    /** 受渡日（T+0）～受渡日（T+5）.未決済建玉金額 */
    private String unsetteledOpenInterest;

    /** 受渡日（T+0）～受渡日（T+5）.決済済建玉金額 */
    private String setteledOpenInterest;

    /** 受渡日（T+0）～受渡日（T+5）.現引現渡建玉金額 */
    private String currentDeliverlyOpenInterest;

    /** 受渡日（T+0）～受渡日（T+5）.建玉必要保証金 */
    private String openInterstDeposit;

    /** 受渡日（T+0）～受渡日（T+5）.建玉必要保証金(うち現金) */
    private String openInterstDepositCash;

    /** 受渡日（T+0）～受渡日（T+5）.買付必要保証金（30％） */
    private String purchaseDeposit30;

    /** 受渡日（T+0）～受渡日（T+5）.買付必要保証金（30.％）(うち現金) */
    private String purchaseDeposit30Cash;

    /** 受渡日（T+0）～受渡日（T+5）.買付必要保証金（30.2％） */
    private String purchaseDeposit32;

    /** 受渡日（T+0）～受渡日（T+5）.買付必要保証金（30.2％）(増担保) */
    private String purchaseDeposit32AdditionalCollateral;

    /** 受渡日（T+0）～受渡日（T+5）.買付必要保証金（30.2％）[配当金] */
    private String purchaseDeposit32Divined;

    /** 受渡日（T+0）～受渡日（T+5）.返却必要保証金 */
    private String returnDeposit;

    /** 受渡日（T+0）～受渡日（T+5）.返却必要保証金(うち現金) */
    private String returnDepositCash;

    /** 受渡日（T+0）～受渡日（T+5）.返却必要保証金[増担保] */
    private String returnDepositAdditionalCollateral;

    /** 受渡日（T+0）～受渡日（T+5）.返却必要保証金[配当金] */
    private String returnDepositDivined;

    /** 受渡日（T+0）～受渡日（T+5）.現引・現渡必要保証金 */
    private String currentDeliverlyDeposit;

    /** 受渡日（T+0）～受渡日（T+5）.信用取引必要保証金（※） */
    private String creditOrderDeposit;

    /** 受渡日（T+0）～受渡日（T+5）.当社必要保証金（※） */
    private String innerDeposit;

}
