package com.sbisec.helios.ap.athena.protocol.fstock.dto;

import java.io.Serializable;

/**
 * 注文履歴 Dto
 *
 */
public class OrderHistory implements Serializable {
    
    private static final long serialVersionUID = -5766320439543497081L;
    
    public OrderHistory() {
    
    }
    
    private String orderTypeCode; // 注文区分
    
    private String executionPrice; // 約定単価
    
    private String executionQuantity; // 約定数量
    
    private String orderPriceKindsCode; // 価格条件
    
    private String orderPrice; // 注文単価
    
    private String stopPrice; // 発火条件価格
    
    private String trailingStopAmount; // トレールストップ幅
    
    private String orderDatetime; // 注文日時
    
    private String executionDatetime; // 約定日時
    
    private String orderDetailStatus; // 注文詳細状況
    
    public String getOrderTypeCode() {
        
        return orderTypeCode;
    }
    
    public void setOrderTypeCode(String orderTypeCode) {
        
        this.orderTypeCode = orderTypeCode;
    }
    
    public String getExecutionPrice() {
        
        return executionPrice;
    }
    
    public void setExecutionPrice(String executionPrice) {
        
        this.executionPrice = executionPrice;
    }
    
    public String getExecutionQuantity() {
        
        return executionQuantity;
    }
    
    public void setExecutionQuantity(String executionQuantity) {
        
        this.executionQuantity = executionQuantity;
    }
    
    public String getOrderPriceKindsCode() {
        
        return orderPriceKindsCode;
    }
    
    public void setOrderPriceKindsCode(String orderPriceKindsCode) {
        
        this.orderPriceKindsCode = orderPriceKindsCode;
    }
    
    public String getOrderPrice() {
        
        return orderPrice;
    }
    
    public void setOrderPrice(String orderPrice) {
        
        this.orderPrice = orderPrice;
    }
    
    public String getStopPrice() {
        
        return stopPrice;
    }
    
    public void setStopPrice(String stopPrice) {
        
        this.stopPrice = stopPrice;
    }
    
    public String getTrailingStopAmount() {
        
        return trailingStopAmount;
    }
    
    public void setTrailingStopAmount(String trailingStopAmount) {
        
        this.trailingStopAmount = trailingStopAmount;
    }
    
    public String getOrderDatetime() {
        
        return orderDatetime;
    }
    
    public void setOrderDatetime(String orderDatetime) {
        
        this.orderDatetime = orderDatetime;
    }
    
    public String getExecutionDatetime() {
        
        return executionDatetime;
    }
    
    public void setExecutionDatetime(String executionDatetime) {
        
        this.executionDatetime = executionDatetime;
    }
    
    public String getOrderDetailStatus() {
        
        return orderDetailStatus;
    }
    
    public void setOrderDetailStatus(String orderDetailStatus) {
        
        this.orderDetailStatus = orderDetailStatus;
    }
    
}
