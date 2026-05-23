package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import com.sbisec.helios.ap.athena.protocol.common.Market;
import com.sbisec.helios.ap.athena.protocol.common.Securities;

import lombok.Data;

/**
 * 注文情報DTO
 *
 * @author SCSK 金志
 * 
 */
@Data
public class OrderInfo {
    
    /** 注文番号（数字）. */
    private String orderNumber;
    
    /** 注文Sub番号（数字）. */
    private String orderSubNumber;
    
    /** 銘柄情報（全角半角）. */
    private Securities brandInformation;
    
    /** 取引通貨. */
    private String stopOrderPriceText2;
    
    /** 市場情報（全角半角）. */
    private Market marketInformation;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 自動買付区分. */
    private String autoBuyClassification;
    
    /** 注文数量. */
    private String foreignQuantity;
    
    /** 価格条件. */
    private String orderPriceKindList;
    
    /** 注文単価（数値(小数)）. */
    private String hiddenOrderPrice;
    
    /** 発火条件価格. */
    private String stopOrderPrice2;
    
    /** トレールストップ幅. */
    private String trailStopWidth;
    
    /** 成行基準価格（全角半角）. */
    private String executeBasePrice;
    
    /** 期間条件. */
    private String periodDate;
    
    /** 期間. */
    private String period;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 決済方法（全角半角）. */
    private String kessaiHoho;
    
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
    private String approximatePositionAmount;
    
    /** 受渡金額（外貨）（数値(小数)）. */
    private String foreignDeliveryAmount;
    
    /** 受渡金額（円貨）（数値(整数)）. */
    private String yenDeliveryAmount;
    
    /** 受渡金額（約定分）. */
    private String deliveryPriceTrade;
    
    /** 国内手数料（外貨）. */
    private String foreignApproximateFeeForeign;
    
    /** 国内手数料（円貨）. */
    private String foreignApproximateFee;
    
    /** 国内消費税（外貨）. */
    private String foreignApproximateConsumptionTaxForeign;
    
    /** 国内消費税（円貨）. */
    private String foreignApproximateConsumptionTax;
    
    /** 現地手数料等（外貨）. */
    private String localCommEtcForeign;
    
    /** 現地手数料等（円貨）. */
    private String localCommEtc;
    
    /** 現地精算金額（外貨）. */
    private String localAdjustmentAmountForeign;
    
    /** 現地精算金額（円貨）. */
    private String localAdjustmentAmount;
    
    /** NISA枠拘束金額. */
    private String nisaLimitRestraintAmount;
    
    /** 概算譲渡益税（数値(整数)）. */
    private String approximateCapitalGainsTax;
    
    /** 注文状況（全角半角）. */
    private String orderStatus;
    
    /** 約定状況. */
    private String tradeStatus;
    
    /** 発火状況. */
    private String ignitionStatus;
    
    /** 国内約定日. */
    private String tradeDate;
    
    /** 国内受渡日. */
    private String domesticSettlementDate;
    
    /** 現地約定日. */
    private String contractDate;
    
    /** 現地受渡日. */
    private String localDeliveryDate;
    
    /** 注文日時. */
    private String orderDate;
    
    /** 約定日時. */
    private String tradeDateTime;
    
    /** 失効日時. */
    private String revocationdateTime;
    
    /** 株取引区分. */
    private String stockTradeClassification;
    
}
