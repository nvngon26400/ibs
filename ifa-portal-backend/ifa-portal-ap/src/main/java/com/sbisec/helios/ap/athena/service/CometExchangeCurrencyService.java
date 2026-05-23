package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.exchange.master.GetExchangeTradeCurrencyReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetExchangeTradeCurrencyRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeCurrenciesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeCurrenciesRes;

/**
 * Comet API 為替マスタサービス Service
 * 為替マスタサービス - 為替取引通貨マスタ一覧取得API
 *
 * @author SCSK
 *
 */
public interface CometExchangeCurrencyService extends com.sbibits.earth.service.Service {
    
    /**
     * 通貨別サービスステータスチェックAPI
     * 
     */
    public GetExchangeTradeCurrencyRes getExchangeTradeCurrency(GetExchangeTradeCurrencyReq req) throws Exception;
    
    /**
     * 為替マスタサービス - 為替取引通貨マスタ一覧取得API
     *
     * @param listExchangeTradeCurrenciesReq HTTPリクエスト
     * @returnListExchangeTradeCurrenciesRes 為替取引通貨マスタ一覧取得
     * @throws Exception 異常
     */
    ListExchangeTradeCurrenciesRes ListExchangeTradeCurrencies(
            ListExchangeTradeCurrenciesReq listExchangeTradeCurrenciesReq) throws Exception;
}
