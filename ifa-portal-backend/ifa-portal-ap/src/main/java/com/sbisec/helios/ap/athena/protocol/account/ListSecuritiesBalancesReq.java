package com.sbisec.helios.ap.athena.protocol.account;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;
import com.sbisec.helios.ap.athena.protocol.common.Page;

/**
 * Description: 外貨建商品保有証券一覧取得 Request
 *
 * @author xiu.yan
 * @version 1.0
 * @date 2/15/2022
 */
public class ListSecuritiesBalancesReq implements BaseRequest {
    
    public ListSecuritiesBalancesReq() {
    
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
        private String specificAccountCode;
        
        // ページング
        private Page page;
        
        /**
         * @param productCode 「非必須」 enums - 商品コード - ProductCode
         */
        public void setProductCode(String productCode) {
            
            this.productCode = productCode;
        }
        
        public String getProductCode() {
            
            return productCode;
        }
        
        /**
         * @param countryCode 「非必須」 enums - 国コード - CountryCode
         */
        public void setCountryCode(String countryCode) {
            
            this.countryCode = countryCode;
        }
        
        public String getCountryCode() {
            
            return countryCode;
        }
        
        /**
         * @param currencyCode 「非必須」 enums - 通貨コード - CurrencyCode
         */
        public void setCurrencyCode(String currencyCode) {
            
            this.currencyCode = currencyCode;
        }
        
        public String getCurrencyCode() {
            
            return currencyCode;
        }
        
        /**
         * @param specificAccountCode 「非必須」 enums - 預り区分 - SpecificAccount
         */
        public void setSpecificAccountCode(String specificAccountCode) {
            
            this.specificAccountCode = specificAccountCode;
        }
        
        public String getSpecificAccountCode() {
            
            return specificAccountCode;
        }
        
        /**
         * @param page 「非必須」 ページング
         */
        public void setPage(Page page) {
            
            this.page = page;
        }
        
        public Page getPage() {
            
            return page;
        }
        
    }
}
