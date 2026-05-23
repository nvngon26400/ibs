package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * 余力サービス - 外国株式信用建玉明細取得API.
 * 
 * @author mengzhe.li
 * @date: 03/09/2022
 */
public class GetMarginPositionReq implements BaseRequest {
    
    public GetMarginPositionReq() {
    
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
        
        private String token;// token
        
        private String ticket;// チケット
        
        public String getToken() {
            
            return token;
        }
        
        /**
         * @param token 「必須」 部店コード(3桁) + '-' + 口座コード(7桁)を設定
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
        
        // 国内約定日
        private String tradeDate;
        
        // 現地約定日
        private String frnTradeDate;
        
        // 新規建単価（外貨）
        private String frnPositionPrice;
        
        // 新規建単価（円貨）
        private String positionPrice;
        
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
         * @param securitiesCode 「必須」 銘柄コード
         */
        public void setSecuritiesCode(String securitiesCode) {
            
            this.securitiesCode = securitiesCode;
        }
        
        public String getBuySellCode() {
            
            return buySellCode;
        }
        
        /**
         * @param buySellCode 「必須」 売買区分
         */
        public void setBuySellCode(String buySellCode) {
            
            this.buySellCode = buySellCode;
        }
        
        public String getMarginCloseLimitType() {
            
            return marginCloseLimitType;
        }
        
        /**
         * @param marginCloseLimitType 「必須」 信用期日
         */
        public void setMarginCloseLimitType(String marginCloseLimitType) {
            
            this.marginCloseLimitType = marginCloseLimitType;
        }
        
        public String getSpecificAccountCode() {
            
            return specificAccountCode;
        }
        
        /**
         * @param specificAccountCode 「必須」 預り区分
         */
        public void setSpecificAccountCode(String specificAccountCode) {
            
            this.specificAccountCode = specificAccountCode;
        }
        
        public String getTradeDate() {
            
            return tradeDate;
        }
        
        /**
         * @param tradeDate 「必須」 国内約定日
         */
        public void setTradeDate(String tradeDate) {
            
            this.tradeDate = tradeDate;
        }
        
        public String getFrnTradeDate() {
            
            return frnTradeDate;
        }
        
        /**
         * @param frnTradeDate 「必須」 現地約定日
         */
        public void setFrnTradeDate(String frnTradeDate) {
            
            this.frnTradeDate = frnTradeDate;
        }
        
        public String getFrnPositionPrice() {
            
            return frnPositionPrice;
        }
        
        /**
         * @param frnPositionPrice 「必須」 新規建単価（外貨
         */
        public void setFrnPositionPrice(String frnPositionPrice) {
            
            this.frnPositionPrice = frnPositionPrice;
        }
        
        public String getPositionPrice() {
            
            return positionPrice;
        }
        
        /**
         * @param positionPrice 「必須」 新規建単価（円貨
         */
        public void setPositionPrice(String positionPrice) {
            
            this.positionPrice = positionPrice;
        }
        
    }
}
