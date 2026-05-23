package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

@Data
public class IfaForeignMarginTradeRepayOrderConfirmA010ApiResponse {

	/** 注文番号（数字）. */
	private String orderNumber;

	/** 注文Sub番号（数字）. */
	private String orderSubNumber;

	/** 取引通貨. */
    private String currencyCode;

	/** 売買区分（全角半角）. */
	private String tradeKbn;

	/** 自動買付区分. */
	private String autoBuyingClassification;

	/** 注文数量. */
	private String orderQuantity;

	/** 価格条件. */
	private String orderPriceKindList;

	/** 注文単価（数値(小数)）. */
	private String hiddenOrderPrice;

	/** 発火条件価格. */
	private String stopOrderPrice;

	/** トレールストップ幅. */
	private String trailStopWidth;

	/** 成行基準価格（全角半角）. */
	private String executeBasePrice;

	/** 期間条件. */
	private String periodRadio;

	/** 期間. */
	private String period;

	/** 預り区分（全角半角）. */
	private String depositType;

	/** 決済方法（半角英数字）. */
	private String kessaiHoho;

	/** 決済通貨. */
	private String settlementCurrencyCode;

	/** 為替レート（数値(小数)）. */
	private String fxRate;

	/** 平均約定単価（数値(小数)）. */
	private String averageTradePrice;

	/** 未約定数量（数値(整数)）. */
	private String unTradeQuantity;

	/** 約定数量（数値(整数)）. */
	private String tradeQuantity;

	/** 約定金額（外貨）（数値(小数)）. */
	private String contractAmountForeign;

	/** 約定金額（円貨）. */
	private String contractAmountJpyAmount;

	/** 受渡金額（外貨）（数値(小数)）. */
	private String foreignDeliveryAmount;

	/** 受渡金額（円貨）（数値(小数)）. */
	private String yenDeliveryAmount;

	/** 受渡金額（約定分）. */
	private String contractDeliveryAmount;

	/** 国内手数料（外貨）※税抜. */
	private String domesticCommForeign;

	/** 国内手数料（円貨）※税抜. */
	private String domesticCommJpAmount;

	/** 国内消費税（外貨）. */
	private String domesticConsumptionTaxForeign;

	/** 国内消費税（円貨）. */
	private String domesticConsumptionTaxJpAmount;

	/** 現地手数料等（外貨）. */
	private String localCommForeign;

	/** 現地手数料等（円貨）. */
	private String localCommJpAmount;

	/** 現地精算金額（外貨）. */
	private String localSettlementAmountForeign;

	/** 現地精算金額（円貨）. */
	private String localSettlementJpAmount;

	/** NISA枠拘束金額. */
	private String nisaRestraintAmount;

	/** 概算譲渡益税（数値(整数)）. */
	private String approximateCapitalGainsTax;

	/** 注文状況（全角半角）. */
	private String orderStatus;

	/** 約定状況. */
	private String tradeStatus;

	/** 発火状況. */
	private String stopOrderStatus;

	/** 国内約定日. */
	private String businessDaysAfterOrder;

	/** 国内受渡日. */
	private String domesticSettlementDate;

	/** 現地約定日. */
	private String localTradeDate;

	/** 現地受渡日. */
	private String foreignSettlementDate;

	/** 注文日時. */
	private String orderDate;

	/** 約定日時. */
	private String tradeDateTime;

	/** 失効日時. */
	private String expiredDateTime;

	/** 株取引区分. */
	private String stockTradeType;

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
	private String transferDepositAmount;

	/** 振替有価証券評価額. */
	private String transferValuableSecurityValuation;

	/** 見積価格（数値(小数)）. */
	private String quotePrice;

	/** 概算諸経費合計. */
	private String approximateCost;

	/** 信用建余力（注文後）. */
	private String marginPositionPowerOrderAfter;

	/** 委託保証金率（注文後預託率）. */
	private String marginDepositRateOrderAfter;

	/** 銘柄種別（全角半角）. */
	private String brandClass;

	/** 注意銘柄. */
	private String tradeLimit;

	/** 取引種別（全角半角）. */
	private String tradeCd;

	/** 返済期限. */
	private String lastTradeDate;

	/** 勧誘区分（全角半角）. */
	private String kanyuKbn;

	/** 受注方法. */
    private String receiveOrderTypeName;

	/** 確認項目.インサイダー確認（半角英数字）. */
    private String checkInsider;

	/** アラート内容確認.取引注意情報（銘柄）確認. */
	private String tradingCautionInformation;

	/** アラート内容確認.注意情報アラート確認. */
	private String noteInfoCheckbox;

	/** アラート内容確認.お知らせアラート確認. */
	private String noteLimitCheck;

	/** アラート内容確認.逆指値注文即時発火. */
	private String methodCheck;

	/** アラート内容確認.翌営業日向け注文. */
	private String nextDayCheck;

	/** 注意情報アラート（全角半角）. */
    private List<String> noticeInfoAlert;

	/** お知らせアラート（全角半角）. */
    private List<String> noticeAlert;

	/** 逆指値注文即時発火メッセージ. */
    private List<String> stopOrderInstantMessage;

	/** 現地約定日超過メッセージ. */
    private List<String> nextBusinessDayMessage;

	/** 取引注意情報（銘柄）メッセージ（全角半角）. */
    private List<String> tradeNoticeInfoBrandMsg;

	/** 市場略名. */
	private String marketAbbreviatedName;

	/** 銘柄コード（半角英数字）. */
	private String brandCode;

	/** 銘柄名（全角半角）. */
	private String brandName;

	/** 本日の注意銘柄URL. */
	private String tradeLimitUrl;

}
