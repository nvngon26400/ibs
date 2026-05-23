package com.sbisec.helios.ap.athena.protocol.exchange.order;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

import lombok.Data;

@Data
public class GetExchangeCancelledOrderInitializationReq implements BaseRequest {
    
    public GetExchangeCancelledOrderInitializationReq() {
        
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
        
        /** 注文番号 */
        private String orderNo;
        
        /** 営業日 "yyyy-MM-dd"形式 */
        private String businessDate;
        
        /** 通貨ペア */
        private String currencyPair;
        
        /** サイクル番号 */
        private int cycleNumber;
        
        /** 注文イベントID */
        private String orderEventId;
        
    }
    
}
