package com.sbisec.helios.ap.athena.protocol.exchange.master;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.common.Page;
import com.sbisec.helios.ap.athena.protocol.exchange.master.dto.ExchangeTradeCurrency;

import lombok.Data;

@Data
public class ListExchangeTradeCurrenciesRes {
    
    /**通貨アイテム*/
    private List<ExchangeTradeCurrency> currencies;
    
    /**ページング情報*/
    private Page page;
}
