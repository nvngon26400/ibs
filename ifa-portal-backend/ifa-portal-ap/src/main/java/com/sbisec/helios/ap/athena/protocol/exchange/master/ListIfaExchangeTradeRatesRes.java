package com.sbisec.helios.ap.athena.protocol.exchange.master;

import java.util.List;
import com.sbisec.helios.ap.athena.protocol.exchange.master.dto.IfaExchangeTradeRate;

import lombok.Data;

@Data
public class ListIfaExchangeTradeRatesRes {
    
    /**レート情報*/
    private List<IfaExchangeTradeRate> ifaExchangeTradeRates;

}
