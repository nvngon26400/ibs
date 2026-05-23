package com.sbisec.helios.ap.athena.protocol.account;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * Description:余力サービス 外国株式信用建余力詳細取得API Request

 * @author SCSK 矢口
    2023/12/1 新規作成
 */
public class GetMarginPowerDetailReq implements BaseRequest {
    
    public GetMarginPowerDetailReq() {
    
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
        
        public String getToken() {
            
            return token;
        }
        
        /**
         * @param token 「必須」 {部店}3桁 + "-" + {口座}7桁を設定
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
        
        public Parameter() {
        
        }
        
        // 国コード
        private String countryCode;
        
        public String getCountryCode() {
            
            return countryCode;
        }
        
        /**
         * @param countryCode 「必須」 enums - 国コード - CountryCode
         */
        public void setCountryCode(String countryCode) {
            
            this.countryCode = countryCode;
        }
    }
}
