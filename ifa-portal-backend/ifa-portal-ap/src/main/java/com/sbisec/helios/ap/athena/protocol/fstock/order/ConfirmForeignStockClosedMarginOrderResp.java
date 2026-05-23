package com.sbisec.helios.ap.athena.protocol.fstock.order;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginOrder;

/**
 * @Description 外国株式取引サービス - 外国株式信用返済注文確認API Response.
 * 
 * @author yunhui.zhao
 * @date 02/14/2022
 */
public class ConfirmForeignStockClosedMarginOrderResp {
    
    public ConfirmForeignStockClosedMarginOrderResp() {
    
    }
    
    // 信用注文情報
    private MarginOrder marginOrder;
    
    // 見積価格
    private String estimatePrice;
    
    // 概算諸経費合計
    private String estimateTotalExpenses;
    
    // 信用建余力（注文後）
    private String marginBuyingPower;
    
    // 委託保証金率（注文後預託率）
    private String depositRate;
    
    // 注文ワーニングステータス
    private List<String> warningStatuses;
    
    /**
     * @return marginOrder 信用注文情報
     */
    public MarginOrder getMarginOrder() {
        
        return marginOrder;
    }
    
    public void setMarginOrder(MarginOrder marginOrder) {
        
        this.marginOrder = marginOrder;
    }
    
    /**
     * @return estimatePrice 見積価格
     */
    public String getEstimatePrice() {
        
        return estimatePrice;
    }
    
    public void setEstimatePrice(String estimatePrice) {
        
        this.estimatePrice = estimatePrice;
    }
    
    /**
     * @return estimateTotalExpenses 概算諸経費合計
     */
    public String getEstimateTotalExpenses() {
        
        return estimateTotalExpenses;
    }
    
    public void setEstimateTotalExpenses(String estimateTotalExpenses) {
        
        this.estimateTotalExpenses = estimateTotalExpenses;
    }
    
    /**
     * @return marginBuyingPower 信用建余力（注文後）
     */
    public String getMarginBuyingPower() {
        
        return marginBuyingPower;
    }
    
    public void setMarginBuyingPower(String marginBuyingPower) {
        
        this.marginBuyingPower = marginBuyingPower;
    }
    
    /**
     * @return depositRate 委託保証金率（注文後預託率）
     */
    public String getDepositRate() {
        
        return depositRate;
    }
    
    public void setDepositRate(String depositRate) {
        
        this.depositRate = depositRate;
    }
    
    /**
     * @return warningStatuses 注文ワーニングステータス
     */
    public List<String> getWarningStatuses() {
        
        return warningStatuses;
    }
    
    public void setWarningStatuses(List<String> warningStatuses) {
        
        this.warningStatuses = warningStatuses;
    }
}
