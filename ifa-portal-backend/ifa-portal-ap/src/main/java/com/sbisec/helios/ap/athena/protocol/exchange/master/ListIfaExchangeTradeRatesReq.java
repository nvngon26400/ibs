package com.sbisec.helios.ap.athena.protocol.exchange.master;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusReq.Header;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusReq.Parameter;

import lombok.Data;

@Data
public class ListIfaExchangeTradeRatesReq implements BaseRequest{
   
    public ListIfaExchangeTradeRatesReq() {
    }
    
    private Header header = new Header();
    
    private Parameter parameter = new Parameter();
    
    @Data
    public class Header {
        
        public Header() {
            
        }
        /**顧客共通情報.部店コード(3桁)+顧客共通情報.口座番号(7桁)*/
        private String token;
        
    }
    
    @Data
    public class Parameter {
        
        public Parameter() {
            
        }
        
        
        /**通貨コード*/
        private String currencyCode;
        
        /**売買区分*/
        private String buySellCode;
    }
}
