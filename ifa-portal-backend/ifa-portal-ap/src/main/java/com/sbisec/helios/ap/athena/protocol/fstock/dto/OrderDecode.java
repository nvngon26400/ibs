package com.sbisec.helios.ap.athena.protocol.fstock.dto;

import java.io.Serializable;

import com.sbisec.helios.ap.athena.protocol.common.Market;
import com.sbisec.helios.ap.athena.protocol.common.Securities;

/**
 * 
 * @Description:注文情報 dto
 * @author shuchen.xin
 * @date: 02/16/2022
 */
public class OrderDecode implements Serializable {
    
    private static final long serialVersionUID = 8550335413347405184L;
    
    public OrderDecode() {
        
    }
    
    // 注文番号
    private String orderNo;
    
    // 注文Sub番号
    private String orderSubNo;
    
    // 銘柄情報
    private Securities securities;
    
    // 銘柄上場ステータス
    private String listedSecuritiesStatus;
    
    // 市場情報
    private Market market;
    
    // 株取引区分
    private String stockTradeType;
    
    // 信用期日
    private String marginCloseLimitType;
    
    // 売買区分
    private String buySellCode;
    
    // 自動買付区分
    private String autoStockOrderType;
    
    // 注文数量
    private String orderQuantity;
    
    // 価格条件
    private String orderPriceKindCode;
    
    // 注文単価
    private String orderPrice;
    
    // 発火条件価格
    private String stopPrice;
    
    // トレールストップ幅
    private String trailingStopAmount;
    
    // 期間条件
    private String orderLimitCode;
    
    // 期間 "yyyy-MM-dd"形式
    private String orderTerm;
    
    // 預り区分
    private String specificAccountCode;
    
    // 決済方法
    private String settlementMethodCode;
    
    // 平均約定単価
    private String executionAveragePrice;
    
    // 未約定数量
    private String unexecutedQuantity;
    
    // 約定数量
    private String executionQuantity;
    
    // 注文状況
    private String orderStatus;
    
    // 約定状況
    private String executionStatus;
    
    // 発火状況
    private String workingStatus;
    
    // 注文日時
    private String orderInputDatetime;
    
    // 訂正可能判定
    private Boolean correctable;
    
    // 取消可能判定
    private Boolean cancelable;
    
    // 手数料適用区分
    private String commissionType;
    
    /**
     * @return 注文番号
     */
    public String getOrderNo() {
        
        return orderNo;
    }
    
    /**
     * @param orderNo the orderNo to set
     */
    public void setOrderNo(String orderNo) {
        
        this.orderNo = orderNo;
    }
    
    /**
     * @return 注文Sub番号
     */
    public String getOrderSubNo() {
        
        return orderSubNo;
    }
    
    /**
     * @param orderSubNo the orderSubNo to set
     */
    public void setOrderSubNo(String orderSubNo) {
        
        this.orderSubNo = orderSubNo;
    }
    
    /**
     * @return 銘柄情報
     */
    public Securities getSecurities() {
        
        return securities;
    }
    
    /**
     * @param securities the securities to set
     */
    public void setSecurities(Securities securities) {
        
        this.securities = securities;
    }
    
    /**
     * @return enums - 銘柄上場状況 - ListedSecuritiesStatus
     */
    public String getListedSecuritiesStatus() {
        
        return listedSecuritiesStatus;
    }
    
    /**
     * @param listedSecuritiesStatus the listedSecuritiesStatus to set
     */
    public void setListedSecuritiesStatus(String listedSecuritiesStatus) {
        
        this.listedSecuritiesStatus = listedSecuritiesStatus;
    }
    
    /**
     * @return 市場
     */
    public Market getMarket() {
        
        return market;
    }
    
    /**
     * @param market the market to set
     */
    public void setMarket(Market market) {
        
        this.market = market;
    }
    
    /**
     * @return enums - 株取引区分 - StockTradeType
     */
    public String getStockTradeType() {
        
        return stockTradeType;
    }
    
    /**
     * @param stockTradeType the stockTradeType to set
     */
    public void setStockTradeType(String stockTradeType) {
        
        this.stockTradeType = stockTradeType;
    }
    
    /**
     * @return enums - 信用期日 - MarginCloseLimitType
     */
    public String getMarginCloseLimitType() {
        
        return marginCloseLimitType;
    }
    
    /**
     * @param marginCloseLimitType the marginCloseLimitType to set
     */
    public void setMarginCloseLimitType(String marginCloseLimitType) {
        
        this.marginCloseLimitType = marginCloseLimitType;
    }
    
    /**
     * @return enums - 売買区分 - BuySell
     */
    public String getBuySellCode() {
        
        return buySellCode;
    }
    
    /**
     * @param buySellCode the buySellCode to set
     */
    public void setBuySellCode(String buySellCode) {
        
        this.buySellCode = buySellCode;
    }
    
    /**
     * @return enums - 自動買付区分 - AutoStockOrderType
     */
    public String getAutoStockOrderType() {
        
        return autoStockOrderType;
    }
    
    /**
     * @param autoStockOrderType the autoStockOrderType to set
     */
    public void setAutoStockOrderType(String autoStockOrderType) {
        
        this.autoStockOrderType = autoStockOrderType;
    }
    
    /**
     * @return 注文数量
     */
    public String getOrderQuantity() {
        
        return orderQuantity;
    }
    
    /**
     * @param orderQuantity the orderQuantity to set
     */
    public void setOrderQuantity(String orderQuantity) {
        
        this.orderQuantity = orderQuantity;
    }
    
    /**
     * @return enums - 価格条件 - OrderPriceKind
     */
    public String getOrderPriceKindCode() {
        
        return orderPriceKindCode;
    }
    
