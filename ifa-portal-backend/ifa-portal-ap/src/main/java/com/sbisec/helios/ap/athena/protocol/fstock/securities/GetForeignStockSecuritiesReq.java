package com.sbisec.helios.ap.athena.protocol.fstock.securities;

import com.sbisec.helios.ap.athena.annotation.UrlParm;
import com.sbisec.helios.ap.athena.protocol.BaseRequest;

import lombok.Data;

/**
 * 外国株式銘柄マスタ取得 Request
 * 
 */
@Data
public class GetForeignStockSecuritiesReq implements BaseRequest {
    
    public GetForeignStockSecuritiesReq() {
        
    }
    
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
        
        /** 国コード */
        @UrlParm(name = "country_code")
        private String countryCode;
        
        /** 銘柄コード */
        @UrlParm(name = "securities_code")
        private String securitiesCode;
    }
}
