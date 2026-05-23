package com.sbisec.helios.ap.common.dto.yanap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbisec.helios.ap.common.dto.DtoIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstimateCapabilityInData;
import com.sbisec.helios.ap.common.dto.RequestHeader;

import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstimateCapabilityIn;


public class NriQueryMgEstimateCapabilityIn extends DtoIn {


    @Override
    public String getApi() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public boolean getRetry() {
        // TODO 自動生成されたメソッド・スタブ
        return false;
    }

    @lombok.Getter(onMethod = @__(@JsonProperty("header")))
    @lombok.Setter(onMethod = @__(@JsonProperty("header")))
    private RequestHeader header;

    @lombok.Getter(onMethod = @__(@JsonProperty("indata")))
    @lombok.Setter(onMethod = @__(@JsonProperty("indata")))
    private QueryMgEstimateCapabilityInData indata;

    
    public NriQueryMgEstimateCapabilityIn(RequestHeader header, QueryMgEstimateCapabilityInData queryMgEstimateCapabilityInData) {
        // TODO 自動生成されたコンストラクター・スタブ
    }

    /**
     * デフォルトコンストラクタ。(デシリアライズ用)
     */
    public NriQueryMgEstimateCapabilityIn() {
    }

}
