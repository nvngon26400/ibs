package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginOrder;

/**
 * @Description 外国株式取引サービス - 外国株式信用注文取消初期情報取得API Response.
 * 
 * @author yunhui.zhao
 * @date 02/14/2022
 */
public class GetForeignStockDeletedMarginOrderInitializationResp {
    
    public GetForeignStockDeletedMarginOrderInitializationResp() {
    
    }
    
    // 信用注文情報
    private MarginOrder marginOrder;
    
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
