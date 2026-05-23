package com.sbisec.helios.ap.athena.protocol.fstock.securities;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.common.Page;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.ForeignStock;

/**
 * @Description:外国株式銘柄サービス - 外国株式銘柄検索API Response.
 * 
 * @author xin.li
 * @date: 02/11/2022
 */
public class ListForeignStockSecuritiesResp {
    
    public ListForeignStockSecuritiesResp() {
    
    }
    
    // 外国株式銘柄情報
    private List<ForeignStock> foreignStocks;
    
    // ページ
    private Page page;
    
    /**
     * @return foreignStocks 外国株式銘柄情報
     */
    public List<ForeignStock> getForeignStocks() {
        
        return foreignStocks;
    }
    
    public void setForeignStocks(List<ForeignStock> foreignStocks) {
        
        this.foreignStocks = foreignStocks;
    }
    
    /**
     * @return page ページ
     */
    public Page getPage() {
        
        return page;
    }
    
    public void setPage(Page page) {
        
        this.page = page;
    }
    
}
