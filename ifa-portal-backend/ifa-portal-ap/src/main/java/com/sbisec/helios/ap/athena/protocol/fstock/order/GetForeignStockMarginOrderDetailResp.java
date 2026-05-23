package com.sbisec.helios.ap.athena.protocol.fstock.order;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginOrderDetail;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.OrderHistory;

/**
 * @Description 外国株式取引サービス - 外国株式信用注文詳細取得API.
 * 
 * @author mengzhe.li
 * @date 02/12/2022
 */
public class GetForeignStockMarginOrderDetailResp {
    
    public GetForeignStockMarginOrderDetailResp() {
    
    }
    
    private MarginOrderDetail marginOrderDetail;// 信用注文詳細情報
    
    private List<OrderHistory> orderHistories;// 注文履歴
    
    /**
     * @return marginOrderDetail 信用注文詳細情報
     */
    public MarginOrderDetail getMarginOrderDetail() {
        
        return marginOrderDetail;
    }
    
    public void setMarginOrderDetail(MarginOrderDetail marginOrderDetail) {
        
        this.marginOrderDetail = marginOrderDetail;
    }
    
    /**
     * @return orderHistories 注文履歴
     */
    public List<OrderHistory> getOrderHistories() {
        
        return orderHistories;
    }
    
    public void setOrderHistories(List<OrderHistory> orderHistories) {
        
        this.orderHistories = orderHistories;
    }
    
}
