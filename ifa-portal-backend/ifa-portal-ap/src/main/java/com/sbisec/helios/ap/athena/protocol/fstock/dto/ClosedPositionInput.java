package com.sbisec.helios.ap.athena.protocol.fstock.dto;

import java.io.Serializable;

/**
 * 返済相手建玉明細情報 Dto.
 * 
 * @author yunhui.zhao
 * @date 02/16/2022
 */
public class ClosedPositionInput implements Serializable {
    
    private static final long serialVersionUID = -5766320439543497081L;
    
    public ClosedPositionInput() {
    
    }
    
    // 売買区分
    private String buySellCode;
    
    // 信用期日
    private String marginCloseLimitType;
    
    // 預り区分
    private String specificAccountCode;
    
    // 国内新規約定日(yyyy-MM-dd)
    private String tradeDate;
    
    // 現地新規約定日(yyyy-MM-dd)
    private String frnTradeDate;
    
    // 建単価
    private String positionPrice;
    
    // 建株数
    private String positionQuantity;
    
    // 返済数量
    private String closeOrderQuantity;
    
    public String getBuySellCode() {
        
        return buySellCode;
    }
    
    /**
     * @param buySellCode 売買区分
     */
    public void setBuySellCode(String buySellCode) {
        
        this.buySellCode = buySellCode;
    }
    
    public String getMarginCloseLimitType() {
        
        return marginCloseLimitType;
    }
    
    /**
     * @param marginCloseLimitType 信用期日
     */
    public void setMarginCloseLimitType(String marginCloseLimitType) {
        
        this.marginCloseLimitType = marginCloseLimitType;
    }
    
    public String getSpecificAccountCode() {
        
        return specificAccountCode;
    }
    
    /**
     * @param specificAccountCode 預り区分
     */
    public void setSpecificAccountCode(String specificAccountCode) {
        
        this.specificAccountCode = specificAccountCode;
    }
    
    public String getTradeDate() {
        
        return tradeDate;
    }
    
    /**
     * @param tradeDate 国内新規約定日(yyyy-MM-dd)
     */
    public void setTradeDate(String tradeDate) {
        
        this.tradeDate = tradeDate;
    }
    
    public String getFrnTradeDate() {
        
        return frnTradeDate;
    }
    
    /**
     * @param frnTradeDate 現地新規約定日(yyyy-MM-dd)
     */
    public void setFrnTradeDate(String frnTradeDate) {
        
        this.frnTradeDate = frnTradeDate;
    }
    
    public String getPositionPrice() {
        
        return positionPrice;
    }
    
    /**
     * @param positionPrice 建単価
     */
    public void setPositionPrice(String positionPrice) {
        
        this.positionPrice = positionPrice;
    }
    
    public String getPositionQuantity() {
        
        return positionQuantity;
    }
    
    /**
     * @param positionPrice 「必須」 建株数
     */
    public void setPositionQuantity(String positionQuantity) {
        
        this.positionQuantity = positionQuantity;
    }
    
    public String getCloseOrderQuantity() {
        
        return closeOrderQuantity;
    }
    
    /**
     * @param closeOrderQuantity 「必須」 返済数量
     */
    public void setCloseOrderQuantity(String closeOrderQuantity) {
        
        this.closeOrderQuantity = closeOrderQuantity;
    }
}
