package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 銘柄建玉明細リスト
 *
 * @author SCSK
 */
@Data
public class BrandPositionDetail {
    
    /** 市場（全角）. */
    private String market;
    
    /** 新規建日. */
    private String constructionDate;
    
    /** 期日短縮区分. */
    private String dueDateShortenClassification;
    
    /** 返済期限. */
    private String lastTradeDate;
    
    /** 親株新規約定日. */
    private String parentStockTradeDate;
    
    /** 特定・一般（全角半角）. */
    private String accountType;
    
    /** 担保. */
    private String security;
    
    /** 担保規制内容. */
    private String collateralRegulations;
    
    /** 建株数（数値(整数)）. */
    private String contPositionTotal;
    
    /** 注文中（数値(整数)）. */
    private String unactualQuantity;
    
    /** 新規単価（数値(小数)）. */
    private String newPrice;
    
    /** 評価単価（前日）（数値(小数)）. */
    private String dayBeforeValuationPrice;
    
    /** 現在値（リアル）. */
    private String latestPrice;
    
    /** 建玉金額（数値(整数)）. */
    private String openInterestAmount;
    
    /** 評価額（前日）. */
    private String valuationPreviousDay;
    
    /** 評価額（リアル）. */
    private String valuationReal;
    
    /** 諸費用. */
    private String charge;
    
    /** 評価損益（前日）. */
    private String domesticPositionValuationTotalPreviousDay;
    
    /** 評価損益（リアル）. */
    private String domesticPositionValuationTotalReal;
    
    /** 現金拘束金（数値(整数)）. */
    private String cashBond;
    
    /** 注文可能数量（数値(整数)）. */
    private String maxOrderableQuantity;
    
    /** 新規建玉指定番号（数字）. */
    private String newOpenInterestNumber;
    
    /** 親株新規約定日２. */
    private String hiddenItemParentStockTradeDate;
    
}
