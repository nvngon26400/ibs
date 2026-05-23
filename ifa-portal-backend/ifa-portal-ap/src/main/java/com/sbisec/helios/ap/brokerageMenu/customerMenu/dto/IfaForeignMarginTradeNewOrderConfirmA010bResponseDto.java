package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 米株信用取引新規注文確認A010bリスポンスDTO
 *
 * @author SCSK 金志
 * 
 */
@Data
public class IfaForeignMarginTradeNewOrderConfirmA010bResponseDto {
    
    /** 注文情報. */
    private OrderInfo orderInfo;
    
    /** 返済建玉指定方法（全角半角）. */
    private String repayPositionDesignateMethod;
    
    /** 返済選択順序（全角半角）. */
    private String repaySelectOrder;
    
    /** 信用期日. */
    private String marginDueDate;
    
    /** 決済損益（数値(整数)）. */
    private String settlement;
    
    /** 保証金不足金額. */
    private String depositDeficientAmount;
    
    /** 振替預り金額. */
    private String currency;
    
    /** 振替有価証券評価額. */
    private String americaStockTransferAmount;
    
    /** 銘柄種別（全角半角）. */
    private String brandClass;
    
    /** 注意銘柄. */
    private String tradeLimitTitle;
    
    /** 有効期間一覧. */
    private List<String> validityPeriodList;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String orderMethod;
    
    /** 重要事項の説明. */
    private String importantMatterType;
    
    /** 英文開示銘柄. */
    private String engPubText;
    
    /** 確認項目. */
    private ConfirmItem confirmItem;
    
    /** アラート内容確認. */
    private AlertContentsConfirm alertContentsConfirm;
    
    /** 注意情報アラート（全角半角）. */
    private String noticeInfoAlert;
    
    /** お知らせアラート（全角半角）. */
    private String noticeAlert;
    
    /** 増し担保規制銘柄取引メッセージ. */
    private String additionalCollateralRegulationConfirmMsg;
    
    /** 逆指値注文即時発火メッセージ. */
    private String stopOrderInstantMessage;
    
    /** 現地約定日超過メッセージ. */
    private String nextBusinessDayMessage;
    
    /** 取引注意情報（銘柄）メッセージ（全角半角）. */
    private String tradeNoticeInfoBrandMsg;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 市場略名. */
    private String listedMarket;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 本日の注意銘柄URL. */
    private String tradeLimit;
    
}
