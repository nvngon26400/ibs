package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.sbisec.helios.ap.athena.annotation.UrlParm;
import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * 外国株式取引サービス - 外国株式現物注文詳細取得API
 *
 * @author xin.li
 * @date: 02/14/2022
 */
public class GetForeignStockOrderDetailReq implements BaseRequest {
    
    public GetForeignStockOrderDetailReq() {
    
    }
    
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
        
        private String token; // {部店}3桁 + "-" + {口座}7桁
        
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
        
        // 注文番号
        @UrlParm(name = "order_no")
        private String orderNo;
        
        public String getOrderNo() {
            
            return orderNo;
        }
        
        /**
         * @param orderNo 「必須」 注文番号
         */
        public void setOrderNo(String orderNo) {
            
            this.orderNo = orderNo;
        }
        
    }
    
}
