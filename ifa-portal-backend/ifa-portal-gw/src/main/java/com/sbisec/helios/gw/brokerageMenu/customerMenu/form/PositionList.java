package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 建玉一覧リスト
 *
 * @author SCSK 金志
 */
@Data
public class PositionList {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 弁済期限（全角半角）. */
    private String paymentDeadline;
    
    /** 市場（全角）. */
    private String market;
    
    /** 新規建日. */
    private String constructionDate;
    
    /** 返済期限短縮（全角半角）. */
    private String repayPeriodShorter;
    
    /** 返済期限. */
    private String lastTradeDate;
    
    /** 親株新規約定日. */
    private String parentStockTradeDate;
    
    /** 特定・一般（全角半角）. */
    private String accountType;
    
    /** 担保. */
    private String domesticCollateral;
    
    /** 担保規制内容. */
    private String collateralRegulations;
    
    /** 建玉件数（数値(整数)）. */
    private String positionCount;
    
    /** 建株数（数値(整数)）. */
    private String contPositionTotal;
    
    /** 注文中（数値(整数)）. */
    private String unactualQuantity;
    
    /** 新規単価（数値(小数)）. */
    private String newPrice;
    
    /** 評価単価（前日）（数値(小数)）. */
    private String dayBeforeValuationPrice;
    
    /** 新規建玉指定番号（数字）. */
    private String newOpenInterestNumber;
    
    /** 現在値（リアル）. */
    private String currentPrice;
    
    /** 建玉金額計（数値(整数)）. */
    private String totalSmallPrice;
    
    /** 評価額計（前日）（数値(整数)）. */
    private String previousDayValueTotal;
    
    /** 評価額計（リアル）（数値(整数)）. */
    private String realtimeValueTotal;
    
    /** 諸経費計（数値(整数)）. */
    private String costSmallTotalYen;
    
    /** 評価損益（前日）. */
    private String domesticPositionValuationTotalPreviousDay;
    
    /** 評価損益（リアル）. */
    private String domesticPositionValuationTotalReal;
    
    /** 現金拘束金（数値(整数)）. */
    private String cashBond;
    
    /** 返済売ボタン表示区分. */
    private String repaySellButtonDisplaylassification;
    
    /** 現引ボタン表示区分. */
    private String receiptButtonDisplaylassification;
    
    /** 返済買ボタン表示区分. */
    private String repayBuyButtonDisplaylassification;
    
    /** 現渡ボタン表示区分. */
    private String deliveryButtonDisplaylassification;
    
    /** 建玉詳細ボタン表示区分. */
    private String positionDetailButtonDisplaylassification;
    
    /** 一括返済ボタン表示区分. */
    private String massRepayButtonDisplaylassification;
    
    /** 活性非活性フラグ. */
    private String activationDeactivationFlag;
    
    /** 新規約定日. */
    private String newTradeDate;
    
}
