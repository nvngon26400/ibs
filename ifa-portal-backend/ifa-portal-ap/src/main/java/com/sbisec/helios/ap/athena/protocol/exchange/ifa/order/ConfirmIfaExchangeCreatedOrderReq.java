package com.sbisec.helios.ap.athena.protocol.exchange.ifa.order;

import com.sbisec.helios.ap.athena.enums.BuySell;
import com.sbisec.helios.ap.athena.enums.CurrencyCode;
import com.sbisec.helios.ap.athena.protocol.BaseRequest;

import lombok.Data;

@Data
public class ConfirmIfaExchangeCreatedOrderReq implements BaseRequest {
    
    public ConfirmIfaExchangeCreatedOrderReq() {
        
    }
    
    // headerとparameterインスタンス化
    private Header header = new Header();
    
    private Parameter parameter = new Parameter();
    
    @Data
    public class Header {
        
        public Header() {
            
        }
        
        private String token;
        
        public String getToken() {
            
            return token;
        }
        
        /**
         * @param token 「必須」 部店コード(3桁) + '-' + 顧客コード(7桁)を設定
         */
        public void setToken(String token) {
            
            this.token = token;
        }
    }
    
    @Data
    public class Parameter {
        
        public Parameter() {
            
        }
        
        /** 通貨コード*/
        private CurrencyCode currencyCode;
        
        /**売買区分*/
        private BuySell buySellCode;
        
        /** 為替注文金額（外貨） */
        private String frnOrderAmount;
    }
    
}
