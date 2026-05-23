package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto;

import lombok.Data;

@Data
public class IfaJointSubscriptSecurityCashBalanceLookupA002DtoResponse {

    /** 年月日. */
    private String dateYmd;

    /** 仲介業者コード. */
    private String brokerCode;

    /** 仲介業者名. */
    private String brokerName;

    /** 営業員コード. */
    private String empCode;

    /** 営業員名. */
    private String brokerChargeName;

    /** 部店. */
    private String buten;

    /** 口座番号. */
    private String accountNumber;

    /** 扱者コード. */
    private String dealerNumber;

    /** 取引コース. */
    private String courceName;

    /** 顧客名_姓名(漢字). */
    private String customerNameKanji;

    /** 顧客名_姓名(カナ). */
    private String customerNameKana;
    
    /** 共募支店コード（数字）. */
    private String jointBranchCode;
    
    /** 共募支店名（全角半角）. */
    private String jointBranchName;

    /** 証券種別名. */
    private String securiytClass;

    /** 取引種別名. */
    private String tradeTypeName;

    /** 返済期限. */
    private String lastTradeDate;

    /** SBI証券の返済期限. */
    private String sbiSecurityLastTradeDate;

    /** 銘柄コード/通貨. */
    private String brandCodeCurrency;

    /** 銘柄名. */
    private String brandName;

    /** 預り区分. */
    private String depositType;

    /** 約定基準残高. */
    private String contractStandardDeposit;

    /** 現在値. */
    private String currentPrice;

    /** 評価額（円貨）. */
    private String valuation;

    /** 通貨. */
    private String currency;

    /** 評価額（外貨）. */
    private String foreignValuation;

    /** 為替レート. */
    private String fxRate;

    /** 閲覧可能部店. */
    private String butenCodeViewAble;

    /** 外債評価基準日. */
    private String foreignStandardDate;

    /** 仕組債区分. */
    private String structuredBondClassification;

}
