package com.sbisec.helios.ap.athena.protocol.exchange.master;

import com.sbisec.helios.ap.athena.annotation.UrlParm;
import com.sbisec.helios.ap.athena.protocol.BaseRequest;

import lombok.Data;

@Data
public class GetExchangeTradeCurrencyReq implements BaseRequest {
    
    public GetExchangeTradeCurrencyReq() {

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
        
        /** 通貨コード*/
        @UrlParm(name = "currency_code")
        private String currencyCode;
        
    }
    
}
