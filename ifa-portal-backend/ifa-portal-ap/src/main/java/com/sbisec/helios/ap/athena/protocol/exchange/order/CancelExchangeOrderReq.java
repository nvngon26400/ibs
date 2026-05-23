package com.sbisec.helios.ap.athena.protocol.exchange.order;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

import lombok.Data;

@Data
public class CancelExchangeOrderReq implements BaseRequest {
    
    // headerとparameterインスタンス化
    private Header header = new Header();
    
    private Parameter parameter = new Parameter();
    
    @Data
    public class Header {
        
        public Header() {
            
        }
        
        private String token;
        
        private String request_id;
        
    }
    
    @Data
    public class Parameter {
        
        public Parameter() {
            
        }
        
        /** 注文番号 */
        private String orderNo;
        
        /** 売買区分 */
        private String buySellCode;
        
        /** 通貨コード */
        private String currencyCode;
        
        /** 営業日 */
        private String businessDate;
        
        /** サイクル番号 */
        private Integer cycleNumber;
        
        /** 注文イベントID */
        private String orderEventId;
        
    }
}
