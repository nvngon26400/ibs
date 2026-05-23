package com.sbisec.helios.ap.athena.protocol.account;

import com.sbisec.helios.ap.athena.annotation.UrlParm;
import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * Description: 外貨建商品保有証券取得 Request
 *
 * @author xin.huang
 * @version 1.0
 * @date 5/23/2022
 */
public class GetSecuritiesBalanceReq implements BaseRequest {
    
    public GetSecuritiesBalanceReq() {
    
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
        
        // 部店コード(3桁) + '-' + 顧客コード(7桁)を設定
        private String token;
        
        // チケット
        private String ticket;
        
        /**
         * @param token 「必須」 部店コード(3桁) + '-' + 顧客コード(7桁)を設定
         */
        public void setToken(String token) {
            
            this.token = token;
        }
        
        public String getToken() {
            
            return token;
        }
        
        /**
         * @param ticket 「必須」 チケット
         */
        public void setTicket(String ticket) {
            
            this.ticket = ticket;
        }
        
        public String getTicket() {
            
            return ticket;
        }
    }
    
    public class Parameter {
        
        public Parameter() {
        
        }
        
        // 商品コード
        private String productCode;
        
        // 国コード
        private String countryCode;
        
        // 通貨コード
        private String currencyCode;
        
        // 預り区分
        @UrlParm(name = "specific_account_code")
        private String specificAccountCode;
        
        // 銘柄コード
        @UrlParm(name = "securities_code")
        private String securitiesCode;
        
        /**
         * @param productCode 「必須」 enums - 商品コード - ProductCode
         */
        public void setProductCode(String productCode) {
            
            this.productCode = productCode;
        }
        
        public String getProductCode() {
            
            return productCode;
        }
        
        /**
         * @param countryCode 「必須」 enums - 国コード - CountryCode
         */
        public void setCountryCode(String countryCode) {
            
            this.countryCode = countryCode;
        }
        
        public String getCountryCode() {
            
            return countryCode;
        }
        
        /**
         * @param currencyCode 「必須」 enums - 通貨コード - CurrencyCode
         */
        public void setCurrencyCode(String currencyCode) {
            
            this.currencyCode = currencyCode;
        }
        
        public String getCurrencyCode() {
            
            return currencyCode;
        }
        
        /**
         * @param specificAccountCode 「必須」 enums - 預り区分 - SpecificAccount
         */
        public void setSpecificAccountCode(String specificAccountCode) {
            
            this.specificAccountCode = specificAccountCode;
        }
        
        public String getSpecificAccountCode() {
            
            return specificAccountCode;
        }
        
        /**
         * @param securitiesCode 「必須」 銘柄コード
         */
        public void setSecuritiesCode(String securitiesCode) {
            
            this.securitiesCode = securitiesCode;
        }
        
        public String getSecuritiesCode() {
            
            return securitiesCode;
        }
        
    }
}
