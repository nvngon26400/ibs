package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;
import lombok.Data;

@Data
public class IfaCouponRedemptionPaymentScheduleListA002DtoResponse {

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

    /** コース名. */
    private String courceName;

    /** 顧客名（漢字）. */
    private String customerNameKanji;

    /** 顧客名（カナ）. */
    private String customerNameKana;

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

    /** 予定日. */
    private String scheduleDate;

    /** 予定利率. */
    private String nextInterest;

    /** 予定概算金額. */
    private String schedulePrice;

    /** クーポン判定日. */
    private String couponDeterminationDate;

    /** KI件数. */
    private String kiCount;

    /** 支店コード. */
    private String branchCode;

    /** 支店名. */
    private String branchName;
}
