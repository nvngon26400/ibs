package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.fstock.order.ListShortableStocksReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ListShortableStocksResp;

/**
 * 
 * Comet API 外国株式信用銘柄 Service.
 * <p>
 * 一覧
 * <p>
 * 外国株式信用銘柄サービス - 外国株式信用売建可能銘柄一覧取得API <br>
 * 
 * @author mengzhe.li
 * @date 03/09/2022
 * 
 */
public interface CometForeignStockShortableStockService {
    
    /**
     * 外国株式信用銘柄サービス - 外国株式信用売建可能銘柄一覧取得API.
     * 
     * @param request Httpリクエスト
     * @return 外国株式信用売建可能銘柄情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.ListShortableStocksReq
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.ListShortableStocksResp
     */
    public ListShortableStocksResp listShortableStocks(ListShortableStocksReq request) throws Exception;
    
}
