package com.sbisec.helios.ap.athena.protocol.fstock.securities;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;
import com.sbisec.helios.ap.athena.protocol.common.Page;

/**
 * @Description 外国株式銘柄サービス - 外国株式銘柄検索API Request.
 *
 * @author xin.li
 * @version 1.0
 * @date 02/11/2022
 */
public class ListForeignStockSecuritiesReq implements BaseRequest {
    
    public ListForeignStockSecuritiesReq() {
    
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
    }
    
    public class Parameter {
        
        public Parameter() {
        
        }
        
        // 国コード
        private String countryCode;
        
        // 検索用キーワード
        private String searchKeyword;
        
        // マッチ種別
        private String matchType;
        
        // 市場コード
        private String marketCode;
        
        // ページ
        private Page page;
        
        // 信用取引コード
        private String marginCode;
        
        public String getCountryCode() {
            
            return countryCode;
        }
        
        /**
         * @param countryCode 「非必須」 国コード
         */
        public void setCountryCode(String countryCode) {
            
            this.countryCode = countryCode;
        }
        
        public String getSearchKeyword() {
            
            return searchKeyword;
        }
        
        /**
         * @param searchKeyword 「非必須」 検索用キーワード（最大 384 バイト、大体 128 つ UTF-8 文字に相当する）
         */
        public void setSearchKeyword(String searchKeyword) {
            
            this.searchKeyword = searchKeyword;
        }
        
        public String getMatchType() {
            
            return matchType;
        }
        
        /**
         * @param matchType 「非必須」 マッチ種別 入力しない場合、「1」とする。デフォルト値: 1
         */
        public void setMatchType(String matchType) {
            
            this.matchType = matchType;
        }
        
        public String getMarketCode() {
            
            return marketCode;
        }
        
        /**
         * @param marketCode 「非必須」 市場コード
         */
        public void setMarketCode(String marketCode) {
            
            this.marketCode = marketCode;
        }
        
        public Page getPage() {
            
            return page;
        }
        
        /**
         * @param page 「非必須」 ページ
         */
        public void setPage(Page page) {
            
            this.page = page;
        }
        
        public String getMarginCode() {
            
            return marginCode;
        }
        
        /**
         * @param marginCode 「非必須」 信用取引コード
         */
        public void setMarginCode(String marginCode) {
            
            this.marginCode = marginCode;
        }
        
    }
}
