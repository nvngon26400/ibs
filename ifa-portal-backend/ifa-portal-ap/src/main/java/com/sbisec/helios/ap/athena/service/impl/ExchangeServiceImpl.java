package com.sbisec.helios.ap.athena.service.impl;

import com.sbisec.helios.ap.athena.protocol.account.accounts.GetJrNisaAccountStatusReq;
import com.sbisec.helios.ap.athena.protocol.account.accounts.GetJrNisaAccountStatusRes;
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
import com.sbisec.helios.ap.athena.protocol.exchange.order.ListExchangeTradeCurrenciesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ListExchangeTradeCurrenciesRes;
import com.sbisec.helios.ap.athena.service.CometExchangeCurrencyService;
import com.sbisec.helios.ap.athena.service.ExchangeService;

public class ExchangeServiceImpl implements ExchangeService {
    
    private CometExchangeCurrencyService cometExchangeCurrencyService;
    
    public CheckExchangeCurrencyServiceStatusRes checkExchangeCurrencyServiceStatus(
            CheckExchangeCurrencyServiceStatusReq req) throws Exception {
        
        return null;
    }
    
    public GetExchangeTradeCurrencyRes getExchangeTradeCurrency(GetExchangeTradeCurrencyReq req) throws Exception {
        
        GetExchangeTradeCurrencyRes getExchangeTradeCurrencyRes = new GetExchangeTradeCurrencyRes();
        
        return getExchangeTradeCurrencyRes;
    }
    
    public MultiGetPossibleWithdrawalsRes multiGetPossibleWithdrawals(MultiGetPossibleWithdrawalsReq api007Request)
            throws Exception {
        
        return null;
    }
    
    public ListExchangeTradeRatesRes listExchangeTradeRates(ListExchangeTradeRatesReq req) throws Exception {
        
        return null;
    }
    
    public GetExchangeBusinessDateRes getExchangeBusinessDate(GetExchangeBusinessDateReq req) throws Exception {
        
        return null;
    }
    
    public GetJrNisaAccountStatusRes getJrNisaAccountStatus(GetJrNisaAccountStatusReq req) throws Exception {
        
        return null;
        
    }
    
    public ConfirmExchangeCreatedOrderRes confirmExchangeCreatedOrder(ConfirmExchangeCreatedOrderReq req)
            throws Exception {
        
        return null;
    }
    
    public ListExchangeOrdersRes listExchangeOrders(ListExchangeOrdersReq req) throws Exception {
        
        return null;
    }
    
    public CreateExchangeOrderRes createExchangeOrder(CreateExchangeOrderReq req) throws Exception {
        
        return null;
        
    }
    
    public ConfirmIfaExchangeCreatedOrderRes confirmIfaExchangeCreatedOrder(ConfirmIfaExchangeCreatedOrderReq req)
            throws Exception {
        
        return null;
    }
    
    public ListExchangeTradeCurrenciesRes listExchangeTradeCurrencies(ListExchangeTradeCurrenciesReq req)
            throws Exception {
        
        return null;
        //        return cometExchangeCurrencyService.listExchangeTradeCurrencies(req);
    }
    
}
