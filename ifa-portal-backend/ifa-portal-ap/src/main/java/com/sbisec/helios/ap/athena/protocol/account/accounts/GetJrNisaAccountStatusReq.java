package com.sbisec.helios.ap.athena.protocol.account.accounts;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

import lombok.Data;

@Data
public class GetJrNisaAccountStatusReq implements BaseRequest {
    
    public GetJrNisaAccountStatusReq() {
        
    }
    
    // headerとparameterインスタンス化
    private Header header = new Header();
    
    private Parameter parameter = new Parameter();
    
    @Data
    public class Header {
        
        public Header() {
            
        }
        
        private String token;
        
    }
    
    @Data
    public class Parameter {
        
        public Parameter() {
            
        }
        
        /** 基準日 */
        private String baseDate;
        
    }
    
}
