package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.common.Market;
import com.sbisec.helios.ap.athena.protocol.common.PriceData;
import com.sbisec.helios.ap.athena.protocol.common.Securities;

import lombok.Data;

/**
 * 外国現物取引注文入力注文確認レスポンス
 *
 * @author SCSK 宇田川達弥
 */
@Data
public class IfaForeignSpotTradeOrderInputA013ResponseDto {
    
    /** 注文番号（数字）. */
    private String orderNumber;
    
    /** 注文Sub番号（数字）. */
    private String orderSubNumber;
    
    /** 銘柄情報. */
    private Securities brandInformation;
    
    /** 取引通貨. */
    private String limitPriceText;
    
    /** 市場情報. */
    private Market marketInformation;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 自動買付区分. */
    private String automaticBuyingCategory;
    
    /** 注文数量. */
    private String orderQuantity;
    
    /** 価格条件. */
    private String priceCondition;
    
    /** 注文単価（数値(小数)）. */
    private String hiddenOrderPrice;
    
    /** 発火条件価格. */
    private String triggerPrice;
    
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
    private String settlementCurrency;
    
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
    private String contractAmountYen;
    
    /** 受渡金額（外貨）（数値(小数)）. */
    private String foreignDeliveryAmount;
    
    /** 受渡金額（円貨）（数値(小数)）. */
    private String yenDeliveryAmount;
    
    /** 受渡金額（約定分）. */
    private String deliveryAmount;
    
    /** 国内手数料（外貨）. */
    private String domesticCommForeign;
    
    /** 国内手数料（円貨）. */
    private String domesticCommJpAmount;
    
    /** 国内消費税（外貨）. */
    private String domesticConsumptionTaxForeign;
    
    /** 国内消費税（円貨）. */
    private String domesticConsumptionTaxYen;
    
    /** 現地手数料等（外貨）. */
    private String foreignFee;
    
    /** 現地手数料等（円貨）. */
    private String yenFee;
    
    /** 現地精算金額（外貨）. */
    private String localSettlementAmountForeign;
    
    /** 現地精算金額（円貨）. */
    private String localSettlementAmountYen;
    
    /** NISA枠拘束金額. */
    private String nisaFrameRestrictionAmount;
    
    /** 概算譲渡益税（数値(整数)）. */
    private String approximateCapitalGainsTax;
    
    /** 注文状況（全角半角）. */
    private String orderStatus;
    
    /** 約定状況. */
    private String tradeStatus;
    
    /** 発火状況. */
    private String orderStopSituation;
    
    /** 国内約定日. */
    private String domesticTradeDate;
    
    /** 国内受渡日. */
    private String domesticSettlementDate;
    
    /** 現地約定日. */
    private String localContractDate;
    
    /** 現地受渡日. */
    private String localDeliveryDate;
    
    /** 注文日時. */
    private String orderDate;
    
    /** 約定日時. */
    private String tradeDateTime;
    
    /** 失効日時. */
    private String expirationDateandTime;
    
    /** 見積価格（数値(小数)）. */
    private String quotePrice;
    
    /** 買付余力（注文後）. */
    private String deliveryAmountExecuted;
    
    /** NISA投資可能枠（注文後）. */
    private String nisaInvestableQuote;
    
    /** 売却可能数（注文後）. */
    private String acPositionAfter;
    
    /** 約定代金ソフトリミット上限超過メッセージ. */
    private String priceLimitCheckText;
    
    /** 逆指値注文即時発火メッセージ. */
    private String methodCheckText;
    
    /** 株価情報. */
    private PriceData stockPriceInformation;
    
    /** 注意銘柄. */
    private String tradeLimit;
    
    /** 市場情報. */
    private IfaForeignSpotTradeOrderInputA013ResponseDtoMarketInfo marketInfo;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 銘柄種別（全角半角）. */
    private String brandClass;
    
    /** 銘柄情報. */
    private IfaForeignSpotTradeOrderInputA013ResponseDtoBrandInfo brandInfo;
    
    /** 価格基本情報. */
    private IfaForeignSpotTradeOrderInputPriceBasicInfo priceBasicInfo;
    
    /** 取引種別（全角半角）. */
    private String buySellTypeName;
    
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
    
    /** 確認項目リスト. */
    private List<IfaForeignSpotTradeOrderInputA013ResponseDtoConfirmItem> confirmItemList;
    
    /** コンプラチェック_判定結果（全角半角）. */
    private String complianceCheckResult;
    
    /** コンプラチェックリスト. */
    private List<IfaForeignSpotTradeOrderInputA013ResponseDtoComplianceCheck> complianceCheckList;
    
    /** 現地約定日超過メッセージ. */
    private String localTradeDateLimitMsg;
    
    /** 注意情報アラート（全角半角）. */
    private String noticeInfoAlert;
    
    /** お知らせアラート（全角半角）. */
    private String noticeAlert;
    
    /** 取引注意情報（銘柄）メッセージ（全角半角）. */
    private String tradeNoticeInfoBrandMsg;
    
    /** 海外ETFアラート. */
    private String overseasEtfAlert;

    /** 本日の注意銘柄URL. */
    private String todayTradeLimitUrl;
    
    /** 休場日URL. */
    private String closedDay;
    
    /** 円貨決済停止日URL. */
    private String yenClosedDateUrl;
    
    /** 取扱銘柄一覧URL. */
    private String listofHandledStocksUrl;
    
    /** お取引注意事項URL. */
    private String noticeofTransactionPrecautionsUrl;
    
}
