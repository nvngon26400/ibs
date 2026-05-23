package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaBuyingPowerForeignA001ApiResponseBycountryBuyingPowerJuniorAccount {

    /** 国内受渡日. */
    private String domesticSettlementDate;

    /** 営業日後. */
    private String settlementDateAfterBusinessDay;

    /** 預り金（数値(整数)）. */
    private String yenDeposit;

    /** 入金額（数値(整数)）. */
    private String depositAmount;

    /** 支払額（数値(整数)）. */
    private String payment;

    /** 未約定買注文額（数値(整数)）. */
    private String uncontractedPurchaseOrderAmount;

    /** 出金額（数値(整数)）. */
    private String withdrawAmount;

    /** 受取額（数値(整数)）. */
    private String amountReceived;

    /** 受取額（日計り分）（数値(整数)）. */
    private String dayTrading;

    /** 振替予定額 (総合口座→ジュニアNISA口座). */
    private String transferPlanAmount;

    /** 残高（預り金）（数値(整数)）. */
    private String depositTotalBalance;

    /** 振替可能額 (総合口座→ジュニアNISA口座). */
    private String transferAbleAmount;

    /** 必要精算額. */
    private String needSettleAmount;

    /** 買付余力. */
    private String buyingPower;

}
