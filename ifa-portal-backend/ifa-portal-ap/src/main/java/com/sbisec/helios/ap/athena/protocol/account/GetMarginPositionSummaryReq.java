package com.sbisec.helios.ap.athena.protocol.account;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * 外国株式信用建玉サマリ取得API Request.
 * 
 * @author yunhui.zhao
 * @date 03/11/2022
 */
public class GetMarginPositionSummaryReq implements BaseRequest {
    
    public GetMarginPositionSummaryReq() {
    
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
        
        // チケット
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
        
        public String getTicket() {
            
            return ticket;
        }
        
        /**
         * @param ticket 「必須」 チケット
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
        
        // 信用期日
        private String marginCloseLimitType;
        
        // 預り区分
        private String specificAccountCode;
        
        // 国内約定日(yyyy-MM-dd)
        private String tradeDate;
        
        // 現地約定日(yyyy-MM-dd)
        private String frnTradeDate;
        
        public String getCountryCode() {
            
            return countryCode;
        }
        
        /**
         * @param countryCode 「必須」 国コード
         */
        public void setCountryCode(String countryCode) {
            
            this.countryCode = countryCode;
        }
        
        public String getSecuritiesCode() {
            
            return securitiesCode;
        }
        
        /**
         * @param countryCode 「必須」 銘柄コード
         */
        public void setSecuritiesCode(String securitiesCode) {
            
            this.securitiesCode = securitiesCode;
        }
        
        public String getBuySellCode() {
            
            return buySellCode;
        }
        
        /**
         * @param countryCode 「必須」 売買区分
         */
        public void setBuySellCode(String buySellCode) {
            
            this.buySellCode = buySellCode;
        }
        
        public String getMarginCloseLimitType() {
            
            return marginCloseLimitType;
        }
        
        /**
         * @param countryCode 「必須」 信用期日
         */
        public void setMarginCloseLimitType(String marginCloseLimitType) {
            
            this.marginCloseLimitType = marginCloseLimitType;
        }
        
        public String getSpecificAccountCode() {
            
            return specificAccountCode;
        }
        
        /**
         * @param countryCode 「必須」 預り区分
         */
        public void setSpecificAccountCode(String specificAccountCode) {
            
            this.specificAccountCode = specificAccountCode;
        }
        
        public String getTradeDate() {
            
            return tradeDate;
        }
        
        /**
         * @param tradeDate 国内約定日(yyyy-MM-dd)
         */
        public void setTradeDate(String tradeDate) {
            
            this.tradeDate = tradeDate;
        }
        
        public String getFrnTradeDate() {
            
            return frnTradeDate;
        }
        
        /**
         * @param frnTradeDate 現地約定日(yyyy-MM-dd)
         */
        public void setFrnTradeDate(String frnTradeDate) {
            
            this.frnTradeDate = frnTradeDate;
        }
    }
}
