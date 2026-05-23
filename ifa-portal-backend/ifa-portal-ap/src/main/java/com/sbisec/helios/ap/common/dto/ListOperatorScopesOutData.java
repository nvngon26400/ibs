package com.sbisec.helios.ap.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/** NRI_QueryAccountBalance outdata */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListOperatorScopesOutData {

	  @lombok.Getter(onMethod = @__(@JsonProperty("operatorScopes")))
	  @lombok.Setter(onMethod = @__(@JsonProperty("operatorScopes")))
	  private String operatorScopes;

  public ListOperatorScopesOutData() {}
}
