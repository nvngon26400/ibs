package com.sbisec.helios.ap.athena.protocol.account;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;
import com.sbisec.helios.ap.athena.protocol.common.Page;

/**
 * 外国株式信用建玉明細一覧取得 Request
 * 
 * @author SCSK
 * @date 03/01/2024
 */
public class ListMarginPositionsReq implements BaseRequest {
    
    public ListMarginPositionsReq() {
        
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
        
        // APP-GWより部店コード(3桁) + '-' + 顧客コード(7桁)を設定
        private String token;
        
        // ticket
        private String ticket;
        
        public String getToken() {
            
            return token;
        }
        
        /**
         * @param token 「必須」 部店コード(3桁) + '-' + 顧客コード(7桁)を設定
         */
        public void setToken(String token) {
            
            this.token = token;
        }
        
        /**
         * @return the ticket
         */
        public String getTicket() {
            
            return ticket;
        }
        
        /**
         * @param ticket 「必須」 ticket
         */
        public void setTicket(String ticket) {
            
            this.ticket = ticket;
        }
        
    }
    
    public class Parameter {
        
        public Parameter() {
        
        }
        
        // 国コード
        private String countryCode;
        
        // 銘柄コード
        private String securitiesCode;
        
        // 売買区分
        private String buySellCode;
        
        // 預り区分
        private String specificAccountCode;
        
        // 信用期日
        private String marginCloseLimitType;
        
        // 国内約定日 "yyyy-MM-dd"形式
        private String tradeDate;
        
        // 現地約定日 "yyyy-MM-dd"形式
        private String frnTradeDate;
        
        // 新規建単価（外貨） サイズ範囲: 0-999999999999.9999
        private String frnPositionPrice;
        
        // ページング
        private Page page;
        
        /**
         * @return the countryCode
         */
        public String getCountryCode() {
            
            return countryCode;
        }
        
        /**
         * @param countryCode 「必須」 国コード
         */
        public void setCountryCode(String countryCode) {
            
            this.countryCode = countryCode;
        }
        
        /**
         * @return securitiesCode 銘柄コード
         */
        public String getSecuritiesCode() {
            
            return securitiesCode;
        }
        
        /**
         * @param securitiesCode 「非必須」銘柄コード
         */
        public void setSecuritiesCode(String securitiesCode) {
            
            this.securitiesCode = securitiesCode;
        }
        
        /**
         * @return buySellCode 売買区分
         */
        public String getBuySellCode() {
            
            return buySellCode;
        }
        
        /**
         * @param buySellCode 「非必須」売買区分
         */
        public void setBuySellCode(String buySellCode) {
            
            this.buySellCode = buySellCode;
        }
        
        /**
         * @return specificAccountCode 預り区分
         */
        public String getSpecificAccountCode() {
            
            return specificAccountCode;
        }
        
        /**
         * @param specificAccountCode 「非必須」預り区分
         */
        public void setSpecificAccountCode(String specificAccountCode) {
            
            this.specificAccountCode = specificAccountCode;
        }
        
        /**
         * @return marginCloseLimitType 信用期日
         */
        public String getMarginCloseLimitType() {
            
            return marginCloseLimitType;
        }
        
        /**
         * @param marginCloseLimitType 「非必須」信用期日
         */
        public void setMarginCloseLimitType(String marginCloseLimitType) {
            
            this.marginCloseLimitType = marginCloseLimitType;
        }
        
        /**
         * @return tradeDate 国内約定日
         */
        public String getTradeDate() {
            
            return tradeDate;
        }
        
        /**
         * @param tradeDate 「非必須」 国内約定日 "yyyy-MM-dd"形式
         */
        public void setTradeDate(String tradeDate) {
            
            this.tradeDate = tradeDate;
        }
        
        /**
         * @return frnTradeDate 現地約定日 "yyyy-MM-dd"形式
         */
        public String getFrnTradeDate() {
            
            return frnTradeDate;
        }
        
        /**
         * @param frnTradeDate 「非必須」 現地約定日 "yyyy-MM-dd"形式
         */
        public void setFrnTradeDate(String frnTradeDate) {
            
            this.frnTradeDate = frnTradeDate;
        }
        
        /**
         * @return frnPositionPrice 新規建単価（外貨）
         */
        public String getFrnPositionPrice() {
            
            return frnPositionPrice;
        }
        
        /**
         * @param frnPositionPrice 「非必須」 新規建単価（外貨） サイズ範囲: 0-999999999999.9999
         */
        public void setFrnPositionPrice(String frnPositionPrice) {
            
            this.frnPositionPrice = frnPositionPrice;
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
