package com.sbisec.helios.ap.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/** NRI_QueryAccountBalance outdata */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IfaExchangeTradeRates {
	  
	  @lombok.Getter(onMethod = @__(@JsonProperty("currencyCode")))
	  @lombok.Setter(onMethod = @__(@JsonProperty("currencyCode")))
	  private String currencyCode;
	  
	  @lombok.Getter(onMethod = @__(@JsonProperty("buySellCode")))
	  @lombok.Setter(onMethod = @__(@JsonProperty("buySellCode")))
	  private String buySellCode;
	  
	  @lombok.Getter(onMethod = @__(@JsonProperty("referenceExchangeRate")))
	  @lombok.Setter(onMethod = @__(@JsonProperty("referenceExchangeRate")))
	  private String referenceExchangeRate;

  public IfaExchangeTradeRates() {}
}
