package com.sbisec.helios.gw.brokerageMenu.jointSubscript.form;


import lombok.Data;

@Data
public class IfaJointSubscriptSecurityCashBalanceLookupA002ApiResponse {

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
    
    /** 共募仲介業者コード. */
    private String jointBrokerCode;

    /** 共募仲介業者名. */
    private String jointBrokerName;
    
    /** 共募支店コード（数字）. */
    private String jointBranchCode;

	/** 共募支店名（全角半角）. */
    private String jointBranchName;

    /** 証券種別名. */
    private String securiytClass;

	/** 取引種別名（全角半角）. */
    private String tradeTypeName;

    /** 返済期限. */
    private String lastTradeDate;

    /** SBI証券の返済期限. */
    private String sbiSecurityLastTradeDate;

	/** 銘柄コード/通貨（全角半角）. */
    private String brandCodeCurrency;

	/** 銘柄名（全角半角）. */
    private String brandName;

	/** 預り区分（全角半角）. */
    private String depositType;

	/** 約定基準残高（数値(整数)）. */
    private String contractStandardDeposit;

	/** 現在値（数値(小数)）. */
    private String currentPrice;

	/** 評価額（円貨）（数値(整数)）. */
    private String valuation;

    /** 通貨. */
    private String currency;

	/** 評価額（外貨）（数値(小数)）. */
    private String foreignValuation;

	/** 為替レート（数値(小数)）. */
    private String fxRate;

	/** 閲覧可能部店（半角英数字）. */
	private String butenCode;

    /** 外債評価基準日. */
    private String foreignStandardDate;

    /** 仕組債区分. */
    private String structuredBondClassification;

}
