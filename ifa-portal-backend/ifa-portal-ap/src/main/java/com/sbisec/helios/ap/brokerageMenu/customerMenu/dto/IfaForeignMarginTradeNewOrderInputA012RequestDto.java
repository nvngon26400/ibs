package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 米株信用取引新規注文入力 A012 リクエストパラメータ
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaForeignMarginTradeNewOrderInputA012RequestDto {
    
    /** 市場コード（全角半角）. */
    private String marketCode;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 注文数量. */
    private String foreignQuantity;
    
    /** 価格条件. */
    private String orderPriceKindList;
    
    /** 価格条件（逆指値） */
    private String orderPriceKindListReversePriceLimit;
    
    /** 注文単価（指値）. */
    private String hiddenOrderPrice;
    
    /** 注文単価（逆指値） */
    private String hiddenOrderPriceReversePriceLimit;
    
    /** 発火条件価格.(手補正) */
    private String orderInputAreaPriceTermsreversePriceLimitStopOrderPrice;
    
    /** 期間条件. */
    private String periodRadio;
    
    /** 期間. */
    private String period;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 信用期日. */
    private String marginDueDate;
    
    /** 決済方法（全角半角）. */
    private String kessaiHoho;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String receiveOrderType;
    
    /** 重要事項の説明. */
    private String importantMatterType;
    
    /** 英文開示銘柄. */
    private String engPubText;
    
    /** 確認項目.インサイダー確認（全角半角）. */
    private String checkInsider;

    /** 本日の注意銘柄URL. */
    private String tradeLimit;
    
    /** 休場日URL. */
    private String closedDay;
    
    /** 円貨決済停止日URL. */
    private String yenClosed;
    
    /** 取扱銘柄一覧URL. */
    private String usequityList;
    
    /** お取引注意事項URL. */
    private String tradingAttention;
    
}
