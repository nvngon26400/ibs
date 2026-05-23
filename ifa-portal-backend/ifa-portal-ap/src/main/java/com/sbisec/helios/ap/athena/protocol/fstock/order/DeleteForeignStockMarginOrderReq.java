package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.sbisec.helios.ap.athena.annotation.UrlParm;
import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * 外国株式取引サービス - 外国株式信用注文取消登録API
 * 
 * @author mengzhe.li
 * @date: 02/11/2022
 */
public class DeleteForeignStockMarginOrderReq implements BaseRequest {
    
    public DeleteForeignStockMarginOrderReq() {
    
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
        
        private String token;
        
        private String trade_password;
        
        private String request_id;
        
        public String getToken() {
            
            return token;
        }
        
        /**
         * @param token 「必須」 部店コード(3桁) + '-' + 顧客コード(7桁)を設定
         */
        public void setToken(String token) {
            
            this.token = token;
        }
        
        public String getTrade_password() {
            
            return trade_password;
        }
        
        /**
         * @param trade_password 「必須」 取引パスワード
         */
        public void setTrade_password(String trade_password) {
            
            this.trade_password = trade_password;
        }
        
        public String getRequest_id() {
            
            return request_id;
        }
        
        /**
         * @param request_id 「必須」 Request Id
         */
        public void setRequest_id(String request_id) {
            
            this.request_id = request_id;
        }
        
    }
    
    public class Parameter {
        
        @UrlParm(name = "order_sub_no")
        private String orderSubNo;
        
        public String getOrderSubNo() {
            
            return orderSubNo;
        }
        
        /**
         * @param orderSubNo 「必須」 注文Sub番号
         */
        public void setOrderSubNo(String orderSubNo) {
            
            this.orderSubNo = orderSubNo;
        }
        
    }
}
