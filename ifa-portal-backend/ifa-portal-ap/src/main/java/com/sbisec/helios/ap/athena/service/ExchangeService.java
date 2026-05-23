package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.account.client_entry.cashing.MultiGetPossibleWithdrawalsReq;
import com.sbisec.helios.ap.athena.protocol.account.client_entry.cashing.MultiGetPossibleWithdrawalsRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ConfirmIfaExchangeCreatedOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ConfirmIfaExchangeCreatedOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetExchangeTradeCurrencyReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetExchangeTradeCurrencyRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeRatesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeRatesRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ConfirmExchangeCreatedOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ConfirmExchangeCreatedOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.CreateExchangeOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.CreateExchangeOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeBusinessDateReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeBusinessDateRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ListExchangeOrdersReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ListExchangeOrdersRes;

public interface ExchangeService {
    
    /**
     * 通貨別サービスステータスチェックAPI
     * 
     */
    public CheckExchangeCurrencyServiceStatusRes checkExchangeCurrencyServiceStatus(
            CheckExchangeCurrencyServiceStatusReq req) throws Exception;
    
    /**
     * 通貨別サービスステータスチェックAPI
     * 
     */
    public GetExchangeTradeCurrencyRes getExchangeTradeCurrency(GetExchangeTradeCurrencyReq req) throws Exception;
    
    /**
     * 出金可能金額一括取得API
     * 
     */
    public MultiGetPossibleWithdrawalsRes multiGetPossibleWithdrawals(MultiGetPossibleWithdrawalsReq req)
            throws Exception;
    
    /**
     * 為替注文確認API（IFA向け）
     * 
     */
    public ConfirmIfaExchangeCreatedOrderRes confirmIfaExchangeCreatedOrder(ConfirmIfaExchangeCreatedOrderReq req)
            throws Exception;
    
    /**
     * 為替取引レート情報一覧取得API（リテール向け）
     * 
     */
    public ListExchangeTradeRatesRes listExchangeTradeRates(ListExchangeTradeRatesReq req) throws Exception;
    
    /**
     * 営業日情報取得API（リテール向け）
     * @param req
     * @return
     * @throws Exception
     */
    public GetExchangeBusinessDateRes getExchangeBusinessDate(GetExchangeBusinessDateReq req) throws Exception;
    
    /**
     * 為替注文確認API（リテール向け）
     * @param req
     * @return
     */
    public ConfirmExchangeCreatedOrderRes confirmExchangeCreatedOrder(ConfirmExchangeCreatedOrderReq req)
            throws Exception;
    
    /**
     * 為替注文一覧取得API
     * @param req
     * @return
     * @throws Exception
     */
    public ListExchangeOrdersRes listExchangeOrders(ListExchangeOrdersReq req) throws Exception;
    
    /**
     * 為替注文登録API（リテール向け）
     * @param req
     * @return
     * @throws Exception
     */
    public CreateExchangeOrderRes createExchangeOrder(CreateExchangeOrderReq req) throws Exception;
    
}
