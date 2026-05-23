package com.sbisec.helios.ap.athena.protocol.exchange.order;

import com.sbisec.helios.ap.athena.annotation.UrlParm;
import com.sbisec.helios.ap.athena.protocol.BaseRequest;

import lombok.Data;

@Data
public class GetExchangeBusinessDateReq implements BaseRequest {
    
    public GetExchangeBusinessDateReq() {

    }
    
    // headerとparameterインスタンス化
    private Header header = new Header();
    
    private Parameter parameter = new Parameter();
    
    @Data
    public class Header {
        
        public Header() {
            
        }
        
    }
    
    @Data
    public class Parameter {
        
        public Parameter() {
            
        }
        
        /** 為替グループ */
        @UrlParm(name = "exchange_group")
        private String exchangeGroup;
    }
    
}
