package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginOrder;

/**
 * 外国株式取引サービス - 外国株式信用注文取消登録API.
 * 
 * @author shuchen.xin
 * @date: 02/09/2022
 */
public class DeleteForeignStockMarginOrderResp {
    
    public DeleteForeignStockMarginOrderResp() {
    
    }
    
    private MarginOrder marginOrder;// 信用注文情報
    
    /**
     * @return marginOrder 信用注文情報
     */
    public MarginOrder getMarginOrder() {
        
        return marginOrder;
    }
    
    public void setMarginOrder(MarginOrder marginOrder) {
        
        this.marginOrder = marginOrder;
    }
    
}
