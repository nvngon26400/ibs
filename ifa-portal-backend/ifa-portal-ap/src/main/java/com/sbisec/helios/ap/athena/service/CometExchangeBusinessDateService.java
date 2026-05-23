package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.exchange.master.GetIfaExchangeBusinessDateReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetIfaExchangeBusinessDateRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeBusinessDateReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeBusinessDateRes;

public interface CometExchangeBusinessDateService {
    
    /**
     * 営業日情報取得API（IFA向け）
     * 
     */
    public GetIfaExchangeBusinessDateRes getIfaExchangeBusinessDate(GetIfaExchangeBusinessDateReq req) throws Exception;
    
    /**
     * 営業日情報取得API（リテール向け）
     * @param req
     * @return
     * @throws Exception
     */
    public GetExchangeBusinessDateRes getExchangeBusinessDate(GetExchangeBusinessDateReq req) throws Exception;
}
