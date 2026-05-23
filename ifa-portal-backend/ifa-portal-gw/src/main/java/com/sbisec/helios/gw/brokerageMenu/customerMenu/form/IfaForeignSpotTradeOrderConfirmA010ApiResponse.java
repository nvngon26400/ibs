package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

/**
 * 外国現物取引注文確認注文発注APIレスポンス
 *
 * @author 福岡利基
 */
@Data
public class IfaForeignSpotTradeOrderConfirmA010ApiResponse {
    
    /** 注文情報. */
    private List<IfaForeignSpotTradeOrderConfirmA010ApiResponseOrder> orderList;
    
    /** 銘柄種別（全角半角）. */
    private String brandClass;
    
    /** 注意銘柄. */
    private String tradeLimit;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String receiveOrderType;
    
    /** 重要事項の説明. */
    private String importantMatterType;
    
    /** 乗換え勧誘(ETF). */
    private String solicitationEtf;
    
    /** 英文開示銘柄. */
    private String engPubCheckbox;
    
    /** 確認項目. */
    private List<IfaForeignSpotTradeOrderConfirmA010ApiResponseCheck> checkList;
    
    /** アラート内容確認. */
    private List<IfaForeignSpotTradeOrderConfirmA010ApiResponseAlertCheck> alertCheckList;
    
    /** 注意情報アラート（全角半角）. */
    private String noticeInfoAlert;
    
    /** お知らせアラート（全角半角）. */
    private String noticeAlert;
    
    /** コンプラランクチェック. */
    private List<IfaForeignSpotTradeOrderConfirmA010ApiResponseComplianceCheck> complianceCheckList;
    
    /** 取引注意情報（銘柄）メッセージ（全角半角）. */
    private String tradeNoticeInfoBrandMsg;
    
    /** 約定代金の条件超過確認（全角半角）. */
    private String contractAmountOverMessage;
    
    /** 逆指値注文即時発火確認. */
    private String stopOrderInstantMessage;
    
    /** 翌営業日向け注文確認. */
    private String nextBusinessDayMessage;
    
    /** 海外ETFアラートメッセージ（全角半角）. */
    private String overseasEtfAlertMessage;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 国コード（全角半角）. */
    private String countryCode;
    
    /** 市場略名. */
    private String marketAbbreviation;
    
    /** 本日の注意銘柄URL. */
    private String todayTradeLimitUrl;
    
}
