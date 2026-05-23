package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.sbisec.helios.ap.athena.annotation.UrlParm;
import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * @Description 外国株式取引サービス - 外国株式信用注文取消初期情報取得API Request.
 * 
 * @author yunhui.zhao
 * @date 02/11/2022
 */
public class GetForeignStockDeletedMarginOrderInitializationReq implements BaseRequest {
    
    public GetForeignStockDeletedMarginOrderInitializationReq() {
    
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
        
        // 注文Sub番号
        @UrlParm(name = "order_sub_no")
        private String orderSubNo;
        
        public String getOrderSubNo() {
            
            return orderSubNo;
        }
        
        /**
         * @param orderSubNo 必須「注文Sub番号」
         */
        public void setOrderSubNo(String orderSubNo) {
            
            this.orderSubNo = orderSubNo;
        }
    }
}
