package com.sbisec.helios.ap.athena.protocol.exchange.master;

import com.sbisec.helios.ap.athena.annotation.UrlParm;
import com.sbisec.helios.ap.athena.protocol.BaseRequest;

import lombok.Data;

@Data
public class ListOperatorScopesReq implements BaseRequest{
    
    public ListOperatorScopesReq() {
    }
    
    private Header header = new Header();
    
    private Parameter parameter = new Parameter();
    
    @Data
    public class Header {
        
        public Header() {
            
        }
        /**顧客共通情報.部店コード(3桁)+顧客共通情報.口座番号(7桁)*/
        public String token;
        
    }

    @Data
    public class Parameter {
        
        public Parameter() {
            
        }
        
        /**ユーザ共通情報.ccsUserID*/
        @UrlParm(name = "operator_id")
        private String operatorId;
        
    }
    
}
