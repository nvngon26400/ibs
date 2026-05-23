package com.sbisec.helios.ap.athena.protocol.fstock.dto;

import java.io.Serializable;

/**
 * @Description 信用注文詳細情報.
 * 
 * @author mengzhe.li
 * @date 02/12/2022
 */
public class MarginOrderDetail implements Serializable {
    
    private static final long serialVersionUID = -5130910484215306856L;
    
    public MarginOrderDetail() {
    
    }
    
    private MarginOrder marginOrder; // 信用注文情報
    
    private Boolean correctable; // 訂正可能判定。true：訂正可/false：訂正不可
    
    private Boolean cancelable; // 取消可能判定。true：取消可/false：取消不可
    
    public MarginOrder getMarginOrder() {
        
        return marginOrder;
    }
    
    public void setMarginOrder(MarginOrder marginOrder) {
        
        this.marginOrder = marginOrder;
    }
    
    public Boolean getCorrectable() {
        
        return correctable;
    }
    
    public void setCorrectable(Boolean correctable) {
        
        this.correctable = correctable;
    }
    
    public Boolean getCancelable() {
        
        return cancelable;
    }
    
    public void setCancelable(Boolean cancelable) {
        
        this.cancelable = cancelable;
    }
    
}
