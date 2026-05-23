package com.sbisec.helios.ap.athena.protocol.information;

import com.sbisec.helios.ap.athena.annotation.SetAttributeName;
import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * @Description マーケット価格情報サービス - マーケット価格取得用チケット登録API Request.
 * 
 * @author SCSK
 * @date 03/01/2024
 */
public class CreateMarketPriceTicketReq implements BaseRequest {
    
    public CreateMarketPriceTicketReq() {
    
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
        
        // ip
        private String ip;
        
        // App User Agent
        @SetAttributeName(name = "X-App-User-Agent")
        private String appUserAgent;
        
        public String getToken() {
            
            return token;
        }
        
        /**
         * @param token 必須「{部店}3桁 + "-" + {口座}7桁」
         */
        public void setToken(String token) {
            
            this.token = token;
        }
        
        public String getIp() {
            
            return ip;
        }
        
        /**
         * @param ip 必須「IPv4 形式」
         */
        public void setIp(String ip) {
            
            this.ip = ip;
        }
        
        public String getAppUserAgent() {
            
            return appUserAgent;
        }
        
        /**
         * @param appUserAgent 必須 「App User Agent」
         */
        public void setAppUserAgent(String appUserAgent) {
            
            this.appUserAgent = appUserAgent;
        }
    }
    
    public class Parameter {
        
        public Parameter() {
        
        }
        
        // 国コード
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
