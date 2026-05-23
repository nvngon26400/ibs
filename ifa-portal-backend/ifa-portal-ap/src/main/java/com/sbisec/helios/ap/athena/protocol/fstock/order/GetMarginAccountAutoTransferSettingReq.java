package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * 外国株式取引サービス - 外国株式信用返済注文登録API Request.
 * 
 * @author xin.huang
 */
public class GetMarginAccountAutoTransferSettingReq implements BaseRequest {
    
    public GetMarginAccountAutoTransferSettingReq() {
    
    }
    
    // headerとparameterインスタンス化
    private Header header = new Header();
    
    private Parameter parameter = new Parameter();
    
    @SuppressWarnings("unchecked")
    @Override
    public Header getHeader() {
        
        return header;
    }
    
    public void setHeader(Header header) {
        
        this.header = header;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Parameter getParameter() {
        
        return parameter;
    }
    
    public void setParameter(Parameter parameter) {
        
        this.parameter = parameter;
    }
    
    public class Header {
        
        public Header() {
        
        }
        
        // token
        private String token;
        
        public String getToken() {
            
            return token;
        }
        
        /**
         * @param token 必須「{部店}3桁 + "-" + {口座}7桁」
         */
        public void setToken(String token) {
            
            this.token = token;
        }
        
    }
    
    public class Parameter {
        
        public Parameter() {
        
        }
    }
}
