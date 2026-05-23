package com.sbisec.helios.ap.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/** NRI_QueryAccountBalance outdata */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currencies {
	  
	  @lombok.Getter(onMethod = @__(@JsonProperty("currencyCode")))
	  @lombok.Setter(onMethod = @__(@JsonProperty("currencyCode")))
	  private String currencyCode;
	  
	  @lombok.Getter(onMethod = @__(@JsonProperty("currencyName")))
	  @lombok.Setter(onMethod = @__(@JsonProperty("currencyName")))
	  private String currencyName;
	  
	  @lombok.Getter(onMethod = @__(@JsonProperty("decimalLength")))
	  @lombok.Setter(onMethod = @__(@JsonProperty("decimalLength")))
	  private String decimalLength;

  public Currencies() {}
}
