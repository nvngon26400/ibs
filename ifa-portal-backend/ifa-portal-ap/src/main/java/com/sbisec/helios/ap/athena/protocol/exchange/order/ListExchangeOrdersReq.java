package com.sbisec.helios.ap.athena.protocol.exchange.order;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;
import com.sbisec.helios.ap.athena.protocol.common.Page;

import lombok.Data;

@Data
public class ListExchangeOrdersReq implements BaseRequest {
    
    public ListExchangeOrdersReq() {
        
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
        
        /** 為替営業日(From) */
        private String businessDateFrom;
        
        /** 為替営業日(To) */
        private String businessDateTo;
        
        /** 注文状況 */
        private String exchangeOrderStatusInput;
        
        /** 通貨コード */
        private String currencyCode;
        
        /**売買区分*/
        private String buySellCode;
        
        /** ページング */
        private Page page;
    }
    
}
