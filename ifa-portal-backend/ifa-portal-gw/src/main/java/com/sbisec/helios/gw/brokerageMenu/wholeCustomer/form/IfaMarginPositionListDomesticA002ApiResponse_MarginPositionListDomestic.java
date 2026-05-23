package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import lombok.Data;

@Data
public class IfaMarginPositionListDomesticA002ApiResponse_MarginPositionListDomestic {
    
    /** 部店. */
    private String butenCode;
    
    /** 口座. */
    private String accountNumber;
    
    /** 取引コース（全角半角）. */
    private String course;

    /** 顧客名（漢字）（全角半角）. */
    private String customerNameKanji;
    
    /** 顧客名（カナ）（全角半角）. */
    private String customerNameKana;
    
    /** 維持率（%）. */
    private String domesticMarginPositionListActualGrntRate;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 市場（全角）. */
    private String market;
    
    /** 取引（全角半角）. */
    private String openTradeKbn;
    
    /** 建日. */
    private String openTradeDate;
    
    /** 返済期限. */
    private String lastTradeDate;
    
    /** SBI証券の返済期限. */
    private String lastTradeDateSbi;
    
    /** 預り（全角半角）. */
    private String depositFullHalf13;
    
    /** 建株数（数値(整数)）. */
    private String contPositionTotal;
    
    /** 注文中（数値(整数)）. */
    private String unactualQuantity;
    
    /** 建単価. */
    private String unitPriceForeign;
    
    /** 現在値（数値(小数)）. */
    private String currentPrice;
    
    /** 建代金（数値(整数)）. */
    private String openAmount;
    
    /** 諸経費等合計. */
    private String cost;
    
    /** 評価損益. */
    private String domesticPositionValuation;
    
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
 
    /** 新規売買区分. */
    private String newCreditOrderType;
    
    /** 新規建玉指定番号. */
    private String newOpenInterestNumber;
    
    /** 弁済期限. */
    private String paymentDeadline;
  
    /** 親株新規約定日. */
    private String parentStockTradeDate;
    
    /** 新規約定日. */
    private String newTradeDate;
    
    /** 新規市場. */
    private String newOpenMarket;

}
