package com.sbisec.helios.ap.common.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/** NRI_QueryAccountBalance outdata */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListExchangeTradeCurrenciesOutData {
    
    @lombok.Getter(onMethod = @__(@JsonProperty("currencyCode")))
    @lombok.Setter(onMethod = @__(@JsonProperty("currencyCode")))
    private String currencyCode;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("currencyName")))
    @lombok.Setter(onMethod = @__(@JsonProperty("currencyName")))
    private String currencyName;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("decimalLength")))
    @lombok.Setter(onMethod = @__(@JsonProperty("decimalLength")))
    private Integer decimalLength;

    @lombok.Getter(onMethod = @__(@JsonProperty("deadlines1")))
    @lombok.Setter(onMethod = @__(@JsonProperty("deadlines1")))
    private String deadlines1;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("deadlines2")))
    @lombok.Setter(onMethod = @__(@JsonProperty("deadlines2")))
    private String deadlines2;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("fxTrade")))
    @lombok.Setter(onMethod = @__(@JsonProperty("fxTrade")))
    private String fxTrade;

	@lombok.Getter(onMethod = @__(@JsonProperty("currencies")))
	@lombok.Setter(onMethod = @__(@JsonProperty("currencies")))
	private List<Currencies> currencies;
    
    public ListExchangeTradeCurrenciesOutData() {}
}
