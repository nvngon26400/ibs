package com.sbisec.helios.ap.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/** NRI_QueryAccountBalance request header and indata */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryAccountBalanceIn {

    @lombok.Getter(onMethod = @__(@JsonProperty("header")))
    @lombok.Setter(onMethod = @__(@JsonProperty("header")))
    private RequestHeader header;

    @lombok.Getter(onMethod = @__(@JsonProperty("indata")))
    @lombok.Setter(onMethod = @__(@JsonProperty("indata")))
    private QueryAccountBalanceInData indata;

    public QueryAccountBalanceIn(RequestHeader header, QueryAccountBalanceInData indata) {
        this.header = header;
        this.indata = indata;
    }

    public QueryAccountBalanceIn() {}
}
