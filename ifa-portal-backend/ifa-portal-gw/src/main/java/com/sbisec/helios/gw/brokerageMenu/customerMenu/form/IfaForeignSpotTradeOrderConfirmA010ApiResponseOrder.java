package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 外国現物取引注文確認注文発注APIレスポンス注文情報
 *
 * @author 福岡利基
 */
@Data
public class IfaForeignSpotTradeOrderConfirmA010ApiResponseOrder {
    
    /** 注文番号（数字）. */
    private String orderNumber;
    
    /** 注文Sub番号（数字）. */
    private String orderSubNumber;
    
    /** 取引通貨. */
    private String limitPriceText;
    
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
    
    /** 決済方法（半角英数字）. */
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
    private String domesticforeignFee;
    
    /** 国内手数料（円貨）. */
    private String domesticyenFee;
    
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
    
}
