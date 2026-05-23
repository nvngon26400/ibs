package com.sbisec.helios.ap.athena.protocol.fstock.order;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.ClosedPositionInput;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginOrderInputForConfirmForeignStockClosedMarginOrder;

/**
 * @Description マーケット価格情報サービス - マーケット価格取得API Request.
 * 
 * @author yunhui.zhao
 * @date 02/11/2022
 */
public class ConfirmForeignStockClosedMarginOrderReq implements BaseRequest {
    
    public ConfirmForeignStockClosedMarginOrderReq() {
        
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
        
        // 取引パスワード
        private String trade_password;
        
        // チケット
        private String ticket;
        
        // チャネル
        private String channel;
        
        public String getToken() {
            
            return token;
        }
        
        /**
         * @param token 必須「{部店}3桁 + "-" + {口座}7桁」
         */
        public void setToken(String token) {
            
            this.token = token;
        }
        
        public String getTrade_password() {
            
            return trade_password;
        }
        
        /**
         * @param trade_password 必須「取引パスワード」
         */
        public void setTrade_password(String trade_password) {
            
            this.trade_password = trade_password;
        }
        
        public String getTicket() {
            
            return ticket;
        }
        
        /**
         * @param ticket 必須「チケット」
         */
        public void setTicket(String ticket) {
            
            this.ticket = ticket;
        }
        
        public String getChannel() {
            
            return channel;
        }
        
        /**
         * @param channel 必須「チャネル」
         */
        public void setChannel(String channel) {
            
            this.channel = channel;
        }
    }
    
    public class Parameter {
        
        public Parameter() {
            
        }
        
        // 信用注文情報
        private MarginOrderInputForConfirmForeignStockClosedMarginOrder marginOrder;
        
        // 返済相手建玉明細情報
        private List<ClosedPositionInput> positions;
        
        public MarginOrderInputForConfirmForeignStockClosedMarginOrder getMarginOrder() {
            
            return marginOrder;
        }
        
        /**
         * @param marginOrder 必須「信用注文情報」
         */
        public void setMarginOrder(MarginOrderInputForConfirmForeignStockClosedMarginOrder marginOrder) {
            
            this.marginOrder = marginOrder;
        }
        
        /**
         * @return the positions
         */
        public List<ClosedPositionInput> getPositions() {
            
            return positions;
        }
        
        /**
         * @param positions 必須「返済相手建玉明細情報」
         */
        public void setPositions(List<ClosedPositionInput> positions) {
            
            this.positions = positions;
        }
    }
}
