package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.common.TickSize;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputBuyingPowerDomestic;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputBuyingPowerForeign;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputPriceBasicInfo;

import lombok.Data;

/**
 * 外国現物取引注文入力株価表示APIレスポンス
 *
 * @author SCSK 宇田川達弥
 */
@Data
public class IfaForeignSpotTradeOrderInputA003ApiResponse {
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 市場略名. */
    private String marketAbbreviation;
    
    /** 市場コード（全角半角）. */
    private String marketCode;
    
    /** 国コード（全角半角）. */
    private String countryCode;
    
    /** タイムゾーン略名. */
    private String timeZoneAbbreviation;
    
    /** 銘柄上場ステータス. */
    private String stockListingStatus;
    
    /** 取引下限数量. */
    private String minimumTradingQuantity;
    
    /** 取引上限数量. */
    private String maximumTradingQuantity;
    
    /** 取引単位. */
    private String tradingUnit;
    
    /** 銘柄種別（全角半角）. */
    private String stockType;
    
    /** 注意銘柄. */
    private String tradeLimit;
    
    /** 価格基本情報リスト. */
    private IfaForeignSpotTradeOrderInputPriceBasicInfo priceBasicInfo;
    
    /** 呼値リスト. */
    private List<TickSize> callValue;
    
    /** 売却可能数. */
    private String acPosition;
    
    /** 売却可能預り区分. */
    private String sellableCustodyCategory;
    
    /** 売却可能保護区分. */
    private String sellableProtectionCategory;
    
    /** 選択可能価格条件リスト. */
    private List<String> orderPriceKindList;
    
    /** 取引通貨. */
    private String limitPriceText;
    
    /** 有効期間一覧. */
    private List<String> validPeriodList;
    
    /** 選択可能期間条件リスト. */
    private List<String> selectAblePeriodTermsList;
    
    /** 選択可能預り区分リスト. */
    private List<String> depositTypeList;
    
    /** 選択可能決済方法リスト. */
    private List<String> stockPriceKindList;
    
    /** 英文開示銘柄判定. */
    private String engPubCheck;
    
    /** ロシア呼値. */
    private String russianCallValue;

    /** 円貨決済停止日URL. */
    private String yenClosed;
    
    /** お取引注意事項URL. */
    private String tradingAttention;
    
    /** 外貨余力リスト. */
    private List<IfaForeignSpotTradeOrderInputBuyingPowerForeign> buyingPowerForeignList;
    
    /** 円貨余力リスト. */
    private List<IfaForeignSpotTradeOrderInputBuyingPowerDomestic> buyingPowerDomesticList;
    
    /** 値幅制限なし. */
    private String priceRangeNoLimit;
    
    /** 値幅下限. */
    private String priceRangeLimitMin;
    
    /** 値幅上限. */
    private String priceRangeLimitMax;
}
