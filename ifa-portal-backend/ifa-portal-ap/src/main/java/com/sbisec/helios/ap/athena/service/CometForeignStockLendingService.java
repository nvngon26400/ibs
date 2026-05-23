package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.fstock.lending.GetForeignStockLendingSubscribedStatusReq;
import com.sbisec.helios.ap.athena.protocol.fstock.lending.GetForeignStockLendingSubscribedStatusResp;

/**
 * Comet API 外国株式貸株サービス Service.
 *
 * @author SCSK川崎
 * @date 03/22/2024
 * 
 */
public interface CometForeignStockLendingService {
    
    /**
     * 外国株式貸株サービス - 外国株式貸株サービス加入判定API.
     *
     * @param request Httpリクエスト
     * @return 外国株式貸株サービス加入状況
     * @throws Exception 異常
     * 
     * @see com.sbisec.helios.ap.athena.protocol.fstock.lending.GetForeignStockLendingSubscribedStatusReq
     * @see com.sbisec.helios.ap.athena.protocol.fstock.lending.GetForeignStockLendingSubscribedStatusResp
     */
    public GetForeignStockLendingSubscribedStatusResp getForeignStockLendingSubscribedStatus(
            GetForeignStockLendingSubscribedStatusReq request) throws Exception;
}
