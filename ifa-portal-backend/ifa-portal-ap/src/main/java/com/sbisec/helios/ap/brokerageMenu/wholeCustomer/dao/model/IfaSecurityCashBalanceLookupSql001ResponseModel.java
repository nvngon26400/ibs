package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

@Data
public class IfaSecurityCashBalanceLookupSql001ResponseModel {

    /** 総行数 */
    private String totalRow;

    /** 年月日 */
    private String dateYmd;

    /** 仲介業者コード */
    private String brokerCode;

    /** 仲介業者名 */
    private String brokerName;

    /** 扱者コード */
    private String dealerNumber;

    /** 営業員コード */
    private String empCode;

    /** 営業員名 */
    private String brokerChargeName;

    /** コースコード */
    private String customerAttribute;

    /** コース名 */
    private String courceName;

    /** 部店 */
    private String buten;

    /** 口座番号 */
    private String accountNumber;

    /** 顧客名_姓名(漢字) */
    private String customerNameKanji;

    /** 顧客名_姓名(カナ) */
    private String customerNameKana;

    /** 証券種別コード */
    private String productCd;

    /** 証券種別名 */
    private String securiytClass;

    /** 銘柄コード/通貨 */
    private String brandCodeCurrency;

    /** 銘柄名 */
    private String brandName;

    /** 取引種別 */
    private String tradeCd;

    /** 取引種別名 */
    private String tradeTypeName;

    /** 残高種別 */
    private String balanceType;

    /** 預り区分名 */
    private String depositName;

    /** 預り区分 */
    private String depositType;

    /** 国籍コード */
    private String countryCode;

    /** 仲介業支店コード */
    private String branchCode;

    /** 仲介業者支店名 */
    private String branchName;

    /** 取得単価 */
    private String acquirePrice;

    /** 現在値 */
    private String appraiseMarketPrice;

    /** 評価損益 */
    private String appraiseProfitLossPrice;

    /** 約定基準残高 */
    private String contractStandardDeposit;

    /** 評価額（円貨） */
    private String currentPrice;

    /** 通貨 */
    private String currency;

    /** 評価額（外貨） */
    private String foreignValuation;

    /** 為替レート */
    private String fxRate;

    /** 表示用返済期限 */
    private String lastTradeDate;

    /** SBI証券の返済期限 */
    private String sbiSecurityLastTradeDate;

    /** 閲覧可能部店 */
    private String butenCodeViewAble;

    /** 外債評価基準日 */
    private String foreignStandardDate;

    /** 仕組債区分 */
    private String structuredBondClassification;


}
