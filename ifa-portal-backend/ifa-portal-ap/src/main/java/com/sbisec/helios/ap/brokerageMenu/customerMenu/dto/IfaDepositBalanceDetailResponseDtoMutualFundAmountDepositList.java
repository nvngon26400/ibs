package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaDepositBalanceDetailResponseDtoMutualFundAmountDepositList {

    /** 保有口数（数値(整数)）. */
    private String unitVolume;

    /** 取得単価/参考単価. */
    private String getPriceReferencePrice;

    /** 現在値（数値(小数)）. */
    private String currentPrice;

    /** 基準価格単位（数値(整数)）. */
    private String depositDetailPriceUnit;

    /** 取得金額/参考金額（数値(小数)）. */
    private String acquireAmountReferenceAmount;

    /** 評価額（数値(小数)）. */
    private String valuation;

    /** 評価損益. */
    private String valuationProfitAndLoss;

    /** 保護／代用区分（全角半角）. */
    private String protectionSubstitutionClass;

}
