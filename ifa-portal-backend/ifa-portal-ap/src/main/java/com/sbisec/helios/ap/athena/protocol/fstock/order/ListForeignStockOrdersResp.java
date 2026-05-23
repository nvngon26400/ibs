package com.sbisec.helios.ap.athena.protocol.fstock.order;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.common.Page;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.OrderDecode;

/**
 * 外国株式注文一覧取得 Response
 * 
 * @author shuchen.xin
 * @date 02/16/2022
 */
public class ListForeignStockOrdersResp {
    
    public ListForeignStockOrdersResp() {
        
    }
    
    // 注文情報
    private List<OrderDecode> orderDecodes;
    
    // ページ
    private Page page;
    
    /**
     * @return 注文情報
     */
    public List<OrderDecode> getOrderDecodes() {
        
        return orderDecodes;
    }
    
    /**
     * @param orderDecode the orderDecode to set
     */
    public void setOrderDecode(List<OrderDecode> orderDecodes) {
        
        this.orderDecodes = orderDecodes;
    }
    
    /**
     * @return ページ
     */
    public Page getPage() {
        
        return page;
    }
    
    /**
     * @param page the page to set
     */
    public void setPage(Page page) {
        
        this.page = page;
    }
    
}
