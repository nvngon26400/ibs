package com.sbisec.helios.ap.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/** NRI_QueryAccountBalance response header and outdata */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryAccountBalanceOut {

  @lombok.Getter(onMethod = @__(@JsonProperty("header")))
  @lombok.Setter(onMethod = @__(@JsonProperty("header")))
  private ResponseHeader header;

  @lombok.Getter(onMethod = @__(@JsonProperty("outdata")))
  @lombok.Setter(onMethod = @__(@JsonProperty("outdata")))
  private QueryAccountBalanceOutData outdata;

  public QueryAccountBalanceOut(ResponseHeader header, QueryAccountBalanceOutData outdata) {
    this.header = header;
    this.outdata = outdata;
  }

  public QueryAccountBalanceOut() {}
}
