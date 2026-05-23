package com.sbisec.helios.ap.athena.protocol.fstock.order;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.fstock.dto.OrderDetail;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.OrderHistory;

/**
 * 外国株式取引サービス - 外国株式現物注文詳細取得API Response.
 *
 * @author xin.li
 * @date: 02/14/2022
 */
public class GetForeignStockOrderDetailResp {
    
    public GetForeignStockOrderDetailResp() {
    
    }
    
    private OrderDetail orderDetail; // 注文詳細情報
    
    private List<OrderHistory> orderHistories; // 注文履歴
    
    /**
     * @return orderDetail 注文詳細情報
     */
    public OrderDetail getOrderDetail() {
        
        return orderDetail;
    }
    
    public void setOrderDetail(OrderDetail orderDetail) {
        
        this.orderDetail = orderDetail;
    }
    
    /**
     * @return orderDetail 注文履歴
     */
    public List<OrderHistory> getOrderHistories() {
        
        return orderHistories;
    }
    
    public void setOrderHistories(List<OrderHistory> orderHistories) {
        
        this.orderHistories = orderHistories;
    }
    
}
