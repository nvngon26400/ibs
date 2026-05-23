package com.sbisec.helios.ap.athena.protocol.exchange.master;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

import lombok.Data;

@Data
public class ListExchangeTradeRatesReq implements BaseRequest {
    
    public ListExchangeTradeRatesReq() {

    }
    
    // headerとparameterインスタンス化
    private Header header = new Header();
    
    private Parameter parameter = new Parameter();
    
    @Data
    public class Header {
        
        public Header() {
            
        }
        /**顧客共通情報.部店コード(3桁)+顧客共通情報.口座番号(7桁)*/
        private String token;
        
        /**  オペレーターID */
        private String operator_id;
    }
    
    @Data
    public class Parameter {
        
        public Parameter() {
            
        }
        
        /** 通貨コード*/
        private String currencyCode;
        
        /**売買区分*/
        private String buySellCode;
        
        /** 為替注文金額（外貨） */
        private String frnOrderAmount;
    }
    
}
