package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 米株信用取引新規注文確認A010リクエスト
 *
 * @author SCSK 金志
 * 
 */
@Data
public class IfaForeignMarginTradeNewOrderConfirmA010ApiRequest {
    
    /** 市場コード（全角半角）. */
    @NotEmpty(message = "市場コード")
    @Size(max = 12, message = "市場コード")
    private String marketCode;
    
    /** 市場略名. */
    @NotEmpty(message = "市場略名")
    private String listedMarket;
    
    /** 銘柄コード（半角英数字）. */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    @NotEmpty(message = "銘柄名")
    @Size(max = 40, message = "銘柄名")
    private String brandName;
    
    /** 売買区分（全角半角）. */
    @NotEmpty(message = "売買区分")
    @Size(max = 2, message = "売買区分")
    private String tradeKbn;
    
    /** 取引種別（全角半角）. */
    @NotEmpty(message = "取引種別")
    private String tradeCd;
    
    /** 注文数量. */
    @NotEmpty(message = "注文数量")
    private String foreignQuantity;
    
    /** 価格条件. */
    @NotEmpty(message = "価格条件")
    private String orderPriceKindList;
    
    /** 注文単価（数値(小数)）. */
    @Digits(integer = 10, fraction = 2, message = "注文単価")
    @NotEmpty(message = "注文単価")
    @Size(max = 14, message = "注文単価")
    private String hiddenOrderPrice;
    
    /** 発火条件価格. */
    @NotEmpty(message = "発火条件価格")
    private String stopOrderPrice2;
    
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
    private String depositType;
    
    /** 決済区分（全角半角）. */
    @NotEmpty(message = "決済区分")
    @Size(max = 2, message = "決済区分")
    private String settlementType;
    
    /** 勧誘区分（全角半角）. */
    @NotEmpty(message = "勧誘区分")
    @Size(max = 2, message = "勧誘区分")
    private String kanyuKbn;
    
    /** 受注方法. */
    @NotEmpty(message = "受注方法")
    private String receiveOrderTypeName;
    
    /** 重要事項の説明. */
    @NotEmpty(message = "重要事項の説明")
    private String importantMatterType;
    
    /** 英文開示銘柄. */
    @NotEmpty(message = "英文開示銘柄")
    private String engPubText;
    
    /** 確認項目.インサイダー確認（全角半角）. */
    @NotEmpty(message = "確認項目.インサイダー確認")
    private String checkInsider;
    
    /** アラート内容確認.取引注意情報（銘柄）確認. */
    @NotEmpty(message = "アラート内容確認.取引注意情報（銘柄）確認")
    private String tradingCautionInformation;
    
    /** アラート内容確認.注意情報アラート確認. */
    @NotEmpty(message = "アラート内容確認.注意情報アラート確認")
    private String noteInfoCheckbox;
    
    /** アラート内容確認.お知らせアラート確認. */
    @NotEmpty(message = "アラート内容確認.お知らせアラート確認")
    private String noteLimitCheck;
    
    /** アラート内容確認.増し担保規制確認. */
    @NotEmpty(message = "アラート内容確認.増し担保規制確認")
    private String additionalCollateralRegulationsConfirm;
    
    /** アラート内容確認.逆指値注文即時発火. */
    @NotEmpty(message = "アラート内容確認.逆指値注文即時発火")
    private String methodCheck;
    
    /** アラート内容確認.翌営業日向け注文. */
    @NotEmpty(message = "アラート内容確認.翌営業日向け注文")
    private String nextDayCheck;
    
    /** 注意情報アラート（全角半角）. */
    @NotEmpty(message = "注意情報アラート")
    @Size(max = 50, message = "注意情報アラート")
    private String noticeInfoAlert;
    
    /** お知らせアラート（全角半角）. */
    @NotEmpty(message = "お知らせアラート")
    @Size(max = 50, message = "お知らせアラート")
    private String noticeAlert;
    
    /** 増し担保規制銘柄取引メッセージ. */
    @NotEmpty(message = "増し担保規制銘柄取引メッセージ")
    private String additionalCollateralRegulationConfirmMsg;
    
    /** 逆指値注文即時発火メッセージ. */
    @NotEmpty(message = "逆指値注文即時発火メッセージ")
    private String stopOrderInstantMessage;
    
    /** 現地約定日超過メッセージ. */
    @NotEmpty(message = "現地約定日超過メッセージ")
    private String nextBusinessDayMessage;
    
    /** 取引注意情報（銘柄）メッセージ（全角半角）. */
    @NotEmpty(message = "取引注意情報（銘柄）メッセージ")
    @Size(max = 50, message = "取引注意情報（銘柄）メッセージ")
    private String tradeNoticeInfoBrandMsg;
    
    /** 信用期日. */
    @NotEmpty(message = "信用期日")
    private String marginDueDate;
    
    /** 本日の注意銘柄URL. */
    @NotEmpty(message = "本日の注意銘柄URL")
    private String tradeLimit;
    
    /** 注文番号（数字）. */
    @NotEmpty(message = "注文番号")
    @Pattern(regexp = "0-9", message = "注文番号")
    @Size(min = 18, max = 18, message = "注文番号")
    private String orderNumber;
    
    /** 注文Sub番号（数字）. */
    @Digits(integer = 18, fraction = 0, message = "注文Sub番号")
    @NotEmpty(message = "注文Sub番号")
    @Pattern(regexp = "0-9", message = "注文Sub番号")
    private String orderSubNumber;
    
    /** 取引通貨. */
    @NotEmpty(message = "取引通貨")
    private String tradeCurrency;
    
    /** 注文日時. */
    @DateTimeFormat(pattern = "yy/MM/dd HH:mm:ss")
    @JsonFormat(pattern = "yy/MM/dd HH:mm:ss")
    @NotEmpty(message = "注文日時")
    @Size(min = 20, max = 20, message = "注文日時")
    private String orderDate;
    
    /** 国内約定日. */
    @NotEmpty(message = "国内約定日")
    private String domesticTradeDate;
    
    /** 現地約定日. */
    @NotEmpty(message = "現地約定日")
    private String localTradeDate;
    
}
