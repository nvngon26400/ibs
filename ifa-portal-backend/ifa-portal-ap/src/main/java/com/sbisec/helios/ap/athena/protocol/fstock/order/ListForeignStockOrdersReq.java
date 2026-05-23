package com.sbisec.helios.ap.athena.protocol.fstock.order;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;
import com.sbisec.helios.ap.athena.protocol.common.Page;

/**
 * 外国株式注文一覧取得 Request
 * 
 * @author shuchen.xin
 * @date 02/16/2022
 */
public class ListForeignStockOrdersReq implements BaseRequest {
    
    public ListForeignStockOrdersReq() {
        
    }
    
    private Header header = new Header();
    
    private Parameter parameter = new Parameter();
    
    @SuppressWarnings("unchecked")
    public Header getHeader() {
        
        return header;
    }
    
    public void setHeader(Header header) {
        
        this.header = header;
    }
    
    @SuppressWarnings("unchecked")
    public Parameter getParameter() {
        
        return parameter;
    }
    
    public void setParameter(Parameter parameter) {
        
        this.parameter = parameter;
    }
    
    public class Header {
        
        public Header() {
            
        }
        
        // {部店}3桁 + "-" + {口座}7桁
        private String token;
        
        /**
         * @return the token
         */
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
        
        // 国コード
        private String countryCode;
        
        // 商品種別
        private String foreignStockProductType;
        
        // 銘柄コード
        private String securitiesCode;
        
        // 口座分類
        private String accountKind;
        
        // 注文日(From) "yyyy-MM-dd"形式
        private String orderDateFrom;
        
        // 注文日(To) "yyyy-MM-dd"形式
        private String orderDateTo;
        
        // 注文日基準種別
        private String orderDateType;
        
        // 注文状況
        private List<String> orderStatuses;
        
        // ページ
        private Page page;
        
        /**
         * @return the countryCode
         */
        public String getCountryCode() {
            
            return countryCode;
        }
        
        /**
         * @param countryCode 「必須」 enums - 国コード - CountryCode
         */
        public void setCountryCode(String countryCode) {
            
            this.countryCode = countryCode;
        }
        
        /**
         * @return the foreignStockProductType
         */
        public String getForeignStockProductType() {
            
            return foreignStockProductType;
        }
        
        /**
         * @param foreignStockProductType 「非必須」 enums - 口座分類 - ForeignStockProductType
         */
        public void setForeignStockProductType(String foreignStockProductType) {
            
            this.foreignStockProductType = foreignStockProductType;
        }
        
        /**
         * @return the securitiesCode
         */
        public String getSecuritiesCode() {
            
            return securitiesCode;
        }
        
        /**
         * @param securitiesCode 「非必須」 銘柄コード
         */
        public void setSecuritiesCode(String securitiesCode) {
            
            this.securitiesCode = securitiesCode;
        }
        
        /**
         * @return the accountKind
         */
        public String getAccountKind() {
            
            return accountKind;
        }
        
        /**
         * @param accountKind 「非必須」 enums - 口座分類 - AccountKind
         */
        public void setAccountKind(String accountKind) {
            
            this.accountKind = accountKind;
        }
        
        /**
         * @return the orderDateFrom
         */
        public String getOrderDateFrom() {
            
            return orderDateFrom;
        }
        
        /**
         * @param orderDateFrom 「必須」 注文日(From) "yyyy-MM-dd"形式
         */
        public void setOrderDateFrom(String orderDateFrom) {
            
            this.orderDateFrom = orderDateFrom;
        }
        
        /**
         * @return the orderDateTo
         */
        public String getOrderDateTo() {
            
            return orderDateTo;
        }
        
        /**
         * @param orderDateTo 「必須」 注文日(To) "yyyy-MM-dd"形式
         */
        public void setOrderDateTo(String orderDateTo) {
            
            this.orderDateTo = orderDateTo;
        }
        
        /**
         * @return the orderDateType
         */
        public String getOrderDateType() {
            
            return orderDateType;
        }
        
        /**
         * @param orderDateType 「必須」 注文日基準種別
         */
        public void setOrderDateType(String orderDateType) {
            
            this.orderDateType = orderDateType;
        }
        
        /**
         * @return the orderStatuses
         */
        public List<String> getOrderStatuses() {
            
            return orderStatuses;
        }
        
        /**
         * @param orderStatuses 「非必須」注文状況
         */
        public void setOrderStatuses(List<String> orderStatuses) {
            
            this.orderStatuses = orderStatuses;
        }
        
        /**
         * @return the page
         */
        public Page getPage() {
            
            return page;
        }
        
        /**
         * @param page 「非必須」 ページ
         */
        public void setPage(Page page) {
            
            this.page = page;
        }
    }
}
