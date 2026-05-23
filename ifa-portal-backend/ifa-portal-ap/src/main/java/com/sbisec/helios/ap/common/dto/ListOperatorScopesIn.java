package com.sbisec.helios.ap.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/** NRI_QueryAccountBalance request header and indata */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListOperatorScopesIn {

	//token
	//operatorId
  @lombok.Getter(onMethod = @__(@JsonProperty("token")))
  @lombok.Setter(onMethod = @__(@JsonProperty("token")))
  private String token;

  @lombok.Getter(onMethod = @__(@JsonProperty("operatorId")))
  @lombok.Setter(onMethod = @__(@JsonProperty("operatorId")))
  private String operatorId;

  public ListOperatorScopesIn(RequestHeader header, QueryAccountBalanceInData indata) {
    this.token = token;
    this.operatorId = operatorId;
  }

  public ListOperatorScopesIn() {}
}


