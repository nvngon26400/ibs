package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaBuyingPowerForeignA002ApiResponseBycountryBuyingPowerWholeAccount {

    /** 国内受渡日. */
    private String domesticSettlementDate;

    /** 営業日後. */
    private String settlementDateAfterBusinessDay;

    /** 預り金（数値(整数)）. */
    private String yenDeposit;

    /** 通貨. */
    private String currencyCode;

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

    /** 振替予定額 （信用口座→現物口座）. */
    private String transferPlanAmount;

    /** 残高（預り金）（数値(整数)）. */
    private String depositTotalBalance;

    /** 必要精算額. */
    private String needSettleAmount;

    /** 買付余力. */
    private String buyingPower;

    /** 当日米株店頭買付約定金額. */
    private String todayAmericaCounterBuyTradeAmount;

    /** 当日外債買付代金. */
    private String todayForeignBondBuyAmount;
    
    /** 買付余力2. */
    private String buyingPower2;
	
}
