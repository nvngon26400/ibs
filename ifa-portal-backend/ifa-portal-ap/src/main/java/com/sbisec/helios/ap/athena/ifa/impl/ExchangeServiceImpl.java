package com.sbisec.helios.ap.athena.ifa.impl;

import com.sbisec.helios.ap.athena.ifa.ExchangeService;
import com.sbisec.helios.ap.athena.protocol.exchange.ifa.order.CreateIfaExchangeOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.ifa.order.CreateIfaExchangeOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ConfirmIfaExchangeCreatedOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ConfirmIfaExchangeCreatedOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetExchangeTradeCurrencyReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetExchangeTradeCurrencyRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetIfaExchangeBusinessDateReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetIfaExchangeBusinessDateRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeCurrenciesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeCurrenciesRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeRatesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeRatesRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListIfaExchangeTradeRatesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListIfaExchangeTradeRatesRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.CancelExchangeOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.CancelExchangeOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ConfirmExchangeCreatedOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ConfirmExchangeCreatedOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.CreateExchangeOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.CreateExchangeOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeBusinessDateReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeBusinessDateRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeCancelledOrderInitializationReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeCancelledOrderInitializationRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ListExchangeOrdersReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ListExchangeOrdersRes;
import com.sbisec.helios.ap.athena.service.CometExchangeBusinessDateService;
import com.sbisec.helios.ap.athena.service.CometExchangeCurrencyService;
import com.sbisec.helios.ap.athena.service.CometExchangeOrderService;
import com.sbisec.helios.ap.athena.service.CometExchangeRateService;
import com.sbisec.helios.ap.athena.utils.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    
    @Autowired
    private CometExchangeOrderService cometExchangeOrderService;
    
    @Autowired
    private CometExchangeCurrencyService cometExchangeCurrencyService;
    
    @Autowired
    private CometExchangeRateService cometExchangeRateService;
    
    @Autowired
    private CometExchangeBusinessDateService cometExchangeBusinessDateService;
    
    @Override
    public CheckExchangeCurrencyServiceStatusRes checkExchangeCurrencyServiceStatus(
            CheckExchangeCurrencyServiceStatusReq req) throws Exception {
        
        return cometExchangeOrderService.checkExchangeCurrencyServiceStatus(req);
    }
    
    @Override
    public CheckExchangeCurrencyServiceStatusRes checkExchangeCurrencyServiceStatusOrderCancel(
            CheckExchangeCurrencyServiceStatusReq req) throws Exception {
        
        return cometExchangeOrderService.checkExchangeCurrencyServiceStatusOrderCancel(req);
    }
    
    @Override
    public GetExchangeTradeCurrencyRes getExchangeTradeCurrency(GetExchangeTradeCurrencyReq req) throws Exception {
        
        return cometExchangeCurrencyService.getExchangeTradeCurrency(req);
    }
    
    @Override
    public ListIfaExchangeTradeRatesRes listIfaExchangeTradeRates(String butenCode, String accountNumber, String currencyCode, String buySellCode) throws Exception {
        
        ListIfaExchangeTradeRatesReq request = new ListIfaExchangeTradeRatesReq();
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        request.getParameter().setCurrencyCode(currencyCode);    
        request.getParameter().setBuySellCode(buySellCode);    
        
        return cometExchangeRateService.listIfaExchangeTradeRates(request);
    }
    
    @Override
    public GetIfaExchangeBusinessDateRes getIfaExchangeBusinessDate(GetIfaExchangeBusinessDateReq req)
            throws Exception {
        
        return cometExchangeBusinessDateService.getIfaExchangeBusinessDate(req);
    }
    
    @Override
    public ConfirmIfaExchangeCreatedOrderRes confirmIfaExchangeCreatedOrder(ConfirmIfaExchangeCreatedOrderReq req)
            throws Exception {
        
        return cometExchangeOrderService.confirmIfaExchangeCreatedOrder(req);
    }
    
    @Override
    public ListExchangeTradeRatesRes listExchangeTradeRates(ListExchangeTradeRatesReq req) throws Exception {
        
        return cometExchangeRateService.listExchangeTradeRates(req);
    }
    
    @Override
    public GetExchangeBusinessDateRes getExchangeBusinessDate(GetExchangeBusinessDateReq req) throws Exception {
        
        return cometExchangeBusinessDateService.getExchangeBusinessDate(req);
    }
    
    @Override
    public ConfirmExchangeCreatedOrderRes confirmExchangeCreatedOrder(ConfirmExchangeCreatedOrderReq req)
            throws Exception {
        
        return cometExchangeOrderService.confirmExchangeCreatedOrder(req);
    }
    
    @Override
    public ListExchangeOrdersRes listExchangeOrders(ListExchangeOrdersReq req) throws Exception {
        
        return cometExchangeOrderService.listExchangeOrders(req);
    }
    
    @Override
    public ListExchangeTradeCurrenciesRes listExchangeTradeCurrencies(
            ListExchangeTradeCurrenciesReq listExchangeTradeCurrenciesReq) throws Exception {
        
        return cometExchangeCurrencyService.ListExchangeTradeCurrencies(listExchangeTradeCurrenciesReq);
    }

    @Override
    public CreateExchangeOrderRes createExchangeOrder(CreateExchangeOrderReq req) throws Exception {
        
        return cometExchangeOrderService.createExchangeOrder(req);
        
    }
    
    @Override
    public GetExchangeCancelledOrderInitializationRes getExchangeCancelledOrderInitialization(
            GetExchangeCancelledOrderInitializationReq req) throws Exception {
        
        return cometExchangeOrderService.getExchangeCancelledOrderInitialization(req);
    }
    
    @Override
    public CancelExchangeOrderRes cancelExchangeOrder(CancelExchangeOrderReq req) throws Exception {
        
        return cometExchangeOrderService.cancelExchangeOrder(req);
    }
    
    @Override
    public CreateIfaExchangeOrderRes createIfaExchangeOrder(CreateIfaExchangeOrderReq req) throws Exception {
        
        return cometExchangeOrderService.createIfaExchangeOrder(req);
    }
    
}
