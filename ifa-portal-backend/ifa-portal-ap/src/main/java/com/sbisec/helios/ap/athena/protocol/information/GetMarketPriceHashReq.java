package com.sbisec.helios.ap.athena.protocol.information;

import com.sbisec.helios.ap.athena.annotation.UrlParm;
import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * @Description マーケット価格情報サービス - マーケット価格ハッシュ取得API Request.
 * 
 * @author yunhui.zhao
 * @date 02/11/2022
 */
public class GetMarketPriceHashReq implements BaseRequest {
    
    public GetMarketPriceHashReq() {
        
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
        
        // チケット
        private String ticket;
        
        public String getToken() {
            
            return token;
        }
        
        /**
         * @param token 必須「{部店}3桁 + "-" + {口座}7桁」
         */
        public void setToken(String token) {
            
            this.token = token;
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
    }
    
    public class Parameter {
        
        public Parameter() {
        
        }
        
        // 国コード
        @UrlParm(name = "country_code")
        private String countryCode;
        
        public String getCountryCode() {
            
            return countryCode;
        }
        
        /**
         * @param countryCode 必須「国コード」
         */
        public void setCountryCode(String countryCode) {
            
            this.countryCode = countryCode;
        }
    }
}
