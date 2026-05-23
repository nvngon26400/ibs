package com.sbisec.helios.ap.pom.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * ポイント残高照会リクエスト
 *
 */
@Data
public class ReferPointReq {
    
    @JsonProperty("buten_code")
    private final String butenCode;
    
    @JsonProperty("account_no")
    private final String accountNo;
    
    @JsonProperty("data_type")
    private final int dataType;
    
    @JsonProperty("channel_type")
    private final int channelType;
}
