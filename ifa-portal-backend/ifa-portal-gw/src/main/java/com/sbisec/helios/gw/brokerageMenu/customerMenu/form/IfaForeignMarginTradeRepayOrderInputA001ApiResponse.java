package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

@Data
public class IfaForeignMarginTradeRepayOrderInputA001ApiResponse {
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 弁済期限（全角半角）. */
    private String paymentDeadline;
    
    /** 建区分. */
    private String trade;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 市場名. */
    private String marketAbbreviatedName;
    
    /** 市場コード（全角半角）. */
    private String marketCode;
    
    /** 国コード（全角半角）. */
    private String countryCode;
    
    /** 取引通貨. */
    private String currencyCode;
    
    /** タイムゾーン略名 */
    private String timeZoneAbbreviatedName;
    
    /** 銘柄上場ステータス */
    private String brandListedStatus;
    
    /** 取引下限数量 */
    private String tradeLowerLimitQuantity;
    
    /** 取引上限数量 */
    private String tradeUpperLimitQuantity;
    
    /** 取引単位 */
    private String tradingUnit;
    
    /** 銘柄種別（全角半角）. */
    private String brandSyubetsu;
    
    /** 注意銘柄. */
    private String tradeLimitTitle;
    
    /** 株価チケット（全角半角）. */
    private String stockTicket;
    
    /** 価格基本情報. */
    private IfaForeignMarginTradeRepayOrderInputApiResponse_priceBasicInfo priceBasicInfo;
    
    /** 選択可能預り区分リスト */
    private List<String> selectAbleDepositTypeList;
    
    /** 選択可能決済方法リスト */
    private List<String> selectAbleSettlementMethodList;
    
    /** 選択可能価格条件リスト */
    private List<String> selectAblePriceTermsList;
    
    /** 選択可能信用期日リスト */
    private List<String> selectAbleMarginDueDateList;
    
    /** 選択可能返済建玉指定方法リスト */
    private List<String> selectAbleRepayPositionDesignationMethodList;
    
    /** 選択可能返済選択順序リスト */
    private List<String> selectAbleRepaySelectSequenceList;
    
    /** 選択可能期間条件リスト */
    private List<String> selectAblePeriodTermsList;
    
    /** 有効期間一覧 */
    private List<String> validityPeriodList;
    
    /** 外国株式信用建玉サマリ. */
    private IfaForeignMarginTradeRepayOrderInputApiResponse_foreignStockMarginPositionSummary foreignStockMarginPositionSummary;
    
    /** 外国株式信用建玉明細一覧. */
    private List<IfaForeignMarginTradeRepayOrderInputApiResponse_foreignStockMarginPositionDetailList> foreignStockMarginPositionDetailList;
    
    /** 期間. */
    private String periodList;
    
    /** 本日の注意銘柄URL. */
    private String tradeLimitUrl;

    /** 休場日URL. */
    private String closedDayUrl;
    
    /** 円貨決済停止日URL. */
    private String yenClosedUrl;
    
    /** 取扱銘柄一覧URL. */
    private String usequityListUrl;
    
    /** お取引注意事項URL. */
    private String tradingAttentionUrl;
}
