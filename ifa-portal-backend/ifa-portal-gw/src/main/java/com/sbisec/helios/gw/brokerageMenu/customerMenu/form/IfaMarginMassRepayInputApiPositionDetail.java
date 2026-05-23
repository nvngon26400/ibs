package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 画面ID：SUB0202_0212-05_1
 * 画面名：信用一括返済入力
 * 建玉明細
 * 2024/04/15 新規作成
 *
 * @author SCSK 池亀緑
 */
@Data
public class IfaMarginMassRepayInputApiPositionDetail {
    
    /** 建玉明細.市場 */
    private String market;
    
    /** 建玉明細.新規建日 */
    private String constructionDate;
    
    /** 建玉明細.返済期限 */
    private String lastTradeDate;
    
    /** 建玉明細.期日短縮区分 */
    private String dueDateShortenClassification;
    
    /** 建玉明細.親株新規約定日 */
    private String parentStockTradeDate;
    
    /** 建玉明細.特定・一般 */
    private String accountType;
    
    /** 建玉明細.担保 */
    private String security;
    
    /** 建玉明細.担保区分 */
    private String securityClassification;
    
    /** 建玉明細.建株数 */
    private String contPositionTotal;
    
    /** 建玉明細.注文中 */
    private String unactualQuantity;
    
    /** 建玉明細.新規単価 */
    private String newPrice;
    
    /** 建玉明細.評価単価（前日） */
    private String dayBeforeValuationPrice;
    
    /** 建玉明細.現在値（リアル） */
    private String currentPriceReal;
    
    /** 建玉明細.建玉金額 */
    private String openInterestAmount;
    
    /** 建玉明細.評価額（前日） */
    private String valuationPreviousDay;
    
    /** 建玉明細.評価額（リアル） */
    private String valuationReal;
    
    /** 建玉明細.諸経費 */
    private String cost;
    
    /** 建玉明細.評価損益（前日） */
    private String profitAndLossPrevDay;
    
    /** 建玉明細.評価損益（リアル） */
    private String profitAndLossReal;
    
    /** 建玉明細.現金拘束金 */
    private String cashBond;
    
    /** 建玉明細.注文株数 */
    private String quantity;
    
    /** 建玉明細.新規建玉指定番号 */
    private String newOpenInterestNumber;
}
