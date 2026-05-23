package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeRatesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeRatesRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListIfaExchangeTradeRatesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListIfaExchangeTradeRatesRes;

public interface CometExchangeRateService extends com.sbibits.earth.service.Service {
    
    /**
     * 為替取引レート情報一覧取得API（IFA向け）
     * 
     */
    public ListIfaExchangeTradeRatesRes listIfaExchangeTradeRates(ListIfaExchangeTradeRatesReq req) throws Exception;
    
    /**
     * 為替取引レート情報一覧取得API（リテール向け）
     * @param req
     * @return
     * @throws Exception
     */
    public ListExchangeTradeRatesRes listExchangeTradeRates(ListExchangeTradeRatesReq req) throws Exception;
    
}
