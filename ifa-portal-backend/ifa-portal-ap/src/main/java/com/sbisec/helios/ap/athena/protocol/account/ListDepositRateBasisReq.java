package com.sbisec.helios.ap.athena.protocol.account;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * Description: リアルタイム委託保証金率取得API Request

 * @author SCSK 矢口
    2023/12/1 移植
 */
public class ListDepositRateBasisReq implements BaseRequest {
    
    public ListDepositRateBasisReq() {
    
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
        
        public Header() {
        
        }
        
        // token
        private String token;
        
        // チケット
        private String ticket;
        
        /**
         * @param token 「必須」 {部店}3桁 + "-" + {口座}7桁を設定
         */
        public void setToken(String token) {
            
            this.token = token;
        }
        
        public String getToken() {
            
            return token;
        }
        
        /**
         * @param ticket チケット
         */
        public void setTicket(String ticket) {
            
            this.ticket = ticket;
        }
        
        public String getTicket() {
            
            return ticket;
        }
    }
    
    /**
     * @author SCSK
     *
     */
    public class Parameter {
        
        public Parameter() {
        
        }
        
        // 国コード
        private String countryCode;
        
        /**
         * @param countryCode 「必須」 enums - 国コード - CountryCode
         */
        public void setCountryCode(String countryCode) {
            
            this.countryCode = countryCode;
        }
        
        public String getCountryCode() {
            
            return countryCode;
        }
    }
}
