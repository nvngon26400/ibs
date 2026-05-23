package com.sbisec.helios.ap.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/** */
public enum LoadBalance {
  ROUND_ROBIN("roundrobin"),
  BACKUP("backup"),
  RANDOM("random"),
  ;

  private String value;

  private LoadBalance(String value) {
    this.value = value;
  }

  @JsonValue
  public String getLoadBalance() {
    return this.value;
  }
}
