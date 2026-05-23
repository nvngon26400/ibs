package com.sbisec.helios.ap.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/** NRI_QueryAccountBalance request header and indata */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListExchangeTradeRatesIn {
    
    @lombok.Getter(onMethod = @__(@JsonProperty("token")))
    @lombok.Setter(onMethod = @__(@JsonProperty("token")))
    private String token;
    
    public ListExchangeTradeRatesIn(String token) {
        
        this.token = token;
    }
    
    public ListExchangeTradeRatesIn() {
        
    }
}
