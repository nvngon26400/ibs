package com.sbisec.helios.ap.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/** EC Gateway Response Header */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseHeader {

  @lombok.Getter(onMethod = @__(@JsonProperty("code")))
  @lombok.Setter(onMethod = @__(@JsonProperty("code")))
  private long code;

  @lombok.Getter(onMethod = @__(@JsonProperty("message")))
  @lombok.Setter(onMethod = @__(@JsonProperty("message")))
  private String message;

  public ResponseHeader(long code, String message) {
    this.code = code;
    this.message = message;
  }

  public ResponseHeader() {}
}
