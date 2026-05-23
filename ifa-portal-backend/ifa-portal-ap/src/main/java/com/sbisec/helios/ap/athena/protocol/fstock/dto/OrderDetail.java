package com.sbisec.helios.ap.athena.protocol.fstock.dto;

import java.io.Serializable;

/**
 * 注文詳細情報 Dto.
 *
 * @author xin.li
 * @date 02/14/2022
 */
public class OrderDetail implements Serializable {
    
    private static final long serialVersionUID = 2222896212867416850L;
    
    public OrderDetail() {
    
    }
    
    private Order order; // 注文情報
    
    private Boolean correctable; // 訂正可能判定。true：訂正可/false：訂正不可
    
    private Boolean cancelable; // 取消可能判定。true：取消可/false：取消不可
    
    /**
     * 取得注文情報
     *
     * @return 注文情報
     */
    public Order getOrder() {
        
        return order;
    }
    
    /**
     * 注文情報
     *
     * @param order 注文情報
     */
    public void setOrder(Order order) {
        
        this.order = order;
    }
    
    /**
     * 訂正可能判定
     *
     * @return true：訂正可/false：訂正不可
     */
    public Boolean getCorrectable() {
        
        return correctable;
    }
    
    /**
     * 訂正可能判定
     *
     * @param correctable true：訂正可/false：訂正不可
     */
    public void setCorrectable(Boolean correctable) {
        
        this.correctable = correctable;
    }
    
    /**
     * 取消可能判定
     *
     * @return true：取消可/false：取消不可
     */
    public Boolean getCancelable() {
        
        return cancelable;
    }
    
    /**
     * 取消可能判定
     *
     * @param cancelable true：取消可/false：取消不可
     */
    public void setCancelable(Boolean cancelable) {
        
        this.cancelable = cancelable;
    }
}
