package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

/**
 * 米株信用取引新規注文入力 A003 レスポンスパラメータ
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaForeignMarginTradeNewOrderInputA003ApiResponse {
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 市場略名. */
    private String listedMarket;
    
    /** 市場コード（全角半角）. */
    private String marketCode;
    
    /** タイムゾーン略名. */
    private String timeZoneAbbreviatedName;
    
    /** 銘柄上場ステータス. */
    private String brandListedStatus;
    
    /** 取引下限数量. */
    private String tradeLowerLimitQuantity;
    
    /** 取引上限数量. */
    private String tradeUpperLimitQuantity;
    
    /** 取引単位. */
    private String tradeUnit;
    
    /** 銘柄種別（全角半角）. */
    private String brandClass;
    
    /** 信用新規買建規制. */
    private Boolean marginNewLongPositionRegulation;
    
    /** 信用新規売建規制. */
    private Boolean marginNewShortPositionRegulation;
    
    /** 注意銘柄. */
    private Boolean tradeLimit;
    
    /** 価格基本情報. */
    private List<IfaForeignMarginTradeNewOrderInputPriceBasicInfo> priceBasicInfo;
    
    /** 選択可能預り区分リスト. */
    private List<String> depositType;
    
    /** 選択可能決済方法リスト. */
    private List<String> currencyTypeName;
    
    /** 選択可能価格条件リスト. */
    private List<String> orderPriceKindList;
    
    /** 選択可能信用期日リスト. */
    private List<String> marginDateList;
    
    /** 選択可能期間条件リスト. */
    private List<String> periodRadio;
    
    /** 有効期間一覧. */
    private List<String> periodDate;
    
    /** 取引通貨. */
    private String tradeCurrency;
    
    /** 英文開示銘柄判定. */
    private String engPubText;
    
    /** 在庫株数(売建可能数量). */
    private List<String> quantityAvailableForSale;

    /** 本日の注意銘柄URL. */
    private String tradeLimitUrl;
    
    /** 休場日URL. */
    private String closedDay;
    
    /** 円貨決済停止日URL. */
    private String yenClosed;
    
    /** 取扱銘柄一覧URL. */
    private String usequityList;
    
    /** お取引注意事項URL. */
    private String tradingAttention;
    
    /** 信用建余力. */
    private String foreignMarginPositionPower;
    
    /** 預託率. */
    private String collateralTransferMarginDepositRateAfter;
    
}
