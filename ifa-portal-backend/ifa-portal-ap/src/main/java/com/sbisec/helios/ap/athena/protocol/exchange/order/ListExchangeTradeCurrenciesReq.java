package com.sbisec.helios.ap.athena.protocol.exchange.order;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;
import com.sbisec.helios.ap.athena.protocol.common.Page;

import lombok.Data;

@Data
public class ListExchangeTradeCurrenciesReq implements BaseRequest {
    
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
        
        // APP-GWより部店コード(3桁) + '-' + 顧客コード(7桁)を設定
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
    
    public class Parameter {
        
        public Parameter() {
        
        }
        
        // ページング情報
        private Page page;
        
        /**
         * @return the page
         */
        public Page getPage() {
            
            return page;
        }
        
        /**
         * @param page the page to set
         */
        public void setPage(Page page) {
            
            this.page = page;
        }
    }
    
}
