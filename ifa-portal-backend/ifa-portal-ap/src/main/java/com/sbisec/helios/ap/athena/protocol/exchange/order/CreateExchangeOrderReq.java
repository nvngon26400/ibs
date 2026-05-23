package com.sbisec.helios.ap.athena.protocol.exchange.order;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

import lombok.Data;

@Data
public class CreateExchangeOrderReq implements BaseRequest {
    
    public CreateExchangeOrderReq() {
        
    }
    
    // headerとparameterインスタンス化
    private Header header = new Header();
    
    private Parameter parameter = new Parameter();
    
    @Data
    public class Header {
        
        public Header() {
            
        }
        
        private String token;
        
        private String operator_id;
        
        private String request_id;
    }
    
    @Data
    public class Parameter {
        
        public Parameter() {
            
        }
        
        /** 通貨コード */
        private String currencyCode;
        
        /** 売買区分 */
        private String buySellCode;
        
        /** 口座分類 */
        private String accountKind;
        
        /** 為替注文金額 */
        private String orderAmount;
        
        /** 売却方法区分 */
        private String sellMethod;
        
        /** 預り区分 */
        private String depositType;
        
    }
    
}
