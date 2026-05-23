package com.sbisec.helios.ap.athena.protocol.exchange.master;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

import lombok.Data;

@Data
public class CheckExchangeCurrencyServiceStatusReq implements BaseRequest {
    
    public CheckExchangeCurrencyServiceStatusReq() {
        
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
        
        /** サービスグループ */
        private String serviceGroup;
        
        /** サービスタイプ */
        private String serviceType;
        
        /** 通貨コード */
        private String currencyCode;
        
        /** 売買区分 */
        private String buySellCode;
    }
    
}
