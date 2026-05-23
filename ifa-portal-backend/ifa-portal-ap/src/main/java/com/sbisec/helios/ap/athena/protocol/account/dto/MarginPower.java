package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;

/**
 * 現在の余力情報 Dto.
 * 
 * @author shuchen.xin
 * @date 02/22/2022
 */
public class MarginPower implements Serializable {
    
    private static final long serialVersionUID = 9012158148739362354L;
    
    public MarginPower() {
    
    }
    
    // 委託保証金現金
    private String marginCash;
    
    // 受入委託保証金
    private String grossMargin;
    
    // 信用建余力
    private String marginBuyingPower;
    
    // 振替指示可能額
    private String marginWithdrawable;
    
    // 預託率
    private String depositRate;
    
    /**
     * @return 委託保証金現金
     */
    public String getMarginCash() {
        
        return marginCash;
    }
    
    /**
     * @param marginCash 委託保証金現金
     */
    public void setMarginCash(String marginCash) {
        
        this.marginCash = marginCash;
    }
    
    /**
     * @return 受入委託保証金
     */
    public String getGrossMargin() {
        
        return grossMargin;
    }
    
    /**
     * @param grossMargin 受入委託保証金
     */
    public void setGrossMargin(String grossMargin) {
        
        this.grossMargin = grossMargin;
    }
    
    /**
     * @return 信用建余力
     */
    public String getMarginBuyingPower() {
        
        return marginBuyingPower;
    }
    
    /**
     * @param marginBuyingPower 信用建余力
     */
    public void setMarginBuyingPower(String marginBuyingPower) {
        
        this.marginBuyingPower = marginBuyingPower;
    }
    
    /**
     * @return 振替指示可能額
     */
    public String getMarginWithdrawable() {
        
        return marginWithdrawable;
    }
    
    /**
     * @param marginWithdrawable 振替指示可能額
     */
    public void setMarginWithdrawable(String marginWithdrawable) {
        
        this.marginWithdrawable = marginWithdrawable;
    }
    
    /**
     * @return 預託率
     */
    public String getDepositRate() {
        
        return depositRate;
    }
    
    /**
     * @param depositRate 預託率
     */
    public void setDepositRate(String depositRate) {
        
        this.depositRate = depositRate;
    }
}
