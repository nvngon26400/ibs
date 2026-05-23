package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.fstock.order.GetOffsetBusinessDateReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetOffsetBusinessDateResp;

/**
 * Comet API 外国株式取引サービス Service.
 *       <p>
 *       一覧
 *       <p>
 *       外国株式取引サービス - 外国株式現地営業日取得API（日数指定版）.
 * 
 * @author yunhui.zhao
 * @date 02/14/2022
 */
public interface CometForeignStockFrnTradeDateService {
    
    /**
     * 外国株式取引サービス - 外国株式現地営業日取得API（日数指定版）.
     * 
     * @param getOffsetBusinessDateReq リクエスト
     * @return 外国株式現地営業日情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.GetOffsetBusinessDateReq
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.GetOffsetBusinessDateResp
     */
    public GetOffsetBusinessDateResp getOffsetBusinessDate(GetOffsetBusinessDateReq getOffsetBusinessDateReq)
            throws Exception;
    
}
