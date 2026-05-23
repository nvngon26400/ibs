package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 外国現物取引注文確認注文発注APIリクエスト
 *
 * @author 福岡利基
 */
@Data
public class IfaForeignSpotTradeOrderConfirmA010ApiRequest {
    
    /** 国コード（全角半角）. */
    @NotEmpty(message = "国コード")
    @Size(min = 2, max = 2, message = "国コード")
    private String countryCode;
    
    /** 市場コード（全角半角）. */
    @NotEmpty(message = "市場コード")
    @Size(max = 12, message = "市場コード")
    private String marketCode;
    
    /** 市場略名. */
    @NotEmpty(message = "市場略名")
    private String marketAbbreviation;
    
    /** 銘柄コード（半角英数字）. */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    @NotEmpty(message = "銘柄名")
    @Size(max = 40, message = "銘柄名")
    private String brandName;
    
    /** 取引種別（全角半角）. */
    @NotEmpty(message = "取引種別")
    @Size(min = 3, max = 3, message = "取引種別")
    private String tradeCd;
    
    /** 注文数量. */
    @NotEmpty(message = "注文数量")
    private String orderQuantity;
    
    /** 価格条件. */
    @NotEmpty(message = "価格条件")
    private String orderPriceKindText;
    
    /** 注文単価（数値(小数)）. */
    @Digits(integer = 10, fraction = 2, message = "注文単価")
    @NotEmpty(message = "注文単価")
    @Size(max = 16, message = "注文単価")
    private String hiddenOrderPrice;
    
    /** 発火条件価格. */
    @NotEmpty(message = "発火条件価格")
    private String stopOrderPrice;
    
    /** 成行基準価格（全角半角）. */
    @NotEmpty(message = "成行基準価格")
    @Size(max = 5000, message = "成行基準価格")
    private String executeBasePrice;
    
    /** 期間条件. */
    @NotEmpty(message = "期間条件")
    private String periodDate;
    
