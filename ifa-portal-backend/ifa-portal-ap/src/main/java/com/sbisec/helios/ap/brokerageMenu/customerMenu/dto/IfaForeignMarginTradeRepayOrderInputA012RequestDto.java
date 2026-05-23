package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaForeignMarginTradeRepayOrderInputA012RequestDto {
    
    /** 市場コード（全角半角）. */
    private String marketCode;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 注文数量（返済株数）. */
    private String closeOrderQuantity;
    
    /** 注文数量（合計）. */
    private String total;
    
    /** 価格条件. */
    private String orderPriceKindList;
    
    /** 注文単価（指値）. */
    private String limitPrice2;
    
    /** 注文単価（逆指値）. */
    private String stopOrderExecutePrice2;
    
    /** 発火条件価格. */
    private String stopOrderPrice;
    
    /** 期間条件. */
    private String periodRadio;
    
    /** 期間 . */
    private String period;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 信用期日. */
    private String marginDueDate;
    
    /** 決済方法（半角英数字）. */
    private String kessaiHoho;
    
    /** 返済建玉指定方法（全角半角）. */
    private String repayPositionDesignateMethod;
    
    /** 返済選択順序（全角半角）. */
    private String repaySelectOrder;
    
    /** 建区分. */
    private String trade;
    
    /** 預り区分（個別）. */
    private String depositTypeIndividual;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String receiveOrderType;
    
    /** 株価チケット（全角半角）. */
    private String stockTicket;
    
    /** 確認項目.インサイダー確認（半角英数字）. */
    private String checkInsider;

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
    
    /** 弁済期限（全角半角）. */
    private String paymentDeadline;
    
    /** 個別建玉情報一覧. */
    private List<IfaForeignMarginTradeRepayOrderInputA012Dto_positionDesignationAreaIndividualPositionInfoList> positionDesignationAreaIndividualPositionInfoList;
    
}
