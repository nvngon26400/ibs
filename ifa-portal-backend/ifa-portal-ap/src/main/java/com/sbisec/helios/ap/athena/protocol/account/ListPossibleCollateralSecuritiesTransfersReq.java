package com.sbisec.helios.ap.athena.protocol.account;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * 代用有価証券振替可能一覧取得API Request
 * 
 * @author shuchen.xin
 * @date 03/31/2022
 */
public class ListPossibleCollateralSecuritiesTransfersReq implements BaseRequest {
    
    public ListPossibleCollateralSecuritiesTransfersReq() {
    
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
    
    public class Parameter {
        
        public Parameter() {
        
        }
        
        private String productCode; // 商品コード
        
        private String countryCode; // 国コード
        
        private String currencyCode; // 通貨コード
        
        public String getProductCode() {
            
            return productCode;
        }
        
        /**
         * @param productCode 「必須」 商品コード - enums - ProductCode
         */
        public void setProductCode(String productCode) {
            
            this.productCode = productCode;
        }
        
        public String getCountryCode() {
            
            return countryCode;
        }
        
        /**
         * @param countryCode 「必須」 国コード - enums - CountryCode
         */
        public void setCountryCode(String countryCode) {
            
            this.countryCode = countryCode;
        }
        
        public String getCurrencyCode() {
            
            return currencyCode;
        }
        
        /**
         * @param currencyCode 「必須」 通貨コード - enums - CurrencyCode
         */
        public void setCurrencyCode(String currencyCode) {
            
            this.currencyCode = currencyCode;
        }
        
    }
}
