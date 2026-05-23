package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.sbisec.helios.ap.athena.annotation.UrlParm;
import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * @Description 外国株式取引サービス - 外国株式信用注文詳細取得API.
 * 
 * @author mengzhe.li
 * @date 02/012/2022
 */
public class GetForeignStockMarginOrderDetailReq implements BaseRequest {
    
    public GetForeignStockMarginOrderDetailReq() {
    
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
    
    /**
     * @author SCSK
     *
     */
    public class Header {
        
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
    
    /**
     * @author SCSK
     *
     */
    public class Parameter {
        
        @UrlParm(name = "order_sub_no")
        private String orderSubNo;
        
        public String getOrderSubNo() {
            
            return orderSubNo;
        }
        
        /**
         * @param orderSubNo 「必須」 注文サブ番号
         */
        public void setOrderSubNo(String orderSubNo) {
            
            this.orderSubNo = orderSubNo;
        }
        
    }
    
}
