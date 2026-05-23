package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

@Data
public class IfaCouponRedemptionPaymentScheduleListSql001ResponseModel {

	/** 総件数. */
    private String totalRow;

    /** 仲介業者コード. */
    private String brokerCode;

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

    /** コース名. */
    private String courceName;

    /** コースコード. */
    private String customerAttribute;

    /** 証券種別名. */
    private String securiytClassName;

    /** 銘柄コード. */
    private String brandCode;

    /** 銘柄名. */
    private String brandName;

    /** 約定基準残高. */
    private String contractStandardDeposit;

    /** 通貨. */
    private String currency;

    /** 元利払種別. */
    private String paymentClass;

    /** 元利払種別タイプ. */
    private String paymentType;

    /** 予定日. */
    private String scheduleDate;

    /** 予定利率. */
    private String nextInterest;

    /** 予定概算金額. */
    private String schedulePrice;

    /** 支店コード. */
    private String branchCode;

    /** 支店名. */
    private String branchName;

    /** 顧客名（漢字）. */
    private String customerNameKanji;

    /** 顧客名（カナ）. */
    private String customerNameKana;

    /** 仲介業者名. */
    private String brokerName;

    /** クーポン判定日. */
    private String couponDeterminationDate;

    /** KI件数. */
    private String kiCount;

}