    /**
     * @param orderPriceKindCode the orderPriceKindCode to set
     */
    public void setOrderPriceKindCode(String orderPriceKindCode) {
        
        this.orderPriceKindCode = orderPriceKindCode;
    }
    
    /**
     * @return 注文単価
     */
    public String getOrderPrice() {
        
        return orderPrice;
    }
    
    /**
     * @param orderPrice the orderPrice to set
     */
    public void setOrderPrice(String orderPrice) {
        
        this.orderPrice = orderPrice;
    }
    
    /**
     * @return 発火条件価格
     */
    public String getStopPrice() {
        
        return stopPrice;
    }
    
    /**
     * @param stopPrice the stopPrice to set
     */
    public void setStopPrice(String stopPrice) {
        
        this.stopPrice = stopPrice;
    }
    
    /**
     * @return トレールストップ幅
     */
    public String getTrailingStopAmount() {
        
        return trailingStopAmount;
    }
    
    /**
     * @param trailingStopAmount the trailingStopAmount to set
     */
    public void setTrailingStopAmount(String trailingStopAmount) {
        
        this.trailingStopAmount = trailingStopAmount;
    }
    
    /**
     * @return enums - 期間条件 - OrderLimit
     */
    public String getOrderLimitCode() {
        
        return orderLimitCode;
    }
    
    /**
     * @param orderLimitCode the orderLimitCode to set
     */
    public void setOrderLimitCode(String orderLimitCode) {
        
        this.orderLimitCode = orderLimitCode;
    }
    
    /**
     * @return 期間 "yyyy-MM-dd"形式
     */
    public String getOrderTerm() {
        
        return orderTerm;
    }
    
    /**
     * @param orderTerm the orderTerm to set
     */
    public void setOrderTerm(String orderTerm) {
        
        this.orderTerm = orderTerm;
    }
    
    /**
     * @return enums - 預り区分 - SpecificAccount
     */
    public String getSpecificAccountCode() {
        
        return specificAccountCode;
    }
    
    /**
     * @param specificAccountCode the specificAccountCode to set
     */
    public void setSpecificAccountCode(String specificAccountCode) {
        
        this.specificAccountCode = specificAccountCode;
    }
    
    /**
     * @return enums - 決済方法 - SettlementMethod
     */
    public String getSettlementMethodCode() {
        
        return settlementMethodCode;
    }
    
    /**
     * @param settlementMethodCode the settlementMethodCode to set
     */
    public void setSettlementMethodCode(String settlementMethodCode) {
        
        this.settlementMethodCode = settlementMethodCode;
    }
    
    /**
     * @return 平均約定単価
     */
    public String getExecutionAveragePrice() {
        
        return executionAveragePrice;
    }
    
    /**
     * @param executionAveragePrice the executionAveragePrice to set
     */
    public void setExecutionAveragePrice(String executionAveragePrice) {
        
        this.executionAveragePrice = executionAveragePrice;
    }
    
    /**
     * @return 未約定数量
     */
    public String getUnexecutedQuantity() {
        
        return unexecutedQuantity;
    }
    
    /**
     * @param unexecutedQuantity the unexecutedQuantity to set
     */
    public void setUnexecutedQuantity(String unexecutedQuantity) {
        
        this.unexecutedQuantity = unexecutedQuantity;
    }
    
    /**
     * @return 約定数量
     */
    public String getExecutionQuantity() {
        
        return executionQuantity;
    }
    
    /**
     * @param executionQuantity the executionQuantity to set
     */
    public void setExecutionQuantity(String executionQuantity) {
        
        this.executionQuantity = executionQuantity;
    }
    
    /**
     * @return enums - 注文状況 - OrderStatus
     */
    public String getOrderStatus() {
        
        return orderStatus;
    }
    
    /**
     * @param orderStatus the orderStatus to set
     */
    public void setOrderStatus(String orderStatus) {
        
        this.orderStatus = orderStatus;
    }
    
    /**
     * @return enums - 約定状況 - ExecutionStatus
     */
    public String getExecutionStatus() {
        
        return executionStatus;
    }
    
    /**
     * @param executionStatus the executionStatus to set
     */
    public void setExecutionStatus(String executionStatus) {
        
        this.executionStatus = executionStatus;
    }
    
    /**
     * @return enums - 発火状況 - WorkingStatus
     */
    public String getWorkingStatus() {
        
        return workingStatus;
    }
    
    /**
     * @param workingStatus the workingStatus to set
     */
    public void setWorkingStatus(String workingStatus) {
        
        this.workingStatus = workingStatus;
    }
    
    /**
     * @return 注文日時
     */
    public String getOrderInputDatetime() {
        
        return orderInputDatetime;
    }
    
    /**
     * @param orderInputDatetime the orderInputDatetime to set
     */
    public void setOrderInputDatetime(String orderInputDatetime) {
        
        this.orderInputDatetime = orderInputDatetime;
    }
    
    /**
     * @return 訂正可能判定。true：訂正可/false：訂正不可
     */
    public Boolean getCorrectable() {
        
        return correctable;
    }
    
    /**
     * @param correctable the correctable to set
     */
    public void setCorrectable(Boolean correctable) {
        
        this.correctable = correctable;
    }
    
    /**
     * @return 取消可能判定。rue：取消可/false：取消不可
     */
    public Boolean getCancelable() {
        
        return cancelable;
    }
    
    /**
     * @param cancelable the cancelable to set
     */
    public void setCancelable(Boolean cancelable) {
        
        this.cancelable = cancelable;
    }
    
    /**
     * @return 手数料適用区分
     */
    public String getCommissionType() {
        
        return commissionType;
    }
    
    /**
     * @param commissionType the commissionType to set
     */
    public void setCommissionType(String commissionType) {
        
        this.commissionType = commissionType;
    }
}
