package com.sbisec.helios.ap.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/** EC Gateway Request Header */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestHeader {

  @lombok.Getter(onMethod = @__(@JsonProperty("buten_cd")))
  @lombok.Setter(onMethod = @__(@JsonProperty("buten_cd")))
  private String butenCd;

  public RequestHeader(String butenCd) {
    this.butenCd = butenCd;
  }

  public RequestHeader() {}
}
