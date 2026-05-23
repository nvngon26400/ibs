package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaDepositBalanceDetailResponseDtoDomesticClaimDepositList {

    /** 保有額面. */
    private String holdingFaceValue;

    /** 利率（数値(小数)）. */
    private String compoundInterest;

    /** 償還日. */
    private String redemptionDate;

    /** 利払日. */
    private String interestPaymentDate;

    /** 参考為替（数値(小数)）. */
    private String exchangeRate;

    /** 取得単価/参考単価. */
    private String getPriceReferencePrice;

    /** 約定為替（数値(小数)）. */
    private String contractExchange;

    /** 約定金額（数値(整数)）. */
    private String contractAmount;

    /** 預り年月日. */
    private String depositDate;

    /** 評価額（数値(小数)）. */
    private String valuation;

    /** 保護／代用区分（全角半角）. */
    private String protectionSubstitutionClass;

    /** 入庫理由（全角半角）. */
    private String storageReason;

}
