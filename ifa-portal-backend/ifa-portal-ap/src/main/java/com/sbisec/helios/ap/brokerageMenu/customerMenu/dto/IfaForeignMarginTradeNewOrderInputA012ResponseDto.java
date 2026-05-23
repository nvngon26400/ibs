package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.common.Market;
import com.sbisec.helios.ap.athena.protocol.common.Securities;

import lombok.Data;

/**
 * 米株信用取引新規注文入力 A012 レスポンスパラメータ
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaForeignMarginTradeNewOrderInputA012ResponseDto {
    
    /** 注文番号（数字）. */
    private String orderNumber;
    
    /** 注文Sub番号（数字）. */
    private String orderSubNumber;
    
    /** 銘柄情報（全角半角）. */
    private Securities brandInformation;
    
    /** 取引通貨. */
    private String tradeCurrency;
    
    /** 市場情報（全角半角）. */
    private Market marketInformation;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 自動買付区分. */
    private String autobuyKbn;
    
    /** 注文数量. */
    private String foreignQuantity;
    
    /** 価格条件. */
    private String orderPriceKindList;
    
    /** 注文単価（数値(小数)）. */
    private String hiddenOrderPrice;
    
    /** 発火条件価格.(手補正) */
    private String orderInputAreaPriceTermsreversePriceLimitStopOrderPrice;
    
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
    
    /** 決済区分（全角半角）. */
    private String settlementType;
    
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
    
    /** 約定金額(外貨). */
    private String approximatePositionAmount;
    
    /** 約定金額(円貨). */
    private String tradeAmountYen;
    
    /** 受渡金額(外貨). */
    private String deliveryAmountForeign;
    
    /** 受渡金額(円貨). */
    private String deliveryAmountYen;
    
    /** 受渡金額(約定分). */
    private String contractDeliveryAmount;
    
    /** 国内手数料(外貨). */
    private String domesticCommForeign;
    
    /** 国内手数料(円貨). */
    private String domesticCommJpAmount;
    
    /** 国内消費税(外貨). */
    private String domesticConsumptionTaxForeign;
    
    /** 国内消費税(円貨). */
    private String domesticConsumptionTaxJpAmount;
    
    /** 現地手数料等(外貨). */
    private String localCommForeign;
    
    /** 現地手数料等(円貨). */
    private String localCommJpAmount;
    
    /** 現地精算金額(外貨). */
    private String localSettlementAmountForeign;
    
    /** 現地精算金額(円貨). */
    private String localSettlementJpAmount;
    
    /** 概算譲渡益税（数値(整数)）. */
    private String approximateCapitalGainsTax;
    
    /** 注文状況（全角半角）. */
    private String orderStatus;
    
    /** 約定状況. */
    private String tradeStatus;
    
    /** 発火状況. */
    private String stopOrderStatus;
    
    /** 国内約定日. */
    private String domesticTradeDate;
    
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
    private String revocationDataTime;
    
    /** 株取引区分. */
    private String stockTradeType;
    
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
    
    /** 適用金利率. */
    private String applicableInterestRate;
    
    /** 適用貸株料率. */
    private String applicableStockLendingFeeRate;
    
    /** 信用建余力(注文後). */
    private String foreignMarginPositionPower;
    
    /** 委託保証金率(注文後預託率). */
    private String marginDepositRateOrderAfter;
    
    /** 逆指値注文即時発火メッセージ. */
    private String methodCheckMessage;
    
    /** 増し担保規制銘柄取引メッセージ. */
    private String additionalCollateralRegulationBrandTradeMsg;
    
    /** 注意銘柄. */
    private Boolean tradeLimit;
    
    /** 銘柄情報 */
    private IfaForeignMarginTradeNewOrderInputBrand brand;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 市場リスト. */
    private IfaForeignMarginTradeNewOrderInputMarket marketList;
    
    /** 銘柄種別（全角半角）. */
    private String brandClass;
    
    /** 価格基本情報. */
    private List<IfaForeignMarginTradeNewOrderInputPriceBasicInfo> priceBasicInfo;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String receiveOrderType;
    
    /** 重要事項の説明. */
    private String importantMatterType;
    
    /** 英文開示銘柄. */
    private String engPubText;
    
    /** 確認項目. */
    private String checkInsider;
    
    /** 現地約定日超過メッセージ. */
    private String localTradeDateLimitMsg;
    
    /** 注意情報アラート（全角半角）. */
    private String noticeInfoAlert;
    
    /** お知らせアラート（全角半角）. */
    private String noticeAlert;
    
    /** 取引注意情報(銘柄)メッセージ. */
    private String tradeNoticeInformationBrand;

    /** 本日の注意銘柄URL. */
    private String tradeLimitUrl;
    
    /** 休場日URL. */
    private String closedDay;
    
    /** 円貨決済停止日URL. */
    private String yenClosed;
    
    /** 取扱銘柄一覧URL. */
    private String usequityList;
    
    /** お取引注意事項URL. */
    private String tradingAttention;
    
    /** 取引種別. */
    private String tradeCd;
    
}
