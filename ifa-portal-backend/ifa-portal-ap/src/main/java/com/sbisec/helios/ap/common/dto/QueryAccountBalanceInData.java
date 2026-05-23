package com.sbisec.helios.ap.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/** NRI_QueryAccountBalance indata */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryAccountBalanceInData {

  @lombok.Getter(onMethod = @__(@JsonProperty("buten_cd")))
  @lombok.Setter(onMethod = @__(@JsonProperty("buten_cd")))
  private String butenCd;

  @lombok.Getter(onMethod = @__(@JsonProperty("koza_no")))
  @lombok.Setter(onMethod = @__(@JsonProperty("koza_no")))
  private String kozaNo;

  public QueryAccountBalanceInData(String butenCd, String kozaNo) {
    this.butenCd = butenCd;
    this.kozaNo = kozaNo;
  }

  public QueryAccountBalanceInData() {}
}
