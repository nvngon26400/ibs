package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 外国現物取引注文入力注文確認リクエスト
 *
 * @author SCSK 宇田川達弥
 */
@Data
public class IfaForeignSpotTradeOrderInputA013RequestDto {
    
    /** 国コード（全角半角）. */
    private String countryCode;
    
    /** 市場コード（全角半角）. */
    private String marketCode;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 取引種別（全角半角）. */
    private String buySellTypeName;
    
    /** 注文数量. */
    private String orderQuantity;
    
    /** 価格条件. */
    private String orderPriceKindList;

    /** 注文単価（数値(小数)）. */
    private String limitOrderPrice;
    
    /** 注文単価（数値(小数)）. */
    private String stopOrderExecutePrice;
    
    /** 発火条件価格. */
    private String stopOrderPrice;
    
    /** 期間条件. */
    private String periodRadio;
    
    /** 期間. */
    private String period;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 決済方法（半角英数字）. */
    private String currencyTypeName;
    
    /** 勧誘区分（全角半角）. */
    private String solicitTypeList;
    
    /** 受注方法. */
    private String receiveOrderType;
    
    /** 重要事項の説明. */
    private String importantMatterType;
    
    /** 乗換え勧誘(ETF). */
    private String solicitationEtf;
    
    /** 英文開示銘柄. */
    private String engPubCheckbox;
    
    /** 確認項目.インサイダー確認. */
    private String checkInsider;

    /** 本日の注意銘柄URL. */
    private String todayTradeLimitUrl;
    
    /** 休場日URL. */
    private String closedDay;
    
    /** 円貨決済停止日URL. */
    private String yenClosedDateUrl;
    
    /** 取扱銘柄一覧URL. */
    private String handledStockListUrl;
    
    /** お取引注意事項URL. */
    private String noticeofTransactionPrecautionsUrl;

}