    /** 期間. */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "期間")
    @Size(min = 10, max = 10, message = "期間")
    private String period;
    
    /** 預り区分（全角半角）. */
    @NotEmpty(message = "預り区分")
    @Size(max = 20, message = "預り区分")
    private String depositTypeName;
    
    /** 決済方法（半角英数字）. */
    @NotEmpty(message = "決済方法")
    @Size(min = 1, max = 1, message = "決済方法")
    private String currencyTypeName;
    
    /** 勧誘区分（全角半角）. */
    @NotEmpty(message = "勧誘区分")
    @Size(max = 2, message = "勧誘区分")
    private String solicitTypeName;
    
    /** 受注方法. */
    @NotEmpty(message = "受注方法")
    private String receiveOrderTypeName;
    
    /** 重要事項の説明. */
    @NotEmpty(message = "重要事項の説明")
    private String importantMatterTypeText;
    
    /** 乗換え勧誘(ETF). */
    @NotEmpty(message = "乗換え勧誘(ETF)")
    private String solicitationEtfText;
    
    /** 英文開示銘柄. */
    @NotEmpty(message = "英文開示銘柄")
    private String engPubText;
    
    /** 確認項目.インサイダー確認（半角英数字）. */
    @NotEmpty(message = "確認項目.インサイダー確認")
    @Size(min = 1, max = 1, message = "確認項目.インサイダー確認")
    private String checkInsider;
    
    /** アラート内容確認.取引注意情報（銘柄）確認. */
    @NotEmpty(message = "アラート内容確認.取引注意情報（銘柄）確認")
    private String tradingCautionInformation;
    
    /** アラート内容確認.コンプラランクチェック確認. */
    @NotEmpty(message = "アラート内容確認.コンプラランクチェック確認")
    private String invitationCheck;
    
    /** アラート内容確認.注意情報アラート確認. */
    @NotEmpty(message = "アラート内容確認.注意情報アラート確認")
    private String noteInfoCheckbox;
    
    /** アラート内容確認.お知らせアラート確認. */
    @NotEmpty(message = "アラート内容確認.お知らせアラート確認")
    private String noteLimitCheck;
    
    /** アラート内容確認.約定代金の上限超過. */
    @NotEmpty(message = "アラート内容確認.約定代金の上限超過")
    private String priceLimitCheck;
    
    /** アラート内容確認.逆指値注文即時発火. */
    @NotEmpty(message = "アラート内容確認.逆指値注文即時発火")
    private String methodCheck;
    
    /** アラート内容確認.翌営業日向け注文. */
    @NotEmpty(message = "アラート内容確認.翌営業日向け注文")
    private String nextDayCheck;
    
    /** アラート内容確認.海外ETFアラート確認. */
    @NotEmpty(message = "アラート内容確認.海外ETFアラート確認")
    private String overseasEtfCheck;
    
    /** 注意情報アラート（全角半角）. */
    @NotEmpty(message = "注意情報アラート")
    @Size(max = 50, message = "注意情報アラート")
    private String noticeInfoAlert;
    
    /** お知らせアラート（全角半角）. */
    @NotEmpty(message = "お知らせアラート")
    @Size(max = 50, message = "お知らせアラート")
    private String noticeAlert;
    
    /** コンプラチェック.メッセージ. */
    @NotEmpty(message = "コンプラチェック.メッセージ")
    private String complianceCheckMessage;
    
    /** コンプラチェック.メッセージID. */
    @NotEmpty(message = "コンプラチェック.メッセージID")
    private String complianceCheckMessageId;
    
    /** コンプラチェック.チェックボックス文言. */
    @NotEmpty(message = "コンプラチェック.チェックボックス文言")
    private String complianceCheckWording;
    
    /** 取引注意情報（銘柄）メッセージ（全角半角）. */
    @NotEmpty(message = "取引注意情報（銘柄）メッセージ")
    @Size(max = 50, message = "取引注意情報（銘柄）メッセージ")
    private String tradeNoticeInfoBrandMsg;
    
    /** 約定代金の条件超過確認（全角半角）. */
    @NotEmpty(message = "約定代金の条件超過確認")
    @Size(max = 50, message = "約定代金の条件超過確認")
    private String contractAmountOverMessage;
    
    /** 逆指値注文即時発火確認. */
    @NotEmpty(message = "逆指値注文即時発火確認")
    private String stopOrderInstantMessage;
    
    /** 翌営業日向け注文確認. */
    @NotEmpty(message = "翌営業日向け注文確認")
    private String nextBusinessDayMessage;
    
    /** 海外ETFアラートメッセージ（全角半角）. */
    @NotEmpty(message = "海外ETFアラートメッセージ")
    @Size(max = 50, message = "海外ETFアラートメッセージ")
    private String overseasEtfAlertMessage;
    
    /** 本日の注意銘柄URL. */
    @NotEmpty(message = "本日の注意銘柄URL")
    private String todayTradeLimitUrl;
    
    /** 注文番号（数字）. */
    @NotEmpty(message = "注文番号（数字）")
    private String orderNumber;
    
    /** 注文Sub番号（数字）. */
    @NotEmpty(message = "注文Sub番号（数字）")
    private String orderSubNumber;
    
    /** 売買区分（全角半角）. */
    @NotEmpty(message = "売買区分（全角半角）")
    private String tradeKbn;
    
    /** 取引通貨. */
    @NotEmpty(message = "取引通貨")
    private String limitPriceText;
    
    /** 注文日時. */
    @NotEmpty(message = "注文日時")
    private String orderDate;
    
    /** 国内約定日. */
    @NotEmpty(message = "国内約定日")
    private String domesticTradeDate;
    
    /** 現地約定日. */
    @NotEmpty(message = "現地約定日")
    private String localContractDate;
    
    /** 概算受渡金額（外貨）. */
    @NotEmpty(message = "概算受渡金額（外貨）")
    private String approximateDeliveryForeignAmount;
}
