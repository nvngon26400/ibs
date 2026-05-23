package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 外国現物取引注文確認注文発注リクエスト
 *
 * @author 福岡利基
 */
@Data
public class IfaForeignSpotTradeOrderConfirmA010aRequestDto {
    
    /** 国コード（全角半角）. */
    private String countryCode;
    
    /** 市場コード（全角半角）. */
    private String marketCode;
    
    /** 市場略名. */
    private String marketAbbreviation;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 注文数量. */
    private String orderQuantity;
    
    /** 価格条件. */
    private String orderPriceKindText;
    
    /** 注文単価（数値(小数)）. */
    private String hiddenOrderPrice;
    
    /** 発火条件価格. */
    private String stopOrderPrice;
    
    /** 成行基準価格（全角半角）. */
    private String executeBasePrice;
    
    /** 期間条件. */
    private String periodDate;
    
    /** 期間. */
    private String period;
    
    /** 預り区分（全角半角）. */
    private String depositTypeName;
    
    /** 決済方法（半角英数字）. */
    private String currencyTypeName;
    
    /** 勧誘区分（全角半角）. */
    private String solicitTypeName;
    
    /** 受注方法. */
    private String receiveOrderTypeName;
    
    /** 重要事項の説明. */
    private String importantMatterTypeText;
    
    /** 乗換え勧誘(ETF). */
    private String solicitationEtfText;
    
    /** 英文開示銘柄. */
    private String engPubText;
    
    /** 確認項目.インサイダー確認（半角英数字）. */
    private String checkInsider;
    
    /** アラート内容確認.取引注意情報（銘柄）確認. */
    private String tradingCautionInformation;
    
    /** アラート内容確認.コンプラランクチェック確認. */
    private String invitationCheck;
    
    /** アラート内容確認.注意情報アラート確認. */
    private String noteInfoCheckbox;
    
    /** アラート内容確認.お知らせアラート確認. */
    private String noteLimitCheck;
    
    /** アラート内容確認.約定代金の上限超過. */
    private String priceLimitCheck;
    
    /** アラート内容確認.逆指値注文即時発火. */
    private String methodCheck;
    
    /** アラート内容確認.翌営業日向け注文. */
    private String nextDayCheck;
    
    /** アラート内容確認.海外ETFアラート確認. */
    private String overseasEtfCheck;
    
    /** 注意情報アラート（全角半角）. */
    private String noticeInfoAlert;
    
    /** お知らせアラート（全角半角）. */
    private String noticeAlert;
    
    /** コンプラチェック.メッセージ. */
    private String complianceCheckMessage;
    
    /** コンプラチェック.メッセージID. */
    private String complianceCheckMessageId;
    
    /** コンプラチェック.チェックボックス文言. */
    private String complianceCheckWording;
    
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
    
    /** 本日の注意銘柄URL. */
    private String todayTradeLimitUrl;
    
    /** 注文番号（数字）. */
    private String orderNumber;
    
    /** 注文Sub番号（数字）. */
    private String orderSubNumber;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 取引通貨. */
    private String limitPriceText;
    
    /** 注文日時. */
    private String orderDate;
    
    /** 国内約定日. */
    private String domesticTradeDate;
    
    /** 現地約定日. */
    private String localContractDate;
    
    /** 概算受渡金額（外貨）. */
    private String approximateDeliveryForeignAmount;
    
}
