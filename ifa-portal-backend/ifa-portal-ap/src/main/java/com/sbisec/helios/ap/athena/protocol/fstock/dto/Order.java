package com.sbisec.helios.ap.athena.protocol.fstock.dto;

import java.io.Serializable;

import com.sbisec.helios.ap.athena.protocol.common.Market;
import com.sbisec.helios.ap.athena.protocol.common.Securities;

import lombok.Data;

/**
 * @Description 注文情報 Dto.
 * 
 * @author shuchen.xin
 * @date 01/05/2022
 */
@Data
public class Order implements Serializable {
    
    private static final long serialVersionUID = -181685067076191022L;    
    
    private String orderNo;// 注文番号
    
    private String orderSubNo;// 注文Sub番号
    
    private Securities securities;// 銘柄情報
    
    private String tradeCurrencyCode;// 取引通貨
    
    private Market market;// 市場情報
    
    private String buySellCode;// 売買区分
    
    private String autoStockOrderType;// 自動買付区分
    
    private String orderQuantity;// 注文数量
    
    private String orderPriceKindCode;// 価格条件
    
    private String orderPrice;// 注文単価
    
    private String stopPrice;// 発火条件価格
    
    private String trailingStopAmount;// トレールストップ幅
    
    private String noLimitPrice;// 成行基準価格
    
    private String orderLimitCode;// 期間条件
    
    private String orderTerm;// 期間
    
    private String specificAccountCode;// 預り区分
    
    private String settlementMethodCode;// 決済方法
    
    private String settlementCurrencyCode;// 決済通貨
    
    private String exchangeRate;// 為替レート
    
    private String executionAveragePrice;// 平均約定単価
    
    private String unexecutedQuantity;// 未約定数量
    
    private String executionQuantity;// 約定数量
    
    private String frnGrossAmount;// 約定金額（外貨）
    
    private String grossAmount;// 約定金額（円貨）
    
    private String frnNetAmount;// 受渡金額（外貨）
    
    private String netAmount;// 受渡金額（円貨）
    
    private String executionNetAmount;// 受渡金額（約定分）
    
    private String frnCommissionAmount;// 国内手数料（外貨）※税抜
    
    private String commissionAmount;// 国内手数料（円貨）※税抜
    
    private String frnCommissionCtax;// 国内消費税（外貨）
    
    private String commissionCtax;// 国内消費税（円貨）
    
    private String frnLocalCharge;// 現地手数料等（外貨）
    
    private String localCharge;// 現地手数料等（円貨）
    
    private String frnLocalNetAmount;// 現地精算金額（外貨）
    
    private String localNetAmount;// 現地精算金額（円貨）
    
    private String nisaFixedAmount;// NISA枠拘束金額
    
    private String specificTax;// 概算譲渡益税
    
    private String orderStatus;// 注文状況
    
    private String executionStatus;// 約定状況
    
    private String workingStatus;// 発火状況
    
    private String tradeDate;// 国内約定日 "yyyy-MM-dd"形式
    
    private String valueDate;// 国内受渡日 "yyyy-MM-dd"形式
    
    private String frnTradeDate;// 現地約定日 "yyyy-MM-dd"形式
    
    private String frnValueDate;// 現地受渡日 "yyyy-MM-dd"形式
    
    private String orderInputDatetime;// 注文日時
    
    private String executionDatetime;// 約定日時
    
    private String expiredDatetime;// 失効日時
    
    private String stockTradeType;// 株取引区分
    
    
}
