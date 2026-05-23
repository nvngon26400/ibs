package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 米株信用取引新規注文確認A010aリクエストDTO
 *
 * @author SCSK 金志
 * 
 */
@Data
public class IfaForeignMarginTradeNewOrderConfirmA010aRequestDto {
    
    /** 市場コード（全角半角）. */
    private String marketCode;
    
    /** 市場略名. */
    private String listedMarket;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 注文数量. */
    private String foreignQuantity;
    
    /** 価格条件. */
    private String orderPriceKindList;
    
    /** 注文単価（数値(小数)）. */
    private String hiddenOrderPrice;
    
    /** 発火条件価格. */
    private String stopOrderPrice2;
    
    /** 成行基準価格（全角半角）. */
    private String executeBasePrice;
    
    /** 期間条件. */
    private String periodDate;
    
    /** 期間. */
    private String period;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 決済区分（全角半角）. */
    private String settlementType;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String receiveOrderTypeName;
    
    /** 重要事項の説明. */
    private String importantMatterType;
    
    /** 英文開示銘柄. */
    private String engPubText;
    
    /** 確認項目.インサイダー確認（全角半角）. */
    private String checkInsider;
    
    /** アラート内容確認.取引注意情報（銘柄）確認. */
    private String tradingCautionInformation;
    
    /** アラート内容確認.注意情報アラート確認. */
    private String noteInfoCheckbox;
    
    /** アラート内容確認.お知らせアラート確認. */
    private String noteLimitCheck;
    
    /** アラート内容確認.増し担保規制確認. */
    private String additionalCollateralRegulationsConfirm;
    
    /** アラート内容確認.逆指値注文即時発火. */
    private String methodCheck;
    
    /** アラート内容確認.翌営業日向け注文. */
    private String nextDayCheck;
    
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
    
    /** 信用期日. */
    private String marginDueDate;
    
    /** 本日の注意銘柄URL. */
    private String tradeLimit;
    
    /** 注文番号（数字）. */
    private String orderNumber;
    
    /** 注文Sub番号（数字）. */
    private String orderSubNumber;
    
    /** 取引通貨. */
    private String tradeCurrency;
    
    /** 注文日時. */
    private String orderDate;
    
    /** 国内約定日. */
    private String domesticTradeDate;
    
    /** 現地約定日. */
    private String localTradeDate;
    
}
