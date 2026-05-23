package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import lombok.Data;

@Data
public class IfaMarginPositionListForeignApi_MarginPositionListForeign {
    
    /** 部店. */
    private String buten;
    
    /** 口座. */
    private String account;
    
    /** 取引コース（全角半角）. */
    private String course;
    
    /** 顧客名(漢字). */
    private String customerNameKanji;
    
    /** 顧客名(カナ). */
    private String customerNameKana;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** ティッカー（全角半角）. */
    private String ticker;
    
    /** 市場（全角）. */
    private String market;
    
    /** 取引（全角半角）. */
    private String openTradeKbn;
    
    /** 建日. */
    private String openTradeDate;
    
    /** 返済期限. */
    private String lastTradeDate;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 担保. */
    private String security;
    
    /** 建株数（数値(整数)）. */
    private String contPositionTotal;
    
    /** 注文中（数値(整数)）. */
    private String unactualQuantity;
    
    /** 建単価. */
    private String positionPrice;
    
    /** 現在値（数値(小数)）. */
    private String currentPrice;
    
    /** 建代金（数値(整数)）. */
    private String openAmount;
    
    /** 諸経費等合計. */
    private String totalExpensesEtc;
    
    /** 評価損益. */
    private String customerListProfitAndLoss;
    
    /** 国コード（全角半角）. */
    private String countryCode;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 信用期日. */
    private String marginDueDate;
    
    /** 国内新規約定日. */
    private String domesticTradeDate;
    
    /** 現地新規約定日. */
    private String foreignTradeDate;
    
    /** 新規建単価（円貨）（数値(整数)）. */
    private String jpyAmountNewPositionPrice;
    
    /** 新規建代金（外貨）（数値(小数)）. */
    private String foreignNewPositionAmount;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者名（全角半角）. */
    private String brokerName;
    
    /** 支店コード（数字）. */
    private String branchCode;
    
    /** 支店名（全角半角）. */
    private String branchName;
    
    /** 営業員コード（半角英数字）. */
    private String empCode;
    
    /** 営業員名（全角半角）. */
    private String brokerChargeName;
    
}
