package com.sbisec.helios.ap.athena.protocol.exchange.master;

import java.util.List;

import lombok.Data;

@Data
public class ListExchangeTradeRatesRes {
    
    /**レート情報*/
    private List<ExchangeTradeRates> exchangeTradeRates;
    
}
