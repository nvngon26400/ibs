package com.sbisec.helios.ap.athena.protocol.exchange.master;

import com.sbisec.helios.ap.athena.annotation.UrlParm;
import com.sbisec.helios.ap.athena.protocol.BaseRequest;

import lombok.Data;

@Data
public class GetIfaExchangeBusinessDateReq implements BaseRequest{
    
    public GetIfaExchangeBusinessDateReq() {

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
